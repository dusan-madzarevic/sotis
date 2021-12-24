from flask import Flask, render_template, url_for, request, redirect
import sys
import pandas as pd

from flask_cors import CORS

from learning_spaces.kst import iita
import pandas as pd
sys.path.append('learning_spaces/')

app = Flask(__name__)
CORS(app)
@app.route('/', methods=['POST','GET'])
def index():
    if request.method == 'GET':
        data_frame = pd.DataFrame({'a': [1, 0, 1], 'b': [0, 1, 0], 'c': [0, 1, 1]})
        print(data_frame)
        response = iita(data_frame, v=1)
        print(response)
        return render_template('index.html')
    else:
        return 'post'

@app.route('/iita', methods=['POST'])
def iitaEndpoint():
    request_data = request.get_json()
    print(request_data)
    results = request_data
    input = {}
    for res in results:
        input[res["studentName"]] = res["answers"]

    data_frame = pd.DataFrame(input)
    response = iita(data_frame, v=1)
    print(pd.Series(response).to_json(orient='values'))
    return pd.Series(response).to_json(orient='values')

@app.route('/<int:id>', methods=['DELETE','PUT'])
def delete(id):
    return redirect('/')

if __name__ == "__main__":
    app.run(debug=True)