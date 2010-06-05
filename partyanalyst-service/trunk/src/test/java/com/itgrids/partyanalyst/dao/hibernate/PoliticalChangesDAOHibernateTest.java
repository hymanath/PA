package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.model.PoliticalChanges;
import com.itgrids.partyanalyst.utils.IConstants;

public class PoliticalChangesDAOHibernateTest extends BaseDaoTestCase {

	private PoliticalChangesDAO politicalChangesDAO;
	private TransactionTemplate transactionTemplate = null;
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public PoliticalChangesDAO getPoliticalChangesDAO() {
		return politicalChangesDAO;
	}
	
	public void setPoliticalChangesDAO(PoliticalChangesDAO politicalChangesDAO) {
		this.politicalChangesDAO = politicalChangesDAO;
	}
/*
	@Test
	public void testGetDataByLocalPoliticalChangesId(){
		List<PoliticalChanges> localPoliticalChanges = politicalChangesDAO.getPartyNameByPoliticalChangesId(1l);
		Assert.assertEquals(localPoliticalChanges.size(), 0);
	}
	
	@Test
	public void testGetSourceNameByLocalPoliticalChangesId(){
		List<PoliticalChanges> localPoliticalChanges = politicalChangesDAO.getSourceNameByPoliticalChangesId(1l);
		Assert.assertEquals(localPoliticalChanges.size(), 0);
	}
*/
	@Test
	public void testGetAllPoliticalChangesByUserId(){
		List localPoliticalChanges = politicalChangesDAO.getAllPoliticalChangesByUserId(5l);
		Assert.assertEquals(localPoliticalChanges.size(), 0);
	}
	
	/*
	public void testDelte(){
		List<PoliticalChangesVO> checkSize = deltePoliticalChangeBasedOnUser(51l);
		System.out.println(checkSize.size());
	}
	public List<PoliticalChangesVO> deltePoliticalChangeBasedOnUser(Long userId){
		List<PoliticalChangesVO> allPoliticalChanges = new ArrayList<PoliticalChangesVO>();		
		List localPoliticalChanges = politicalChangesDAO.getAllPoliticalChangesByUserId(userId,IConstants.TRUE);
		try{			
			for(int i=0;i<localPoliticalChanges.size();i++){
				Object[] parms = (Object[])localPoliticalChanges.get(i);
				PoliticalChangesVO politicalChangesVO = new PoliticalChangesVO();	
				politicalChangesVO.setPoliticalChangeId(Long.parseLong(parms[8].toString()));
				politicalChangesVO.setTitle(parms[0]!=null?parms[0].toString():"");
				politicalChangesVO.setDescription(parms[1]!=null?parms[1].toString():"");
				if(parms[2]!=null){
			        SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
			        String strDateNew = sdf.format((Date)parms[2]);
			        politicalChangesVO.setDate(strDateNew);			       
				}else{
					politicalChangesVO.setDate("");
				}
				politicalChangesVO.setName(parms[3]!=null?parms[3].toString():"");
				politicalChangesVO.setSelectedPersonId(parms[5]!=null?Long.parseLong(parms[5].toString()):0l);
				politicalChangesVO.setSourceOfInformation(parms[7]!=null?parms[7].toString():"");
				allPoliticalChanges.add(politicalChangesVO);
			}	
			
			return allPoliticalChanges;		
		}catch(Exception e){
			e.printStackTrace();
			log.debug("Exception raised.");
			return null;
		}		
	}
	*/
}
