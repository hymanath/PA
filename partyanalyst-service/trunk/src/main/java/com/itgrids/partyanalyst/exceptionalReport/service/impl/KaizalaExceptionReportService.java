package com.itgrids.partyanalyst.exceptionalReport.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IKaizalaAnswerInfoDAO;
import com.itgrids.partyanalyst.dto.KaizalaExceptionalReportVO;
import com.itgrids.partyanalyst.exceptionalReport.service.IKaizalaExceptionReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

public class KaizalaExceptionReportService implements IKaizalaExceptionReportService {
	private final static Logger LOG = Logger.getLogger(KaizalaExceptionReportService.class);
	
	private DateUtilService dateUtilService;
	private CommonMethodsUtilService commonMethodsUtilService;
	private SetterAndGetterUtilService setterAndGetterUtilService;
	private IKaizalaAnswerInfoDAO kaizalaAnswerInfoDAO;
	private IConstituencyDAO constituencyDAO;
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}
	public void setKaizalaAnswerInfoDAO(IKaizalaAnswerInfoDAO kaizalaAnswerInfoDAO) {
		this.kaizalaAnswerInfoDAO = kaizalaAnswerInfoDAO;
	}
	
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public List<KaizalaExceptionalReportVO> getConstituencyWisePoorPerformance(Long stateId,int size,String location){
		try{
			int countPosition;
			if(location != null && location.trim().equalsIgnoreCase("constituency")){
				countPosition = 2;
			}else{
				countPosition = 1;
			}
			List<Object[]> targetList = kaizalaAnswerInfoDAO.getConstituencyWiseTargetList(stateId,location);
			
			List<Object[]> installedCommitteeMembers = kaizalaAnswerInfoDAO.getConstituencyWiseInstalledCommitteeMembers(stateId,location);
			//create Map for constituencyId and installedCommitteeMembersCount
			Map<Long,Long> constituencyIdAndInstalledCommitteeMembersCount = new HashMap<Long,Long>();
			if(installedCommitteeMembers != null && installedCommitteeMembers.size() > 0){
				for(Object[] param : installedCommitteeMembers){
					if(param[0] != null){
						constituencyIdAndInstalledCommitteeMembersCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[countPosition]));
					}
				}
			}
			List<Object[]> notHavingSmartPhone = kaizalaAnswerInfoDAO.getConstituencyWiseNotHavingSmartPhone(stateId,location);
			//create map for constituencyId and notHavingSmartPhoneCount
			Map<Long,Long> constituencyIdAndNotHavingSmartPhoneCount = new HashMap<Long,Long>();
			if(notHavingSmartPhone != null && notHavingSmartPhone.size() > 0){
				for(Object[] param : notHavingSmartPhone){
					if(param[0] != null){
						constituencyIdAndNotHavingSmartPhoneCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[countPosition]));
					}
				}
			}
			List<Object[]> publicInstalled = kaizalaAnswerInfoDAO.getConstituencyWisePublicInstalled(stateId,location);
			//create a map for constituencyId and publicInstalledCount
			Map<Long,Long> constituencyIdAndPublicInstalledCount = new HashMap<Long,Long>();
			if(publicInstalled != null && publicInstalled.size() > 0){
				for(Object[] param : publicInstalled){
					if(param[0] != null){
						constituencyIdAndPublicInstalledCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[countPosition]));
					}
				}
			}
			List<Object[]> cadreInstalled = kaizalaAnswerInfoDAO.getConstituecnyWiseCadreInstalled(stateId,location);
			//create a map for constituencyId and cadreInstalledCount
			Map<Long,Long> constituencyIdAndCadreInstalledCount = new HashMap<Long,Long>();
			if(cadreInstalled != null && cadreInstalled.size() > 0){
				for(Object[] param : cadreInstalled){
					if(param[0] != null){
						Long count = constituencyIdAndCadreInstalledCount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(count == null){
							count= commonMethodsUtilService.getLongValueForObject(param[countPosition]);
							constituencyIdAndCadreInstalledCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), count);
						}else{
							count = count+ commonMethodsUtilService.getLongValueForObject(param[countPosition]);
							constituencyIdAndCadreInstalledCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), count);
						}
					}
				}
			}
			//collect all the constituencyId and name
			Set<Long> conIds = new HashSet<Long>();
			Set<Long> parIds = new HashSet<Long>();
			if(targetList != null && targetList.size() > 0){
				for(Object[] param : targetList){
					if(location != null && location.trim().equalsIgnoreCase("constituency")){
						conIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
						parIds.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					}else{
						parIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
					
				}
			}
			
			//get the name of constituency
			List<Object[]> constList = null;
			if(conIds != null && conIds.size() > 0){
				constList = constituencyDAO.getConstituenciesNamesByIds(new ArrayList<Long>(conIds));
			}
			//create Map for constituencyId and name
			Map<Long,String> constituencyIdAndName = new HashMap<Long,String>();
			if(constList != null && constList.size() > 0){
				for(Object[] param : constList){
					constituencyIdAndName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			//get the name of parliament
			List<Object[]> parList = null;
			if(parIds != null && parIds.size() > 0){
				parList = constituencyDAO.getConstituenciesNamesByIds(new ArrayList<Long>(parIds));
			}
			//create map for parliamentId and name
			Map<Long,String> parliamentIdAndName = new HashMap<Long,String>();
			if(parList != null && parList.size() > 0){
				for(Object[] param : parList){
					parliamentIdAndName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			
			//create vo object for ui
			List<KaizalaExceptionalReportVO> exceptionalReportVOs = new ArrayList<KaizalaExceptionalReportVO>();
			KaizalaExceptionalReportVO exceptionalReportVO = null;
			
			if(targetList != null && targetList.size() > 0){
				for(Object[] param : targetList){
					if(param[0] != null){
						exceptionalReportVO = new KaizalaExceptionalReportVO();
						if(location != null && location.trim().equalsIgnoreCase("constituency")){
							exceptionalReportVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[0]));
							exceptionalReportVO.setConstituencyName(constituencyIdAndName.get(commonMethodsUtilService.getLongValueForObject(param[0])));
							exceptionalReportVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[1]));
							exceptionalReportVO.setParliamentName(parliamentIdAndName.get(commonMethodsUtilService.getLongValueForObject(param[1])));
						}else{
							exceptionalReportVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[0]));
							exceptionalReportVO.setParliamentName(parliamentIdAndName.get(commonMethodsUtilService.getLongValueForObject(param[0])));
						}
						//set target,
						exceptionalReportVO.setTarget(commonMethodsUtilService.getLongValueForObject(param[countPosition]));
						//set installed committee member count
						exceptionalReportVO.setCommitteeInstalled(commonMethodsUtilService.getLongValueForObject(constituencyIdAndInstalledCommitteeMembersCount.get(commonMethodsUtilService.getLongValueForObject(param[0]))));
						//set committee having no start phone
						exceptionalReportVO.setCommitteeHavingNoSmartPhone(commonMethodsUtilService.getLongValueForObject(constituencyIdAndNotHavingSmartPhoneCount.get(commonMethodsUtilService.getLongValueForObject(param[0]))));
						//set committee not installed
						exceptionalReportVO.setCommitteeNotInstalled(exceptionalReportVO.getTarget() - ( exceptionalReportVO.getCommitteeInstalled() + exceptionalReportVO.getCommitteeHavingNoSmartPhone() ));
						//set committee not installed percent
						exceptionalReportVO.setCommitteeNotInstalledPer(calculatePercantage( exceptionalReportVO.getCommitteeNotInstalled() , exceptionalReportVO.getTarget() ));
						//set publicInstalled
						exceptionalReportVO.setPublicInstalled(commonMethodsUtilService.getLongValueForObject(constituencyIdAndPublicInstalledCount.get(commonMethodsUtilService.getLongValueForObject(param[0]))));
						//set cadreInstalled
						exceptionalReportVO.setCadreInstalled(commonMethodsUtilService.getLongValueForObject(constituencyIdAndCadreInstalledCount.get(commonMethodsUtilService.getLongValueForObject(param[0]))));
						//set totalInstalled
						exceptionalReportVO.setTotalInstalled(exceptionalReportVO.getCommitteeInstalled() + exceptionalReportVO.getPublicInstalled() + exceptionalReportVO.getCadreInstalled());
						exceptionalReportVOs.add(exceptionalReportVO);
					}
				}
			}
			if(exceptionalReportVOs != null && exceptionalReportVOs.size() > 0){
				Collections.sort(exceptionalReportVOs, new Comparator<KaizalaExceptionalReportVO>() {
					@Override
					public int compare(KaizalaExceptionalReportVO obj1,	KaizalaExceptionalReportVO obj2) {
						Double value1 = obj1.getCommitteeNotInstalledPer();
						Double value2 = obj2.getCommitteeNotInstalledPer();
						return value2.compareTo(value1);
					}
				});
			}
			if(size >= 0){
				if(exceptionalReportVOs.size() > size){
					exceptionalReportVOs = exceptionalReportVOs.subList(0, size);
				}
			}
			return exceptionalReportVOs;
		}catch(Exception e){
			LOG.error("Error occured getConstituencyWisePoorPerformance() method of KaizalaExceptionReportService{}");
		}
		return null;
	}
	public KaizalaExceptionalReportVO getOverallReport(Long stateId){
		try{
			KaizalaExceptionalReportVO  exceptionalReportVO = new KaizalaExceptionalReportVO();
			Long totalTarget = kaizalaAnswerInfoDAO.getTotalTarget(stateId);
			Long totalInstalled = kaizalaAnswerInfoDAO.getTotalInstalled(stateId);
			Long notHavingSmartPhone = kaizalaAnswerInfoDAO.getNotHavingSmartPhone(stateId);
			
			Long notInstalled = totalTarget - (totalInstalled + notHavingSmartPhone);
			exceptionalReportVO.setTarget(totalTarget);
			exceptionalReportVO.setTotalInstalled(totalInstalled);
			exceptionalReportVO.setTotalNotInstalled(notInstalled);
			exceptionalReportVO.setTotalNotHavingSmartphone(notHavingSmartPhone);
			
			return exceptionalReportVO;
		}catch(Exception e){
			LOG.error("Error occured getOverallReport() method of KaizalaExceptionReportService{}");
		}
		return null;
	}
	public Double calculatePercantage(Long subCount,Long totalCount){
		Double d=0.0d;
		if(subCount.longValue()>0l && totalCount.longValue()==0l)
			LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);
	
		if(totalCount.longValue() > 0l){
			d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
	}
}
