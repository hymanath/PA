package com.itgrids.partyanalyst.excel.booth;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;

import com.itgrids.partyanalyst.excel.CsvException;
import com.itgrids.partyanalyst.excel.VoterDataExcelColumnNames;

public class VoterDataExcelReader {

	public List<VoterDataUploadVO> readExcel(File filePath) throws CsvException{
		List<VoterDataUploadVO> voterDataUploadVOs = new ArrayList<VoterDataUploadVO>();
		try{
			Workbook workbook=Workbook.getWorkbook(filePath);	
			Sheet[] sheets = workbook.getSheets();
			VoterDataUploadVO voterDataUploadVO = null;
			for(Sheet sheet:sheets){
				List<BoothResultExcelColumnMapper> voterExcelData = new ArrayList<BoothResultExcelColumnMapper>();
				voterDataUploadVO = new VoterDataUploadVO();
				for(int row = 0; row < sheet.getRows(); row++ ){
					BoothResultExcelColumnMapper boothResultExcelColumnMapper = new BoothResultExcelColumnMapper(sheet, row, sheet.getColumns());
					voterExcelData.add(boothResultExcelColumnMapper);
				}
				voterDataUploadVO.setVoterVOs(readExcelAndBindObject(voterExcelData, voterDataUploadVO));
				voterDataUploadVOs.add(voterDataUploadVO);
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return voterDataUploadVOs;
	}

	private List<VoterVO> readExcelAndBindObject(List<BoothResultExcelColumnMapper> voterExcelSheetData, VoterDataUploadVO voterDataUploadVO) throws Exception{
		List<VoterVO> voterVOs = new ArrayList<VoterVO>();
		for(BoothResultExcelColumnMapper boothResultExcelColumnMapper:voterExcelSheetData){
			VoterVO voterVO = new VoterVO();			
			String cellData = boothResultExcelColumnMapper.getColumn1();
			if(cellData.contains("_") && boothResultExcelColumnMapper.getColumn3().length() == 0 && boothResultExcelColumnMapper.getColumn4().length() == 0){
				if(VoterDataExcelColumnNames.DISRICT_ID.getValue().equals(cellData)){
					voterDataUploadVO.setDistrictId(checkForLong(boothResultExcelColumnMapper.getColumn2()));
					continue;
				}
				if(VoterDataExcelColumnNames.CONSTITUENCY_NAME.getValue().equals(cellData)){
					voterDataUploadVO.setConstituencyName(boothResultExcelColumnMapper.getColumn2());
					continue;
				}if(VoterDataExcelColumnNames.TEHSIL_NAME.getValue().equals(cellData)){
					voterDataUploadVO.setMandalName(boothResultExcelColumnMapper.getColumn2());
					continue;
				}if(VoterDataExcelColumnNames.PART_NUMBER.getValue().equals(cellData)){
					voterDataUploadVO.setPartNo(boothResultExcelColumnMapper.getColumn2());
					continue;
				}				
			}
			else 
			if(VoterDataExcelColumnNames.HOUSE_NUMBER.getValue().equals(cellData)){
				continue;
			}
				voterVO.setHouseNo(boothResultExcelColumnMapper.getColumn1());
				voterVO.setFirstName(boothResultExcelColumnMapper.getColumn2());
				voterVO.setVoterLastName(boothResultExcelColumnMapper.getColumn3());
				voterVO.setRelationshipType(boothResultExcelColumnMapper.getColumn4());
				voterVO.setRelativeFirstName(boothResultExcelColumnMapper.getColumn5());
				voterVO.setRelativeLastName(boothResultExcelColumnMapper.getColumn6());
				voterVO.setHamlet(boothResultExcelColumnMapper.getColumn7());
				voterVO.setTownShip(boothResultExcelColumnMapper.getColumn8());
				voterVO.setLocalArea(boothResultExcelColumnMapper.getColumn9());
				voterVO.setCast(boothResultExcelColumnMapper.getColumn10());
				voterVO.setCastCatagery(boothResultExcelColumnMapper.getColumn11());
				voterVO.setCastSubCatagery(boothResultExcelColumnMapper.getColumn12());
				voterVO.setVoterIDCardNo(boothResultExcelColumnMapper.getColumn13());
				voterVO.setGender(boothResultExcelColumnMapper.getColumn14());
				voterVO.setAge(checkForLong(boothResultExcelColumnMapper.getColumn15()));
			
			voterVOs.add(voterVO);
		}	
		return voterVOs;
	}

	public static void main(String[] args)throws Exception{
		System.out.println("C:/Documents and Settings/ITGrids/Desktop/VoterData.xls");
		VoterDataExcelReader voterDataExcelReader = new VoterDataExcelReader();
		File filePath = new File("C:/Documents and Settings/ITGrids/Desktop/VoterData.xls");
		List<VoterDataUploadVO> list = voterDataExcelReader.readExcel(filePath);
		System.out.println("Total Parts:"+list.size());
		for(VoterDataUploadVO obj:list){
			System.out.println("District Name:"+obj.getDistrictId());
			System.out.println("Constituency Name:"+obj.getConstituencyName());
			System.out.println("Tehsil Name:"+obj.getMandalName());
			System.out.println("Part No:"+obj.getPartNo());
			List<VoterVO> voters = obj.getVoterVOs();
			for(VoterVO voterVo:voters){
				System.out.print(voterVo.getHouseNo()+"\t");
				System.out.print(voterVo.getFirstName()+"\t");
				System.out.print(voterVo.getVoterLastName()+"\t");
				System.out.print(voterVo.getRelationshipType()+"\t");
				System.out.print(voterVo.getRelativeFirstName()+"\t");
				System.out.print(voterVo.getRelativeLastName()+"\t");
				System.out.print(voterVo.getCast()+"\t");
				System.out.print(voterVo.getCastCatagery()+"\t");
				System.out.print(voterVo.getCastSubCatagery()+"\t");
				System.out.print(voterVo.getHamlet()+"\t");
				System.out.print(voterVo.getTownShip()+"\t");
				System.out.print(voterVo.getGender()+"\t");
				System.out.print(voterVo.getAge()+"\t");
				System.out.println(voterVo.getVoterIDCardNo()+"\t");
			}
		}
	}
	
	public Long checkForLong(String value)throws Exception{
		Long longVal = new Long(0);
		if(!StringUtils.isEmpty(value)){
			if(StringUtils.isNumeric(value.trim()))
				longVal = new Long(value);
		}
		return longVal;
	}




}
