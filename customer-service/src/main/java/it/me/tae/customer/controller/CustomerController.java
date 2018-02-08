package it.me.tae.customer.controller;

import java.util.logging.Logger;

import it.me.tae.customer.model.CustomerProto.*;
import it.me.tae.customer.contract.AccountClient;
import it.me.tae.customer.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {
    protected Logger logger = Logger.getLogger(CustomerController.class.getName());

    @Autowired
    CustomerRepository repository;
    @Autowired
    AccountClient accountClient;

    @RequestMapping(value = "/customers/pesel/{pesel}", produces = "application/x-protobuf")
    public Customer findByPesel(@PathVariable("pesel") String pesel) {
        logger.info(String.format("Customer.findByPesel(%s)", pesel));
        return repository.findByPesel(pesel);
    }

    @RequestMapping(value = "/customers", produces = "application/x-protobuf")
    public Customers findAll() {
        logger.info("Customer.findAll()");
        return Customers.newBuilder().addAllCustomers(repository.findAll()).build();
    }

    @RequestMapping(value = "/customers/{id}", produces = "application/x-protobuf")
    public Customer findById(@PathVariable("id") Integer id) {
        logger.info(String.format("Customer.findById(%s)", id));
        Customer customer = repository.findById(id);
        Accounts accounts = accountClient.getAccounts(id);
        customer = Customer.newBuilder(customer).addAllAccounts(accounts.getAccountList()).build();
        return customer;
    }

}