package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
import com.itgrids.partyanalyst.dto.TdpCadreLocationWiseReportVO;
import com.itgrids.partyanalyst.dto.TdpCadreVolunteerVO;
import com.itgrids.partyanalyst.dto.ZebraPrintDetailsVO;

public interface ITdpCadreReportService {
	public List<TdpCadreLocationWiseReportVO> getLocationWiseReportDetailsForExcel(List<Long> constituencyIds);
	public TdpCadreLocationWiseReportVO generateExcelReportForTdpCadre(List<Long> constituencyIds);
	public ZebraPrintDetailsVO getMemberShipCardPrintDetails(String searchType,Long stateTypeId,List<Long> selectedLocationIds,String fromDateStr,String toDateStr);
	public SurveyTransactionVO getConstituencyDetailsInDistricts(List<Long> districtIdList);
	public String updatePrintingStatusInTdpCadreTable();
	//public ZebraPrintDetailsVO createDashBoardForPrintingCardsDetails(String accessType,String accessValue,Long stateTypeId,String searchType, Long selectedLocationId);
	public ZebraPrintDetailsVO dashBoardForPrintingCardsDetails(String accessType,String accessValue,Long stateTypeId);
	
	public ZebraPrintDetailsVO getCadreDetailsByStatus(Long constituencyId,String status);
	
	public List<CadreRegistrationVO> getCadreDetailsInTeluguByMembershipId(String membershipId);
	public List<ZebraPrintDetailsVO> getDayWiseCardPrintedCountInfo(String type,String status,Long Id,Long stateId);
	public List<ZebraPrintDetailsVO> getDayWiseCardPrintedCountInfoForParlment(String status,Long Id,Long stateId);
	//public List<String> getJobCodesByLocationWise(String type,Long Id);
	public List<ZebraPrintDetailsVO> getJobCodesByLocationWise(String type,Long Id);
	public ZebraPrintDetailsVO createDashBoardForPrintingCardsDetails(String accessType,String accessValue,Long stateTypeId,String searchType, Long selectedLocationId,String statusType);
	public List<CadreRegistrationVO> getMembershipCardDetailsForCallCenter(String mobileNo,String trNumber,String membership);
	public ResultStatus saveCallCenterFeedbackForCardStatus(TdpCadreLocationWiseReportVO vo);
	public List<BasicVO> getfeedbackDetails();
	public ResultStatus insertTdpCadreSmsStatusFromExcel(String filePath);
	public ResultStatus saveCadreRegistration(final TdpCadreVolunteerVO inputVO);
	public List<GenericVO> getGHMCConstituencies();
	
	public TdpCadreVolunteerVO getConstituencyWiseVolunteerInfo(Long constituencyId, String searchType);
	
	public ResultStatus assignConstiteuncyForValeenteer(Long consituencyId, Long valeenteerId);
	
	public Boolean isExistUSerByMobileAndEmail(String mobileNo, String emailId);
}
