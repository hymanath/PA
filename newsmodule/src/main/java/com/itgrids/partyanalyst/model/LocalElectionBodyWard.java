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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
/**
 * LocalElectionBodyWard entity. 
 * @author Y.Ravi Kiran.
 * @date 24-10-10.
 */
@Entity
@Table(name = "local_election_body_ward")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LocalElectionBodyWard extends BaseModel implements Serializable {

	private static final long serialVersionUID = 8117328490181862137L;
	private Long localElectionBodyWardId;
	private String wardName;
	private String cirlceName;
	private String cirlceZone;
	private Constituency constituency;
	
	//Default constructor
		public  LocalElectionBodyWard(){
			
		} 
	
	//Primary key Constructor
		public  LocalElectionBodyWard(Long localElectionBodyWardId){
			this.localElectionBodyWardId = localElectionBodyWardId;
		} 
	
	//Full Constructor
		public  LocalElectionBodyWard(String wardName,String cirlceName,Constituency constituency){
			this.wardName = wardName;
			this.cirlceName = cirlceName;
			this.constituency = constituency;
		}

		
		// Property accessors
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "local_election_body_ward_id ", unique = true, nullable = false)
		public Long getLocalElectionBodyWardId() {
			return localElectionBodyWardId;
		}

		public void setLocalElectionBodyWardId(Long localElectionBodyWardId) {
			this.localElectionBodyWardId = localElectionBodyWardId;
		}

		@Column(name = "ward_name", length = 100)
		public String getWardName() {
			return wardName;
		}

		public void setWardName(String wardName) {
			this.wardName = wardName;
		}

		@Column(name = "circle_name", length = 100)
		public String getCirlceName() {
			return cirlceName;
		}

		public void setCirlceName(String cirlceName) {
			this.cirlceName = cirlceName;
		}

		@Column(name = "circle_zone", length = 100)
		public String getCirlceZone() {
			return cirlceZone;
		}

		public void setCirlceZone(String cirlceZone) {
			this.cirlceZone = cirlceZone;
		}

		@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
		@JoinColumn(name = "constituency_id")
		@LazyToOne(LazyToOneOption.NO_PROXY)
		@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
		public Constituency getConstituency() {
			return constituency;
		}

		public void setConstituency(Constituency constituency) {
			this.constituency = constituency;
		}
		
		
	
}
