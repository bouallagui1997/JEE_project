package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Tickets database table.
 * 
 */
@Entity
@Table(name="Tickets")
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TicketId")
	private int ticketId;

	
	@Column(name="approved")
	private int approved;
	
	
	
	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name="EventId")

	private Event event;

	public Ticket() {
	}
	

	public Ticket(Event event) {
		super();
		this.event = event;
	}


	public Ticket(int approved, Event event) {
		super();
		this.approved = approved;
		this.event = event;
	}


	public Ticket(int approved) {
		super();
		this.approved = approved;
	}


	public int getApproved() {
		return approved;
	}


	public void setApproved(int approved) {
		this.approved = approved;
	}


	public int getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}