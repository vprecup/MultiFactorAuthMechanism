package p;
import org.apache.commons.io.*;
import org.json.JSONException;
import org.json.JSONObject; 
import java.net.*;
import java.nio.charset.Charset;
import java.io.IOException;
import java.net.MalformedURLException;
public class CallEmailService {

    private String token;
	private String email;

    public CallEmailService(String email, String token) {
        this.token=token;
		this.email=email;
		try{
		URL url = new URL("http://localhost:8080/emailcontroller.com/"+email+"/"+token);	
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		System.out.println(url);
		System.out.println(connection.getResponseCode());
		}
		catch(IOException io){
			System.out.println("IO Exception");
		}
    }
}