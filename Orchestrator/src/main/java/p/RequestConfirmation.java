package p;
import org.apache.commons.io.*;
import org.json.JSONException;
import org.json.JSONObject; 
import java.net.*;
import java.nio.charset.Charset;
import java.io.IOException;

public class RequestConfirmation {

    private boolean confirmation;

    public RequestConfirmation(String str) {
        try{
		JSONObject json = new JSONObject(IOUtils.toString(new URL("http://ip.jsontest.com/"+str), Charset.forName("UTF-8")));
		if(json.get("confirm").toString() == "true")
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