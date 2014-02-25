package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IHHOptionTypeDAO;
import com.itgrids.partyanalyst.dao.IHHOptionsDAO;
import com.itgrids.partyanalyst.dao.IHHQuestionOptionsDAO;
import com.itgrids.partyanalyst.dao.IHHSurveyAnswersDAO;
import com.itgrids.partyanalyst.dao.IHHSurveyQuestionDAO;
import com.itgrids.partyanalyst.dao.IHHSurveySubTypeDAO;
import com.itgrids.partyanalyst.dao.IHouseHoldVoterDAO;
import com.itgrids.partyanalyst.dao.IHouseHoldsDAO;
import com.itgrids.partyanalyst.dao.IHouseHoldsFamilyDetailsDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dao.IVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyRelationDAO;
import com.itgrids.partyanalyst.dao.IHHLeaderDAO;
import com.itgrids.partyanalyst.dao.IHHBoothLeaderDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.HHLeaderDetailsVO;
import com.itgrids.partyanalyst.dto.HHQuestionDetailsVO;
import com.itgrids.partyanalyst.dto.HHSurveyVO;
import com.itgrids.partyanalyst.dto.HouseHoldVotersVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.HHOptionType;
import com.itgrids.partyanalyst.model.HHOptions;
import com.itgrids.partyanalyst.model.HHQuestionOptions;
import com.itgrids.partyanalyst.model.HHSurveyAnswers;
import com.itgrids.partyanalyst.model.HHSurveyQuestion;
import com.itgrids.partyanalyst.model.HHSurveySubType;
import com.itgrids.partyanalyst.model.HouseHoldVoter;
import com.itgrids.partyanalyst.model.HouseHolds;
import com.itgrids.partyanalyst.model.HouseHoldsFamilyDetails;
import com.itgrids.partyanalyst.model.VoterFamilyRelation;
import com.itgrids.partyanalyst.model.HHLeader;
import com.itgrids.partyanalyst.model.HHBoothLeader;
import com.itgrids.partyanalyst.service.IHouseHoldSurveyReportService;
import com.itgrids.partyanalyst.utils.DateUtilService;
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
	private IHHBoothLeaderDAO hhBoothLeaderDAO;
	private IPublicationDateDAO publicationDateDAO;
	private DateUtilService DateUtilService = new DateUtilService();
	
	
	@Autowired
	private IVoterFamilyRelationDAO voterFamilyRelationDAO;
	
	@Autowired
	private IVoterDAO voterDAO;
	
	@Autowired
	private IUserVoterCategoryValueDAO  userVoterCategoryValueDAO ;
	 
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IVoterCategoryValueDAO voterCategoryValueDAO;
	
	@Autowired
	private IHouseHoldsFamilyDetailsDAO houseHoldsFamilyDetailsDAO; 
	
	
	private IHouseHoldVoterDAO houseHoldVoterDAO;
	private IHHLeaderDAO hhLeaderDAO;		    
	
	public IPublicationDateDAO getPublicationDateDAO() {
		return publicationDateDAO;
	}

	public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
		this.publicationDateDAO = publicationDateDAO;
	}

	public IHHBoothLeaderDAO getHhBoothLeaderDAO() {
		return hhBoothLeaderDAO;
	}

	public void setHhBoothLeaderDAO(IHHBoothLeaderDAO hhBoothLeaderDAO) {
		this.hhBoothLeaderDAO = hhBoothLeaderDAO;
	}
	
	public IHHLeaderDAO getHhLeaderDAO() {
		return hhLeaderDAO;
	}

	public void setHhLeaderDAO(IHHLeaderDAO hhLeaderDAO) {
		this.hhLeaderDAO = hhLeaderDAO;
	}

	public IHouseHoldVoterDAO getHouseHoldVoterDAO() {
		return houseHoldVoterDAO;
	}

	public void setHouseHoldVoterDAO(IHouseHoldVoterDAO houseHoldVoterDAO) {
		this.houseHoldVoterDAO = houseHoldVoterDAO;
	}

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

	public List<HHSurveyVO> getHHSurveyQuestionOptions(Long surveyId,Long boothId,String houseNo,Long voterId){
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
			
			
			
			
			Long houseHoldId=null;
			
			houseHoldId = houseHoldVoterDAO.getHouseHoldIdForVoter(voterId);
			
			//List<HouseHolds> houseHolds=houseHoldsDAO.getHouseHoldInfoByPanchayatOrLocalId(panchayatId, muncipalityId, houseNo);
			/*if(houseHolds.size()>0){
				for(HouseHolds houseHold:houseHolds){
					houseHoldId=houseHold.getHouseHoldId();
				}
			}*/
			
			List<HHSurveyAnswers> srvyAnsrsList=new ArrayList<HHSurveyAnswers>();
			
			if(houseHoldId!=null){
				srvyAnsrsList=hhSurveyAnswersDAO.getSurveyAnswersByHouseHoldId(houseHoldId);
			}
			
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
				mainQstnVO.setMainQues(StringEscapeUtils.unescapeJava(obj[1].toString()));
				
				//mainQstnVO.setOptsSelected(qstOptnSlctedMap.get(obj[0].toString()));
				
				
				mainQstnList.add(mainQstnVO);
			}
			for(Object[] obj:subQList){
				HHSurveyVO subQstnVO=new HHSurveyVO();
				subQstnVO.setSubQuesId(Long.valueOf(obj[0].toString()));
				subQstnVO.setSubQues(StringEscapeUtils.unescapeJava(obj[1].toString()));
				
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
			hsvo.setQuestion(StringEscapeUtils.unescapeJava(hhSrvyQstn.getQuestion()));
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
				optVo.setOption(StringEscapeUtils.unescapeJava(qo.getHhOptions().getOptions()));
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
					question.setQuestion(escapeUnicode(StringEscapeUtils.unescapeHtml(qstnDtls.getQuestion().split("::")[0]).trim()));
					question.setIsDeleted(IConstants.FALSE);
					question.setSurveyId(1l);
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
							
							options.setOptions(escapeUnicode(StringEscapeUtils.unescapeHtml(optionDtls[i])));
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
	
	   public String escapeUnicode(String input) {
			StringBuilder b = new StringBuilder(input.length());
			Formatter f = new Formatter(b);
			for (char c : input.toCharArray()) {
				if (c < 128) {
				  b.append(c);
				} else {
				  f.format("\\u%04x", (int) c);
				}
			}
			return b.toString();
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
				subTypesList.add(new GenericVO(Long.parseLong(obj[0].toString()),StringEscapeUtils.unescapeJava(obj[1].toString())));
			
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
				subTypesList.add(new GenericVO(Long.parseLong(obj[0].toString()),StringEscapeUtils.unescapeJava(obj[1].toString())));
		
	} catch (Exception e) {
		log.error("Exception raised in the getSurveyTypes service method");
		e.printStackTrace();
	}
	 return subTypesList;
	}
	
	public String saveQuestOptnsOfHH(final Long boothId, final String houseNo,
			final List<HHSurveyVO> questOptsList,final Long houseHoldsId) {
		final List<HHSurveyAnswers> hhAnswrsList=new ArrayList<HHSurveyAnswers>();
		
		
		
		try{
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				Long muncipalityId=null;
				Long panchayatId=null;
				//Long houseHoldId=null;
				
			/*	List<Booth> boothDtls=boothDAO.getModelByBoothId(boothId);
				
				for(Booth booth:boothDtls){
					if(booth.getLocalBody()!=null){
						muncipalityId=booth.getLocalBody().getLocalElectionBodyId();
					}else if(booth.getPanchayat()!=null){
						panchayatId=booth.getPanchayat().getPanchayatId();
					}
				}
				
				List<HouseHolds> houseHolds=houseHoldsDAO.getHouseHoldInfoByPanchayatOrLocalId(panchayatId, muncipalityId, houseNo);
				
				*/
				
	/*	if(houseHolds.size()>0){
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
		}*/
		

         /*List<Long> voterIDs = new ArrayList<Long>();
         
         voterIDs.add(6979790L);
         
         List<Long> voterFamilyRelationIds = new ArrayList<Long>();
         
         voterFamilyRelationIds.add(2L);
         
         
         for(int i=0;i<voterIDs.size();i++)
         {
        	 HouseHoldVoter houseHoldVoter = new HouseHoldVoter();
        	 
        	 houseHoldVoter.setVoterId(voterIDs.get(0));
        	 houseHoldVoter.setHouseHoldId(houseHoldId);
        	 houseHoldVoter.setVoterFamilyRelationId(voterFamilyRelationIds.get(0));
        	 
        	 houseHoldVoterDAO.save(houseHoldVoter);
        	 
         }*/
		
		//List<Object[]> houseHoldsList=houseHoldsDAO.
				
				
				
		//delete all the previous records by houseHoldsId
	   //This is the temporary solutions.We need to change this using is_delete column in table
				
			hhSurveyAnswersDAO.deleteAllPreviousAnswersByHouseHoldsId(houseHoldsId);
		
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
							hsAnswer.setHouseHoldId(houseHoldsId);
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
							hsAnswer.setHouseHoldId(houseHoldsId);
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
						hsAnswer.setHouseHoldId(houseHoldsId);
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
	
	public Long saveHouseHoldsVotersDetails(final HouseHoldVotersVO votersDetails)
	{
		log.debug("Entered into the saveHouseHoldsVotersDetails service method");
		 Long houseHoldId = 0L;
		
		try {
			
			 houseHoldId = (Long)transactionTemplate.execute(new TransactionCallback() {
				
				public Object doInTransaction(TransactionStatus status) {
					
					Long muncipalityId=null;
					Long panchayatId=null;
                    Long hseHoldId = 0L;
                    
                    if(votersDetails.getHouseHoldsId() == 0L)
                    {
                    
					List<Booth> boothDtls=boothDAO.getModelByBoothId(votersDetails.getBoothId());
					
					for(Booth booth:boothDtls){
						if(booth.getLocalBody()!=null){
							muncipalityId=booth.getLocalBody().getLocalElectionBodyId();
						}else if(booth.getPanchayat()!=null){
							panchayatId=booth.getPanchayat().getPanchayatId();
						}
					}
					
					
						HouseHolds hhModel=new HouseHolds();
						
						hhModel.setHouseNo(votersDetails.getHouseNo());
						hhModel.setPanchaytId(panchayatId);
						hhModel.setLocalElectionBodyId(muncipalityId);
						
						hhModel=houseHoldsDAO.save(hhModel);
						
						hseHoldId = hhModel.getHouseHoldId();
						
						for(HouseHoldVotersVO voterDtls:votersDetails.getHouseHoldsVoters())
						{
							
							if(!voterDtls.isNewPerson())
							{
								
								HouseHoldVoter voter = new HouseHoldVoter();
								
								
								voter.setVoterFamilyRelationId(voterDtls.getVoterFamilyRelationId());
								voter.setHouseHoldId(hseHoldId);
								voter.setEducationId(voterDtls.getEducationId());
								voter.setOccupationId(voterDtls.getOccupationId());
								voter.setSocialCategoryId(voterDtls.getSocialPstnId());
								
								List<HouseHoldVoter> houseHoldVotersList = houseHoldVoterDAO.checkExistanceOfVoterInHouseHoldsVoter(voterDtls.getVoterId());
								
								if(houseHoldVotersList != null && houseHoldVotersList.size() >0)
								{
									for(HouseHoldVoter hhVoter:houseHoldVotersList)
									{
										hhVoter.setIsDelete(IConstants.TRUE);
										houseHoldVoterDAO.save(hhVoter);
									}
								}
								
								voter.setVoterId(voterDtls.getVoterId());
								voter.setIsDelete(IConstants.FALSE);
								voter.setOwnerMobileNo(voterDtls.getOwnerMobileNo());
								voter.setLeaderId(voterDtls.getLeaderId());
								
								houseHoldVoterDAO.save(voter);
								
								
							}else
							{
								HouseHoldsFamilyDetails familyDetails = new HouseHoldsFamilyDetails();
								
								familyDetails.setAge(voterDtls.getAge());
								familyDetails.setGender(voterDtls.getGender());
								familyDetails.setName(voterDtls.getName());
								familyDetails.setInsertedTime(DateUtilService.getCurrentDateAndTime());
								
								if(voterDtls.getRelationShipType() != null && !voterDtls.getRelationShipType().equalsIgnoreCase(""))
								 familyDetails.setRelationshipType(voterDtls.getRelationShipType());
								
								if(voterDtls.getRelativeName() != null && !voterDtls.getRelativeName().equalsIgnoreCase(""))
								 familyDetails.setRelativeName(voterDtls.getRelativeName());
								
								if(voterDtls.getMobileNo() != null && !voterDtls.getMobileNo().equalsIgnoreCase(""))
								familyDetails.setMobileNo(voterDtls.getMobileNo());
								
								familyDetails = 	houseHoldsFamilyDetailsDAO.save(familyDetails);
								
								
								HouseHoldVoter newPerson = new HouseHoldVoter();
								
								newPerson.setVoterFamilyRelationId(voterDtls.getVoterFamilyRelationId());
								newPerson.setHouseHoldId(hseHoldId);
								newPerson.setEducationId(voterDtls.getEducationId());
								newPerson.setOccupationId(voterDtls.getOccupationId());
								newPerson.setSocialCategoryId(voterDtls.getSocialPstnId());
								newPerson.setHouseHoldsFamilyDetailsId(familyDetails.getHouseHoldsFamilyDetailsId());
								newPerson.setIsDelete(IConstants.FALSE);
								newPerson.setOwnerMobileNo(voterDtls.getOwnerMobileNo());
								newPerson.setLeaderId(voterDtls.getLeaderId());
								
								houseHoldVoterDAO.save(newPerson);

							}
							}
						
                    }else
                    {
                    	hseHoldId = votersDetails.getHouseHoldsId();
                    	
                    	List<Long> savedVoterIds = new ArrayList<Long>();
                    	List<Long> newVoterIds = new ArrayList<Long>();
                    	
						List<HouseHoldVoter> houseHoldsVoters = houseHoldVoterDAO
										.getHouseHoldsVoterdDetailsByHouseHoldId(hseHoldId);
						
						for(HouseHoldVoter houseHoldVoter:houseHoldsVoters)
							savedVoterIds.add(houseHoldVoter.getVoterId());
							
						
						for(HouseHoldVotersVO voterDtls:votersDetails.getHouseHoldsVoters())
							if(voterDtls.getHouseHoldFamilyMemberId() != null && voterDtls.getVoterId() != null)
							newVoterIds.add(voterDtls.getVoterId());
						
						
						for(HouseHoldVotersVO voterDtls:votersDetails.getHouseHoldsVoters())
						{
							
							if(!voterDtls.isNewPerson())
							{
								if(savedVoterIds.contains(voterDtls.getVoterId()))
								{
									HouseHoldVoter houseHoldsVoter = getMatchedHouseHoldsVoter(
													voterDtls.getVoterId(),
													houseHoldsVoters);
								
										houseHoldsVoter.setVoterFamilyRelationId(voterDtls.getVoterFamilyRelationId());
										houseHoldsVoter.setHouseHoldId(hseHoldId);
										houseHoldsVoter.setEducationId(voterDtls.getEducationId());
										houseHoldsVoter.setOccupationId(voterDtls.getOccupationId());
										houseHoldsVoter.setSocialCategoryId(voterDtls.getSocialPstnId());
										houseHoldsVoter.setOwnerMobileNo(voterDtls.getOwnerMobileNo());
										houseHoldsVoter.setLeaderId(voterDtls.getLeaderId());
										houseHoldsVoter.setIsDelete(IConstants.FALSE);
										
										houseHoldVoterDAO.save(houseHoldsVoter);
										
										savedVoterIds.remove(voterDtls.getVoterId());
										
								}else
								{
									HouseHoldVoter voter = new HouseHoldVoter();
									
									voter.setVoterId(voterDtls.getVoterId());
									voter.setVoterFamilyRelationId(voterDtls.getVoterFamilyRelationId());
									voter.setHouseHoldId(hseHoldId);
									voter.setEducationId(voterDtls.getEducationId());
									voter.setOccupationId(voterDtls.getOccupationId());
									voter.setSocialCategoryId(voterDtls.getSocialPstnId());
									voter.setOwnerMobileNo(voterDtls.getOwnerMobileNo());
									voter.setLeaderId(voterDtls.getLeaderId());
									voter.setIsDelete(IConstants.FALSE);
									
									houseHoldVoterDAO.save(voter);
									
								
									
								}
							
							}
						}
						
						
						for(Long savedVoterID:savedVoterIds)
						{
							HouseHoldVoter houseHoldsVoter = getMatchedHouseHoldsVoter(
									savedVoterID,houseHoldsVoters);
							
							houseHoldsVoter.setIsDelete(IConstants.TRUE);
							houseHoldVoterDAO.save(houseHoldsVoter);
							
							
						}
						
						
						//updating family members details

						List<Long> savedMemberIds = new ArrayList<Long>();
						List<Long> newMemberIds = new ArrayList<Long>();
						
						List<HouseHoldsFamilyDetails> membersDetails = houseHoldVoterDAO
										.getFamilyMembersDetailsByHouseHoldsId(hseHoldId);	
						
						
						for(HouseHoldsFamilyDetails details:membersDetails)
							savedMemberIds.add(details.getHouseHoldsFamilyDetailsId());
						
						for(HouseHoldVotersVO voterDtls:votersDetails.getHouseHoldsVoters())
							if(voterDtls.isNewPerson() && voterDtls.getHouseHoldFamilyMemberId().longValue() !=0)
								newMemberIds.add(voterDtls.getHouseHoldFamilyMemberId());
						
						
						for(HouseHoldVotersVO voterDtls:votersDetails.getHouseHoldsVoters())
						{
							
							if(voterDtls.isNewPerson())
							{
								if(savedMemberIds.contains(voterDtls.getHouseHoldFamilyMemberId()))
								{
									HouseHoldsFamilyDetails familyDetails = getMatchedFamilyMemberDetails(
											voterDtls.getHouseHoldFamilyMemberId(),
											membersDetails);
									
									familyDetails.setAge(voterDtls.getAge());
									familyDetails.setGender(voterDtls.getGender());
									familyDetails.setName(voterDtls.getName());
									//familyDetails.setInsertedTime(DateUtilService.getCurrentDateAndTime());
									
									familyDetails.setRelationshipType(voterDtls.getRelationShipType());									
									familyDetails.setRelativeName(voterDtls.getRelativeName());									
									familyDetails.setMobileNo(voterDtls.getMobileNo());									
									familyDetails = houseHoldsFamilyDetailsDAO.save(familyDetails);
									
									List<HouseHoldVoter> houseHoldVoters = houseHoldVoterDAO
													.getHouseHoldVoterDetailsByFamilyMemberId(familyDetails
															.getHouseHoldsFamilyDetailsId());	
									
									if(houseHoldVoters != null && houseHoldVoters.size() >0)
									{
										HouseHoldVoter familyMember = houseHoldVoters.get(0);
										
										familyMember.setEducationId(voterDtls.getEducationId());
										familyMember.setOccupationId(voterDtls.getOccupationId());
										familyMember.setSocialCategoryId(voterDtls.getSocialPstnId());
										familyMember.setVoterFamilyRelationId(voterDtls.getVoterFamilyRelationId());
										familyMember.setOwnerMobileNo(voterDtls.getOwnerMobileNo());
										familyMember.setLeaderId(voterDtls.getLeaderId());
										houseHoldVoterDAO.save(familyMember);
										
									}
										
									savedMemberIds.remove(voterDtls.getHouseHoldFamilyMemberId());
										
								}else
								{
									HouseHoldsFamilyDetails familyDetails = new HouseHoldsFamilyDetails();
									
									familyDetails.setAge(voterDtls.getAge());
									familyDetails.setGender(voterDtls.getGender());
									familyDetails.setName(voterDtls.getName());
									familyDetails.setInsertedTime(DateUtilService.getCurrentDateAndTime());
									familyDetails.setRelationshipType(voterDtls.getRelationShipType());									
									familyDetails.setRelativeName(voterDtls.getRelativeName());									
									familyDetails.setMobileNo(voterDtls.getMobileNo());	
									familyDetails.setIsDelete(IConstants.FALSE);
									
									familyDetails = houseHoldsFamilyDetailsDAO.save(familyDetails);
									
									HouseHoldVoter voter = new HouseHoldVoter();
									
									voter.setVoterId(voterDtls.getVoterId());
									voter.setVoterFamilyRelationId(familyDetails.getHouseHoldsFamilyDetailsId());
									voter.setHouseHoldId(hseHoldId);
									voter.setEducationId(voterDtls.getEducationId());
									voter.setOccupationId(voterDtls.getOccupationId());
									voter.setSocialCategoryId(voterDtls.getSocialPstnId());
									voter.setHouseHoldsFamilyDetailsId(familyDetails.getHouseHoldsFamilyDetailsId());
									voter.setOwnerMobileNo(voterDtls.getOwnerMobileNo()!=null?voterDtls.getOwnerMobileNo():"");
									voter.setLeaderId(voterDtls.getLeaderId());
									voter.setIsDelete(IConstants.FALSE);
									
									houseHoldVoterDAO.save(voter);
										
								}
								
								
							}
						}
						for(Long savedMemberId:savedMemberIds)
						{
							
							HouseHoldsFamilyDetails familyDetails = getMatchedFamilyMemberDetails(
									savedMemberId,
									membersDetails);
							
							familyDetails.setIsDelete(IConstants.TRUE);
							houseHoldsFamilyDetailsDAO.save(familyDetails);
							
						}
						
                    }
					return hseHoldId;
				}
			});
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception raised in  saveHouseHoldsVotersDetails service method");
			return 0L;

		}
		
		return houseHoldId;
	}
	
	public GenericVO getLeaderIdAndMobileNoFromHH(Long voterId){
		log.debug("Entered into the getLeaderIdAndMobileNoFromHH method");
		GenericVO gvo=new GenericVO();
		try {
			List<Object[]> hhLdrMblList=houseHoldVoterDAO.getOwnerMobileAndLeaderIdForVoterId(voterId);
		
			for(Object[] obj:hhLdrMblList){
				
				
				gvo.setId(Long.valueOf(obj[0].toString()));
				gvo.setName(obj[1].toString());
			}
				
		} catch (Exception e) {
			log.error("Exception raised in the getLeaderIdAndMobileNoFromHH method");
			e.printStackTrace();
		}
		return gvo;
	}
	
	public String saveMainQuestionDetails(String qtn)
	{
		log.debug("Entered into the saveMainQuestionDetails method");
		try {
			
					HHSurveySubType mainQuestion = new HHSurveySubType();
					
					mainQuestion.setName(escapeUnicode(StringEscapeUtils.unescapeHtml(qtn.trim())));
					mainQuestion.setIsMain(IConstants.TRUE);
					
					mainQuestion = hhSurveySubTypeDAO.save(mainQuestion);
					
				
		} catch (Exception e) {
			log.error("Exception raised in the saveMainQuestionDetails method");
			e.printStackTrace();
		}
		return "success";
	}
   
	public String saveSubQuestionDetails(Long id,String subQtn)
	{
		log.debug("Entered into the saveSubQuestionDetails method");
		try {
					HHSurveySubType subQuestion = new HHSurveySubType();
					
					subQuestion.setName(escapeUnicode(StringEscapeUtils.unescapeHtml(subQtn.trim())));
					subQuestion.setIsMain(IConstants.FALSE);
					subQuestion.setParentId(id);
					
					subQuestion = hhSurveySubTypeDAO.save(subQuestion);
		} catch (Exception e) {
			log.error("Exception raised in the saveSubQuestionDetails method");
			e.printStackTrace();
		}
		return "success";
	}
	
	
	
	public HouseHoldsFamilyDetails getMatchedFamilyMemberDetails(Long memberId,List<HouseHoldsFamilyDetails> houseHoldVoters)
	{
		
		for(HouseHoldsFamilyDetails familyMember:houseHoldVoters)
			if(familyMember.getHouseHoldsFamilyDetailsId().longValue() == memberId.longValue())
				return familyMember;
		return null;
		
		
	}
	
	public HouseHoldVoter getMatchedHouseHoldsVoter(Long voterId,List<HouseHoldVoter> houseHoldVoters)
	{
		
		for(HouseHoldVoter houseHoldsVoter:houseHoldVoters)
			if(houseHoldsVoter.getVoterId().longValue() == voterId.longValue())
				return houseHoldsVoter;
		return null;
		
		
	}
	
	public List<SelectOptionVO> getUserVoterCategoryValuesById(Long categoryId)
	{
		List<SelectOptionVO> values = new ArrayList<SelectOptionVO>();
		
		List<Object[]> list = userVoterCategoryValueDAO.getValuesById(categoryId);
		
		
		for(Object[] obj:list)
			values.add(new SelectOptionVO(Long.parseLong(obj[0].toString()),obj[1].toString()));
			
		return values;
	}
	
	public List<SelectOptionVO> getFamilyRelationsList()
	{
		List<SelectOptionVO> values = new ArrayList<SelectOptionVO>();
		
		List<VoterFamilyRelation> list = voterFamilyRelationDAO.getAll();
		
		
		for(VoterFamilyRelation relation:list)
			values.add(new SelectOptionVO(relation.getVoterFamilyRelationId(),relation.getRelation()));
			
		return values;
	}
	
	public Long getHouseHoldIdOfVoter(Long voterId){
		
		Long houseHoldId = houseHoldVoterDAO.getHouseHoldIdForVoter(voterId);
		return houseHoldId;
		
	}
	
    public List<String> getAllVoterIds(){
		
		List<String> allVoterIds = hhLeaderDAO.getAllExistingVoterIds();		
		return allVoterIds;
		
	}
    
    public ResultStatus saveLeaderDetails(final HHLeaderDetailsVO leaderDtls)
	{
		log.debug("Entered into the saveLeaderDetails service method");
		ResultStatus resultStatus = new ResultStatus();
		List namesList = hhBoothLeaderDAO.getLeaderIdForBoothId(leaderDtls.getName(),leaderDtls.getBoothId());
	    if(namesList != null && namesList.size() > 0 ){
	    	resultStatus.setResultCode(ResultCodeMapper.FAILURE);
	    	return resultStatus;
	    }
	    else{
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
			
					HHLeader details = new HHLeader();
					details.setName(leaderDtls.getName());
					details.setMobileNo(leaderDtls.getMobileNo());
					details.setVoterId(leaderDtls.getVoterId());
					details.setUniqueCode(leaderDtls.getUniqueCode());					
					details.setIs_active(leaderDtls.getIsActive());
					
					     details = hhLeaderDAO.save(details);
										
							HHBoothLeader hhBoothLeader = new HHBoothLeader();							
							hhBoothLeader.setConstituencyId(leaderDtls.getConstId());
							hhBoothLeader.setBoothId(leaderDtls.getBoothId());
							hhBoothLeader.setHhLeaderId(details.getId());
							hhBoothLeader = hhBoothLeaderDAO.save(hhBoothLeader);
					}				
			});
		} catch (Exception e) {
			log.error("Exception raised in the saveLeaderDetails service method");
			e.printStackTrace();
		}
	  }
	    resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
    	return resultStatus;
	}
    
    public List<SelectOptionVO> getBoothIdsByConstituencyId(Long constituencyId)
    {
    	Long publicationId =publicationDateDAO.getLatestPublicationIdByConstiId(constituencyId);
    	List<Object[]> boothIdNames = boothDAO.getBoothsInAConstituencyByPublication(constituencyId,publicationId);
    	List<SelectOptionVO> result = new ArrayList<SelectOptionVO>(); 
    	if(boothIdNames != null && boothIdNames.size() > 0){
    	for(Object[] param: boothIdNames)
    	{
    		SelectOptionVO vo =new SelectOptionVO();
    		vo.setId((Long)param[0]);
    		vo.setName("booth - "+param[1].toString());
    		result.add(vo);
    	}
    	}
		return result;
    
    }
    
}
