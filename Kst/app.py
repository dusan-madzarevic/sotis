from flask import Flask, render_template, url_for, request, redirect
import sys
from learning_spaces.pks import blim

app = Flask(__name__)

@app.route('/', methods=['POST','GET'])
def index():
    if request.method == 'GET':
        return render_template('index.html')
    else:
        return 'post'

@app.route('/<int:id>', methods=['DELETE','PUT'])
def delete(id):
    return redirect('/')

if __name__ == "__main__":
    app.run(debug=True)