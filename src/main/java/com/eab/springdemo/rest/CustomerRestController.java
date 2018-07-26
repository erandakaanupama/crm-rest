package com.eab.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eab.springdemo.entity.Customer;
import com.eab.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire customer service
	@Autowired
	private CustomerService customerService;

	// add mapping for GET /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {

		return customerService.getCustomers();
	}

	// add mapping fot GET /customers/{customerID}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		Customer theCustomer = customerService.getCustomer(customerId);

		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		return theCustomer;
	}

	// add new customer
	//
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {

		theCustomer.setId(0);
		this.customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	// update a customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		this.customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	// delete customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer tempCustomer = customerService.getCustomer(customerId);
		
		if (tempCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		
		customerService.deleteCustomer(customerId); 
		return "Deleted customer id - "+ customerId;
	}
}


