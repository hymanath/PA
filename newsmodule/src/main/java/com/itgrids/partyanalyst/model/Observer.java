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
@Table(name = "observer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Observer extends BaseModel implements java.io.Serializable{

	private Long observerId;
	private String observerName;
	private String isDeleted;
	private Set<DebateObserver> debateObserver = new HashSet<DebateObserver>(0);

	
	//default constructor.
	
		public Observer() {
		}
		//setters and getters.
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "observer_id", unique = true, nullable = false)
		public Long getObserverId() {
			return observerId;
		}


		public void setObserverId(Long observerId) {
			this.observerId = observerId;
		}

		@Column(name = "observer_name", length = 50)
		public String getObserverName() {
			return observerName;
		}


		public void setObserverName(String observerName) {
			this.observerName = observerName;
		}

		@Column(name ="is_deleted",length = 1,nullable=true,updatable=true)
        public String getIsDeleted() {
			return isDeleted;
		}


		public void setIsDeleted(String isDeleted) {
			this.isDeleted = isDeleted;
		}
	
	
		
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "observer")
		@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
		public Set<DebateObserver> getDebateObserver() {
			return debateObserver;
		}


		public void setDebateObserver(Set<DebateObserver> debateObserver) {
			this.debateObserver = debateObserver;
		}
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
