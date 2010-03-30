package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
/*import java.util.ArrayList;*/
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author Narender Akula
 *
 */
//2
public class PartyResultInfoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2071095192981333534L;
	// key consists of required party info and value consists of list of opposition party info.
	/*private Map<PartyInfoVO, List<PartyInfoVO>> partyData = new LinkedHashMap<PartyInfoVO, List<PartyInfoVO>>();

	public Map<PartyInfoVO, List<PartyInfoVO>> getPartyData() {
		return partyData;
	}

	public void setPartyData(Map<PartyInfoVO, List<PartyInfoVO>> partyData) {
		this.partyData = partyData;
	}*/

	private PartyInfoVO partyInfoVO;
	private List<PartyInfoVO> alliancePartysInfo;
	private List<PartyInfoVO> oppositionPartyInfo;
	
	
	public PartyInfoVO getPartyInfoVO() {
		return partyInfoVO;
	}
	public void setPartyInfoVO(PartyInfoVO partyInfoVO) {
		this.partyInfoVO = partyInfoVO;
	}
	public List<PartyInfoVO> getOppositionPartyInfo() {
		return oppositionPartyInfo;
	}
	public void setOppositionPartyInfo(List<PartyInfoVO> oppositionPartyInfo) {
		this.oppositionPartyInfo = oppositionPartyInfo;
	}
	public List<PartyInfoVO> getAlliancePartysInfo() {
		return alliancePartysInfo;
	}
	public void setAlliancePartysInfo(List<PartyInfoVO> alliancePartysInfo) {
		this.alliancePartysInfo = alliancePartysInfo;
	}
}
