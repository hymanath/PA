package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import com.itgrids.partyanalyst.dao.ICandidatePartyCategoryDAO;

public class CandidatePartyCategoryDAOHibernateTest extends BenefitDAOHibernateTest{
	
	private ICandidatePartyCategoryDAO candidatePartyCategoryDAO;

	public void setCandidatePartyCategoryDAO(
			ICandidatePartyCategoryDAO candidatePartyCategoryDAO) {
		this.candidatePartyCategoryDAO = candidatePartyCategoryDAO;
	}
	
	/*public void test()
	{
	 candidatePartyCategoryDAO.getAll();	
	}*/
	
	/*public void testgetSelectdGalleryNews()
	{
		List<Object[]> values = candidatePartyCategoryDAO.getSelectdGalleryNews(1301l);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1] +":"+ parms[2] +":"+ parms[3]);
		}
	}
*/
	
	/*public void testgetCountForNewsInASelectedGallery()
	{
		System.out.println(candidatePartyCategoryDAO.getCountForNewsInASelectedGallery(2243l));
	}*/
	
	public void testgetLatestGallerices()
	{
		List<Object[]> values = candidatePartyCategoryDAO.getAllCategoryes();
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1] + ":"+ parms[2] );
		}
	}
}
