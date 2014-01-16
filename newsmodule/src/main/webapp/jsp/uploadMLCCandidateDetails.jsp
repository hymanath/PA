<%@ page language="java" contentType="text/html; charset=utf-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <META http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> Telugudesam Party </title>

<!-- <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>-->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
	<script type="text/javascript" src="js/jquery.google.api/jquery.min.js"></script>

<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	 
	<!-- YUI Skin Sam -->
<!-- YUI Dependency files (End) -->
<script type="text/javascript" src="js/simplePagination/gallaryResponsePagination.js" ></script>
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 
<!--  <script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script> -->
<script type="text/javascript" src="js/jquery.google.api/jquery.2.8.2.combo.js"></script>
 <!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" /> -->
 <link  rel="stylesheet" type="text/css" href="js/jquery.google.api/jquery-ui1.10.3.css"/>
<!-- <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script> -->
 <script type="text/javascript" src="js/jquery.google.api/jquery-ui.js"></script>
<style>
#partyList{margin-left: 48px; width: 220px;}
#designationId{margin-left: 10px; width: 220px;}
</style>

<script>
$('document').ready(function(){
$('#candidateName').val('');
$('#education').val('');
$('#partyList').val(872);
});

function insertCandidateDetails()
{
$('#errStatusDiv').html('');
  var locationId = $('#locationId').val();
  var locationValue = "";
  	
	
  if(locationId == 1)
  {
	locationValue = $('#assembSelReportId option:selected').val();
  }
  else if(locationId == 2)
  {
	locationValue = $('#parliamSelReportId option:selected').val();
  }
  else
  {
   $('#errStatusDiv').html('<b style="color:red">Please Select Location</b>');	
  }
  if($('#candidateName').val() == ""){
   $('#errStatusDiv').html('<b style="color:red">Candidate Name is Required</b>');
  }
  else if($('#partyList').val() <=0)
	$('#errStatusDiv').html('<b style="color:red">Party Name is Required</b>');	
  else if($("#designationId").val() == 0)
	$('#errStatusDiv').html('<b style="color:red">Please Select Designation.</b>');
 
  if(isValid($('#candidateName').val())){
	$('#errStatusDiv').html('<b style="color:red">Candidate Name should not contain #,$,%,& Special charactors</b>');
	return ; 
  }	
  if(isValid($('#education').val())){
	$('#errStatusDiv').html('<b style="color:red">Education should not contain #,$,%,& Special charactors</b>');
	return ; 
  }
 else{
	var jsObj =
		{   
			candidateName : $('#candidateName').val(),
            education: $('#education').val(),
            gender:$('#gender').val(),
			partyId : $('#partyList').val(),
			designationId: $("#designationId").val(),
			locationValue : locationValue,
			locationId : locationId,
			task:"insertMLCCandidateDetails"
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "insertMLCCandidateDetails.action?"+rparam;					callAjax(jsObj,url); 
 }
}
function callAjax(jsObj,url)
{
	 var myResults;
	 var callback = {			
	success : function( o ) {
		try {												
				myResults = YAHOO.lang.JSON.parse(o.responseText);	
			
				if(jsObj.task == "insertMLCCandidateDetails")
				{
					var cssObj = {    
				     'font-weight' : 'bold',
				      'color' : 'green'
			        }


					if(myResults == "success"){
					 $('#successDiv').text("Candidate Details Inserted Successfully....").css(cssObj).show().delay(2000).fadeOut(400);
					 $('#candidateName , #education').val('');
					}else{

					 $('#successDiv').html('<b>Error occured .Try again...</b>');

					}
					
				}
				
				
			}catch (e) {
			
			}  
   },
   scope : this,
   failure : function( o ) {
				//alert( "Failed to load result" + o.status + " " + o.statusText);
			 }
   };
YAHOO.util.Connect.asyncRequest('POST', url, callback);
}


function getRespectedLocationlevel(value)
{
	if(value == 1)
	{
		$('.assembSelReport').show();
		$('.parliamSelReport').hide();
	}
	else
	{
		$('.parliamSelReport').show();
		$('.assembSelReport').hide();
	}
}

function isValid(str){
 var iChars = "#$%&";
 var flag = false;
	for (var i = 0; i < str.length; i++) {
		if (iChars.indexOf(str.charAt(i)) != -1) {			
			flag = true;
		}
    }
	return flag;
}
function clearDiv(divId)
{
  document.getElementById(divId).innerHTML = "";
}
</script>
</head>
<body>


<div style="margin-left:295px;">
<div id="errStatusDiv" style="height: 30px;"></div>
<div id="reportHintDiv" style="margin-bottom:15px;" > Note: Candidate Name and Education should not contain #,$,%,& Special charactors.</div>
  <div>Enter Candidatename :<input type="text"  id="candidateName" onkeyup="clearDiv('errStatusDiv');"/></div>
  <div>Enter Education :<input type="text" style="margin-left:31px;"  id="education" onkeyup="clearDiv('errStatusDiv');"/></div>
  <div>Gender :
  <select type="text" id="gender" style="margin-left:78px; width: 220px;">
    <option value="M">Male</option>
	<option value="F">Female</option>
  </select></div>
  <div>
  Select Party : <s:select id="partyList" list="partiesList" theme="simple" listKey="id" listValue="name"></s:select>
  <!-- <select id="partyList" style="margin-left: 48px; width: 220px;">
	  <option value="0">Select Party</option>
	  <option value="163">BJP</option>
	  <option value="265">CPI</option>
	  <option value="269">CPM</option>
	  <option value="362">INC</option>
	  <option value="990">MIM</option>
	  <option value="872">TDP</option>
	  <option value="886">TRS</option>
	  <option value="1117">YSRCP</option>
  </select> -->
 </div>
 <div>
 Select Designation : <s:select id="designationId" list="selectOptionVOList" theme="simple" listKey="id" listValue="name"></s:select>
 </div>
 Select Location :  <Select id="locationId" onChange="getRespectedLocationlevel(this.value);" style="margin-left:29px; width: 220px;">
 <option value=0>Select Location</option>
 <option value=1>Assembly Constituency</option>
 <option value=2>Parliment Constituency</option>
 </select>
 <div style="display:none;" class="parliamSelReport">Select Constituency
 <s:select name="parliamSelReport" id="parliamSelReportId" list="parlConstiList" theme="simple" listKey="id" listValue="name" style="margin-left:11px; width: 220px;"/>
							</div>
 <div style="display:none;" class="assembSelReport">Select Constituency :
 <s:select name="assembSelReport" id="assembSelReportId" list="assemConstiList" theme="simple" listKey="id" listValue="name" style="margin-left:11x; width: 220px;"/>
  </div>
 
<a class="btn btn-primary" style="margin-left:198px;" href="javascript:{insertCandidateDetails();}">Insert</a>
<div id="successDiv"></div>

</div>


</body>
</html>