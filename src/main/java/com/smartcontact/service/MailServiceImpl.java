package com.smartcontact.service;

import com.smartcontact.model.Contact;
import com.smartcontact.model.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendContactSavedMail(User user, Contact contact) {
        if (user == null || user.getEmail() == null) {
            System.err.println("⚠️ Skipped sending mail: user email not found");
            return;
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(user.getEmail());
            helper.setSubject("📇 SmartContactCRM - New Contact Added");

            String name = (user.getFullName() != null && !user.getFullName().isEmpty())
                    ? user.getFullName()
                    : user.getUsername();

            String htmlBody = """
                    <div style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                        <h2 style="color:#007bff;">SmartContact CRM</h2>
                        <p>Hello <b>%s</b>,</p>
                        <p>🎉 A new contact has been successfully added to your account.</p>
                        
                        <h4>📇 Contact Details:</h4>
                        <ul style="list-style: none; padding-left: 0;">
                            <li><b>Name:</b> %s</li>
                            <li><b>Email:</b> %s</li>
                            <li><b>Phone:</b> %s</li>
                            <li><b>Category:</b> %s</li>
                            <li><b>DOB:</b> %s</li>
                        </ul>

                        <p>Keep managing your contacts smartly and securely 🚀</p>
                        <p style="font-size:0.9em; color:#888;">- SmartContactCRM Team</p>
                    </div>
                    """.formatted(
                    name,
                    safe(contact.getName()),
                    safe(contact.getEmail()),
                    safe(contact.getPhone()),
                    safe(contact.getCategory()),
                    contact.getDob() != null ? contact.getDob().toString() : "N/A"
            );

            helper.setText(htmlBody, true);
            mailSender.send(message);

            System.out.println("✅ Mail sent successfully to: " + user.getEmail());

        } catch (MessagingException e) {
            System.err.println("❌ Failed to send mail: " + e.getMessage());
        }
    }

    private String safe(String value) {
        return value != null ? value : "N/A";
    }
}
