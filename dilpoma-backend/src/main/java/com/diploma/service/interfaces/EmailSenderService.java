package com.diploma.service.interfaces;

public interface EmailSenderService {
    void sendSimpleMessage(String to, String subject, String message);
    void sendResetLinkPassword(String to, String token);
    void sendNewModerLinkPassword(String to, String token);
}
