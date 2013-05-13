package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IInfluencingPeoplePositionDAO;
import com.itgrids.partyanalyst.model.InfluencingPeoplePosition;

public class InfluencingPeoplePositionDAOHibernateTest extends BaseDaoTestCase {

	private IInfluencingPeoplePositionDAO influencingPeoplePositionDAO;

	public IInfluencingPeoplePositionDAO getInfluencingPeoplePositionDAO() {
		return influencingPeoplePositionDAO;
	}

	public void setInfluencingPeoplePositionDAO(
			IInfluencingPeoplePositionDAO influencingPeoplePositionDAO) {
		this.influencingPeoplePositionDAO = influencingPeoplePositionDAO;
	}
	
	/*public void testFindByStaticPosition(){
		List result = influencingPeoplePositionDAO.findByStaticPosition("mptc",1l);
		System.out.println(result.size());
	}	

	public void testGetPositionNameAndId(){
		List<InfluencingPeoplePosition>  result = influencingPeoplePositionDAO.getPositionNameAndId();
		for(InfluencingPeoplePosition influencingPeoplePosition : result){
			System.out.println(influencingPeoplePosition.getInfluencingPeoplePositionId()+"\t\t"+influencingPeoplePosition.getPosition());
		}
	}
	*/
	
	/*public void testGetPositionNameAndId(){
		List<InfluencingPeoplePosition>  result = influencingPeoplePositionDAO.getAll();
		for(InfluencingPeoplePosition influencingPeoplePosition : result){
			System.out.println(influencingPeoplePosition.getInfluencingPeoplePositionId()+"\t\t"+influencingPeoplePosition.getPosition());
		}
	}*/
	
	public void testGetPositionNameByUserId()
	{
		List<InfluencingPeoplePosition>  result = influencingPeoplePositionDAO.getPositionNameByUserId(1l);
		System.out.println(result.size());
		if(result != null && result.size() > 0)
		for(InfluencingPeoplePosition influencingPeoplePosition : result){
			System.out.println(influencingPeoplePosition.getInfluencingPeoplePositionId()+"\t\t"+influencingPeoplePosition.getPosition());
		}
	}
}
