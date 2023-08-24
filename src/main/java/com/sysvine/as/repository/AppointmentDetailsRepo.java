package com.sysvine.as.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sysvine.as.model.AppointmentDetails;

/**
 * 
 * @author Bagavath
 *
 */
public interface AppointmentDetailsRepo extends JpaRepository<AppointmentDetails, Integer> {
	
	boolean existsById(Integer id);

}
