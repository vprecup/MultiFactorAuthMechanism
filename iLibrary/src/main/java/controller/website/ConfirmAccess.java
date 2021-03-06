package controller.website;
import java.util.*;
import model.UserManager;
import model.User;

public class ConfirmAccess extends StandardAction
{
	String token;
	public String execute()
	{
		if(getMethod().equals("POST"))
			return doPost();
		else
			return doGet();
	}
	public String doGet()
	{
		return "confirmaccess";
	}
	public String doPost()
	{
		if(restConfirmation(token))
		{
			session.setAttribute("confirmedLogin","1");
			return "success";
		}
		return "confirmaccess";
		
	}
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
}