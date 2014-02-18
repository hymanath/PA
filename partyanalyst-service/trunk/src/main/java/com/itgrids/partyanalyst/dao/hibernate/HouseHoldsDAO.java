package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHouseHoldsDAO;
import com.itgrids.partyanalyst.model.HouseHolds;


public class HouseHoldsDAO extends GenericDaoHibernate<HouseHolds,Long> implements IHouseHoldsDAO {
	
	public HouseHoldsDAO() {
		super(HouseHolds.class);
	}
	
	public List<HouseHolds> getHouseHoldInfoByPanchayatOrLocalId(Long panchayatId,Long localBodyId,String houseNo){
		StringBuffer sb=new StringBuffer();
		sb.append("select model from HouseHolds model");
		if(panchayatId!=null){
			sb.append(" where model.panchayat.panchayatId =:panchayatId");
		}else if(localBodyId!=null){
			sb.append(" where model.localElectionBody.localElectionBodyId =:localBodyId");
		}
		
		sb.append(" and model.houseNo =:houseNo");
		
		String query=sb.toString();
		
		Query qry=getSession().createQuery(query);
		
		if(panchayatId!=null){
			qry.setParameter("panchayatId", panchayatId);
		}
		if(localBodyId!=null){
			qry.setParameter("localBodyId", localBodyId);
		}
			qry.setParameter("houseNo", houseNo);
		
		return qry.list();
	}
	
	
}
