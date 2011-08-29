package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.columns.enums.RegistrationColumnNames;
import com.itgrids.partyanalyst.model.Registration;

public class RegistrationDAO extends GenericDaoHibernate<Registration, Long> implements
		IRegistrationDAO {
	
	public RegistrationDAO() {
		super(Registration.class);
	}
	@SuppressWarnings("unchecked")
	public List<Registration> findByProperty(RegistrationColumnNames propertyName, Object value){
		return getHibernateTemplate().find("from Registration where " + propertyName + "=?", value);
	}
	
	public List<Registration> findByFirstName(Object firstName){

		return findByProperty(RegistrationColumnNames.FIRSTNAME, firstName);
	}
	
	public List<Registration> findByUserName(Object userName){

		return findByProperty(RegistrationColumnNames.USERNAME, userName);
	}
	
	public List<Registration> findByMiddleName(Object middleName){

		return findByProperty(RegistrationColumnNames.MIDDLENAME, middleName);
		
	}
	
	public List<Registration> findByLastName(Object lastName){

		return findByProperty(RegistrationColumnNames.LASTNAME, lastName);
	}
	
		
	public List<Registration> findByCountry(Object country){

		return findByProperty(RegistrationColumnNames.COUNTRY, country);
	}
	
	public List<Registration> findByEmail(Object email){

		return findByProperty(RegistrationColumnNames.EMAIL, email);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findUserName(){
		
		return getHibernateTemplate().find("select userName from Registration");
		
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findPasswords(){
		return getHibernateTemplate().find("select password from Registration");
	}
	
	@SuppressWarnings("unchecked")
	public List<Registration> findByUserNameAndPassword(String userName, String password){
		
	 Query queryObject = getSession().createQuery("from Registration as model where model.userName = ? and model.password = ?");
	 queryObject.setParameter(0, userName);
	 queryObject.setParameter(1, password);
	 return queryObject.list(); 

	}
	
	@SuppressWarnings("unchecked")
	public List<Registration> findByUserRegistrationId(Long registrationId) {
		return getHibernateTemplate().find(" from Registration model where model.registrationId = ?", registrationId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Registration> getAllRegisteredUsers() {
		return getHibernateTemplate().find(" select model.registrationId,model.firstName,model.lastName from Registration model ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSubusersByParentUserId(Long parentUserId){
		return getHibernateTemplate().find("select model.parentUser.registrationId , model.registrationId From Registration model where model.parentUser.registrationId=?",parentUserId);
	}
}
