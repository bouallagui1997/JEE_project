package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.TypedQuery;

import interf.remote.EventServiceRemote;
import model.Event;
import model.Eventcategories;







@Stateless
@LocalBean
public class EventService implements EventServiceRemote  {

	@PersistenceContext(unitName = "pijee")
	EntityManager em;

	@Override
	public int AddEvent(Event e) {
		em.persist(e);
		return 1;
	}

	@Override
	public List<Event> getAllEvents() {
		List<Event> evts = em.createQuery("Select e from Event e",
				Event.class).getResultList();
				return evts;
	}

	@Override
	public void deleteEventById(int EventId) 
		
		{Event e = em.find(Event.class,EventId);

			em.remove(e);
			
	}

	@Override
	public void updateEvent(Event e) {
		em.merge(e);
		
	}

	@Override
	public Event getEventById(int eventId) {
		Event e = 	em.find(Event.class, eventId);
		return e;
	}

	@Override
	public Long getNumberEventWorkshop() {
		Eventcategories nom=Eventcategories.Workshop;
		TypedQuery <Long> query1 =em.createQuery("SELECT COUNT(e) FROM Event e WHERE e.eventCategories=:Workshop", Long.class);
		 query1.setParameter("Workshop", nom);
		long nbr=query1.getSingleResult();

		
		return nbr;
	}

	
	
	
	   public List <Event> advancedSearch(String text) {
		   TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e where upper(e.titleEvent) like CONCAT('%',UPPER(:text),'%')  ",Event.class);
		  query.setParameter("text", text);
		  List <Event> Eventlist = query.getResultList();
	   
	        return Eventlist;
	    }

	public List<Event> FilterByCategory(Eventcategories category) {
		//Conferance,Workshop,Stand,Sales
		
		
	//	int ordre =category.ordinal();
		//System.out.println("oooordreeeeee "+ ordre);
//String text= Integer.toString(ordre);
		 TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e where e.eventCategories=:text)",Event.class);
		  query.setParameter("text", category);
		  List <Event> Eventlist = query.getResultList();
	   
	        return Eventlist;
	}

	@Override
	public Long getNumberEventConferance() {
		Eventcategories nom=Eventcategories.Conferance;
		TypedQuery <Long> query1 =em.createQuery("SELECT COUNT(e) FROM Event e WHERE e.eventCategories=:Conferance", Long.class);
		 query1.setParameter("Conferance", nom);
		long nbr=query1.getSingleResult();
		return nbr;
	}

	@Override
	public Long getNumberEventStand() {
		Eventcategories nom=Eventcategories.Stand;
		TypedQuery <Long> query1 =em.createQuery("SELECT COUNT(e) FROM Event e WHERE e.eventCategories=:Stand", Long.class);
		 query1.setParameter("Stand", nom);	
			
		long nbr=query1.getSingleResult();
		return nbr;	
	}

	@Override
	public Long getNumberEventSales() {
		Eventcategories nom=Eventcategories.Sales;
		TypedQuery <Long> query1 =em.createQuery("SELECT COUNT(e) FROM Event e WHERE e.eventCategories=:Sales", Long.class);
		 query1.setParameter("Sales", nom);	
		long nbr=query1.getSingleResult();
		return nbr;
	}
	
	

	
	
}
