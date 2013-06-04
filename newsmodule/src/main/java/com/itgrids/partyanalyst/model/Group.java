package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import com.itgrids.partyanalyst.model.BaseModel;

import java.util.List;

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

@Entity
@Table (name="groups")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Group extends BaseModel implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Long groupId;
	private String groupName;
	private String comments;
//	private List<AllianceGroup> allianceGroups;


	
	public Group() {
		super();
	}

	/*public Group(Long groupId, String groupName, String comments,
			List<AllianceGroup> allianceGroups) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.comments = comments;
		this.allianceGroups = allianceGroups;
	}*/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "group_id", unique = true, nullable = false)
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Column(name="group_name")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name="comments")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	public List<AllianceGroup> getAllianceGroups() {
		return allianceGroups;
	}

	public void setAllianceGroups(List<AllianceGroup> allianceGroups) {
		this.allianceGroups = allianceGroups;
	}*/
}
