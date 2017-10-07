package com.itgrids.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InnovationSocietyDtlsVO {

	private String title;
	
	private List<ApInnovationSocietyDtlsVO> data;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ApInnovationSocietyDtlsVO> getData() {
		return data;
	}

	public void setData(List<ApInnovationSocietyDtlsVO> data) {
		this.data = data;
	}
	
}
