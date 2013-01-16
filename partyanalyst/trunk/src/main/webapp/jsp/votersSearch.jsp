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
</style>
<script type="text/javascript">
  function getVotersInfo(){
     var level = $("#reportLevel").val();
	 var publicationDateId = $("#publicationDateList").val();
	var type = '';
	var id='';
	var flag =true;
	if(level == 1){
	type = 'constituency';
	id = $("#constituencyList").val();
	if(id == 0 ||id == null)
		{
		str +='Please Select Constituency';
		flag =false;
		}
	}
	else if(level == 2){
	type = 'mandal';
	id = $("#mandalField").val();
	if(id == 0 || id == null)
	{
	str +='Please Select Mandal';
	flag =false;
	}
   }
	else if(level == 3){
	  type = 'panchayat';
	  id = $("#panchayatField").val();
		
	 if(id == 0 || id == null)
			{
				str +='Please Select Panchayat';
				flag =false;
			}
	}
	else if(level == 4){

		 type = 'booth';
		 id = $("#pollingStationField").val();
          if(id == 0 || id == null)
			{
			str +='Please Select Booth';
			flag =false;
			}
	}
	if(publicationDateId == 0 || publicationDateId == null){
	       str +='Please Select Publication Date';
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
	        str +='Please Enter Valid Start Age';
			flag =false;
	   }else{
	     startAge = ageStart;
	   }
	}
	if(ageEnd.length > 0){
	   if(isNaN(ageEnd)){
	        str +='Please Enter Valid End Age';
			flag =false;
	   }else{
	     endAge = ageEnd;
	   }
	}
	if(flag){
	  var jsObj=
			{
				locationLvl:type,	
				id:id,
				publicationDateId:publicationDateId,
				voterCardId:voterCardId,
				voterName:voterName,
				voterNameType:voterNameType,
				guardianName:guardianName,
				gender:gender,
				startAge:startAge,
				endAge:endAge,
				task:"getVotersInfoBySearchCriteria"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersInfoBySearchAction.action?"+rparam+"&save=";					
		callAjax(jsObj,url);
	
	}else{
	  
	}
  }
</script>
</head>
<body>
      <div class="titleHeading">VOTERS SEARCH</div>
      <div id="AlertMsg" style="font-family: verdana;font-size: 13px;"></div>
	  
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
</body>
</html>