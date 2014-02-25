package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IPfbReportDAO;
import com.itgrids.partyanalyst.dao.hibernate.AssemblyPoliticalFeedbackDAO;
import com.itgrids.partyanalyst.dao.hibernate.ConstituencyDAO;
import com.itgrids.partyanalyst.dao.hibernate.ParlimentActionItemsDAO;
import com.itgrids.partyanalyst.dao.hibernate.ParlimentPoliticalFeedbackDAO;
import com.itgrids.partyanalyst.dao.hibernate.UserDAO;
import com.itgrids.partyanalyst.dto.PoliticalFeedBackVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AssemblyPoliticalFeedback;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ParlimentActionItems;
import com.itgrids.partyanalyst.model.ParlimentPoliticalFeedback;
import com.itgrids.partyanalyst.model.PfbReport;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.service.IPoliticalFeedBackService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class PoliticalFeedBackService implements IPoliticalFeedBackService{

	private static final Logger LOG  = Logger.getLogger(PoliticalFeedBackService.class);
	private ParlimentPoliticalFeedbackDAO      			parlimentPoliticalFeedbackDAO;
	private ParlimentActionItemsDAO            			parlimentActionItemsDAO;
	private AssemblyPoliticalFeedbackDAO      			assemblyPoliticalFeedbackDAO;
	private ConstituencyDAO                   			constituencyDAO;
	private UserDAO                            			userDAO;
	private TransactionTemplate 			            transactionTemplate;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IDelimitationConstituencyDAO				delimitationConstituencyDAO;
	private IPfbReportDAO 								pfbReportDAO;
	
	
	public void setPfbReportDAO(IPfbReportDAO pfbReportDAO) {
		this.pfbReportDAO = pfbReportDAO;
	}
	public void setParlimentPoliticalFeedbackDAO(
			ParlimentPoliticalFeedbackDAO parlimentPoliticalFeedbackDAO) {
		this.parlimentPoliticalFeedbackDAO = parlimentPoliticalFeedbackDAO;
	}
	public void setParlimentActionItemsDAO(
			ParlimentActionItemsDAO parlimentActionItemsDAO) {
		this.parlimentActionItemsDAO = parlimentActionItemsDAO;
	}
	public void setAssemblyPoliticalFeedbackDAO(
			AssemblyPoliticalFeedbackDAO assemblyPoliticalFeedbackDAO) {
		this.assemblyPoliticalFeedbackDAO = assemblyPoliticalFeedbackDAO;
	}
	public void setConstituencyDAO(ConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	
	/**
	 * This service is used for telugu font saving as well as telugu font retriving
	 * @param input
	 * @return String b
	 */
	public String escapeUnicode(String input)
	{
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

	
	/**
	 * This Service is used for saving the political feed back details
	 * @param politicalFeedBackVO
	 * @return resultStatus
	 */
	public ResultStatus savePoliticalFeedBack(final PoliticalFeedBackVO politicalFeedBackVO)
	{
		final ResultStatus resultStatus = new ResultStatus();
		try {
			LOG.debug("Entered into savePoliticalFeedBack method in PoliticalFeedBackService Class");
			 transactionTemplate.execute(new TransactionCallback() {
				    public Object doInTransaction(TransactionStatus status) {
					if(politicalFeedBackVO.getType().equalsIgnoreCase("edit"))
					{
						int count = parlimentPoliticalFeedbackDAO.deleteRecord(politicalFeedBackVO.getId());
						System.out.println(count);
					}
					if(politicalFeedBackVO != null)
					{
						  DateUtilService dateUtilService = new DateUtilService();
						  SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
						  ParlimentPoliticalFeedback parlimentPoliticalFeedback = new ParlimentPoliticalFeedback();
						  parlimentPoliticalFeedback.setSummary(politicalFeedBackVO.getSummary() != null ? escapeUnicode(StringEscapeUtils.escapeJava(politicalFeedBackVO.getSummary())) : null);
						  try {
							parlimentPoliticalFeedback.setCreatedDate(sdf.parse(politicalFeedBackVO.getCreatedDate()));
						  } catch (ParseException e) {}
						  parlimentPoliticalFeedback.setInsertedDate(dateUtilService.getCurrentDateAndTime());
						  parlimentPoliticalFeedback.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
						  parlimentPoliticalFeedback.setIsDeleted("N");
						  Constituency  constituency = constituencyDAO.get(politicalFeedBackVO.getParlimentId());
						  parlimentPoliticalFeedback.setParlimentConstituency(constituency);
						  User user = userDAO.get(politicalFeedBackVO.getUserId());
						  parlimentPoliticalFeedback.setUser(user);
						  
						  parlimentPoliticalFeedback = parlimentPoliticalFeedbackDAO.save(parlimentPoliticalFeedback);
						  if(parlimentPoliticalFeedback != null)
						  {
														  
							  for(SelectOptionVO selectOptionVO : politicalFeedBackVO.getActionItemsList())
							  {
								  ParlimentActionItems parlimentActionItems = new ParlimentActionItems();
								  parlimentActionItems.setActionItem(selectOptionVO.getName().trim().length() > 0 ? escapeUnicode(StringEscapeUtils.escapeJava(selectOptionVO.getName().trim())):null);
								  parlimentActionItems.setSource(selectOptionVO.getLocation().trim().length() > 0 ? escapeUnicode(StringEscapeUtils.escapeJava(selectOptionVO.getLocation().trim())):null);
								  parlimentActionItems.setParlimentPoliticalFeedback(parlimentPoliticalFeedback);
								  parlimentActionItemsDAO.save(parlimentActionItems);
							  }
							  for (PoliticalFeedBackVO politicalFeedBack : politicalFeedBackVO.getPoliticalFeedBackVOList())
							  {
								  AssemblyPoliticalFeedback assemblyPoliticalFeedback = new AssemblyPoliticalFeedback();
								  assemblyPoliticalFeedback.setParlimentPoliticalFeedback(parlimentPoliticalFeedback);
								  assemblyPoliticalFeedback.setCmPoliticalFeedback(politicalFeedBack.getCmPoliticalFeedBack().trim().length() > 0 ? escapeUnicode(StringEscapeUtils.escapeJava(politicalFeedBack.getCmPoliticalFeedBack())):null);
								  assemblyPoliticalFeedback.setImpNews(politicalFeedBack.getImpNews().trim().length() > 0 ? escapeUnicode(StringEscapeUtils.escapeJava(politicalFeedBack.getImpNews())):null);
								  assemblyPoliticalFeedback.setOtherPoliticalBack(politicalFeedBack.getOtherPoliticalFeedBack().trim().length() > 0 ?escapeUnicode(StringEscapeUtils.escapeJava(politicalFeedBack.getOtherPoliticalFeedBack())):null);
								  Constituency assemblyConstituency = constituencyDAO.get(politicalFeedBack.getConstituencyId());
								  assemblyPoliticalFeedback.setConstituency(assemblyConstituency);
								  assemblyPoliticalFeedback.setImpSource(politicalFeedBack.getImpSource().trim().length() > 0 ? escapeUnicode(StringEscapeUtils.escapeJava(politicalFeedBack.getImpSource().trim())):null);
								  assemblyPoliticalFeedback.setCmFeedBackSource(politicalFeedBack.getCmPoliticalFeedBack().trim().length() > 0 ? escapeUnicode(StringEscapeUtils.escapeJava(politicalFeedBack.getCmPoliticalFeedBack().trim())):null);
								  assemblyPoliticalFeedback.setOtherFeedBackSource(politicalFeedBack.getOtherPoliticalFeedBack().trim().length() > 0 ? escapeUnicode(StringEscapeUtils.escapeJava(politicalFeedBack.getOtherPoliticalFeedBack().trim())):null);
								  assemblyPoliticalFeedbackDAO.save(assemblyPoliticalFeedback);
								  
							  }
							  
							  
							  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
							  resultStatus.setMessage("success");
						  }
					  
				  }
				  return resultStatus; 
				  }
				  
			 });
		} catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			resultStatus.setMessage("Exception Occured While Saving");
			LOG.error("Exception Occured in savePoliticalFeedBack method in PoliticalFeedBackService Class",e);
		}
		return resultStatus;
	}
	
	/**
	 * this service is used for getting the access parliments list
	 * @param userId
	 * @return
	 */
	public List<SelectOptionVO> getAccessParliments(Long userId)
	{
		List<SelectOptionVO> returnList = null;
		try {
			LOG.debug("Entered into getAccessParliments method in PoliticalFeedBackService Class");
			List<String> accessValues = userDAO.getAccessValue(userId);
			List<Long> conList = null;
			if(accessValues != null && accessValues.size() > 0)
			{
				conList = new ArrayList<Long>();
				for (String string : accessValues) {
					conList.add(Long.valueOf(string));
				}
				
				List<Object[]> constiList = constituencyDAO.getPCConstituencyes(conList);
				if(constiList != null && constiList.size() > 0)
				{
					returnList = new ArrayList<SelectOptionVO>();
					for (Object[] objects : constiList) {
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)objects[0]);
						selectOptionVO.setName(objects[1].toString());
						returnList.add(selectOptionVO);
						
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getAccessParliments method in PoliticalFeedBackService Class",e);
		}
		return returnList;
	}
	
	/**
	 * This service is used for getting the assembly constituencyes Forselected parliment constituency
	 * @param pcId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getAssemblyListByPCId(Long pcId)
	{
		List<SelectOptionVO> returnList = null;
		try {
			LOG.debug("Entered into getAssemblyListByPCId method in PoliticalFeedBackService Class");
			Map<Long,String> constiMap = null;
			Map<Long,Long> constNosMap = null;
			List<Object[]> constiList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(pcId);
			if(constiList != null && constiList.size() > 0)
			{
				
				constiMap = new HashMap<Long, String>();
				
				for (Object[] objects : constiList)
				{
					constiMap.put((Long)objects[0], objects[1].toString());
				}
				List<Object[]> constituencyNos = delimitationConstituencyDAO.findConstituencyNosByConstituencyId(new ArrayList<Long>(constiMap.keySet()));
				if(constituencyNos != null && constituencyNos.size() > 0)
				{
					constNosMap = new HashMap<Long, Long>();
					for (Object[] parms : constituencyNos) {
						constNosMap.put((Long)parms[0], (Long)parms[1]);
					}
				}
				
				Set<Long> constiIds = constiMap.keySet();
				if(constiIds != null && constiIds.size() > 0)
				{
					returnList = new ArrayList<SelectOptionVO>();
					for (Long consituencyId : constiIds) {
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId(consituencyId);
						selectOptionVO.setName(constiMap.get(consituencyId));
						selectOptionVO.setCount(constNosMap.get(consituencyId));
						returnList.add(selectOptionVO);
					}
				}
				
			}

			
		} catch (Exception e) {
			LOG.error("Exception Occured in getAccessParliments method in PoliticalFeedBackService Class",e);
		}
		return returnList;
	}
	
	/**
	 * this service is used for getting the political feed backs for seleted parliment and selected date
	 * @param date
	 * @param pcId
	 * @return
	 */
	public List<PoliticalFeedBackVO> getSelectedPolitialFeedBackDetails(String date,Long pcId)
	{
		List<PoliticalFeedBackVO> returnList = null;
		try {
			LOG.debug("Entered into getSelectedPolitialFeedBackDetails method in PoliticalFeedBackService Class");
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Map<Long,PoliticalFeedBackVO> parlimentMap = null;
			Map<Long,List<PoliticalFeedBackVO>> assemblyMap = null;
			Map<Long,List<SelectOptionVO>> pcActionItemsMap = null;
			Map<Long,Long> constNosMap = null;
 			List<Long> pfbIds = null;
			List<Long> constiIds = null;
			List<Object[]> parlimetfeedBacks = parlimentPoliticalFeedbackDAO.getParlimentPoliticalFeedBacks(sdf.parse(date), pcId);
			if(parlimetfeedBacks != null && parlimetfeedBacks.size() > 0)
			{
				pfbIds = new ArrayList<Long>();
				constiIds = new ArrayList<Long>();
				parlimentMap = new HashMap<Long, PoliticalFeedBackVO>();
				for (Object[] parms : parlimetfeedBacks) {
					PoliticalFeedBackVO pcFeedBackVO = parlimentMap.get((Long)parms[0]);
					if(pcFeedBackVO == null)
					{
						 pcFeedBackVO = new PoliticalFeedBackVO();
						 parlimentMap.put((Long)parms[0], pcFeedBackVO);
						 pfbIds.add((Long)parms[0]);
					}
					constiIds.add((Long)parms[1]);
					pcFeedBackVO.setParlimentId((Long)parms[1]);
					pcFeedBackVO.setParlimentName(parms[2] != null ? parms[2].toString():null);
					pcFeedBackVO.setSummary(parms[3] != null ? StringEscapeUtils.unescapeJava(parms[3].toString()) : null);
					pcFeedBackVO.setCreatedDate(parms[4] != null ? parms[4].toString() : null);
				}
				
				
				List<Object[]> assemblyeiseList = assemblyPoliticalFeedbackDAO.getAssemblyPoliticalFeedBacks(pfbIds);
				if(assemblyeiseList != null && assemblyeiseList.size() > 0)
				{
					assemblyMap = new HashMap<Long, List<PoliticalFeedBackVO>>();
					for (Object[] parms : assemblyeiseList) {
						constiIds.add((Long)parms[1]);
					}
					
					List<Object[]> constituencyNos = delimitationConstituencyDAO.findConstituencyNosByConstituencyId(constiIds);
					if(constituencyNos != null && constituencyNos.size() > 0)
					{
						constNosMap = new HashMap<Long, Long>();
						for (Object[] parms : constituencyNos) {
							constNosMap.put((Long)parms[0], (Long)parms[1]);
						}
					}
					fillAssemblyPoliticalFeedBackForm(assemblyeiseList,assemblyMap,constNosMap);

				}
				
				List<Object[]> actionItesList = parlimentActionItemsDAO.getParlimentActionItems(pfbIds);
				if(actionItesList != null && actionItesList.size() > 0)
				{
					pcActionItemsMap = new HashMap<Long, List<SelectOptionVO>>();
					fillParlimetActionItemsForm(actionItesList,pcActionItemsMap);
				}
			}
			
			
			if(pfbIds != null && pfbIds.size() > 0)
			{
				returnList = new ArrayList<PoliticalFeedBackVO>();
				for (Long pfbId : pfbIds) {
					PoliticalFeedBackVO pcFeedBackVO = parlimentMap.get(pfbId);
					pcFeedBackVO.setConstituencyNo(constNosMap != null ? constNosMap.get(pcFeedBackVO.getParlimentId()) : null);
					pcFeedBackVO.setId(pfbId);
					if(pcFeedBackVO != null)
					{
						pcFeedBackVO.setPoliticalFeedBackVOList(assemblyMap.get(pfbId));
						pcFeedBackVO.setActionItemsList(pcActionItemsMap.get(pfbId));
						returnList.add(pcFeedBackVO);
					}
					
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getSelectedPolitialFeedBackDetails method in PoliticalFeedBackService Class",e);
		}
		/*String url = buildExcelForPFB(returnList);
		returnList.get(0).setIsDeleted(url);*/
		return returnList;
	}
	
	
	
	public PoliticalFeedBackVO getSelectedPoliticalFeedBackDetails(Long pfbId)
	{
		PoliticalFeedBackVO politicalFeedBackVO = new PoliticalFeedBackVO();
		try {
			LOG.debug("Entered into getSelectedPoliticalFeedBackDetails method in PoliticalFeedBackService Class");
			List<Long> pfbIds = new ArrayList<Long>();
			pfbIds.add(pfbId);
			Map<Long,PoliticalFeedBackVO> parlimentMap = null;
			Map<Long,List<PoliticalFeedBackVO>> assemblyMap = null;
			Map<Long,List<SelectOptionVO>> pcActionItemsMap = null;
			Map<Long,Long> constNosMap = null;
			List<Long> constiIds = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			List<Object[]> parlimetfeedBacks = parlimentPoliticalFeedbackDAO.getParlimentPoliticalFeedBacksById(pfbId);
			if(parlimetfeedBacks != null && parlimetfeedBacks.size() > 0)
			{
				constiIds = new ArrayList<Long>();
				parlimentMap = new HashMap<Long, PoliticalFeedBackVO>();
				for (Object[] parms : parlimetfeedBacks) {
					PoliticalFeedBackVO pcFeedBackVO = parlimentMap.get((Long)parms[0]);
					if(pcFeedBackVO == null)
					{
						 pcFeedBackVO = new PoliticalFeedBackVO();
						 parlimentMap.put((Long)parms[0], pcFeedBackVO);
					}
					constiIds.add((Long)parms[1]);
					pcFeedBackVO.setParlimentId((Long)parms[1]);
					pcFeedBackVO.setParlimentName(parms[2] != null ? parms[2].toString():null);
					pcFeedBackVO.setSummary(parms[3] != null ? StringEscapeUtils.unescapeJava(parms[3].toString()) : null);
					pcFeedBackVO.setCreatedDate(parms[4] != null ?sdf.format(parseFormat.parse(parms[4].toString()))  : null);
				}
				List<Object[]> assemblyeiseList = assemblyPoliticalFeedbackDAO.getAssemblyPoliticalFeedBacks(pfbIds);
				if(assemblyeiseList != null && assemblyeiseList.size() > 0)
				{
					assemblyMap = new HashMap<Long, List<PoliticalFeedBackVO>>();
					assemblyMap = new HashMap<Long, List<PoliticalFeedBackVO>>();
					for (Object[] parms : assemblyeiseList) {
						constiIds.add((Long)parms[1]);
					}
					
					List<Object[]> constituencyNos = delimitationConstituencyDAO.findConstituencyNosByConstituencyId(constiIds);
					if(constituencyNos != null && constituencyNos.size() > 0)
					{
						constNosMap = new HashMap<Long, Long>();
						for (Object[] parms : constituencyNos) {
							constNosMap.put((Long)parms[0], (Long)parms[1]);
						}
					}
					fillAssemblyPoliticalFeedBackForm(assemblyeiseList,assemblyMap,constNosMap);

				}
				
				List<Object[]> actionItesList = parlimentActionItemsDAO.getParlimentActionItems(pfbIds);
				if(actionItesList != null && actionItesList.size() > 0)
				{
					pcActionItemsMap = new HashMap<Long, List<SelectOptionVO>>();
					fillParlimetActionItemsForm(actionItesList,pcActionItemsMap);
				}
				
			}
			
			if(pfbIds != null && pfbIds.size() > 0)
			{
				
				for (Long id : pfbIds) {
					 politicalFeedBackVO = parlimentMap.get(id);
					 politicalFeedBackVO.setId(pfbId);
					if(politicalFeedBackVO != null)
					{
						politicalFeedBackVO.setPoliticalFeedBackVOList(assemblyMap.get(id));
						politicalFeedBackVO.setActionItemsList(pcActionItemsMap.get(id));
					}
					
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getSelectedPoliticalFeedBackDetails method in PoliticalFeedBackService Class",e);
		}
		return politicalFeedBackVO;
	}
	public void fillAssemblyPoliticalFeedBackForm(List<Object[]> assemblyeiseList , Map<Long,List<PoliticalFeedBackVO>> assemblyMap,Map<Long,Long> constNosMap)
	{
		for (Object[] parms : assemblyeiseList)
			{
				List<PoliticalFeedBackVO> acFeedBackList = assemblyMap.get((Long)parms[0]);
				
				if(acFeedBackList == null)
				{
					acFeedBackList = new ArrayList<PoliticalFeedBackVO>();
					assemblyMap.put((Long)parms[0], acFeedBackList);
				}
				PoliticalFeedBackVO acFeedBackVO = new PoliticalFeedBackVO();
				acFeedBackVO.setConstituencyId(parms[1] != null ? (Long)parms[1] : null);
				if(constNosMap != null && constNosMap.size() > 0)
				{
					acFeedBackVO.setConstituencyNo(constNosMap.get(acFeedBackVO.getConstituencyId()));
				}
				acFeedBackVO.setAssemblyName(parms[2] != null ? parms[2].toString() : null);
				acFeedBackVO.setImpNews(parms[3] != null ? StringEscapeUtils.unescapeJava(parms[3].toString()) : null);
				acFeedBackVO.setCmPoliticalFeedBack(parms[4] != null ? StringEscapeUtils.unescapeJava(parms[4].toString()) : null);
				acFeedBackVO.setOtherPoliticalFeedBack(parms[5] != null ? StringEscapeUtils.unescapeJava(parms[5].toString()) : null);
				acFeedBackVO.setImpSource(parms[6] != null ? StringEscapeUtils.unescapeJava(parms[6].toString()) : null);
				acFeedBackVO.setCmSource(parms[7] != null ? StringEscapeUtils.unescapeJava(parms[7].toString()) : null);
				acFeedBackVO.setOtherSource(parms[8] != null ? StringEscapeUtils.unescapeJava(parms[8].toString()) : null);
			    acFeedBackList.add(acFeedBackVO);
		}
	}
	
	public void fillParlimetActionItemsForm(List<Object[]> actionItesList,Map<Long,List<SelectOptionVO>> pcActionItemsMap)
	{
		for (Object[] parms : actionItesList)
		{
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			List<SelectOptionVO> actionItems = pcActionItemsMap.get((Long)parms[0]);
			if(actionItems == null)
			{
				actionItems = new ArrayList<SelectOptionVO>();
				pcActionItemsMap.put((Long)parms[0], actionItems);
			}
			selectOptionVO.setName(parms[1] != null ? StringEscapeUtils.unescapeJava(parms[1].toString()) : null);
			selectOptionVO.setLocation(parms[2] != null ? StringEscapeUtils.unescapeJava(parms[2].toString()) : null);
			actionItems.add(selectOptionVO);
		}
	}
	public List<SelectOptionVO> getAllParlimentConstituencys()
	{
		List<SelectOptionVO> returnList = null;
		try {
			LOG.debug("Entered into getAllParlimentConstituencys method in PoliticalFeedBackService Class");
			List<Object[]> constIds = constituencyDAO.getAllParlimentConstituencys();
			if(constIds != null && constIds.size() > 0)
			{
				returnList = new ArrayList<SelectOptionVO>();
				for (Object[] objects : constIds) {
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)objects[0]);
					selectOptionVO.setName(objects[1].toString());
					returnList.add(selectOptionVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getAllParlimentConstituencys method in PoliticalFeedBackService Class",e);
		}
		return returnList;
	}
	
	public String deletedPoliticalFeedBadk(Long id)
	{
		String status = null;
		try {
			LOG.debug("Entered into deletedPoliticalFeedBadk method in PoliticalFeedBackService Class");
			int result = parlimentPoliticalFeedbackDAO.deletePoliticalFeedBack(id);
			if(result == 0)
			{
				status = "FAIL";
			}
			else
			{
				status = "SUCCESS";
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in deletedPoliticalFeedBadk method in PoliticalFeedBackService Class",e);
		}
		return status;
	}
	
	 public String savePFBReportForPdf( Long userId, Long pfbId, String description, String path)
	 {
		 String string = "invalid";
		 try {
			 LOG.info("Enterd into savePFBReportForPdf() in DebateService class");
						String key = pfbReportDAO.getpfbDatils(userId, pfbId);
						if(key == null)
						{
							PfbReport pfbReport = new PfbReport();
							DateUtilService currentDate = new DateUtilService();
							pfbReport.setDescription(escapeUnicode(StringEscapeUtils.unescapeJava(description)));
							pfbReport.setCreatedDate(currentDate.getCurrentDateAndTime());
							ParlimentPoliticalFeedback parlimentPoliticalFeedback = parlimentPoliticalFeedbackDAO.get(pfbId);
							if(parlimentPoliticalFeedback != null)
							{
								pfbReport.setParlimentPoliticalFeedback(parlimentPoliticalFeedback);
							}
							User user = userDAO.get(userId);
							if(user != null)
							{
								pfbReport.setUser(user);
							}
							String key1 = UUID.randomUUID().toString();
							pfbReport.setKey(key1);
							pfbReport.setIsDeleted("N");
							pfbReport = pfbReportDAO.save(pfbReport);
							if(pfbReport == null)
							{
								
								string = "error";
							}
							else
							{
								string = path+"key="+key1;
								
							}
						}
						else
						{
							string = path+"key="+key;
						}
		} catch (Exception e) {
			LOG.error("Error occured in savePFBReportForPdf() in DebateService class",e);
			string = "error";
		}
		 return string;
	 }
	 
	 
	 public String deltePfbReportDetails(String key)
	 {
		 String status = null;
		 try {
			 LOG.info("Enterd into deltePfbReportDetails() in DebateService class");
			 int count = pfbReportDAO.deletePfdDetails(key);
			 if(count > 0)
			 {
				 status = "success"; 
			 }
		} catch (Exception e) {
			LOG.error("Error occured in deltePfbReportDetails() in DebateService class",e);
		}
		return status;
	 }
	 
	 public Long getPfbDetails(String key)
	 {
		 Long count = 0l;
		 try {
			 LOG.info("Enterd into getPfbDetails() in DebateService class");
			 count = pfbReportDAO.getPfdDetails(key);
		} catch (Exception e) {
			LOG.error("Error occured in getPfbDetails() in DebateService class",e);
		}
		 return count;
	 }
	/* public String buildExcelForPFB(List<PoliticalFeedBackVO> returnList){
			LOG.info("entered into buildExcelForSmsPole()in meetingService class.");
			String filename =null;
			try {
				
				if(returnList != null && returnList.size() > 0)
				{
					
					Random randomNumber = new Random();
					int number = randomNumber.nextInt(100000);
					 filename= "Reports"+"/"+"pfb"+number+".xls";
					//path = "Reports"+"/"+"StateAbstract"+number+".xls";
				    FileOutputStream fileOut = new FileOutputStream(IWebConstants.STATIC_CONTENT_FOLDER_URL+filename);
				    
					HSSFWorkbook workbook = new HSSFWorkbook();// creating excel sheet
					
					HSSFSheet worksheet = workbook.createSheet(" PFB REPORT ");		// sheet 1
					
					buildExcelForPFBView(returnList,worksheet,workbook);
		
					workbook.write(fileOut);
					
					fileOut.close();
				}
				
			} catch (Exception e) {
				LOG.error("Exception raised while buildExcelForSmsPole()in meetingService class.",e);
			}	
			return filename;
			

		}
	 
	 public String buildExcelForPFBView(List<PoliticalFeedBackVO> returnList,HSSFSheet worksheet,HSSFWorkbook workbook)
		{
			LOG.info("entere into  generateXlReportForStateMeeting()in meetingService class.");
			String path = null;
				try
				   {
					HSSFFont font = workbook.createFont();
					font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
					font.setItalic(false);
					font.setFontHeight((short)240);
					HSSFCellStyle style = workbook.createCellStyle();
					style.setFont(font);

					HSSFRow  rowHead = worksheet.createRow((short) 0);
					Cell cellHead = rowHead.createCell(0);
					cellHead.setCellValue("P_CODE");
					cellHead.setCellStyle(style);
					cellHead = rowHead.createCell(1);
					cellHead.setCellValue("PARLIMENT NAME");
					cellHead.setCellStyle(style);
					cellHead = rowHead.createCell(2);
					cellHead.setCellValue("ACTION ITEMS");
					cellHead.setCellStyle(style);
					cellHead = rowHead.createCell(3);
					cellHead.setCellValue("SUMMARY");
					cellHead.setCellStyle(style);
				    int rownum = 1;
				    HSSFRow  row1= worksheet.createRow((short)rownum);
			    	Cell cell = row1.createCell(0);
			    	cell.setCellValue("PARLIAMENT WISE DAILY POLITICAL FEEDBACK REPORT");
			    	cell.setCellStyle(style);
			    	worksheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 3));
				    for (PoliticalFeedBackVO PoliticalFeedBackVO : returnList) 
				    {	
				    	for(String actionItem : PoliticalFeedBackVO.getActionItems())
				    	{
				    		rownum++;
				    		HSSFRow  row = worksheet.createRow((short)rownum);
				    		if(rownum == 2)
					    	{
					    		row.createCell((short)0).setCellValue(PoliticalFeedBackVO.getParlimentId());
					    		row.setHeight((short)100);
					    		row.createCell((short)1).setCellValue(PoliticalFeedBackVO.getParlimentName());
					    		row.setHeight((short)100);
					    		row.createCell((short)3).setCellValue(PoliticalFeedBackVO.getSummary());
					    		row.setHeight((short)100);
					    	}
				    		 row.createCell((short)2).setCellValue(actionItem);
				    		 row.setHeight((short)100);
				    		
				    	}
				       
				    	
				    }  
				    HSSFRow  rowHead1 = worksheet.createRow((short) rownum);
					Cell cellHead1 = rowHead1.createCell(0);
					cellHead1.setCellValue("C_CODE");
					cellHead1.setCellStyle(style);
					cellHead1 = rowHead1.createCell(1);
					cellHead1.setCellValue("CONSTITUENCY NAME");
					cellHead1.setCellStyle(style);
					cellHead1 = rowHead1.createCell(2);
					cellHead1.setCellValue("IMP NEWS");
					cellHead1.setCellStyle(style);
					cellHead1 = rowHead1.createCell(3);
					cellHead1.setCellValue("CM POLITICAL FEEDBACK");
					cellHead1.setCellStyle(style);
					cellHead1 = rowHead1.createCell(4);
					cellHead1.setCellValue("OTHER POLITICAL FEEDBACK");
					cellHead1.setCellStyle(style);
				    HSSFRow  row2= worksheet.createRow((short)rownum++);
			    	Cell cell2 = row2.createCell(0);
			    	cell2.setCellValue("ASSEMBLY WISE DAILY POLITICAL FEEDBACK REPORT");
			    	cell2.setCellStyle(style);
			    	worksheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 4));
			    	for (PoliticalFeedBackVO PoliticalFeedBackVO : returnList)
			    	{
						for (PoliticalFeedBackVO PoliticalFeedBackVO1 : PoliticalFeedBackVO.getPoliticalFeedBackVOList())
						{
							HSSFRow  row = worksheet.createRow((short)rownum);
				    		row.createCell((short)0).setCellValue(PoliticalFeedBackVO1.getConstituencyId());
				    		row.setHeight((short)200);
					    	row.createCell((short)1).setCellValue(PoliticalFeedBackVO1.getAssemblyName());
					    	row.setHeight((short)200);
					    	row.createCell((short)3).setCellValue(PoliticalFeedBackVO1.getCmPoliticalFeedBack());
					    	row.setHeight((short)200);
				    		row.createCell((short)2).setCellValue(PoliticalFeedBackVO1.getImpNews() );
				    		row.setHeight((short)200);
				    		row.createCell((short)4).setCellValue(PoliticalFeedBackVO1.getOtherPoliticalFeedBack() );
				    		row.setHeight((short)200);
				    		rownum++;
						}
					}
				} 
			    catch (Exception e) 
			    {
			    	LOG.error("Exception raised while generateXlReportForStateMeeting()in meetingService class.",e);
			    }
				return path;
			}
	*/
}
