package com.itgrids.partyanalyst.model;

import java.io.Serializable;

/**
 * @author <a href="sasi.itgrids.hyd@gmail.com">SASIn</a>
 * @since 19th AUG 2014
 */

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "file_department")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
				
public class FileDepartment extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long fileDepartmentId;
	private Long fileId;
	private Long departmentId;
	private File file;
	private Department department;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="file_department_id", unique=true, nullable=false)
	public Long getFileDepartmentId() {
		return fileDepartmentId;
	}
	public void setFileDepartmentId(Long fileDepartmentId) {
		this.fileDepartmentId = fileDepartmentId;
	}
	@Column(name = "file_id")
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	@Column(name = "department_id")
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "file_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	
	
	
	 
}
