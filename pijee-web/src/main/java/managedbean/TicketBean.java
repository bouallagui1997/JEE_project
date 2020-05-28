package managedbean;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import model.Event;
import model.Ticket;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.activation.*;


@ManagedBean(name="TicketBean")
@SessionScoped
@ViewScoped
@Named
@RequestScoped
public class TicketBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Ticket> tickets;

	

	@EJB
	services.EventService EventService;
	@EJB
	services.UserService userService;
	
	@EJB
	services.TicketService ticketService;

	
	public List<Ticket> getTickets() {
		
		tickets = ticketService.getTicketsByEventNotApproved(EventBean.idSelectedEvent) ;
	
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	//confirmation
	public String ApprovedTicket(int idticket) throws MalformedURLException, IOException  {
		
		ticketService.ApprovedTicket(idticket);
		//lena bch nabaathou mail de confirmation w yaatih lpdf eli fih ticket 
Event event = ticketService.getEventByIdTicket(idticket);
	
//pdf
Document document = new Document();
try
{
   PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\abbes\\Desktop\\upload\\ticket.pdf"));
   document.open();
   
  // document.add(new Paragraph("hello mr/mss. : "+event.getUser().getNom()));
   
   document.add(new Paragraph("your secret identifier for event:     "+event.getEventId()));
   document.add(new Paragraph("title event:       "+event.getTitleEvent()));
   document.add(new Paragraph("description event:    "+event.getDetailEvent()));
   document.add(new Paragraph("date of begin event:    "+event.getDateDebut()));
   document.add(new Paragraph("date of end event:    "+event.getDateFin()));
   document.add(new Paragraph("the category of this event : "+event.getEventCategories()));
   document.add(new Paragraph("the poster of our event on the next page "));
   
   //image
   
   String imageUrl = "file:///"+event.getPathfile();
   Image image1 = Image.getInstance(new URL(imageUrl));
   document.add(image1);
   image1.setAbsolutePosition(100f, 550f);
   //Scale to new height and new width of image
   image1.scaleAbsolute(200, 200);
   
   
   document.close();
   writer.close();
} catch (DocumentException e)
{
   e.printStackTrace();
} catch (FileNotFoundException e)
{
   e.printStackTrace();
}

//mail
	    try {
		    Properties		props	    = new Properties();
		    
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.port", "587");
		    props.put("mail.smtp.auth", "true");

		    Session session=Session.getInstance(props, new Authenticator()
		    {
		        @Override
		        protected PasswordAuthentication getPasswordAuthentication()
		        {
		            return new PasswordAuthentication("bouallaguiayoub1997@gmail.com", "bouallagui1997");
		        }
		    });
		    
		    
		    Message		message	    = new MimeMessage(session);
		    
		    InternetAddress adrExpediteur=new InternetAddress("bouallaguiayoub1997@gmail.com");
		    message.setFrom(adrExpediteur);
		    
		    
		    InternetAddress	recipient   = new InternetAddress("ayoub.bouallagui@esprit.tn");
		    message.setRecipient(Message.RecipientType.TO, recipient);
		    message.setSubject("ticket confirmed");
	 
		    // Partie 1: Le texte
		    MimeBodyPart mbp1 = new MimeBodyPart();
		    mbp1.setText(" hello mr/mss.   : "+event.getUser().getNom()+"   your request about the event   "+event.getTitleEvent()+"  has been confirmed by the administration with succes and  you find your ticket as PDF on this piece joint, you will find the the localisation of the event in google map on our website. THANK YOU :) ");
	 
		    // Partie 2: Le fichier joint
		    MimeBodyPart mbp2 = new MimeBodyPart();
		    String pdf = "C:\\Users\\abbes\\Desktop\\upload\\ticket.pdf";
		    
		    try {
				mbp2.attachFile(pdf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
		    // On regroupe les deux dans le message
		    MimeMultipart mp = new MimeMultipart();
		    mp.addBodyPart(mbp1);
		    mp.addBodyPart(mbp2);
		    message.setContent(mp);
	 
		    Transport.send(message);
		}
		catch(NoSuchProviderException e) {
		    System.err.println("Pas de transport disponible pour ce protocole");
		    System.err.println(e);
		}
		catch(AddressException e) {
		    System.err.println("Adresse invalide");
		    System.err.println(e);
		}
		catch(MessagingException e) {
		    System.err.println("Erreur dans le message");
		    System.err.println(e);
	
	}
	
	
	    return "/pages/welcome?faces-redirect=true";
	}
	
}
