<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<TITLE>${year} ${electionType} Election Candidates Details</TITLE>
<LINK rel="stylesheet" type="text/css" href="styles/ElectionsReslutsPage/candiateDetailsForElectionReport.css">
<LINK type="text/css" rel="stylesheet" href="styles/ElectionsReslutsPage/datatable1.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<LINK rel="stylesheet" type="text/css" href="styles/CommentsDialog/commentsDialog.css">
<!-- Dependencies -->
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></SCRIPT>
<script type="text/javascript" src="js/CommentsDialog/commentsDialog.js"></script>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></SCRIPT>

 
<!-- Slider skin (optional) --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.2r1/build/slider/assets/skins/sam/slider.css">
 
<!-- Slider source file --> 
<script src="http://yui.yahooapis.com/2.8.2r1/build/slider/slider-min.js"></script>


<!-- JQuery files (Start) -->
 <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<!-- JQuery files (End) -->

<style type="text/css">
	#slider-bg
	{ 
		background:url(http://yui.yahooapis.com/2.8.2r1/build/slider/assets/bg-fader.gif) 5px 0 no-repeat; 
	}

	.yui-skin-sam .yui-h-slider
	{
		-moz-background-clip:border;
		-moz-background-inline-policy:continuous;
		-moz-background-origin:padding;
		background:transparent url(http://yui.yahooapis.com/2.8.2r1/build/slider/assets/skins/sam/bg-h.gif) no-repeat scroll 5px 0;
		height:28px;
		width:110px;
	} 

	#accessContent_body
	{
		font-size:16px;
		color:#3D3A32;
	}

	.contactUsTable th
	{
		padding:5px;
		color:#20435A;
		text-align:left;
	}

	.contactUsTable td
	{
		padding:5px;
		text-align:left;
	}

</style>

<SCRIPT type="text/javascript">

var electionType = '${electionType}';
var electionId = '${electionId}';
var stateID =  '${stateID}' ;
var stateName = '${stateName}';
var year = '${year}';
var reasonPostingEntitlement = ${reasonPostingEntitlement};

var participatedCandidatesDetailsDataTable,previousCommentsDataTable;
var candidateDetailsObj={
		candidateDetailsArr:[],
		partiesArr:[],
		statesArr:[],
		distArr:[]
};
var hidden=1;
function incrementHidden()
{
	hidden++;
}
function callAjax(param,jsObj,url){
	var myResults;
					
		var callback = {			
		               success : function( o ) {
						try {												
								if(o.responseText)
									myResults = YAHOO.lang.JSON.parse(o.responseText);
									
								if(jsObj.task == "getAllCandidates")
								{	
									var elmt = document.getElementById("electionPageAjaxImgDiv");
									if(elmt)
										elmt.style.display = 'none';	

                                    if(myResults.candidateDetails!=null){

										if(myResults.candidateDetails.length > 0){
										showCandidates(myResults,jsObj);
										}
										else{
											showErrorInfo();
										}
									}
									else if(myResults.mandalAllElectionDetailsVO != null){
										if(myResults.mandalAllElectionDetailsVO.length > 0)
										{
										showTehsilCandidatesByDistrictWise(myResults,jsObj);
										}
										else{
                                            showErrorInfo();
										}
									}	
									
								}
								if(jsObj.task == "getCommentsClassificationsList")
								{
									buildCommentsClassificationsOptions(myResults);
								}
								if(jsObj.task == "getPreviousComments")
								{
									showPreviousComments(myResults,jsObj);
								}
								if(jsObj.task == "addNewComment")
								{
									updatePreviousCommentsDataTable(myResults);
								}
								
							}
						catch (e) {   
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

function partywiseClickHandler()
{
	
	var allCandidatesRadio = document.getElementById("allCandidates");
	var wonCandidatesRadio = document.getElementById("wonCandidates");
	var lostCandidatesRadio = document.getElementById("lostCandidates");
	var regionalRadioBtns = document.getElementsByName("regionalRadio");
	var regionSelect = document.getElementsByName("regionSelect");
	var regionalOptionsRow = document.getElementById("regionalOptionsRow");
	var selectPartyEl = document.getElementById("selectParty");
	if(regionalOptionsRow)
	{
		regionalOptionsRow.style.display = 'none';
	}	 		
	if(allCandidatesRadio.checked == true)
	{
		allCandidatesRadio.checked = false;
	}
	if(wonCandidatesRadio.checked == true)
	{
		wonCandidatesRadio.checked = false;
	}if(lostCandidatesRadio.checked == true)
	{
		lostCandidatesRadio.checked = false;
	}		
	for (i=0; i< regionSelect.length; i++)
	{
		if(regionSelect[i].style.display == "block")			
		{
			regionSelect[i].style.display = 'none';}
	}
	
	if(selectPartyEl.style.display == 'none')
	{
		selectPartyEl.style.display = 'block';
		selectPartyEl.selectedIndex = '0';
	} else 	selectPartyEl.style.display = 'none';	
}

function selectPartyOnchangeHandler(value)
{
	var allCandidatesRadio = document.getElementById("allCandidates");
	var wonCandidatesRadio = document.getElementById("wonCandidates");
	var regionalOptionsRow = document.getElementById("regionalOptionsRow");
	var regionSelect = document.getElementsByName("regionSelect");
	if(regionalOptionsRow)
	{
		regionalOptionsRow.style.display = 'none';
	}			
	if(allCandidatesRadio.checked == true)
	{
		allCandidatesRadio.checked = false;
	}
	if(wonCandidatesRadio.checked == true)
	{
		wonCandidatesRadio.checked = false;
	}	
	for (i=0; i< regionSelect.length; i++)
	{
		if(regionSelect[i].style.display == "block")			
		{regionSelect[i].style.display = 'none';}
	}	
}

function allCandidatesClickHandler()
{
	var regionalRadioBtns = document.getElementsByName("regionalRadio");
	var regionSelect = document.getElementsByName("regionSelect");
	var regionalOptionsRow = document.getElementById("regionalOptionsRow");
	var partywiseCheckBoxEl = document.getElementById("partywiseCheckBox");
	if(regionalOptionsRow && regionalOptionsRow.style.display == 'none')
	{		
		regionalOptionsRow.style.display = '';
	}
	
	for (i=0; i< regionalRadioBtns.length; i++)
	{
		if(regionalRadioBtns[i].checked == true)
		{regionalRadioBtns[i].checked = false;}
		if(regionalRadioBtns[i].id == 'stateLevelA' && partywiseCheckBoxEl.checked == false) 
			{regionalRadioBtns[i].disabled = true;}
		else if(regionalRadioBtns[i].id == 'stateLevelA' && partywiseCheckBoxEl.checked == true)
		{
				regionalRadioBtns[i].disabled = false;
		}
		if(regionalRadioBtns[i].id == 'countryLevelP' && partywiseCheckBoxEl.checked == false)
		{
			regionalRadioBtns[i].disabled = true;
		}else if(regionalRadioBtns[i].id == 'countryLevelP' && partywiseCheckBoxEl.checked == true)
		{
			regionalRadioBtns[i].disabled = false;
		}
		if(regionalRadioBtns[i].id == 'stateLevelZ' && partywiseCheckBoxEl.checked == false)
		{
			regionalRadioBtns[i].disabled = true;
		}else if(regionalRadioBtns[i].id == 'stateLevelZ' && partywiseCheckBoxEl.checked == true)
		{
			regionalRadioBtns[i].disabled = false;
		}
	}
	for (j=0; j< regionSelect.length; j++)
	{
		if(regionSelect[j].style.display == "block")			
		{
			regionSelect[j].style.display = 'none';}
	}	
}

function wonCandidatesClickHandler()
{
	var regionalRadioBtns = document.getElementsByName("regionalRadio");
	var regionSelect = document.getElementsByName("regionSelect");
	var regionalOptionsRow = document.getElementById("regionalOptionsRow");
	
	if(regionalOptionsRow && regionalOptionsRow.style.display == 'none')
	{
		regionalOptionsRow.style.display = '';
	}	
	for (i=0; i< regionalRadioBtns.length; i++)
	{
		if(regionalRadioBtns[i].checked == true)
		{regionalRadioBtns[i].checked = false;}
		if(regionalRadioBtns[i].id == 'stateLevelA')
		{regionalRadioBtns[i].disabled = false;}
		if(regionalRadioBtns[i].id == 'countryLevelP')
		{regionalRadioBtns[i].disabled = false;}
		if(regionalRadioBtns[i].id == 'stateLevelZ')
		{regionalRadioBtns[i].disabled = false;}
	}
	for (k=0; k< regionSelect.length; k++)
	{
		if(regionSelect[k].style.display == "block")			
		{
			regionSelect[k].style.display = 'none';}
	}		
}//wkg
function lostCandidatesClickHandler()
{
	var regionalRadioBtns = document.getElementsByName("regionalRadio");
	var regionSelect = document.getElementsByName("regionSelect");
	var regionalOptionsRow = document.getElementById("regionalOptionsRow");
	var partywiseCheckBoxEl = document.getElementById("partywiseCheckBox");
	if(regionalOptionsRow && regionalOptionsRow.style.display == 'none')
	{
		regionalOptionsRow.style.display = '';
	}	
	/*for (i=0; i< regionalRadioBtns.length; i++)
	{
		if(regionalRadioBtns[i].checked == true)
		{regionalRadioBtns[i].checked = false;}
		if(regionalRadioBtns[i].id == 'stateLevelA')
		{regionalRadioBtns[i].disabled = false;}
		if(regionalRadioBtns[i].id == 'countryLevelP')
		{regionalRadioBtns[i].disabled = false;}
		if(regionalRadioBtns[i].id == 'stateLevelZ')
		{regionalRadioBtns[i].disabled = false;}
	}*/

	for (i=0; i< regionalRadioBtns.length; i++)
	{
		if(regionalRadioBtns[i].checked == true)
		{regionalRadioBtns[i].checked = false;}
		if(regionalRadioBtns[i].id == 'stateLevelA' && partywiseCheckBoxEl.checked == false) 
			{regionalRadioBtns[i].disabled = true;}
		else if(regionalRadioBtns[i].id == 'stateLevelA' && partywiseCheckBoxEl.checked == true)
		{
				regionalRadioBtns[i].disabled = false;
		}
		if(regionalRadioBtns[i].id == 'countryLevelP' && partywiseCheckBoxEl.checked == false)
		{
			regionalRadioBtns[i].disabled = true;
		}else if(regionalRadioBtns[i].id == 'countryLevelP' && partywiseCheckBoxEl.checked == true)
		{
			regionalRadioBtns[i].disabled = false;
		}
		if(regionalRadioBtns[i].id == 'stateLevelZ' && partywiseCheckBoxEl.checked == false)
		{
			regionalRadioBtns[i].disabled = true;
		}else if(regionalRadioBtns[i].id == 'stateLevelZ' && partywiseCheckBoxEl.checked == true)
		{
			regionalRadioBtns[i].disabled = false;
		}
	}
	
	for (k=0; k< regionSelect.length; k++)
	{
		if(regionSelect[k].style.display == "block")			
		{
			regionSelect[k].style.display = 'none';}
	}
}
function countryLevelPClickHandler()
{
	var selectStatePEl = document.getElementById("selectStateP");
	if(selectStatePEl.style.display == "block")
	{		
		selectStatePEl.style.display = 'none';
	}
	allCandidates();	
}

function stateLevelPClickHandler()
{
	var selectStatePEl = document.getElementById("selectStateP");
	if(selectStatePEl.style.display == "none")
	{		
		selectStatePEl.style.display = 'block';
		selectStatePEl.selectedIndex = '0';
	}
}

function stateLevelAClickHandler()
{
	var regionSelect = document.getElementsByName("regionSelect");
	
	for (i=0; i< regionSelect.length; i++)
	{
		if(regionSelect[i].style.display == "block")			
		{
			regionSelect[i].style.display = 'none';}
	}
	allCandidates();
}

function distLevelAClickHandler()
{
	var selectdistrictAEl = document.getElementById("selectdistrictA");
	if(selectdistrictAEl.style.display == "none")
	{		
		selectdistrictAEl.style.display = 'block';
		selectdistrictAEl.selectedIndex='0';
	}	
}
function distLevelZClickHandler()
{
	var selectdistrictZEl = document.getElementById("selectdistrictZ");
	if(selectdistrictZEl.style.display == "none")
	{		
		selectdistrictZEl.style.display = 'block';
		selectdistrictZEl.selectedIndex='0';
	}
}
function distLevelMClickHandler()
{
	var selectdistrictMEl = document.getElementById("selectdistrictM");
	if(selectdistrictMEl.style.display == "none")
	{		
		selectdistrictMEl.style.display = 'block';
		selectdistrictMEl.selectedIndex='0';
	}
}
function buildParticipatedCandidatesDetailsDataTable(data)
{
	
	var participatedCandidatesDetailsColumnDefs = [
								{key: "count", label: "S No",formatter:"number", sortable:true},	
								{key: "name", label: "Name", sortable:true},		
								{key: "constituency", label: "Constituency", sortable:true},	
								{key: "party", label: "Party", sortable:true},
								{key: "partyFlag", label: "Flag", sortable:true},
								{key: "votesEarned", label: "Votes Earned",formatter:"number", sortable:true},
								{key: "votesPercentage", label: "Votes %", formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},		
								{key: "rank", label: "Rank", formatter:"number", sortable:true},	
		              	 	    {key: "marginVotes", label: "Margin Votes",formatter:"number", sortable:true},
		              	 	 	{key: "marginVotesPercentage", label: "Margin Votes %",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},
		              	 	 	{key: "moreDetails", label: "More Details"},
							    {key: "commentsCount", label: "No of Reasons"},
		              	 	 	{key: "comments", label: ""}
		              	 	    ];                	 	    

		var participatedCandidatesDetailsDataSource = new YAHOO.util.DataSource(data); 
		participatedCandidatesDetailsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		participatedCandidatesDetailsDataSource.responseSchema = {
                fields: [ {key: "count", parser:"number"},"name", "constituency", "party", "partyFlag",
                         {key:  "votesEarned", parser:"number"},
                		  {key: "votesPercentage", parser:YAHOO.util.DataSourceBase.parseNumber},
                		  {key: "rank", parser:"number"},
                		  {key: "marginVotes", parser:YAHOO.util.DataSourceBase.parseNumber},
                		  {key: "marginVotesPercentage", parser:YAHOO.util.DataSourceBase.parseNumber},"moreDetails","commentsCount","comments"]     
		};
		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
				rowsPerPage    : 50,
				template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
				rowsPerPageOptions: [50,100,150,200], 
			    pageLinks: 50			        
			    }),
			    caption:"Candidates Details" 
				};
		
		participatedCandidatesDetailsDataTable = new YAHOO.widget.DataTable("participatedCandidatesDetailsDataTable", participatedCandidatesDetailsColumnDefs, participatedCandidatesDetailsDataSource,myConfigs);		            	
	
}
function allCandidates()
{	
	var elmt = document.getElementById("electionPageAjaxImgDiv");	
	var electionLevel;
	var partywiseCheckBoxEl = document.getElementById("partywiseCheckBox");
	var allCandidatesRadioEl = document.getElementById("allCandidates");
	var wonCandidatesRadioEl = document.getElementById("wonCandidates");
	var lostCandidatesRadioEl = document.getElementById("lostCandidates");
	var stateLevelAEl = document.getElementById("stateLevelA");
	var distLevelAEl = document.getElementById("distLevelA");
	var countryLevelPEl =  document.getElementById("countryLevelP");
	var stateLevelPEl = document.getElementById("stateLevelP");
	var selectdistrictAEl = document.getElementById("selectdistrictA"); 
	var selectStatePEl = document.getElementById("selectStateP");
	var selectPartyEl = document.getElementById("selectParty");
	var stateLevelZEl = document.getElementById("stateLevelZ");
	var distLevelZEl = document.getElementById("distLevelZ");
	var selectdistrictZEl = document.getElementById("selectdistrictZ");
	var stateLevelMEl = document.getElementById("stateLevelM");
	var distLevelMEl = document.getElementById("distLevelM");
	var selectdistrictMEl = document.getElementById("selectdistrictM");	
	var stateId;
	var partyId;
	var locationId;
	var resultsCategory;   

	if(elmt.style.display == 'none')
		elmt.style.display = 'block';

	if(partywiseCheckBoxEl !=null){
	  if(partywiseCheckBoxEl.checked == true)
		{
		partyId = selectPartyEl.value;
	}
	 else{
		partyId = 0;
		}
}      
 else{
		partyId = 0;
		}

 	if(allCandidatesRadioEl){ 

	if(allCandidatesRadioEl.checked == true)
	{
		
		resultsCategory = allCandidatesRadioEl.value;
	} 
	else{
	resultsCategory = allCandidatesRadioEl.value;	

	}
	}else if (lostCandidatesRadioEl !=null && lostCandidatesRadioEl.checked == true){
		resultsCategory = lostCandidatesRadioEl.value;
	} else if (wonCandidatesRadioEl.checked == true)
	{
		resultsCategory = wonCandidatesRadioEl.value;
	}
	else if(allCandidatesRadioEl.checked == true)
	{
		
		resultsCategory = allCandidatesRadioEl.value;
	} 

	if(electionType == 'Assembly')
	{
		if(stateLevelAEl.checked == true)
		{
			electionLevel = stateLevelAEl.value;
			stateId = stateID;
			locationId = 0;
		} 
		if(distLevelAEl!= null){
		if(distLevelAEl.checked == true)
		{   
			electionLevel =distLevelAEl.value;
			locationId=selectdistrictAEl.value;
			stateId = stateID;
		}
	  }
		else{

			locationId=0;
		}
	}
	if(electionType == 'Parliament')
	{
		if(countryLevelPEl.checked == true)
		{
			
			electionLevel = countryLevelPEl.value;
			locationId = "1";
			stateId = 0;
		} 
		if(stateLevelPEl){
		if(stateLevelPEl.checked == true)
		{   
			
			electionLevel =stateLevelPEl.value;
			stateId=selectStatePEl.value;
			locationId = "0";
		}
	  }	
	}
	if(electionType == 'Zptc')
	{
		
		if(stateLevelZEl.checked == true)
		{
			electionLevel = "stateWiseZptc";
			locationId = 0;
			stateId = stateID;
		}
		if(distLevelZEl){
		if(distLevelZEl.checked == true)
		{
			electionLevel = "districtWiseZptc";
			locationId = selectdistrictZEl.value;
			stateId = stateID;
		}	
		}
	}
	if(electionType == 'Mptc')
	{
		if(stateLevelMEl.checked == true)
		{
			electionLevel = "stateWiseMptc";
			locationId = 0;
			stateId = stateID;
		}
		if(distLevelMEl){
		if(distLevelMEl.checked == true)
		{
			electionLevel = "districtWiseMptc";
			locationId = selectdistrictMEl.value;
			stateId = stateID;
		}
	}	
							
	}
	if(electionType == 'Muncipality' || electionType == 'Corporation' || electionType == 'Greater Municipal Corp')
	{
		if(stateLevelAEl.checked == true)
		{
			electionLevel = "statewiseAssembly";
			locationId = 0;
			stateId = stateID;
		}
		if(distLevelAEl)
		if(distLevelAEl.checked == true)
		{
			electionLevel = "districtwiseAssembly";
			locationId = selectdistrictAEl.value;
			stateId = stateID;
		}
		
	}
	
	var jsObj=		
	{		
			electionType:electionType,
			stateID: stateId,
			year : year,
			partyId: partyId,
			electionLevel: electionLevel,
			resultsCategory: resultsCategory,
			locationId: locationId,
			task:"getAllCandidates"						
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	incrementHidden();
	//if(electionType == 'Assembly' || electionType == 'Parliament' || electionType == 'MUNCIPALITY' || electionType == 'CORPORATION' || electionType == 'Greater Municipal Corp'){
		buildDataTable(rparam);
	//}else{
	//	var url = "<%=request.getContextPath()%>/candidateDetailsForElectionDetailsReportAjaxAction.action?"+rparam+"&hidden="+hidden;		
	//	callAjax(rparam,jsObj,url);
	//}
}

function buildDataTable(rparam)
{
	document.getElementById("electionPageAjaxImgDiv").style.display = 'none';

	YAHOO.widget.DataTable.moreDetails = function(elLiner, oRecord, oColumn, oData) 
	  {
		var user = oData;
		var id= oRecord.getData("constituencyId");
		var electionType= oRecord.getData("electionType");
		var electionYear= oRecord.getData("electionYear");
		elLiner.innerHTML ='<A href="javascript:{}" title="Click To View Detailed Election Results" onclick="getMoreDetails('+id+',\''+electionType+'\','+electionYear+')">More Details</A>';
			
	  };

	  YAHOO.widget.DataTable.partyFlag = function(elLiner, oRecord, oColumn, oData) 
	  {
		var user = oData;
		var partyFlag= oRecord.getData("partyFlag");		
		elLiner.innerHTML ='<Img src="<%=request.getContextPath()%>/images/party_flags/'+partyFlag+'" height="30" width="40" border="none"/>';			
	  };

	  YAHOO.widget.DataTable.commentsCount = function(elLiner, oRecord, oColumn, oData) 
	  {
		var user = oData;
		var commentsCount= oRecord.getData("commentsCount");		
		elLiner.innerHTML =commentsCount+' Reasons ';			
	  };
	  
	  YAHOO.widget.DataTable.comments = function(elLiner, oRecord, oColumn, oData) 
	  {
		var user = oData;		
		var constituencyId = oRecord.getData("constituencyId");
		var constituencyName = oRecord.getData("constituencyName");	
		var candidateName = oRecord.getData("candidateName");
		var candidateId = oRecord.getData("candidateId");	
		var rank = oRecord.getData("rank");	
		var partyName = oRecord.getData("partyName");		
		elLiner.innerHTML ='<A href="javascript:{}" title="Click To View/Add Reasons" onclick="checkForEntitlement(\''+candidateId+'\',\''+candidateName+'\',\'candidate\',\''+rank+'\',\''+constituencyId+'\',\''+constituencyName+'\',\''+partyName+'\',\''+1+'\',\'0\')"><IMG src="images/icons/electionResultsReport/notes.png" border="none"></IMG></A>';			
	  };
	<c:if test="${hasDeatiledAnalysis}">
	  
	var candidateDetails = [ 
	       							{key:"candidateName", label: "Name",sortable: true} ,
	       		    	            {key:"constituencyName", label: "Constituency", sortable: true}, 
	       		    	           	{key:"partyName", label: "Party Name", sortable: true},
	       							{key:"partyFlag", label: "Party Flag",sortable:true,formatter:YAHOO.widget.DataTable.partyFlag},
	       		    				{key:"votesEarned", label: "Votes Earned",sortable:true},
	       							{key:"votesPercentage", label: "Votes %",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},
	       		    				{key:"rank", label: "Rank",sortable:true},
	       							{key:"votesDifference", label: "Margin Votes",sortable:true},
	       							{key:"marginVotesPercentage", label: "Margin Votes %",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},
	       							{key:"details", label: "More Details",formatter:YAHOO.widget.DataTable.moreDetails},
	       							{key:"commentsCount", label: "Comments Count",formatter:YAHOO.widget.DataTable.commentsCount},
	       							{key:"comments", label: "Reasons",formatter:YAHOO.widget.DataTable.comments}
	       		    	        ]; 
								</c:if>

								<c:if test="${!hasDeatiledAnalysis}">

										var candidateDetails = [ 
	       							{key:"candidateName", label: "Name",sortable: true} ,
	       		    	            {key:"constituencyName", label: "Constituency", sortable: true}, 
	       		    	           	{key:"partyName", label: "Party Name", sortable: true},
	       							{key:"partyFlag", label: "Party Flag",sortable:true,formatter:YAHOO.widget.DataTable.partyFlag},
	       		    				{key:"votesEarned", label: "Votes Earned",sortable:true},
	       							
	       		  
	       							{key:"details", label: "More Details",formatter:YAHOO.widget.DataTable.moreDetails},
	       							
	       							{key:"comments", label: "Reasons",formatter:YAHOO.widget.DataTable.comments}
	       		    	        ]; </c:if>

	       	var candidateDetailsDataSource = new YAHOO.util.DataSource("candidateDetailsForElectionDetailsReportAjaxAction.action?"+rparam+"&hidden="+hidden+"&"); 
	       	candidateDetailsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON; 
	       	
	       	candidateDetailsDataSource.responseSchema = { 
	                   resultsList:"candidateDetails", 
	       		fields: [
	       				{key:"candidateName"},
	       				"constituencyName","partyName", "partyFlag","votesEarned",
	       				"votesPercentage","rank","votesDifference","marginVotesPercentage",
	       				"constituencyId","electionType","electionYear","commentsCount","comments","candidateId","candidateName"
	       				],
	       		metaFields: {
	       			totalRecords: "totalSearchCount" // Access to value in the server response
	       		}         
	               };


	           var myConfigs = {
	       			        initialRequest: "sort=candidateName&dir=asc&startIndex=0&results=20", // Initial request for first page of data
	       			        dynamicData: true, // Enables dynamic server-driven data
	       			        sortedBy : {key:"candidateName", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
	       			        paginator: new YAHOO.widget.Paginator({ rowsPerPage:20 }) // Enables pagination 
	       		};

	       		var candidateDetailsDataTable = new YAHOO.widget.DataTable("participatedCandidatesDetailsDataTable", candidateDetails,candidateDetailsDataSource, myConfigs);

	       		candidateDetailsDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) 
	       		{		
	       		        oPayload.totalRecords = oResponse.meta.totalRecords;
	       		        return oPayload;
	       		}
	       		//function calling to build Result
	       		return {
	       			oDS: candidateDetailsDataSource,
	       			oDT: candidateDetailsDataTable,
	           	};
}

function showErrorInfo()
{

  var errorEl = document.getElementById("error");
  var candidatesResultsTable = document.getElementById("participatedCandidatesDetailsDataTable");

    if(errorEl)
	{
		errorEl.innerHTML='';

		var errorData='';
		errorData = 'No candidates matched by this selection criteria';
		errorEl.innerHTML=errorData;
	

		if(errorEl.style.display == 'block')
		{
			errorEl.style.display = 'none';
		}
		if(errorEl.style.display == 'none')
		{
			errorEl.style.display = 'block';
		}
        
		candidatesResultsTable.innerHTML= '';
	}
}

function showCandidates(results,jsObj)
{

	
	var emptyArray = new Array();
	var assignTocandidateDetailsArr = new Array();
	var candidateDetails = results.candidateDetails;	
	var count=0;
	var errorEl = document.getElementById("error");
	if(candidateDetails.length != 0)
	{
		if(errorEl.style.display == 'block')
		{errorEl.style.display = 'none'}
		for(var i in candidateDetails)
		{		
			var partyFlag = results.candidateDetails[i].partyFlag;
			count = count + 1;
			var constituencyName='';
			if((electionType=='Mptc') || (electionType=='Zptc')){
				constituencyName = candidateDetails[i].localBodyElectionsConstituencyName;
			}else{
				constituencyName = candidateDetails[i].constituencyName;
			}
			var candidateDetailsObj1 = {					
					count: count, 
					name: candidateDetails[i].candidateName,
					constituency: constituencyName,					
					party: candidateDetails[i].partyName,
					partyFlag:'<Img src="<%=request.getContextPath()%>/images/party_flags/'+partyFlag+'" height="30" width="40" border="none"/>',
					votesEarned: candidateDetails[i].votesEarned,
					votesPercentage: candidateDetails[i].votesPercentage,
					rank: candidateDetails[i].rank,
					marginVotes: candidateDetails[i].votesDifference,
					marginVotesPercentage: candidateDetails[i].marginVotesPercentage,
					constituencyId: candidateDetails[i].constituencyId,
					electionType: candidateDetails[i].electionType,
					electionYear: candidateDetails[i].electionYear,
					moreDetails: '<A href="javascript:{}" title="Click To View Detailed Election Results" onclick="getMoreDetails('+candidateDetails[i].constituencyId+',\''+candidateDetails[i].electionType+'\','+candidateDetails[i].electionYear+')">More Details</A>',					
					comments: '<A href="javascript:{}" title="Click To View/Add Reasons" onclick="checkForEntitlement(\''+candidateDetails[i].candidateId+'\',\''+candidateDetails[i].candidateName+'\',\'candidate\',\''+candidateDetails[i].rank+'\',\''+candidateDetails[i].constituencyId+'\',\''+candidateDetails[i].constituencyName+'\',\''+candidateDetails[i].partyName+'\',\''+jsObj.task+'\',\'0\')"><IMG src="images/icons/electionResultsReport/notes.png" border="none"></IMG></A>',
					commentsCount: candidateDetails[i].commentsCount+" Reasons"
			};
			assignTocandidateDetailsArr.push(candidateDetailsObj1);		
		}
		candidateDetailsObj.candidateDetailsArr = assignTocandidateDetailsArr;
		buildParticipatedCandidatesDetailsDataTable(candidateDetailsObj.candidateDetailsArr);
	} 
	else 	{
		candidateDetailsObj.candidateDetailsArr = emptyArray;
		if(participatedCandidatesDetailsDataTable)
		{participatedCandidatesDetailsDataTable.destroy();}
		if(errorEl.style.display == 'none')
		{errorEl.style.display = 'block'}
		//buildParticipatedCandidatesDetailsDataTable(emptyArray);
	}	
}

function checkForEntitlement(id,candidateName,category, rank,constituencyId,constituencyName,partyName,task,status)
{

	var elmt = document.getElementById('commentsDialogDiv');

	if(reasonPostingEntitlement)
		showCommentsDialog(id,candidateName,category, rank,constituencyId,constituencyName,partyName,task,status);
	else
	{
		addCommentsDialog = $( "#commentsDialogDiv" ).dialog({
			title:"Access Info",
			autoOpen: true,
			show: "blind",
			width: 450,
			minHeight:200,
			modal: true,
			hide: "explode"
		});

		var str = '';
		str += '<div>';		
		str += '<div id="accessContent_body">';
		str += "<p style='color:red'>Sorry! You don't have access to post reason</p>";
		str += '<p style="text-decoration:underline;">For access privileges please contact us at:</p>';
		str += '<table class="contactUsTable">';
		str += '<tr>';
		str += '<th valign="top">Email<th>';
		str += '<td valign="top">info@itgrids.com<br/>a.dakavaram@itgrids.com</td>';
		str += '</tr>';		
		str += '<tr>';
		str += '<th valign="top">Mobile<th>';
		str += '<td valign="top">+91-9676696760</td>';
		str += '</tr>';
		str += '</table>';
		str += '</div>';
		str += '</div>';
		
		elmt.innerHTML = str;
	}
}

function showTehsilCandidatesByDistrictWise(results,jsObj){
	
	var emptyArray = new Array();
	var assignTocandidateDetailsArr = new Array();
	var candidateDetails = results.mandalAllElectionDetailsVO.allVotersDetails;	
	var count=0;
	var errorEl = document.getElementById("error");
	if(candidateDetails.length != 0)
	{
		if(errorEl.style.display == 'block')
		{errorEl.style.display = 'none'}
		for(var i in candidateDetails)
		{		
			var partyFlag = results.mandalAllElectionDetailsVO.allVotersDetails[i].partyFlag;
			count = count + 1;
			var constituencyName='';
			if((electionType=='MPTC') || (electionType=='ZPTC')){
				constituencyName = candidateDetails[i].constituencyName;
			}else{
				constituencyName = candidateDetails[i].tehsilName;
			}
			var candidateDetailsObj1 = {
					
					count: count, 
					name: candidateDetails[i].candidateName,
					constituency: constituencyName,				
					party: candidateDetails[i].partyShortName,
					partyFlag:'<Img src="<%=request.getContextPath()%>/images/party_flags/'+partyFlag+'" height="30" width="40" border="none"/>',
					votesEarned: candidateDetails[i].votesEarned,
					votesPercentage: candidateDetails[i].votesPercentage,
					rank: candidateDetails[i].rank,
					marginVotes: candidateDetails[i].votesDifference,
					marginVotesPercentage: candidateDetails[i].marginVotesPercentage,
					constituencyId: candidateDetails[i].constituencyId,
					electionType: candidateDetails[i].electionType,
					electionYear: candidateDetails[i].electionYear,
					moreDetails: '<A href="javascript:{}" onclick="getMoreDetails('+candidateDetails[i].constituencyId+',\''+candidateDetails[i].electionType+'\','+candidateDetails[i].electionYear+')">More Details</A>'
			};
			assignTocandidateDetailsArr.push(candidateDetailsObj1);		
		}
		candidateDetailsObj.candidateDetailsArr = assignTocandidateDetailsArr;
		buildParticipatedCandidatesDetailsDataTable(candidateDetailsObj.candidateDetailsArr);
	} 
	else 	{
		candidateDetailsObj.candidateDetailsArr = emptyArray;
		if(participatedCandidatesDetailsDataTable)
		{participatedCandidatesDetailsDataTable.destroy();}
		if(errorEl.style.display == 'none')
		{errorEl.style.display = 'block'}
		//buildParticipatedCandidatesDetailsDataTable(emptyArray);
	}	
}


function getMoreDetails(constiId,elecType,elecYear)
{	
	 var browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
	 browser1.focus();
}
function showExistingComments(id,candidateName,category, constituencyId,constituencyName,partyName)
{
	var candidateId;
	var constituencyId;
	if(category == "candidate")
	{
		candidateId = id;
		constituencyId = constituencyId;		
	}
	var jsObj={
			
			candidateId: candidateId,
			candidateName: candidateName,
			constituencyId: constituencyId,
			electionId: electionId,
			electionType: electionType,
			year: year,
			candidateId: candidateId,
			constituencyId: constituencyId,
			constituencyName: constituencyName,
			partyName: partyName,			
			task:"getPreviousComments"				
		  }
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);//+rparam+"&hidden="+hidden incrementHidden();
	incrementHidden();
	var url = "<%=request.getContextPath()%>/commentsDataAction.action?"+rparam+"&hidden="+hidden;		
	callAjax(rparam,jsObj,url);	
}
function getCommentsClassifications(rank)
{ 
	var jsObj={
			rank: rank,
			task: "getCommentsClassificationsList"				
		  }	
var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "<%=request.getContextPath()%>/commentsClassificationDataAction.action?"+rparam;		
callAjax(rparam,jsObj,url);
}
function handleAddCommentsSubmit(id,category,constituencyId)
{
	var commentVal = document.getElementById("commentText").value; 
	var postedByVal = document.getElementById("commentPostedByText").value;
	var partyId;
	var candidateId;
	var constituencyId;
	var commentCategoryId;
	var alertMessageEl = document.getElementById("alertMessage"); 

	var reasonSeverityElmt = document.getElementById("slider-value");
	var reasonSeverityvalue = reasonSeverityElmt.innerHTML;

	if(category == "candidate")
	{
		var commentCategoryEl = document.getElementById("commentsClassificaitonSelectBox");
		if(commentCategoryEl)
		{
			commentCategoryId = commentCategoryEl.value;
			
		}	
		partyId = '0';
		candidateId = id;
		constituencyId = constituencyId;		
	}
	if(category == "party")
	{
		partyId = id;
		candidateId = '0';
		constituencyId = '0';
		commentCategoryId = '0';	
		
	}
	
	if(commentCategoryId == '' || commentVal == '' || postedByVal == '' || commentCategoryId == 'Select Classification' )		
	{
		alertMessageEl.innerHTML = 'Please Fill Mandatory Fields!';
		return;		
	}	
	if(commentCategoryId != '' && commentVal != '' && postedByVal != '')		
	{
		var jsObj={
				electionId: electionId,
				electionType: electionType,
				year: year,
				partyId: partyId,
				candidateId: candidateId,
				constituencyId: constituencyId,
				commentDesc: commentVal,
				postedBy: postedByVal,
				category: category,
				commentCategoryId: commentCategoryId,
				reasonSeverityvalue:reasonSeverityvalue,
				task:"addNewComment"				
			  }	 
			
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/commentsDataAction.action?"+rparam;		
		callAjax(rparam,jsObj,url);	
		alertMessageEl.innerHTML = '';
	}
			
	
}

function handleAddCommentsCancel(task,status)
{
	allCandidates();
	addCommentsDialog.dialog("destroy");	
}


--></SCRIPT>
</HEAD>
<BODY class="yui-skin-sam">
<CENTER>
<c:if test="${hasDeatiledAnalysis}">
<H3>${year} ${electionType} Election Candidates Details</H3></c:if>
<c:if test="${!hasDeatiledAnalysis}">
<h3>${year} ${electionType} Election Won Candidates Details in StateLevel</h3>
</c:if>
<DIV id="electionPageAjaxImgDiv">
	<DIV> Loading Candidate Details! Please Wait..</DIV>
	<IMG src="images/icons/barloader.gif"/>
</DIV>
<c:if test="${hasDeatiledAnalysis}">
<DIV id="optionsDiv" class="optionsDiv"></c:if>
<c:if test="${!hasDeatiledAnalysis}">
<DIV id="optionsDiv" class="optionsDiv" style="display:none;"></c:if>
<c:if test="${hasDeatiledAnalysis}">
	<P class="paraText">To View Candidates Details for a particular party, please select the "Partywise Candidate Details" check box and select a party from drop down list and select the options provided below. </P></c:if>

	<TABLE  class="optionsTable" width="80%" border="0">
	<TR><c:if test="${hasDeatiledAnalysis}">
		<TD align="left" class="td" style="width:20%;"><INPUT type="checkbox" name="partywiseCheckBox" id="partywiseCheckBox" onclick="partywiseClickHandler()" />Partywise Candidates</TD>
		<TD align="left" colspan="1" style="width:20%;"><s:select id="selectParty" theme="simple"  name="selectParty" cssClass="selectBoxStyle" list="partiesList" listKey="id" style="display:none;" listValue="name" onchange="selectPartyOnchangeHandler()" /></TD>
		<TD align="left" colspan="1" style="width:20%;"></TD>
		<TD align="left" colspan="1" style="width:20%;"></TD></c:if>
	</TR>	
	<TR>
		<TD align="left" class="td" style="width:20%;"><INPUT type="radio" name="candidatesOption" id="wonCandidates" value="wonCandidatesOnly" onClick="wonCandidatesClickHandler()" checked="true"/>Won Candidates</TD>

		<c:if test="${hasDeatiledAnalysis}">
		<TD align="left" class="td" style="width:20%;"><INPUT type="radio" name="candidatesOption" id="lostCandidates" value="lostCandidatesOnly" onClick="lostCandidatesClickHandler()"/>Lost Candidates</TD>
		<TD align="left" class="td" style="width:20%;"><INPUT type="radio" name="candidatesOption" id="allCandidates" value="allCandidates" onClick="allCandidatesClickHandler()" />All Contested Candidates</TD>	
		<TD align="left" colspan="1" style="width:20%;"></TD></c:if>
	</TR>	
	<c:if test="${electionType == 'Parliament'}">
		<TR id="regionalOptionsRow">
			<TD class="td" style="width:20%;"><INPUT type="radio" name="regionalRadio" id="countryLevelP" value="countrywiseParliament" onClick="countryLevelPClickHandler()" checked="true"/>Country Level</TD>	
			<c:if test="${hasDeatiledAnalysis}">
			<TD class="td" style="width:20%;"><INPUT type="radio" name="regionalRadio" id="stateLevelP" value="statewiseParliament" onClick="stateLevelPClickHandler()"/>State Level</TD>
			<TD style="width:20%;"><s:select id="selectStateP" name="regionSelect" cssClass="selectBoxStyle" style="display:none;" theme="simple" list="statesListObj.getAllStates" listKey="id"  listValue="name" onChange="allCandidates()"  /></TD>
			<TD align="left" colspan="1" style="width:20%;"></TD>
		</TR>
	</c:if>	</c:if>
	 <c:if test="${electionType == 'Assembly' || electionType == 'Muncipality' || electionType == 'Corporation' ||  electionType == 'Greater Municipal Corp'}" >  
		<TR id="regionalOptionsRow">
		<TD class="td" name="RegionalOptionsA" style="width:20%;"><INPUT type="radio" name="regionalRadio" id="stateLevelA" value="statewiseAssembly" onClick="stateLevelAClickHandler()" checked="true"/>State Level</TD>

		<c:if test="${hasDeatiledAnalysis}">
		<TD class="td" name="RegionalOptionsA" style="width:20%;"><INPUT type="radio" name="regionalRadio" id="distLevelA" value="districtwiseAssembly" onClick="distLevelAClickHandler()"/>District Level</TD>
		<TD style="width:20%;"><s:select id="selectdistrictA" name="regionSelect" cssClass="selectBoxStyle" theme="simple" list="districtsList" listKey="id"  listValue="name" style="display:none;" onChange="allCandidates()"  /></TD>
		<TD align="left" colspan="1" style="width:20%;"></TD>
		</c:if>
		</TR>
	</c:if>		
	<c:if test="${electionType == 'Zptc'}">  
	<TR id="regionalOptionsRow">
		<TD class="td" name="RegionalOptionsA" style="width:20%;"><INPUT type="radio" name="regionalRadio" id="stateLevelZ" value="stateWiseZptc" onClick="stateLevelAClickHandler()" checked="true"/>State Level</TD>	
		<c:if test="${hasDeatiledAnalysis}">
		<TD class="td" name="RegionalOptionsA" style="width:20%;"><INPUT type="radio" name="regionalRadio" id="distLevelZ" value="districtwiseZptc" onClick="distLevelZClickHandler()"/>District Level</TD>
		<TD style="width:20%;"><s:select id="selectdistrictZ" name="regionSelect" cssClass="selectBoxStyle" theme="simple" list="districtsList" listKey="id"  listValue="name" style="display:none;" onChange="allCandidates()"  /></TD>
		<TD align="left" colspan="1" style="width:20%;"></TD></c:if>
		</TR>
	</c:if>
	<c:if test="${electionType == 'Mptc'}">  
	<TR>
		<TD class="td" name="RegionalOptionsA" style="width:20%;"><INPUT type="radio" name="regionalRadio" id="stateLevelM" value="stateWiseMptc" onClick="stateLevelAClickHandler()" checked="true"/>State Level</TD>	
		<c:if test="${hasDeatiledAnalysis}">
		<TD class="td" name="RegionalOptionsA" style="width:20%;"><INPUT type="radio" name="regionalRadio" id="distLevelM" value="districtwiseMptc" onClick="distLevelMClickHandler()"/>District Level</TD>
		<TD style="width:20%;"><s:select id="selectdistrictM" name="regionSelect" cssClass="selectBoxStyle" theme="simple" list="districtsList" listKey="id"  listValue="name" style="display:none;" onChange="allCandidates()"  /></TD>
		<TD align="left" colspan="1" style="width:20%;"></TD></c:if>
	</TR>	
	</c:if>
	</TABLE>
</DIV>
<DIV id="error" class="errorMessage" style="display:none;">No candidates matched by this selection criteria </DIV>
<DIV id="participatedCandidatesDetailsDataTable" align="left"></DIV>
<DIV class = "yui-skin-sam"><DIV id="commentsDialogDiv"></DIV></DIV>
</CENTER>
<SCRIPT type="text/javascript">
allCandidates();
</SCRIPT>
</BODY>
</HTML>