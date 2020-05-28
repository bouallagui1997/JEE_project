package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import interf.remote.TicketServiceRemote;
import model.Event;
import model.Ticket;

@Stateless
@LocalBean
public class TicketService implements TicketServiceRemote {

	@PersistenceContext(unitName = "pijee")
	EntityManager em;

	@Override
	public void AddTicket(Ticket t) {
		em.persist(t);
		
	}

	@Override
	public List<Ticket> getTicketsByEventNotApproved(int IdSelectedEvent) {
	
		TypedQuery<Ticket> query = em.createQuery("Select t from Ticket t where t.approved='0' and t.event.eventId=:IdSelectedEvent",
				Ticket.class);
		query.setParameter("IdSelectedEvent", IdSelectedEvent);
		 List <Ticket> tks = query.getResultList();
			
		 
				return tks;
	}

	@Override
	public void ApprovedTicket(int idticket) {
		Ticket t = em.find(Ticket.class,idticket);
		t.setApproved(1);
		em.merge(t);
		
	}

	@Override
	public Ticket getTicketById(int idticket) {
		Ticket t = 	em.find(Ticket.class, idticket);
		return t;
	}

	@Override
	public Event getEventByIdTicket(int idticket) {
		

		TypedQuery<Event> query = em.createQuery("Select t.event from Ticket t where  t.ticketId=:idticket",
				Event.class);
		query.setParameter("idticket", idticket);
		Event e = query.getSingleResult();
			
		 
				return e;
		
		
	}
	
}
