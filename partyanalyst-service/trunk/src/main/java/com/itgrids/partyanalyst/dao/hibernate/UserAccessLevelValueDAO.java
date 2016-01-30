package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserAccessLevelValueDAO;
import com.itgrids.partyanalyst.model.UserAccessLevelValue;

public class UserAccessLevelValueDAO extends GenericDaoHibernate<UserAccessLevelValue,Long> implements IUserAccessLevelValueDAO{

	public UserAccessLevelValueDAO(){
		super(UserAccessLevelValue.class);
	}
	
	public List<Object[]> getAccessValuesOfUserId(Long userId){
		Query query = getSession().createQuery(" select model.accessLevel.accessLevelId," +
				" model.accessLevelValue," +
				" model.accessLevel.accessLevel " +
				" from UserAccessLevelValue model " +
				" where model.user.userId =:userId");
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getAccessValuesByUser(Long userId,String type){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.accessLevel.accessLevelId," +
				" model.accessLevelValue," +
				" model.accessLevel.accessLevel " +
				" from UserAccessLevelValue model " +
				" where model.user.userId =:userId");
		if(type.equalsIgnoreCase("ward")){
			sb.append(" and model.accessLevel.accessLevelId = 8 ");
		}
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getAssignedWardsByUser(Long userId){
		
		Query query = getSession().createQuery(" select C.constituencyId," +
							" C.name, " +
							" GMW.divisionName " +
							" from UserAccessLevelValue UALV,Constituency C,GreaterMuncipalWard GMW " +
							" where UALV.accessLevelValue = C.constituencyId " +
							" and C.constituencyId = GMW.wardId and UALV.accessLevelId = 8 " +
							" and UALV.userId = :userId order by C.name ");
		query.setParameter("userId", userId);
		return query.list();
	}
}
