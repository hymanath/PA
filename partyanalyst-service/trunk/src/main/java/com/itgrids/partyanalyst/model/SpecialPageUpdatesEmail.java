package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="special_page_updates_email")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpecialPageUpdatesEmail implements Serializable {
	 
	private static final long serialVersionUID = 6903207903832835488L;
	private Long specialPageUpdatesEmailId;
	private Long specialPageId;
	private String email;
	private String unsubscribed;
	 
	public SpecialPageUpdatesEmail(){
		
	}
	public SpecialPageUpdatesEmail(Long specialPageId, String email,String unsubscribed){
		this.specialPageId = specialPageId;
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
	  //special_page_id        bigint(15) NULL
	@Column(name="special_page_id")
	public Long getSpecialPageId() {
		return specialPageId;
	}
	public void setSpecialPageId(Long specialPageId) {
		this.specialPageId = specialPageId;
	}
	 //email            varchar(100) NULL
	@Column(name="email",length=100)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
