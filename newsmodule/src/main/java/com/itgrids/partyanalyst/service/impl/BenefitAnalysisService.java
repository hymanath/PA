package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyCategoryDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyFileDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.IGroupDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.BenfitVO;
import com.itgrids.partyanalyst.dto.NewsActivityVO;
import com.itgrids.partyanalyst.service.IBenefitAnalysisService;

public class BenefitAnalysisService implements IBenefitAnalysisService {

	private static final Logger LOG = Logger.getLogger(BenefitAnalysisService.class);
	private ICandidatePartyCategoryDAO candidatePartyCategoryDAO;
	private IPartyDAO partyDAO;
	private IGallaryDAO gallaryDAO;
	private ICandidatePartyFileDAO candidatePartyFileDAO;
	private ICandidateDAO candidateDAO;
	private IGroupDAO groupDAO;
	
	public ICandidatePartyCategoryDAO getCandidatePartyCategoryDAO() {
		return candidatePartyCategoryDAO;
	}

	public void setCandidatePartyCategoryDAO(
			ICandidatePartyCategoryDAO candidatePartyCategoryDAO) {
		this.candidatePartyCategoryDAO = candidatePartyCategoryDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public IGallaryDAO getGallaryDAO() {
		return gallaryDAO;
	}

	public void setGallaryDAO(IGallaryDAO gallaryDAO) {
		this.gallaryDAO = gallaryDAO;
	}

	public ICandidatePartyFileDAO getCandidatePartyFileDAO() {
		return candidatePartyFileDAO;
	}

	public void setCandidatePartyFileDAO(
			ICandidatePartyFileDAO candidatePartyFileDAO) {
		this.candidatePartyFileDAO = candidatePartyFileDAO;
	}

	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

	public IGroupDAO getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(IGroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

	public List<BenfitVO> getCategoryWiseBenifit(Date fromDate,Date toDate,Long stateId,List<Long> partyIds){
		
		List<BenfitVO> resultsList = new ArrayList<BenfitVO>();
		try{
			if(partyIds.contains(872l)){
				partyIds.remove(872l);
				partyIds.add(0,872l);
			}
			List<Object[]> allCategoryWiseBenfitsList = new ArrayList<Object[]>();
			Map<Long,String> partyNames = new HashMap<Long,String>();
			Map<Long,String> categoryNames = new HashMap<Long,String>();
			
			//Map<categoryId,Map<partyId,Map<benfitId,count>>>
			 Map<Long,Map<Long,Map<Long,Long>>> categoryMap = new HashMap<Long,Map<Long,Map<Long,Long>>>();
			
			for(Long partyId:partyIds){
				 //0 count,1 benifitId,2 categoryId,3 partyId
				List<Object[]> categoryWiseBenfitsList =  candidatePartyCategoryDAO.getCategoryWiseBenifit(fromDate, toDate, stateId, partyId);
				allCategoryWiseBenfitsList.addAll(categoryWiseBenfitsList);
			}
			populateDataToMap(allCategoryWiseBenfitsList,categoryMap);
			if(categoryMap.size() >0){
				populatePartyNames(partyNames,partyIds);
				populateCategoryNames(categoryNames,new ArrayList<Long>(categoryMap.keySet()));	
				populateDataToBenfitVO(resultsList,categoryMap,partyNames,categoryNames,partyIds);
				Collections.sort(resultsList,benefitSort);
			}
		}catch(Exception e){
			LOG.error("Exception rised in getCategoryWiseBenifit ",e);
		}
		return resultsList;
	}
	
	public void populateDataToMap(List<Object[]> allCategoryWiseBenfitsList, Map<Long,Map<Long,Map<Long,Long>>> categoryMap){
		
		Map<Long,Map<Long,Long>> partyMap = null;
		Map<Long,Long> benfitMap = null;
				
        for(Object[] result:allCategoryWiseBenfitsList){
			
			partyMap = categoryMap.get((Long)result[2]);
			if(partyMap == null){
				partyMap = new HashMap<Long,Map<Long,Long>>();
				categoryMap.put((Long)result[2], partyMap);
			}
			
			benfitMap = partyMap.get((Long)result[3]);
			if(benfitMap == null){
				benfitMap = new HashMap<Long,Long>();
				partyMap.put((Long)result[3], benfitMap);
			}
			
			benfitMap.put((Long)result[1],(Long)result[0]);
		}
	}
	
	public void populateDataToBenfitVO(List<BenfitVO> resultsList,Map<Long,Map<Long,Map<Long,Long>>> categoryMap,Map<Long,String> partyNames,Map<Long,String> categoryNames,List<Long> partyIds){
		
		BenfitVO categoryVO = null;
		BenfitVO partyVO = null;
		
		List<BenfitVO> parties = null;
		
		Map<Long,Map<Long,Long>> partyMap = null;
		Map<Long,Long> benfitMap = null;
		
		for(Long categoryId:categoryMap.keySet()){
			categoryVO = new BenfitVO();
			categoryVO.setId(categoryId);
			categoryVO.setName(categoryNames.get(categoryId));
			categoryVO.setCount(0l);
			partyMap = categoryMap.get(categoryId);
			
			parties = new ArrayList<BenfitVO>();
			for(Long partyId:partyIds){
				
				benfitMap = partyMap.get(partyId);
				
				partyVO = new BenfitVO();
				partyVO.setCount(0l);
				partyVO.setNegCount(0l);
				partyVO.setName(partyNames.get(partyId));
				partyVO.setId(partyId);
				
				if(benfitMap != null){
					if(benfitMap.get(1l) != null){
						partyVO.setCount(benfitMap.get(1l));
						categoryVO.setCount(categoryVO.getCount()+benfitMap.get(1l));
					}
					if(benfitMap.get(2l) != null){
						partyVO.setNegCount(benfitMap.get(2l));
						categoryVO.setCount(categoryVO.getCount()+benfitMap.get(2l));
					}
					
				}
				parties.add(partyVO);
			}
			categoryVO.setBenfitVOList(parties);
			resultsList.add(categoryVO);
		}
	}
	
	public void populatePartyNames(Map<Long,String> partyNames,List<Long> partyIds){
		
		List<Object[]> partyDetails = partyDAO.getPartyDetails(partyIds);
		
		for(Object[] party:partyDetails){
			partyNames.put((Long)party[0], party[1].toString());
		}
		
	}
	
    public void populateCategoryNames(Map<Long,String> categoryNames,List<Long> categoryIds){
    	
    	List<Object[]> categoryDetails = gallaryDAO.getGalleriesForIds(categoryIds);
		
		for(Object[] category:categoryDetails){
			categoryNames.put((Long)category[0], category[1].toString());
		}
	}
    
    public List<NewsActivityVO> getCategoryBenifitWiseNews(Date fromDate,Date toDate,Long partyId,Long categoryId,Long benfitId,Long stateId){
    	
    	 List<NewsActivityVO> results = new ArrayList<NewsActivityVO>();
    	 NewsActivityVO vo = null;
    	 // 0 title,1 title font,2 desc,3 desc font,4 fileId
    	 List<Object[]>  newsList = candidatePartyCategoryDAO.getCategoryBenifitWiseNews(fromDate,toDate,partyId,categoryId,benfitId,stateId);
    	 Long count = candidatePartyCategoryDAO.getCategoryBenifitWiseNewsCount(fromDate, toDate, partyId, categoryId, benfitId, stateId);
    	 
    	 for(Object[] news:newsList){
    		 vo = new NewsActivityVO();
    		 vo.setTitle(StringEscapeUtils.unescapeJava(news[0].toString()));
    		 if(news[1] != null)
    		   vo.setTitleFont("1");
    		 vo.setDescription(StringEscapeUtils.unescapeJava(news[2].toString()));
    		 if(news[3] != null)
    		   vo.setFont("1");
    		 vo.setId((Long)news[4]);
    		 results.add(vo);
    	 }
    	 if(results.size() > 0){
    		 results.get(0).setCount(count);
    	 }
    	 return results;
    }
    
    public Comparator<BenfitVO> benefitSort = new Comparator<BenfitVO>()
		{
					  
		   public int compare(BenfitVO vo1, BenfitVO vo2)
			{
		       return (vo2.getCount().intValue()) - (vo1.getCount().intValue());
			}
		};
	
     public List<BenfitVO> getCandidateGroupWiseBenifit(Long groupId,Date fromDate,Date toDate,Long stateId,Long partyId){
    	 Map<Long,BenfitVO> resultMap = new HashMap<Long,BenfitVO>();
    	 try{
    		   // 0 count,1 candidateId, 2 benfitId
    		   List<Object[]> candidateGroupWiseBenfitsList =  candidatePartyFileDAO.getCandidateGroupWiseBenifit(fromDate, toDate, stateId, groupId,partyId);
    		   
    		   populateResultToMap(candidateGroupWiseBenfitsList,resultMap);
    		   
    		   populateCandidateNames(resultMap,getCandidateNames(resultMap.keySet()));
    	 }catch(Exception e){
 			LOG.error("Exception rised in getCandidateGroupWiseBenifit ",e);
 		}
    	 
    	 return new ArrayList<BenfitVO>(resultMap.values());
     }
     
     public void populateResultToMap(List<Object[]> candidateGroupWiseBenfitsList,Map<Long,BenfitVO> resultMap){
    	 
    	 for(Object[] candidate:candidateGroupWiseBenfitsList){
    		 if(candidate[2] != null && ((Long)candidate[2]).longValue() != 3l){
	    		 BenfitVO vo = resultMap.get((Long)candidate[1]);
	    		 if(vo == null){
	    			 vo = new BenfitVO();
	    			 vo.setId((Long)candidate[1]);
	    			 vo.setCount(0l);
	 				 vo.setNegCount(0l);
	 				resultMap.put((Long)candidate[1],vo);
	    		 }
	    		 if(((Long)candidate[2]).longValue() == 1l){
	    			 vo.setCount((Long)candidate[0]);
	    		 }else if(((Long)candidate[2]).longValue() == 2l){
	    			 vo.setNegCount((Long)candidate[0]);
	    		 }
    	    }
    	 }
     }
     
     public void populateCandidateNames(Map<Long,BenfitVO> resultMap,Map<Long,String> candidateNames){
    	 
    	 for(Long key:resultMap.keySet()){  		 
    		 resultMap.get(key).setName(candidateNames.get(key));
    	 }
     }
     
     public Map<Long,String> getCandidateNames(Set<Long> candidateIds){
    	 
    	 Map<Long,String> candidateNames = new HashMap<Long,String>();
    	 
    	 if(candidateIds.size() > 0){
    		 //0 id,1 name
    		 List<Object[]> candidateNamesList = candidateDAO.getCandidateNames(candidateIds);
    		 for(Object[] candidate:candidateNamesList){
    			 candidateNames.put((Long)candidate[0], candidate[1].toString());
        	 }
    	 }
    	 
    	 return candidateNames;
     }
     
     public List<NewsActivityVO> getCandidateGroupWiseBenifitNews(Date fromDate,Date toDate,Long candidateId,Long benfitId,int startIndex,int maxIndex){
     	
    	 List<NewsActivityVO> results = new ArrayList<NewsActivityVO>();
    	 NewsActivityVO vo = null;
    	 // 0 title,1 title font,2 desc,3 desc font, 4 count
    	 List<Object[]>  newsList = candidatePartyFileDAO.getCandidateGroupBenifitWiseNews(fromDate, toDate, candidateId, benfitId,startIndex,maxIndex);
    	 Long count  = candidatePartyFileDAO.getCandidateGroupBenifitWiseNewsCount(fromDate, toDate, candidateId, benfitId);
    	 for(Object[] news:newsList){
    		 vo = new NewsActivityVO();
    		 vo.setTitle(StringEscapeUtils.unescapeJava(news[0].toString()));
    		 if(news[1] != null)
    		   vo.setTitleFont("1");
    		 vo.setDescription(StringEscapeUtils.unescapeJava(news[2].toString()));
    		 if(news[3] != null)
    		   vo.setFont("1");
    		 vo.setId((Long)news[4]);
    		 results.add(vo);
    	 }
    	 if(results.size() > 0){
    		 results.get(0).setCount(count);
    	 }
    	 return results;
    }
     
     public List<BenfitVO> getCandidateGroups(){
    	 List<BenfitVO> groups = new ArrayList<BenfitVO>();
    	 try{
    		 BenfitVO vo = null;
    		 List<Object[]> groupsList = groupDAO.getAllGroups();
    		 for(Object[] group:groupsList){
    			 vo = new BenfitVO();
    			 vo.setId((Long)group[0]);
    			 vo.setName(group[1].toString());
    			 groups.add(vo);
    		 }
    		 
    	 }catch(Exception e){
    		 LOG.error("Exception rised in getCandidateGroups",e);
    	 }
    	 
    	 return groups;
     }
     
     public void getLocationWiseBenifit(Long stateId,Date fromDate,Date toDate,Long locationLevel,Long levelId,Long levelValue){
    	 
    	 
     }
}
