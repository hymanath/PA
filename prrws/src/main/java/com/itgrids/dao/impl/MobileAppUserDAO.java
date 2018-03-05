package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IMobileAppUserDAO;
import com.itgrids.model.MobileAppUser;

@Repository
public class MobileAppUserDAO extends GenericDaoHibernate<MobileAppUser, Long> implements IMobileAppUserDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public MobileAppUserDAO() {
		super(MobileAppUser.class);		
	}
	
	public List<Object[]> checkLogin(String userName,String password){
		//0-mobileAppUserId,1-fullName,2-username,3-password,4-mobileNo,5-mobileAppUserTypeId,6-userType
		Query query = getSession().createQuery(" select model.mobileAppUserId,model.fullName,model.username,model.password,model.mobileNo,"
				+ " model.mobileAppUserType.mobileAppUserTypeId,model.mobileAppUserType.userType "
				+ " from MobileAppUser model "
				+ " where model.isEnabled='Y' and model.isDeleted='N' "
				+ " and model.username=:userName and model.password=:password ");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return query.list();
	}
}
