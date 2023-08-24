package com.sysvine.as.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sysvine.as.dto.ResponseDTO;
import com.sysvine.as.model.AppointmentDetails;
import com.sysvine.as.service.AppointmentDetailsService;
/**
 * 
 * @author Bagavath
 * 
 * Below class is use for access the API 
 *
 */

@RestController
@RequestMapping("/api/appointment")
public class AutoController {
	
	@Autowired
	AppointmentDetailsService AppointmentService;
	
	/**
	 * GET all appointment
	 * @return
	 */
	@GetMapping
	public ResponseDTO getAppointment() {
		return AppointmentService.getAppointmentList();
	}
	
	/**
	 * POST appointment ment
	 * @param appointmentDetials
	 * @return
	 */
	
	@PostMapping
	public ResponseDTO saveAppointment(@RequestBody AppointmentDetails appointmentDetials) {
		return AppointmentService.postAppointmentList(appointmentDetials);
	}
	
	/**
	 * GET appointment by id
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseDTO getAppointmentByid(@PathVariable Integer id ) {
		return AppointmentService.getAppointment(id);
	}
	
	/**
	 * Update the appointment 
	 * @param appointmentDetials
	 * @return
	 */
	@PatchMapping
	public ResponseDTO updateAppointmentByid(@RequestBody AppointmentDetails appointmentDetials) {
		return AppointmentService.postAppointmentList(appointmentDetials);
	}
	
	/**
	 * 
	 * GET appointment by id and Delete it
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseDTO deleteAppointmentByid(@PathVariable Integer id ) {
		return AppointmentService.deleteAppointment(id);
	}
	

}
