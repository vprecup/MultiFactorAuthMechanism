package controller.website;
import java.util.*;
import javax.servlet.http.*;
import com.opensymphony.xwork2.*;
import org.apache.struts2.ServletActionContext;
import model.DefaultManager;
import model.User;
import java.net.*;
import java.io.*;
import org.json.JSONException;
import org.json.JSONObject; 

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
	public void restTokenGenerator()
	{
		if(session.getAttribute("userId") != null)
		{
			DefaultManager userHome = new DefaultManager("User");
			String userId = session.getAttribute("userId").toString();
			User user = (User)userHome.findById(userId);
			try
			{
				System.out.println(user.getEmail());
				URL url = new URL("http://localhost:7788/requestcode.com/"+user.getEmail());	
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				System.out.println(conn.getResponseCode());
				//System.out.println(conn);

				conn.disconnect();
			}
			catch(IOException io)
			{
				System.out.println("IO Exception");
			}
		}
	}
	public boolean restConfirmation(String token)
	{
		if(session.getAttribute("userId") != null)
		{
			DefaultManager userHome = new DefaultManager("User");
			String userId = session.getAttribute("userId").toString();
			User user = (User)userHome.findById(userId);
			try
			{

				URL url = new URL("http://localhost:7788/requestconfirmation.com/"+user.getEmail()+"/"+token);	
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				InputStream in = new BufferedInputStream(conn.getInputStream());
				JSONObject obj = new JSONObject(getResponseText(in));
				//System.out.println(obj);
				String confirmation = obj.get("confirmation").toString();
				//System.out.println(confirmation);

				conn.disconnect();
				if(confirmation.equals("true"))
				{
					return true;
				}
			}
			catch(IOException io)
			{
				System.out.println("IO Exception");
			}
			return false;
		}
		else
		{
			return false;
		}
	}
	private static String getResponseText(InputStream inStream) {
	    return new Scanner(inStream).useDelimiter("\\A").next();
	}
}