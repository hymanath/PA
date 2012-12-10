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
		
	Select Panchayat :<select id="panchayatField" class="selectWidth" name="state" style="margin-left:19px;" onChange="getVoterDetailsForPanchayat();buildVotersByLocPanchayatDataTable(this.id)"></select></div>
	
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
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Voters" id="votersId" style="height:24px;" onclick="showVotersDiv()"></b> </td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="AgeWise" id="ageWiseId" style="height:24px;" onclick="showAgeDiv()"></b> </td>
					
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
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Important Families</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
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

<div id="tableDiv"></div>

<!-- for  body 1 end    result  -->




<!-- for  body 2 start    result  -->

<div id='votersMainOuterDiv2' style="display:none">
	<div id='votersHeaderDiv2'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Local Cast statistics</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>
	</div>

	<div id='LocalCastDiv' class="divInfo">
	<div id='localCastStatsTabContent_header'></div>
	<div id='localCastStatsTabContent_body' class="yui-skin-sam yui-dt-sortable">	</div><br>
	<div id='localCastStatsTabContent_subbody'>
	
		</div>


</div>

<!-- for  body 2 end    result  -->



<!-- for  body3 start    result  -->

<div id='votersMainOuterDiv3' style="display:none">
	<div id='votersHeaderDiv3'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Voters Info</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>
	</div>
    
	<div id="votersBasicInfoDiv"></div>
	<div id="votersBasicInfoSubChartDiv"></div>
	<div id="votersBasicInfoSubDiv" class="yui-skin-sam yui-dt-sortable"></div>
	<div id='votersDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 3 end    result  -->



<!-- for  body4 start    result  -->

<div id='votersMainOuterDiv4' style="display:none">
	<div id='votersHeaderDiv4'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Age Wise</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
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
	var mandalId='';
	if(level == 1){
	type = 'constituency';
	id = $("#constituencyList").val();
	}
	else if(level == 2){
	type = 'mandal';
	mandalId = $("#mandalField").val();
	id=mandalId.substring(1);

	}
	else if(level == 3){
	  type = 'panchayat';
	  id = $("#panchayatField").val();
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

	if(reportLevel == "1"){

		var jsObj=
				{
					//constituencyId:constituencyId,
					//publicationDateId:publicationId,
					constituencyId:'232',
					publicationDateId:'2',
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

	var startNumber = mandalId.substring(0,1)

		

	if(reportLevel == "2"){


        if(startNumber == "1"){

			var jsObj=
					{

			        //boothId:boothId,
					//publicationDateId:publicationId,
					constituencyId:'0',
					publicationDateId:'2',
					mandalId:'844',
					boothId:'0',
					panchayatId:'0',
				    type:"mandal"
						
					};
		}else if(startNumber == "2"){

			var jsObj=
					{

						mandalId:mandalId,
						publicationId:publicationId,
						type:"localElectionBody",
						
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
					type:"panchayat",
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
			        //boothId:boothId,
					//publicationId:publicationId,
					constituencyId:'0',
					publicationDateId:'2',
					mandalId:'0',
					panchayatId:'0',					
					boothId:'202',
					type:"booth",
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAgewiseVoterDetails.action?"+rparam;

	callAjaxorVoterDetails(jsObj,url);
	}
}

function callAjaxorVoterDetails(jsObj,url){

	var myResults;

		 var callback = {			
				   success : function( o ) {
					try {										
					  myResults = YAHOO.lang.JSON.parse(o.responseText);					
						  if(jsObj.type == "constituency")
							buildConstituencyVoterDetailsTable(myResults);
						  else if (jsObj.type == "mandal")
							buildConstituencyVoterDetailsTable(myResults);
						  else if (jsObj.type == "localElectionBody")
							  buildConstituencyVoterDetailsTable(myResults);
						  else if (jsObj.type == "panchayat")
							  buildConstituencyVoterDetailsTable(myResults);
						  else if (jsObj.type == "booth")
							  buildConstituencyVoterDetailsTable(myResults);
					
						
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

function buildConstituencyVoterDetailsTable(result){

	var str='';
	str+='<table border="1" style="margin-left:200px;">';
	str+='<tr>'
	str+='<tr>'
	str+='<th rowspan="2">Age Range</th>';
	str+='<th colspan="2">TotalVoters</th>';
	str+='<th colspan="2">Male</th>';
	str+='<th colspan="2">FeMale</th>';
	str+='<th colspan="2">UnKnown</th>';
	str+='</tr>';
    
	str+='<tr>';
	str+='<th>TotalVoters</th>';
	str+='<th>TotalPercanrtage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percanrtage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percanrtage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percanrtage</th>';
	str+='</tr>';



	for(var i in result.votersDetailsVO){

	str+='<tr>';
	str+='<th>'+result.votersDetailsVO[i].ageRange+'</th>';
	str+='<th>'+result.votersDetailsVO[i].totalVoters+'</th>';
	str+='<th>'+result.votersDetailsVO[i].totalVotersPercent.toFixed(2)+'</th>';
	str+='<th>'+result.votersDetailsVO[i].totalMaleVoters+'</th>';
	str+='<th>'+result.votersDetailsVO[i].totalMaleVotersPercent.toFixed(2)+'</th>';
	str+='<th>'+result.votersDetailsVO[i].totalFemaleVoters+'</th>';
	str+='<th>'+result.votersDetailsVO[i].totalFemaleVotersPercent.toFixed(2)+'</th>';
	str+='<th>'+result.votersDetailsVO[i].totalUnknownVoters+'</th>';
	str+='<th>'+result.votersDetailsVO[i].totalUnknownVotersPercent.toFixed(2)+'</th>';
	
	str+='</tr>';

	}

	str+='</table>';

	$('#tableDiv').html(str);


}

</script>
</body>
</html>