package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUnionTabUserDAO;
import com.itgrids.partyanalyst.model.UnionTabUser;

public class UnionTabUserDAO extends GenericDaoHibernate<UnionTabUser, Long> implements IUnionTabUserDAO{

	public UnionTabUserDAO() {
		super(UnionTabUser.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<UnionTabUser> checkUserExists(String uname,String pwd)
	{
		Query query = getSession().createQuery("select model from UnionTabUser model where model.isDeleted = 'N' and model.isEnabled ='Y'" +
				" and model.userName = :uname and model.passWord = :pwd");
		query.setParameter("uname", uname);
		query.setParameter("pwd", pwd);
		return query.list();
	}

}
