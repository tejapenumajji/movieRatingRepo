package com.movierating.api.rest;


import com.movierating.domain.Customer;
import com.movierating.domain.CustomerRating;
import com.movierating.domain.Movie;
import com.movierating.service.MovieRatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/api/rest")
public class MovieRatingController extends AbstractRestHandler {

    @Autowired
    private MovieRatingService movieRatingService;

    @RequestMapping(value = "/customer",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody Customer customer,
                                 HttpServletRequest request, HttpServletResponse response) {
    	Customer createdCustomer = this.movieRatingService.createCustomer(customer);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdCustomer.getId()).toString());
    }

    @RequestMapping(value = "/customer",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Page<Customer> getAllCustomer(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.movieRatingService.getAllCustomers(page, size);
    }

    @RequestMapping(value = "/movie",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createMovie(@RequestBody Movie movie,
                                 HttpServletRequest request, HttpServletResponse response) {
    	Movie createdMovie = this.movieRatingService.createMovie(movie);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdMovie.getId()).toString());
    }

    @RequestMapping(value = "/movie",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Page<Movie> getAllMovie(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.movieRatingService.getAllMovies(page, size);
    }

    @RequestMapping(value = "/customer/{id}/movie/{movieId}/rate/{rating}",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomerRating(@PathVariable("id") Long customerId,@PathVariable("movieId") Long movieId, @PathVariable("rating") Integer ratingId,
                                 HttpServletRequest request, HttpServletResponse response) {
    	Customer customer = this.movieRatingService.getCustomer(customerId);
    	checkResourceFound(customer);
    	Movie movie = this.movieRatingService.getMovie(movieId);
    	checkResourceFound(movie);
    	CustomerRating createdCustomerRating = this.movieRatingService.createCustomerRating(new CustomerRating(customerId, movieId, ratingId));
        response.setHeader("Location", request.getRequestURL().append("/").append(createdCustomerRating.getId()).toString());
    }

    @RequestMapping(value = "/customerRating/all",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Page<CustomerRating> getAllCustomerRating(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.movieRatingService.getAllCustomerRatings(page, size);
    }
    
    @RequestMapping(value = "/highestRatedMovie",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    String getAllCustomerRating(HttpServletRequest request, HttpServletResponse response) {
        return this.movieRatingService.getHighestRatedMovie();
    }
    
    @RequestMapping(value = "/highestRating/customer",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Customer getCustomerGiviesMostRatingAverage(HttpServletRequest request, HttpServletResponse response) {
        return this.movieRatingService.getCustomerGiviesMostRatingAverage();
    }
}
