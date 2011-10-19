package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemExternalSourceDAO;
import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.model.PoliticalChangesInformationSource;
import com.itgrids.partyanalyst.model.ProblemExternalSource;

public class ProblemExternalSourceDAOHibernateTest extends BaseDaoTestCase{

	private IProblemExternalSourceDAO problemExternalSourceDAO;

	public IProblemExternalSourceDAO getProblemExternalSourceDAO() {
		return problemExternalSourceDAO;
	}

	public void setProblemExternalSourceDAO(
			IProblemExternalSourceDAO problemExternalSourceDAO) {
		this.problemExternalSourceDAO = problemExternalSourceDAO;
	}
	
	/*public void testGetAll(){
		List<ProblemExternalSource> list = problemExternalSourceDAO.getAll();
		assertEquals(1, list.size());
	}

	public void testFindByExternalSourceId(){
		List<ProblemExternalSource> regUser = problemExternalSourceDAO.findByProblemExternalSourceId(new Long(1));
		assertEquals(1, regUser.size());
	}
	
	public void testGetExternalPersonDetails(){
		List<ProblemExternalSource> result = problemExternalSourceDAO.getExternalPersonDetails(24l);
		PoliticalChangesVO politicalChangesVO = new PoliticalChangesVO();
		for(ProblemExternalSource resultIterator : result){
			politicalChangesVO.setName(resultIterator.getName());
			System.out.println("a"+resultIterator.getName());
			System.out.println("b"+resultIterator.getMobile());
			System.out.println("c"+resultIterator.getTelePhone());
			System.out.println("d"+resultIterator.getAddress());
			System.out.println("e"+resultIterator.getEmail());
			politicalChangesVO.setMobile(resultIterator.getMobile());
			politicalChangesVO.setTelephoneNo(resultIterator.getTelePhone());
			politicalChangesVO.setAddress(resultIterator.getAddress());
			politicalChangesVO.setEmail(resultIterator.getEmail());
		}
		
	}
	*/
	public void testfindByProblemExternalSourceName(){
		
		List<ProblemExternalSource> result = problemExternalSourceDAO.findByProblemExternalSourceName("");
		for(int i=0;i<result.size();i++)
		System.out.println(result.get(i).getEmail());
		
	}
}
