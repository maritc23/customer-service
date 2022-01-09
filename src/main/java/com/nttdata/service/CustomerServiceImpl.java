package com.nttdata.service;

import com.nttdata.dao.CustomerRepository;
import com.nttdata.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepo;

    public void createCustomer(Customer employee) {
        customerRepo.save(employee).subscribe();
    }

    public Mono<Customer> findByCustomerId(Integer id) {
        return customerRepo.findById(id);
    }

    public Flux<Customer> findAllCustomer() {
        return customerRepo.findAll();
    }

    public Mono<Customer> updateCustomer(Customer employee) {
        return customerRepo.save(employee);
    }

    public Mono<Void> deleteCustomer(Integer id) {
        return customerRepo.deleteById(id);
    }

	
}