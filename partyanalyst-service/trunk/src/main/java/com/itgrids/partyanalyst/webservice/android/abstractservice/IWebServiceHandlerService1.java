package com.itgrids.partyanalyst.webservice.android.abstractservice;

import java.util.List;

import com.itgrids.partyanalyst.dto.AffiliatedMemberVO;
import com.itgrids.partyanalyst.dto.AppDbDataVO;
import com.itgrids.partyanalyst.dto.CadreImageVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.LoginResponceVO;
import com.itgrids.partyanalyst.dto.MissedCallCampaignVO;
import com.itgrids.partyanalyst.dto.NewCadreRegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SinkVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.dto.TabRecordsStatusVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VoterWebServiceDataVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLocationTrackingVo;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLoginUtils;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLoginVO;

public interface IWebServiceHandlerService1 {
	
	public Object checkForUserAuthentication(UserLoginVO inputvo);
	public Object saveSurveyFieldUsers(SurveyResponceVO inputResponse);
	public ResultStatus saveUserTrackingLocation(UserLocationTrackingVo userLocationTrackingVo);

	public LoginResponceVO checkForUserAuthenticationForCadre(UserLoginVO inputvo);
	public Object saveSurveyFieldUsersForCadre(List<CadreRegistrationVO> inputResponse);
	
	public Object getVCadreDataByPanchayatId(Long panchayatId);
	
	public LoginResponceVO databaseCheckForCadreUser(UserLoginVO inputvo);

/*	public ResultStatus checkUserAuthenticationAndUpdateAuthorisedTime(String userId,String macAdressId);
*/	
	public AppDbDataVO getAllVersionsOfAnApp(String appName,Double currentVerson,boolean includeTest);
	
	public AppDbDataVO getAllUpdatesByVersion(String appName,Double version);
	
	public void savingUserDetailsWhoLoggedIn(final UserLoginUtils inputs,final LoginResponceVO out);
	
	public Object saveSurveyFieldUsersForCadreOnline(List<CadreRegistrationVO> inputResponseList);
	
	public LoginResponceVO checkValidLoginOrNot(String userName,String password,String imei1,String imei2,String version);	
	
	public String getTabUsersLoginDetails(TabRecordsStatusVO inputVo);
	
	public String getTabUsersRecordsDetails(List<TabRecordsStatusVO> inputVo);
	
	public List<VoterWebServiceDataVO> voterSearchDetails(Long constituencyId,String candidateName,String voterCardId,Integer startIndex);
	
	public List<SinkVO> sinkMissingData(List<SinkVO> inputs);
	
	public String getTabUsersLoginKeyDetails(TabRecordsStatusVO inputVo);
	
	public TdpCadreVO searchTdpCadreDetailsBySearchCriteria(String constituencyId,String name,String memberShipCardNo, String voterCardNo, String refNo, String mobileNo);
	
	public String getDynamicValueOfAKey();
	
	public boolean checkHasAccess(Long userId);
	
	public CadreImageVO sinkImageMissingData(CadreImageVO cadreImageVO);
	 public TdpCadreVO searchTdpCadreDetailsBySVoterIdCardNo(String voterCardNo, String isFamilyVoter);
	 public String saveStatus(CadreRegistrationVO inputs);
	 public String saveMissedCallDetails(MissedCallCampaignVO input);
	 
	 public Object saveSurveyFieldUsersForAffliatedCadre(List<CadreRegistrationVO> inputResponseList);
	 public Object getMemberDetailsByMembershipId(String membershipId);
	 
	 public NewCadreRegistrationVO getRegistrationPersonDetails(String voterCardNo,Long familyVoterId,Long tdpCadreId,String status);
	 
	 public List<AffiliatedMemberVO> searchAffiliatedMemberDetails(String searchType,String searchValue,String locationType, Long locationValue);
	 }
