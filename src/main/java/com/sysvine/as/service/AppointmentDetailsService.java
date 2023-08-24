package com.sysvine.as.service;

import java.util.List;

import com.sysvine.as.dto.ResponseDTO;
import com.sysvine.as.model.AppointmentDetails;

public interface AppointmentDetailsService {

	public ResponseDTO getAppointmentList();
	
	public ResponseDTO getAppointment(Integer id );
	
	public ResponseDTO postAppointmentList(AppointmentDetails appointmentDetails);
	
	public ResponseDTO deleteAppointment(int id);

}
