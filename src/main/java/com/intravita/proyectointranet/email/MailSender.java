package com.intravita.proyectointranet.email;

import java.sql.SQLException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * MailSender- Clase para gestionar el envio de email de la cuenta de intravita a usuarios
 *
 * @author Intravita
 * @since sprint 2
 */

public class MailSender {

	static UserData userData = new UserData();

	private static Session getSession() throws SQLException{
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userData.getUserName(), userData.getPassword());
			}
		});
		return session;
	}
	
	public void sendMailRecoverPwd(String usuario, String pwd) throws Exception{
		try {

			Message message = new MimeMessage(getSession());
			message.setFrom(new InternetAddress(userData.getUserName()));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(usuario));
			message.setSubject("Servicio de atención al cliente de IntraVita - Contraseña temporal");
			message.setText("Saludos, su nueva contraseña para acceder a su cuenta es: "+pwd+
							"\nPor favor, para una mayor seguridad, cambie esta contraseña por una nueva dentro de la configuración de su perfil.");
			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}





