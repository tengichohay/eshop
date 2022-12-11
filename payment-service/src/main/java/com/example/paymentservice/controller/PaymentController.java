package com.example.paymentservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class PaymentController {


	@PostMapping("/hi")
	@PreAuthorize("hasAnyAuthority('USER')")
	public ResponseEntity<?> sayHi(@RequestParam("name") String name) {
		return new ResponseEntity<>("hi " + name, HttpStatus.OK);
	}


}
