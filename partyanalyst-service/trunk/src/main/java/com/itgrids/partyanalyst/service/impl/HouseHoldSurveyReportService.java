package com.itgrids.partyanalyst.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IHHBoothLeaderDAO;
import com.itgrids.partyanalyst.dao.IHHLeaderBooksDAO;
import com.itgrids.partyanalyst.dao.IHHLeaderDAO;
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
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.HHLeaderDetailsVO;
import com.itgrids.partyanalyst.dto.HHQuestionDetailsVO;
import com.itgrids.partyanalyst.dto.HHQuestionSummaryReportVO;
import com.itgrids.partyanalyst.dto.HHReportVO;
import com.itgrids.partyanalyst.dto.HHSurveyVO;
import com.itgrids.partyanalyst.dto.HouseHoldVotersVO;
import com.itgrids.partyanalyst.dto.HouseHoldsReportVO;
import com.itgrids.partyanalyst.dto.HouseHoldsSummaryReportVO;
import com.itgrids.partyanalyst.dto.HouseHoldsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.HHBoothLeader;
import com.itgrids.partyanalyst.model.HHLeader;
import com.itgrids.partyanalyst.model.HHLeaderBooks;
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
import com.itgrids.partyanalyst.service.IHouseHoldSurveyReportService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
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
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IVotersAnalysisService votersAnalysisService;

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
	
	@Autowired private IHHLeaderBooksDAO hhLeaderBooksDAO;
	
	
	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	private IHouseHoldVoterDAO houseHoldVoterDAO;
	private IHHLeaderDAO hhLeaderDAO;		    
	
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
			Collections.sort(optionsList,sortById);
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
	
	 public static Comparator<HHSurveyVO> sortById = new Comparator<HHSurveyVO>()
	 {				  
	     public int compare(HHSurveyVO resultData1, HHSurveyVO resultData2)
		{
			return (resultData1.getOptionId().intValue()) - (resultData2.getOptionId().intValue());
		}
	 };
	
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
						hhModel.setInsertedTime(DateUtilService.getCurrentDateAndTime());
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
								voter.setLeaderBookNo(voterDtls.getLeaderBookNo());
								
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
								newPerson.setLeaderBookNo(voterDtls.getLeaderBookNo());
								
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
										houseHoldsVoter.setLeaderBookNo(voterDtls.getLeaderBookNo());
										houseHoldsVoter.setIsDelete(IConstants.FALSE);
										
										houseHoldVoterDAO.save(houseHoldsVoter);
										
										savedVoterIds.remove(voterDtls.getVoterId());
										
								}else
								{
									HouseHoldVoter voter = new HouseHoldVoter();
									int update_count=houseHoldVoterDAO.updateStatusIfVoterIdExist(voterDtls.getVoterId());
									
									voter.setVoterId(voterDtls.getVoterId());
									voter.setVoterFamilyRelationId(voterDtls.getVoterFamilyRelationId());
									voter.setHouseHoldId(hseHoldId);
									voter.setEducationId(voterDtls.getEducationId());
									voter.setOccupationId(voterDtls.getOccupationId());
									voter.setSocialCategoryId(voterDtls.getSocialPstnId());
									voter.setOwnerMobileNo(voterDtls.getOwnerMobileNo());
									voter.setLeaderId(voterDtls.getLeaderId());
									voter.setLeaderBookNo(voterDtls.getLeaderBookNo());
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
									
									familyDetails.setRelationshipType(voterDtls.getVoterFamilyRelationId().toString());									
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
										familyMember.setLeaderBookNo(voterDtls.getLeaderBookNo());
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
									voter.setVoterFamilyRelationId(Long.valueOf(voterDtls.getRelationShipType()));
									voter.setHouseHoldId(hseHoldId);
									voter.setEducationId(voterDtls.getEducationId());
									voter.setOccupationId(voterDtls.getOccupationId());
									voter.setSocialCategoryId(voterDtls.getSocialPstnId());
									voter.setHouseHoldsFamilyDetailsId(familyDetails.getHouseHoldsFamilyDetailsId());
									voter.setOwnerMobileNo(voterDtls.getOwnerMobileNo()!=null?voterDtls.getOwnerMobileNo():"");
									voter.setLeaderId(voterDtls.getLeaderId());
									voter.setLeaderBookNo(voterDtls.getLeaderBookNo());
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
							
							int count=houseHoldVoterDAO.childMembersDelete(savedMemberId);
							
							familyDetails.setIsDelete(IConstants.TRUE);
							houseHoldsFamilyDetailsDAO.save(familyDetails);
							
						}
						
                    }
					return hseHoldId;
				}
			});
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception raised in  saveHouseHoldsVotersDetails service method" +e);
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
		List namesList = hhBoothLeaderDAO.getLeaderIdForBoothId(leaderDtls.getVoterId(),leaderDtls.getBoothId());
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
					//details.setUniqueCode(leaderDtls.getUniqueCode());					
					details.setIs_active(leaderDtls.getIsActive());
					//details.setConstituencyId(leaderDtls.getConstId());
					//details.setBoothId(leaderDtls.getBoothId());
					details = hhLeaderDAO.save(details);
					
							HHLeaderBooks hhLeaderBooks = new HHLeaderBooks();
							hhLeaderBooks.setLeaderId(details.getId());
							hhLeaderBooks.setBookNo(Long.parseLong(leaderDtls.getUniqueCode(),10));
							hhLeaderBooks = hhLeaderBooksDAO.save(hhLeaderBooks);
										
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
    
    public VoterDetailsVO getVoterDetailsByVoterId(String voterIdCardNo)
	{
		log.debug("Entered into the getVoterDetailsByVoterId method");
		VoterDetailsVO resultVO = new VoterDetailsVO();
		try {
			List<Object[]> constyPublicationIdByVoterId=boothPublicationVoterDAO.getConstyPublicationIdByVoterId1(voterIdCardNo);
			List<VoterDetailsVO> constyPublicationIds= new ArrayList<VoterDetailsVO>();
			if(constyPublicationIdByVoterId != null && constyPublicationIdByVoterId.size() > 0)
			{
			Object[] list = (Object[])constyPublicationIdByVoterId.get(0);
			    VoterDetailsVO vo= new VoterDetailsVO();
				vo.setVoterId((Long)list[3]);
				vo.setVoterName(list[4].toString());
				vo.setConstituencyId((Long)list[0]);
				vo.setBoothId((Long)list[1]);
				vo.setPublicationDateId((Long)list[2]);
				constyPublicationIds.add(vo);			
			
		    resultVO.setConstyPublicationIds(constyPublicationIds);
			
		    List<SelectOptionVO> publicationDatesList = votersAnalysisService.publicationDetailsBasedOnConstituency(vo.getConstituencyId());
			List<SelectOptionVO> boothList = votersAnalysisService.getBoothsForConstituencyAndPublication(vo.getConstituencyId(),vo.getPublicationDateId());
			resultVO.setPublicationDatesList(publicationDatesList);
			resultVO.setBoothList(boothList);
			}
		} catch (Exception e) {
			log.error("Exception raised in the getVoterDetailsByVoterId method");
			e.printStackTrace();
		}
		return resultVO;
	}
    
    public HouseHoldsVO getBoothsOrBooksInConstituencyOfLeaderToAssign(final Long constituencyId,Long publicationId,final Long leaderId,String task,String bookNo,final List<Long> boothOrBooks){
    	HouseHoldsVO hv = new HouseHoldsVO();
    	try{
    	if(task.equalsIgnoreCase("forBooths")){
    		List<Object[]> boothsInConsti = boothDAO.getBoothsInAConstituencyByPublication(constituencyId,publicationId);
    		List<Object[]> boothsOfLeader = hhBoothLeaderDAO.getBoothsOfLeader(leaderId);
    		
    		List<Long> hseHldsUnderLdr = houseHoldVoterDAO.getHouseHoldsOfLeader(leaderId);
    		
    		List<Long> boothsUnderLdr = new ArrayList<Long>();
    		if(hseHldsUnderLdr != null && hseHldsUnderLdr.size()>0){
    			boothsUnderLdr = boothPublicationVoterDAO.getBoothIdsOfVoterIds(hseHldsUnderLdr, publicationId);
    		}
    		
    		List<Long> bthsOfLdrList = new ArrayList<Long>();
    		if(boothsOfLeader!=null && boothsOfLeader.size()>0){
    			for(Object[] obj:boothsOfLeader){
    				bthsOfLdrList.add(Long.valueOf(obj[0].toString()));
    			}
    		}
    		
    		if(boothsInConsti !=null && boothsInConsti.size()>0){
    				List<HouseHoldsVO> boothsList = new ArrayList<HouseHoldsVO>();
    			for(Object[] obj:boothsInConsti){
    				HouseHoldsVO hvo = new HouseHoldsVO();
    				
    				if(bthsOfLdrList!=null && bthsOfLdrList.size()>0){
    					if(bthsOfLdrList.contains(Long.valueOf(obj[0].toString()))){
    						hvo.setEnableBooth(true);
    						
    						if(boothsUnderLdr!=null && boothsUnderLdr.size()>0){
    							if(boothsUnderLdr.contains(Long.valueOf(obj[0].toString()))){
    								hvo.setDisableBooth(true);
    							}else{
    								hvo.setDisableBooth(false);
    							}
    						}else{
    							hvo.setDisableBooth(false);
    						}
    						
    					}else{
    						hvo.setEnableBooth(false);
    						hvo.setDisableBooth(false);
    					}
    				}
    				
    				hvo.setBoothId(Long.valueOf(obj[0].toString()));
    				hvo.setPartNo(Long.valueOf(obj[1].toString()));
    				
    				boothsList.add(hvo);
    			}
    			
    			hv.setBoothsInConstituency(boothsList);
    		}
    		
    		
    		
    	}else if(task.equalsIgnoreCase("forBooks")){
    		//List<Long> booksOfLeader = hhLeaderBooksDAO.getAllLeaderBooks(leaderId);
    		List<Long> bksOfLeaderInConsti = hhLeaderBooksDAO.getAllLeaderBooksInConstituency(leaderId,constituencyId);
    		
    		List<Object[]> bksOfLeaderInConstincy = hhLeaderBooksDAO.getAllLeaderBooksInConstituencyWithNo(leaderId,constituencyId);
    		List<Object[]> booksNotAssgned = hhLeaderBooksDAO.getAllBooksNotAssigned();
    		
    		List<Long> hseHldsUnderLdr = houseHoldVoterDAO.getBooksOfHouseHoldsOfLeader(leaderId, constituencyId);
    		
    		    		
    		List<Long> bksOfLdrList = new ArrayList<Long>();
    		if(bksOfLeaderInConsti!=null && bksOfLeaderInConsti.size()>0){
    			bksOfLdrList = bksOfLeaderInConsti;
    		}
    		
    		booksNotAssgned.addAll(bksOfLeaderInConstincy);
    		
    		if(booksNotAssgned !=null && booksNotAssgned.size()>0){
    				List<HouseHoldsVO> booksList = new ArrayList<HouseHoldsVO>();
    			for(Object[] obj:booksNotAssgned){
    				HouseHoldsVO hvo = new HouseHoldsVO();
    				
    				hvo.setBookNo(Long.valueOf(obj[1].toString()));
					hvo.setBookId(Long.valueOf(obj[0].toString()));
					
    				
    				if(bksOfLdrList!=null && bksOfLdrList.size()>0){
    					
    					if(bksOfLdrList.contains(Long.valueOf(obj[0].toString()))){
    						hvo.setEnableBook(true);
    						
    						if(hseHldsUnderLdr!=null && hseHldsUnderLdr.size()>0){
    							if(hseHldsUnderLdr.contains(Long.valueOf(obj[0].toString()))){
    								hvo.setDisableBook(true);
    							}else{
    								hvo.setDisableBook(false);
    							}
    						}else{
    							hvo.setDisableBook(false);
    						}
    						
    					}else{
    						hvo.setEnableBook(false);
    						hvo.setDisableBook(false);
    					}
    				}
    				
    				
    				booksList.add(hvo);
    			}
    			
    			hv.setBooksInConstituency(booksList);
    		}
    		
    	}else if(task.equalsIgnoreCase("checkBook")){
    		List<Object[]> booksOfConstituency = hhLeaderBooksDAO.getAllBooksInConstituencyWithBookNo(constituencyId,Long.parseLong(bookNo));
    		List<Object[]> booksNotAssgned = hhLeaderBooksDAO.getAllBooksNotAssignedWithBookNo(Long.parseLong(bookNo));
    		
    		if(booksOfConstituency != null && booksOfConstituency.size()>0 || booksNotAssgned !=null && booksNotAssgned.size()>0){
    			hv.setStatusOfBook("exist");
    		}else{
    			hv.setStatusOfBook("notExist");
    		}
    		
    	}else if(task.equalsIgnoreCase("leadersOfConstituency")){
    		List<Object[]> leadersOfConsti = hhBoothLeaderDAO.getLeadersOfConstituency(constituencyId);
    		List<HouseHoldsVO> leadersInConstiList = new ArrayList<HouseHoldsVO>();
    		
    		if(leadersOfConsti!=null && leadersOfConsti.size()>0){
    			for(Object[] obj:leadersOfConsti){
    				HouseHoldsVO hvo = new HouseHoldsVO();
    				
    				hvo.setLeaderId(Long.valueOf(obj[0].toString()));
    				hvo.setLeaderName(obj[1].toString());
    				hvo.setVoterId(obj[2].toString());
    				
    				leadersInConstiList.add(hvo);
    				
    			}
    			
    			hv.setLeadersOfConsti(leadersInConstiList);
    		}
    		
    	}else if(task.equalsIgnoreCase("assignSelectedBooths")){
    		Object stat = transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					
					List<Object[]> boothsOfLeader = hhBoothLeaderDAO.getBoothsOfLeader(leaderId);
					
					List<Long> bthsOfLdrList = new ArrayList<Long>();
		    		if(boothsOfLeader!=null && boothsOfLeader.size()>0){
		    			for(Object[] obj:boothsOfLeader){
		    				bthsOfLdrList.add(Long.valueOf(obj[0].toString()));
		    			}
		    		}
		    		
		    		Collection<Long> similar = new HashSet<Long>( bthsOfLdrList );
		            Collection<Long> different = new HashSet<Long>();
		            different.addAll( boothOrBooks );
		            different.addAll( bthsOfLdrList );
		            
		            similar.retainAll( bthsOfLdrList );
		            different.removeAll( similar );
		            
		            List<Long> toRemove = new ArrayList<Long>();
		            
		            
		            if(bthsOfLdrList!=null && bthsOfLdrList.size()>0){
		            	for(Long booth:bthsOfLdrList){
		            		if(!boothOrBooks.contains(booth)){
		            			toRemove.add(booth);
		            		}
		            	}
		            }
		          
		            different.removeAll(toRemove);
		            
		            int deletdCount = 0;
		            if(toRemove!=null && toRemove.size()>0){
		            	deletdCount = hhBoothLeaderDAO.deleteLeaderWithBooths(toRemove,leaderId);
					}
		            
		            if(different!=null && different.size()>0){
    					for(Long boothId:different){
							HHBoothLeader hhBoothLeader = new HHBoothLeader();							
							hhBoothLeader.setConstituencyId(constituencyId);
							hhBoothLeader.setBoothId(boothId);
							hhBoothLeader.setHhLeaderId(leaderId);
							hhBoothLeader = hhBoothLeaderDAO.save(hhBoothLeader);
    					}
					}
				}});
    		
    		if(stat != null){
    			hv.setStatusOfBook("success");
    		}
    		
    		
    	}else if(task.equalsIgnoreCase("assignSelectedBooks")){
    		Object stat = transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					
				List<Long> bksOfLeaderInConsti = hhLeaderBooksDAO.getAllLeaderBooksInConstituency(leaderId,constituencyId);
				
				Collection<Long> similar = new HashSet<Long>( bksOfLeaderInConsti );
	            Collection<Long> different = new HashSet<Long>();
	            different.addAll( boothOrBooks );
	            different.addAll( bksOfLeaderInConsti );
	            
	            similar.retainAll( bksOfLeaderInConsti );
	            different.removeAll( similar );
	            
	            List<Long> toRemove = new ArrayList<Long>();
	            
	            List<Long> bksNotAssgnd = new ArrayList<Long>();
	            List<Object[]> booksNotAssgned = hhLeaderBooksDAO.getAllBooksNotAssigned();
	            if(booksNotAssgned !=null && booksNotAssgned.size()>0){
	            	for(Object[] obj:booksNotAssgned){
	            		bksNotAssgnd.add(Long.valueOf(obj[0].toString()));
	            	}
	            }
	            
	            
	            
	            
	            
	            if(bksOfLeaderInConsti!=null && bksOfLeaderInConsti.size()>0){
	            	for(Long booth:bksOfLeaderInConsti){
	            		if(!boothOrBooks.contains(booth)){
	            			toRemove.add(booth);
	            		}
	            	}
	            }
	          
	            different.removeAll(toRemove);
	            
	            List<Long> diffList = new ArrayList<Long>();
	            diffList.addAll(different);
	            
	            List<Object[]> list = null;
	            if(diffList!=null && diffList.size()>0){
	             list = hhLeaderBooksDAO.getAllLeaderBooksInConstituencyWithNoOfBookIds(leaderId, constituencyId,diffList);
	            }
	            Map<Long,Long> bookMap = new HashMap<Long, Long>();
	            if(list!=null && list.size()>0){
	            	for(Object[] obj:list){
	            		bookMap.put(Long.valueOf(obj[0].toString()), Long.valueOf(obj[1].toString()));
	            	}
	            }
	            
	            
	            int delCount =0;
	            
	            if(toRemove!=null && toRemove.size()>0){
	            	delCount = hhLeaderBooksDAO.deleteLeaderWithBooks(toRemove, constituencyId);
	            }
					
    			if(different!=null && different.size()>0){
        			for(Long book:different){
        				
        				if(bksNotAssgnd!=null && bksNotAssgnd.size()>0){
        					if(bksNotAssgnd.contains(book)){
        						int count = hhLeaderBooksDAO.updateLeaderForBooks(book, leaderId);
        					}else{
        						HHLeaderBooks hhLeaderBooks = new HHLeaderBooks();							
            					hhLeaderBooks.setLeaderId(leaderId);
            					hhLeaderBooks.setBookNo(bookMap.get(book));
            					hhLeaderBooks = hhLeaderBooksDAO.save(hhLeaderBooks);
        					}
        				}else{
        					HHLeaderBooks hhLeaderBooks = new HHLeaderBooks();							
        					hhLeaderBooks.setLeaderId(leaderId);
        					hhLeaderBooks.setBookNo(bookMap.get(book));
        					hhLeaderBooks = hhLeaderBooksDAO.save(hhLeaderBooks);
        				}
        			}
        		}
			}});
    		if(stat != null){
    			hv.setStatusOfBook("success");
    		}
    	}
    	
    	else if(task.equalsIgnoreCase("addGivenBookNo")){
    		
    		List<Object[]> booksOfConstituency = hhLeaderBooksDAO.getAllBooksInConstituencyWithBookNo(constituencyId,Long.parseLong(bookNo));
    		List<Object[]> booksNotAssgned = hhLeaderBooksDAO.getAllBooksNotAssignedWithBookNo(Long.parseLong(bookNo));
    		boolean bookExist = false;
    		
    		if(booksOfConstituency != null && booksOfConstituency.size()>0 || booksNotAssgned !=null && booksNotAssgned.size()>0){
    			bookExist = true;
    		}else{
    			hv.setStatusOfBook("failed");
    		}
    		
    		if(!bookExist){
    		HHLeaderBooks hhLeaderBooks = new HHLeaderBooks();							
			hhLeaderBooks.setBookNo(Long.parseLong(bookNo));
			hhLeaderBooks = hhLeaderBooksDAO.save(hhLeaderBooks);
			
				if(hhLeaderBooks!=null){
					hv.setStatusOfBook("success");
				}else{
					hv.setStatusOfBook("failed");
				}
    		}
    	}else if(task.equalsIgnoreCase("getBooksOfLeader")){
    		List<Object[]> bookList = hhLeaderBooksDAO.getBooksOfLeader(leaderId);
    		List<HouseHoldsVO> booksList = new ArrayList<HouseHoldsVO>();
    		
    		if(bookList!=null && bookList.size()>0){
    				for(Object[] ob:bookList){
    	    			HouseHoldsVO hsv = new HouseHoldsVO();
    	    			hsv.setBookId(Long.valueOf(ob[0].toString()));
    	    			hsv.setBookNo(Long.valueOf(ob[1].toString()));
    	    			booksList.add(hsv);
    	    		}
    	    	}
    		
    		hv.setBooksInConstituency(booksList);
    	    	
    		}
    	}catch (Exception e) {
    		Log.error("Exception Raised in AssignBoothsOrBooks Method " + e);
    		
		}
    	
    	return hv;
    }
    
    public List<GenericVO> getHouseHoldConstituencies(){
    	List<GenericVO> gvList = new ArrayList<GenericVO>();
    	
    	//List<Object[]> list = hhBoothLeaderDAO.getConstituenciesOfHouseHolds();
    	List<Object[]> list = houseHoldVoterDAO.getConstituenciesOfHouseHolds();
    	
    	if(list!=null && list.size()>0){
    		for(Object[] obj:list){
    			GenericVO gv = new GenericVO();
    			gv.setId(Long.valueOf(obj[0].toString()));
    			gv.setName(obj[1].toString());
    			
    			gvList.add(gv);
    		}
    	}
    	gvList.add(0,new GenericVO(0l, "Select"));
    	return gvList;
    }
    
    public HouseHoldsSummaryReportVO getReportsOfHouseHolds(HouseHoldsSummaryReportVO inputVO){
    	HouseHoldsSummaryReportVO finalVO = new HouseHoldsSummaryReportVO();
    	String task = inputVO.getTask();
    	
    	if(task.equalsIgnoreCase("constituencySummary")){
    		Long constituencyId = inputVO.getConstituencyId();
        	
        	List<Object[]> hhCntOfConsti = houseHoldVoterDAO.getHouseHoldsCountInConstituency1(constituencyId);
        	
        	List<Object[]> pnchytList = houseHoldVoterDAO.getAllPanchayatsInHouseHoldsOfConstituency(constituencyId);
        	
        	List<Object[]> pnchytSmryList = houseHoldVoterDAO.getAllLeadersBooksFamilies(constituencyId);
        	
        	List<Object[]> leadersCount = houseHoldVoterDAO.getActiveLeadersOfConstituency(constituencyId);
        	
        	List<Object[]> vtrsList = houseHoldVoterDAO.getVoterAndNonVoterCountInConstituency(constituencyId);
        	List<Object[]> nonVtrsList = houseHoldVoterDAO.getVoterAndNonVoterCountInConstituency1(constituencyId);
        	
        	if(vtrsList!=null && vtrsList.size()>0){
        		for(Object[] obj:vtrsList){
        			finalVO.setConstiVotersCount(Long.valueOf(obj[2].toString()));
        		}
        	}
        	
        	if(nonVtrsList!=null && nonVtrsList.size()>0){
        		for(Object[] obj:nonVtrsList){
        			finalVO.setConstiNonVotersCount(Long.valueOf(obj[2].toString()));
        		}
        	}
        	
        	List<HouseHoldsSummaryReportVO> panchayatNamesList = new ArrayList<HouseHoldsSummaryReportVO>();
        	List<HouseHoldsSummaryReportVO> pnchytSummryList = new ArrayList<HouseHoldsSummaryReportVO>();
        	
        	if(hhCntOfConsti!=null && hhCntOfConsti.size()>0){
        		for(Object[] obj:hhCntOfConsti){
        			finalVO.setConstituencyId(Long.valueOf(obj[0].toString()));
        			finalVO.setConstituency(obj[1].toString());
        			finalVO.setConstiHouseHoldsCount(Long.valueOf(obj[2].toString()));
        			finalVO.setActiveLeadersCount(leadersCount.size());
        		}
        	}
        	
        	if(pnchytList !=null && pnchytList.size()>0){
        		for(Object[] obj:pnchytList){
        			HouseHoldsSummaryReportVO tempVO = new HouseHoldsSummaryReportVO();
        			tempVO.setPanchayatId(Long.valueOf(obj[0].toString()));
        			tempVO.setPanchayatName(obj[1].toString());
        			
        			panchayatNamesList.add(tempVO);
        		}
        		finalVO.setPanchayatNamesList(panchayatNamesList);
        	}
        	
        	if(pnchytSmryList!=null && pnchytSmryList.size()>0){
        		for(Object[] obj:pnchytSmryList){
        			HouseHoldsSummaryReportVO tempVO = new HouseHoldsSummaryReportVO();
        			tempVO.setPanchayatId(Long.valueOf(obj[0].toString()));
        			tempVO.setPanchayatName(obj[1].toString());
        			tempVO.setHouseHoldsCount(Long.valueOf(obj[2].toString()));
        			tempVO.setLeadersCount(Long.valueOf(obj[3].toString()));
        			
        			pnchytSummryList.add(tempVO);
        		}
        		finalVO.setPanchayatList(pnchytSummryList);
        	}
        	
        	
        	
        	
    	}else if(task.equalsIgnoreCase("leaderOfPanchayat")){
    		Long panchayatId = inputVO.getPanchayatId();
    		
    		List<HouseHoldsSummaryReportVO> leadersInPnchyt = new ArrayList<HouseHoldsSummaryReportVO>();
    		
    		if(panchayatId!=null){
    			List<Object[]> list = houseHoldVoterDAO.getLeadersAndCountInLocality(panchayatId);
    			if(list!=null && list.size()>0){
    				for(Object[] obj:list){
    					HouseHoldsSummaryReportVO tempVO = new HouseHoldsSummaryReportVO();
    					tempVO.setHouseHoldsCount(Long.valueOf(obj[2].toString()));
    					tempVO.setLeaderId(Long.valueOf(obj[3].toString()));
    					tempVO.setVoterName(obj[4].toString());
    					tempVO.setVoterCardNo(obj[5].toString());
    					tempVO.setMobileNo(obj[6].toString());
    					
    					finalVO.setPanchayatId(Long.valueOf(obj[0].toString()));
    					finalVO.setPanchayatName(obj[1].toString());
    					leadersInPnchyt.add(tempVO);
    				}
    			}
    		}
    		
    		finalVO.setLeadersOfPnchyt(leadersInPnchyt);
    		
    	}else if(task.equalsIgnoreCase("familyHeadsUnderLeader")){
    		Long leaderId = inputVO.getLeaderId();
    		
    		List<HouseHoldsSummaryReportVO> leadersInPnchyt = new ArrayList<HouseHoldsSummaryReportVO>();
    		
    		if(leaderId!=null){
    			
    			Map<Long,HouseHoldsSummaryReportVO> hhCountMap = new HashMap<Long, HouseHoldsSummaryReportVO>();
    		/*	List<Object[]> hhList = houseHoldVoterDAO.getFamilyAndVotersCountInHouseHoldsNew(leaderId, 2);
    			if(hhList!=null && hhList.size()>0){
    				for(Object[] obj:hhList){
    					HouseHoldsSummaryReportVO tempVO = new HouseHoldsSummaryReportVO();
    					tempVO.setHouseHoldId(Long.valueOf(obj[0].toString()));
    					tempVO.setVotersCount(Long.valueOf(obj[1].toString()));
    					tempVO.setNonVotersCount(Long.valueOf(obj[2].toString()));
    					
    					hhCountMap.put(Long.valueOf(obj[0].toString()), tempVO);
    					
    				}
    			}*/
    			
    			List<Object[]> list1 = houseHoldVoterDAO.getFamilyAndVotersCountInHouseHoldsNew(leaderId, 2);
    			
    			
    			List<HHReportVO> hhList = new ArrayList<HHReportVO>();
    			if(list1!=null && list1.size()>0){
    				for(Object[] obj:list1){
    					HHReportVO hv = getMatchedHouseHold(hhList, Long.valueOf(obj[1].toString()));
    					boolean newlyAdded = false;
    					if(hv==null){
    						hv = new HHReportVO();
    						hv.setVotersCount(0l);
    						hv.setNonVotersCount(0l);
    						
    						newlyAdded = true;
    					}
    					
    					hv.setHouseHoldId(Long.valueOf(obj[1].toString()));
    					if(obj[2]!=null){
    						hv.setVotersCount(hv.getVotersCount()+1);
    					}
    					if(obj[3]!=null){
    						hv.setNonVotersCount(hv.getNonVotersCount()+1);
    					}
    					
    					if(Long.valueOf(obj[4].toString())==1){
    						hv.setRelationShipId(1l);
    					}
    					
    					if(newlyAdded){
    						hhList.add(hv);		
    					}
    					
    				}
    			}
    			
    			List<HHReportVO> finalList = new ArrayList<HHReportVO>();
    			if(hhList!=null && hhList.size()>0){
    				for(HHReportVO hv:hhList){
    					if(hv.getRelationShipId()!=null){
    						finalList.add(hv);
    						
    						HouseHoldsSummaryReportVO tempVO = new HouseHoldsSummaryReportVO();
    						tempVO.setHouseHoldId(hv.getHouseHoldId());
    						tempVO.setVotersCount(hv.getVotersCount());
    						tempVO.setNonVotersCount(hv.getNonVotersCount());
    						
    						hhCountMap.put(hv.getHouseHoldId(), tempVO);
    					}
    				}
    			}
    			
    			
    			List<Object[]> list = houseHoldVoterDAO.getFamilyHeadsUnderLeader(leaderId);
    			if(list!=null && list.size()>0){
    				for(Object[] obj:list){
    					HouseHoldsSummaryReportVO tempVO = new HouseHoldsSummaryReportVO();
    					tempVO.setHouseHoldId(Long.valueOf(obj[1].toString()));
    					tempVO.setHouseNo(obj[2].toString());
    					tempVO.setVoterName(obj[3].toString());
    					tempVO.setVoterCardNo(obj[4].toString());
    					
    					finalVO.setLeaderId(Long.valueOf(obj[7].toString()));
    					finalVO.setLeaderName(obj[8].toString());
    					
    					if(hhCountMap.get(Long.valueOf(obj[1].toString()))!=null){
    						HouseHoldsSummaryReportVO hhVO = hhCountMap.get(Long.valueOf(obj[1].toString()));
    						tempVO.setVotersCount(hhVO.getVotersCount());
    						tempVO.setNonVotersCount(hhVO.getNonVotersCount());
    					}
    					
    					leadersInPnchyt.add(tempVO);
    				}
    			}
    		}
    		
    		finalVO.setLeadersOfPnchyt(leadersInPnchyt);
    		
    	}else if(task.equalsIgnoreCase("familyHeadsUnderPanchayat")){
    		Long panchayatId = inputVO.getPanchayatId();
    		
    		List<HouseHoldsSummaryReportVO> leadersInPnchyt = new ArrayList<HouseHoldsSummaryReportVO>();
    		
    		if(panchayatId!=null){
    			
    			Map<Long,HouseHoldsSummaryReportVO> hhCountMap = new HashMap<Long, HouseHoldsSummaryReportVO>();
    			/*List<Object[]> hhList = houseHoldVoterDAO.getFamilyAndVotersCountInHouseHolds(panchayatId, 1);
    			if(hhList!=null && hhList.size()>0){
    				for(Object[] obj:hhList){
    					HouseHoldsSummaryReportVO tempVO = new HouseHoldsSummaryReportVO();
    					tempVO.setHouseHoldId(Long.valueOf(obj[0].toString()));
    					tempVO.setVotersCount(Long.valueOf(obj[1].toString()));
    					tempVO.setNonVotersCount(Long.valueOf(obj[2].toString()));
    					
    					hhCountMap.put(Long.valueOf(obj[0].toString()), tempVO);
    					
    				}
    			}*/
    			List<Object[]> list1 = houseHoldVoterDAO.getFamilyAndVotersCountInHouseHoldsNew(panchayatId, 1);
    			
    			
    			List<HHReportVO> hhList = new ArrayList<HHReportVO>();
    			if(list1!=null && list1.size()>0){
    				for(Object[] obj:list1){
    					HHReportVO hv = getMatchedHouseHold(hhList, Long.valueOf(obj[1].toString()));
    					boolean newlyAdded = false;
    					if(hv==null){
    						hv = new HHReportVO();
    						hv.setVotersCount(0l);
    						hv.setNonVotersCount(0l);
    						
    						newlyAdded = true;
    					}
    					
    					hv.setHouseHoldId(Long.valueOf(obj[1].toString()));
    					if(obj[2]!=null){
    						hv.setVotersCount(hv.getVotersCount()+1);
    					}
    					if(obj[3]!=null){
    						hv.setNonVotersCount(hv.getNonVotersCount()+1);
    					}
    					
    					if(Long.valueOf(obj[4].toString())==1){
    						hv.setRelationShipId(1l);
    					}
    					
    					if(newlyAdded){
    						hhList.add(hv);		
    					}
    					
    				}
    			}
    			
    			List<HHReportVO> finalList = new ArrayList<HHReportVO>();
    			if(hhList!=null && hhList.size()>0){
    				for(HHReportVO hv:hhList){
    					if(hv.getRelationShipId()!=null){
    						finalList.add(hv);
    						
    						HouseHoldsSummaryReportVO tempVO = new HouseHoldsSummaryReportVO();
    						tempVO.setHouseHoldId(hv.getHouseHoldId());
    						tempVO.setVotersCount(hv.getVotersCount());
    						tempVO.setNonVotersCount(hv.getNonVotersCount());
    						
    						hhCountMap.put(hv.getHouseHoldId(), tempVO);
    					}
    				}
    			}
    			
    			List<Object[]> list = houseHoldVoterDAO.getFamilyHeadsInPanchayat(panchayatId);
    			if(list!=null && list.size()>0){
    				for(Object[] obj:list){
    					HouseHoldsSummaryReportVO tempVO = new HouseHoldsSummaryReportVO();
    					tempVO.setHouseHoldId(Long.valueOf(obj[1].toString()));
    					tempVO.setHouseNo(obj[2].toString());
    					tempVO.setVoterName(obj[3].toString());
    					tempVO.setVoterCardNo(obj[4].toString());
    					
    					finalVO.setPanchayatId(Long.valueOf(obj[5].toString()));
    					finalVO.setPanchayatName(obj[6].toString());
    					
    					if(hhCountMap.get(Long.valueOf(obj[1].toString()))!=null){
    						HouseHoldsSummaryReportVO hhVO = hhCountMap.get(Long.valueOf(obj[1].toString()));
    						tempVO.setVotersCount(hhVO.getVotersCount());
    						tempVO.setNonVotersCount(hhVO.getNonVotersCount());
    					}
    					
    					leadersInPnchyt.add(tempVO);
    				}
    			}
    		}
    		
    		finalVO.setLeadersOfPnchyt(leadersInPnchyt);
    	}else if(task.equalsIgnoreCase("familyHeadsUnderBook")){
    		Long bookId = inputVO.getBookId();
    		
    		List<HouseHoldsSummaryReportVO> familyHeadsUnderBook = new ArrayList<HouseHoldsSummaryReportVO>();
    		
    		if(bookId!=null){
    			
    			Map<Long,HouseHoldsSummaryReportVO> hhCountMap = new HashMap<Long, HouseHoldsSummaryReportVO>();
    		/*	List<Object[]> hhList = houseHoldVoterDAO.getFamilyAndVotersCountInHouseHolds(bookId, 3);
    			if(hhList!=null && hhList.size()>0){
    				for(Object[] obj:hhList){
    					HouseHoldsSummaryReportVO tempVO = new HouseHoldsSummaryReportVO();
    					tempVO.setHouseHoldId(Long.valueOf(obj[0].toString()));
    					tempVO.setVotersCount(Long.valueOf(obj[1].toString()));
    					tempVO.setNonVotersCount(Long.valueOf(obj[2].toString()));
    					
    					hhCountMap.put(Long.valueOf(obj[0].toString()), tempVO);
    					
    				}
    			}
    			*/
    			List<Object[]> list1 = houseHoldVoterDAO.getFamilyAndVotersCountInHouseHoldsNew(bookId, 3);
    			
    			
    			List<HHReportVO> hhList = new ArrayList<HHReportVO>();
    			if(list1!=null && list1.size()>0){
    				for(Object[] obj:list1){
    					HHReportVO hv = getMatchedHouseHold(hhList, Long.valueOf(obj[1].toString()));
    					boolean newlyAdded = false;
    					if(hv==null){
    						hv = new HHReportVO();
    						hv.setVotersCount(0l);
    						hv.setNonVotersCount(0l);
    						
    						newlyAdded = true;
    					}
    					
    					hv.setHouseHoldId(Long.valueOf(obj[1].toString()));
    					if(obj[2]!=null){
    						hv.setVotersCount(hv.getVotersCount()+1);
    					}
    					if(obj[3]!=null){
    						hv.setNonVotersCount(hv.getNonVotersCount()+1);
    					}
    					
    					if(Long.valueOf(obj[4].toString())==1){
    						hv.setRelationShipId(1l);
    					}
    					
    					if(newlyAdded){
    						hhList.add(hv);		
    					}
    					
    				}
    			}
    			
    			List<HHReportVO> finalList = new ArrayList<HHReportVO>();
    			if(hhList!=null && hhList.size()>0){
    				for(HHReportVO hv:hhList){
    					if(hv.getRelationShipId()!=null){
    						finalList.add(hv);
    						
    						HouseHoldsSummaryReportVO tempVO = new HouseHoldsSummaryReportVO();
    						tempVO.setHouseHoldId(hv.getHouseHoldId());
    						tempVO.setVotersCount(hv.getVotersCount());
    						tempVO.setNonVotersCount(hv.getNonVotersCount());
    						
    						hhCountMap.put(hv.getHouseHoldId(), tempVO);
    					}
    				}
    			}
    			
    			List<Object[]> list = houseHoldVoterDAO.getFamilyHeadsUnderBook(bookId);
    			if(list!=null && list.size()>0){
    				for(Object[] obj:list){
    					HouseHoldsSummaryReportVO tempVO = new HouseHoldsSummaryReportVO();
    					tempVO.setHouseHoldId(Long.valueOf(obj[1].toString()));
    					tempVO.setHouseNo(obj[2].toString());
    					tempVO.setVoterName(obj[3].toString());
    					tempVO.setVoterCardNo(obj[4].toString());
    					
    					finalVO.setPanchayatId(Long.valueOf(obj[5].toString()));
    					finalVO.setPanchayatName(obj[6].toString());
    					
    					if(hhCountMap.get(Long.valueOf(obj[1].toString()))!=null){
    						HouseHoldsSummaryReportVO hhVO = hhCountMap.get(Long.valueOf(obj[1].toString()));
    						tempVO.setVotersCount(hhVO.getVotersCount());
    						tempVO.setNonVotersCount(hhVO.getNonVotersCount());
    					}
    					
    					familyHeadsUnderBook.add(tempVO);
    				}
    			}
    		}
    		
    		finalVO.setFamilyHeadsUnderBook(familyHeadsUnderBook);
    	}else if(task.equalsIgnoreCase("familyHeadsUnderOptions")){
    		Long optionId = inputVO.getOptionId();
    		Long panchayatId = inputVO.getPanchayatId();
    		
    		List<HouseHoldsSummaryReportVO> familyHeadsUnderOptn = new ArrayList<HouseHoldsSummaryReportVO>();
    		
    		if(optionId!=null && panchayatId!=null){
    			
    			Map<Long,HouseHoldsSummaryReportVO> hhCountMap = new HashMap<Long, HouseHoldsSummaryReportVO>();
    			List<Object[]> hhList = hhSurveyAnswersDAO.getVoterAndNonVotersUnderOption(optionId, panchayatId);
    			List<Object[]> hhList1 = hhSurveyAnswersDAO.getVoterAndNonVotersUnderOption1(optionId, panchayatId);
    			
    			if(hhList!=null && hhList.size()>0){
    				for(Object[] obj:hhList){
    					HouseHoldsSummaryReportVO tempVO = new HouseHoldsSummaryReportVO();
    					tempVO.setHouseHoldId(Long.valueOf(obj[0].toString()));
    					tempVO.setVotersCount(Long.valueOf(obj[1].toString()));
    					//tempVO.setNonVotersCount(Long.valueOf(obj[2].toString()));
    					
    					hhCountMap.put(Long.valueOf(obj[0].toString()), tempVO);
    					
    				}
    			}
    			
    			if(hhList1!=null && hhList1.size()>0){    				
    				for(Object[] obj:hhList1){  					
    					HouseHoldsSummaryReportVO tempVO = hhCountMap.get(Long.valueOf(obj[0].toString()));
    					if(tempVO!=null){
    						tempVO.setNonVotersCount(Long.valueOf(obj[1].toString()));
    					}
    				}
    			}
    			List<Object[]> list = hhSurveyAnswersDAO.getHouseHoldsOfPanchayatWithOption(optionId,panchayatId);
    			if(list!=null && list.size()>0){
    				for(Object[] obj:list){
    					HouseHoldsSummaryReportVO tempVO = new HouseHoldsSummaryReportVO();
    					tempVO.setHouseHoldId(Long.valueOf(obj[1].toString()));
    					tempVO.setHouseNo(obj[2].toString());
    					tempVO.setVoterName(obj[3].toString());
    					tempVO.setVoterCardNo(obj[4].toString());
    					
    					finalVO.setPanchayatId(Long.valueOf(obj[5].toString()));
    					finalVO.setPanchayatName(obj[6].toString());
    					finalVO.setOption(StringEscapeUtils.unescapeJava(obj[8].toString()));
    					
    					if(hhCountMap.get(Long.valueOf(obj[1].toString()))!=null){
    						HouseHoldsSummaryReportVO hhVO = hhCountMap.get(Long.valueOf(obj[1].toString()));
    						tempVO.setVotersCount(hhVO.getVotersCount());
    						tempVO.setNonVotersCount(hhVO.getNonVotersCount());
    					}
    					
    					familyHeadsUnderOptn.add(tempVO);
    				}
    			}
    		}
    		
    		finalVO.setFamilyHeadsUnderOption(familyHeadsUnderOptn);
    	}
    	
    	
    	
    	//List<Object[]> list = houseHoldVoterDAO.
    	
    	/*//List<Object[]> summaryList = houseHoldVoterDAO.getAllLeadersBooksFamilies();
    	
    	List<Object[]> panchayatWiseFamilies = houseHoldVoterDAO.getFamilyHeadsInPanchayat(3361l);
    	
    	List<Object[]> leadersInLocation = houseHoldVoterDAO.getLeadersAndCountInLocality(null);*/
    	
    	return finalVO;
    }
    
    
    /*	@param1 surveyId
     *  @return this method returns List<HHQuestionSummaryReportVO> 
     *     which includes question's of Survey except Questions having answer type Text
     * 	@author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
     * 	@since July 1st 2014
     * */
    public List<HHQuestionSummaryReportVO> getQuestionsOfSurvey(Long surveyId){
    	Log.debug("Entered Into HouseHoldsSurveyReportService getQuestionsOfSurvey() ");
    	List<HHQuestionSummaryReportVO> questionsList= new ArrayList<HHQuestionSummaryReportVO>();
    	try{
    	
    		List<Object[]> list = hhSurveyQuestionDAO.getAllQuestionInSurvey(surveyId);
    		if(list!=null && list.size()>0){
    			for(Object[] obj:list){
    				if(Long.valueOf(obj[2].toString()) != 3){ // As 3 is the id of Text Type Answer Question
    					HHQuestionSummaryReportVO temp = new HHQuestionSummaryReportVO();
    					temp.setQuestion(StringEscapeUtils.unescapeJava(obj[1].toString()));
    					temp.setQuestionId(Long.valueOf(obj[0].toString()));
    					
    					questionsList.add(temp);
    				}
    			}
    		}
    	}catch (Exception e) {
    		Log.error("Exception In HouseHoldsSurveyReportService getQuestionsOfSurvey() " + e);
		}
    	return questionsList;
    }
    
    
    /*	@param1 questionId
     *  @param2 constituencyId
     *  @return this method returns List<HHQuestionSummaryReportVO> 
     *     which includes Options summary for that Question
     * 	@author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
     * 	@since July 1st 2014
     * */
    public HHQuestionSummaryReportVO getOptionsCountForQuestion(Long questionId,Long constituencyId){
    	Log.debug("Entered Into HouseHoldsSurveyReportService getOptionsCountForQuestion() ");
    	
    	HHQuestionSummaryReportVO finalVO = new HHQuestionSummaryReportVO();
    	
    	
    	try{
    		
    		List<HHQuestionSummaryReportVO> optionsList= new ArrayList<HHQuestionSummaryReportVO>();
    		//List<Object[]> list = hhSurveyAnswersDAO.getQuestionWiseSummaryCount(questionId, constituencyId);
    		List<Object[]> optsList = hhQuestionOptionsDAO.getOptionsForQuestions(questionId);
    		
    		/*Map<Long,Long> optsCountMap = new HashMap<Long, Long>();
    		
    		Long totalCount = 0l;
    		if(list!=null && list.size()>0){
    			for(Object[] obj:list){
    				optsCountMap.put(Long.valueOf(obj[1].toString()),Long.valueOf(obj[0].toString()));
    				totalCount = totalCount + Long.valueOf(obj[0].toString());
    			}
    			
    			finalVO.setTotalCount(totalCount);
    		}
    	
    		if(optsList!=null && optsList.size()>0){
    			
    			finalVO.setQuestion(StringEscapeUtils.unescapeJava(list.get(0)[4].toString()));
    			finalVO.setQuestionId(Long.valueOf(list.get(0)[3].toString()));
    			
    			for(Object[] obj:optsList){
    				HHQuestionSummaryReportVO opts = new HHQuestionSummaryReportVO();
    				opts.setQuestionId(Long.valueOf(obj[3].toString()));
    				opts.setQuestion(obj[4].toString());
    				opts.setOption(StringEscapeUtils.unescapeJava(obj[1].toString()));
    				opts.setOptionId(Long.valueOf(obj[0].toString()));
    				
    				if(optsCountMap.get(Long.valueOf(obj[0].toString()))!=null){
    					opts.setOptsCount(optsCountMap.get(Long.valueOf(obj[0].toString())));
    				}else{
    					opts.setOptsCount(0l);
    				}
    				
    				opts.setPercentage(calcPercentage(totalCount,opts.getOptsCount()));
    				
    				optionsList.add(opts);
    			}
    		}*/
    		
    		
    		List<Object[]> pancList= hhSurveyAnswersDAO.getQuestionWiseSummaryCountByPanchayat(questionId, constituencyId);
    		List<HHQuestionSummaryReportVO> panchayatList = new ArrayList<HHQuestionSummaryReportVO>();
    		if(pancList!=null && pancList.size()>0){
    			for(Object[] obj:pancList){
    				HHQuestionSummaryReportVO panchayatVO= getMatchedPanchayat(panchayatList,Long.valueOf(obj[5].toString()));
    				if(panchayatVO==null){
	    				HHQuestionSummaryReportVO temp = new HHQuestionSummaryReportVO();
	    				temp.setPanchayatId(Long.valueOf(obj[5].toString()));
	    				temp.setPanchayat(obj[6].toString());
	    				temp.setOptionsList(getOptionVO(optsList));
	    				panchayatList.add(temp);
    				}
    			}
    		}
    		
    		if(pancList!=null && pancList.size()>0){
    			for(Object[] obj:pancList){
    				Long pancId = Long.valueOf(obj[5].toString());
    				HHQuestionSummaryReportVO panchayatVO= getMatchedPanchayat(panchayatList,pancId);
    				
    				Long optnId = Long.valueOf(obj[1].toString());
    				HHQuestionSummaryReportVO optionVO = getMatchedOption(panchayatVO.getOptionsList(),optnId);
    				optionVO.setOptsCount(Long.valueOf(obj[0].toString()));
    				
    				Long ttl = panchayatVO.getTotalCount();
    				if(ttl ==null){
    					ttl = 0l;
    				}
    				panchayatVO.setTotalCount(ttl+Long.valueOf(obj[0].toString()));
    				
    			}
    		}
    		
    		finalVO.setPanchayatList(panchayatList);
    		
    		if(optsList!=null && optsList.size()>0){   			
    			for(Object[] obj:optsList){
    				HHQuestionSummaryReportVO opts = new HHQuestionSummaryReportVO();
    				opts.setQuestionId(Long.valueOf(obj[3].toString()));
    				opts.setQuestion(StringEscapeUtils.unescapeJava(obj[4].toString()));
    				opts.setOption(StringEscapeUtils.unescapeJava(obj[1].toString()));
    				opts.setOptionId(Long.valueOf(obj[0].toString()));
    				opts.setOptsCount(0l);
    				optionsList.add(opts);
    			}
    			finalVO.setOptionsList(optionsList);
    		}
    		
    		List<HHQuestionSummaryReportVO> optnsresult = finalVO.getPanchayatList();
    		for(HHQuestionSummaryReportVO vo:optnsresult){
    				List<HHQuestionSummaryReportVO> optnList = vo.getOptionsList();
    				if(optnList !=  null){
	    				for(HHQuestionSummaryReportVO vo1 : optnList){
	    					HHQuestionSummaryReportVO options = getMatchedOption(optionsList,vo1.getOptionId());
	    					if(options != null){
	    						options.setOptsCount(options.getOptsCount() + (vo1.getOptsCount()!=null?vo1.getOptsCount():0L));
	    					}
	    				}
    				}
				}
    		    		
    	}catch (Exception e) {
    		Log.error("Exception In HouseHoldsSurveyReportService getOptionsCountForQuestion() " + e);
		}
    	return finalVO;
    }
    public HHQuestionSummaryReportVO getMatchedPanchayat(List<HHQuestionSummaryReportVO> panList,Long panId){
    	if(panList!=null && panList.size()>0 && panId !=null){
    		for(HHQuestionSummaryReportVO temp:panList){
    			if(temp.getPanchayatId().equals(panId)){
    				return temp;
    			}
    		}
    	} 
    	return null;
    }
    
    public HHQuestionSummaryReportVO getMatchedOption(List<HHQuestionSummaryReportVO> optnList,Long optnId){
    	if(optnList!=null && optnList.size()>0 && optnId !=null){
    		for(HHQuestionSummaryReportVO temp:optnList){
    			if(temp.getOptionId().equals(optnId)){
    				return temp;
    			}
    		}
    	} 
    	return null;
    }
    
    public List<HHQuestionSummaryReportVO> getOptionVO(List<Object[]> optsList){
    	List<HHQuestionSummaryReportVO> options = new ArrayList<HHQuestionSummaryReportVO>();
    		if(optsList!=null && optsList.size()>0){
    			for(Object[] obj:optsList){
    				HHQuestionSummaryReportVO opts = new HHQuestionSummaryReportVO();
    				opts.setOption(StringEscapeUtils.unescapeJava(obj[1].toString()));
    				opts.setOptionId(Long.valueOf(obj[0].toString()));
    				opts.setTotalCount(0l);
    				options.add(opts);
    			}
    		}
    	
    	return options;
    }
    
    public String calcPercentage(Long total,Long count){
		if(total>0){
			return count != 0 ? roundTo2DigitsFloatValue((float) count * 100f / total): "0.00";
		}else{
			return "0.00";
		}
	}
    
    public String roundTo2DigitsFloatValue(Float number){
		  
		  String result = "";
		  try{
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			result =  f.format(number);
		  }catch(Exception e){
			  log.error("Exception raised in roundTo2DigitsFloatValue service method",e);
		  }
		  return result;
	  }
    
    public List<HouseHoldVotersVO> getFamilyMembersUnderFamilyHead(Long houseHoldId){
    	   
        List<HouseHoldVotersVO> familyMembersList= new ArrayList<HouseHoldVotersVO>();
        try{
       
            List<HouseHoldVoter> list = houseHoldVoterDAO.getHouseHoldsVoterdDetailsByHouseHoldId1(houseHoldId);
           
            for(HouseHoldVoter hhv:list)
            {           
                HouseHoldVotersVO vo = new HouseHoldVotersVO();  
                if(hhv.getVoterId() != null)
                {
                	vo.setName(hhv.getVoter().getName());
                	vo.setVoterIDCardNo(hhv.getVoter().getVoterIDCardNo());
                	vo.setRelation(hhv.getVoter().getRelationshipType());  
                	vo.setRelativeName(hhv.getVoter().getRelativeName());
                	vo.setAge(hhv.getVoter().getAge());
                	vo.setPanchayatName(hhv.getHouseHolds().getPanchayat().getPanchayatName());
                }
                else
                {
                	vo.setName(hhv.getHouseHoldsFamilyDetails().getName());
                	vo.setVoterIDCardNo("");
                	vo.setRelativeName(hhv.getHouseHoldsFamilyDetails().getRelativeName());
                	vo.setAge(hhv.getHouseHoldsFamilyDetails().getAge());
                	vo.setRelation(hhv.getVoterFamilyRelation().getRelation()); 
                }
                familyMembersList.add(vo);
            }
           
        }catch (Exception e) {
        	 log.error("Exception raised in getFamilyMembersUnderFamilyHead service method",e);
        }
        return familyMembersList;
    }
    
    public List<HouseHoldVotersVO> getNonVoterAgeRangeDetailsInConstituency(Long constituencyId)
    {
    	List<HouseHoldVotersVO> ageRangeDetails = new ArrayList<HouseHoldVotersVO>();
    	try{
    		
    		List<Object[]> list = houseHoldVoterDAO.getNonVoterAgeRangesInConstituency(constituencyId,IConstants.KPM_AGE1_MIN,IConstants.KPM_AGE1_MAX);   		 
			List<Object[]> list1 = houseHoldVoterDAO.getNonVoterAgeRangesInConstituency(constituencyId,IConstants.KPM_AGE2_MIN,IConstants.KPM_AGE2_MAX);   		 
			List<Object[]> list2 = houseHoldVoterDAO.getNonVoterAgeRangesInConstituency(constituencyId,IConstants.KPM_AGE3_MIN,IConstants.KPM_AGE3_MAX);
	
			for(Object[] params:list)
			{ 				
				HouseHoldVotersVO vo = new HouseHoldVotersVO();
				 vo.setPanchayatId((Long)params[3]);
				 vo.setPanchayatName(params[4].toString());
				 vo = getAgeRangesOfHouseHolds(vo);
				 
				 ageRangeDetails.add(vo);					 						
			}
			 
			for(Object[] params :list){
				 HouseHoldVotersVO vo1 = getMatchedVO(ageRangeDetails,(Long)params[3]);
				 if(vo1 != null){	
					 HouseHoldVotersVO ageRangeVO = getMatchedAgeRange(vo1.getAgeRangesList(),IConstants.KPM_AGE1_MIN+"-"+IConstants.KPM_AGE1_MAX);
					 if(ageRangeVO!=null){
						 ageRangeVO.setAgeRangeCount((Long)params[2]);
					 }
				 }					
			 }		
		
			 for(Object[] params :list1)
			 {
				 HouseHoldVotersVO vo1 = getMatchedVO(ageRangeDetails,(Long)params[3]);
				 if(vo1 != null){	
					 HouseHoldVotersVO ageRangeVO = getMatchedAgeRange(vo1.getAgeRangesList(),IConstants.KPM_AGE2_MIN+"-"+IConstants.KPM_AGE2_MAX);
					 if(ageRangeVO!=null){
						 ageRangeVO.setAgeRangeCount((Long)params[2]);
					 }
				 }					
			 }
			 
			 for(Object[] params :list2)
			 {
				 HouseHoldVotersVO vo1 = getMatchedVO(ageRangeDetails,(Long)params[3]);
				 if(vo1 != null){	
					 HouseHoldVotersVO ageRangeVO = getMatchedAgeRange(vo1.getAgeRangesList(),IConstants.KPM_AGE3_MIN+"-"+IConstants.KPM_AGE3_MAX);
					 if(ageRangeVO!=null){
						 ageRangeVO.setAgeRangeCount((Long)params[2]);
					 }
				 }				
			 }   			    			
         }catch (Exception e) {
         	 log.error("Exception raised in getNonVoterAgeRangeDetailsInConstituency service method",e);
         }
    	 return ageRangeDetails; 
    }
    
    public HouseHoldVotersVO getAgeRangesOfHouseHolds(HouseHoldVotersVO finalVO){
		
		HouseHoldVotersVO hv =null;
		
		List<HouseHoldVotersVO> ageRangesList = new ArrayList<HouseHoldVotersVO>();
		
		hv  = new HouseHoldVotersVO();
		hv.setAgeRange(IConstants.KPM_AGE1_MIN+"-"+IConstants.KPM_AGE1_MAX);
		ageRangesList.add(hv);
		
		hv  = new HouseHoldVotersVO();
		hv.setAgeRange(IConstants.KPM_AGE2_MIN+"-"+IConstants.KPM_AGE2_MAX);
		ageRangesList.add(hv);
		
		hv  = new HouseHoldVotersVO();
		hv.setAgeRange(IConstants.KPM_AGE3_MIN+"-"+IConstants.KPM_AGE3_MAX);
		ageRangesList.add(hv);
		
		finalVO.setAgeRangesList(ageRangesList);
		return finalVO;
		
	}
    public HouseHoldVotersVO getMatchedVO(List<HouseHoldVotersVO> resultList,Long panchayatId)
	{
	
		try{
			if(resultList == null)
				return null;
			for(HouseHoldVotersVO vo : resultList)
			{
				if(panchayatId.longValue() == vo.getPanchayatId().longValue())
					return vo;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}

    public HouseHoldVotersVO getMatchedAgeRange(List<HouseHoldVotersVO> ageRanges,String ageRange){
	if(ageRanges!=null && ageRanges.size()>0 && ageRange!=null && ageRange.trim()!=""){
		for(HouseHoldVotersVO ag:ageRanges){
			if(ag.getAgeRange().equalsIgnoreCase(ageRange)){
				return ag;
			}
		}
	}
	return null;
    }
    
   

    public List<HouseHoldsReportVO> getBooksDetailsOfHouseHolds(Long constituencyId)
    {
    	List<HouseHoldsReportVO>  booksDetails= new ArrayList<HouseHoldsReportVO>();
    	try{
    		List<Object[]> list = houseHoldVoterDAO.getBooksOfHouseHolds(constituencyId);
    		if(list != null && list.size() > 0){
    		for(Object[] params : list){
    			
    			HouseHoldsReportVO hhv = new HouseHoldsReportVO();
    			hhv.setBookId((Long)params[0]);
    			hhv.setTehsilName(params[2].toString());
    			hhv.setPanchayatId((Long)params[4]);
    			hhv.setPanchayatName(params[5].toString());
    			hhv.setLeaderName(params[6].toString());
    			hhv.setVoterId(params[7].toString());
    			hhv.setBookNo((Long)params[1]);
    			hhv.setVotersCount((Long)params[8]);
    			hhv.setNonVotersCount((Long)params[9]);
    			hhv.setFamiliesCount((Long)params[10]);
    			booksDetails.add(hhv);
    			
    		}
    		}
         }catch (Exception e) {
         	 log.error("Exception raised in getBooksOfHouseHoldDetails service method",e);
         }
    	 return booksDetails; 
    }
    
    
    public List<HouseHoldVotersVO> getAgeRangeWiseNonVotersDetails(Long panchayatId,int type,Long fromAge,Long toAge)
    {
    	List<HouseHoldVotersVO>  nonVotersDetails= new ArrayList<HouseHoldVotersVO>();
    	try{
    		List<Object[]> list = houseHoldVoterDAO.getNonVotersAgeGroupInHouseHolds(panchayatId,type,fromAge,toAge);
    		if(list != null && list.size() > 0){
    		for(Object[] params : list){
    			
    			HouseHoldVotersVO hhv = new HouseHoldVotersVO();
    			hhv.setHouseHoldFamilyMemberId((Long)params[0]);
    			hhv.setName(params[1].toString());
    			hhv.setHouseNo(params[3].toString());
    			hhv.setAge((Long)params[4]);
    			hhv.setRelativeName(params[6].toString());
    			nonVotersDetails.add(hhv);
    			
    		}
    		}
         }catch (Exception e) {
         	 log.error("Exception raised in getAgeRangeWiseNonVotersDetails service method",e);
         }
    	 return nonVotersDetails; 
    }
  public Object getBooksWiseSummaryReport(Long constituencyId)
 {

	  List<HouseHoldsVO> houseHoldsVoList=null;
		try {
			List<Object[]> votersCountList = houseHoldVoterDAO
					.getBooksOfHouseHolds(constituencyId);
			List<Object[]> nonVotersCountList = houseHoldVoterDAO
					.getBooksOfHouseHoldsNV(constituencyId);
			List<Object[]> familiesCountList = houseHoldVoterDAO
					.getBooksOfHouseHoldsHHCount(constituencyId);

			Map<Long, HouseHoldsVO> m = new HashMap<Long, HouseHoldsVO>();
			if (votersCountList != null && votersCountList.size() > 0) {
				for (Object[] votersCount : votersCountList) {
					HouseHoldsVO houseHoldsVO = new HouseHoldsVO();
					houseHoldsVO
							.setBookId((Long) votersCount[0] != null ? (Long) votersCount[0]
									: 0l);// bookid
					houseHoldsVO
							.setBookNo((Long) votersCount[1] != null ? (Long) votersCount[1]
									: 0l);// bookno
					houseHoldsVO.setMessage((String) votersCount[2]);// tehsilname.
					houseHoldsVO
							.setBoothId((Long) votersCount[4] != null ? (Long) votersCount[4]
									: 0l);// pid
					houseHoldsVO.setHeading((String) votersCount[5]);// pname
					houseHoldsVO.setLeaderName((String) votersCount[6]);// leadername
					houseHoldsVO.setVoterId((String) votersCount[7]);// leader
																		// voterId
					houseHoldsVO
							.setVotersCount((Long) votersCount[8] != null ? (Long) votersCount[8]
									: 0l);// votersCount
					m.put((Long) votersCount[0], houseHoldsVO);
				}
			}
			if (nonVotersCountList != null && nonVotersCountList.size() > 0) {
				for (Object[] nonVotersCount : nonVotersCountList) {
					HouseHoldsVO vo = m.get((Long) nonVotersCount[0]);
					if (vo != null)
						vo.setNonVotersCount((Long) nonVotersCount[8] != null ? (Long) nonVotersCount[8]
								: 0l);// nonvoterscount.
				}

			}
			if (familiesCountList != null && familiesCountList.size() > 0) {
				for (Object[] familiesCount : familiesCountList) {
					HouseHoldsVO vo = m.get((Long) familiesCount[0]);
					if (vo != null)
						vo.setFamiliesCount((Long) familiesCount[8] != null ? (Long) familiesCount[8]
								: 0l);// familiescount.
				}
			}
			houseHoldsVoList = new ArrayList<HouseHoldsVO>();
			houseHoldsVoList.addAll(m.values());
		} catch (Exception e) {
			log.error(
					"Exception raised in getBooksWiseSummaryReport service method",
					e);
		}

		return houseHoldsVoList;

	} 
  
	public HHReportVO getPanchayatWiseDetails(Long constituencyId){
	  
	  HHReportVO resultVO = new HHReportVO();
	  try {
		
		List<Object[]> list = houseHoldVoterDAO.getAllPanchayatsInHouseHoldsOfConstituency(constituencyId);

		HHReportVO finalVO = new HHReportVO();
		List<HHReportVO> panchayatsList = new ArrayList<HHReportVO>();
		
		if(list!=null && list.size()>0){
			for(Object[] obj:list){
				HHReportVO hv= new HHReportVO();
				hv.setConstituency(obj[2].toString());
				hv.setPanchayatId(Long.valueOf(obj[0].toString()));
				hv.setPanchayatName(obj[1].toString());
				hv.setVotersCount(0l);
				hv.setNonVotersCount(0l);
				hv.setLedersCount(0l);
				hv.setLeadersList(new ArrayList<HHReportVO>());
				hv.setLeaders(new ArrayList<Long>());
				panchayatsList.add(hv);
			}
		}
		
		finalVO.setPanchayatList(panchayatsList);

		List<Object[]> allList = houseHoldVoterDAO.getBookWiseHouseHolds(constituencyId);
		List<Long> constituencyLeaders = new ArrayList<Long>();

		if(allList!=null && allList.size()>0){
			for(Object[] obj:allList){
			
				if(!constituencyLeaders.contains((Long)(obj[13])))
					constituencyLeaders.add((Long)(obj[13]));
				HHReportVO hv= getMatchedPanchayatList(finalVO.getPanchayatList(), Long.valueOf(obj[6].toString()));
				if(hv!=null){
					List<HHReportVO> hvList = hv.getHouseHoldsList();
				if(hvList==null){
					
					hvList = new ArrayList<HHReportVO>();
				}
				
				HHReportVO houseHold = getMatchedHouseHold(hvList, Long.valueOf(obj[3].toString()));
				boolean newlyAdded = false;
				
				if(houseHold==null){
					houseHold = new HHReportVO();
					houseHold.setHouseHoldId(Long.valueOf(obj[3].toString()));
					houseHold.setVotersCount(0l);
					houseHold.setLedersCount(0l);
					houseHold.setNonVotersCount(0l);
					houseHold.setPanchayatId(Long.valueOf(obj[6].toString()));
					houseHold.setPanchayatName(obj[7].toString());
					houseHold.setLeaderBookId(Long.valueOf(obj[1].toString()));
					houseHold.setBookNo(obj[2].toString());
					houseHold.setLeaderName(obj[8].toString());
					houseHold.setLeaderVoterId(obj[9].toString());
					houseHold.setLeaderId(Long.valueOf(obj[13].toString()));
					newlyAdded = true;
				}
		
				if(obj[12]!=null){
					if(Long.valueOf(obj[12].toString()).equals(1l)){
						houseHold.setRelationShipId(Long.valueOf(obj[12].toString()));
					//houseHold.setHeadName(obj[12].toString());
					if(obj[10]!=null){
						houseHold.setHeadVoterId(obj[10].toString());
					}
					}
				}
				
				if(obj[10]!=null){
					houseHold.setVotersCount(houseHold.getVotersCount()+1);
				}
				if(obj[11]!=null){
					houseHold.setNonVotersCount(houseHold.getNonVotersCount()+1);
				}
				if(newlyAdded){
					hvList.add(houseHold);
				}
					hv.setHouseHoldsList(hvList);
				}
		
				}
			}
		
			List<HHReportVO> pList = finalVO.getPanchayatList();
			if(pList!=null && pList.size()>0){
				for(HHReportVO hv:pList){
					HHReportVO hr = getMatchedPanchayatList(pList, hv.getPanchayatId());
					List<HHReportVO> hpList = null;
						if(hr!=null){
							hpList = hr.getHhListFinal();
						if(hpList==null){
								hpList = new ArrayList<HHReportVO>();
							}
						}
					List<HHReportVO> hhList = hv.getHouseHoldsList();
					if(hhList!=null && hhList.size()>0){
						for(HHReportVO hh:hhList){
							if(hh.getRelationShipId()!=null){
								hpList.add(hh);
								hr.setVotersCount(hh.getVotersCount()+hr.getVotersCount());
								hr.setNonVotersCount(hh.getNonVotersCount()+hr.getNonVotersCount());
								
								if(!hr.getLeaders().contains(hh.getLeaderId())){
									hr.getLeaders().add(hh.getLeaderId());
									hr.setLedersCount(hr.getLedersCount()+1);				
								}
							}
						}
					}
					hr.setHhListFinal(hpList);
				}
			}	
			
			List<Object[]> leaderBooks = hhLeaderBooksDAO.getAllBooksLeadersInConstituency(constituencyId);
			List<HHReportVO> leadersList = new ArrayList<HHReportVO>();
			for(Object[] obj:leaderBooks){
				HHReportVO hv = new HHReportVO();
				hv.setLeaderBookId(Long.valueOf(obj[0].toString()));
				hv.setLeaderId(Long.valueOf(obj[5].toString()));
				hv.setLeaderVoterId(obj[3].toString());
				/*hv.setPanchayatId(Long.valueOf(obj[6].toString()));
				hv.setPanchayatName(obj[7].toString());*/
				hv.setBookNo(obj[1].toString());
				hv.setLeaderName(obj[2].toString());
				hv.setVotersCount(0l);
				hv.setNonVotersCount(0l);
				hv.setHouseHoldsCount(0l);
				hv.setHouseHolds(new ArrayList<Long>());
				hv.setPanchayatIds(new ArrayList<Long>());
				leadersList.add(hv);
			}
			
			List<HHReportVO> panchayats = finalVO.getPanchayatList();
			Long constituencyVotersCount= 0l;
			Long constituencyNonVotersCount= 0l;
			Long constituencyHouseHoldsCount= 0l;
			String constituency = "";
			List<HHReportVO> resultList = new ArrayList<HHReportVO>();
			for(HHReportVO hr:panchayats){
				HHReportVO vo = new HHReportVO();
				vo.setPanchayatId(hr.getPanchayatId());
				vo.setPanchayatName(hr.getPanchayatName());
				constituency= hr.getConstituency();
				vo.setVotersCount(hr.getVotersCount());
				vo.setNonVotersCount(hr.getNonVotersCount());
				vo.setHouseHoldsCount(Long.valueOf(hr.getHhListFinal().size()));
				vo.setLedersCount(hr.getLedersCount());
				constituencyVotersCount = constituencyVotersCount + hr.getVotersCount();
				constituencyNonVotersCount = constituencyNonVotersCount + hr.getNonVotersCount();
				constituencyHouseHoldsCount = constituencyHouseHoldsCount + hr.getHhListFinal().size();
				resultList.add(vo);
			}
			resultVO.setPanchayatList(resultList);
			resultVO.setLedersCount(Long.valueOf(constituencyLeaders.size()));
			resultVO.setVotersCount(constituencyVotersCount);
			resultVO.setNonVotersCount(constituencyNonVotersCount);
			resultVO.setHouseHoldsCount(constituencyHouseHoldsCount);
			resultVO.setConstituency(constituency);
			resultVO.setConstituencyId(constituencyId);
			
			
			for(HHReportVO hr:panchayats){
					List<HHReportVO> hList = hr.getHhListFinal();
						if(hList!=null && hList.size()>0){
							for(HHReportVO hv:hList){
								HHReportVO temp = getMatchedLeaderBook(leadersList, hv.getLeaderBookId());
								if(temp!=null){
									temp.setVotersCount(temp.getVotersCount()+hv.getVotersCount());
									temp.setNonVotersCount(temp.getNonVotersCount()+hv.getNonVotersCount());
									
									if(!temp.getHouseHolds().contains(hv.getHouseHoldId())){
										temp.getHouseHolds().add(hv.getHouseHoldId());
										temp.setHouseHoldsCount(temp.getHouseHoldsCount() + 1);				
									}
									
									if(!temp.getPanchayatIds().contains(hv.getPanchayatId())){
										temp.getPanchayatIds().add(hv.getPanchayatId());
										if(temp.getPanchayatId() != null){
											temp.setPanchayatName(temp.getPanchayatName()+','+hv.getPanchayatName());
										}
										else{								
											temp.setPanchayatId(hv.getPanchayatId());
											temp.setPanchayatName(hv.getPanchayatName());
										}
									}
								}
							}
					}
			 }
			 List<HHReportVO> resultList1 = new ArrayList<HHReportVO>();
			 for(HHReportVO hr1:leadersList){
				 HHReportVO vo = new HHReportVO();
				 	vo.setLeaderBookId(hr1.getLeaderBookId());
					vo.setBookNo(hr1.getBookNo());
					vo.setLeaderVoterId(hr1.getLeaderVoterId());
					vo.setPanchayatName(hr1.getPanchayatName());
					vo.setLeaderName(hr1.getLeaderName());
					vo.setVotersCount(hr1.getVotersCount());
					vo.setNonVotersCount(hr1.getNonVotersCount());
					vo.setHouseHoldsCount(hr1.getHouseHoldsCount());
					resultList1.add(vo);
					}
			 resultVO.setHhListFinal(resultList1);	 
	  	}catch(Exception e) {
	  		log.error("Exception raised in getPanchayatWiseDetails method",e);
	  	}
	  return resultVO;
	}
		
  
  
  	public HHReportVO getMatchedPanchayatList(List<HHReportVO> list,Long id){
		if(list!=null && list.size()>0 && id!=null){
			for(HHReportVO hv:list){
					if(hv.getPanchayatId().equals(id)){
						return hv;
					}
				}
			}
			return null;
	}

	public HHReportVO getMatchedLeaderBook(List<HHReportVO> list,Long id){
			if(list!=null && list.size()>0 && id!=null){
				for(HHReportVO hv:list){
					if(hv.getLeaderBookId().equals(id)){
						return hv;
					}
				}
			}
			return null;
	}

	public HHReportVO getMatchedHouseHold(List<HHReportVO> list,Long id){
			if(list!=null && list.size()>0 && id!=null){
				for(HHReportVO hv:list){
					if(hv.getHouseHoldId().equals(id)){
						return hv;
					}
				}
			}
			return null;
	}
	public HHReportVO getMatchedLeaders(List<HHReportVO> list,Long id){
		if(list!=null && list.size()>0 && id!=null){
			for(HHReportVO hv:list){
					if(hv.getLeaderId().equals(id)){
						return hv;
					}
				}
			}
			return null;
	}
}
