<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voters analysis</title>
<style>
#voterModReportMainDiv{margin-left:auto;margin-right:auto;float:none;width:960px;margin-top: 30px; margin-bottom: 30px;}
#voterInfoTab, #voterAgeInfoTab,#voterGenderInfoTab{border: 1px solid #D3D3D3;
    border-collapse: collapse;
    padding: 10px;width:50%}

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
    font-size: 11px; 
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

</style>
<script type="text/javascript" src="js/voterModificationReport.js"></script>
 <script type="text/javascript">
	var constituencyId = "${constituencyId}";
	var fromPublicationDateId = "${fromPublicationDateId}";
	var toPublicationDateId = "${toPublicationDateId}";
	var locationType = "${locationType}";
	var locationValue = "${locationValue}";
 </script>
 
</head>
<body>
<div id="votersCountVaryDiv" class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;">
  <h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;">Voters Count Vary between Publication Dates</h4>
	<div style="margin-top:10px;" class='breadcrumb'>
		Previous Publication Date<font class="requiredFont">*</font> <select id="prevpublicationDateList" class="selectWidth" style="width:172px;height:25px;" name="prevpublicationDateList" >
		</select>
		
		Present Publication Date<font class="requiredFont">*</font> <select id="prespublicationDateList" class="selectWidth" style="width:172px;height:25px;" name="prespublicationDateList" onChange='buildVoterDiff()'>
		</select>
		<span class="btn" onClick="getModifiedVotersCountBetweenPublications(previousPublication,presentPublicationId,locationValue,locationType)">View</span>
	</div>
	<div id='VoterDiff' style="margin-left:auto;margin-right:auto;width:400px;" class="breadcrumb">
		<span>Added Voters </span>-<span class='btn'> No Data</span>
		<span>Deleted Voters </span>-<span class='btn'> No Data</span>
	</div>
  </div>
<div id="voterModReportMainDiv">
  <div id="voterModReportInnerDiv">
		<div id="voterInfoDiv">
			<span id="voterInfoAjaxImg" style="display:none;float: right;clear:both;">
			   <img src="images/icons/search.gif" />
			</span>
		</div>
		<div id="voterAgeInfoDiv">
			<span id="voterAgeInfoAjaxImg" style="display:none;float: right;clear:both;">
			   <img src="images/icons/search.gif" />
			</span>
		</div>
		
		<div id="voterGenderInfoMainDiv">
			<img src="images/icons/search.gif" style="display:none;float: right;clear:both;" id="voterGenderInfoDivAjaxImg"/>
			<div id="voterGenderInfoDiv"></div>
		</div>

		<div id="genderWiseVoterModifiMainDiv">
			<img src="images/icons/search.gif" style="display:none;float: right;clear:both;" id="genderWiseVoterModifiAjaxImg"/>
			<div id="genderWiseVoterModifiDiv"></div>
		</div>

  </div>
</div>

<script type="text/javascript">

//Created by sasi
getPublicationDate();
	
getVoterInfo();
getAddedDeletedVoterInfoInALocation();
getGenderWiseVoterModificationsBetweenPublications();
getGenderWiseVoterModificationsForEachPublication();

</script>
</body>
</html>