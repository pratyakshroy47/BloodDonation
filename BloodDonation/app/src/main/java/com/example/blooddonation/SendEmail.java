package com.example.blooddonation;

import android.os.Message;
import android.se.omapi.Session;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;


public class SendEmail {

    /*
    public static MimeMessage createEmail(String to, String from, String subject, String bodyText) throws MessagingException {
        Properties props=new Properties();
        Session session=Session.getDefaultInstance(props, null);
        MimeMessage email=new MimeMessage(session);
        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        email.setSubject(subject);
        email.setText(bodyText);
        return email;
    }

    public static Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }

    public static Message sendMessage(Gmail service, String userId, MimeMessage emailContent) throws MessagingException, IOException {
        Message message = createMessageWithEmail(emailContent);
        message = service.users().messages().send(userId, message).execute();
        System.out.println("Message id: " + message.getId());
        System.out.println(message.toPrettyString());
        return message;
    }
     */

}
