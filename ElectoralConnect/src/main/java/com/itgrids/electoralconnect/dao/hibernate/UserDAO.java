package com.itgrids.electoralconnect.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.electoralconnect.dao.IUserDAO;
import com.itgrids.electoralconnect.dao.IUserLoginDAO;
import com.itgrids.electoralconnect.model.User;
import com.itgrids.electoralconnect.model.UserLogin;


public class UserDAO extends GenericDaoHibernate<User,Long> implements IUserDAO{
	public UserDAO()
	{
		super(User.class);
	}
	
	/**
	 * This DAO is used for verfying the user entered username and password is correct or wrong
	 * @param  String userName
	 * @param String password
	 * @return List<Object[]>
	 * @date 29-06-2013
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> checkForValidUser(String userName,String password)
	{
		Object[] parms = {userName , password};
		return getHibernateTemplate().find("select model.userProfile,model.userLogin from User model " +
				" where model.userLogin.userName = ? and model.userLogin.password = ? ", parms);
	}
	
	/**
	 * This DAO is Used for updating the new password
	 * @param String password
	 * @param Long userId
	 * @return int
	 */
	public int updatePassword(String password,Long userId)
	{
		Query query = getSession().createQuery("update UserLogin model1 set model1.password = :password " +
				" , model1.isPwdChanged = 'YES' where  model1.userLoginId = :userId ");
		query.setParameter("password", password);
		query.setParameter("userId", userId);
		int x= query.executeUpdate();
		getSession().flush();
		return x;
	}
	
	/**
	 * This DAO is used to get the user details by username
	 * @param String username
	 * @return User
	 */
	public User getUserDetailsByUserName(String username)
	{
		Query query = getSession().createQuery("select model from User model where " +
				" model.userLogin.userName = :username");
		query.setParameter("username", username);
		return (User) query.uniqueResult();
	}
	/**
	 * This DAO is used to get the password details by user id
	 * @param Long userId
	 * @return String
	 * @date 09-07-2013
	 */
	public String getPasswordByUser(Long userId)
	{
		Query query = getSession().createQuery("select model.userLogin.password from User model where " +
				" model.userId = :userId");
		query.setParameter("userId", userId);
		return (String) query.uniqueResult();
	}
}
