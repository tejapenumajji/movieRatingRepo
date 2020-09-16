package com.movierating.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.movierating.domain.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Customer findCustomerById(int customerId);
    Page findAll(Pageable pageable);
}
