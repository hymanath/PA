<%@ page import="java.util.ResourceBundle;" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Constituency Management</title>

<!-- YUI Dependency files (Start) -->

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
    
<!-- YUI Dependency files (End) -->

	<link type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" rel="stylesheet" />
	<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.tabs.js"></script>
	
	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
	<link rel="stylesheet" type="text/css" href="styles/constituencyPage/constituencyPage.css">	
	<link rel="stylesheet" type="text/css" href="styles/constituencyManagement/constituencyManagement.css">	

	<script type="text/javascript" src="js/constituencyManagement/constituencyManagement.js"></script>
	<script type="text/javascript" src="js/constituencyManagement/influencingPeople.js"></script>
	<script type="text/javascript" src="js/constituencyManagement/localUserGroups.js"></script>
	<script type="text/javascript" src="js/constituencyManagement/cadreManagement.js"></script>
	<script type="text/javascript" src="js/problemManagementReport/problemManagementReport.js"></script>
	<script type="text/javascript" src="js/influencingPeople/influencingPeople.js"></script>
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>

	<script type="text/javascript" src="http://www.google.com/jsapi"></script>

	<script type="text/javascript">
		google.load("visualization", "1", {packages:["corechart"]});
		 <%			
			ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
			String problemLabel = rb.getString("problem");
			String constituencyMgmt = rb.getString("constMgmt");
			String probMgmt = rb.getString("probMgmt");
			String recommLetters = rb.getString("recommLetters");
			String distEPapers = rb.getString("distEPapers");
			String localLeaders = rb.getString("localLeaders");
			String localProbs = rb.getString("localProbs");
			String localCastStats = rb.getString("localCastStats");
			String localPolChanges = rb.getString("localPolChanges");
			String voterByLoc= rb.getString("voterByLoc");
			String impVoters= rb.getString("impVoters");
			String name = rb.getString("name");
			String occupation = rb.getString("occupation");
			String position = rb.getString("position");
			String inflScope = rb.getString("inflScope");
			String contactnbr= rb.getString("contactnbr");
			String description = rb.getString("description");
			String identifiedDate = rb.getString("identifiedDate");
			String source = rb.getString("source");
			String status = rb.getString("status");
			String cast = rb.getString("cast");
			String castPopulation = rb.getString("castPopulation");
			String castPercentage = rb.getString("castPercentage");
			String date = rb.getString("date");
			String impact = rb.getString("impact");
			String gender = rb.getString("gender");
			String age = rb.getString("age");
			String hNo = rb.getString("hNo");
			String guardName= rb.getString("guardName");
			String relationship = rb.getString("relationship");
			String castCategory = rb.getString("castCategory");
			String mbrsInFamily = rb.getString("mbrsInFamily");
			String eldstPersonName = rb.getString("eldstPersonName");
			String ygstPersonName = rb.getString("ygstPersonName");
			String newIssues= rb.getString("newIssues");
			String clasfdIssues = rb.getString("clasfdIssues");
			String assignedIssues = rb.getString("assignedIssues");
			String progress = rb.getString("progress");
			String fixedIssues = rb.getString("fixedIssues");
			String sNo = rb.getString("sNo");
			String title = rb.getString("title");
			String location = rb.getString("location");
			String scope = rb.getString("scope");
			String problemType = rb.getString("problemType");
			String department = rb.getString("department");
			String concernedDept = rb.getString("concernedDept");
			String assignedOfficial = rb.getString("assignedOfficial");
			String addNewProb = rb.getString("addNewProb");
			String pendingIssues = rb.getString("pendingIssues");
			String select = rb.getString("select");
			String STATE = rb.getString("STATE");
			String DISTRICT = rb.getString("DISTRICT");
			String CONSTITUENCY = rb.getString("CONSTITUENCY");
			String MANDAL  = rb.getString("MANDAL");
			String VILLAGE = rb.getString("VILLAGE");
			String HAMLET   = rb.getString("HAMLET");
			String classify = rb.getString("classify");
			String assign = rb.getString("assign");
			String totalNumOfVoters = rb.getString("totalNumOfVoters");
			String maleVoters = rb.getString("maleVoters");
			String femaleVoters = rb.getString("femaleVoters");
			String email = rb.getString("email");
			String address = rb.getString("address");
			String telephoneNo = rb.getString("telephoneNo");
			String reportedDate = rb.getString("reportedDate");
			String existingFrom = rb.getString("existingFrom");
			String problemSource = rb.getString("problemSource");
			String mobile  = rb.getString("mobile");
			String constMgmtAlertMessage = rb.getString("constMgmtAlertMessage");
			String mandalLeaders= rb.getString("mandalLeaders");
			String electionYear= rb.getString("electionYear");
			String rank= rb.getString("rank");
			String mptcName= rb.getString("mptcName");
			String party= rb.getString("party");
			String candidateName= rb.getString("candidateName");
			String votesEarned= rb.getString("votesEarned");
			String totalValidVotes= rb.getString("totalValidVotes");
			String reason= rb.getString("reason");
			String fixedDate= rb.getString("fixedDate");
			String pending=rb.getString("pending");
			String fixed=rb.getString("fixed");
			String mainEdition = rb.getString("mainEdition");
			String subEdition = rb.getString("subEdition");
			String language = rb.getString("language");
			String designation = rb.getString("designation"); 
			String newIssuesAlertMessage = rb.getString("newIssuesAlertMessage");
			String pendingFrom = rb.getString("pendingFrom");
			String comments = rb.getString("comments");			
		  %> 
		
		var localizationObj = {
								problemLabel : '<%=problemLabel%>',
								constituencyMgmt : '<%=constituencyMgmt%>',								
								probMgmt : '<%=probMgmt%>',
								localLeaders : '<%=localLeaders%>',
								localProbs : '<%=localProbs%>',
								localCastStats : '<%=localCastStats%>',
								localPolChanges : '<%=localPolChanges%>',
								voterByLoc : '<%=voterByLoc%>',
								impVoters : '<%=impVoters%>',
								name : '<%=name%>',
								occupation : '<%=occupation%>',
								position : '<%=position%>',
								inflScope : '<%=inflScope%>',
								contactnbr : '<%=contactnbr%>',
								description : '<%=description%>',
								identifiedDate : '<%=identifiedDate%>',
								source : '<%=source%>',
								status : '<%=status%>',
								cast : '<%=cast%>',
								castPopulation : '<%=castPopulation%>',
								castPercentage : '<%=castPercentage%>',
								date : '<%=date%>',
								impact : '<%=impact%>',
								gender : '<%=gender%>',
								age : '<%=age%>',
								hNo : '<%=hNo%>',
								guardName : '<%=guardName%>',
								relationship : '<%=relationship%>',
								castCategory : '<%=castCategory%>',
								mbrsInFamily : '<%=mbrsInFamily%>',
								eldstPersonName : '<%=eldstPersonName%>',
								ygstPersonName : '<%=ygstPersonName%>',
								newIssues : '<%=newIssues%>',
								clasfdIssues : '<%=clasfdIssues%>',
								assignedIssues : '<%=assignedIssues%>',
								progress : '<%=progress%>',
								fixedIssues : '<%=fixedIssues%>',
								sNo : '<%=sNo%>',
								title : '<%=title%>',
								location : '<%=location%>',
								scope : '<%=scope%>',
								problemType : '<%=problemType%>',
								department : '<%=department%>',
								concernedDept : '<%=concernedDept%>',
								assignedOfficial : '<%=assignedOfficial%>',
								addNewProb : '<%=addNewProb%>',
								pendingIssues : '<%=pendingIssues%>',
								select : '<%=select%>',
								STATE : '<%=STATE%>',
								DISTRICT : '<%=DISTRICT%>',
								CONSTITUENCY : '<%=CONSTITUENCY%>',
								MANDAL  : '<%=MANDAL%>',
								VILLAGE : '<%=VILLAGE%>',
								HAMLET   : '<%=HAMLET%>',
								classify : '<%=classify%>',
								assign : '<%=assign%>',
								totalNumOfVoters : '<%=totalNumOfVoters%>',
								maleVoters : '<%=maleVoters%>',
								femaleVoters : '<%=femaleVoters%>',
								email : '<%=email%>',
								address : '<%=address%>',
								telephoneNo : '<%=telephoneNo%>',
								reportedDate : '<%=reportedDate%>',
								existingFrom : '<%=existingFrom%>',
								problemSource : '<%=problemSource%>',
								mobile  : '<%=mobile%>',
								constMgmtAlertMessage : '<%=constMgmtAlertMessage%>',
								mandalLeaders : '<%=mandalLeaders%>',
								electionYear : '<%=electionYear%>',
								rank : '<%=rank%>',
								mptcName : '<%=mptcName%>',
								party : '<%=party%>',
								candidateName : '<%=candidateName%>',
								votesEarned : '<%=votesEarned%>',
								totalValidVotes : '<%=totalValidVotes%>',
								reason : '<%=reason%>',
								fixedDate : '<%=fixedDate%>',
								pending : '<%=pending%>',
								fixed : '<%=fixed%>',
								mainEdition : '<%=mainEdition%>',
								subEdition : '<%=subEdition%>',
								language : '<%=language%>',
								designation : '<%=designation%>',
								newIssuesAlertMessage : '<%=newIssuesAlertMessage%>',
								pendingFrom : '<%=pendingFrom%>',
								comments : '<%=comments%>'
							  };
		  var accessType= '${accessType}';			  
		  var accessValue= '${accessValue}';
		  var popupPanel;
		  var initialProbs = new Array();
		  var smsHidden = 1;
		  var externalPerson = '${EXTERNAL_PERSON}';
		  var hidden = '${sessionScope.HiddenCount}';
		  
		  <c:forEach var="prob" items="${problemsList}">
			  var problemInfo = {
					  	problemLocationId:'${prob.problemLocationId}',
						problem:'${prob.problem}', 
					  	description:'${prob.description}',
						existingFrom:'${prob.existingFrom}',
						hamlet:'${prob.hamlet}',
						problemSourceScope:'${prob.problemSourceScope}',
						problemAndProblemSourceId:'${prob.problemAndProblemSourceId}',
						status:'${prob.status}',
						probSource:'${prob.probSource}' 
					  };
			  problemMgmtObj.initialProblems.push(problemInfo);
		</c:forEach>

		
		function getProblemHistoryInfo(problemLocationId){
			var jsObj=
			{
					locationId:problemLocationId,
					task:"getProblemDetails"						
			};

			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/problemManagementHistoryResultsNew.action?"+rparam;						
			callAjax(rparam,jsObj,url);
		}
  
		function getProblemsStatusCountByAccessType()
		{
			incrementHidden();
			
			var jsObj= 
			{			
				task: "getProblemsStatusCount"		
			}
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/problemsCountByStatusBasedOnAccessLevelsAction.action?"+param+"&hidden="+hidden;
			callAjax(param,jsObj,url);
		}
		
		function getProblemsByStatusInLocations(status)
		{	
			
			var jsObj=
				{
						locationId: accessValue,
						accessType: accessType,
						task: "getProblemsByStatusInALocation",
						status: status						
				};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/problemDetailsByStatusAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
		}
		
		function getProblemDetailsInSelectedDates(statusId)
		{
			var alertMessageDivEl = document.getElementById("alertMessageDiv");
			var fromDateEl = document.getElementById("existingFromText").value;
			var toDateEl = document.getElementById("tillDateText").value;
			
			
			if(statusId == -1 && fromDateEl == '')
			{
				alertMessageDivEl.innerHTML ='';
				alertMessageDivEl.innerHTML ='Please Select FromDate and Problem Status';
				return;
			}			
			if(statusId == -1)
			{
				alertMessageDivEl.innerHTML ='';
				alertMessageDivEl.innerHTML = 'Please Select Valid Problem Staus';
				
				return;
			} 
			if(fromDateEl == '')
			{
				alertMessageDivEl.innerHTML ='';
				alertMessageDivEl.innerHTML = 'Please Select From Date';				
				return;
			}			
			else{
				alertMessageDivEl.innerHTML ='';

			
			var jsObj= 
			{	
				fromDate: fromDateEl,
				toDate: toDateEl,
				accessType:accessType,
				accessValue:accessValue,
				status: statusId,   			
				task: "getProblemDetailsInSelectedDates"
						
			}
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/problemsByDateBasedOnStatusAction.action?"+param;
			callAjax(param,jsObj,url);
		}
	}	
		function getInfluencingPeopleInAConstituency()
		{
			var jsObj= 
			{	
				regionId:"",
				regionType:"",						 			  			
				task: "getInfluencingPeopleInAConstituency"
						
			};
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/influencingPeopleInConstituencyAction.action?"+param;
			
			callAjax(param,jsObj,url);			
		}
		
		function reGetInfluencingPeopleInAConstituency(regionType,regionId)
		{
			var jsObj= 
			{	
				regionId:regionId,
				regionType:regionType,						 			  			
				task: "reGetInfluencingPeopleInAConstituency"
						
			};
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/influencingPeopleInConstituencyAction.action?"+param;
			
			callAjax(param,jsObj,url);	
		}

		function reGetLocalGroupsInAConstituency(regionType,regionId)
		{
			var jsObj= 
			{	
				regionId:regionId,
				regionType:regionType,						 			  			
				task: "reGetLocalGroupsInAConstituency"
						
			};
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getLocalUserGroupsAction.action?"+param;
			
			callAjax(param,jsObj,url);	
		}

		function smsRenewalMessage()
		{
			var elmt = document.getElementById('smsErrorPopupDiv');
			var divChild = document.createElement('div');
			divChild.setAttribute('id','smsErrorDiv');
			
			var str = '';
			str	+= '<div id="smsErrorMain" style="padding:10px;">';
			str	+= '	<table id="loginDetailsTable" width="100%">';
			str	+= '		<tr>';
			str	+= '			<th colspan="3" align="left">';
			str	+= '				Your SMS Credentials are expired ';
			str	+= '			</th>';		
			str	+= '		</tr>';
			str	+= '		<tr>';
			str	+= '			<td colspan="3">Please contact contact us @  </td>';
			str	+= '		</tr>';
			str	+= '		<tr>';
			str	+= '			<th align="left">Phone </th><td>: </td><td> +91-40-40124153</td>';
			str	+= '		</tr>';
			str	+= '		<tr>';
			str	+= '			<th align="left">Mail </th><td>: </td><td> license@itgrids.com</td>';
			str	+= '		</tr>';
			str	+= '	</table>';	
			str	+= '</div>';
			divChild.innerHTML=str;		
			
			elmt.appendChild(divChild);	
			if(popupPanel)
				popupPanel.destroy();
			popupPanel = new YAHOO.widget.Dialog("smsErrorDiv",
					{ 
						 height:'150px',
						 width:'250px',
			             fixedcenter : true, 
			             visible : true,
			             constraintoviewport : true, 
			    		 iframe :true,
			    		 modal :true,
			    		 hideaftersubmit:true,
			    		 close:true,
						 draggable:true
		             } ); 
			popupPanel.render();
		}
		
		function sendSMS()
		{
			var remainingSms = "${remainingSms}"; 
			if(remainingSms==0){
				smsRenewalMessage();
				return;
			}
			
			var message = document.getElementById("smsText").value;
			var smsBlockAlertEl = document.getElementById("smsBlockAlert");
			var numbersArr = new Array();
			
			var smsConfirmationEl = document.getElementById("smsConfirmation");
			
			var str1='';
			smsConfirmationEl.innerHTML = str1;

			if(mobileNumbersArray.length == 0 && message == '')
			{
				smsBlockAlertEl.innerHTML = '';
				smsBlockAlertEl.innerHTML = 'Please select person name and then type your message';
				return;
			}
			if(mobileNumbersArray.length == 0)
			{
				smsBlockAlertEl.innerHTML = '';
				smsBlockAlertEl.innerHTML = 'Please select person name in the above table';
				return;
			}
			if(message == '')
			{
				smsBlockAlertEl.innerHTML = '';
				smsBlockAlertEl.innerHTML = 'Please type your message';
				return;
			}
			for(var i in mobileNumbersArray)
			{
				numbersArr.push(mobileNumbersArray[i]._oData.contactNumber);				
			}
			smsHiddenIncrementHidden();
			var jsObj= 
				{
					numbers: numbersArr,
					message:message,
					module:"Influencing People",	
					task:"sendSMS"			
				}
				var param="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/userGroupSMSAjaxAction.action?"+param+"&smsHidden="+smsHidden;
				callAjax(param,jsObj,url);			
		}

		function smsHiddenIncrementHidden()
		{
			smsHidden++;
		}
		
		function redirectToNewWindowForAddingInfluencingPeople(type,id){
			var browser1 = window.open("<s:url action="influencingPeopleAction.action"/>?windowTask="+type+"&influencingPersonId="+id,"influencingPeopleAction","scrollbars=yes,height=630,width=620,left=300,top=10");
			browser1.focus();
		}

		function getInfluencingPersonInfo(influencingPersonId)
		{
			var urlStr = "getInfluencingPersonInfoAction.action?windowTask=influencingPersonInfoPopup&influencingPersonId="+influencingPersonId;
			var browser2 = window.open(urlStr,"influencingPersonInfoPopup","scrollbars=yes,height=570,width=600,left=200,top=50");	
			browser2.focus();
		}
		
		function getSubLevelInfluenceData(regionId,regionName,regionType,areaType,regionTitle,regionTitleId,status)
		{
			var jsObj= 
			{	
				regionId:regionId,
				regionName:regionName,	
				regionType:regionType,
				regionTitle:regionTitle,
				regionTitleId:regionTitleId,
				areaType:areaType,
				status:status,
				task: "getSubLevelInfluencePeople"						
			};
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/subLevelInfluenceAction.action?"+param;
			
			callAjax(param,jsObj,url);		
		}

		function getSubLevelLocalGroupData(regionId,regionName,regionType,areaType,regionTitle,regionTitleId,status)
		{
			var jsObj= 
			{	
				regionId:regionId,
				regionName:regionName,	
				regionType:regionType,
				regionTitle:regionTitle,
				regionTitleId:regionTitleId,
				areaType:areaType,
				status:status,
				task: "getSubLevelLocalUserGroupsPeople"						
			};
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/subLevelLocalGroupsAction.action?"+param;
			
			callAjax(param,jsObj,url);		
		}

		function callAjax(param,jsObj,url){
			var myResults;
	 					
	 		var callback = {			
	 		               success : function( o ) 
							  {
								try {												
										if(o.responseText)
											myResults = YAHOO.lang.JSON.parse(o.responseText);
											
										if(jsObj.task == "getProblemsStatusCount")
										{
											showProblemsStatusCount(myResults);
										} else if(jsObj.task == "getProblemDetailsInSelectedDates")
										{											
											buildProblemsDetailsDT(myResults);
										} else if(jsObj.task == "getInfluencingPeopleInAConstituency")
										{												
											populateInfluencingPeople(jsObj,myResults);
										} else if(jsObj.task == "getProblemsByStatusInALocation")
										{
											buildProblemsByStatusDialog(myResults,jsObj);
										}else if(jsObj.task == "getProblemDetails")
										{
											showProblemsHistoryReport(myResults);			
										} else if(jsObj.task == "sendSMS")
										{
											showSentSmsConfirmation(myResults);
										}
										if(jsObj.task == "getAllPoliticalChangesForTheUser"){
											buildDataTableForLocalPoliticalChanges(myResults);
										}
										if(jsObj.task == "getExternalPersonDetails"){
											buildExternalPersonDetailsPopUp(myResults);
										}
										if(jsObj.task == "deltePoliticalChange"){
											getAllPoliticalChangesForTheUser();
										}
										if(jsObj.task == "deleteInfluencingPerson"){
											alert("Succesfully Deleted");
											getInfluencingPeopleInAConstituency();	
										}	
										if(jsObj.task == "getLocalUserGroups" || jsObj.task == "reGetLocalGroupsInAConstituency")
										{
											buildLocalUserGroupsCriteria(jsObj,myResults);
										}
										if(jsObj.task == "getUserGroupsBasedOnCriteria")
										{
											buildUserGroupsBasedOnCriteria(jsObj,myResults);
										}
										if(jsObj.task == "getSubLevelInfluencePeople")
										{
											buildSubLevelInfluencePeople(jsObj,myResults);
										}
										if(jsObj.task == "getSubLevelLocalUserGroupsPeople")
										{
											buildSubLevelLocalGroupPeople(jsObj,myResults);
										}
										if(jsObj.task == "getInfluencePeopleScopeSelectBox")
										{
											buildInfluencePeopleScopeSelectBox(jsObj,myResults);
										}
										if(jsObj.task == "reGetInfluencingPeopleInAConstituency")
										{
											populateInfluencingPeople(jsObj,myResults);
										}

									}
								catch (e)
									{   
									   	alert("Invalid JSON result" + e);   
									}	  
					              },
					               scope : this,
					               failure : function( o ) {
					                			//alert( "Failed to load result" + o.status + " " + o.statusText);
					                         }
					               };

					YAHOO.util.Connect.asyncRequest('GET', url, callback);
			}
		function openAddNewProblemWindow()
		{	
		var browser1 = window.open("<s:url action="addNewProblemAction.action"/>","addNewProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
							 
			 browser1.focus();
		}	

		function deleteInfluencingPeopleDetails(influencingPeopleId){

			var ask = confirm("Do You want to delete");
			if (ask ==  true)
		 {
			var jsObj= 
			{		
				influencingPeopleId :influencingPeopleId,		  			
				task: "deleteInfluencingPerson"		
			};
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "deleteInfluencingPeopleAjaxAction.action?"+param;
			callAjax(param,jsObj,url);
		}	
		
		else
		  {
		  		return;	
		  }	
		}		
		
		function redirectToNewLocalGroupCreation(windowTask,categoryId,localUserGroupId)
		{
			var createGroupBrowser = window.open("<s:url action="createLocalGroupAction.action"/>?windowTask="+windowTask+"&grCategoryId="+categoryId+"&localUserGroupId="+localUserGroupId,"createLocalGroup","scrollbars=yes,height=600,width=800,left=200,top=100");										 
		    createGroupBrowser.focus();
		}

		function redirectToNewLocalGroupMember(categoryId,groupId)
		{

			var createGroupMemberBrowser = window.open("<s:url action="createLocalGroupMemberPopupAction.action"/>?groupCategoryId="+categoryId+"&groupId="+groupId,"createLocalGroupMember","scrollbars=yes,height=600,width=600,left=200,top=200");										 
		    createGroupMemberBrowser.focus();
		}
		

	</script>

	
</head>
<body>
	
	<div id="errorMessageDIV" class="yui-skin-sam">
		<div id="smsErrorPopupDiv">
		</div>
	</div>

	<div id="constituencyMgmtDiv_main" style="padding:10px;">
		<div id="constituencyMgmt_main_header">
			<div id="constituencyMgmt_head_label">Constituency Management</div>
			<DIV id="constMgmtDesc">
				<UL>
					<LI>Know the problems and their status in your constituency</LI>
					<LI>Know about the Local Political Changes and impact in your constituency</LI>
					<LI>View the people who are having more influence in your constituency</LI>
					<LI>Access voters details by hamlet level, and group them by Important Families and Cast</LI>
					<LI>View Mandal Level Leaders Details</LI>
				</UL>
			</DIV>
		</div>
		<div id="constituencyMgmt_main_content">
			<div id="problem_stats_main" style="margin-bottom:10px;">
				<div id="problem_stats_head">
					<table cellspacing="0" cellpadding="0" width="100%">
						<tr>
							<td width="1px"><img width="24" height="30" src="images/icons/constituencyManagement/header_left_blue.png"/></td>
							<td><div id="problem_stats_head_label" class="containerHeadLabelDivClass" style="width:892px;height:14px;">Problem Statistics</div></td>
							<td><img width="8" height="30" src="images/icons/constituencyManagement/header_right_blue.png"/></td>
						</tr>
					</table>	
				</div>
				<div id="problem_stats_body" class="containerBodyDivClass">
					<div style="text-align:right;padding:15px;">
						<a class="linkButton" href="javascript:{}" onclick="openAddNewProblemWindow()">Add New Problem</a>						
						 <a class="linkButton" href="constituencyManagementAction.action?cmTask=PROBLEMS_MANAGEMENT">Manage Problems</a>
						 <a class="linkButton" href="constituencyManagementAction.action?cmTask=CONSTITUENCY_MANAGEMENT">View Detailed Statistics</a>
					</div>
					<div id="problems_outline_Div"></div>
					<DIV id="problems_Options" ></DIV>
					<div id="constituencyMgmtBodyDiv" class="yui-skin-sam"><div id="moreDetailsPanelDiv"></div></div>
					
					<DIV class="yui-skin-sam"><DIV id="problemsByStatusPanelDiv"></DIV></DIV>
				</div>
			</div>
			
			<!-- New Layout (Start)-->
			<div id="constituencyManagement_mainTabContainer">
				<div id="tabs">					
					<ul>
						<li><a href="#tabs-1">Influencing People</a></li>
						<li><a href="#tabs-2">Local User Groups</a></li>
						<li><a href="#tabs-3">Local Political Changes</a></li>
					</ul>
					<div id="tabs-1">
						<table width="100%" cellpadding="0" cellspacing="5">
						<tr>
							<td colspan="2">
								<div id="influencePeopleChartDiv_Outer" class="rounded" style="margin:0px;"> 						
									<div class="corner topLeft"></div>
									<div class="corner topRight"></div>
									<div class="corner bottomLeft"></div>
									<div class="corner bottomRight"></div> 

									<div id="differentViewsRadioDiv_influencingPeople" class="differentViewsDivClass"></div>
									<DIV class="yui-skin-sam" style="text-align:right;">
										<div id="localPoliticalChangesRegistration"></DIV>
										<input type="button" style="margin-right:10px;margin-top:10px;" onclick="redirectToNewWindowForAddingInfluencingPeople('new',0)" value="Add Influencing People" class="linkButton" />
									</DIV>
									<div id="influencePeopleChartDiv_main" class="contentDivClass">										
									</div>	
								</div>														
							</td>
						</tr>
						<tr>
						<td width="30%" valign="top">
							<div id="influencePeopleRegionWisOverView_Outer" class="rounded" style="margin:0px;"> 						
								<div class="corner topLeft"></div>
								<div class="corner topRight"></div>
								<div class="corner bottomLeft"></div>
								<div class="corner bottomRight"></div> 

								<div id="influencePeopleRegionWiseOverView_main" class="contentDivClass">
									
								</div>	
							</div>
							
							<div id="influencePeopleScopeWiseOverView_Outer" class="rounded"> 						
								<div class="corner topLeft"></div>
								<div class="corner topRight"></div>
								<div class="corner bottomLeft"></div>
								<div class="corner bottomRight"></div> 

								<div id="influencePeopleScopeWiseOverView_main" class="contentDivClass">
									
								</div>	
							</div>

						</td>
						<td width="70%" valign="top">
							<div id="influencePeopleDetail_Outer" class="rounded" style="margin:0px;"> 						
								<div class="corner topLeft"></div>
								<div class="corner topRight"></div>
								<div class="corner bottomLeft"></div>
								<div class="corner bottomRight"></div> 
<div id="influencePeopleDetail_main" class="contentDivClass">
	<div id="influencePeopleDetail_head">
									<table cellspacing="0" cellpadding="0" border="0" width="100%">
									<tr>
										<td width="30px"><img width="30" height="36" src="images/icons/districtPage/header_left.gif"></td>
										<td align="left">
<div style="height:36px;padding:0px;width: 560px;"" class="districtPageRoundedHeaders_center">
<span class="regionsHead_center_label" style="top:10px;">Influence People Detail Info</span>
											</div>
										</td>
										<td width="5px"><img width="8" height="36" src="images/icons/districtPage/header_right.gif"></td>
									</tr>
									</table>
									</div>
<div id="influencePeopleDetail_body">
<div id="influencePeopleRegionsList" class="influencePeopleRegionsList"></div>
<div id="influencePeopleRegionsTypeRadio" class="influencePeopleRegionsList"></div>
<div id="influencePeopleRegionsData_main"></div>
</div>
								</div>	
							</div>
						</td>
						</tr>
						</table>
						
					</div>
					<div id="tabs-2">
						<table width="100%" cellpadding="0" cellspacing="5">
						<tr>
							<td colspan="2">
								<div id="localGroupsChartDiv_Outer" class="rounded" style="margin:0px;"> 						
									<div class="corner topLeft"></div>
									<div class="corner topRight"></div>
									<div class="corner bottomLeft"></div>
									<div class="corner bottomRight"></div> 

										<div id="differentViewsRadioDiv_localGroups" class="differentViewsDivClass">	</div>
										<DIV class="yui-skin-sam" style="text-align:right;">
										<div id="localPoliticalChangesRegistration"></DIV>
											<input type="button" style="margin-right:10px;margin-top:10px;" onclick="redirectToNewLocalGroupCreation('new',0,0)" value="Create A Group" class="linkButton" />
											<input type="button" style="margin-right:10px;margin-top:10px;" onclick="redirectToNewLocalGroupMember(0,0)" value="Create A Group Member" class="linkButton" />
								        </DIV>
										<div id="localGroupsChartDiv_main" class="contentDivClass">										
										</div>	

								</div>														
							</td>
						</tr>
						<tr>
						<td width="30%" valign="top">
							<div id="localGroupsRegionWisOverView_Outer" class="rounded" style="margin:0px;"> 						
								<div class="corner topLeft"></div>
								<div class="corner topRight"></div>
								<div class="corner bottomLeft"></div>
								<div class="corner bottomRight"></div> 

								<div id="localGroupsRegionWiseOverView_main" class="contentDivClass">
																	
								</div>	
							</div>

						</td>
						<td width="70%" valign="top">
							<div id="localGroupsDetail_Outer" class="rounded" style="margin:0px;"> 						
								<div class="corner topLeft"></div>
								<div class="corner topRight"></div>
								<div class="corner bottomLeft"></div>
								<div class="corner bottomRight"></div> 

								<div id="localGroupsDetail_main" class="contentDivClass">
									<div id="localGroupsDetail_head">
									<table cellspacing="0" cellpadding="0" border="0" width="100%">
									<tr>
										<td width="24px"><img width="25px" height="30px" src="images/icons/districtPage/header_left.gif"></td>
										<td align="left">
											<div style="height:30px;padding:0px" class="districtPageRoundedHeaders_center">
												<span id="localGroupsLabelSpan" class="regionsHead_center_label" style="top:10px;">Local Groups Detail Info</span>
											</div>
										</td>
										<td width="5px"><img width="8" height="30" src="images/icons/districtPage/header_right.gif"></td>
									</tr>
									</table>
									</div>
									<div id="localGroupsDetail_body">
										<div id="localGroupsRegionsList" class="influencePeopleRegionsList"></div>
										<div id="localGroupsRegionsTypeRadio" class="influencePeopleRegionsList"></div>
										<div id="localGroupsRegionsData_main"></div>
									</div>
								</div>	
							</div>
						</td>
						</tr>
						</table>
					
					</div>
					<div id="tabs-3">
						<div id="localPoliticalChanges_Outer" class="rounded"> 						
							<div class="corner topLeft"></div>
							<div class="corner topRight"></div>
							<div class="corner bottomLeft"></div>
							<div class="corner bottomRight"></div> 

							<div id="localPoliticalChanges_main" class="contentDivClass yui-skin-sam" >
								<DIV ><div id="localPoliticalChangesRegistration"></DIV>
										<input type="button"  onclick="redirectToNewWindowForEditingPoliticalChanges('new',0)" value="Add Local Political Changes" class="linkButton" style="margin-left:584px;margin-top:11px;"/>
								</div>
								<div id="political_changes_data_head">
									<font style="color:#4B74C6;font-weight:bold;"> Political Changes </font> gives glance of total changes that occur in that constituency and helps the user to analyze the changes and act accordingly.
								</div>
								<div id="political_changes_data_table"></div>
								<div id="political_changes_data_body"></div>	
							</div>	
						</div>
										
					</div>
				</div>
			</div>
			<!-- New Layout (End)-->

			<div id="voters_stats_main" style="margin-bottom:10px;">
				<div id="voters_stats_head">
					<table cellspacing="0" cellpadding="0"  width="100%">
						<tr>
							<td width="1px"><img width="24" height="29" src="images/icons/constituencyManagement/header_left_blue.png"/></td>
							<td><div id="voters_stats_head_label" class="containerHeadLabelDivClass" style="width:892px;height:13px;">Voters Details</div></td>
							<td><img width="8" height="30" src="images/icons/constituencyManagement/header_right_blue.png"/></td>
						</tr>
					</table>	
				</div>
				<div id="voters_stats_body" class="containerBodyDivClass" style="padding:10px;">
					<div id="voterStats_content" class="widgetDescPara">
						
							<font style="color:#4B74C6"> Voters Details </font> enables the user to view the complete details of voters and their their cast information upto the hamlet level.The user has to select the region according to their access level and can obtain the voter information.
						
					</div>
					<div style="text-align:right;padding:15px;">
						
					<a class="linkButton" href="constituencyManagementWithVoterDataAction.action?cmTask=CONSTITUENCY_MANAGEMENT">View Voters Details</a>
					</div>
				</div>
			</div>	
			<div>
			<!--<table width="100%" style="margin-right:10px;">
			<tr>
				<td style="vertical-align:top;align:left;" width="65%">
					<div id="influencing_people_main" class="yui-skin-sam" style="margin-bottom:10px;">
						<div id="influencing_people_head">
							<table cellspacing="0" cellpadding="0" width="100%">
								<tr>
									<td width="1px"><img src="images/icons/constituencyManagement/header_left_blue.png"/></td>
									<td align="left"><div id="influencing_people_head_label" class="containerHeadLabelDivClass" style="width:890px;">Influencing People</div></td>
									<td><img src="images/icons/constituencyManagement/header_right_blue.png"/></td>
								</tr>
							</table>	
						</div>
						<div id="influencing_people_body" class="containerBodyDivClass">

							
							<DIV class="yui-skin-sam" style="text-align:right;"><div id="localPoliticalChangesRegistration"></DIV>

							<input type="button" style="margin-right:10px;margin-top:10px;" onclick="redirectToNewWindowForAddingInfluencingPeople('new',0)" value="Add Influencing People" class="linkButton" />
							</DIV>
					
							<div id="influencing_people_data_body" class="yui-skin-sam"><div id="influencingPeopleDtDiv"></div></div>
							<div id="influencingPeoplePopUpDiv"></div>
							<div id="influencing_people_footer">
								<DIV id="smsBlockAlert" class="errorMessage"></DIV>
								<DIV id="smsConfirmation" class="confirmationMessage"></DIV>
								<div id="sendSMSBlock" style="border:1px solid;margin:10px;width:530px;">
									<TABLE>
										<TR>
											<TD colspan="2"><DIV style="text-align:left;">Should not exceed 200 chars!</DIV></TD>
										</TR>	
										<TR>
											<TD><TEXTAREA id="smsText" cols="70" onkeyup='limitText("smsText","maxcount",200)'></TEXTAREA></TD>
											<TD valign="bottom"><INPUT type="button" value="Send SMS" onclick="sendSMS()" class="button"/></TD>
										</TR>
										<TR>
											<TD colspan="2" ><DIV id="remainChars" style="text-align:left;"><SPAN id="maxcount">200 </SPAN><SPAN>chars remaining..</SPAN></DIV></TD>
											
										</TR>	
									</TABLE>
								</div>			
							</div>
						</div>
					</div>
				</td>				
			</tr>
			</table>
			
			-->
			<!--<div id="political_changes_main" class="yui-skin-sam" style="margin-bottom:10px;">
				<div id="political_changes_head">
					<table cellspacing="0" cellpadding="0" width="100%">
						<tr>
							<td width="1px"><img src="images/icons/constituencyManagement/header_left_blue.png"/></td>
							<td><div id="politicalChanges_head_label" class="containerHeadLabelDivClass" style="width:892px;">Local Political Changes</div></td>
							<td><img src="images/icons/constituencyManagement/header_right_blue.png"/></td>
						</tr>
					</table>	
				</div>
				<div id="political_changes_body" class="containerBodyDivClass">
					
				</div>
			</div>	-->				
							
		</div>
		</div>
	</div>
	

	<script type="text/javascript">
		
		<c:forEach var="probStatus"  items="${statusList}" >
		var ob={
					id:'${probStatus.id}',
					value:'${probStatus.name}'
				};
		problemMgmtObj.problemsStatusArr.push(ob);
		</c:forEach>
		initializeConstituencyManagement();	
		$(
			function()
			{
				$("#tabs").tabs();
			}
		);

	</script>
	
</body>
</html>