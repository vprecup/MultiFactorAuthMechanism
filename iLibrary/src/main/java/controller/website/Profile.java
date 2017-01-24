package controller.website;

import java.util.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import java.sql.*;
import java.io.*;
import java.time.*;
import java.lang.*;
import model.*;

public class Profile extends StandardAction
{
	private String cpassword;
	private Person person;
	private User user;

	//Map ce contine variabilele ce vor fi trimise prin Ajax in format json
	private Map<String,Object> jsonResponse = new HashMap<String,Object>();

	@SuppressWarnings("unchecked")
	public String execute()
	{
		if(getMethod().equals("POST"))
			return doPost();
		else
			return doGet();
	}
	public String doPost()
	{
		
		return "signup";
	}
	public String doGet()
	{
		DefaultManager userHome  = new DefaultManager("userHome");
		//String userId = session.get("userId");
		//User user = userHome.findById(userId);


		return "success";
	}
}