/**
 * 
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IDebateParticipantDAO;

/**
 * @author Administrator
 *
 */
public class DebateParticipantDAOHibernateTest extends BaseDaoTestCase{

	private IDebateParticipantDAO debateParticipantDAO;

	public void setDebateParticipantDAO(IDebateParticipantDAO debateParticipantDAO) {
		this.debateParticipantDAO = debateParticipantDAO;
	}

	
	/*public void testAll()
	{
		
	}*/
	
	/*public void testgetPartyWiseDebateAnalysis()
	{
		List<Object[]> values = debateParticipantDAO.getCandidateWiseDebateAnalysis(872l,2938l);
		for (Object[] objects : values) {
			System.out.println(objects[4]);
			System.out.println(objects[5]);
			System.out.println(objects[6]);
		}
	}*/
	
	/*public void testgetDebateTotalScaleForEachParty()
	{
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String fromDateStr = "01/01/2013";
			String toDateStr = "02/05/2014";
			List<Object[]> values = debateParticipantDAO.getTotalDabtesCountsForEachCandidate(sdf.parse(fromDateStr),sdf.parse(toDateStr));
			for (Object[] objects : values) {
				System.out.println(objects[0] +":"+ objects[1] +":"+ objects[2]+":"+ objects[3] +":"+ objects[4]);
			}
		} catch (Exception e) {
		}
		
	}*/
	
	/*public void testgetDebateTotalScaleForEachParty()
	{
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String fromDateStr = "01/01/2013";
			String toDateStr = "02/05/2014";
			List<Object[]> values = debateParticipantDAO.getTotalDabtesCountsForEachCandidate(sdf.parse(fromDateStr),sdf.parse(toDateStr));
			for (Object[] objects : values) {
				System.out.println(objects[0] +":"+ objects[1] +":"+ objects[2]+":"+ objects[3] +":"+ objects[4]);
			}
		} catch (Exception e) {
		}
		
	}*/
	
	/*public void testDebateCandidateCharacteristicsDetails()
	{
		try {
			
			List<Object[]> values = debateParticipantDAO.getDebateCandidateCharacteristicsDetails();
			for (Object[] objects : values) {
				System.out.println((Long)objects[0] +":"+ objects[1].toString() +":"+ (Long)objects[2]+":" + (Long)objects[4]+":"+ objects[5].toString()+ (Long)objects[6]+":"+ objects[7].toString() + ":"+ objects[8].toString());
			}
		} catch (Exception e) {
		}
		
	}*/
/*	public void testgetDebateTotalScaleForEachParty()
	{
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String fromDateStr = "01/01/2013";
			String toDateStr = "02/05/2014";
			List<Object[]> values = debateParticipantDAO.getTotalDabtesCountsForEachCandidate(sdf.parse(fromDateStr),sdf.parse(toDateStr));
			for (Object[] objects : values) {
				System.out.println(objects[0] +":"+ objects[1] +":"+ objects[2]+":"+ objects[3] +":"+ objects[4]);
			}
		} catch (Exception e) {
		}
		
	}*/
	
	public void testgetPartiesAndCandidateIds()
	{
		try {
			List<Object[]> values = debateParticipantDAO.getPartiesAndCanidatesIds();
			for(Object[] objects:values){
				System.out.println(objects[0]+"..."+objects[1]+"..."+objects[2]+"..."+objects[3]+"..."+objects[4]);
			
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
