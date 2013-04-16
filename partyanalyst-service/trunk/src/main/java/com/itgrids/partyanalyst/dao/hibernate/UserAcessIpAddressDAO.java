package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserAcessIpAddressDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.model.Locality;
import com.itgrids.partyanalyst.model.UserAcessIpAddress;

public class UserAcessIpAddressDAO extends GenericDaoHibernate<UserAcessIpAddress, Long> implements IUserAcessIpAddressDAO {
	public UserAcessIpAddressDAO() {
		super(UserAcessIpAddress.class);
		
	}

	@Override
	public List<?> checkForAccess(RegistrationVO registrationVO,
			String IpAddress) {
		
				Query query = getSession().createQuery("select distinct model.ipAddress  from UserAcessIpAddress model  " +
				  "join model._userId where "+
				"model._userId.userId = :id and model.ipAddress = :ipId  ");
		
		
		/*query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("userId", userId);*/
		query.setParameter("id", registrationVO.getRegistrationID());
		query.setParameter("ipId", IpAddress);
		
		return query.list();
	}
	
	
}
