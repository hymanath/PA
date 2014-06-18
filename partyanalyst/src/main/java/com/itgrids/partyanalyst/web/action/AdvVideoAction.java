package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AdvVideoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IAdvVideoService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AdvVideoAction extends ActionSupport implements ServletRequestAware  {


	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(AdvVideoAction.class);
	transient private HttpServletRequest request;
	private String task = null;
	private IAdvVideoService advVideoService;
	private AdvVideoVO advVideoVO;
	transient private JSONObject jobj = null;
	private String showName;
	private String date;
	private Long duration;
	private String thumb;
	private String description;
	private String tag;
	private String code;
	private Long categoery;
	private ResultStatus resultStatus;
	private List<AdvVideoVO> advVideoList;
	
	
	public List<AdvVideoVO> getAdvVideoList() {
		return advVideoList;
	}
	public void setAdvVideoList(final List<AdvVideoVO> advVideoList) {
		this.advVideoList = advVideoList;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(final ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
	}
	public IAdvVideoService getAdvVideoService() {
		return advVideoService;
	}
	public void setAdvVideoService(final IAdvVideoService advVideoService) {
		this.advVideoService = advVideoService;
	}
	
	
	public AdvVideoVO getAdvVideoVO() {
		return advVideoVO;
	}
	public void setAdvVideoVO(final AdvVideoVO advVideoVO) {
		this.advVideoVO = advVideoVO;
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(final String task) {
		this.task = task;
	}
	
	
	public String getShowName() {
		return showName;
	}
	public void setShowName(final String showName) {
		this.showName = showName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(final String date) {
		this.date = date;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(final Long duration) {
		this.duration = duration;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(final String thumb) {
		this.thumb = thumb;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(final String tag) {
		this.tag = tag;
	}
	public String getCode() {
		return code;
	}
	public void setCode(final String code) {
		this.code = code;
	}
	public Long getCategoery() {
		return categoery;
	}
	public void setCategoery(final Long categoery) {
		this.categoery = categoery;
	}
	public String execute()
	{
		final HttpSession session = request.getSession();
		
		final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
		{
			return Action.ERROR;
		}
		
		return Action.SUCCESS;
	}
	/**
	 * This methid is used for getting the selected video details
	 * @return String 
	 * @date 25-09-2013
	 * @author Prasad Thiragabathina
	 */
	public String getAdvVideoForDisplay()
	{
		LOG.debug("Entered into getAdvVideoForDisplay() method in AdvVideoAction Class");
		final String param = getTask();
		try {
			jobj = new JSONObject(param);
			final Long videoId = jobj.getLong("id");
			advVideoVO = advVideoService.getVideoForSelected(videoId);
		} catch (ParseException e) {
			LOG.error("Exception raised in getAdvVideoForDisplay() method in AdvVideoAction Class",e);
		}
		return Action.SUCCESS;
	}
	
	/**
	 * This methid is used for Saving the video details
	 * @return String 
	 * @date 26-09-2013
	 * @author Prasad Thiragabathina
	 */
	public String saveVideos()
	{
		LOG.debug("Entered into saveVideos() method in AdvVideoAction Class");
		final AdvVideoVO advVideoVO = new AdvVideoVO();
		advVideoVO.setCode(code);
		advVideoVO.setDescription(description);
		advVideoVO.setDuration(duration);
		advVideoVO.setTags(tag);
		advVideoVO.setThumbnailUrl(thumb);
		advVideoVO.setTime(date);
		advVideoVO.setTitle(showName);
		advVideoVO.setAdvVideoId(categoery);
		if(advVideoVO != null)
		{
			resultStatus = advVideoService.saveVideos(advVideoVO);
		}
		
		return Action.SUCCESS;
	}
	
	/**
	 * This methid is used for getting the videos for selected dates
	 * @return String 
	 * @date 26-09-2013
	 * @author Prasad Thiragabathina
	 */
	public String getVideosForSelection()
	{
		try {
			LOG.debug("Entered into getVideosForSelection() method in AdvVideoAction Class");
			final String param = getTask();
			jobj = new JSONObject(param);
			final String fromDate = jobj.getString("fromDate");
			final String toDate   = jobj.getString("toDate");
			advVideoList    = advVideoService.getVideosForSelectedDates(fromDate,toDate);
		} catch (Exception e) {
			LOG.error("Exception raised in getVideosForSelection() method in AdvVideoAction Class",e);
		}
		return Action.SUCCESS;
	}
	

}
