# pyrefly: ignore [missing-import]
from flask import Flask, jsonify
import requests

app = Flask(__name__)
GATEWAY = "http://localhost:8080/api"   # the SAME front door everyone uses

@app.route("/")
@app.route("/report")
def report():
    # plain HTTP calls to the Java services — via the gateway
    students = requests.get(f"{GATEWAY}/students", timeout=3).json()
    courses  = requests.get(f"{GATEWAY}/courses",  timeout=3).json()

    return jsonify({
        "generatedBy":   "Python (Flask)",
        "totalStudents": len(students),
        "totalCourses":  len(courses),
        "studentNames":  [s["name"]  for s in students],
        "courseTitles":  [c["title"] for c in courses],
    })

if __name__ == "__main__":
    app.run(port=5000)