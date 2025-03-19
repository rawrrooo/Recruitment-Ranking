import sqlite3

def find_best_match(resume_text):
    conn = sqlite3.connect("C:/Recruitment-Ranking/database/recruitment.db")
    cursor = conn.cursor()
    cursor.execute("SELECT title, skills FROM jobs")
    jobs = cursor.fetchall()
    conn.close()

    best_match = "No suitable job found"
    best_score = 0

    for job in jobs:
        job_title, job_skills = job
        job_skills_list = job_skills.split(",")

        match_score = sum(1 for skill in job_skills_list if skill.lower() in resume_text.lower())

        if match_score > best_score:
            best_score = match_score
            best_match = f"Best Match: {job_title} (Score: {best_score}/{len(job_skills_list)})"

    return best_match
