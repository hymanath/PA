package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsCountVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.INewsByPagingService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class NewsPaginationAction  extends ActionSupport implements ServletRequestAware  {
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private FileVO fileVO;
	private List<FileVO> fileVOList;
	private ICandidateDetailsService candidateDetailsService;
	private String level;
	//private INewsByPagingService newsByPagingService;
	
	
	
	public FileVO getFileVO() {
		return fileVO;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setFileVO(FileVO fileVO) {
		this.fileVO = fileVO;
	}
	public List<FileVO> getFileVOList() {
		return fileVOList;
	}
	public void setFileVOList(List<FileVO> fileVOList) {
		this.fileVOList = fileVOList;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	/*public INewsByPagingService getNewsByPagingService() {
		return newsByPagingService;
	}
	public void setNewsByPagingService(INewsByPagingService newsByPagingService) {
		this.newsByPagingService = newsByPagingService;
	}*/
	
	public String execute()throws Exception
	{
		return Action.SUCCESS;
	}
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}
	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}
	public String getNewsByPaging(){
		try{
			  jObj = new JSONObject(getTask());
			}catch (Exception e) {
			  e.printStackTrace();
			  Log.error("Exception Occured in ajaxHandler() method, Exception - "+e);
			}
		Long partyId=jObj.getLong("partyId");
		int first=jObj.getInt("firstResult");
		int max=jObj.getInt("maxResult");
		String queryType=jObj.getString("queryType");
		Long stateId=jObj.getLong("stateId");
		Long scopeId=jObj.getLong("scopeId");
		String level=jObj.getString("level");
		
		Map<String ,List<FileVO>> resultMapList = new HashMap<String,List<FileVO>>();
		
		fileVOList=new ArrayList<FileVO>();
		resultMapList=candidateDetailsService.getPhotosNewsVideosUpdateForACandidate(first, max,level);
		if(level.equalsIgnoreCase("state")){
			fileVOList=resultMapList.get("NewsGallary");
		}
		if(level.equalsIgnoreCase("district")){
			fileVOList=resultMapList.get("NewsGallaryForDist");
		}
		
		/*if(first == 1){
			fileVO =new FileVO();
			fileVO.setTitle("TITLE 1");
			fileVO.setDescription("DESC 1");
			fileVOList.add(fileVO);
		
			fileVO =new FileVO();
			fileVO.setTitle("TITLE 2");
			fileVO.setDescription("DESC 2");
			fileVOList.add(fileVO);
		}
		if(first == 40){
			
		
			fileVO =new FileVO();
			fileVO.setTitle("TITLE 3");
			fileVO.setDescription("DESC 3");
			fileVOList.add(fileVO);
		
			fileVO =new FileVO();
			fileVO.setTitle("TITLE 4");
			fileVO.setDescription("DESC 4");
			fileVOList.add(fileVO);
		}
		
		else{
			fileVO =new FileVO();
			fileVO.setTitle("TITLE 5");
			fileVO.setDescription("DESC 5");
			fileVOList.add(fileVO);
		}*/
		return Action.SUCCESS;
	}

	//@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
}
