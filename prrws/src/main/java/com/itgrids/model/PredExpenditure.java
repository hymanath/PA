package com.itgrids.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pred_expenditure")
public class PredExpenditure implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long predExpenditureId;
	private Long expNo;
	private String paoName;
	private String tddocode;
	private String ddocode;
	private String division;
	private String hoa;
	private String workName;
	private String agencyName;
	private String grossAmount;
	private String deductions;
	private String netAmount;
	private String bankAccno;
	private String ifscCode;
	private String chqNo;
	private Date chqDate;
	
	@Id
	@Column(name="pred_expenditure_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPredExpenditureId() {
		return predExpenditureId;
	}
	public void setPredExpenditureId(Long predExpenditureId) {
		this.predExpenditureId = predExpenditureId;
	}
	@Column(name="exp_no")
	public Long getExpNo() {
		return expNo;
	}
	public void setExpNo(Long expNo) {
		this.expNo = expNo;
	}
	@Column(name="pao_name")
	public String getPaoName() {
		return paoName;
	}
	public void setPaoName(String paoName) {
		this.paoName = paoName;
	}
	@Column(name="tddocode")
	public String getTddocode() {
		return tddocode;
	}
	public void setTddocode(String tddocode) {
		this.tddocode = tddocode;
	}
	@Column(name="ddocode")
	public String getDdocode() {
		return ddocode;
	}
	public void setDdocode(String ddocode) {
		this.ddocode = ddocode;
	}
	@Column(name="division")
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	@Column(name="hoa")
	public String getHoa() {
		return hoa;
	}
	public void setHoa(String hoa) {
		this.hoa = hoa;
	}
	@Column(name="work_name")
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	@Column(name="agency_name")
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	@Column(name="gross_amount")
	public String getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(String grossAmount) {
		this.grossAmount = grossAmount;
	}
	@Column(name="deductions")
	public String getDeductions() {
		return deductions;
	}
	public void setDeductions(String deductions) {
		this.deductions = deductions;
	}
	@Column(name="net_amount")
	public String getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}
	@Column(name="bank_accno")
	public String getBankAccno() {
		return bankAccno;
	}
	public void setBankAccno(String bankAccno) {
		this.bankAccno = bankAccno;
	}
	@Column(name="ifsc_code")
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	@Column(name="chq_no")
	public String getChqNo() {
		return chqNo;
	}
	public void setChqNo(String chqNo) {
		this.chqNo = chqNo;
	}
	@Column(name="chq_date")
	public Date getChqDate() {
		return chqDate;
	}
	public void setChqDate(Date chqDate) {
		this.chqDate = chqDate;
	}
	
	
}
