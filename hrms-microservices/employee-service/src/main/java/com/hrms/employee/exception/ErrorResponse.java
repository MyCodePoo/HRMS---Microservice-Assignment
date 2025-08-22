package com.hrms.employee.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
	
	 private boolean success;
	    private String message;
	    private String details;
	    private LocalDateTime timestamp;

	    public ErrorResponse(boolean success, String message, String details, LocalDateTime timestamp) {
	        this.success = success;
	        this.message = message;
	        this.details = details;
	        this.timestamp = timestamp;
	    }

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getDetails() {
			return details;
		}

		public void setDetails(String details) {
			this.details = details;
		}

		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}
	    
	    
	    

}
