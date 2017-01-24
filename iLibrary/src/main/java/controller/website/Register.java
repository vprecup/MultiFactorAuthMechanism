package controller.website;

import java.util.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import java.sql.*;
import java.io.*;
import java.time.*;
import java.lang.*;
import model.DefaultManager;
import model.UserManager;
import model.PersonManager;
import model.User;
import model.Person;

public class Register extends StandardAction
{
	private String cpassword;
	private Person person;
	private User user;

	//Map ce contine variabilele ce vor fi trimise prin Ajax in format json
	private Map<String,Object> jsonResponse = new HashMap<String,Object>();

	@SuppressWarnings("unchecked")
	public String execute()
	{
		if(!checkLoginPermission())
		{
			return "confirmaccess";
		}
		if(getMethod().equals("POST"))
			return doPost();
		else
			return doGet();
	}
	public String doPost()
	{
		int userId = 0;
		DefaultManager userManager = new DefaultManager("User");
		try{
			user.setPassword(Utils.hashPassword(user.getPassword()));
			user.setRole_id(1);
			userManager.add(user);
			userId = user.getId();
		}
		catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		DefaultManager personManager = new DefaultManager("Person");
		try {
			person.setUser_id(userId);
			personManager.add(person);
			/*Emailer email = new Emailer();
			email.setTo(user.getEmail());
			email.setSubject("Test Struts2 Mail");
			email.setBody("Test Struts2 Mail da dad dadada <br>");
			email.sendMail();*/
		
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "signup";
	}
	public String doGet()
	{
		//display logic
		return "success";
	}
	public void setJsonResponse(Map<String,Object> jsonResponse)
	{
		this.jsonResponse = jsonResponse;
	}
	public Map<String,Object> getJsonResponse()
	{
		return jsonResponse;
	}
	public Person getPerson() 
	{
		return person;
	}
	public void setPerson(Person person) 
	{
		this.person = person;
	}
	public User getUser() 
	{
		return user;
	}
	public void setUser(User user) 
	{
		this.user = user;
	}
	public String getCpassword()
	{
		return cpassword;
	}
	public void setCpassword(String cpassword)
	{
		this.cpassword = cpassword;
	}
	public boolean checkUsername(String username)
	{
		UserManager userHome = new UserManager();
		List<User> result = new ArrayList<User>();

		Map<String, String> filters = new HashMap<String, String>();
		filters.put("username", username);
		
		result = userHome.list(filters);

		if(result.size() != 0)
		{
			return true;
		}
		return false;
	}
	public boolean checkEmail(String email)
	{
		UserManager userHome = new UserManager();
		List<User> result = new ArrayList<User>();

		Map<String, String> filters = new HashMap<String, String>();
		filters.put("email", email);
		
		result = userHome.list(filters);

		if(result.size() != 0)
		{
			return true;
		}
		return false;
	}
	public String ajaxValidation()
	{
		//aici treaba e simpla ...cand trimit formul prin post la aceasta functie,
		//intervine automat functia validate si verifica datele
		//iar daca se trece de validare ...practic mai facem aici alte verificam ( daca e cazul)
		//dar in acest caz nu mai avem nevoie de nimic si astfel setam result = success
		jsonResponse.put("result","success");
		return SUCCESS;
	}
	public void validate()
    {
    	if(person != null && user != null)
    	{
    		if (person.getFirstname() != null && person.getFirstname().length() == 0) 
	    	{
	            addFieldError("firstname","You can't leave this empty");
	        }
	        if (person.getLastname() != null && person.getLastname().length() == 0) 
	        {
	            addFieldError("lastname","You can't leave this empty");
	        }
	        if (user.getUsername() != null) 
	        {
	        	if(user.getUsername().length() == 0)
	            	addFieldError("username","You can't leave this empty");
	            else if(checkUsername(user.getUsername()))
	            	addFieldError("username","This username already exists");
	        }
	        if(user.getPassword() != null && user.getPassword().length() == 0) 
	    	{
	            addFieldError("password","You can't leave this empty");
	        }
	        if(getCpassword() != null && getCpassword().length() == 0) 
	    	{
	            addFieldError("cpassword","You can't leave this empty");
	        }
	        if(user.getPassword() != null && getCpassword() != null && !user.getPassword().equals(getCpassword()))
	        {
	        	addFieldError("password","Passwords should be the same");
	        }
	        if (user.getEmail() != null) 
	    	{
	    		if(user.getEmail().length() == 0)
	            	addFieldError("email","You can't leave this empty");
	            else if(checkEmail(user.getEmail()))
	            	addFieldError("email","This email already exists");
	        }
    	}
    	if(hasErrors())
    	{
    		jsonResponse.put("result","error");
    		jsonResponse.put("errors",getFieldErrors());
    	}
    	else
    	{
    		jsonResponse.put("result","success");
    	}
        out.put("errors",getFieldErrors());
      	
    }
    public String ceva()
	{
		return SUCCESS;
	}
	
}