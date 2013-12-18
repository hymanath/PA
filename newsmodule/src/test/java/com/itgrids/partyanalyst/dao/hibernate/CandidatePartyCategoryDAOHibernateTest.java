package com.itgrids.partyanalyst.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidatePartyCategoryDAO;

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
	
	/*public void testgetCategorysCountByFileId(){
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
	}*/
	
	/*public void testgetCategoeryAndConsttituencyWiseNews()
	{
		List<Long> cids = new ArrayList<Long>();
		List<Long> coIds = new ArrayList<Long>();
		coIds.add(232l);
		coIds.add(231l);
		cids.add(1l);
		cids.add(4l);
		cids.add(3990l);
		cids.add(3l);
		cids.add(5l);
		cids.add(3989l);
		cids.add(2l);
		cids.add(7l);
		cids.add(6l);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String fromDateStr = "1/12/2013";
		String toDateStr = "17/12/2013";
		Date fromDate = null;
		try {
			fromDate = format.parse(fromDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date toDate = null;
		try {
			toDate = format.parse(toDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(fromDate);
		System.out.println(toDate);
		List<Object[]> values = candidatePartyCategoryDAO.getCategoeryAndConsttituencyWiseCount(cids,coIds,fromDate,toDate);
		for(Object[] param:values)
			System.out.println(param[0]+"-"+param[1] +"-"+ param[2]);
	}*/
}
