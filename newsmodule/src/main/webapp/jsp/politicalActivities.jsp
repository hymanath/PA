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
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
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
   <div class="span3" style="margin-left:240px;"><label style="float: left;"><strong>Select District<span class="requiredFont">*</span></strong></label><s:select name="districtSelReport" id="districtSel" list="districts" theme="simple" listKey="id" listValue="name"/></div>
  </div> 
  <div class="span12">
   <div class="span3" style="margin-left:240px;"><input class="btn btn-success" id="getNewsButton" style="margin-top:10px;" onclick="getTotalNewsToChangeKeywords();" type="button" value="Submit"></input></div>
  </div>   
  <div class="span12">
   <div class="span12 yui-skin-sam yui-dt-sortable" id="newsTable"></div>
  </div>
  <div class="span12 showHide" style="display:none;">
   <div id="errorMsgDiv1"></div>
   <div class="span3" style="margin-left:240px;"><strong>Select KeyWords To Add</strong><select id="keywordsSelected" multiple="multiple"><option value="Cadre">Cadre</option><option value="MLA/Incharge">MLA/Incharge</option><option value="MP/Incharge">MP/Incharge</option></select><input class="btn btn-success" id="updateKeyId" style="margin-top:10px;" onclick="updateKeywords();" type="button" value="Update Keywords"></input></div>
  </div>   
 </div> 
 <form id="updateKeywordsForm" method="post" action="updateKeywordsAction.action" name="updateKeywordsForm" style="border:0px">
   <div><input type="hidden" name="task" id="submitFormValues" /></div>
 </form>
 <script type="text/javascript"> 
 var checkedFileIdsArray = new Array();
 $('#districtSel').multiselect({	
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
  $(".newsCheckId").live("click",function(){
        var fileId = $(this).attr('value');
        if($(this).is(':checked') && checkedFileIdsArray.indexOf(fileId) == -1){
           checkedFileIdsArray.push(fileId);
        }else{
          if(checkedFileIdsArray.indexOf(fileId) != -1)
            checkedFileIdsArray = jQuery.removeFromArray(fileId, checkedFileIdsArray);
        }
  });
  
  
  
  function getCandidatePartyBenefitsDiv()
{
  $("#candidatePartyBenefitsDiv").dialog({ stack: false,
							    height: 530,
								width: 900,
								position:[130,130],								
								modal: true,
								title:'News Details',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#candidatePartyBenefitsDiv").dialog();
}
var selfromDate;
var seltoDate;
var seldistIds;
function getTotalNewsToChangeKeywords(from,to,distIds)
{
  $("#errorMsgDiv").html("");
 
  var fromDate = "";
  var toDate = "";
  if(from != undefined){
    fromDate = from;
  }else{
   fromDate = $("#newsFromDateId").val();
   selfromDate = fromDate;
  }
  if(to != undefined){
    toDate = to;
  }else{
    toDate = $("#newsToDateId").val();
	seltoDate = toDate;
  }
  
  var districts = "";
  if(distIds != undefined){
    districts = distIds
  }else{
     var selectedDistrict = $("#districtSel").multiselect("getChecked").map(function(){
			return this.value;    
		}).get();
	 for(var i in selectedDistrict)
	 {
		districts = districts+""+selectedDistrict[i]+",";
	 }
	 if(districts!=0 && districts.length > 0){
	   districts = districts.substring(0,districts.length - 1);
	 }
	 seldistIds = districts;
	 if(seldistIds.length == 0){
	    $("#errorMsgDiv").html("Please Select District");
		return;
	 }
  }
   $(".showHide").show();
   $("#getNewsButton").attr('disabled','disabled');
 YAHOO.widget.DataTable.checkBox = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var fileId = oRecord.getData("id");
		var partyId = oRecord.getData("partyId");
		if(checkedFileIdsArray.indexOf(""+fileId+","+partyId+"") != -1)
		 str +="<input type='checkbox' checked='checked' class='newsCheckId'  value='"+fileId+","+partyId+"' />";
		else
		 str +="<input type='checkbox' class='newsCheckId'  value='"+fileId+","+partyId+"' />";
		elLiner.innerHTML=str;
					
	};
	
	
	YAHOO.widget.DataTable.title = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var fileId = oRecord.getData("id");
				var title = oRecord.getData("title");
		var isEenaduTelugu = oRecord.getData("tileFont");
		if(isEenaduTelugu == "true" || isEenaduTelugu == true)
		  str +="<span class='enadu'><a href='javascript:{}' onclick='getNewsDetailsByContentId("+fileId+")' class='newsLinkCls'>"+title+"</a></span>";
		  else
		  str +="<span><a href='javascript:{}' onclick='getNewsDetailsByContentId("+fileId+")' class='newsLinkCls'>"+title+"</a></span>";
		  
		elLiner.innerHTML=str;
					
	};
	
	YAHOO.widget.DataTable.description = function(elLiner, oRecord, oColumn, oData)
	{
	    var str='';
		var name = oData;
		var descEenadu = oRecord.getData("descFont");
		var description = oRecord.getData("description");

		if(descEenadu)
		  str +="<span class='enadu'>"+description+"</span>";
		  else
		  str +="<span >"+description+"</span>";
		  
		elLiner.innerHTML=str;
					
	};
	 

	   var newsColumns = [
				   {key:"SELECT NEWS",label:"SELECT NEWS",formatter:YAHOO.widget.DataTable.checkBox},
				   {key:"date", label:"DATE"},
				   {key:"name", label:"PARTY"},
				   {key:"title", label:"TITLE",formatter:YAHOO.widget.DataTable.title},
				   {key:"description", label:"DESCRIPTION",formatter:YAHOO.widget.DataTable.description},				   
				   {key:"keywords", label:"KEYWORDS"},
				   {key:"location", label:"LOCATION"}
		];
  
  var newsDataSource = new YAHOO.util.DataSource("getNewsToUpdateKeywordsAction.action?fromDate="+fromDate+"&toDate="+toDate+"&districts="+districts+"&categories="+3991+"&");
  newsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
  newsDataSource.responseSchema = {
  resultsList: "activitiesList",
   fields: [
             {key:"id", parser:"number"},
			        "name","location","title", "date","description","tileFont", "descFont","keywords","partyId"],
			        

    metaFields: {
    totalRecords: "count" // Access to value in the server response
     },
  };
  
  
  var myConfigs = {
initialRequest: "sort=title&dir=asc&startIndex=0&results=30", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"title", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 30 
			    })  // Enables pagination
};

var newsDataTable = new YAHOO.widget.DataTable("newsTable",
newsColumns, newsDataSource, myConfigs);

newsDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
oPayload.totalRecords = oResponse.meta.totalRecords;
 $("#getNewsButton").removeAttr('disabled');
return oPayload;
  
}
}

function updateKeywords(){
    $("#errorMsgDiv1").html("");
    var selectedValues = $('#keywordsSelected').val();
	var keywords ="";
	var newsArray = new Array();
	var errorStr ="";
	var errorStatus = false;
	for(var i in selectedValues){
	  keywords = keywords+""+selectedValues[i]+",";
	}
	if(keywords.length > 0){
	  keywords = keywords.substring(0, keywords.length - 1);
	}
	for(var j in checkedFileIdsArray){
	  var ids = checkedFileIdsArray[j].split(",");
	  var obj={
				 partyId:ids[1],
			     fileId:ids[0]
			  } 
	  newsArray.push(obj);
	}
	if(keywords.length == 0){
	    errorStr+="Please Select Keywords</br>";
		errorStatus = true;
	}
	if(newsArray.length == 0){
	    errorStr+="Please Select News</br>";
		errorStatus = true;
	}
	if(errorStatus){
	  $("#errorMsgDiv1").html(errorStr);
	  return;
	}
	$("#updateKeyId").attr('disabled','disabled');
	var jsObj = {
			selectedNews:newsArray,
			keywords:keywords
	};
	$("#submitFormValues").val(YAHOO.lang.JSON.stringify(jsObj));
	var uploadHandlernew1 = {
		   success : function( o ) { 
			        var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
	                $("#updateKeyId").removeAttr('disabled');
					showUpdateStatus(uploadResult);
				}									
	   };	
	  YAHOO.util.Connect.setForm('updateKeywordsForm',false);
	  YAHOO.util.Connect.asyncRequest('POST','updateKeywordsAction.action',uploadHandlernew1);
}

function showUpdateStatus(status){
 if(status.indexOf("Success") > -1){
    checkedFileIdsArray = new Array();
	getTotalNewsToChangeKeywords(selfromDate,seltoDate,seldistIds);
   alert("KeyWords Updated Successfully.");
 }else if(status.indexOf("error") > -1){
    alert("Exception Occured Please Try Again Later.");
 }
}
 </script>
</body>
</html>