package org.diginamic.demojwt.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exemples")
public class ExempleController {

	@GetMapping("/url1")
	@Secured("USER")
	public String getData1() {
		return "Coucou1";
	}
	
	@GetMapping("/url2")
	@Secured("ADMIN")
	public String getData2() {
		return "Coucou2";
	}
}
