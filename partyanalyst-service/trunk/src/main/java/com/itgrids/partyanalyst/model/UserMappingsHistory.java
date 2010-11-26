/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 25, 2010
 */

package com.itgrids.partyanalyst.model;

import java.util.Date;

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

import org.hibernate.annotations.NotFoundAction;

/**
 * UserMappingsHistory entity. 
 * This class maintains details of the mappings modified or updated/deleted by the user
 * through the mapping user interface provided in the admin section     
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "user_mappings_history")
public class UserMappingsHistory extends BaseModel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	   * Holds the primary key of the table. 
	   *
	   */
	private Long userMappingsHistoryId;
	/**
	   * Holds the user object . 
	   *
	   */
	private Registration user;
	/**
	   * Holds the database table name in which modifications took place. 
	   *
	   */
	
	private String tableName;
	/**
	   * Holds the date/time on which the modifications were made. 
	   *
	   */
	
	private Date lastUpdated;
	/**
	   * Holds the number of rows inserted newly or updated in the table. 
	   *
	   */	
	private Long noOfRowsUpdated;
	/**
	   * Holds the number of rows deleted from the table. 
	   *
	   */
	private Long noOfRowsDeleted;
	
	public UserMappingsHistory() {
		super();		
	}

	public UserMappingsHistory(Long userMappingsHistoryId, Registration user,
			String tableName, Date lastUpdated, Long noOfRowsUpdated,
			Long noOfRowsDeleted) {
		super();
		this.userMappingsHistoryId = userMappingsHistoryId;
		this.user = user;
		this.tableName = tableName;
		this.lastUpdated = lastUpdated;
		this.noOfRowsUpdated = noOfRowsUpdated;
		this.noOfRowsDeleted = noOfRowsDeleted;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_mappings_history_id", nullable = false, unique = true)	
	public Long getUserMappingsHistoryId() {
		return userMappingsHistoryId;
	}

	public void setUserMappingsHistoryId(Long userMappingsHistoryId) {
		this.userMappingsHistoryId = userMappingsHistoryId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	 
	public Registration getUser() {
		return user;
	}

	public void setUser(Registration user) {
		this.user = user;
	}

	@Column(name = "table_name", length = 100)
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "last_updated", length = 10)
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Column(name = "no_rows_updated")
	public Long getNoOfRowsUpdated() {
		return noOfRowsUpdated;
	}

	public void setNoOfRowsUpdated(Long noOfRowsUpdated) {
		this.noOfRowsUpdated = noOfRowsUpdated;
	}

	@Column(name = "no_rows_deleted")
	public Long getNoOfRowsDeleted() {
		return noOfRowsDeleted;
	}

	public void setNoOfRowsDeleted(Long noOfRowsDeleted) {
		this.noOfRowsDeleted = noOfRowsDeleted;
	}
	
	
	
	
	
	
	
	
}
