package controller.website;
import java.util.*;
import model.UserManager;
import model.User;

public class Login extends StandardAction
{
	String username;
	String password;
	public String execute()
	{
		if(!checkLoginPermission())
		{
			return "notconfirmed";
		}
		if(getMethod().equals("POST"))
			return doPost();
		else
			return doGet();
	}
	public String doGet()
	{
		return "login";
	}
	public String doPost()
	{
		if (username != null && !username.equals(""))
		{
			if(checkPassword(username,password))
			{
				String token = callRestService();
				session.setAttribute("confirmedLogin","0");
				return "confirmaccess";
			}
		}
		return "loginfailed";
	}
	public boolean checkPassword(String username, String password)
	{
		UserManager userHome = new UserManager();
		List<User> result = new ArrayList<User>();

		Map<String, String> filters = new HashMap<String, String>();
		filters.put("username", username);
		
		result = userHome.list(filters);
		if(result.size() == 1)
		{
			User user = result.get(0);
			String hp = Utils.hashPassword(password);
			if(user.getPassword().equals(hp))
			{
				session.setAttribute("userId",user.getId());
				session.setAttribute("user",user.getUsername());
		        session.setAttribute("token", new Date());
		        out.put("session",session);
		        return true;
			}
		}
		return false;
	}

	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
}