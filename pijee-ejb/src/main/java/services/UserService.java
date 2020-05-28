package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import interf.remote.UserServiceRemote;

import model.User;





@Stateless
@LocalBean
public class UserService  implements UserServiceRemote {

	
	@PersistenceContext(unitName = "pijee")
	EntityManager em;

	
	@Override
	public void adduser(User u){
		
		em.persist(u);
		
	}


	@Override
	public User getuserByloginAndPassword(String login, String password) {
		TypedQuery<User> query = em.createQuery("SELECT u from User u where u.nom=:login and u.password=:password", User.class);
		query.setParameter("login", login);
		query.setParameter("password", password);
		User user = null;
		try { user = query.getSingleResult(); }
		catch (Exception

		e) { System.out.println("Erreur : " +
		e); }

		return user;
	}


	
	

}
