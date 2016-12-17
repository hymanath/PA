package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreCountsGenderVO;
import com.itgrids.partyanalyst.dto.CadreCountsVO;
import com.itgrids.partyanalyst.dto.CardPrintValidationUserVO;
import com.itgrids.partyanalyst.dto.CardPrintValidationVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.ImageCadreVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TdpCadrePrintDetailsVO;

public interface ICadreRegistrationServiceNew {
	
	public ResultStatus pushTotalTodayTdpCadreDataToIntermediate();
	public ResultStatus pushTotalTodayTdpCadreDataToIntermediateByLowLevel();
	public ResultStatus pushTdpCadreDataToIntermediateDateWise();
	public void saveCadreImage(ImageCadreVO inputVO);
	public ResultStatus pushTabUserInfoIntoIntermediateTable();
	public ResultStatus pushTdpCadreDataHourWiseForTabUsersByToday();
	public ResultStatus pushHourWiseTdpCadreDetailsByToday();
	public ResultStatus pushTdpCadreDataHourWiseForTabUsersByOverall();
	public ResultStatus pushHourWiseTdpCadreDetailsByOverall();
	
	public ResultStatus pushDataSourceWisetdpCadreCounts();
	public ResultStatus pushDataSourceWisetdpCadreCountsByState();
	
	public CardPrintValidationUserVO validateCardPrintUserLogin(String username,String password);
	public TdpCadrePrintDetailsVO  getTdpCadrePrintDetailsByMemberShipId(String memberShipId);
	public ResultStatus updateCardPrintValidStatus(final CardPrintValidationVO inputVO);
	
	public ResultStatus pushCadreCountsLocationWiseByGender();
	public ResultStatus pushCadreCountsLocationWiseByCasteState();
	public ResultStatus pushCadreCountsLocationWiseByAge();
	
	public CadreCountsVO ageWiseTdpCadreSummaryReport(Long stateId);
	public List<CadreCountsVO> getLocationWisegeWiseTdpCadreCounts(Long stateId , Long districtId , String searchType);
	
	public CadreCountsVO casteCategoryWiseTdpCadreSummaryReport(Long stateId);
	public List<CadreCountsVO> stateWiseTdpCadreCasteCounts(Long stateId,Double limit);
	public List<CadreCountsVO> districtWiseTdpCadreCasteCounts(Long stateId , Long districtId,Double limit);
	public List<CadreCountsVO> constituencyWiseTdpCadreCasteCounts(Long stateId , Long districtId,Double limit);
	
	public CadreCountsGenderVO stateWiseCadreGenderCounts(Long stateId);
	public List<CadreCountsGenderVO> locationWiseCadreGenderCounts(Long stateId,Long districtId,String searchType);
	public IdAndNameVO getLocationInfoByUserId(Long userId);  
	
	public CadreCountsVO priviledgedCasteCategoryWiseTdpCadreSummaryReport(List<Long> locationIdList, String accessType);
	public List<CadreCountsVO> privilegedStateWiseTdpCadreCasteCounts(List<Long> locationIdList, String accessType,Double limit);
	public List<CadreCountsVO> privilegedDistrictWiseTdpCadreCasteCounts(List<Long> locationIdList, Double limit,String accessType);
	public List<CadreCountsVO> privilegedConstituencyWiseTdpCadreCasteCounts(List<Long> locationIdList, Double limit,String accessType);
	
	public CadreCountsVO privilegedAgeWiseTdpCadreSummaryReport(List<Long> locationIdList, String accessType);
	public List<CadreCountsVO> privilegedLocationWisegeWiseTdpCadreCounts(List<Long> locationIdList , String searchType);
	
	public CadreCountsGenderVO privilegedStateWiseCadreGenderCounts(List<Long> locationIdList, String accessType);
	public List<CadreCountsGenderVO> privilegedLocationWiseCadreGenderCounts(List<Long> locationIdList, String searchType);
	public List<CadreCountsVO> getBellowLevelLocationWiseAgeReport(Long constituecnyId, String searchType);
	
}
