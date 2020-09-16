package com.movierating.service;

import com.movierating.dao.jpa.CustomerRatingRepository;
import com.movierating.dao.jpa.CustomerRepository;
import com.movierating.dao.jpa.MovieRepository;
import com.movierating.domain.Customer;
import com.movierating.domain.CustomerRating;
import com.movierating.domain.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MovieRatingService {

	private static final Logger log = LoggerFactory.getLogger(MovieRatingService.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private CustomerRatingRepository customerRatingRepository;

	public MovieRatingService() {
	}

	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer getCustomer(long id) {
		return customerRepository.findOne(id);
	}

	public void updateCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public void deleteCustomer(Long id) {
		customerRepository.delete(id);
	}

	public Page<Customer> getAllCustomers(Integer page, Integer size) {
		Page pageOfCustomers = customerRepository.findAll(new PageRequest(page, size));

		return pageOfCustomers;
	}

	public Movie createMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	public Movie getMovie(long id) {
		return movieRepository.findOne(id);
	}

	public Page<Movie> getAllMovies(Integer page, Integer size) {
		Page pageOfCustomers = movieRepository.findAll(new PageRequest(page, size));

		return pageOfCustomers;
	}

	public CustomerRating createCustomerRating(CustomerRating customerRating) {
		return customerRatingRepository.save(customerRating);
	}

	public Page<CustomerRating> getAllCustomerRatings(Integer page, Integer size) {
		Page pageOfCustomers = customerRatingRepository.findAll(new PageRequest(page, size));

		return pageOfCustomers;
	}

	public String getHighestRatedMovie() {
		List<CustomerRating> customerRatingList = new ArrayList<CustomerRating>();
		List<CustomerRating> avgRatingList = new ArrayList<>();
		
		customerRatingRepository.findAll().forEach(cr -> customerRatingList.add(cr));
		
		//geting the most rating movie
		customerRatingList.stream().collect(
				Collectors.groupingBy(cr -> cr.getMovieId(), Collectors.averagingInt(cr -> cr.getCustomerRating())))
				.forEach((movieId, avgRating) -> {
					CustomerRating avgCr = new CustomerRating();
					avgCr.setMovieId(movieId);
					avgCr.setAvgRating(avgRating);
					avgRatingList.add(avgCr);
				});
		Collections.sort(avgRatingList, Comparator.comparingDouble(CustomerRating::getAvgRating));
		CustomerRating highestRatingObj = avgRatingList.get(avgRatingList.size() - 1);
		Movie highestRatedMovie = movieRepository.findOne(highestRatingObj.getMovieId());
		String highestRatedMovieStr = highestRatedMovie.getName()+" - "+highestRatingObj.getAvgRating();
		System.out.println("Highest Rated movie: " + highestRatedMovieStr);
		return highestRatedMovieStr;
	}
	
	public Customer getCustomerGiviesMostRatingAverage() {
		List<CustomerRating> customerRatingList = new ArrayList<CustomerRating>();
		List<Customer> custAvgRatingList = new ArrayList<>();
		List<Double> allRatingsList = new ArrayList<>();
		
		customerRatingRepository.findAll().forEach(cr -> customerRatingList.add(cr));
		
		//getting the customer having highest average rating
		customerRatingList.stream().collect(
				Collectors.groupingBy(c -> c.getCustomerId(), Collectors.averagingInt(c -> c.getCustomerRating())))
				.forEach((customerId, custAvgRating) -> {
					Customer custAvg = new Customer();
					custAvg.setId(customerId);
					custAvg.setCustomerAverageRating(custAvgRating);
					custAvgRatingList.add(custAvg);
					allRatingsList.add(custAvgRating);
				});
		
		Collections.sort(custAvgRatingList, Comparator.comparingDouble(Customer::getCustomerAverageRating));
		Customer highestRatingCustObj = custAvgRatingList.get(custAvgRatingList.size() - 1);
		Customer highestRatedCustDetails = customerRepository.findOne(highestRatingCustObj.getId());
		highestRatingCustObj.setFname(highestRatedCustDetails.getFname());
		highestRatingCustObj.setLname(highestRatedCustDetails.getLname());
		System.out.println("Customer having highest rating: " + highestRatingCustObj);
		
		//getting the overall average customer rating
		DoubleSummaryStatistics stats = allRatingsList.stream().mapToDouble((rating) ->rating).summaryStatistics();
		highestRatingCustObj.setAverageRating(stats.getAverage());
		return highestRatingCustObj;
	}
}
