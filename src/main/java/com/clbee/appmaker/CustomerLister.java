package com.clbee.appmaker;

import com.clbee.appmaker.repo.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
class CustomerLister implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final List<CustomerRepository> customerList;

    CustomerLister(List<CustomerRepository> customerList) {
        this.customerList = customerList;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (customerList.size() > 0) {
            customerList.get(0).findAll().forEach(customer -> logger.info("{}", customer));
        }
    }
}