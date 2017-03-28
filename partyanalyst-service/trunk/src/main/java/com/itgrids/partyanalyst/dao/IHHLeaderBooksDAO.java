
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HHLeaderBooks;

public interface IHHLeaderBooksDAO extends GenericDao<HHLeaderBooks, Long>{
	public List<Long> getAllLeaderBooksInConstituency(Long leaderId,Long constituencyId);
	public List<Object[]> getAllBooksInConstituency(Long constituencyId);
	public List<Object[]> getAllBooksNotAssigned();
	public Integer deleteLeaderWithBooks(List<Long> books,Long constituencyId);
	public Integer updateLeaderForBooks(Long bookId,Long leaderId);
	public List<Object[]> getAllLeaderBooksInConstituencyWithNo(Long leaderId,Long constituencyId);
	public List<Object[]> getAllLeaderBooksInConstituencyWithNoOfBookIds(Long leaderId,Long constituencyId,List<Long> bookIds);
	public List<Object[]> getAllBooksInConstituencyWithBookNo(Long constituencyId,Long bookNo);
	public List<Object[]> getAllBooksNotAssignedWithBookNo(Long bookNo);
	public List<Object[]> getBooksOfLeader(Long leaderId);
	public List<Object[]> getAllBooksLeadersInConstituency(Long constituencyId);
}