package controller.website;

import com.opensymphony.xwork2.*;
import java.util.*;
import java.io.*;

public class Utils{
	public static String hashPassword(String string)
	{
		String input = String.valueOf(string);
		String md5 = null;
		try {
	        java.security.MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
	        //Update input string in message digest
	        digest.update(input.getBytes(), 0, input.length());
	        //Converts message digest value in base 16 (hex)
	        md5 = new java.math.BigInteger(1, digest.digest()).toString(16);
	    }
	    catch (java.security.NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return md5;
	}
}