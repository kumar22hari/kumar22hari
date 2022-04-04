package com.loan.inheritanceexp;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class BaseEntity {
 

    private String createdBy;
 
    private String lastUpdatedBy;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
    
    
 
}