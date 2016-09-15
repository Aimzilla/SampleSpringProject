package com.GC.API;

import java.io.StringReader;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/*@RequestMapping(value="getweather", method=RequestMethod.GET)
	public String getWeather(Model model){
		try{
		
		HttpClient http = HttpClientBuilder.create().build();
		
		//address we want to call:
		HttpHost host = new HttpHost("forecast.weather.gov", 80, "http");
		
		//http method...query from the end of the address in the line above...
		HttpGet getPage = new HttpGet("/MapClick.php?lat=38.4247341&lon=-86.9624086&FcstType=xml");
		
		//execute the http request and get the response back
		HttpResponse resp = http.execute(host, getPage);
		
		//String result = EntityUtils.toString(resp.getEntity());
		
		String result = "";
		String xmlString = EntityUtils.toString(resp.getEntity());
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = factory.newDocumentBuilder();
		
		InputSource inStream = new InputSource();
		inStream.setCharacterStream(new StringReader(xmlString));
		Document doc = db.parse(inStream);

		String weatherForeCast = "empty";
		NodeList nl = doc.getElementsByTagName("text");//text is the tag name in the xml file (website)
		
		for (int i = 0; i < nl.getLength(); i++) {

			org.w3c.dom.Element nameElement = (org.w3c.dom.Element) nl.item(i);
			weatherForeCast = nameElement.getFirstChild().getNodeValue().trim();
			result += ("<h6>" + weatherForeCast + "</h6>");
		}

		model.addAttribute("pagedata", result);
	}
	catch (Exception e){
		
		return "errorpage";
	}
		return "weather";
	}
}*/


/*
//new project using new API
@RequestMapping(value="getphonenumber", method=RequestMethod.GET)
public String getPhoneNumber(Model model){
	try{
	
	HttpClient http = HttpClientBuilder.create().build();
	
	//address we want to call:
	HttpHost host = new HttpHost("cosmin-us-phone-number-lookup.p.mashape.com/get.php", 80, "http");
	
	//http method...query from the end of the address in the line above...
	HttpGet getPage = new HttpGet("/get.php?phone=3055050000");
	
	//get the mashape key online and add this following line and enter key here: 
	getPage.setHeader("X-Mashape-Key", "L8SeIMT5womshOS4RySameeDvfjxp14qs2hjsntxe4oWQtJLfr");
	
	//execute the http request and get the response back
	HttpResponse resp = http.execute(host, getPage);
	
	//String result = EntityUtils.toString(resp.getEntity());
	
	String result = "";
	String xmlString = EntityUtils.toString(resp.getEntity());
	
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder db = factory.newDocumentBuilder();
	
	InputSource inStream = new InputSource();
	inStream.setCharacterStream(new StringReader(xmlString));
	Document doc = db.parse(inStream);

	String phoneNumber = "empty";
	NodeList nl = doc.getElementsByTagName("text");//text is the tag name in the xml file (website)
	
	for (int i = 0; i < nl.getLength(); i++) {

		org.w3c.dom.Element nameElement = (org.w3c.dom.Element) nl.item(i);
		phoneNumber = nameElement.getFirstChild().getNodeValue().trim();
		result += ("<h6>" + phoneNumber + "</h6>");
	}

	model.addAttribute("pagedata", result);
}
catch (Exception e){
	
	return "errorpage";
}
	return "phoneNumber";*/

	
	//**** FINAL PROJECT CODE ****
	
@RequestMapping(value = "getdog", method = RequestMethod.GET)
public String getDog(Model model) {
    try {
        HttpClient http = HttpClientBuilder.create().build();

        // address that we want to call
        HttpHost host = new HttpHost("api.petfinder.com", 80, "http");
        // http method: get
        HttpGet getPage = new HttpGet("/pet.getRandom?format=xml&key=688cf0271f4f3125175bf1d9a9f8973f&animal=dog&output=basic");

        //getPage.setHeader("X-Mashape-Key", "put key here");

        
        // execute the http request and get the http response back
        HttpResponse resp = http.execute(host, getPage);

         //String result = EntityUtils.toString(resp.getEntity());

        String result = "";
       String xmlString = EntityUtils.toString(resp.getEntity());
       
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        
        InputSource inStream = new InputSource();
        inStream.setCharacterStream(new StringReader(xmlString));
        Document doc = db.parse(inStream);
        

        String weatherForeCast = "empty";
        NodeList nl = doc.getElementsByTagName("breed");
        //NodeList name = doc.getElementsByTagName("name");
        //NodeList breed = doc.getElementsByTagName("breed");
        //NodeList dog = doc.getElementsByTagName("dog");
        for (int i = 0; i < nl.getLength(); i++) {

            org.w3c.dom.Element nameElement = (org.w3c.dom.Element) nl.item(i);
            weatherForeCast = nameElement.getFirstChild().getNodeValue().trim();
            result += ("<h6>" + weatherForeCast + "</h6>");

        }

        model.addAttribute("pagedata", result);
        
    } catch (Exception e) {
        return "errorpage";
    }
    return "dog";

}


}

