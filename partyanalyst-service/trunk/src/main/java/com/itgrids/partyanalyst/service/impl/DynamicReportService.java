package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.jfree.util.Log;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.BoothRangeVO;
import com.itgrids.partyanalyst.dto.BoothReportVO;
import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.service.IDynamicReportService;
import com.itgrids.partyanalyst.utils.IConstants;

public class DynamicReportService implements IDynamicReportService{

	private static final Logger LOG = Logger.getLogger(DynamicReportService.class);
	
	private IBoothDAO boothDAO;
	private IVoterInfoDAO voterInfoDAO;
	private ITdpCadreDAO tdpCadreDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public ResultStatus createCadreAndVoterExcelReportsForAConstitueny(Long constituencyId,Long publicationDateId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<Object[]> boothsList = boothDAO.getAllBoothsOfAConstituency(constituencyId,publicationDateId);
			BoothReportVO boothReportVO = null;
			for(Object[] params : boothsList)
			{
				try{
					boothReportVO = new BoothReportVO();
					boothReportVO.setBoothId((Long)params[0]);
					boothReportVO.setPartNo(params[1].toString());
					boothReportVO.setLocation(params[2].toString());
					String partNo = boothReportVO.getPartNo();
					
					VoterInfo voterInfo = voterInfoDAO.getVoterInfoOfALocation(constituencyId, publicationDateId,4l,boothReportVO.getBoothId());
					
					boothReportVO.setTotalVoters(voterInfo.getTotalVoters());
					boothReportVO.setNoOfFamilies(voterInfo.getTotalFamilies());
					
					boothReportVO.setCadreWithVoterId(tdpCadreDAO.getVoterIdExistCadreInABooth(constituencyId,publicationDateId,boothReportVO.getPartNo()));
					boothReportVO.setCadreWithFamilyVoterId(tdpCadreDAO.getFamilyVoterIdExistCadreInABooth(constituencyId,publicationDateId,boothReportVO.getPartNo()));
					boothReportVO.setTotalCadre(boothReportVO.getCadreWithVoterId()+boothReportVO.getCadreWithFamilyVoterId());
					
					Set<Long> houseVoters = new HashSet<Long>(0);
					Set<String> houses = new HashSet<String>(0);
					
					List<String> houseNosList1 = tdpCadreDAO.getVoterIdExistCadreFamiliesInABooth(constituencyId,publicationDateId,boothReportVO.getPartNo());
					if(houseNosList1 != null && houseNosList1.size() > 0)
					{
						boothReportVO.setCadreAvailableFamilies(houseNosList1.size());
						houses.addAll(houseNosList1);
						List<Long> houseNosList1Voters = boothPublicationVoterDAO.getVoterIdsInABoothOfGivenHouseNos(constituencyId, publicationDateId, partNo, houseNosList1);
						boothReportVO.setCadreFamilyVoters(houseNosList1Voters.size());
						houseVoters.addAll(houseNosList1Voters);
					}
					else
					{
						boothReportVO.setCadreAvailableFamilies(0);
						boothReportVO.setCadreFamilyVoters(0);
					}
					
					List<String> houseNosList2 = tdpCadreDAO.getFamilyVoterIdExistCadreFamiliesInABooth(constituencyId,publicationDateId,boothReportVO.getPartNo());
					if(houseNosList2 != null && houseNosList2.size() > 0)
					{
						houses.addAll(houseNosList2);
						boothReportVO.setCadreAvailableRefFamilies(houses.size()-boothReportVO.getCadreAvailableFamilies());
						List<Long> houseNosList1Voters = boothPublicationVoterDAO.getVoterIdsInABoothOfGivenHouseNos(constituencyId, publicationDateId, partNo, houseNosList2);
						houseVoters.addAll(houseNosList1Voters);
						boothReportVO.setCadreFamilyRefVoters(houseVoters.size()-boothReportVO.getCadreFamilyVoters());
					}
					else
					{
						boothReportVO.setCadreAvailableRefFamilies(0);
						boothReportVO.setCadreFamilyRefVoters(0);
					}
					
					boothReportVO.setCadreAvailableFamiliesPer(new BigDecimal((double)boothReportVO.getCadreAvailableFamilies()*100/boothReportVO.getNoOfFamilies()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					boothReportVO.setCadreFamilyVotersPer(new BigDecimal((double)boothReportVO.getCadreFamilyVoters()*100/boothReportVO.getTotalVoters()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					boothReportVO.setCadreAvailableRefFamiliesPer(new BigDecimal((double)boothReportVO.getCadreAvailableRefFamilies()*100/boothReportVO.getNoOfFamilies()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					boothReportVO.setCadreFamilyRefVotersPer(new BigDecimal((double)boothReportVO.getCadreFamilyRefVoters()*100/boothReportVO.getTotalVoters()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					
					boothReportVO.setFamilyRangeList(getBoothFamilywiseStatistics(constituencyId,publicationDateId,partNo));
					HSSFWorkbook workbook = new HSSFWorkbook();
					
					List<CadreVo> vdList = new ArrayList<CadreVo>(0);
					List<Object[]> cvList = tdpCadreDAO.getCadreBasicDetailsInABooth(constituencyId,publicationDateId,partNo);
					List<Object[]> cfvList = tdpCadreDAO.getFamilyCadreBasicDetailsInABooth(constituencyId,publicationDateId,partNo);
					
					List<String> cvIdsList = new ArrayList<String>(0);
					List<String> cfvIdsList = new ArrayList<String>(0);
					
					for(Object[] par : cvList)
						cvIdsList.add(par[0].toString());
					
					for(Object[] par : cfvList)
						cfvIdsList.add(par[0].toString());
					
					List<Object[]> voterData = tdpCadreDAO.getVoterAndCadreDetailsInABooth(constituencyId,publicationDateId,partNo);
					
					if(voterData != null && voterData.size() > 0)
					{
						CadreVo cadreVo = null;
						for(Object[] objArr : voterData)
						{
							try{
							cadreVo = new CadreVo();
							cadreVo.setVoterCardId(objArr[0] != null ? objArr[0].toString() : "");
							cadreVo.setFirstName(objArr[1] != null ? objArr[1].toString() : "");
							cadreVo.setAge(objArr[2] != null ? Long.parseLong(objArr[2].toString()) : 0l);
							cadreVo.setGender(objArr[3] != null ? objArr[3].toString() : "");
							cadreVo.setStreet(objArr[4] != null ? objArr[4].toString() : "");
							cadreVo.setHno(objArr[5] != null ? objArr[5].toString() : "");
							cadreVo.setFatherName(objArr[6] != null ? objArr[6].toString() : "");
							cadreVo.setRelationType(objArr[7] != null ? objArr[7].toString() : "");
							cadreVo.setBoothName(objArr[8] != null ? objArr[8].toString() : "");
							
							if(cvIdsList.contains(objArr[0].toString()))
							{
								for(Object[] par : cvList)
								if(objArr[0].toString().equalsIgnoreCase(par[0].toString()))
								{
									cadreVo.setBloodGroup("Y");
									cadreVo.setLastName(par[1] != null ? par[1].toString() : "");
									cadreVo.setMobileNo(par[2] != null ? par[2].toString() : "");
									cadreVo.setCasteCategoryName(par[3] != null ? par[3].toString() : "");
								}
							}
							else if(cfvIdsList.contains(objArr[0].toString()))
							{
								for(Object[] par : cfvList)
								if(objArr[0].toString().equalsIgnoreCase(par[0].toString()))
								{
									cadreVo.setBloodGroup("Voters ID Registed as Family Voter Id");
									cadreVo.setLastName(par[1] != null ? par[1].toString() : "");
									cadreVo.setMobileNo(par[2] != null ? par[2].toString() : "");
									cadreVo.setCasteCategoryName(par[3] != null ? par[3].toString() : "");
								}
							}
							else
							{
								cadreVo.setBloodGroup("");
								cadreVo.setLastName("");
								cadreVo.setMobileNo("");
								cadreVo.setCasteCategoryName("");
							}
							
							vdList.add(cadreVo);
							}catch(Exception e){Log.error(e);e.printStackTrace();}
						}
					}
					
					List<CadreVo> cdList = new ArrayList<CadreVo>(0);
					List<Object[]> cadreData = new ArrayList<Object[]>(0);
					List<Object[]> cList1 = tdpCadreDAO.getCadreDetailsInABooth(constituencyId,publicationDateId,partNo);
					List<Object[]> cList2 = tdpCadreDAO.getFamilyCadreDetailsInABooth(constituencyId,publicationDateId,partNo);
					cadreData.addAll(cList1);
					cadreData.addAll(cList2);
					
					CadreVo cadreVo = null;
					for(Object[] objArr : cadreData)
					{
						try{
							cadreVo = new CadreVo();
							cadreVo.setBoothName(objArr[1] != null ? objArr[1].toString() : "");
							cadreVo.setAddress(objArr[2] != null ? objArr[2].toString() : "");
							cadreVo.setVoterCardId(objArr[3].toString());
							String cadreType = (objArr[0] == null ? "VOTER" : "FAMILY VOTER");
							cadreVo.setImage(cadreType);
							cadreVo.setFirstName(objArr[4] != null ? objArr[4].toString() : "");
							cadreVo.setAge(objArr[5] != null ? Long.valueOf(objArr[5].toString()) : 0l);
							cadreVo.setGender(objArr[6] != null ? objArr[6].toString() : "M");
							cadreVo.setMobileNo(objArr[7] != null ? objArr[7].toString() : "");
							cadreVo.setFatherName(objArr[8] != null ? objArr[8].toString() : "");
							cadreVo.setHno(objArr[9] != null ? objArr[9].toString() : "");
							cadreVo.setCasteCategoryName(objArr[10] != null ? objArr[10].toString() : "");
							cadreVo.setEducationStr(objArr[11] != null ? objArr[11].toString() : "");
							cadreVo.setOccupation(objArr[12] != null ? objArr[12].toString() : "");
							cadreVo.setEmailId(objArr[13] != null ? objArr[13].toString() : "");
							cdList.add(cadreVo);
						}catch(Exception e)
						{
							LOG.error(e);
							e.printStackTrace();
						}
					}
					
					List<CadreVo> hdList = new ArrayList<CadreVo>(0);
					List<Object[]> houseData = tdpCadreDAO.getVoterHouseWiseDetailsInABooth(constituencyId,publicationDateId,partNo);
					
					for(Object[] objArr : houseData)
					{
						try{
							cadreVo = new CadreVo();
							cadreVo.setHno(objArr[0] != null ? objArr[0].toString() : "");
							cadreVo.setAddress(objArr[1] != null ? objArr[1].toString() : "");
							cadreVo.setCount(((BigInteger)objArr[2]).longValue());
							cadreVo.setAge(((BigInteger)objArr[3]).longValue());
							cadreVo.setFirstName(objArr[4] != null ? objArr[4].toString() : "");
							cadreVo.setMobileNo(objArr[5] != null ? objArr[5].toString() : "");
							cadreVo.setCasteCategoryName(objArr[6] != null ? objArr[6].toString() : "");
							hdList.add(cadreVo);
						}catch(Exception e)
						{
							LOG.error(e);
							e.printStackTrace();
						}
					}
					
					createCadreAndVoterSummerySheet(workbook,boothReportVO);
					createVoterDataSheet(workbook,vdList);
					createCadreDataSheet(workbook,cdList);
					createHouseDataSheet(workbook,hdList);
					
					String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
	    	        FileOutputStream out =
	    	                new FileOutputStream(new File(IConstants.STATIC_CONTENT_FOLDER_URL+"Dynamic_Reports"+pathSeperator+boothReportVO.getPartNo()+".xls"));
	    	        workbook.write(out);
	    	        out.close();
				}catch(Exception e)
				{
					LOG.error(e);
				}
				
			}
			
		}catch(Exception e)
		{
			LOG.error("Exception occured in createCadreAndVoterExcelReportsForAConstitueny() Method",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
		}
		return resultStatus;
	}
	
	public List<BoothRangeVO> getBoothFamilywiseStatistics(Long constituencyId,Long publicationDateId,String partNo)
	{
		List<BoothRangeVO> result = new ArrayList<BoothRangeVO>(0);
		try{
		BoothRangeVO boothRangeVO1 = new BoothRangeVO();
		BoothRangeVO boothRangeVO2 = new BoothRangeVO();
		BoothRangeVO boothRangeVO3 = new BoothRangeVO();
		BoothRangeVO boothRangeVO4 = new BoothRangeVO();
		BoothRangeVO boothRangeVO5 = new BoothRangeVO();
		BoothRangeVO boothRangeVO6 = new BoothRangeVO();
		
		boothRangeVO1.setRange("Below 3");
		boothRangeVO2.setRange("3 - 5");
		boothRangeVO3.setRange("6 - 8");
		boothRangeVO4.setRange("9 - 10");
		boothRangeVO5.setRange("11 - 13");
		boothRangeVO6.setRange("Above 13");
		
		result.add(boothRangeVO1);
		result.add(boothRangeVO2);
		result.add(boothRangeVO3);
		result.add(boothRangeVO4);
		result.add(boothRangeVO5);
		result.add(boothRangeVO6);
		
		List<Object[]> list = boothPublicationVoterDAO.getFamilyWiseVotersInABooth(constituencyId,publicationDateId,partNo);
		List<String> votersHosueNos1 = new ArrayList<String>(0);
		List<String> votersHosueNos2 = new ArrayList<String>(0);
		List<String> votersHosueNos3 = new ArrayList<String>(0);
		List<String> votersHosueNos4 = new ArrayList<String>(0);
		List<String> votersHosueNos5 = new ArrayList<String>(0);
		List<String> votersHosueNos6 = new ArrayList<String>(0);
		
		for(Object[] params : list)
		{
			String houseNo = params[0].toString();
			int voters = (int)Long.parseLong(params[1].toString());
			
			if(voters < 3)
			{
				result.get(0).setTotalVoters(result.get(0).getTotalVoters()+voters);
				votersHosueNos1.add(houseNo);
			}
			else if(voters >= 3 && voters <= 5)
			{
				result.get(1).setTotalVoters(result.get(1).getTotalVoters()+voters);
				votersHosueNos2.add(houseNo);
			}
			else if(voters >= 6 && voters <= 8)
			{
				result.get(2).setTotalVoters(result.get(2).getTotalVoters()+voters);
				votersHosueNos3.add(houseNo);
			}
			else if(voters >= 9 && voters <= 10)
			{
				result.get(3).setTotalVoters(result.get(3).getTotalVoters()+voters);
				votersHosueNos4.add(houseNo);
			}
			else if(voters >= 11 && voters <= 13)
			{
				result.get(4).setTotalVoters(result.get(4).getTotalVoters()+voters);
				votersHosueNos5.add(houseNo);
			}
			else if(voters > 13)
			{
				result.get(5).setTotalVoters(result.get(5).getTotalVoters()+voters);
				votersHosueNos6.add(houseNo);
			}
		}
		
		result.get(0).setNoOfFamilies(votersHosueNos1.size());
		result.get(1).setNoOfFamilies(votersHosueNos2.size());
		result.get(2).setNoOfFamilies(votersHosueNos3.size());
		result.get(3).setNoOfFamilies(votersHosueNos4.size());
		result.get(4).setNoOfFamilies(votersHosueNos5.size());
		result.get(5).setNoOfFamilies(votersHosueNos6.size());
		
		if(votersHosueNos1.size() > 0)
		{
			List<Object[]> list1 = tdpCadreDAO.getCadreAvailableFamiliesInABooth(constituencyId,publicationDateId,partNo,votersHosueNos1);
			
			if(list1 != null && list1.size() > 0)
			{
				List<String> cadreHouses = new ArrayList<String>(0);
				int cadreCount = 0;
				for(Object[] params : list1)
				{
					int ct = (int)(((BigInteger)params[1]).intValue()+ ((BigInteger)params[2]).intValue());
					if(ct > 0)
						cadreHouses.add(params[0].toString());
					cadreCount = cadreCount + ct;
				}
				result.get(0).setCadreAvailableFamilies(cadreHouses.size());
				result.get(0).setCadreCount(cadreCount);
				if(cadreHouses.size() > 0)
					result.get(0).setCadreAvailableFamiliesVoters(boothPublicationVoterDAO.getVoterIdsInABoothOfGivenHouseNos(constituencyId, publicationDateId, partNo, cadreHouses).size());
				else
					result.get(0).setCadreAvailableFamiliesVoters(0);
			}
		}
		
		if(votersHosueNos2.size() > 0)
		{
			List<Object[]> list2 = tdpCadreDAO.getCadreAvailableFamiliesInABooth(constituencyId,publicationDateId,partNo,votersHosueNos2);
			
			if(list2 != null && list2.size() > 0)
			{
				List<String> cadreHouses = new ArrayList<String>(0);
				int cadreCount = 0;
				for(Object[] params : list2)
				{
					int ct = (int)(((BigInteger)params[1]).intValue()+ ((BigInteger)params[2]).intValue());
					if(ct > 0)
						cadreHouses.add(params[0].toString());
					cadreCount = cadreCount + ct;
				}
				result.get(1).setCadreAvailableFamilies(cadreHouses.size());
				result.get(1).setCadreCount(cadreCount);
				if(cadreHouses.size() > 0)
					result.get(1).setCadreAvailableFamiliesVoters(boothPublicationVoterDAO.getVoterIdsInABoothOfGivenHouseNos(constituencyId, publicationDateId, partNo, cadreHouses).size());
				else
					result.get(1).setCadreAvailableFamiliesVoters(0);
			}
		}
		
		if(votersHosueNos3.size() > 0)
		{
			List<Object[]> list3 = tdpCadreDAO.getCadreAvailableFamiliesInABooth(constituencyId,publicationDateId,partNo,votersHosueNos3);
			
			if(list3 != null && list3.size() > 0)
			{
				List<String> cadreHouses = new ArrayList<String>(0);
				int cadreCount = 0;
				for(Object[] params : list3)
				{
					int ct = (int)(((BigInteger)params[1]).intValue()+ ((BigInteger)params[2]).intValue());
					if(ct > 0)
						cadreHouses.add(params[0].toString());
					cadreCount = cadreCount + ct;
				}
				result.get(2).setCadreAvailableFamilies(cadreHouses.size());
				result.get(2).setCadreCount(cadreCount);
				if(cadreHouses.size() > 0)
					result.get(2).setCadreAvailableFamiliesVoters(boothPublicationVoterDAO.getVoterIdsInABoothOfGivenHouseNos(constituencyId, publicationDateId, partNo, cadreHouses).size());
				else
					result.get(2).setCadreAvailableFamiliesVoters(0);
			}
		}
		
		if(votersHosueNos4.size() > 0)
		{
			List<Object[]> list4 = tdpCadreDAO.getCadreAvailableFamiliesInABooth(constituencyId,publicationDateId,partNo,votersHosueNos4);
			
			if(list4 != null && list4.size() > 0)
			{
				List<String> cadreHouses = new ArrayList<String>(0);
				int cadreCount = 0;
				for(Object[] params : list4)
				{
					int ct = (int)(((BigInteger)params[1]).intValue()+ ((BigInteger)params[2]).intValue());
					if(ct > 0)
						cadreHouses.add(params[0].toString());
					cadreCount = cadreCount + ct;
				}
				result.get(3).setCadreAvailableFamilies(cadreHouses.size());
				result.get(3).setCadreCount(cadreCount);
				if(cadreHouses.size() > 0)
					result.get(3).setCadreAvailableFamiliesVoters(boothPublicationVoterDAO.getVoterIdsInABoothOfGivenHouseNos(constituencyId, publicationDateId, partNo, cadreHouses).size());
				else
					result.get(3).setCadreAvailableFamiliesVoters(0);
			}
		}
		
		if(votersHosueNos5.size() > 0)
		{
			List<Object[]> list5 = tdpCadreDAO.getCadreAvailableFamiliesInABooth(constituencyId,publicationDateId,partNo,votersHosueNos5);
			
			if(list5 != null && list5.size() > 0)
			{
				List<String> cadreHouses = new ArrayList<String>(0);
				int cadreCount = 0;
				for(Object[] params : list5)
				{
					int ct = (int)(((BigInteger)params[1]).intValue()+ ((BigInteger)params[2]).intValue());
					if(ct > 0)
						cadreHouses.add(params[0].toString());
					cadreCount = cadreCount + ct;
				}
				result.get(4).setCadreAvailableFamilies(cadreHouses.size());
				result.get(4).setCadreCount(cadreCount);
				if(cadreHouses.size() > 0)
					result.get(4).setCadreAvailableFamiliesVoters(boothPublicationVoterDAO.getVoterIdsInABoothOfGivenHouseNos(constituencyId, publicationDateId, partNo, cadreHouses).size());
				else
					result.get(4).setCadreAvailableFamiliesVoters(0);
			}
		}
		
		if(votersHosueNos6.size() > 0)
		{
			List<Object[]> list6 = tdpCadreDAO.getCadreAvailableFamiliesInABooth(constituencyId,publicationDateId,partNo,votersHosueNos6);
			
			if(list6 != null && list6.size() > 0)
			{
				List<String> cadreHouses = new ArrayList<String>(0);
				int cadreCount = 0;
				for(Object[] params : list6)
				{
					int ct = (int)(((BigInteger)params[1]).intValue()+ ((BigInteger)params[2]).intValue());
					if(ct > 0)
						cadreHouses.add(params[0].toString());
					cadreCount = cadreCount + ct;
				}
				result.get(5).setCadreAvailableFamilies(cadreHouses.size());
				result.get(5).setCadreCount(cadreCount);
				if(cadreHouses.size() > 0)
					result.get(5).setCadreAvailableFamiliesVoters(boothPublicationVoterDAO.getVoterIdsInABoothOfGivenHouseNos(constituencyId, publicationDateId, partNo, cadreHouses).size());
				else
					result.get(5).setCadreAvailableFamiliesVoters(0);
			}
		}
		
		}catch(Exception e)
		{
			LOG.error("Exception occured getBoothFamilywiseStatistics Method, ",e);
		}
		return result;
	}
	
	public void createCadreAndVoterSummerySheet(HSSFWorkbook workbook,BoothReportVO boothReportVO)
	{
		try{
			HSSFSheet sheet = workbook.createSheet("Summery");
			Row header = sheet.createRow(1);
			header.createCell(0).setCellValue("TIRUPATI ASSEMBLY - BOOTH WISE INFORMATION");
			
			Row row1 = sheet.createRow(5);
			row1.createCell(1).setCellValue("BOOTH NO :");
			row1.createCell(2).setCellValue(boothReportVO.getPartNo());
			
			Row row2 = sheet.createRow(6);
			row2.createCell(1).setCellValue("BOOTH LOCATION :");
			row2.createCell(2).setCellValue(boothReportVO.getLocation());
			
			Row row3 = sheet.createRow(9);
			row3.createCell(1).setCellValue("TOTAL VOTERS  :");
			row3.createCell(2).setCellValue(boothReportVO.getTotalVoters());
			row3.createCell(4).setCellValue("2014 CADRE :");
			row3.createCell(5).setCellValue(boothReportVO.getTotalCadre());
			
			Row row4 = sheet.createRow(10);
			row4.createCell(1).setCellValue("NO OF FAMILY  :");
			row4.createCell(2).setCellValue(boothReportVO.getNoOfFamilies());
			
			Row row5 = sheet.createRow(11);
			row5.createCell(1).setCellValue("CADRE AVAILABLE NO OF FAMILY:");
			row5.createCell(2).setCellValue(boothReportVO.getCadreAvailableFamilies());
			row5.createCell(3).setCellValue(boothReportVO.getCadreAvailableFamiliesPer());
			row5.createCell(4).setCellValue("REGI WITH OWN VOTER ID :");
			row5.createCell(5).setCellValue(boothReportVO.getCadreWithVoterId());
			
			Row row6 = sheet.createRow(12);
			row6.createCell(1).setCellValue("CADRE AVAILABLE FAMILY - TOTAL VOTERS :");
			row6.createCell(2).setCellValue(boothReportVO.getCadreFamilyVoters());
			row6.createCell(3).setCellValue(boothReportVO.getCadreFamilyVotersPer());
			row6.createCell(4).setCellValue("FAMILY VOTER ID :");
			row6.createCell(5).setCellValue(boothReportVO.getCadreWithFamilyVoterId());
			
			Row row7 = sheet.createRow(13);
			row7.createCell(1).setCellValue("CADRE AVAILABLE FAMILY - REF FAMILY VOTER ID");
			row7.createCell(2).setCellValue(boothReportVO.getCadreAvailableRefFamilies());
			row7.createCell(3).setCellValue(boothReportVO.getCadreAvailableRefFamiliesPer());
			
			Row row8 = sheet.createRow(14);
			row8.createCell(1).setCellValue("CADRE AVAILABLE FAMILY (REF FAMILY VOTER ID)- TOTAL VOTERS :");
			row8.createCell(2).setCellValue(boothReportVO.getCadreFamilyRefVoters());
			row8.createCell(3).setCellValue(boothReportVO.getCadreFamilyRefVotersPer());
			
			Row row9 = sheet.createRow(16);
			row9.createCell(1).setCellValue("VOTERS RANGES VS NO OF FAMILY :");
			
			Row row10 = sheet.createRow(18);
			row10.createCell(2).setCellValue("Below 3");
			row10.createCell(3).setCellValue(" 3 - 5 ");
			row10.createCell(4).setCellValue(" 6 - 8 ");
			row10.createCell(5).setCellValue(" 8 - 10 ");
			row10.createCell(6).setCellValue(" 10 - 13 ");
			row10.createCell(7).setCellValue(" Above 13 ");
			
			Row row11 = sheet.createRow(19);
			row11.createCell(1).setCellValue("No of Families");
			row11.createCell(2).setCellValue(boothReportVO.getFamilyRangeList().get(0).getNoOfFamilies());
			row11.createCell(3).setCellValue(boothReportVO.getFamilyRangeList().get(1).getNoOfFamilies());
			row11.createCell(4).setCellValue(boothReportVO.getFamilyRangeList().get(2).getNoOfFamilies());
			row11.createCell(5).setCellValue(boothReportVO.getFamilyRangeList().get(3).getNoOfFamilies());
			row11.createCell(6).setCellValue(boothReportVO.getFamilyRangeList().get(4).getNoOfFamilies());
			row11.createCell(7).setCellValue(boothReportVO.getFamilyRangeList().get(5).getNoOfFamilies());
			
			Row row12 = sheet.createRow(20);
			row12.createCell(1).setCellValue("Total Voters");
			row12.createCell(2).setCellValue(boothReportVO.getFamilyRangeList().get(0).getTotalVoters());
			row12.createCell(3).setCellValue(boothReportVO.getFamilyRangeList().get(1).getTotalVoters());
			row12.createCell(4).setCellValue(boothReportVO.getFamilyRangeList().get(2).getTotalVoters());
			row12.createCell(5).setCellValue(boothReportVO.getFamilyRangeList().get(3).getTotalVoters());
			row12.createCell(6).setCellValue(boothReportVO.getFamilyRangeList().get(4).getTotalVoters());
			row12.createCell(7).setCellValue(boothReportVO.getFamilyRangeList().get(5).getTotalVoters());
			
			Row row13 = sheet.createRow(21);
			row13.createCell(1).setCellValue("Cadre Available Families");
			row13.createCell(2).setCellValue(boothReportVO.getFamilyRangeList().get(0).getCadreAvailableFamilies());
			row13.createCell(3).setCellValue(boothReportVO.getFamilyRangeList().get(1).getCadreAvailableFamilies());
			row13.createCell(4).setCellValue(boothReportVO.getFamilyRangeList().get(2).getCadreAvailableFamilies());
			row13.createCell(5).setCellValue(boothReportVO.getFamilyRangeList().get(3).getCadreAvailableFamilies());
			row13.createCell(6).setCellValue(boothReportVO.getFamilyRangeList().get(4).getCadreAvailableFamilies());
			row13.createCell(7).setCellValue(boothReportVO.getFamilyRangeList().get(5).getCadreAvailableFamilies());
					
			Row row14 = sheet.createRow(22);
			row14.createCell(1).setCellValue("Cadre - Count");
			row14.createCell(2).setCellValue(boothReportVO.getFamilyRangeList().get(0).getCadreCount());
			row14.createCell(3).setCellValue(boothReportVO.getFamilyRangeList().get(1).getCadreCount());
			row14.createCell(4).setCellValue(boothReportVO.getFamilyRangeList().get(2).getCadreCount());
			row14.createCell(5).setCellValue(boothReportVO.getFamilyRangeList().get(3).getCadreCount());
			row14.createCell(6).setCellValue(boothReportVO.getFamilyRangeList().get(4).getCadreCount());
			row14.createCell(7).setCellValue(boothReportVO.getFamilyRangeList().get(5).getCadreCount());
			
			Row row15 = sheet.createRow(23);
			row15.createCell(1).setCellValue("Cadre Available Families - Votes Count");
			row15.createCell(2).setCellValue(boothReportVO.getFamilyRangeList().get(0).getCadreAvailableFamiliesVoters());
			row15.createCell(3).setCellValue(boothReportVO.getFamilyRangeList().get(1).getCadreAvailableFamiliesVoters());
			row15.createCell(4).setCellValue(boothReportVO.getFamilyRangeList().get(2).getCadreAvailableFamiliesVoters());
			row15.createCell(5).setCellValue(boothReportVO.getFamilyRangeList().get(3).getCadreAvailableFamiliesVoters());
			row15.createCell(6).setCellValue(boothReportVO.getFamilyRangeList().get(4).getCadreAvailableFamiliesVoters());
			row15.createCell(7).setCellValue(boothReportVO.getFamilyRangeList().get(5).getCadreAvailableFamiliesVoters());
			
		}catch(Exception e)
		{
			LOG.error(e);
		}
	}
	
	public void createVoterDataSheet(HSSFWorkbook workbook,List<CadreVo> list)
	{
		try{
			HSSFSheet sheet = workbook.createSheet("Booth Voters");
			
			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("VOTER ID");
			header.createCell(1).setCellValue("VOTER NAME");
			header.createCell(2).setCellValue("AGE");
			header.createCell(3).setCellValue("GENDER");
			header.createCell(4).setCellValue("AREA COVERED");
			header.createCell(5).setCellValue("HOUSE NO");
			header.createCell(6).setCellValue("RELATIVE NAME");
			header.createCell(7).setCellValue("RELATIONSHIP TYPE");
			header.createCell(8).setCellValue("BOOTH NO");
			header.createCell(9).setCellValue("Is_Cadre");
			header.createCell(10).setCellValue("Cadre_Name");
			header.createCell(11).setCellValue("Cadre_Mobile");
			header.createCell(12).setCellValue("Caste_Name");
			
			int index = 0;
			for(CadreVo cadreVo : list)
			{
				try{
					index++;
					Row row = sheet.createRow(index);
					row.createCell(0).setCellValue(cadreVo.getVoterCardId());
					row.createCell(1).setCellValue(cadreVo.getFirstName());
					row.createCell(2).setCellValue(cadreVo.getAge());
					row.createCell(3).setCellValue(cadreVo.getGender());
					row.createCell(4).setCellValue(cadreVo.getStreet());
					row.createCell(5).setCellValue(cadreVo.getHno());
					row.createCell(6).setCellValue(cadreVo.getFatherName());
					row.createCell(7).setCellValue(cadreVo.getRelationType());
					row.createCell(8).setCellValue(cadreVo.getBoothName());
					row.createCell(9).setCellValue(cadreVo.getBloodGroup());
					row.createCell(10).setCellValue(cadreVo.getLastName());
					row.createCell(11).setCellValue(cadreVo.getMobileNo());
					row.createCell(12).setCellValue(cadreVo.getCasteCategoryName());
					
				}catch(Exception e)
				{
					LOG.error(e);
				}
			}
				
		}catch(Exception e)
		{
			LOG.error("Exception occured in createVoterDataSheet() Method - ",e);
		}
	}
	
	public void createCadreDataSheet(HSSFWorkbook workbook,List<CadreVo> list)
	{
		try{
			HSSFSheet sheet = workbook.createSheet("2014 Cadre - Full Details");
			
			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("PART NO");
			header.createCell(1).setCellValue("BOOTH LOCATION");
			header.createCell(2).setCellValue("VOTER ID CARD NO");
			header.createCell(3).setCellValue("VOTER TYPE");
			header.createCell(4).setCellValue("FIRST_NAME");
			header.createCell(5).setCellValue("AGE");
			header.createCell(6).setCellValue("GENDER");
			header.createCell(7).setCellValue("MOBILE_NO");
			header.createCell(8).setCellValue("RELATIVE_NAME");
			header.createCell(9).setCellValue("HOUSE_NO");
			header.createCell(10).setCellValue("CASTE NAME");
			header.createCell(11).setCellValue("EDUCATION");
			header.createCell(12).setCellValue("OCCUPATION");
			header.createCell(13).setCellValue("CADRE_AADHER_NO");
			
			int index = 0;
			for(CadreVo cadreVo : list)
			{
				try{
					index++;
					Row row = sheet.createRow(index);
					row.createCell(0).setCellValue(cadreVo.getBoothName());
					row.createCell(1).setCellValue(cadreVo.getAddress());
					row.createCell(2).setCellValue(cadreVo.getVoterCardId());
					row.createCell(3).setCellValue(cadreVo.getImage());
					row.createCell(4).setCellValue(cadreVo.getFirstName());
					row.createCell(5).setCellValue(cadreVo.getAge());
					row.createCell(6).setCellValue(cadreVo.getGender());
					row.createCell(7).setCellValue(cadreVo.getMobileNo());
					row.createCell(8).setCellValue(cadreVo.getFatherName());
					row.createCell(9).setCellValue(cadreVo.getHno());
					row.createCell(10).setCellValue(cadreVo.getCasteCategoryName());
					row.createCell(11).setCellValue(cadreVo.getEducationStr());
					row.createCell(12).setCellValue(cadreVo.getOccupation());
					row.createCell(13).setCellValue(cadreVo.getEmailId());
					
				}catch(Exception e)
				{
					LOG.error(e);
				}
			}
			
		}catch(Exception e)
		{
			LOG.error("Exception occured in createVoterDataSheet() Method - ",e);
		}
	}
	
	public void createHouseDataSheet(HSSFWorkbook workbook,List<CadreVo> list)
	{
		try{
			HSSFSheet sheet = workbook.createSheet("House Wise");
			
			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("HOUSE NO");
			header.createCell(1).setCellValue("AREA COVERED");
			header.createCell(2).setCellValue("No of Total Voters");
			header.createCell(3).setCellValue("No Cadre Reg Voters");
			header.createCell(4).setCellValue("Name");
			header.createCell(5).setCellValue("Cadre Mobile no");
			header.createCell(6).setCellValue("Cadre Caste");
			
			int index = 0;
			for(CadreVo cadreVo : list)
			{
				try{
					index++;
					Row row = sheet.createRow(index);
					row.createCell(0).setCellValue(cadreVo.getHno());
					row.createCell(1).setCellValue(cadreVo.getAddress());
					row.createCell(2).setCellValue(cadreVo.getCount());
					row.createCell(3).setCellValue(cadreVo.getAge());
					row.createCell(4).setCellValue(cadreVo.getFirstName());
					row.createCell(5).setCellValue(cadreVo.getMobileNo());
					row.createCell(6).setCellValue(cadreVo.getCasteCategoryName());
					
				}catch(Exception e)
				{
					LOG.error(e);
				}
			}
		}catch(Exception e)
		{
			LOG.error("Exception occured in createVoterDataSheet() Method - ",e);
		}
	}
}
