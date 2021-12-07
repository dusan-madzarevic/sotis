from flask import Flask, render_template, url_for, request, redirect
import sys
from learning_spaces.kst import iita
import pandas as pd
sys.path.append('learning_spaces/')

app = Flask(__name__)

@app.route('/', methods=['POST','GET'])
def index():
    if request.method == 'GET':
        data_frame = pd.DataFrame({'a': [1, 0, 1], 'b': [0, 1, 0], 'c': [0, 1, 1]})
        response = iita(data_frame, v=1)
        print(response)
        return render_template('index.html')
    else:
        return 'post'

@app.route('/<int:id>', methods=['DELETE','PUT'])
def delete(id):
    return redirect('/')

if __name__ == "__main__":
    app.run(debug=True)