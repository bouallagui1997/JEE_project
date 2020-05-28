package managedbean;



import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.Eventcategories;



@ManagedBean(name = "data")
@ApplicationScoped
public class Data implements Serializable {
	private static final long serialVersionUID = 1L;
	public Eventcategories[] getCategories()
	{ return Eventcategories.values(); 

}
}