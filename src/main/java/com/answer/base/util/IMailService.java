package com.answer.base.util;

public interface IMailService {
    void sendSimpleMail(String to,String subject,String content);
    void sendHtmlMail(String to,String subject,String content);
    void sendFileMail(String to,String subject,String content,String filePath);
}
