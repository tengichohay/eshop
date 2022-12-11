package com.example.authservice.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/greeting")
public class GreetingController {

	@GetMapping
	public ResponseEntity<?> greeting() {
		return ResponseEntity.ok("Hello I'm auth service");
	}
}
