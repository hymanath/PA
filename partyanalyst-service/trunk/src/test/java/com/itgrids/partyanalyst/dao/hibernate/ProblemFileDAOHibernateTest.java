package com.itgrids.partyanalyst.dao.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemFileDAO;
import com.itgrids.partyanalyst.model.ProblemFile;

public class ProblemFileDAOHibernateTest extends BaseDaoTestCase{
	
	private IProblemFileDAO problemFileDAO;

	public void setProblemFileDAO(IProblemFileDAO problemFileDAO) {
		this.problemFileDAO = problemFileDAO;
	}

	public IProblemFileDAO getProblemFileDAO() {
		return problemFileDAO;
	}

	

	
	/*public void test()
	{
		problemFileDAO.getAll();
		
	}*/
	/*public void testGetAllNonApprovedImagesBetweenDatesWithCompleteData(){
		
		List<Object[]> result = problemFileDAO.getAllNonApprovedImagesBetweenDatesWithCompleteData();
		System.out.println(result.size());
		}*/
	
	/*public IProblemFileDAO getProblemFileDAO() {
		return problemFileDAO;
	}




	public void setProblemFileDAO(IProblemFileDAO problemFileDAO) {
		this.problemFileDAO = problemFileDAO;
	}*/




/*public void testGetAllNonApprovedFilesAndProblemDetails()
	{
		List<ProblemFile> list = problemFileDAO.getAllNonApprovedFilesAndProblemDetails();
		
		System.out.println(list.size());
		
		for(ProblemFile params : list)
		{
			System.out.println();
			System.out.print("----"+params.toString());
		}
	}*/
	/*
	 * .problemFileId,model.problemHistory.problemLocation.problemAndProblemSource.problem.problem,model.file.fileTitle,model.file.fileDescription,
	 * model.problemHistory.problemLocation.problemImpactLevel.scope," +
				" model.problemHistory.problemLocation.problemAndProblemSource.problem.existingFrom, model.problemHistory.problemLocation.problemAndProblemSource.problem.identifiedOn,
				model.problemHistory.problemLocation.problemAndProblemSource.externalUser.name," +
				" model.problemHistory.problemLocation.problemAndProblemSource.externalUser.lastName,model.file.fileName,model.isApproved
	 * 
	 * 
	 * 
	 * @SuppressWarnings("deprecation")
	public void testgetAllApprovedImagesBetweenDates()
	{
		
		Date startDate=null;
		Date endDate=null;
		DateFormat format = 
			 new SimpleDateFormat("yyyy-MM-dd");
		 
		try {
			 startDate = (Date)format.parse("2011-04-09 01:33:43");
			
			endDate = (Date)format.parse("2011-04-09 21:35:51");
			
			String query = "date(model.dateUpdated) =?";
		} catch (ParseException e1) {
				e1.printStackTrace();
		}
		
		List<Object[]>  result = problemFileDAO.getAllApprovedImagesBetweenDates(startDate,endDate,"NEW");
		
			for(Object[] params : result)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
				System.out.println(params[2]);
				
			}
		
	
	}*/
	
	/*@SuppressWarnings("deprecation")
	public void testgetAllNoNApprovalImagesBetweenDates() throws ParseException
	{
		Date startDate = null;
		Date endDate = null;
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		startDate = (Date)format.parse("2011-04-09 01:33:43");
		endDate = (Date)format.parse("2011-04-03 20:50:29");
		String query = "date(model.dateUpdated) =?";
		List<Object[]> result = problemFileDAO.getAllNoNApprovalImagesBetweenDates(startDate, endDate, "NEW");
		for(Object[] params : result)
		{
			System.out.println(params[0]);
			System.out.println(params[1]);
			
		}
	}
	*/
	/*
	@SuppressWarnings("unchecked")
	public void testgetAllPostedImagesBetweenDates() throws ParseException
	{
	Date startDate = null;
	Date endDate = null;
	DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	startDate = (Date)format.parse("2012-04-13 10:30:09");
	endDate = (Date)format.parse("2012-04-18 14:59:46");
	String query = "date(model.dateUpdated) = ?";
	List<Object[]> result = problemFileDAO.getAllPostedImagesBetweenDates(startDate, endDate, "NEW");
	for(Object[] params : result)
	{
		System.out.println(params[0]);
		System.out.println(params[1].toString());
	}
	
	}*/
	/*
	@SuppressWarnings("unchecked")
	public void testgetApprovalImagesForParticularDate() throws ParseException
	{
		Date particularDate = null;
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		particularDate = (Date)format.parse("2011-04-09 01:33:43");
		
		String query = "date(model.dateUpdated) = ?";
		List<Object[]> result = problemFileDAO.getApprovalImagesForParticularDate(particularDate, "NEW");
		for(Object[] params : result)
		{
			System.out.println(params[0]);
			System.out.println(params[1]);
		}
		}*/
	/*@SuppressWarnings("unchecked")
	public void testgetAllNonApprovalImagesForParticularDate() throws ParseException
	{
		Date particularDate = null;
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		particularDate = (Date)format.parse("2011-04-09 01:33:43");
		
		String query = "date(model.dateUpdated) = ?";
		List<Object[]> result = problemFileDAO.getApprovalImagesForParticularDate(particularDate, "NEW");
		for(Object[] params : result)
		{
			System.out.println(params[0]);
			System.out.println(params[1]);
		}
		}*/
	
	/*public void testgetAllgetAllImagesForParticularDate() throws ParseException
	{
		Date particularDate = null;
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		particularDate = (Date)format.parse("2012-04-18 17:27:12");
		
		String query = "date(model.dateUpdated) = ?";
		List<ProblemFile> result = problemFileDAO.getApprovalImagesForParticularDate(particularDate, "NEW");
		for(ProblemFile params : result)
		{
			System.out.println(params.getIsApproved());
		}
		}*/
	
	/*public void testGetNoOfFilesUploadedForAUser()
	{
		Object object = problemFileDAO.getNoOfFilesUploadedForAUser(223l);
		System.out.println(object);
	}
	*/

/*public void testdeleteProblemFile()
{
	Integer value = problemFileDAO.deleteProblemFile(38l);
	System.out.println(value);
}*/
}
