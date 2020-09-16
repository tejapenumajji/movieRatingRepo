package com.movierating.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;


@Entity
@Table(name = "customer")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    @Id
    @GeneratedValue()
    private long id;

    @Column(nullable = false)
    private String fname;
    
    @Column()
    private String lname;
    
    private double customerAverageRating;
    
    private double averageRating;

    public Customer() {
    }
    
    public Customer(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    public long getId() {
        return this.id;
    }

    // for tests ONLY
    public void setId(long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
    
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    
	public double getCustomerAverageRating() {
		return customerAverageRating;
	}

	public void setCustomerAverageRating(double customerAverageRating) {
		this.customerAverageRating = customerAverageRating;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", fname=" + fname + ", lname=" + lname + ", customerAverageRating="
				+ customerAverageRating + ", averageRating=" + averageRating + "]";
	}

}
