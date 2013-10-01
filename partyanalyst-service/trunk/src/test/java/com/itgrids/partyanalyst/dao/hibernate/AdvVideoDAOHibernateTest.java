package com.itgrids.partyanalyst.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAdvVideoDAO;
import com.itgrids.partyanalyst.model.AdvVideo;

public class AdvVideoDAOHibernateTest extends BaseDaoTestCase{

	private IAdvVideoDAO advVideoDAO ;

	public void setAdvVideoDAO(IAdvVideoDAO advVideoDAO) {
		this.advVideoDAO = advVideoDAO;
	}
	
	
	/*public void testgetTopVideosForDisplaying()
	{
		List<AdvVideo> values = advVideoDAO.getTopVideosForDisplaying(0,4);
		for (AdvVideo advVideo : values) {
			System.out.println("--------------------------");
			System.out.println(advVideo.getAdvVideoId());
			System.out.println(advVideo.getCode());
			System.out.println(advVideo.getThumbnailUrl());
			System.out.println("--------------------------");
			
		}
	}*/
	
	/*public void testgetSelectdVideoToDisplay()
	{
		List<AdvVideo> values = advVideoDAO.getSelectdVideoToDisplay(1l);
		for (AdvVideo advVideo : values) {
			System.out.println("--------------------------");
			System.out.println(advVideo.getAdvVideoId());
			System.out.println(advVideo.getCode());
			System.out.println(advVideo.getThumbnailUrl());
			System.out.println("--------------------------");
			
		}
	}*/
	
	/*public void testSaveVideo()
	{
		SimpleDateFormat  df = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
		AdvVideo advVideo = new AdvVideo();
		advVideo.setCode("abc");
		advVideo.setDescription("assa");
		advVideo.setDuration(20);
		advVideo.setShow("eewwe");
		advVideo.setTags("sddsd");
		advVideo.setThumbnailUrl("dfgfgf");
		try {
			advVideo.setUpdateTime(df.parse("2013-09-25 19:11:45"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		advVideo.setVideoCategory(null);
		advVideoDAO.save(advVideo);
	}*/
	
	public void testVideos() throws ParseException
	{
		SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = df.parse("2013-09-24");
		Date toDateDate = df.parse("2013-09-27");
		List<AdvVideo> values =  advVideoDAO.getSelectedVideosForDates(fromDate,toDateDate);
		for (AdvVideo advVideo : values) {
			System.out.println("--------------------------");
			System.out.println(advVideo.getAdvVideoId());
			System.out.println(advVideo.getCode());
			System.out.println(advVideo.getThumbnailUrl());
			System.out.println("--------------------------");
			
		}
	}
	
}
