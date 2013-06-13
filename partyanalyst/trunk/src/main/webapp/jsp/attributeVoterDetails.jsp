<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 <!--<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>-->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

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
	<script type="text/javascript" src="js/voterAnalysis/voterAnalysis1.js"></script>	
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
   <script type="text/javascript" src="http://www.google.com/jsapi"></script>
   <%--  <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/facescroll.js"></script>
	   <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/jquery.ui.touch-punch.min.js"></script> --%>

   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
   
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
  <link rel="stylesheet" type="text/css" href="styles/voterAnalysisStyles/voterAnalysisStyles.css"> 
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jquery.dataTables.js"></script>

<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="js/commonVoterDetails.js"></script>
<title>Voters Details</title>

</head>
<body>
<div id="headingDiv" align="center" >Voter Details</div>
<div id="ajaxImageDiv" align="center" style="margin-top: 50px;"><img src="./images/icons/goldAjaxLoad.gif" alt="Processing Image"/> </div>
<div id="mainDiv">
<div id="votersCountId"> </div>
<div id="hamletAndBoothCount"></div>
<div id="voterTypeId"></div>
</div>

<div id="atterHeadingDiv" style="display:none;margin-left:20px"><h5>Check Required Fields To Show</h5></div>
<div id="impFamilySelectedDetails" style="padding:10px 0 10px 27px;border:1px solid #c3c3c3;border-radius:5px; display:none;margin-left:20px;margin-right: 20px;height:100px;">
		
<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="snoId">S NO</input></label>
		
<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="nameId">Name</input></label>

<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="voterId">Voter Id</input></label>

<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="boothNoId">Booth NO</input></label>

<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="genderId" >Gender</input></label>

<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="ageId" >Age</input></label>

<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="houseNoId">House NO</input></label>
			
<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="typeId">Type</input></label>
			
<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="actionsId">Actions</input></label>

<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes notRequiredAttrClass" style="margin:0px 7px 4px 0px;" id="casteId" value="cast" >Caste</input></label>

<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes notRequiredAttrClass" style="margin:0px 7px 4px 0px;" id="partyId" value="party" >Party</input></label>


<div id="impFamilySelectedDetails1"></div>

<div style="float:right;margin-top:-2px;margin-right:10px;"><a class="btn" href="javaScript:{checkForAttributesToDisplay();}">Show Details</a></div>
</div>

<div id="influencePeopleOuterDiv" >
  <div id="influencePeopleInnerDiv" style="border: 1px solid black;border-radius: 4px 4px 4px 4px;margin-top: 11px;padding: 10px;display:none;"></div>
  <div id="totalCountId" style="display:none;"></div>
  <div id="searchResultsDiv"  class="yui-skin-sam yui-dt-sortable" style="display:none;border: 1px solid black;border-radius: 4px 4px 4px 4px;margin-top: 11px;padding: 10px;"></div>
</div>
<div id="votersInnerDiv1" style="height:auto">
<div id="imgDescriptionDiv" style="margin-top: 30px; display:none;margin-left:20px;float:left;">
<b style="margin-left: 5px">Influencing People</b>:<img title="Influencing People" alt="Influencing People" src="./images/icons/influencing.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<b style="margin-left: 50px">Cadre</b>:<img title="Cadre" alt="Cadre" src="./images/icons/cadre.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<b style="margin-left: 50px">Politician</b>:<img title="Politician" alt="Politicion" src="./images/icons/politican.png" style="margin-bottom: 10px;
    margin-left: 16px;"/>
<div id="errorMessageDiv"></div>
</div>
<div id="votersByLocationTabContentDiv_body" class="yui-skin-sam yui-dt-sortable yui-dt table table-bordered table-striped table-hover" style="display:none;">
</div></div>

<div id="influencyPopupDiv"></div>

<script>
var categoryValueId = '${categoryValueId}';
var casteId = '${casteId}';
var gender = '${gender}';
var locationValue= '${locationValue}';
var publicationDateId='${publicationDateId}';
var areaType='${areaType}';
var limit = 100;
function buildVotersByLocPanchayatDataTable1()
{
	 $("#ajaxImageDiv").css('display','none');
	$('.requiredAttrClass').each(function(){
	  $(this).attr('checked','checked');
    });

	$('.notRequiredAttrClass').each(function(){
		$(this).attr('checked',false);
	});
	$('#atterHeadingDiv').show();
	$('#impFamilySelectedDetails').show();
	$('#imgDescriptionDiv').show();

$('#votersByLocationTabContentDiv_body').show();
if(categoryValueId == "0" || categoryValueId == "0")
	return false;
YAHOO.widget.DataTable.ActionLink = function(elLiner, oRecord, oColumn, oData)
{
var str ='';
var id=oRecord.getData("voterIds");
var name = oRecord.getData("name");
var influencePerson=oRecord.getData("influencePerson");

str += '<ul class="dd_menu">';
str += ' <li><i class="icon-th-list"></i>';
str += ' <ul>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'cadre\',\''+name+'\');">Create New Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};"  onclick="openCadreSearchWindow('+id+');">Add To Existing Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'influencingPeople\',\''+name+'\');">Create New Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{getInfluencePeopleOfAnUser('+id+')};">Add To Existing Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'candidate\',\''+name+'\');">Add To Politician</a>';
str += ' </li>';
str += ' </ul>';
str += ' </li>';
str += ' </ul>';
elLiner.innerHTML =str;
}
YAHOO.widget.DataTable.Type = function(elLiner, oRecord, oColumn, oData)
	{
		var str ='';
		var id=oRecord.getData("voterIds");
		var isInfluencePerson=oRecord.getData("isInfluencePerson");
		var isCadere = oRecord.getData("isCadrePerson");
		var isPolitician = oRecord.getData("isPoliticion");
		if(isInfluencePerson == true)
		{
			str+='<img title="InfluencingPeople" alt="InfluencePerson" src="./images/icons/influencing.png"/>';
		}
		if(isCadere == true)
		{
			str+='<img title="Cadre" alt="CadrePerson" src="./images/icons/cadre.png"/>';
		}
		if(isPolitician == true)
		{
			str+='<img title="Politician" alt="Politicion" src="./images/icons/politican.png"/>';
		}
		elLiner.innerHTML =str;
	}
YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var voterId= oRecord.getData("voterIds");


		str+='<a href="javaScript:{getInfluencePeopleOfAnUser('+voterId+');}">Click here</a>';
		
		elLiner.innerHTML=str;
					
	};

var votersByLocBoothColumnDefs = [
{key:"serialNo", label: "SNo",width:15,sortable: true,formatter:"number"},
{key:"name", label: "Name",width:100, sortable: true},
{key:"voterIDCardNo", label: "Voter ID",width:120,sortable: true},
{key:"partNo", label: "Booth No",width:30, sortable:true},
{key:"gender", label: "Gen", width:15, sortable: true},
{key:"age", label: "Age",  width:15,sortable:true},
{key:"houseNo", label: "HNO",width:20, sortable:true},
{key:"relativeFirstName", label: "Guardian Name", width:70,sortable:true},
{key:"Type", label: "Type", width:60,formatter:YAHOO.widget.DataTable.Type},

{key:"mobileNo",label:"MobileNo",sortable:true,width:50},
{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}

];

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetailsForAttributeAjax.action?categoryValueId="+categoryValueId+"&casteId="+casteId+"&gender="+gender+"&locationValue="+locationValue+"&areaType="+areaType+"&publicationDateId="+publicationDateId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [{key:"serialNo", parser:"number"},
"name", "gender", "age", "houseNo","relativeFirstName","voterIDCardNo","mobileNo","voterIds","influencePerson","isInfluencePerson","isPoliticion","isCadrePerson","partNo"],

metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
},
};
var myConfigs = {
initialRequest: "sort=initial&dir=asc&startIndex=0&results=100", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"serialNo", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 100 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
pResults = oResponse.results;
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
}

return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};


}
function checkForAttributesToDisplay(){
	confTrue = true;
	reqfields = "";
	reqfieldsArr = new Array();
	$('.attributeTypeClassIni1').each(function() {
           if($(this).is(':checked')){
		        var ids = ($(this).val()).split(",");
		       reqfieldsArr.push($(this).val());
		       reqfields = reqfields+","+ids[0];
		    }
          });
	$('.notRequiredAttrClass').each(function() {
           if($(this).is(':checked')){
		        var ids = ($(this).val()).split(",");
		       reqfields = reqfields+","+ids[0];
		    }
          });
		   if(reqfields.length > 0)
		  reqfields = reqfields.slice(1);
		  var jsObj=
			{
				
				categoryValueId:categoryValueId,
				casteId: casteId,
				gender:gender,
				reqfields:reqfields,
				startIndex : 0,
				maxIndex : 100,
				sort : "voterId",
				dir : "asc",
				task:"getVoterData"
			}
			
		
		   buildVotersInFamilyWithRetrievedResults();

}

function buildVotersInFamilyWithRetrievedResults(){
	

	$('#votersByLocationTabContentDiv_body').show();
	var x = 1;
	
	    YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
			{
			var vId = oRecord.getData("voterId");
			var vName = oRecord.getData("name");
			var vBoothId = oRecord.getData("boothId");
			 
			elLiner.innerHTML ='<a href="javascript:{};" style="cursor:pointer;" onclick="openProblemEditForm('+vId+','+vBoothId+');">'+vName+'</a>';
				
			} 
         
		  
YAHOO.widget.DataTable.ActionLink = function(elLiner, oRecord, oColumn, oData)
{
var str ='';
var id=oRecord.getData("voterIds");
var name = oRecord.getData("name");
var influencePerson=oRecord.getData("influencePerson");
str += '<ul class="dd_menu">';
str += ' <li><i class="icon-th-list"></i>';
str += ' <ul>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'cadre\',\''+name+'\');">Create New Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};"  onclick="openCadreSearchWindow('+id+');">Add To Existing Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'influencingPeople\',\''+name+'\');">Create New Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{getInfluencePeopleOfAnUser('+id+')};">Add To Existing Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'candidate\',\''+name+'\');">Add To Politician</a>';
str += ' </li>';
str += ' </ul>';
str += ' </li>';
str += ' </ul>';
elLiner.innerHTML =str;
}
		 
YAHOO.widget.DataTable.Type = function(elLiner, oRecord, oColumn, oData)
	    {
			var str ='';
			var id=oRecord.getData("voterId");
			var isInfluencePerson=oRecord.getData("isInfluencePerson");
			var isCadere = oRecord.getData("isCadrePerson");
			var isPolitician = oRecord.getData("isPoliticion");
			if(isInfluencePerson == true)
			{
				str+='<img title="InfluencingPeople" alt="InfluencePerson" src="./images/icons/influencing.png"/>';
			}
			if(isCadere == true)
			{
				str+='<img title="Cadre" alt="CadrePerson" src="./images/icons/cadre.png"/>';
			}
			if(isPolitician == true)
			{
				str+='<img title="Politician" alt="Politicion" src="./images/icons/politican.png"/>';
			}
			elLiner.innerHTML =str;
	    }
		
	YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	{ 
			confTrue = false;
		    var check = false;
			var vId = oRecord.getData("voterId");
			var vBoothId = oRecord.getData("boothId");
            for(var i in selectedVotersArr){
			if(selectedVotersArr[i].boothId == vBoothId && selectedVotersArr[i].voterId == vId ){
				 check = true;
			 }
		    }		
			
			if(check)
			  elLiner.innerHTML="<input type='checkbox' checked='checked' class='familyMemberCheck' value='"+vId+"'/><input type='hidden' class='selectedBoothId' value='"+vBoothId+"'/>";
            else
              elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+vId+"'/><input type='hidden' class='selectedBoothId' value='"+vBoothId+"'/>";			
		  };
	
	for(var i in reqfieldsArr)
	{
		var id3 = reqfieldsArr[i].split(",");
		YAHOO.widget.DataTable[""+id3[0]] = function(elLiner, oRecord, oColumn, oData) 
		{ 			
			var ids={};
			ids[0]=oColumn.field;
			var val = "";
				var categ = oRecord.getData("categoriesList");
				  for(var i in categ){
					if(categ[i].categoryValuesId == ids[0])
					   if(categ[i].name != null)
						val = categ[i].name;
				  }
				
				elLiner.innerHTML=val;			
		  };
		 
		 }


		var votersResultColumnDefs = 
	 [ 		    	             
		{key:"serialNo", label: "SNo",width:15,sortable: true,formatter:"number"},  	            
		{key:"name", label: "Name", sortable: true,width:100},
		{key:"houseNo", label: "HNO",sortable:true,width:20},
		{key:"voterId", label: "Voter Card Id",sortable:true,width:100},
		{key:"partNo", label: "Booth No",sortable:true,width:70}
	]; 
	if($("#ageId").is(':checked'))
	{
		obj = {key:"age",label: "Age",sortable: true,width:15};
		votersResultColumnDefs.push(obj);	
	}
	if($("#genderId").is(':checked'))
	{
		obj = {key:"gender",label: "Gender",sortable: true,width:35};
		votersResultColumnDefs.push(obj);
	}
	if($("#casteId").is(':checked'))
	{
		obj = {key:"casteName",label: "Caste",width:50};
		votersResultColumnDefs.push(obj);
	}
	if($("#partyId").is(':checked'))
	{

		obj = {key:"partyName",label: "Party",width:40};
		votersResultColumnDefs.push(obj);
	}
	if($("#typeId").is(':checked'))
	{
		obj = {key:"Type",label: "Type",formatter:YAHOO.widget.DataTable.Type,width:70};
		votersResultColumnDefs.push(obj);
	}
	if($("#actionsId").is(':checked'))
	{
		obj = {key:"Actions",label: "Actions",formatter:YAHOO.widget.DataTable.ActionLink};
		votersResultColumnDefs.push(obj);
	}

		 for(var i in reqfieldsArr){
		    var ids1 = reqfieldsArr[i].split(",");
		     
		   obj = {
				key:""+ids1[0], label: ""+ids1[1],formatter:YAHOO.widget.DataTable[""+ids1[0]]
					};
					votersResultColumnDefs.push(obj);
					
		 }
		var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDataForAttribute.action?categoryValueId="+categoryValueId+"&casteId="+casteId+"&gender="+gender+"&locationValue="+locationValue+"&areaType="+areaType+"&publicationDateId="+publicationDateId+"&reqfields="+reqfields+"&");
		votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
		votersByLocBoothDataSource.responseSchema = {
		resultsList: "votersList",
		fields : ["voterId","name","houseNo","age","gender","casteName","partyName","isCadrePerson","isInfluencePerson","isPoliticion","categoriesList","partNo","serialNo","voterIds"],
		metaFields: {
		totalRecords: "totalVoters" // Access to value in the server response
		}
		};

		
//end
		var myConfigs = {
		initialRequest: "sort=initial&dir=asc&startIndex=0&results=100", // Initial request for first page of data
		dynamicData: true, // Enables dynamic server-driven data
		sortedBy : {key:"serialNo", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
		   paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : limit 
						})  // Enables pagination
		};
		if(confTrue){
		 var stindx = 0;
		 try{
		stindx =  (parseInt($.trim($('.yui-pg-current-page').html()))-1)*100;
		  //stindx = 0;
		  myConfigs["paginator"] = new YAHOO.widget.Paginator({ 
						rowsPerPage    : limit ,
						initialPage:$('.yui-pg-current-page').html(),
						totalRecords:totalReq
						}) 
		  }catch(e){}
		 myConfigs["initialRequest"] ="sort=name&dir=asc&startIndex="+stindx+"&results="+limit+"&initialPage ="+$('.yui-pg-current-page').html();
		}
		var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
		votersResultColumnDefs, votersByLocBoothDataSource, myConfigs);
        
		votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		totalReq = oResponse.meta.totalRecords;
		return oPayload;
		}


		return {
		oDS: votersByLocBoothDataSource,
		oDT: votersByLocBoothDataTable
		};
    }
	function callAjax(jsObj,url)
{
	var myResults;

	var callback = {			
 		success : function( o ) 
		{
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);
			if (jsObj.task == "getUserCategories")
			{
				buildCategories(myResults);
			}
			else if (jsObj.task == "ckeckForVoterId")
			{
				buildVoterToSelectedType(myResults,jsObj);
			}
			else if(jsObj.task == "getVotersCountForAttribute")
			{
				
			   buildVotersCount(myResults,jsObj)
			}
			   else if (jsObj.task == "getInfluencingPeopleCountForCategoryAndCaste")
			{
				buildVoterTypeDetails(myResults,jsObj);
			}	
		}
		catch (e)
			{
							     
			}  
 		},
 		scope : this,
		failure : function( o ) 
		{
			//alert( "Failed to load result" + o.status + " " + o.statusText);
		}
	   };

 	YAHOO.util.Connect.asyncRequest('POST', url, callback);
}

function getUserCategories(){

	var jsObj=
			{
			 task:"getUserCategories"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getUserCategoriesAction.action?"+rparam;						
		callAjax(jsObj,url);

}
function buildCategories(results){

    var str='';

	for(var i in results){
      
       str+='<label style="float:left;margin:3px;"><input type="checkbox" style="margin:0px 7px 4px 0px;" class="attributeTypeClassIni1" value="'+results[i].id+','+results[i].name+'"/>'+results[i].name+'</label>';
	}

	$('#impFamilySelectedDetails1').html(str);

}
function getVotersCount()
{
var jsObj=
			{
			publicationDateId : publicationDateId,
			categoryValueId :categoryValueId,
			casteId:casteId,
			locationValue:locationValue,
			areaType:areaType,
			task:"getVotersCountForAttribute"
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	   var url = "getTotalVotersForCustomGroup.action?"+rparam;						
		callAjax(jsObj,url);
}

function getInfluencingPeopleCount()
{
	var jsObj=
			{
			publicationDateId : publicationDateId,
			categoryValueId :categoryValueId,
			casteId:casteId,
			gender:gender,
			locationValue:locationValue,
			areaType:areaType,

		task:"getInfluencingPeopleCountForCategoryAndCaste"
	};
	var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj);
		
    var url1 = "getInfluencingPeopleCountForCustomGroupAction.action?"+rparam1;
	callAjax(jsObj,url1);
}
function buildVoterTypeDetails(myResults,jsObj)
{
	
	if(myResults != null)
	{
		 $('#voterTypeId').html('<span>Influencing People : </span><span class="btnName" onclick="getPeopleData(\'InfluencePeople\')"><b style="color:navy;">'+myResults.influencePeopleCount+'</b></span><span style="margin-left: 10px;">Cadre : </span><span class="btnName"  onclick="getPeopleData(\'Cadre\')"><b style="color:navy;">'+myResults.cadreCount+'</b></span><span style="margin-left: 10px;">Politican : </span><span class="btnName" onclick="getPeopleData(\'Politician\')"><b style="color:navy;">'+myResults.politicianCount+'</b></span>'); 
		
	}
	//getVoterDetails();
}

function buildVotersCount(myResults,jsObj)
{
	
	if(myResults != null)
	{
		 $('#votersCountId').html('<span id="totCount">Total Voters : </span><span><b style="color:navy">'+myResults.totalVoters+'</b></span><span id="maleCount" style="margin-left: 10px;">Male Voters : </span><span><b style="color:navy">'+myResults.maleVoters+' </b></span><span id="femaleCount" style="margin-left: 10px;">Female Voters : </span><span><b style="color:navy">'+myResults.femaleVoters+'</b></span>'); 
		
	}
	//getInfluencingPeopleCount(jsObj.customVoterGroupId);
}
function getPeopleData(type)
{
	
var reqBrowser = window.open("influencingCadrePoliticianDisplayWindowAction.action?userVoterCategoryValue="+categoryValueId+"&publicationDateId="+publicationDateId+"&btnName="+type+"&locationValue="+locationValue+"&maintype="+areaType+"&gender="+gender+"&casteId="+casteId+"&","newBrowser","width=1080,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
reqBrowser.focus();
	
}
getVotersCount();
getInfluencingPeopleCount();
getUserCategories();
buildVotersByLocPanchayatDataTable1();


</script>
</body>
</html>