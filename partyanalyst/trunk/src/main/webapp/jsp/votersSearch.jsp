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
			<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
 
 <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.9.0/build/tabview/assets/skins/sam/tabview.css"> 
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
    width: 974px;
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
.dd_menu {padding:0px; margin:0; list-style-type:none;}
.dd_menu li {float:left;display:inline;}
.dd_menu li a {padding:0px 20px; display:block; color:#fff; text-decoration:none; font:12px arial, verdana, sans-serif; font-weight: bold;}
.dd_menu li:hover a {text-decoration:underline;}

.dd_menu ul {position:absolute; left:-9999px; top:-9999px; list-style-type:none;}
.dd_menu li:hover {position:relative; background:none;}
.dd_menu li:hover ul {left:-160px; top:15px; padding:3px; border:1px solid grey; width:208px;}
.dd_menu li:hover ul li {border:none;}
.dd_menu li:hover ul li a {height:18px;color:#003366; padding:5px 0px; display:block; font-size:11px; width:208px; line-height:18px; text-indent:5px; color:#444; background:#d0e0ea; text-decoration:none; border:1px solid transparent;}
.dd_menu li:hover ul li a:hover {height:18px; background:#c4d8e6; color:#003366; border:solid 1px #444;}

</style>
<script type="text/javascript">
var selectedType="";
var selectedTypeId="";
var publicationId="";
var madalType="";
var selectedLevel="";

var votersLimitExist = false;
var limit = 1000;
var confTrue = false;
var totalReq = 1000;
var selectedVotersArr = new Array();
var RlocationLvl;var RlId;var RpublicationDateId;var RvoterCardId;var RvoterName;var RvoterNameType;var RguardianName;var Rgender;var RstartAge;var RendAge;var Rreqfields;var RreqfieldsArr;
var RQueryType = "and";var RfromSno;var RtoSno;var RHouseNo;
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
  var isMuncipality;
  function getVotersInfo(){
	isMuncipality=false;
	if($('#mandalField').val().slice(0,1)==1){
		isMuncipality=true;
	}
	$('#errorMessageDiv').hide();
    $("#multipleVoterFamiliesEditDiv").html("");
	$("#errorMsgAlert").html("");
    var level = $("#reportLevel").val();
	 selectedLevel=level;
	var publicationDateId = $("#publicationDateList").val();
	publicationId=publicationDateId;
	var type = '';
	var id='';
	var flag =true;
		var str ='';
	if(level == 1){
	type = 'constituency';
	id = $("#constituencyList").val();
	selectedType=type;
	selectedTypeId=id;
	if(id == 0 ||id == null)
		{
		str +='<div>Please Select Constituency</div>';
		flag =false;
		}
	}
	else if(level == 2){
	type = 'mandal';
	id = $("#mandalField").val();
	
	selectedType=type;
	selectedTypeId=id.substring(1);
	if(id.charAt(0) =="1"){
		 selectedType = "muncipality";
		madalType = "muncipality";
					}
	if(id == 0 || id == null)
	{
	str +='<div>Please Select Mandal</div>';
	flag =false;
	}
   }
	else if(level == 3){
	  type = 'panchayat';
	  id = $("#panchayatField").val();
	  selectedType=type;
	  selectedTypeId=id;
		
	 if(id == 0 || id == null)
			{
				str +='<div>Please Select Panchayat</div>';
				flag =false;
			}
	}
	else if(level == 4){

		 type = 'booth';
		 id = $("#pollingStationField").val();
		 selectedType=type;
	     selectedTypeId=id;
		 
          if(id == 0 || id == null)
			{
			str +='<div>Please Select Polling Station</div>';
			flag =false;
			}
	}
	else if(level == 5){

		 type = 'ward';
		 id = $("#wardField").val();
		 selectedType=type;
	     selectedTypeId=id;
          if(id == 0 || id == null)
			{
			str +='<div>Please Select Ward</div>';
			flag =false;
			}
	}
	else if(level == 6){

		 type = 'hamlet';
		 id = $("#hamletField").val();
		 selectedType=type;
	     selectedTypeId=id;
          if(id == 0 || id == null)
			{
			str +='<div>Please Select Hamlet</div>';
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
	var fromSno = 0;
	var toSno = 0;
	var queryType = "and";
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
	if(flag){
	  var reqfields = "";
	   var reqfieldsArr = new Array();
	  $('.attributeTypeClassIni').each(function() {
           if($(this).is(':checked')){
		        var ids = ($(this).val()).split(",");
				if(ids[0] === "locality")
				{
				var id2 = $("#mandalField").val();
	       		  
				if(type !=  "ward" && id2.charAt(0) !="1")
				reqfieldsArr.push("Hamlet,Hamlet");
				reqfieldsArr.push("LocalArea,LocalArea");
		        reqfields = reqfields+","+ids[0];
				
				}else{
		       reqfieldsArr.push($(this).val());
		       reqfields = reqfields+","+ids[0];
			   }
		    }
          });
		  if(reqfields.length > 0)
		  reqfields = reqfields.slice(1);
		  limit = $("#votersPageLimit").val();
		  getCategoriesForAUser();
		  selectedVotersArr = new Array();
		  $("#topSelCount").html("Total Voters Selected For Edit : "+selectedVotersArr.length);
          $("#bottomSelCount").html("Total Voters Selected For Edit : "+selectedVotersArr.length);
		  //alert(selectedType);
	      //alert(selectedTypeId);
	      //alert(publicationId);

    if($("#andCondChk").is(':checked')){
	   queryType = 'and';
	}else {
	   queryType = 'or';
	}
	buildVotersByLocBoothDataTable(type,id,publicationDateId,voterCardId,voterName,voterNameType,guardianName,gender,startAge,endAge,reqfields,reqfieldsArr,queryType,houseNo,fromSno,toSno);
	   //alert(reqfieldsArr);
	
	}else{
	  $("#errorMsgAlert").html(str);
	}
  }
  function showNewsDetails(){
  }
  function buildVotersByLocBoothDataTable(locationLvl,lId,publicationDateId,voterCardId,voterName,voterNameType,guardianName,gender,startAge,endAge,reqfields,reqfieldsArr,queryType,houseNo,fromSno,toSno)
	{var x = 1;
	RlocationLvl=locationLvl;RlId=lId;RpublicationDateId=publicationDateId;RvoterCardId=voterCardId;RvoterName=voterName;RvoterNameType=voterNameType;RguardianName=guardianName;Rgender=gender;RstartAge=startAge;RendAge=endAge;Rreqfields=reqfields;RreqfieldsArr=reqfieldsArr;RQueryType=queryType;RHouseNo=houseNo;RfromSno=fromSno;RtoSno=toSno;
    
           $("#topButtons").html('<div><input type="button" style="margin-bottom: 14px;margin-left: 20px;" class="btn" value="Edit all selected voters" onclick="getAllVoterFamiliesForEditWithSelection();"/><input class="btn" type="button" value="Select All" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="selectAllCheckBoxesForEdit();"></input><input class="btn" type="button" value="UnSelect All" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="unselectAllCheckBoxes();"></input><img alt="Processing Image" id="imgDiv" style="display:none;margin-left: 37px;margin-bottom: 12px;"src="./images/icons/search.gif"></div>');
		   $("#topButtons").append('<div style="margin-bottom: 10px;margin-right: 20px;float:right;" id="imgDescriptionDiv"><b style="margin-left: 5px">Influencing People</b>:<img style="margin-bottom: 10px;margin-left: 8px;" src="./images/icons/influencing.png" alt="Politicion" title="Politician"><b style="margin-left: 22px">Cadre</b>:<img style="margin-bottom: 10px;margin-left: 8px;" src="./images/icons/cadre.png" alt="Politicion" title="Cadre"><b style="margin-left: 22px">Politician</b>:<img style="margin-bottom: 10px;margin-left: 8px;" src="./images/icons/politican.png" alt="Politicion" title="Politician"></div>');
		   $("#bottomButtons").html('<div><input type="button" style="margin-bottom: 14px;margin-left: 20px;" class="btn" value="Edit all selected voters" onclick="getAllVoterFamiliesForEditWithSelection();"/><input class="btn" type="button" value="Select All" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="selectAllCheckBoxesForEdit();"></input><input class="btn" type="button" value="UnSelect All" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="unselectAllCheckBoxes();"></input><img alt="Processing Image" id="imgDiv" style="display:none;margin-left: 37px;margin-bottom: 12px;"src="./images/icons/search.gif"><input type="button" style="margin-bottom: 14px;margin-left: 20px;" class="btn" value="Create Custom Groups(Cast,Party,Locaities)" onclick="openNewWindow();"/></div>');
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
		  
		  YAHOO.widget.DataTable.ActionLink = function(elLiner, oRecord, oColumn, oData)
	      {
			var str ='';
			var id=oRecord.getData("voterId");
			str += '<ul class="dd_menu">';
			str += ' <li><i class="icon-th-list"></i>';
			str += ' <ul>';
			str += ' <li>';
			str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'cadre\');">Create New Cadre</a>';
			str += ' </li>';
			str += ' <li>';
			str += ' <a href= "javascript:{};" onclick="openCadreSearchWindow('+id+');">Add To Existing Cadre</a>';
			str += ' </li>';
			str += ' <li>';
			str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'influencingPeople\');">Create New Influencing People</a>';
			str += ' </li>';
			str += ' <li>';
			str += ' <a href= "javascript:{getInfluencePeopleOfAnUser('+id+')};">Add To Existing Influencing People</a>';
			str += ' </li>';
			str += ' <li>';
			str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'candidate\');">Add To Politician</a>';
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
		
		 for(var i in reqfieldsArr){
		 var id3 = reqfieldsArr[i].split(",");
		    YAHOO.widget.DataTable[""+id3[0]] = function(elLiner, oRecord, oColumn, oData) 
		  { 
		      //alert(elLiner);
			    
			    //alert(oColumn.field.charAt(oColumn.field.length-1));
				// alert(oData);
		   
		   // var ids = reqfieldsArr[oColumn.field.charAt(oColumn.field.length-1)].split(",");
          //alert(oColumn.field.charAt(oColumn.field.length-1));			
		 var ids={};
		 ids[0]=oColumn.field;
		var val = "";
			var categ = oRecord.getData("categoriesList");
			if(ids[0] == "cast"){
			  if(oRecord.getData("cast") != null)
			    val = oRecord.getData("cast");
			}else if(ids[0] == "party"){
			  if(oRecord.getData("party") != null)
			  val = oRecord.getData("party");
			}else if(ids[0] == "Hamlet"){
			   //  alert("ok");
			  if(oRecord.getData("hamletName") != null)
			  val = oRecord.getData("hamletName");
			}else if(ids[0] == "LocalArea"){
			
			  if(oRecord.getData("localAreaName") != null)
			  val = oRecord.getData("localAreaName");
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
			{key:"boothName", label: "Booth No", sortable:true},
			{key:"Type", label: "Type", width:70,formatter:YAHOO.widget.DataTable.Type},
			{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}
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
		if($("#serialnumbrCol").is(':checked')){
		    obj = {key:"fromSno", label: "Serial Number", sortable:true};
			votersByLocBoothColumnDefs.push(obj);
		}
		 for(var i in reqfieldsArr){
		    var ids1 = reqfieldsArr[i].split(",");
		     
		   obj = {
				key:""+ids1[0], label: ""+ids1[1],sortable:true,formatter:YAHOO.widget.DataTable[""+ids1[0]]
					};
					votersByLocBoothColumnDefs.push(obj);
					
		 }
		var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVotersInfoBySearchAction.action?locationLvl="+locationLvl+"&publicationDateId="+publicationDateId+"&voterCardId="+voterCardId+"&voterName="+voterName+"&voterNameType="+voterNameType+"&guardianName="+guardianName+"&gender="+gender+"&queryType="+queryType+"&fromSno="+fromSno+"&toSno="+toSno+"&houseNo="+houseNo+"&startAge="+startAge+"&endAge="+endAge+"&id="+lId+"&selIds="+reqfields+"&task=votersData&save=&");
		votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
		votersByLocBoothDataSource.responseSchema = {
		resultsList: "votersList",
		fields: ["name","voterIdCardNo","boothName", "gender", "age", "houseNo","gaurdian","relationship","voterId","boothId","categoriesList","cast","party","location","hamletName","localAreaName","fromSno","isInfluencePerson","isCadrePerson","isPoliticion"],
		metaFields: {
		totalRecords: "totalHousesCount" // Access to value in the server response
		}
		};

		
//end
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
		if(totalReq == null)
		{
			$('#votersBySearchTabContentDiv_body').hide();
			$('#imgDescriptionDiv').hide();
			$('#topSelCount').hide();
			$('#topCount').hide();
			$('#bottomCount').hide();
			$('#bottomSelCount').hide();
			$('#topButtons').hide();
			$('#bottomButtons').hide();
			$('#categoriesDiv').hide();
			$('#errorMessageDiv').show().html('No Data Avalible For Given Search Details');
		}
		else
		{
			$('#votersBySearchTabContentDiv_body').show();
			$('#imgDescriptionDiv').show();
			$('#topSelCount').show();
			$('#bottomSelCount').show();
			$('#topButtons').show();
			$('#bottomButtons').show();
			$('#categoriesDiv').show();
			$('#topCount').show();
			$('#bottomCount').show();
			$("#topCount").html("<b>Total Voters: "+totalReq+"</b>");
			$("#bottomCount").html("<b>Total Voters: "+totalReq+"</b>");
		}
        
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
		str+='   <td style="padding-left:20px;"><input type="checkbox" id="serialnumbrCol" />  Serial Number</td>';
	    str+='   <td style="padding-left:20px;"><input type="checkbox" class="attributeTypeClassIni" value="cast,Caste"/>  Caste</td>';
		str+='   <td style="padding-left:20px;"><input type="checkbox" class="attributeTypeClassIni" value="party,Party"/>  Party</td>';
		str+='   <td style="padding-left:20px;"><input type="checkbox" class="attributeTypeClassIni" value="locality,Locality"/>  Locality</td>';
		
		var x = 4;
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
		 if( selectedLevel == 3 || selectedLevel == 4 )
		str+='   <td style="padding-left:20px;"><input type="checkbox" class="allAttributeTypeClass" value="Locality"/>  Locality</td>'; 
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
			if( selectedLevel == 3 || selectedLevel == 4 )
		str+='   <td style="padding-left:20px;"><input type="checkbox" disabled="disabled" class="singleAttributeTypeClass" value="Locality"/>  Locality</td>'; 
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
		// alert(ids);
		 if(type == 'all'){
		 
		    if(selectedVotersArr.length > 500)
			  {
		   		alert(" Please select atmost 500 voters to edit");
				  return;
			  }
			 // alert($(".familyMemberCheck :first").val());
		   getVotersInfoToEditAUser(ids,$(".familyMemberCheck :first").val());
		 }else{
 	         if(selectedVotersArr.length > 30)
				  {
					alert(" Please select atmost 30 voters to edit");
					  return;
				  }
		     getVotersInfoToEditAllUser(ids,selectedVotersArr);
		 }
		 /*  $(".localityallfamily").each(function (){
		  setTimeout(function(){
		 
		 alert($(".localityallfamily").val());
		 var selectid= $(".localityallfamily").attr("id");
		 var locationid= $(".localityallfamily").attr("datalocationdiv");
		 var valj= $(".localityallfamily").attr("dataj");
		 getLocalitiesList(selectid,locationid,valj);
		 },500);}); */
		 var idsArray ={};
		 if($("#singleAttributeType").is(':checked')){
		 /*setTimeout(function(){
		  $(".localityallfamily").each(function (){
		  var i=0;
		 
		// alert($(this).val());
		 var optionValue=$(this).val();
		   if(optionValue != 0){
		 var selectid= $(this).attr("id");
		 var locationid= $(this).attr("datalocationdiv");
		 var valj= $(this).attr("dataj");
		 var optionValue=$(this).val();
		 getLocalitiesList(selectid,locationid,valj);
		 idsArray[i]=selectid;
		 i++;
		  }		  		  
		 })},500);*/ 
		}
	  }
	  function getVotersInfoToEditAllUser(ids,votersIds){
		var jsObj=
				{
					task:"getAllVoters",
		            type:"getAllVoters",
					selectedVoters:new Array(),
					ids:ids,
					votersIds:votersIds,
					selectedType:selectedType,
					selectedTypeId:selectedTypeId,
					publicationId:publicationId
					
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
					voterId:voterId,
					selectedType:selectedType,
					selectedTypeId:selectedTypeId,
					publicationId:publicationId
					
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
									  buildVotersByLocBoothDataTable(RlocationLvl,RlId,RpublicationDateId,RvoterCardId,RvoterName,RvoterNameType,RguardianName,Rgender,RstartAge,RendAge,Rreqfields,RreqfieldsArr,RQueryType,RHouseNo,RfromSno,RtoSno);
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
									  buildVotersByLocBoothDataTable(RlocationLvl,RlId,RpublicationDateId,RvoterCardId,RvoterName,RvoterNameType,RguardianName,Rgender,RstartAge,RendAge,Rreqfields,RreqfieldsArr,RQueryType,RHouseNo,RfromSno,RtoSno)
									  alert("Updated Successfully");
									  
								    }
									else if(jsObj.task == "getCategoriesForAUserInitial")
								    { 
									  buildCategoriesListInit(myResults);
								    }
                                    else if(jsObj.task=="ckeckForVoterId")
									{	
										buildVoterToSelectedType(myResults,jsObj);
									}
                                    else if(jsObj.task == "addVoterToCadre"){
										if(myResults == "notLogged"){
										 openDialogForLoginWindow();
										}else if(myResults == "error" || myResults == "failure"){
										  alert("Unable to process your request,please try later.");
										}else{
										   alert("Voter Added To Cadre Successfully.");
										}
								   }else if(jsObj.task == "mapVoterAsInfluencingPerson"){
									alert("Voter Added as influence person.");
									$('#influencePeopleOuterDiv').dialog('close');
								   }
                                   else if(jsObj.task == "getInfluencingPeopleBySearch")
                                   {
									$('#ajaxImageDiv1').css('display','none');
                                     buildInfluencePeopleSearchResults(myResults,jsObj.voterId);
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
	var  localityPresent=false;
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
		   if(results.localityPresent){
		      localityPresent = true;
			  str+="<tr>";	
		      str+="    <td><b>LocalityName:</b></td>";		  
			  str+="    <td><select id='localityForAll'  onchange='getLocalitiesList(\"localityForAll \",\"locationdiv\",null)' >";
				        for(var l in results.localitiesList){
						   if(results.localitiesList[l].hampletPresent)
						   mandalType = "mandal";
						   else 
						    mandalType = "muncipality";
						 str+="<option value="+results.localitiesList[l].id+">"+results.localitiesList[l].value+"</option>";
					    }
			  str+="    </select></td>";
			  str+="<td><div id='locationdiv' ></td> ";
			  str+="</tr>";                       
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
           str+="<input id='votersEditSaveButtnImg' style='margin-bottom: 20px;margin-left: 360px;margin-top: 10px;' type='button' class='btn btn-success' onclick='updateAllSelectedVotersForAll("+castPresent+","+partyPresent+","+localityPresent+","+totalCategCount+");' value='Update All Voters'/><input  style='margin-left: 10px;margin-top: -8px;' type='button' class='btn btn-success' onclick='ClearAllSelectedVoters();' value='Clear All Voters'/><img id='votersEditSaveAjaxImg' style='width: 20px; padding-left: 30px; display: none;' src='images/icons/ajaxImg.gif'>";
	    $("#multipleVoterFamiliesEditDiv").html(str);		   
		}
	   
	}
	 function updateAllSelectedVotersForAll(castPresent,partyPresent,localityPresent,count){
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
			  if(localityPresent){
			  var hamletId=$("#localityForAll").val();
			  var localityId= $("#localitylocationdiv").val();
			  	//alert(mandalType);
				if(mandalType == "muncipality" ){
				 obj["localityHamletId"] = hamletId;
				 obj["hamletId"] = 0;
				 }else{
				obj["hamletId"] = hamletId;
			    obj["localityHamletId"] = localityId;
			   }
			   }
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
			localityPresent:localityPresent,
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
     
	 function updateAllSelectedVotersForIndividule(castPresent,partyPresent,localityPresent,count){
	    var votersEditInfo = new Array();
     $('.familyVoterEditId').each(function(){
	        var obj={
	    
	        };
			  obj["voterId"] = $(this).val();
			  if(castPresent)
			  obj["castId"] = $(this).closest("table").find(".castallfamily").val();
			  if(partyPresent)
			  obj["partyId"] = $(this).closest("table").find(".partyallfamily").val();
			   if(localityPresent)
			  obj["hamletId"] =  $(this).closest("table").find(".localityallfamily").val();
			 
			 var leng = $(this).closest("table").find(".localityallfamily").length;
			  if( $(this).closest("table").find(".localityallfamily").length )
			  obj["localityHamletId"] =  $(this).closest("table").find(".localityFamily").val() ;
			 else
			  obj["localityHamletId"]= 0;
			  
			  
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
					localityPresent:localityPresent,
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
				if(results.localityPresent){
				  str+="   <tr>";
				  str+="   <td><b>Locality Name:</b></td>";
				
				        str+="<td><select id='localityvalid"+localid+"' onchange='getLocalitiesList(\"localityvalid"+localid+"\",\"locationdiv"+localid+"\",\" "+j+"\")' dataj='"+j+"' datalocationdiv=\"locationdiv"+localid+"\" class='localityallfamily localityallfamily"+j+"' >";
						var selectedValue = 0;
						 for(var l in results.localitiesList){
						  if(voters[k].hamletId != results.localitiesList[l].id)								   
						 str+="<option value="+results.localitiesList[l].id+">"+results.localitiesList[l].value+"</option>";
					      else{
							  selectedValue = results.localitiesList[l].id;
						  str+="<option selected='selected' value="+results.localitiesList[l].id+">"+results.localitiesList[l].value+"</option>";
						
						 }
                      

                   if(selectedValue !=0){
					   getLocalitiesList1("localityvalid"+localid,"locationdiv"+localid,j,selectedValue);
					   selectedValue = 0;
				   }

                    // idsArray[i]=selectid;
					   }
				  str+="    </select></td>";
				  str+="    <td><a title='Apply this value to this family' href='javascript:{};' onclick='applyValueToAllVoters(\"localityvalid"+localid+"\",\"localityallfamily"+j+"\")'><i class='icon-ok'></i></a></td>";
				  str+="    <td><a title='Apply this value to all families' href='javascript:{};' onclick='applyValueToAllVoters(\"localityvalid"+localid+"\",\"localityallfamily\")'><i class='icon-ok-sign'></i></a></td>";
				  str+="<td><div id='locationdiv"+localid+"' ></td> "; 
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
		 str+="<input id='votersEditSaveButtnImg' style='margin-bottom: 20px;margin-left: 360px;margin-top: 10px;' type='button' class='btn btn-success' onclick='updateAllSelectedVotersForIndividule("+results.castPresent+","+results.partyPresent+","+results.localityPresent+","+categCount+");' value='Update All Voters'/><input  style='margin-left: 10px;margin-top: -8px;' type='button' class='btn btn-success' onclick='ClearAllSelectedVoters();' value='Clear All Voters'/><img id='votersEditSaveAjaxImg' style='width: 20px; padding-left: 30px; display: none;' src='images/icons/ajaxImg.gif'>";
		 $("#multipleVoterFamiliesEditDiv").html(str);
	   }
	}
	
	function checkForVoter(voterId,type)
{
	var jsObj = {
			voterId:voterId,
			type : type,
			task:"ckeckForVoterId"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "ckeckForVoterIdAction.action?"+rparam;
	callAjaxForCandSearch(jsObj, url);
}
function buildVoterToSelectedType(result,jsObj)
{
	if(jsObj.type == "influencingPeople")
	{
		if(result.resultCode == 1)
		{
			addInfluencingPeople(jsObj.voterId);
		}
		else 
		{
			alert('Voter is already added as a Influencing People');
		
		}
	}
	
	if(jsObj.type == "cadre")
	{
		if(result.resultCode == 1)
		{
			openRegistrationForm(jsObj.voterId);
		}
		else
		{
			alert('Voter is already added as a Cadre');
			
		}
	}
	
	if(jsObj.type == "candidate")
	{
		if(result.resultCode == 1)
		{
		addToPolitician(jsObj.voterId);
		}
		else
		{
			alert('Voter is already added as a politician');
			
		}
	}
	
}
function addInfluencingPeople(voterId)
{
	var type='edit';
	var urlStr = "influencingPeopleAction.action?windowTask="+type+"&voterId="+voterId;
	var browser2 = window.open(urlStr,"influencingPeopleAction","scrollbars=yes,height=630,width=620,left=300,top=10");
	browser2.focus();
	//buildVotersByLocBoothDataTable(RlocationLvl,RlId,RpublicationDateId,RvoterCardId,RvoterName,RvoterNameType,RguardianName,Rgender,RstartAge,RendAge,Rreqfields,RreqfieldsArr,RQueryType,RHouseNo,RfromSno,RtoSno);
}
function openRegistrationForm(voterId)
{
	var task = "update_existing";
	var urlStr = "cadreRegisterPageAction.action?voterId="+voterId+"&windowTask="+task;
	var updateBrowser = window.open(urlStr,"cadreRegistration","scrollbars=yes,left=200,top=200");	
	updateBrowser.focus();		
	//buildVotersByLocBoothDataTable(RlocationLvl,RlId,RpublicationDateId,RvoterCardId,RvoterName,RvoterNameType,RguardianName,Rgender,RstartAge,RendAge,Rreqfields,RreqfieldsArr,RQueryType,RHouseNo,RfromSno,RtoSno);	
}
function addToPolitician(voterId)
{
	var urlStr = "assigningCandidatesToVoterAction.action?voterId="+voterId;
	var browser2 = window.open(urlStr,"assigningCandidatesToVoterAction","scrollbars=yes,height=630,width=620,left=300,top=10");
	browser2.focus();
	//buildVotersByLocBoothDataTable(RlocationLvl,RlId,RpublicationDateId,RvoterCardId,RvoterName,RvoterNameType,RguardianName,Rgender,RstartAge,RendAge,Rreqfields,RreqfieldsArr,RQueryType,RHouseNo,RfromSno,RtoSno);
}
function mapSelectedCadreToVoter(cid,cName,voterId){
	 var jsObj=
		{
			 cadreId:cid,
			 voterId:voterId,
			 task:"addVoterToCadre"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "addVoterToCadreAction.action?"+rparam;	
		callAjaxForCandSearch(jsObj,url);
}	
function openCadreSearchWindow(clickedId){
  
	var cadreWindow = window.open("cadreSearchAction.action?windowTask=Search&voterId="+clickedId,"cadreSearch","scrollbars=yes,height=600,width=750,left=200,top=200");
  cadreWindow.focus();
}
function showInfluencePeopleDialog(voterId){
	$('#searchResultsDiv').html('');


	var str='';

    str+='<div>';
	 str+='<div><span>Enter Name<span class="requiredFont">*</span> :</span><span><input id="nameId" type="text" name="name"/></span><span id="nameErrMsg" class="locationErrorMsg"></span></div>';
	 str+='<div><span>Father Name :</span><span><input id="fatherNameId" type="text" name="name"/></span></div>';
     
	// str+='<h5>Select Scope</h5>';
     str+='<div>';
	  str+='<span>Select Scope</span>';
	  str+='<select id="scopeId" onChange="showLocationsDiv();">';
	        str+='<option value="0">Select</option>';
			str+='<option value="2">STATE</option>';
			str+='<option value="3">DISTRICT</option>';
			str+='<option value="4">CONSTITUENCY</option>';
			str+='<option value="5">MANDAL</option>';
			str+='<option value="6">VILLAGE</option>';
			str+='<option value="7">MUNCIPAL-CORP-GMC</option>';
			str+='<option value="8">WARD</option>';
			str+='<option value="9">BOOTH</option>';
	  str+='</select>';
     str+='</div>';

     str+='<div id="locationsDiv" style="display:none;padding:10px;margin:5px;border:1px solid #c3c3c3;width:525px;">';
	  str+='<div  id="regionstitleDiv" style="display:none;"><h5>Select region</h5></div>';

	  str+='<div id="stateSelect" style="margin:3px;width:540px;display:none;" class="locationDivClass"><span>STATE<span class="requiredFont">*</span></span><span style="margin-left:145px;"><select id="stateSelectId"><option value="1">ANDHRA PRADESH</option></select></span></div>';

	  str+='<div id="districtSelect" style="margin:3px;width:540px;display:none;" class="locationDivClass"><span>DISTRICT<span class="requiredFont">*</span></span><span style="margin-left:121px;"><select id="districtSelectId" onChange="getConstituenciesInDistrict();"><option value="0">Select</option></select></span><span id="districtErrMsg" class="locationErrorMsg" ></span></div>';

	  str+='<div id="constituencySelect" style="margin:3px;width:540px;display:none;" class="locationDivClass" ><span>CONSTITUENCY<span class="requiredFont">*</span></span><span style="margin-left:69px;"><select id="constituencySelectId" onChange="getMandalsOrMuncipalities();"><option value="0">Select</option></select></span><span id="constituencyErrMsg" class="locationErrorMsg"></span></div>';

	  str+='<div id="mandalSelect"  class="locationDivClass" style="margin:3px;width:540px;display:none;"><span>TEHSIL/MUNCIPALITY<span class="requiredFont">*</span></span><span style="margin-left:25px;"><select id="mandalSelectId" onChange="getHamletsOrWards();"><option value="0">Select</option></select></span><span id="tehsilOrMuncipalityErrMsg" class="locationErrorMsg"></span></div>';

	  str+='<div id="wardSelect"style="margin:3px;width:540px;display:none;"  class="locationDivClass" ><span>VILLAGE/WARD/DIVISION<span class="requiredFont">*</span></span><span><select id="wardSelectId"><option value="0">Select</option></select></span><span id="villageOrWardErrMsg" class="locationErrorMsg"></span></div>';

	  str+='<div id="boothSelect" class="locationDivClass" style="margin:3px;width:540px;display:none;"><span>BOOTH<span class="requiredFont">*</span></span><span style="margin-left:138px;"><select id="boothSelectId"><option value="0">Select</option></select></span><span id="boothErrMsg" class="locationErrorMsg"></span></div>';
 
	str+='</div>';
	str+='<div><a class="btn btn-primary" style="float:right;" href="javaScript:{callAjaxToSearchInfluencingPeople('+voterId+');}">Search</a></div>';
	str+='<div id="ajaxImageDiv1" style="display:none;"><img style="margin-left:244px;" src="images/icons/ajaxImg.gif"></div>';


	$('#influencePeopleInnerDiv').html(str);

	$('#influencePeopleOuterDiv').dialog({ 
	                            title:'Search Influence People',
	                            height: 'auto',
								width: 700,
								closeOnEscape: false,
								show: "blind",
								hide: "explode",
								modal: true,
	                             buttons: {
							   "Close":function() {$(this).dialog("close")}
								   }	

     });


	 getDistrictsInAState();

}

function buildInfluencePeopleSearchResults(results,voterId){
	 
    $("#searchResultsDiv").html("");

	 YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var id= oRecord.getData("influencePersonId");

		str+='<span><a href="javaScript:{mapAsInfluencePeople('+id+','+voterId+');}" class="btn btn-mini" ">Map to voter</a></span>';
	
		elLiner.innerHTML=str;
					
	};

	
     var influencePeopleColumnDefs =
		                [ 
						 {key:"firstName",label:"First Name",sortable:true},
						 {key:"lastName",label:"Last Name",sortable:true},
   		                 {key:"contactNumber",label:"Mobile No",sortable:true},
		                 {key:"Select",label:"Select", formatter:YAHOO.widget.DataTable.select}
						]; 

     var myConfigs =
		        { 
			        paginator : new YAHOO.widget.Paginator({ 
		              rowsPerPage    : 10
			         }) 
				};
			    
				
	 var myDataSource = new YAHOO.util.DataSource(results);
		 myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
		 myDataSource.responseschema = {
						 fields : ["firstName","lastName"]
					};

	 var familesDataSource = new YAHOO.widget.DataTable("searchResultsDiv", influencePeopleColumnDefs,myDataSource, myConfigs);
 
}


function mapAsInfluencePeople(id,voterId){


	var jsObj=
			{
				influencePersonId:id,
                voterId:voterId,
				task:"mapVoterAsInfluencingPerson"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "mapVoterAsInfluencingPerson.action?"+rparam;						
		callAjaxForCandSearch(jsObj,url);
}


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

function getInfluencePeopleOfAnUser(voterId){

  showInfluencePeopleDialog(voterId)
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
function callAjaxToSearchInfluencingPeople(voterId)
{

	$('#districtErrMsg ,#constituencyErrMsg , #tehsilOrMuncipalityErrMsg ,#villageOrWardErrMsg , #boothErrMsg , #searchResultsDiv , #nameErrMsg').html('');
	var isValid = true;

	var scopeSelected = $('#scopeId').val();

	if(scopeSelected == "3"){
		if($('#districtSelectId').val() == "0")
		{
			 isValid = false; 
			$('#districtErrMsg').html('District is required');
		}

	}
	else if(scopeSelected == "4"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			 isValid = false; 
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			 isValid = false; 
		}

	}
	else if(scopeSelected == "5"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			 isValid = false; 
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			 isValid = false; 
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Mandal is required');
			 isValid = false;
		}

	}
	else if(scopeSelected == "6"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			isValid = false;
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			isValid = false;
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Mandal is required');
			isValid = false;
		}
		if($('#wardSelectId').val() == "0")
		{
			$('#villageOrWardErrMsg').html('Village is required');
			isValid = false;
		}

	}
	else if(scopeSelected == "7"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			isValid = false;
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			isValid = false;
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Muncipality is required');
			 isValid = false; 
		}

	}
	else if(scopeSelected == "8"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			isValid = false;

		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			 isValid = false; 
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Muncipality is required');
			 isValid = false; 
		}
		if($('#wardSelectId').val() == "0")
		{
			$('#villageOrWardErrMsg').html('Ward is required');
			 isValid = false; 
		}
	}
	else if(scopeSelected == "9"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			 isValid = false; 
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			 isValid = false; 
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Muncipality is required');
			 isValid = false; 
		}
		if($('#wardSelectId').val() == "0")
		{
			$('#villageOrWardErrMsg').html('Ward is required');
			 isValid = false; 
		}
		if($('#boothSelectId').val() == "0")
		{
			$('#boothErrMsg').html('Booth is required');
			 isValid = false; 
		}
	}

	if($('#nameId').val() == "")
	{
		$('#nameErrMsg').html('Name is required');
		isValid = false; 
	}


	if(isValid == false)	
		isValid = true;
	else	
     callAjaxToSearchInfluencingPeople1(voterId);

}

function callAjaxToSearchInfluencingPeople1(voterId)
{

document.getElementById('ajaxImageDiv1').style.display = 'block';



	var stateVal = 0;
	var districtVal = 0;
	var constituencyVal = 0;
	var mandalVal = 0;
	var muncipalityVal = 0;
	var villageVal = 0;
	var wardVal = 0;
	var boothVal = 0;
	var name="";
	var fatherOrSpouceName = "";

	name=$('#nameId').val();
	fatherOrSpouceName = $('#fatherNameId').val();

	var scopeVal = $('#scopeId').val();

	if(scopeVal == "2")
		stateVal = $('#stateSelectId').val();
	else if (scopeVal == "3")
	{
	     districtVal = $('#districtSelectId').val();
	}
	else if (scopeVal == "4")
	{
	     constituencyVal = $('#constituencySelectId').val();
	}
	else if (scopeVal == "5" || scopeVal == "7")
	{	
		 var mandalTemp = $('#mandalSelectId').val();

		 if(mandalTemp.substring(0,1) == "2")
			mandalVal =  mandalTemp.substring(1);
		 else if(mandalTemp.substring(0,1) == "1")
			muncipalityVal =  mandalTemp.substring(1);
    
	}
	else if (scopeVal == "6" || scopeVal == "8")
	{

		 var villageTemp = $('#wardSelectId').val();

		 if(villageTemp.substring(0,1) == "2")
			villageVal =  villageTemp.substring(1);
		 else if(villageTemp.substring(0,1) == "1")
			wardVal =  villageTemp.substring(1);
	}
	else if (scopeVal == "9")
	{
         boothVal = $('#boothSelectId').val();
	}


	var jsObj=
			{   scope:scopeVal,
				name:name,
				state:stateVal,
 			    district: districtVal,
                constituency:constituencyVal,
                mandal:mandalVal,
				muncipality:muncipalityVal,
				hamlet:villageVal,
				ward:wardVal,
				booth:boothVal,	
				fatherOrSpouceName:fatherOrSpouceName,
				voterId:voterId,
				task:"getInfluencingPeopleBySearch"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getInfluencingPeopleBySearch.action?"+rparam;						
		callAjaxForCandSearch(jsObj,url);
		

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
function refreshingchildWindowWindow()
{
	buildVotersByLocBoothDataTable(RlocationLvl,RlId,RpublicationDateId,RvoterCardId,RvoterName,RvoterNameType,RguardianName,Rgender,RstartAge,RendAge,Rreqfields,RreqfieldsArr,RQueryType,RHouseNo,RfromSno,RtoSno);
}
</script>
</head>
<body>
  <div style="width:960px;margin-left:auto;margin-right:auto;">
      <div class="titleHeading">VOTERS SEARCH</div>
	  <fieldset id="mainFieldset">
      <div id="AlertMsg" style="font-family: verdana;font-size: 13px;color:red;"></div>
	  <div id="errorMsgAlert" style="font-family: verdana;font-size:14px;color:red;margin-left:100px;"></div>
     <!--  <div id="errorMsgAlert" style="font-family: verdana;font-size:14px;color:red;margin-left:100px;">
	   updations are possible only through panchayat and polling station
	   </div> --> 
<!--  <div id="demo1" class="yui-navset"> 
		  <ul class="yui-nav"> 
	        <li class="selected"><a href="#locationUpdationDiv"><em>LocationUpdation</em></a></li> 
	        <li ><a href="#reportLevelDiv"><em>VoterSearch</em></a></li> 
	        
	    </ul>   
      <div class="yui-content"> 		
	   <div id="locationUpdationDiv" class="selectDiv">Select Updation Level<font class="requiredFont">*</font>
	   <select id="reportLevel1" class="selectWidth" style="margin-left: 8px;width:165px;" name="constituencyList" onchange="showReportLevel(this.options[this.selectedIndex].value);">
		<option value=0>select</option>
		<option value=5>Ward</option>
		<option value=3>Panchayat</option>
		<option value=4>PollingStation</option>
		</select>
      </div>
	   </div>
	    </div> -->
	  <div id="reportLevelDiv" class="selectDiv">Select Level<font class="requiredFont">*</font><select id="reportLevel" class="selectWidth" style="margin-left:76px;width:165px;" name="constituencyList" onchange="showReportLevel(this.options[this.selectedIndex].value);">
		<option value=1>Constituency</option>
		<option value=2>Mandal</option>
		<option value=5>Ward</option>
		<option value=3>Panchayat</option>
	    <option value=6>Hamlets</option>
        <option value=4>PollingStation</option>
		</select>
      </div>
	 
   
	
	
	  <div id="ConstituencyDiv" class="selectDiv">
	     Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;width:165px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="getMandalList(\'mandalField\');getPublicationDate();"/> &nbsp;&nbsp;	
	     Select Publication Date<font class="requiredFont">*</font> <select id="publicationDateList" class="selectWidth" style="width:180px;" name="publicationDateList" >
		</select>  <span style='display:none;float: right;' id='ajaxLoad'><img src='./images/icons/search.gif' /></span>		
	  </div>
	  
	  <div id="mandalDiv" class="selectDiv" style="display:none;">
	     Select Mandal<font class="requiredFont">*</font>
		 <select id="mandalField" class="selectWidth" name="state" onchange="getPanchayatList('panchayat','panchayatField');getPanchayatList('pollingstationByPublication','pollingStationField');getPanchayatList('ward','wardField')" style="margin-left:60px;width:165px;"></select>
	  </div>
	   <div id="wardDiv" class="selectDiv" style="display:none;">
	    Select Ward<font class="requiredFont">*</font> <select id="wardField" class="selectWidth" name="state" onchange="getLocalitiesList('ward','wardField');getLocalitiesList('pollingstationByPublication','pollingStationField');" style="margin-left:70px;width:165px;"></select> 
	  </div>
		
	  <div id="panchayatDiv" class="selectDiv" style="display:none;">
	    Select Panchayat<font class="requiredFont">*</font> 	
	    <select id="panchayatField" class="selectWidth" name="state" onchange="getHamletsList('hamlet','hamletField');" style="margin-left:39px;width:165px;"></select>
	  </div>
	   <div id="hamletDiv" class="selectDiv" style="display:none;">
	    Select Hamlet<font class="requiredFont">*</font> <select id="hamletField" class="selectWidth" name="state" onchange="getLocalitiesList('ward','wardField');getLocalitiesList('pollingstationByPublication','pollingStationField');" style="margin-left:60px;width:165px;"></select> 
	 </div>
	
	  <div id="pollingStationDiv" class="selectDiv" style="display:none;">
	    Select PollingStation<font class="requiredFont">*</font><select id="pollingStationField" class="selectWidth" name="state"  style="margin-left:20px;width:165px;"></select>
	  </div>
	  <div id="localityDiv" class="selectDiv" style="display:none;">
	   Select Locality<font class="requiredFont">*</font> <select id="localityField" class="selectWidth" name="state" onchange="getLocalitiesList('ward','wardField');getLocalitiesList('pollingstationByPublication','pollingStationField');" style="margin-left:60px;width:165px;"></select> 
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
	   <input type="radio" checked="true"  value="all" name="genderChkBox" /><b style="font-size: 12px; font-family: verdana;"> All </b>
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
      <div class="selectDiv"> 
	    <div class="row "style="padding: 10px 0px 0px 162px; margin-top: -22px; ">
		   <div class="span">
		     <label class="radio">
	          <input type="radio" checked="true" id="andCondChk" value="allCriteria" name="queryTypeChkBox" /><b style="font-size: 12px; font-family: verdana;">All Of These Criteria</b>
	         </label>
		   </div>
		   <div class="span">
		     <label class="radio">
	          <input type="radio" id="maleSelect" name="queryTypeChkBox"  value="oneCriteria"  /><b style="font-size: 12px; font-family: verdana;">Any One Of These Criteria </b>
	         </label>
		   </div>
	    </div>
	  </div>
	  <div class="selectDiv">
	   <table><tr>
	     <td style="width:132px;"><span>Check Required Fields To Show</span></td>
	     <td><span id="requiredFieldsToCheck"></span></td>
	   </tr></table>
	  </div>
	  <div class="selectDiv">
	   <table><tr>
	     <td style="width:165px;"><span>Total Voters To Show For Each Page</span></td>
	     <td><select id="votersPageLimit"><option value="100">100</option><option value="200">200</option><option value="500">500</option><option value="1000">1000</option></select></td>
	     <td><div id="localityUpdation"></td>
	  </tr>
	   
	   </table>
	  </div>
	  <input style="margin-left:265px;margin-bottom:10px;" onclick="getVotersInfo();" class="btn btn-success" type="button" value="Search"/>
	   </fieldset>
	   <div id="topButtons"></div>
	   <div><span id="topCount"></span><span id="topSelCount"></span></div>
	   <div  id="votersBySearchTabContentDiv_body"   class="yui-skin-sam yui-dt-sortable"></div><div id="errorMessageDiv" style="display:none;font-weight:bold;color:red" align="center"></div>
	   <div><span id="bottomCount"></span><span id="bottomSelCount"></span></div>
	   <div id="categoriesDiv"></div>
	   <div id="bottomButtons"></div>
	   <div id="impFamDtlsOuterPopUp" style="display:none;">
		   <div id="impFamDtlsTitle"></div>
		   <div id="impFamDtls"  class="yui-skin-sam yui-dt-sortable" style="border:1px solid black;width: -moz-fit-content;"></div>
		</div>
	  <div id="multipleVoterFamiliesEditDiv"></div>
	 <div id="influencePeopleOuterDiv" style="display:none;">
         <div id="influencePeopleInnerDiv"></div>
         <div id="searchResultsDiv"  class="yui-skin-sam yui-dt-sortable"></div>
     </div>

<script type="text/javascript">
  getCategoriesForAUserInital();
 
	    var tabView = new YAHOO.widget.TabView('demo1'); 
	
</script>
</div>
</body>
</html>