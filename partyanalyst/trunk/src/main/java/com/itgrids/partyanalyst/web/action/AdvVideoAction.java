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
	private HttpServletRequest request;
	private String task = null;
	private IAdvVideoService advVideoService;
	private AdvVideoVO advVideoVO;
	JSONObject jobj = null;
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
	public void setAdvVideoList(List<AdvVideoVO> advVideoList) {
		this.advVideoList = advVideoList;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public IAdvVideoService getAdvVideoService() {
		return advVideoService;
	}
	public void setAdvVideoService(IAdvVideoService advVideoService) {
		this.advVideoService = advVideoService;
	}
	
	
	public AdvVideoVO getAdvVideoVO() {
		return advVideoVO;
	}
	public void setAdvVideoVO(AdvVideoVO advVideoVO) {
		this.advVideoVO = advVideoVO;
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	
	
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getCategoery() {
		return categoery;
	}
	public void setCategoery(Long categoery) {
		this.categoery = categoery;
	}
	public String execute()
	{
		HttpSession session = request.getSession();
		
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
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
		String param = null;
		param = getTask();
		try {
			jobj = new JSONObject(param);
			Long videoId = jobj.getLong("id");
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
		AdvVideoVO advVideoVO = new AdvVideoVO();
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
			String param = null;
			param = getTask();
			jobj = new JSONObject(param);
			String fromDate = jobj.getString("fromDate");
			String toDate   = jobj.getString("toDate");
			advVideoList    = advVideoService.getVideosForSelectedDates(fromDate,toDate);
		} catch (Exception e) {
			LOG.error("Exception raised in getVideosForSelection() method in AdvVideoAction Class",e);
		}
		return Action.SUCCESS;
	}
	

}
