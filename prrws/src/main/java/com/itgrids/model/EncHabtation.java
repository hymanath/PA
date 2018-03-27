package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="enc_habitation")
public class EncHabtation {
	
	private  Long encHabitationId;
	private Long districtCode;
	private String districtNmae;
	private Long mandalCode;
	private String mandalName;
	private Long assemblyCode;
	private String assemblyName;
	private Long districtCode2011;
	private String mCode2011;
	private Long vCode2011;
	private Long vName2011;
	private String panchayatCode;
	private String panchayatName;
	private String habCode;
	private String habName;

	@Id
	@Column(name="enc_habitation_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getEncHabitationId() {
		return encHabitationId;
	}
	public void setEncHabitationId(Long encHabitationId) {
		this.encHabitationId = encHabitationId;
	}
	@Column(name="dist_code")
	public Long getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(Long districtCode) {
		this.districtCode = districtCode;
	}
	@Column(name="dist_name")
	public String getDistrictNmae() {
		return districtNmae;
	}
	public void setDistrictNmae(String districtNmae) {
		this.districtNmae = districtNmae;
	}
	@Column(name="mand_code")
	public Long getMandalCode() {
		return mandalCode;
	}
	public void setMandalCode(Long mandalCode) {
		this.mandalCode = mandalCode;
	}
	@Column(name="mand_name")
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	@Column(name="ac_code")
	public Long getAssemblyCode() {
		return assemblyCode;
	}
	public void setAssemblyCode(Long assemblyCode) {
		this.assemblyCode = assemblyCode;
	}
	@Column(name="ac_name")
	public String getAssemblyName() {
		return assemblyName;
	}
	public void setAssemblyName(String assemblyName) {
		this.assemblyName = assemblyName;
	}
	@Column(name="dcode_2011")
	public Long getDistrictCode2011() {
		return districtCode2011;
	}
	public void setDistrictCode2011(Long districtCode2011) {
		this.districtCode2011 = districtCode2011;
	}
	@Column(name="mcode_2011")
	public String getmCode2011() {
		return mCode2011;
	}
	public void setmCode2011(String mCode2011) {
		this.mCode2011 = mCode2011;
	}
	@Column(name="vcode_2011")
	public Long getvCode2011() {
		return vCode2011;
	}
	public void setvCode2011(Long vCode2011) {
		this.vCode2011 = vCode2011;
	}
	@Column(name="vname_2011")
	public Long getvName2011() {
		return vName2011;
	}
	public void setvName2011(Long vName2011) {
		this.vName2011 = vName2011;
	}
	@Column(name="pcode")
	public String getPanchayatCode() {
		return panchayatCode;
	}
	public void setPanchayatCode(String panchayatCode) {
		this.panchayatCode = panchayatCode;
	}
	@Column(name="pname")
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	@Column(name="han_code")
	public String getHabCode() {
		return habCode;
	}
	public void setHabCode(String habCode) {
		this.habCode = habCode;
	}
	@Column(name="hab_name")
	public String getHabName() {
		return habName;
	}
	public void setHabName(String habName) {
		this.habName = habName;
	}
	
	
}
