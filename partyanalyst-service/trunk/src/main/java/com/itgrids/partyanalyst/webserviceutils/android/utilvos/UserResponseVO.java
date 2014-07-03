/**
 * 
 */
package com.itgrids.partyanalyst.webserviceutils.android.utilvos;

/**
 * @author Anilkumar Jul 2, 2014
 *
 */
public class UserResponseVO {
	
	private String   userId;
	private String 	constituencyId;
	private String 	userTypeId;
	private String status="success";
	public UserResponseVO() {}
	public UserResponseVO(String userId, String constituencyId,
			String userTypeId) {
		super();
		this.userId = userId;
		this.constituencyId = constituencyId;
		this.userTypeId = userTypeId;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}
	
	
		
		
		

}
