package com.answer.base.util;

import com.answer.base.exception.http.MailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class IMailServiceImpl implements IMailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.from}")
    private String from;
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[陈湖旧事]").append(subject);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from);
            mailMessage.setTo(to);
            mailMessage.setSubject(stringBuilder.toString());
            mailMessage.setText(content);
            mailSender.send(mailMessage);
        }catch (Exception e){
            throw new MailException(70001);
        }

    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[陈湖旧事]").append(subject);
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(stringBuilder.toString());
            helper.setText(content,true);
            mailSender.send(message);

        }catch (Exception e){
            throw new MailException(70001);

        }
    }

    @Override
    public void sendFileMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[陈湖旧事]").append(subject);
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(stringBuilder.toString());
            helper.setText(content,true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            mailSender.send(message);
        }catch (Exception e){
//            throw new MailException(70001);
            System.out.println(e);
        }
    }
}
