package managedbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import model.User;
import services.UserService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



@ManagedBean(name="loginBean")
@SessionScoped


public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String login; private String password; private User user;
	private Boolean loggedIn;
	@EJB
	UserService UserService;
	
	
	
	
	
	public String doLogin()
	{
	String navigateTo = "null";
	user = UserService.getuserByloginAndPassword(login, password);
	if (user != null) {
		
		

	navigateTo = "/pages/welcome?faces-redirect=true";
	setLoggedIn(true);
	}
	else
	{
	FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));}
	
	

	
	

	return navigateTo;
	}
	public String doLogout()
	{FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	return "/login?faces-redirect=true";}
	
	public String addEvent()
	{
	return "/pages/ajoutEvent?faces-redirect=true";}
	
	//Générer un constructeur sans argument, les Getters et les Setters
	
	
	
	

	
	
	
	
	
	

	
	public LoginBean() {
		super();
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserService getUserService() {
		return UserService;
	}
	public void setUserService(UserService userService) {
		UserService = userService;
	}


}
