package com.itgrids.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itgrids.model.ExampleModel;
import com.itgrids.rest.Response;
import com.itgrids.service.IExampleService;

@EnableAutoConfiguration
@RestController
@RequestMapping("/example")
public class ExampleController {

	private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);

	@Autowired
	private IExampleService exampleService;

	
	@GetMapping(value = "/getExample/{id}")
	public Response getExample(@PathVariable int id) {

		Response response = null;

		try {

			response = new Response(200, "success", exampleService.get(id));
			return response;
		} catch (Exception e) {
			logger.error("Error in getting Example", e);
			response = new Response(207, "Failed", e.getMessage());
			return response;
		}

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Response save(@RequestBody ExampleModel request) {

		Response response = null;

		try {

			exampleService.save(request);

			response = new Response(200, "success", "Saved Successfully");
			return response;
		} catch (Exception e) {
			logger.error("Error in saving example", e);
			response = new Response(207, "Failed", e.getMessage());
			return response;
		}

	}

}
