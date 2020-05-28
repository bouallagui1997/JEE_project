package interf.remote;

import java.util.List;

import model.Event;
import model.Ticket;

public interface TicketServiceRemote {
	public void AddTicket(Ticket  t);
	public List<Ticket> getTicketsByEventNotApproved(int IdSelectedEvent);
	public void ApprovedTicket(int idticket);
	public Ticket getTicketById(int idticket);
	public Event getEventByIdTicket(int idticket);

}
