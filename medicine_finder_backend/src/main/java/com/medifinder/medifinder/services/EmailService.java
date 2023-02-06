package com.medifinder.medifinder.services;

import sendinblue.ApiException;

import java.util.List;


public interface EmailService {
    List<String> sendVerifyEmail(String toEmail, String toName, String token) throws ApiException;
}
