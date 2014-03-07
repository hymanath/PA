
package com.itgrids.partyanalyst.dao.hibernate;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.model.Constituency;

public class UserDAOHibernateTest extends BaseDaoTestCase{

	private IUserDAO userDAO;
	private IConstituencyDAO constituencyDAO ;
	public IUserDAO getUserDAO() {
		return userDAO;
	}
                                                        
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	
	
	

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public AgeRangeVO getConstituencyWiseDetails(Long constituencyId,Long publicationId,List<String> ageRanges)	
	{
		if(ageRanges==null||ageRanges.get(0).isEmpty())
		 {
			ageRanges=new ArrayList<String>();
			ageRanges.add("18-22");
			ageRanges.add("23-25");
			ageRanges.add("26-35");
			ageRanges.add("36-45");
			ageRanges.add("46-60");
			ageRanges.add("Above60");
		 }
	String	queryForTotalCount=	
				(" select bpv.booth.constituency.constituencyId, bpv.booth.constituency.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
				"	from BoothPublicationVoter bpv "+
				"	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
				" group by bpv.voter.gender ");	

List<Object[]> totalCountList = userDAO.getData(queryForTotalCount);
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
 
 String subString="-";
	List<AgeRangeVO>  query=new ArrayList<AgeRangeVO>();
	for(String ageRange:ageRanges)
	{
if(ageRange.contains(subString))
{    
	    String[] parts = ageRange.split("-");
		    String part1 = parts[0]; 
		    String part2 = parts[1]; 
				
				String	queryString=null;
			queryString=	
						(" select bpv.booth.constituency.constituencyId, bpv.booth.constituency.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
						"	from BoothPublicationVoter bpv "+
						"	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
						" and bpv.voter.age>="+part1+" and bpv.voter.age<="+part2+"  "+
						" group by bpv.voter.gender ");	
			
			 AgeRangeVO vo=new AgeRangeVO();
		        vo.setAgeRange(ageRange);
		        vo.setQueryString(queryString);
			     query.add(vo);
	       }  
	      
	       else
	       {  String queryString2=null;
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
			   List<Object[]> list = userDAO.getData(vo.getQueryString());
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
	}
		
		
public List<AgeRangeVO> getMandalWiseForAConstituency(Long constituencyId,Long publicationId,List<String> ageRanges)
{
	if(ageRanges==null||ageRanges.get(0).isEmpty())
	{
		ageRanges=new ArrayList<String>();
		ageRanges.add("18-22");
		ageRanges.add("23to25");
		ageRanges.add("26to35");
		ageRanges.add("36to45");
		ageRanges.add("46to60");
		ageRanges.add("Above60");
	}
	 List<AgeRangeVO> li= generateQueries(constituencyId,publicationId,ageRanges,"mandal");
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
	
	
	
	
	 List<Object[]> totalCountList = userDAO.getData(queryForTotalCount);
	  
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
	
	
	List<Object[]> totalCountList = userDAO.getData(queryForTotalCount);
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
	String subString="-";
	List<AgeRangeVO>  query=new ArrayList<AgeRangeVO>();
	for(String ageRange:ageRanges)
	{
   if(ageRange.contains(subString))
   {    
	    String[] parts = ageRange.split("-");
		    String part1 = parts[0]; 
		    String part2 = parts[1]; 
			
			String	queryString=null;
			if(type.equalsIgnoreCase("mandal"))
			{
		    	queryString = (" select bpv.booth.tehsil.tehsilId, bpv.booth.tehsil.tehsilName,count(distinct bpv.voter.voterId),bpv.voter.gender "+
		    		 "	from BoothPublicationVoter bpv "+
		    		 "	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
		    		 " and bpv.voter.age>="+part1+" and bpv.voter.age<="+part2+"  and bpv.booth.localBody.localElectionBodyId is null "+
		    		 "		group by bpv.booth.tehsil.tehsilId,bpv.voter.gender");
		    
		   }
		   else if(type.equalsIgnoreCase("municpality"))
		   {
		      queryString=	
					(" select bpv.booth.localBody.localElectionBodyId , bpv.booth.localBody.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
					"	from BoothPublicationVoter bpv "+
					"	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
					" and bpv.voter.age>="+part1+" and bpv.voter.age<="+part2+" and bpv.booth.localBody.localElectionBodyId is not null "+
					" group by bpv.booth.localBody.localElectionBodyId,bpv.voter.gender ");	
		   
		   
		   
		   } 
		   
	        AgeRangeVO vo=new AgeRangeVO();
	        vo.setAgeRange(ageRange);
	        vo.setQueryString(queryString);
		     query.add(vo);
		
	     }
	   else{
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
	   List<Object[]> list = userDAO.getData(vo.getQueryString());
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

public List<AgeRangeVO> getMunicipalityWiseForAConstituency(Long constituencyId,Long publicationId,List<String> ageRanges)
{
	if(ageRanges==null||ageRanges.get(0).isEmpty())
	{
		ageRanges=new ArrayList<String>();
		ageRanges.add("18-22");
		ageRanges.add("23-25");
		ageRanges.add("26-35");
		ageRanges.add("36-45");
		ageRanges.add("46-60");
		ageRanges.add("Above60");
	}
	 List<AgeRangeVO> li= generateQueries(constituencyId,publicationId,ageRanges,"municpality");
     return li;
}


public List<AgeRangeVO> getPanchayatWiseForAConstituency(Long constituencyId,Long publicationId,List<String> ageRanges,boolean pId)
{
	if(ageRanges==null||ageRanges.get(0).isEmpty())
	{
		ageRanges=new ArrayList<String>();
		ageRanges.add("18-22");
		ageRanges.add("23-25");
		ageRanges.add("26-35");
		ageRanges.add("36-45");
		ageRanges.add("46-60");
		ageRanges.add("Above60");
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
	         List<Object[]> totalCountList = userDAO.getData(queryForTotalCount);
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
			   if(ageRange.contains(subString))
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
		      else{
		    	  
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
			List<Object[]> par = userDAO.getData(partialBoot);
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
		         List<Object[]> totalCountList = userDAO.getData(queryForTotalCount);
		         List<Object[]> totalCountList2 = userDAO.getData(parqueryForTotalCount);
		         
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
		         if(ageRange.contains(subString))
		         {    
		      	    String[] parts = ageRange.split("-");
		      		    String part1 = parts[0]; 
		      		    String part2 = parts[1]; 
					    
					    ////pid,pname,count,tnme. 
				   String	    queryham =
					    		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
					    				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName,bpv.voter.gender "+
					    		"from BoothPublicationVoter bpv "+
					    		"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
					    		     " and bpv.voter.age>="+part1+" and bpv.voter.age<="+part2+" and bpv.booth.panchayat.panchayatId not in("+paids+") "+
					    		"group by bpv.booth.panchayat.panchayatId,bpv.voter.gender ");	    
					    
					    
					    
					    
			    String	parqueryham = 
						("select ph.panchayat.panchayatId,ph.panchayat.panchayatName,count(distinct bpv.voter.voterId),ph.panchayat.tehsil.tehsilName,bpv.voter.gender  "+
						"from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph "+
						"where bpv.voter.voterId=uvd.voter.voterId and uvd.hamlet.hamletId=ph.hamlet.hamletId "+ 
						"          and bpv.voter.age>="+part1+" and bpv.voter.age<="+part2+" and ph.panchayat.panchayatId in("+paids+") "+
						 "         and bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
						"group by ph.panchayat.panchayatId,bpv.voter.gender  ");

				
				        AgeRangeVO vo=new AgeRangeVO();
				        vo.setAgeRange(ageRange);
				        vo.setQueryString(queryham);
				        vo.setPartQueryString(parqueryham);
					     query.add(vo);
					
				     }
				   else{
					   
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
  List<Object[]> list1 = userDAO.getData(vo.getQueryString());
  List<Object[]> list2 =null;
  if(partial){
   list2 = userDAO.getData(vo.getPartQueryString());
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

public List<AgeRangeVO> getHamletWiseDetailsForAConstituency(Long constituencyId,Long publicationId,List<String> ageRanges)
{  
	if(ageRanges==null||ageRanges.get(0).isEmpty())
	{
		ageRanges=new ArrayList<String>();
		ageRanges.add("18-22");
		ageRanges.add("23-25");
		ageRanges.add("26-35");
		ageRanges.add("36-45");
		ageRanges.add("46-60");
		ageRanges.add("Above60");
	}
	
	
	
String	queryForTotalCount = ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName,bpv.voter.gender " +
	    		"from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
	    		"where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
	    		"bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId ="+publicationId+"  " +
	    		"and uvd.user.userId=1   " +
	    		" group by  uvd.hamlet.hamletId,bpv.voter.gender");

         Map<Long,AgeRangeVO> totalCountMap=new LinkedHashMap<Long,AgeRangeVO>();
         List<Object[]> totalCountList = userDAO.getData(queryForTotalCount);
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
	
         String subString="-";
     	List<AgeRangeVO>  query=new ArrayList<AgeRangeVO>();
     	for(String ageRange:ageRanges)
     	{
        if(ageRange.contains(subString))
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
   else{
	   
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
  List<Object[]> list1 = userDAO.getData(vo.getQueryString());
 
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
public List<AgeRangeVO> getBoothWiseForAConstituency(Long constituencyId,Long publicationId,List<String> ageRanges)
{
	if(ageRanges==null||ageRanges.get(0).isEmpty())
	{
		ageRanges=new ArrayList<String>();
		ageRanges.add("18-22");
		ageRanges.add("23-25");
		ageRanges.add("26-35");
		ageRanges.add("36-45");
		ageRanges.add("46-60");
		ageRanges.add("Above60");
	}
String	queryForTotalCount=	
				("	select bpv.booth.boothId,bpv.booth.partNo,count(bpv.voter.voterId),bpv.booth.panchayat.panchayatName,bpv.voter.gender "+
				"from BoothPublicationVoter bpv "+
				"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" " +
				"and bpv.booth.localBody.localElectionBodyId is null  "+
				"group by bpv.booth.boothId,bpv.voter.gender ");	

     Map<Long,AgeRangeVO> totalCountMap=new LinkedHashMap<Long,AgeRangeVO>();
     List<Object[]> totalCountList = userDAO.getData(queryForTotalCount);
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
	
     String subString="-";
 	List<AgeRangeVO>  query=new ArrayList<AgeRangeVO>();
 	for(String ageRange:ageRanges)
 	{
    if(ageRange.contains(subString))
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
     else{
   	  
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
     }
	  List<AgeRangeVO>  li =printData3( query,totalCountMap);
	  return li; 
}
public List<AgeRangeVO> getPanchayatWiseForBoothForAConstituency(Long constituencyId,Long publicationId,List<String> ageRanges)
{
	if(ageRanges==null||ageRanges.get(0).isEmpty())
	{
		ageRanges=new ArrayList<String>();
		ageRanges.add("18-22");
		ageRanges.add("23-25");
		ageRanges.add("26-35");
		ageRanges.add("36-45");
		ageRanges.add("46-60");
		ageRanges.add("Above60");
	}
String	queryForTotalCount=("select bpv.booth.boothId,bpv.booth.partNo," +
			"count(distinct bpv.voter.voterId),bpv.booth.localBody.name,bpv.voter.gender "+
	"from BoothPublicationVoter bpv "+
	"where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"   "+
	     "and bpv.booth.localBody.localElectionBodyId is not null  "+
	"group by bpv.booth.boothId,bpv.voter.gender  ");
 Map<Long,AgeRangeVO> totalCountMap=new LinkedHashMap<Long,AgeRangeVO>();
 List<Object[]> totalCountList = userDAO.getData(queryForTotalCount);
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
	
	
 String subString="-";
	List<AgeRangeVO>  query=new ArrayList<AgeRangeVO>();
	for(String ageRange:ageRanges)
	{
if(ageRange.contains(subString))
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
     else{
   	  
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
  List<Object[]> list1 = userDAO.getData(vo.getQueryString());
 
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

List<String> ageRanges=new ArrayList<String>();


AgeRangeVO vo;
HSSFWorkbook workbook;
HSSFSheet constituencyWiseSheet  ;
HSSFSheet mandalWiseSheet;
HSSFSheet panchayatwiseSheet;
HSSFSheet boothWiseSheet;
HSSFSheet hamletWiseSheet;
FileOutputStream out;

public void test1234()
{
	try {
		ageRanges.add("18-22");
		ageRanges.add("23-25");
		ageRanges.add("26-35");
		ageRanges.add("36-45");
		ageRanges.add("46-60");
		ageRanges.add("Above60");
		
		
		
		List<Object[]> constiIds = constituencyDAO.getDistrictConstituencies(9l);
		
		for (Object[] parms : constiIds)
		{
			workbook = new HSSFWorkbook(); 
			
			String filename = null;
			 filename="C:/"+parms[1].toString()+".xls" ;
			 out=new FileOutputStream(filename);
			Long constituecnyId = (Long)parms[0];
			System.out.println(parms[1].toString());
			if(parms[2].toString().equalsIgnoreCase("RURAL"))
			{
				AgeRangeVO constituencyWise = getConstituencyWiseDetails(constituecnyId,10l,ageRanges);
				constituencyWiseSheet = workbook.createSheet("Constituency");
				constituencyWiseSheet.autoSizeColumn((short) 1000);
				generateExcelsForConstituency(constituencyWise,constituencyWiseSheet,workbook);		
				
				
				
				 // Mandal Wise Report Genereation
				/* List<AgeRangeVO> muncipalityWise = getMunicipalityWiseForAConstituency(232l,10l,ageRanges);
				 List<AgeRangeVO> muncipalityWise1 = new ArrayList<AgeRangeVO>();//kavali---->kavali municipality.
				 for (AgeRangeVO ageRangeVO : muncipalityWise) {
					 ageRangeVO.setTehsilName(ageRangeVO.getTehsilName() + " Munciplity");
					 ageRangeVO.setPanchayatName(ageRangeVO.getPanchayatName() + " Munciplity");
					 muncipalityWise1.add(ageRangeVO);
				}
				*/
				List<AgeRangeVO> mandalWise = getMandalWiseForAConstituency(constituecnyId,10l,ageRanges);
				
				Collections.sort(mandalWise,mandalWiseSort);//dnso of mandalname.
				
				 /*if(muncipalityWise1 != null && muncipalityWise1.size() > 0)
				  mandalWise.addAll(muncipalityWise1);*/
				
				 mandalWiseSheet = workbook.createSheet("Mandal");
				 mandalWiseSheet.autoSizeColumn((short) 1000);
				 generateExcelsForMandalReport1(mandalWise,mandalWiseSheet,workbook);
				 
				
				
				 
				 // Panchayat Wise Report Generation
				// List<AgeRangeVO> muncipalityWise = getMunicipalityWiseForAConstituency(232l,10l,ageRanges);
				 //List<AgeRangeVO> muncipalityWise1 = new ArrayList<AgeRangeVO>();//kavali---->kavali municipality.
				/* for (AgeRangeVO ageRangeVO : muncipalityWise) {
					 ageRangeVO.setTehsilName(ageRangeVO.getTehsilName() + " Munciplity");
					 ageRangeVO.setPanchayatName(ageRangeVO.getPanchayatName() + " Munciplity");
					 muncipalityWise1.add(ageRangeVO);
				 }*/
				 List<AgeRangeVO> panchayatWise = getPanchayatWiseForAConstituency(constituecnyId,10l,ageRanges,false);
				/* if(muncipalityWise1 != null && muncipalityWise1.size() > 0)
				 panchayatWise.addAll(muncipalityWise1);*/
				 panchayatwiseSheet = workbook.createSheet("Panchayat");
				 panchayatwiseSheet.autoSizeColumn((short) 1000);
				 generateExcelsForPanchayatReport(panchayatWise,panchayatwiseSheet,workbook);
				
				
				 
				
				 //Hamlet Wise Report Generation
				 hamletWiseSheet = workbook.createSheet("Hamlet");
				 hamletWiseSheet.autoSizeColumn((short) 1000);
				 List<AgeRangeVO> hamletWise = getHamletWiseDetailsForAConstituency(constituecnyId,10l,ageRanges);
				 Collections.sort(hamletWise,hamletWiseSort);
				 generateExcelsForHamletReport(hamletWise,hamletWiseSheet,workbook);
				
		 
				 
				
				//  Booth Wise Report Generation
				 boothWiseSheet = workbook.createSheet("Booth");
				 boothWiseSheet.autoSizeColumn((short) 1000);
				 List<AgeRangeVO> boothWise = getBoothWiseForAConstituency(constituecnyId,10l,ageRanges);
				 
				 //List<AgeRangeVO> panchayatWiseForBooth = getPanchayatWiseForBoothForAConstituency(232l,10l,ageRanges);
				 //List<AgeRangeVO> panchayatWiseForBooth1 = new ArrayList<AgeRangeVO>();
				 /*for (AgeRangeVO ageRangeVO : panchayatWiseForBooth) {
					 ageRangeVO.setTehsilName(ageRangeVO.getTehsilName() + " Munciplity");
					 panchayatWiseForBooth1.add(ageRangeVO);
				}*/
				 
				/* if(panchayatWiseForBooth1 != null && panchayatWiseForBooth1.size() > 0)
				 {
					 boothWise.addAll(panchayatWiseForBooth1);
				 }*/
				 generateExcelsForBoothReport(boothWise,boothWiseSheet,workbook);
			}
			else if(parms[2].toString().equalsIgnoreCase("URBAN"))
			{
				AgeRangeVO constituencyWise = getConstituencyWiseDetails(constituecnyId,10l,ageRanges);
				constituencyWiseSheet = workbook.createSheet("Constituency");
				constituencyWiseSheet.autoSizeColumn((short) 1000);
				generateExcelsForConstituency(constituencyWise,constituencyWiseSheet,workbook);	
				
				//  Booth Wise Report Generation
				 boothWiseSheet = workbook.createSheet("Booth");
				 boothWiseSheet.autoSizeColumn((short) 1000);
				 //List<AgeRangeVO> boothWise = getBoothWiseForAConstituency(232l,10l,ageRanges);
				 
				 List<AgeRangeVO> panchayatWiseForBooth = getPanchayatWiseForBoothForAConstituency(constituecnyId,10l,ageRanges);
				 List<AgeRangeVO> panchayatWiseForBooth1 = new ArrayList<AgeRangeVO>();
				 for (AgeRangeVO ageRangeVO : panchayatWiseForBooth) {
					 ageRangeVO.setTehsilName(ageRangeVO.getTehsilName() + " Munciplity");
					 panchayatWiseForBooth1.add(ageRangeVO);
				}
				 
				/* if(panchayatWiseForBooth1 != null && panchayatWiseForBooth1.size() > 0)
				 {
					 boothWise.addAll(panchayatWiseForBooth1);
				 }*/
				 generateExcelsForBoothReport(panchayatWiseForBooth1,boothWiseSheet,workbook);
			}//111
			else
			{
				AgeRangeVO constituencyWise = getConstituencyWiseDetails(constituecnyId,10l,ageRanges);
				constituencyWiseSheet = workbook.createSheet("Constituency");
				constituencyWiseSheet.autoSizeColumn((short) 1000);
				generateExcelsForConstituency(constituencyWise,constituencyWiseSheet,workbook);		
				
				
				
				 // Mandal Wise Report Genereation
				 List<AgeRangeVO> muncipalityWise = getMunicipalityWiseForAConstituency(constituecnyId,10l,ageRanges);
				 List<AgeRangeVO> muncipalityWise1 = new ArrayList<AgeRangeVO>();//kavali---->kavali municipality.
				 for (AgeRangeVO ageRangeVO : muncipalityWise) {
					 ageRangeVO.setTehsilName(ageRangeVO.getTehsilName() + " Munciplity");
					 ageRangeVO.setPanchayatName(ageRangeVO.getPanchayatName() + " Munciplity");
					 muncipalityWise1.add(ageRangeVO);
				}
				
				List<AgeRangeVO> mandalWise = getMandalWiseForAConstituency(constituecnyId,10l,ageRanges);
				Collections.sort(mandalWise,mandalWiseSort);//dnso of mandalname.
				
				 if(muncipalityWise1 != null && muncipalityWise1.size() > 0)
				  mandalWise.addAll(muncipalityWise1);
				
				 mandalWiseSheet = workbook.createSheet("Mandal");
				 mandalWiseSheet.autoSizeColumn((short) 1000);
				 generateExcelsForMandalReport1(mandalWise,mandalWiseSheet,workbook);
				 
				
				
				 
				 // Panchayat Wise Report Generation
				// List<AgeRangeVO> muncipalityWise = getMunicipalityWiseForAConstituency(232l,10l,ageRanges);
				 //List<AgeRangeVO> muncipalityWise1 = new ArrayList<AgeRangeVO>();//kavali---->kavali municipality.
				/* for (AgeRangeVO ageRangeVO : muncipalityWise) {
					 ageRangeVO.setTehsilName(ageRangeVO.getTehsilName() + " Munciplity");
					 ageRangeVO.setPanchayatName(ageRangeVO.getPanchayatName() + " Munciplity");
					 muncipalityWise1.add(ageRangeVO);
				 }*/
				 List<AgeRangeVO> panchayatWise = getPanchayatWiseForAConstituency(constituecnyId,10l,ageRanges,false);
				 if(muncipalityWise1 != null && muncipalityWise1.size() > 0)
				 panchayatWise.addAll(muncipalityWise1);
				 panchayatwiseSheet = workbook.createSheet("Panchayat");
				 panchayatwiseSheet.autoSizeColumn((short) 1000);
				 generateExcelsForPanchayatReport(panchayatWise,panchayatwiseSheet,workbook);
				
				
				 
				
				 //Hamlet Wise Report Generation
				 hamletWiseSheet = workbook.createSheet("Hamlet");
				 hamletWiseSheet.autoSizeColumn((short) 1000);
				 List<AgeRangeVO> hamletWise = getHamletWiseDetailsForAConstituency(constituecnyId,10l,ageRanges);
				 Collections.sort(hamletWise,hamletWiseSort);
				 generateExcelsForHamletReport(hamletWise,hamletWiseSheet,workbook);
				
		 
				 
				
				//  Booth Wise Report Generation
				 boothWiseSheet = workbook.createSheet("Booth");
				 boothWiseSheet.autoSizeColumn((short) 1000);
				 List<AgeRangeVO> boothWise = getBoothWiseForAConstituency(constituecnyId,10l,ageRanges);
				 
				 List<AgeRangeVO> panchayatWiseForBooth = getPanchayatWiseForBoothForAConstituency(constituecnyId,10l,ageRanges);
				 List<AgeRangeVO> panchayatWiseForBooth1 = new ArrayList<AgeRangeVO>();
				 for (AgeRangeVO ageRangeVO : panchayatWiseForBooth) {
					 ageRangeVO.setTehsilName(ageRangeVO.getTehsilName() + " Munciplity");
					 panchayatWiseForBooth1.add(ageRangeVO);
				}
				 
				 if(panchayatWiseForBooth1 != null && panchayatWiseForBooth1.size() > 0)
				 {
					 boothWise.addAll(panchayatWiseForBooth1);
				 }
				 generateExcelsForBoothReport(boothWise,boothWiseSheet,workbook);
			}
			workbook.write(out);
			System.out.print("generated.");
			}
		
		
	 }
	catch(Exception e)
	{
		e.printStackTrace();
	}

}


public void generateExcelsForBoothReport(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook)
{
	 DecimalFormat df = new DecimalFormat("##.##");
	
	//report 1
	//Font font = workbook.createFont();
  //  font.setFontHeightInPoints((short)15);
    //font.setFontName("Arial");
    
    CellStyle style = workbook.createCellStyle();
    //style.setFont(font);
    style.setFillForegroundColor(HSSFColor.YELLOW.index);
    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    style.setAlignment(CellStyle.ALIGN_CENTER);
    HSSFRow  rowHead= sheet.createRow((short) 0);
    
    Cell cell = rowHead.createCell(1);
    cell.setCellValue("5. Age wise Information - Booth wise");
    //cell.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
    
    Row row1 = sheet.createRow(1);
    Cell cell1 = row1.createCell(0);
    cell1.setCellValue("Panchayat");
    cell1.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(1,2,0,0 ));
    
    cell1 = row1.createCell(1);
    cell1.setCellValue("Polling Station #");
    cell1.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(1,2,1,1 ));

    cell1 = row1.createCell(2);
    cell1.setCellValue("Total Voters");
    cell1.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(1,2,2,2 ));
   
    cell1 = row1.createCell(3);
    cell1.setCellValue("% in Constituency");
    cell1.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(1,2,3,3 ));
    
    int count = 0;
	int colCount = 4;
	for (String name : ageRanges)
	{
		    cell1 = row1.createCell(colCount);
		    cell1.setCellValue(name);
		    cell1.setCellStyle(style);
		    int spanCount = colCount+1;
		    sheet.addMergedRegion(new CellRangeAddress(1,1,colCount,spanCount));
		    count++;
		    colCount = spanCount;
		    colCount++;
	}
    	
	Row row2 = sheet.createRow(2);
	int colSpan = 4;
	for (String name : ageRanges)
    {
	    cell1 = row2.createCell(colSpan++);
	    cell1.setCellValue("Votes");
	    cell1.setCellStyle(style);
	    
	    cell1 = row2.createCell(colSpan++);
	    cell1.setCellValue("%");
	    cell1.setCellStyle(style);
	  //  colSpan++;
   }

	int rownum = 3;
	Long totalCountForAConstituency = 0l;
	for(AgeRangeVO VO : list)
	{
		totalCountForAConstituency = totalCountForAConstituency + VO.getTotalCount();
	}
    for(AgeRangeVO VO : list)
    {
    	HSSFRow  row4= sheet.createRow((short) rownum);
 	    Cell cell2 = row4.createCell(0);
 	    cell2.setCellValue(VO.getTehsilName());
 	   
 	    Cell cell3 = row4.createCell(1);
 	    cell3.setCellValue(Long.parseLong(VO.getPanchayatName()));
 	 
 	    Cell cell4 = row4.createCell(2);
	    cell4.setCellValue(VO.getTotalCount());
 	    
	    Cell cell5 = row4.createCell(3);
 	    cell5.setCellValue(Double.parseDouble(df.format((VO.getTotalCount()/totalCountForAConstituency.floatValue())*100 )));
 		
 	

        Map<String,AgeRangeVO >  mapp=VO.getMap();

 	    int cellnum=4;
 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
 	    {    
 	 	     AgeRangeVO VO1     =    mappp.getValue();
 	 		 Cell cell7 = row4.createCell(cellnum++);
 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
 	 		 
 	 		 Cell cell8 = row4.createCell(cellnum++);
 	 		 cell8.setCellValue(VO1.getAgeRangePerc());

 	   }
 	   rownum++;
   }
    
  
    
    
    rownum++;
    rownum++;
    rownum++;
    rownum++;
  // Report 2 
    
   
    HSSFRow  rowForReport2= sheet.createRow((short) rownum);
    Cell cellForReport2 = rowForReport2.createCell(0);
    cellForReport2.setCellValue("Panchayat");
    cellForReport2.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
    
    Cell cellForReport21 = rowForReport2.createCell(1);
    cellForReport21.setCellValue("Polling Station #");
    cellForReport21.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,1,1 ));
    
   // int countForReport2 = 0;
	int colCountForReport2 = 2;
	Cell cell12 = rowForReport2.createCell(colCountForReport2);
	cell12.setCellValue("Total Voters");
	cell12.setCellStyle(style);
    int spanCount = colCountForReport2+2;
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
    //countForReport2++;
    colCountForReport2 = spanCount;
    colCountForReport2++;
    
    //System.out.println(colCountForReport2);
    
	for (String name : ageRanges)
	{
		Cell cell123 = rowForReport2.createCell(colCountForReport2);
	    cell123.setCellValue(name);
	    cell123.setCellStyle(style);
	    spanCount = colCountForReport2+2;
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
	    //countForReport2++;
	    colCountForReport2 = spanCount;
	    colCountForReport2++;
	   // System.out.println(colCountForReport2);
	}
	rownum++;
	Row row2ForReport2 = sheet.createRow(rownum++);
	
	 Cell cell143= row2ForReport2.createCell(2);
	 cell143.setCellValue("Votes");
	 cell143.setCellStyle(style);
	    
	 cell143 = row2ForReport2.createCell(3);
	 cell143.setCellValue("Male");
	 cell143.setCellStyle(style);
	    
	 cell143 = row2ForReport2.createCell(4);
	 cell143.setCellValue("Female");
	 cell143.setCellStyle(style);
	int colSpanForReport2 = 5;
	for (String name : ageRanges)
    {
	   Cell cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Votes");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Male");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Female");
	   cell134.setCellStyle(style);
   }
	
	for(AgeRangeVO VO : list)
    {
    	HSSFRow  row4= sheet.createRow((short) rownum);
 	    Cell cell2 = row4.createCell(0);
 	    cell2.setCellValue(VO.getTehsilName());

 	    Cell cell21 = row4.createCell(1);
 	    cell21.setCellValue(Long.parseLong(VO.getPanchayatName()));
	    
        Cell cell73 = row4.createCell(2);
        cell73.setCellValue(VO.getTotalCount());
 		 
 		 Cell cell83 = row4.createCell(3);
 		cell83.setCellValue(VO.getTotalMaleCount());
 		 
 		 Cell cell93 = row4.createCell(4);
 		 cell93.setCellValue(VO.getTotalFemaleCount());
 		
 	    int cellnum=5;
 	    Map<String,AgeRangeVO >  mapp=VO.getMap();
 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
 	    {    
 	 	     AgeRangeVO VO1     =    mappp.getValue();
 	 	     if(VO1 != null)
 	 	     {
 	 	    	Cell cell7 = row4.createCell(cellnum++);
 	 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount()  != null ? VO1.getParticularAgeVotersCount():0);
 	 	 		 
 	 	 		 Cell cell8 = row4.createCell(cellnum++);
 	 	 		 cell8.setCellValue(VO1.getMaleCount() != null ?VO1.getMaleCount():0);
 	 	 	
 	 	 		 
 	 	 		 Cell cell9 = row4.createCell(cellnum++);
 	 	 		 cell9.setCellValue(VO1.getFemaleCount()  != null ? VO1.getFemaleCount():0);
 	 	     }
 	 	     else
 	 	     {
 	 	    	Cell cell7 = row4.createCell(cellnum++);
 	 	 		 cell7.setCellValue(0);
 	 	 		 
 	 	 		 Cell cell8 = row4.createCell(cellnum++);
 	 	 		 cell8.setCellValue(0);
 	 	 	
 	 	 		 
 	 	 		 Cell cell9 = row4.createCell(cellnum++);
 	 	 		 cell9.setCellValue(0);
 	 	     }
 	 		 
 	 		
 	   }
 	   	  
 	     rownum++;
   }
	rownum++;
    rownum++;
    rownum++;
    rownum++;
  // Report 3
    
   
    HSSFRow  rowForReport3= sheet.createRow((short) rownum);
    Cell cellForReport3 = rowForReport3.createCell(0);
    cellForReport3.setCellValue("Panchayat");
    cellForReport3.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
    
    Cell cellForReport33 = rowForReport3.createCell(1);
    cellForReport33.setCellValue("Polling Station #");
    cellForReport33.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,1,1 ));
    
   // int countForReport2 = 0;
	int colCountForReport3 = 2;
	Cell cell122 = rowForReport3.createCell(colCountForReport3);
	cell122.setCellValue("Total Voters");
	cell122.setCellStyle(style);
    int spanCount3 = colCountForReport3+2;
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
    //countForReport2++;
    colCountForReport3 = spanCount3;
    colCountForReport3++;
    
    //System.out.println(colCountForReport2);
    
	for (String name : ageRanges)
	{
		Cell cell123 = rowForReport3.createCell(colCountForReport3);
	    cell123.setCellValue(name);
	    cell123.setCellStyle(style);
	    spanCount3 = colCountForReport3+2;
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
	    //countForReport2++;
	    colCountForReport3 = spanCount3;
	    colCountForReport3++;
	   // System.out.println(colCountForReport2);
	}
	rownum++;
	Row row2ForReport3 = sheet.createRow(rownum++);
	
	 Cell cell13= row2ForReport3.createCell(2);
	 cell13.setCellValue("Votes");
	 cell13.setCellStyle(style);
	    
	 cell13 = row2ForReport3.createCell(3);
	 cell13.setCellValue("Male");
	 cell13.setCellStyle(style);
	    
	 cell13 = row2ForReport3.createCell(4);
	 cell13.setCellValue("Female");
	 cell13.setCellStyle(style);
	int colSpanForReport3 = 5;
	for (String name : ageRanges)
    {
	   Cell cell134 = row2ForReport3.createCell(colSpanForReport3++);
	   cell134.setCellValue("Votes");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport3.createCell(colSpanForReport3++);
	   cell134.setCellValue("Male");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport3.createCell(colSpanForReport3++);
	   cell134.setCellValue("Female");
	   cell134.setCellStyle(style);
   }
	
	for(AgeRangeVO VO : list)
    {
    	HSSFRow  row4= sheet.createRow((short) rownum);
 	    Cell cell2 = row4.createCell(0);
 	    cell2.setCellValue(VO.getTehsilName());

        Cell cell17 = row4.createCell(1);
        cell17.setCellValue(Long.parseLong(VO.getPanchayatName()));
        
        Cell cell73 = row4.createCell(2);
        cell73.setCellValue(VO.getTotalCount());
 		 
 		 Cell cell83 = row4.createCell(3);
 		   cell83.setCellValue(Double.parseDouble(df.format(((double)VO.getTotalMaleCount()*100)/VO.getTotalCount())));
 		
 		 Cell cell93 = row4.createCell(4);
 		 cell93.setCellValue(Double.parseDouble(df.format(((double)VO.getTotalFemaleCount()*100)/VO.getTotalCount())));
 		Long totlCountForAPollingstation = VO.getTotalCount();
 		
 		Map<String,AgeRangeVO >  mapp=VO.getMap();
 	    int cellnum=5;
 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
 	    {    
 	 	     AgeRangeVO VO1     =    mappp.getValue();
 	 		 Cell cell7 = row4.createCell(cellnum++);
 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
 	 		 
 	 		 Cell cell8 = row4.createCell(cellnum++);
 	 		 cell8.setCellValue(VO1.getMaleCount() != null ? Double.parseDouble(df.format(VO1.getMaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100))  : 0.0);
 	 		

 	 		 Cell cell9 = row4.createCell(cellnum++);
 	  		 cell9.setCellValue(VO1.getFemaleCount() != null ? Double.parseDouble(df.format(VO1.getFemaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100)) : 0.0);

 	   }
 	
	  
 	   rownum++;
   }
}


	
	
public void generateExcelsForHamletReport(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook)
{
	DecimalFormat df = new DecimalFormat("##.##");
    //report 1	
	//Font font = workbook.createFont();
   // font.setFontHeightInPoints((short)15);
    //font.setFontName("Arial");
    CellStyle style = workbook.createCellStyle();
    style.setFillForegroundColor(HSSFColor.YELLOW.index);
    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    style.setAlignment(CellStyle.ALIGN_CENTER);
   // style.setFont(font);
    HSSFRow  rowHead= sheet.createRow((short) 0);
    
    Cell cell = rowHead.createCell(1);
    cell.setCellValue("4. Age wise Information - Hamlet wise");
    //cell.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
    
    Row row1 = sheet.createRow(1);
    Cell cell1 = row1.createCell(0);
    cell1.setCellValue("Panchayat");
    cell1.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(1,2,0,0 ));
    
    cell1 = row1.createCell(1);
    cell1.setCellValue("Hamlet");
    cell1.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(1,2,1,1 ));

    cell1 = row1.createCell(2);
    cell1.setCellValue("Total Voters");
    cell1.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(1,2,2,2 ));
   
    
    
    int count = 0;
	int colCount = 3;
	for (String name : ageRanges)
	{
		    cell1 = row1.createCell(colCount);
		    cell1.setCellValue(name);
		    cell1.setCellStyle(style);
		    int spanCount = colCount+1;
		    sheet.addMergedRegion(new CellRangeAddress(1,1,colCount,spanCount));
		    count++;
		    colCount = spanCount;
		    colCount++;
	}
    	
	Row row2 = sheet.createRow(2);
	int colSpan = 3;
	for (String name : ageRanges)
    {
	    cell1 = row2.createCell(colSpan++);
	    cell1.setCellValue("Votes");
	    cell1.setCellStyle(style);
	    
	    cell1 = row2.createCell(colSpan++);
	    cell1.setCellValue("%");
	    cell1.setCellStyle(style);
	  //  colSpan++;
   }

	int rownum = 3;
	for(AgeRangeVO VO : list)
    {
    	HSSFRow  row4= sheet.createRow((short) rownum);
 	    Cell cell2 = row4.createCell(0);
 	    cell2.setCellValue(VO.getPanchayatName());
 	   
 	    Cell cell3 = row4.createCell(1);
 	    cell3.setCellValue(VO.getHamletName());
 	    
 	    Cell cell4 = row4.createCell(2);
 	    cell4.setCellValue(VO.getTotalCount());
 		
 	    
        Map<String,AgeRangeVO >  mapp=VO.getMap();
 	    int cellnum=3;
 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
 	    {    
 	 	     AgeRangeVO VO1     =    mappp.getValue();
 	 		 Cell cell7 = row4.createCell(cellnum++);
 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount()>0 ? VO1.getParticularAgeVotersCount() :0);
 	 		 Cell cell8 = row4.createCell(cellnum++);
 	 		 cell8.setCellValue(VO1.getAgeRangePerc()>0 ? VO1.getAgeRangePerc():0.00 );
 	 
 	 	 }
 	   rownum++;
   }
    
  
    
    
    rownum++;
    rownum++;
    rownum++;
    rownum++;
  // Report 2 
    
  
    HSSFRow  rowForReport2= sheet.createRow((short) rownum);
    Cell cellForReport2 = rowForReport2.createCell(0);
    cellForReport2.setCellValue("Panchayat");
    cellForReport2.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
    
    Cell cellForReport21 = rowForReport2.createCell(1);
    cellForReport21.setCellValue("Hamlet");
    cellForReport21.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,1,1 ));
    
   // int countForReport2 = 0;
	int colCountForReport2 = 2;
	Cell cell12 = rowForReport2.createCell(colCountForReport2);
	cell12.setCellValue("Total Voters");
	cell12.setCellStyle(style);
    int spanCount = colCountForReport2+2;
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
    //countForReport2++;
    colCountForReport2 = spanCount;
    colCountForReport2++;
    
    //System.out.println(colCountForReport2);
    
	for (String name : ageRanges)
	{
		Cell cell123 = rowForReport2.createCell(colCountForReport2);
	    cell123.setCellValue(name);
	    cell123.setCellStyle(style);
	    spanCount = colCountForReport2+2;
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
	    //countForReport2++;
	    colCountForReport2 = spanCount;
	    colCountForReport2++;
	   // System.out.println(colCountForReport2);
	}
	rownum++;
	Row row2ForReport2 = sheet.createRow(rownum++);
	
	 Cell cell143= row2ForReport2.createCell(2);
	 cell143.setCellValue("Votes");
	 cell143.setCellStyle(style);
	    
	 cell143 = row2ForReport2.createCell(3);
	 cell143.setCellValue("Male");
	 cell143.setCellStyle(style);
	    
	 cell143 = row2ForReport2.createCell(4);
	 cell143.setCellValue("Female");
	 cell143.setCellStyle(style);
	int colSpanForReport2 = 5;
	for (String name : ageRanges)
    {
	   Cell cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Votes");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Male");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Female");
	   cell134.setCellStyle(style);
   }
	
	for(AgeRangeVO VO : list)
    {
    	HSSFRow  row4= sheet.createRow((short) rownum);
 	    Cell cell2 = row4.createCell(0);
 	    cell2.setCellValue(VO.getPanchayatName());

 	    Cell cell21 = row4.createCell(1);
 	    cell21.setCellValue(VO.getHamletName());
	    
        Cell cell73 = row4.createCell(2);
        cell73.setCellValue(VO.getTotalCount());
 		 
 		 Cell cell83 = row4.createCell(3);
 		 cell83.setCellValue(VO.getTotalMaleCount());
 		 
 		 Cell cell93 = row4.createCell(4);
 		 cell93.setCellValue(VO.getTotalFemaleCount());	
 		 
 		 int cellnum=5;
 	    Map<String,AgeRangeVO >  mapp=VO.getMap();
 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
 	    {    
 	 	     AgeRangeVO VO1     =   mappp.getValue();
 	 		 Cell cell7 = row4.createCell(cellnum++);
 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
 	 		 
 	 		 Cell cell8 = row4.createCell(cellnum++);
 	 		 cell8.setCellValue(VO1.getMaleCount()!=null&&VO1.getMaleCount()>0 ?VO1.getMaleCount():0);
 	 		 
 	 		 Cell cell9 = row4.createCell(cellnum++);
             cell9.setCellValue(VO1.getFemaleCount()!=null && VO1.getFemaleCount()>0 ? VO1.getFemaleCount():0);
 	 		}
 	 rownum++;
   }
	
	rownum++;
    rownum++;
    rownum++;
    rownum++;
  // Report 3
    
   
    HSSFRow  rowForReport3= sheet.createRow((short) rownum);
    Cell cellForReport3 = rowForReport3.createCell(0);
    cellForReport3.setCellValue("Panchayat");
    cellForReport3.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
    
    Cell cellForReport33 = rowForReport3.createCell(1);
    cellForReport33.setCellValue("Hamlet");
    cellForReport33.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,1,1 ));
    
   // int countForReport2 = 0;
	int colCountForReport3 = 2;
	Cell cell122 = rowForReport3.createCell(colCountForReport3);
	cell122.setCellValue("Total Voters");
	cell122.setCellStyle(style);
    int spanCount3 = colCountForReport3+2;
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
    //countForReport2++;
    colCountForReport3 = spanCount3;
    colCountForReport3++;
    
    //System.out.println(colCountForReport2);
    
	for (String name : ageRanges)
	{
		Cell cell123 = rowForReport3.createCell(colCountForReport3);
	    cell123.setCellValue(name);
	    cell123.setCellStyle(style);
	    spanCount3 = colCountForReport3+2;
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
	    //countForReport2++;
	    colCountForReport3 = spanCount3;
	    colCountForReport3++;
	   // System.out.println(colCountForReport2);
	}
	rownum++;
	Row row2ForReport3 = sheet.createRow(rownum++);
	
	 Cell cell13= row2ForReport3.createCell(2);
	 cell13.setCellValue("Votes");
	 cell13.setCellStyle(style);
	    
	 cell13 = row2ForReport3.createCell(3);
	 cell13.setCellValue("Male");
	 cell13.setCellStyle(style);
	    
	 cell13 = row2ForReport3.createCell(4);
	 cell13.setCellValue("Female");
	 cell13.setCellStyle(style);
	int colSpanForReport3 = 5;
	for (String name : ageRanges)
    {
	   Cell cell134 = row2ForReport3.createCell(colSpanForReport3++);
	   cell134.setCellValue("Votes");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport3.createCell(colSpanForReport3++);
	   cell134.setCellValue("Male");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport3.createCell(colSpanForReport3++);
	   cell134.setCellValue("Female");
	   cell134.setCellStyle(style);
   }
	
	for(AgeRangeVO VO : list)
    {
    	HSSFRow  row4= sheet.createRow((short) rownum);
 	    Cell cell2 = row4.createCell(0);
 	    cell2.setCellValue(VO.getPanchayatName());

        
        Cell cell17 = row4.createCell(1);
        cell17.setCellValue(VO.getHamletName());
        
        Cell cell73 = row4.createCell(2);
        cell73.setCellValue(VO.getTotalCount());
 		 
 		 Cell cell83 = row4.createCell(3);
 		 cell83.setCellValue(Double.parseDouble(df.format(((double)VO.getTotalMaleCount()*100)/VO.getTotalCount())));
 		
 		 Cell cell93 = row4.createCell(4);
 		 cell93.setCellValue(Double.parseDouble(df.format(((double)VO.getTotalFemaleCount()*100)/VO.getTotalCount())));
 		 
 	   int cellnum=5;
 	   Map<String,AgeRangeVO >  mapp=VO.getMap();
       for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
 	    {    
 	 	     AgeRangeVO VO1     =    mappp.getValue();
 	 		 Cell cell7 = row4.createCell(cellnum++);
 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
 	 		 
 	 		 Cell cell8 = row4.createCell(cellnum++);
 	 		 cell8.setCellValue(VO1.getMaleCount()!=null ? Double.parseDouble(df.format(VO1.getMaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100)):0.00 );
 	 		
 	 		 Cell cell9 = row4.createCell(cellnum++);
 	 		 cell9.setCellValue(VO1.getFemaleCount()!=null ? Double.parseDouble(df.format( VO1.getFemaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100)) :0.00);
 	 	}
 	rownum++;
   }
}
public void generateExcelsForPanchayatReport(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook)
{
	 DecimalFormat df = new DecimalFormat("##.##");
	 //report1
	//Font font = workbook.createFont();
    //font.setFontHeightInPoints((short)15);
    //font.setFontName("Arial");
    CellStyle style = workbook.createCellStyle();
    style.setFillForegroundColor(HSSFColor.YELLOW.index);
    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    style.setAlignment(CellStyle.ALIGN_CENTER);
   // style.setFont(font);
    HSSFRow  rowHead= sheet.createRow((short) 0);
    
    Cell cell = rowHead.createCell(1);
    cell.setCellValue("3. Age wise Information - Panchayath");
    //cell.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
    
    Row row1 = sheet.createRow(1);
    Cell cell1 = row1.createCell(0);
    cell1.setCellValue("Mandal");
    cell1.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(1,2,0,0 ));
    
    cell1 = row1.createCell(1);
    cell1.setCellValue("Panchayat");
    cell1.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(1,2,1,1 ));

    cell1 = row1.createCell(2);
    cell1.setCellValue("Total Voters");
    cell1.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(1,2,2,2 ));
   
    cell1 = row1.createCell(3);
    cell1.setCellValue("% of age in Constituency");
    cell1.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(1,2,3,3));
    
    cell1 = row1.createCell(4);
    cell1.setCellValue("% of age in Mandal");
    cell1.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(1,2,4,4));
    
    int count = 0;
	int colCount = 5;
	for (String name : ageRanges)
	{
		    cell1 = row1.createCell(colCount);
		    cell1.setCellValue(name);
		    cell1.setCellStyle(style);
		    int spanCount = colCount+1;
		    sheet.addMergedRegion(new CellRangeAddress(1,1,colCount,spanCount));
		    count++;
		    colCount = spanCount;
		    colCount++;
	}
    
	Row row2 = sheet.createRow(2);
	int colSpan = 5;
	for (String name : ageRanges)
    {
	    cell1 = row2.createCell(colSpan++);
	    cell1.setCellValue("Votes");
	    cell1.setCellStyle(style);
	    
	    cell1 = row2.createCell(colSpan++);
	    cell1.setCellValue("%");
	    cell1.setCellStyle(style);
	  //  colSpan++;
   }

	int rownum = 3;
	Long totalCountForAConstituency = 0l;
	
	for(AgeRangeVO VO : list)
	{
		totalCountForAConstituency = totalCountForAConstituency + VO.getTotalCount();
	}
	
	Map<String,Long> mandalwiseCount = new HashMap<String, Long>();
	for(AgeRangeVO VO1 :  list)
	{
		Long totalCount = mandalwiseCount.get(VO1.getTehsilName());
		if(totalCount == null)
		{
			totalCount = VO1.getTotalCount();
			mandalwiseCount.put(VO1.getTehsilName(), totalCount);
		}
		else
		{
			totalCount = totalCount + VO1.getTotalCount();
			mandalwiseCount.put(VO1.getTehsilName(), totalCount);
		}
	}
    for(AgeRangeVO VO : list)
    {
    	HSSFRow  row4= sheet.createRow((short) rownum);
 	    Cell cell2 = row4.createCell(0);
 	    cell2.setCellValue(VO.getTehsilName());
 	   
 	    Cell cell3 = row4.createCell(1);
 	    cell3.setCellValue(VO.getPanchayatName());
 	    
 	    Cell cell4 = row4.createCell(2);
 	    cell4.setCellValue(VO.getTotalCount());
 		
 	    double percantageInConstituency = Double.parseDouble(df.format((VO.getTotalCount()/totalCountForAConstituency.floatValue())*100 ));
 	    Cell cell6= row4.createCell(3);
 	    cell6.setCellValue(percantageInConstituency);
 	    
 	    Cell cell73= row4.createCell(4);
 	    Long mandalCount1 =  mandalwiseCount.get(VO.getTehsilName());
 	    Double percentage1 = Double.parseDouble(df.format((VO.getTotalCount()/mandalCount1.doubleValue() )*100));
 	    cell73.setCellValue(percentage1);

        Map<String,AgeRangeVO >  mapp=VO.getMap();

 	    int cellnum=5;
 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
 	    {    
 	 	     AgeRangeVO VO1     =    mappp.getValue();
 	 		 Cell cell7 = row4.createCell(cellnum++);
 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
 	 		 
 	 		 Cell cell8 = row4.createCell(cellnum++);
 	 		 cell8.setCellValue(VO1.getAgeRangePerc());

 	   }
 	   rownum++;
   }
    
  
    
    
    rownum++;
    rownum++;
    rownum++;
    rownum++;
  // Report 2 
    
   
    HSSFRow  rowForReport2= sheet.createRow((short) rownum);
    Cell cellForReport2 = rowForReport2.createCell(0);
    cellForReport2.setCellValue("Mandal");
    cellForReport2.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
    
    Cell cellForReport21 = rowForReport2.createCell(1);
    cellForReport21.setCellValue("Panchayat");
    cellForReport21.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,1,1 ));
    
   // int countForReport2 = 0;
	int colCountForReport2 = 2;
	Cell cell12 = rowForReport2.createCell(colCountForReport2);
	cell12.setCellValue("Total Voters");
	cell12.setCellStyle(style);
    int spanCount = colCountForReport2+2;
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
    //countForReport2++;
    colCountForReport2 = spanCount;
    colCountForReport2++;
    
    //System.out.println(colCountForReport2);
    
	for (String name : ageRanges)
	{
		Cell cell123 = rowForReport2.createCell(colCountForReport2);
	    cell123.setCellValue(name);
	    cell123.setCellStyle(style);
	    spanCount = colCountForReport2+2;
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
	    //countForReport2++;
	    colCountForReport2 = spanCount;
	    colCountForReport2++;
	   // System.out.println(colCountForReport2);
	}
	rownum++;
	Row row2ForReport2 = sheet.createRow(rownum++);
	
	 Cell cell143= row2ForReport2.createCell(2);
	 cell143.setCellValue("Votes");
	 cell143.setCellStyle(style);
	    
	 cell143 = row2ForReport2.createCell(3);
	 cell143.setCellValue("Male");
	 cell143.setCellStyle(style);
	    
	 cell143 = row2ForReport2.createCell(4);
	 cell143.setCellValue("Female");
	 cell143.setCellStyle(style);
	int colSpanForReport2 = 5;
	for (String name : ageRanges)
    {
	   Cell cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Votes");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Male");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport2.createCell(colSpanForReport2++);
	   cell134.setCellValue("Female");
	   cell134.setCellStyle(style);
   }
	
	for(AgeRangeVO VO : list)
    {
    	HSSFRow  row4= sheet.createRow((short) rownum);
 	    Cell cell2 = row4.createCell(0);
 	    cell2.setCellValue(VO.getTehsilName());

 	    Cell cell21 = row4.createCell(1);
 	    cell21.setCellValue(VO.getPanchayatName());
	    
        Map<String,AgeRangeVO >  mapp=VO.getMap();
        
        Cell cell73 = row4.createCell(2);
        cell73.setCellValue(VO.getTotalCount());
 		 
 		 Cell cell83 = row4.createCell(3);
 		 cell83.setCellValue(VO.getTotalMaleCount());
 		 
 		 Cell cell93 = row4.createCell(4);
 		 cell93.setCellValue(VO.getTotalFemaleCount());
 		
 	    int cellnum=5;
 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
 	    {    
 	 	     AgeRangeVO VO1 = mappp.getValue();
 	 		 Cell cell7 = row4.createCell(cellnum++);
 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount() != null ? VO1.getParticularAgeVotersCount() : 0);
 	         Cell cell8 = row4.createCell(cellnum++);
 	 		 cell8.setCellValue(VO1.getMaleCount() != null ? VO1.getMaleCount() : 0);
 	 		 Cell cell9 = row4.createCell(cellnum++);
 	 		 cell9.setCellValue(VO1.getFemaleCount() != null ? VO1.getFemaleCount() : 0);
 	   }
 	  
 	   rownum++;
    }
	rownum++;
    rownum++;
    rownum++;
    rownum++;
  // Report 3
  
    HSSFRow  rowForReport3= sheet.createRow((short) rownum);
    Cell cellForReport3 = rowForReport3.createCell(0);
    cellForReport3.setCellValue("Mandal");
    cellForReport3.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
    
    Cell cellForReport33 = rowForReport3.createCell(1);
    cellForReport33.setCellValue("Panchayat");
    cellForReport33.setCellStyle(style);
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,1,1 ));
    
   // int countForReport2 = 0;
	int colCountForReport3 = 2;
	Cell cell122 = rowForReport3.createCell(colCountForReport3);
	cell122.setCellValue("Total Voters");
	cell122.setCellStyle(style);
    int spanCount3 = colCountForReport3+2;
    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
    //countForReport2++;
    colCountForReport3 = spanCount3;
    colCountForReport3++;
    
    //System.out.println(colCountForReport2);
    
	for (String name : ageRanges)
	{
		Cell cell123 = rowForReport3.createCell(colCountForReport3);
	    cell123.setCellValue(name);
	    cell123.setCellStyle(style);
	    spanCount3 = colCountForReport3+2;
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
	    //countForReport2++;
	    colCountForReport3 = spanCount3;
	    colCountForReport3++;
	   // System.out.println(colCountForReport2);
	}
	rownum++;
	Row row2ForReport3 = sheet.createRow(rownum++);
	
	 Cell cell13= row2ForReport3.createCell(2);
	 cell13.setCellValue("Votes");
	 cell13.setCellStyle(style);
	    
	 cell13 = row2ForReport3.createCell(3);
	 cell13.setCellValue("Male");
	 cell13.setCellStyle(style);
	    
	 cell13 = row2ForReport3.createCell(4);
	 cell13.setCellValue("Female");
	 cell13.setCellStyle(style);
	int colSpanForReport3 = 5;
	for (String name : ageRanges)
    {
	   Cell cell134 = row2ForReport3.createCell(colSpanForReport3++);
	   cell134.setCellValue("Votes");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport3.createCell(colSpanForReport3++);
	   cell134.setCellValue("Male");
	   cell134.setCellStyle(style);
	    
	   cell134 = row2ForReport3.createCell(colSpanForReport3++);
	   cell134.setCellValue("Female");
	   cell134.setCellStyle(style);
   }
	
	for(AgeRangeVO VO : list)
    {
    	HSSFRow  row4= sheet.createRow((short) rownum);
 	    Cell cell2 = row4.createCell(0);
 	    cell2.setCellValue(VO.getTehsilName());

        Map<String,AgeRangeVO >  mapp=VO.getMap();
        
        Cell cell17 = row4.createCell(1);
        cell17.setCellValue(VO.getPanchayatName());
        
        Cell cell73 = row4.createCell(2);
        cell73.setCellValue(VO.getTotalCount());
 		 
 		 Cell cell83 = row4.createCell(3);
 	 	 cell83.setCellValue(VO.getTotalMaleCount() != null ? Double.parseDouble(df.format(((double)VO.getTotalMaleCount()*100)/VO.getTotalCount())):0.00);

 		 Long totlCountForAPanchayat = VO.getTotalCount();
 		 Cell cell93 = row4.createCell(4);
 	 	 cell93.setCellValue(VO.getTotalFemaleCount() != null ? Double.parseDouble(df.format(((double)VO.getTotalFemaleCount()*100)/VO.getTotalCount())):0.00);
 	    int cellnum=5;
 	   
		 
 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
 	    {    
 	 	     AgeRangeVO VO1     =    mappp.getValue();
 	 		 Cell cell7 = row4.createCell(cellnum++);
 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
 	 		 
 	 		 Cell cell8 = row4.createCell(cellnum++);
 	 		 cell8.setCellValue(Double.parseDouble(df.format(VO1.getMaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100)) );
 	 		 
 	 		 
 	 		 Cell cell9 = row4.createCell(cellnum++);
 	 		 cell9.setCellValue(Double.parseDouble(df.format(VO1.getFemaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100)) );
 	   }
 	   rownum++;
   }
}
	
	 


public void generateExcelsForMandalReport1(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook)
{      
	  DecimalFormat df = new DecimalFormat("##.##");
	
	    //Font font = workbook.createFont();
	   // font.setFontHeightInPoints((short)15);
	    //font.setFontName("Arial");
	    CellStyle style = workbook.createCellStyle();
	    style.setFillForegroundColor(HSSFColor.YELLOW.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	  //  style.setFont(font);
	    HSSFRow  rowHead= sheet.createRow((short) 0);
	    
	    Cell cell = rowHead.createCell(1);
	    cell.setCellValue("2. Age wise Information - Mandal Wise");
	   // cell.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
	    
	    Row row1 = sheet.createRow(1);
	    Cell cell1 = row1.createCell(0);
	    cell1.setCellValue("Mandal");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,0,0 ));
	    
	    cell1 = row1.createCell(1);
	    cell1.setCellValue("Total Voters");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,1,1 ));

	    cell1 = row1.createCell(2);
	    cell1.setCellValue("% in Constituency");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,2,2 ));
	    
	    int count = 0;
		int colCount = 3;
		for (String name : ageRanges)
		{
			    cell1 = row1.createCell(colCount);
			    cell1.setCellValue(name);
			    cell1.setCellStyle(style);
			    int spanCount = colCount+1;
			    sheet.addMergedRegion(new CellRangeAddress(1,1,colCount,spanCount));
			    count++;
			    colCount = spanCount;
			    colCount++;
		}
	    	
		Row row2 = sheet.createRow(2);
		int colSpan = 3;
		for (String name : ageRanges)
	    {
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("Votes");
		    cell1.setCellStyle(style);
		    
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("%");
		    cell1.setCellStyle(style);
		  //  colSpan++;
	   }
   
		int rownum = 3;
		Long totalCountForAConstituency = 0l;
		for(AgeRangeVO VO : list)
		{
			totalCountForAConstituency = totalCountForAConstituency + VO.getTotalCount();
		}
        for(AgeRangeVO VO : list)
        {  
	    	HSSFRow  row4= sheet.createRow((short) rownum);
	 	    Cell cell2 = row4.createCell(0);
	 	    cell2.setCellValue(VO.getTehsilName());
	 	   
	 	    Cell cell3 = row4.createCell(1);
	 	    cell3.setCellValue(VO.getTotalCount());
	 	   
	 		
	 	    Cell cell6= row4.createCell(2);
	 	    cell6.setCellValue(Double.parseDouble(df.format(((double)VO.getTotalCount()*100)/totalCountForAConstituency)));
	
	        Map<String,AgeRangeVO >  mapp=VO.getMap();
	
	 	    int cellnum=3;
	 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	 	    {    
	 	 	     AgeRangeVO VO1     =    mappp.getValue();
	 	 		 Cell cell7 = row4.createCell(cellnum++);
	 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
	 	 		 
	 	 		 Cell cell8 = row4.createCell(cellnum++);
	 	 		 cell8.setCellValue(VO1.getAgeRangePerc());
	
	 	   }
	 	   rownum++;
       }
        
      
        
        
        rownum++;
        rownum++;
        rownum++;
        rownum++;
      // Report 2 
        
       
        HSSFRow  rowForReport2= sheet.createRow((short) rownum);
        Cell cellForReport2 = rowForReport2.createCell(0);
        cellForReport2.setCellValue("Mandal");
        cellForReport2.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
	    
	   // int countForReport2 = 0;
		int colCountForReport2 = 1;
		Cell cell12 = rowForReport2.createCell(colCountForReport2);
		cell12.setCellValue("Total Voters");
		cell12.setCellStyle(style);
	    int spanCount = colCountForReport2+2;
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
	    //countForReport2++;
	    colCountForReport2 = spanCount;
	    colCountForReport2++;
	    
	    //System.out.println(colCountForReport2);
	    
		for (String name : ageRanges)
		{
			Cell cell123 = rowForReport2.createCell(colCountForReport2);
		    cell123.setCellValue(name);
		    cell123.setCellStyle(style);
		    spanCount = colCountForReport2+2;
		    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport2,spanCount));
		    //countForReport2++;
		    colCountForReport2 = spanCount;
		    colCountForReport2++;
		   // System.out.println(colCountForReport2);
		}
		rownum++;
		Row row2ForReport2 = sheet.createRow(rownum++);
		
		 Cell cell143= row2ForReport2.createCell(1);
		 cell143.setCellValue("Votes");
		 cell143.setCellStyle(style);
		    
		 cell143 = row2ForReport2.createCell(2);
		 cell143.setCellValue("Male");
		 cell143.setCellStyle(style);
		    
		 cell143 = row2ForReport2.createCell(3);
		 cell143.setCellValue("Female");
		 cell143.setCellStyle(style);
		int colSpanForReport2 = 4;
		for (String name : ageRanges)
	    {
		   Cell cell134 = row2ForReport2.createCell(colSpanForReport2++);
		   cell134.setCellValue("Votes");
		   cell134.setCellStyle(style);
		    
		   cell134 = row2ForReport2.createCell(colSpanForReport2++);
		   cell134.setCellValue("Male");
		   cell134.setCellStyle(style);
		    
		   cell134 = row2ForReport2.createCell(colSpanForReport2++);
		   cell134.setCellValue("Female");
		   cell134.setCellStyle(style);
	   }
		
		
		for(AgeRangeVO VO : list)
        {
			Long maleCountForAMandal=0l;
			Long femaleCountForAMandal=0l;
	    	HSSFRow  row4= sheet.createRow((short) rownum);
	 	    Cell cell2 = row4.createCell(0);
	 	    cell2.setCellValue(VO.getTehsilName());
	
	        Map<String,AgeRangeVO >  mapp=VO.getMap();
	        
	        Cell cell73 = row4.createCell(1);
	        cell73.setCellValue(VO.getTotalCount());
	 		 
	 		 Cell cell83 = row4.createCell(2);
	 		cell83.setCellValue(VO.getTotalMaleCount());
	 		Cell cell93 = row4.createCell(3);
	 		cell93.setCellValue(VO.getTotalFemaleCount());
	 	    int cellnum=4;
	 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	 	    {    
	 	 	     AgeRangeVO VO1     =    mappp.getValue();
	 	 		 Cell cell7 = row4.createCell(cellnum++);
	 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
	 	 		 
	 	 		 Cell cell8 = row4.createCell(cellnum++);
	 	 		 cell8.setCellValue(VO1.getMaleCount());
	 	 		
	 	 		 Cell cell9 = row4.createCell(cellnum++);
	 	 		 cell9.setCellValue(VO1.getFemaleCount());
	 	 		
	 	   }
	 	

	 	   rownum++;
       }
		
		
		rownum++;
        rownum++;
        rownum++;
        rownum++;
      // Report 3
        
       
        HSSFRow  rowForReport3= sheet.createRow((short) rownum);
        Cell cellForReport3 = rowForReport3.createCell(0);
        cellForReport3.setCellValue("Mandal");
        cellForReport3.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+1,0,0 ));
	    
	   // int countForReport2 = 0;
		int colCountForReport3 = 1;
		Cell cell122 = rowForReport3.createCell(colCountForReport3);
		cell122.setCellValue("Total Voters");
		cell122.setCellStyle(style);
	    int spanCount3 = colCountForReport3+2;
	    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
	    //countForReport2++;
	    colCountForReport3 = spanCount3;
	    colCountForReport3++;
	    
	    //System.out.println(colCountForReport2);
	    
		for (String name : ageRanges)
		{
			Cell cell123 = rowForReport3.createCell(colCountForReport3);
		    cell123.setCellValue(name);
		    cell123.setCellStyle(style);
		    spanCount3 = colCountForReport3+2;
		    sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colCountForReport3,spanCount3));
		    //countForReport2++;
		    colCountForReport3 = spanCount3;
		    colCountForReport3++;
		   // System.out.println(colCountForReport2);
		}
		rownum++;
		Row row2ForReport3 = sheet.createRow(rownum++);
		
		 Cell cell13= row2ForReport3.createCell(1);
		 cell13.setCellValue("Votes");
		 cell13.setCellStyle(style);
		    
		 cell13 = row2ForReport3.createCell(2);
		 cell13.setCellValue("Male");
		 cell13.setCellStyle(style);
		    
		 cell13 = row2ForReport3.createCell(3);
		 cell13.setCellValue("Female");
		 cell13.setCellStyle(style);
		int colSpanForReport3 = 4;
		for (String name : ageRanges)
	    {
		   Cell cell134 = row2ForReport3.createCell(colSpanForReport3++);
		   cell134.setCellValue("Votes");
		   cell134.setCellStyle(style);
		    
		   cell134 = row2ForReport3.createCell(colSpanForReport3++);
		   cell134.setCellValue("Male");
		   cell134.setCellStyle(style);
		    
		   cell134 = row2ForReport3.createCell(colSpanForReport3++);
		   cell134.setCellValue("Female");
		   cell134.setCellStyle(style);
	   }
		
		for(AgeRangeVO VO : list)
        {   
			Long maleCountForAMandal=0l;
			Long femaleCountForAMandal=0l;
			
	    	HSSFRow  row4= sheet.createRow((short) rownum);
	 	    Cell cell2 = row4.createCell(0);
	 	    cell2.setCellValue(VO.getTehsilName());
	
	        Map<String,AgeRangeVO >  mapp=VO.getMap();
	        
	        Cell cell73 = row4.createCell(1);
	        cell73.setCellValue(VO.getTotalCount());
	        
	 		 
	 		 Cell cell83 = row4.createCell(2);
	 		cell83.setCellValue(Double.parseDouble(df.format(((double)VO.getTotalMaleCount()*100)/VO.getTotalCount() )));
	 		
	 		 Cell cell93 = row4.createCell(3);
	 		cell93.setCellValue(Double.parseDouble(df.format(((double)VO.getTotalFemaleCount()*100)/VO.getTotalCount() )));
	 	    int cellnum=4;
	 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	 	    {    
	 	 	     AgeRangeVO VO1     =    mappp.getValue();
	 	 		 Cell cell7 = row4.createCell(cellnum++);
	 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
	 	 		 
	 	 		 Cell cell8 = row4.createCell(cellnum++);
	 	 		 cell8.setCellValue(Double.parseDouble(df.format(VO1.getMaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100)));
	 	 		
	 	 		 
	 	 		 Cell cell9 = row4.createCell(cellnum++);
	 	 		 cell9.setCellValue(Double.parseDouble(df.format(VO1.getFemaleCount()/VO1.getParticularAgeVotersCount().doubleValue()*100)));
	 	 		
	 	   }
	 	
	 	   rownum++;
       }
}

/*public void generateExcelsForMandalReport2(List<AgeRangeVO>  list ,HSSFSheet sheet,HSSFWorkbook workbook)
{
	    Font font = workbook.createFont();
	    font.setFontHeightInPoints((short)15);
	    font.setFontName("Arial");
	    CellStyle style = workbook.createCellStyle();
	    style.setFont(font);
	    HSSFRow  rowHead= sheet.createRow((short) 0);
	    
	    Cell cell = rowHead.createCell(1);
	    cell.setCellValue("2. Age wise Information - Mandal Wise");
	    cell.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
	    
	    Row row1 = sheet.createRow(1);
	    Cell cell1 = row1.createCell(0);
	    cell1.setCellValue("Mandal");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,0,0 ));
	    
	    cell1 = row1.createCell(1);
	    cell1.setCellValue("Total Voters");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,1,1 ));

	    cell1 = row1.createCell(2);
	    cell1.setCellValue("% in Constituency");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,2,2 ));
	    
	    int count = 0;
		int colCount = 3;
		for (String name : ageRanges)
		{
			  cell1 = row1.createCell(colCount);
			    cell1.setCellValue(name);
			    cell1.setCellStyle(style);
			    int spanCount = colCount+1;
			    sheet.addMergedRegion(new CellRangeAddress(1,1,colCount,spanCount));
			    count++;
			    colCount = spanCount;
			    colCount++;
		}
	    	
		Row row2 = sheet.createRow(2);
		int colSpan = 2;
		for (String name : ageRanges)
	    {
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("Votes");
		    cell1.setCellStyle(style);
		    
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("%");
		    cell1.setCellStyle(style);
	   }
   
		int rownum = 3;
		Long total = 0l;
		for(AgeRangeVO VO : list)
		{
			total = total + VO.getTotalCount();
		}
        for(AgeRangeVO VO : list)
        {
	    	HSSFRow  row4= sheet.createRow((short) rownum);
	 	    Cell cell2 = row4.createCell(0);
	 	    cell2.setCellValue(VO.getTehsilName());
	 	   
	 	    Cell cell3 = row4.createCell(1);
	 	    cell3.setCellValue(VO.getTotalCount());
	 		
	 	    float perc = (VO.getTotalCount()/total.floatValue())*100;
	 	    Cell cell6= row4.createCell(2);
	 	    cell6.setCellValue(perc);
	
	        Map<String,AgeRangeVO >  mapp=VO.getMap();
	
	 	    int cellnum=3;
	 	    for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	 	    {    
	 	 	     AgeRangeVO VO1     =    mappp.getValue();
	 	 		 Cell cell7 = row4.createCell(cellnum++);
	 	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
	 	 		 
	 	 		 Cell cell8 = row4.createCell(cellnum++);
	 	 		 cell8.setCellValue(VO1.getAgeRangePerc());
	
	 	   }
       }
	   
}
*/

public void generateExcelsForConstituency(AgeRangeVO  VO ,HSSFSheet sheet,HSSFWorkbook workbook)
{
	
	    DecimalFormat df = new DecimalFormat("##.##");
	   // Font font = workbook.createFont();
	   // font.setFontHeightInPoints((short)15);
	    //font.setFontName("Arial");
	    CellStyle style = workbook.createCellStyle();
	    style.setFillForegroundColor(HSSFColor.YELLOW.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	   // style.setFont(font);
	    HSSFRow  rowHead= sheet.createRow((short) 0);
	    
	    Cell cell = rowHead.createCell(1);
	    cell.setCellValue("1. Age wise Information - "+ VO.getTehsilName() +" Constituency");
	   // cell.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
	    
	    Row row1 = sheet.createRow(1);
	    Cell cell1 = row1.createCell(0);
	    cell1.setCellValue("Constituency");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,0,0 ));
	    
	    cell1 = row1.createCell(1);
	    cell1.setCellValue("Total Voters");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,1,2 ));


		int colCount = 3;
		for (String name : ageRanges)
		{
			  cell1 = row1.createCell(colCount);
			    cell1.setCellValue(name);
			    cell1.setCellStyle(style);
			    int spanCount = colCount+1;
			    sheet.addMergedRegion(new CellRangeAddress(1,1,colCount,spanCount));
			    
			    colCount = spanCount;
			    colCount++;
		}
	    	
		Row row2 = sheet.createRow(2);
		int colSpan = 3;
		for (String name : ageRanges)
	    {
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("Votes");
		    cell1.setCellStyle(style);
		    
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("%");
		    cell1.setCellStyle(style);
	   }
      // int rownum = 3;

	   HSSFRow  row4= sheet.createRow((short) 3);
	   Cell cell2 = row4.createCell(0);
	   cell2.setCellValue(VO.getTehsilName());//cname.
		
	   Cell cell6= row4.createCell(1);
	   cell6.setCellValue(VO.getTotalCount());
	   sheet.addMergedRegion(new CellRangeAddress(3,3,1,2));

       Map<String,AgeRangeVO >  mapp=VO.getMap();
   
	   int cellnum=3;
	   for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	   {    
		   	 AgeRangeVO VO1     =    mappp.getValue();
	 		 
		   	 Cell cell7 = row4.createCell(cellnum++);
	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
	 		 
	 		 Cell cell8 = row4.createCell(cellnum++);
	 		 cell8.setCellValue(VO1.getAgeRangePerc());

	   }
	 
	   HSSFRow  row453= sheet.createRow((short) 4);
	   	Cell cell713 = row453.createCell(1);
	   	cell713.setCellStyle(style);
	   	cell713.setCellValue("Male");
		 
		 Cell cell813 = row453.createCell(2);
		 cell813.setCellStyle(style);
		 cell813.setCellValue("Female");
		 int colCount23 =3;
		 for (String name : ageRanges)
	     {
				Cell cell73 = row453.createCell(colCount23++);
				cell73.setCellStyle(style);
				cell73.setCellValue("Male");
				 
				 Cell cell34 = row453.createCell(colCount23++);
				 cell34.setCellStyle(style);
				 cell34.setCellValue("Female");
	     }
		 
	   HSSFRow  row45= sheet.createRow((short) 5);
	     Cell cell71 = row45.createCell(1);
		 cell71.setCellValue(VO.getTotalMaleCount());
		 
		 Cell cell81 = row45.createCell(2);
		 cell81.setCellValue(VO.getTotalFemaleCount());
	   int cellnum3=3;
	   for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	   {    
		   	 AgeRangeVO VO1 = mappp.getValue();
	 		 
		   	 Cell cell7 = row45.createCell(cellnum3++);
	 		 cell7.setCellValue(VO1.getMaleCount());
	 		 
	 		 Cell cell8 = row45.createCell(cellnum3++);
	 		 cell8.setCellValue(VO1.getFemaleCount());

	   }
	   
	  
	   HSSFRow  row5= sheet.createRow((short)6);
	     Cell cell19 = row5.createCell(1);
	     Double malePerc = VO.getTotalMaleCount()/VO.getTotalCount().doubleValue() *100;
	     cell19.setCellValue(Double.parseDouble(df.format(malePerc)));
		 
		 Cell cell18 = row5.createCell(2);
		 Double femalePerc = VO.getTotalFemaleCount()/VO.getTotalCount().doubleValue() *100;
		 cell18.setCellValue(Double.parseDouble(df.format(femalePerc)));
	     int cellnum78 = 3;
	     for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	     {    
		   
		     AgeRangeVO VO1 = mappp.getValue();
	 		 Long totalCountAgeRangeWise = VO1.getMaleCount() + VO1.getFemaleCount();
		   	 Cell cell7 = row5.createCell(cellnum78++);
	 		 cell7.setCellValue(Double.parseDouble(df.format(VO1.getMaleCount()/VO1.getParticularAgeVotersCount().doubleValue() * 100)));
	 		 Cell cell8 = row5.createCell(cellnum78++);
	 		 cell8.setCellValue(Double.parseDouble(df.format(VO1.getFemaleCount()/VO1.getParticularAgeVotersCount().doubleValue() *100)));

	     }
}

public void generateExcelsForMandal(AgeRangeVO  VO ,HSSFSheet sheet,HSSFWorkbook workbook)
{
	
	    Font font = workbook.createFont();
	    font.setFontHeightInPoints((short)15);
	    font.setFontName("Arial");
	    CellStyle style = workbook.createCellStyle();
	    style.setFont(font);
	    HSSFRow  rowHead= sheet.createRow((short) 0);
	    
	    Cell cell = rowHead.createCell(1);
	    cell.setCellValue("1. Age wise Information - Kavali Constituency");
	    cell.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(0,0,1,10));
	    
	    Row row1 = sheet.createRow(1);
	    Cell cell1 = row1.createCell(0);
	    cell1.setCellValue("Constituency");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,0,0 ));
	    
	    cell1 = row1.createCell(1);
	    cell1.setCellValue("Total Voters");
	    cell1.setCellStyle(style);
	    sheet.addMergedRegion(new CellRangeAddress(1,2,1,1 ));

	    int count = 0;
		int colCount = 2;
		for (String name : ageRanges)
		{
			  cell1 = row1.createCell(colCount);
			    cell1.setCellValue(name);
			    cell1.setCellStyle(style);
			    int spanCount = colCount+3;
			    sheet.addMergedRegion(new CellRangeAddress(1,1,colCount,spanCount));
			    count++;
			    colCount = spanCount;
			    colCount++;
		}
	    	
		Row row2 = sheet.createRow(2);
		int colSpan = 2;
		for (String name : ageRanges)
	    {
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("Votes");
		    cell1.setCellStyle(style);
		    
		    cell1 = row2.createCell(colSpan++);
		    cell1.setCellValue("%");
		    cell1.setCellStyle(style);
	   }
       int rownum = 3;

	   HSSFRow  row4= sheet.createRow((short) rownum);
	   Cell cell2 = row4.createCell(0);
	   cell2.setCellValue(VO.getTehsilName());
	   
	   Cell cell3 = row4.createCell(1);
	   cell3.setCellValue(VO.getPanchayatName());
		
	   Cell cell6= row4.createCell(2);
	   cell6.setCellValue(VO.getTotalCount());

       Map<String,AgeRangeVO >  mapp=VO.getMap();
   
	   int cellnum=3;
	   for(Map.Entry<String,AgeRangeVO>  mappp :mapp.entrySet() )  
	   {    
	 	 AgeRangeVO VO1     =    mappp.getValue();
	 	 
	 	
	 		 Cell cell7 = row4.createCell(cellnum++);
	 		 cell7.setCellValue(VO1.getParticularAgeVotersCount());
	 		 
	 		 Cell cell8 = row4.createCell(cellnum++);
	 		 cell8.setCellValue(VO1.getAgeRangePerc());
	 		
	 		
	 		 
	 	
	   }
}
/*public AgeRangeVO getConstituencyWiseDetails(Long constituencyId,Long publicationId,List<String> ageRanges)
{
	String subString="to";
	List<AgeRangeVO>  query=new ArrayList<AgeRangeVO>();
	for(String ageRange:ageRanges)
	{
       if(ageRange.toLowerCase().contains(subString.toLowerCase()))
       {    
    	    String[] parts = ageRange.split("to");
		    String part1 = parts[0]; 
		    String part2 = parts[1]; 
			
			String	queryString=null;
		queryString=	
					(" select bpv.booth.constituency.constituencyId, bpv.booth.constituency.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
					"	from BoothPublicationVoter bpv "+
					"	where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
					" and bpv.voter.age>="+part1+" and bpv.voter.age<="+part2+"  "+
					" group by bpv.voter.gender ");	
		
		 AgeRangeVO vo=new AgeRangeVO();
	        vo.setAgeRange(ageRange);
	        vo.setQueryString(queryString);
		     query.add(vo);
       }  
      
       else
       {  String queryString2=null;
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
	}
	 AgeRangeVO  li =printDataForConstituency( query);
     return li;	   */
/*}//m				   
public AgeRangeVO printDataForConstituency(List<AgeRangeVO> query)
	{
		 
		AgeRangeVO  li=null;
		
		 

	List<AgeRangeVO> output=new ArrayList<AgeRangeVO>();
	  if(query!=null)
	  {
	    for(AgeRangeVO   vo    :query)
	    {
		   List<Object[]> list = userDAO.getData(vo.getQueryString());
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
				   if(vo == null)
				   {
				    vo = new AgeRangeVO();
				    vo.setTehsilName((obj[1].toString()));
				    vo.setTehsilId((Long)obj[0]);
				     Map<String,AgeRangeVO> m=new LinkedHashMap<String,AgeRangeVO>();
				     vo.setMap(m);
				     
				    }
				   
				   if(obj[2]!=null)
				   {
					   String ageRange =param.getAgeRange();
					    	
					    	  Map<String,AgeRangeVO>   m =vo.getMap();
					    	    
					    	     AgeRangeVO particularAge  =m.get(ageRange);  //checking for particular  agerangevo.
					    	     long a=(Long)obj[0];
					    	     long b=vo.getTehsilId();
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
					    	    	  
					    	    	  
					    	    	 vo.setTotalCount(vo.getTotalCount()+(Long)obj[2]);
					    	    	 
					    	    	 
					    	    	 
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
					    	       vo.setTotalCount(vo.getTotalCount()+(Long)obj[2]);
					    	     } 
					    	    	 
					    	     }	 
					    	     }//if 	 
					      }//for
				   }//if
					   
					   
					   
		}//for
		  
li=gettingPercantage1(vo);
return li; 	
	
	
}*/


/*public AgeRangeVO gettingPercantage1(AgeRangeVO outerVO) 
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
	   }
 

return outerVO;


}
*/


public static Comparator<AgeRangeVO> hamletWiseSort = new Comparator<AgeRangeVO>()
{	  
		  public int compare(AgeRangeVO vo2,AgeRangeVO vo1)
			{  
			               Long i2=vo2.getTotalCount();
			               Long i1=vo1.getTotalCount();
			               return i1.compareTo(i2);
			
			}
};

public static Comparator<AgeRangeVO> mandalWiseSort = new Comparator<AgeRangeVO>()
{	  
		  public int compare(AgeRangeVO vo2,AgeRangeVO vo1)
			{  
			              String  s2=vo2.getTehsilName();
			              String s1=vo1.getTehsilName();
			               return s2.compareTo(s1);
			
			}
};
}















/*=panchayatwise.
				//String constiId = "299";
				StringBuilder table = new StringBuilder();
				Set<String> partialIds = new HashSet<String>();
				String partialBoot = (" select pbp.panchayat.panchayatId,pbp.booth.panchayat.panchayatId  "+
                                     "from PartialBoothPanchayat pbp "+
                                     "where pbp.booth.constituency.constituencyId=232 and pbp.booth.publicationDate.publicationDateId=8 ");
				List<Object[]> par = userDAO.getData(partialBoot);
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
				String queryham = "";
				String queryham1 = "";
				String queryham2 = "";
				String queryham3 = "";
				String queryham4 = "";
				
				
				String parqueryham = "";
				String parqueryham1 = "";
				String parqueryham2 = "";
				String parqueryham3 = "";
				String parqueryham4 = "";
				
				
				
				if(paids.length() == 0){
					
					
				/* queryham = (" select  p.panchayat_id,p.panchayat_name,count(distinct bpv.voter_id),t.tehsil_name "+
 "from panchayat p,tehsil t,booth b,booth_publication_voter bpv ,voter v "+
 "where b.constituency_id = 232 and b.publication_date_id = 8 and b.booth_id = bpv.booth_id  and "+  
  "  bpv.voter_id = v.voter_id  and v.age >= 18 and v.age <= 22 and b.panchayat_id = p.panchayat_id  and b.tehsil_id = t.tehsil_id "+ 
 "group by b.panchayat_id;");*/
/*		
queryham =
("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
		"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName "+
"from BoothPublicationVoter bpv "+
"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
     " and bpv.voter.age>=18 and bpv.voter.age<=22 "+
"group by bpv.booth.panchayat.panchayatId");
		
	
queryham =
("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
		"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName "+
"from BoothPublicationVoter bpv "+
"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
     " and bpv.voter.age>=18 and bpv.voter.age<=22 "+
"group by bpv.booth.panchayat.panchayatId");
		
queryham1 =
("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
		"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName "+
"from BoothPublicationVoter bpv "+
"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
     " and bpv.voter.age>=23 and bpv.voter.age<=35 "+
"group by bpv.booth.panchayat.panchayatId");
		
queryham2 =
("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
		"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName "+
"from BoothPublicationVoter bpv "+
"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
     " and bpv.voter.age>=36 and bpv.voter.age<=50 "+
"group by bpv.booth.panchayat.panchayatId");
		
queryham3 =
("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
		"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName "+
"from BoothPublicationVoter bpv "+
"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
     " and bpv.voter.age>=51 and bpv.voter.age<=65 "+
"group by bpv.booth.panchayat.panchayatId");
		
queryham4 =
("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
		"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName "+
"from BoothPublicationVoter bpv "+
"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
     " and bpv.voter.age>65 "+
"group by bpv.booth.panchayat.panchayatId");
		

}else{queryham =
		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName "+
		"from BoothPublicationVoter bpv "+
		"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
		     " and bpv.voter.age>=18 and bpv.voter.age<=22 and bpv.booth.panchayat.panchayatId not in("+paids+") "+
		"group by bpv.booth.panchayat.panchayatId");
				
	
		queryham1 =
		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName "+
		"from BoothPublicationVoter bpv "+
		"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
		     " and bpv.voter.age>=23 and bpv.voter.age<=35 and bpv.booth.panchayat.panchayatId not in("+paids+") "+
		"group by bpv.booth.panchayat.panchayatId");
				
		queryham2 =
		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName "+
		"from BoothPublicationVoter bpv "+
		"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
		     " and bpv.voter.age>=36 and bpv.voter.age<=50  and bpv.booth.panchayat.panchayatId not in("+paids+") "+
		"group by bpv.booth.panchayat.panchayatId");
				
		queryham3 =
		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName "+
		"from BoothPublicationVoter bpv "+
		"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
		     " and bpv.voter.age>=51 and bpv.voter.age<=65 and bpv.booth.panchayat.panchayatId not in("+paids+") "+
		"group by bpv.booth.panchayat.panchayatId");
				
		queryham4 =
		("select bpv.booth.panchayat.panchayatId, bpv.booth.panchayat.panchayatName," +
				"count(distinct bpv.voter.voterId),bpv.booth.tehsil.tehsilName "+
		"from BoothPublicationVoter bpv "+
		"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8"+
		     " and bpv.voter.age>65 and bpv.booth.panchayat.panchayatId not in("+paids+") "+
		"group by bpv.booth.panchayat.panchayatId");
					 
		parqueryham = 
("select ph.panchayat.panchayatId,ph.panchayat.panchayatName,count(distinct bpv.voter.voterId),ph.panchayat.tehsil.tehsilName "+
"from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph "+
"where bpv.voter.voterId=uvd.voter.voterId and uvd.hamlet.hamletId=ph.hamlet.hamletId "+ 
"          and bpv.voter.age>=18 and bpv.voter.age<=22 and ph.panchayat.panchayatId in("+paids+") "+
 "         and bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
"group by ph.panchayat.panchayatId");


		parqueryham1 = 
("select ph.panchayat.panchayatId,ph.panchayat.panchayatName,count(distinct bpv.voter.voterId),ph.panchayat.tehsil.tehsilName "+
"from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph "+
"where bpv.voter.voterId=uvd.voter.voterId and uvd.hamlet.hamletId=ph.hamlet.hamletId "+ 
"          and bpv.voter.age>=23 and bpv.voter.age<=35 and ph.panchayat.panchayatId in("+paids+") "+
 "         and bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
"group by ph.panchayat.panchayatId");

		parqueryham2 = 
("select ph.panchayat.panchayatId,ph.panchayat.panchayatName,count(distinct bpv.voter.voterId),ph.panchayat.tehsil.tehsilName "+
"from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph "+
"where bpv.voter.voterId=uvd.voter.voterId and uvd.hamlet.hamletId=ph.hamlet.hamletId "+ 
"          and bpv.voter.age>=36 and bpv.voter.age<=50 and ph.panchayat.panchayatId in("+paids+") "+
 "         and bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
"group by ph.panchayat.panchayatId");

		parqueryham3 = 
("select ph.panchayat.panchayatId,ph.panchayat.panchayatName,count(distinct bpv.voter.voterId),ph.panchayat.tehsil.tehsilName "+
"from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph "+
"where bpv.voter.voterId=uvd.voter.voterId and uvd.hamlet.hamletId=ph.hamlet.hamletId "+ 
"          and bpv.voter.age>=51 and bpv.voter.age<=65 and ph.panchayat.panchayatId in("+paids+") "+
 "         and bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
"group by ph.panchayat.panchayatId");

		parqueryham4 = 
("select ph.panchayat.panchayatId,ph.panchayat.panchayatName,count(distinct bpv.voter.voterId),ph.panchayat.tehsil.tehsilName "+
"from BoothPublicationVoter bpv,UserVoterDetails uvd,PanchayatHamlet ph "+
"where bpv.voter.voterId=uvd.voter.voterId and uvd.hamlet.hamletId=ph.hamlet.hamletId "+ 
"          and bpv.voter.age>65 and ph.panchayat.panchayatId in("+paids+") "+
 "         and bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
"group by ph.panchayat.panchayatId");
}

printData1( queryham, queryham1, queryham2, queryham3, queryham4, "Panchayat", "hamlet",parqueryham,parqueryham1,parqueryham2,parqueryham3,parqueryham4); 
			
			
					
					


 }//test

		public void printData1(String queryham,String queryham1,String queryham2,String queryham3,String queryham4,String one,String two,String parqueryham,String parqueryham1,String parqueryham2,String parqueryham3,String parqueryham4)
		{


		Map<Long,AgeRangeVO> map = new HashMap<Long,AgeRangeVO>();

		StringBuilder table = new StringBuilder();
	
		
		
						List<Object[]> list1 = userDAO.getData(queryham);
						List<Object[]> list2 = userDAO.getData(queryham1);
						List<Object[]> list3 = userDAO.getData(queryham2);
						List<Object[]> list4 = userDAO.getData(queryham3);
						List<Object[]> list5 = userDAO.getData(queryham4);
						
						List<Object[]> list6 = null;
						List<Object[]> list7 = null;
						List<Object[]> list8 = null;
						List<Object[]> list9 = null;
						List<Object[]> list10 = null;
					
						if(parqueryham.length() > 0){
							list6 = userDAO.getData(parqueryham);
						}
						if(parqueryham1.length() > 0){
							list7 = userDAO.getData(parqueryham1);
						}
						if(parqueryham2.length() > 0){
							list8 = userDAO.getData(parqueryham2);
						}
						if(parqueryham3.length() > 0){
							list9 = userDAO.getData(parqueryham3);
						}	
						if(parqueryham4.length() > 0){
							list10 = userDAO.getData(parqueryham4);
						}	

		//0-pid,1-pname,2-count,3-tid				
						
		if(list1 != null && list1.size() >0)
		{
		for(Object[] data:list1)
		{
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null)
		{
		   vo = new AgeRangeVO();
		   vo.setPanchayat(data[1].toString());
		   vo.setHamlet(data[3].toString());
		   map.put((Long)data[0],vo);
		   
		}

		if(data[2] != null){
		    vo.setAge18to22(vo.getAge18to22()+( (Long)data[2]) );
		    vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		}

		}}


		if(list2 != null && list2.size() >0){
		for(Object[] data:list2){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge23to35(vo.getAge23to35()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		}
		}} 

		if(list3 != null && list3.size() >0){
		for(Object[] data:list3){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge36to50(vo.getAge36to50()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		}
		}}

		if(list4 != null && list4.size() >0){
		for(Object[] data:list4){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge51to65(vo.getAge51to65()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		}
		}}

		if(list5 != null && list5.size() >0){
		for(Object[] data:list5){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAgeAbove65(vo.getAgeAbove65()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		}
		}}

		
		if(list6 != null && list6.size() >0)
		{
		for(Object[] data:list6)
		{
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null)
		{
		   vo = new AgeRangeVO();
		   vo.setPanchayat(data[1].toString());
		   vo.setHamlet(data[3].toString());
		   map.put((Long)data[0],vo);
		   
		}

		if(data[2] != null){
		    vo.setAge18to22(vo.getAge18to22()+( (Long)data[2]) );
		    vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		}

		}}


		if(list7 != null && list7.size() >0){
		for(Object[] data:list7){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge23to35(vo.getAge23to35()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		}
		}} 

		if(list8 != null && list8.size() >0){
		for(Object[] data:list8){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge36to50(vo.getAge36to50()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		}
		}}

		if(list9 != null && list9.size() >0){
		for(Object[] data:list9){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge51to65(vo.getAge51to65()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		}
		}}

		if(list10 != null && list10.size() >0){
		for(Object[] data:list10){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAgeAbove65(vo.getAgeAbove65()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		}
		}}	
		
		
		
for(Map.Entry<Long,AgeRangeVO>  ageRangeVO :map.entrySet() )
		{
			 AgeRangeVO vo1 = ageRangeVO.getValue();
			 Long totalVoters = vo1.getTotalCount();
			  DecimalFormat df = new DecimalFormat("##.##");

			 vo1.setAge18to22Perc(( Double.parseDouble(df.format(((double)vo1.getAge18to22()*100)/totalVoters )) ));                     
			 vo1.setAge23to35Perc(Double.parseDouble(df.format(((double)  vo1.getAge23to35()*100)/totalVoters ) ));
			 vo1.setAge36to50Perc( Double.parseDouble(df.format(((double)  vo1.getAge36to50()*100)/totalVoters ) ) );
			 vo1.setAge51to65Perc(Double.parseDouble(df.format(((double)  vo1.getAge51to65()*100)/totalVoters ) ));
			 vo1.setAgeAbove65Perc(Double.parseDouble(df.format(((double)  vo1.getAgeAbove65()*100)/totalVoters ) ));
			 
			 
}


      List<AgeRangeVO> li = new ArrayList<AgeRangeVO>(map.values());
		table.append("<table>");
		table.append("<tr>");
		table.append("<td>"+one+"</td><td>"+two+"</td><td>totalCount</td><td>18to22</td><td>18to22perc</td><td>23 - 35</td><td>23-35perc</td><td>36 - 50</td><td>36-50 perc</td><td>51 - 65</td><td>51-65perc</td><td>Above 65</td><td>above65 perc</td>");
		table.append("</tr>");
		for(AgeRangeVO vo:li){
		table.append("<tr>");
		table.append("<td>"+vo.getPanchayat()+"</td><td>"+vo.getHamlet()+"</td><td>"+vo.getTotalCount()+"</td><td>"+vo.getAge18to22()+"</td><td>"+vo.getAge18to22Perc()+"</td><td>"+vo.getAge23to35()+"</td><td>" +vo.getAge23to35Perc()+"</td><td>" +vo.getAge36to50()+"</td><td>"+vo.getAge36to50Perc()+"</td><td>"+vo.getAge51to65()+"</td><td>"+vo.getAge51to65Perc()+"</td><td>"+vo.getAgeAbove65()+"</td><td>"+vo.getAgeAbove65Perc()+"</td>");
		table.append("</tr>");
		}
		table.append("</table>");
		System.out.println(table.toString());

		 

		}




}//test.



*/
		
//=============================================================================





/*------------------		//mandal wise query.------------------------------
		String v18to22=
("	select  b.tehsil_id,t.tehsil_name,v.gender,count(distinct v.voter_id)  "+
 "from booth b,booth_publication_voter bpv,voter v,tehsil t "+
"where b.booth_id =bpv.booth_id and bpv.voter_id=v.voter_id  and t.tehsil_id=b.tehsil_id "+
"     and b.constituency_id=232 and publication_date_id=8 "+
"     and  v.age>=18 and v.age<=22 and b.local_election_body_id is null "+
"group by b.tehsil_id,v.gender   ");
		

String v23to35=
("	select  b.tehsil_id,t.tehsil_name,v.gender,count(distinct v.voter_id)  "+
		 "from booth b,booth_publication_voter bpv,voter v,tehsil t "+
		"where b.booth_id =bpv.booth_id and bpv.voter_id=v.voter_id  and t.tehsil_id=b.tehsil_id "+
		"     and b.constituency_id=232 and publication_date_id=8 "+
		"     and  v.age>=23 and v.age<=35 and b.local_election_body_id is null "+
		"group by b.tehsil_id,v.gender   ");


String v36to50=
("	select  b.tehsil_id,t.tehsil_name,v.gender,count(distinct v.voter_id)  "+
		 "from booth b,booth_publication_voter bpv,voter v,tehsil t "+
		"where b.booth_id =bpv.booth_id and bpv.voter_id=v.voter_id  and t.tehsil_id=b.tehsil_id "+
		"     and b.constituency_id=232 and publication_date_id=8 "+
		"     and  v.age>=36 and v.age<=50 and b.local_election_body_id is null "+
		"group by b.tehsil_id,v.gender   ");

	String v51to65=
			("	select  b.tehsil_id,t.tehsil_name,v.gender,count(distinct v.voter_id)  "+
					 "from booth b,booth_publication_voter bpv,voter v,tehsil t "+
					"where b.booth_id =bpv.booth_id and bpv.voter_id=v.voter_id  and t.tehsil_id=b.tehsil_id "+
					"     and b.constituency_id=232 and publication_date_id=8 "+
					"     and  v.age>=51 and v.age<=65 and b.local_election_body_id is null "+
					"group by b.tehsil_id,v.gender   ");
		
	String v65above= 
			("	select  b.tehsil_id,t.tehsil_name,v.gender,count(distinct v.voter_id)  "+
					 "from booth b,booth_publication_voter bpv,voter v,tehsil t "+
					"where b.booth_id =bpv.booth_id and bpv.voter_id=v.voter_id  and t.tehsil_id=b.tehsil_id "+
					"     and b.constituency_id=232 and publication_date_id=8 "+
					"     and  v.age>65  and b.local_election_body_id is null "+
					"group by b.tehsil_id,v.gender   ");			

*/
/*		
		
String v18to22=
	
 (" select bpv.booth.tehsil.tehsilId, bpv.booth.tehsil.tehsilName,count(distinct bpv.voter.voterId),bpv.voter.gender "+
 "	from BoothPublicationVoter bpv "+
 "	where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
 " and bpv.voter.age>=18 and bpv.voter.age<=22 and bpv.booth.localBody.localElectionBodyId is null "+
 "		group by bpv.booth.tehsil.tehsilId,bpv.voter.gender");

String v23to35=
(" select bpv.booth.tehsil.tehsilId, bpv.booth.tehsil.tehsilName,count(distinct bpv.voter.voterId),bpv.voter.gender "+
		 "	from BoothPublicationVoter bpv "+
		 "	where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
		 " and bpv.voter.age>=23 and bpv.voter.age<=35 and bpv.booth.localBody.localElectionBodyId is null "+
		 "		group by bpv.booth.tehsil.tehsilId,bpv.voter.gender");

String v36to50=	
(" select bpv.booth.tehsil.tehsilId, bpv.booth.tehsil.tehsilName,count(distinct bpv.voter.voterId),bpv.voter.gender "+
		 "	from BoothPublicationVoter bpv "+
		 "	where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
		 " and bpv.voter.age>=36 and bpv.voter.age<=50 and bpv.booth.localBody.localElectionBodyId is null "+
		 "		group by bpv.booth.tehsil.tehsilId,bpv.voter.gender");

String v51to65=	
(" select bpv.booth.tehsil.tehsilId, bpv.booth.tehsil.tehsilName,count(distinct bpv.voter.voterId),bpv.voter.gender "+
		 "	from BoothPublicationVoter bpv "+
		 "	where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
		 " and bpv.voter.age>=51 and bpv.voter.age<=65 and bpv.booth.localBody.localElectionBodyId is null "+
		 "		group by bpv.booth.tehsil.tehsilId,bpv.voter.gender");

String v65above= 	
(" select bpv.booth.tehsil.tehsilId, bpv.booth.tehsil.tehsilName,count(distinct bpv.voter.voterId),bpv.voter.gender "+
		 "	from BoothPublicationVoter bpv "+
		 "	where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
		 " and bpv.voter.age>65 and bpv.booth.localBody.localElectionBodyId is null "+
		 "		group by bpv.booth.tehsil.tehsilId,bpv.voter.gender");	
	

printData( v18to22, v23to35, v36to50, v51to65, v65above,  "Mandal",null);

}
public void printData(String v18to22,String v23to35,String v36to50,String v51to65,String v65above,String one,String two){

		Map<Long,AgeRangeVO> map = new HashMap<Long,AgeRangeVO>();

		StringBuilder table = new StringBuilder();

		List<Object[]> list = userDAO.getData(v18to22);
		List<Object[]> v23to35d = userDAO.getData(v23to35);
		List<Object[]> v36to50d = userDAO.getData(v36to50);
		List<Object[]> v51to65d = userDAO.getData(v51to65);
		List<Object[]> v65aboved = userDAO.getData(v65above);
		
		//0-tehsilId,1-tehsilName,2-count,3-gender.
		if(list != null && list.size() >0){
        for(Object[] data:list){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}

		if(data[2] != null){
		vo.setAge18to22(vo.getAge18to22()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null)
		{
		
		  if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male"))
		   vo.setAge18to22M(vo.getAge18to22M()+((Long)data[2]));
		
		  if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female"))
		  vo.setAge18to22F(vo.getAge18to22F()+((Long)data[2]));
	    }}}}
		
		if(v23to35d != null && v23to35d.size() >0){
        for(Object[] data:v23to35d){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge23to35(vo.getAge23to35()+((Long)data[2]).longValue());
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null){
		if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male")){
		vo.setAge23to35M(vo.getAge23to35M()+((Long)data[2]));
		}
		if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female")){

		vo.setAge23to35F(vo.getAge23to35F()+((Long)data[2]));
		}
		}
		}
		}
		}
		if(v36to50d != null && v36to50d.size() >0){


		for(Object[] data:v36to50d){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge36to50(vo.getAge36to50()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null){
		if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male")){
		vo.setAge36to50M(vo.getAge36to50M()+((Long)data[2]));
		}
		if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female")){
		vo.setAge36to50F(vo.getAge36to50F()+((Long)data[2]));
		}
		}
		}
		}
		}
		if(v51to65d != null && v51to65d.size() >0){


		for(Object[] data:v51to65d){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge51to65(vo.getAge51to65()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null){
		if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male")){
		vo.setAge51to65M(vo.getAge51to65M()+((Long)data[2]).longValue());
		}
		if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female")){
		vo.setAge51to65F(vo.getAge51to65F()+((Long)data[2]));
		}
		}
		}
		}
		}
		if(v65aboved != null && v65aboved.size() >0){


		for(Object[] data:v65aboved){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAgeAbove65(vo.getAgeAbove65()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null){
		if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male")){
		vo.setAgeAbove65M(vo.getAgeAbove65M()+((Long)data[2]));
		}
		if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female")){
		vo.setAgeAbove65F(vo.getAgeAbove65F()+((Long)data[2]));
		}
		}
		}
		}
		}
		for(Map.Entry<Long,AgeRangeVO>  ageRangeVO :map.entrySet() )
		{
			 AgeRangeVO vo1 = ageRangeVO.getValue();
			 Long totalVoters = vo1.getTotalCount();
			  DecimalFormat df = new DecimalFormat("##.##");

			 vo1.setAge18to22Perc(( Double.parseDouble(df.format(((double)vo1.getAge18to22()*100)/totalVoters )) ));                     
			 vo1.setAge23to35Perc(Double.parseDouble(df.format(((double)  vo1.getAge23to35()*100)/totalVoters ) ));
			 vo1.setAge36to50Perc( Double.parseDouble(df.format(((double)  vo1.getAge36to50()*100)/totalVoters ) ) );
			 vo1.setAge51to65Perc(Double.parseDouble(df.format(((double)  vo1.getAge51to65()*100)/totalVoters ) ));
			 vo1.setAgeAbove65Perc(Double.parseDouble(df.format(((double)  vo1.getAgeAbove65()*100)/totalVoters ) ));
			 
			 
			
		}
		List<AgeRangeVO> li = new ArrayList<AgeRangeVO>(map.values());
		 
		table.append("<table>");
		table.append("<tr>");
		table.append("<td>"+one+"</td><td>tvoters</td><td>yvoters</td><td>yvotersp</td><td>yvotersM</td><td>yvotersf</td>" +
				"<td>23-35</td><td>23-35p</td><td>23-35M</td><td>23-5F</td>" +
				"<td>36-50</td><td>36-50p</td><td>36-50M</td><td>36-50F</td>" +
				"<td>51-65</td><td>51-65P</td><td>51-65M</td><td>51-65F</td>" +
				"<td>abopve65</td><td>above65P</td><td>above65M</td><td>above65F</td>");
		table.append("</tr>");
		for(AgeRangeVO vo:li){
		table.append("<tr>");
		table.append("<td>"+vo.getPanchayat()+"</td><td>"+vo.getTotalCount()+"</td><td>" +
				     +vo.getAge18to22()+"</td><td>"+vo.getAge18to22Perc()+"</td><td>"+vo.getAge18to22M()+"</td><td>"+vo.getAge18to22F()+"</td><td>" + 
				     +vo.getAge23to35()+"</td><td>"+vo.getAge23to35Perc()+"</td><td>"+vo.getAge23to35M()+"</td><td>"+vo.getAge23to35F()+"</td><td>" + 
				     +vo.getAge36to50()+"</td><td>"+vo.getAge36to50Perc()+"</td><td>"+vo.getAge36to50M()+"</td><td>"+vo.getAge36to50F()+"</td><td>" + 
				     +vo.getAge51to65()+"</td><td>"+vo.getAge51to65Perc()+"</td><td>"+vo.getAge51to65M()+"</td><td>"+vo.getAge51to65F()+"</td><td>"  +
				     +vo.getAgeAbove65()+"</td><td>"+vo.getAgeAbove65Perc()+"</td><td>"+vo.getAgeAbove65M()+"</td><td>"+vo.getAgeAbove65F()+"</td>" 
				
				
				);
		table.append("</tr>");
		}
		table.append("</table>");
		System.out.println(table.toString());
		}
		
*/	
	
		
		/*	
		//--------------municpality wise voters in const.	-----------------------------
	/*	
		select b.local_election_body_id ,l.name ,v.gender,count(distinct v.voter_id)  
from booth b,booth_publication_voter bpv,voter v,local_election_body l
where b.booth_id =bpv.booth_id and bpv.voter_id=v.voter_id and b.local_election_body_id=l.local_election_body_id
      and b.constituency_id=232 and publication_date_id=8
      and  v.age>=18 and v.age<=22  and b.local_election_body_id is not null
group by b.local_election_body_id,v.gender;

		
		
	*/	
/*		String v18to22=	
(" select bpv.booth.localBody.localElectionBodyId , bpv.booth.localBody.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
"	from BoothPublicationVoter bpv "+
"	where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
" and bpv.voter.age>=18 and bpv.voter.age<=22 and bpv.booth.localBody.localElectionBodyId is not null "+
"		group by bpv.booth.localBody.localElectionBodyId,bpv.voter.gender ");	
		
		
String v23to35=	
(" select bpv.booth.localBody.localElectionBodyId , bpv.booth.localBody.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
"	from BoothPublicationVoter bpv "+
"	where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
" and bpv.voter.age>=23 and bpv.voter.age<=35 and bpv.booth.localBody.localElectionBodyId is not null "+
"		group by bpv.booth.localBody.localElectionBodyId,bpv.voter.gender ");	
		

		
String v36to50=		
(" select bpv.booth.localBody.localElectionBodyId , bpv.booth.localBody.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
"	from BoothPublicationVoter bpv "+
"	where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
" and bpv.voter.age>=36 and bpv.voter.age<=50 and bpv.booth.localBody.localElectionBodyId is not null "+
"		group by bpv.booth.localBody.localElectionBodyId,bpv.voter.gender ");	
				

String v51to65=		
(" select bpv.booth.localBody.localElectionBodyId , bpv.booth.localBody.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
"	from BoothPublicationVoter bpv "+
"	where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
" and bpv.voter.age>=51 and bpv.voter.age<=65 and bpv.booth.localBody.localElectionBodyId is not null "+
"		group by bpv.booth.localBody.localElectionBodyId,bpv.voter.gender ");	
				
String v65above= 		
(" select bpv.booth.localBody.localElectionBodyId , bpv.booth.localBody.name,count(distinct bpv.voter.voterId),bpv.voter.gender "+
"	from BoothPublicationVoter bpv "+
"	where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 "+
" and bpv.voter.age>65  and bpv.booth.localBody.localElectionBodyId is not null "+
"	group by bpv.booth.localBody.localElectionBodyId,bpv.voter.gender ");	
				
		
printData( v18to22, v23to35, v36to50, v51to65, v65above,  "municipality",null);

}
public void printData(String v18to22,String v23to35,String v36to50,String v51to65,String v65above,String one,String two){

		Map<Long,AgeRangeVO> map = new HashMap<Long,AgeRangeVO>();

		StringBuilder table = new StringBuilder();

		List<Object[]> list = userDAO.getData(v18to22);
		List<Object[]> v23to35d = userDAO.getData(v23to35);
		List<Object[]> v36to50d = userDAO.getData(v36to50);
		List<Object[]> v51to65d = userDAO.getData(v51to65);
		List<Object[]> v65aboved = userDAO.getData(v65above);
		
		//0-tehsilId,1-tehsilName,2-count,3-gender.
		if(list != null && list.size() >0){
        for(Object[] data:list){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}

		if(data[2] != null){
		vo.setAge18to22(vo.getAge18to22()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null)
		{
		
		  if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male"))
		   vo.setAge18to22M(vo.getAge18to22M()+((Long)data[2]));
		
		  if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female"))
		  vo.setAge18to22F(vo.getAge18to22F()+((Long)data[2]));
	    }}}}
		
		if(v23to35d != null && v23to35d.size() >0){
        for(Object[] data:v23to35d){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge23to35(vo.getAge23to35()+((Long)data[2]).longValue());
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null){
		if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male")){
		vo.setAge23to35M(vo.getAge23to35M()+((Long)data[2]));
		}
		if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female")){

		vo.setAge23to35F(vo.getAge23to35F()+((Long)data[2]));
		}
		}
		}
		}
		}
		if(v36to50d != null && v36to50d.size() >0){


		for(Object[] data:v36to50d){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge36to50(vo.getAge36to50()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null){
		if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male")){
		vo.setAge36to50M(vo.getAge36to50M()+((Long)data[2]));
		}
		if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female")){
		vo.setAge36to50F(vo.getAge36to50F()+((Long)data[2]));
		}
		}
		}
		}
		}
		if(v51to65d != null && v51to65d.size() >0){


		for(Object[] data:v51to65d){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge51to65(vo.getAge51to65()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null){
		if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male")){
		vo.setAge51to65M(vo.getAge51to65M()+((Long)data[2]).longValue());
		}
		if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female")){
		vo.setAge51to65F(vo.getAge51to65F()+((Long)data[2]));
		}
		}
		}
		}
		}
		if(v65aboved != null && v65aboved.size() >0){


		for(Object[] data:v65aboved){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAgeAbove65(vo.getAgeAbove65()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null){
		if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male")){
		vo.setAgeAbove65M(vo.getAgeAbove65M()+((Long)data[2]));
		}
		if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female")){
		vo.setAgeAbove65F(vo.getAgeAbove65F()+((Long)data[2]));
		}
		}
		}
		}
		}
		for(Map.Entry<Long,AgeRangeVO>  ageRangeVO :map.entrySet() )
		{
			 AgeRangeVO vo1 = ageRangeVO.getValue();
			 Long totalVoters = vo1.getTotalCount();
			  DecimalFormat df = new DecimalFormat("##.##");

			 vo1.setAge18to22Perc(( Double.parseDouble(df.format(((double)vo1.getAge18to22()*100)/totalVoters )) ));                     
			 vo1.setAge23to35Perc(Double.parseDouble(df.format(((double)  vo1.getAge23to35()*100)/totalVoters ) ));
			 vo1.setAge36to50Perc( Double.parseDouble(df.format(((double)  vo1.getAge36to50()*100)/totalVoters ) ) );
			 vo1.setAge51to65Perc(Double.parseDouble(df.format(((double)  vo1.getAge51to65()*100)/totalVoters ) ));
			 vo1.setAgeAbove65Perc(Double.parseDouble(df.format(((double)  vo1.getAgeAbove65()*100)/totalVoters ) ));
			 
			 
			
		}
		List<AgeRangeVO> li = new ArrayList<AgeRangeVO>(map.values());
		 
		table.append("<table>");
		table.append("<tr>");
		table.append("<td>"+one+"</td><td>tvoters</td><td>yvoters</td><td>yvotersp</td><td>yvotersM</td><td>yvotersf</td>" +
				"<td>23-35</td><td>23-35p</td><td>23-35M</td><td>23-5F</td>" +
				"<td>36-50</td><td>36-50p</td><td>36-50M</td><td>36-50F</td>" +
				"<td>51-65</td><td>51-65P</td><td>51-65M</td><td>51-65F</td>" +
				"<td>abopve65</td><td>above65P</td><td>above65M</td><td>above65F</td>");
		table.append("</tr>");
		for(AgeRangeVO vo:li){
		table.append("<tr>");
		table.append("<td>"+vo.getPanchayat()+"</td><td>"+vo.getTotalCount()+"</td><td>" +
				     +vo.getAge18to22()+"</td><td>"+vo.getAge18to22Perc()+"</td><td>"+vo.getAge18to22M()+"</td><td>"+vo.getAge18to22F()+"</td><td>" + 
				     +vo.getAge23to35()+"</td><td>"+vo.getAge23to35Perc()+"</td><td>"+vo.getAge23to35M()+"</td><td>"+vo.getAge23to35F()+"</td><td>" + 
				     +vo.getAge36to50()+"</td><td>"+vo.getAge36to50Perc()+"</td><td>"+vo.getAge36to50M()+"</td><td>"+vo.getAge36to50F()+"</td><td>" + 
				     +vo.getAge51to65()+"</td><td>"+vo.getAge51to65Perc()+"</td><td>"+vo.getAge51to65M()+"</td><td>"+vo.getAge51to65F()+"</td><td>"  +
				     +vo.getAgeAbove65()+"</td><td>"+vo.getAgeAbove65Perc()+"</td><td>"+vo.getAgeAbove65M()+"</td><td>"+vo.getAgeAbove65F()+"</td>" 
				
				
				);
		table.append("</tr>");
		}
		table.append("</table>");
		System.out.println(table.toString());
		}
}
	
	
	*/
			
		
//================================================================================================================
	
	
	
	
	
	
	
	


		






























/*		
	-----------------boothwise(for mandal and munici)-----------------	
//boothwiseforaconstituency(balance is part_no sorting).
	/*
	String v18to22=
	( "select b.booth_id,b.part_no,count(bpv.voter_id), v.gender "+
	  "	from booth b,booth_publication_voter bpv,voter v "+
	  "	where b.booth_id=bpv.booth_id and bpv.voter_id=v.voter_id "+
	  "	      and b.constituency_id=232 and b.publication_date_id=8 and v.age>=18 and v.age<=22 "+
	  "	group by b.booth_id,v.gender ") ;
	

	
	String v23to35=
			( "select b.booth_id,b.part_no,count(bpv.voter_id), v.gender "+
			  "	from booth b,booth_publication_voter bpv,voter v "+
			  "	where b.booth_id=bpv.booth_id and bpv.voter_id=v.voter_id "+
			  "	      and b.constituency_id=232 and b.publication_date_id=8 and v.age>=23 and v.age<=35 "+
			  "	group by b.booth_id,v.gender ") ;

	
	
					
	String v36to50=
			( "select b.booth_id,b.part_no,count(bpv.voter_id), v.gender "+
			  "	from booth b,booth_publication_voter bpv,voter v "+
			  "	where b.booth_id=bpv.booth_id and bpv.voter_id=v.voter_id "+
			  "	      and b.constituency_id=232 and b.publication_date_id=8 and v.age>=36 and v.age<=50 "+
			  "	group by b.booth_id,v.gender ") ;
	
				
		
	String v51to65=
			( "select b.booth_id,b.part_no,count(bpv.voter_id), v.gender "+
			  "	from booth b,booth_publication_voter bpv,voter v "+
			  "	where b.booth_id=bpv.booth_id and bpv.voter_id=v.voter_id "+
			  "	      and b.constituency_id=232 and b.publication_date_id=8 and v.age>=51 and v.age<=65 "+
			  "	group by b.booth_id,v.gender ") ;

	
	
	
				
		
	String v65above=
			( "select b.booth_id,b.part_no,count(bpv.voter_id), v.gender "+
			  "	from booth b,booth_publication_voter bpv,voter v "+
			  "	where b.booth_id=bpv.booth_id and bpv.voter_id=v.voter_id "+
			  "	      and b.constituency_id=232 and b.publication_date_id=8 and v.age>65 "+
			  "	group by b.booth_id,v.gender ") ;
	
	

	*/
	
	
/*	
	String v18to22=	
("	select bpv.booth.boothId,bpv.booth.partNo,count(bpv.voter.voterId),bpv.voter.gender "+
	"from BoothPublicationVoter bpv "+
	"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 and bpv.voter.age>=18 and bpv.voter.age<=22 "+
	"group by bpv.booth.boothId,bpv.voter.gender ");	
	
	
	String v23to35=
("	select bpv.booth.boothId,bpv.booth.partNo,count(bpv.voter.voterId),bpv.voter.gender "+
	"from BoothPublicationVoter bpv "+
	"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 and bpv.voter.age>=23 and bpv.voter.age<=35 "+
	"group by bpv.booth.boothId,bpv.voter.gender ");	
		
	

	String v36to50=	
("	select bpv.booth.boothId,bpv.booth.partNo,count(bpv.voter.voterId),bpv.voter.gender "+
"from BoothPublicationVoter bpv "+
"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 and bpv.voter.age>=36 and bpv.voter.age<=50 "+
"group by bpv.booth.boothId,bpv.voter.gender ");	 
		
	
	String v51to65=	
("	select bpv.booth.boothId,bpv.booth.partNo,count(bpv.voter.voterId),bpv.voter.gender "+
"from BoothPublicationVoter bpv "+
"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 and bpv.voter.age>=51 and bpv.voter.age<=65 "+
"group by bpv.booth.boothId,bpv.voter.gender ");	 
			
	String v65above=	
("	select bpv.booth.boothId,bpv.booth.partNo,count(bpv.voter.voterId),bpv.voter.gender "+
"from BoothPublicationVoter bpv "+
"where bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId=8 and bpv.voter.age>65 "+
"group by bpv.booth.boothId,bpv.voter.gender ");	 		
	
				
	printData( v18to22, v23to35, v36to50, v51to65, v65above,  "Booth",null);
		
		
}
	
	public void printData(String v18to22,String v23to35,String v36to50,String v51to65,String v65above,String one,String two){

		Map<Long,AgeRangeVO> map = new HashMap<Long,AgeRangeVO>();

		StringBuilder table = new StringBuilder();

		List<Object[]> list = userDAO.getData(v18to22);
		List<Object[]> v23to35d = userDAO.getData(v23to35);
		List<Object[]> v36to50d = userDAO.getData(v36to50);
		List<Object[]> v51to65d = userDAO.getData(v51to65);
		List<Object[]> v65aboved = userDAO.getData(v65above);
		
		//0-boothid,1-partno,2-count,3-gender.
		if(list != null && list.size() >0){
        for(Object[] data:list){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}

		if(data[2] != null){
		vo.setAge18to22(vo.getAge18to22()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null)
		{
		
		  if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male"))
		   vo.setAge18to22M(vo.getAge18to22M()+((Long)data[2]));
		
		  if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female"))
		  vo.setAge18to22F(vo.getAge18to22F()+((Long)data[2]));
	    }}}}
		
		if(v23to35d != null && v23to35d.size() >0){
        for(Object[] data:v23to35d){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge23to35(vo.getAge23to35()+((Long)data[2]).longValue());
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null){
		if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male")){
		vo.setAge23to35M(vo.getAge23to35M()+((Long)data[2]));
		}
		if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female")){

		vo.setAge23to35F(vo.getAge23to35F()+((Long)data[2]));
		}
		}
		}
		}
		}
		if(v36to50d != null && v36to50d.size() >0){


		for(Object[] data:v36to50d){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge36to50(vo.getAge36to50()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null){
		if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male")){
		vo.setAge36to50M(vo.getAge36to50M()+((Long)data[2]));
		}
		if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female")){
		vo.setAge36to50F(vo.getAge36to50F()+((Long)data[2]));
		}
		}
		}
		}
		}
		if(v51to65d != null && v51to65d.size() >0){


		for(Object[] data:v51to65d){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge51to65(vo.getAge51to65()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null){
		if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male")){
		vo.setAge51to65M(vo.getAge51to65M()+((Long)data[2]).longValue());
		}
		if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female")){
		vo.setAge51to65F(vo.getAge51to65F()+((Long)data[2]));
		}
		}
		}
		}
		}
		if(v65aboved != null && v65aboved.size() >0){


		for(Object[] data:v65aboved){
		AgeRangeVO vo = map.get((Long)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		//vo.setHamlet(data[3].toString());
		map.put((Long)data[0],vo);
		}
		if(data[2] != null){
		vo.setAgeAbove65(vo.getAgeAbove65()+((Long)data[2]));
		vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
		if(data[3] != null){
		if(data[3].toString().equalsIgnoreCase("M") || data[3].toString().equalsIgnoreCase("Male")){
		vo.setAgeAbove65M(vo.getAgeAbove65M()+((Long)data[2]));
		}
		if(data[3].toString().equalsIgnoreCase("F") || data[3].toString().equalsIgnoreCase("Female")){
		vo.setAgeAbove65F(vo.getAgeAbove65F()+((Long)data[2]));
		}
		}
		}
		}
		}
		for(Map.Entry<Long,AgeRangeVO>  ageRangeVO :map.entrySet() )
		{
			 AgeRangeVO vo1 = ageRangeVO.getValue();
			 Long totalVoters = vo1.getTotalCount();
			  DecimalFormat df = new DecimalFormat("##.##");

			 vo1.setAge18to22Perc(( Double.parseDouble(df.format(((double)vo1.getAge18to22()*100)/totalVoters )) ));                     
			 vo1.setAge23to35Perc(Double.parseDouble(df.format(((double)  vo1.getAge23to35()*100)/totalVoters ) ));
			 vo1.setAge36to50Perc( Double.parseDouble(df.format(((double)  vo1.getAge36to50()*100)/totalVoters ) ) );
			 vo1.setAge51to65Perc(Double.parseDouble(df.format(((double)  vo1.getAge51to65()*100)/totalVoters ) ));
			 vo1.setAgeAbove65Perc(Double.parseDouble(df.format(((double)  vo1.getAgeAbove65()*100)/totalVoters ) ));
			 
			 
			
		}
		List<AgeRangeVO> li = new ArrayList<AgeRangeVO>(map.values());
		 
		table.append("<table>");
		table.append("<tr>");
		table.append("<td>"+one+"</td><td>tvoters</td><td>yvoters</td><td>yvotersp</td><td>yvotersM</td><td>yvotersf</td>" +
				"<td>23-35</td><td>23-35p</td><td>23-35M</td><td>23-5F</td>" +
				"<td>36-50</td><td>36-50p</td><td>36-50M</td><td>36-50F</td>" +
				"<td>51-65</td><td>51-65P</td><td>51-65M</td><td>51-65F</td>" +
				"<td>abopve65</td><td>above65P</td><td>above65M</td><td>above65F</td>");
		table.append("</tr>");
		for(AgeRangeVO vo:li){
		table.append("<tr>");
		table.append("<td>"+vo.getPanchayat()+"</td><td>"+vo.getTotalCount()+"</td><td>" +
				     +vo.getAge18to22()+"</td><td>"+vo.getAge18to22Perc()+"</td><td>"+vo.getAge18to22M()+"</td><td>"+vo.getAge18to22F()+"</td><td>" + 
				     +vo.getAge23to35()+"</td><td>"+vo.getAge23to35Perc()+"</td><td>"+vo.getAge23to35M()+"</td><td>"+vo.getAge23to35F()+"</td><td>" + 
				     +vo.getAge36to50()+"</td><td>"+vo.getAge36to50Perc()+"</td><td>"+vo.getAge36to50M()+"</td><td>"+vo.getAge36to50F()+"</td><td>" + 
				     +vo.getAge51to65()+"</td><td>"+vo.getAge51to65Perc()+"</td><td>"+vo.getAge51to65M()+"</td><td>"+vo.getAge51to65F()+"</td><td>"  +
				     +vo.getAgeAbove65()+"</td><td>"+vo.getAgeAbove65Perc()+"</td><td>"+vo.getAgeAbove65M()+"</td><td>"+vo.getAgeAbove65F()+"</td>" 
				
				
				);
		table.append("</tr>");
		}
		table.append("</table>");
		System.out.println(table.toString());
		}
	
}*/		
/*
 * =========================================================================================================================

		/*         hamletwise(old):
		 *         ===========
				  List<AgeRangeVO> li = null ;
				  Map<Long,AgeRangeVO> map;
			 try{
				  map = new HashMap<Long,AgeRangeVO>();
        List<Object[]>   getAgeHamletWise =userDAO.getAgePanchayatHamletWithType(221l,10l);
		          if(getAgeHamletWise != null && getAgeHamletWise.size() >0)
				 {
				    for(Object[] data:getAgeHamletWise)
				    {
					  AgeRangeVO vo = map.get((Long)data[0]); 
					  if(vo == null)
					  {
						vo = new AgeRangeVO();
						vo.setPanchayat(data[3].toString());
						vo.setHamlet(data[1].toString());
						map.put((Long)data[0],vo);
					   }
					 if(data[2] != null)
					 {
						setData(data,vo,data[5]);
					 }
				   }
				}  
				 
		          
	getAgeHamletWise = userDAO.getAgePanchayatHamletWithType1(221l,10l);
				
			if(getAgeHamletWise != null && getAgeHamletWise.size() >0)
			{
				 for(Object[] data:getAgeHamletWise)
				 {
					AgeRangeVO vo = map.get((Long)data[0]);
					if(vo == null)
					{
						vo = new AgeRangeVO();
						vo.setPanchayat(data[3].toString());
						vo.setHamlet(data[1].toString());
						map.put((Long)data[0],vo);
					}
					if(data[2] != null)
					{
					  vo.setYoungVoters(vo.getYoungVoters()+((Long)data[2]).longValue());
					  if(data[4] != null){
							              if(data[4].toString().equalsIgnoreCase("M") || data[4].toString().equalsIgnoreCase("Male"))
							                 vo.setYoungVotersM(vo.getYoungVotersM()+((Long)data[2]));
							              
							              if(data[4].toString().equalsIgnoreCase("F") || data[4].toString().equalsIgnoreCase("Female"))
								             vo.setYoungVotersF(vo.getYoungVotersF()+((Long)data[2]));
							              }
					}
				}
			}
			 
			 li = new ArrayList<AgeRangeVO>(map.values());
				StringBuilder table = new StringBuilder();
				table.append("<table>");
				table.append("<tr>");
				table.append("<td>Panchayat</td><td>Hamlet</td><td>Young Voters</td><td>Young Voters Male</td><td>Young Voters Female</td><td>18 - 25</td><td>18 - 25 Male</td><td>18 - 25 Female</td><td>26 - 35</td><td>26 - 35 Male</td><td>26 - 35 Female</td><td>36 - 45</td><td>36 - 45</td><td>36 - 45 Male</td><td>46 - 60 Female</td><td>Above 60</td><td>Above 60 Male</td><td>Above 60 Female</td>");
				table.append("</tr>");
				for(AgeRangeVO vo:li){ 
					table.append("<tr>");
					  table.append("<td>"+vo.getPanchayat()+"</td><td>"+vo.getHamlet()+"</td><td>"+vo.getYoungVoters()+"</td><td>"+vo.getYoungVotersM()+"</td><td>"+vo.getYoungVotersF()+"</td><td>"+vo.getAge18To25()+"</td><td>"+vo.getAge18To25M()+"</td><td>"+vo.getAge18To25F()+"</td><td>"+vo.getAge26to35()+"</td><td>"+vo.getAge26to35M()+"</td><td>"+vo.getAge26to35F()+"</td><td>"+vo.getAge36to45()+"</td><td>"+vo.getAge36to45M()+"</td><td>"+vo.getAge36to45F()+"</td><td>"+vo.getAge46to60()+"</td><td>"+vo.getAge46to60M()+"</td><td>"+vo.getAge46to60F()+"</td><td>"+vo.getAbove60()+"</td><td>"+vo.getAbove60M()+"</td><td>"+vo.getAbove60F()+"</td>");
					table.append("</tr>");
				}
				table.append("</table>");
			System.out.println(table.toString());
			 }catch(Exception e)
			 {
				e.printStackTrace(); 
			 }
			
			
			 
			 
			 
	}//test.

    public void setData(Object[] data,AgeRangeVO vo,Object gender){                                       
		 if(data[2] != null){
			 if(data[4]!=null)
			 {
			    
				if((Long)data[4] == 2){
					vo.setAge18To25(vo.getAge18To25()+((Long)data[2]));
					 if(gender != null){
						if(gender.toString().equalsIgnoreCase("M") || gender.toString().equalsIgnoreCase("Male")){
							vo.setAge18To25M(vo.getAge18To25M()+((Long)data[2]));
						}
						if(gender.toString().equalsIgnoreCase("F") || gender.toString().equalsIgnoreCase("Female")){
							
							vo.setAge18To25F(vo.getAge18To25F()+((Long)data[2]));
						}
					 }
					
				}else if(((Long)data[4]) == 3){
					vo.setAge26to35(vo.getAge26to35()+((Long)data[2]));
					if(gender != null){
						if(gender.toString().equalsIgnoreCase("M") || gender.toString().equalsIgnoreCase("Male")){
							vo.setAge26to35M(vo.getAge26to35M()+((Long)data[2]));
						}
						if(gender.toString().equalsIgnoreCase("F") || gender.toString().equalsIgnoreCase("Female")){
							vo.setAge26to35F(vo.getAge26to35F()+((Long)data[2]));
						}
					 }
				}else if(((Long)data[4]) == 4){
					vo.setAge36to45(vo.getAge36to45()+((Long)data[2]).longValue());
					if(gender != null){
						if(gender.toString().equalsIgnoreCase("M") || gender.toString().equalsIgnoreCase("Male")){
							vo.setAge36to45M(vo.getAge36to45M()+((Long)data[2]));
						}
						if(gender.toString().equalsIgnoreCase("F") || gender.toString().equalsIgnoreCase("Female")){
							vo.setAge36to45F(vo.getAge36to45F()+((Long)data[2]).longValue());
						}
					 }
				}else if(((Long)data[4]) == 5){
					vo.setAge46to60(vo.getAge46to60()+((Long)data[2]));
					if(gender != null){
						if(gender.toString().equalsIgnoreCase("M") || gender.toString().equalsIgnoreCase("Male")){
							vo.setAge46to60M(vo.getAge46to60M()+((Long)data[2]));
						}
						if(gender.toString().equalsIgnoreCase("F") || gender.toString().equalsIgnoreCase("Female")){
							vo.setAge46to60F(vo.getAge46to60F()+((Long)data[2]));
						}
					 }
				}else if(((Long)data[4])== 6){
					vo.setAbove60(vo.getAbove60()+((Long)data[2]));
					if(gender != null){
						if(gender.toString().equalsIgnoreCase("M") || gender.toString().equalsIgnoreCase("Male")){
							vo.setAbove60M(vo.getAbove60M()+((Long)data[2]));
						}
						if(gender.toString().equalsIgnoreCase("F") || gender.toString().equalsIgnoreCase("Female")){
							vo.setAbove60F(vo.getAbove60F()+((Long)data[2]));
						}
					 }
				}
			 }
			}
	 }//setData().	
*/
	 
    

/*

-----hamletwise new(version 0)-------

 List<AgeRangeVO> li = null ;
		  Map<Long,AgeRangeVO> map;
	 try{
		  map = new HashMap<Long,AgeRangeVO>();
		  
List<Object[]>   getAgeHamletWise =userDAO.getAgePanchayatHamletWithType(221l,10l);
//0-hid,1-hname,2-count,3-pname,4-agerange.
         if(getAgeHamletWise != null && getAgeHamletWise.size() >0)
		 {
		    for(Object[] data:getAgeHamletWise)
		    {
			  AgeRangeVO vo = map.get((Long)data[0]); 
			  if(vo == null)
			  {   
				vo = new AgeRangeVO();
				vo.setPanchayat(data[3].toString());
				vo.setHamlet(data[1].toString());
				map.put((Long)data[0],vo);
			   }
			 if(data[2] != null)
			 {
				  vo.setTotalVoters(vo.getTotalVoters()+(Long)data[2]);				  
				  setData(data,vo);
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
        	 vo1.setAgeabove60Percantage(Double.parseDouble(df.format(((double)  vo1.getAbove60()*100)/totalVoters ) ));
            }  

	 li = new ArrayList<AgeRangeVO>(map.values());
		StringBuilder table = new StringBuilder();
		table.append("<table>");
		table.append("<tr>");
		table.append("<td>Panchayat</td><td>Hamlet</td><td>total voters</td><td>Young Voters</td><td> youngperc</td><td>23 - 25</td><td>23-25perc</td><td>26 - 35</td><td>26-35perc</td><td>36 - 45</td><td>36-45perc</td><td>46-60</td><td>46-60perc</td><td>Above 60</td><td>above60perc</td>");
		table.append("</tr>");
		for(AgeRangeVO vo:li)
		{ 
			table.append("<tr>");
			  table.append("<td>"+vo.getPanchayat()+"</td><td>"+vo.getHamlet()+"</td><td>"+vo.getTotalVoters()+"</td><td>"+vo.getYoungVoters()+"</td><td>"+vo.getYoungVotersPercantage()+"</td><td>"+vo.getAge23to25()+"</td><td>"+vo.getAge23to25Percantage()+"</td><td>"+vo.getAge26to35()+"</td><td>"+vo.getAge26to35Percantage()+ "</td><td>"+vo.getAge36to45()+"</td><td>"+vo.getAge36to45Percantage()+"</td><td>"+vo.getAge46to60()+"</td><td>"+vo.getAge46to60Percantage()+"</td><td>"+vo.getAbove60()+"</td><td>"+vo.getAgeabove60Percantage()+"</td>");
		    table.append("</tr>");
		}
		table.append("</table>");
	System.out.println(table.toString());
	 }catch(Exception e)
	 {
		e.printStackTrace(); 
	 }
	 
	 
	  
}//test.

public void setData(Object[] data,AgeRangeVO vo){   
	
if(data[2] != null){
	 if(data[4]!=null) //1,2,3,4,5,6
	 { 
		 if((Long)data[4] == 1){
			vo.setYoungVoters(vo.getYoungVoters()+((Long)data[2]));
		   
		    
		 }
		   
				
		 else if((Long)data[4] == 2){
			vo.setAge23to25(vo.getAge23to25()+((Long)data[2]));
			
		 }
			
			
		else if(((Long)data[4]) == 3){
			vo.setAge26to35(vo.getAge26to35()+((Long)data[2]));
		

		}
		
		else if(((Long)data[4]) == 4){
			vo.setAge36to45(vo.getAge36to45()+((Long)data[2]).longValue());
		

		}	
		else if(((Long)data[4]) == 5){
			vo.setAge46to60(vo.getAge46to60()+((Long)data[2]));
		

		}
		else if(((Long)data[4])== 6){
			vo.setAbove60(vo.getAbove60()+((Long)data[2]));
			
		}
	}
	    }


   
	}
}//setData().	





         hamletwise(version1)
         
String v18to22 = ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName,uvd.voter.gender " +
				         "from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
				         "where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
				         "bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId = 8  " +
				         "and uvd.user.userId=1 and uvd.voter.age>=18 and  uvd.voter.age<=22 " +
				         "group by  uvd.hamlet.hamletId,uvd.voter.gender");
	   
		String v23to35 = ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName,uvd.voter.gender " +
		         "from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
		         "where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
		         "bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId = 8  " +
		         "and uvd.user.userId=1 and uvd.voter.age>=23 and  uvd.voter.age<=35 " +
		         "group by  uvd.hamlet.hamletId,uvd.voter.gender");
	    
		String v36to50 = ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName,uvd.voter.gender " +
		         "from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
		         "where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
		         "bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId = 8  " +
		         "and uvd.user.userId=1 and uvd.voter.age>=36 and  uvd.voter.age<=50 " +
		         "group by  uvd.hamlet.hamletId,uvd.voter.gender");
		
		String v51to65 = ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName,uvd.voter.gender " +
		         "from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
		         "where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
		         "bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId = 8  " +
		         "and uvd.user.userId=1 and uvd.voter.age>=51and  uvd.voter.age<=65 " +
		         "group by  uvd.hamlet.hamletId,uvd.voter.gender");
		
		
		String v65above= ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName,uvd.voter.gender " +
		         "from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
		         "where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
		         "bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId = 8  " +
		         "and uvd.user.userId=1 and uvd.voter.age>65 " +
		         "group by  uvd.hamlet.hamletId,uvd.voter.gender");
		
		printData( v18to22, v23to35, v36to50, v51to65, v65above, "Panchayat", "Booth");
		
		
}//test
	
	public void printData(String v18to22,String v23to35,String v36to50,String v51to65,String v65above,String one,String two){

		Map<BigInteger,AgeRangeVO> map = new HashMap<BigInteger,AgeRangeVO>();

		StringBuilder table = new StringBuilder();


		List<Object[]> list = userDAO.getData(v18to22);
		List<Object[]> v23to35d = userDAO.getData(v23to35);
		List<Object[]> v36to50d = userDAO.getData(v36to50);
		List<Object[]> v51to65d = userDAO.getData(v51to65);
		List<Object[]> v65aboved = userDAO.getData(v65above);
		if(list != null && list.size() >0){


		for(Object[] data:list){
		AgeRangeVO vo = map.get((BigInteger)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		vo.setHamlet(data[3].toString());
		map.put((BigInteger)data[0],vo);
		}

		if(data[2] != null){
		vo.setYoungVoters(vo.getYoungVoters()+((BigInteger)data[2]).longValue());
		if(data[4] != null){
		if(data[4].toString().equalsIgnoreCase("M") || data[4].toString().equalsIgnoreCase("Male")){
		vo.setYoungVotersM(vo.getYoungVotersM()+((BigInteger)data[2]).longValue());
		}
		if(data[4].toString().equalsIgnoreCase("F") || data[4].toString().equalsIgnoreCase("Female")){
		vo.setYoungVotersF(vo.getYoungVotersF()+((BigInteger)data[2]).longValue());
		}
		}
		}
		}
		}
		if(v23to35d != null && v23to35d.size() >0){


		for(Object[] data:v23to35d){
		AgeRangeVO vo = map.get((BigInteger)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		vo.setHamlet(data[3].toString());
		map.put((BigInteger)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge18To25(vo.getAge18To25()+((BigInteger)data[2]).longValue());
		if(data[4] != null){
		if(data[4].toString().equalsIgnoreCase("M") || data[4].toString().equalsIgnoreCase("Male")){
		vo.setAge18To25M(vo.getAge18To25M()+((BigInteger)data[2]).longValue());
		}
		if(data[4].toString().equalsIgnoreCase("F") || data[4].toString().equalsIgnoreCase("Female")){

		vo.setAge18To25F(vo.getAge18To25F()+((BigInteger)data[2]).longValue());
		}
		}
		}
		}
		}
		if(v36to50d != null && v36to50d.size() >0){


		for(Object[] data:v36to50d){
		AgeRangeVO vo = map.get((BigInteger)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		vo.setHamlet(data[3].toString());
		map.put((BigInteger)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge26to35(vo.getAge26to35()+((BigInteger)data[2]).longValue());
		if(data[4] != null){
		if(data[4].toString().equalsIgnoreCase("M") || data[4].toString().equalsIgnoreCase("Male")){
		vo.setAge26to35M(vo.getAge26to35M()+((BigInteger)data[2]).longValue());
		}
		if(data[4].toString().equalsIgnoreCase("F") || data[4].toString().equalsIgnoreCase("Female")){
		vo.setAge26to35F(vo.getAge26to35F()+((BigInteger)data[2]).longValue());
		}
		}
		}
		}
		}
		if(v51to65d != null && v51to65d.size() >0){


		for(Object[] data:v51to65d){
		AgeRangeVO vo = map.get((BigInteger)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		vo.setHamlet(data[3].toString());
		map.put((BigInteger)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge36to45(vo.getAge36to45()+((BigInteger)data[2]).longValue());
		if(data[4] != null){
		if(data[4].toString().equalsIgnoreCase("M") || data[4].toString().equalsIgnoreCase("Male")){
		vo.setAge36to45M(vo.getAge36to45M()+((BigInteger)data[2]).longValue());
		}
		if(data[4].toString().equalsIgnoreCase("F") || data[4].toString().equalsIgnoreCase("Female")){
		vo.setAge36to45F(vo.getAge36to45F()+((BigInteger)data[2]).longValue());
		}
		}
		}
		}
		}
		if(v65aboved != null && v65aboved.size() >0){


		for(Object[] data:v65aboved){
		AgeRangeVO vo = map.get((BigInteger)data[0]);
		if(vo == null){
		vo = new AgeRangeVO();
		vo.setPanchayat(data[1].toString());
		vo.setHamlet(data[3].toString());
		map.put((BigInteger)data[0],vo);
		}
		if(data[2] != null){
		vo.setAge46to60(vo.getAge46to60()+((BigInteger)data[2]).longValue());
		if(data[4] != null){
		if(data[4].toString().equalsIgnoreCase("M") || data[4].toString().equalsIgnoreCase("Male")){
		vo.setAge46to60M(vo.getAge46to60M()+((BigInteger)data[2]).longValue());
		}
		if(data[4].toString().equalsIgnoreCase("F") || data[4].toString().equalsIgnoreCase("Female")){
		vo.setAge46to60F(vo.getAge46to60F()+((BigInteger)data[2]).longValue());
		}
		}
		}
		}
		}
		List<AgeRangeVO> li = new ArrayList<AgeRangeVO>(map.values());
		table.append("<table>");
		table.append("<tr>");
		table.append("<td>"+one+"</td><td>"+two+"</td><td>Young Voters</td><td>Young Voters Male</td><td>Young Voters Female</td><td>23 - 35</td><td>23 - 35 Male</td><td>23 - 35 Female</td><td>36 - 50</td><td>36 - 50 Male</td><td>36 - 50 Female</td><td>51 - 65</td><td>51 - 65 Male</td><td>51 - 65 Female</td><td>Above 65</td><td>Above 65 Male</td><td>Above 65 Female</td>");
		table.append("</tr>");
		for(AgeRangeVO vo:li){
		table.append("<tr>");
		table.append("<td>"+vo.getPanchayat()+"</td><td>"+vo.getHamlet()+"</td><td>"+vo.getYoungVoters()+"</td><td>"+vo.getYoungVotersM()+"</td><td>"+vo.getYoungVotersF()+"</td><td>"+vo.getAge18To25()+"</td><td>"+vo.getAge18To25M()+"</td><td>"+vo.getAge18To25F()+"</td><td>"+vo.getAge26to35()+"</td><td>"+vo.getAge26to35M()+"</td><td>"+vo.getAge26to35F()+"</td><td>"+vo.getAge36to45()+"</td><td>"+vo.getAge36to45M()+"</td><td>"+vo.getAge36to45F()+"</td><td>"+vo.getAge46to60()+"</td><td>"+vo.getAge46to60M()+"</td><td>"+vo.getAge46to60F()+"</td>");
		table.append("</tr>");
		}
		table.append("</table>");
		System.out.println(table.toString());
		}	
	
	
                   hamletwise(version2)	:
                   ======================
	
		//0-hid,1-hname,2-count,3-pname
		String v18to22 = ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName " +
		         "from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
		         "where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
		         "bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId = 8  " +
		         "and uvd.user.userId=1 and uvd.voter.age>=18 and  uvd.voter.age<=22 " +
		         "group by  uvd.hamlet.hamletId");

String v23to35 = ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName " +
        "from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
        "where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
        "bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId = 8  " +
        "and uvd.user.userId=1 and uvd.voter.age>=23 and  uvd.voter.age<=35 " +
        "group by  uvd.hamlet.hamletId");

String v36to50 = ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName  " +
        "from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
        "where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
        "bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId = 8  " +
        "and uvd.user.userId=1 and uvd.voter.age>=36 and  uvd.voter.age<=50 " +
        "group by  uvd.hamlet.hamletId");

String v51to65 = ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName " +
        "from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
        "where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
        "bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId = 8  " +
        "and uvd.user.userId=1 and uvd.voter.age>=51and  uvd.voter.age<=65 " +
        "group by  uvd.hamlet.hamletId");


String v65above= ("select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName  " +
        "from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd  " +
        "where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and  " +
        "bpv.booth.constituency.constituencyId=232 and bpv.booth.publicationDate.publicationDateId = 8  " +
        "and uvd.user.userId=1 and uvd.voter.age>65 " +
        "group by  uvd.hamlet.hamletId");

printData( v18to22, v23to35, v36to50, v51to65, v65above, "Panchayat", "hamlet");


       }//test

public void printData(String v18to22,String v23to35,String v36to50,String v51to65,String v65above,String one,String two)
{


Map<Long,AgeRangeVO> map = new HashMap<Long,AgeRangeVO>();

StringBuilder table = new StringBuilder();
//0-hid,1-hname,2-count,3-pname

List<Object[]> list = userDAO.getData(v18to22);//[1, SREERAM NAGAR COLONY, 48, VELICHERLA]ie Long,String,Long,string.
List<Object[]> v23to35d = userDAO.getData(v23to35);
List<Object[]> v36to50d = userDAO.getData(v36to50);
List<Object[]> v51to65d = userDAO.getData(v51to65);
List<Object[]> v65aboved = userDAO.getData(v65above);

if(list != null && list.size() >0)
{
for(Object[] data:list)
{
AgeRangeVO vo = map.get((Long)data[0]);
if(vo == null)
{
   vo = new AgeRangeVO();
   vo.setPanchayat(data[1].toString());
   vo.setHamlet(data[3].toString());
   map.put((Long)data[0],vo);
   
}

if(data[2] != null){
    vo.setAge18to22(vo.getAge18to22()+( (Long)data[2]) );
    vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
}

}}


if(v23to35d != null && v23to35d.size() >0){
for(Object[] data:v23to35d){
AgeRangeVO vo = map.get((Long)data[0]);
if(vo == null){
vo = new AgeRangeVO();
vo.setPanchayat(data[1].toString());
vo.setHamlet(data[3].toString());
map.put((Long)data[0],vo);
}
if(data[2] != null){
vo.setAge23to35(vo.getAge23to35()+((Long)data[2]));
vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
}
}} 

if(v36to50d != null && v36to50d.size() >0){
for(Object[] data:v36to50d){
AgeRangeVO vo = map.get((Long)data[0]);
if(vo == null){
vo = new AgeRangeVO();
vo.setPanchayat(data[1].toString());
vo.setHamlet(data[3].toString());
map.put((Long)data[0],vo);
}
if(data[2] != null){
vo.setAge36to50(vo.getAge36to50()+((Long)data[2]));
vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
}
}}

if(v51to65d != null && v51to65d.size() >0){
for(Object[] data:v51to65d){
AgeRangeVO vo = map.get((Long)data[0]);
if(vo == null){
vo = new AgeRangeVO();
vo.setPanchayat(data[1].toString());
vo.setHamlet(data[3].toString());
map.put((Long)data[0],vo);
}
if(data[2] != null){
vo.setAge51to65(vo.getAge51to65()+((Long)data[2]));
vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
}
}}

if(v65aboved != null && v65aboved.size() >0){
for(Object[] data:v65aboved){
AgeRangeVO vo = map.get((Long)data[0]);
if(vo == null){
vo = new AgeRangeVO();
vo.setPanchayat(data[1].toString());
vo.setHamlet(data[3].toString());
map.put((Long)data[0],vo);
}
if(data[2] != null){
vo.setAgeAbove65(vo.getAgeAbove65()+((Long)data[2]));
vo.setTotalCount( vo.getTotalCount()+ ((Long)data[2]) );
}
}}

for(Map.Entry<Long,AgeRangeVO>  ageRangeVO :map.entrySet() )
{
	 AgeRangeVO vo1 = ageRangeVO.getValue();
	 Long totalVoters = vo1.getTotalCount();
	  DecimalFormat df = new DecimalFormat("##.##");

	 vo1.setAge18to22Perc(( Double.parseDouble(df.format(((double)vo1.getAge18to22()*100)/totalVoters )) ));                     
	 vo1.setAge23to35Perc(Double.parseDouble(df.format(((double)  vo1.getAge23to35()*100)/totalVoters ) ));
	 vo1.setAge36to50Perc( Double.parseDouble(df.format(((double)  vo1.getAge36to50()*100)/totalVoters ) ) );
	 vo1.setAge51to65Perc(Double.parseDouble(df.format(((double)  vo1.getAge51to65()*100)/totalVoters ) ));
	 vo1.setAgeAbove65Perc(Double.parseDouble(df.format(((double)  vo1.getAgeAbove65()*100)/totalVoters ) ));
	 
	 
	
	
	
}





List<AgeRangeVO> li = new ArrayList<AgeRangeVO>(map.values());
table.append("<table>");
table.append("<tr>");
table.append("<td>"+one+"</td><td>"+two+"</td><td>totalCount</td><td>18to22</td><td>18to22perc</td><td>23 - 35</td><td>23-35perc</td><td>36 - 50</td><td>36-50 perc</td><td>51 - 65</td><td>51-65perc</td><td>Above 65</td><td>above65 perc</td>");
table.append("</tr>");
for(AgeRangeVO vo:li){
table.append("<tr>");
table.append("<td>"+vo.getPanchayat()+"</td><td>"+vo.getHamlet()+"</td><td>"+vo.getTotalCount()+"</td><td>"+vo.getAge18to22()+"</td><td>"+vo.getAge18to22Perc()+"</td><td>"+vo.getAge23to35()+"</td><td>" +vo.getAge23to35Perc()+"</td><td>" +vo.getAge36to50()+"</td><td>"+vo.getAge36to50Perc()+"</td><td>"+vo.getAge51to65()+"</td><td>"+vo.getAge51to65Perc()+"</td><td>"+vo.getAgeAbove65()+"</td><td>"+vo.getAgeAbove65Perc()+"</td>");
table.append("</tr>");
}
table.append("</table>");
System.out.println(table.toString());

 

}





		
			
       

















====================================================================================================================================
*/

/*
============================================================================================================================
               panchayatwise(new).
               
               
try{
	//String constiId = "299";
	StringBuilder table = new StringBuilder();
	Set<String> partialIds = new HashSet<String>();
	String partialBoot = ("select pbp.panchayat_id,b.panchayat_id from dakavara_pa.partial_booth_panchayat pbp,booth b where pbp.booth_id = b.booth_id and b.constituency_id = 232 and b.publication_date_id = 8 ");
	List<Object[]> par = userDAO.getData(partialBoot);
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
	String queryham = "";
	String queryham1 = "";
	String parqueryham = "";
	String parqueryham1 = "";
	if(paids.length() == 0){
	 queryham = ("select  p.panchayat_id,p.panchayat_name,count(distinct bpv.voter_id),t.tehsil_name,v.voter_age_range_id,v.gender from panchayat p,tehsil t,booth b,booth_publication_voter bpv ,voter v where "+
			" b.constituency_id =232 and b.publication_date_id = 8 and b.booth_id = bpv.booth_id  and  "+
			"   bpv.voter_id = v.voter_id  and b.panchayat_id = p.panchayat_id  and b.tehsil_id = t.tehsil_id group by b.panchayat_id,v.voter_age_range_id,v.gender");
	
	 queryham1 = ("select  p.panchayat_id,p.panchayat_name,count(distinct bpv.voter_id),t.tehsil_name,v.voter_age_range_id,v.gender from panchayat p,tehsil t,booth b,booth_publication_voter bpv ,voter v where "+
			" b.constituency_id =232 and b.publication_date_id = 8 and b.booth_id = bpv.booth_id  and  "+
			"   bpv.voter_id = v.voter_id  and v.age >= 18 and v.age <= 22 and b.panchayat_id = p.panchayat_id  and b.tehsil_id = t.tehsil_id group by b.panchayat_id,v.gender");
	}else{
		queryham = ("select  p.panchayat_id,p.panchayat_name,count(distinct bpv.voter_id),t.tehsil_name,v.voter_age_range_id,v.gender from panchayat p,tehsil t,booth b,booth_publication_voter bpv ,voter v where "+
				" b.constituency_id = 232 and b.publication_date_id = 8 and b.booth_id = bpv.booth_id  and  "+
				"   bpv.voter_id = v.voter_id  and b.panchayat_id not in("+paids+") and b.panchayat_id = p.panchayat_id  and b.tehsil_id = t.tehsil_id group by b.panchayat_id,v.voter_age_range_id,v.gender");
		
		 queryham1 = ("select  p.panchayat_id,p.panchayat_name,count(distinct bpv.voter_id),t.tehsil_name,v.voter_age_range_id,v.gender from panchayat p,tehsil t,booth b,booth_publication_voter bpv ,voter v where "+
				" b.constituency_id = 232 and b.publication_date_id =8 and b.booth_id = bpv.booth_id  and  "+
				"   bpv.voter_id = v.voter_id  and v.age >= 18 and v.age <= 22 and b.panchayat_id not in("+paids+")  and b.panchayat_id = p.panchayat_id  and b.tehsil_id = t.tehsil_id group by b.panchayat_id,v.gender");
		 
		 parqueryham = ("select  p.panchayat_id,p.panchayat_name,count(distinct bpv.voter_id),t.tehsil_name,v.voter_age_range_id,v.gender from panchayat p,tehsil t,booth b,booth_publication_voter bpv ,voter v,user_voter_details uvd,panchayat_hamlet ph where "+
					" b.constituency_id = 232 and b.publication_date_id = 8 and b.booth_id = bpv.booth_id  and  "+
					"   bpv.voter_id = v.voter_id  and bpv.voter_id = uvd.voter_id and uvd.hamlet_id = ph.hamlet_id and ph.panchayat_id = p.panchayat_id and p.panchayat_id in("+paids+")   and p.tehsil_id = t.tehsil_id group by p.panchayat_id,v.voter_age_range_id,v.gender");
			
		 parqueryham1 = ("select  p.panchayat_id,p.panchayat_name,count(distinct bpv.voter_id),t.tehsil_name,v.voter_age_range_id,v.gender from panchayat p,tehsil t,booth b,booth_publication_voter bpv ,voter v,user_voter_details uvd,panchayat_hamlet ph  where "+
					" b.constituency_id = 232 and b.publication_date_id = 8 and b.booth_id = bpv.booth_id  and  "+
					"   bpv.voter_id = v.voter_id    and bpv.voter_id = uvd.voter_id and uvd.hamlet_id = ph.hamlet_id and ph.panchayat_id = p.panchayat_id and  v.age >= 18 and v.age <= 22 and p.panchayat_id in("+paids+")    and p.tehsil_id = t.tehsil_id group by p.panchayat_id,v.gender");
	}
	    List<Object[]> list = new ArrayList<Object[]>();
		List<Object[]> list1 = userDAO.getData(queryham);
		List<Object[]> list2 = null;
		if(parqueryham.length() > 0){
			list2 = userDAO.getData(parqueryham);
		}
		if(list1 != null)
		list.addAll(list1);
		if(list2 != null)
		list.addAll(list2);
		Map<Long,AgeRangeVO> map = new HashMap<Long,AgeRangeVO>();
		
		
		
		if(list != null && list.size() >0){
		   for(Object[] data:list){
			AgeRangeVO vo = map.get(   ( (BigInteger)data[0]).longValue()     );
			if(vo == null){
				vo = new AgeRangeVO();
				vo.setPanchayat(data[1].toString());
				vo.setHamlet(data[3].toString());
				map.put( ( (BigInteger)data[0]).longValue() ,vo);
			}
			if(data[2] != null){
				setData(data,vo,data[5]);
			}
		}
		}
		
		 list = new ArrayList<Object[]>();
		 list1 = userDAO.getData(queryham1);
		 list2 = null;
		if(parqueryham1.length() > 0){
			list2 = userDAO.getData(parqueryham1);
		}
		if(list1 != null)
		list.addAll(list1);
		if(list2 != null)
		list.addAll(list2);
		if(list != null && list.size() >0){
			for(Object[] data:list){
				
				AgeRangeVO vo = map.get( ((BigInteger)data[0]).longValue() );
				if(vo == null){
					vo = new AgeRangeVO();
					vo.setPanchayat(data[1].toString());
					vo.setHamlet(data[3].toString());
					map.put((Long)data[0],vo);
				}
				if(data[2] != null){
					vo.setYoungVoters(vo.getYoungVoters()+((BigInteger)data[2]).longValue());
					if(data[5] != null){
						if(data[5].toString().equalsIgnoreCase("M") || data[5].toString().equalsIgnoreCase("Male")){
							vo.setYoungVotersM(vo.getYoungVotersM()+((BigInteger)data[2]).longValue());
						}
						if(data[5].toString().equalsIgnoreCase("F") || data[5].toString().equalsIgnoreCase("Female")){
							vo.setYoungVotersF(vo.getYoungVotersF()+((BigInteger)data[2]).longValue());
						}
				   }
				}
			}
			}
		List<AgeRangeVO> li = new ArrayList<AgeRangeVO>(map.values());
		table.append("<table>");
		table.append("<tr>");
		table.append("<td>Panchayat</td><td>Mandal</td><td>Young Voters</td><td>Young Voters Male</td><td>Young Voters Female</td><td>18 - 25</td><td>18 - 25 Male</td><td>18 - 25 Female</td><td>26 - 35</td><td>26 - 35 Male</td><td>26 - 35 Female</td><td>36 - 45</td><td>36 - 45</td><td>36 - 45 Male</td><td>46 - 60 Female</td><td>Above 60</td><td>Above 60 Male</td><td>Above 60 Female</td>");
		table.append("</tr>");
		for(AgeRangeVO vo:li){
			table.append("<tr>");
			   table.append("<td>"+vo.getPanchayat()+"</td><td>"+vo.getHamlet()+"</td><td>"+vo.getYoungVoters()+"</td><td>"+vo.getYoungVotersM()+"</td><td>"+vo.getYoungVotersF()+"</td><td>"+vo.getAge18To25()+"</td><td>"+vo.getAge18To25M()+"</td><td>"+vo.getAge18To25F()+"</td><td>"+vo.getAge26to35()+"</td><td>"+vo.getAge26to35M()+"</td><td>"+vo.getAge26to35F()+"</td><td>"+vo.getAge36to45()+"</td><td>"+vo.getAge36to45M()+"</td><td>"+vo.getAge36to45F()+"</td><td>"+vo.getAge46to60()+"</td><td>"+vo.getAge46to60M()+"</td><td>"+vo.getAge46to60F()+"</td><td>"+vo.getAbove60()+"</td><td>"+vo.getAbove60M()+"</td><td>"+vo.getAbove60F()+"</td>");
            table.append("</tr>");
		}
		table.append("</table>");
	System.out.println(table.toString());
	}catch(Exception e){
		e.printStackTrace();
	}
}



public void setData(Object[] data,AgeRangeVO vo,Object gender){                                       
if(data[2] != null){
 if(data[4]!=null)
 {
    
	if( ( (BigInteger)data[0]).longValue()  == 2){
		vo.setAge18To25(vo.getAge18To25()+((BigInteger)data[2]).longValue());
		 if(gender != null){
			if(gender.toString().equalsIgnoreCase("M") || gender.toString().equalsIgnoreCase("Male")){
				vo.setAge18To25M(vo.getAge18To25M()+((BigInteger)data[2]).longValue());
			}
			if(gender.toString().equalsIgnoreCase("F") || gender.toString().equalsIgnoreCase("Female")){
				
				vo.setAge18To25F(vo.getAge18To25F()+((BigInteger)data[2]).longValue());
			}
		 }
		
	}else if((   ( (BigInteger)data[4]).longValue()     ) == 3){
		vo.setAge26to35(vo.getAge26to35()+(  ( (BigInteger)data[2]).longValue() ) );
		if(gender != null){
			if(gender.toString().equalsIgnoreCase("M") || gender.toString().equalsIgnoreCase("Male")){
				vo.setAge26to35M(vo.getAge26to35M()+( ( (BigInteger)data[2]).longValue()) );
			}
			if(gender.toString().equalsIgnoreCase("F") || gender.toString().equalsIgnoreCase("Female")){
				vo.setAge26to35F(vo.getAge26to35F()+(  ( (BigInteger)data[2]).longValue() ) );
			}
		 }
	}else if((  ( (BigInteger)data[4]).longValue()   ) == 4){
		vo.setAge36to45(vo.getAge36to45()+((BigInteger)data[2]).longValue());
		if(gender != null){
			if(gender.toString().equalsIgnoreCase("M") || gender.toString().equalsIgnoreCase("Male")){
				vo.setAge36to45M(vo.getAge36to45M()+((BigInteger)data[2]).longValue());
			}
			if(gender.toString().equalsIgnoreCase("F") || gender.toString().equalsIgnoreCase("Female")){
				vo.setAge36to45F(vo.getAge36to45F()+((BigInteger)data[2]).longValue());
			}
		 }
	}else if(  ( (BigInteger)data[4]).longValue()   == 5){
		vo.setAge46to60(vo.getAge46to60()+((BigInteger)data[2]).longValue());
		if(gender != null){
			if(gender.toString().equalsIgnoreCase("M") || gender.toString().equalsIgnoreCase("Male")){
				vo.setAge46to60M(vo.getAge46to60M()+((BigInteger)data[2]).longValue());
			}
			if(gender.toString().equalsIgnoreCase("F") || gender.toString().equalsIgnoreCase("Female")){
				vo.setAge46to60F(vo.getAge46to60F()+((BigInteger)data[2]).longValue());
			}
		 }
	}else if(  ( (BigInteger)data[4]).longValue()  == 6){
		vo.setAbove60(vo.getAbove60()+((BigInteger)data[2]).longValue());
		if(gender != null){
			if(gender.toString().equalsIgnoreCase("M") || gender.toString().equalsIgnoreCase("Male")){
				vo.setAbove60M(vo.getAbove60M()+((BigInteger)data[2]).longValue());
			}
			if(gender.toString().equalsIgnoreCase("F") || gender.toString().equalsIgnoreCase("Female")){
				vo.setAbove60F(vo.getAbove60F()+((BigInteger)data[2]).longValue());
			}
		 }
	}
 }
}
}//setData().			
}
====================================================================================

*/		
		
		
		
		
		
		
		
		
		
		
		
		
		
	



	
	/*public void testCheckUserPassword()
	{
		List<User> list = userDAO.checkUserPassword("7258695", 12l);
		System.out.println(list.size());
	}*/
	
	/*public void testChangeUserPassword()
	{
	Integer result = userDAO.changeUserPassword("7258695", 12l, IConstants.TRUE, new Date());
	System.out.println(result);
	}*/
	
	/*public void testGetUserIdByUserName()
	{
		List<Object> list = userDAO.getUserIdByUserName("Anuradha@aa.com");
		System.out.println(list.get(0));
	}*/
	
	/*public void testFindByUserNameAndPassword()
	{
		User user = userDAO.findByUserNameAndPassword("aaaa@aa.com", "3685171");
		System.out.println(user.getUserName());
	}*/
	
	/*public void test()
	{
		System.out.println(userDAO.getUserProfileImageNameByUserId(1l));
	}*/
	
	/*public void testGetUserLocationDetailsByUserIds()
	{
		List<Long> userIds = new ArrayList<Long>(0);
		userIds.add(1l);
		
		List<Object[]> list = userDAO.getUserLocationDetailsByUserIds(userIds);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
			System.out.print("----\t"+obj.toString());
		}
	}*/
	/*public void testSaveUserProfileImageNameToDB()
	{
		Integer result = userDAO.saveUserProfileImageNameToDB(1l, "1.jpg");
		System.out.println(result);
	}
	
	public void testGetUserByUserId()
	{
		User user = userDAO.getUserByUserId(1l);
		System.out.println(user.getFirstName());
	}*/
	
	/*public void testAllRegisteredUsersData()
	{
		List<Object[]> users = userDAO.allRegisteredUsersData();
		System.out.println(users.size());
		if(users != null && users.size() > 0)
		{
			for(Object[] result : users)
			{
				System.out.println(result[0]+" ----- "+result[1]+" "+result[2]);
			}
		}
		
	}*/
	/*public void testGetUserByUserName()
	{
		List<User> params = userDAO.getUserByUserName("a.dakavaram@itgrids.com");
		for(User list : params)
		{
			System.out.println(list.getLastName());
		}
	}*/

/*public void testGetUserEmail()
	{
		List<Object[]> result = userDAO.getUserEmail(2l);
		System.out.println(result.size());
		if(result != null && result.size() > 0)
		{
		for(Object[] params: result)
		{
			System.out.println(params[0]);
			System.out.println(params[1].toString()+" "+params[2].toString());
			System.out.println(params[3]);
		}
	 }
	}*/
	
	/*public void testchangeUserNameAsEmail()
	{
		List<User> params = userDAO.changeUserNameAsEmail("nunna@gmail.com");
		for(User list : params)
		{
			System.out.println(list.getLastName());
		}
		
	}*/
	
	/*public void testGetUserDetails()
	{
		List result = userDAO.getUserDetails("Chintu@anu.com");
		System.out.println(result.size());
	}*/
	
	/*public void testCheckAnonymousUserLogin()
	{
		List<User> result = userDAO.checkAnonymousUserLogin("anuradha","anuradha");
		System.out.println(result.size());
		if(result != null && result.size() >0)
		{
		for(User userDetails : result)
		{
			System.out.println(userDetails.getUserName());
			System.out.println(userDetails.getPassword());
		}
		}
	}*/
	
	/*public void testGetPasswordNotUpdatdUsersList()
	{
		List<Object[]> list = userDAO.getPasswordNotUpdatdUsersList();
		System.out.println(list.size());
		if(list != null && list.size() >0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
			}
		}
	}*/
	/*public void testGetAllUsersMobile()
	{
		List<Object[]> list = userDAO.getAllUsersMobile();
		if(list != null &&list.size() > 0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
				System.out.println(params[2]);
				System.out.println(params[3]);
			}
			
		}
	}*/
	
	/*public void testGetAllMobilenosAsUnique()
	{
		Object list = userDAO.getAllMobilenosAsUnique();
		System.out.println(list);
	}*/
	
	/*public void testFindByUserRegistrationId()
	{
		List<User> result = userDAO.findByUserRegistrationId(1l);
		if(result != null && result.size() > 0)
		{
			for(User userDetails : result)
			{
				System.out.println(userDetails.getUserName());
				System.out.println(userDetails.getPassword());
			}
		}
	}*/
	
	/*public void testGetAllRegisteredUsers()
	{
		List<User> details = userDAO.getAllRegisteredUsers();
		Assert.assertEquals(33, details.size());
	}*/
	
	/*public void testGetSubusersByParentUserId()
	{
		List<Object[]> list = userDAO.getSubusersByParentUserId(1l);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
			}
		}
	}*/
	
	/*public void testGetAnanymousUserLocationDetailsByIds()
	{
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(611l); 
		List list = userDAO.getAnanymousUserLocationDetailsByIds(userIds);
		System.out.println(list.size());
		if(list !=null && list.size()>0){
			Object[] values = (Object[])list.get(0);
			Long districtId = (Long)values[2];
			Long constiId = (Long)values[4];
			System.out.println("districtId : "+districtId+"constiId : "+constiId);
			for(Object val:values)
				System.out.println(val!=null?val.toString():"");
			
		}
			
	}*/
	/*public void testGetAnanymousUserLocationDetailsByIds()
	{
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(611l); 
		List list = userDAO.getAnanymousUserLocationDetailsByIds(userIds);
		System.out.println(list.size());
		if(list !=null && list.size()>0){
			Object[] values = (Object[])list.get(0);
			
			List locationIds = new ArrayList();
			locationIds.add((Long)values[0]);
			locationIds.add((Long)values[2]);
			locationIds.add((Long)values[4]);
			
			System.out.println(locationIds.get(0));
			System.out.println(locationIds.get(1));
			System.out.println(locationIds.get(2));
		}
	
			
	}*/
	
	/*public void testGetUserEmailByUserId()
	{
		List<Object[]> list = userDAO.getUserEmailByUserId(1l);
		if(list != null && list.size() >0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
				System.out.println(params[2]);
			}
		}
	}*/
	
	/*public void testGetUserBasicDetailsForProfile()
	{
		List<Object[]> userList = userDAO.getUserBasicDetailsForProfile(1l);
		System.out.println(userList.size());
		if(userList !=null && userList.size() > 0)
		{
			for(Object[] params : userList)
			{
				System.out.println(params[1]);
			}
		}
		
	}*/
	
	
	
	/*public void testGetUserBasicDetailsForProfile()
	{
		List<Long> userId = new ArrayList<Long>(0);
		userId.add(1l);
		userId.add(2l);
		List<Object[]> userList = userDAO.getUserBasicDetailsForProfile(userId);
		System.out.println(userList.size());
		if(userList !=null && userList.size() > 0)
		{
			for(Object[] params : userList)
			{
				System.out.println(params[1]);
			}
		}
		
	}*/
	
	/*public void testgetAllUsersInSelectedLocations()
	{
		List<Long> locationIds = new ArrayList<Long>();
		locationIds.add(2210l);
		List<Object> list = userDAO.getAllUsersInSelectedLocations(locationIds, "constituency", 100l, 0l, null);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(int i=0;i<list.size(); i++)
			{
				Object[] result = (Object[])list.get(i);
				System.out.println(result[6]+"  "+result[7]);
				System.out.println(result[8]+"  "+result[9]);
				
			}
		}
	}*/
	/*public void testgetNotConnectedUsersInSelectedLocations()
	{
		List<Long> locationIds = new ArrayList<Long>();
		locationIds.add(10l);
		List<Long> otherUsers = new ArrayList<Long>();
		otherUsers.add(10l);
		
		List<Object> list = userDAO.getNotConnectedUsersInSelectedLocations(817l, locationIds, "DISTRICT", otherUsers, 2l, 0l, null);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(int i=0;i<list.size(); i++)
			{
				Object[] result = (Object[])list.get(i);
				System.out.println(result[6]+"  "+result[7]);
				System.out.println(result[8]+"  "+result[9]);
				
			}
		}
	}
	*/
	
	/*public void testGetUserNameAndPWDByUserId()
	{
		List<Object[]> list = userDAO.getUserNameAndPwdByUserId(1l);
		for(Object[] params : list)
			System.out.println(params[0]+" "+params[1]);
	}*/
	
/*	
	public void testgetAllUsersCountInSelectedLocations()
	{
		List<Long> locationIds = new ArrayList<Long>(0);
		//locationIds.add(467l);467, , , , , , , 
		locationIds.add(467l);
		locationIds.add(217l);
		locationIds.add(213l);
		locationIds.add(209l);
		locationIds.add(2210l);
		locationIds.add(2110l);
		locationIds.add(229l);
		locationIds.add(219l);
		Long count = userDAO.getAllUsersCountInSelectedLocations(locationIds,IConstants.CONSTITUENCY_LEVEL,"");
		System.out.println(count);
	}*/
	
/*	public void testupdateAllUsersPasswords()
	{	System.out.println(new Date());
		List<User> users  =userDAO.updateAllUsersPasswords();
		
		
		int i = 0;
		System.out.println(new Date());
		System.out.println(users.size());
		
		for(User user:users){
			String secretKey = EncryptDecrypt.getSecretKey();
			EncryptDecrypt phash = new EncryptDecrypt(secretKey);
			String encryptedPassword = phash.encryptText(user.getPassword());
			
			user.setHashKeyTxt(secretKey);
			user.setPasswdHashTxt(encryptedPassword);
			userDAO.save(user);			
			i++;
			
			if(i == 1)
			System.out.println("UPDATE user SET hash_key_txt = '"+secretKey +"',passwd_hash_txt = '"+encryptedPassword+"' WHERE user_id = "+user.getUserId()+";");
		}
		
	}
*/	
	/*public void testCreateHashKeyAndValueForAPassword()
	{
		String secretKey = EncryptDecrypt.getSecretKey();
		EncryptDecrypt phash = new EncryptDecrypt(secretKey);
		String encryptedPassword = phash.encryptText("guest@pa");
		System.out.println(secretKey);
		System.out.println(encryptedPassword);
	}
	public void testCheckSuggestionOrder(){
		List<String> ids = new ArrayList<String>();
		ids.add("VERY STRONG");
		ids.add("STRONG");
		ids.add("OK");
		ids.add("POOR");
		ids.add("VERY POOR");
		ids.add("WOREST");
		int x = -1;
		for(int j = ids.size()-1;j>0;j--){//2009
				x = x+1;
			for(int i = 0;i< (ids.size()-1-x);i++)
			{//2004
				System.out.println(ids.get(i)+","+ids.get(j));
			}
		}
		 x = -1;
		 System.out.println("-------------------");
		for(int j = ids.size()-1;j>0;j--){//2004
				x = x+1;
			for(int i = 0;i< (ids.size()-1-x);i++)
			{//2009
				System.out.println(ids.get(j)+","+ids.get(i));
			}
		}
		 System.out.println("-------------------");
		for(int i = 0;i<ids.size();i++){
			System.out.println(ids.get(i)+","+ids.get(i));
		}
	}*/
	/*public void testCheckSuggestionOrderNew(){
		List<String> ids = new ArrayList<String>();
		ids.add("WOREST");
		ids.add("VERY POOR");
		ids.add("POOR");
		ids.add("OK");
		ids.add("STRONG");
		ids.add("VERY STRONG");
		
		
		
		
	  for(int z = ids.size()-2;z>=0;z--){
		int x = -1;
		for(int j = 0;j<ids.size()-z-1;j++){//2009
			for(int i = ids.size()-1;i>=z+1;i--)
			{//2004
				System.out.println(ids.get(j)+","+ids.get(i));
			}
		}
	  }
	  int z = 1;
	  for(z = 1;z<ids.size();z++){
		  for(int j = 0;j<z;j++){//2009
				for(int i = ids.size()-z+j;i>=ids.size()-z+j;i--)
				{//2004
					System.out.println(ids.get(j)+","+ids.get(i)+","+z);
				}
			}
		  }
	  z = z-1;
	  System.out.println("-----------------");
	  for(int y = 2;y<=ids.size()-1;y++){
		  for(int j = y-2;j<y;j++){
			  z++;
			  System.out.println(ids.get(y-1)+","+ids.get(j)+","+z);
				for(int i = j,l=y;i>0&&l<ids.size()-1;i--,l++)
				{
					System.out.println(ids.get(l)+","+ids.get(i-1)+","+z);
				}
			}
		  } 
		 for(int z = 5;z<=ids.size()-1;z++){
		  for(int j = z-2;j<z;j++){
			  System.out.println(ids.get(z-1)+","+ids.get(j));
				for(int i = j,l=z;i>0&&l<ids.size()-1;i--,l++)
				{
					System.out.println(ids.get(l)+","+ids.get(i-1));
				}
			}
		  } 
		int x = -1;
		 System.out.println("-------------------");
		for(int j = ids.size()-1;j>0;j--){//2004
				x = x+1;
			for(int i = 0;i< (ids.size()-1-x);i++)
			{//2009
				System.out.println(ids.get(j)+","+ids.get(i));
			}
		}
		 System.out.println("-------------------");
		for(int i = 0;i<ids.size();i++){
			System.out.println(ids.get(i)+","+ids.get(i));
		}
	}*/

//desc of voters for displaying hamletwise. 

/*public static Comparator<SelectOptionVO> sourceSort = new Comparator<SelectOptionVO>()
{
	  
  public int compare(SelectOptionVO cstVO1, SelectOptionVO cstVO2)
	{
	   return (cstVO2.getId().intValue()) - (cstVO1.getId().intValue());
	}
};*/
