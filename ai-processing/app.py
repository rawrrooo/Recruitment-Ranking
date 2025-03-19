from flask import Flask, request
import resume_matcher

app = Flask(__name__)
@app.route("/")
@app.route("/match_resume", methods=["POST"])
def match_resume():
    resume_content = request.data.decode("utf-8")
    match_result = resume_matcher.find_best_match(resume_content)
    return match_result

if __name__ == "__main__":
    app.run(debug=True)
