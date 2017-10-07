package com.itgrids.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApInnovationSocietyDtlsVO {

	
	private String Parameter;
	private String Target;
	private String Achieved;

	
	public String getParameter() {
		return Parameter;
	}
	public void setParameter(String parameter) {
		Parameter = parameter;
	}
	public String getTarget() {
		return Target;
	}
	public void setTarget(String target) {
		Target = target;
	}
	public String getAchieved() {
		return Achieved;
	}
	public void setAchieved(String achieved) {
		Achieved = achieved;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApInnovationSocietyDtlsVO [Parameter=");
		builder.append(Parameter);
		builder.append(", Target=");
		builder.append(Target);
		builder.append(", Achieved=");
		builder.append(Achieved);
		builder.append("]");
		return builder.toString();
	}
	
   	
	
}
