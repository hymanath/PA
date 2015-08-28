<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voter Caste Search</title>

<script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
 <script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>
 <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
 <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.2.0/js/tab.js"></script>
<style type="text/css">

.labelClass{
float:left;
margin:3px 7px 0px 0px;
}
.titleHeading{
    color: steelblue;
    font-family: verdana;
    font-size: 13px;
    font-weight: bold;
    margin-bottom: 20px;
	font-size: 16px;
	text-align:center;
	margin-top: 20px;
 }
 .selectDiv{
	 width:80%;
	 padding-top:10px;
	 padding-bottom:10px;
	 font-family: verdana;
	 font-size: 12px;
	 margin-left:auto;
	 margin-right:auto;
	 font-weight:bold;
	 color:#333333;
 }
 
  fieldset{
    border: 3px solid #CFD6DF;
    margin-bottom: 10px;
    padding: 10px;
}
body {
    color: #000000;
}

 #topButtons,#bottomButtons{
   margin-top:10px;
 }

   .requiredFont{
	color:red;
	font-size:13px;
}
#mainFieldset{
 margin:20px;
}

input[type="text"]{
		border-radius: 4px 4px 4px 4px;
		color: #000000;
		display: inline-block;
		font-size: 13px;
		line-height: 18px;
		padding: 4px;
	}
	
.nav-tabs > .active > a, .nav-tabs > .active > a:hover {
    background-color: #81BEF7 !important;
}	
#VoterdataTable,#voterDataTablesearch,#casteDataTable,#surveyStatusDatatable,#basicTable{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

#VoterdataTable tr:nth-child(even),#voterDataTablesearch tr:nth-child(even),#casteDataTable tr:nth-child(even),#surveyStatusDatatable
tr:nth-child(even),#basicTable tr:nth-child(even){background:#EdF5FF;}

#VoterdataTable td,#voterDataTablesearch td,#casteDataTable td,#surveyStatusDatatable td,#basicTable td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}

#VoterdataTable th,#voterDataTablesearch th,#casteDataTable th,#surveyStatusDatatable th,#basicTable th{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: left;
	color:#333333;
	}



table.dataTable tr.odd {
    background-color: #ffffff;
}
table.dataTable tr.even {
    background-color:#EdF5FF;
}
table.dataTable tr.odd td.sorting_1 {
    background-color: #ffffff;
}
table.dataTable tr.even td.sorting_1 {
    background-color: #EdF5FF;
}
.titleHeading1{
    color: steelblue;
    font-family: verdana;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 20px;
    margin-top: 20px;
   
}
</style>

<script type="text/javascript">
var presentPublication = 11;

function getDistrictsInAState(){
	var jsObj=
			{
				id:1,
				task:"districtsInState"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);
}

function getConstituenciesInDistrict(){
	var jsObj=
			{
				id:$('#districtSelectId').val(),
				task:"constituenciesInDistrict"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);
}


function getMandalsOrMuncipalities1(areaType){

	var jsObj=
			{
				id:$('#constituencySelectId').val(),
				task:"subRegionsInConstituency",
				areaType:areaType
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);

}

function getHamletsOrRegions(){

	var jsObj=
			{
				id:$('#mandalSelectId').val(),
				task:"hamletsOrWardsInRegion"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);

}


function getBoothsInTehsilOrMunicipality(){

	var jsObj=
			{
				id:$('#mandalSelectId').val(),
				constId:$('#constituencySelectId').val(),
				task:"boothsInTehsilOrMunicipality"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);

}

function callAjaxForLocations(jsObj,url)
{
			
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
								if(jsObj.task == "districtsInState")
								{
									buildDistricts(myResults);										
								}
								else if(jsObj.task == "constituenciesInDistrict")
								{
                                     buildConstituencies(myResults);
								}
								else if(jsObj.task == "subRegionsInConstituency")
								{
									buildMandalsOrMuncipalities(myResults);
								}
								else if(jsObj.task == "hamletsOrWardsInRegion")
								{
									buildHamletsOrWardsInRegion(myResults);

								}
								else if(jsObj.task == "boothsInTehsilOrMunicipality")
								{
									buildBooths(myResults);
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


function buildDistricts(results)
{

	$('#districtSelectId').find('option').remove();
	$('#districtSelectId').append('<option value="0">Select</option>');

	for(var i=0;i<results.length;i++)
		$('#districtSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}

function buildConstituencies(results)
{
  $('#constituencySelectId').find('option').remove();
  $('#constituencySelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#constituencySelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}

function buildMandalsOrMuncipalities(results)
{
 $('#mandalSelectId').find('option').remove();
 $('#mandalSelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#mandalSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}


function buildHamletsOrWardsInRegion(results){

	$('#wardSelectId').find('option').remove();
	$('#wardSelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#wardSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');


}

function buildBooths(results){

	$('#boothSelectId').find('option').remove();
	$('#boothSelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#boothSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}
function showLocationsDiv(){

		if($('#scopeId').val() == "0")
		{
			$('#regionstitleDiv').css('display','none');
			$('#locationsDiv').css('display','none');
		}
		else
	    {
			$('#regionstitleDiv').css('display','block');
			$('#locationsDiv').css('display','block');
		}

		$('#constituencySelectId ,#mandalSelectId , #wardSelectId ,#wardSelectId,#boothSelectId').find('option').remove();

		$('#constituencySelectId ,#mandalSelectId , #wardSelectId ,#wardSelectId,#boothSelectId').append('<option value="0">Select</option>');

		$('#districtSelectId').val("0");

	if($('#scopeId').val() == "2")
	{
		$('#stateSelect').css('display','block');
		$('#districtSelect , #constituencySelect , #mandalSelect , #wardSelect , #boothSelect').css('display','none');
		
	}
	else if($('#scopeId').val() == "3")
	{
		$('#stateSelect , #districtSelect').css('display','block');

		$('#constituencySelect , #mandalSelect ,#wardSelect ,#boothSelect').css('display','none');
		
	}
	else if($('#scopeId').val() == "4")
	{
		$('#stateSelect , #districtSelect , #constituencySelect').css('display','block');

		$('#mandalSelect , #wardSelect , #boothSelect').css('display','none');
	
	}
	else if($('#scopeId').val() == "5" || $('#scopeId').val() == "7")
	{
		$('#stateSelect , #districtSelect , #constituencySelect ,#mandalSelect').css('display','block');
		
		$('#wardSelect , #boothSelect').css('display','none');
	}
	else if( $('#scopeId').val() == "6" || $('#scopeId').val() == "8")
	{
		$('#stateSelect , #districtSelect , #constituencySelect , #mandalSelect , #wardSelect ').css('display','block');
		
		$('#boothSelect').css('display','none');
	}
	else if( $('#scopeId').val() == "9")
	{
		$('.locationDivClass').css('display','block');
	}

}

function getMandalsOrMuncipalities()
{
	if($('#scopeId').val() == "5" || $('#scopeId').val() == "6")
      getMandalsOrMuncipalities1("RURAL");
	else if($('#scopeId').val() == "7" || $('#scopeId').val() == "8")
	 getMandalsOrMuncipalities1("URBAN");
	else if($('#scopeId').val() == "9")
	 getMandalsOrMuncipalities1("");

}

function getHamletsOrWards(){

	if($('#scopeId').val() == "9"){
 	 getHamletsOrRegions();
	 getBoothsInTehsilOrMunicipality();
	}else{
		  getHamletsOrRegions();
  
	}

}

function clearFieldsData(){
		$("#errorMsgAlert").html("");
		var mandalId=document.getElementById("mandalField");
	    var panchayatFieldId=document.getElementById("panchayatField");
		var pollingStationFieldId=document.getElementById("pollingStationField");
		removeSelectElements(pollingStationFieldId);
		removeSelectElements(mandalId);
		removeSelectElements(panchayatFieldId);
		$('#constituencyList').val(0);
		
	}
	function clearErrDiv(){
	$("#errorMsgAlert").html("");
	$("#errorMsgAlert1").html("");
	
	}
	function clearData()
	{
	
	$('#reportLevel').val(1);
	$('#reportLevel1').val(0);
	$('#constituencyList').val(0);
	$('#constituencyList1').val(0);
	$("#mandalDiv").hide();
	$("#panchayatDiv").hide();
	$("#pollingStationDiv").hide();
	$("#voterId").val('');
	$("#reqHouseNo").val('');
	$("#voterName").val('');
	$("#gaurdianName").val('');
		$("#fromAge").val('');
		$("#toAge").val('');
		$("#fromSno").val('');
		$("#toSno").val('');
	$('#startWith').attr('checked', 'checked');  
	$('#allGenderId').attr('checked', 'checked'); 
	$("#voterCountData").html('');
	$("#voterDetailsDiv").html('');
	$("#CastevoterData").html('');
	
}
</script>
</head>
<body>

 <div style="width:960px;margin-left:auto;margin-right:auto;margin-top:20px;">
    <ul class="nav nav-tabs" id="myTab">
	
	<li class="active"> <a href="#statusDiv">STATUS REPORT </a></li>
<li ><a href="#voterSearchDiv">VOTER CASTE SEARCH </a></li>
<li><a href="#voterCountDetailsDiv">VOTER CASTE REPORT </a></li>
		<c:if test="${fn:contains(sessionScope.USER.entitlements,'CTP_CASTE_ADMIN_ENTITLEMENT')}">
<li><a href="#adminTabDiv" >ADMIN </a></li>
</c:if>
</ul>
<div class="tab-content widget" style="margin-top:-20px;">
	 	
	 
	 <div id="statusDiv"  class="tab-pane active">
	 <img src="./images/Loading-data.gif" alt="Processing Image" id="voterDetailsImg" style="width: 65px;" class="offset4"/>
	    <div class="titleHeading" >CONSTITUENCY WISE OVERVIEW
	 </div>
   <div id="statusContentDiv"></div>
   <span style='display:none;' class="offset4" id='ajaxLoad3'><img src='./images/icons/goldAjaxLoad.gif' /></span>
    <div id="surveyStatusDetailsDiv"></div>
	</div>
	<div id="voterSearchDiv"  class="tab-pane ">
      <div class="titleHeading" >VOTER CASTE SEARCH
	 </div>
	 
      <div id="AlertMsg" style="font-family: verdana;font-size: 13px;color:red;"></div>
	  <div id="errorMsgAlert" style="font-family: verdana;font-size:14px;color:red;margin-left:100px;"></div>
   
	  <div id="reportLevelDiv" class="selectDiv">Select Level<font class="requiredFont">*</font><select id="reportLevel" class="selectWidth" style="margin-left:76px;width:165px;" name="constituencyList" onchange="clearFieldsData(),showReportLevel1(this.options[this.selectedIndex].value);">

		<option value=1>Constituency</option>
		<option value=2>Mandal</option>
	    <option value=3>Panchayat</option>
		<option value=4>PollingStation</option>
		</select>
      </div>
	
	
	  <div id="ConstituencyDiv" class="selectDiv">
	    
		 Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;width:165px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="clearErrDiv(),getMandalOrMuncipalityList();"/>
		 
	  <span style='display:none;float: right;' id='ajaxLoad'><img src='./images/icons/search.gif' /></span>		
	  </div>
	  
	 

	<div id="mandalDiv" class="selectDiv" style="display:none;">
	     <span id="mandalSpan">Select Mandal</span><font class="requiredFont">*</font>
		 <select id="mandalField" class="selectWidth" name="state" onchange="clearErrDiv(),getPanchayatOrWardsList('panchayat','panchayatField');getPanchayatOrWardsList('pollingstationByPublication','pollingStationField');" style="margin-left:60px;width:165px;"></select>
	  </div>
	  
	  <div id="panchayatDiv" class="selectDiv" style="display:none;">
	    Select Panchayat<font class="requiredFont">*</font> 	
	    <select id="panchayatField" class="selectWidth" name="state"  onchange="clearErrDiv();" style="margin-left:39px;width:165px;"></select>
	  </div>
	  
	
	  <div id="pollingStationDiv" class="selectDiv" style="display:none;">
	    Select PollingStation<font class="requiredFont">*</font><select id="pollingStationField" class="selectWidth" name="state"  style="margin-left:20px;width:165px;" onchange="clearErrDiv();"></select>
	  </div>
	  
	  
	  <div class="selectDiv">
	   VoterId<input style="width:154px;margin-left: 116px;" type="text" id="voterId" />
	  </div>
	  
	  <div class="selectDiv">
	    House No<input style="width:154px;margin-left: 102px;" type="text" id="reqHouseNo" />
	  </div>
	  
	  <div class="selectDiv">
	   Name<input style="width:154px;margin-left: 127px;" type="text" id="voterName" />
	   <div class="row pull-right">
	   <div class="span2">
	   <label class="radio"  style="margin-left: 9px;">
	   <input type="radio"name="voterNameChkBox" checked="true" id="startWith"  value="start" /><b style="font-size: 12px; font-family: verdana;"> Start With</b>
         </label>
		 </div>
		 <div class="span3">
		<label class="radio"style="margin-left: -53px;">
	   <input type="radio"  id="anyWhere" name="voterNameChkBox" value="any"/><b style="font-size: 12px; font-family: verdana;"> Any Where</b>
	 </label>
	 </div>
	 </div>
	  </div>
	  
	  <div class="selectDiv">
	   Guardian Name<input style="width:154px;margin-left:62px;" type="text" id="gaurdianName" />
	  </div>
	  
	  <div class="selectDiv">
	   Gender 
	    <div class="row "style="padding: 0px 0px 0px 162px; margin-top: -22px; ">
		   <div class="span">
		   <label class="radio">
	   <input type="radio" checked="true"  value="all" name="genderChkBox" id="allGenderId"/><b style="font-size: 12px; font-family: verdana;"> All </b>
	    </label>
		 </div>
		 	   <div class="span">
		 <label class="radio">
	   <input type="radio" id="maleSelect" name="genderChkBox"  value="male"  /><b style="font-size: 12px; font-family: verdana;"> Male </b>
	    </label>
			 </div>
			 	   <div class="span">
			 <label class="radio">
	   <input type="radio" id="femaleSelect" name="genderChkBox"  value="female" /><b style="font-size: 12px; font-family: verdana;"> Female</b>
	       </label>
		    </div>
	  </div>
	    </div>
	  <div class="selectDiv">
	   Age Between <input style="width:67px;margin-left:73px;" type="text" id="fromAge" /> <input style="width:67px;" type="text" id="toAge" />
	  </div>
	  <div class="selectDiv">
	   Serial No Between <input style="width:67px;margin-left:38px;" type="text" id="fromSno" /> <input style="width:67px;" type="text" id="toSno" />
	  </div>
  
	  <input style="margin-left:240px;margin-bottom:10px;" onclick="getVotersInfoForCTP();" class="btn btn-success" type="button" id="searchbtnId" value="Search"/>
	    <span style='display:none;' id='ajaxLoad4'><img src='./images/icons/search.gif' /></span>

<div id="errorMessageDiv" style="display:none;font-weight:bold;color:red" align="center"></div>
	<div id="voterDetailsDiv"></div>
	
	</div>
	<div id="voterCountDetailsDiv" class="tab-pane" >

	 <div class="titleHeading" >VOTER CASTE REPORT
	 </div>
	
	 
	 <div class="row-fluid">
	  <div  class="span10 form-inline offset2">
	    <div id="errorMsgAlert1" style="font-family: verdana;font-size:14px;color:red;"></div>
		<label id="ConstituencyDiv1"> Select Constituency <font class="requiredFont">*</font><s:select theme="simple"  label="Select Your State" name="constituencyList" id="constituencyList1" list="constituencyList" listKey="id" listValue="name" onchange="clearErrDiv(),getMandalOrMuncipalityList();"/></label>&nbsp;&nbsp;
		
	   <label id="reportLevelDiv1" >Select Level<font class="requiredFont">*</font><select id="reportLevel1"  name="constituencyList">
	   <option value=0>Select Location Level</option>
	   <option value="1">Constituency</option>
		<option value=2>Mandal</option>
	    <option value=3>Panchayat</option>
		<option value=4>PollingStation</option>
		</select>
      </label>&nbsp;&nbsp;
	   <input onclick="getCasteVotersCountInRegion();getVotersCountInRegion();" class="btn btn-success" type="button"  value="submit"/>
	
	  </div>
	 	</div>
		<div id="voterCountData" style="margin-top:10px;"></div>
		 <span style='display:none;' class="offset4" id='ajaxLoad2'><img src='./images/icons/goldAjaxLoad.gif' /></span>
		<div id="CastevoterData" style="margin-top:10px;"></div>
		
		<div id="errorMessageDiv1" style="display:none;font-weight:bold;color:red" align="center"></div>
	 
	  <span style='display:none;' id='ajaxLoad1'><img src='./images/icons/search.gif' /></span>

	</div>
	   	<c:if test="${fn:contains(sessionScope.USER.entitlements,'CTP_CASTE_ADMIN_ENTITLEMENT')}">
	   <div id="adminTabDiv"  class="tab-pane">
		
		<div class="titleHeading1 offset3"> MOVE VOTER_CASTE DATA TO FINAL</div>
		<div  class="offset3 form-inline">
		<label id="ConstituencyDiv1"> Select Constituency <font class="requiredFont">*</font><s:select theme="simple"  label="Select Your State" name="constituencyList" id="adminconstituencyList" list="constituencyList" listKey="id" listValue="name"/></label>&nbsp;&nbsp;
		 <input class="btn btn-success" type="button"  value="submit" onclick="saveVoterFinalCasteOfAConstituency();"/>  <span style='display:none;' id='adminajaxLoad'><img src='./images/icons/search.gif' /></span>		
		 </div>
	
		<div class="titleHeading1 offset3"> MOVE VOTER_CASTE FINAL DATA TO A  TABLE</div>
			 <div  class="offset3 form-inline">
		<label id="ConstituencyDiv1" > Select Constituency <font class="requiredFont">*</font><s:select theme="simple"  label="Select Your State" name="constituencyList" id="adminconstituencyList1" list="constituencyList" listKey="id" listValue="name"/></label>&nbsp;&nbsp;
		 <input  class="btn btn-success" type="button"  value="submit" onclick="saveVoterFinalCasteToMainTableOfAConstituency();"/>  <span style='display:none;' id='adminajaxLoad1'><img src='./images/icons/search.gif' /></span>		
		 </div>
		</div>
		</c:if>
</div>
<script>

    $('#myTab a').click(function (e) {

	clearData();
    e.preventDefault();
    $(this).tab('show');
	
    })

function getMandalOrMuncipalityList()
{
    var tempVar = "mandalList";
    var selectElmt = "mandalField";
    var reportLevelValue = $("#reportLevel").val();
	var constituencyId = $("#constituencyList").val(); 

	if(reportLevelValue == 5)
     tempVar = "muncipalityList";
			
		var jsObj=
			{
				constituencyId:constituencyId,
				tempVar:tempVar,
				selectElmt:selectElmt,
				task:"getMandalOrMuncipalityList"
					
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getMandalOrMuncipalityListForVotersAnalysisAction.action?"+rparam;						
		callAjax(jsObj,url);
		
}

function getWardsForMuncipality()
{
  var constituencyId = $("#constituencyList").val();
  var localEleBodyId = $("#mandalField").val();
  var publicationDateId = presentPublication;
  
  var jsObj=
			{
				constituencyId:constituencyId,
				localEleBodyId:localEleBodyId,
				publicationDateId:publicationDateId,
				selectElmt:"wardField",
				task:"getWardsListForMuncipality"
					
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getWardsListForMuncipalityAction.action?"+rparam;						
		callAjax(jsObj,url);
  
}
	function enableButton(id)
{

	document.getElementById(id).disabled  = false;
}

function disableButton(id)
{
	
	document.getElementById(id).disabled  = true;
}
function showReportLevel1(value)
	{
		if(value == 1)
		{
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'none';
			document.getElementById('panchayatDiv').style.display = 'none';
			document.getElementById('pollingStationDiv').style.display = 'none';
			
		}
		else if(value == 2)
		{
			$("#mandalSpan").html('Select Mandal');
			$("#mandalField").css({ "margin-left" : "60px"} );
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'none';
			document.getElementById('pollingStationDiv').style.display = 'none';
			
			getMandalList('mandalField');

		}
		else if(value == 3)
		{
			
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'block';
			document.getElementById('pollingStationDiv').style.display = 'none';
			
			getPanchayatList1('panchayat','panchayatField');

		}
		else if(value == 4)
		{
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'none';
			document.getElementById('pollingStationDiv').style.display = 'block';
			
			getPanchayatList1('pollingstationByPublication','pollingStationField');
		}
		
	}
function getPanchayatList1(checkedele,selectedEle)
	{
		
		var constituencyId = $("#constituencyList").val();
		var reportLevel = $("#reportLevel").val();
		var mandalId=document.getElementById("mandalField");
		var value1 = '';
		if(mandalId.selectedIndex != -1 && mandalId != 0)
		{
			var name=mandalId.options[mandalId.selectedIndex].name;
			var value1=mandalId.options[mandalId.selectedIndex].value;
		}
		var type = "mandal";
		if(value1.charAt(0) =="1"){
		 type = "muncipality";
		}
		var value = value1.substring(1);
		var publicationValue = presentPublication;
		var alertEl = document.getElementById("AlertMsg");
		alertEl.innerHTML = '';
		if(mandalId.selectedIndex != -1 && mandalId != 0)
		{
			var selectname = mandalId.options[mandalId.selectedIndex].text;
			var flag= selectname.search("MUNCIPALITY");
		}
		if(flag == -1){
		if(mandalId.selectedIndex != -1 && mandalId != 0)
		{
			var selectname = mandalId.options[mandalId.selectedIndex].text;
			var flag= selectname.search("Corp");
		}
		}
		 if(value1 !='' && value1 == 0)
		{
			alertEl.innerHTML ='<P>Please Select Mandal</P>';
			return;
		}
		if(flag != -1 && checkedele == "panchayat")
		{
		document.getElementById('panchayatDiv').style.display='none';
		}
		else if(flag != -1 && checkedele == "pollingstationByPublication" && reportLevel == 4)
		{
		 flag = -1;
		 document.getElementById('panchayatDiv').style.display='none';
		}
		if(flag == -1)
		{
		var jsObj=
			{
					
				selected:value,
				checkedele:checkedele,
				selectedEle:selectedEle,
				constituencyId:$("#constituencyList").val(),
				flag:flag,
				type:type,
				publicationValue : publicationValue,
				task:"getPanchayat"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatByConstituencyAction.action?"+rparam;						
		callAjax(jsObj,url);
		
	}
	}
	function getPanchayatOrWardsList(checkedele,selectedEle)
	{
     
	  var publicationValue = presentPublication;
		var constituencyId = $("#constituencyList").val();
		var reportLevel = $("#reportLevel").val();
		var mandalId=document.getElementById("mandalField");
		var value1 = '';
		if(mandalId.selectedIndex != -1 && mandalId != 0)
		{
			var name=mandalId.options[mandalId.selectedIndex].name;
			var value1=mandalId.options[mandalId.selectedIndex].value;
		}
		var type = "mandal";
		if(value1.charAt(0) =="1"){
		 type = "muncipality";
		}
		var value = value1.substring(1);
		
		var alertEl = document.getElementById("AlertMsg");
		if(alertEl !=null)
			alertEl.innerHTML = '';
		if(mandalId.selectedIndex != -1 && mandalId != 0)
		{
			var selectname = mandalId.options[mandalId.selectedIndex].text;
			var flag= selectname.search("MUNCIPALITY");
		}
		if(flag == -1){
		if(mandalId.selectedIndex != -1 && mandalId != 0)
		{
			var selectname = mandalId.options[mandalId.selectedIndex].text;
			var flag= selectname.search("Corp");
		}
		}
		 if(value1 !='' && value1 == 0)
		{
			alertEl.innerHTML ='<P>Please Select Mandal</P>';
			return;
		}
		if(flag != -1 && checkedele == "panchayat")
		{
		document.getElementById('panchayatDiv').style.display='none';
		}
		else if(flag != -1 && checkedele == "pollingstationByPublication" && reportLevel == 4)
		{
		 flag = -1;
		 document.getElementById('panchayatDiv').style.display='none';
		}
		if(flag == -1 )
		{
		var jsObj=
			{
					
				selected:value,
				checkedele:checkedele,
				selectedEle:selectedEle,
				constituencyId:$("#constituencyList").val(),
				flag:flag,
				type:type,
				publicationValue : publicationValue,
				task:"getPanchayat"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatByConstituencyAction.action?"+rparam;						
		callAjax(jsObj,url);
		
	}

	}
	var isMuncipality;
	var type;
	function getVotersInfoForCTP()
	{
	
	$("#voterDetailsDiv").html('');
	$('#errorMessageDiv').hide();
	$("#errorMsgAlert").html("");
	var id='';
	var publicationDateId ='';
	var flag =true;
	var str ='';
	isMuncipality=false;	
	var constituencyId = $("#constituencyList").val();
	var level = $("#reportLevel").val();
	id = $("#constituencyList").val();
	if(id == 0 ||id == null)
	{
		str +='<div>Please Select Constituency</div>';
		$("#errorMsgAlert").html(str);
		return;
	}
	
	if(level == 2 || level == 3 || level == 4 ){
	id = $("#mandalField").val();
			if(id == 0 || id == null)
			{
				str +='<div>Please Select Mandal</div>';
				$("#errorMsgAlert").html(str);
				return;
			}	
    }
    if(level == 3 ){
	  id = $("#panchayatField").val();
		if(id == 0 || id == null)
		{
			str +='<div>Please Select Panchayat</div>';
			$("#errorMsgAlert").html(str);
			return;
		}
	}
	if(level == 4){
	  id = $("#pollingStationField").val();
        if(id == 0 || id == null)
		{
			str +='<div>Please Select Polling Station</div>';
			$("#errorMsgAlert").html(str);
			return;
		}
	}
	
		
		if(level == 1){
		type = 'constituency';
		id = $("#constituencyList").val();
		selectedType=type;
		selectedTypeId=id;	
	}
	else if(level == 2){
		type = 'mandal';
		id = $("#mandalField").val();
			if(id.slice(0,1)==1){
				isMuncipality=true;
			}
		selectedType=type;
		selectedTypeId=id;
			if(id.charAt(0) =="1"){
				 selectedType = "muncipality";
				madalType = "muncipality";
			}		
   }
   else if(level == 3){
	  type = 'panchayat';
	  id = $("#panchayatField").val();
	  selectedType=type;
	  selectedTypeId=id;	
	}
	else if(level == 4){
		 type = 'booth';
		 id = $("#pollingStationField").val();
		 selectedType=type;
	     selectedTypeId=id;	
	}
	var voterCardId = '';
	var voterName = '';
	var voterNameType = '';
	var guardianName = '';
	var gender = '';
	var startAge = 0;
	var endAge = 0;
	var fromSno = 0;
	var toSno = 0;
	var houseNo ='';
	voterCardId = $.trim($("#voterId").val());
	voterName = $.trim($("#voterName").val());
	houseNo = $.trim($("#reqHouseNo").val());
	if($("#startWith").is(':checked')){
	   voterNameType = 'start';
	}else{
	  voterNameType = 'anywhere';
	}
	guardianName = $.trim($("#gaurdianName").val());

	if($("#maleSelect").is(':checked')){
	   gender = 'M';
	}else if($("#femaleSelect").is(':checked')){
	  gender = 'F';
	}
	var ageStart = $.trim($("#fromAge").val());
	var ageEnd = $.trim($("#toAge").val());
	if(ageStart.length > 0){
	   if(isNaN(ageStart)){
	        str +='<div>Please Enter Valid Start Age</div>';
			flag =false;
	   }else{
	     startAge = ageStart;
	   }
	}
	if(ageEnd.length > 0){
	   if(isNaN(ageEnd)){
	        str +='<div>Please Enter Valid End Age</div>';
			flag =false;
	   }else{
	     endAge = ageEnd;
	   }
	}
	
	if(ageStart.length > 0 && ageEnd.length > 0){
		if(ageStart > ageEnd || (ageStart == 0 && ageEnd == 0)){
			str +='<div>Please Enter Valid Ege Range</div>';
			flag =false;
		}
	}
	if(ageStart.length > 0 && ageEnd.length == 0){
		str +='<div>Please Enter Ege Age</div>';
		flag =false;
	}
	var startSno = $.trim($("#fromSno").val());
	var endSno = $.trim($("#toSno").val());
	if(startSno.length > 0){
	   if(isNaN(startSno)){
	        str +='<div>Please Enter Valid Start Sno</div>';
			flag =false;
	   }else{
	     fromSno = startSno;
	   }
	}
	if(endSno.length > 0){
	   if(isNaN(endSno)){
	        str +='<div>Please Enter Valid End Sno</div>';
			flag =false;
	   }else{
	     toSno = endSno;
	   }
	}
if(!flag){
	  $("#errorMsgAlert").html(str);
	 
	}
	else
	{
		$("#ajaxLoad4").show();
 var arr = [];
 var obj = {
	 voterCardId: voterCardId,
	  voterName:voterName,
	  voterNameType:voterNameType,
	  guardianName:guardianName,
	  gender:gender,
	  startAge:startAge,
	  endAge:endAge,
	  fromSno:fromSno,
	  toSno:toSno,
	  houseNo:houseNo,
	  locationLvl:selectedType,
	  id:selectedTypeId
 }
	 
 arr.push(obj);
 var jObj = {
	 searchArr : arr,
		 task:"ctpSearch"
 }
 $.ajax({
	          type:'POST',
	          url: 'getctpSearchVoterDetails.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jObj)},
			  success: function(result){ 
				  $("#ajaxLoad4").hide();
			  if(result.votersList== null || result.votersList.length == 0)
			  $('#errorMessageDiv').show().html('No Data Avalible For Given Search Details');
			  else
				  buildVoterDetails(result.votersList);
				  },
	          error:function() { 
	           console.log('error', arguments);
	         }
	    });
		}
	}
	
	function getVotersCountDetails()
	{
		
		
		$("#statusContentDiv").html('');
		$("#voterDetailsImg").show();
		var jObj ={
			task : "getVoterCount"
		}
	$.ajax({
	          type:'POST',
	          url: 'getctpVoterCountDetails.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jObj)},
			  success: function(result){ 
				  $("#voterDetailsImg").hide();
			  buildVoterCount(result.votersList);
				  },
	          error:function() { 
	           console.log('error', arguments);
	         }
	    });
		
	}
	function buildVoterCount(result)
	{
		var str = '';
		str+='<table class="table table-bordered" id="basicTable">';
		str+='<thead>';
		str+='<th>District</th>';
		str+='<th>Constituency</th>';
		str+='<th>Total Voters</th>';
		str+='<th>Caste</th>';
		str+='<th>Caste Percentage</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
		{
		str+='<tr>';
		str+='<td>'+result[i].districtName+'</td>';
		str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].count+'</td>';
		str+='<td>'+result[i].casteCount+'</td>';
		str+='<td>'+result[i].percentage+'</td>';
		str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$("#statusContentDiv").html(str);
		

	}
	function buildVoterDetails(result)
	{
		var str = '';
		
		str+='<table class="table table-bordered" id="voterDataTablesearch">';
		str+='<thead>';
		str+='<th>Name</th>';
		str+='<th>VoterId</th>';
		str+='<th>booth</th>';
		str+='<th>Serial Number</th>';
		str+='<th>HNO</th>';
		str+='<th>gender</th>';
		str+='<th>age</th>';
		str+='<th>caste</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
		{
		
		str+='<tr>';
		str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].voterIdCardNo+'</td>';
		str+='<td>'+result[i].boothName+'</td>';
		str+='<td>'+result[i].fromSno+'</td>';
		str+='<td>'+result[i].houseNo+'</td>';
		str+='<td>'+result[i].gender+'</td>';
		str+='<td>'+result[i].age+'</td>';
		str+='<td>'+result[i].casteName+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$("#voterDetailsDiv").html(str);
		if(result.length > 20)
		{
		$("#voterDataTablesearch").dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 20,
		"aLengthMenu": [[20, 30, 90, -1], [20, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null,null,null
		] 
		});
		}
	}

	
		
	
	function showHide(id)
	{
	
		if(id == "voterSearchDiv")
		{
			
			$("#voterSearchDiv").show();
			$("#voterCountDetailsDiv").hide();
		}
		else
		{
			$("#voterSearchDiv").hide();
			$("#voterCountDetailsDiv").show();
			}
	}

	function getVotersCountInRegion()
	{
	
	$("#voterCountData").html('');
	$('#errorMessageDiv1').html('');
	var constituencyId = $("#constituencyList1").val();
	var regionVal = $("#reportLevel1").val();
	var regionType = $("#reportLevel1 option:selected").text();
	if(constituencyId == 0)
		{
	$("#errorMsgAlert1").html('Please Select Constituency');
	return;
		}
		else if(regionVal == 0)
		{
	$("#errorMsgAlert1").html('Please Select region level');
	return;
		}
		if(regionVal == 4)
			regionType = "BOOTH";
		$("#ajaxLoad1").show();
		$("#errorMsgAlert1").html('');
	var jObj = {
		constituencyId :constituencyId,
		locationType : regionType
	}
	$.ajax({
	type : 'GET',
	url : 'getVotersCountInRegionAction.action',
	dataType:'json',
	data:{task:JSON.stringify(jObj)},
	success:function(result)
		{
		$("#ajaxLoad1").hide();
		
		buildVoterCountData(result,jObj.locationType,constituencyId);
				  },
	          error:function() { 
	           console.log('error', arguments);
	         }
	});
		
	}
	function buildVoterCountData(resultList,type,constituencyId)
	{
		var result = resultList.votersList;
		var result1 =  resultList.localbodyList;
		
		if(result.length == 0 && (result1 == null || result1.length == 0))
		{
		 $('#errorMessageDiv1').show().html('No Data Avalible ');
				return;
		}
			var str = '';
		str+='<div class="titleHeading">Voter Caste Mapped Report</div>';
		str+='<table id="VoterdataTable" class="table table-bordered">';
		str+='<thead>';
		if(type == "Constituency")
		str+='<th>Constituency</th>';
		if(type == "Mandal")
		str+='<th>Mandal</th>';
		if(type == "Panchayat")
		str+='<th>Panchayat</th>';
		if(type == "BOOTH")
		str+='<th>Booth</th>';

		str+='<th>Total Voters</th>';
		str+='<th>Caste Voters</th>';
		str+='<th>Caste Voters - Male</th>';
		str+='<th>Caste Voters - Female</th>';
		
		str+='<th>Caste Percentage</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
		{
		str+='<tr>';
		
		str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].count+'</td>';
		
		str+='<td>'+result[i].casteCount+'</td>';
		
		str+='<td>'+result[i].maleCnt+'</td>';
		
		str+='<td>'+result[i].femaleCnt+'</td>';
		
		str+='<td>'+result[i].percentage+'</td>';
		str+='</tr>';
		}
			if(type == "Mandal" &&  (result1 != null && result1.length > 0))
			{
				for(var j in result1)
				{
					str+='<tr>';
					
					str+='<td>'+result1[j].name+'</td>';
					str+='<td>'+result1[j].count+'</td>';
					
					str+='<td>'+result1[j].casteCount+'</td>';
					
					
					str+='<td>'+result1[j].maleCnt+'</td>';
					
					str+='<td>'+result1[j].femaleCnt+'</td>';
					
					str+='<td>'+result1[i].percentage+'</td>';
					str+='</tr>';
				}
			}
		str+='</tbody>';
		str+='</table>';
		$("#voterCountData").html(str);
		var dataLength;
		if(result1 != null)
		 dataLength = result.length + result1.length ;
		else
		dataLength = result.length;
		if(dataLength > 20 )
		{
		$("#VoterdataTable").dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 20,
		"aLengthMenu": [[20, 30, 90, -1], [20, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null
		] 
		});
		}
		
	}
	function buildCasteVoterCountData(resultList,type,constituencyId)
	{
	
		var result = resultList.votersList;
		var result1 =  resultList.localbodyList;
		if(result.length == 0 && (result1 == null || result1.length == 0))
		{
			$('#errorMessageDiv1').show().html('No Data Avalible ');
			return;
		}
		var str = '';
			str+='<div class="titleHeading">Voter Caste Report</div>';
		str+='<table class="table table-bordered" id="casteDataTable">';
		str+='<thead>';
		if(type == "Constituency")
		str+='<th>Constituency</th>';
		if(type == "Mandal")
		str+='<th>Mandal</th>';
		if(type == "Panchayat")
		str+='<th>Panchayat</th>';
		if(type == "BOOTH")
		str+='<th>Booth</th>';
		str+='<th>Total Caste Assigned Voters</th>';
		str+='<th>Caste </th>';
		str+='<th>Caste Voters</th>';
		str+='<th>Caste Voters - Male</th>';
		str+='<th>Caste Voters - Female</th>';
		str+='<th>Caste Percentage</th>';
		str+='</thead>';
		str+='<tbody>';
		var dataLenth = 0;
		
		for(var i in result)
		{
			dataLenth = dataLenth + result[i].casteList.length ;
			
			for(var j in result[i].casteList)
			{
			str+='<tr>';
			str+='<td>'+result[i].name+'</td>';
			if(result[i].totalCasteVoters > 0 && (type == "Panchayat" || type == "BOOTH"))
				{
			str+='<td><a onclick="openWindow(\''+result[i].id+'\',\''+result[i].selType+'\',0,\''+constituencyId+'\',\'all\',\''+result[i].name+'\');">'+result[i].totalCasteVoters+'</a></td>';
				}
				else
				{
			str+='<td>'+result[i].totalCasteVoters+'</td>';
				}
			str+='<td>'+result[i].casteList[j].cast+'</td>';
		    if(result[i].casteList[j].casteCount > 0 && (type == "Panchayat" || type == "BOOTH"))
				{
			str+='<td><a onclick="openWindow(\''+result[i].id+'\',\''+result[i].selType+'\',\''+result[i].casteList[j].casteStateId+'\',\''+constituencyId+'\',\'all\',\''+result[i].name+'\');">'+result[i].casteList[j].casteCount+'</a></td>';
				}
			else
				{
			str+='<td>'+result[i].casteList[j].casteCount+'</td>';
				}
			
			str+='<td>'+result[i].casteList[j].maleCnt+'</td>';
			
			str+='<td>'+result[i].casteList[j].femaleCnt+'</td>';
			
			str+='<td>'+result[i].casteList[j].percentage+'</td>';
			str+='</tr>';
			}
		}
		
			if(type == "Mandal" &&  (result1 != null && result1.length > 0))
			{
				for(var k in result1)
				{
					dataLenth = dataLenth + result1[k].casteList.length ;
						for(var l in result1[k].casteList)
						{
						str+='<tr>';
						str+='<td>'+result1[k].name+'</td>';
						
						str+='<td>'+result1[k].totalCasteVoters+'</td>';
						
						str+='<td>'+result1[k].casteList[l].cast+'</td>';
						
						str+='<td>'+result1[k].casteList[l].casteCount+'</td>';
						
						str+='<td>'+result1[k].casteList[l].maleCnt+'</td>';
						
						
						str+='<td>'+result1[k].casteList[l].femaleCnt+'</td>';
						
						str+='<td>'+result1[k].casteList[l].percentage+'</td>';
						str+='</tr>';
						}
				}
			}
		str+='</tbody>';
		str+='</table>';
		$("#CastevoterData").html(str);
		
		if(dataLenth > 20)
		{
			$("#casteDataTable").dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 20,
		"aLengthMenu": [[20, 30, 90, -1], [20, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null,null
		] 
		});
		}
	}
	
	function getCasteVotersCountInRegion()
	{
	
		
	$("#CastevoterData").html('');
	$("#ajaxLoad2").show();
	var constituencyId = $("#constituencyList1").val();
	var regionVal = $("#reportLevel1").val();
	var regionType = $("#reportLevel1 option:selected").text();
	
		if(regionVal == 4)
			regionType = "BOOTH";
		
	var jObj = {
		constituencyId :constituencyId,
		locationType : regionType
	}
	$.ajax({
	type : 'GET',
	url : 'getCasteVotersCountInRegionAction.action',
	dataType:'json',
	data:{task:JSON.stringify(jObj)},
	success:function(result)
		{
		$("#ajaxLoad2").hide();
		buildCasteVoterCountData(result,jObj.locationType,constituencyId);
				  },
	          error:function() { 
	           console.log('error', arguments);
	         }
	});
		
		
	}
	function getSurveyStatusDetails()
	{
		$("#ajaxLoad3").show();
	var jObj = {
	task : "surveyStatusDetails"
	}
	$.ajax({
	type : 'GET',
	url : 'getSurveyStatusDetailsAction.action',
	dataType:'json',
	data:{task:JSON.stringify(jObj)},
	success:function(result)
		{
		$("#ajaxLoad3").hide();
		
		buildSurveyStatusDetails(result.subList);
				  },
	          error:function() { 
	           console.log('error', arguments);
	         }
	});
	
	}
	function buildSurveyStatusDetails(result)
	{
		var str ='';
		str+='<div class="titleHeading"> Survey Booths Status Report</div>';
		str+='<table  id="surveyStatusDatatable" class="table table-bordered">';	
		str+='<thead>';
		str+='<tr>';
		str+='<th rowspan ="2">SNO</th>';
		str+='<th rowspan ="2">Constituency</th>';
		str+='<th rowspan ="2">Total Booths</th>';
		str+='<th colspan ="2">Survey On Going Booths</th>';
		str+='<th colspan ="2">Redo Booths</th>';
		str+='<th rowspan ="2">Third Party verification Booths</th>';
		str+='</tr>';
		str+='<tr>';
		str+='<th>Total Booths</th>';
		str+='<th>Completed </th>';
		str+='<th>Total Booths</th>';
		str+='<th>Completed </th>';

		str+='</tr>';
		str+='</thead>';
		var cnt = 1;
		str+='<tbody>';
		for(var i in result)
		{
			
			str+='<tr>';
			str+='<td>'+cnt+'</td>';
			if(result[i].totalBooths == result[i].redoBoothsCompleted)
			str+='<td style="background:green;color:#fff;">'+result[i].name+'</td>';
			else
			str+='<td>'+result[i].name+'</td>';
			str+='<td>'+result[i].totalBooths+'</td>';
			str+='<td>'+result[i].surveyprocessTotal+'</td>';
			str+='<td>'+result[i].surveyprocessCompleted+'</td>';
			str+='<td>'+result[i].redoBoothsTotal+'</td>';
			str+='<td>'+result[i].redoBoothsCompleted+'</td>';
			if(result[i].surveyCompletedBooths > 0)
			str+='<td> Y </td>';
			else
		    str+='<td> N </td>';
			
			str+='</tr>';
			cnt ++;
		}
		str+='</tbody>';
		str+='</table>';	
		$("#surveyStatusDetailsDiv").html(str);
		if(result.length > 20)
		{
		$("#surveyStatusDatatable").dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 20,
		"aLengthMenu": [[20, 30, 90, -1], [20, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null,null,null
		] 
		});
		}
	}
	function openWindow(id,type,casteId,constituencyId,gender,locationName)
	{
		var url = "votersInCasteAction.action?id="+id+"&type="+type+"&casteId="+casteId+"&constituencyId="+constituencyId+"&gender = "+gender+"&locationName="+locationName+"&";
		var updateBrowser = window.open(url,"VoterDetailsInAcaste","scrollbars=yes,height=600,width=1000,left=200,top=200");	
		updateBrowser.focus();

	}
	function saveVoterFinalCasteOfAConstituency()
	{
	var constituencyId = $("#adminconstituencyList").val();
	$("#adminajaxLoad").show();
	var jObj ={
	constituencyId:constituencyId,
			task : "saveVoterFinalCasteOfAConstituency"
		}
	$.ajax({
	          type:'POST',
	          url: 'saveVoterFinalCasteOfAConstituency.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jObj)},
			  success: function(result){ 
				$("#adminajaxLoad").hide();
				  },
	          error:function() { 
	           console.log('error', arguments);
	         }
	    });
	}
	function saveVoterFinalCasteToMainTableOfAConstituency()
	{
	var constituencyId = $("#adminconstituencyList1").val();
	$("#adminajaxLoad1").show();
	var jObj ={
	constituencyId:constituencyId,
			task : "saveVoterFinalCasteToMainTableOfAConstituency"
		}
	$.ajax({
	          type:'POST',
	          url: 'saveVoterFinalCasteToMainTableOfAConstituency.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(jObj)},
			  success: function(result){ 
			$("#adminajaxLoad").hide();
				  },
	          error:function() { 
	           console.log('error', arguments);
	         }
	    });
	}
	
</script>
<script>
getVotersCountDetails();
getSurveyStatusDetails();
</script>
</body>
</html>