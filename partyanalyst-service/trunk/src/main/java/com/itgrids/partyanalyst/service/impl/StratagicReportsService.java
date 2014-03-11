package com.itgrids.partyanalyst.service.impl;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportHelperVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsVerVO;
import com.itgrids.partyanalyst.service.IStratagicReportsService;

public class StratagicReportsService implements IStratagicReportsService{
	private static final Logger log = Logger.getLogger(StratagicReportsService.class);
	
	@Autowired IUserDAO userDAO;
	
	@Autowired IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	@Autowired IPartyTrendsDAO partyTrendsDAO;
	
	@Autowired INominationDAO nominationDAO;
	
	   
	public List<AgeRangeVO> getBoothWiseAddedAndDeletedVoters(Long constiId,Long pubId){
		List<AgeRangeVO> boothWiseAddedDeletedVoters=new ArrayList<AgeRangeVO>();
		try{
			//String constiId = "221";
			StringBuilder table = new StringBuilder();
			
			String queryham = "select VM.part_no,count(VM.voter_id),V.voter_age_range_id,VM.voter_status_id  from voter_modification VM, voter V where "+
			"V.voter_id = VM.voter_id AND "+
			"VM.constituency_id = "+constiId+" and "+
			"VM.publication_date_id = "+pubId+" and VM.voter_status_id in(1,2) "+ 
			"GROUP BY VM.part_no,VM.voter_status_id ,V.voter_age_range_id "+
			"ORDER BY VM.part_no";

			String queryham1 =  "select VM.part_no,count(VM.voter_id),VM.voter_status_id from voter_modification VM, voter V where "+
			"V.voter_id = VM.voter_id AND "+
			"VM.constituency_id = "+constiId+" and "+
			"VM.publication_date_id = "+pubId+"  and VM.voter_status_id in(1,2)  and V.age BETWEEN 18 and 22 "+ 
			"GROUP BY VM.part_no,VM.voter_status_id "+
			"ORDER BY VM.part_no";
			
			String queryham2 = "select p.panchayat_name,b.part_no from booth b,panchayat p where b.constituency_id = "+constiId+" and b.publication_date_id = "+pubId+" and b.local_election_body_id is null and b.panchayat_id = p.panchayat_id ";
			String queryham3 = "select t.tehsil_name,b.part_no from booth b,tehsil t where b.constituency_id = "+constiId+" and b.publication_date_id = "+pubId+" and b.local_election_body_id is not null and b.tehsil_id = t.tehsil_id ";	
			
			List<Object[]> list = userDAO.getData(queryham);
			
				List<Object[]> pancnames = userDAO.getData(queryham2);
				List<Object[]> muncnames = userDAO.getData(queryham3);
				
				Map<String,String> namesMap = new HashMap<String,String>();
				List<Long> loc = new ArrayList<Long>();
				for(Object[] data:pancnames){
					namesMap.put(data[1].toString(), data[0].toString());
					loc.add(new Long(data[1].toString().trim()));
				}
				for(Object[] data:muncnames){
					namesMap.put(data[1].toString(), data[0].toString()+" Municipality");
					loc.add(new Long(data[1].toString().trim()));
				}
				Map<String,AgeRangeVO> map = new HashMap<String,AgeRangeVO>();
				
				List<Object[]> boothTotalsList=boothPublicationVoterDAO.getTotalVotersOfBoothByConstituencyId(constiId, pubId);
				Map<String,Long> boothsTtlMap=new HashMap<String, Long>();
				if(boothTotalsList.size()>0){
					for(Object[] obj:boothTotalsList){
						boothsTtlMap.put(obj[1].toString(), Long.valueOf(obj[0].toString()));
					}
				}
				
				if(list != null && list.size() >0){
				
				for(Object[] data:list){
					AgeRangeVO vo = map.get(data[0].toString());
					Long boothTotalVoters=0l;
					if(vo == null){
						vo = new AgeRangeVO();
						vo.setPanchayat(namesMap.get(data[0].toString()));
						vo.setHamlet(data[0].toString());
						boothTotalVoters=boothsTtlMap.get(vo.getHamlet());
						vo.setTotalVotersInBooth(boothTotalVoters);
						
						map.put(data[0].toString(),vo);
					}
					
					
					if(data[1] != null){
					 if(((BigInteger)data[3]).longValue() == 1){
						if(((BigInteger)data[2]).longValue() == 2){
							vo.setAge18To25(((BigInteger)data[1]).longValue());
							/*vo.setAge18To25Per(calcPercentage(boothTotalVoters, vo.getAge18To25()));
							totalAdded+=vo.getAge18To25();*/
						}else if(((BigInteger)data[2]).longValue() == 3){
							vo.setAge26to35(((BigInteger)data[1]).longValue());
							/*vo.setAge26to35Per(calcPercentage(boothTotalVoters, vo.getAge26to35()));
							totalAdded+=vo.getAge26to35();*/
						}else if(((BigInteger)data[2]).longValue() == 4){
							vo.setAge36to45(((BigInteger)data[1]).longValue());
							/*vo.setAge36to45Per(calcPercentage(boothTotalVoters,vo.getAge36to45()));
							totalAdded+=vo.getAge36to45();*/
						}else if(((BigInteger)data[2]).longValue() == 5){
							vo.setAge46to60(((BigInteger)data[1]).longValue());
							/*vo.setAge46to60Per(calcPercentage(boothTotalVoters, vo.getAge46to60()));
							totalAdded+=vo.getAge46to60();*/
						}else if(((BigInteger)data[2]).longValue() == 6){
							vo.setAbove60(((BigInteger)data[1]).longValue());
							/*vo.setAbove60Per(calcPercentage(boothTotalVoters,vo.getAbove60()));
							totalAdded+=vo.getAbove60();*/
						}
					 }else{
						 if(((BigInteger)data[2]).longValue() == 2){
								vo.setDelAge18To25(((BigInteger)data[1]).longValue());
								/*totalDeleted+=vo.getDelAge18To25();
								vo.setDelAge18To25Per(calcPercentage(boothTotalVoters, vo.getDelAge18To25()));*/
							}else if(((BigInteger)data[2]).longValue() == 3){
								vo.setDelAge26to35(((BigInteger)data[1]).longValue());
								/*totalDeleted+=vo.getDelAge26to35();
								vo.setDelAge26to35Per(calcPercentage(boothTotalVoters, vo.getDelAge26to35()));*/
							}else if(((BigInteger)data[2]).longValue() == 4){
								vo.setDelAge36to45(((BigInteger)data[1]).longValue());
								/*totalDeleted+=vo.getDelAge36to45();
								vo.setDelAge36to45Per(calcPercentage(boothTotalVoters, vo.getDelAge36to45()));*/
							}else if(((BigInteger)data[2]).longValue() == 5){
								vo.setDelAge46to60(((BigInteger)data[1]).longValue());
								/*totalDeleted+=vo.getDelAge46to60();
								vo.setDelAge46to60Per(calcPercentage(boothTotalVoters, vo.getDelAge46to60()));*/
							}else if(((BigInteger)data[2]).longValue() == 6){
								vo.setDelAbove60(((BigInteger)data[1]).longValue());
								/*totalDeleted+=vo.getDelAbove60();
								vo.setDelAbove60Per(calcPercentage(boothTotalVoters, vo.getDelAbove60()));*/
							}
					 }
					}
					//vo.setTotalVotersAdded(totalAdded);
					//vo.setTotalVotersDeleted(totalDeleted);
					//vo.setTotalVotersAddedPer(calcPercentage(boothTotalVoters,totalAdded));
					//vo.setTotalVotersDeletedPer(calcPercentage(boothTotalVoters,totalDeleted));
				}
				
				}
				list = userDAO.getData(queryham1);
				if(list != null && list.size() >0){
					
					
					for(Object[] data:list){
						AgeRangeVO vo = map.get(data[0].toString());
						if(vo == null){
							vo = new AgeRangeVO();
							vo.setPanchayat(namesMap.get(data[0].toString()));
							vo.setHamlet(data[0].toString());
							map.put(data[0].toString(),vo);
						}
						if(data[1] != null){
						 if(((BigInteger)data[2]).longValue() == 1){
							 vo.setYoungVoters(((BigInteger)data[1]).longValue());
							// vo.setYoungVotersPer(calcPercentage(vo.getTotalVotersInBooth(), vo.getYoungVoters()));
						 }else{
							 vo.setDelYoungVoters(((BigInteger)data[1]).longValue());
							// vo.setDelYoungVotersPer(calcPercentage(vo.getTotalVotersInBooth(), vo.getDelYoungVoters()));
						 }
						}
					}
					}
				Collections.sort(loc);
				
				
				
				/*table.append("<table>");
				table.append("<tr>");
				table.append("<td>Panchayat</td><td>Booth</td><td>Young Voters</td><td>18 - 25</td><td>26 - 35</td><td>36 - 45</td><td>46 - 60</td><td>Above 60</td><td>Young Voters</td><td>18 - 25</td><td>26 - 35</td><td>36 - 45</td><td>46 - 60</td><td>Above 60</td>");
				table.append("</tr>");*/
				for(Long id:loc){
					AgeRangeVO vo = map.get(id.toString());
					if(vo == null){
						vo = new AgeRangeVO();
						vo.setPanchayat(namesMap.get(id.toString()));
						vo.setHamlet(id.toString());
					}
					
					//PERCENTAGE CALUCULATIONS
					vo.setTotalVotersAdded(vo.getAge18To25()+vo.getAge26to35()+vo.getAge36to45()+vo.getAge46to60()+vo.getAbove60());
					vo.setTotalVotersAddedPer(calcPercentage(vo.getTotalVotersInBooth(), vo.getTotalVotersAdded()));
					
					vo.setAge18To25Per(calcPercentage(vo.getTotalVotersAdded(), vo.getAge18To25()));
					vo.setAge26to35Per(calcPercentage(vo.getTotalVotersAdded(), vo.getAge26to35()));
					vo.setAge36to45Per(calcPercentage(vo.getTotalVotersAdded(),vo.getAge36to45()));
					vo.setAge46to60Per(calcPercentage(vo.getTotalVotersAdded(), vo.getAge46to60()));
					vo.setAbove60Per(calcPercentage(vo.getTotalVotersAdded(),vo.getAbove60()));
					vo.setYoungVotersPer(calcPercentage(vo.getTotalVotersAdded(), vo.getYoungVoters()));
					
					
					vo.setTotalVotersDeleted(vo.getDelAge18To25()+vo.getDelAge26to35()+vo.getDelAge36to45()+vo.getDelAge46to60()+vo.getDelAbove60());
					vo.setTotalVotersDeletedPer(calcPercentage(vo.getTotalVotersInBooth(), vo.getTotalVotersDeleted()));
					vo.setDelAge18To25Per(calcPercentage(vo.getTotalVotersDeleted(), vo.getDelAge18To25()));
					vo.setDelAge26to35Per(calcPercentage(vo.getTotalVotersDeleted(), vo.getDelAge26to35()));
					vo.setDelAge36to45Per(calcPercentage(vo.getTotalVotersDeleted(), vo.getDelAge36to45()));
					vo.setDelAge46to60Per(calcPercentage(vo.getTotalVotersDeleted(), vo.getDelAge46to60()));
					vo.setDelAbove60Per(calcPercentage(vo.getTotalVotersDeleted(), vo.getDelAbove60()));
					vo.setDelYoungVotersPer(calcPercentage(vo.getTotalVotersDeleted(), vo.getDelYoungVoters()));
					
					boothWiseAddedDeletedVoters.add(vo);
					/*table.append("<tr>");
					   table.append("<td>"+vo.getPanchayat()+"</td><td>Booth-"+vo.getHamlet()+"</td><td>"+vo.getYoungVoters()+"</td><td>"+vo.getAge18To25()+"</td><td>"+vo.getAge26to35()+"</td><td>"+vo.getAge36to45()+"</td><td>"+vo.getAge46to60()+"</td><td>"+vo.getAbove60()+"</td><td>"+vo.getDelYoungVoters()+"</td><td>"+vo.getDelAge18To25()+"</td><td>"+vo.getDelAge26to35()+"</td><td>"+vo.getDelAge36to45()+"</td><td>"+vo.getDelAge46to60()+"</td><td>"+vo.getDelAbove60()+"</td>");
					table.append("</tr>");*/
				}
				/*table.append("</table>");*/
			System.out.println(table.toString());
			}catch(Exception e){
				e.printStackTrace();
			}
		
		return boothWiseAddedDeletedVoters;
	}
	
	public String calcPercentage(Long total,Long count){
		if(total>0){
			return count != 0 ? roundTo2DigitsFloatValue((float) count * 100f / total): "0.00";
		}else{
			return "0.00";
		}
	}
	
	public String roundTo2DigitsFloatValue(Float number){
		  
		log.debug("Entered into the roundTo2DigitsFloatValue service method");
		  
		  String result = "";
		  try
		  {
			  
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			result =  f.format(number);
		  }catch(Exception e)
		  {
			  log.error("Exception raised in roundTo2DigitsFloatValue service method");
			  e.printStackTrace();
		  }
		  return result;
	  }
	public String roundTo2DigitsDoubleValue(Double number){
		  
		log.debug("Entered into the roundTo2DigitsFloatValue service method");
		  
		  String result = "";
		  try
		  {
			  
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			result =  f.format(number);
		  }catch(Exception e)
		  {
			  log.error("Exception raised in roundTo2DigitsFloatValue service method");
			  e.printStackTrace();
		  }
		  return result;
	  }
	public List<PartyElectionTrendsReportVO> getPreviousTrendsReport(Long constId){
		
		List<Object[]> ids = (List<Object[]>)partyTrendsDAO.getPreviousTrendsData(null, constId);
		Map<Long,PartyElectionTrendsReportVO> maps = new HashMap<Long, PartyElectionTrendsReportVO>();

		System.out.println(ids.size());
		
		for (Object[] object : ids) {
			
		if(maps.containsKey(Long.valueOf(object[0].toString())))
		{
			PartyElectionTrendsReportVO vo =maps.get(Long.valueOf(object[0].toString()));
	        vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
	        vo.setTotalVoters(((Double)object[1]).longValue());
	        vo.setTotalVotesPolled(((Double)object[2]).longValue());
	        vo.setDistrictId(Long.valueOf(object[9].toString()));
	        PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
	        voh.setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((Double)object[7])));
	        voh.setVotesEarned(((Double)object[4]).longValue());
	        voh.setRank((Long)object[8]);
	        if(((Long)object[3]).equals(872L))
	        {
	     	   voh.setMarginVotes(((Double)object[5]).longValue());
	     	   voh.setMarginVotesPercentage((Double.valueOf(object[6].toString())));
	     	   vo.setTdpVo(voh);
	        }else if(((Long)object[3]).equals(362L))
	        {
	     	   vo.setIncVo(voh);

	        }else if(((Long)object[3]).equals(662L)||((Long)object[3]).equals(1117L)  )
	        {
	     	   vo.setPrpVo(voh);

	        }
	        else if(Long.valueOf(object[9].toString())<10){
	        	if(((Long)object[3]).equals(163L)){
	        		vo.setBjpVo(voh);
	        	}
	        	else if(((Long)object[3]).equals(886L)){
	        		vo.setTrsVo(voh);
	        	}
	        	 else {
	  	     	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
	  	     	   if(vo1!=null){
	  	     		   vo1.setVotesEarned(vo1.getVotesEarned()+((Double)object[4]).longValue()) ;
	  	     		  if( vo1.getRank()<(Long)object[8] )
	          			  vo1.setRank((Long)object[8]); 
	  	     	   }
	  	        
	  	     	   else 
	  	     	   {
	  	     		   vo1= new PartyElectionTrendsReportHelperVO();
	  	     		   vo1.setVotesEarned(((Double)object[4]).longValue()) ;
	  		 	     	  vo1.setRank((Long)object[8]); 

	  	     	   }
	  	     	  //vo1.setRank((Long)object[8]); 
	  	     	  vo.setOthersVo(vo1);
	  	        }
	        }
	        else {
	     	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
	     	   if(vo1!=null){
	     		   vo1.setVotesEarned(vo1.getVotesEarned()+((Double)object[4]).longValue()) ;
	     		  if( vo1.getRank()<(Long)object[8] )
        			  vo1.setRank((Long)object[8]); 
	     	   }
	        
	     	   else 
	     	   {
	     		   vo1= new PartyElectionTrendsReportHelperVO();
	     		   vo1.setVotesEarned(((Double)object[4]).longValue()) ;
		 	     	  vo1.setRank((Long)object[8]); 

	     	   }
	     	  //vo1.setRank((Long)object[8]); 
	     	  vo.setOthersVo(vo1);
	        }
			
		}
		else
		{
			PartyElectionTrendsReportVO vo =new PartyElectionTrendsReportVO();
			           vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
			           vo.setTotalVoters(((Double)object[1]).longValue());
			           vo.setTotalVotesPolled(((Double)object[2]).longValue());
			           vo.setDistrictId(Long.valueOf(object[9].toString()));
			           PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
			           voh.setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((Double)object[7])));
			           voh.setVotesEarned(((Double)object[4]).longValue());
				     	 voh.setRank((Long)object[8]); 

			           if(((Long)object[3]).equals(872L))
			           {
			        	   voh.setMarginVotes((Long)object[5]);
			        	   voh.setMarginVotesPercentage(((Double)object[6]));
			        	   vo.setTdpVo(voh);
			           }else if(((Long)object[3]).equals(362L))
			           {
			        	   vo.setIncVo(voh);

			           }else if(((Long)object[3]).equals(662L)||((Long)object[3]).equals(1117L)  )
			           {
			        	   vo.setPrpVo(voh);

			           }
			           else if(Long.valueOf(object[9].toString())<10){
			           		if(((Long)object[3]).equals(163L)){
			           			vo.setBjpVo(voh);
			           		}
			           		else if(((Long)object[3]).equals(886L)){
			           			vo.setTrsVo(voh);
			           		}
			           	 else {
				        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
				        	   if(vo1!=null){
				        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Double)object[4]).longValue()) ;
				        		  if( vo1.getRank()<(Long)object[8] )
				        			  vo1.setRank((Long)object[8]); 
				        	   }
				        	   else 
				        	   {
				        		   vo1= new PartyElectionTrendsReportHelperVO();
				        		   vo1.setVotesEarned(((Double)object[4]).longValue()) ;
						 	     	  vo1.setRank((Long)object[8]); 

				        	   }
	   
				        	   vo.setOthersVo(vo1);
				           }
			           }
			           
			           else {
			        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
			        	   if(vo1!=null){
			        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Double)object[4]).longValue()) ;
			        		  if( vo1.getRank()<(Long)object[8] )
			        			  vo1.setRank((Long)object[8]); 
			        	   }
			        	   else 
			        	   {
			        		   vo1= new PartyElectionTrendsReportHelperVO();
			        		   vo1.setVotesEarned(((Double)object[4]).longValue()) ;
					 	     	  vo1.setRank((Long)object[8]); 

			        	   }
   
			        	   vo.setOthersVo(vo1);
			           }
			           
		maps.put(Long.valueOf(object[0].toString()),vo );
		}
		}
		System.out.println(maps);
		List<PartyElectionTrendsReportVO> finalRes= new ArrayList<PartyElectionTrendsReportVO>();
		for(Long year:maps.keySet())
		{
			PartyElectionTrendsReportVO vo=	maps.get(year);
			vo.getOthersVo();
			//vo.getOthersVo().setVotesEarned(vo.getTotalVotesPolled()-( + vo.getIncVo()!=null ?vo.getIncVo().getVotesEarned().longValue():0L));
			vo.getOthersVo().setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((double)((double)vo.getOthersVo().getVotesEarned()/(double)vo.getTotalVotesPolled())*100)));
			finalRes.add(vo);
		}
		Collections.sort(finalRes);
		return finalRes;
	}
	public List<PartyElectionTrendsReportVO> getPreviousTrendsReportParliament(Long constId){
		Map<Long,Long> idMap = new HashMap<Long, Long>();
		List<Object[]> obj=	partyTrendsDAO.getTotalVotersForConst(constId);
		for (Object[] objects : obj) {
			idMap.put(Long.valueOf(objects[0].toString()), ((Double)objects[1]).longValue());
			
		}

		List<Object[]> ids = (List<Object[]>)partyTrendsDAO.getPreviousTrendsDataForParleament(null, constId);
		Map<Long,PartyElectionTrendsReportVO> maps = new HashMap<Long, PartyElectionTrendsReportVO>();

		System.out.println(ids.size());
		
		for (Object[] object : ids) {
			
		if(maps.containsKey(Long.valueOf(object[0].toString())))
		{
			PartyElectionTrendsReportVO vo =maps.get(Long.valueOf(object[0].toString()));
			  vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
			  if(idMap.containsKey(Long.valueOf(object[0].toString())))
			   vo.setTotalVoters(idMap.get(Long.valueOf(object[0].toString())));
	           vo.setTotalVotesPolled(Long.valueOf(object[1].toString()));
	           vo.setDistrictId(Long.valueOf(object[5].toString()));
	           PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
	           voh.setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((double)(((double)((Long)object[3]).longValue()/(double)Long.valueOf(object[1].toString()))*100))));
	           voh.setVotesEarned(((Long)object[3]).longValue());
	           if(((Long)object[2]).equals(872L))
	           {
	        	 /*  voh.setMarginVotes((Long)object[5]);
	        	   voh.setMarginVotesPercentage(((Double)object[6]));*/
	        	   vo.setTdpVo(voh);
	           }else if(((Long)object[2]).equals(362L))
	           {
	        	   vo.setIncVo(voh);

	           }else if(((Long)object[2]).equals(662L)||((Long)object[2]).equals(1117L)  )
	           {
	        	   vo.setPrpVo(voh);

	           }
	           else if(Long.valueOf(object[5].toString())<10){
	           		if(((Long)object[3]).equals(163L)){
	           			vo.setBjpVo(voh);
	           		}
	           		else if(((Long)object[3]).equals(886L)){
	           			vo.setTrsVo(voh);
	           		}
	           		else {
			        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
			        	   if(vo1!=null)
			        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Long)object[3]).longValue()) ;
			        	   else 
			        	   {
			        		   vo1= new PartyElectionTrendsReportHelperVO();
			        		   vo1.setVotesEarned(((Long)object[3]).longValue()) ;

			        	   }
			        		   
			        	   vo.setOthersVo(vo1);
			           }
	           }
	           else {
	        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
	        	   if(vo1!=null)
	        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Long)object[3]).longValue()) ;
	        	   else 
	        	   {
	        		   vo1= new PartyElectionTrendsReportHelperVO();
	        		   vo1.setVotesEarned(((Long)object[3]).longValue()) ;

	        	   }
	        		   
	        	   vo.setOthersVo(vo1);
	        }
			
		}
		else
		{
			PartyElectionTrendsReportVO vo =new PartyElectionTrendsReportVO();
			           vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
			     	  if(idMap.containsKey(Long.valueOf(object[0].toString())))
				       vo.setTotalVoters(idMap.get(Long.valueOf(object[0].toString())));
			           vo.setTotalVotesPolled(Long.valueOf(object[1].toString()));
			           vo.setDistrictId(Long.valueOf(object[5].toString()));
			           PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
			           voh.setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((double)(((double)(((Long)object[3]).longValue())/(double)(Long.valueOf(object[1].toString()).longValue()))*100))));
			           voh.setVotesEarned(((Long)object[3]).longValue());
			           if(((Long)object[2]).equals(872L))
			           {
			        	 /*  voh.setMarginVotes((Long)object[5]);
			        	   voh.setMarginVotesPercentage(((Double)object[6]));*/
			        	   vo.setTdpVo(voh);
			           }else if(((Long)object[2]).equals(362L))
			           {
			        	   vo.setIncVo(voh);

			           }else if(((Long)object[2]).equals(662L)||((Long)object[2]).equals(1117L)  )
			           {
			        	   vo.setPrpVo(voh);

			           } else if(Long.valueOf(object[5].toString())<10){
			           		if(((Long)object[3]).equals(163L)){
			           			vo.setBjpVo(voh);
			           		}
			           		else if(((Long)object[3]).equals(886L)){
			           			vo.setTrsVo(voh);
			           		}
			           		else {
					        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
					        	   if(vo1!=null)
					        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Long)object[3]).longValue()) ;
					        	   else 
					        	   {
					        		   vo1= new PartyElectionTrendsReportHelperVO();
					        		   vo1.setVotesEarned(((Long)object[3]).longValue()) ;

					        	   }
					        		   
					        	   vo.setOthersVo(vo1);
					           }
			           }
			           
			           else {
			        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
			        	   if(vo1!=null)
			        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Long)object[3]).longValue()) ;
			        	   else 
			        	   {
			        		   vo1= new PartyElectionTrendsReportHelperVO();
			        		   vo1.setVotesEarned(((Long)object[3]).longValue()) ;

			        	   }
			        		   
			        	   vo.setOthersVo(vo1);
			           }
			           
		maps.put(Long.valueOf(object[0].toString()),vo );
		}
		}
		System.out.println(maps);
		List<PartyElectionTrendsReportVO> finalRes= new ArrayList<PartyElectionTrendsReportVO>();

		for(Long year:maps.keySet())
		{PartyElectionTrendsReportVO vo=maps.get(year);
			Long tdp=vo.getTdpVo()!=null ?vo.getTdpVo().getVotesEarned():0L;
			
			Long inc =vo.getIncVo()!=null ? vo.getIncVo().getVotesEarned():0L;
			 
			Long prp11= vo.getPrpVo()!=null ? vo.getPrpVo().getVotesEarned() :0L;
			 
			Long others = vo.getOthersVo().getVotesEarned();
			
			Long max=inc;
			if(prp11!=null && prp11>inc )
				max=prp11;
			else if(others !=null && others>inc )
				max=others;
			
			if(vo.getTdpVo()==null){
				PartyElectionTrendsReportHelperVO tdpVo=new PartyElectionTrendsReportHelperVO();
				vo.setTdpVo(tdpVo);
			}
			vo.getTdpVo().setMarginVotes(tdp-max);
			System.out.println(vo.getTdpVo().getMarginVotes());
			System.out.println(vo.getTotalVotesPolled());
			System.out.println((double)(vo.getTdpVo().getMarginVotes()/vo.getTotalVotesPolled())*100); 
			
			vo.getTdpVo().setMarginVotesPercentage(Double.valueOf(roundTo2DigitsDoubleValue( (double)(((double)vo.getTdpVo().getMarginVotes()/(double)vo.getTotalVotesPolled())*100))));
			
			//PartyElectionTrendsReportVO vo=	maps.get(year);
			
			//vo.getOthersVo().setVotesEarned(vo.getTotalVotesPolled()-( + vo.getIncVo()!=null ?vo.getIncVo().getVotesEarned().longValue():0L));
			vo.getOthersVo().setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((double)(((double)vo.getOthersVo().getVotesEarned()/(double)vo.getTotalVotesPolled())*100))));
			finalRes.add(vo);
		}
		
	
		Collections.sort(finalRes);
	return finalRes	;
	}
	
	public PartyResultsVerVO getZptcMptcResultsOfConstituency(Long constiutencyId){
		PartyResultsVerVO pvMain=new PartyResultsVerVO();
		
		List<Long> electionTypeIds=new ArrayList<Long>();
		electionTypeIds.add(37l);
		electionTypeIds.add(39l);
		electionTypeIds.add(65l);
		electionTypeIds.add(67l);
		
		List<Object[]> li=nominationDAO.findAllZptcOrMptcResultsInaConstituency(constiutencyId,electionTypeIds,"2009");
		List<Object[]> li1=nominationDAO.findAllZptcOrMptcResultsInaConstituencyPartyWise(constiutencyId, electionTypeIds, "2009");
		
		
		List<PartyResultsVO> electionList=new ArrayList<PartyResultsVO>();
		for(Object[] ob:li){
			PartyResultsVO pvo=new PartyResultsVO();
			pvo.setElectionId(Long.valueOf(ob[5].toString()));
			pvo.setValidVotes(ob[4]!=null?((Double)ob[4]).longValue():0l);
			pvo.setVotesPolled(ob[3]!=null?((Double)ob[3]).longValue():0l);
			pvo.setYear(Long.valueOf(ob[0].toString()));
			pvo.setElectionName(ob[1].toString());
			
			electionList.add(pvo);
		}
		
		List<Long> partyIds=new ArrayList<Long>();
		partyIds.add(872l);
		partyIds.add(362l);
		partyIds.add(886l);
		
		
		for(Object[] ob:li1){
			PartyResultsVO pvo_temp=getMatchedVO(electionList,Long.valueOf(ob[5].toString()));
			List<PartyResultsVO> partyResults=pvo_temp.getPartyResultsVOList();
			if(partyResults==null){
				partyResults=new ArrayList<PartyResultsVO>();
			}
			PartyResultsVO pv=getMatchedPartyVO(partyResults,Long.valueOf(ob[0].toString()));
			if(pv==null){
				pv=new PartyResultsVO();
			}
			pv.setPartyId(Long.valueOf(ob[0].toString()));
			pv.setPartyName(ob[1].toString());
			pv.setVotesEarned(((Double)(ob[2])).longValue());
			pv.setDiffPercent(calcPercentage(pvo_temp.getValidVotes(), pv.getVotesEarned()));
			
			partyResults.add(pv);
			
			
			if(!partyIds.contains(pv.getPartyId())){
				Long existVotes=pvo_temp.getOtherVotes()!=null?pvo_temp.getOtherVotes():0l;
				pvo_temp.setOtherVotes(existVotes+pv.getVotesEarned());
				pvo_temp.setOtherVotesPercent(calcPercentage(pvo_temp.getValidVotes(), pvo_temp.getOtherVotes()));
			}
			pvo_temp.setPartyResultsVOList(partyResults);
			
		}
		
		for(PartyResultsVO pvo:electionList){
			List<PartyResultsVO> partyResults=pvo.getPartyResultsVOList();
			Collections.sort(partyResults);
			Long marginVotes=0l;
			for(int i=0;i<partyResults.size();i++){
				if(partyResults.get(i).getPartyId().longValue()==872l){
					if(i==0){
						if(!partyIds.contains(partyResults.get(1).getPartyId())){
							marginVotes=partyResults.get(0).getVotesEarned()-pvo.getOtherVotes();
							partyResults.get(0).setRank(1l);
							pvo.setMarginVotes(marginVotes);
							pvo.setMarginPercent(calcPercentage(pvo.getValidVotes(), marginVotes));
						}else{
							marginVotes=partyResults.get(0).getVotesEarned()-partyResults.get(1).getVotesEarned();
							partyResults.get(0).setRank(1l);
							pvo.setMarginVotes(marginVotes);
							pvo.setMarginPercent(calcPercentage(pvo.getValidVotes(), marginVotes));
						}
					}else{
						if(!partyIds.contains(partyResults.get(0).getPartyId())){
							marginVotes=partyResults.get(i).getPartyId().longValue()-pvo.getOtherVotes();
							partyResults.get(0).setRank(1l);
							pvo.setMarginVotes(marginVotes);
							pvo.setMarginPercent(calcPercentage(pvo.getValidVotes(), marginVotes));
						}else{
							marginVotes=partyResults.get(i).getVotesEarned()-partyResults.get(0).getVotesEarned();
							partyResults.get(0).setRank(1l);
							pvo.setMarginVotes(marginVotes);
							pvo.setMarginPercent(calcPercentage(pvo.getValidVotes(), marginVotes));
						}
					}
				}
			}
		}
		
		pvMain.setPartyResultsVOList(electionList);
		return pvMain;
		
	}
	
	public PartyResultsVerVO getMuncipalCorpPrevResults(Long constiutencyId){
		List<Long> electionIds=new ArrayList<Long>();
		electionIds.add(40l);
		electionIds.add(42l);
		electionIds.add(201l);
		
		//electionIds.add(63l);
		
		//List<Object[]> li=nominationDAO.findAllZptcOrMptcResultsInaConstituency(323l,electionTypeIds,"2009");
		//List<Object[]> li1=nominationDAO.findAllZptcOrMptcResultsInaConstituencyPartyWise(323l, electionTypeIds, "2009");
		
		List<Object[]> li=nominationDAO.findMuncipalOrCorpResultsInaConstituency(constiutencyId,electionIds);
		List<Object[]> li1=nominationDAO.findMuncipalOrCorpResultsInaConstituencyPartyWise(constiutencyId, electionIds);
		
		
		/*List<Object[]> li=nominationDAO.findMuncipalOrCorpResultsOfGMCInaConstituency(315l, electionIds);
		List<Object[]> li1=nominationDAO.findMuncipalOrCorpResultsOfGMCInaConstituencyPartyWise(315l, electionIds);*/
		
		
		List<PartyResultsVO> electionList=new ArrayList<PartyResultsVO>();
		Map<Long,List<PartyResultsVO>> eleMap=new HashMap<Long, List<PartyResultsVO>>();
		
		for(Object[] ob:li){
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[7].toString()));
			if(wardsList==null){
				wardsList=new ArrayList<PartyResultsVO>();
			}
			
			eleMap.put(Long.valueOf(ob[7].toString()), wardsList);
		}
		for(Object[] ob:li){
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[7].toString()));
			
			PartyResultsVO pvo=new PartyResultsVO();
			pvo.setElectionId(Long.valueOf(ob[7].toString()));
			pvo.setValidVotes(ob[6]!=null?((Double)ob[6]).longValue():0l);
			pvo.setVotesPolled(ob[5]!=null?((Double)ob[5]).longValue():0l);
			pvo.setYear(Long.valueOf(ob[0].toString()));
			pvo.setElectionName(ob[1].toString());
			pvo.setLocation(ob[3].toString());
			pvo.setLocationId(Long.valueOf(ob[2].toString()));
			wardsList.add(pvo);
		}
		
		List<Long> partyIds=new ArrayList<Long>();
		partyIds.add(872l);
		partyIds.add(362l);
		partyIds.add(886l);
		partyIds.add(163l);
		
		
		for(Object[] ob:li1){
			
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[5].toString()));
			
			PartyResultsVO pvo_temp=getMatchedWardVO(wardsList,Long.valueOf(ob[5].toString()),Long.valueOf(ob[6].toString()));
			
			List<PartyResultsVO> partyResults=pvo_temp.getPartyResultsVOList();
			if(partyResults==null){
				partyResults=new ArrayList<PartyResultsVO>();
			}
			PartyResultsVO pv=getMatchedPartyVO1(partyResults,Long.valueOf(ob[0].toString()),Long.valueOf(ob[9].toString()));
			if(pv==null){
				pv=new PartyResultsVO();
			}
			pv.setPartyId(Long.valueOf(ob[0].toString()));
			pv.setPartyName(ob[1].toString());
			pv.setVotesEarned(((Double)(ob[2])).longValue());
			pv.setDiffPercent(calcPercentage(pvo_temp.getVotesPolled(), pv.getVotesEarned()));
			pv.setRank(Long.valueOf(ob[8].toString()));
			pv.setNominationId(Long.valueOf(ob[9].toString()));
			partyResults.add(pv);
			
			
			if(!partyIds.contains(pv.getPartyId())){
				Long existVotes=pvo_temp.getOtherVotes()!=null?pvo_temp.getOtherVotes():0l;
				pvo_temp.setOtherVotes(existVotes+pv.getVotesEarned());
				pvo_temp.setOtherVotesPercent(calcPercentage(pvo_temp.getVotesPolled(), pvo_temp.getOtherVotes()));
			}
			pvo_temp.setPartyResultsVOList(partyResults);
			
		}
		

		PartyResultsVerVO prvo=new PartyResultsVerVO();
		Map<Long,PartyResultsVerVO> partyRanksMap=new HashMap<Long, PartyResultsVerVO>();
		
		Map<Long,PartyResultsVO> party_res=new HashMap<Long, PartyResultsVO>();
		for (Entry<Long, List<PartyResultsVO>> entry : eleMap.entrySet())
		{
		List<PartyResultsVO> electionList1=entry.getValue();
		Long totalValidVotes=0l;
		for(PartyResultsVO pvo:electionList1){
			totalValidVotes+=pvo.getValidVotes();
		}
		for(PartyResultsVO pvo:electionList1){
			List<PartyResultsVO> partyResults=pvo.getPartyResultsVOList();
			Collections.sort(partyResults);
			Long marginVotes=0l;
			for(int i=0;i<partyResults.size();i++){
				//PARTICIPATED AND RANK COUNT START
				PartyResultsVO parr=party_res.get(partyResults.get(i).getPartyId().longValue());
				if(parr==null){
					parr=new PartyResultsVO();
					parr.setWon(0l);
					parr.setParticipated(0l);
					parr.setVotesEarned(0l);
					parr.setPercentage(calcPercentage(totalValidVotes, parr.getVotesEarned()));
					//party_res.put(partyResults.get(i).getPartyId().longValue(), parr);
				}
				
				if(partyResults.get(i).getRank()!=null){
					if(partyResults.get(i).getRank()==1){
						Long exist=parr.getWon();
						parr.setWon(exist+1l);
					}
				}
					Long existPrtcptd=parr.getParticipated();
					parr.setParticipated(existPrtcptd+1l);
					parr.setPartyId(partyResults.get(i).getPartyId().longValue());
					
					
					Long votesErnd_exist=parr.getVotesEarned();
					parr.setVotesEarned(partyResults.get(i).getVotesEarned()+votesErnd_exist);
					parr.setPercentage(calcPercentage(totalValidVotes,parr.getVotesEarned()));
					
					party_res.put(partyResults.get(i).getPartyId().longValue(), parr);
					
					if(!partyIds.contains(partyResults.get(i).getPartyId().longValue())){
						if(prvo.getParticipated()==null){
							prvo.setParticipated(0l);
							prvo.setWon(0l);
							prvo.setOtherVotes(0l);
							prvo.setOtherVotesPercent(calcPercentage(totalValidVotes,prvo.getOtherVotes()));
						}
						Long exPr=prvo.getParticipated();
						prvo.setParticipated(exPr+1l);
						
						if(partyResults.get(i).getRank()==1){
							Long exWn=prvo.getWon();
							prvo.setWon(exWn+1l);
						}
						
						Long votesErnd=prvo.getOtherVotes();
						prvo.setOtherVotes(partyResults.get(i).getVotesEarned()+votesErnd);
						prvo.setOtherVotesPercent(calcPercentage(totalValidVotes,prvo.getOtherVotes()));
					
					}
				//END	
				
					
				
				if(partyResults.get(i).getPartyId().longValue()==872l){
					if(i==0){
						if(!partyIds.contains(partyResults.get(1).getPartyId())){
							marginVotes=partyResults.get(0).getVotesEarned()-pvo.getOtherVotes();
						}else{
							marginVotes=partyResults.get(0).getVotesEarned()-partyResults.get(1).getVotesEarned();
							partyResults.get(0).setRank(1l);
							pvo.setMarginVotes(marginVotes);
						}
					}else{
						if(!partyIds.contains(partyResults.get(0).getPartyId())){
							marginVotes=partyResults.get(i).getPartyId().longValue()-pvo.getOtherVotes();
						}else{
							marginVotes=partyResults.get(i).getVotesEarned()-partyResults.get(0).getVotesEarned();
							partyResults.get(0).setRank(1l);
							pvo.setMarginVotes(marginVotes);
						}
					}
				}
			}
		}
		prvo.setElectionId(entry.getKey());
		
		List<PartyResultsVO> partyStrengths=new ArrayList<PartyResultsVO>(party_res.values());
			if(prvo.getPartyResultsVOList()==null){
				prvo.setPartyResultsVOList(electionList1);
				prvo.setPartyStrengths(partyStrengths);
			}else{
				List<PartyResultsVO> eleList=prvo.getPartyResultsVOList();
				eleList.addAll(electionList1);
				prvo.setPartyResultsVOList(eleList);
			}
		}
		
		return prvo;
	}
	
	public PartyResultsVerVO getMuncipalCorpPrevResultsInGHMC(Long constiutencyId){
		List<Long> electionIds=new ArrayList<Long>();
		
		electionIds.add(63l);
		
		List<Object[]> li=nominationDAO.findMuncipalOrCorpResultsOfGMCInaConstituency(constiutencyId, electionIds);
		List<Object[]> li1=nominationDAO.findMuncipalOrCorpResultsOfGMCInaConstituencyPartyWise(constiutencyId, electionIds);
		
		
		List<PartyResultsVO> electionList=new ArrayList<PartyResultsVO>();
		Map<Long,List<PartyResultsVO>> eleMap=new HashMap<Long, List<PartyResultsVO>>();
		
		for(Object[] ob:li){
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[7].toString()));
			if(wardsList==null){
				wardsList=new ArrayList<PartyResultsVO>();
			}
			
			eleMap.put(Long.valueOf(ob[7].toString()), wardsList);
		}
		for(Object[] ob:li){
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[7].toString()));
			
			PartyResultsVO pvo=new PartyResultsVO();
			pvo.setElectionId(Long.valueOf(ob[7].toString()));
			pvo.setValidVotes(ob[6]!=null?((Double)ob[6]).longValue():0l);
			pvo.setVotesPolled(ob[5]!=null?((Double)ob[5]).longValue():0l);
			pvo.setYear(Long.valueOf(ob[0].toString()));
			pvo.setElectionName(ob[1].toString());
			pvo.setLocation(ob[3].toString());
			pvo.setLocationId(Long.valueOf(ob[2].toString()));
			wardsList.add(pvo);
		}
		
		List<Long> partyIds=new ArrayList<Long>();
		partyIds.add(872l);
		partyIds.add(362l);
		partyIds.add(886l);
		partyIds.add(163l);
		
		for(Object[] ob:li1){
			
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[5].toString()));
			
			PartyResultsVO pvo_temp=getMatchedWardVO(wardsList,Long.valueOf(ob[5].toString()),Long.valueOf(ob[6].toString()));
			
			List<PartyResultsVO> partyResults=pvo_temp.getPartyResultsVOList();
			if(partyResults==null){
				partyResults=new ArrayList<PartyResultsVO>();
			}
			PartyResultsVO pv=getMatchedPartyVO1(partyResults,Long.valueOf(ob[0].toString()),Long.valueOf(ob[9].toString()));
			if(pv==null){
				pv=new PartyResultsVO();
				pv.setRank(0l);
			}
			pv.setPartyId(Long.valueOf(ob[0].toString()));
			pv.setPartyName(ob[1].toString());
			pv.setVotesEarned(((Double)(ob[2])).longValue());
			pv.setDiffPercent(calcPercentage(pvo_temp.getVotesPolled(), pv.getVotesEarned()));
			pv.setRank(Long.valueOf(ob[8].toString()));
			pv.setNominationId(Long.valueOf(ob[9].toString()));
			partyResults.add(pv);
			
			
			if(!partyIds.contains(pv.getPartyId())){
				Long existVotes=pvo_temp.getOtherVotes()!=null?pvo_temp.getOtherVotes():0l;
				pvo_temp.setOtherVotes(existVotes+pv.getVotesEarned());
				pvo_temp.setOtherVotesPercent(calcPercentage(pvo_temp.getVotesPolled(), pvo_temp.getOtherVotes()));
			}
			pvo_temp.setPartyResultsVOList(partyResults);
			
		}
		

		PartyResultsVerVO prvo=new PartyResultsVerVO();
		Map<Long,PartyResultsVO> party_res=new HashMap<Long, PartyResultsVO>();
		
		for (Entry<Long, List<PartyResultsVO>> entry : eleMap.entrySet())
		{
		List<PartyResultsVO> electionList1=entry.getValue();
		Long totalValidVotes=0l;
		for(PartyResultsVO pvo:electionList1){
			totalValidVotes+=pvo.getValidVotes();
		}
		for(PartyResultsVO pvo:electionList1){
			List<PartyResultsVO> partyResults=pvo.getPartyResultsVOList();
			Collections.sort(partyResults);
			Long marginVotes=0l;
			
			for(int i=0;i<partyResults.size();i++){
				
				//PARTICIPATED AND RANK COUNT START
				PartyResultsVO parr=party_res.get(partyResults.get(i).getPartyId().longValue());
				if(parr==null){
					parr=new PartyResultsVO();
					parr.setWon(0l);
					parr.setParticipated(0l);
					parr.setVotesEarned(0l);
					parr.setPercentage(calcPercentage(totalValidVotes, parr.getVotesEarned()));
				}
				
				if(partyResults.get(i).getRank()!=null){
					if(partyResults.get(i).getRank()==1){
						Long exist=parr.getWon();
						parr.setWon(exist+1l);
					}
				}
					Long existPrtcptd=parr.getParticipated();
					parr.setParticipated(existPrtcptd+1l);
					parr.setPartyId(partyResults.get(i).getPartyId().longValue());
					
					Long votesErnd_exist=parr.getVotesEarned();
					parr.setVotesEarned(partyResults.get(i).getVotesEarned()+votesErnd_exist);
					parr.setPercentage(calcPercentage(totalValidVotes,parr.getVotesEarned()));
					
					party_res.put(partyResults.get(i).getPartyId().longValue(), parr);
					
					if(!partyIds.contains(partyResults.get(i).getPartyId().longValue())){
						if(prvo.getParticipated()==null){
							prvo.setParticipated(0l);
							prvo.setWon(0l);
							prvo.setOtherVotes(0l);
							prvo.setOtherVotesPercent(calcPercentage(totalValidVotes,prvo.getOtherVotes()));
						}
						Long exPr=parr.getParticipated();
						prvo.setParticipated(exPr+1l);
						
						if(partyResults.get(i).getRank()==1){
							Long exWn=prvo.getWon();
							prvo.setWon(exWn+1l);
						}
						
						Long votesErnd=prvo.getOtherVotes();
						prvo.setOtherVotes(partyResults.get(i).getVotesEarned()+votesErnd);
						prvo.setOtherVotesPercent(calcPercentage(totalValidVotes,prvo.getOtherVotes()));
					
					}
				//END
				
				if(partyResults.get(i).getPartyId().longValue()==872l){
					if(i==0){
						if(!partyIds.contains(partyResults.get(1).getPartyId())){
							marginVotes=partyResults.get(0).getVotesEarned()-pvo.getOtherVotes();
						}else{
							marginVotes=partyResults.get(0).getVotesEarned()-partyResults.get(1).getVotesEarned();
							partyResults.get(0).setRank(1l);
							pvo.setMarginVotes(marginVotes);
						}
					}else{
						if(!partyIds.contains(partyResults.get(0).getPartyId())){
							marginVotes=partyResults.get(i).getPartyId().longValue()-pvo.getOtherVotes();
						}else{
							marginVotes=partyResults.get(i).getVotesEarned()-partyResults.get(0).getVotesEarned();
							partyResults.get(0).setRank(1l);
							pvo.setMarginVotes(marginVotes);
						}
					}
				}
			}
		}
		prvo.setElectionId(entry.getKey());
		
		List<PartyResultsVO> partyStrengths=new ArrayList<PartyResultsVO>(party_res.values());
		
			if(prvo.getPartyResultsVOList()==null){
				prvo.setPartyResultsVOList(electionList1);
				prvo.setPartyStrengths(partyStrengths);
			}else{
				List<PartyResultsVO> eleList=prvo.getPartyResultsVOList();
				eleList.addAll(electionList1);
				prvo.setPartyResultsVOList(eleList);
			}
		}
		
		return prvo;
	} 
	
	public PartyResultsVO getMatchedVO(List<PartyResultsVO> pvoList,Long electionId){
		for(PartyResultsVO pv:pvoList){
			if(pv.getElectionId().longValue()==electionId.longValue()){
				return pv;
			}
		}
		return null;
	}
	public PartyResultsVO getMatchedPartyVO(List<PartyResultsVO> pvoList,Long partyId){
		for(PartyResultsVO pv:pvoList){
			if(pv.getPartyId().longValue()==partyId.longValue()){
				return pv;
			}
		}
		return null;
	}
	
	public PartyResultsVO getMatchedPartyVO1(List<PartyResultsVO> pvoList,Long partyId,Long nominationId){
		for(PartyResultsVO pv:pvoList){
			if(pv.getPartyId().longValue()==partyId.longValue() && pv.getNominationId().longValue() == nominationId.longValue()){
				return pv;
			}
		}
		return null;
	}
	
	public PartyResultsVO getMatchedWardVO(List<PartyResultsVO> pvoList,Long electionId,Long wardId){
		for(PartyResultsVO pv:pvoList){
			if(pv.getElectionId().longValue()==electionId.longValue() && pv.getLocationId().longValue()==wardId.longValue()){
				return pv;
			}
		}
		return null;
	}
}
