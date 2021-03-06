package com.itgrids.partyanalyst.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.service.IVotersInformationService;


public  class VotersInformationService implements IVotersInformationService  {

	
	private IConstituencyDAO constituencyDAO;
	private IUserDAO userDAO;
	private Constituency constituency;
	private Object[] data;
	private AgeWiseExcelsGenerationService ageWiseExcelsGenerationService;
	
	private final static Logger LOG = Logger.getLogger(VotersInformationService.class);
	
	HSSFWorkbook workbook;
	HSSFSheet constituencyWiseSheet  ;
	HSSFSheet mandalWiseSheet;
	HSSFSheet panchayatwiseSheet1;
	HSSFSheet panchayatwiseSheet2;
	HSSFSheet panchayatwiseSheet3;
	HSSFSheet boothWiseSheet1;
	HSSFSheet boothWiseSheet2;
	HSSFSheet boothWiseSheet3;
	HSSFSheet hamletWiseSheet1;
	HSSFSheet hamletWiseSheet2;
	HSSFSheet hamletWiseSheet3;
	FileOutputStream out;
	
	public void setAgeWiseExcelsGenerationService(
			AgeWiseExcelsGenerationService ageWiseExcelsGenerationService) {
		this.ageWiseExcelsGenerationService = ageWiseExcelsGenerationService;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public ResultStatus	gettingVotersInfoService(String constituencyId,String publicationId,String ageOrCasteRadio,List<String> ageRanges,boolean pId,String path)
	{
		ResultStatus resultStatus = new ResultStatus();
	    FileOutputStream out = null;
		try {			
			LOG.info("Enetered into gettingVotersInfoService method in VotersInformationService Service");
			workbook = new HSSFWorkbook(); 
			String constituencyName = constituencyDAO.get(Long.valueOf(constituencyId)).getName();
			String uidentifier = new Random().nextInt(100000000)+"";
			String filename=path+"Reports"+"/AgeWise"+"/"+constituencyName+"_"+uidentifier+".xls";
			out=new FileOutputStream(filename);
		    constituency=constituencyDAO.get(Long.parseLong(constituencyId));
		    if(constituency.getAreaType().equalsIgnoreCase("RURAL"))
			   {
				   if(ageOrCasteRadio.equalsIgnoreCase("age"))
				   {  
					    //constituency wise.
					    AgeRangeVO    constituencyWise =getConstituencyWiseDetails(Long.parseLong(constituencyId),Long.parseLong(publicationId), ageRanges);
					    constituencyWiseSheet = workbook.createSheet("Constituency");
					    ageWiseExcelsGenerationService.generateExcelsForConstituency(constituencyWise, constituencyWiseSheet, workbook, ageRanges);
					    //mandalwise.
					    List<AgeRangeVO> mandalWise =getMandalWiseForAConstituency(Long.parseLong(constituencyId),Long.parseLong(publicationId), ageRanges);
					    Collections.sort(mandalWise,mandalWiseSort);
					    mandalWiseSheet = workbook.createSheet("Mandal");
					    ageWiseExcelsGenerationService.generateExcelsForMandalReport1(mandalWise, mandalWiseSheet, workbook, ageRanges);
					    //panchayatwise.
					     List<AgeRangeVO> panchayatWise = getPanchayatWiseForAConstituency(Long.parseLong(constituencyId),Long.parseLong(publicationId), ageRanges,pId);
					     Collections.sort(panchayatWise,panchayatWiseSort);
					     panchayatwiseSheet1 = workbook.createSheet("Panchayat Age Info");
						 panchayatwiseSheet2 = workbook.createSheet("Panchayat Info Gender Wise");
						 panchayatwiseSheet3 = workbook.createSheet("Panchayat Info Gender% Wise");
					     ageWiseExcelsGenerationService.generateExcelsForPanchayatReport1(panchayatWise, panchayatwiseSheet1, workbook, ageRanges);
					     ageWiseExcelsGenerationService.generateExcelsForPanchayatReport2(panchayatWise, panchayatwiseSheet2, workbook, ageRanges);
					     ageWiseExcelsGenerationService.generateExcelsForPanchayatReport3(panchayatWise, panchayatwiseSheet3, workbook, ageRanges);
					    //hamletwise.
					    List<AgeRangeVO>  hamletWise = getHamletWiseDetailsForAConstituency(Long.parseLong(constituencyId),Long.parseLong(publicationId), ageRanges );
					    hamletWiseSheet1 = workbook.createSheet("Hamlet Age Info");
						 hamletWiseSheet2 = workbook.createSheet("Hamlet Info Gender Wise");
						 hamletWiseSheet3 = workbook.createSheet("Hamlet Info Gender% Wise");
						 Collections.sort(hamletWise,hamletWiseSort);
					     ageWiseExcelsGenerationService.generateExcelsForHamletReport1(hamletWise, hamletWiseSheet1, workbook, ageRanges);
					     ageWiseExcelsGenerationService.generateExcelsForHamletReport2(hamletWise, hamletWiseSheet2, workbook, ageRanges);
					     ageWiseExcelsGenerationService.generateExcelsForHamletReport3(hamletWise, hamletWiseSheet3, workbook, ageRanges);
					    //boothwise.
					     List<AgeRangeVO> boothWise = getBoothWiseForAConstituency(Long.parseLong(constituencyId),Long.parseLong(publicationId), ageRanges);
					     boothWiseSheet1 = workbook.createSheet("Booth Age Info");
						 boothWiseSheet2 = workbook.createSheet("Booth Info Gender Wise");
						 boothWiseSheet3 = workbook.createSheet("Booth Info Gender% Wise");
						 Collections.sort(boothWise,boothWiseSort);
					    ageWiseExcelsGenerationService.generateExcelsForBoothReport1(boothWise, boothWiseSheet1, workbook, ageRanges);
					    ageWiseExcelsGenerationService.generateExcelsForBoothReport2(boothWise, boothWiseSheet2, workbook, ageRanges);
					    ageWiseExcelsGenerationService.generateExcelsForBoothReport3(boothWise, boothWiseSheet3, workbook, ageRanges);
			
				   }//if
				   
			   }//rural
			   if(constituency.getAreaType().equalsIgnoreCase("URBAN"))
			   {
				  if(ageOrCasteRadio.equalsIgnoreCase("age"))
			        {
				       AgeRangeVO    constituencyWise =getConstituencyWiseDetails(Long.parseLong(constituencyId),Long.parseLong(publicationId), ageRanges);
				       constituencyWiseSheet = workbook.createSheet("Constituency");
					   ageWiseExcelsGenerationService.generateExcelsForConstituency(constituencyWise, constituencyWiseSheet, workbook, ageRanges);
					   
					   List<AgeRangeVO> panchayatWiseForBooth = getPanchayatWiseForBoothForAConstituency(Long.parseLong(constituencyId),Long.parseLong(publicationId),ageRanges);
					   List<AgeRangeVO> panchayatWiseForBooth1 = new ArrayList<AgeRangeVO>();
					   for (AgeRangeVO ageRangeVO : panchayatWiseForBooth) {
							 ageRangeVO.setTehsilName(ageRangeVO.getTehsilName() + " Munciplity");
							 panchayatWiseForBooth1.add(ageRangeVO);
						}
					   boothWiseSheet1 = workbook.createSheet("Booth Age Info");
					   boothWiseSheet2 = workbook.createSheet("Booth Info Gender Wise");
					   boothWiseSheet3 = workbook.createSheet("Booth Info Gender% Wise");
					   ageWiseExcelsGenerationService.generateExcelsForBoothReport1(panchayatWiseForBooth1,boothWiseSheet1,workbook,ageRanges);
					   ageWiseExcelsGenerationService.generateExcelsForBoothReport2(panchayatWiseForBooth1,boothWiseSheet2,workbook,ageRanges);
					   ageWiseExcelsGenerationService.generateExcelsForBoothReport3(panchayatWiseForBooth1,boothWiseSheet3,workbook,ageRanges);
					   
			        }
				  
			   }//111
			   if(constituency.getAreaType().equalsIgnoreCase("RURAL-URBAN"))
			   {
				   if(ageOrCasteRadio.equalsIgnoreCase("age"))
			       { //constituency wise.
					 AgeRangeVO    constituencyWise =getConstituencyWiseDetails(Long.parseLong(constituencyId),Long.parseLong(publicationId), ageRanges);
					 constituencyWiseSheet = workbook.createSheet("Constituency");
					 constituencyWiseSheet.autoSizeColumn((short) 1000);
                     ageWiseExcelsGenerationService.generateExcelsForConstituency(constituencyWise, constituencyWiseSheet, workbook, ageRanges);
					 //municpality wise.
					 List<AgeRangeVO> muncipalityWise = getMunicipalityWiseForAConstituency(Long.parseLong(constituencyId),Long.parseLong(publicationId), ageRanges);
					 List<AgeRangeVO> muncipalityWise1 = new ArrayList<AgeRangeVO>();//kavali---->kavali municipality.
					 for (AgeRangeVO ageRangeVO : muncipalityWise) {
						 ageRangeVO.setTehsilName(ageRangeVO.getTehsilName() + " Munciplity");
						 ageRangeVO.setPanchayatName(ageRangeVO.getPanchayatName() + " Munciplity");
						 muncipalityWise1.add(ageRangeVO);
					 }
					 //mandalwise.
					 List<AgeRangeVO> mandalWise = getMandalWiseForAConstituency(Long.parseLong(constituencyId),Long.parseLong(publicationId), ageRanges);
					 Collections.sort(mandalWise,mandalWiseSort);//dnso of mandalname.
					 if(muncipalityWise1 != null && muncipalityWise1.size() > 0)
						mandalWise.addAll(muncipalityWise1);
					 mandalWiseSheet = workbook.createSheet("Mandal");
					 ageWiseExcelsGenerationService.generateExcelsForMandalReport1(mandalWise, mandalWiseSheet, workbook, ageRanges);
					 //panchayatwise.   
					 List<AgeRangeVO> panchayatWise = getPanchayatWiseForAConstituency(Long.parseLong(constituencyId),Long.parseLong(publicationId), ageRanges,pId);
					 Collections.sort(panchayatWise,panchayatWiseSort);
					 if(muncipalityWise1 != null && muncipalityWise1.size() > 0)
						panchayatWise.addAll(muncipalityWise1);
					 panchayatwiseSheet1 = workbook.createSheet("Panchayat Age Info");
					 panchayatwiseSheet2 = workbook.createSheet("Panchayat Info Gender Wise");
					 panchayatwiseSheet3 = workbook.createSheet("Panchayat Info Gender% Wise");
	                 panchayatwiseSheet1.autoSizeColumn((short) 1000);
					 panchayatwiseSheet2.autoSizeColumn((short) 1000);
					 panchayatwiseSheet3.autoSizeColumn((short) 1000);
					 ageWiseExcelsGenerationService.generateExcelsForPanchayatReport1(panchayatWise, panchayatwiseSheet1, workbook, ageRanges);
					 ageWiseExcelsGenerationService.generateExcelsForPanchayatReport2(panchayatWise, panchayatwiseSheet2, workbook, ageRanges);
					 ageWiseExcelsGenerationService.generateExcelsForPanchayatReport3(panchayatWise, panchayatwiseSheet3, workbook, ageRanges);
					 //hamletwise.
			         List<AgeRangeVO>  hamletWise = getHamletWiseDetailsForAConstituency(Long.parseLong(constituencyId),Long.parseLong(publicationId), ageRanges );
			         Collections.sort(hamletWise,hamletWiseSort);
			         hamletWiseSheet1 = workbook.createSheet("Hamlet Age Info");
					 hamletWiseSheet2 = workbook.createSheet("Hamlet Info Gender Wise");
					 hamletWiseSheet3 = workbook.createSheet("Hamlet Info Gender% Wise");
					 Collections.sort(hamletWise,hamletWiseSort);
				     ageWiseExcelsGenerationService.generateExcelsForHamletReport1(hamletWise, hamletWiseSheet1, workbook, ageRanges);
				     ageWiseExcelsGenerationService.generateExcelsForHamletReport2(hamletWise, hamletWiseSheet2, workbook, ageRanges);
				     ageWiseExcelsGenerationService.generateExcelsForHamletReport3(hamletWise, hamletWiseSheet3, workbook, ageRanges);
					 //boothwise.
				     List<AgeRangeVO> boothWise  = getBoothWiseForAConstituency(Long.parseLong(constituencyId),Long.parseLong(publicationId), ageRanges);
				     List<AgeRangeVO> panchayatWiseForBooth = getPanchayatWiseForBoothForAConstituency(Long.parseLong(constituencyId),Long.parseLong(publicationId), ageRanges);
					 List<AgeRangeVO> panchayatWiseForBooth1 = new ArrayList<AgeRangeVO>();
					 for (AgeRangeVO ageRangeVO : panchayatWiseForBooth) {
						 ageRangeVO.setTehsilName(ageRangeVO.getTehsilName() + " Munciplity");
						 panchayatWiseForBooth1.add(ageRangeVO);
					}
					if(panchayatWiseForBooth1 != null && panchayatWiseForBooth1.size() > 0)
						boothWise.addAll(panchayatWiseForBooth1);
					 boothWiseSheet1 = workbook.createSheet("Booth Age Info");
					 boothWiseSheet2 = workbook.createSheet("Booth Info Gender Wise");
					 boothWiseSheet3 = workbook.createSheet("Booth Info Gender% Wise");
					 Collections.sort(boothWise,boothWiseSort);
					 ageWiseExcelsGenerationService.generateExcelsForBoothReport1(boothWise, boothWiseSheet1, workbook, ageRanges);
					 ageWiseExcelsGenerationService.generateExcelsForBoothReport2(boothWise, boothWiseSheet2, workbook, ageRanges);
					 ageWiseExcelsGenerationService.generateExcelsForBoothReport3(boothWise, boothWiseSheet3, workbook, ageRanges);
					
				    }
		   }
			   workbook.write(out);
			   resultStatus.setMessage("Reports"+"/AgeWise"+"/"+constituencyName+"_"+uidentifier+".xls"); 
			   resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		} catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			LOG.error("Error Occured in gettingVotersInfoService method in VotersInformationService Service", e);
		}
		finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultStatus;
}
AgeRangeVO vo;
//getConstituencyWiseDetails
public AgeRangeVO getConstituencyWiseDetails(Long constituencyId,Long publicationId,List<String> ageRanges)	
{
	if(ageRanges==null || ageRanges.size() == 0 || ageRanges.get(0).isEmpty())
	 {
		ageRanges=new ArrayList<String>();
		ageRanges.add("18-22");
		ageRanges.add("23-25");
		ageRanges.add("26-35");
		ageRanges.add("36-45");
		ageRanges.add("46-60");
		ageRanges.add(">60");
	 }
String	queryForTotalCount=	
			(" select bpv.booth.constituency.constituencyId, bpv.booth.constituency.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
			"	from BoothPublicationVoter bpv "+
			"	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
			" group by bpv.voter.gender ");	

List<Object[]> totalCountList = userDAO.getData1(queryForTotalCount);
AgeRangeVO ageRangeVOForTotalCount=null;
if(totalCountList!=null)
{
for (Object[] totalCountListObj : totalCountList) 
{
 
if(ageRangeVOForTotalCount==null)
{
	ageRangeVOForTotalCount=new AgeRangeVO();
	ageRangeVOForTotalCount.setTehsilId((Long)totalCountListObj[0]);//constid
	ageRangeVOForTotalCount.setTehsilName(totalCountListObj[1].toString());//constname.
	if((Long)totalCountListObj[2]!=null)
	{ 
		ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+(Long)totalCountListObj[2]);
      if(totalCountListObj[3].toString()!=null)
      {
		  if(totalCountListObj[3].toString().equalsIgnoreCase("M") )
			ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
		 else if(totalCountListObj[3].toString().equalsIgnoreCase("F") )
			 ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
		}
	}
}
else
{
	if((Long)totalCountListObj[2]!=null)
	{
		  ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+(Long)totalCountListObj[2] );

		  if(totalCountListObj[3].toString()!=null){
		if(totalCountListObj[3].toString().equalsIgnoreCase("M") )
			ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
		else
			ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
	}}
}

}
}
List<AgeRangeVO>  query=new ArrayList<AgeRangeVO>();
for(String ageRange:ageRanges)
{
	if(ageRange.contains("-"))
	{    
		    String[] parts = ageRange.split("-");
			    String part1 = parts[0]; 
			    String part2 = parts[1]; 
					
					String	queryString1=null;
				queryString1=	
							(" select bpv.booth.constituency.constituencyId, bpv.booth.constituency.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
							"	from BoothPublicationVoter bpv "+
							"	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
							" and bpv.voter.age>="+part1+" and bpv.voter.age<="+part2+"  "+
							" group by bpv.voter.gender ");	
				
				 AgeRangeVO vo=new AgeRangeVO();
			        vo.setAgeRange(ageRange);
			        vo.setQueryString(queryString1);
				     query.add(vo);
	}  
	else if( (ageRange.contains(">")) && (!ageRange.contains("=")))
	{
		     String queryString2=null;
				  String str=ageRange;
			   	   str = str.trim();
			   	  Pattern p = Pattern.compile("(\\d+)");
			   	  Matcher m = p.matcher(str);
			   	  Long ageRangeageRange=0l;
			   	  if(m.find())
			   	    ageRangeageRange=Long.parseLong(m.group(1));
			   	    queryString2= 	
						   (" select bpv.booth.constituency.constituencyId, bpv.booth.constituency.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
						   		 "	from BoothPublicationVoter bpv "+
						   		 "	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
						   		 " and bpv.voter.age>"+ageRangeageRange+"  "+
						   		 "		group by bpv.voter.gender");	
						   	
			     
			   	   AgeRangeVO vo=new AgeRangeVO();
			        vo.setAgeRange(ageRange);
			        vo.setQueryString(queryString2);
				     query.add(vo);
		    	   
				    
		    	   
	}
	else if((ageRange.contains("<")) && (!ageRange.contains("=")))
	{	
		 String queryString3=null;
		  String str=ageRange;
	  	   str = str.trim();
	  	  Pattern p = Pattern.compile("(\\d+)");
	  	  Matcher m = p.matcher(str);
	  	  Long ageRangeageRange=0l;
	  	  if(m.find())
	  	    ageRangeageRange=Long.parseLong(m.group(1));
	  	    queryString3= 	
				   (" select bpv.booth.constituency.constituencyId, bpv.booth.constituency.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
				   		 "	from BoothPublicationVoter bpv "+
				   		 "	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
				   		 " and bpv.voter.age<"+ageRangeageRange+"  "+
				   		 "		group by bpv.voter.gender");	
				   	
	    
	  	   AgeRangeVO vo=new AgeRangeVO();
	       vo.setAgeRange(ageRange);
	       vo.setQueryString(queryString3);
		     query.add(vo);
	}
	else if((ageRange.contains(">")) && (ageRange.contains("=")))
	{  String queryString4=null;
	String str=ageRange;
	   str = str.trim();
	  Pattern p = Pattern.compile("(\\d+)");
	  Matcher m = p.matcher(str);
	  Long ageRangeageRange=0l;
	  if(m.find())
	    ageRangeageRange=Long.parseLong(m.group(1));
	    queryString4= 	
			   (" select bpv.booth.constituency.constituencyId, bpv.booth.constituency.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
			   		 "	from BoothPublicationVoter bpv "+
			   		 "	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
			   		 " and bpv.voter.age>="+ageRangeageRange+"  "+
			   		 "		group by bpv.voter.gender");	
			   	

	   AgeRangeVO vo=new AgeRangeVO();
	 vo.setAgeRange(ageRange);
	 vo.setQueryString(queryString4);
	  query.add(vo);

	}
	else if((ageRange.contains("<")) && (ageRange.contains("=")))
	{  String queryString5=null;
	   String str=ageRange;
	   str = str.trim();
	  Pattern p = Pattern.compile("(\\d+)");
	  Matcher m = p.matcher(str);
	  Long ageRangeageRange=0l;
	  if(m.find())
	    ageRangeageRange=Long.parseLong(m.group(1));
	    queryString5= 	
			   (" select bpv.booth.constituency.constituencyId, bpv.booth.constituency.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
			   		 "	from BoothPublicationVoter bpv "+
			   		 "	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
			   		 " and bpv.voter.age<="+ageRangeageRange+"  "+
			   		 "		group by bpv.voter.gender");	
			   	

	   AgeRangeVO vo=new AgeRangeVO();
	 vo.setAgeRange(ageRange);
	 vo.setQueryString(queryString5);
	  query.add(vo);

	}
	else 
	{  String queryString6=null;
	   Long ageRangeageRange=Long.parseLong(ageRange);
	  
	    queryString6= 	
			   (" select bpv.booth.constituency.constituencyId, bpv.booth.constituency.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
			   		 "	from BoothPublicationVoter bpv "+
			   		 "	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
			   		 " and bpv.voter.age="+ageRangeageRange+"  "+
			   		 "		group by bpv.voter.gender");	
			   	

	   AgeRangeVO vo=new AgeRangeVO();
	 vo.setAgeRange(ageRange);
	 vo.setQueryString(queryString6);
	  query.add(vo);
	}

}
	 AgeRangeVO  li =printDataForConstituency( query,ageRangeVOForTotalCount);
    return li;	   	
}
public AgeRangeVO printDataForConstituency(List<AgeRangeVO> query,AgeRangeVO ageRangeVOForTotalCount)
{
  AgeRangeVO  li=null;
  AgeRangeVO ageRangeVO=null;
  
  List<AgeRangeVO> output=new ArrayList<AgeRangeVO>();
		  if(query!=null)
		  {
		    for(AgeRangeVO   vo    :query)
		    {
			   List<Object[]> list = userDAO.getData1(vo.getQueryString());
			   AgeRangeVO vo1=new AgeRangeVO();
			   vo1.setAgeRange(vo.getAgeRange());
			   vo1.setOutput(list);
			   output.add(vo1);
		     }
		    }	
		  for(AgeRangeVO param:output){
			  List<Object[]> list=param.getOutput();
			  if(list.size()>0){
				  for(Object[] obj:list){ //iteration starts.
					 long l=(Long)obj[0];
					  
					//0-cid,1-cname,2-count,3-gender
					   if(ageRangeVO == null)
					   {
						   ageRangeVO = new AgeRangeVO();
						   ageRangeVO.setTehsilName((obj[1].toString()));//constname.
						   ageRangeVO.setTehsilId((Long)obj[0]);//cid
						   ageRangeVO.setTotalCount(ageRangeVOForTotalCount.getTotalCount());
						   ageRangeVO.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount());
						   ageRangeVO.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount());
					       Map<String,AgeRangeVO> m=new LinkedHashMap<String,AgeRangeVO>();
					       ageRangeVO.setMap(m);
					     
					    }
					   
					   if(obj[2]!=null)
					   {
						   String ageRange =param.getAgeRange();
						    	
						    	  Map<String,AgeRangeVO>   m =ageRangeVO.getMap();
						    	    
						    	     AgeRangeVO particularAge  =m.get(ageRange);  //checking for particular  agerangevo.
						    	     long a=(Long)obj[0];
						    	     long b=ageRangeVO.getTehsilId();
						    	     if(a==b){
						    	     if(particularAge==null)
						    	     {
						    	    	 particularAge =new AgeRangeVO();
						    	    	 particularAge.setParticularAgeVotersCount(( particularAge.getParticularAgeVotersCount())+ ((Long)obj[2]) );
						    	    
						    	    	 if(obj[3]!=null)
							    	       {
							    	    	   if(obj[3].toString().equalsIgnoreCase("M"))
							    	    	      particularAge.setMaleCount(Long.parseLong(obj[2].toString()));
							    	    		   
							    	    	   if(obj[3].toString().equalsIgnoreCase("F"))
								    	    	      particularAge.setFemaleCount(Long.parseLong(obj[2].toString()));
								    	    		      
							    	    	   
							    	       }
						    	    	  m.put(ageRange, particularAge);
						    	    	 
						    	     }
						    	     else
						    	     {		
							    	   particularAge.setParticularAgeVotersCount(( particularAge.getParticularAgeVotersCount())+ ((Long)obj[2]) );
						    	       if(obj[3]!=null)
						    	       {
						    	    	   if(obj[3].toString().equalsIgnoreCase("M"))
						    	    	      particularAge.setMaleCount(Long.parseLong(obj[2].toString()));
						    	    		   
						    	    	   if(obj[3].toString().equalsIgnoreCase("F"))
							    	    	      particularAge.setFemaleCount(Long.parseLong(obj[2].toString()));
							    	     }
						    	     
						    	     } 
						    	    	 
						    	     }	 
						    	     }//if 	 
						      }//for
					   }//if
					}//for
			  
	li=gettingPercantage1(ageRangeVO);
	return li; 	
	}
public AgeRangeVO gettingPercantage1(AgeRangeVO outerVO) 
	{
		if(outerVO != null)
		{
			DecimalFormat df = new DecimalFormat("##.##");
			  Long totalVoters=outerVO.getTotalCount();

			  Map<String,AgeRangeVO> innerMap         =outerVO.getMap();
			  for(Map.Entry<String,AgeRangeVO>  innerMapEntrySet :innerMap.entrySet() )
			  {
				  AgeRangeVO innerVO=innerMapEntrySet.getValue();
				    Long particularAgeCount = innerVO.getParticularAgeVotersCount();
				 
				    double d   =((double)particularAgeCount*100)/totalVoters;
				    innerVO.setAgeRangePerc(( Double.parseDouble (df.format( d) ) ));
			   }}
		  
		return outerVO;
	}//end of cons wise details.
		


//getHamletWiseDetailsForAConstituency.
public List<AgeRangeVO> getHamletWiseDetailsForAConstituency(Long constituencyId,Long publicationId,List<String> ageRanges)
{  
	if(ageRanges==null || ageRanges.size() == 0 ||ageRanges.get(0).isEmpty())
	{
		ageRanges=new ArrayList<String>();
		ageRanges.add("18-22");
		ageRanges.add("23-25");
		ageRanges.add("26-35");
		ageRanges.add("36-45");
		ageRanges.add("46-60");
		ageRanges.add(">60");
	}
	
	
	
String	queryForTotalCount = ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName,bpv.voter.gender " +
	    		"from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
	    		"where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
	    		"bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId ="+publicationId+"  " +
	    		"and uvd.user.userId=1   " +
	    		" group by  uvd.hamlet.hamletId,bpv.voter.gender");

         Map<Long,AgeRangeVO> totalCountMap=new LinkedHashMap<Long,AgeRangeVO>();
         List<Object[]> totalCountList = userDAO.getData1(queryForTotalCount);
         if(totalCountList!=null)
         {
           for (Object[] totalCountListObj : totalCountList) {
           AgeRangeVO ageRangeVOForTotalCount=totalCountMap.get((Long)totalCountListObj[0]);
           if(ageRangeVOForTotalCount==null) 
          {
	        ageRangeVOForTotalCount=new AgeRangeVO();
	        ageRangeVOForTotalCount.setHamletId((Long)totalCountListObj[0]);
	        ageRangeVOForTotalCount.setHamletName(totalCountListObj[1].toString());
	        ageRangeVOForTotalCount.setPanchayatName(totalCountListObj[3].toString());
            if((Long)totalCountListObj[2]!=null)
	        { 
		      ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+(Long)totalCountListObj[2]);
              if(totalCountListObj[4].toString()!=null)
              {
		        if(totalCountListObj[4].toString().equalsIgnoreCase("M") )
			    ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
		        else if(totalCountListObj[4].toString().equalsIgnoreCase("F") )
			    ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
		     }
	        }
	       totalCountMap.put((Long)totalCountListObj[0], ageRangeVOForTotalCount);
          }
          else
   		 {
   			if((Long)totalCountListObj[2]!=null)
   			{
   				ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+(Long)totalCountListObj[2] );
                if(totalCountListObj[4].toString()!=null){
   				if(totalCountListObj[4].toString().equalsIgnoreCase("M") )
   					ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
   				else
   					ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
   			}}
   		}
     }
   }
	
      
     	List<AgeRangeVO>  query=new ArrayList<AgeRangeVO>();
     	for(String ageRange:ageRanges)
     	{
     		  if(ageRange.contains("-"))
     		  {    
     		     	    String[] parts = ageRange.split("-");
     		     		    String part1 = parts[0]; 
     		     		    String part2 = parts[1]; 
     			    //0-hid,1-hname,2-count,3-pname
     			    
     		        String	queryString = ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName,bpv.voter.gender " +
     			    		"from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
     			    		"where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
     			    		"bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId ="+publicationId+"  " +
     			    		"and uvd.user.userId=1 and uvd.voter.age>="+part1+" and  uvd.voter.age<="+part2+"  " +
     			    		" group by  uvd.hamlet.hamletId,bpv.voter.gender");
     		        AgeRangeVO vo=new AgeRangeVO();
     		        vo.setAgeRange(ageRange);
     		        vo.setQueryString(queryString);
     			     query.add(vo);
     			
     		   }
     		  else if( (ageRange.contains(">")) && (!ageRange.contains("=")))
     		  {
     			   
     				  String str=ageRange;
     		   	   str = str.trim();
     		   	  Pattern p = Pattern.compile("(\\d+)");
     		   	  Matcher m = p.matcher(str);
     		   	  Long ageRangeageRange=0l;
     		   	  if(m.find())
     		   	    ageRangeageRange=Long.parseLong(m.group(1));
     			   
     			   
     			   String queryString2= ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName,bpv.voter.gender  " +
     			    			"from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
     			    			"where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
     			    			"bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId ="+publicationId+"    " +
     			    			"and uvd.user.userId=1 and uvd.voter.age>"+ageRangeageRange+" " +
     			    			"group by  uvd.hamlet.hamletId,bpv.voter.gender");
     		   
     		        AgeRangeVO vo=new AgeRangeVO();
     		        vo.setAgeRange(ageRange);
     		        vo.setQueryString(queryString2);
     			     query.add(vo);
     			    	
     		  }
     		  else if( (ageRange.contains(">")) && (ageRange.contains("=")))
     		  {
     			   
     				  String str=ageRange;
     		   	   str = str.trim();
     		   	  Pattern p = Pattern.compile("(\\d+)");
     		   	  Matcher m = p.matcher(str);
     		   	  Long ageRangeageRange=0l;
     		   	  if(m.find())
     		   	    ageRangeageRange=Long.parseLong(m.group(1));
     			   
     			   
     			   String queryString3= ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName,bpv.voter.gender  " +
     			    			"from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
     			    			"where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
     			    			"bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId ="+publicationId+"    " +
     			    			"and uvd.user.userId=1 and uvd.voter.age>="+ageRangeageRange+" " +
     			    			"group by  uvd.hamlet.hamletId,bpv.voter.gender");
     		   
     		        AgeRangeVO vo=new AgeRangeVO();
     		        vo.setAgeRange(ageRange);
     		        vo.setQueryString(queryString3);
     			     query.add(vo);
     			    	
     		  }
     		  else if( (ageRange.contains("<")) && (!ageRange.contains("=")))
     		  {
     			   
     				  String str=ageRange;
     		   	   str = str.trim();
     		   	  Pattern p = Pattern.compile("(\\d+)");
     		   	  Matcher m = p.matcher(str);
     		   	  Long ageRangeageRange=0l;
     		   	  if(m.find())
     		   	    ageRangeageRange=Long.parseLong(m.group(1));
     			   
     			   
     			   String queryString4= ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName,bpv.voter.gender  " +
     			    			"from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
     			    			"where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
     			    			"bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId ="+publicationId+"    " +
     			    			"and uvd.user.userId=1 and uvd.voter.age<"+ageRangeageRange+" " +
     			    			"group by  uvd.hamlet.hamletId,bpv.voter.gender");
     		   
     		        AgeRangeVO vo=new AgeRangeVO();
     		        vo.setAgeRange(ageRange);
     		        vo.setQueryString(queryString4);
     			     query.add(vo);
     			    	
     		  }
     		  else if( (ageRange.contains("<")) && (ageRange.contains("=")))
     		  {
     			   
     				  String str=ageRange;
     		   	   str = str.trim();
     		   	  Pattern p = Pattern.compile("(\\d+)");
     		   	  Matcher m = p.matcher(str);
     		   	  Long ageRangeageRange=0l;
     		   	  if(m.find())
     		   	    ageRangeageRange=Long.parseLong(m.group(1));
     			   
     			   
     			   String queryString5= ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName,bpv.voter.gender  " +
     			    			"from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
     			    			"where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
     			    			"bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId ="+publicationId+"    " +
     			    			"and uvd.user.userId=1 and uvd.voter.age<="+ageRangeageRange+" " +
     			    			"group by  uvd.hamlet.hamletId,bpv.voter.gender");
     		   
     		        AgeRangeVO vo=new AgeRangeVO();
     		        vo.setAgeRange(ageRange);
     		        vo.setQueryString(queryString5);
     			     query.add(vo);
     			    	
     		  }  
     		  else
     		  {
     			  Long ageRangeageRange=Long.parseLong(ageRange);
     		      String queryString6= ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName,bpv.voter.gender  " +
     			    			"from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
     			    			"where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
     			    			"bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId ="+publicationId+"    " +
     			    			"and uvd.user.userId=1 and uvd.voter.age="+ageRangeageRange+" " +
     			    			"group by  uvd.hamlet.hamletId,bpv.voter.gender");
     		  
     		       AgeRangeVO vo=new AgeRangeVO();
     		       vo.setAgeRange(ageRange);
     		       vo.setQueryString(queryString6);
     			     query.add(vo);
     		   }
}	    
List<AgeRangeVO>  li =printData4( query,totalCountMap);
return li;    
}
public List<AgeRangeVO> printData4(List<AgeRangeVO> query,Map<Long,AgeRangeVO> totalCountMap )
{
List<AgeRangeVO> li =null;
Map<Long,AgeRangeVO> hamletMap=new LinkedHashMap<Long, AgeRangeVO>();
for(Map.Entry<Long,AgeRangeVO>  totalCountMapEntrySet :totalCountMap.entrySet() )
	  {
	       Long hamletId = totalCountMapEntrySet.getKey();
	       AgeRangeVO  totalCountMapVO=totalCountMapEntrySet.getValue();
	       
	       AgeRangeVO  hamletMapVO= hamletMap.get(hamletId);
	       if(hamletMapVO==null)
	       {  hamletMapVO=new AgeRangeVO();
	          hamletMapVO.setTotalCount(totalCountMapVO.getTotalCount());
	          hamletMapVO.setTotalMaleCount(totalCountMapVO.getTotalMaleCount());
	          hamletMapVO.setTotalFemaleCount(totalCountMapVO.getTotalFemaleCount());
	          hamletMapVO.setHamletId(totalCountMapVO.getHamletId());
	          hamletMapVO.setHamletName(totalCountMapVO.getHamletName());
	          hamletMapVO.setPanchayatName(totalCountMapVO.getPanchayatName());
			  Map<String,AgeRangeVO> m=new LinkedHashMap<String,AgeRangeVO>();
			  hamletMapVO.setMap(m);
			  hamletMap.put(hamletId, hamletMapVO);
	       }
	      
	   }

List<AgeRangeVO> output=new ArrayList<AgeRangeVO>();
if(query!=null)
{
for(AgeRangeVO   vo    :query)
{
  List<Object[]> list1 = userDAO.getData1(vo.getQueryString());
 
  AgeRangeVO vo1=new AgeRangeVO();
  vo1.setAgeRange(vo.getAgeRange());
  vo1.setOutput(list1);
  output.add(vo1);
}
}

if(output!=null)
{
for(AgeRangeVO param:output){
 List<Object[]> list=param.getOutput();
 iterateLogic3(hamletMap,param,list);

}
}
li=gettingPercantage(hamletMap);
return li;

}

public void iterateLogic3(Map<Long,AgeRangeVO> hamletMap,AgeRangeVO param, List<Object[]> list) {
if(list.size()>0){
	  for(Object[] obj:list){
		 long l=(Long)obj[0];
		  AgeRangeVO vo = hamletMap.get((Long)obj[0]);
		  if(obj[2]!=null)
		   {
			     String ageRange =param.getAgeRange();
			     for(Map.Entry<Long,AgeRangeVO>  ageRangeVO :hamletMap.entrySet() )
			     {
			    	 AgeRangeVO vo1 = ageRangeVO.getValue();
			    	 Long hamletId = ageRangeVO.getKey();
			    	  Map<String,AgeRangeVO>   m =vo1.getMap();
			    	     AgeRangeVO particularAge  =m.get(ageRange);
			    	     long a=(Long)obj[0];
			    	     long b=vo1.getHamletId();
			    	    if(a==b){
			    	     if(particularAge==null)
			    	     {
			    	    	 particularAge =new AgeRangeVO();
			    	    	 particularAge.setParticularAgeVotersCount ((particularAge.getParticularAgeVotersCount())+ ((Long)obj[2]) );
			    	    	 particularAge.setHamletId(hamletId);
			    	    	 if(obj[4]!=null)
				    	       {
				    	    	   if(obj[4].toString().equalsIgnoreCase("M"))
				    	    	      particularAge.setMaleCount(Long.parseLong(obj[2].toString()));
				    	    		   
				    	    	   if(obj[4].toString().equalsIgnoreCase("F"))
					    	    	      particularAge.setFemaleCount(Long.parseLong(obj[2].toString()));
					    	    		      
				    	    	   
				    	       }
			    	    	 
			    	    	 m.put(ageRange, particularAge);
			    	    	// vo.setTotalCount(vo.getTotalCount()+(Long)obj[2]);
			    	    	 
			    	    	 
			    	     }
			    	     else
			    	     {
			    	    	long hamletIdd=particularAge.getHamletId();
			    	    	if(hamletIdd==l ) 
			    	    	{		
			    	         particularAge.setParticularAgeVotersCount(( particularAge.getParticularAgeVotersCount())+ ((Long)obj[2]) );
			    	       	
				    	       if(obj[4]!=null)
				    	       {
				    	    	   if(obj[4].toString().equalsIgnoreCase("M"))
				    	    	      particularAge.setMaleCount(Long.parseLong(obj[2].toString()));
				    	    		   
				    	    	   if(obj[4].toString().equalsIgnoreCase("F"))
					    	    	      particularAge.setFemaleCount(Long.parseLong(obj[2].toString()));
					    	    		      
				    	    	   
				    	       }
			    	         
			    	         //vo.setTotalCount(vo.getTotalCount()+(Long)obj[2]);
			    	        } 
			    	    	 
			    	     }	 
			    	    }//if 	 
			      }//for
		   }//if
			   
}//for
 }//if
}
//end getHamletWiseDetailsForAConstituency.


//getPanchayatWiseForAConstituency.
public List<AgeRangeVO> getPanchayatWiseForAConstituency(Long constituencyId,Long publicationId,List<String> ageRanges,boolean pId)
{
	if(ageRanges==null || ageRanges.size() == 0 ||ageRanges.get(0).isEmpty())
	{
		ageRanges=new ArrayList<String>();
		ageRanges.add("18-22");
		ageRanges.add("23-25");
		ageRanges.add("26-35");
		ageRanges.add("36-45");
		ageRanges.add("46-60");
		ageRanges.add(">60");
	}
	
	 if( pId==false )
     {
	   List<AgeRangeVO>  li = getPanchayatWiseDetails( constituencyId, publicationId, ageRanges,false);
	   return li;
	 }
	else{
	       List<AgeRangeVO>  li1=getPanchayatWiseDetails( constituencyId, publicationId, ageRanges,true);
           return li1;
         }
}

public List<AgeRangeVO> getPanchayatWiseDetails(Long constituencyId,Long publicationId,List<String> ageRanges,boolean partial)
{  
	 if(!partial)
		{ 
		  String queryForTotalCount=("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
 				    "count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender "+
 		            "from BoothPublicationVoter bpv "+
 		            "where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
 		            "group by bpv.booth.panchayat.panchayatId,bpv.voter.gender  ");

	         Map<Long,AgeRangeVO> totalCountMap=new LinkedHashMap<Long,AgeRangeVO>();
	         List<Object[]> totalCountList = userDAO.getData1(queryForTotalCount);
	         if(totalCountList!=null)
	         {
	           for (Object[] totalCountListObj : totalCountList) {
	           AgeRangeVO ageRangeVOForTotalCount=totalCountMap.get((Long)totalCountListObj[0]);
	           if(ageRangeVOForTotalCount==null) 
	          {
		        ageRangeVOForTotalCount=new AgeRangeVO();
		        ageRangeVOForTotalCount.setPanchayatId((Long)totalCountListObj[0]);//pid
		        ageRangeVOForTotalCount.setPanchayatName(totalCountListObj[1].toString());//pname
		        ageRangeVOForTotalCount.setTehsilName(totalCountListObj[3].toString());//tehsil name.
	            if((Long)totalCountListObj[2]!=null)
		        { 
			      ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+(Long)totalCountListObj[2]);
	              if(totalCountListObj[4].toString()!=null)
	              {
			        if(totalCountListObj[4].toString().equalsIgnoreCase("M") )
				    ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
			        else if(totalCountListObj[4].toString().equalsIgnoreCase("F") )
				    ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
			     }
		        }
		       totalCountMap.put((Long)totalCountListObj[0], ageRangeVOForTotalCount);
	          }
	          else
	   		 {
	   			if((Long)totalCountListObj[2]!=null)
	   			{
	   				ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+(Long)totalCountListObj[2] );
	                if(totalCountListObj[4].toString()!=null){
	   				if(totalCountListObj[4].toString().equalsIgnoreCase("M") )
	   					ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
	   				else
	   					ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
	   			}}
	   		}
	     }
	   }
		 
		   
			 
				List<AgeRangeVO>  query=new ArrayList<AgeRangeVO>();
				for(String ageRange:ageRanges)
				{
					   if(ageRange.contains("-"))
					   {    
						    String[] parts = ageRange.split("-");
							    String part1 = parts[0]; 
							    String part2 = parts[1]; 
						//pid,pname,count,tnme. 
					  String queryString =
					    		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
					    				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender "+
					    		"from BoothPublicationVoter bpv "+
					    		"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
					    		     " and bpv.voter.age>="+part1+" and bpv.voter.age<="+part2+" "+
					    		"group by bpv.booth.panchayat.panchayatId,bpv.voter.gender  ");
				        AgeRangeVO vo=new AgeRangeVO();
				        vo.setAgeRange(ageRange);
				        vo.setQueryString(queryString);
					     query.add(vo);
					
				       }
				      else if((ageRange.contains(">"))&&(!ageRange.contains("=")))
				      {
				    	  
				    	  String str=ageRange;
				    	   str = str.trim();
				    	  Pattern p = Pattern.compile("(\\d+)");
				    	  Matcher m = p.matcher(str);
				    	  Long ageRangeageRange=0l;
				    	  if(m.find())
				    	    ageRangeageRange=Long.parseLong(m.group(1));
				    	  String queryString2 =
					    		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
					    				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender "+
					    		"from BoothPublicationVoter bpv "+
					    		"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
					    		     " and bpv.voter.age>"+ageRangeageRange+"  "+
					    		"group by bpv.booth.panchayat.panchayatId,bpv.voter.gender ");
				   
				           AgeRangeVO vo=new AgeRangeVO();
				           vo.setAgeRange(ageRange);
				           vo.setQueryString(queryString2);
					       query.add(vo);
					    	
					    }
				      else if((ageRange.contains(">"))&&(ageRange.contains("=")))
				      {
				    	  
				    	  String str=ageRange;
				    	   str = str.trim();
				    	  Pattern p = Pattern.compile("(\\d+)");
				    	  Matcher m = p.matcher(str);
				    	  Long ageRangeageRange=0l;
				    	  if(m.find())
				    	    ageRangeageRange=Long.parseLong(m.group(1));
				    	  String queryString3 =
					    		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
					    				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender "+
					    		"from BoothPublicationVoter bpv "+
					    		"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
					    		     " and bpv.voter.age>="+ageRangeageRange+"  "+
					    		"group by bpv.booth.panchayat.panchayatId,bpv.voter.gender ");
				   
				           AgeRangeVO vo=new AgeRangeVO();
				           vo.setAgeRange(ageRange);
				           vo.setQueryString(queryString3);
					       query.add(vo);
					    	
					    }
				      else if((ageRange.contains("<"))&&(!ageRange.contains("=")))
				      {
				    	  
				    	  String str=ageRange;
				    	   str = str.trim();
				    	  Pattern p = Pattern.compile("(\\d+)");
				    	  Matcher m = p.matcher(str);
				    	  Long ageRangeageRange=0l;
				    	  if(m.find())
				    	    ageRangeageRange=Long.parseLong(m.group(1));
				    	  String queryString4 =
					    		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
					    				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender "+
					    		"from BoothPublicationVoter bpv "+
					    		"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
					    		     " and bpv.voter.age<"+ageRangeageRange+"  "+
					    		"group by bpv.booth.panchayat.panchayatId,bpv.voter.gender ");
				   
				           AgeRangeVO vo=new AgeRangeVO();
				           vo.setAgeRange(ageRange);
				           vo.setQueryString(queryString4);
					       query.add(vo);
					    	
					    }
				      else if((ageRange.contains("<"))&&(ageRange.contains("=")))
				      {
				    	  
				    	  String str=ageRange;
				    	   str = str.trim();
				    	  Pattern p = Pattern.compile("(\\d+)");
				    	  Matcher m = p.matcher(str);
				    	  Long ageRangeageRange=0l;
				    	  if(m.find())
				    	    ageRangeageRange=Long.parseLong(m.group(1));
				    	  String queryString5 =
					    		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
					    				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender "+
					    		"from BoothPublicationVoter bpv "+
					    		"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
					    		     " and bpv.voter.age<="+ageRangeageRange+"  "+
					    		"group by bpv.booth.panchayat.panchayatId,bpv.voter.gender ");
				   
				           AgeRangeVO vo=new AgeRangeVO();
				           vo.setAgeRange(ageRange);
				           vo.setQueryString(queryString5);
					       query.add(vo);
					    	
					    }
				      else 
				      {
				    	  
				    	  
				    	  Long ageRangeageRange=Long.parseLong(ageRange);
				    	 
				    	  String queryString6 =
					    		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
					    				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender "+
					    		"from BoothPublicationVoter bpv "+
					    		"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
					    		     " and bpv.voter.age="+ageRangeageRange+"  "+
					    		"group by bpv.booth.panchayat.panchayatId,bpv.voter.gender ");
				   
				           AgeRangeVO vo=new AgeRangeVO();
				           vo.setAgeRange(ageRange);
				           vo.setQueryString(queryString6);
					       query.add(vo);
					    	
					    }
					  
			          }
			  List<AgeRangeVO>  li =printData2( query,false,totalCountMap);
			  return li; 
		  }
		else //partial
		{
			
			
			Set<String> partialIds = new HashSet<String>();
			String partialBoot = (" select pbp.panchayat.panchayatId,pbp.booth.panchayat.panchayatId  "+
			        "from PartialBoothPanchayat pbp "+
			        "where pbp.booth.constituency.constituencyId=232 and pbp.booth.publicationDate.publicationDateId=8 ");
			List<Object[]> par = userDAO.getData1(partialBoot);
			for(Object[] p:par){
			if(p[0] !=null){
			partialIds.add(p[0].toString());
			}
			if(p[1] !=null){
			partialIds.add(p[1].toString());
			}
			}
			String paids = "";
			for(String g:partialIds){
			if(paids.length()==0)
			paids = g;
			else
			paids =paids+","+g;
			}
				
			if(paids.length() == 0){	
				   List<AgeRangeVO>  li=	getPanchayatWiseDetails(constituencyId,publicationId,ageRanges,false);
			       return li;   
			}
			else{
				String queryForTotalCount=("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
	  				    "count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender "+
	  		            "from BoothPublicationVoter bpv "+
	  		            "where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
	  		            "group by bpv.booth.panchayat.panchayatId,bpv.voter.gender  ");
				
			    
			    String	parqueryForTotalCount = 
						("select ph.panchayat.panchayatId,ph.panchayat.panchayatName,count(distinct bpv.voter.voterId),ph.panchayat.tehsil.tehsilName,bpv.voter.gender  "+
						"from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph "+
						"where bpv.voter.voterId=uvd.voter.voterId and uvd.hamlet.hamletId=ph.hamlet.hamletId "+ 
						"   and ph.panchayat.panchayatId in("+paids+") "+
						 "         and bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
						"group by ph.panchayat.panchayatId,bpv.voter.gender  ");
				
				
				

		         Map<Long,AgeRangeVO> totalCountMap=new LinkedHashMap<Long,AgeRangeVO>();
		         List<Object[]> totalCountList = userDAO.getData1(queryForTotalCount);
		         List<Object[]> totalCountList2 = userDAO.getData1(parqueryForTotalCount);
		         
		         if(totalCountList2!=null)
		        	 totalCountList.addAll(totalCountList2);
		          
		          if(totalCountList!=null)
		         {
		           for (Object[] totalCountListObj : totalCountList) {
		           AgeRangeVO ageRangeVOForTotalCount=totalCountMap.get((Long)totalCountListObj[0]);
		           if(ageRangeVOForTotalCount==null) 
		          {
			        ageRangeVOForTotalCount=new AgeRangeVO();
			        ageRangeVOForTotalCount.setPanchayatId((Long)totalCountListObj[0]);//pid
			        ageRangeVOForTotalCount.setPanchayatName(totalCountListObj[1].toString());//pname
			        ageRangeVOForTotalCount.setTehsilName(totalCountListObj[3].toString());//tehsil name.
		            if((Long)totalCountListObj[2]!=null)
			        { 
				      ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+(Long)totalCountListObj[2]);
		              if(totalCountListObj[4].toString()!=null)
		              {
				        if(totalCountListObj[4].toString().equalsIgnoreCase("M") )
					    ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
				        else if(totalCountListObj[4].toString().equalsIgnoreCase("F") )
					    ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
				     }
			        }
			       totalCountMap.put((Long)totalCountListObj[0], ageRangeVOForTotalCount);
		          }
		          else
		   		 {
		   			if((Long)totalCountListObj[2]!=null)
		   			{
		   				ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+(Long)totalCountListObj[2] );
		                if(totalCountListObj[4].toString()!=null){
		   				if(totalCountListObj[4].toString().equalsIgnoreCase("M") )
		   					ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
		   				else
		   					ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
		   			}}
		   		}
		     }
		   }
		          String subString="-";
		      	List<AgeRangeVO>  query=new ArrayList<AgeRangeVO>();
		      	for(String ageRange:ageRanges)
		      	{
			         if(ageRange.contains("-"))
			         {    
			      	    String[] parts = ageRange.split("-");
			      		    String part1 = parts[0]; 
			      		    String part2 = parts[1]; 
						    
						    ////pid,pname,count,tnme. 
					   String queryham1 =
						    		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
						    				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender "+
						    		"from BoothPublicationVoter bpv "+
						    		"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
						    		     " and bpv.voter.age>="+part1+" and bpv.voter.age<="+part2+" and bpv.booth.panchayat.panchayatId not in("+paids+") "+
						    		"group by bpv.booth.panchayat.panchayatId,bpv.voter.gender ");	    
						    
						    
						    
						    
				    String	parqueryham1 = 
							("select ph.panchayat.panchayatId,ph.panchayat.panchayatName,count(distinct bpv.voter.voterId),ph.panchayat.tehsil.tehsilName,bpv.voter.gender  "+
							"from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph "+
							"where bpv.voter.voterId=uvd.voter.voterId and uvd.hamlet.hamletId=ph.hamlet.hamletId "+ 
							"          and bpv.voter.age>="+part1+" and bpv.voter.age<="+part2+" and ph.panchayat.panchayatId in("+paids+") "+
							 "         and bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
							"group by ph.panchayat.panchayatId,bpv.voter.gender  ");

					
					        AgeRangeVO vo=new AgeRangeVO();
					        vo.setAgeRange(ageRange);
					        vo.setQueryString(queryham1);
					        vo.setPartQueryString(parqueryham1);
						     query.add(vo);
						
					     }
					   else if((ageRange.contains(">"))&&(!ageRange.contains("=")))
					   {
						   
						   String str=ageRange;
				    	   str = str.trim();
				    	  Pattern p = Pattern.compile("(\\d+)");
				    	  Matcher m = p.matcher(str);
				    	  Long ageRangeageRange=0l;
				    	  if(m.find())
				    	    ageRangeageRange=Long.parseLong(m.group(1));
				    	    
						   
						  String queryham2 =
						    		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
						    				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender  "+
						    		"from BoothPublicationVoter bpv "+
						    		"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
						    		     " and bpv.voter.age>"+ageRangeageRange+" and bpv.booth.panchayat.panchayatId not in("+paids+") "+
						    		"group by bpv.booth.panchayat.panchayatId,bpv.voter.gender  ");	    
						   
						  String parqueryham2 = 
									("select ph.panchayat.panchayatId,ph.panchayat.panchayatName,count(distinct bpv.voter.voterId),ph.panchayat.tehsil.tehsilName,bpv.voter.gender  "+
									"from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph "+
									"where bpv.voter.voterId=uvd.voter.voterId and uvd.hamlet.hamletId=ph.hamlet.hamletId "+ 
									"          and bpv.voter.age>"+ageRangeageRange+" and ph.panchayat.panchayatId in("+paids+") "+
									 "         and bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
									"group by ph.panchayat.panchayatId,bpv.voter.gender  ");
					   
					        AgeRangeVO vo=new AgeRangeVO();
					        vo.setAgeRange(ageRange);
					        vo.setQueryString(queryham2);
					        vo.setPartQueryString(parqueryham2);
						     query.add(vo);
						    	
						    }
					   else if((ageRange.contains(">"))&&(ageRange.contains("=")))
					   {
						   
						   String str=ageRange;
				    	   str = str.trim();
				    	  Pattern p = Pattern.compile("(\\d+)");
				    	  Matcher m = p.matcher(str);
				    	  Long ageRangeageRange=0l;
				    	  if(m.find())
				    	    ageRangeageRange=Long.parseLong(m.group(1));
				    	    
						   
						  String queryham3 =
						    		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
						    				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender  "+
						    		"from BoothPublicationVoter bpv "+
						    		"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
						    		     " and bpv.voter.age>="+ageRangeageRange+" and bpv.booth.panchayat.panchayatId not in("+paids+") "+
						    		"group by bpv.booth.panchayat.panchayatId,bpv.voter.gender  ");	    
						   
						  String parqueryham3 = 
									("select ph.panchayat.panchayatId,ph.panchayat.panchayatName,count(distinct bpv.voter.voterId),ph.panchayat.tehsil.tehsilName,bpv.voter.gender  "+
									"from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph "+
									"where bpv.voter.voterId=uvd.voter.voterId and uvd.hamlet.hamletId=ph.hamlet.hamletId "+ 
									"          and bpv.voter.age>="+ageRangeageRange+" and ph.panchayat.panchayatId in("+paids+") "+
									 "         and bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
									"group by ph.panchayat.panchayatId,bpv.voter.gender  ");
					   
					        AgeRangeVO vo=new AgeRangeVO();
					        vo.setAgeRange(ageRange);
					        vo.setQueryString(queryham3);
					        vo.setPartQueryString(parqueryham3);
						     query.add(vo);
						    	
						    }
					   else if((ageRange.contains("<"))&&(!ageRange.contains("=")))
					   {
						   
						   String str=ageRange;
				    	   str = str.trim();
				    	  Pattern p = Pattern.compile("(\\d+)");
				    	  Matcher m = p.matcher(str);
				    	  Long ageRangeageRange=0l;
				    	  if(m.find())
				    	    ageRangeageRange=Long.parseLong(m.group(1));
				    	    
						   
						  String queryham4 =
						    		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
						    				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender  "+
						    		"from BoothPublicationVoter bpv "+
						    		"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
						    		     " and bpv.voter.age<"+ageRangeageRange+" and bpv.booth.panchayat.panchayatId not in("+paids+") "+
						    		"group by bpv.booth.panchayat.panchayatId,bpv.voter.gender  ");	    
						   
						  String parqueryham4 = 
									("select ph.panchayat.panchayatId,ph.panchayat.panchayatName,count(distinct bpv.voter.voterId),ph.panchayat.tehsil.tehsilName,bpv.voter.gender  "+
									"from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph "+
									"where bpv.voter.voterId=uvd.voter.voterId and uvd.hamlet.hamletId=ph.hamlet.hamletId "+ 
									"          and bpv.voter.age<"+ageRangeageRange+" and ph.panchayat.panchayatId in("+paids+") "+
									 "         and bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
									"group by ph.panchayat.panchayatId,bpv.voter.gender  ");
					   
					        AgeRangeVO vo=new AgeRangeVO();
					        vo.setAgeRange(ageRange);
					        vo.setQueryString(queryham4);
					        vo.setPartQueryString(parqueryham4);
						     query.add(vo);
						    	
						    }
					   else if((ageRange.contains("<"))&&(ageRange.contains("=")))
					   {
						   
						   String str=ageRange;
				    	   str = str.trim();
				    	  Pattern p = Pattern.compile("(\\d+)");
				    	  Matcher m = p.matcher(str);
				    	  Long ageRangeageRange=0l;
				    	  if(m.find())
				    	    ageRangeageRange=Long.parseLong(m.group(1));
				    	    
						   
						  String queryham5 =
						    		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
						    				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender  "+
						    		"from BoothPublicationVoter bpv "+
						    		"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
						    		     " and bpv.voter.age<="+ageRangeageRange+" and bpv.booth.panchayat.panchayatId not in("+paids+") "+
						    		"group by bpv.booth.panchayat.panchayatId,bpv.voter.gender  ");	    
						   
						  String parqueryham5 = 
									("select ph.panchayat.panchayatId,ph.panchayat.panchayatName,count(distinct bpv.voter.voterId),ph.panchayat.tehsil.tehsilName,bpv.voter.gender  "+
									"from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph "+
									"where bpv.voter.voterId=uvd.voter.voterId and uvd.hamlet.hamletId=ph.hamlet.hamletId "+ 
									"          and bpv.voter.age<="+ageRangeageRange+" and ph.panchayat.panchayatId in("+paids+") "+
									 "         and bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
									"group by ph.panchayat.panchayatId,bpv.voter.gender  ");
					   
					        AgeRangeVO vo=new AgeRangeVO();
					        vo.setAgeRange(ageRange);
					        vo.setQueryString(queryham5);
					        vo.setPartQueryString(parqueryham5);
						     query.add(vo);
						    	
						    }
					   else 
					   {
						 
				    	  Long ageRangeageRange=Long.parseLong(ageRange);
				    	  String queryham6 =
						    		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
						    				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender  "+
						    		"from BoothPublicationVoter bpv "+
						    		"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
						    		     " and bpv.voter.age="+ageRangeageRange+" and bpv.booth.panchayat.panchayatId not in("+paids+") "+
						    		"group by bpv.booth.panchayat.panchayatId,bpv.voter.gender  ");	    
						   
						  String parqueryham6 = 
									("select ph.panchayat.panchayatId,ph.panchayat.panchayatName,count(distinct bpv.voter.voterId),ph.panchayat.tehsil.tehsilName,bpv.voter.gender  "+
									"from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph "+
									"where bpv.voter.voterId=uvd.voter.voterId and uvd.hamlet.hamletId=ph.hamlet.hamletId "+ 
									"          and bpv.voter.age="+ageRangeageRange+" and ph.panchayat.panchayatId in("+paids+") "+
									 "         and bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
									"group by ph.panchayat.panchayatId,bpv.voter.gender  ");
					   
					        AgeRangeVO vo=new AgeRangeVO();
					        vo.setAgeRange(ageRange);
					        vo.setQueryString(queryham6);
					        vo.setPartQueryString(parqueryham6);
						     query.add(vo);
						    	
						    }
					 }	    
			List<AgeRangeVO>  li =printData2( query,true,totalCountMap);
			return li;
		}
			
	   }//partial
		    
		    
}//method

public List<AgeRangeVO> printData2(List<AgeRangeVO> query,boolean partial,Map<Long,AgeRangeVO> totalCountMap)
{
List<AgeRangeVO> li =null;

Map<Long,AgeRangeVO> panchayatMap=new LinkedHashMap<Long, AgeRangeVO>();
for(Map.Entry<Long,AgeRangeVO>  totalCountMapEntrySet :totalCountMap.entrySet() )
	  {
	       Long panchayatId = totalCountMapEntrySet.getKey();
	       AgeRangeVO  totalCountMapVO=totalCountMapEntrySet.getValue();
	       
	       AgeRangeVO  panchayatMapVO= panchayatMap.get(panchayatId);
	       if(panchayatMapVO==null)
	       {  panchayatMapVO=new AgeRangeVO();
	          panchayatMapVO.setTotalCount(totalCountMapVO.getTotalCount());
	          panchayatMapVO.setTotalMaleCount(totalCountMapVO.getTotalMaleCount());
	          panchayatMapVO.setTotalFemaleCount(totalCountMapVO.getTotalFemaleCount());
	          panchayatMapVO.setPanchayatId(totalCountMapVO.getPanchayatId());
	          panchayatMapVO.setPanchayatName(totalCountMapVO.getPanchayatName());
	          panchayatMapVO.setTehsilName(totalCountMapVO.getTehsilName());
			  Map<String,AgeRangeVO> m=new LinkedHashMap<String,AgeRangeVO>();
			  panchayatMapVO.setMap(m);
			  panchayatMap.put(panchayatId, panchayatMapVO);
	       }
	      }


List<AgeRangeVO> output=new ArrayList<AgeRangeVO>();
if(query!=null)
{
for(AgeRangeVO   vo    :query)
{
  List<Object[]> list1 = userDAO.getData1(vo.getQueryString());
  List<Object[]> list2 =null;
  if(partial){
   list2 = userDAO.getData1(vo.getPartQueryString());
  }
  AgeRangeVO vo1=new AgeRangeVO();
  vo1.setAgeRange(vo.getAgeRange());
  vo1.setOutput(list1);
  if(partial){
  vo1.setOutput1(list2);
  } 
  output.add(vo1);
}
}

if(output!=null)
{
for(AgeRangeVO param:output){
 List<Object[]> list=param.getOutput();
 iterateLogic(panchayatMap,param,list);
 if(partial){
 List<Object[]> list1=param.getOutput1();
 iterateLogic(panchayatMap,param,list1);
 }
}
}
li=gettingPercantage(panchayatMap);
return li;

}

public List<AgeRangeVO> gettingPercantage(Map<Long,AgeRangeVO> particularMap) 
{
	for(Map.Entry<Long,AgeRangeVO>  ageRangeVO :particularMap.entrySet() )
	{
		DecimalFormat df = new DecimalFormat("##.##");

		AgeRangeVO outerVO=ageRangeVO.getValue();
	    Long totalVoters=outerVO.getTotalCount();
	    Map<String,AgeRangeVO> innerMap         =outerVO.getMap();
	    for(Map.Entry<String,AgeRangeVO>  innerMapEntrySet :innerMap.entrySet() )
	    {
		  AgeRangeVO innerVO=innerMapEntrySet.getValue();
		    Long particularAgeCount = innerVO.getParticularAgeVotersCount();
		 
		    double d   =((double)particularAgeCount*100)/totalVoters;
		    innerVO.setAgeRangePerc(( Double.parseDouble (df.format( d) ) ));
	    }
 }
List<AgeRangeVO>  li = new ArrayList<AgeRangeVO>(particularMap.values());
return li;
}

public void iterateLogic(Map<Long,AgeRangeVO> panchayatMap,AgeRangeVO param, List<Object[]> list) {
if(list.size()>0){
	  for(Object[] obj:list){
		 long l=(Long)obj[0];
		  AgeRangeVO vo = panchayatMap.get((Long)obj[0]);
		   //pid,pname,count,tnme,gender. -->0,1,2,3,4.
		   
		   if(obj[2]!=null)
		   {
			     String ageRange =param.getAgeRange();
			     for(Map.Entry<Long,AgeRangeVO>  ageRangeVO :panchayatMap.entrySet() )
			     {
			    	 AgeRangeVO vo1 = ageRangeVO.getValue();
			    	 Long panchayatId = ageRangeVO.getKey();
			    	  Map<String,AgeRangeVO>   m =vo1.getMap();
			    	     AgeRangeVO particularAge  =m.get(ageRange);
			    	     long a=(Long)obj[0];
			    	     long b=vo1.getPanchayatId();
			    	    if(a==b){
			    	     if(particularAge==null)
			    	     {
			    	    	 particularAge =new AgeRangeVO();
			    	    	 particularAge.setParticularAgeVotersCount ((particularAge.getParticularAgeVotersCount())+ ((Long)obj[2]) );
			    	    	 particularAge.setPanchayatId(panchayatId);
			    	    	 if(obj[4]!=null)
				    	       {
				    	    	   if(obj[4].toString().equalsIgnoreCase("M"))
				    	    	      particularAge.setMaleCount(Long.parseLong(obj[2].toString()));
				    	    		   
				    	    	   if(obj[4].toString().equalsIgnoreCase("F"))
					    	    	      particularAge.setFemaleCount(Long.parseLong(obj[2].toString()));
					    	    		      
				    	    }
			    	    	m.put(ageRange, particularAge);
			    	    	
			    	       }
			    	     else
			    	     {
			    	    	long panchayatIdd=particularAge.getPanchayatId();
			    	    	if(panchayatIdd==l ) 
			    	    	{		
			    	         particularAge.setParticularAgeVotersCount(( particularAge.getParticularAgeVotersCount())+ ((Long)obj[2]) );
			    	       	
				    	       if(obj[4]!=null)
				    	       {
				    	    	   if(obj[4].toString().equalsIgnoreCase("M"))
				    	    	      particularAge.setMaleCount(Long.parseLong(obj[2].toString()));
				    	    		   
				    	    	   if(obj[4].toString().equalsIgnoreCase("F"))
					    	    	      particularAge.setFemaleCount(Long.parseLong(obj[2].toString()));
					    	    		      
				    	    	}
			    	         } 
			    	      }	 
			    	    }//if 	 
			      }//for
		   }//if
			   
}//for
 }//if
}

//end getPanchayatWiseForAConstituency.

//mandalwise details. 
public List<AgeRangeVO> getMandalWiseForAConstituency(Long constituencyId,Long publicationId,List<String> ageRanges)
{
	if(ageRanges==null || ageRanges.size() == 0 ||ageRanges.get(0).isEmpty())
	{
		ageRanges=new ArrayList<String>();
		ageRanges.add("18-22");
		ageRanges.add("23-25");
		ageRanges.add("26-35");
		ageRanges.add("36-45");
		ageRanges.add("46-60");
		ageRanges.add(">60");
	}
	 List<AgeRangeVO> li= generateQueries(constituencyId,publicationId,ageRanges,"mandal");
	   return li;
} 


  
//munc wise details.  
public List<AgeRangeVO> getMunicipalityWiseForAConstituency(Long constituencyId,Long publicationId,List<String> ageRanges)
{
	if(ageRanges==null || ageRanges.size() == 0 ||ageRanges.get(0).isEmpty())
	{
		ageRanges=new ArrayList<String>();
		ageRanges.add("18-22");
		ageRanges.add("23-25");
		ageRanges.add("26-35");
		ageRanges.add("36-45");
		ageRanges.add("46-60");
		ageRanges.add(">60");
	}
	 List<AgeRangeVO> li= generateQueries(constituencyId,publicationId,ageRanges,"municpality");
     return li;
}
public List<AgeRangeVO> generateQueries(Long constituencyId,Long publicationId,List<String> ageRanges,String type)
{
	Map<Long,AgeRangeVO> totalCountMap=new LinkedHashMap<Long,AgeRangeVO>();
	if(type=="mandal")
	{
		String  queryForTotalCount=" select bpv.booth.tehsil.tehsilId, bpv.booth.tehsil.tehsilName,count(distinct bpv.voter.voterId),bpv.voter.gender "+
		    		 "	from BoothPublicationVoter bpv "+
		    		 "	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
		    		 " and bpv.booth.localBody.localElectionBodyId is null "+
		    		 "	group by bpv.booth.tehsil.tehsilId,bpv.voter.gender";
	
	
	
	
	 List<Object[]> totalCountList = userDAO.getData1(queryForTotalCount);
	  
	 if(totalCountList!=null)
	 {
	 for (Object[] totalCountListObj : totalCountList) {
		 AgeRangeVO   ageRangeVOForTotalCount=totalCountMap.get((Long)totalCountListObj[0]);
	 if(ageRangeVOForTotalCount==null) 
		{
			ageRangeVOForTotalCount=new AgeRangeVO();
			ageRangeVOForTotalCount.setTehsilId((Long)totalCountListObj[0]);
			ageRangeVOForTotalCount.setTehsilName(totalCountListObj[1].toString());
			if((Long)totalCountListObj[2]!=null)
			{ 
				ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+(Long)totalCountListObj[2]);
                if(totalCountListObj[3].toString()!=null)
                {
				  if(totalCountListObj[3].toString().equalsIgnoreCase("M") )
					ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
				 else if(totalCountListObj[3].toString().equalsIgnoreCase("F") )
					 ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
				}
			}
			totalCountMap.put((Long)totalCountListObj[0], ageRangeVOForTotalCount);
			
		}
		else
		{
			if((Long)totalCountListObj[2]!=null)
			{
				  ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+(Long)totalCountListObj[2] );

				  if(totalCountListObj[3].toString()!=null){
				if(totalCountListObj[3].toString().equalsIgnoreCase("M") )
					ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
				else
					ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
			}}
		}
		
     }
}}
	
	if(type=="municpality")
	{
	  String	queryForTotalCount=(" select bpv.booth.localBody.localElectionBodyId , bpv.booth.localBody.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
			"	from BoothPublicationVoter bpv "+
			"	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
			" and bpv.booth.localBody.localElectionBodyId is not null "+
			" group by bpv.booth.localBody.localElectionBodyId,bpv.voter.gender ");	
	
	
	List<Object[]> totalCountList = userDAO.getData1(queryForTotalCount);
	 for (Object[] totalCountListObj : totalCountList) {
		 AgeRangeVO   ageRangeVOForTotalCount=totalCountMap.get((Long)totalCountListObj[0]);
		
		if(ageRangeVOForTotalCount==null) 
		{
			ageRangeVOForTotalCount=new AgeRangeVO();
			ageRangeVOForTotalCount.setTehsilId((Long)totalCountListObj[0]);
			ageRangeVOForTotalCount.setTehsilName(totalCountListObj[1].toString());
			ageRangeVOForTotalCount.setPanchayatName(totalCountListObj[1].toString());
			if((Long)totalCountListObj[2]!=null)
			{
				ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+ (Long)totalCountListObj[2]);

				if(totalCountListObj[3].toString()!=null)
				{
				 if(totalCountListObj[3].toString().equalsIgnoreCase("M") )
					ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
				else
					ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
			     }
			}
	    totalCountMap.put((Long)totalCountListObj[0], ageRangeVOForTotalCount);
			
		}
		else
		{
			if((Long)totalCountListObj[2]!=null)
			{
			   ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+ (Long)totalCountListObj[2]);

			    if(totalCountListObj[3].toString()!=null)
				{
				 if(totalCountListObj[3].toString().equalsIgnoreCase("M") )
					ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
				 else
					ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
			    }
		      }
		
         }
}
}
	
	List<AgeRangeVO>  query=new ArrayList<AgeRangeVO>();
	for(String ageRange:ageRanges)
	{
		   if(ageRange.contains("-"))
		   {    
			        String[] parts = ageRange.split("-");
				    String part1 = parts[0]; 
				    String part2 = parts[1]; 
					
					String	queryString1=null;
					if(type.equalsIgnoreCase("mandal"))
					{
				    	queryString1 = (" select bpv.booth.tehsil.tehsilId, bpv.booth.tehsil.tehsilName,count(distinct bpv.voter.voterId),bpv.voter.gender "+
				    		 "	from BoothPublicationVoter bpv "+
				    		 "	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
				    		 " and bpv.voter.age>="+part1+" and bpv.voter.age<="+part2+"  and bpv.booth.localBody.localElectionBodyId is null "+
				    		 "		group by bpv.booth.tehsil.tehsilId,bpv.voter.gender");
				    
				   }
				   else if(type.equalsIgnoreCase("municpality"))
				   {
				      queryString1=	
							(" select bpv.booth.localBody.localElectionBodyId , bpv.booth.localBody.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
							"	from BoothPublicationVoter bpv "+
							"	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
							" and bpv.voter.age>="+part1+" and bpv.voter.age<="+part2+" and bpv.booth.localBody.localElectionBodyId is not null "+
							" group by bpv.booth.localBody.localElectionBodyId,bpv.voter.gender ");	
				   
				   } 
				   
			        AgeRangeVO vo=new AgeRangeVO();
			        vo.setAgeRange(ageRange);
			        vo.setQueryString(queryString1);
				     query.add(vo);
				
		 }
		  else if( (ageRange.contains(">")) && (!ageRange.contains("=")))
		  {
				      
				   String queryString2=null;
				   String str=ageRange;
			   	   str = str.trim();
			   	  Pattern p = Pattern.compile("(\\d+)");
			   	  Matcher m = p.matcher(str);
			   	  Long ageRangeageRange=0l;
			   	  if(m.find())
			   	    ageRangeageRange=Long.parseLong(m.group(1));
			   	    
				   if(type.equalsIgnoreCase("mandal"))
					{
				     queryString2= 	
						   (" select bpv.booth.tehsil.tehsilId, bpv.booth.tehsil.tehsilName,count(distinct bpv.voter.voterId),bpv.voter.gender "+
						   		 "	from BoothPublicationVoter bpv "+
						   		 "	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
						   		 " and bpv.voter.age>"+ageRangeageRange+" and bpv.booth.localBody.localElectionBodyId is null "+
						   		 "		group by bpv.booth.tehsil.tehsilId,bpv.voter.gender");	
						   	
			        }
					  else if(type.equalsIgnoreCase("municpality"))
				   {
				       queryString2=	
							(" select bpv.booth.localBody.localElectionBodyId , bpv.booth.localBody.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
							"	from BoothPublicationVoter bpv "+
							"	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
							" and bpv.voter.age>"+ageRangeageRange+" and bpv.booth.localBody.localElectionBodyId is not null "+
							"		group by bpv.booth.localBody.localElectionBodyId,bpv.voter.gender ");	
				   
				   
				    }
					 
			        AgeRangeVO vo=new AgeRangeVO();
			        vo.setAgeRange(ageRange);
			        vo.setQueryString(queryString2);
				     query.add(vo);
				    	
			}
		  else if( (ageRange.contains("<")) && (!ageRange.contains("=")) )
		  {
			   String queryString3=null;
			   String str=ageRange;
		   	   str = str.trim();
		   	  Pattern p = Pattern.compile("(\\d+)");
		   	  Matcher m = p.matcher(str);
		   	  Long ageRangeageRange=0l;
		   	  if(m.find())
		   	    ageRangeageRange=Long.parseLong(m.group(1));
		   	    
			   if(type.equalsIgnoreCase("mandal"))
				{
			     queryString3= 	
					   (" select bpv.booth.tehsil.tehsilId, bpv.booth.tehsil.tehsilName,count(distinct bpv.voter.voterId),bpv.voter.gender "+
					   		 "	from BoothPublicationVoter bpv "+
					   		 "	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
					   		 " and bpv.voter.age<"+ageRangeageRange+" and bpv.booth.localBody.localElectionBodyId is null "+
					   		 "		group by bpv.booth.tehsil.tehsilId,bpv.voter.gender");	
					   	
		        }
				  else if(type.equalsIgnoreCase("municpality"))
			   {
			       queryString3=	
						(" select bpv.booth.localBody.localElectionBodyId , bpv.booth.localBody.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
						"	from BoothPublicationVoter bpv "+
						"	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
						" and bpv.voter.age<"+ageRangeageRange+" and bpv.booth.localBody.localElectionBodyId is not null "+
						"		group by bpv.booth.localBody.localElectionBodyId,bpv.voter.gender ");	
			   
			   
			    }
				 
		        AgeRangeVO vo=new AgeRangeVO();
		        vo.setAgeRange(ageRange);
		        vo.setQueryString(queryString3);
			     query.add(vo); 
			  
		}
		  else if( (ageRange.contains(">")) && (ageRange.contains("=")) )
		  {
			  String queryString4=null;
			   String str=ageRange;
		  	   str = str.trim();
		  	  Pattern p = Pattern.compile("(\\d+)");
		  	  Matcher m = p.matcher(str);
		  	  Long ageRangeageRange=0l;
		  	  if(m.find())
		  	    ageRangeageRange=Long.parseLong(m.group(1));
		  	    
			   if(type.equalsIgnoreCase("mandal"))
				{
			     queryString4= 	
					   (" select bpv.booth.tehsil.tehsilId, bpv.booth.tehsil.tehsilName,count(distinct bpv.voter.voterId),bpv.voter.gender "+
					   		 "	from BoothPublicationVoter bpv "+
					   		 "	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
					   		 " and bpv.voter.age>="+ageRangeageRange+" and bpv.booth.localBody.localElectionBodyId is null "+
					   		 "		group by bpv.booth.tehsil.tehsilId,bpv.voter.gender");	
					   	
		       }
				  else if(type.equalsIgnoreCase("municpality"))
			   {
			       queryString4=	
						(" select bpv.booth.localBody.localElectionBodyId , bpv.booth.localBody.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
						"	from BoothPublicationVoter bpv "+
						"	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
						" and bpv.voter.age>="+ageRangeageRange+" and bpv.booth.localBody.localElectionBodyId is not null "+
						"		group by bpv.booth.localBody.localElectionBodyId,bpv.voter.gender ");	
			   
			   
			    }
				 
		       AgeRangeVO vo=new AgeRangeVO();
		       vo.setAgeRange(ageRange);
		       vo.setQueryString(queryString4);
			     query.add(vo);   
		}
		  else if( (ageRange.contains("<")) && (ageRange.contains("=")) )
		  { 
			  String queryString5=null;
			   String str=ageRange;
		 	   str = str.trim();
		 	  Pattern p = Pattern.compile("(\\d+)");
		 	  Matcher m = p.matcher(str);
		 	  Long ageRangeageRange=0l;
		 	  if(m.find())
		 	    ageRangeageRange=Long.parseLong(m.group(1));
		 	    
			   if(type.equalsIgnoreCase("mandal"))
				{
			     queryString5= 	
					   (" select bpv.booth.tehsil.tehsilId, bpv.booth.tehsil.tehsilName,count(distinct bpv.voter.voterId),bpv.voter.gender "+
					   		 "	from BoothPublicationVoter bpv "+
					   		 "	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
					   		 " and bpv.voter.age<="+ageRangeageRange+" and bpv.booth.localBody.localElectionBodyId is null "+
					   		 "		group by bpv.booth.tehsil.tehsilId,bpv.voter.gender");	
					   	
		      }
				  else if(type.equalsIgnoreCase("municpality"))
			   {
			       queryString5=	
						(" select bpv.booth.localBody.localElectionBodyId , bpv.booth.localBody.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
						"	from BoothPublicationVoter bpv "+
						"	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
						" and bpv.voter.age<="+ageRangeageRange+" and bpv.booth.localBody.localElectionBodyId is not null "+
						"		group by bpv.booth.localBody.localElectionBodyId,bpv.voter.gender ");	
			   
			   
			    }
				 
		      AgeRangeVO vo=new AgeRangeVO();
		      vo.setAgeRange(ageRange);
		      vo.setQueryString(queryString5);
			     query.add(vo);   
		}
		  else { 
			  String queryString6=null;
			  Long ageRangeageRange=Long.parseLong(ageRange);
			  if(type.equalsIgnoreCase("mandal"))
				{
			      queryString6= 	
					   (" select bpv.booth.tehsil.tehsilId, bpv.booth.tehsil.tehsilName,count(distinct bpv.voter.voterId),bpv.voter.gender "+
					   		 "	from BoothPublicationVoter bpv "+
					   		 "	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
					   		 " and bpv.voter.age="+ageRangeageRange+" and bpv.booth.localBody.localElectionBodyId is null "+
					   		 "		group by bpv.booth.tehsil.tehsilId,bpv.voter.gender");	
					   	
		        }
				  else if(type.equalsIgnoreCase("municpality"))
			   {
			       queryString6=	
						(" select bpv.booth.localBody.localElectionBodyId , bpv.booth.localBody.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
						"	from BoothPublicationVoter bpv "+
						"	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
						" and bpv.voter.age="+ageRangeageRange+" and bpv.booth.localBody.localElectionBodyId is not null "+
						"		group by bpv.booth.localBody.localElectionBodyId,bpv.voter.gender ");	
			   
			   
			    }
				 
		    AgeRangeVO vo=new AgeRangeVO();
		    vo.setAgeRange(ageRange);
		    vo.setQueryString(queryString6);
			     query.add(vo);   
		}
}	    
 List<AgeRangeVO>  li =printData1( query,totalCountMap);
return li;
	
}
public List<AgeRangeVO> printData1(List<AgeRangeVO> query,Map<Long,AgeRangeVO> totalCountMap )
{
	 Map<Long,AgeRangeVO> tehsilMap=new LinkedHashMap<Long, AgeRangeVO>();
	 for(Map.Entry<Long,AgeRangeVO>  totalCountMapEntrySet :totalCountMap.entrySet() )
	  {
	       Long tehsilId = totalCountMapEntrySet.getKey();
	       AgeRangeVO  totalCountMapVO=totalCountMapEntrySet.getValue();
	       
	       AgeRangeVO  tehsilMapVO= tehsilMap.get(tehsilId);
	       if(tehsilMapVO==null)
	       {  tehsilMapVO=new AgeRangeVO();
	    	  tehsilMapVO.setTotalCount(totalCountMapVO.getTotalCount());
	          tehsilMapVO.setTotalMaleCount(totalCountMapVO.getTotalMaleCount());
	          tehsilMapVO.setTotalFemaleCount(totalCountMapVO.getTotalFemaleCount());
	          tehsilMapVO.setTehsilId(totalCountMapVO.getTehsilId());
	          tehsilMapVO.setTehsilName(totalCountMapVO.getTehsilName());
	          tehsilMapVO.setPanchayatName(totalCountMapVO.getTehsilName());
			  Map<String,AgeRangeVO> m=new LinkedHashMap<String,AgeRangeVO>();
			  tehsilMapVO.setMap(m);
	          tehsilMap.put(tehsilId, tehsilMapVO);
	       }
	      
	   }
       
	List<AgeRangeVO> li =null;
List<AgeRangeVO> output=new ArrayList<AgeRangeVO>();
  if(query!=null)
  {
    for(AgeRangeVO   vo    :query)
    {
	   List<Object[]> list = userDAO.getData1(vo.getQueryString());
	   AgeRangeVO vo1=new AgeRangeVO();
	   vo1.setAgeRange(vo.getAgeRange());
	   vo1.setOutput(list);
	   output.add(vo1);
     }
    }
  
  for(AgeRangeVO param:output){
	  List<Object[]> list=param.getOutput();
	  if(list.size()>0){
		  for(Object[] obj:list){ //iteration starts.
			 long l=(Long)obj[0];
			  AgeRangeVO vo = tehsilMap.get((Long)obj[0]);
			//0-tid,1-tname,2-count,3-gender
			//0-lebid,1-muname,2-count,3-gender.
			   
			   if(obj[2]!=null)
			   {
				     String ageRange =param.getAgeRange();
				     for(Map.Entry<Long,AgeRangeVO>  ageRangeVO :tehsilMap.entrySet() )
				     {
				    	 AgeRangeVO vo1 = ageRangeVO.getValue();
				    	 Long tehsilId = ageRangeVO.getKey();
				    	  Map<String,AgeRangeVO>   m =vo1.getMap();
				    	    
				    	     AgeRangeVO particularAge  =m.get(ageRange);  //checking for particular  agerangevo.
				    	     long a=(Long)obj[0];
				    	     long b=vo1.getTehsilId();
				    	     if(a==b){
				    	     if(particularAge==null)
				    	     {
				    	    	 particularAge =new AgeRangeVO();
				    	    	 particularAge.setParticularAgeVotersCount(( particularAge.getParticularAgeVotersCount())+ ((Long)obj[2]) );
				    	    	 particularAge.setTehsilId(tehsilId);
				    	    	 if(obj[3]!=null)
					    	       {
					    	    	   if(obj[3].toString().equalsIgnoreCase("M"))
					    	    	      particularAge.setMaleCount(Long.parseLong(obj[2].toString()));
					    	    		   
					    	    	   if(obj[3].toString().equalsIgnoreCase("F"))
						    	    	      particularAge.setFemaleCount(Long.parseLong(obj[2].toString()));
						    	    		      
					    	    	   
					    	       }
				    	    	  m.put(ageRange, particularAge);
				    	    	  
				    	    	  
				    	    	 //vo.setTotalCount(vo.getTotalCount()+(Long)obj[2]);
				    	    	 
				    	    	 
				    	    	 
				    	     }
				    	     else
				    	     {
				    	    	long tehsilIdd=particularAge.getTehsilId();
				    	    	if(tehsilIdd==l ) 
				    	    	{		
					    	    	 particularAge.setParticularAgeVotersCount(( particularAge.getParticularAgeVotersCount())+ ((Long)obj[2]) );
				    	       if(obj[3]!=null)
				    	       {
				    	    	   if(obj[3].toString().equalsIgnoreCase("M"))
				    	    	      particularAge.setMaleCount(Long.parseLong(obj[2].toString()));
				    	    		   
				    	    	   if(obj[3].toString().equalsIgnoreCase("F"))
					    	    	      particularAge.setFemaleCount(Long.parseLong(obj[2].toString()));
					    	    		      
				    	    	   
				    	       }
				    	       //vo.setTotalCount(vo.getTotalCount()+(Long)obj[2]);
				    	     } 
				    	    	 
				    	     }	 
				    	     }//if 	 
				      }//for
			   }//if
				   
				   
				   
	}//for
	  }
  }
li= gettingPercantage(tehsilMap);
return li;                                          
 }

public List<AgeRangeVO> getBoothWiseForAConstituency(Long constituencyId,Long publicationId,List<String> ageRanges)
{
	if(ageRanges==null || ageRanges.size() == 0 ||ageRanges.get(0).isEmpty())
	{
		ageRanges=new ArrayList<String>();
		ageRanges.add("18-22");
		ageRanges.add("23-25");
		ageRanges.add("26-35");
		ageRanges.add("36-45");
		ageRanges.add("46-60");
		ageRanges.add(">60");
	}
String	queryForTotalCount=	
				("	select bpv.booth.boothId,bpv.booth.partNo,count(bpv.voter.voterId),bpv.booth.panchayat.panchayatName,bpv.voter.gender "+
				"from BoothPublicationVoter bpv "+
				"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" " +
				"and bpv.booth.localBody.localElectionBodyId is null  "+
				"group by bpv.booth.boothId,bpv.voter.gender ");	

     Map<Long,AgeRangeVO> totalCountMap=new LinkedHashMap<Long,AgeRangeVO>();
     List<Object[]> totalCountList = userDAO.getData1(queryForTotalCount);
     if(totalCountList!=null)
     {
       for (Object[] totalCountListObj : totalCountList) {
       AgeRangeVO ageRangeVOForTotalCount=totalCountMap.get((Long)totalCountListObj[0]);
       if(ageRangeVOForTotalCount==null) 
      {
        ageRangeVOForTotalCount=new AgeRangeVO();
        ageRangeVOForTotalCount.setPanchayatId((Long)totalCountListObj[0]);//boothid
        ageRangeVOForTotalCount.setPanchayatName(totalCountListObj[1].toString());//partno
        ageRangeVOForTotalCount.setTehsilName(totalCountListObj[3].toString());//panchayatname.
        if((Long)totalCountListObj[2]!=null)
        { 
	      ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+(Long)totalCountListObj[2]);
          if(totalCountListObj[4].toString()!=null)
          {
	        if(totalCountListObj[4].toString().equalsIgnoreCase("M") )
		    ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
	        else if(totalCountListObj[4].toString().equalsIgnoreCase("F") )
		    ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
	     }
        }
       totalCountMap.put((Long)totalCountListObj[0], ageRangeVOForTotalCount);
      }
      else
		 {
			if((Long)totalCountListObj[2]!=null)
			{
				ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+(Long)totalCountListObj[2] );
            if(totalCountListObj[4].toString()!=null){
				if(totalCountListObj[4].toString().equalsIgnoreCase("M") )
					ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
				else
					ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
			}}
		}
 }
}	
	
   
List<AgeRangeVO>  query=new ArrayList<AgeRangeVO>();
 	for(String ageRange:ageRanges)
 	{
 		if(ageRange.contains("-"))
 		{    
 		    String[] parts = ageRange.split("-");
 			    String part1 = parts[0]; 
 			    String part2 = parts[1]; 
 				    
 				    String	queryString=	
 							("	select bpv.booth.boothId,bpv.booth.partNo,count(bpv.voter.voterId),bpv.booth.panchayat.panchayatName,bpv.voter.gender "+
 							"from BoothPublicationVoter bpv "+
 							"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" " +
 							"and bpv.booth.localBody.localElectionBodyId is null and bpv.voter.age>="+part1+" and bpv.voter.age<="+part2+" "+
 							"group by bpv.booth.boothId,bpv.voter.gender ");	
 									
 			 
 		   AgeRangeVO vo=new AgeRangeVO();
 		   vo.setAgeRange(ageRange);
 		   vo.setQueryString(queryString);
 		     query.add(vo);

 		  }
 		else if((ageRange.contains(">"))&&(!ageRange.contains("=")))
 		{
 		  
 		  String str=ageRange;
 		   str = str.trim();
 		  Pattern p = Pattern.compile("(\\d+)");
 		  Matcher m = p.matcher(str);
 		  Long ageRangeageRange=0l;
 		  if(m.find())
 		    ageRangeageRange=Long.parseLong(m.group(1));
 		  

 		  
 		    String	queryString2=	
 					("	select bpv.booth.boothId,bpv.booth.partNo,count(bpv.voter.voterId),bpv.booth.panchayat.panchayatName,bpv.voter.gender "+
 					"from BoothPublicationVoter bpv "+
 					"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" " +
 					"and bpv.booth.localBody.localElectionBodyId is null and bpv.voter.age>"+ageRangeageRange+"  "+
 					"group by bpv.booth.boothId,bpv.voter.gender ");	
 		    
 		  
 		    AgeRangeVO vo=new AgeRangeVO();
 		      vo.setAgeRange(ageRange);
 		      vo.setQueryString(queryString2);
 		       query.add(vo);
 		    	
 		}
 		else if((ageRange.contains(">"))&&(ageRange.contains("=")))
 		{
 		  
 		  String str=ageRange;
 		   str = str.trim();
 		  Pattern p = Pattern.compile("(\\d+)");
 		  Matcher m = p.matcher(str);
 		  Long ageRangeageRange=0l;
 		  if(m.find())
 		    ageRangeageRange=Long.parseLong(m.group(1));
 		  
 		    String	queryString3=	
 					("	select bpv.booth.boothId,bpv.booth.partNo,count(bpv.voter.voterId),bpv.booth.panchayat.panchayatName,bpv.voter.gender "+
 					"from BoothPublicationVoter bpv "+
 					"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" " +
 					"and bpv.booth.localBody.localElectionBodyId is null and bpv.voter.age>="+ageRangeageRange+"  "+
 					"group by bpv.booth.boothId,bpv.voter.gender ");	
 		    
 		  
 		    AgeRangeVO vo=new AgeRangeVO();
 		      vo.setAgeRange(ageRange);
 		      vo.setQueryString(queryString3);
 		       query.add(vo);
 		    	
 		}
 		else if((ageRange.contains("<"))&&(!ageRange.contains("=")))
 		{
 		  
 		  String str=ageRange;
 		   str = str.trim();
 		  Pattern p = Pattern.compile("(\\d+)");
 		  Matcher m = p.matcher(str);
 		  Long ageRangeageRange=0l;
 		  if(m.find())
 		    ageRangeageRange=Long.parseLong(m.group(1));
 		 String	queryString4=	
 					("	select bpv.booth.boothId,bpv.booth.partNo,count(bpv.voter.voterId),bpv.booth.panchayat.panchayatName,bpv.voter.gender "+
 					"from BoothPublicationVoter bpv "+
 					"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" " +
 					"and bpv.booth.localBody.localElectionBodyId is null and bpv.voter.age<"+ageRangeageRange+"  "+
 					"group by bpv.booth.boothId,bpv.voter.gender ");	
 		    
 		  
 		    AgeRangeVO vo=new AgeRangeVO();
 		      vo.setAgeRange(ageRange);
 		      vo.setQueryString(queryString4);
 		       query.add(vo);
 		    	
 		}
 		else if((ageRange.contains("<"))&&(ageRange.contains("=")))
 		{
 		  
 		  String str=ageRange;
 		   str = str.trim();
 		  Pattern p = Pattern.compile("(\\d+)");
 		  Matcher m = p.matcher(str);
 		  Long ageRangeageRange=0l;
 		  if(m.find())
 		    ageRangeageRange=Long.parseLong(m.group(1));
 		  
 		    String	queryString5=	
 					("	select bpv.booth.boothId,bpv.booth.partNo,count(bpv.voter.voterId),bpv.booth.panchayat.panchayatName,bpv.voter.gender "+
 					"from BoothPublicationVoter bpv "+
 					"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" " +
 					"and bpv.booth.localBody.localElectionBodyId is null and bpv.voter.age<="+ageRangeageRange+"  "+
 					"group by bpv.booth.boothId,bpv.voter.gender ");	
 		    
 		  
 		    AgeRangeVO vo=new AgeRangeVO();
 		      vo.setAgeRange(ageRange);
 		      vo.setQueryString(queryString5);
 		       query.add(vo);
 		    	
 		}
 		else
 		{
 		  
 		Long ageRangeageRange=Long.parseLong(ageRange);
 		String	queryString6=	
 					("	select bpv.booth.boothId,bpv.booth.partNo,count(bpv.voter.voterId),bpv.booth.panchayat.panchayatName,bpv.voter.gender "+
 					"from BoothPublicationVoter bpv "+
 					"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" " +
 					"and bpv.booth.localBody.localElectionBodyId is null and bpv.voter.age="+ageRangeageRange+"  "+
 					"group by bpv.booth.boothId,bpv.voter.gender ");	
 		    
 		  
 		    AgeRangeVO vo=new AgeRangeVO();
 		      vo.setAgeRange(ageRange);
 		      vo.setQueryString(queryString6);
 		       query.add(vo);
 		    	
 		}
}
	  List<AgeRangeVO>  li =printData3( query,totalCountMap);
	  return li; 
}
public List<AgeRangeVO> getPanchayatWiseForBoothForAConstituency(Long constituencyId,Long publicationId,List<String> ageRanges)
{
	if(ageRanges==null || ageRanges.size() == 0 ||ageRanges.get(0).isEmpty())
	{
		ageRanges=new ArrayList<String>();
		ageRanges.add("18-22");
		ageRanges.add("23-25");
		ageRanges.add("26-35");
		ageRanges.add("36-45");
		ageRanges.add("46-60");
		ageRanges.add(">60");
	}
String	queryForTotalCount=("select bpv.booth.boothId,bpv.booth.partNo," +
			"count(distinct bpv.voter.voterId),bpv.booth.localBody.name,bpv.voter.gender "+
	"from BoothPublicationVoter bpv "+
	"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
	     "and bpv.booth.localBody.localElectionBodyId is not null  "+
	"group by bpv.booth.boothId,bpv.voter.gender  ");
 Map<Long,AgeRangeVO> totalCountMap=new LinkedHashMap<Long,AgeRangeVO>();
 List<Object[]> totalCountList = userDAO.getData1(queryForTotalCount);
 if(totalCountList!=null)
 {
   for (Object[] totalCountListObj : totalCountList) {
   AgeRangeVO ageRangeVOForTotalCount=totalCountMap.get((Long)totalCountListObj[0]);
   if(ageRangeVOForTotalCount==null) 
  {
    ageRangeVOForTotalCount=new AgeRangeVO();
    ageRangeVOForTotalCount.setPanchayatId((Long)totalCountListObj[0]);//boothid.
    ageRangeVOForTotalCount.setPanchayatName(totalCountListObj[1].toString());//partno.
    ageRangeVOForTotalCount.setTehsilName(totalCountListObj[3].toString());//lebname.
    if((Long)totalCountListObj[2]!=null)
    { 
      ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+(Long)totalCountListObj[2]);
      if(totalCountListObj[4].toString()!=null)
      {
        if(totalCountListObj[4].toString().equalsIgnoreCase("M") )
	    ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
        else if(totalCountListObj[4].toString().equalsIgnoreCase("F") )
	    ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
     }
    }
   totalCountMap.put((Long)totalCountListObj[0], ageRangeVOForTotalCount);
  }
  else
	 {
		if((Long)totalCountListObj[2]!=null)
		{
			ageRangeVOForTotalCount.setTotalCount(ageRangeVOForTotalCount.getTotalCount()+(Long)totalCountListObj[2] );
        if(totalCountListObj[4].toString()!=null){
			if(totalCountListObj[4].toString().equalsIgnoreCase("M") )
				ageRangeVOForTotalCount.setTotalMaleCount(ageRangeVOForTotalCount.getTotalMaleCount()+(Long)totalCountListObj[2]);
			else
				ageRangeVOForTotalCount.setTotalFemaleCount(ageRangeVOForTotalCount.getTotalFemaleCount()+(Long)totalCountListObj[2]); 
		}}
	}
}
}	
	
	List<AgeRangeVO>  query=new ArrayList<AgeRangeVO>();
	for(String ageRange:ageRanges)
	{
		if(ageRange.contains("-"))
		{    
		String[] parts = ageRange.split("-");
		    String part1 = parts[0]; 
		    String part2 = parts[1]; 
		       
			    String queryString =
				("select bpv.booth.boothId,bpv.booth.partNo," +
						"count(distinct bpv.voter.voterId),bpv.booth.localBody.name,bpv.voter.gender "+
				"from BoothPublicationVoter bpv "+
				"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
				     "and bpv.booth.localBody.localElectionBodyId is not null and bpv.voter.age>="+part1+" and bpv.voter.age<="+part2+" "+
				"group by bpv.booth.boothId,bpv.voter.gender  ");
		   AgeRangeVO vo=new AgeRangeVO();
		   vo.setAgeRange(ageRange);
		   vo.setQueryString(queryString);
		     query.add(vo);

		}
		else if((ageRange.contains(">"))&&(!ageRange.contains("=")))
		{
		   	  
		  String str=ageRange;
		   str = str.trim();
		  Pattern p = Pattern.compile("(\\d+)");
		  Matcher m = p.matcher(str);
		  Long ageRangeageRange=0l;
		  if(m.find())
		    ageRangeageRange=Long.parseLong(m.group(1));
		  

		    String queryString2 =
			("select bpv.booth.boothId,bpv.booth.partNo," +
		"count(distinct bpv.voter.voterId),bpv.booth.localBody.name,bpv.voter.gender "+
		"from BoothPublicationVoter bpv "+
		"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
		 "and bpv.booth.localBody.localElectionBodyId is not  null and bpv.voter.age>"+ageRangeageRange+"  "+
		"group by bpv.booth.boothId,bpv.voter.gender  ");
		    
		  
		    AgeRangeVO vo=new AgeRangeVO();
		      vo.setAgeRange(ageRange);
		      vo.setQueryString(queryString2);
		       query.add(vo);
		    	
		}
		else if((ageRange.contains(">"))&&(ageRange.contains("=")))
		{
		   	  
		  String str=ageRange;
		   str = str.trim();
		  Pattern p = Pattern.compile("(\\d+)");
		  Matcher m = p.matcher(str);
		  Long ageRangeageRange=0l;
		  if(m.find())
		    ageRangeageRange=Long.parseLong(m.group(1));
		  

		    String queryString3 =
			("select bpv.booth.boothId,bpv.booth.partNo," +
		"count(distinct bpv.voter.voterId),bpv.booth.localBody.name,bpv.voter.gender "+
		"from BoothPublicationVoter bpv "+
		"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
		 "and bpv.booth.localBody.localElectionBodyId is not  null and bpv.voter.age>="+ageRangeageRange+"  "+
		"group by bpv.booth.boothId,bpv.voter.gender  ");
		    
		  
		    AgeRangeVO vo=new AgeRangeVO();
		      vo.setAgeRange(ageRange);
		      vo.setQueryString(queryString3);
		       query.add(vo);
		    	
		}
		else if((ageRange.contains("<"))&&(!ageRange.contains("=")))
		{
		   	  
		  String str=ageRange;
		   str = str.trim();
		  Pattern p = Pattern.compile("(\\d+)");
		  Matcher m = p.matcher(str);
		  Long ageRangeageRange=0l;
		  if(m.find())
		    ageRangeageRange=Long.parseLong(m.group(1));
		  

		    String queryString4 =
			("select bpv.booth.boothId,bpv.booth.partNo," +
		"count(distinct bpv.voter.voterId),bpv.booth.localBody.name,bpv.voter.gender "+
		"from BoothPublicationVoter bpv "+
		"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
		 "and bpv.booth.localBody.localElectionBodyId is not  null and bpv.voter.age<"+ageRangeageRange+"  "+
		"group by bpv.booth.boothId,bpv.voter.gender  ");
		    
		  
		    AgeRangeVO vo=new AgeRangeVO();
		      vo.setAgeRange(ageRange);
		      vo.setQueryString(queryString4);
		       query.add(vo);
		    	
		}
		else if((ageRange.contains("<"))&&(ageRange.contains("=")))
		{
		   	  
		  String str=ageRange;
		   str = str.trim();
		  Pattern p = Pattern.compile("(\\d+)");
		  Matcher m = p.matcher(str);
		  Long ageRangeageRange=0l;
		  if(m.find())
		    ageRangeageRange=Long.parseLong(m.group(1));
		  

		    String queryString5 =
			("select bpv.booth.boothId,bpv.booth.partNo," +
		"count(distinct bpv.voter.voterId),bpv.booth.localBody.name,bpv.voter.gender "+
		"from BoothPublicationVoter bpv "+
		"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
		 "and bpv.booth.localBody.localElectionBodyId is not  null and bpv.voter.age<="+ageRangeageRange+"  "+
		"group by bpv.booth.boothId,bpv.voter.gender  ");
		    
		  
		    AgeRangeVO vo=new AgeRangeVO();
		      vo.setAgeRange(ageRange);
		      vo.setQueryString(queryString5);
		       query.add(vo);
		    	
		}
		else
		{
		Long ageRangeageRange=Long.parseLong(ageRange);
		String queryString6 =
				("select bpv.booth.boothId,bpv.booth.partNo," +
			"count(distinct bpv.voter.voterId),bpv.booth.localBody.name,bpv.voter.gender "+
			"from BoothPublicationVoter bpv "+
			"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
			 "and bpv.booth.localBody.localElectionBodyId is not  null and bpv.voter.age="+ageRangeageRange+"  "+
			"group by bpv.booth.boothId,bpv.voter.gender  ");
			    
			  
			    AgeRangeVO vo=new AgeRangeVO();
			      vo.setAgeRange(ageRange);
			      vo.setQueryString(queryString6);
			       query.add(vo);	
		}
		     }
	  List<AgeRangeVO>  li =printData3( query,totalCountMap);
	  return li; 
}
public List<AgeRangeVO> printData3(List<AgeRangeVO> query,Map<Long,AgeRangeVO> totalCountMap)
{
List<AgeRangeVO> li =null;
Map<Long,AgeRangeVO> boothMap=new LinkedHashMap<Long, AgeRangeVO>();
for(Map.Entry<Long,AgeRangeVO>  totalCountMapEntrySet :totalCountMap.entrySet() )
	  {
	       Long panchayatId = totalCountMapEntrySet.getKey();
	       AgeRangeVO  totalCountMapVO=totalCountMapEntrySet.getValue();
	       
	       AgeRangeVO  boothMapMapVO= boothMap.get(panchayatId);
	       if(boothMapMapVO==null)
	       {  boothMapMapVO=new AgeRangeVO();
	          boothMapMapVO.setTotalCount(totalCountMapVO.getTotalCount());
	          boothMapMapVO.setTotalMaleCount(totalCountMapVO.getTotalMaleCount());
	          boothMapMapVO.setTotalFemaleCount(totalCountMapVO.getTotalFemaleCount());
	          boothMapMapVO.setPanchayatId(totalCountMapVO.getPanchayatId());//boothid.
	          boothMapMapVO.setPanchayatName(totalCountMapVO.getPanchayatName());//partno
	          boothMapMapVO.setTehsilName(totalCountMapVO.getTehsilName());//panch/mun name.
			  Map<String,AgeRangeVO> m=new LinkedHashMap<String,AgeRangeVO>();
			  boothMapMapVO.setMap(m);
			  boothMap.put(panchayatId, boothMapMapVO);
	       }
	      
	   }

List<AgeRangeVO> output=new ArrayList<AgeRangeVO>();
if(query!=null)
{
for(AgeRangeVO   vo    :query)
{
  List<Object[]> list1 = userDAO.getData1(vo.getQueryString());
 
  AgeRangeVO vo1=new AgeRangeVO();
  vo1.setAgeRange(vo.getAgeRange());
  vo1.setOutput(list1);
  output.add(vo1);
}
}

if(output!=null)
{
for(AgeRangeVO param:output){
 List<Object[]> list=param.getOutput();
 iterateLogic1(boothMap,param,list);

}
}
li=gettingPercantage(boothMap);
return li;

}

public void iterateLogic1(Map<Long,AgeRangeVO> boothMap,AgeRangeVO param, List<Object[]> list) {
if(list.size()>0){
	  for(Object[] obj:list){
		 long l=(Long)obj[0];
		  AgeRangeVO vo = boothMap.get((Long)obj[0]);
		
		   if(obj[2]!=null)
		   {
			     String ageRange =param.getAgeRange();
			     for(Map.Entry<Long,AgeRangeVO>  ageRangeVO :boothMap.entrySet() )
			     {
			    	 AgeRangeVO vo1 = ageRangeVO.getValue();
			    	 Long panchayatId = ageRangeVO.getKey();
			    	  Map<String,AgeRangeVO>   m =vo1.getMap();
			    	     AgeRangeVO particularAge  =m.get(ageRange);
			    	     long a=(Long)obj[0];
			    	     long b=vo1.getPanchayatId();
			    	    if(a==b){
			    	     if(particularAge==null)
			    	     {
			    	    	 particularAge =new AgeRangeVO();
			    	    	 particularAge.setParticularAgeVotersCount ((particularAge.getParticularAgeVotersCount())+ ((Long)obj[2]) );
			    	    	 particularAge.setPanchayatId(panchayatId);
			    	    	 if(obj[4]!=null)
				    	       {
				    	    	   if(obj[4].toString().equalsIgnoreCase("M"))
				    	    	      particularAge.setMaleCount(Long.parseLong(obj[2].toString()));
				    	    		   
				    	    	   if(obj[4].toString().equalsIgnoreCase("F"))
					    	    	      particularAge.setFemaleCount(Long.parseLong(obj[2].toString()));
					    	    		      
				    	    	   
				    	       }
			    	    	 
			    	    	 m.put(ageRange, particularAge);
			    	    	 
			    	     }
			    	     else
			    	     {
			    	    	long panchayatIdd=particularAge.getPanchayatId();
			    	    	if(panchayatIdd==l ) 
			    	    	{		
			    	         particularAge.setParticularAgeVotersCount(( particularAge.getParticularAgeVotersCount())+ ((Long)obj[2]) );
			    	       	
				    	       if(obj[4]!=null)
				    	       {
				    	    	   if(obj[4].toString().equalsIgnoreCase("M"))
				    	    	      particularAge.setMaleCount(Long.parseLong(obj[2].toString()));
				    	    		   
				    	    	   if(obj[4].toString().equalsIgnoreCase("F"))
					    	    	      particularAge.setFemaleCount(Long.parseLong(obj[2].toString()));
					    	    		      
				    	    	   
				    	       }
			    	         
			    	        } 
			    	    	 
			    	     }	 
			    	    }//if 	 
			      }//for
		   }//if
			   
}//for
 }//if
}


public static Comparator<AgeRangeVO> mandalWiseSort = new Comparator<AgeRangeVO>()
{	  
		  public int compare(AgeRangeVO vo2,AgeRangeVO vo1)
			{  
			              String  s2=vo2.getTehsilName();
			              String s1=vo1.getTehsilName();
			               return s2.compareTo(s1);
			
			}
};
public static Comparator<AgeRangeVO> panchayatWiseSort = new Comparator<AgeRangeVO>()
{	  
		  public int compare(AgeRangeVO vo2,AgeRangeVO vo1)
			{  
			              String  s2=vo2.getTehsilName();
			              String s1=vo1.getTehsilName();
			               return s2.compareTo(s1);
			
			}
};
public static Comparator<AgeRangeVO> hamletWiseSort = new Comparator<AgeRangeVO>()
{	  
		  public int compare(AgeRangeVO vo2,AgeRangeVO vo1)
			{  
			               Long i2=vo2.getTotalCount();
			               Long i1=vo1.getTotalCount();
			               return i1.compareTo(i2);
			
			}
};
public static Comparator<AgeRangeVO> boothWiseSort = new Comparator<AgeRangeVO>()
{	  
		  public int compare(AgeRangeVO vo2,AgeRangeVO vo1)
			{  
			            Long  l2=Long.parseLong(vo2.getPanchayatName());
			            Long l1=Long.parseLong(vo1.getPanchayatName());
			               return l2.compareTo(l1);
			
			}
};



















   

//============================================================================
/*
//getHamletWiseDetailsForAConstituency for a constituency.
	  public List<AgeRangeVO> getHamletWiseDetailsForAConstituency(Long constituencyId,Long publicationId)
	 {
		  List<AgeRangeVO> li = null ;
		  Map<Long,AgeRangeVO> map;
	 try{
	 List<Object[]>   getAgeHamletWise = userDAO.getAgePanchayatHamletWithType(constituencyId,publicationId);
	  map = new HashMap<Long,AgeRangeVO>();
		
		if(getAgeHamletWise != null && getAgeHamletWise.size() >0){
		for(Object[] data:getAgeHamletWise){
			AgeRangeVO vo = map.get((Long)data[0]);
			if(vo == null){
				vo = new AgeRangeVO();
				vo.setPanchayat(data[3].toString());
				vo.setHamlet(data[1].toString());
				map.put((Long)data[0],vo);
			}
			if(data[2] != null){
				vo.setTotalVoters(vo.getTotalVoters()+(Long)data[2]);	
				setData(data,vo,null);
			}
		}
		}
		
		 for (Entry<Long, AgeRangeVO> ageRangeVO : map.entrySet()) {
        	 AgeRangeVO vo1 = ageRangeVO.getValue();
        	 Long totalVoters = vo1.getTotalVoters();
        	  DecimalFormat df = new DecimalFormat("##.##");
       
        	 vo1.setYoungVotersPercantage( Double.parseDouble(df.format(((double)vo1.getYoungVoters()*100)/totalVoters )));                     
        	 vo1.setAge23to25Percantage(Double.parseDouble(df.format(((double)  vo1.getAge23to25()*100)/totalVoters ) ));
        	 vo1.setAge26to35Percantage( Double.parseDouble(df.format(((double)  vo1.getAge26to35()*100)/totalVoters ) ) );
        	 vo1.setAge36to45Percantage(Double.parseDouble(df.format(((double)  vo1.getAge36to45()*100)/totalVoters ) ));
        	 vo1.setAge46to60Percantage(Double.parseDouble(df.format(((double)  vo1.getAge46to60()*100)/totalVoters ) ));
        	 vo1.setAge>60Percantage(Double.parseDouble(df.format(((double)  vo1.get>60()*100)/totalVoters ) ));
            }  

	li = new ArrayList<AgeRangeVO>(map.values());
	 }catch(Exception e)
	 {
		e.printStackTrace(); 
	 }
	
	return li;
	 
}//getHamletWiseDetails().	 
	 
*/


/*
//boothwisewithpanchayatsforaconstituency.	  
public List<AgeRangeVO>	  getBoothWiseWithPanForConstituency(Long constituencyId, Long publicationId)
{
	Map<Long,AgeRangeVO> map;
	List<AgeRangeVO> li = null ;
	
	try{
	 map = new HashMap<Long,AgeRangeVO>();
	 List<Object[]>  getAgeBoothWise= userDAO.getAgePanchayatBoothWiseWithType(constituencyId,publicationId);
	   if(getAgeBoothWise != null && getAgeBoothWise.size() >0){
	    for(Object[] data:getAgeBoothWise){
		
		AgeRangeVO vo = map.get((Long)data[0]); 
		if(vo == null){
			vo = new AgeRangeVO();
			vo.setPanchayat(data[3].toString());
			vo.setHamlet(data[1].toString());
			map.put((Long)data[0],vo);
		}
		if(data[2] != null){
			setData(data,vo,data[5]);
		}
	}
	}  
	
      getAgeBoothWise= userDAO.getAgePanchayatBoothWiseWithType1(constituencyId,publicationId);
      if(getAgeBoothWise != null && getAgeBoothWise.size() >0)
			
			 for(Object[] data:getAgeBoothWise){
				
				AgeRangeVO vo = map.get((Long)data[0]);
				if(vo == null){
					vo = new AgeRangeVO();
					vo.setPanchayat(data[3].toString());
					vo.setHamlet(data[1].toString());
					map.put((Long)data[0],vo);
				}
				if(data[2] != null){
					vo.setYoungVoters(vo.getYoungVoters()+((Long)data[2]));
					if(data[4] != null){
							if(data[4].toString().equalsIgnoreCase("M") || data[4].toString().equalsIgnoreCase("Male")){
								vo.setYoungVotersM(vo.getYoungVotersM()+((Long)data[2]));
							}
							if(data[4].toString().equalsIgnoreCase("F") || data[4].toString().equalsIgnoreCase("Female")){
								vo.setYoungVotersF(vo.getYoungVotersF()+((Long)data[2]));
							}
					}
				}
			}
			
	
	 li = new ArrayList<AgeRangeVO>(map.values());
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	  
	return li;
	
}//boothwisewithpanchayatsforaconstituency  
	 
	 
	 
//panchayat wise age details for a constituency.(for this output add getPWiseWithMuncipalForConstituency output)	 
public List<AgeRangeVO> getPanchayatWiseForConstituency(Long constituencyId ,Long publicationId)	 
{	 
	List<AgeRangeVO> li=null;
	Map<Long,AgeRangeVO> map ;
	try{
	 
	 List<Object[]>  getAgePanchayatWise= userDAO.getAgePanchayatWiseWithType(constituencyId,publicationId);
	 map = new HashMap<Long,AgeRangeVO>();
		
		
		
		
	  map = new HashMap<Long,AgeRangeVO>();
		
		if(getAgePanchayatWise != null && getAgePanchayatWise.size() >0){
		for(Object[] data:getAgePanchayatWise){
			
			AgeRangeVO vo = map.get((Long)data[0]); 
			if(vo == null){
				vo = new AgeRangeVO();
				vo.setPanchayat(data[1].toString());
				vo.setHamlet(data[3].toString());
				map.put((Long)data[0],vo);
			}
			if(data[2] != null){
				setData(data,vo,data[5]);
			}
		}
		}  
	 
   getAgePanchayatWise= userDAO.getAgePanchayatWiseWithType1(constituencyId,publicationId);
   if(getAgePanchayatWise != null && getAgePanchayatWise.size() >0)
		
		 for(Object[] data:getAgePanchayatWise){
			
			AgeRangeVO vo = map.get((Long)data[0]);
			if(vo == null){
				vo = new AgeRangeVO();
				vo.setPanchayat(data[1].toString());
				vo.setHamlet(data[3].toString());
				map.put((Long)data[0],vo);
			}
			if(data[2] != null){
				vo.setYoungVoters(vo.getYoungVoters()+((Long)data[2]));
				if(data[5] != null){
						if(data[5].toString().equalsIgnoreCase("M") || data[5].toString().equalsIgnoreCase("Male")){
							vo.setYoungVotersM(vo.getYoungVotersM()+((Long)data[2]));
						}
						if(data[5].toString().equalsIgnoreCase("F") || data[5].toString().equalsIgnoreCase("Female")){
							vo.setYoungVotersF(vo.getYoungVotersF()+((Long)data[2]));
						}
				}
			}
		}
		
	 
	li = new ArrayList<AgeRangeVO>(map.values());
	}catch(Exception e)
	{
	  e.printStackTrace();	
	}
	 
	return li; 
	 
}//getPanchayatWiseForConstituency




//Booth wise age details for a munc in a constituency.	 
public List<AgeRangeVO>  getBoothWiseWithMunForAConstituency(Long constituencyId,Long publicationId)
{
	List<AgeRangeVO> li=null;
try{
	
	Map<Long,AgeRangeVO> map = new HashMap<Long,AgeRangeVO>();
	  
	List<Long> localElecBodyList =userDAO.gettingLocalElectionBodyForAConstituency(constituencyId);
	
	List<Object[]>   getAgeBoothWiseforMunc =userDAO.getAgeBoothWiseForMunWithType(constituencyId,publicationId,localElecBodyList);
	  
	 if(getAgeBoothWiseforMunc != null && getAgeBoothWiseforMunc.size() >0){
	    for(Object[] data:getAgeBoothWiseforMunc){
		
		AgeRangeVO vo = map.get((Long)data[0]); 
		if(vo == null){
			vo = new AgeRangeVO();
			vo.setPanchayat(data[3].toString());
			vo.setHamlet(data[1].toString());
			map.put((Long)data[0],vo);
		}
		if(data[2] != null){
			setData(data,vo,data[5]);
		}
	}
	}  
	  getAgeBoothWiseforMunc =userDAO.getAgeBoothWiseForMunWithType1(constituencyId,publicationId,localElecBodyList);
		 
			if(getAgeBoothWiseforMunc != null && getAgeBoothWiseforMunc.size() >0)
				
				 for(Object[] data:getAgeBoothWiseforMunc){
					
					AgeRangeVO vo = map.get((Long)data[0]);
					if(vo == null){
						vo = new AgeRangeVO();
						vo.setPanchayat(data[3].toString());
						vo.setHamlet(data[1].toString());
						map.put((Long)data[0],vo);
					}
					if(data[2] != null){
						vo.setYoungVoters(vo.getYoungVoters()+((Long)data[2]).longValue());
						if(data[4] != null){
							if(data[4].toString().equalsIgnoreCase("M") || data[4].toString().equalsIgnoreCase("Male")){
								vo.setYoungVotersM(vo.getYoungVotersM()+((Long)data[2]));
							}
							if(data[4].toString().equalsIgnoreCase("F") || data[4].toString().equalsIgnoreCase("Female")){
								vo.setYoungVotersF(vo.getYoungVotersF()+((Long)data[2]));
							}
					   }
					}
				}
				
			 
	 li = new ArrayList<AgeRangeVO>(map.values()); 
	   
}
catch(Exception e)
       {e.printStackTrace();}
return li;

}//getBoothWiseWithMunForAConstituency.

 

public List<AgeRangeVO> getPWiseWithMuncipalForConstituency(Long constituencyId,Long publicationId)
{
	List<AgeRangeVO> li=null;
	
  try{
	  Map<Integer,AgeRangeVO> map = new HashMap<Integer,AgeRangeVO>();
	  
	  List<Long> localElecBodyList =userDAO.gettingLocalElectionBodyForAConstituency(constituencyId);
	  
	  List<Object[]>   getAgePanchayatWiseForMunicpalWithType =userDAO.getAgePanchayatWiseForMunicpalWithType(constituencyId,publicationId,localElecBodyList);
		if(getAgePanchayatWiseForMunicpalWithType != null && getAgePanchayatWiseForMunicpalWithType.size() >0){
			for(Object[] data:getAgePanchayatWiseForMunicpalWithType){
				
				AgeRangeVO vo = map.get((Integer)data[0]); 
				if(vo == null){
					vo = new AgeRangeVO();
					vo.setPanchayat(data[1].toString());
					vo.setHamlet(data[3].toString());
					map.put((Integer)data[0],vo);
				}
				if(data[2] != null){
					setData(data,vo,data[5]);
				}
			}
			}  
	  
		getAgePanchayatWiseForMunicpalWithType =userDAO.getAgePanchayatWiseForMunicpalWithType1(constituencyId,publicationId,localElecBodyList);
	   if(getAgePanchayatWiseForMunicpalWithType != null && getAgePanchayatWiseForMunicpalWithType.size() >0){
			 for(Object[] data:getAgePanchayatWiseForMunicpalWithType){
					
					AgeRangeVO vo = map.get((Integer)data[0]);
					if(vo == null){
						vo = new AgeRangeVO();
						vo.setPanchayat(data[1].toString());
						vo.setHamlet(data[3].toString());
						map.put((Integer)data[0],vo);
					}
					if(data[2] != null){
						vo.setYoungVoters(vo.getYoungVoters()+((Long)data[2]));
						if(data[4] != null){
								if(data[4].toString().equalsIgnoreCase("M") || data[4].toString().equalsIgnoreCase("Male")){
									vo.setYoungVotersM(vo.getYoungVotersM()+((Long)data[2]));
								}
								if(data[4].toString().equalsIgnoreCase("F") || data[4].toString().equalsIgnoreCase("Female")){
									vo.setYoungVotersF(vo.getYoungVotersF()+((Long)data[2]));
								}
						}
					}
				}
	   }	
			 
	  
	  
	  
	  li = new ArrayList<AgeRangeVO>(map.values());
   
  }catch(Exception e)
   {e.printStackTrace();}
	
	return li;
}
*/












}