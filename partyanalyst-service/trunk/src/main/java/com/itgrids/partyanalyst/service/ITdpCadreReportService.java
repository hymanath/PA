package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
import com.itgrids.partyanalyst.dto.TdpCadreLocationWiseReportVO;
import com.itgrids.partyanalyst.dto.ZebraPrintDetailsVO;

public interface ITdpCadreReportService {
	public List<TdpCadreLocationWiseReportVO> getLocationWiseReportDetailsForExcel(List<Long> constituencyIds);
	public TdpCadreLocationWiseReportVO generateExcelReportForTdpCadre(List<Long> constituencyIds);
	public ZebraPrintDetailsVO getMemberShipCardPrintDetails(String searchType,Long stateTypeId,List<Long> selectedLocationIds,String fromDateStr,String toDateStr);
	public SurveyTransactionVO getConstituencyDetailsInDistricts(List<Long> districtIdList);
	public String updatePrintingStatusInTdpCadreTable();
	public ZebraPrintDetailsVO createDashBoardForPrintingCardsDetails(String accessType,String accessValue,Long stateTypeId,String searchType, Long selectedLocationId);
	public ZebraPrintDetailsVO dashBoardForPrintingCardsDetails(String accessType,String accessValue,Long stateTypeId);
	
	public ZebraPrintDetailsVO getCadreDetailsByStatus(Long constituencyId,String status);
	
	public List<CadreRegistrationVO> getCadreDetailsInTeluguByMembershipId(String membershipId);
	
}
