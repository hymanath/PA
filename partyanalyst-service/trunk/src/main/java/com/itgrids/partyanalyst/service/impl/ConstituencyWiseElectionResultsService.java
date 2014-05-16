package com.itgrids.partyanalyst.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.dto.BaseCandidateResultVO;
import com.itgrids.partyanalyst.dto.BasePartyIdsMAp;
import com.itgrids.partyanalyst.service.IConstituencyWiseElectionResultsService;


public class ConstituencyWiseElectionResultsService implements
		IConstituencyWiseElectionResultsService {
	
	private final static Logger log = Logger.getLogger(ConstituencyWiseElectionResultsService.class);

	@Autowired
	private IPartyTrendsDAO partyTrendsDAO;
	@Autowired
	private  IDelimitationConstituencyDAO delimitationConstituencyDAO;
    @Override
	public Object constituencyResults(String stateNo,Long assemblylevel,Long constituencyNo,String description)
	{
		String insertFor="";
		if(!assemblylevel.equals(1L))
			insertFor="Parliament";
		else
			insertFor="Constituency";
		StringBuffer sb=new StringBuffer(description);
		insertDataBasedOnString(constituencyNo.toString(), insertFor, sb,stateNo);
		return "sucess";
	}
	
	//common to all methods
	 public List<BaseCandidateResultVO> divideStrinIntoResults(StringBuffer sb,String constNo,Properties prop,String insertFor)
	 {
		 
	List<BaseCandidateResultVO> constList =new ArrayList<BaseCandidateResultVO>();
	//read data from properties file
	String divForConst=null;
	
   
    if(!insertFor.equals("Parliament"))
     divForConst=(String) prop.get("divToConst");
    else
      divForConst=(String) prop.get("divToParliament");
    String resultFlag=(String) prop.get("resultFlag");
    int statusCount=Integer.valueOf(prop.get("resultStatusDivCount").toString()) ;
    int  tableCount=Integer.valueOf(prop.get("tableDataDivCount").toString()) ; 
    
	 StringBuffer sbString =new StringBuffer();
	 sbString.append("<div id=\"");
	 sbString.append(divForConst);
	 sbString.append("\"[\\s\\S]*?<table.*?>(<tr.*?>(<td.*?>.*</td>)*</tr>)</table>");
	 System.out.println(sbString);
		// String patern="<div id=\"div1\"[\\s\\S]*?<table.*?>(<tr.*?>(<td.*?>.*</td>)*</tr>)</table>"; 
	 String pattern2="<td.*?>([\\s\\S]+?)</td>";
	 String patern=sbString.toString();
	 Pattern pat =    Pattern.compile(patern, Pattern.CASE_INSENSITIVE);
	 Matcher matcher=pat.matcher(sb);
	 String str=null;
		 while (matcher.find())
		 {
		 str=matcher.group(2).toString();
	     }
		 if(str==null) {
			 System.out.println("Some Thing Wrong With URl"+sb.toString());
			 log.debug("constNo Not Have A Profer Url"+constNo+"data i ahve got is "+sb.toString());
				 return null;
				 
		 }
		 log.debug(str);
		Pattern pat1 =    Pattern.compile(pattern2, Pattern.CASE_INSENSITIVE);
		Matcher matcher1=pat1.matcher(str);
		int count=1;
			
		int flagCount=0;
		BaseCandidateResultVO vo = new BaseCandidateResultVO();
		   while (matcher1.find()) {
			   try {
				   if(count==statusCount) {
				log.debug("======="+matcher1.group(1).toString());
				String resultStatus=matcher1.group(1).toString().trim();
				if(resultStatus.equalsIgnoreCase(resultFlag))
					vo.setStatus(1);
				   }
				if(count>tableCount)
				{
					

					if(flagCount<=2) {
						System.out.print("======="+matcher1.group(1).toString());
						
						String data=matcher1.group(1).toString().trim();
						String data1=data+"";
						log.debug("==="+data);
						data=data.replaceAll("\\.", " ").replaceAll("  ", " ").replaceAll("[â?]", "");
					if(flagCount==0)
					vo.setCandidateName(data);
					if(flagCount==1)							
					vo.setPartyName(data1);
					if(flagCount==2)	{
					vo.setCount(Long.valueOf(data));
					flagCount=-1;
					//vo.setCandidateName(matcher1.group(1).toString());
					constList.add(vo);
					System.out.println();
					flagCount=-1;
					vo=new BaseCandidateResultVO();
					}
					}
					else {
						vo.setCandidateName(matcher1.group(1).toString());
						constList.add(vo);
						System.out.println();
						flagCount=-1;
						vo=new BaseCandidateResultVO();
					}
					flagCount++;
			}
				count++;
				    }
		   
			
		   catch (Exception e) {
			Log.debug("Exception Raised for"+constNo+e.getMessage());
		}
		   }//while
			return constList;
}
	 public static String roundTo2DigitsFloatValue(Float number){
	  
	  Log.debug("Entered into the roundTo2DigitsFloatValue service method");
	  
	  String result = "";
	  try
	  {
		  
		
		NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
		f.setMaximumFractionDigits(2);  
		f.setMinimumFractionDigits(2);
		
		result =  f.format(number);
	  }catch(Exception e)
	  {
		  Log.error("Exception raised in roundTo2DigitsFloatValue service method");
		  e.printStackTrace();
	  }
	  return result;
}
     public static Properties loadProperties()
     {     Properties prop = new  Properties();
    	 InputStream in=null;
 		try {
 			
 			//reading paroperties file
 			in = ConstituencyWiseElectionResultsService.class.getClassLoader().getResourceAsStream("electionresults2014.properties");
 		  
 		    prop.load(in);
 		}catch (Exception e) {
			log.debug("Exception Occured while loading properties file");
		}
		return prop;
     }
     
     //for individual result
     public void insertDataBasedOnString(String constNo,String insertFor,StringBuffer text, String stateNo)
     {
 		Map<Long,Long> constIdsMap=new HashMap<Long,Long>();
 		Map<Long,Long> totalVotesMap=new HashMap<Long,Long>();
 		InputStream in=null;
 		try {
 			
 			//reading paroperties file
 			in = ConstituencyWiseElectionResultsService.class.getClassLoader().getResourceAsStream("electionresults2014.properties");
 		    Properties prop = new  Properties();
 		    prop.load(in);
 		    Long electionId=null;
 		    Long electionScopeId=null;
 			Long stateId=Long.valueOf((String) prop.get("stateId"));

 		    if(!insertFor.equalsIgnoreCase("Parliament"))
 		    { 
 		    	electionId=Long.valueOf((String) prop.get("electionId"));
 		    	electionScopeId=Long.valueOf((String) prop.get("electionScopeId"));
 		    }
 		    else
 		    {  
 		    	stateId=BasePartyIdsMAp.getStateNoMap().get(stateNo.toString()).longValue();
 		    	electionId=Long.valueOf((String) prop.get("electionIdForPaliament"));
 		    	electionScopeId=Long.valueOf((String) prop.get("electionScopeIdForPaliament"));
 		    	
 		    }
 			
 			
 		    List<Object[]>	 objs=(List<Object[]>) partyTrendsDAO.getVotesPolledForConst(electionId,stateId);
 		    
 		    for (Object[] objects : objs) {
 		    	totalVotesMap.put(Long.valueOf(objects[0].toString()), Double.valueOf(objects[1].toString()).longValue());
 			}
 		    System.out.println(totalVotesMap.size());
 	      
 			List<Object[]> value = delimitationConstituencyDAO.getConstituencyNoByCountry(stateId,2009L,electionScopeId);
 			for (Object[] objects : value) {
 				//System.out.println(objects[1]+"====="+objects[0]);
 				constIdsMap.put(Long.valueOf(objects[1].toString()), Long.valueOf(objects[0].toString()));
 			}
 			System.out.println(constIdsMap.size());
 			
 			List<BaseCandidateResultVO> candidatesList=	divideStrinIntoResults(text,constNo, prop, insertFor);

 			insertCandidateResult(constNo, prop, constIdsMap, totalVotesMap, stateId, electionId, electionScopeId,insertFor,candidatesList);
 		
 		}catch (Exception e) {
 			log.error("exception thrown in candidate result"+e.getMessage());
 		}finally {
 			
 			  if(in!=null)
 				try {
 					in.close();
 				} catch (IOException e) {					
 					e.printStackTrace();
 				}
 			
 		}
 			
 		
     }
    
     public void  insertCandidateResult(String constNo,Properties prop, Map<Long,Long> constIdsMap,Map<Long,Long> totalVotesMap,Long stateId,Long  electionId,Long electionScopeId, String insertFor, List<BaseCandidateResultVO> candidatesList)
		
		{
			 
			try {
		
		
	//	List<BaseCandidateResultVO> candidatesList=	divideStrinIntoResults(sb,constNo, prop, insertFor);
		
		Comparator<BaseCandidateResultVO> countComp=new Comparator<BaseCandidateResultVO>() {

			@Override
			public int compare(BaseCandidateResultVO o1, BaseCandidateResultVO o2) {
			
				return o2.getCount().compareTo(o1.getCount());
			}
		};
		Long constId=Long.valueOf(constNo);
		Collections.sort(candidatesList, countComp);
		Long topCount=0L;
		Long constituencyId=constIdsMap.get(constId);
		if(constituencyId==null)
			constituencyId=40761L;//==================================================
		long totalvotespolled=0;
		for(int rankers=0;rankers<candidatesList.size();rankers++)
		{
			BaseCandidateResultVO bvo=candidatesList.get(rankers);
			
			Long  count=bvo.getCount();
			totalvotespolled=totalvotespolled+count;
			bvo.setRank((long)rankers+1);
			Long totalVotesPolled=totalVotesMap.get(constituencyId);
			//set votes percentage here
			if(totalVotesMap.containsKey(constituencyId) )
			{
				
				if(totalVotesPolled!=0L)
				{
					//caculate percentage
					float percentage=(float)((float)count/(float)totalVotesPolled)*100;
					bvo.setVotesPercengate(roundTo2DigitsFloatValue(percentage));
				}
				
				
			}
			
			//set margin here and percentage here
			if(rankers==0)
			{
				topCount=count;
				BaseCandidateResultVO bvo1=candidatesList.get(1);
				Long secondCount=bvo1.getCount();
				
				Long marginVotes=topCount-secondCount;
				//calculate percentage
				bvo.setMarginVotes(marginVotes.doubleValue());
				if(totalVotesPolled!=0L)
				{
				
					//caculate percentage
					float percentage=(float)((float)marginVotes/(float)totalVotesPolled)*100;
					bvo.setMarginVotesPercentage(roundTo2DigitsFloatValue(percentage));
				}
				
			}else
			{
				Long marginVotes=topCount-count;
				//calculate percentage
				bvo.setMarginVotes(marginVotes.doubleValue());
				if(totalVotesPolled!=0L)
				{
				
					//caculate percentage
					float percentage=(float)((float)marginVotes/(float)totalVotesPolled)*100;
					bvo.setMarginVotesPercentage(roundTo2DigitsFloatValue(percentage));
			}
			
			}
			
		}
		
		System.out.println(candidatesList);
		Map<String,Integer> partyIds=BasePartyIdsMAp.getMap();

		for (BaseCandidateResultVO baseCandidateResultVO : candidatesList) {
			
			//check whether partyId is avilable or not
			
			Integer status=baseCandidateResultVO.getStatus();
			if(status!=null && status.equals(1))
			{
				//update constituency election
				int status1=partyTrendsDAO.updateCandidateElection(electionId, status, constituencyId);
				Log.debug("SucessFully Completed"+constituencyId+"==="+constNo+"=="+status1);
			}
				
			
			String pName=baseCandidateResultVO.getPartyName();
			
			
			//if partyName Available
			
			
			if(partyIds.containsKey(pName))
			{
				System.out.println("inside parliament===="+pName);
			    //if not  independent  do  this
				
				Long partyId=Long.valueOf(partyIds.get(pName).toString());
				
				if(!partyId.equals(366L) && !partyId.equals(232323L))
				{
			   //update candidate result  by using partyId and electionId if his not not independent
					//constIdsMap.get(constId);
				int updatesCount =partyTrendsDAO.updateCandidateReult(partyId, electionId, baseCandidateResultVO.getCount().doubleValue(),constituencyId,baseCandidateResultVO.getVotesPercengate(),baseCandidateResultVO.getMarginVotes(),baseCandidateResultVO.getMarginVotesPercentage());

				System.out.println(updatesCount);
				if(updatesCount!=1)
				{
					Log.warn("failed updation of party"+partyId+"and constId"+partyId);
					System.out.println("failed updation of party "+partyId+" and constId "+partyId);
				}
				}else {
					if(partyId.equals(366L)) {
				//here update independent
					int updated =partyTrendsDAO.updateCandidateReultForIndependents(baseCandidateResultVO.getCandidateName(), electionId, baseCandidateResultVO.getCount().doubleValue(),constituencyId,baseCandidateResultVO.getVotesPercengate(),baseCandidateResultVO.getMarginVotes(),baseCandidateResultVO.getMarginVotesPercentage());
					System.out.println(updated);
						if(updated!=1)
						{
							Log.warn("failed updation of independent "+baseCandidateResultVO.getCandidateName());
						}
					}else {
							if(partyId.equals(232323L)) {
								//update contituency election result
								int updatedS=partyTrendsDAO.updateNotaVotesInConstElectResult(electionId, baseCandidateResultVO.getCount(), constituencyId);
								Log.warn("nota "+updatedS);
								System.out.println("nota not updated any where ");
							}
						
					}
				}//end of else
			
			}else
			{
				// acknowledge to user this partyname not in database need to do something
				System.out.println("the party names those not avilable =="+pName);
			}
			
		}
	}catch (Exception e) {
		log.debug("exception raised at database data updation "+constNo+" ==="+e.getMessage());
	}finally{
		//release resources here 
	}
}

     
     //bulk inser methods need to change for parliament
     
     public void bulkDataInsertUsingRemotingWebserviceCall(String insertFor) {
    	 
    	 Map<Long,Long> constIdsMap=new HashMap<Long,Long>();
  		 Map<Long,Long> totalVotesMap=new HashMap<Long,Long>();
		    Properties prop = new  Properties();

 		InputStream in=null;
 		try {
 			
 			//reading paroperties file
 			in = ConstituencyWiseElectionResultsService.class.getClassLoader().getResourceAsStream("electionresults2014.properties");
 		    prop.load(in);
 		    Long electionId=null;
 		    Long electionScopeId=null;
 			Long stateId=Long.valueOf((String) prop.get("stateId"));

 		    if(!insertFor.equalsIgnoreCase("Parliament"))
 		    {
 		    	electionId=Long.valueOf((String) prop.get("electionId"));
 		    	electionScopeId=Long.valueOf((String) prop.get("electionScopeId"));
 		    }
 		    else
 		    {
 		    	electionId=Long.valueOf((String) prop.get("electionIdForPaliament"));
 		    	electionScopeId=Long.valueOf((String) prop.get("electionScopeIdForPaliament"));
 		    }
 			
 			     
 			
 			//System.exit(0);
 			
 			//get totalvotes polled for all constituencies
 		    List<Object[]>	 objs=(List<Object[]>) partyTrendsDAO.getVotesPolledForConst(electionId,stateId);
 		    
 		    for (Object[] objects : objs) {
 		    	totalVotesMap.put(Long.valueOf(objects[0].toString()), Double.valueOf(objects[1].toString()).longValue());
 			}
 		    System.out.println(totalVotesMap.size());
 	       // System.exit(0);
 			
 			//load all constituencies which are not completed
 			List<Object[]> value = delimitationConstituencyDAO.getConstituencyNoByCountry(stateId,2009L,electionScopeId);
 			for (Object[] objects : value) {
 				//System.out.println(objects[1]+"====="+objects[0]);
 				constIdsMap.put(Long.valueOf(objects[1].toString()), Long.valueOf(objects[0].toString()));
 			}
 			System.out.println(constIdsMap.size());
 			
 			
 			// get not completed constituencies			
 			List<?>  filtered=partyTrendsDAO.loadConStituencyIdsWithNo(electionId,stateId,electionScopeId,2009L);
 			log.debug("remaining to complete"+filtered.size());
 			
 			for (Object object : filtered) {
 				
 	 			insertCandidateResult(object.toString(), prop, constIdsMap, totalVotesMap, stateId, electionId, electionScopeId,insertFor);

			}
 		}catch (Exception e) {
 			log.error("exception thrown in candidate result"+e.getMessage());
 		}finally {
 			  if(in!=null)
 				try {
 					in.close();
 				} catch (IOException e) {					
 					e.printStackTrace();
 				}
 			 prop=null;
 		}//finally 
 			
 		}
     public void  insertCandidateResult(String constNo,Properties prop, Map<Long,Long> constIdsMap,Map<Long,Long> totalVotesMap,Long stateId,Long  electionId,Long electionScopeId, String insertFor)
		
		{
    	 
    	 List<BaseCandidateResultVO> candidatesList=null;
			try {
		
		
		candidatesList=	getCandidatesVotesForConstituency(constNo,prop,insertFor);
		
		Comparator<BaseCandidateResultVO> countComp=new Comparator<BaseCandidateResultVO>() {

			@Override
			public int compare(BaseCandidateResultVO o1, BaseCandidateResultVO o2) {
			
				return o2.getCount().compareTo(o1.getCount());
			}
		};
		Long constId=Long.valueOf(constNo);
		Collections.sort(candidatesList, countComp);
		Long topCount=0L;
		Long constituencyId=constIdsMap.get(constId);
		if(constituencyId==null)
			constituencyId=40761L;//==================================================
		long totalvotespolled=0;
		for(int rankers=0;rankers<candidatesList.size();rankers++)
		{
			BaseCandidateResultVO bvo=candidatesList.get(rankers);
			
			Long  count=bvo.getCount();
			totalvotespolled=totalvotespolled+count;
			bvo.setRank((long)rankers+1);
			Long totalVotesPolled=totalVotesMap.get(constituencyId);
			//set votes percentage here
			if(totalVotesMap.containsKey(constituencyId) )
			{
				
				if(totalVotesPolled!=0L)
				{
					//caculate percentage
					float percentage=(float)((float)count/(float)totalVotesPolled)*100;
					bvo.setVotesPercengate(roundTo2DigitsFloatValue(percentage));
				}
				
				
			}
			
			//set margin here and percentage here
			if(rankers==0)
			{
				topCount=count;
				BaseCandidateResultVO bvo1=candidatesList.get(1);
				Long secondCount=bvo1.getCount();
				
				Long marginVotes=topCount-secondCount;
				//calculate percentage
				bvo.setMarginVotes(marginVotes.doubleValue());
				if(totalVotesPolled!=0L)
				{
				
					//caculate percentage
					float percentage=(float)((float)marginVotes/(float)totalVotesPolled)*100;
					bvo.setMarginVotesPercentage(roundTo2DigitsFloatValue(percentage));
				}
				
			}else
			{
				Long marginVotes=topCount-count;
				//calculate percentage
				bvo.setMarginVotes(marginVotes.doubleValue());
				if(totalVotesPolled!=0L)
				{
				
					//caculate percentage
					float percentage=(float)((float)marginVotes/(float)totalVotesPolled)*100;
					bvo.setMarginVotesPercentage(roundTo2DigitsFloatValue(percentage));
			}
			
			}
			
		}
		
		System.out.println(candidatesList);
		Map<String,Integer> partyIds=BasePartyIdsMAp.getMap();

		for (BaseCandidateResultVO baseCandidateResultVO : candidatesList) {
			
			//check whether partyId is avilable or not
			
			Integer status=baseCandidateResultVO.getStatus();
			if(status!=null && status.equals(1))
			{
				//update constituency election
				int status1=partyTrendsDAO.updateCandidateElection(electionId, status, constituencyId);
				Log.debug("SucessFully Completed"+constituencyId+"==="+constNo+"=="+status1);
			}
				
			
			String pName=baseCandidateResultVO.getPartyName();
			
			
			//if partyName Available
			
			
			if(partyIds.containsKey(pName))
			{
				System.out.println("inside parliament===="+pName);
			    //if not  independent  do  this
				
				Long partyId=Long.valueOf(partyIds.get(pName).toString());
				
				if(!partyId.equals(366L) && !partyId.equals(232323L))
				{
			   //update candidate result  by using partyId and electionId if his not not independent
					//constIdsMap.get(constId);
				int updatesCount =partyTrendsDAO.updateCandidateReult(partyId, electionId, baseCandidateResultVO.getCount().doubleValue(),constituencyId,baseCandidateResultVO.getVotesPercengate(),baseCandidateResultVO.getMarginVotes(),baseCandidateResultVO.getMarginVotesPercentage());

				System.out.println(updatesCount);
				if(updatesCount!=1)
				{
					Log.warn("failed updation of party"+partyId+"and constId"+partyId);
					System.out.println("failed updation of party "+partyId+" and constId "+partyId);
				}
				}else {
					if(partyId.equals(366L)) {
				//here update independent
					int updated =partyTrendsDAO.updateCandidateReultForIndependents(baseCandidateResultVO.getCandidateName(), electionId, baseCandidateResultVO.getCount().doubleValue(),constituencyId,baseCandidateResultVO.getVotesPercengate(),baseCandidateResultVO.getMarginVotes(),baseCandidateResultVO.getMarginVotesPercentage());
					System.out.println(updated);
						if(updated!=1)
						{
							Log.warn("failed updation of independent "+baseCandidateResultVO.getCandidateName());
						}
					}else {
							if(partyId.equals(232323L)) {
								//update contituency election result
								int updatedS=partyTrendsDAO.updateNotaVotesInConstElectResult(electionId, baseCandidateResultVO.getCount(), constituencyId);
								Log.warn("nota "+updatedS);
								System.out.println("nota not updated any where ");
							}
						
					}
				}//end of else
			
			}else
			{
				// acknowledge to user this partyname not in database need to do something
				log.debug("the party names those not avilable =="+pName);
			}
			
		}
	} catch (Exception e) {
		log.debug("exception raised because of cause "+e.getCause());
		e.printStackTrace();
	}finally{
		candidatesList=null;
	}
}
     public List<BaseCandidateResultVO> getCandidatesVotesForConstituency(String constNo,Properties prop, String insertFor) throws IOException
 	{
 	//	List<BaseCandidateResultVO> constList =new ArrayList<BaseCandidateResultVO>();
 		//String url="http://affidavitarchive.nic.in/DynamicAffidavitDisplay/CANDIDATEAFFIDAVIT.aspx?YEARID=May-2014 ( GEN )&AC_No=84&st_code=S01&constType=AC";
   	     //String url="http://eciresults.ap.nic.in/ConstituencywiseS26"+constNo+".htm?ac="+constNo;
 		
 		//read propertiesFileDta
 	    
 	    String url1="";
 	    		 if(!insertFor.equalsIgnoreCase("Parliament"))
 	 		    {
 	    			url1=(String) prop.get("url"); 
 	 		    }
 	 		    else
 	 		    {
 	 		    	url1=(String) prop.get("urlForPaliament"); 
 	 		    }
 	    		
 	    		 
 	    
 	    String stateNo=(String) prop.get("stateNo");
 	   /* String divForConst=(String) prop.get("divToConst");
 	    String resultFlag=(String) prop.get("resultFlag");
 	    int statusCount=Integer.valueOf(prop.get("resultStatusDivCount").toString()) ;
 	    int  tableCount=Integer.valueOf(prop.get("tableDataDivCount").toString()) ; */
 	   
 	    
 	    //end read properties
 	    
 		url1=url1.replaceAll("\\$", stateNo);
 		System.out.println(url1);
 		url1=url1.replaceAll("\\@", constNo);
 		String url=url1;
 		
 		System.out.println(url);
 		
 	   // System.exit(0);
      	 HttpClient client = null;
      	 PostMethod post = null;
          GetMethod get=null;
    		 client = new HttpClient(new MultiThreadedHttpConnectionManager());

    		 client.getHttpConnectionManager().getParams().setConnectionTimeout(Integer.parseInt("30000"));
    		 get=new GetMethod(url);
    		
    		try {
    			Thread.sleep(200);
    		int status=	client.executeMethod(get);
    		System.out.println(status);
    		}catch (Exception e) {
    			e.printStackTrace();
 			}
    		
    		//System.out.println(get.getResponseBodyAsString());
    		
    		/*String sb=null;
 		
 			sb = get.getResponseBodyAsString();*/
    	InputStream fis=	get.getResponseBodyAsStream();
     	BufferedReader br= new BufferedReader(new InputStreamReader(fis));
     	StringBuffer sb =new StringBuffer();
     	
     	String line = br.readLine();

         while (line != null) {
             sb.append(line);
          
             line = br.readLine();
         }
     	
     		
 		 get=null;
 		return  divideStrinIntoResults(sb, constNo, prop, insertFor);
 		 
 	}

  //bulk insert methods for parliament
     
    public void bulkDataInsertUsingRemotingWebserviceCallParliament(String insertFor,Long stateId) {
    	 
    	 Map<Long,Long> constIdsMap=new HashMap<Long,Long>();
  		 Map<Long,Long> totalVotesMap=new HashMap<Long,Long>();
		    Properties prop = new  Properties();

 		InputStream in=null;
 		try {
 			
 			//reading paroperties file
 			in = ConstituencyWiseElectionResultsService.class.getClassLoader().getResourceAsStream("electionresults2014.properties");
 		    prop.load(in);
 		    Long electionId=null;
 		    Long electionScopeId=null;

 		    if(!insertFor.equalsIgnoreCase("Parliament"))
 		    {
 		    	electionId=Long.valueOf((String) prop.get("electionId"));
 		    	electionScopeId=Long.valueOf((String) prop.get("electionScopeId"));
 		    }
 		    else
 		    {
 		    	electionId=Long.valueOf((String) prop.get("electionIdForPaliament"));
 		    	electionScopeId=Long.valueOf((String) prop.get("electionScopeIdForPaliament"));
 		    }
 			
 			     
 			
 			//System.exit(0);
 			
 			//get totalvotes polled for all constituencies
 		    List<Object[]>	 objs=(List<Object[]>) partyTrendsDAO.getVotesPolledForConst(electionId,stateId);
 		    
 		    for (Object[] objects : objs) {
 		    	totalVotesMap.put(Long.valueOf(objects[0].toString()), Double.valueOf(objects[1].toString()).longValue());
 			}
 		    System.out.println(totalVotesMap.size());
 	       // System.exit(0);
 			
 			//load all constituencies which are not completed
 			List<Object[]> value = delimitationConstituencyDAO.getConstituencyNoByCountry(stateId,2009L,electionScopeId);
 			for (Object[] objects : value) {
 				//System.out.println(objects[1]+"====="+objects[0]);
 				constIdsMap.put(Long.valueOf(objects[1].toString()), Long.valueOf(objects[0].toString()));
 			}
 			System.out.println(constIdsMap.size());
 			
 			
 			// get not completed constituencies			
 			List<?>  filtered=partyTrendsDAO.loadConStituencyIdsWithNo(electionId,stateId,electionScopeId,2009L);
 			log.debug("remaining to complete"+filtered.size());
 			
 			for (Object object : filtered) {
 				
 				insertCandidateResultForParliament(object.toString(), prop, constIdsMap, totalVotesMap, stateId, electionId, electionScopeId,insertFor);

			}
 		}catch (Exception e) {
 			log.error("exception thrown in candidate result"+e.getMessage());
 		}finally {
 			  if(in!=null)
 				try {
 					in.close();
 				} catch (IOException e) {					
 					e.printStackTrace();
 				}
 			 prop=null;
 		}//finally 
 			
 		}
     
    public void  insertCandidateResultForParliament(String constNo,Properties prop, Map<Long,Long> constIdsMap,Map<Long,Long> totalVotesMap,Long stateId,Long  electionId,Long electionScopeId, String insertFor)
	
	{
	 
	 List<BaseCandidateResultVO> candidatesList=null;
		try {
	
	
	candidatesList=	getCandidatesVotesForConstituency(constNo,prop,insertFor,stateId);
	
	Comparator<BaseCandidateResultVO> countComp=new Comparator<BaseCandidateResultVO>() {

		@Override
		public int compare(BaseCandidateResultVO o1, BaseCandidateResultVO o2) {
		
			return o2.getCount().compareTo(o1.getCount());
		}
	};
	Long constId=Long.valueOf(constNo);
	if(candidatesList==null)
	{
		return;
	}
	Collections.sort(candidatesList, countComp);
	Long topCount=0L;
	Long constituencyId=constIdsMap.get(constId);
	if(constituencyId==null)
		throw new RuntimeException("constituencyIdNotFoundForNumber"+constId );
		//constituencyId=40761L;//==================================================
	long totalvotespolled=0;
	for(int rankers=0;rankers<candidatesList.size();rankers++)
	{
		BaseCandidateResultVO bvo=candidatesList.get(rankers);
		
		Long  count=bvo.getCount();
		totalvotespolled=totalvotespolled+count;
		bvo.setRank((long)rankers+1);
		Long totalVotesPolled=totalVotesMap.get(constituencyId);
		//set votes percentage here
		if(totalVotesMap.containsKey(constituencyId) )
		{
			
			if(totalVotesPolled!=0L)
			{
				//caculate percentage
				float percentage=(float)((float)count/(float)totalVotesPolled)*100;
				bvo.setVotesPercengate(roundTo2DigitsFloatValue(percentage));
			}
			
			
		}
		
		//set margin here and percentage here
		if(rankers==0)
		{
			topCount=count;
			BaseCandidateResultVO bvo1=candidatesList.get(1);
			Long secondCount=bvo1.getCount();
			
			Long marginVotes=topCount-secondCount;
			//calculate percentage
			bvo.setMarginVotes(marginVotes.doubleValue());
			if(totalVotesPolled!=0L)
			{
			
				//caculate percentage
				float percentage=(float)((float)marginVotes/(float)totalVotesPolled)*100;
				bvo.setMarginVotesPercentage(roundTo2DigitsFloatValue(percentage));
			}
			
		}else
		{
			Long marginVotes=topCount-count;
			//calculate percentage
			bvo.setMarginVotes(marginVotes.doubleValue());
			if(totalVotesPolled!=0L)
			{
			
				//caculate percentage
				float percentage=(float)((float)marginVotes/(float)totalVotesPolled)*100;
				bvo.setMarginVotesPercentage(roundTo2DigitsFloatValue(percentage));
		}
		
		}
		
	}
	
	System.out.println(candidatesList);
	Map<String,Integer> partyIds=BasePartyIdsMAp.getMap();

	for (BaseCandidateResultVO baseCandidateResultVO : candidatesList) {
		
		//check whether partyId is avilable or not
		
		Integer status=baseCandidateResultVO.getStatus();
		if(status!=null && status.equals(1))
		{
			//update constituency election
			int status1=partyTrendsDAO.updateCandidateElection(electionId, status, constituencyId);
			Log.debug("SucessFully Completed"+constituencyId+"==="+constNo+"=="+status1);
		}
			
		
		String pName=baseCandidateResultVO.getPartyName();
		
		
		//if partyName Available
		
		
		if(partyIds.containsKey(pName))
		{
			System.out.println("inside parliament===="+pName);
		    //if not  independent  do  this
			
			Long partyId=Long.valueOf(partyIds.get(pName).toString());
			
			if(!partyId.equals(366L) && !partyId.equals(232323L))
			{
		   //update candidate result  by using partyId and electionId if his not not independent
				//constIdsMap.get(constId);
			int updatesCount =partyTrendsDAO.updateCandidateReult(partyId, electionId, baseCandidateResultVO.getCount().doubleValue(),constituencyId,baseCandidateResultVO.getVotesPercengate(),baseCandidateResultVO.getMarginVotes(),baseCandidateResultVO.getMarginVotesPercentage());

			System.out.println(updatesCount);
			if(updatesCount!=1)
			{
				Log.warn("failed updation of party"+partyId+"and constId"+partyId);
				System.out.println("failed updation of party "+partyId+" and constId "+partyId);
			}
			}else {
				if(partyId.equals(366L)) {
			//here update independent
				int updated =partyTrendsDAO.updateCandidateReultForIndependents(baseCandidateResultVO.getCandidateName(), electionId, baseCandidateResultVO.getCount().doubleValue(),constituencyId,baseCandidateResultVO.getVotesPercengate(),baseCandidateResultVO.getMarginVotes(),baseCandidateResultVO.getMarginVotesPercentage());
				System.out.println(updated);
					if(updated!=1)
					{
						Log.warn("failed updation of independent "+baseCandidateResultVO.getCandidateName());
					}
				}else {
						if(partyId.equals(232323L)) {
							//update contituency election result
							int updatedS=partyTrendsDAO.updateNotaVotesInConstElectResult(electionId, baseCandidateResultVO.getCount(), constituencyId);
							Log.warn("nota "+updatedS);
							System.out.println("nota not updated any where ");
						}
					
				}
			}//end of else
		
		}else
		{
			// acknowledge to user this partyname not in database need to do something
			log.debug("the party names those not avilable =="+pName);
		}
		
	}
} catch (Exception e) {
	log.debug("exception raised because of cause "+e.getCause());
	e.printStackTrace();
}finally{
	candidatesList=null;
}
}
    
    public List<BaseCandidateResultVO> getCandidatesVotesForConstituency(String constNo,Properties prop, String insertFor,Long stateId) throws IOException
 	{
 	//	List<BaseCandidateResultVO> constList =new ArrayList<BaseCandidateResultVO>();
 		//String url="http://affidavitarchive.nic.in/DynamicAffidavitDisplay/CANDIDATEAFFIDAVIT.aspx?YEARID=May-2014 ( GEN )&AC_No=84&st_code=S01&constType=AC";
   	     //String url="http://eciresults.ap.nic.in/ConstituencywiseS26"+constNo+".htm?ac="+constNo;
 		
 		//read propertiesFileDta
 	    
 	    String url1="";
 	    		 if(!insertFor.equalsIgnoreCase("Parliament"))
 	 		    {
 	    			url1=(String) prop.get("url"); 
 	 		    }
 	 		    else
 	 		    {
 	 		    	url1=(String) prop.get("urlForPaliament"); 
 	 		    }
 	    		
 	    		String stateNo=BasePartyIdsMAp.getStateMap().get(stateId.intValue());
 	    
 	   // String stateNo=(String) prop.get("stateNo");
 	   /* String divForConst=(String) prop.get("divToConst");
 	    String resultFlag=(String) prop.get("resultFlag");
 	    int statusCount=Integer.valueOf(prop.get("resultStatusDivCount").toString()) ;
 	    int  tableCount=Integer.valueOf(prop.get("tableDataDivCount").toString()) ; */
 	   
 	    
 	    //end read properties
 	    
 		url1=url1.replaceAll("\\$", stateNo);
 		System.out.println(url1);
 		url1=url1.replaceAll("\\@", constNo);
 		String url=url1;
 		
 		System.out.println(url);
 		
 	   // System.exit(0);
      	 HttpClient client = null;
      	 PostMethod post = null;
          GetMethod get=null;
    		 client = new HttpClient(new MultiThreadedHttpConnectionManager());

    		 client.getHttpConnectionManager().getParams().setConnectionTimeout(Integer.parseInt("30000"));
    		 get=new GetMethod(url);
    		
    		try {
    			Thread.sleep(200);
    		int status=	client.executeMethod(get);
    		System.out.println(status);
    		}catch (Exception e) {
    			Log.debug("Exception Raised Beacuse of "+e.getMessage());
    			e.printStackTrace();
 			}
    		
    		//System.out.println(get.getResponseBodyAsString());
    		
    		/*String sb=null;
 		
 			sb = get.getResponseBodyAsString();*/
    	InputStream fis=	get.getResponseBodyAsStream();
     	BufferedReader br= new BufferedReader(new InputStreamReader(fis));
     	StringBuffer sb =new StringBuffer();
     	
     	String line = br.readLine();

         while (line != null) {
             sb.append(line);
          
             line = br.readLine();
         }
     	
     		
 		 get=null;
 		return  divideStrinIntoResults(sb, constNo, prop, insertFor);
 		 
 	}

}
