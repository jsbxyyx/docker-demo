package org.xxz.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@GetMapping(value = {"/", "/index"})
	public String index() {
		return "hello docker";
	}
	
}
