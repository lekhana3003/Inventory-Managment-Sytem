package com.example.demo.Exception;

import java.util.Date;

	public class ExceptionResponse {
		private Date timestamp;
		private String message;
		  public ExceptionResponse( String message) {
			    super();
			    this.timestamp =new Date();
			    this.message = message;
			    
			  }
		 
		  public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}
		public void setMessage(String message) {
			this.message = message;
		}

	public Date getTimestamp() {
	    return timestamp;
	  }

	  public String getMessage() {
	    return message;
	  }

	  @Override
			public String toString() {
				return "ExceptionResponse [timestamp=" + timestamp + ", message=" + message + "]";
			}

	}