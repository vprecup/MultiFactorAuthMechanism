package p;
import org.apache.commons.io.*;
import org.json.JSONException;
import org.json.JSONObject; 
import java.net.*;
import java.nio.charset.Charset;
import java.io.IOException;

public class RequestConfirmation {

    private boolean confirmation;

    public RequestConfirmation(String email, String token) {

        try{
		URL url = new URL("http://onetimepasswordsvc.azurewebsites.net/tapi/ChallengeToken/"+email+"/"+token);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		if(connection.getResponseCode() == 200)
			this.confirmation = true;
		else
			this.confirmation = false;
		}
		catch(IOException e){
			System.err.println("Couldn't read");
		}		        
		}
	
    public Boolean getConfirmation() {
        return confirmation;
    }
}