var app = {};

app.ui = {
	getAppointmentList: function() {
		$(document).ready(function() {
			// Function to populate the table
			function populateTable(data) {
				var tableBody = $('#dataTable tbody');
				tableBody.empty(); // Clear existing data

				data.forEach(function(row, index) {
					let utcDate = moment(row.appointmentDate);
					var newRow = `<tr>
                        <th scope="row">${index + 1}</th>
                        <td>${row.patientName}</td>
                        <td>${row.doctorName}</td>
                        <td>${utcDate.format("YYYY-MM-DD")}</td>
                        <td>${row.appointmentTime}</td>
                        
                        <td><button class="btn btn-sm btn-primary edit-btn" data-id="${row.id}"  onclick=app.ui.editAppointment(this)>Edit</button>
                          <button class="btn btn-sm btn-danger delete-btn" data-id="${row.id}" onclick=app.ui.deleteAppointment(this)>Delete</button></td>
                      </tr>`;
					tableBody.append(newRow);
				});
			}

			// Make an AJAX GET request
			$.ajax({
				url: "/api/appointment", // Replace with your API endpoint
				method: 'GET',
				success: function(response) {
					// Assuming response is an array of objects with patientName, doctorName,  date and time properties
					populateTable(response.data);
				},
				error: function(error) {
					console.error('Error:', error);
				}
			});
		});

	},
	postAppointment: function() {
		var doctorName = $('#doctor').val();
		var patientName = $('#patient').val();
		var appointmentDate = $('#datePicker').val();
		var appointmentTime = $('#timePicker').val();

		var emptyFields = [];

		if (doctorName.trim() === "") {
			emptyFields.push("Doctor Name");
		}
		if (patientName.trim() === "") {
			emptyFields.push("Patient Name");
		}
		if (appointmentDate.trim() === "") {
			emptyFields.push("Appointment Date");
		}

		if (appointmentTime.trim() === "") {
			emptyFields.push("Appointment Date");
		}

		if (emptyFields.length > 0) {
			event.preventDefault();
			var message = "Please fill in the following required fields: " + emptyFields.join(", ");
			Swal.fire('Fields Required!', message, 'warning');

			return;
		}

		var json = {
			"doctorName": doctorName,
			"appointmentTime": appointmentTime,
			"patientName": patientName,
			"appointmentDate": appointmentDate,
			"isDeleted": true
		}

		$.ajax({
			url: "/api/appointment", // Replace with your API endpoint
			method: 'POST',
			data: JSON.stringify(json),
			contentType: 'application/json',
			success: function(response) {
				$('#doctor').val("");
				$('#patient').val("");
				$('#datePicker').val("");
				$('#timePicker').val("");
				$('#ModalAdd').modal('hide');
				Swal.fire('Saved!', 'Appointment has been Saved.', 'success');

				app.ui.getAppointmentList();
			},
			error: function(error) {
				console.error('Error:', error);
			}
		});
	},
	deleteAppointment: function(ele) {
		var id = $(ele).data('id');

		Swal.fire({
			title: 'Delete',
			text: "Are you sure you want to delete this Appointment?!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes, delete it!'
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url: '/api/appointment/' + id, // Replace with your API endpoint for deleting data
					method: 'DELETE',
					success: function(response) {
						if (response.status == "TRUE") {

							Swal.fire('Deleted!', 'Appointment has been deleted.', 'success');

							app.ui.getAppointmentList();
						} else {

							Swal.fire('Deleted!', 'Appointment failed To delete.', 'warning');

						}
					},
					error: function(error) {
						console.error('Error:', error);
					}
				});
			}
		})

	},
	editAppointment: function(ele) {
		var id = $(ele).data('id');
		$.ajax({
			url: '/api/appointment/' + id, // Replace with your API endpoint for fetching data
			method: 'GET',
			success: function(data) {
				// Populate modal with fetched data
				let utcDate = moment(data.data[0].appointmentDate);
				$('#id').val(data.data[0].id);
				$('#doctoredit').val(data.data[0].doctorName);
				$('#patientedit').val(data.data[0].patientName);
				$('#datePickeredit').val(utcDate.format("YYYY-MM-DD"));
				$('#timePickeredit').val(data.data[0].appointmentTime);
				$('#ModalEdit').modal('show');

			},
			error: function(error) {
				console.error('Error:', error);
			}
		});
	},
	updateAppointment: function() {
		var doctorName = $('#doctoredit').val();
		var patientName = $('#patientedit').val();
		var appointmentDate = $('#datePickeredit').val();
		var appointmentTime = $('#timePickeredit').val();
		var id = $('#id').val();

		var emptyFields = [];

		if (doctorName.trim() === "") {
			emptyFields.push("Doctor Name");
		}
		if (patientName.trim() === "") {
			emptyFields.push("Patient Name");
		}
		if (appointmentDate.trim() === "") {
			emptyFields.push("Appointment Date");
		}

		if (appointmentTime.trim() === "") {
			emptyFields.push("Appointment Date");
		}

		if (emptyFields.length > 0) {
			event.preventDefault();
			var message = "Please fill in the following required fields: " + emptyFields.join(", ");
			Swal.fire('Fields Required!', message, 'warning');

			return;
		}



		var json = {
			"id": id,
			"doctorName": doctorName,
			"appointmentTime": appointmentTime,
			"patientName": patientName,
			"appointmentDate": appointmentDate,
			"isDeleted": true
		}

		$.ajax({
			url: "/api/appointment", // Replace with your API endpoint
			method: 'PATCH',
			data: JSON.stringify(json),
			contentType: 'application/json',
			success: function(response) {
				$('#doctoredit').val("");
				$('#patientedit').val("");
				$('#datePickeredit').val("");
				$('#timePickeredit').val("");
				$('#ModalEdit').modal('hide');
				Swal.fire('Updated!', 'Appointment has been Updated.', 'success');

				app.ui.getAppointmentList();
			},
			error: function(error) {
				console.error('Error:', error);
			}
		});
	},

}


app.ui.getAppointmentList();

