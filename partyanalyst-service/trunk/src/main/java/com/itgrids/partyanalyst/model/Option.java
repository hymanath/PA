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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "options")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Option extends BaseModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long optionsId;
	private String options;
	private String description;
	private Long orderId;
	private Option parentOption;
	private OptionType optionType;
	private String subOptionName;
	private String hasRemarks;
	private UpdationDetails updationDetails;
		
	private Set<QuestionOptions> questionOptions = new HashSet<QuestionOptions>(0);

	public Option() {
	}
	
	public Option(Long optionsId,String options,String description,Long orderId,Option parentOption,OptionType optionType,String subOptionName,String hasRemarks,UpdationDetails updationDetails) {
		this.optionsId = optionsId;
		this.options = options;
		this.description = description;
		this.orderId = orderId;
		this.parentOption = parentOption;
		this.optionType = optionType;
		this.subOptionName = subOptionName;
		this.hasRemarks = hasRemarks;
		this.updationDetails = updationDetails;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "options_id", unique = true, nullable = false)
	public Long getOptionsId() {
		return optionsId;
	}

	public void setOptionsId(Long optionsId) {
		this.optionsId = optionsId;
	}

	@Column(name = "options", length = 100)
	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "order_id", length = 100)
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="parent_option_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Option getParentOption() {
		return parentOption;
	}

	public void setParentOption(Option parentOption) {
		this.parentOption = parentOption;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="option_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public OptionType getOptionType() {
		return optionType;
	}

	public void setOptionType(OptionType optionType) {
		this.optionType = optionType;
	}

	@Column(name = "sub_option_name", length = 100)
	public String getSubOptionName() {
		return subOptionName;
	}

	public void setSubOptionName(String subOptionName) {
		this.subOptionName = subOptionName;
	}

	@Column(name = "has_remarks", length = 100)
	public String getHasRemarks() {
		return hasRemarks;
	}

	public void setHasRemarks(String hasRemarks) {
		this.hasRemarks = hasRemarks;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updation_details_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UpdationDetails getUpdationDetails() {
		return updationDetails;
	}

	public void setUpdationDetails(UpdationDetails updationDetails) {
		this.updationDetails = updationDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "options")
	public Set<QuestionOptions> getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(Set<QuestionOptions> questionOptions) {
		this.questionOptions = questionOptions;
	}
	
	
}
