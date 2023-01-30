package com.medifinder.medifinder.Utils.Service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import sendinblue.ApiClient;
import sendinblue.ApiException;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
@NoArgsConstructor
public class EmailService {

    @Value("${sib.api-key}")
    private String SibApiKey;

    @Value("${email.sender.email}")
    private String senderEmail;

    @Value("${email.sender.name}")
    private String senderName;

    @Value("${web_app.url}")
    private String webAppUrl;

    public List<String> sendVerifyEmail(String toEmail, String toName, String token) throws ApiException {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        // Configure API key authorization: api-key
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(SibApiKey);

        TransactionalEmailsApi api = new TransactionalEmailsApi();

        SendSmtpEmailSender sender = new SendSmtpEmailSender();
        sender.setEmail(senderEmail);
        sender.setName(senderName);

        List<SendSmtpEmailTo> toList = new ArrayList<SendSmtpEmailTo>();
        SendSmtpEmailTo to = new SendSmtpEmailTo();
        to.setEmail(toEmail);
        to.setName(toName);
        toList.add(to);

        Properties params = new Properties();
        params.setProperty("url", webAppUrl);
        params.setProperty("target", webAppUrl + "/auth/confirm?token=" + token);
        params.setProperty("subject", "Medicine Finder - Verify your email");

        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
        sendSmtpEmail.setSender(sender);
        sendSmtpEmail.setTo(toList);
        sendSmtpEmail.setParams(params);
        sendSmtpEmail.setSubject("{{params.subject}}");

        sendSmtpEmail.setHtmlContent("""
                <!doctypehtml><meta charset=utf-8><meta content="ie=edge"http-equiv=x-ua-compatible><title>Email Confirmation</title><meta content="width=device-width,initial-scale=1"name=viewport><style>@media screen{@font-face{font-family:'Source Sans Pro';font-style:normal;font-weight:400;src:local('Source Sans Pro Regular'),local('SourceSansPro-Regular'),url(https://fonts.gstatic.com/s/sourcesanspro/v10/ODelI1aHBYDBqgeIAH2zlBM0YzuT7MdOe03otPbuUS0.woff) format('woff')}@font-face{font-family:'Source Sans Pro';font-style:normal;font-weight:700;src:local('Source Sans Pro Bold'),local('SourceSansPro-Bold'),url(https://fonts.gstatic.com/s/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGFkQc6VGVFSmCnC_l7QZG60.woff) format('woff')}}a,body,table,td{-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%}table,td{mso-table-rspace:0;mso-table-lspace:0}img{-ms-interpolation-mode:bicubic}a[x-apple-data-detectors]{font-family:inherit!important;font-size:inherit!important;font-weight:inherit!important;line-height:inherit!important;color:inherit!important;text-decoration:none!important}div[style*="margin: 16px 0;"]{margin:0!important}body{width:100%!important;height:100%!important;padding:0!important;margin:0!important}table{border-collapse:collapse!important}a{color:#1a82e2}img{height:auto;line-height:100%;text-decoration:none;border:0;outline:0}</style><body style=background-color:#e9ecef><div class=preheader style=display:none;max-width:0;max-height:0;overflow:hidden;font-size:1px;line-height:1px;color:#fff;opacity:0>Medicine Finder</div><table border=0 cellpadding=0 cellspacing=0 width=100%><tr><td align=center bgcolor=#e9ecef><!--[if (gte mso 9)|(IE)]><table border=0 cellpadding=0 cellspacing=0 width=600 align=center><tr><td align=center valign=top width=600><![endif]--><table border=0 cellpadding=0 cellspacing=0 width=100% style=max-width:600px><tr><td align=center style="padding:36px 24px"valign=top></table><!--[if (gte mso 9)|(IE)]><![endif]--><tr><td align=center bgcolor=#e9ecef><!--[if (gte mso 9)|(IE)]><table border=0 cellpadding=0 cellspacing=0 width=600 align=center><tr><td align=center valign=top width=600><![endif]--><table border=0 cellpadding=0 cellspacing=0 width=100% style=max-width:600px><tr><td align=left bgcolor=#ffffff style="padding:36px 24px 0;font-family:'Source Sans Pro',Helvetica,Arial,sans-serif;border-top:3px solid #d4dadf"><h1 style=margin:0;font-size:32px;font-weight:700;letter-spacing:-1px;line-height:48px>Confirm Your Email Address</h1></table><!--[if (gte mso 9)|(IE)]><![endif]--><tr><td align=center bgcolor=#e9ecef><!--[if (gte mso 9)|(IE)]><table border=0 cellpadding=0 cellspacing=0 width=600 align=center><tr><td align=center valign=top width=600><![endif]--><table border=0 cellpadding=0 cellspacing=0 width=100% style=max-width:600px><tr><td align=left bgcolor=#ffffff style="padding:24px;font-family:'Source Sans Pro',Helvetica,Arial,sans-serif;font-size:16px;line-height:24px"><p style=margin:0>Tap the button below to confirm your email address. If you didn't create an account with <a href={{params.url}}>Medicine Finder</a>, you can ignore this email.<tr><td align=left bgcolor=#ffffff><table border=0 cellpadding=0 cellspacing=0 width=100%><tr><td align=center bgcolor=#ffffff style=padding:12px><table border=0 cellpadding=0 cellspacing=0><tr><td align=center bgcolor=#1a82e2 style=border-radius:6px><a href={{params.target}} target=_blank style="display:inline-block;padding:16px 36px;font-family:'Source Sans Pro',Helvetica,Arial,sans-serif;font-size:16px;color:#fff;text-decoration:none;border-radius:6px">Verify Email</a></table></table><tr><td align=left bgcolor=#ffffff style="padding:24px;font-family:'Source Sans Pro',Helvetica,Arial,sans-serif;font-size:16px;line-height:24px"><p style=margin:0>If that doesn't work, copy and paste the following link in your browser:<p style=margin:0><a href={{params.target}} target=_blank>{{params.target}}</a></table><!--[if (gte mso 9)|(IE)]><![endif]--><tr><td align=center bgcolor=#e9ecef style=padding:24px><!--[if (gte mso 9)|(IE)]><table border=0 cellpadding=0 cellspacing=0 width=600 align=center><tr><td align=center valign=top width=600><![endif]--><table border=0 cellpadding=0 cellspacing=0 width=100% style=max-width:600px><tr><td align=center bgcolor=#e9ecef style="padding:12px 24px;font-family:'Source Sans Pro',Helvetica,Arial,sans-serif;font-size:14px;line-height:20px;color:#666"><p style=margin:0>You received this email because we received a new registration for your email. If you didn't register you can safely ignore this email.<tr><td align=center bgcolor=#e9ecef style="padding:12px 24px;font-family:'Source Sans Pro',Helvetica,Arial,sans-serif;font-size:14px;line-height:20px;color:#666"><p style=margin:0>To stop receiving these emails, you can <a href={{params.url}} target=_blank>unsubscribe</a> at any time.</table><!--[if (gte mso 9)|(IE)]><![endif]--></table>                                
                """);

        CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);
        return response.getMessageIds();
    }
}
