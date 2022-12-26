package com.example.paymentservice.controller;


import com.example.shared.common.context.DataContextHelper;
import com.example.shared.common.response.ResponseEntites;
import com.example.shared.controller.AbstractResponseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping(value = "/v1")
public class PaymentController extends AbstractResponseController {

	protected PaymentController(ResponseEntites<Object> responseEntites, DataContextHelper dataContextHelper) {
		super(responseEntites, dataContextHelper);
	}

	@PostMapping("/hi")
//	@PreAuthorize("hasAnyAuthority('USER')")
	public DeferredResult<ResponseEntity<?>> sayHi(@RequestParam("name") String name) {
		return responseEntityDeferredResult(() -> new ResponseEntity<>("hi " + name, HttpStatus.OK));
	}


}
