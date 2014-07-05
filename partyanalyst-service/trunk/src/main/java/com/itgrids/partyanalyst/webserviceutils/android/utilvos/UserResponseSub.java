/**
 * 
 */
package com.itgrids.partyanalyst.webserviceutils.android.utilvos;

import java.util.List;

/**
 * @author Administrator Jul 2, 2014
 *	verifiersData:[{voterId:"",boothId:"",partNo:",iscadre:"","isInfluencePerson":"","localarea":"",casteId:"",hamletId:"",wardId"",dataTypeId:""

 */
public class UserResponseSub  extends UserResponseVO{
	private List<String> partNos;
	private List<String> boothIds;
	private List<Long> voterIds;
	private List<Long> remainingDataBoothIds;
	
	
	public List<Long> getRemainingDataBoothIds() {
		return remainingDataBoothIds;
	}
	public void setRemainingDataBoothIds(List<Long> remainingDataBoothIds) {
		this.remainingDataBoothIds = remainingDataBoothIds;
	}
	public UserResponseSub() {
		super();
		
	}
	public UserResponseSub(String userId, String constituencyId,
			String userTypeId) {
		super(userId, constituencyId, userTypeId);
		
	}
	
	public UserResponseSub(List<String> partNos, List<String> boothIds) {
		super();
		this.partNos = partNos;
		this.boothIds = boothIds;
	}
	
	

	
	public List<String> getPartNos() {
		return partNos;
	}
	
	
	public void setPartNos(List<String> partNos) {
		this.partNos = partNos;
	}
	public List<String> getBoothIds() {
		return boothIds;
	}
	public void setBoothIds(List<String> boothIds) {
		this.boothIds = boothIds;
	}
	
	public List<Long> getVoterIds() {
		return voterIds;
	}
	public void setVoterIds(List<Long> voterIds) {
		this.voterIds = voterIds;
	}
	
   
}
