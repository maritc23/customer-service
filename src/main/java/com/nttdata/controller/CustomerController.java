package com.nttdata.controller;

import com.nttdata.model.Customer;
import com.nttdata.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody Customer employee) {
    	logger.info("creating customer");
        customerServiceImpl.createCustomer(employee);
    }

    @GetMapping(value = "/customers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Customer> findAll() {
    	logger.info("find customers");
        return customerServiceImpl.findAllCustomer();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Mono<Customer>> findEmpById(@PathVariable("id") Integer id) {
        Mono<Customer> employee = customerServiceImpl.findByCustomerId(id);
        return new ResponseEntity<Mono<Customer>>(employee, employee != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Customer> update(@RequestBody Customer employee) {
    	
        return customerServiceImpl.updateCustomer(employee);
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        customerServiceImpl.deleteCustomer(id).subscribe();
    }

}
