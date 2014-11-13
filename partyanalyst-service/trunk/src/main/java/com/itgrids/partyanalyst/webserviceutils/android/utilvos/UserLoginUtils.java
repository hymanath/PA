/**
 * 
 */
package com.itgrids.partyanalyst.webserviceutils.android.utilvos;

/**
 * @author Administrator Jul 10, 2014
 *
 */
/**
 * @author Administrator Jul 10, 2014
 *
 */
public class UserLoginUtils  extends UserLoginVO{

	
	private String boothId;
	private String partNo;
	private Long userId;
	private String type;
	private String imei1;
	private String imei2;
	private String version;
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBoothId() {
		return boothId;
	}
	public void setBoothId(String boothId) {
		this.boothId = boothId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getImei1() {
		return imei1;
	}
	public void setImei1(String imei1) {
		this.imei1 = imei1;
	}
	public String getImei2() {
		return imei2;
	}
	public void setImei2(String imei2) {
		this.imei2 = imei2;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
}
