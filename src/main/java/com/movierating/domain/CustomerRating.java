package com.movierating.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;


@Entity
@Table(name = "customerRating")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerRating {

    @Id
    @GeneratedValue()
    private long id;

    @Column()
    private long customerId;
    
    @Column()
    private int customerRating;
    
    @Column()
    private long movieId;
    
    private double avgRating;

    public CustomerRating() {
    }
    
    public CustomerRating(long customerId, long movieId, int customerRating) {
        this.customerId = customerId;
        this.movieId = movieId;
        this.customerRating = customerRating;
    }
    
    public CustomerRating(long movieId, int customerRating) {
        this.movieId = movieId;
        this.customerRating = customerRating;
    }

    public long getId() {
        return this.id;
    }

    // for tests ONLY
    public void setId(long id) {
        this.id = id;
    }

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public int getCustomerRating() {
		return customerRating;
	}

	public void setCustomerRating(int customerRating) {
		this.customerRating = customerRating;
	}
	
	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	@Override
	public String toString() {
		return "CustomerRating [id=" + id + ", customerId=" + customerId + ", customerRating=" + customerRating
				+ ", movieId=" + movieId + ", avgRating=" + avgRating + "]";
	}

}
