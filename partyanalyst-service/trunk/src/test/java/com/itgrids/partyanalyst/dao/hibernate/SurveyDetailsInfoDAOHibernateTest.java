package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.ConstituencyDetailReportVO;
import com.itgrids.partyanalyst.dto.GenericVO;

public class SurveyDetailsInfoDAOHibernateTest extends BaseDaoTestCase{
	private ISurveyDetailsInfoDAO surveyDetailsInfoDAO;
	private IVoterInfoDAO voterInfoDAO;

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public void setSurveyDetailsInfoDAO(ISurveyDetailsInfoDAO surveyDetailsInfoDAO) {
		this.surveyDetailsInfoDAO = surveyDetailsInfoDAO;
	}
	
	public void testDetails()
	{
		/*List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(371197l);
		boothIds.add(371198l);
		 List<Object[]> list = surveyDetailsInfoDAO.getVoterDetailsForbooths(boothIds);
		 System.out.println(list.size());*/
		
		// List<Object[]> constituencyCollectdDetls = surveyDetailsInfoDAO.getsurveyDetailsInfoByboothId(232L,1L);
		// List<Object[]> constituencyVerifiedDetls = surveyDetailsInfoDAO.getsurveyDetailsInfoByboothId(232L,4L);
		// List<Object[]> constituencyBoothInfo = surveyDetailsInfoDAO.getBoothDetailsByConstituencyId(232L);
		 
		 List<Object[]> constituencyCollectdDetls = surveyDetailsInfoDAO.getsurveyDetailsInfoByboothId(370993L,1L);
		 List<Object[]> constituencyVerifiedDetls = surveyDetailsInfoDAO.getsurveyDetailsInfoByboothId(370993L,4L);
		 
		 ConstituencyDetailReportVO reportVO = new ConstituencyDetailReportVO();
		 Long totalVoters = voterInfoDAO.getTotalVotersForSelectdLevel(4L, 370993L, 10L, 232L); //(report level id,reportlevelValue,publicationId,constId);
		 if(constituencyCollectdDetls != null && constituencyCollectdDetls.size()>0){
				
			 reportVO.setConstituencyTotalVoters(totalVoters);
			 
			 for (Object[] param : constituencyCollectdDetls) {
				
				reportVO.setTotalColelctedVoters(param[0] != null ? (Long)param[0]:0L);

				Long casteCollectedCount = (param[1] != null ? (Long)param[1]:0L) + (param[2] != null ? (Long)param[2]:0L);
				reportVO.setCasteCollectedCount(casteCollectedCount);
				
				Long hamletCollectedCount =  (param[3] != null ? (Long)param[3]:0L) + (param[4] != null ? (Long)param[4]:0L);
				reportVO.setHamletCollectedCount(hamletCollectedCount);
				
				reportVO.setCadreCollectedCount(param[5] != null ? (Long)param[5]:0L);
				reportVO.setInfluencePeopleCollectedCount(param[6] != null ? (Long)param[6]:0L);
				reportVO.setMobileNoCollectedCount(param[7] != null ? (Long)param[7]:0L);
				
				reportVO.setNotCollectedVoters(totalVoters - (param[0]!= null?(Long) param[0]:0L));
				}
			 
			 
			 }
		 
			 if(constituencyCollectdDetls != null && constituencyCollectdDetls.size()>0){
				 
				 for (Object[] params : constituencyVerifiedDetls) {
					 
						reportVO.setTotalVerifiedVoters(params[0] != null ? (Long)params[0]:0L);

						Long casteVerifiedCount = (params[1] != null ? (Long)params[1]:0L) + (params[2] != null ? (Long)params[2]:0L);
						reportVO.setCasteVerifiedCount(casteVerifiedCount);
						
						Long hamletVerifiedCount = (params[3] != null ? (Long)params[3]:0L) + (params[4] != null ? (Long)params[4]:0L);
						reportVO.setHamletVerifiedCount(hamletVerifiedCount);
						
						reportVO.setCadreVerifiedCount(params[5] != null ? (Long)params[5]:0L);
						reportVO.setInfluencePeopleVerifiedCount(params[6] != null ? (Long)params[6]:0L);
						reportVO.setMobileNoVerifiedCount(params[7] != null ? (Long)params[7]:0L);
						
						reportVO.setNotVerifiedVoters(totalVoters - (params[0]!= null?(Long) params[0]:0L));
						
				 }
			 }
			 
			/* List<GenericVO> boothsList = new ArrayList<GenericVO>();
			 if(constituencyBoothInfo != null && constituencyBoothInfo.size()>0){
				 
				 for (Object[] booth : constituencyBoothInfo) {
					 GenericVO vo = new GenericVO();
					 vo.setId((Long) booth[0]);
					 vo.setName(booth[1].toString());
					 boothsList.add(vo);					 
				 }
			 }
		 
			if(boothsList != null && boothsList.size()>0){ 
					reportVO.setBoothsList(boothsList);
			}
		 		 */
	}

}
