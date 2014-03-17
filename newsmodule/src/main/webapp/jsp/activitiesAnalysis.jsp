<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Political Activities</title>
<link rel="stylesheet" href="js/ui/1.10.3/smoothness/jquery1.10.3-ui.css" />
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
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">      

	<!-- YUI Dependency files (End) -->

<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="js/bootstrap.min.js"></script>





<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />

<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>

<style type="text/css">
@font-face
{
font-family:eFont;src: url('img/eenadu.ttf');
 }
@font-face{ font-family: 'eFont'; src: url('fonts/eenadu.eot');}
@font-face {
    font-family: "eFont";
    font-style: normal;
    font-weight: normal;
    src: local("?"), url("fonts/eenadu_fonts/eenadu.woff") format("woff"), url("fonts/eenadu_fonts/eenadu.ttf") format("truetype"), url("fonts/eenadu_fonts/eenadu.svg") format("svg");
}
 
 .enadu
{
font-family: eFont;
font-size:20px;
}
#newsTable table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#newsTable table tr:nth-child(even){background:#EdF5FF;}
#newsTable table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#newsTable table th{
background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 4px;
    padding-left: 4px;
    padding-right: 4px;
    padding-top: 4px;
    text-align: left;
	color:#333333;
	}
#newsTable{
	font-family : arial;
	font-size: 13px;
    margin-top:0px;
	padding: 10px 10px 10px 0px;
}
#newsTable table th a{
color:#333333;
}
#errorMsgDiv,#errorMsgDiv1{
  color:red;
  font-size:12px;
  margin-bottom:2px;
  margin-left:251px;
  font-weight:bold;
}
#newsTable{
 overflow-x:scroll;
 margin-left:-15px;
}
</style>
</head>
<body>
 <div class="container">
  <div id="errorMsgDiv"></div>
  <div class="span12">
   <div class="span3" style="margin-left:240px;"><label style="float: left;"><strong>Start Date<span class="requiredFont">*</span></strong></label><input type="text" name="fromDate" class="inpit-block-level  dateField" id="newsFromDateId" readonly="true"/></div>
   <div class="span3" style="margin-left:3px;"><label style="float: left;"><strong>End Date<span class="requiredFont">*</span></strong></label><input type="text" name="toDate" readonly="true" class="inpit-block-level  dateField" id="newsToDateId"/></div>
  </div>
  <div class="span12">
   <div class="span3" style="margin-left:240px;"><label style="float: left;"><strong>Select Location Type<span class="requiredFont">*</span></strong></label><select onchange="showLocations();" id="locationType" ><option value="1">District</option><option value="2">Constituency</option></select></div>
   <div class="span3" id="showHideDistis" style="margin-left:3px;"><label style="float: left;"><strong>Select District<span class="requiredFont">*</span></strong></label><s:select name="districtSelReport" id="districtSel" list="districts" theme="simple" listKey="id" listValue="name"/></div>
   <div class="span3" id="showHideConstis" style="display:none;margin-left:3px;"><label style="float: left;"><strong>Select Constituency<span class="requiredFont">*</span></strong></label><s:select  id="constituencySel" list="assemblies" theme="simple" listKey="id" listValue="name"/></div>
  </div>
  <div class="span12">
   <div class="span3" style="margin-left:240px;"><label style="float: left;"><strong>Select Party<span class="requiredFont">*</span></strong></label><select name="partySelReport" id="partySel"  /></div>
  </div>   
  <div class="span12">
   <div class="span3" style="margin-left:240px;"><input class="btn btn-success" id="getNewsButton" style="margin-top:10px;margin-left:190px;" onclick="getActititiesCountForReport();" type="button" value="Submit"></input></div>
  </div>   
  <div class="span12">
   <div class="span12" id="newsTable"></div>
  </div>  
 </div> 
 
 <script type="text/javascript"> 
 var checkedFileIdsArray = new Array();
 $('#districtSel').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
	}).multiselectfilter({ });
	$('#constituencySel').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
	}).multiselectfilter({ });
 $(document).ready(function() {
 $(".dateField").datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
      changeYear: true,
		maxDate: new Date(),	
	});
	$('.dateField').datepicker('setDate', new Date());
  });
  
  $(".ui-multiselect").css("width","220px");

function getActititiesCountForReport(){
    $("#errorMsgDiv").html("");
   $("#newsTable").html("");
  var fromDate = "";
  var toDate = "";
   fromDate = $("#newsFromDateId").val();
   toDate = $("#newsToDateId").val();
	
  
  var locations = "";
  var parties ="";
  var type = $("#locationType").val();
   if(type == 1){
     var selectedDistrict = $("#districtSel").multiselect("getChecked").map(function(){
			return this.value;    
		}).get();
	 for(var i in selectedDistrict)
	 {
		locations = locations+""+selectedDistrict[i]+",";
	 }
	 if(locations!=0 && locations.length > 0){
	   locations = locations.substring(0,locations.length - 1);
	 }
	} else{
	     var selectedConstis = $("#constituencySel").multiselect("getChecked").map(function(){
			return this.value;    
		 }).get();
	    for(var i in selectedConstis)
	    {
		 locations = locations+""+selectedConstis[i]+",";
	    }
	   if(locations!=0 && locations.length > 0){
	    locations = locations.substring(0,locations.length - 1);
	   }
	
	}
	  var selectedParties = $("#partySel").multiselect("getChecked").map(function(){
			return this.value;    
		}).get();
	 for(var i in selectedParties)
	 {
		parties = parties+""+selectedParties[i]+",";
	 }
	 if(parties!=0 && parties.length > 0){
	   parties = parties.substring(0,parties.length - 1);
	 }
	 
	 var str ="";
	 var invalid = false; 
	 if(locations.length == 0){
	  if(type == 1){
	   str+="Please Select District</br>";
	  }else{
	   str+="Please Select Constituency</br>";
	  }
	   invalid = true;
	 }
	 if(parties.length == 0){
	   str+="Please Select Party";
	   invalid = true;
	 }
	 if(invalid){
	    $("#errorMsgDiv").html(str);
	   return;
	 }
    var jsObj =
		{ 
            fromDate : fromDate,
		    toDate : toDate,
			locationType : $("#locationType").val(),
			locationIds : locations,
			partyIds : parties,
			task:"getActivities"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getActivitiesCountAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function getPartiesList()
	{
		var jsObj=
			{
				partySelectBoxId:"partiesList",
				partiesListForWhome:"partiesListForWhome",
				task:'getPartyList'
			};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "getPartiesListAction.action?"+rparam;
		callAjax(jsObj, url);
	}
function callAjax(jsObj,url)
{
	
	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText); 
		  if(jsObj.task == "getActivities"){
		    buildCountsTable(myResults,jsObj);
		  }else if(jsObj.task == "getPartyList"){
		    populateParties(myResults);
		  }
		}
		catch(e)
		{  
		 
		}  
	 },
	scope : this,
	failure : function( o )
	{
				
	}
  };

 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildCountsTable(myResults,jsObj){
   if(myResults.length == 0){
		   $("#newsTable").html("<div style='margin-left:397px;font-weight:bold;color:red;'>No News Exists</div>");
		 }else{
		    var str ="<table>";
			str+="<tr>";
			if(jsObj.locationType == 1)
			  str+="  <th rowspan='2'>District</th>";
			else
			  str+="  <th rowspan='2'>Constituency</th>";
			for(var i in myResults[0].activitiesList){
			  str+="  <th colspan='"+myResults[0].activitiesList[i].activitiesList.length+"'>"+myResults[0].activitiesList[i].name+"</th>";
			}
			str+="</tr>";
			str+="<tr>";
			  for(var i = 0; i<myResults[0].activitiesList.length;i++){
			    for(var j in myResults[0].activitiesList[0].activitiesList){
			     str+=" <th>"+myResults[0].activitiesList[0].activitiesList[j].name+"</th>";
				}
			  }
			str+="</tr>";
		    for(var i in myResults){//itreating locations
			  str+="<tr>";
			  str+="  <td>"+myResults[i].name+"</td>"
			  for(var j in myResults[i].activitiesList){//iterating parties
			    for(var k in myResults[i].activitiesList[j].activitiesList){//iterating activities
				  str+="<td>"+myResults[i].activitiesList[j].activitiesList[k].count+"</td>";
				}
			  }
			  str+="</tr>";
			}
			str+="</table>";
			$("#newsTable").html(str);
		 }
}

function populateParties(myResults){
   for(var i in myResults)
			{
				$('#partySel').append('<option value="'+myResults[i].id+'">'+myResults[i].name+'</option>');
			}
			$('#partySel').multiselect({	
			     multiple: true,
			     selectedList: 1,
			     hide: "explode"	
	          }).multiselectfilter({ });
   $(".ui-multiselect").css("width","220px");
}
getPartiesList();
function showLocations(){
  var type = $("#locationType").val();
  if(type == 1){
    $("#showHideDistis").show();
	$("#showHideConstis").hide();
  }else{
    $("#showHideDistis").hide();
	$("#showHideConstis").show();
  }
}
 </script>
</body>
</html>