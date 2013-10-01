package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAdvVideoDAO;
import com.itgrids.partyanalyst.dao.IVideoCategoeryDAO;
import com.itgrids.partyanalyst.dto.AdvVideoVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.AdvVideo;
import com.itgrids.partyanalyst.service.IAdvVideoService;

public class AdvVideoService implements IAdvVideoService{

	private static final Logger LOG = Logger.getLogger(AdvVideoService.class);
	private IAdvVideoDAO advVideoDAO ;
	private TransactionTemplate transactionTemplate = null;
	private IVideoCategoeryDAO  videoCategoeryDAO;
	public IAdvVideoDAO getAdvVideoDAO() {
		return advVideoDAO;
	}

	public void setAdvVideoDAO(IAdvVideoDAO advVideoDAO) {
		this.advVideoDAO = advVideoDAO;
	}

	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	
	public IVideoCategoeryDAO getVideoCategoeryDAO() {
		return videoCategoeryDAO;
	}

	public void setVideoCategoeryDAO(IVideoCategoeryDAO videoCategoeryDAO) {
		this.videoCategoeryDAO = videoCategoeryDAO;
	}

	/**
	 * This Service is used for getting the latest adv videos for displaying in our site
	 * @param int startIndes
	 * @param int maxIndex
	 * @return List<AdvVideoVO>
	 * @author Prasad Thiragabathina
	  * @date 25-09-2013
	 */
	public List<AdvVideoVO> getTopAdvVideosForDisplaying() {
		List<AdvVideoVO> returnList = null;
		try {
			LOG.debug("Entered into getTopAdvVideosForDisplaying() method in AdvVideoService");
			List<AdvVideo> advVideosList = advVideoDAO.getTopVideosForDisplaying(0, 4);
			if(advVideosList != null && advVideosList.size() > 0)
			{
				returnList = new ArrayList<AdvVideoVO>();
				fillAdvVideoVO(returnList,advVideosList);
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getTopAdvVideosForDisplaying() method in AdvVideoService",e);
		}
		
		return returnList;
	}
	
	/**
	 * This Service is used for getting the selectd video to display
	 * @param videoId
	 * @return AdvVideoVO
	 * @author Prasad Thiragabathina 
	 * @date 25-09-2013
	 */
	public AdvVideoVO getVideoForSelected(Long videoId)
	{
		List<AdvVideoVO> advVideoVOList = null;
		try {
			LOG.debug("Entered into getVideoForSelected() method in AdvVideoService");
			List<AdvVideo> videoDetails = advVideoDAO.getSelectdVideoToDisplay(videoId);
			if(videoDetails != null && videoDetails.size() > 0)
			{
				advVideoVOList = new ArrayList<AdvVideoVO>();
				fillAdvVideoVO(advVideoVOList,videoDetails);
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getVideoForSelected() method in AdvVideoService",e);
		}
		
		return advVideoVOList.get(0);
	}
	
	/**
	 * This Service is used for filling the AdvVideoVO for Data storing
	 * @param advVideoVOList
	 * @param videoDetails
	 * @author Prasad Thiragabathina 
	 * @date 25-09-2013
	 */
	public void fillAdvVideoVO(List<AdvVideoVO> advVideoVOList,List<AdvVideo> videoDetails)
	{
		try {
			LOG.debug("Entered into fillAdvVideoVO() method in AdvVideoService");
			for (AdvVideo advVideo : videoDetails) {
				AdvVideoVO advVideoVO = new AdvVideoVO();
				advVideoVO.setAdvVideoId(advVideo.getAdvVideoId());
				advVideoVO.setDescription(advVideo.getDescription() != null ? advVideo.getDescription() : "");
				advVideoVO.setType(advVideo.getVideoCategory() != null ? advVideo.getVideoCategory().getCategory() :"");
				advVideoVO.setCode(advVideo.getCode() != null ? advVideo.getCode() :"");
				advVideoVO.setDuration(advVideo.getDuration() > 0 ? Long.valueOf(advVideo.getDuration()) : 0l);
				advVideoVO.setTitle(advVideo.getShow() != null ? advVideo.getShow() : "");
				advVideoVO.setTags(advVideo.getTags() != null ? advVideo.getTags() : "");
				advVideoVO.setThumbnailUrl(advVideo.getThumbnailUrl() != null ? advVideo.getThumbnailUrl() : "");
				advVideoVOList.add(advVideoVO);
				}
		} catch (Exception e) {
			LOG.error("Exception raised in fillAdvVideoVO() method in AdvVideoService",e);
		}
		
	}
	
	/**
	 * This Service is used for saving the videos 
	 * @param advVideoVO
	 * @return ResultStatus
	 * @date 26-09-1013
	 * @author Prasad Thiragabathina
	 */
	public ResultStatus saveVideos(final AdvVideoVO advVideoVO)
	{
		
		final ResultStatus resultStatus = new ResultStatus();
		final SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			LOG.debug("Entered into saveVideos() method in AdvVideoService");
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {	
					
					AdvVideo advVideo = new AdvVideo();
					advVideo.setCode(advVideoVO.getCode() != null ? advVideoVO.getCode() : "");
					advVideo.setDescription(advVideoVO.getDescription() != null ? advVideoVO.getDescription() : "");
					advVideo.setDuration(advVideoVO.getDuration() > 0 ? advVideoVO.getDuration().intValue() : 0);
					advVideo.setShow(advVideoVO.getTitle() != null ? advVideoVO.getTitle() : "");
					advVideo.setTags(advVideoVO.getTags() != null ? advVideoVO.getTags() : "");
					advVideo.setThumbnailUrl(advVideoVO.getThumbnailUrl() != null ? advVideoVO.getThumbnailUrl() : "");
					try {
						advVideo.setUpdateTime(df.parse(advVideoVO.getTime()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					advVideo.setVideoCategory(videoCategoeryDAO.get(advVideoVO.getAdvVideoId()));
					
					advVideoDAO.save(advVideo);
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				}	
				});
		} catch (Exception e) {
			LOG.error("Exception raised in saveVideos() method in AdvVideoService",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		
		return resultStatus;
	}
	/**
	 * This Service is used for getting the all videos for selected dates
	 * @param String fromDate
	 * @param String toDate
	 * @return List<AdvVideoVO>
	 * @date 26-09-2013
	 * @author Prasad Thiragabathina
	 */
	public List<AdvVideoVO> getVideosForSelectedDates(String fromDate,String toDate) throws ParseException
	{
		List<AdvVideoVO> returnList = null;
		try {
			LOG.debug("Entered into getVideosForSelectedDates() method in AdvVideoService");
			SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
			Date newFromDate = df.parse(fromDate);
			Date newToDate = df.parse(toDate);
			List<AdvVideo> advVideosList = advVideoDAO.getSelectedVideosForDates(newFromDate, newToDate);
			if(advVideosList != null && advVideosList.size() > 0)
			{
				returnList= new ArrayList<AdvVideoVO>();
				fillAdvVideoVO(returnList,advVideosList);
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getVideosForSelectedDates() method in AdvVideoService",e);
		}
		
		return returnList;
	}
	
	
}
