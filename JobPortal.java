import java.sql.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class JobPortal {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/recruitment.db")) {
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO jobs (title, skills) VALUES ('Software Engineer', 'Java, Python, AI')");
            System.out.println("Job added: Software Engineer");

            ResultSet rs = stmt.executeQuery("SELECT email FROM applicants");
            while (rs.next()) {
                sendEmail(rs.getString("email"), "New Job Alert!", "A new Software Engineer job tailored with your resume is available!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendEmail(String to, String subject, String body) {
        String from = "your-email@example.com";
        String password = "your-password";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
