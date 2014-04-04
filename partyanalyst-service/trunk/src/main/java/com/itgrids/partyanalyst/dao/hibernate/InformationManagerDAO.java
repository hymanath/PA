package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IInformationManagerDAO;
import com.itgrids.partyanalyst.model.InformationManager;

public class InformationManagerDAO extends GenericDaoHibernate<InformationManager, Long> implements IInformationManagerDAO{

	public InformationManagerDAO() {
		super(InformationManager.class);
		// TODO Auto-generated constructor stub
	}

	
	@SuppressWarnings("unchecked")
	public List<Object[]> getInformationUsers(Long userId)
	{
		Query query = getSession().createQuery("select model.informationManagerId,model.firstName,model.lastName,model.mobile,model.designation,model.createdTime from InformationManager model where model.parentUser.userId = :userId");
		query.setParameter("userId", userId);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getMobileNosForReceiverIds(List<Long> receiverIds){
		Query queryObj = getSession().createQuery("select model.mobile from InformationManager model " +
				" where model.informationManagerId in(:receiverIds)");
		queryObj.setParameterList("receiverIds", receiverIds);
		return queryObj.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getInformatioManagerIdsByReceiverIds(List<Long> receiverIds){
		Query queryObj = getSession().createQuery("select model.receiverId from UserSmsReceiver model " +
				" where model.userSmsReceiverId in(:receiverIds)");
		queryObj.setParameterList("receiverIds", receiverIds);
		return queryObj.list();
	}
}
