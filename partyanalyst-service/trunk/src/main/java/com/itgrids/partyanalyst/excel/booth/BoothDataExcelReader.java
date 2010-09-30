package com.itgrids.partyanalyst.excel.booth;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.itgrids.partyanalyst.excel.CsvException;
import com.itgrids.partyanalyst.excel.booth.BoothDataExcelColumnMapper;

public class BoothDataExcelReader {
	
	private List<BoothDataUploadVO> boothDataUploadVOs;
	
	public BoothDataExcelReader(){
	}	

	public List<BoothDataUploadVO> getBoothDataUploadVOs() {
		return boothDataUploadVOs;
	}

	public void setBoothDataUploadVOs(List<BoothDataUploadVO> boothDataUploadVOs) {
		this.boothDataUploadVOs = boothDataUploadVOs;
	}

	public void readExcelFile(File filePath) throws CsvException{
		try{
			Workbook workbook=Workbook.getWorkbook(filePath);	
			Sheet[] sheets = workbook.getSheets();
			boothDataUploadVOs = new ArrayList<BoothDataUploadVO>();
			for(Sheet sheet:sheets){
				BoothDataUploadVO boothDataUploadVO = new BoothDataUploadVO();
				String sheetName  = sheet.getCell(0,0).getContents().trim();
				String [] constituencyAndDistrinct = StringUtils.split(sheetName,"_");
				String constituencyName = constituencyAndDistrinct[0];
				String districtId = constituencyAndDistrinct[1];
				boothDataUploadVO.setConstituencyName(constituencyName);
				boothDataUploadVO.setDistrictId(new Long(districtId.trim()));
				List<BoothDataExcelColumnMapper> boothDataExcelRows = new ArrayList<BoothDataExcelColumnMapper>();
				for (int row = 2; row < sheet.getRows(); row++) {
					BoothDataExcelColumnMapper boothDataExcelColumnMapper = new BoothDataExcelColumnMapper(sheet,row,sheet.getColumns());
					if(StringUtils.isEmpty(boothDataExcelColumnMapper.getColumn1()))
						break;
					boothDataExcelRows.add(boothDataExcelColumnMapper);
				}
				
				boothDataUploadVO.setBooths(identifyRowAndBindObject(boothDataExcelRows));
				boothDataUploadVOs.add(boothDataUploadVO);
			}
			//testExcelReading();
		} catch(IOException ioe){
			throw new CsvException(ioe.getMessage());
		}catch(BiffException ioe){
			throw new CsvException(ioe.getMessage());
		}catch(Exception excep){
			excep.printStackTrace();
		}
	}
	
	public List<BoothInfo> identifyRowAndBindObject(List<BoothDataExcelColumnMapper> boothDataExcelRows) throws CsvException{
		List<BoothInfo> boothsInfo = new ArrayList<BoothInfo>();
		try{
			for(BoothDataExcelColumnMapper boothDataExcelRow:boothDataExcelRows){
				BoothInfo boothRecord = new BoothInfo();
				Method []methods=BoothDataExcelColumnMapper.class.getDeclaredMethods();
				for(Method method:methods){
					if(method.getName().startsWith("get")){
						String stringVar=(String)method.invoke(boothDataExcelRow, null);
						if(method.getName().equals("getColumn2")){
							boothRecord.setMandalName(stringVar);
						}
						else
						if(method.getName().equals("getColumn3")){
							boothRecord.setPartNo(stringVar);
						}
						else
						if(method.getName().equals("getColumn4")){
							boothRecord.setPartName(stringVar);
						}
						else
						if(method.getName().equals("getColumn5")){
							boothRecord.setLocation(stringVar);
						}
						else
						if(method.getName().equals("getColumn6")){
							boothRecord.setVillagesCovered(stringVar);
						}
						else
						if(method.getName().equals("getColumn7")){
							boothRecord.setCensusCode(checkForSpecialCharacterInCensusCodes(stringVar));
						}
						else
						if(method.getName().equals("getColumn8")){
							boothRecord.setMaleVoters(stringVar);
						}
						else
						if(method.getName().equals("getColumn9")){
							boothRecord.setFemaleVoters(stringVar);
						}
						else
						if(method.getName().equals("getColumn10")){
							boothRecord.setTotalVoters(stringVar);
						}
					}
				}
				boothsInfo.add(boothRecord);
			}
		}catch (IllegalArgumentException argumentExeception) {
			throw new CsvException(argumentExeception.getMessage());
		}catch(InvocationTargetException invocationEx){
			throw new CsvException(invocationEx.getMessage());
		}catch(IllegalAccessException iace){
			throw new CsvException(iace.getMessage());
		}
		return boothsInfo;
	}
	
	public void testExcelReading(){
		System.out.println("Total Constituencies:"+boothDataUploadVOs.size());
		for(BoothDataUploadVO boothDataUploadVO:boothDataUploadVOs){
			System.out.println("Constituency Name:"+boothDataUploadVO.getConstituencyName());
			System.out.println("District Id:"+boothDataUploadVO.getDistrictId());
			System.out.println("Total Booths : "+ boothDataUploadVO.getBooths().size());
			/*int i = 0;
			for(BoothInfo booth:boothDataUploadVO.getBooths()){
				System.out.print(booth.getMandalName()+" ");
				System.out.print(booth.getPartNo()+" ");
				System.out.print(booth.getPartName()+" ");
				System.out.print(booth.getVillagesCovered()+" ");
				System.out.print(booth.getLocation()+" ");
				System.out.print(booth.getCensusCode()+" ");
				System.out.print(booth.getMaleVoters()+" ");
				System.out.print(booth.getFemaleVoters()+" ");
				System.out.println(booth.getTotalVoters()+" ");
				i++;
				if(i==9)
					break;
			}*/
		}
	}
	
	public String checkForSpecialCharacterInCensusCodes(String censusCode){
		Long value = new Long(0);
		if(StringUtils.isEmpty(censusCode))
			return value.toString();
		if(StringUtils.contains(censusCode, "'")){
			censusCode = StringUtils.replace(censusCode, "'", "");
			System.out.print(censusCode+",");
		}
		return censusCode.trim();
	}
	
	public static void main(String[] args)throws Exception {
		BoothDataExcelReader reader = new BoothDataExcelReader();
		File exceFile = new File("C:/Documents and Settings/ITGrids/Desktop/Siva Files/Booth Data Upload1/Nelloreboothdata2004_New.xls");
		reader.readExcelFile(exceFile);
		reader.testExcelReading();
	}
	
}