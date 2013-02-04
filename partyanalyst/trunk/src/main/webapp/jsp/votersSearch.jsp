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
	overflow-x: scroll;
	display:none;
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
 #impFamDtlsTitle,#categoriesDiv{
    color: steelblue;
    font-family: verdana;
    font-size: 13px;
    font-weight: bold;
}
#categoriesDiv{
  margin-top:10px;
  margin-bottom:10px;
  margin-left:20px;
}
  #topCount,#bottomCount{
    font-size: 15px;
    margin-left: 20px;
   }
   .requiredFont{
	color:red;
	font-size:13px;
}
#mainFieldset{
 margin:20px;
}
 #bottomSelCount,#topSelCount{ 
	font-size: 14px;
    font-weight: bold;
    margin-left: 10px;
}
#votersBySearchTabContentDiv_body table{
  width: 100%;
}
</style>
<script type="text/javascript">
var votersLimitExist = false;
var limit = 1000;
var confTrue = false;
var totalReq = 1000;
var selectedVotersArr = new Array();
var RlocationLvl;var RlId;var RpublicationDateId;var RvoterCardId;var RvoterName;var RvoterNameType;var RguardianName;var Rgender;var RstartAge;var RendAge;var Rreqfields;var RreqfieldsArr;
  $(document).ready(function(){
     $("#singleAttributeType").live("click",function(){
	      $("#multipleVoterFamiliesEditDiv").html("");
		 $('.allAttributeTypeClass').each(function() {
		       $(this).removeAttr('checked');
            $(this).attr('disabled','disabled');
        });
		$('.singleAttributeTypeClass').each(function() {
		        $(this).removeAttr('checked');
		       $(this).removeAttr('disabled');
         });
	 });
	   $("#allAttributeType").live("click",function(){
	     $("#multipleVoterFamiliesEditDiv").html("");
		 $('.singleAttributeTypeClass').each(function() {
		       $(this).removeAttr('checked');
            $(this).attr('disabled','disabled');
         });
		 $('.allAttributeTypeClass').each(function() {
		        $(this).removeAttr('checked');
		       $(this).removeAttr('disabled');
         });
	   });
	   $("#allAttributeTypeAllId").live("click",function(){
	     if($(this).is(':checked')){
		   $('.allAttributeTypeClass').each(function() {
		      if($(this).attr('id') != "allAttributeTypeAllId"){
		       $(this).removeAttr('checked');
               $(this).attr('disabled','disabled');
			  }
           });
		 }else{
		   $('.allAttributeTypeClass').each(function(){
               $(this).removeAttr('disabled');
			 
           });
		 }
	  });
      $("#singleAttributeTypeId").live("click",function(){
	     if($(this).is(':checked')){
		   $('.singleAttributeTypeClass').each(function() {
		      if($(this).attr('id') != "singleAttributeTypeId"){
		       $(this).removeAttr('checked');
               $(this).attr('disabled','disabled');
			  }
           });
		 }else{
		   $('.singleAttributeTypeClass').each(function(){
               $(this).removeAttr('disabled');
			 
           });
		 }
	  });
	  $(".familyMemberCheck").live("click",function(){
	        if($(this).is(':checked')){
			   var obj={
				 voterId:$(this).val(),
			     boothId:$(this).closest("tr").find(".selectedBoothId").val()
			   }
			   selectedVotersArr.push(obj);
			   $("#topSelCount").html("Total Voters Selected For Edit : "+selectedVotersArr.length);
               $("#bottomSelCount").html("Total Voters Selected For Edit : "+selectedVotersArr.length);
	       }else{
			 for(var i in selectedVotersArr){
			   if(selectedVotersArr[i].boothId == $(this).closest("tr").find(".selectedBoothId").val() && selectedVotersArr[i].voterId == $(this).val() ){
				  selectedVotersArr.splice(i, 1);
			   }
		   }
	           $("#topSelCount").html("Total Voters Selected For Edit : "+selectedVotersArr.length);
               $("#bottomSelCount").html("Total Voters Selected For Edit : "+selectedVotersArr.length);
	     }
	   });
  });
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
	  var reqfields = "";
	   var reqfieldsArr = new Array();
	  $('.attributeTypeClassIni').each(function() {
           if($(this).is(':checked')){
		        var ids = ($(this).val()).split(",");
		       reqfieldsArr.push($(this).val());
		       reqfields = reqfields+","+ids[0];
		    }
          });
		  if(reqfields.length > 0)
		  reqfields = reqfields.slice(1);
		  limit = $("#votersPageLimit").val();
		  getCategoriesForAUser();
		  selectedVotersArr = new Array();
		  $("#topSelCount").html("Total Voters Selected For Edit : "+selectedVotersArr.length);
          $("#bottomSelCount").html("Total Voters Selected For Edit : "+selectedVotersArr.length);
	buildVotersByLocBoothDataTable(type,id,publicationDateId,voterCardId,voterName,voterNameType,guardianName,gender,startAge,endAge,reqfields,reqfieldsArr);
	}else{
	  $("#errorMsgAlert").html(str);
	}
  }
  function showNewsDetails(){
  }
  function buildVotersByLocBoothDataTable(locationLvl,lId,publicationDateId,voterCardId,voterName,voterNameType,guardianName,gender,startAge,endAge,reqfields,reqfieldsArr)
	{var x = 1;
	RlocationLvl=locationLvl;RlId=lId;RpublicationDateId=publicationDateId;RvoterCardId=voterCardId;RvoterName=voterName;RvoterNameType=voterNameType;RguardianName=guardianName;Rgender=gender;RstartAge=startAge;RendAge=endAge;Rreqfields=reqfields;RreqfieldsArr=reqfieldsArr;
    
           $("#topButtons").html('<div><input type="button" style="margin-bottom: 14px;margin-left: 20px;" class="btn" value="Edit all selected voters" onclick="getAllVoterFamiliesForEditWithSelection();"/><input class="btn" type="button" value="Select All" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="selectAllCheckBoxesForEdit();"></input><input class="btn" type="button" value="UnSelect All" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="unselectAllCheckBoxes();"></input><img alt="Processing Image" id="imgDiv" style="display:none;margin-left: 37px;margin-bottom: 12px;"src="./images/icons/search.gif"></div>');
		   $("#bottomButtons").html('<div><input type="button" style="margin-bottom: 14px;margin-left: 20px;" class="btn" value="Edit all selected voters" onclick="getAllVoterFamiliesForEditWithSelection();"/><input class="btn" type="button" value="Select All" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="selectAllCheckBoxesForEdit();"></input><input class="btn" type="button" value="UnSelect All" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="unselectAllCheckBoxes();"></input><img alt="Processing Image" id="imgDiv" style="display:none;margin-left: 37px;margin-bottom: 12px;"src="./images/icons/search.gif"></div>');
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
		  { confTrue = false;
		    var check = false;
			var vId = oRecord.getData("voterId");
			var vBoothId = oRecord.getData("boothId");
               for(var i in selectedVotersArr){
			   if(selectedVotersArr[i].boothId == vBoothId && selectedVotersArr[i].voterId == vId ){
				  check = true;
			   }
		      }		
			//elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+vId+","+vBoothId+"'/>";
			if(check)
			  elLiner.innerHTML="<input type='checkbox' checked='checked' class='familyMemberCheck' value='"+vId+"'/><input type='hidden' class='selectedBoothId' value='"+vBoothId+"'/>";
            else
              elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+vId+"'/><input type='hidden' class='selectedBoothId' value='"+vBoothId+"'/>";			
		  };
		 for(var i in reqfieldsArr){
		    YAHOO.widget.DataTable["select"+i] = function(elLiner, oRecord, oColumn, oData) 
		  { 
		     var ids = reqfieldsArr[oColumn.field.charAt(oColumn.field.length-1)].split(",");
			 var val = "";
			var categ = oRecord.getData("categoriesList");
			if(ids[0] == "cast"){
			  if(oRecord.getData("cast") != null)
			    val = oRecord.getData("cast");
			}else if(ids[0] == "party"){
			  if(oRecord.getData("party") != null)
			  val = oRecord.getData("party");
			}
		    else{
			  for(var i in categ){
			    if(categ[i].categoryValuesId == ids[0])
				   if(categ[i].name != null)
				    val = categ[i].name;
			  }
			}
			elLiner.innerHTML=val;			
		  };
		 }
		var votersByLocBoothColumnDefs = [
		{key:"select", label: "Select", formatter:YAHOO.widget.DataTable.select},
		
		{key:"name", label: "Name", sortable: true,formatter:YAHOO.widget.DataTable.NameLink},
		{key:"houseNo", label: "House No", sortable:true,formatter:YAHOO.widget.DataTable.voterLink},
		{key:"boothName", label: "Booth No", sortable:true}
		];
		if($("#voterIdColum").is(':checked')){
		     obj = {key:"voterIdCardNo",label: "Voter Id",sortable: true};
			votersByLocBoothColumnDefs.push(obj);
		}
		if($("#genderColum").is(':checked')){
		   obj = {key:"gender", label: "Gender", sortable: true};
			votersByLocBoothColumnDefs.push(obj);
		}
		if($("#ageColum").is(':checked')){
		   obj = {key:"age", label: "Age", sortable:true};
			votersByLocBoothColumnDefs.push(obj);
		}
		if($("#gaurdianColum").is(':checked')){
		   obj = {key:"gaurdian", label: "GuardName", sortable:true};
			votersByLocBoothColumnDefs.push(obj);
		}
		if($("#relationShipCol").is(':checked')){
		    obj = {key:"relationship", label: "Relationship", sortable:true};
			votersByLocBoothColumnDefs.push(obj);
		}
		 for(var i in reqfieldsArr){
		    var ids = reqfieldsArr[i].split(",");
		   obj = {
				key:"select"+i, label: ""+ids[1], formatter:YAHOO.widget.DataTable["select"+i]
					};
					votersByLocBoothColumnDefs.push(obj);
		 }
		var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVotersInfoBySearchAction.action?locationLvl="+locationLvl+"&publicationDateId="+publicationDateId+"&voterCardId="+voterCardId+"&voterName="+voterName+"&voterNameType="+voterNameType+"&guardianName="+guardianName+"&gender="+gender+"&startAge="+startAge+"&endAge="+endAge+"&id="+lId+"&selIds="+reqfields+"&task=votersData&save=&");
		votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
		votersByLocBoothDataSource.responseSchema = {
		resultsList: "votersList",
		fields: ["name","voterIdCardNo","boothName", "gender", "age", "houseNo","gaurdian","relationship","voterId","boothId","categoriesList","cast","party"],
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
		if(confTrue){
		 var stindx = 0;
		 try{
		  stindx =  (parseInt($.trim($('.yui-pg-current-page').html()))-1)*100;
		  myConfigs["paginator"] = new YAHOO.widget.Paginator({ 
						rowsPerPage    : limit ,
						initialPage:$('.yui-pg-current-page').html(),
						totalRecords:totalReq
						}) 
		  }catch(e){}
		 myConfigs["initialRequest"] ="sort=name&dir=asc&startIndex="+stindx+"&results="+limit+"&initialPage ="+$('.yui-pg-current-page').html();
		}
		var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersBySearchTabContentDiv_body",
		votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);
         $("#votersBySearchTabContentDiv_body").show();
		votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		totalReq = oResponse.meta.totalRecords;
		if(totalReq == null || totalReq == '')
		 totalReq = 0;
		$("#topCount").html("<b>Total Voters: "+totalReq+"</b>");
        $("#bottomCount").html("<b>Total Voters: "+totalReq+"</b>");
		return oPayload;
		}


		return {
		oDS: votersByLocBoothDataSource,
		oDT: votersByLocBoothDataTable
		};
    }
	function selectAllCheckBoxesForEdit(){
	 selectedVotersArr = new Array();
	 $(".familyMemberCheck").each(function() {
            $(this).attr('checked','checked');
			   var obj={
				 voterId:$(this).val(),
			     boothId:$(this).closest("tr").find(".selectedBoothId").val()
			   }
			   selectedVotersArr.push(obj);
       });
	     $("#topSelCount").html("Total Voters Selected For Edit : "+selectedVotersArr.length);
         $("#bottomSelCount").html("Total Voters Selected For Edit : "+selectedVotersArr.length);
	}
	
	function unselectAllCheckBoxes(){
	   selectedVotersArr = new Array();
	   $(".familyMemberCheck").each(function() {
            $(this).removeAttr('checked');
       });
	   $("#topSelCount").html("Total Voters Selected For Edit : "+selectedVotersArr.length);
       $("#bottomSelCount").html("Total Voters Selected For Edit : "+selectedVotersArr.length);
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
function getCategoriesForAUser(){
    var jsObj=
			{
				task:"getCategoriesForAUser"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersInfoBySearchAction.action?task=categories&save=";						
		callAjaxForCandSearch(jsObj,url);

}
function getCategoriesForAUserInital(){
    var jsObj=
			{
				task:"getCategoriesForAUserInitial"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersInfoBySearchAction.action?task=categories&save=";						
		callAjaxForCandSearch(jsObj,url);

}
function buildVotersInFamilySearch(results,hno){
   var temp = false;
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
function buildCategoriesListInit(result){
	  var str ='';
	    str+='<table style="margin-left:5px;margin-bottom:5px;color:#000000;"><tr>';
		str+='   <td style="padding-left:20px;"><input type="checkbox" id="voterIdColum" />  Voter Id</td>';
		str+='   <td style="padding-left:20px;"><input type="checkbox" id="genderColum" />  Gender</td>';
		str+='   <td style="padding-left:20px;"><input type="checkbox" id="ageColum" />  Age</td>';
		str+='   <td style="padding-left:20px;"><input type="checkbox" id="gaurdianColum" />  GuardName</td>';
		str+='   <td style="padding-left:20px;"><input type="checkbox" id="relationShipCol" />  Relationship</td>';
	    str+='</tr><tr>';
	    str+='   <td style="padding-left:20px;"><input type="checkbox" class="attributeTypeClassIni" value="cast,Caste"/>  Caste</td>';
		str+='   <td style="padding-left:20px;"><input type="checkbox" class="attributeTypeClassIni" value="party,Party"/>  Party</td>';
		 var x = 2;
		if(result != null && result.category != null && result.category.length > 0){
		  for(var i in result.category){
		   if(x%5 == 0)
		    str+='<tr>';
		   str+='   <td style="padding-left:20px;"><input type="checkbox" class="attributeTypeClassIni" value="'+result.category[i].id+','+result.category[i].name+'"/>  '+result.category[i].name+'</td>';
	        x = x+1;
			if(x%5 == 0)
		    str+='</tr>';
		  }	
		  if(x%5 != 0)
		    str+='</tr>';
		}else{
		  str+='</tr>';
		}
	   str+='</table>';
	   
	    $("#requiredFieldsToCheck").html(str);
	  }
	function buildCategoriesList(result){
	  var str ='';
	   str+='<div><input type="radio" id="allAttributeType" name="attributeType" checked="checked" /> Select Attributes for all voters to update<span style=" color: #000000;"> (Hint: Please select atmost 500 voters to edit)<span></div>';
	   str+='<table style="margin-left:5px;margin-bottom:5px;color:#000000;"><tr>';
	    str+='   <td style="padding-left:20px;"><input type="checkbox" id="allAttributeTypeAllId" class="allAttributeTypeClass" value="all" />  All</td>';
	    str+='   <td style="padding-left:20px;"><input type="checkbox" class="allAttributeTypeClass" value="cast" />  Caste</td>';
		str+='   <td style="padding-left:20px;"><input type="checkbox" class="allAttributeTypeClass" value="party"/>  Party</td>';
		 var x = 3;
		if(result != null && result.category != null && result.category.length > 0){
		  for(var i in result.category){
		   if(x%5 == 0)
		    str+='<tr>';
		   str+='   <td style="padding-left:20px;"><input type="checkbox" class="allAttributeTypeClass" value="'+result.category[i].id+'"/>  '+result.category[i].name+'</td>';
	        x = x+1;
			if(x%5 == 0)
		    str+='</tr>';
		  }	
		  if(x%5 != 0)
		    str+='</tr>';
		}else{
		  str+='</tr>';
		}
	   str+='</table>';
	   str+='<div><input type="radio" id="singleAttributeType" name="attributeType" /> Select Attributes for each voter to update<span style=" color: #000000;"> (Hint: Please select atmost 30 voters to edit)</span></div>';
	   str+='<table style="margin-left:5px;margin-bottom:5px;color:#000000;"><tr>';
	    str+='   <td style="padding-left:20px;"><input type="checkbox" disabled="disabled" id="singleAttributeTypeId" class="singleAttributeTypeClass" value="all" />  All</td>';
		str+='   <td style="padding-left:20px;"><input type="checkbox" disabled="disabled" class="singleAttributeTypeClass" value="cast" />  Caste</td>';
		str+='   <td style="padding-left:20px;"><input type="checkbox" disabled="disabled" class="singleAttributeTypeClass" value="party"/>  Party</td>';
		var x = 3;
		if(result != null && result.category != null && result.category.length > 0){
		  for(var i in result.category){
		   if(x%5 == 0)
		    str+='<tr>';
		   str+='   <td style="padding-left:20px;"><input type="checkbox" disabled="disabled" class="singleAttributeTypeClass" value="'+result.category[i].id+'"/>  '+result.category[i].name+'</td>';
		  x = x+1;
			if(x%5 == 0)
		    str+='</tr>';
		  }	
		  if(x%5 != 0)
		    str+='</tr>';
		}else{
		  str+='</tr>';
		}
	   str+='</table>';
	    $("#categoriesDiv").html(str);
	  }
	  
	  function getAllVoterFamiliesForEditWithSelection(){

		  var count = 0;
	    
		  if(selectedVotersArr.length == 0){
		    alert("Please select voters to edit");
			return;
		  }
	   var type = 'all';
	   var ids ='';
	    if($("#singleAttributeType").is(':checked'))
		  type = 'individual';
		if(type == 'all'){
		   $('.allAttributeTypeClass').each(function() {
            if($(this).is(':checked'))
			 ids = ids+","+$(this).val();
        }); 
		  
		}else{
		  $('.singleAttributeTypeClass').each(function() {
            if($(this).is(':checked'))
			 ids = ids+","+$(this).val();
			  
		  });
		 
		}
		 if(ids.length == 0){
		   alert("Please check atleast one attribute to edit");
		   return;
		 }
		 ids = ids.slice(1);
		 if(type == 'all'){
		 
		    if(selectedVotersArr.length > 500)
			  {
				alert(" Please select atmost 500 voters to edit");
				  return;
			  }
		   getVotersInfoToEditAUser(ids,$(".familyMemberCheck :first").val());
		 }else{
 	         if(selectedVotersArr.length > 30)
				  {
					alert(" Please select atmost 30 voters to edit");
					  return;
				  }
		     getVotersInfoToEditAllUser(ids,selectedVotersArr);
		 }
	  }
	  function getVotersInfoToEditAllUser(ids,votersIds){
		var jsObj=
				{
					task:"getAllVoters",
		            type:"getAllVoters",
					selectedVoters:new Array(),
					ids:ids,
					votersIds:votersIds
					
				}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersInfoForEditBySearchAction.action?"+rparam+"&save=";						
		callAjaxForCandSearch(jsObj,url);

     }
	 
	  function getVotersInfoToEditAUser(ids,voterId){
		var jsObj=
				{
					task:"getAllVoters",
		            type:"",
					selectedVoters:new Array(),
					ids:ids,
					voterId:voterId
					
				}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersInfoForEditBySearchAction.action?"+rparam+"&save=";						
		callAjaxForCandSearch(jsObj,url);

}
function callAjaxForCandSearch(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
									if(jsObj.task == "getCategoriesForAUser")
								    {
										buildCategoriesList(myResults);
										
								    }else if(jsObj.task == "getAllVoters" && jsObj.type == ""){
									    if(myResults == "notLogged"){
										 openDialogForLoginWindow();
										}else{
										 buildVotersForEditForAll(myResults);
									   } 
									}
									else if(jsObj.task == "updateAllVoters")
								    { 
									   confTrue = true;
									  $("#votersEditSaveAjaxImg").hide();
                                      $("#votersEditSaveButtnImg").removeAttr("disabled");
									  buildVotersByLocBoothDataTable(RlocationLvl,RlId,RpublicationDateId,RvoterCardId,RvoterName,RvoterNameType,RguardianName,Rgender,RstartAge,RendAge,Rreqfields,RreqfieldsArr);
									  alert("Updated Successfully");
									  
									  
								    }else if(jsObj.task == "getAllVoters" && jsObj.type == "getAllVoters"){
									    if(myResults == "notLogged"){
										 openDialogForLoginWindow();
										}else{
										   buildVotersInFamilyForSelectedEdit(myResults);
									   } 
									}
                                    else if(jsObj.task == "updateIndividuls")
								    { 
									  confTrue = true;
									  $("#votersEditSaveAjaxImg").hide();
                                      $("#votersEditSaveButtnImg").removeAttr("disabled");
									  buildVotersByLocBoothDataTable(RlocationLvl,RlId,RpublicationDateId,RvoterCardId,RvoterName,RvoterNameType,RguardianName,Rgender,RstartAge,RendAge,Rreqfields,RreqfieldsArr)
									  alert("Updated Successfully");
									  
								    }
									else if(jsObj.task == "getCategoriesForAUserInitial")
								    { 
									  buildCategoriesListInit(myResults);
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
	
  function buildVotersForEditForAll(results){
    var str = "";
	var castPresent = false;
	var partyPresent = false;
	var totalCategCount = 0;
    if(results != null){
		   str+="<fieldset>";
		    str+="<table>";
		   if(results.castPresent){
		      castPresent = true;
			  str+="<tr>";	
		      str+="    <td><b>Caste:</b></td>";		  
			  str+="    <td><select id='castSelForAll' >";
				        for(var l in results.casteGroupNameList){
						 str+="<option value="+results.casteGroupNameList[l].id+">"+results.casteGroupNameList[l].name+"</option>";
					    }
			  str+="    </select></td></tr>";
		   }  
		   if(results.partyPresent){
		      partyPresent = true;
			  str+="<tr>";	
		      str+="    <td><b>Party Name:</b></td>";		  
			  str+="    <td><select id='partySelForAll' >";
				        for(var l in results.parties){
						 str+="<option value="+results.parties[l].id+">"+results.parties[l].name+"</option>";
					    }
			  str+="    </select></td></tr>";
		   }  
           if(results.categoriesList != null && results.categoriesList.length > 0){
		      totalCategCount = results.categoriesList.length;
			  for(var m in results.categoriesList){
		         str+="   <tr>";
		          str+=" <td><b>"+results.categoriesList[m].userCategoryValueName+":</b><input type='hidden' id='categIdSelForAll"+m+"' value='"+results.categoriesList[m].userCategoryValueId+"' /></td>";
				  str+="    <td><select id='categValSelForAll"+m+"'>";	
						    for(var l in results.categoriesList[m].category){
						      str+="<option value="+results.categoriesList[m].category[l].id+">"+results.categoriesList[m].category[l].name+"</option>";
						     }
				   str+="   </select></td>";
				   str+="   </tr>";
			 }
		    }   	
		   str+="</table>";	
           str+="</fieldset>";	
           str+="<input id='votersEditSaveButtnImg' style='margin-bottom: 20px;margin-left: 360px;margin-top: 10px;' type='button' class='btn btn-success' onclick='updateAllSelectedVotersForAll("+castPresent+","+partyPresent+","+totalCategCount+");' value='Update All Voters'/><input  style='margin-left: 10px;margin-top: -8px;' type='button' class='btn btn-success' onclick='ClearAllSelectedVoters();' value='Clear All Voters'/><img id='votersEditSaveAjaxImg' style='width: 20px; padding-left: 30px; display: none;' src='images/icons/ajaxImg.gif'>";
	    $("#multipleVoterFamiliesEditDiv").html(str);		   
		}
	   
	}
	 function updateAllSelectedVotersForAll(castPresent,partyPresent,count){
	     var votersEditInfo = new Array();
		 var voterIds = '';
		 if(selectedVotersArr.length > 500){
	     alert("Please select atmost 500 voters to edit");
		 return;
	     }
	       for(var i in selectedVotersArr){
		        voterIds = voterIds+","+selectedVotersArr[i].voterId;
		   }
	   
	  voterIds = voterIds.slice(1);
	        var obj={
	    
	        };
			  if(castPresent)
			  obj["castId"] = $("#castSelForAll").val();
			  if(partyPresent)
			  obj["partyId"] = $("#partySelForAll").val();
			  if(count > 0){
			    for(var i=0; i<count ; i++){
				  var val1= $("#categIdSelForAll"+i).val();
				  var val2= $("#categValSelForAll"+i).val();
				  obj["categ"+i] = val1+","+val2;
				}
			  }
			  votersEditInfo.push(obj);

		if(voterIds.length > 0){
		    $("#votersEditSaveAjaxImg").show();
			$("#votersEditSaveButtnImg").attr("disabled", "disabled");	
		     var jsObj=
		  {
		    total:count,
			partyPresent:partyPresent,
			castPresent:castPresent,
			voterIds:voterIds,
			selectedVoters:votersEditInfo,
			task:"updateAllVoters"
		  };
		  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		  var url = "getVotersInfoForEditBySearchAction.action?"+rparam+"&save=";		  
		  callAjaxForCandSearch(jsObj,url);
		}else{
		  alert("Please select voters to edit");
		}
	 }
     
	 function updateAllSelectedVotersForIndividule(castPresent,partyPresent,count){
	    var votersEditInfo = new Array();
     $('.familyVoterEditId').each(function(){
	        var obj={
	    
	        };
			  obj["voterId"] = $(this).val();
			  if(castPresent)
			  obj["castId"] = $(this).closest("table").find(".castallfamily").val();
			  if(partyPresent)
			  obj["partyId"] = $(this).closest("table").find(".partyallfamily").val();
			  if(count > 0){
			    for(var i=0; i<count ; i++){
				  var val1= $(this).closest("table").find(".categ"+i+"main").val();
				  var val2= $(this).closest("table").find(".categ"+i+"ori").val();
				  obj["categ"+i] = val1+","+val2;
				}
			  }
			  votersEditInfo.push(obj);
			
        });
			if(votersEditInfo.length > 30){
			 alert("Please select atmost 30 voters to edit");
			 return;
		    }
			if(votersEditInfo.length == 0){
			 alert("Please select voters to edit");
			 return;
		    }
		if(votersEditInfo.length > 0){
		    $("#votersEditSaveAjaxImg").show();
			$("#votersEditSaveButtnImg").attr("disabled", "disabled");	
		     var jsObj=
		        {
					total:count,
					partyPresent:partyPresent,
					castPresent:castPresent,
					selectedVoters:votersEditInfo,
					task:"updateIndividuls"
		      };
		  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		  var url = "getVotersInfoForEditBySearchAction.action?"+rparam+"&save=";		  
		  callAjaxForCandSearch(jsObj,url);
		}
	 }
	 
	 function buildVotersInFamilyForSelectedEdit(results){
	   var localid = 0;
	   var categCount = 0;
	   var str = "";
	   if(results != null){
		  for(var i in results.boothsList){
			str+= '<div style="margin-top:10px;"><span><b>Panchayat Name: </b>'+results.boothsList[i].panchayatName+'</span> ';
			str+= '&nbsp;&nbsp;<span><b>Booth Name: </b></span>'+results.boothsList[i].boothName+'</div><div style="margin-top:5px;">';
			str+= '<span ><b>Villiage Covered: </b></span>'+results.boothsList[i].villiageCovered+'</div>';
			var family = results.boothsList[i].familiesList;
			for(var j in family){
			   str+="<div class='votersEditTitle'>Voters in House No : "+family[j].houseNo+"</div>";
			   var voters = family[j].votersList;
			   str+="<fieldset>";
			   str+="<table style='width:900px;'>";
			   var k = 0;		   
			   for(k in voters){
				   if(voters[k].categoriesList != null){
					  totalCategories = voters[k].categoriesList.length;
				   }
				   localid = localid+1;
				  if(k == 0)
				  str+="<tr>";	
				  str+="<td  style='width:300px;padding-top:20px;'>";
				  str+="<table>";
				  str+="   <tr><td><b>Voter Name:</b></td><td>"+voters[k].name+"<input type='hidden' class='familyVoterEditId' value='"+voters[k].voterId+"' /></td></tr>";
				  str+="   <tr><td><b>Guardian Name:  </b></td><td>"+voters[k].gaurdian+"</td></tr>";
				  str+="   <tr><td><b>RelationShip:</b></td><td>"+voters[k].relationship+"</td></tr>";
				  str+="   <tr><td><b>Age:</b></td><td>"+voters[k].age+"</td></tr>";
				  str+="   <tr><td><b>Gender:</b></td><td>"+voters[k].gender+"</td></tr>";
				 if(results.castPresent){
 				  str+="   <tr>";	
				  str+="    <td><b>Caste:</b></td>";		  
				  str+="    <td><select id='castvalid"+localid+"' class='castallfamily castallfamily"+j+"' >";
						 for(var l in results.casteGroupNameList){
						   if(voters[k].casteStateId != results.casteGroupNameList[l].id)
							str+="<option value="+results.casteGroupNameList[l].id+">"+results.casteGroupNameList[l].name+"</option>";
						   else
							str+="<option value="+results.casteGroupNameList[l].id+" selected='selected'>"+results.casteGroupNameList[l].name+"</option>";			
						 }
				  str+="    </select></td>";
				  str+="    <td><a title='Apply this value to this family' href='javascript:{};' onclick='applyValueToAllVoters(\"castvalid"+localid+"\",\"castallfamily"+j+"\")'><i class='icon-ok'></i></a></td>";
				  str+="    <td><a title='Apply this value to all families' href='javascript:{};' onclick='applyValueToAllVoters(\"castvalid"+localid+"\",\"castallfamily\")'><i class='icon-ok-sign'></i></a></td>";
				  str+="   </tr>";
				 }
				 if(results.partyPresent){
				  str+="   <tr>";
				  str+="   <td><b>Party Name:</b></td>";
				  str+="    <td><select id='partyvalid"+localid+"' class='partyallfamily partyallfamily"+j+"' >";
						 for(var l in results.parties){
						   if(voters[k].partyId != results.parties[l].id)
							str+="<option value="+results.parties[l].id+">"+results.parties[l].name+"</option>";
						   else
							str+="<option value="+results.parties[l].id+" selected='selected'>"+results.parties[l].name+"</option>";			
						 }
				  str+="    </select></td>";
				  str+="    <td><a title='Apply this value to this family' href='javascript:{};' onclick='applyValueToAllVoters(\"partyvalid"+localid+"\",\"partyallfamily"+j+"\")'><i class='icon-ok'></i></a></td>";
				  str+="    <td><a title='Apply this value to all families' href='javascript:{};' onclick='applyValueToAllVoters(\"partyvalid"+localid+"\",\"partyallfamily\")'><i class='icon-ok-sign'></i></a></td>";
				   str+="   </tr>";
				}
                 if(voters[k].categoriesList != null){
                    categCount = voters[k].categoriesList.length;
                  }				 
				  for(var m in voters[k].categoriesList){
					 str+="   <tr>";
					  str+=" <td><b>"+voters[k].categoriesList[m].userCategoryValueName+":</b><input type='hidden' class='categ"+m+"main' value='"+voters[k].categoriesList[m].userCategoryValueId+"' /></td>";
					  str+="    <td><select id='categ"+m+"val"+localid+"' class='categ"+m+"ori categ"+m+"ori"+j+"' >";	
							for(var l in voters[k].categoriesList[m].category){
							   if(voters[k].categoriesList[m].categoryValuesId != voters[k].categoriesList[m].category[l].id)
								str+="<option value="+voters[k].categoriesList[m].category[l].id+">"+voters[k].categoriesList[m].category[l].name+"</option>";
							   else
								str+="<option value="+voters[k].categoriesList[m].category[l].id+" selected='selected'>"+voters[k].categoriesList[m].category[l].name+"</option>";			
							 }
					   str+="   </select></td>";
					   str+="    <td><a title='Apply this value to this family' href='javascript:{};' onclick='applyValueToAllVoters(\"categ"+m+"val"+localid+"\",\"categ"+m+"ori"+j+"\")'><i class='icon-ok'></i></a></td>";
					   str+="    <td><a title='Apply this value to all families' href='javascript:{};' onclick='applyValueToAllVoters(\"categ"+m+"val"+localid+"\",\"categ"+m+"ori\")'><i class='icon-ok-sign'></i></a></td>";
					   str+="   </tr>";
					 

				 }
				   str+="</table>";
				  str+="</td>";
				   if(k != 0 && k%3 == 2)
					str+="</tr>";	
			   }
				  if(k != 0 && k%2 != 2)
					 str+="</tr>";	
			   str+="</table>";	
			   str+="</fieldset>";		   
			}
		 
		}
		 str+="<input id='votersEditSaveButtnImg' style='margin-bottom: 20px;margin-left: 360px;margin-top: 10px;' type='button' class='btn btn-success' onclick='updateAllSelectedVotersForIndividule("+results.castPresent+","+results.partyPresent+","+categCount+");' value='Update All Voters'/><input  style='margin-left: 10px;margin-top: -8px;' type='button' class='btn btn-success' onclick='ClearAllSelectedVoters();' value='Clear All Voters'/><img id='votersEditSaveAjaxImg' style='width: 20px; padding-left: 30px; display: none;' src='images/icons/ajaxImg.gif'>";
		 $("#multipleVoterFamiliesEditDiv").html(str);
	   }
	}
</script>
</head>
<body>
  <div style="width:960px;margin-left:auto;margin-right:auto;">
      <div class="titleHeading">VOTERS SEARCH</div>
	  <fieldset id="mainFieldset">
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
	  <div class="selectDiv">
	   <table><tr>
	     <td style="width:132px;"><span>Check Required Fields To Show</span></td>
	     <td><span id="requiredFieldsToCheck"></span></td>
	   </tr></table>
	  </div>
	  <div class="selectDiv">
	   <table><tr>
	     <td style="width:155px;"><span>Total Voters To Show For Each Page</span></td>
	     <td><select id="votersPageLimit"><option value="100">100</option><option value="200">200</option><option value="500">500</option><option value="1000">1000</option></select></td>
	   </tr></table>
	  </div>
	  <input style="margin-left:265px;margin-bottom:10px;" onclick="getVotersInfo();" class="btn btn-success" type="button" value="Search"/>
	   </fieldset>
	   <div id="topButtons"></div>
	   <div><span id="topCount"></span><span id="topSelCount"></span></div>
	   <div  id="votersBySearchTabContentDiv_body"   class="yui-skin-sam yui-dt-sortable"></div>
	   <div><span id="bottomCount"></span><span id="bottomSelCount"></span></div>
	   <div id="categoriesDiv"></div>
	   <div id="bottomButtons"></div>
	   <div id="impFamDtlsOuterPopUp" style="display:none;">
		   <div id="impFamDtlsTitle"></div>
		   <div id="impFamDtls"  class="yui-skin-sam yui-dt-sortable" style="border:1px solid black;width: -moz-fit-content;"></div>
		</div>
	  <div id="multipleVoterFamiliesEditDiv"></div>
	  
<script type="text/javascript">
  getCategoriesForAUserInital();
</script>
</div>
</body>
</html>