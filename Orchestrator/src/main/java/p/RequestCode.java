package p;
import org.apache.commons.io.*;
import org.json.JSONException;
import org.json.JSONObject; 
import java.net.*;
import java.nio.charset.Charset;
import java.io.IOException;

public class RequestCode {

    private String code;
	private String email;
    public RequestCode(String email) {
        this.email=email;
		try{
		JSONObject json = new JSONObject(IOUtils.toString(new URL("http://ip.jsontest.com/"), Charset.forName("UTF-8")));
//		this.code = json.get("ip").toString();
		this.code = "12fdsaf34";
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