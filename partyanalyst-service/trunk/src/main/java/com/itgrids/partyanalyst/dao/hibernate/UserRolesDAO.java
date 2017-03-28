package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserRolesDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.model.UserRoles;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserRolesDAO extends GenericDaoHibernate<UserRoles, Long>
		implements IUserRolesDAO {

	public UserRolesDAO() {
		super(UserRoles.class);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getUserRoles(Long userId) {
		return getHibernateTemplate()
				.find("select model.role.roleId , model.role.roleType from UserRoles model where model.user.userId =?",
						userId);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllFreeuser() {
		return getHibernateTemplate()
				.find("select model.user.firstName,user.mobile from UserRoles model where role.roleType='FREE_USER'");
	}

	@SuppressWarnings("unchecked")
	public List<String> getUserRolesOfAUser(Long userId) {
		Query query = getSession()
				.createQuery(
						"select model.role.roleType from UserRoles model where model.user.userId =?");
		query.setParameter(0, userId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllUsersMobile(String roleType) {
		return getHibernateTemplate()
				.find("select distinct model.firstName,model.lastName,model.mobile,model.constituency.name,model.userId from UserRoles model where model.role.roleType = ?",
						roleType);
	}

	@SuppressWarnings("unchecked")
	public List<Object> getAllMobilenosAsUnique() {
		return getHibernateTemplate()
				.find("select distinct model.user.mobile from UserRoles model where role.roleType='FREE_USER' and model.user.mobile is not null");
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllFreeusertoSendSms() {
		return getHibernateTemplate()
				.find("select model.user.firstName,model.user.lastName,model.user.mobile,model.user.constituency.name,model.user.userId from UserRoles model where role.roleType='FREE_USER' and model.user.mobile is not null ");

	}

	/* commented by srishailam SendupdatesByemail writen below
	 * @SuppressWarnings("unchecked")
	public List<Object[]> getUsersForSendingEmails(Long selectedState,
			Long selectedDistrict, Long selectedConstituency, Long userType,Long locationScope) {

		String query = null;
		Query query1 = null;
		
		query = "select model.user.userId,model.user.firstName,model.user.lastName,model.user.email,model.user.state.stateName,"
				+ "model.user.district.districtName,model.user.constituency.name from UserRoles model";
		if(userType==0){
			
			if(locationScope==1){
				query=query+" "+"where  model.user.email IS NOT NULL and model.user.email!='' ";
			query1 = getSession().createQuery(query);
			}
			else if(locationScope==2){
				query=query+" "+"where model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? ";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, selectedState);				
			}
			
			else if(locationScope==3){
				query=query+" "+"where model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.user.districtId=?";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, selectedState);
				query1.setParameter(1, selectedDistrict);	
			}
			
			else if(locationScope==4){
				query=query+" "+"where model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.user.districtId=? and model.user.constituencyId=?";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, selectedState);
				query1.setParameter(1, selectedDistrict);	
				query1.setParameter(2, selectedConstituency);
			}
		}
		
		if(userType==1){
			
			if(locationScope==1){
			query=query+" "+"where model.user.email IS NOT NULL and model.user.email!='' and model.role.roleId=?";	
			query1 = getSession().createQuery(query);
			query1.setParameter(0, userType);	
			
			}
			else if(locationScope==2){
				query=query+" "+"where model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.role.roleId=?";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, selectedState);
				query1.setParameter(1, userType);	
			}
			
			else if(locationScope==3){
				query=query+" "+"where model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.user.districtId=? and model.role.roleId=?";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, selectedState);
				query1.setParameter(1, selectedDistrict);	
				query1.setParameter(2, userType);
			}
			
			else if(locationScope==4){
				query=query+" "+"where model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.user.districtId=? and model.user.constituencyId=? and model.role.roleId=?";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, selectedState);
				query1.setParameter(1, selectedDistrict);	
				query1.setParameter(2, selectedConstituency);
				query1.setParameter(3, userType);
			}
		}
		
			if(userType==2){
			
			if(locationScope==1){
				query=query+" "+"where model.user.email IS NOT NULL and model.user.email!='' and model.role.roleId=?";	
				query1 = getSession().createQuery(query);
				query1.setParameter(0, userType);	
			}
			else if(locationScope==2){
				query=query+" "+"where model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.role.roleId=?";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, selectedState);	
				query1.setParameter(1, userType);
			}
			
			else if(locationScope==3){
				query=query+" "+"where model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.user.districtId=? and model.role.roleId=?";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, selectedState);
				query1.setParameter(1, selectedDistrict);	
				query1.setParameter(2, userType);
			}
			
			else if(locationScope==4){
				query=query+" "+"where model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.user.districtId=? and model.user.constituencyId=? and model.role.roleId=?";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, selectedState);
				query1.setParameter(1, selectedDistrict);	
				query1.setParameter(2, selectedConstituency);
				query1.setParameter(3, userType);
			}
		}
			
			if(userType==3){
				
				if(locationScope==1){
				query1 = getSession().createQuery(query);
			}
				else if(locationScope==2){
					query=query+" "+"where model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.role.roleId=?";
					query1 = getSession().createQuery(query);
					query1.setParameter(0, selectedState);	
					query1.setParameter(1, userType);
				}
				
				else if(locationScope==3){
					query=query+" "+"where model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.user.districtId=? and model.role.roleId=?";
					query1 = getSession().createQuery(query);
					query1.setParameter(0, selectedState);
					query1.setParameter(1, selectedDistrict);
					query1.setParameter(2, userType);
				}
				
				else if(locationScope==4){
					query=query+" "+"where model.user.email IS NOT NULL and  model.user.email!='' and model.user.stateId=? and model.user.districtId=? and model.user.constituencyId=? and model.role.roleId=?";
					query1 = getSession().createQuery(query);
					query1.setParameter(0, selectedState);
					query1.setParameter(1, selectedDistrict);	
					query1.setParameter(2, selectedConstituency);
					query1.setParameter(3, userType);
				}
			}
	return query1.list();
	
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getUsersForSendingEmail(RegistrationVO regiVO,String order, String sortBy, int startIndex, int maxIndex,boolean iscount) {
		String query = null;
		Query query1 = null;
		Long userType = Long.valueOf(regiVO.getAccessType());
		Long locationScope = Long.valueOf(regiVO.getAccessValue());
		if(sortBy.equalsIgnoreCase("name"))
			sortBy = "firstName";
		if(iscount == true)
			query = "select distinct count(model.user.userId),model.user.userId from UserRoles model,User model1 ";
		else
			query = "select model.user.userId,model.user.firstName,model.user.lastName,model.user.email,model.user.state.stateName,"
				+ "model.user.district.districtName,model.user.constituency.name from UserRoles model,User model1 ";
		
		if(userType==0){
			
			if(locationScope==1){
				query=query+" "+"where  model.user.userId = model1.userId and model.user.email IS NOT NULL and model.user.email!='' order by model.user."+sortBy+" "+order+" ";
			query1 = getSession().createQuery(query);
			}
			else if(locationScope==2){
				query=query+" "+"where  model.user.userId = model1.userId and   model.user.userId = model1.userId and  model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? order by model.user."+sortBy+" "+order+" ";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, regiVO.getStateId());				
			}
			
			else if(locationScope==3){
				query=query+" "+"where  model.user.userId = model1.userId and  model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.user.districtId=? order by model.user."+sortBy+" "+order+" ";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, regiVO.getStateId());
				query1.setParameter(1, regiVO.getDistrictId());	
			}
			
			else if(locationScope==4){
				query=query+" "+"where  model.user.userId = model1.userId and  model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.user.districtId=? and model.user.constituencyId=? order by model.user."+sortBy+" "+order+" ";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, regiVO.getStateId());
				query1.setParameter(1, regiVO.getDistrictId());	
				query1.setParameter(2, regiVO.getConstituencyId());
			}
		}
		
		if(userType==1){
			
			if(locationScope==1){
			query=query+" "+"where  model.user.userId = model1.userId and  model.user.email IS NOT NULL and model.user.email!='' and model.role.roleId=? order by model.user."+sortBy+" "+order+" ";	
			query1 = getSession().createQuery(query);
			query1.setParameter(0, userType);	
			
			}
			else if(locationScope==2){
				query=query+" "+"where  model.user.userId = model1.userId and  model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.role.roleId=? order by model.user."+sortBy+" "+order+" ";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, regiVO.getStateId());
				query1.setParameter(1, userType);	
			}
			
			else if(locationScope==3){
				query=query+" "+"where  model.user.userId = model1.userId and  model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.user.districtId=? and model.role.roleId=? order by model.user."+sortBy+" "+order+" ";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, regiVO.getStateId());
				query1.setParameter(1, regiVO.getDistrictId());	
				query1.setParameter(2, userType);
			}
			
			else if(locationScope==4){
				query=query+" "+"where  model.user.userId = model1.userId and  model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.user.districtId=? and model.user.constituencyId=? and model.role.roleId=? order by model.user."+sortBy+" "+order+" ";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, regiVO.getStateId());
				query1.setParameter(1, regiVO.getDistrictId());	
				query1.setParameter(2, regiVO.getConstituencyId());
				query1.setParameter(3, userType);
			}
		}
		
			if(userType==2){
				
				if(regiVO.getUserType().equalsIgnoreCase("Volunteer"))
				{
					query=query+" ,UserProfileOpts model2 where  model.user.userId = model1.userId and model2.user.userId = model.user.userId and model2.profileOpts.profileOptsId = 2 and ";
				}
				else 
					query=query+" where  model.user.userId = model1.userId and  ";
			if(locationScope==1){
				query=query+" "+" model.user.email IS NOT NULL and model.user.email!='' and model.role.roleId=? order by model.user.firstName asc ";	
				query1 = getSession().createQuery(query);
				query1.setParameter(0, userType);	
			}
			else if(locationScope==2){
				query=query+" "+" model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.role.roleId=? order by model.user."+sortBy+" "+order+" ";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, regiVO.getStateId());	
				query1.setParameter(1, userType);
			}
			
			else if(locationScope==3){
				query=query+" "+" model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.user.districtId=? and model.role.roleId=? order by model.user."+sortBy+" "+order+" ";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, regiVO.getStateId());
				query1.setParameter(1, regiVO.getDistrictId());	
				query1.setParameter(2, userType);
			}
			
			else if(locationScope==4){
				query=query+" "+" model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.user.districtId=? and model.user.constituencyId=? and model.role.roleId=? order by model.user.firstName asc ";
				query1 = getSession().createQuery(query);
				query1.setParameter(0, regiVO.getStateId());
				query1.setParameter(1, regiVO.getDistrictId());	
				query1.setParameter(2, regiVO.getConstituencyId());
				query1.setParameter(3, userType);
			}
		}
			
			if(userType==3){
				
				if(locationScope==1){
				query1 = getSession().createQuery(query);
			}
				else if(locationScope==2){
					query=query+" "+"where  model.user.userId = model1.userId and  model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.role.roleId=? order by model.user."+sortBy+" "+order+" ";
					query1 = getSession().createQuery(query);
					query1.setParameter(0, regiVO.getStateId());	
					query1.setParameter(1, userType);
				}
				
				else if(locationScope==3){
					query=query+" "+"where  model.user.userId = model1.userId and  model.user.email IS NOT NULL and model.user.email!='' and model.user.stateId=? and model.user.districtId=? and model.role.roleId=? order by model.user."+sortBy+" "+order+" ";
					query1 = getSession().createQuery(query);
					query1.setParameter(0, regiVO.getStateId());
					query1.setParameter(1, regiVO.getDistrictId());
					query1.setParameter(2, userType);
				}
				
				else if(locationScope==4){
					query=query+" "+"where  model.user.userId = model1.userId and  model.user.email IS NOT NULL and  model.user.email!='' and model.user.stateId=? and model.user.districtId=? and model.user.constituencyId=? and model.role.roleId=? order by model.user."+sortBy+" "+order+" ";
					query1 = getSession().createQuery(query);
					query1.setParameter(0, regiVO.getStateId());
					query1.setParameter(1, regiVO.getDistrictId());	
					query1.setParameter(2, regiVO.getConstituencyId());
					query1.setParameter(3, userType);
				}
			}
			if(iscount == false){
				query1.setFirstResult(startIndex);
				query1.setMaxResults(maxIndex);
			}
	return query1.list();
	
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllRestrictedUsers()
	{
		return getHibernateTemplate().find("select model.user.userId,model.user.firstName,model.user.lastName from UserRoles model where model.user._loginRestriction='"+IConstants.FALSE+"' and model.role.roleType ='PARTY_ANALYST_USER' ");
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllUsers()
	{
		return getHibernateTemplate().find("select model.user.userId,model.user.firstName,model.user.lastName from UserRoles model where model.user._loginRestriction='"+IConstants.TRUE+"' and model.role.roleType ='PARTY_ANALYST_USER' ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllUsersByRoleType(String roleType)
	{
		return getHibernateTemplate().find(" select distinct model.user.userId,model.user.firstName,model.user.lastName from UserRoles model where model.role.roleType = ? order by model.user.firstName ",roleType);
	}
	
	
	public Long checkForUserPublicStreeming(Long userId)
	{
		Query query = getSession().createQuery("select model.user.userId from UserRoles model " +
				" where model.role.roleId = 2 and model.user.userId = :userId ");
		query.setParameter("userId", userId);
		return (Long)query.uniqueResult();
	}

}
