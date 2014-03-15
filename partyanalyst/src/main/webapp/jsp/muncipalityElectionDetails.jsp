<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

var constilist = new Array();
<c:forEach var="constituency" items="${constiList}" varStatus="i">
    var obj ={
	id:'${constituency.id}',
	name: '${constituency.name}'
	} 
	constilist.push(obj);
</c:forEach>
	//console.log(constilist);
$(document).ready(function(){
	buildConstitutencyList();
	
});

function  buildConstitutencyList(){
	if(constilist != null && constilist.length >0){
		$('#muncipalConstiList1').find('option').remove();
		$('#muncipalConstiList1').append('<option value="0"> Select Constituency </option>');
		for(var i in constilist){
			$('#muncipalConstiList1').append('<option value="'+constilist[i].id+'">'+constilist[i].name+'</option>');
		}
		$("#facebook").hide();
	}
}
function getElectionYear(){
 var cosntiId = $('#muncipalConstiList1').val();
	var jsObj = {
	 constiId : cosntiId,
	 task : "getElectionYearsForCosntituency"
	 }
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getElectionYearsForCosntituencyAction.action?"+rparam;

    callAjaxFor(jsObj,url);
}

function getPArtyDetails(){
  var electionIds = []; 
$('#muncipalElectnYears :selected').each(function(i, selected){ 
  electionIds[i] = $(selected).val(); 
});

	var jsObj = {
			 electionId : electionIds,
			 task : "getPartyListForElection"
			 }

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyListForElectionAction.action?"+rparam;

    callAjaxFor(jsObj,url);
	  
}

function  getPartyWiseELecitonDetails(){


	$('#errorDiv').html('');
	var isValid = true;
	var constituencyId = $('#muncipalConstiList1').val();	
	var electionIds = []; 
	$('#muncipalElectnYears :selected').each(function(i, selected){ 
	  electionIds[i] = $(selected).val(); 
	});
	
	var partyLists = []; 
	$('#partyList :selected').each(function(i, selected){ 
	  partyLists[i] = $(selected).val(); 
	});
	
	
	if(constituencyId == null || constituencyId ==0){
		$('#errorDiv').append('Please Select Constituency .</br> ');
		isValid = false;
	}
	
	if(electionIds == null || electionIds.length ==0){
		$('#errorDiv').append('Please Select Election Years .</br> ');
		isValid = false;
	}
	
	if(partyLists == null || partyLists.length ==0){
		$('#errorDiv').append('Please Select Party . ');
		isValid = false;
	}
	
	if(isValid){
		var jsObj = {
				 electionId : electionIds,
				 partyList : partyLists,
				 task : "getPartyWiseELecitonDetails"
				 }

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPartyWiseELecitonDetailsAction.action?"+rparam;

		callAjaxFor(jsObj,url);
	}
}


function callAjaxFor(jsObj,url)
{
			 var myResults;
			 var callback = {			
 		               success : function( o ) {
						try {
						myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task == "getElectionYearsForCosntituency"){
									if(myResults != null && myResults.length >0){
										$('#muncipalElectnYears').find('option').remove();
										for(var i in myResults){
											$('#muncipalElectnYears').append('<option value="'+myResults[i].id+'">'+myResults[i].name+'</option>');
										}
									}
								}
								else if(jsObj.task == "getPartyListForElection"){
									if(myResults != null && myResults.length >0){
										$('#partyList').find('option').remove();
										for(var i in myResults){
											$('#partyList').append('<option value="'+myResults[i].id+'">'+myResults[i].name+'</option>');
										}
									}
									
								}
								
								else if( jsObj.task= "getPartyWiseELecitonDetails"){
									alert("success");
								}
						}catch (e){
							console.log(e);
						}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };
 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
}
</script>
<body>
	<div id="mainDiv" style="border:2px solid #78BCE8;padding:20px;border-radius:5px;margin-top:25px;">
	<div id="headingDiv"> Major Parties Election Results </div>
	<div class="container m_top20" style="margin-left:150px;">
		<div id="errorDiv" style="color:red;margin-left:130px;font-size:13px;font-weight:bold;"></div>
		<div class="row-fluid">
			<div class="span5">
				 <div class="row-fluid" >
					<div class="span4">
                         <label class="pull-left" style="font-weight:bold;">Constituency :<span class="requiredFont">*</span> </label>						  
					</div>
					<div class="span8">
						<select  id="muncipalConstiList1" onchange="getElectionYear();"></select>
						
					</div>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span5">
				 <div class="row-fluid">
					<div class="span4">
                         <label class="pull-left" style="font-weight:bold;"> Election Years :<span class="requiredFont">*</span> </label>						  
					</div>
					<div class="span8">	<select  id="muncipalElectnYears" onchange="getPArtyDetails(this.value);" multiple="true" ></select></div>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span5">
				 <div class="row-fluid">
					<div class="span4">
                         <label class="pull-left" style="font-weight:bold;"> Party :<span class="requiredFont">*</span> </label>						  
					</div>
					<div class="span8">	<select  id="partyList" multiple="true" ></select></div>
				</div>
			</div>
		</div>
		
		<input type="button" class="btn btn-info offset2" value="Submit" onclick="getPartyWiseELecitonDetails();"/>
	</div>
	</div>
</body>
<script>

</script>
</html>