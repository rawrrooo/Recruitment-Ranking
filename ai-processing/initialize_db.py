import sqlite3
import os

db_path = "C:/Recruitment-Ranking/database/recruitment.db"
os.makedirs(os.path.dirname(db_path), exist_ok=True)

conn = sqlite3.connect(db_path)
cursor = conn.cursor()

cursor.execute('''
CREATE TABLE IF NOT EXISTS jobs (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    skills TEXT NOT NULL
)
''')
cursor.execute('''
CREATE TABLE IF NOT EXISTS applicants (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    resume TEXT NOT NULL
)
''')
conn.commit()
conn.close()

print("Database recreated successfully!")
