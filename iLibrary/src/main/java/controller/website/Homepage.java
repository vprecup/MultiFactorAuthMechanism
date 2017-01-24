package controller.website;

import com.opensymphony.xwork2.*;
import java.util.*;
import java.io.*;
import freemarker.template.*;

public class Homepage extends StandardAction
{
	public String execute()
	{
		if(!checkLoginPermission())
		{
			return "confirmaccess";
		}
		return "home";
	}
	
}