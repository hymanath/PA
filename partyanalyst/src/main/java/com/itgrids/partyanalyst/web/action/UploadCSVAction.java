package com.itgrids.partyanalyst.web.action;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

//import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
//import au.com.bytecode.opencsv.bean.CsvToBean;

import com.itgrids.partyanalyst.csv.Assembly;
import com.itgrids.partyanalyst.csv.AssemblyConstCandidate;
import com.itgrids.partyanalyst.csv.AssemblyConstituency;
import com.itgrids.partyanalyst.helper.Constants;
import com.opensymphony.xwork2.ActionSupport;

public class UploadCSVAction extends ActionSupport implements ServletResponseAware, ServletRequestAware, ServletContextAware {
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(UploadCSVAction.class);

    private HttpServletResponse response;
    private HttpServletRequest request; 

	private ServletContext context;
	//private IStaticDataService staticDataService;
	private String elecType;
	private String elecScope;
	private String elecYear;
	
	/*public void setStaticDataService(IStaticDataService staticDataService) {
        this.staticDataService = staticDataService;
    }

    public IStaticDataService getStaticDataService() {
		return staticDataService;
	}*/
    
    public String execute() throws JRException {/*
		
		log.debug("excute started...");
		String contextPath = context.getRealPath("/");
		String csvFile = request.getParameter("uploadFile");
		
		elecType = request.getParameter("elecType");
		elecScope = request.getParameter("elecScope");
		elecYear = request.getParameter("elecYear");
	
		try {
			
			List<Assembly> assembly = new ArrayList<Assembly>();
			int i = 0;
		    
		    if(2 == Constants.ASSEMBLY) {
		    		ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
		            strat.setType(Assembly.class);
		         
		            String[] columns = new String[] {"index", "constituency", "party", "votesPolled", "margin", "remarks"};
		            strat.setColumnMapping(columns);

		            CsvToBean csv = new CsvToBean();
		            List list = csv.parse(strat, new FileReader(csvFile));
		            
		            System.out.println("list size:" + list.size());
		            
		            boolean isConstituency = false;
	            	boolean endOfConstituency = true;
	            	boolean endOfCandidateDetails = false;
	            	List<AssemblyConstCandidate> candidateDetailsList = null;
	            	List<AssemblyConstituency> constituencyList = new ArrayList<AssemblyConstituency>();
	            	AssemblyConstituency constituency = null;
	            	
		            for(Object obj: list) {
		            	if(i > 0){
		            		Assembly bean = (Assembly)obj;
		            		
		            		if(bean.getConstituency().trim().length() == 0 &&
		            				bean.getParty().trim().length() == 0 ) {
		            			isConstituency = false;
		    	            	endOfConstituency = true;
		    	            	endOfCandidateDetails = false;
		            		}

		            		else if(endOfConstituency && !isConstituency && bean.getParty().trim().length() == 0) {
			            		candidateDetailsList = new ArrayList<AssemblyConstCandidate>();
			            		constituency = new AssemblyConstituency();
			            		constituency.setName(bean.getConstituency());
			            		isConstituency = true;
			            		endOfCandidateDetails = false;
			            		endOfConstituency = false;
			            		System.out.println("Const Name:" + constituency.getName());
			            	} 
			            	else if(bean.getConstituency() == null || bean.getConstituency().trim().length() == 0) {
			            		long votes = (bean.getVotesPolled().trim().length() == 0) ? 0 : Long.parseLong(bean.getVotesPolled()); 

			            		if(Constants.TOTAL_ELECTORS.equals(bean.getParty().trim())) {
			            			constituency.setTotalVotes(votes);
			            		} else if(Constants.VALID_VOTES.equals(bean.getParty().trim())) {
			            			constituency.setValidVotes(votes);
			            		} else if(Constants.REJECTED_VOTES.equals(bean.getParty().trim())) {
			            			constituency.setRejectedVotes(votes);
			            		} else if(Constants.MISSING_VOTES.equals(bean.getParty().trim())) {
			            			constituency.setMissingVotes(votes);
			            		} else if(Constants.TOTAL_VOTES_POLLED.equals(bean.getParty().trim())) {
			            			constituency.setPolledVotes(votes);
			            		} else if(Constants.TENDERED_VOTES.equals(bean.getParty().trim())){
			            			constituency.setTenderedVotes(votes);
			            			constituency.setCandidateDetails(candidateDetailsList);
			            			constituencyList.add(constituency);
			            			endOfCandidateDetails = true;
			            			constituency = null;
			            			endOfConstituency = true;
			            			isConstituency = false;
			            		}
			            	} 
			            	else if(isConstituency && !endOfCandidateDetails && bean.getConstituency() != null && bean.getConstituency().trim() != ""){
			            		AssemblyConstCandidate candidateDetails = new AssemblyConstCandidate();
			            		candidateDetails.setCandidate(bean.getConstituency());
				            	candidateDetails.setParty(bean.getParty());
				            	candidateDetails.setVotesPolled(bean.getVotesPolled());
				            	candidateDetailsList.add(candidateDetails);
			            	}
			            	//System.out.println(bean.getConstituency() + " " + bean.getParty() + " " + isConstituency + " " + endOfCandidateDetails + " " + endOfConstituency);
		            	}
		            	
		            	i++;
		            }
		            processAssemblyData(constituencyList);
		            System.out.println("No. of records:" + i + " " + constituencyList.size() + " " + candidateDetailsList.size());
		    }
		   
		} catch (FileNotFoundException e) {
			log.error("File Not Found" + e.getStackTrace());
		} catch (IOException e) {
			log.error("IOException while reading data from csv " + e.getStackTrace());
		}
		*/
		return SUCCESS;
	 
    }
    
	private void processAssemblyData(List<AssemblyConstituency> constituencyList) {
		
		for(AssemblyConstituency constituency: constituencyList) {
			System.out.println(constituency.getName() + ": " + constituency.getCandidateDetails().size() );
		}
		/*List<Constituency> constList = new ArrayList<Constituency>();
		List<Party> partyList = new ArrayList<Party>();
		List<ConstituencyElection> constElectionList = new ArrayList<ConstituencyElection>();
		
		ElectionType elecType = new ElectionType();
		elecType.setElectionType(this.elecType);
		elecType.setScope(elecScope);
		
		Election election = new Election();
		election.setElectionYear(elecYear);
		
		ConstituencyElection constElection = new ConstituencyElection();
		constElection.setElection(election);
		
		
		*/
		
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
}
