package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Events database table.
 * 
 */
@Entity
@Table(name="Events")
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EventId")
	private int eventId;

	@Column(name="DateDebut")
	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	@Column(name="DateFin")
	@Temporal(TemporalType.DATE)
	private Date dateFin;

	private String detailEvent;

	@Column(name="EventCategories")
	private Eventcategories eventCategories;

	@Column(name="TitleEvent")
	private String titleEvent;
	
	
	
	@Column(name="pathfile")
	private String pathfile;

	@Column(name="AddressToPersist")
	private String adress;
	
	




	public String getAdress() {
		return adress;
	}






	public void setAdress(String adress) {
		this.adress = adress;
	}



	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserId")
	private User user;


	
	




	public Event() {
		super();
	}

	
	
	
	
	
	public Event(int eventId, String titleEvent, String detailEvent,Date dateDebut, Date dateFin, 
			Eventcategories eventCategories
			) {
		super();
		this.eventId = eventId;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.detailEvent = detailEvent;
		this.eventCategories = eventCategories;
		this.titleEvent = titleEvent;
	}



	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="event" ,cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
			fetch=FetchType.EAGER)
	private List<Ticket> tickets;

	public Event( int eventId,Date dateDebut, Date dateFin, String detailEvent, Eventcategories eventCategories, String titleEvent,
			User user) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.detailEvent = detailEvent;
		this.eventCategories = eventCategories;
		this.titleEvent = titleEvent;
		this.user = user;
		this.eventId=eventId;
	}
	

	public Event(Date dateDebut, Date dateFin, String detailEvent, Eventcategories eventCategories, String titleEvent,
			User user) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.detailEvent = detailEvent;
		this.eventCategories = eventCategories;
		this.titleEvent = titleEvent;
		this.user = user;
	}



	public Event(Date dateDebut, Date dateFin, String detailEvent, Eventcategories eventCategories, String titleEvent) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.detailEvent = detailEvent;
		this.eventCategories = eventCategories;
		this.titleEvent = titleEvent;
	}

	

		
		
		
		
		
		
		public Event( int eventId,Date dateDebut, Date dateFin, String detailEvent, Eventcategories eventCategories,
				String titleEvent, String pathfile, User user,String AddressToPersist) {
			super();
			this.eventId = eventId;
			this.dateDebut = dateDebut;
			this.dateFin = dateFin;
			this.detailEvent = detailEvent;
			this.eventCategories = eventCategories;
			this.titleEvent = titleEvent;
			this.pathfile = pathfile;
			this.user = user;
			this.adress=AddressToPersist;
		}
	 
		
		
		
		
		
		
		
		
	 
	public Event( Date dateDebut, Date dateFin, String detailEvent, Eventcategories eventCategories,
			String titleEvent, String pathfile, User user, String adress) {
		super();
		
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.detailEvent = detailEvent;
		this.eventCategories = eventCategories;
		this.titleEvent = titleEvent;
		this.pathfile = pathfile;
		this.user = user;
		this.adress=adress;
	}
	
	

	
	public Event( Date dateDebut, Date dateFin, String detailEvent, Eventcategories eventCategories,
			String titleEvent, String pathfile, User user) {
		super();
		
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.detailEvent = detailEvent;
		this.eventCategories = eventCategories;
		this.titleEvent = titleEvent;
		this.pathfile = pathfile;
		this.user = user;
	}


	public String getPathfile() {
		return pathfile;
	}



	public void setPathfile(String pathfile) {
		this.pathfile = pathfile;
	}



	public int getEventId() {
		return this.eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getDetailEvent() {
		return this.detailEvent;
	}

	public void setDetailEvent(String detailEvent) {
		this.detailEvent = detailEvent;
	}

	public Eventcategories getEventCategories() {
		return this.eventCategories;
	}

	public void setEventCategories(Eventcategories eventCategories) {
		this.eventCategories = eventCategories;
	}

	public String getTitleEvent() {
		return this.titleEvent;
	}

	public void setTitleEvent(String titleEvent) {
		this.titleEvent = titleEvent;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setEvent(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setEvent(null);

		return ticket;
	}





}