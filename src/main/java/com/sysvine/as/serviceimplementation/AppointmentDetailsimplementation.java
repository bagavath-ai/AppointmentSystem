package com.sysvine.as.serviceimplementation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysvine.as.dto.ResponseDTO;
import com.sysvine.as.dto.ResponseDTO.message;
import com.sysvine.as.dto.ResponseDTO.status;
import com.sysvine.as.model.AppointmentDetails;
import com.sysvine.as.repository.AppointmentDetailsRepo;
import com.sysvine.as.service.AppointmentDetailsService;

import jakarta.transaction.Transactional;

@Service("appointment")
public class AppointmentDetailsimplementation implements AppointmentDetailsService {

	@Autowired
	AppointmentDetailsRepo appointmentDetailsRepo;

	public ResponseDTO getAppointmentList() {
		ResponseDTO response = new ResponseDTO(status.TRUE, message.SUCCESS,  appointmentDetailsRepo.findAll());
		return response;
	};

	@Transactional
	public ResponseDTO postAppointmentList(AppointmentDetails appointmentDetails) {
		ResponseDTO response = new ResponseDTO();
		try {
			AppointmentDetails save = appointmentDetailsRepo.save(appointmentDetails);
			response = new ResponseDTO(status.TRUE, message.SUCCESS,  Arrays.asList(save));
		} catch (Exception e) {
			response = new ResponseDTO(status.FALSE, message.FAILED, e.getMessage());

		}
		return response;
	};

	@Transactional
	public ResponseDTO getAppointment(Integer id) {
		ResponseDTO response = new ResponseDTO();
		try {
			Optional<AppointmentDetails> appointmentDetial = appointmentDetailsRepo.findById(id);
			if (appointmentDetial.isPresent()) {
				response = new ResponseDTO(status.TRUE, message.SUCCESS, Arrays.asList(appointmentDetial.get()));
			}else {
				response = new ResponseDTO(status.FALSE, message.FAILED, "No data present");
			}
		} catch (Exception e) {
			response = new ResponseDTO(status.FALSE, message.FAILED, e.getMessage());

		}
		return response;

	};
	
	public ResponseDTO deleteAppointment(int id) {
		ResponseDTO response = new ResponseDTO();
		try {
			if (appointmentDetailsRepo.existsById(id)) {
				appointmentDetailsRepo.deleteById(id);
				response = new ResponseDTO(status.TRUE, message.SUCCESS, Arrays.asList());
			}else {
				response = new ResponseDTO(status.FALSE, message.FAILED, "No data present");
			}
		} catch (Exception e) {
			response = new ResponseDTO(status.FALSE, message.FAILED, e.getMessage());

		}
		return response;

	};


	
	
	

}
