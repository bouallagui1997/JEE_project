package interf.remote;

import java.util.List;

import model.Event;
import model.Eventcategories;


public interface EventServiceRemote {
	public int AddEvent(Event e);
	public List<Event>getAllEvents();
	public void deleteEventById(int EventId) ;
	public void  updateEvent (Event e);
public Event	getEventById(int eventId);
public Long getNumberEventWorkshop();
public Long getNumberEventConferance();
public Long getNumberEventStand();
public Long getNumberEventSales();
public List <Event> advancedSearch(String text);
public List<Event> FilterByCategory(Eventcategories category);

}
