package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserFavoriteLinksDAO;
import com.itgrids.partyanalyst.model.UserFavoriteLinks;

public class UserFavoriteLinksDAO  extends GenericDaoHibernate<UserFavoriteLinks, Long>implements IUserFavoriteLinksDAO {
	
	
	public UserFavoriteLinksDAO(){		
		super(UserFavoriteLinks.class);		
	}
	
	
	public List<UserFavoriteLinks> getUserFavouriteLinksAction(Long userId){
		
		Query query = getSession().createQuery("select model from UserFavoriteLinks model where model.user.userId = ?");
		
		query.setParameter(0, userId);
		
		return query.list();
		
	}
	
	
	public int deleteUserFavouritelinkById(Long linkId){
		
		
		Query query = getSession().createQuery("delete from UserFavoriteLinks model where model.userFavoriteLinksId = ?");
		
		query.setParameter(0, linkId);
		
		return query.executeUpdate();
	}
	
	
	public List<UserFavoriteLinks> checkForAlreadyExistedOrNot(Long userId,String url){
		
		Query query = getSession().createQuery("select model from UserFavoriteLinks model where model.url = ? and model.user.userId = ?");
		
		query.setParameter(0, url);
		query.setParameter(1, userId);
		return query.list();
		
	}
	
	public List<Object[]> getFavoroteLinksForPublicProfileStreeming(Long userId,Date toDate,Date fromDate)
	{
		Query query = getSession().createQuery("select model.favoriteLinkPage.page," +//0
				" model.url , " +//1
				" model.createdTime," +//2
				" model.user.firstName , " +//3
				" model.user.lastName ," +//4
				" model.user.profileImg " +//5
				" from UserFavoriteLinks model " +
				" where model.user.userId = :userId and date(model.createdTime) >= :fromDate  and " +
				" date(model.createdTime) <= :toDate ");
		query.setParameter("userId", userId);
		query.setParameter("toDate", toDate);
		query.setParameter("fromDate", fromDate);
	 	return query.list();
	}

}
