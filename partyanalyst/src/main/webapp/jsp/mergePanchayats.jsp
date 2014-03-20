<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Merge Panchayat</title>
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	

	<!-- YUI Dependency files (End) -->
	
  	<style>

	select {
		background-color: #FFFFFF;
		border: 1px solid #CCCCCC;
		width: 250px;
		margin-bottom:15px;
		padding: 5px;
	}
	.requiredFont{
		color:red;
	}
	.header2{
	  display:none;
	}
	#headingDiv
{
	background-color: #06ABEA;
	color : #FFFFFF;
	padding:5px;
	border-radius : 5px;
    padding: 6px;
	text-align : center;
	width: 950px;
	margin-left: 0px; 
	margin-bottom: 25px;
	font-weight:bold;
	font-size:15px;
}
	</style>
	
 </head>
<script>
$(document).ready(function(){
getConstituencyList();
});

function getConstituencyList(){

var jsObj= 
	{	
		task:"getConstituencies"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getConstituenciesByPartyNYearAction.action?"+param;
	callAjax(param,jsObj,url);

}

function getSearchDtails (){
	$('#errorDiv').html('');
	var searchType = $('#searchTypeId').val();
	var constiId =  $('#listConstituencyNames').val();
	var flag = true;
	if(searchType == 0){
		$('#errorDiv').html(' Please Select Search Type.');
		flag = false;
	}
	if(constiId == 0){
		$('#errorDiv').html(' Please Select Constituency.');
		flag = false;
	}
	else if(searchType == 1){
		searchType="basedOnElection";
	}
	else if(searchType == 2){
		getPublicationDate();
		return;
	}

	if(flag)
	{		
		var jsObj= 
		{
			constituencyId : constiId,
			task:"getElectionYearsDate"		
		};
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "electionDetailsByConstituencyAction.action?"+param;
		callAjax(param,jsObj,url);
	}
}

function getPublicationDate()
	{
	var constituencyID =  $('#listConstituencyNames').val();
	
	var jsObj = 
	{
		selected:constituencyID,
		task:"getPublicationDate"
	}	
	var param = "task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}

function getPanchayatList(constituencyType)
	{
	
	var constituencyID =  $('#listConstituencyNames').val();
	var searchType = $('#searchTypeId').val();

	var flag = true;
	if(searchType == 0){
		$('#errorDiv').html(' Please Select Search Type.');
		flag = false;
	}
	if(constituencyID == 0){
		$('#errorDiv').html(' Please Select Constituency.');
		flag = false;
	}
	else if(searchType == 1){
	var electionId =  $('#elctionYearsList').val();
		var jsObj=
			{
				constituencyId    : constituencyID,
				electionId : electionId,						
				task  : "getReportLevelDetails"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatDetailsForElectionInCosntituencyAction.action?"+rparam;	
	}
	else if(searchType == 2){
	var publicationID =  $('#publciationList').val();
		var jsObj=
			{
				type  : "Panchayat",
				level : 1,
				id    : constituencyID,
				publicationId : publicationID,						
				task  : "getReportLevelDetails"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getReportLevelDetails.action?"+rparam;						
	}
		callAjax(rparam,jsObj,url);
	
	}
	
	
function mergePanchayatDetails(){
	var constituencyID =  $('#listConstituencyNames').val();
	var panchayatId =  $('#PanchayatList').val();
	var PanchayatIdsForMerge =  $('#PanchayatListForMerge').val();
	var searchType = $('#searchTypeId').val();
	var searchTypeValue = $('#searchTypeList').val();
	$('#errorDiv').html('');
	var flag = true;
	var type = "";
	if(searchType == 0){
		$('#errorDiv').append(' Please Select Search Type.<br>');
		flag = false;
	}
	if(constituencyID == 0){
		$('#errorDiv').append(' Please Select Constituency.<br>');
		flag = false;
	}
	if(searchTypeValue == 0){
		$('#errorDiv').append(' Please Select Publication Date Id.<br>');
		flag = false;
	}
	
	if(panchayatId == 0){
		$('#errorDiv').append(' Please Select Panchayat.<br>');
		flag = false;
	}
	if(PanchayatIdsForMerge == null ||  PanchayatIdsForMerge.length == 0){
		$('#errorDiv').append(' Please Select Panchayaties to be Merge.<br>');
		flag = false;
	}
	
	else if(searchType == 1){
		type = "E";
		searchTypeValue = $('#elctionYearsList').val();
	}
	else if(searchType == 2){
		type = "P";
		searchTypeValue = $('#publciationList').val();
	}

	if(flag){
		var jsObj=
			{
				type : type,
				searchTypeValue : searchTypeValue,
				cosntituencyId : constituencyID,
				panchayatId  : panchayatId,		
				PanchayatIdsForMerge : PanchayatIdsForMerge,
				task  : "mergePanchayatsToOnePanchayat"
			}


			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "mergePanchayatsToOnePanchayatAction.action?"+rparam;	

			callAjax(rparam,jsObj,url);
	}
}
function callAjax(param,jsObj,url){
	var myResults;					
		var callback = {			
				success : function( o ) {
					try 
					{												
						if(o.responseText)
							myResults = YAHOO.lang.JSON.parse(o.responseText);
						
						if(jsObj.task == "getConstituencies")
						{							
							populateConstituencyListDropdown(myResults);
						}
						else if(jsObj.task == "getSearchTypeDetails")
						{	
							console.log(myResults);
						}
						else if(jsObj.task == "electionDetailsByConstituency")
						{	
							console.log(myResults);
						}
						else if(jsObj.task=="getPublicationDate")
						{							
							$("#publciationList").find("option").remove();
							$("#PanchayatList,#PanchayatListForMerge").find("option").remove();
							if(myResults != null && myResults.length > 0){
								for(var i in myResults){
								
								$("#publciationList").append("<option value='"+myResults[i].id+"'>"+myResults[i].name+"</option>");
								
								}
							
							}
						
						}
						else if(jsObj.task=="getElectionYearsDate")
						{							
							$("#elctionYearsList").find("option").remove();
							$("#PanchayatList,#PanchayatListForMerge").find("option").remove();
							$("#elctionYearsList").append("<option value='0'> Select Election Year </option>");
							if(myResults != null && myResults.length > 0){
								for(var i in myResults){
								
								$("#elctionYearsList").append("<option value='"+myResults[i].id+"'>"+myResults[i].name+"</option>");
								
								}
							
							}
						
						}
						else if(jsObj.task == "getReportLevelDetails")
						{	
							$("#PanchayatList,#PanchayatListForMerge").find("option").remove();
							$("#PanchayatList").append("<option value='0'>Select Panchayat</option>");
							if(myResults != null && myResults.length > 0){
								for(var i in myResults){
								
								$("#PanchayatList,#PanchayatListForMerge").append("<option value='"+myResults[i].id+"'>"+myResults[i].name+"</option>");
								
								}
							
							}
						
						}
						else if(jsObj.task == "mergePanchayatsToOnePanchayat")
						{
							if(myResults.resultCode == 0){
								$('#errorDiv').append('<span style="color:green;"> Panchayat Details are successfully merged.</span>');
							}else{
								$('#errorDiv').append('Error occured while merging Panchayats.<br>');
							}
						}
					}catch (e){
					//alert("Invalid JSON result" + e);  
					}  
				},
			       scope : this,
			       failure : function( o ) {
			        			//alert( "Failed to load result" + o.status + " " + o.statusText);
			        }
			    };
		YAHOO.util.Connect.asyncRequest('POST', url, callback);
	}

	
function populateConstituencyListDropdown(results){

	var list = document.getElementById("listConstituencyNames");
	removeSelectElements(list);
	if(results!=null)
		for(var i in results)
		{
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
			addOptions(list,opElmt);			
		}	
}

function removeSelectElements(elmt)
{
	if(!elmt)
		return;

	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	

}

function addOptions(list,opElmt){
	try
		{
		list.add(opElmt,null); // standards compliant
		}
	catch(ex)
		{
		list.add(opElmt); // IE only
		}
}

function clearFields(){
	$('#listConstituencyNames').val(0);
	$('#elctionYearsList,#publciationList,#PanchayatList,#PanchayatListForMerge').find('option').remove();
	var searchType = $('#searchTypeId').val();
	if(searchType =="1"){
		$('#electionYearsDiv').css("display","block");
		$('#publicationDateDiv').css("display","none");
	}
	else if(searchType =="2"){
		$('#electionYearsDiv').css("display","none");
		$('#publicationDateDiv').css("display","block");
	}
}
</script>
 
<body>
	<div id="mainDiv" style="border:2px solid #78BCE8;padding:20px;border-radius:5px;margin-top:25px;">
	<div id="headingDiv"> Merging Panchayat Details </div>
	<div class="container m_top20" style="margin-left:150px;">
		<div id="errorDiv" style="color:red;margin-left:130px;font-size:13px;font-weight:bold;"></div>
		
		<div class="row-fluid">
			<div class="span5">
				 <div class="row-fluid" >
					<div class="span4">
                         <label class="pull-left" style="font-weight:bold;">Search Type :<span class="requiredFont">*</span> </label>						  
					</div>
					<div class="span8">
						<select  id="searchTypeId" onchange="clearFields();">
						<option value="0"> Select Search Type</option>
						<option value="1"> Search By Election </option>
						<option value="2"> Search By Publication </option>
						<option></option>
						</select>
						
					</div>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span5">
				 <div class="row-fluid" >
					<div class="span4">
                         <label class="pull-left" style="font-weight:bold;">Constituency :<span class="requiredFont">*</span> </label>						  
					</div>
					<div class="span8">
						<select  id="listConstituencyNames" onchange="getSearchDtails();"></select>
						
					</div>
				</div>
			</div>
		</div>

		<div class="row-fluid" id="electionYearsDiv" style="display:none;">
			<div class="span5">
				 <div class="row-fluid">
					<div class="span4">
                         <label class="pull-left" style="font-weight:bold;"> Election Years:<span class="requiredFont">*</span> </label>						  
					</div>
					<div class="span8">	<select  id="elctionYearsList" onchange="getPanchayatList();"></select></div>
				</div>
			</div>
		</div>
		
		<div class="row-fluid" id="publicationDateDiv" style="display:none;">
			<div class="span5">
				 <div class="row-fluid">
					<div class="span4">
                         <label class="pull-left" style="font-weight:bold;"> Publication Date :<span class="requiredFont">*</span> </label>						  
					</div>
					<div class="span8">	<select  id="publciationList" onchange="getPanchayatList();"></select></div>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span5">
				 <div class="row-fluid">
					<div class="span4">
                         <label class="pull-left" style="font-weight:bold;"> Panchayat :<span class="requiredFont">*</span> </label>						  
					</div>
					<div class="span8">	<select  id="PanchayatList"	 ></select></div>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span5">
				 <div class="row-fluid">
					<div class="span4">
                         <label class="pull-left" style="font-weight:bold;"> Merge Panchayat :<span class="requiredFont">*</span> </label>						  
					</div>
					<div class="span8">	<select  id="PanchayatListForMerge" multiple="true" ></select></div>
				</div>
			</div>
		</div>
		
		
		<input type="button" class="btn btn-info offset2" value="Merge Panchayats" onclick="mergePanchayatDetails();"/>
	</div>
	</div>
</body>
 </html>