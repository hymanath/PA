package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHHLeaderBooksDAO;
import com.itgrids.partyanalyst.model.HHLeaderBooks;


public class HHLeaderBooksDAO extends GenericDaoHibernate<HHLeaderBooks,Long> implements IHHLeaderBooksDAO {
	
	public HHLeaderBooksDAO() {
		super(HHLeaderBooks.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAllLeaderBooksInConstituency(Long leaderId,Long constituencyId){
		Query query= getSession().createQuery(" select distinct model.hhLeaderBookId from HHLeaderBooks model,HHBoothLeader model1 where" +
				" model.leader.id = model1.hhLeader.id and " +
				" model1.constituency.constituencyId = :constituencyId and " +
				" model.leader.id = :leaderId and model.leader.id is not null");
		query.setParameter("leaderId", leaderId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getAllLeaderBooksInConstituencyWithNo(Long leaderId,Long constituencyId){
		Query query= getSession().createQuery(" select distinct model.hhLeaderBookId,model.bookNo from HHLeaderBooks model,HHBoothLeader model1 where" +
				" model.leader.id = model1.hhLeader.id and " +
				" model1.constituency.constituencyId = :constituencyId and " +
				" model.leader.id = :leaderId and model.leader.id is not null");
		query.setParameter("leaderId", leaderId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllBooksInConstituency(Long constituencyId){
		Query query= getSession().createQuery(" select distinct model.hhLeaderBookId,model.bookNo from HHLeaderBooks model,HHBoothLeader model1 where " +
				" model.leader.id = model1.hhLeader.id and " +
				" model1.constituency.constituencyId = :constituencyId" +
				" and model1.hhLeader.is_active ='YES'" +
				" and model.leader.id is not null");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getAllBooksNotAssigned(){
		Query query = getSession().createQuery(" select model.hhLeaderBookId,model.bookNo from HHLeaderBooks model where model.leader.id is null");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllBooksInConstituencyWithBookNo(Long constituencyId,Long bookNo){
		Query query= getSession().createQuery(" select distinct model.hhLeaderBookId,model.bookNo from HHLeaderBooks model,HHBoothLeader model1 where " +
				" model.leader.id = model1.hhLeader.id and " +
				" model1.constituency.constituencyId = :constituencyId" +
				" and model.bookNo = :bookNo " +
				" and model.leader.id is not null");
		query.setParameter("bookNo", bookNo);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getAllBooksNotAssignedWithBookNo(Long bookNo){
		Query query = getSession().createQuery(" select model.hhLeaderBookId,model.bookNo from HHLeaderBooks model where " +
				" model.bookNo = :bookNo and" +
				" model.leader.id is null");
		
		query.setParameter("bookNo", bookNo);
		return query.list();
	}
	
	public Integer deleteLeaderWithBooks(List<Long> books,Long constituencyId){
		Query query = getSession().createQuery("update HHLeaderBooks model set model.leader.id = null where" +
				" model.hhLeaderBookId in(:books)");
		
		query.setParameterList("books", books);
		return query.executeUpdate();
	}
	
	public Integer updateLeaderForBooks(Long bookId,Long leaderId){
		Query query = getSession().createQuery(" update HHLeaderBooks model set model.leader.id =:leader where" +
				" model.hhLeaderBookId =:bookId");
		query.setParameter("bookId", bookId);
		query.setParameter("leader",leaderId);
		return query.executeUpdate();
	}
	
	public List<Object[]> getAllLeaderBooksInConstituencyWithNoOfBookIds(Long leaderId,Long constituencyId,List<Long> bookIds){
		Query query= getSession().createQuery(" select distinct model.hhLeaderBookId,model.bookNo from HHLeaderBooks model,HHBoothLeader model1 where" +
				" model.leader.id = model1.hhLeader.id and " +
				" model1.constituency.constituencyId = :constituencyId and " +
				" model.hhLeaderBookId in (:bookIds) and " +
				" model.leader.id = :leaderId and model.leader.id is not null");
		query.setParameter("leaderId", leaderId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("bookIds", bookIds);
		
		return query.list();
	}
	
	public List<Object[]> getBooksOfLeader(Long leaderId){
		Query query= getSession().createQuery(" select distinct model.hhLeaderBookId,model.bookNo from HHLeaderBooks model,HHBoothLeader model1 where" +
				" model.leader.id = model1.hhLeader.id and " +
				" model.leader.id = :leaderId and model.leader.id is not null");
		query.setParameter("leaderId", leaderId);
		return query.list();
	}
	
		
	public List<Object[]> getAllBooksLeadersInConstituency(Long constituencyId){
		Query query= getSession().createQuery(" select distinct model.hhLeaderBookId," +
		" model.bookNo," +
		" model.leader.name," +
		" model.leader.voterId," +
		" model.leader.mobileNo," +
		" model.leader.id " +
	//	" model1.booth.panchayat.panchayatId," +
	//	" model1.booth.panchayat.panchayatName" +
		" from HHLeaderBooks model,HHBoothLeader model1 where " +
		" model.leader.id = model1.hhLeader.id and " +
		" model1.constituency.constituencyId = :constituencyId" +
		" and model1.hhLeader.is_active ='YES'" +
		" and model.leader.id is not null");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
		}
}
