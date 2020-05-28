package interf.remote;

import javax.ejb.Remote;

import model.User;


@Remote
public interface UserServiceRemote {
public void adduser(User u);
public User getuserByloginAndPassword(String login, String password);


}
