package com.itgrids.controllers.web;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@EnableAutoConfiguration
@Controller
@RequestMapping("/")
public class NewsArticlesController {
	 
private static final Logger LOG = Logger.getLogger(NewsArticlesController.class);
	@RequestMapping(value ="/newsArticles",method = RequestMethod.GET)
	public String newsArticles() {
	   return "newsArticles";
	}
	@RequestMapping(value ="/showCnpArticles",method = RequestMethod.GET)
	public String showCnpArticles() {
	   return "showCnpArticles";
	}
	@RequestMapping(value ="/electronicMediaBulletinsCD",method = RequestMethod.GET)
	public String electronicMediaBulletinsCD() {
	   return "electronicMediaBulletinsCD";
	}
	
	@RequestMapping(value="/wordCloud", method = RequestMethod.GET)
	public String wordCloud(){
		return "wordCloud";
	}
}
