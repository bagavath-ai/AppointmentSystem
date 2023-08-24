package com.sysvine.as.model;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE APPOINTMENT_DETAILS SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Builder
public class AppointmentDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "patient_name")
	private String PatientName;
	
	@Column(name = "doctor_name")
	private String DoctorName;
	
	@Column(name = "appointment_date")
	private Date AppointmentDate;
	
	@Column(name = "appointment_time")
	private LocalTime  AppointmentTime;
	
	@CreationTimestamp
	@Column(name = "created_by")
	private Timestamp CreatedBy;
	
	@CreationTimestamp
	@Column(name = "updated_by")
	private Timestamp UpdatedBy;
	
	@Column(name = "is_deleted")
	private boolean isDeleted ;

}
