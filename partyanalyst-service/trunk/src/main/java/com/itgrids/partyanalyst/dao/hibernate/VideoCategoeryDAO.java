package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVideoCategoeryDAO;
import com.itgrids.partyanalyst.model.VideoCategory;

public class VideoCategoeryDAO extends GenericDaoHibernate<VideoCategory, Long> implements IVideoCategoeryDAO{

	public VideoCategoeryDAO() {
		super(VideoCategory.class);
		// TODO Auto-generated constructor stub
	}
}
