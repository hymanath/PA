package com.itgrids.dto;

import java.util.List;

public class NregaLocationOverviewVO {
	
	     private String uniqueId;
	     private String district;
	     private String constituency;
	     private String mandal;
	     private String panchayt;
		 private Long target;
		 private Long grounded;
		 private Long noTGrounded;
		 private Long completed;
		 private String type;
		 private Long green;
		 private Long red;
		 private Long orange;
		 private Long total;
		 private String percentage;
		 private List<NregaLocationOverviewVO> subList1;
		 private List<NregaLocationOverviewVO> subList2;
	  
		public String getUniqueId() {
			return uniqueId;
		}
		public void setUniqueId(String uniqueId) {
			this.uniqueId = uniqueId;
		}
		public String getDistrict() {
			return district;
		}
		public void setDistrict(String district) {
			this.district = district;
		}
		public String getConstituency() {
			return constituency;
		}
		public void setConstituency(String constituency) {
			this.constituency = constituency;
		}
		public String getMandal() {
			return mandal;
		}
		public void setMandal(String mandal) {
			this.mandal = mandal;
		}
		public String getPanchayt() {
			return panchayt;
		}
		public void setPanchayt(String panchayt) {
			this.panchayt = panchayt;
		}
		public Long getTarget() {
			return target;
		}
		public void setTarget(Long target) {
			this.target = target;
		}
		public Long getGrounded() {
			return grounded;
		}
		public void setGrounded(Long grounded) {
			this.grounded = grounded;
		}
		public Long getNoTGrounded() {
			return noTGrounded;
		}
		public void setNoTGrounded(Long noTGrounded) {
			this.noTGrounded = noTGrounded;
		}
		public Long getCompleted() {
			return completed;
		}
		public void setCompleted(Long completed) {
			this.completed = completed;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Long getGreen() {
			return green;
		}
		public void setGreen(Long green) {
			this.green = green;
		}
		public Long getRed() {
			return red;
		}
		public void setRed(Long red) {
			this.red = red;
		}
		public Long getOrange() {
			return orange;
		}
		public void setOrange(Long orange) {
			this.orange = orange;
		}
		public Long getTotal() {
			return total;
		}
		public void setTotal(Long total) {
			this.total = total;
		}
		public String getPercentage() {
			return percentage;
		}
		public void setPercentage(String percentage) {
			this.percentage = percentage;
		}
		public List<NregaLocationOverviewVO> getSubList1() {
			return subList1;
		}
		public void setSubList1(List<NregaLocationOverviewVO> subList1) {
			this.subList1 = subList1;
		}
		public List<NregaLocationOverviewVO> getSubList2() {
			return subList2;
		}
		public void setSubList2(List<NregaLocationOverviewVO> subList2) {
			this.subList2 = subList2;
		}
	  
	  
}
