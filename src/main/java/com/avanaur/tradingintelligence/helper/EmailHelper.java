package com.avanaur.tradingintelligence.helper;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class EmailHelper {

	public static MimeMessage getMimeMessage(InputStream is) {
		Session s = Session.getDefaultInstance(new Properties());
		try {
			return new MimeMessage(s, is);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
