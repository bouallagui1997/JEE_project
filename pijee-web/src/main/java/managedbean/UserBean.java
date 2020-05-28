package managedbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.User;
import services.UserService;


@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	User u = new User("ayoub","pass");
	
	
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	@EJB
	UserService UserService;
	public void adduser() {
		UserService.adduser(u);
	FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("add succes"));
	}
	
}
