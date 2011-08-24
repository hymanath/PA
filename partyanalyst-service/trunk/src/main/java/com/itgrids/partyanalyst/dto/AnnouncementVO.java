package com.itgrids.partyanalyst.dto;

import java.util.Date;

public class AnnouncementVO {
	    private String title;
	    private String message;
	    private String fromdate;
	    private String todate;
	    private long userId;
	    private long constituencyId;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getFromdate() {
			return fromdate;
		}
		public void setFromdate(String fromdate) {
			this.fromdate = fromdate;
		}
		public String getTodate() {
			return todate;
		}
		public void setTodate(String todate) {
			this.todate = todate;
		}
		public long getUserId() {
			return userId;
		}
		public void setUserId(long userid) {
			this.userId = userId;
		}
		public long getConstituencyId() {
			return constituencyId;
		}
		public void setConstituencyId(long constituencyId) {
			this.constituencyId = constituencyId;
		}
	    
}
