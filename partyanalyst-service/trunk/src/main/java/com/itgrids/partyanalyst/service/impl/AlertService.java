package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.service.IAlertService;

public class AlertService implements IAlertService{
	private static final Logger LOG = Logger.getLogger(AlertService.class);
	@Autowired
	private ICandidateDAO candidateDAO;
      
	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

	public List<BasicVO> getCandidatesByName(String candidateName){
    	List<BasicVO> list = new ArrayList<BasicVO>();
    	 List<Object[]> candidate=null;
    	 if(candidateName != null && candidateName.length() > 0)
    		 candidate=candidateDAO.getCandidatesByName(candidateName);
    	 else
    		 candidate=candidateDAO.getCandidatesByName();
    	
    	 try{
    		 LOG.info("entered into getCandidatesByName()");
    	 if(candidate !=null && candidate.size()>0){
    		 
    		 for (Object[] objects : candidate) {
    			BasicVO vo= new BasicVO();
    			 vo.setId(Long.valueOf(objects[0].toString()));//candidate id
    			 vo.setName(objects[1].toString());//last name
    			 list.add(vo);
			}
    	 }
    	 }
    	 catch (Exception e) {
    		 e.printStackTrace();
			LOG.error("Exception ocured in getCandidatesByName()"+e);
		}
		return list;
    }
    
    

}
