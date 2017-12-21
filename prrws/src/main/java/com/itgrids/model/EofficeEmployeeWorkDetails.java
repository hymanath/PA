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
@Table(name = "eoffice_employee_work_details")
public class EofficeEmployeeWorkDetails extends BaseModel implements Serializable{

	private Long eofficeEmployeeWorkDetailsId;
	private Long departmentId;
	private String departmentName;
	private String orgName;
	private String employeeName;
	private String postName;
	private String postDetailsActive;
	private Long opBalanceCount;
	private Long fileCreated;
	private Long fileReceived;
	private Long totalFiles;
	private Long filesClosed;
	private Long filesForwarded;
	private Long filesParked;
	private Long fileAction;
	private Long firstCount;
	private Long secondCount;
	private Long thirdCount;
	private Long fourthCount;
	private Long fifthCount;
	private Long totalCount;
	private Date fromDate;
	private Date toDate;
	private Long noOfDays;
	private String isDeleted;
	private Date insertedTime;
	
	
	@Id
	@Column(name="eoffice_employee_work_details_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getEofficeEmployeeWorkDetailsId() {
		return eofficeEmployeeWorkDetailsId;
	}
	public void setEofficeEmployeeWorkDetailsId(Long eofficeEmployeeWorkDetailsId) {
		this.eofficeEmployeeWorkDetailsId = eofficeEmployeeWorkDetailsId;
	}
	
	@Column(name="department_id")
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	@Column(name="department_name")
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@Column(name="org_name")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	@Column(name="employee_name")
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	@Column(name="post_name")
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	@Column(name="post_details_active")
	public String getPostDetailsActive() {
		return postDetailsActive;
	}
	public void setPostDetailsActive(String postDetailsActive) {
		this.postDetailsActive = postDetailsActive;
	}
	
	@Column(name="op_balance_count")
	public Long getOpBalanceCount() {
		return opBalanceCount;
	}
	public void setOpBalanceCount(Long opBalanceCount) {
		this.opBalanceCount = opBalanceCount;
	}
	
	@Column(name="file_created")
	public Long getFileCreated() {
		return fileCreated;
	}
	public void setFileCreated(Long fileCreated) {
		this.fileCreated = fileCreated;
	}
	
	@Column(name="file_received")
	public Long getFileReceived() {
		return fileReceived;
	}
	public void setFileReceived(Long fileReceived) {
		this.fileReceived = fileReceived;
	}
	
	@Column(name="total_files")
	public Long getTotalFiles() {
		return totalFiles;
	}
	public void setTotalFiles(Long totalFiles) {
		this.totalFiles = totalFiles;
	}
	
	@Column(name="files_closed")
	public Long getFilesClosed() {
		return filesClosed;
	}
	public void setFilesClosed(Long filesClosed) {
		this.filesClosed = filesClosed;
	}
	
	@Column(name="files_forwarded")
	public Long getFilesForwarded() {
		return filesForwarded;
	}
	public void setFilesForwarded(Long filesForwarded) {
		this.filesForwarded = filesForwarded;
	}
	
	@Column(name="files_parked")
	public Long getFilesParked() {
		return filesParked;
	}
	public void setFilesParked(Long filesParked) {
		this.filesParked = filesParked;
	}
	
	@Column(name="file_action")
	public Long getFileAction() {
		return fileAction;
	}
	public void setFileAction(Long fileAction) {
		this.fileAction = fileAction;
	}
	
	@Column(name="first_count")
	public Long getFirstCount() {
		return firstCount;
	}
	public void setFirstCount(Long firstCount) {
		this.firstCount = firstCount;
	}
	
	@Column(name="second_count")
	public Long getSecondCount() {
		return secondCount;
	}
	public void setSecondCount(Long secondCount) {
		this.secondCount = secondCount;
	}
	
	@Column(name="third_count")
	public Long getThirdCount() {
		return thirdCount;
	}
	public void setThirdCount(Long thirdCount) {
		this.thirdCount = thirdCount;
	}
	
	@Column(name="fourth_count")
	public Long getFourthCount() {
		return fourthCount;
	}
	public void setFourthCount(Long fourthCount) {
		this.fourthCount = fourthCount;
	}
	
	@Column(name="fifth_count")
	public Long getFifthCount() {
		return fifthCount;
	}
	public void setFifthCount(Long fifthCount) {
		this.fifthCount = fifthCount;
	}
	
	@Column(name="total_count")
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	@Column(name="from_date")
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	@Column(name="to_date")
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	@Column(name="no_of_days")
	public Long getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(Long noOfDays) {
		this.noOfDays = noOfDays;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
}
