package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IHHOptionTypeDAO;
import com.itgrids.partyanalyst.dao.IHHOptionsDAO;
import com.itgrids.partyanalyst.dao.IHHQuestionOptionsDAO;
import com.itgrids.partyanalyst.dao.IHHSurveyAnswersDAO;
import com.itgrids.partyanalyst.dao.IHHSurveyQuestionDAO;
import com.itgrids.partyanalyst.dao.IHHSurveySubTypeDAO;
import com.itgrids.partyanalyst.dao.IHouseHoldsDAO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.HHQuestionDetailsVO;
import com.itgrids.partyanalyst.dto.HHSurveyVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.HHOptionType;
import com.itgrids.partyanalyst.model.HHOptions;
import com.itgrids.partyanalyst.model.HHQuestionOptions;
import com.itgrids.partyanalyst.model.HHSurveyAnswers;
import com.itgrids.partyanalyst.model.HHSurveyQuestion;
import com.itgrids.partyanalyst.model.HHSurveySubType;
import com.itgrids.partyanalyst.model.HouseHolds;
import com.itgrids.partyanalyst.service.IHouseHoldSurveyReportService;
import com.itgrids.partyanalyst.utils.IConstants;

public class HouseHoldSurveyReportService implements IHouseHoldSurveyReportService{
	private static final Logger log = Logger.getLogger(HouseHoldSurveyReportService.class);
	
	private IHHSurveyQuestionDAO hhSurveyQuestionDAO;
	
	private IHHOptionTypeDAO hhOptionTypeDAO ;
	private TransactionTemplate transactionTemplate;
	private IHHOptionsDAO hhOptionsDAO;
	private IHHQuestionOptionsDAO hhQuestionOptionsDAO;
	private IHHSurveySubTypeDAO hhSurveySubTypeDAO;
	private IBoothDAO boothDAO;
	private IHouseHoldsDAO houseHoldsDAO;
	private IHHSurveyAnswersDAO hhSurveyAnswersDAO;
	
	
	public IHHSurveyAnswersDAO getHhSurveyAnswersDAO() {
		return hhSurveyAnswersDAO;
	}

	public void setHhSurveyAnswersDAO(IHHSurveyAnswersDAO hhSurveyAnswersDAO) {
		this.hhSurveyAnswersDAO = hhSurveyAnswersDAO;
	}

	public IHouseHoldsDAO getHouseHoldsDAO() {
		return houseHoldsDAO;
	}

	public void setHouseHoldsDAO(IHouseHoldsDAO houseHoldsDAO) {
		this.houseHoldsDAO = houseHoldsDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IHHOptionTypeDAO getHhOptionTypeDAO() {
		return hhOptionTypeDAO;
	}

	public void setHhOptionTypeDAO(IHHOptionTypeDAO hhOptionTypeDAO) {
		this.hhOptionTypeDAO = hhOptionTypeDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IHHOptionsDAO getHhOptionsDAO() {
		return hhOptionsDAO;
	}

	public void setHhOptionsDAO(IHHOptionsDAO hhOptionsDAO) {
		this.hhOptionsDAO = hhOptionsDAO;
	}

	public IHHQuestionOptionsDAO getHhQuestionOptionsDAO() {
		return hhQuestionOptionsDAO;
	}

	public void setHhQuestionOptionsDAO(IHHQuestionOptionsDAO hhQuestionOptionsDAO) {
		this.hhQuestionOptionsDAO = hhQuestionOptionsDAO;
	}

	public IHHSurveySubTypeDAO getHhSurveySubTypeDAO() {
		return hhSurveySubTypeDAO;
	}

	public void setHhSurveySubTypeDAO(IHHSurveySubTypeDAO hhSurveySubTypeDAO) {
		this.hhSurveySubTypeDAO = hhSurveySubTypeDAO;
	}

	public IHHSurveyQuestionDAO getHhSurveyQuestionDAO() {
		return hhSurveyQuestionDAO;
	}

	public void setHhSurveyQuestionDAO(IHHSurveyQuestionDAO hhSurveyQuestionDAO) {
		this.hhSurveyQuestionDAO = hhSurveyQuestionDAO;
	}

	public List<HHSurveyVO> getHHSurveyQuestionOptions(Long surveyId,Long boothId,String houseNo){
		List<HHSurveyVO> qstnOptionsList=new ArrayList<HHSurveyVO>();
		List<HHSurveyVO> mainQstnList=new ArrayList<HHSurveyVO>();
		List<HHSurveyVO> subQstnList=new ArrayList<HHSurveyVO>();
		
		List<HHSurveyVO> subsList=null;
		
		try {
			List<HHSurveyQuestion> srvyQstnList=hhSurveyQuestionDAO.getModelBySurveyId(surveyId);
			
			List<Object[]> list=hhSurveySubTypeDAO.getMainSurveyTypes();
			List<Object[]> subQList=hhSurveySubTypeDAO.getSubSurveyTypes();
			
			List<Booth> boothDtls=boothDAO.getModelByBoothId(boothId);
			
			//GETTING THE HOUSE HOLD INFORMATION
			Long muncipalityId=null;
			Long panchayatId=null;
			
			for(Booth booth:boothDtls){
				if(booth.getLocalBody()!=null){
					muncipalityId=booth.getLocalBody().getLocalElectionBodyId();
				}else if(booth.getPanchayat()!=null){
					panchayatId=booth.getPanchayat().getPanchayatId();
				}
			}
			
			List<HouseHolds> houseHolds=houseHoldsDAO.getHouseHoldInfoByPanchayatOrLocalId(panchayatId, muncipalityId, houseNo);
			Long houseHoldId=null;
			if(houseHolds.size()>0){
				for(HouseHolds houseHold:houseHolds){
					houseHoldId=houseHold.getHouseHoldId();
				}
			}
			
			List<HHSurveyAnswers> srvyAnsrsList=hhSurveyAnswersDAO.getSurveyAnswersByHouseHoldId(houseHoldId);
			Map<Long,HHSurveyVO> qstOptnSlctedMap=new HashMap<Long, HHSurveyVO>();
			
			for(HHSurveyAnswers hhsa:srvyAnsrsList){
				if(qstOptnSlctedMap.get(hhsa.getHhSurveyQuestionId())!=null){
					HHSurveyVO hvoTemp=qstOptnSlctedMap.get(hhsa.getHhSurveyQuestionId());
					List<HHSurveyVO> optsList=hvoTemp.getOptions();
					
					HHSurveyVO optVos=new HHSurveyVO();
					optVos.setOptionId(hhsa.getHhOptionsId());
					optVos.setOption(hhsa.getRemarks());
					optVos.setOptionTypeId(hhsa.getHhSurveyQuestion().getHhoptionType().getOptionTypeId());
					
					optsList.add(optVos);
					hvoTemp.setOptions(optsList);
					
					qstOptnSlctedMap.put(hhsa.getHhSurveyQuestionId(), hvoTemp);
				}else{
					HHSurveyVO hvoTemp=new HHSurveyVO();
						List<HHSurveyVO> optsList=new ArrayList<HHSurveyVO>();
						
						HHSurveyVO optVos=new HHSurveyVO();
						optVos.setOptionId(hhsa.getHhOptionsId());
						optVos.setOption(hhsa.getRemarks());
						optVos.setOptionTypeId(hhsa.getHhSurveyQuestion().getHhoptionType().getOptionTypeId());
						
						optsList.add(optVos);
						hvoTemp.setOptions(optsList);
					
						qstOptnSlctedMap.put(hhsa.getHhSurveyQuestionId(), hvoTemp);
						
				}
			}
			
			for(Object[] obj:list){
				HHSurveyVO mainQstnVO=new HHSurveyVO();
				mainQstnVO.setMainQuesId(Long.valueOf(obj[0].toString()));
				mainQstnVO.setMainQues(obj[1].toString());
				
				//mainQstnVO.setOptsSelected(qstOptnSlctedMap.get(obj[0].toString()));
				
				
				mainQstnList.add(mainQstnVO);
			}
			for(Object[] obj:subQList){
				HHSurveyVO subQstnVO=new HHSurveyVO();
				subQstnVO.setSubQuesId(Long.valueOf(obj[0].toString()));
				subQstnVO.setSubQues(obj[1].toString());
				
				//subQstnVO.setOptsSelected(qstOptnSlctedMap.get(obj[0].toString()));
				
				subQstnList.add(subQstnVO);
			}
			
			/*for(HHSurveyQuestion sqstn:srvyQstnList){
				HHSurveyVO hsvo=getMatchedVO(sqstn.getSurveySubType().getParentId(),srvyQstnList);
			}*/
			List<HHSurveyVO> questionsList=new ArrayList<HHSurveyVO>();
			for(HHSurveyQuestion sqstn:srvyQstnList){
				HHSurveyVO mainQstnVO=new HHSurveyVO();
				
				HHSurveyVO hsvo=getMatchedVOForSubQstns(sqstn.getSurveySubType().getHHSurveySubTypeId(),subQstnList);
				if(hsvo!=null){
					hsvo=returnHHVO(sqstn,hsvo,qstOptnSlctedMap);
					//hsvo.setQuestionsList(questList);
					if(sqstn.getSurveySubType().getParentId()!=null){
						HHSurveyVO prntVO=getMatchedVOForMainQstns(sqstn.getSurveySubType().getParentId(),mainQstnList);
						
						
						
						if(prntVO.getSubQuestList()==null){
							subsList=new ArrayList<HHSurveyVO>();
							subsList.add(hsvo);
							prntVO.setSubQuestList(subsList);
						}
						
						if(prntVO.getSubQuestList()!=null){
							HHSurveyVO hhsvo=getMatchedVOForSubQstns(hsvo.getSubQuesId(),prntVO.getSubQuestList());
							if(hhsvo==null){
								subsList=new ArrayList<HHSurveyVO>();
								subsList.add(hsvo);
								List<HHSurveyVO> hsListTemp=prntVO.getSubQuestList();
								hsListTemp.add(hsvo);
								prntVO.setSubQuestList(hsListTemp);
							}
						}
						
						
					}
				}else{
					
					if(sqstn.getSurveySubType().getHHSurveySubTypeId()!=null){
						HHSurveyVO prntVO=getMatchedVOForMainQstns(sqstn.getSurveySubType().getHHSurveySubTypeId(),mainQstnList);
						if(prntVO.getDirectSubQuestList()==null){
							HHSurveyVO hsDrctQuesVO=new HHSurveyVO();
							hsDrctQuesVO=returnHHVO(sqstn,hsDrctQuesVO,qstOptnSlctedMap);
							subsList=new ArrayList<HHSurveyVO>();
							subsList.add(hsDrctQuesVO);
							prntVO.setDirectSubQuestList(subsList);
						}else{
							List<HHSurveyVO> hsList=prntVO.getDirectSubQuestList();
							HHSurveyVO hsDrctQuesVO=hsList.get(0);
							hsDrctQuesVO=returnHHVO(sqstn,hsDrctQuesVO,qstOptnSlctedMap);
							//hsList.add(hsDrctQuesVO);
						}
					}
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mainQstnList;
	}
	
	
	
	public HHSurveyVO returnHHVO(HHSurveyQuestion hhSrvyQstn,HHSurveyVO subListVO,Map<Long,HHSurveyVO> qstOptnSlctedMap){
		
		HHSurveyVO hsvo=new HHSurveyVO();
		List<HHSurveyVO> questList,questList1=null;
		try {
			hsvo.setQuestionId(hhSrvyQstn.getSurveyQuestionId());
			hsvo.setQuestion(hhSrvyQstn.getQuestion());
			hsvo.setQuestionCode(hhSrvyQstn.getQuestionCode());
			hsvo.setOptsSelected(qstOptnSlctedMap.get(hhSrvyQstn.getSurveyQuestionId()));
			List<Long> optsSlctdList=new ArrayList<Long>();
			String optRemarked="";
			
			if(hsvo.getOptsSelected()!=null){
			for(HHSurveyVO optsVo:hsvo.getOptsSelected().getOptions()){
				if(optsVo.getOptionId()!=null){
					optsSlctdList.add(optsVo.getOptionId());
				}
				if(optsVo.getOption()!=null){
				if(optsVo.getOption().trim().length()>0){
					optRemarked=optsVo.getOption();
				}
				}
			}
			}
			
			Set<HHQuestionOptions> options=hhSrvyQstn.getHhQuestionOptions();
			List<HHSurveyVO> optionsList=new ArrayList<HHSurveyVO>();
			for(HHQuestionOptions qo:options){
				HHSurveyVO optVo=new HHSurveyVO();
				optVo.setOptionId(qo.getHhOptions().getOptionsId());
				optVo.setOption(qo.getHhOptions().getOptions());
				if(optsSlctdList.contains(qo.getHhOptions().getOptionsId())){
					optVo.setOptSelected(true);
				}else{
					optVo.setOptSelected(false);
				}
				
				if(optRemarked.length()>0){
					optVo.setOptsRemarked(optRemarked);
				}
				optionsList.add(optVo);
			}
			
			hsvo.setOptions(optionsList);
			hsvo.setOptionType(hhSrvyQstn.getHhoptionType().getOptionType());
			hsvo.setOptionTypeId(hhSrvyQstn.getHhoptionType().getOptionTypeId());
			
			if(hsvo.getQuestionsList()==null){
				questList=new ArrayList<HHSurveyVO>();
				questList.add(hsvo);
				hsvo.setQuestionsList(questList);
			}else{
				questList=hsvo.getQuestionsList();
				questList.add(hsvo);
				
			}
			
			if(subListVO.getQuestionsList()==null){
				questList1=new ArrayList<HHSurveyVO>();
				questList1.add(hsvo);
				subListVO.setQuestionsList(questList1);
			}else{
				questList1=subListVO.getQuestionsList();
				questList1.add(hsvo);
				subListVO.setQuestionsList(questList1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return subListVO;
	}
	
	public HHSurveyVO getMatchedVOForMainQstns(Long id,List<HHSurveyVO> list )
	{
		
		for(HHSurveyVO dtls:list)
			if(dtls.getMainQuesId() == id.longValue() )
				return dtls;
	
		return null;
	}
	
	public HHSurveyVO getMatchedVOForSubQstns(Long id,List<HHSurveyVO> list )
	{
		
		for(HHSurveyVO dtls:list)
			if(dtls.getSubQuesId() == id.longValue() )
				return dtls;
	
		return null;
	}
	
	public List<GenericVO> getAllOptionTypes()
	{
		log.debug("Entered into the getAllOptionTypes service method");
		List<GenericVO> optionTypesList = new ArrayList<GenericVO>();
		
		try {
			List<HHOptionType> optionTypes = hhOptionTypeDAO.getAll();
			
			
			for(HHOptionType optionType:optionTypes)
				optionTypesList.add(new GenericVO(optionType.getOptionTypeId(),optionType.getOptionType()));
		} catch (Exception e) {
			log.error("Exception raised in the getAllOptionTypes service method");
			e.printStackTrace();
		}
		
		return optionTypesList;
	}
	
	public String saveHouseHoldQuestionDetails(final HHQuestionDetailsVO qstnDtls)
	{
		log.debug("Entered into the saveHouseHoldQuestionDetails service method");
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
			
					HHSurveyQuestion question = new HHSurveyQuestion();
					
					question.setHhoptionType(hhOptionTypeDAO.get(qstnDtls.getOptnTypeId()));
					question.setQuestion(qstnDtls.getQuestion().split("::")[0]);
					question.setIsDeleted(IConstants.FALSE);
					question.setQuestionCode(qstnDtls.getQuestion().split("::")[1]);
					question.setSurveySubTypeId(qstnDtls.getSurveySubTypeId() != 0 ? qstnDtls
							.getSurveySubTypeId() : qstnDtls.getSurveyTypeId());
					
					if(qstnDtls.isRemarks())
						question.setRemarks(IConstants.TRUE);
					else
						question.setRemarks(IConstants.FALSE);
					
					question = hhSurveyQuestionDAO.save(question);
					
					
					if(qstnDtls.getOptions() != null && qstnDtls.getOptions().length() >0)
					{
						String[] optionDtls = qstnDtls.getOptions().split("::");
						
						for(int i=0;i<optionDtls.length;i++)
						{
							HHOptions options = new HHOptions();
							
							options.setOptions(optionDtls[i]);
							options.setIsDelete(IConstants.FALSE);
							options.setOrderId((long)i);
							
							options = hhOptionsDAO.save(options);
							
				           HHQuestionOptions HhhQuestionOptions = new HHQuestionOptions();
				           
				           HhhQuestionOptions.setHhOptions(options);
				           HhhQuestionOptions.setHhSurveyQuestion(question);
							
				           hhQuestionOptionsDAO.save(HhhQuestionOptions);
						}
					}
					
				}});
		} catch (Exception e) {
			log.error("Exception raised in the saveHouseHoldQuestionDetails service method");
			e.printStackTrace();
		}
		return "success";
	}
	
	public String createSurveySubCategory(String name,boolean isChild,Long parentId)
	{
		log.debug("Entered into the createSurveySubCategory service method");

		
		try {
			HHSurveySubType type = new HHSurveySubType();
			
				type.setName(name);
				
				if(isChild)
				{
					type.setIsMain(IConstants.FALSE);
					type.setParentId(parentId);
				}else
				{
					type.setIsMain(IConstants.TRUE);	
				}
				
				hhSurveySubTypeDAO.save(type);
		} catch (Exception e) {
			log.error("Exception raised in the createSurveySubCategory service method");
			e.printStackTrace();
		}
		
		return "success";
	}
	
	public List<GenericVO> getSubSurveyTypesByMainTypeId(Long mainTypeId)
	{
		log.debug("Entered into the getSubSurveyTypesByMainTypeId service method");

		List<GenericVO> subTypesList = new ArrayList<GenericVO>();
		
		try {
			List<Object[]> list = hhSurveySubTypeDAO.getSubSurveyTypesByMainTypeId(mainTypeId);
			
			for(Object[] obj:list)
				subTypesList.add(new GenericVO(Long.parseLong(obj[0].toString()),obj[1].toString()));
			
		} catch (Exception e) {
			log.error("Exception raised in the getSubSurveyTypesByMainTypeId service method");
			e.printStackTrace();
		}
		 return subTypesList;
	}
	
	public List<GenericVO> getSurveyTypes()
	{
		log.debug("Entered into the getSurveyTypes service method");

		List<GenericVO> subTypesList = new ArrayList<GenericVO>();
		
		try {
			List<Object[]> list = hhSurveySubTypeDAO.getMainSurveyTypes();
			
			for(Object[] obj:list)
				subTypesList.add(new GenericVO(Long.parseLong(obj[0].toString()),obj[1].toString()));
		
	} catch (Exception e) {
		log.error("Exception raised in the getSurveyTypes service method");
		e.printStackTrace();
	}
	 return subTypesList;
	}
	
	public String saveQuestOptnsOfHH(final Long boothId,final String houseNo,final List<HHSurveyVO> questOptsList){
		final List<HHSurveyAnswers> hhAnswrsList=new ArrayList<HHSurveyAnswers>();
		
		
		
		try{
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				Long muncipalityId=null;
				Long panchayatId=null;
				Long houseHoldId=null;
				
				List<Booth> boothDtls=boothDAO.getModelByBoothId(boothId);
				
				for(Booth booth:boothDtls){
					if(booth.getLocalBody()!=null){
						muncipalityId=booth.getLocalBody().getLocalElectionBodyId();
					}else if(booth.getPanchayat()!=null){
						panchayatId=booth.getPanchayat().getPanchayatId();
					}
				}
				
				List<HouseHolds> houseHolds=houseHoldsDAO.getHouseHoldInfoByPanchayatOrLocalId(panchayatId, muncipalityId, houseNo);
				
				
				
		if(houseHolds.size()>0){
			for(HouseHolds houseHold:houseHolds){
				houseHoldId=houseHold.getHouseHoldId();
				
			}
		}else{
			HouseHolds hhModel=new HouseHolds();
			hhModel.setHouseNo(houseNo);
			hhModel.setPanchaytId(panchayatId);
			hhModel.setLocalElectionBodyId(muncipalityId);
			
			hhModel=houseHoldsDAO.save(hhModel);
			houseHoldId=hhModel.getHouseHoldId();
		}
		//List<Object[]> houseHoldsList=houseHoldsDAO.
		
		for(HHSurveyVO hsvo:questOptsList){
			HHSurveyAnswers hsAnswer=null;
			
			if(hsvo.getOptionTypeId()==1){
				if(hsvo.getOptions()!=null){
					for(HHSurveyVO optn:hsvo.getOptions()){
					if(optn!=null){
						if(optn.getOptionId()!=null){
							hsAnswer=new HHSurveyAnswers();
							hsAnswer.setHhOptionsId(optn.getOptionId());
							hsAnswer.setHhSurveyQuestionId(hsvo.getQuestionId());
							hsAnswer.setHouseHoldId(houseHoldId);
							hhAnswrsList.add(hsAnswer);
						}
					}
					}
				}
			}else if(hsvo.getOptionTypeId()==2){
				if(hsvo.getOptions()!=null){
					for(HHSurveyVO optn:hsvo.getOptions()){
						if(optn!=null){
						if(optn.getOptionId()!=null){
							hsAnswer=new HHSurveyAnswers();
							hsAnswer.setHhOptionsId(optn.getOptionId());
							hsAnswer.setHhSurveyQuestionId(hsvo.getQuestionId());
							hsAnswer.setHouseHoldId(houseHoldId);
							hhAnswrsList.add(hsAnswer);
						}
						}
					}
				}
			}else if(hsvo.getOptionTypeId()==3){
				if(hsvo.getOption().trim()!="" && hsvo.getOption().trim().length()>0){
						hsAnswer=new HHSurveyAnswers();
						hsAnswer.setRemarks(hsvo.getOption());
						//hsAnswer.setHhOptionsId(null);
						hsAnswer.setHhSurveyQuestionId(hsvo.getQuestionId());
						hsAnswer.setHouseHoldId(houseHoldId);
						hhAnswrsList.add(hsAnswer);
					
				}
			}
		}
		
		if(hhAnswrsList.size()>0){
			for(HHSurveyAnswers hhAnswer:hhAnswrsList){
				hhSurveyAnswersDAO.save(hhAnswer);
			}
		}

		}});
		}
		catch (Exception e) {
			log.error("Exception raised in the saveHouseHoldQuestionDetails service method");
			e.printStackTrace();
		}
		return "success";
	}
	
}
