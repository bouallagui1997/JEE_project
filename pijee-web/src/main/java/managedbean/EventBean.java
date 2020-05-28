package managedbean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.Part;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;

import model.Event;
import model.Eventcategories;
import model.Ticket;
import model.User;


@Named
@RequestScoped
@ManagedBean(name="EventBean")
@SessionScoped
@ViewScoped
public class EventBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private String title;
	@Temporal(TemporalType.DATE)
	private Date datedebut = new Date();
	@Temporal(TemporalType.DATE)
	private Date  datefin = new Date();
	private String detail;
	private static List<Event> events;
	private Eventcategories  category ;
	UserBean ub= new UserBean();
	LoginBean lb = new LoginBean();
	 public static int eventIdToBeUpdated;
	


	private String searchByTitle;
	 private Part file;
	 
	 
	 public static int idSelectedEvent;
	 
public static String AddressToPersist;
	
	


	public void submit() {
	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 
	
	 
	 
	public EventBean(Date datedebut, String title, String detail, Eventcategories category) {
		super();
		this.datedebut = datedebut;
		this.title = title;
		this.detail = detail;
		this.category = category;
	}

	public EventBean() {
		super();
	}

	@EJB
	services.EventService EventService;
	@EJB
	services.UserService userService;
	
	@EJB
	services.TicketService ticketService;

	
 
	
	
   
	public String addEvent() throws IOException {
	
		
	            InputStream input=file.getInputStream();
	            String namefile = file.getSubmittedFileName().substring(22);
	            File f=new File("\\Users\\abbes\\Desktop\\upload\\"+namefile);
	            System.out.println("hhhhhhhhhhhhhhhhhhhhhh"+namefile);
	            
	            if(!f.exists()){
	                f.createNewFile();
	            }
	            
    
         
         System.out.println("pathfiiiiiile"+namefile);
         String pathfile= "C:\\Users\\abbes\\Desktop\\upload"+namefile;
       	 System.out.println("pathfiiiiiile"+pathfile);

	 User	u = userService.getuserByloginAndPassword("ayoub", "pass");
	 

	 
		 EventService.AddEvent(new Event(datedebut,datefin,detail,category,title,pathfile,u,AddressToPersist)); 
		
	  
         FileOutputStream output=new FileOutputStream(f);
         byte[] buffer=new byte[1024];
         int length;
         while((length=input.read(buffer))>0){
             output.write(buffer, 0, length);
         }
         
         input.close();
         output.close();
		 
 System.out.println("Adresscenter ::::::::::::::::::::::::::::"+  AddressToPersist );
		 
//EventService.AddEvent(new Event(datedebut,datefin,detail,category,title,u));
	FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("add succes"));
	
	return "/pages/welcome?faces-redirect=true";
	}
	/*
	public String getLocation(String Address)
	{
		System.out.println("getLocation                                          "+Address);
		return "/pages/welcome?faces-redirect=true";
	}
	
	*/
	public String removeEvent(int EventId)
	{
		
		EventService.deleteEventById(EventId);
	return "/pages/welcome?faces-redirect=true";
	}
	
	public List<Event> getEvents() {

			if ((searchByTitle ==null) && (category !=null))
			{
			events = EventService.FilterByCategory(category);
			this.setEvents(events);
			return events ;
		
			}
			else if ((searchByTitle !=null) && (category ==null))
			
		{
			events =EventService.advancedSearch(searchByTitle) ;
			setEvents(events);
			return events ;
			
		}
			else
			
		if ((searchByTitle ==null) && (category ==null))
			{
				events = EventService.getAllEvents();
			setEvents(events);
			}
		return events ;
		
		
		
	}
	/*
	public List<Event> SearchByTitle()
	{
		if ((searchByTitle !=null) && (category ==null))
		events=EventService.advancedSearch(searchByTitle);
	return events ;
	}
	
	public List<Event> SearchByCategory()
	{
		if ((searchByTitle ==null) && (category !=null))
		
		events = EventService.FilterByCategory(category);
		return events ;
	}
	
	*/
	
	public String modifier(Event event)
	{
	this.setTitle(event.getTitleEvent());
	this.setDetail(event.getDetailEvent());
	this.setDatedebut(event.getDateDebut());
	this.setDatefin(event.getDateFin());
	this.setCategory(event.getEventCategories());
	this.setEventIdToBeUpdated(event.getEventId());
	
	return "/pages/UpdateEvent?faces-redirect=true";
	
	}
	
	public String mettreAjourEvent() throws IOException{
		User	u = userService.getuserByloginAndPassword("ayoub", "pass");

	
		
	    InputStream input=file.getInputStream();
        String namefile = file.getSubmittedFileName().substring(22);
        File f=new File("\\Users\\abbes\\Desktop\\upload\\"+namefile);
        System.out.println("hhhhhhhhhhhhhhhhhhhhhh"+namefile);
        
        if(!f.exists()){
            f.createNewFile();
        }
        


 String pathfile= "C:\\Users\\abbes\\Desktop\\upload"+namefile;
	
		
		System.out.println(eventIdToBeUpdated+ "  "+category+"  "+   title);
		
		
	
		EventService.updateEvent(new Event(eventIdToBeUpdated,datedebut,datefin,detail,
				 category, title,pathfile,u,AddressToPersist));
		
 
        FileOutputStream output=new FileOutputStream(f);
        byte[] buffer=new byte[1024];
        int length;
        while((length=input.read(buffer))>0){
            output.write(buffer, 0, length);
        }
        
        input.close();
        output.close();
		 

		return "/pages/welcome?faces-redirect=true";	
	}
public String getBarchart()
	
	{
		return "/pages/Bar?faces-redirect=true";	
	}
	

	public String getPiechart()
	
	{
		return "/pages/pie?faces-redirect=true";	
	}
	
	public Event getEventById(int eventId)
	{
		Event e= EventService.getEventById(eventId);	
		return e;
	}

	
	public void getticket(int eventId)
	{
		Event e= EventService.getEventById(eventId);
		
		ticketService.AddTicket(new Ticket(0,e));
		FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("add succes"));
		
	}
	
	
	
	
	
	public String  listNotApproved(int eid)
	{

		 idSelectedEvent=eid;
		System.out.println("iddd     eventId      ::::::::::::  "+eid);
		
		return "/pages/ListTicketToApprove?faces-redirect=true";
	}
	
	
	
	
	
	
	
	
	
	

	
	
	  public void onStateChange(StateChangeEvent event) {
	        LatLngBounds bounds = event.getBounds();
	        int zoomLevel = event.getZoomLevel();
	          
	        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Zoom Level", String.valueOf(zoomLevel)));
	        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Center", event.getCenter().toString()));
	        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "NorthEast", bounds.getNorthEast().toString()));
	        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "SouthWest", bounds.getSouthWest().toString()));
	    }
	    
	    public static String onPointSelect(PointSelectEvent event) {
	        LatLng latlng = event.getLatLng();
	          double latitude=latlng.getLat();
	          double langitude =latlng.getLng();
	         String Adresscenter=String.valueOf(latitude)+", "+String.valueOf(langitude);
	      
	      //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
	         AddressToPersist=Adresscenter;
	        
	         System.out.println("Point Selected Lat:" +AddressToPersist );
	    
	   return Adresscenter;
	  
	   
	    }
	 
	    public void addMessage(FacesMessage message) {
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	
	
	
	
	
	
	
	public String Index()
	
	{
		return "/pages/welcome?faces-redirect=true";
	}
	
	
	
	
	
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Eventcategories getCategory() {
		return category;
	}

	public void setCategory(Eventcategories category) {
		this.category = category;
	}

	public UserBean getUb() {
		return ub;
	}

	public void setUb(UserBean ub) {
		this.ub = ub;
	}

	public services.EventService getEventService() {
		return EventService;
	}

	public void setEventService(services.EventService eventService) {
		EventService = eventService;
	}



	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Integer getEventIdToBeUpdated() {
		return eventIdToBeUpdated;
	}

	public void setEventIdToBeUpdated(Integer eventIdToBeUpdated) {
		this.eventIdToBeUpdated = eventIdToBeUpdated;
	}

	public String getSearchByTitle() {
		return searchByTitle;
	}

	public void setSearchByTitle(String searchByTitle) {
		this.searchByTitle = searchByTitle;
	}
	
	
	
	

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

	public int getIdSelectedEvent() {
		return idSelectedEvent;
	}

	public void setIdSelectedEvent(int idSelectedEvent) {
		this.idSelectedEvent = idSelectedEvent;
	}





	
	
	

}
