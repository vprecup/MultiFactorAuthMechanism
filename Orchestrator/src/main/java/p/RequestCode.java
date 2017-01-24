package p;
import org.apache.commons.io.*;
import org.json.JSONException;
import org.json.JSONObject; 
import java.net.*;
import java.nio.charset.Charset;
import java.io.IOException;
import java.io.*;
public class RequestCode {

    private String code;
	private String email;
    public RequestCode(String email) {
        this.email=email;
		try{
		//URL url = new URL("http://onetimepasswordsvc.azurewebsites.net/tapi/GetToken/"+email);
//		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		JSONObject json = new JSONObject(IOUtils.toString(new URL("http://onetimepasswordsvc.azurewebsites.net/tapi/GetToken/"+email), Charset.forName("UTF-8")));
		this.code = json.get("token").toString();
		CallEmailService caller = new CallEmailService(email, code);
		}
		catch(IOException e){
			System.err.println("Couldn't read");
		}		        
    }
	
    public String getCode() {
        return code;
    }
}