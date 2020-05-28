package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the Users database table.
 * 
 */
@Entity
@Table(name="Users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="UserId")
	private int userId;

	@Column(name="Nom")
	private String nom;

	@Column(name="Password")
	private String password;

	public User() {
	}

	public User(int userId, String nom, String password) {
		super();
		this.userId = userId;
		this.nom = nom;
		this.password = password;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String nom, String password) {
		super();
		this.nom = nom;
		this.password = password;
	}
	
	public User(int userId) {
		super();
		this.userId = userId;
	}

	@OneToMany(mappedBy="user",cascade = CascadeType.REMOVE, 
			fetch=FetchType.EAGER)
			private List<Event> events = new ArrayList<>();

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
			

	public Event addEvents(Event e) {
		getEvents().add(e);
		e.setUser(this);

		return e;
	}

	public Event removeEvents(Event e) {
		getEvents().remove(e);
		e.setUser(null);

		return e;
	}
	
}