<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voters Search</title>
<script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>
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
<style type="text/css">
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
 #votersBySearchTabContentDiv_body table th ,#impFamDtls table th{
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    text-align: left;
   }
   #votersBySearchTabContentDiv_body table td ,#impFamDtls table td{
    color: #676A67;
    font: small-caption;
  }	
  #votersBySearchTabContentDiv_body{
    border: 1px solid black;
    margin-left: auto;
    margin-right: auto;
    width: 982px;
  }
  fieldset{
    border: 3px solid #CFD6DF;
    margin-bottom: 10px;
    padding: 10px;
}
body {
    color: #000000;
}
 #multipleVoterFamiliesEditDiv{
    font-family: Verdana,Arial,sans-serif;
    font-size: 1.1em;
 }
 #topButtons,#bottomButtons{
   margin-top:10px;
 }
 #impFamDtlsTitle {
    color: steelblue;
    font-family: verdana;
    font-size: 13px;
    font-weight: bold;
}
</style>
<script type="text/javascript">
var votersLimitExist = false;
var limit = 100;
  function getVotersInfo(){
    $("#multipleVoterFamiliesEditDiv").html("");
	$("#errorMsgAlert").html("");
    var level = $("#reportLevel").val();
	var publicationDateId = $("#publicationDateList").val();
	var type = '';
	var id='';
	var flag =true;
		var str ='';
	if(level == 1){
	type = 'constituency';
	id = $("#constituencyList").val();
	if(id == 0 ||id == null)
		{
		str +='<div>Please Select Constituency</div>';
		flag =false;
		}
	}
	else if(level == 2){
	type = 'mandal';
	id = $("#mandalField").val();
	if(id == 0 || id == null)
	{
	str +='<div>Please Select Mandal</div>';
	flag =false;
	}
   }
	else if(level == 3){
	  type = 'panchayat';
	  id = $("#panchayatField").val();
		
	 if(id == 0 || id == null)
			{
				str +='<div>Please Select Panchayat</div>';
				flag =false;
			}
	}
	else if(level == 4){

		 type = 'booth';
		 id = $("#pollingStationField").val();
          if(id == 0 || id == null)
			{
			str +='<div>Please Select Polling Station</div>';
			flag =false;
			}
	}
	if(publicationDateId == 0 || publicationDateId == null){
	       str +='<div>Please Select Publication Date</div>';
			flag =false;
	}
	var voterCardId = '';
	var voterName = '';
	var voterNameType = '';
	var guardianName = '';
	var gender = '';
	var startAge = 0;
	var endAge = 0;
	
	voterCardId = $.trim($("#voterId").val());
	voterName = $.trim($("#voterName").val());
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
	if(flag){
	buildVotersByLocBoothDataTable(type,id,publicationDateId,voterCardId,voterName,voterNameType,guardianName,gender,startAge,endAge);
	}else{
	  $("#errorMsgAlert").html(str);
	}
  }
  function showNewsDetails(){
  }
  function buildVotersByLocBoothDataTable(locationLvl,lId,publicationDateId,voterCardId,voterName,voterNameType,guardianName,gender,startAge,endAge)
	{
         $("#topButtons").html('<div style="margin-left:10px;"><b style="font-size:14px;">Hint: Please select atmost 30 voters to edit</b></div><div><input type="button" style="margin-bottom: 14px;margin-left: 20px;" class="btn" value="Edit all selected voters" onclick="getAllVoterFamiliesForEdit();"/><input class="btn" type="button" value="Select All" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="selectAllCheckBoxesForEdit();"></input><input class="btn" type="button" value="UnSelect All" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="unselectAllCheckBoxes();"></input><img alt="Processing Image" id="imgDiv" style="display:none;margin-left: 37px;margin-bottom: 12px;"src="./images/icons/search.gif"></div>');
		 $("#bottomButtons").html('<div style="margin-left:10px;"><b style="font-size:14px;">Hint: Please select atmost 30 voters to edit</b></div><div><input type="button" style="margin-bottom: 14px;margin-left: 20px;" class="btn" value="Edit all selected voters" onclick="getAllVoterFamiliesForEdit();"/><input class="btn" type="button" value="Select All" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="selectAllCheckBoxesForEdit();"></input><input class="btn" type="button" value="UnSelect All" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="unselectAllCheckBoxes();"></input><img alt="Processing Image" id="imgDiv" style="display:none;margin-left: 37px;margin-bottom: 12px;"src="./images/icons/search.gif"></div>');
	    YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
			{
			var vId = oRecord.getData("voterId");
			var vName = oRecord.getData("name");
			var vBoothId = oRecord.getData("boothId");
			elLiner.innerHTML ='<a href="javascript:{};" style="cursor:pointer;" onclick="openProblemEditForm('+vId+','+vBoothId+');">'+vName+'</a>';
				
			}
          YAHOO.widget.DataTable.voterLink = function(elLiner, oRecord, oColumn, oData) 
			{
			var vBoothId = oRecord.getData("boothId");
			var houseNo = oRecord.getData("houseNo");
			elLiner.innerHTML ='<a href="javascript:{};" style="cursor:pointer;" onclick="getVotersInAFamilySearch('+vBoothId+','+publicationDateId+',\''+houseNo+'\');">'+houseNo+'</a>';
				
			}
		YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
		  {
			var vId = oRecord.getData("voterId");
			var vBoothId = oRecord.getData("boothId"); 
			//elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+vId+","+vBoothId+"'/>";
			elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+vId+"'/><input type='hidden' class='selectedBoothId' value='"+vBoothId+"'/>";			
		  };
			
		var votersByLocBoothColumnDefs = [
		{key:"select", label: "Select", formatter:YAHOO.widget.DataTable.select},
		{key:"voterIdCardNo",label: "Voter Id",sortable: true},
		{key:"name", label: "Name", sortable: true,formatter:YAHOO.widget.DataTable.NameLink},
		{key:"houseNo", label: "House No", sortable:true,formatter:YAHOO.widget.DataTable.voterLink},
		{key:"boothName", label: "Booth No", sortable:true},
		{key:"gender", label: "Gender", sortable: true},
		{key:"age", label: "Age", sortable:true},
		{key:"gaurdian", label: "GuardName", sortable:true},
		{key:"relationship", label: "Relationship", sortable:true}
		];

		var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVotersInfoBySearchAction.action?locationLvl="+locationLvl+"&publicationDateId="+publicationDateId+"&voterCardId="+voterCardId+"&voterName="+voterName+"&voterNameType="+voterNameType+"&guardianName="+guardianName+"&gender="+gender+"&startAge="+startAge+"&endAge="+endAge+"&id="+lId+"&save=&");
		votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
		votersByLocBoothDataSource.responseSchema = {
		resultsList: "votersList",
		fields: ["name","voterIdCardNo","boothName", "gender", "age", "houseNo","gaurdian","relationship","voterId","boothId"],
		metaFields: {
		totalRecords: "totalHousesCount" // Access to value in the server response
		}
		};

		var myConfigs = {
		initialRequest: "sort=name&dir=asc&startIndex=0&results="+limit, // Initial request for first page of data
		dynamicData: true, // Enables dynamic server-driven data
		sortedBy : {key:"name", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
		   paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : limit 
						})  // Enables pagination
		};

		var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersBySearchTabContentDiv_body",
		votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

		votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
		}


		return {
		oDS: votersByLocBoothDataSource,
		oDT: votersByLocBoothDataTable
		};
    }
	function selectAllCheckBoxesForEdit(){
	 $(".familyMemberCheck").each(function() {
            $(this).attr('checked','checked');
       });
	}
	
	function unselectAllCheckBoxes(){
	   $(".familyMemberCheck").each(function() {
            $(this).removeAttr('checked');
       });
	}
	function getVotersInAFamilySearch(id,publicationDateId,hNo){
    var jsObj=
			{
					
				hno:hNo,
				id:id,
				publicationDateId:publicationDateId,
				task:"getVotersInAFamilySearch"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "votersFamilyDetailsAction.action?"+rparam;						
		callAjax(jsObj,url);

}
function buildVotersInFamilySearch(results,hno){
    $("#impFamDtlsTitle").html("<b>Voters in house no "+hno+"</b>");
	YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		
		var id=oRecord.getData("voterId");
		var boothId=oRecord.getData("boothId"); 
		var name = oRecord.getData("name");
		
		elLiner.innerHTML ='<a id="openProblemEditFormId" onclick=" openProblemEditForm('+id+','+boothId+');">'+name+'</a>';
		
	}

     var votersResultColumnDefs = [ 		    	             
		    	           	{key:"name", label: "Name", sortable: true,formatter:YAHOO.widget.DataTable.NameLink},
							{key:"gender", label: "Gender", sortable: true},
		    				{key:"age", label: "Age",sortable:true},
							{key:"houseNo", label: "House No",sortable:true},
							{key:"gaurdian", label: "Guardian Name",sortable:true},
							{key:"relationship", label: "Relationship",sortable:true},
							{key:"mobileNo",label:"mobileNo",sortable:true}
						]; 

    var myConfigs = { 
			    
				};
	var myDataSource = new YAHOO.util.DataSource(results);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : ["name","gender","age","houseNo","gaurdian","relationship","voterId","boothId"]
					};

		var familesDataSource = new YAHOO.widget.DataTable("impFamDtls", votersResultColumnDefs,myDataSource, myConfigs);
    $("#impFamDtlsOuterPopUp").dialog({
            modal: true,
            title: "<b>Voters Details</b>",
			width: 970,
            height: 600
           
        });
}
</script>
</head>
<body>
      <div class="titleHeading">VOTERS SEARCH</div>
      <div id="AlertMsg" style="font-family: verdana;font-size: 13px;color:red;"></div>
	  <div id="errorMsgAlert" style="font-family: verdana;font-size:14px;color:red;margin-left:100px;"></div>
      <div id="reportLevelDiv" class="selectDiv">Select Level<font class="requiredFont">*</font><select id="reportLevel" class="selectWidth" style="margin-left:76px;" name="constituencyList" onchange="showReportLevel(this.options[this.selectedIndex].value);">
		<option value=1>Constituency</option>
		<option value=2>Mandal</option>
		<option value=3>Panchayat</option>
		<option value=4>PollingStation</option>
		</select>
      </div>

	  <div id="ConstituencyDiv" class="selectDiv">
	     Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="getMandalList(\'mandalField\');getPublicationDate();"/> &nbsp;&nbsp;	
	     Select Publication Date<font class="requiredFont">*</font> <select id="publicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
		</select>		
	  </div>
	  
	  <div id="mandalDiv" class="selectDiv" style="display:none;">
	     Select Mandal<font class="requiredFont">*</font> <select id="mandalField" class="selectWidth" name="state" onchange="getPanchayatList('panchayat','panchayatField');getPanchayatList('pollingstationByPublication','pollingStationField');" style="margin-left:60px;"></select>
	  </div>
		
	  <div id="panchayatDiv" class="selectDiv" style="display:none;">
	    Select Panchayat<font class="requiredFont">*</font> 	
	    <select id="panchayatField" class="selectWidth" name="state"  style="margin-left:39px;"></select>
	  </div>
	
	  <div id="pollingStationDiv" class="selectDiv" style="display:none;">
	    Select PollingStation<font class="requiredFont">*</font><select id="pollingStationField" class="selectWidth" name="state"  style="margin-left:20px;"></select>
	  </div>
	  
	  <div class="selectDiv">
	   VoterId<input style="width:137px;margin-left: 116px;" type="text" id="voterId" />
	  </div>
	  
	  <div class="selectDiv">
	   Name<input style="width:137px;margin-left: 127px;" type="text" id="voterName" />
	   <input type="radio"name="voterNameChkBox" checked="true" id="startWith" style="margin-left:10px;" value="start" /> Start With <input type="radio" style="margin-left:10px;" id="anyWhere" name="voterNameChkBox" value="any"/> Any Where
	  </div>
	  
	  <div class="selectDiv">
	   Guardian Name<input style="width:137px;margin-left:62px;" type="text" id="gaurdianName" />
	  </div>
	  
	  <div class="selectDiv">
	   Gender <input type="radio" checked="true" style="margin-left:112px;" value="all" name="genderChkBox" /> All <input type="radio" id="maleSelect" name="genderChkBox" style="margin-left:10px;" value="male"  /> Male <input type="radio" id="femaleSelect" name="genderChkBox" style="margin-left:10px;" value="female" /> Female
	  </div>
	  
	  <div class="selectDiv">
	   Age Between <input style="width:59px;margin-left:73px;" type="text" id="fromAge" /> <input style="width:59px;" type="text" id="toAge" />
	  </div>
	  
	  <input style="margin-left:265px;margin-bottom:10px;" onclick="getVotersInfo();" class="btn btn-success" type="button" value="Search"/>
	   <div id="topButtons"></div>
	   
	   <div  id="votersBySearchTabContentDiv_body"   class="yui-skin-sam yui-dt-sortable"></div>
	   
	   <div id="bottomButtons"></div>
	   <div id="impFamDtlsOuterPopUp" style="display:none;">
		   <div id="impFamDtlsTitle"></div>
		   <div id="impFamDtls"  class="yui-skin-sam yui-dt-sortable" style="border:1px solid black;width: -moz-fit-content;"></div>
		</div>
	  <div id="multipleVoterFamiliesEditDiv"></div>
</body>
</html>