package com.my.eventsdemo.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.eventsdemo.dao.UserDao;
import com.my.eventsdemo.entity.UserStatus;
import com.my.eventsdemo.model.UserRegister;

/**
 * Handles requests for the application home page.
 */



@Controller
public class HomeController {
	
	
	
	JSONObject eventsResponse=null;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
/*	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}*/
	
	/**
	 * rest metode
	 */
	
	@RequestMapping(value = "/api/info", method = RequestMethod.GET, produces = "text/plain")
	public @ResponseBody String info() {
		String name="";
		try{
		 User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      name=user.getUsername();
		}catch(Exception e){
			
		}
		return name;
	}
	
	@RequestMapping(value = "/api/role", method = RequestMethod.GET, produces = "text/plain")
	public @ResponseBody String role() {
		
		 User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      String name=user.getAuthorities().iterator().next().getAuthority();
	      logger.info(name);
		return name;
	}
	

	

	

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/api/citySuggestions/{query}", method = RequestMethod.GET)
	public @ResponseBody JSONArray getCities(@PathVariable String query) {
		
		String qu=query;
		qu=qu.replace(" ", "%20");
		
		  HttpClient httpclient = HttpClients.createDefault();
		  org.apache.http.HttpEntity entity = null;
		  JSONArray arrayObj=null;
	        try
	        {
	            URIBuilder builder = new URIBuilder("https://api.allevents.in/geo/city_suggestion/"+qu);


	            URI uri = builder.build();
	            HttpGet request = new HttpGet(uri);
	            request.setHeader("Ocp-Apim-Subscription-Key", "5b298f3bad504791a7052c6d6257f97b");


	            // Request body
	            //StringEntity reqEntity = new StringEntity("{body}");
	           // request.setEntity(reqEntity);

	            HttpResponse response = httpclient.execute(request);
	            entity = response.getEntity();
	            String json_string = EntityUtils.toString(entity);
	            Object object=null;
	           
	            JSONParser jsonParser=new JSONParser();
	            object=jsonParser.parse(json_string);
	            arrayObj=(JSONArray) object;
	        }
	        catch (Exception e)
	        {
	            System.out.println(e.getMessage());
	        }
		
		
		
		return arrayObj;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/api/eventsincity", method = RequestMethod.GET)
	public @ResponseBody JSONArray getEventsInCity(@RequestParam Map<String,String> requestParams) {
		
		String city=requestParams.get("city");
		String state=requestParams.get("state");
		String country=requestParams.get("country");
		boolean helper=false;
		boolean more=true;
		JSONArray eventsArray=new JSONArray();
		int page=0;
		//logger.info("parametars:" + city + state + country);	
		
		
		if(!helper){
			
			
			
		  HttpClient httpclient = HttpClients.createDefault();
		  
		  while(more){
		  
		  
		  org.apache.http.HttpEntity entity = null;
		  JSONObject arrayObj=null;
	        try
	        {
	        	 URIBuilder builder = new URIBuilder("https://api.allevents.in/events/list/");

	             builder.setParameter("city", city);
	             builder.setParameter("state", state);
	             builder.setParameter("country", country);
	             builder.setParameter("page", Integer.toString(page));
	             
	             page++;
	             
	             URI uri = builder.build();
	             HttpPost request = new HttpPost(uri);
	             request.setHeader("Ocp-Apim-Subscription-Key", "5b298f3bad504791a7052c6d6257f97b");


	             // Request body
	             StringEntity reqEntity = new StringEntity("{body}");
	             request.setEntity(reqEntity);

	             HttpResponse response = httpclient.execute(request);
	             entity = response.getEntity();
	            
	            String json_string = EntityUtils.toString(entity);
	            Object object=null;
	            JSONParser jsonParser=new JSONParser();
	            object=jsonParser.parse(json_string);
	            arrayObj=(JSONObject) object;
	            eventsResponse=arrayObj;
	            JSONArray data = (JSONArray) arrayObj.get("data");
	            
	           
	            	
	            	
	            		eventsArray.addAll(data);
	          
	           
	            
	            
	            
	            if(data.size()<100)
	            	more=false;
	            
	            
	            
	            //helper=true;
	        }
	        catch (Exception e)
	        {
	            System.out.println(e.getMessage());
	        }
		
		}
		}
		return eventsArray;
	}
	
	
}
