package com.movierating.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.movierating.domain.CustomerRating;

public interface CustomerRatingRepository extends PagingAndSortingRepository<CustomerRating, Long> {
    Page findAll(Pageable pageable);
}
