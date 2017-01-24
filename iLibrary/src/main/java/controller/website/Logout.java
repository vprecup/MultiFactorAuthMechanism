package controller.website;

import java.util.*;
import model.UserManager;
import model.User;

public class Logout extends StandardAction
{
	//business logic
	public String execute()
	{
		logout();
        return "home";
	}
	public void logout()
	{
		session.removeAttribute("userId");
		session.removeAttribute("user");
        session.removeAttribute("token");
        session.removeAttribute("confirmedLogin");
	}
}
