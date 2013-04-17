<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booth Wise Modifications Complete Info</title>

<script type="text/javascript" src="js/BoothWiseModificationsCompleteInfo/boothWiseModificationsCompleteInfo.js"></script>
<script type="text/javascript" src="js/blockui.js"></script>

<link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 

<script type="text/javascript">
	
	var constituencyId = "${constituencyId}";
	var fromPublicationDateId = "${fromPublicationDateId}";
	var toPublicationDateId = "${toPublicationDateId}";
	var locationType = "${locationType}";
	var locationValue = "${locationValue}";
	//var localElectionBodyId = "${localElectionBodyId}";
	var locationName = '${locationName}';
	//var fromPublicationName = '${fromPublicationName}';
	//var toPublicationName = '${toPublicationName}';
	var locationTypeVar = '${locationTypeVariable}';


</script>
<style type="text/css">
#boothWiseCompleteInfoMainDiv{
	float: none;
    margin-left: auto;
    margin-right: auto;
	width: 990px;
}

#movedTable th,#relocatedTable th {
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}
#movedTable td,#relocatedTable td {
    color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;
}
#boothWiseInfoDiv{margin-top:15px;}
#relocatedTable,#movedTable{margin-top:10px;}
.fontStyle{font-weight:bold;font-family: arial;font-size: 14px;margin-left : 40px;}
#boothWiseVoterInfoBasicDiv{ margin: 13px 0 6px -20px;}
.widget h4, h2 {margin: 0;}
.widget { margin: 5px 0 10px;}
.voterInfoDiv{display: inline-block; color: rgb(0, 0, 0); position: relative; width: 96%;background:#FFF;}

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
p {line-height: 15px;}
</style>
</head>
<body>
 <div id="boothWiseCompleteInfoMainDiv">
 
 <div id="voterimpFamDtlsOuterPopUp">
 <div id="voterimpFamDtls" class="yui-skin-sam yui-dt-sortable"></div>
 </div>
	<div align="center">
		<div style="text-align: center; margin-top: 30px;width:938px;" class="btn btn-info">
			<span style="color:#3F3F3F;"><h3>Booth Wise Voters Info Report For ${locationName} ${locationTypeVariable}</h3></span>
		</div>
    </div>

	<span id="ajaxImg" style="display:none;float: right;clear:both; margin-top: 8px;">
	  <img src="images/icons/goldAjaxLoad.gif" />
	</span>
	<div id="boothWiseInfoDiv"></div>


 </div>
 <script type="text/javascript">
	getBoothWiseCompleteInfo();
 </script>
</body>
</html>