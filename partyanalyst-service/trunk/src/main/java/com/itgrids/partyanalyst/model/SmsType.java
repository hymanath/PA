package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
@Table(name="sms_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsType extends BaseModel implements Serializable{
	
	    private static final long serialVersionUID = 1L;
	
		 private Long smsTypeId;
		 private String type;
	    
		 private Set<SmsTrack> smsTrack = new HashSet<SmsTrack>(0);
	     private Set<SmsResponseDetails> smsResponseDetails = new HashSet<SmsResponseDetails>(0);




		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "sms_type_id", unique = true, nullable = false)
		public Long getSmsTypeId() {
			return smsTypeId;
		}
		public void setSmsTypeId(Long smsTypeId) {
			this.smsTypeId = smsTypeId;
		}
		
		@Column(name="type" , length = 10)
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "smsType")
		@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	    public Set<SmsTrack> getSmsTrack() {
			return smsTrack;
		}
		public void setSmsTrack(Set<SmsTrack> smsTrack) {
			this.smsTrack = smsTrack;
		}		
		
	
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "smsType")
		@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
		public Set<SmsResponseDetails> getSmsResponseDetails() {
			return smsResponseDetails;
		}
		public void setSmsResponseDetails(Set<SmsResponseDetails> smsResponseDetails) {
			this.smsResponseDetails = smsResponseDetails;
		}
		 
}

