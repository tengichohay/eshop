package com.example.documentation.controller;

import com.example.documentation.config.ServiceDefinitionsContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceDefinitionController {

	private final ServiceDefinitionsContext definitionContext;

	public ServiceDefinitionController(ServiceDefinitionsContext definitionContext) {
		this.definitionContext = definitionContext;
	}

	@GetMapping("/service/{servicename}")
	public String getServiceDefinition(@PathVariable("servicename") String serviceName) {
		return definitionContext.getSwaggerDefinition(serviceName);
	}
}