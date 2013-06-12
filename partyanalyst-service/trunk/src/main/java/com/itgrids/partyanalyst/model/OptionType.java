package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "option_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OptionType extends BaseModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long optionTypeId;
	private String optionType;
	
	private Set<Option> option = new HashSet<Option>(0);
	private Set<SurveyQuestion> surveyQuestion = new HashSet<SurveyQuestion>(0);
	
	public OptionType() {
	}
	public OptionType(Long optionTypeId,String optionType) {
		this.optionTypeId = optionTypeId;
		this.optionType = optionType;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "option_type_id", unique = true, nullable = false)
	public Long getOptionTypeId() {
		return optionTypeId;
	}
	public void setOptionTypeId(Long optionTypeId) {
		this.optionTypeId = optionTypeId;
	}
	@Column(name = "option_type", length = 100)
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "optionType")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<Option> getOption() {
		return option;
	}

	public void setOption(Set<Option> option) {
		this.option = option;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "optionType")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<SurveyQuestion> getSurveyQuestion() {
		return surveyQuestion;
	}
	public void setSurveyQuestion(Set<SurveyQuestion> surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}
	
	
}
