package com.itgrids.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tressed_habitation")
public class TressedHabitation{
	private Long tressedHabitationId;
	private Long districtCode;
	private String districtName;
	private Long constituencyCode;
	private String constituencyName;
	private Long mandalCode;
	private String mandalName;
	private String habitationCode;
	private String habitationName;
	private String coverageStatus;
	private String isDeleted;
	public TressedHabitation(){}
	public TressedHabitation(Long tressedHabitationId, Long districtCode, String districtName, Long constituencyCode,
			String constituencyName, Long mandalCode, String mandalName, String habitationCode, String habitationName,
			String coverageStatus, String isDeleted) {
		super();
		this.tressedHabitationId = tressedHabitationId;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.constituencyCode = constituencyCode;
		this.constituencyName = constituencyName;
		this.mandalCode = mandalCode;
		this.mandalName = mandalName;
		this.habitationCode = habitationCode;
		this.habitationName = habitationName;
		this.coverageStatus = coverageStatus;
		this.isDeleted = isDeleted;
	}
	@Id
	@Column(name="tressed_habitation_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getTressedHabitationId() {
		return tressedHabitationId;
	}
	public void setTressedHabitationId(Long tressedHabitationId) {
		this.tressedHabitationId = tressedHabitationId;
	}
	@Column(name="district_code")
	public Long getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(Long districtCode) {
		this.districtCode = districtCode;
	}
	@Column(name="district_name")
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	@Column(name="constituency_code")
	public Long getConstituencyCode() {
		return constituencyCode;
	}
	public void setConstituencyCode(Long constituencyCode) {
		this.constituencyCode = constituencyCode;
	}
	@Column(name="constituency_name")
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	@Column(name="mandal_code")
	public Long getMandalCode() {
		return mandalCode;
	}
	public void setMandalCode(Long mandalCode) {
		this.mandalCode = mandalCode;
	}
	@Column(name="mandal_name")
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	
	@Column(name="habitation_code")
	public String getHabitationCode() {
		return habitationCode;
	}
	public void setHabitationCode(String habitationCode) {
		this.habitationCode = habitationCode;
	}
	
	@Column(name="habitation_name")
	public String getHabitationName() {
		return habitationName;
	}
	public void setHabitationName(String habitationName) {
		this.habitationName = habitationName;
	}
	@Column(name="coverage_status")
	public String getCoverageStatus() {
		return coverageStatus;
	}
	public void setCoverageStatus(String coverageStatus) {
		this.coverageStatus = coverageStatus;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public int hashCode() {
		System.out.println("=========================================================================");
		final int prime = 31;
		int result = 1;
		result = prime * result + ((constituencyCode == null) ? 0 : constituencyCode.hashCode());
		result = prime * result + ((coverageStatus == null) ? 0 : coverageStatus.hashCode());
		result = prime * result + ((districtCode == null) ? 0 : districtCode.hashCode());
		result = prime * result + ((habitationCode == null) ? 0 : habitationCode.hashCode());
		result = prime * result + ((mandalCode == null) ? 0 : mandalCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TressedHabitation other = (TressedHabitation) obj;
		if (constituencyCode == null) {
			if (other.constituencyCode != null)
				return false;
		} else if (!constituencyCode.equals(other.constituencyCode))
			return false;
		if (coverageStatus == null) {
			if (other.coverageStatus != null)
				return false;
		} else if (!coverageStatus.equals(other.coverageStatus))
			return false;
		if (districtCode == null) {
			if (other.districtCode != null)
				return false;
		} else if (!districtCode.equals(other.districtCode))
			return false;
		if (habitationCode == null) {
			if (other.habitationCode != null)
				return false;
		} else if (!habitationCode.equals(other.habitationCode))
			return false;
		if (mandalCode == null) {
			if (other.mandalCode != null)
				return false;
		} else if (!mandalCode.equals(other.mandalCode))
			return false;
		return true;
	}
	
}
