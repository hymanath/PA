package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.CardPrintValidationUserVO;
import com.itgrids.partyanalyst.dto.ImageCadreVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

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
	
	public CardPrintValidationUserVO validateCardPrintUserLogin(String username,String password);
	
}
