package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHouseHoldVoterDAO;
import com.itgrids.partyanalyst.dto.HHSurveyVO;
import com.itgrids.partyanalyst.dto.HouseHoldVotersVO;
import com.itgrids.partyanalyst.model.HHSurveyAnswers;
import com.itgrids.partyanalyst.model.HouseHoldVoter;

public class HouseHoldVoterDAOHibernateTest extends BaseDaoTestCase{
	
	private IHouseHoldVoterDAO houseHoldVoterDAO;

	public void setHouseHoldVoterDAO(IHouseHoldVoterDAO houseHoldVoterDAO) {
		this.houseHoldVoterDAO = houseHoldVoterDAO;
	}



	/*public void test(){
		//System.out.println("hhOptionsDAO");
		
		Long houseHoldId= houseHoldVoterDAO.getHouseHoldIdForVoter(6979790l);
		System.out.println(houseHoldId);
		
		int count = houseHoldVoterDAO.childMembersDelete(10l);
		System.out.println(count);
		
		List<HouseHoldVoter> list=houseHoldVoterDAO.getHouseHoldsVoterdDetailsByHouseHoldId1(51l);
		
		System.out.println(list.size());
		List<Long> vtrIds=new ArrayList<Long>();
		vtrIds.add(6979790l);
		List<Long> list = houseHoldVoterDAO.getVoterIdsExistByVoterIds(vtrIds);
		for(Long ni:list){
			System.out.println(ni);
		}
	}*/
	
	public void testForGettingFamilyDetails(){
		List<HouseHoldVoter> list=houseHoldVoterDAO.getTotalVoters();
		
		List<HouseHoldVotersVO> hvoList=new ArrayList<HouseHoldVotersVO>();
		
		
		for(HouseHoldVoter hh:list){
			HouseHoldVotersVO hvo=new HouseHoldVotersVO();
			if(hh.getVoter()!=null){
				hvo.setName(hh.getVoter().getName());
			}else{
				hvo.setName(hh.getHouseHoldsFamilyDetails().getName());
			}
			hvo.setMobileNo(hh.getOwnerMobileNo());
			hvo.setHouseHoldsId(hh.getHouseHolds().getHouseHoldId());
			hvo.setHouseNo(hh.getHouseHolds()!=null?hh.getHouseHolds().getHouseNo():"");
			hvo.setRelation(hh.getVoterFamilyRelation()!=null?hh.getVoterFamilyRelation().getRelation():"");
			hvo.setEducation(hh.getEducation()!=null?hh.getEducation().getCategoryValue():"");
			hvo.setOccupation(hh.getOccupation()!=null?hh.getOccupation().getCategoryValue():"");
			hvo.setSocialPosition(hh.getSocialCategory()!=null?hh.getSocialCategory().getCategoryValue():"");
			if(hh.getHouseHoldsFamilyDetails()!=null){
				//hvo.setChildrenName(hh.getHouseHoldsFamilyDetails().getName());
				hvo.setAge(hh.getHouseHoldsFamilyDetails().getAge());
			}
			hvoList.add(hvo);
		}
		List<Long> houseHoldIds=new ArrayList<Long>();
		for(HouseHoldVotersVO hvo:hvoList){
			houseHoldIds.add(hvo.getHouseHoldsId());
		//	System.out.println(hvo.getHouseHoldsId()+"\t"+"#"+hvo.getHouseNo()+"\t"+hvo.getName()+"\t"+hvo.getRelation()+"\t"+hvo.getEducation()+"\t"+hvo.getOccupation()+"\t"+hvo.getSocialPosition()+"\t"+hvo.getAge()+"\t"+hvo.getMobileNo());
		}
		
		Set<Long> houseHold_set = new HashSet<Long>(houseHoldIds);
		List<Object[]> questList= houseHoldVoterDAO.getAllQuestions();
		
		
		Map<Long,List<HHSurveyVO>> hqMap=new HashMap<Long, List<HHSurveyVO>>();
		for(Long lng:houseHoldIds){
			//HHSurveyVO hsv=new HHSurveyVO();
			List<HHSurveyVO> questVOList=new ArrayList<HHSurveyVO>();
			if(questList.size()>0){
				for(Object[] quest:questList){
					HHSurveyVO hvo=new HHSurveyVO();
					hvo.setQuestionId(Long.valueOf(quest[0].toString()));
					hvo.setQuestion(quest[1].toString());
					questVOList.add(hvo);
				}
			}
			/*hsv.setHouseHoldId(lng);
			hsv.setQuestionsList(questVOList);*/
			
		hqMap.put(lng, questVOList);
		}
		
		//houseHoldIds = new ArrayList(houseHold_set);
		
		List<HHSurveyAnswers> answers_list=houseHoldVoterDAO.getAllSurveyAnswers(houseHoldIds);
		Map<Long,HHSurveyVO> houseHoldQuestMap=new HashMap<Long, HHSurveyVO>();
		for(HHSurveyAnswers hhvo:answers_list){
			HHSurveyVO hsrvy=houseHoldQuestMap.get(hhvo.getHouseHoldId());
			if(hsrvy==null){
				hsrvy=new HHSurveyVO();
				hsrvy.setQuestionsList(hqMap.get(hhvo.getHouseHoldId()));
				houseHoldQuestMap.put(hhvo.getHouseHoldId(), hsrvy);
			}
			HHSurveyVO hhQuestVO=getMatchedHouseHoldsVoter(hhvo.getHhSurveyQuestionId(), hsrvy.getQuestionsList());
			if(hhQuestVO!=null){
				if(hhvo.getHhOptionsId()!=null){
					if(hhQuestVO.getOption()!=null && hhQuestVO.getOption().length()>0){
						String option=hhQuestVO.getOption()+","+hhvo.getHhOptions().getOptionsId().toString();
						hhQuestVO.setOption(option);
					}else{
						String option=hhvo.getHhOptions().getOptionsId().toString();
						hhQuestVO.setOption(option);
					}
				}else{
					hhQuestVO.setOption(hhvo.getRemarks());
				}
			}
		}
		
		
		for (Entry<Long, HHSurveyVO> entry : houseHoldQuestMap.entrySet())
		{
			List<HHSurveyVO> enrList=entry.getValue().getQuestionsList();
			System.out.println("\t");
			for(HHSurveyVO en:enrList){
				Long quesId=en.getQuestionId();
				System.out.print(quesId+"\t");
			}
			System.out.println();
		}

		for (Entry<Long, HHSurveyVO> entry : houseHoldQuestMap.entrySet())
		{
			System.out.println();
			System.out.print(entry.getKey()+"\t");
			List<HHSurveyVO> enrList=entry.getValue().getQuestionsList();
			
			
			
			for(HHSurveyVO en:enrList){
				Long quesId=en.getQuestionId();
				Long optionId=null;
				String option="";
				if(en.getOptionId()!=null){
					optionId=en.getOptionId();
				}
				if(en.getOption()!=null && en.getOption()!=""){
					option=en.getOption();
				}
				if(optionId!=null){
					System.out.print(optionId+"\t");
				}else{
					System.out.print(option+"\t");
				}
			}
			
		}


	}
	
	
	public HHSurveyVO getMatchedHouseHoldsVoter(Long questId,List<HHSurveyVO> questionsList)
	{
		
		for(HHSurveyVO hhSurveyVO:questionsList)
			if(hhSurveyVO.getQuestionId().longValue() == questId.longValue())
				return hhSurveyVO;
		return null;
		
		
	}

}
