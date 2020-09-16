package com.movierating.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.movierating.domain.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    Movie findMovieById(int movieId);
    Page findAll(Pageable pageable);
}

