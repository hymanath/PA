<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voter Modification Report</title>

<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/blockui.js"></script>

<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<style>
.errorClass
{
	color:red;
}
#voterModReportMainDiv{margin-left:auto;margin-right:auto;float:none;width:960px;margin-top: 30px; margin-bottom: 30px;}
#voterInfoTab, #voterAgeInfoTab,#voterGenderInfoTab{margin:5px;}

	#voterInfoTab th,#voterAgeInfoTab th,#voterGenderInfoTab th {
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}

#voterInfoTab td ,#voterAgeInfoTab td,#voterGenderInfoTab td{
    color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;
}
.voterinfoHeading h2{ color: #4682B4;
    font-family: arial;
    font-size: 15px;}
.voterInfoTable{border-collapse: collapse;
    border-color: #666666;
    border-width: 1px;
    color: #333333;
    font-family: arial,sans-serif;
    font-size: 13px; 
    min-width: 97%;
    text-align: center;}

    .voterInfoTable th {
    background-color: #DBEBFF;
    border-color: #666666;
    border-style: solid;
    border-width: 1px;
    padding: 8px;
}

.voterInfoTable td {
    background-color: #FFFFFF;
    border-color: #666666;
    border-style: solid;
    border-width: 1px;
    padding: 8px;
}
.requiredFont{color:red}
#headingSpanCls{background: none repeat scroll 0 0 #06ABEA;
    border-radius: 5px 5px 5px 5px;
    color: #FFFFFF;
    font-size: 17px;
    font-weight: bold;
    padding: 5px 10px;
    text-align: center;text-transform: capitalize;}
#voterDetails.dataTable tr.odd {background-color: #FFF !important;}
#voterDetails.dataTable tr.even td.sorting_1 {background:#DBEBFF !important;}
#voterDetails.dataTable tr.odd td.sorting_1{background:#FFF !important;}
#voterDetails.dataTable td {padding: 7px 10px;}
#voterDetails{border:1px solid #000;}
#voterDetails.dataTable thead th {background: none repeat scroll 0 0 #DBEBFF;padding: 10px 18px 10px 10px;}
#voterDetails.dataTable tr.even{background:#DBEBFF !important;}
#voterDetails_info , #voterDetails_paginate { margin-bottom: 14px;margin-top: 10px;}
#subLevelVotersTable_wrapper{padding-top: 15px;}
#genderWiseVoterModifiMainDiv{padding-bottom: 15px;}
#subLevelVotersTable_info,#subLevelVotersTable_paginate{margin-top: 10px;margin-bottom: 10px;}
.voterInfoLinksCLS:hover{text-decoration:none;}

#voterimpFamDtls table{ border: 1px solid #D3D3D3;
    border-collapse: collapse;
    margin-left: auto;
    margin-right: auto;
    padding: 10px;
    width: 100%;margin-top:10px;}
#voterimpFamDtls table tr:nth-child(even){background:#EdF5FF;}

#voterimpFamDtls table th a{color:#333333;text-align:center;}
#voterimpFamDtls table th{background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;}
	#voterimpFamDtls table td{color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;}

	.ui-widget-header {
    background: none repeat scroll 0 0 #4285F4;
    color: #FFFFFF;
}
#subLevelDiv{width:98%;}
#helpbutt{margin-left: 6px;}
</style>
<link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<script type="text/javascript" src="js/voterModificationReport/voterModificationReport.js"></script>


 <script type="text/javascript">

	var constituencyId = "${constituencyId}";
	var fromPublicationDateId = "${fromPublicationDateId}";
	var toPublicationDateId = "${toPublicationDateId}";
	var locationType = "${locationType}";
	var locationValue = "${locationValue}";
	var localElectionBodyId = "${localElectionBodyId}";
	var locationName = '${locationName}';
	var fromPublicationName = '${fromPublicationName}';
	var toPublicationName = '${toPublicationName}';
	var locationTypeVar = '${locationTypeVariable}';
 </script>
 
</head>
<body>

<!-- <div id="votersCountVaryDiv" class="widget blue whitegloss" style="display:block;width: 92%;color:#000;position:relative;margin-right:auto;margin-left:auto;">
  <span id="voterAgeInfoAjaxImg" style="display:none;float: right;clear:both;">
	   <img src="images/icons/search.gif" />
  </span>
  <h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;">Newly Added / Deleted Voters Info Report For ${locationName} ${locationType} Between ${fromPublicationName} to ${toPublicationName}</h4>

	<div style="margin-top:10px;" class='breadcrumb'>
	<p>Select Publications to View to Voters Modification Info</p>
		From Publication <font class="requiredFont">*</font> <select id="prevpublicationDateList" class="selectWidth" style="width:172px;height:25px;" name="prevpublicationDateList" >
		</select>
		
		To Publication <font class="requiredFont">*</font> <select id="prespublicationDateList" class="selectWidth" style="width:172px;height:25px;" name="prespublicationDateList" onChange='buildVoterDiff()'>
		</select>
		<span class="btn" onClick="getModifiedVotersCountBetweenPublications(previousPublication,presentPublicationId,locationValue,locationType)">View</span>
	</div>-->

	<!-- <div id='VoterDiff' style="margin-left:auto;margin-right:auto;width:400px;" class="breadcrumb">
		<span>Added Voters </span>-<span class='btn'> No Data</span>
		<span>Deleted Voters </span>-<span class='btn'> No Data</span>
	</div> -->
  <!-- </div> -->
	<div align="center">
   <div class="btn btn-info" style="text-align: center; margin-top: 30px;width:938px;" >
	<span style="color:#3F3F3F;"><h3>Added / Deleted Voters Info Report For ${locationName}&nbsp;<span style="text-transform: capitalize;">${locationTypeVariable}</span></h3></span>
	<span><h5 style="font-size: 16px;padding:10px;"> Between <span style="border-bottom:1px solid;">  ${fromPublicationName}</span> To <span style="border-bottom:1px solid"> ${toPublicationName}</span></h5></span>
  </div></div> 
<div id="voterModReportMainDiv">

  <div id="voterModReportInnerDiv">
		<div id="voterInfoDiv" class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;margin-top: 0px;">
			<span id="voterInfoAjaxImg" style="display:none;float: right;clear:both;">
			   <img src="images/icons/search.gif" />
			</span>
		</div>
				
		<div id="voterGenderInfoMainDiv" class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;">
			<img src="images/icons/search.gif" style="display:none;float: right;clear:both;" id="voterGenderInfoDivAjaxImg"/>
			<div id="voterGenderInfoDiv"></div>
		</div>

		<div id="genderWiseVoterModifiMainDiv" class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;">
			<img src="images/icons/search.gif" style="display:none;float: right;clear:both;" id="genderWiseVoterModifiAjaxImg"/>
			<div id="genderWiseVoterModifiDiv"></div>
		</div>
		
		<div id="voterAgeInfoDiv" class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;">
			<span id="voterAgeInfoAjaxImg" style="display:none;float: right;clear:both;">
			   <img src="images/icons/search.gif" />
			</span>
		</div>


		<div id="subLevelsMainDiv" class="widget blue whitegloss" style="margin-top: 20px; display: inline-block; position: relative; color: #000; width: 96%;">
		   <div id="subLevelDiv" style="margin:5px 0px 30px 0px;"></div>
		   <div id="subLevelAjaxImageDiv" style="display:none;margin-left:400px;"><img src="images/icons/goldAjaxLoad.gif"/></div>
		</div>

		
        <div id="mainDiv" class="widget blue whitegloss" style="margin-top:20px;display:inline-block;width: 96%;color:#000;position:relative;">

	
	  <h4> Newly added / deleted voters info</h4>	   
	<span style="float:right;"><img id="popupAjaxImage" src="./images/icons/search.gif" alt="Processing Image" style="display:none;"/></span>

	<!--	<div class="form-horizontal" style="margin-left:238px;">



		<div class="control-group" style="margin-top:19px;">
			<label style="text-align:left;" class="control-label">FROM PUBLICATION DATE:</label>
			  <div class="controls">
			      <select id="fromPublicationDateId">
				</select>
              </div>
	     </div>

		<div class="control-group" style="margin-top:19px;">
			<label style="text-align:left;" class="control-label">TO PUBLICATION DATE:</label>
			  <div class="controls">
			     <select id="toPublicationDateId">	
				</select><span class="errorClass" id="topubErrMsg"></span>
              </div>
	     </div>

     <div class="control-group" style="margin-top:19px;">
	    <label style="text-align:left;" class="control-label"> SCOPE:</label>
			  <div class="controls">
			      <select  id="scopeSelectId">
					<option value="0">SELECT SCOPE</option>
					<option value="1">MANDAL/MUNCIPALITY</option>
					<option value="2">PANCHAYAT/WARD</option>
					<option value="3">BOOTH</option>
				  </select>
              </div>
	</div>



	<div class="control-group" id="mandalDiv" style="display:none;"> 
		 
		<label style="text-align:left;" class="control-label"> MANDAL/MUNCIPALITY:</label>
		     <div class="controls">
		        <select id="mandalSelectId">
				 <option value="0">SELECT</option>				              
			    </select>
             </div>
	</div>
	<div class="control-group" id="panchayatDiv" style="display:none;">
	   <label style="text-align:left;" class="control-label"> PANCHAYAT/WARD:</label>
		     <div class="controls">
		        <select id="panchayatSelectId">
				 <option value="0">SELECT</option>				             
				</select>
             </div>
	</div>
	<div class="control-group" id="boothDiv" style="display:none;">		 
	    <label style="text-align:left;" class="control-label">BOOTH:</label>
		      <div class="controls">
		         <select id="boothSelectId">
				   <option value="0">SELECT</option>				                 
				 </select>
              </div>
	</div>

	

	<div class="control-group" style="margin-top:19px;">
	 <label style="text-align:left;" class="control-label">SELECT STATUS:</label>
		<label style="margin:5px;float:left;">
           <input style="margin:0px;" type="radio" name="status"  value="Added">&nbsp;&nbsp;Added
        </label>
        <label style="margin:5px;float:left;">
           <input style="margin:0px;" type="radio" name="status"  value="Deleted">&nbsp;&nbsp;Deleted
        </label>
		 <label style="margin:5px;float:left;">
           <input style="margin:0px;" type="radio" name="status"  checked value="all">&nbsp;&nbsp;All
        </label>

	</div>
	

	<span><a class="btn btn-primary" href="javaScript:{getAllVotersModificationDetailsBetweenPublications('search','');}" style="margin-left:180px;margin-bottom:10px;">Search</a></span>

   </div> -->

   <!-- <div id="voterDetailsAjaxImageDiv" style="display:none;margin-left:400px;"><img src="images/icons/goldAjaxLoad.gif"/></div>
<div id="subLevelDiv" style="margin:30px 0px 30px 0px;"></div> -->


<span id="allvotersDetails" style="margin-left:400px;display:none;">
			<img src="images/icons/goldAjaxLoad.gif"/>
			</span>
	 <div id="allVoterDetailsForALocation">
	 </div>



  </div>
</div>
<div id="voterimpFamDtlsOuterPopUp">
 <div id="voterimpFamDtls" class="yui-skin-sam yui-dt-sortable"></div>
 </div>
 <div id="GenderOrAddedVotersPopup"><div id="GenderOrAddedVotersTable"><div></div>
 <div id="help_window"></div>
<script type="text/javascript">

getPublicationDate();
getVoterInfo();
getAddedDeletedVoterInfoInALocation();
getGenderWiseVoterModificationsBetweenPublications();
getGenderWiseVoterModificationsForEachPublication();
getAllVotersModificationDetailsBetweenPublications('default',"onload");
callAjaxForSubLevelInformation();

$("document").ready(function(){
	$("#boothWiseVoterModificationInfo").live("click",function(){

		var urlStr = "boothWiseModifiedVoterInfoAction.action?constituencyId="+constituencyId+"&fromPublicationDateId="+fromPublicationDateId+"&toPublicationDateId="+toPublicationDateId+"&locationType="+locationType+"&locationValue="+locationValue+"";
		window.open(urlStr,'_blank');
	    window.focus();
		
	});

	$("#boothWiseCompleteInfo").live("click",function(){

		var urlStr = "boothWiseModificationsCompleteInfo.action?constituencyId="+constituencyId+"&fromPublicationDateId="+fromPublicationDateId+"&toPublicationDateId="+toPublicationDateId+"&locationType="+locationType+"&locationValue="+locationValue+"";
		window.open(urlStr,'_blank');
	    window.focus();
		
	});
	
});

function genereatePdf()
{
	var constituencyId = ${constituencyId};
	var publicationId  = ${toPublicationDateId};
	createPdfs(constituencyId,publicationId);
}
function createPdfs(constituencyId,toPublicationDateId)
{
	$.blockUI({ message: '<h6><img src="images/icons/ajaxImg.gif"/>Please wait.....</h6>' });
	var jObj=
	{
	constituencyId : constituencyId,
	locationType   : "constituency",
	locationValue  : constituencyId,
	publicationId  : toPublicationDateId,
	type           : "constituency",
	task           : "createPdfs"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "createPdfAction.action?"+rparam;

	callAjax(jObj,url);
}
function buildPdf(result)
{
	var str = '';
	var url = result.url;
	//url.replace(new RegExp(/\\/g),"/");
	str +='<input type="button" style="float: right; margin-top: -20px; margin-right: 10px;" id="pdfGeneraion" href="javascript:{}" onClick = "genereatePdf();" value="Generate Pdf" class="btn btn-info">'
	str +='<a  id="viewPdfId" href="'+url+'" style="float: right; height: 16px; font-family: tahoma; font-size: small; font-weight: 100; margin-top: -20px; margin-right: 10px;"	target="_blank" id="pdfView"   class="btn btn-info">View Pdf</a>';
	$('#pdfDiv').html(str);
}

</script>
</body>
</html>