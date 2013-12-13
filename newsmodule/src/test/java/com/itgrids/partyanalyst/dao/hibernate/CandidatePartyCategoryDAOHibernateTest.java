package com.itgrids.partyanalyst.dao.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidatePartyCategoryDAO;
import com.itgrids.partyanalyst.model.File;

public class CandidatePartyCategoryDAOHibernateTest extends BaseDaoTestCase{
	
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
	
/*	public void testgetLatestGallerices()
	{
		List<Object[]> values = candidatePartyCategoryDAO.getAllCategoryes();
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1] + ":"+ parms[2] );
		}
		
		List<File> files= candidatePartyCategoryDAO.getFileListByCandidateId(12631l, 0, 10, "", null, null, null);
		System.out.println(files.size());
		for(File f: files)
		{
			System.out.println(f.getFileId());
		}
	}*/
	
	public void testgetCategorysCountByFileId(){
		Set<Long> fids = new HashSet<Long>();
		fids.add(51l);
		fids.add(50l);
		fids.add(49l);
		fids.add(39l);
		fids.add(42l);
		fids.add(43l);
		fids.add(40l);
		fids.add(41l);
		fids.add(44l);
		fids.add(45l);
		List<Object[]> files= candidatePartyCategoryDAO.getCategorysCountByFileId(fids);
		System.out.println(files.size());
		for(Object[] param:files)
			System.out.println(param[0]+"-"+param[1]);
	}
}
