package controller.website;
import java.util.*;
import javax.servlet.http.*;
import com.opensymphony.xwork2.*;
import org.apache.struts2.ServletActionContext;
import model.DefaultManager;
import model.User;

public class StandardAction extends ActionSupport
{
	//Map ce contine variabilele ce vor fi accesate in template
	public Map<String, Object> out = new HashMap<String, Object>();
	
	//Initializarea sesiunii
	//public Map session = ActionContext.getContext().getSession();
	public HttpServletRequest request = ServletActionContext.getRequest();
	public HttpSession session = request.getSession();
	//Functie ce pregateste sesiunea si o arunca in setul de date out descris mai sus
	public Map getOut()
	{
		out.put("session",session);
		return out;
	}
	public String getMethod()
	{
		return (String)request.getMethod();
	}
	public boolean checkLoginPermission()
	{
		if(session.getAttribute("userId") != null)
		{
			if(session.getAttribute("confirmedLogin") == "1"){return true;}
			else{return false;}
		}
		else
		{
			return true;
		}
		
	}
	public String callRestService()
	{
		if(session.getAttribute("userId") != null)
		{
			DefaultManager userHome = new DefaultManager("User");
			String userId = session.getAttribute("userId").toString();
			User user = (User)userHome.findById(userId);
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			return "token";
		}
		else
		{
			return "no user";
		}
	}
}