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
    <link rel="stylesheet" type="text/css" href="styles/constituencyManagement/constituencyManagement.css">
<!-- YUI Dependency files (End) -->
	

	<script type="text/javascript" src="js/constituencyManagement/constituencyManagement.js"></script>
	<script type="text/javascript" src="js/constituencyManagement/cadreManagement.js"></script>
	<script type="text/javascript" src="js/problemManagementReport/problemManagementReport.js"></script>
	<script type="text/javascript" src="js/influencingPeople/influencingPeople.js"></script>
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>

	<script type="text/javascript">
		
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

		  <c:forEach var="prob" items="${problemsList}">
			  var problemInfo = {
					  	problemLocationId:'${prob.problemLocationId}',
						problem:'${prob.problem}', 
					  	description:'${prob.description}',
						existingFrom:'${prob.existingFrom}',
						hamlet:'${prob.hamlet}',
						problemSourceScope:'${prob.problemSourceScope}',
						problemAndProblemSourceId:'${prob.problemAndProblemSourceId}',
						status:'${prob.status}' 
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
			var jsObj= 
			{			
				task: "getProblemsStatusCount"		
			}
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/problemsCountByStatusBasedOnAccessLevelsAction.action?"+param;
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
				alertMessageDivEl.innerHTML = 'Please Select Problem Staus From The DropDown ';
				
				return;
			} 
			if(fromDateEl == '')
			{
				alertMessageDivEl.innerHTML ='';
				alertMessageDivEl.innerHTML = 'Please Select From Date';				
				return;
			}			
			
			
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
		
		function getInfluencingPeopleInAConstituency()
		{
			var jsObj= 
			{	
				accessType:accessType,
				accessValue:accessValue,
				flag: "CONSTITUENCY",
				hamletId: "null",				 			  			
				task: "getInfluencingPeopleInAConstituency"
						
			};
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/influencingPeopleInConstituencyAction.action?"+param;
			
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
		
		function redirectToNewWindowForAddingInfluencingPeople(){
			var browser1 = window.open("<s:url action="influencingPeopleRegistration.action"/>","influencingPeopleRegistration","scrollbars=yes,height=600,width=450,left=200,top=200");
			browser1.focus();
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
											buildInfluencingPeopleDT(myResults);
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
									}
								catch (e)
									{   
									   	alert("Invalid JSON result" + e);   
									}	  
					              },
					               scope : this,
					               failure : function( o ) {
					                			alert( "Failed to load result" + o.status + " " + o.statusText);
					                         }
					               };

					YAHOO.util.Connect.asyncRequest('GET', url, callback);
			}
		function openAddNewProblemWindow()
		{	
	var browser1 = window.open("<s:url action="addNewProblemAction.action"/>","addNewProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
							 
			 browser1.focus();
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
							<td width="1px"><img src="images/icons/constituencyManagement/header_left_blue.png"/></td>
							<td><div id="problem_stats_head_label" class="containerHeadLabelDivClass" style="width:892px;">Problem Statistics</div></td>
							<td><img src="images/icons/constituencyManagement/header_right_blue.png"/></td>
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
			<div>
			<table width="100%">
			<tr>
				<td style="vertical-align:top">
					<div id="influencing_people_main" class="yui-skin-sam" style="margin-bottom:10px;width:568px">
						<div id="influencing_people_head">
							<table cellspacing="0" cellpadding="0" width="100%">
								<tr>
									<td width="1px"><img src="images/icons/constituencyManagement/header_left_blue.png"/></td>
									<td><div id="influencing_people_head_label" class="containerHeadLabelDivClass" style="width:520px;">Influencing People</div></td>
									<td><img src="images/icons/constituencyManagement/header_right_blue.png"/></td>
								</tr>
							</table>	
						</div>
						<div id="influencing_people_body" class="containerBodyDivClass">

							
							<DIV class="yui-skin-sam"><div id="localPoliticalChangesRegistration"></DIV>
								<input type="button" style="margin-left:330px;margin-top:10px;" onclick="redirectToNewWindowForAddingInfluencingPeople()" value="Add Influencing Persons" class="linkButton" />
							</DIV>
					
							<div id="influencing_people_data_body" class="yui-skin-sam"><div id="influencingPeopleDtDiv"></div></div>
							<div id="influencingPeoplePopUpDiv"></div>
							<div id="influencing_people_footer">
								<DIV id="smsBlockAlert" class="errorMessage"></DIV>
								<DIV id="smsConfirmation" class="confirmationMessage"></DIV>
								<div id="sendSMSBlock" style="border:1px solid;margin:10px;width:500px;">
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
				<td style="vertical-align:top">
					<div id="voters_stats_main" style="margin-bottom:10px;">
						<div id="voters_stats_head">
							<table cellspacing="0" cellpadding="0" width="100%">
								<tr>
									<td width="1px"><img src="images/icons/constituencyManagement/header_left_blue.png"/></td>
									<td><div id="voters_stats_head_label" class="containerHeadLabelDivClass" style="width:320px;">Voters Details</div></td>
									<td><img src="images/icons/constituencyManagement/header_right_blue.png"/></td>
								</tr>
							</table>	
						</div>
						<div id="voters_stats_body" class="containerBodyDivClass" style="padding:10px;">
							<div id="voterStats_content" class="widgetDescPara" style="height:120px;">
								
									<font style="color:#4B74C6"> Voters Details </font> enables the user to view the complete details of voters and their their cast information upto the hamlet level.The user has to select the region according to their access level and can obtain the voter information.
								
							</div>
							<div style="text-align:right;padding:15px;">
								<!--<a class="linkButton" href="constituencyManagementAction.action?cmTask=voterStas">View Voters Details</a>
							-->
							<a class="linkButton" href="constituencyManagementAction.action?cmTask=CONSTITUENCY_MANAGEMENT">View Voters Details</a>
							</div>
						</div>
					</div>	
				</td>
			</tr>
			</table>
			
			<div id="political_changes_main" class="yui-skin-sam" style="margin-bottom:10px;">
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
					<DIV class="yui-skin-sam"><div id="localPoliticalChangesRegistration"></DIV>
							<input type="button"  onclick='redirectToNewWindowForAddingPoliticalChanges("new")' value="Add Local Political Changes" class="linkButton" style="margin-left:584px;margin-top:11px;"/>
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
	

	<script type="text/javascript">
		
		<c:forEach var="probStatus"  items="${statusList}" >
		var ob={
					id:'${probStatus.id}',
					value:'${probStatus.name}'
				};
		problemMgmtObj.problemsStatusArr.push(ob);
		</c:forEach>
		initializeConstituencyManagement();	
		
	</script>
	
</body>
</html>