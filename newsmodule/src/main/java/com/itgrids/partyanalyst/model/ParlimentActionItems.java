package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "parliment_action_items")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParlimentActionItems implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
	private Long parlimentActionItemsId;
	private ParlimentPoliticalFeedback parlimentPoliticalFeedback;
	private String actionItem;
	private String source;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "parliment_action_items_id", unique = true, nullable = false)
	public Long getParlimentActionItemsId() {
		return parlimentActionItemsId;
	}
	public void setParlimentActionItemsId(Long parlimentActionItemsId) {
		this.parlimentActionItemsId = parlimentActionItemsId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "parliment_political_feedback_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ParlimentPoliticalFeedback getParlimentPoliticalFeedback() {
		return parlimentPoliticalFeedback;
	}
	public void setParlimentPoliticalFeedback(
			ParlimentPoliticalFeedback parlimentPoliticalFeedback) {
		this.parlimentPoliticalFeedback = parlimentPoliticalFeedback;
	}
	
	@Column(name="action_item")
	public String getActionItem() {
		return actionItem;
	}
	public void setActionItem(String actionItem) {
		this.actionItem = actionItem;
	}
	
	@Column(name="source")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
	
}
