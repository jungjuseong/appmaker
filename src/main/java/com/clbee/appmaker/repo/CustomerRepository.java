package com.clbee.appmaker.repo;
import com.clbee.appmaker.dao.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();
    Customer findById(long id);
    Customer save(Customer customer);
}