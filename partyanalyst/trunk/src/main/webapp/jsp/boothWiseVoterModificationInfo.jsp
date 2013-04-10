<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Booth Wise Voter Modification Report</title>

<script type="text/javascript" src="js/boothWiseVoterModificationInfo/boothWiseVoterModificationInfo.js"></script>

<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/blockui.js"></script>

  <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
  <style type="text/css">
   #boothWiseVoterModifMainDiv{width:990px;margin-left:auto;margin-right:auto;float:none;}

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
	#subLevelsMainDiv{
	margin-top: 20px;
	display: inline-block;
	position: relative; 
	color: #000; 
	width: 96%;
	}
	#subLevelDiv{overflow-x: scroll;}
	#subLevelVotersTable{margin-bottom: 14px;}
	#subLevelsErrorMsgDiv{height: 50px; text-align: center; font-size: 18px; margin-top: 15px;}
	#subLevelVotersTable_info{ margin-bottom: 16px;}
	#subLevelVotersTable_wrapper{ padding-top: 10px;}
	table.dataTable thead th{ padding: 3px 6px 3px 10px;}
  </style>
  <script type="text/javascript">


 var locationType = '${locationType}';
 var constituencyId = '${constituencyId}';
 var fromPublicationDateId = '${fromPublicationDateId}';
 var toPublicationDateId = '${toPublicationDateId}';
 var locationValue = '${locationValue}';
  </script>
</head>
<body>
<div id="boothWiseVoterModifMainDiv">

  <div id="subLevelsMainDiv" class="widget blue whitegloss" style="">
		   <div id="subLevelDiv" style="margin:5px 0px 30px 0px;"></div>
		   <div id="subLevelAjaxImageDiv" style="display:none;margin-left:400px;"><img src="images/icons/goldAjaxLoad.gif"/></div>
  </div>

<div id="GenderOrAddedVotersPopup"><div id="GenderOrAddedVotersTable"><div></div>

</div>

<script type="text/javascript">
  getBoothWiseVoterModificationDetails();
</script>
</body>
</html>