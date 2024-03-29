package com.answer.base.util;

import com.answer.base.exception.http.MailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class IMailServiceImpl implements IMailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${spring.mail.from}")
    private String from;
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from);
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
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
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
//            使用 thymeleaf 生成 模板
            Context context = new Context();
            context.setVariable("title",subject);
            context.setVariable("content",content);
            String emailContent = templateEngine.process("emailTemplate",context);
//            发送邮件
            helper.setText(emailContent,true);
            mailSender.send(message);

        }catch (Exception e){
            throw new MailException(70001);

        }
    }

    @Override
    public void sendFileMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            // 使用 thymeleaf 生成 模板
            Context context = new Context();
            context.setVariable("title",subject);
            context.setVariable("content",content);
            String emailContent = templateEngine.process("emailTemplate",context);
            helper.setText(emailContent,true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            mailSender.send(message);
        }catch (Exception e){
            throw new MailException(70001);
//            System.out.println(e);
        }
    }
}
