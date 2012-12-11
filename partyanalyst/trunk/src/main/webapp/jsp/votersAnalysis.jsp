<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>

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
	<script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
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
   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<title>Voters analysis</title>

<style type="text/css">
table.gridtable {
	font-family: arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>

<style>
  #votersBasicInfoDivSub{
    background-color: #EDF5FF;
    font-size: 15px;
    margin-top: 5px;
    text-align: center;
}	

#votersBasicInfoSubDiv{
  margin-left: 240px;
}

#votersBasicInfoSubChartDiv{
  margin-left: 308px;
}
.pull-right{
	 margin-top: -15px;
}

.form-horizontal label{  display:block; font:12px Arial;
    margin-bottom:5px;padding-top:5px;cursor:hand;cursor:pointer;}
	.form-horizontal .radio
	{
	font-size: 13px;
    font-weight: normal;
    margin-top:-1px;
	margin-right:8px;
	}
.buttonStyle {
	-moz-border-radius:5px 5px 5px 5px;
    background: none repeat scroll 0 0 #0063DC;
    border: medium none;
    border-radius: 4px 4px 4px 4px;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
	height:24px;
}
 #votersouterDiv{
	 margin-left: auto;
    margin-right: auto;
    width: 980px;
 }

 fieldset {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border: 3px solid #CFD6DF;
   
    margin-bottom: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
}
p {
    color: #333333;
    font-size: 13px;
    line-height: 18px;
}
.divInfo
{
 background-color:#FFFFFF;
 border-bottom: 1px solid #B3C1CE;
 border-left: 1px solid #B3C1CE;
 border-right: 1px solid #B3C1CE;
 padding:5px;

}
#subLevelTable,#localCastStatsTabContent_body table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;}
#subLevelTable tr:nth-child(even),#localCastStatsTabContent_body table  tr:nth-child(even){background:#EdF5FF;}

#subLevelTable td,#localCastStatsTabContent_body table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}

#subLevelTable th,#localCastStatsTabContent_body table th
{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: left;
	}

#impFamPancBothDtls,#impFamDtls{
 margin-top:25px;
 margin-left: auto;
    margin-right: auto;
    width: 84%;
}

#localCastStatsTabContent_subbody caption,#localCastStatsTabContent_body caption{
    color: #000;
    font-size: 13px;
    margin-left: -460px;
    font-style: normal;
	font-family: verdana,sans-serif;
   }
.votersWidgetHeader
	{
		background-image:url("images/icons/electionResultsAnalysisReport/mid.png");
		height:30px;
	}

	.votersWidgetMainHeader
	{
		background-image:url("images/icons/districtPage/header_body.png");
		height:36px;
	}
	.votersWidgetHeader_span
	{
		position:relative;
		top:8px;
		left:10px;
		color:#4B74C6;
		font-weight:bold;
	}

</style>

<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
	
var Localization = { <%
			
			ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
			
			String ACONSTITUENCY = rb.getString("ACONSTITUENCY");
			String locationAlert = rb.getString("validLocation");
		  %> }
var locationDetails={
				constituencyArr:[],
					};

<c:forEach var="constituency" items="${constituencyList}">
	var ob={
			id:'${constituency.id}',
			value:'${constituency.name}'
			};
locationDetails.constituencyArr.push(ob);
</c:forEach>


	

		

</script>
</head>
<body>
<br><br>

<div id="votersouterDiv" >



<fieldset>
<div style="color:#707070;font-weight:bold;font-size:12px;">Please select from the following list boxes to view detailed statistics by Assmbly/mandal/Panchayat/Polling station level</div><P >Fields marked with <font class="requiredFont"> * </font> are mandatory</P>
<div id="locationAlertMsg" align="left"></div>

<div id="reportLevelDiv" style="width:80%;padding-top:10px;padding-bottom:10px;margin-left:auto;margin-right:auto;">Select Report Level : <select id="reportLevel" class="selectWidth" style="margin-left:3px;" name="constituencyList" onchange="showReportLevel(this.options[this.selectedIndex].value);">
		<option value=1>Constituency</option>
		<option value=2>Mandal</option>
		<option value=3>Panchayat</option>
		<option value=4>PollingStation</option>
		</select>
		
</div>
	<div id="ConstituencyDiv" style="width:80%;padding-top:10px;padding-bottom:10px;margin-left:auto;margin-right:auto;">
	Select Constituency : <s:select theme="simple" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="getVoterDetailsForConstituency();getMandalList(\'mandalField\');getPublicationDate();"/>&nbsp;&nbsp;

	
		
		Select Publication Date : <select id="publicationDateList" class="selectWidth" style="margin-left:17px;width:175px;" name="publicationDateList">
		</select>
		
	</div>
	<div id="mandalDiv" style="width:80%;padding-top:10px;padding-bottom:10px;display:none;margin-left:auto;margin-right:auto;">
		
	Select Mandal&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: <select id="mandalField" class="selectWidth" style="margin-left:1px;" name="state" onchange="getVoterDetailsForMandal();getPanchayatList('panchayat','panchayatField');getPanchayatList('pollingstation','pollingStationField');"></select></div>
		
	<div id="panchayatDiv" style="width:80%;padding-top:10px;padding-bottom:10px;display:none;margin-left:auto;margin-right:auto;">
		
	Select Panchayat :<select id="panchayatField" class="selectWidth" name="state" style="margin-left:19px;" onChange="getVoterDetailsForPanchayat();buildVotersByLocPanchayatDataTable(this.id);"></select></div>
	
	<div id="pollingStationDiv" style="width:80%;padding-top:10px;padding-bottom:10px;display:none;margin-left:auto;margin-right:auto;">
	Select PollingStation : <select id="pollingStationField" class="selectWidth" name="state" style="margin-left:3px;"  onChange="getVoterDetailsForBooth();buildVotersByLocBoothDataTable(this.id);"></select></div>
	

	<div id="profileManagementDiv">
		<table class="statusData_table" width="100%">	
		
          <tr>
			<td class="statusData_table_data" width="100%" style="padding-top:23px">
				<table>
				  <tr>
			    	<td style="padding-left:109px"><b><input type="button" class="buttonStyle" value="Important Families" id="importantFamiliesId" style="height:24px;" onclick="showImportantFamiliesDiv()"></b></td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Local Cast Statistics" 
					 id="localCaststatId" style="height:24px;" onclick="showLocalCastDiv();getVotersCastInfo();getCastInfoForsubLevel();"></b> </td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Voters" id="votersId" style="height:24px;" onclick="showVotersDiv();"></b> </td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="AgeWise" id="ageWiseId" style="height:24px;" onclick="showAgeDiv();"></b> </td>
					
				  </tr>
				</table>
			</td>
		 </tr>
		</table>
	
</div>
	
	</div>



</div>
</fieldset>

</div>

<!-- for  body 1 start    result  -->
<HR>
<div id='votersMainOuterDiv1' style="display:none">
	<div id='votersHeaderDiv1'>
		<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
					<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
					<td><div class="votersWidgetMainHeader"><span id="votersPopulationRange_head_span" class="votersWidgetHeader_span" style="top:11px;"> Important Families</span></div></td>
					<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>					
				</table>
		<div id="impFamPancBothDtls"></div>
		<div id="impFamDtls"  class="yui-skin-sam yui-dt-sortable"></div>
	</div>

	<div id='ImportantFamiliesDiv' class="divInfo">
	<div style="margin-left:120px;" id="votersByLocationTabContentDiv_body" class="yui-skin-sam yui-dt-sortable"></div>

<div style="margin-left:120px;" id="votersByPanchayatTabContentDiv_body" class="yui-skin-sam yui-dt-sortable"></div>
	</div>

</div>


<div id="voterDetailsNote"></div>
<div id="tableDiv" style="margin-left:119px;padding:10px;"></div>

<div id="voterAgewiseDetailsNote"></div>
<div id="agewiseDetails" style="margin-left:35px;padding:10px;"></div>

<div id="voterAgeAngGenderwiseDetailsNote"></div>
<div id="ageAndgenderWiseDetails" style="margin-left:10px;padding:10px;"> </div>

<!-- for  body 1 end    result  -->

<!-- for  body 2 start    result  -->

<div id='votersMainOuterDiv2' style="display:none">
	<div id='votersHeaderDiv2'>
		<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
					<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
					<td><div class="votersWidgetMainHeader"><span id="votersPopulationRange_head_span" class="votersWidgetHeader_span" style="top:11px;"> Local Cast Statistics</span></div></td>
					<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>					
				</table>
	</div>

	<div id='LocalCastDiv' class="divInfo">
	<div id='localCastStatsTabContent_header'></div>
	<div id='localCastStatsTabContent_body' class="yui-skin-sam yui-dt-sortable">	</div><br>
	<div id='localCastStatsTabContent_subbody'></div>
	<div id='localCastStatsTabContent_subbody1'></div>


</div>

<!-- for  body 2 end    result  -->



<!-- for  body3 start    result  -->

<div id='votersMainOuterDiv3' style="display:none">
	<div id='votersHeaderDiv3'>
		<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
					<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
					<td><div class="votersWidgetMainHeader"><span id="votersPopulationRange_head_span" class="votersWidgetHeader_span" style="top:11px;"> Voters Info</span></div></td>
					<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>					
				</table>
	</div>
    
	<div id="votersBasicInfoDiv"></div>
	<div id="votersBasicInfoSubChartDiv"></div>
	<div id="votersBasicInfoSubDiv" class="yui-skin-sam yui-dt-sortable"></div>
	<div id='votersDiv' class="divInfo"></div>
	
	

</div>

<!-- for  body 3 end    result  -->



<!-- for  body4 start    result  -->

<div id='votersMainOuterDiv4' style="display:none">
	<div id='votersHeaderDiv4'>
		<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
					<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
					<td><div class="votersWidgetMainHeader"><span id="votersPopulationRange_head_span" class="votersWidgetHeader_span" style="top:11px;"> Age Wise</span></div></td>
					<td width="5px"><img src="images/icons/districtPage/header_right.gif"/></td>
					</tr>					
				</table>
	</div>

	<div id='ageWiseInfoDiv' class="divInfo">


	
	</div>

</div>

<!-- for  body 4 end    result  -->

</div>

</div>
<script type="text/javascript">

showImportantFamiliesDiv();
//buildVotersByLocPanchayatDataTable();
showImportantFamiliesDiv();


</script>

<script>

function getCastInfoForsubLevel()
	{

	var publicationDateId = $("#publicationDateList").val();
	var level = $("#reportLevel").val();
	var type = '';
	var id='';
	var typeName='';
	var mandalId='';
	if(level == 1){
	type = 'constituency';
	id = $("#constituencyList").val();
	typeName = $("#constituencyList :selected").text();
	}
	else if(level == 2){
	type = 'mandal';
	mandalId = $("#mandalField").val();
	id=mandalId.substring(1);
	typeName = $("#mandalField :selected").text();
	}
	else if(level == 3){
	  type = 'panchayat';
	  id = $("#panchayatField").val();
	  typeName = $("#panchayatField :selected").text();
	}
	else if(level == 4){
		 type = 'booth';
		 id = $("#pollingStationField").val();
	}
		
		var jsObj=
		{		
				
				searchType : "Caste",
				searchText : "R",
				type:type,	
				id:id,
				typeName:typeName,
				publicationDateId:publicationDateId,	
				task:"getCastInfoForsubLevels"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj,url);

	}
function getVoterDetailsForConstituency(){


	var reportLevel = $('#reportLevel').val();
	var constituencyId = $('#constituencyList').val();
	var publicationId = $('#publicationDateList').val();

	if(publicationId == null)
		return false;

	if(reportLevel == "1"){

		var jsObj=
				{					
					constituencyId:constituencyId,
					publicationDateId:publicationId,
					mandalId:'0',
					boothId:'0',
					panchayatId:'0',
				    type:"constituency",
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;						
		
		callAjaxorVoterDetails(jsObj,url);
	}
}

function getVoterDetailsForMandal(){

    var reportLevel = $('#reportLevel').val();
	var mandalId = $('#mandalField').val();
	var publicationId = $('#publicationDateList').val();

	var startNumber = mandalId.substring(0,1);

	if(reportLevel == "2"){


        if(startNumber == "2"){

			var jsObj=
					{
						constituencyId:'0',
						publicationDateId:publicationId,
						mandalId:mandalId,
						boothId:'0',
						panchayatId:'0',
						type:"mandal"
						
					};
		}else if(startNumber == "1"){

			var jsObj=
					{
						mandalId:mandalId,
						publicationDateId:publicationId,
						type:"localElectionBody"
						
					};
		}

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

		callAjaxorVoterDetails(jsObj,url);
	}
}


function getVoterDetailsForPanchayat(){


    var reportLevel = $('#reportLevel').val();
	var panchayatId = $('#panchayatField').val();
	var publicationId = $('#publicationDateList').val();

   if(reportLevel == "3"){

		var jsObj=
				{
			        constituencyId:'0',
					mandalId:'0',
					boothId:'0',
					panchayatId:panchayatId,
					publicationDateId:publicationId,
					type:"panchayat"
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

      callAjaxorVoterDetails(jsObj,url);
   }
}


function getVoterDetailsForBooth(){


    var reportLevel = $('#reportLevel').val();
	var boothId = $('#pollingStationField').val();
	var publicationId = $('#publicationDateList').val();

	if(reportLevel == "4"){

		var jsObj=
				{ 
					constituencyId:'0',
					publicationDateId:publicationId,
					mandalId:'0',
					panchayatId:'0',					
					boothId:boothId,
					type:"booth",
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAgewiseVoterDetails.action?"+rparam;

	callAjaxorVoterDetails(jsObj,url);

	}
}

function callAjaxorVoterDetails(jsObj,url){

	$('#tableDiv').html('');
	$('#agewiseDetails').html('');
	$('#ageAndgenderWiseDetails').html('');

	var myResults;

		 var callback = {			
				   success : function( o ) {
					try {	
						
						//console.log(o.responseText);
					  myResults =  YAHOO.lang.JSON.parse(o.responseText);					
							buildConstituencyVoterDetailsTable(myResults,jsObj.type);

							if(jsObj.type != "booth"){
								buildAgewiseDetails(myResults,jsObj.type);
								buildAgeAndGenderWiseDetails(myResults,jsObj.type);
							}
						 
						
					}catch (e){   
							
						alert("Invalid JSON result" + e);   
					}  
				   },
				   scope : this,
				   failure : function( o ) {
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);


}

function buildConstituencyVoterDetailsTable(result,type){

	var noteString = '';

	if(type == "constituency")
		noteString = $('#constituencyList :selected').text()+" "+"constituency";
	else if(type == "mandal")
		noteString = $('#mandalField :selected').text()+" ";
	else if(type == "panchayat")
		noteString = $('#panchayatField :selected').text()+" "+"panchayat";
	else if(type == "localElectionBody")
		noteString = $('#mandalField :selected').text()+" "+"localElection";
	else 
		noteString = $('#pollingStationField :selected').text();

	$('#voterDetailsNote').html('<h4 style="color:green;margin-left:120px;">'+noteString+" "+"voters details"+'</h4>');

	var str='';
	str+='<table border="1" style="margin-top:20px;text-align:center;" class="gridtable">';
	str+='<tr>'
	str+='<tr>'
	str+='<th rowspan="2">Age Range</th>';
	str+='<th colspan="2">TotalVoters</th>';
	str+='<th colspan="2">Male</th>';
	str+='<th colspan="2">Female</th>';
	str+='<th colspan="2">UnKnown</th>';
	str+='</tr>';
    
	str+='<tr>';
	str+='<th>TotalVoters</th>';
	str+='<th>TotalPetcentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	str+='</tr>';

	for(var i in result.votersDetailsVO){

	str+='<tr>';
	str+='<td>'+result.votersDetailsVO[i].ageRange+'</td>';
	str+='<td>'+result.votersDetailsVO[i].totalVoters+'</td>';

	if(result.votersDetailsVO[i].totalVotersPercent != null)
		 str+='<td>'+result.votersDetailsVO[i].totalVotersPercent.toFixed(2)+'</td>';	 
	else
		str+='<td>0.00</td>';
	

	str+='<td>'+result.votersDetailsVO[i].totalMaleVoters+'</td>';
    
	if(result.votersDetailsVO[i].totalMaleVotersPercent != null)
		str+='<td>'+result.votersDetailsVO[i].totalMaleVotersPercent.toFixed(2)+'</td>';
	else
		str+='<td>0.00</td>';

	str+='<td>'+result.votersDetailsVO[i].totalFemaleVoters+'</td>';

	if(result.votersDetailsVO[i].totalFemaleVotersPercent != null)
	  str+='<td>'+result.votersDetailsVO[i].totalFemaleVotersPercent.toFixed(2)+'</td>';
	else
		str+='<td>0.00</td>';
	str+='<td>'+result.votersDetailsVO[i].totalUnknownVoters+'</td>';

	if(result.votersDetailsVO[i].totalUnknownVotersPercent != null)
	   str+='<td>'+result.votersDetailsVO[i].totalUnknownVotersPercent.toFixed(2)+'</td>';
	else
		str+='<td>0.00</td>';
	
	str+='</tr>';

	}

	str+='</table>';

	$('#tableDiv').html(str);


}
function buildAgewiseDetails(results , type){


   var innerResults;
   var noteString;

	if(type == "constituency"){
		innerResults = results.mandalsVotersDetails;
		//noteString = "Mandals of "+$('#constituencyList :selected').text()+" "+"constituency";

		noteString = "Mandal wise voter details:Agewise";
	}
	else if(type == "mandal"){
		innerResults = results.panchayatVotersDetails;
		//noteString ="Panchayatis of "+ $('#panchayatField :selected').text();
		noteString = "Panchayat wise voter details:Agewise";
	}
	else if(type == "panchayat"){
		innerResults = results.boothVotersDetails;
	    // noteString = "Booths of "+$('#panchayatField :selected').text()+" "+"panchayat";
		noteString = "Booth wise voter details:Agewise";
	}
	else if(type == "localElectionBody"){
		innerResults = results.boothVotersDetails;
		//noteString = "Booths of "+$('#mandalField :selected').text()+" "+"localElection";
		noteString = "Booth wise voter details:Agewise";
	}
	


	if(innerResults.length == 0){
		alert("No Records");
		return false;
	}

	//$('#voterAgewiseDetailsNote').html('<h4 style="color:green;margin-left:120px;">'+noteString+" "+"voters agewise details"+'</h4>');
	$('#voterAgewiseDetailsNote').html('<h4 style="color:green;margin-left:120px;">'+noteString+'</h4>');

	

	var str='';

str+='<table border="1" style="margin-top:20px;text-align:center;" class="gridtable">';


str+='<tr>';

str+='<th rowspan="2">Mandal Name</th>';
str+='<th  rowspan="2">Total Voters</th>';
str+='<th colspan="2">18-25</th>';
str+='<th colspan="2">26-35</th>';
str+='<th colspan="2">36-45</th>';
str+='<th colspan="2">46-60</th>';
str+='<th colspan="2">60-Above</th>';

str+='</tr>';
str+='<tr>';

str+='<th>Voters</th>';
str+='<th>Percentage</th>';
str+='<th>Voters</th>';
str+='<th>Percentage</th>';
str+='<th>Voters</th>';
str+='<th>Percentage</th>';
str+='<th>Voters</th>';
str+='<th>Percentage</th>';
str+='<th>Voters</th>';
str+='<th>Percentage</th>';
str+='</tr>';

for(var i=0;i<innerResults.length;i++){

	str+='<tr>';

	if(type == "constituency")
	 str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	 str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
	 str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	 str+='<td>'+innerResults[i].boothName+'</td>';

	str+='<td>'+innerResults[i].totalVoters+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor18To25+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor18To25+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor26To35+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor26To35+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor36To45+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor36To45+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor46To60+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor46To60+'</td>';
	str+='<td>'+innerResults[i].totalVotersForAbove60+'</td>';
	str+='<td>'+innerResults[i].votersPercentForAbove60+'</td>';
	str+='</tr>';

}

str+='</table>';


$('#agewiseDetails').html(str);

}

function buildAgeAndGenderWiseDetails(results , type){



	 var innerResults;
	    var noteString;


	if(type == "constituency"){
		innerResults = results.mandalsVotersDetails;
		//noteString = "Mandals of "+$('#constituencyList :selected').text()+" "+"constituency";
		noteString = "Mandal wise voter details:Age and gender wise";

	}
	else if(type == "mandal"){
		innerResults = results.panchayatVotersDetails;
		//noteString = "Booths of "+$('#panchayatField :selected').text()+" "+"panchayat";
		noteString = "Mandal wise voter details:Age and gender wise";
	}
	else if(type == "panchayat"){
		innerResults = results.boothVotersDetails;
		innerResults = results.boothVotersDetails;
	    // noteString = "Booths of "+$('#panchayatField :selected').text()+" "+"panchayat";
		noteString = "Mandal wise voter details:Age and gender wise";
	}
	else if(type == "localElectionBody"){
		innerResults = results.boothVotersDetails;
		//noteString = "Booths of "+$('#mandalField :selected').text()+" "+"localElection";
		noteString = "Mandal wise voter details:Age and gender wise";
	}

	if(innerResults.length == 0){
		alert("No Records");
		return false;
	}

	//$('#voterAgeAngGenderwiseDetailsNote').html('<h4 style="color:green;margin-left:120px;">'+noteString+" "+"voters agewise details"+'</h4>');'

	$('#voterAgeAngGenderwiseDetailsNote').html('<h4 style="color:green;margin-left:120px;">'+noteString+'</h4>');

	var str='';

str+='	<table border="1" style="margin-top:20px;text-align:center;" class="gridtable">';

str+='<tr>';
str+='<th rowspan="2">Mandal Name</th>';
str+='<th colspan="3">18-25</th>';
str+='<th colspan="3">26-35</th>';
str+='<th colspan="3">36-45</th>';
str+='<th colspan="3">46-60</th>';
str+='<th colspan="3">60-Above</th>';

str+='</tr>';
str+='<tr>';


str+='<th>Male</th>';
str+='<th>Female</th>';
str+='<th>Unknown</th>';
str+='<th>Male</th>';
str+='<th>Female</th>';
str+='<th>Unknown</th>';
str+='<th>Male</th>';
str+='<th>Female</th>';
str+='<th>Unknown</th>';
str+='<th>Male</th>';
str+='<th>Female</th>';
str+='<th>Unknown</th>';
str+='<th>Male</th>';
str+='<th>Female</th>';
str+='<th>Unknown</th>';
str+='</tr>';

for(var i=0;i<innerResults.length;i++){


	str+='<tr>';

	if(type == "constituency")
	str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	str+='<td>'+innerResults[i].boothName+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotesFor18To25+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor18To25+'</td>';
	str+='<td>'+innerResults[i].totalUnknownVotersFor18To25+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor26To35+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor26To35+'</td>';
	str+='<td>'+innerResults[i].totalUnknownVotersFor26To35+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor36To45+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor36To45+'</td>';
	str+='<td>'+innerResults[i].totalUnknownVotersFor36To45+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor46To60+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor46To60+'</td>';
	str+='<td>'+innerResults[i].totalUnknownVotersFor46To60+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersForAbove60+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersForAbove60+'</td>';
	str+='<td>'+innerResults[i].totalUnknownVotersForAbove60+'</td>';

	str+='</tr>';

}

str+='</table>';

$('#ageAndgenderWiseDetails').html(str);
}
	
</script>
</body>
</html>