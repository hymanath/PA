package com.itgrids.partyanalyst.dto;

public class BaseDTO {
	
		public final static String SUCCESS = "SUCCESS";
		public final static String PARTIAL = "PARTIAL";
		public final static String FAILURE = "FAILURE";
		
		private String requestStatus;

		public String getRequestStatus() {
			return requestStatus;
		}

		public void setRequestStatus(String requestStatus) {
			this.requestStatus = requestStatus;
		}

}
