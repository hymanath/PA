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
#newsTable table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;border: 1px solid #B2AEAE;text-align: center;}
#newsTable table th{
background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 4px;
    padding-left: 4px;
    padding-right: 4px;
    padding-top: 4px;
    text-align: center;
	color:#333333;
	border: 1px solid #B2AEAE;
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
  <legend class="boxHeading text-center">Political Activities Analysis</legend>
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
    <div class="span3" style="margin-left:240px;"><label style="float: left;"><strong>Select Category<span class="requiredFont">*</span></strong></label>
      <select id="categorySelectDIV" multiple="multiple">
	     <option value="3991">Activities</option>
		 <option value="1">Election Campaign</option>	 
		 <option value="4015">Election Issues</option>
		 <option value="7">Public Problems</option>
      </select>
   </div>
   <div class="span3" style="margin-left:3px;"><label style="float: left;"><strong>Select Party<span class="requiredFont">*</span></strong></label><select name="partySelReport" id="partySel"></select></div>
  
  </div>   
  <div class="span12">
   <div class="span9" style=""><input class="btn btn-success" id="getNewsButton" style="margin-top:10px;margin-left:333px;" onclick="getActititiesCountForReport('dataTable');" type="button" value="Get Data Table"></input><input class="btn btn-success" id="getNewsExcelButton" style="margin-top:10px;margin-left:5px;" onclick="getActititiesCountForReport('excel');" type="button" value="Get Excel"></input><img id="ajaxcallimg" style="display:none;padding-left:10px;padding-top: 10px;" src="images/search.jpg"></div>
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
	$('#categorySelectDIV').multiselect();
  });
  
  $(".ui-multiselect").css("width","220px");

function getActititiesCountForReport(buildType){
    $("#errorMsgDiv").html("");
   $("#newsTable").html("");
  var fromDate = "";
  var toDate = "";
   fromDate = $("#newsFromDateId").val();
   toDate = $("#newsToDateId").val();
	
  
  var locations = "";
  var parties ="";
  var categories ="";
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
	 
	 var selectedCatgs = $("#categorySelectDIV").multiselect("getChecked").map(function(){
			return this.value;    
		}).get();
	 for(var i in selectedCatgs)
	 {
		categories = categories+""+selectedCatgs[i]+",";
	 }
	 if(categories!=0 && categories.length > 0){
	   categories = categories.substring(0,categories.length - 1);
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
	   str+="Please Select Party</br>";
	   invalid = true;
	 }
	  if(categories.length == 0){
	   str+="Please Select Category";
	   invalid = true;
	 }
	 if(invalid){
	    $("#errorMsgDiv").html(str);
	   return;
	 }
	 $("#getNewsButton").attr("disabled","disabled");
	 $("#getNewsExcelButton").attr("disabled","disabled");
     $("#ajaxcallimg").show();
    var jsObj =
		{ 
            fromDate : fromDate,
		    toDate : toDate,
			locationType : $("#locationType").val(),
			locationIds : locations,
			partyIds : parties,
			categories:categories,
			reportType:buildType,
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
		    $("#getNewsButton").removeAttr('disabled'); 
			$("#getNewsExcelButton").removeAttr("disabled");
			$("#ajaxcallimg").hide();
			if(jsObj.reportType == "excel"){
				window.open(myResults);
			}else{
		       buildCountsTable(myResults,jsObj);
			}
		  }else if(jsObj.task == "getPartyList"){
		    populateParties(myResults);
		  }
		}
		catch(e)
		{  
		   $("#getNewsButton").removeAttr('disabled'); 
		   $("#ajaxcallimg").hide();
		   $("#getNewsExcelButton").removeAttr("disabled");
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
		 try{
		  var problemsPresent = false;
		  var campanionPresent = false;
		  var activityPresent = false;
		  var elecIssuesPresent = false;
		  if(myResults[0].elecCampionPresnt != null && myResults[0].elecCampionPresnt == "true"){
		    campanionPresent = true;
		  }
		  if(myResults[0].actitityPresent != null && myResults[0].actitityPresent == "true" ){
		    activityPresent = true;
		  }
		  if(myResults[0].elecIssusPresnt != null && myResults[0].elecIssusPresnt == "true" ){
		    elecIssuesPresent = true;
		  }
		  if(myResults[0].problemsPresnt != null && myResults[0].problemsPresnt == "true" ){
		    problemsPresent = true;
		  }
		  var str ="<table>";
		   if(problemsPresent && !campanionPresent && !activityPresent && !elecIssuesPresent){
		       str+="<tr>";
				if(jsObj.locationType == 1){
				   str+="  <th>District</th>";
				 }else{
				   str+="  <th>Constituency</th>";
				 }
				 str+="  <th>Public Issues</th>";
				 str+="</tr>";
				 
				 for(var i in myResults){
				   str+="<tr>";
				   str+="  <td>"+myResults[i].name+"</td>";
				   str+="  <td>"+myResults[i].count+"</td>";
				   str+="</tr>";
				 }
				 str+="</table>";
			}else{		
					str+="<tr>";
					if(jsObj.locationType == 1){
					  if(activityPresent || campanionPresent)
						 str+="  <th rowspan='3'>District</th>";
					  else
						 str+="  <th rowspan='2'>District</th>";
					}else{
						if(activityPresent || campanionPresent)
						 str+="  <th rowspan='3'>Constituency</th>";
						else
						 str+="  <th rowspan='2'>Constituency</th>";
					 }
					 if(problemsPresent){
					   if(activityPresent || campanionPresent)
						 str+="  <th rowspan='3'>Public Issues</th>";
						else
						 str+="  <th rowspan='2'>Public Issues</th>";
					  }
					  var colspanLength = 0;
					  if(campanionPresent){
						 colspanLength = colspanLength+2;
					  }
					  if(activityPresent){
						 colspanLength = colspanLength+3;
					  }
					  if(elecIssuesPresent){
						 colspanLength = colspanLength+1;
					  }
					for(var i in myResults[0].activitiesList){
					  str+="  <th colspan='"+colspanLength+"'>"+myResults[0].activitiesList[i].name+"</th>";
					}
					str+="</tr>";
					str+="<tr>";
					  for(var i = 0; i<myResults[0].activitiesList.length;i++){
					   
					   if(activityPresent || campanionPresent){
						
						 
						   if(activityPresent){
							  str+="  <th colspan='3'>Activities</th>";			     
						   }
							if(campanionPresent){
							  str+="  <th colspan='2'>Election Campaign</th>";			     
						   }
						   if(elecIssuesPresent){
							 str+="  <th rowspan='2'>Election Issues</th>";
						   }
						 
						 
					   }else{
						   if(elecIssuesPresent){
							 str+="  <th>Election Issues</th>";
						   }
					   }
					  
					  }
					  str+="</tr>";
					  str+="<tr>";
					  for(var i = 0; i<myResults[0].activitiesList.length;i++){		  
						   if(activityPresent || campanionPresent){
							
							 for(var i in myResults[0].activitiesList){
							   if(activityPresent){
								  str+="  <th>Cadre</th><th>MLA/Incharge</th><th>MP/Incharge</th>";			     
							   }
							   if(campanionPresent){
								  str+="  <th>MLA/Incharge</th><th>MP/Incharge</th>";			     
							   }					  
							 }				 
						   }
					  }
					 str+="</tr>";
					for(var i in myResults){//itreating locations
					  str+="<tr>";
					  str+="  <td>"+myResults[i].name+"</td>"
					  if(problemsPresent){
					   str+="  <td>"+myResults[i].count+"</td>"
					  }
					  for(var j in myResults[i].activitiesList){//iterating parties
						if(activityPresent){
							for(var k in myResults[i].activitiesList[j].activitiesList){//iterating activities
							  str+="<td>"+myResults[i].activitiesList[j].activitiesList[k].count+"</td>";
							}
						}
						if(campanionPresent){
							for(var k in myResults[i].activitiesList[j].electionCampanion){//iterating electionCampanion
							  str+="<td>"+myResults[i].activitiesList[j].electionCampanion[k].count+"</td>";
							}
						}
						if(elecIssuesPresent){
							 str+="  <td>"+myResults[i].activitiesList[j].count+"</td>";
						  }
					  }
					  str+="</tr>";
					}
					str+="</table>";
			 }
			$("#newsTable").html(str);
			}catch(e){
			  //alert(e);
			}
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