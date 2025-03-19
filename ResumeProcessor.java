import java.nio.file.*;
import java.sql.*;

public class ResumeProcessor {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/recruitment.db")) {
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO applicants (name, email, resume) VALUES ('Alice', 'alice@example.com', 'alice_resume.txt')");

            String resume = new String(Files.readAllBytes(Paths.get("resumes/alice_resume.txt")));
            String aiResult = analyzeResume(resume);
            System.out.println("AI Matching Result: " + aiResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String analyzeResume(String resumeContent) {
        return "Sending resume to AI..."; // Placeholder (connects to Flask)
    }
}
