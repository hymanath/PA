package com.itgrids.electoralconnect.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.electoralconnect.dao.IAnnouncementFilesDAO;
import com.itgrids.electoralconnect.model.AnnouncementFiles;

public class AnnouncementFilesDAO extends GenericDaoHibernate<AnnouncementFiles, Long> implements IAnnouncementFilesDAO{

		public AnnouncementFilesDAO() {
			super(AnnouncementFiles.class);
		}

		/**
		 * This DAO is used to get All announcemets
		 * @param int startRecord
		 * @param int maxRecord
		 * @return List<AnnouncementFiles>
		 */
		@SuppressWarnings("unchecked")
		public List<AnnouncementFiles> getAllAnnouncements(int startRecord,int maxRecord){
			Query queryObject=getSession().createQuery("select model from AnnouncementFiles model where model.announcements.isDeleted='NO'");
			
			queryObject.setFirstResult(startRecord);
			queryObject.setMaxResults(maxRecord);
			
			return queryObject.list();
		}
		/**
		 * This DAO is used To get The Count For All Announcements
		 * @return int
		 */
		public int getAllAnnouncementsCountOfUser(){
			Query queryObject=getSession().createQuery("select count(model.announcementFilesId) from AnnouncementFiles model where model.announcements.isDeleted='NO'");
			
			return ((Long)queryObject.uniqueResult()).intValue();
		}
	
		/**
		 * This DAO is used to get the Annoncement by annoncement id
		 * @param Long announcementId
		 * @return List<Object[]>
		 * @date 04-07-2013
		 */
		@SuppressWarnings("unchecked")
		public List<Object[]> getAnnoncementById(Long announcementId)
		{
			Query query = getSession().createQuery("select model.announcementFilesId,model.announcements,model.file from AnnouncementFiles model " +
					" where model.announcements.announcementId = :announcementId");
			query.setParameter("announcementId", announcementId);
			return query.list();
		}
		
		/**
		 * This DAO is used to get All Annoncements
		 * @return List<Object[]>
		 * @date 04-07-2013
		 */
		@SuppressWarnings("unchecked")
		public List<Object[]> getAllAnnoncement(Long announcementTypeId)
		{
			Query query = getSession().createQuery("select model.announcementFilesId,model.announcements,model.file from AnnouncementFiles model " +
					" where model.announcements.announcementType.announcementTypeId = :announcementTypeId" );
			query.setParameter("announcementTypeId", announcementTypeId);
			return query.list();
		}
		

		/**
		 * This DAO is used to get Latest Annoncements used in webservice
		 * @return List<Object[]>
		 * @date 13-07-2013
		 * @author sasi
		 */
		@SuppressWarnings("unchecked")
		public List<Object[]> getLatest50Annoncements(Long announcementTypeId)
		{
			Query query = getSession().createQuery("select model.announcementFilesId,model.announcements,model.file from AnnouncementFiles model " +
					" where model.announcements.announcementType.announcementTypeId = :announcementTypeId" );
			query.setParameter("announcementTypeId", announcementTypeId);
			query.setFirstResult(0);
			query.setMaxResults(50);
			return query.list();
		}
		
		
		/**
		 * This DAO is used to get Announcements By Announcement File Id
		 * @param Long announcementFileId
		 * @return List<Object[]>
		 * @date 05-07-2013
		 */
		@SuppressWarnings("unchecked")
		public List<Object[]> getAnnouncementsByAnnouncementFileId(Long announcementFileId)
		{
			Query query = getSession().createQuery("select model.announcementFilesId,model.announcements,model.file from AnnouncementFiles model " +
					" where model.announcementFilesId = :announcementFileId");
			query.setParameter("announcementFileId", announcementFileId);
			return query.list();
		}
		/**
		 * This DAO is used to get the announcements between the selectd dates
		 * @param Date startDate
		 * @param Date endDate
		 * @param int startIndex
		 * @param int maxIndex
		 * @return List<AnnouncementFiles>
		 * @date 08-07-2013
		 */
		@SuppressWarnings("unchecked")
		public List<AnnouncementFiles> getAnnouncemetsBtSelDates(Date startDate,Date endDate,int startIndex,int maxIndex)
		{
			Query query = getSession().createQuery("select model from AnnouncementFiles model " +
					" where Date(model.announcements.updatedTime) between :startDate and :endDate  and model.announcements.isDeleted='NO'");
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setFirstResult(startIndex);
			query.setMaxResults(maxIndex);
			return query.list();
		}
		/**
		 * This DAO is Used To get The Count of Announcemets For Selected Between dates
		 * @param Date startDate
		 * @param Date endDate
		 * @return int
		 * @date 08-07-2013
		 */
		public int getSelBtDatesAnnouncementsCountOfUser(Date startDate,Date endDate){
			Query query=getSession().createQuery("select count(model.announcementFilesId) from AnnouncementFiles model where model.announcements.isDeleted='NO'" +
					" and Date(model.announcements.updatedTime) between :startDate and :endDate  and model.announcements.isDeleted='NO'");
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			return ((Long)query.uniqueResult()).intValue();
		}
		/**
		 * This DAO is used to get all the Announcemetes for selected announcemet type
		 * @param Long typeId
		 * @param int startIndex
		 * @param int maxIndex
		 * @return List<AnnouncementFiles>
		 * @date 08-07-2013
		 */
		@SuppressWarnings("unchecked")
		public List<AnnouncementFiles> getAllAnnouncemetsForSelectedType(Long typeId,int startIndex,int maxIndex)
		{
			Query query = getSession().createQuery("select model from AnnouncementFiles model " +
					" where model.announcements.announcementType.announcementTypeId = :typeId  and model.announcements.isDeleted='NO'");
			query.setParameter("typeId", typeId);
			query.setFirstResult(startIndex);
			query.setMaxResults(maxIndex);
			return query.list();
		}
		/**
		 * This DAO is used to get count for seleted announcenet type
		 * @param Long typeId
		 * @return Long
		 * @date 08-07-2013
		 */
		public Long getCountForSelAnnouncemetType(Long typeId)
		{
			Query query = getSession().createQuery("select count(model.announcementFilesId) from AnnouncementFiles model " +
					" where model.announcements.announcementType.announcementTypeId = :typeId and model.announcements.isDeleted='NO'");
			query.setParameter("typeId", typeId);
			return (Long) query.uniqueResult();
		}


	
}
