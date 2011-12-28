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
@Table(name="special_page_updates_email")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpecialPageUpdatesEmail implements Serializable {
	 
	private static final long serialVersionUID = 6903207903832835488L;
	private Long specialPageUpdatesEmailId;
	private SpecialPage specialPage;
	private String email;
	private String unsubscribed;
	 
	public SpecialPageUpdatesEmail(){
		
	}
	public SpecialPageUpdatesEmail(SpecialPage specialPage, String email,String unsubscribed){
		this.specialPage = specialPage;
		this.email = email;
		this.unsubscribed = unsubscribed;
		
	}
	//special_page_updates_email_id    bigint(15) AUTO_INCREMENT NOT NULL
	//PRIMARY KEY(special_page_updates_email_id)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="special_page_updates_email_id",unique=true,nullable=false)
	public Long getSpecialPageUpdatesEmailId() {
		return specialPageUpdatesEmailId;
	}
	public void setSpecialPageUpdatesEmailId(Long specialPageUpdatesEmailId) {
		this.specialPageUpdatesEmailId = specialPageUpdatesEmailId;
	}

	@Column(name="email",length=100)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "special_page_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public SpecialPage getSpecialPage() {
		return specialPage;
	}
	
	public void setSpecialPage(SpecialPage specialPage) {
		this.specialPage = specialPage;
	}
	
	//unsubscribed        varchar(10) NULL
	@Column(name="unsubscribed",length=10)
	public String getUnsubscribed() {
		return unsubscribed;
	}
	public void setUnsubscribed(String unsubscribed) {
		this.unsubscribed = unsubscribed;
	}
	
	
    
    
    

}
