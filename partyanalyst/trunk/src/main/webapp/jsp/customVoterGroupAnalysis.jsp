<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 <%-- <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>--%>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
	<script type="text/javascript" src="js/customVoterGroupAnalysis.js"></script>
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
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 

<script type="text/javascript" src="http://www.google.com/jsapi"></script>
   <%--  <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/facescroll.js"></script>
	   <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/jquery.ui.touch-punch.min.js"></script> --%>

<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
   
<script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
<script type="text/javascript" src="js/highcharts/js/highchartColorPicker.js"></script>

   <style type="text/css">
    #customVotersMainDiv{float: none;margin: 20px auto;width: 980px;}
	#casteWiseVotersCountInnerDiv table td{
    color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;
	}
	#casteWiseVotersCountInnerDiv table th{background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
    }
	#casteWiseVotersCountInnerDiv{margin-top: 16px;}
	#amount {
    text-align: center;
    width: 90%;
}
#castContainerChartInner{padding-bottom: 2px;}
#casteWiseVotersCountDiv{padding-bottom: 30px;}
#rangeSliderDiv{width:500px;margin-left:auto;margin-right:auto;border:1px solid #ccc;padding:5px 20px;margin-top:50px;}

.table-bordered th, .table-bordered td{text-align:center;color:#000000;font-size:14px;}
	.table-bordered th{background:#D9EDF7;}
	
	#ageWiseInGroupDiv{
		width:950px;margin-left:auto;margin-right:auto;margin:10px;
	}


#partyWiseVotersJqTable th{
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
table.dataTable tr.odd {
    background-color: #ffffff;
}
table.dataTable tr.even {
    background-color:#EdF5FF;
}
table.dataTable tr.odd td.sorting_1 {
    background-color: #ffffff;
}
table.dataTable tr.even td.sorting_1 {
    background-color: #EdF5FF;
}

#partyWiseVotersJqTable td{ padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}


.noteDiv {
    margin-bottom: 18px;
    margin-top: 29px;
}
#impFamiliesTitle{
margin-top: 17px;
}
.table thead.info th,.impFamilesMainDiv th,#censusTab th{background:#d9edf7; color:#454545;}

.whitegloss h5.whitegloss{margin: 0px -20px; padding: 10px 10px 10px 20px;clear:both;}

.FamiliyList li{margin:5px;font-weight:bold;font-size:14px;padding:6px;width:100%;}
#ImportantFamiliesDiv{
	  margin-bottom: 20px;
	}
   </style>


   <script type="text/javascript">
   var customVoterGroupId = "${customVoterGroupId}";
   var publicationId = "${publicationDateId}";
   </script>
</head>
<body>
<div id="ageWiseInGroupDiv" class="widget blue whitegloss"></div>
<div id="customVotersMainDiv">
 <div id="casteWiseVotersCountDiv" class="widget blue">
  <h4>Caste Wise Custom Voters Count</h4>
  <div id="casteWiseVotersCountInnerDiv"></div>
  <div id="castContainerChartInner" style="border:1px solid;">
		<div id="rangeSliderDiv">
			<h5 style="text-align:center;">Drag Slider for Building Chart Based on Voters Caste Percentage </h5>
			<div id="slider" class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" aria-disabled="false"><a href="#" class="ui-slider-handle ui-state-default ui-corner-all" style="left: 0%;"></a>
			</div>
				<p style="padding-bottom:2px;">
					<input type="text" id="amount" readonly style="border: 0; color: #f6931f; font-weight: bold;background-color:#ffffff;" />
				</p>
		</div>
  <div id="casteWiseVotersCountGrapDiv"></div>
  </div>
 </div>

<!-- For Part wise VotersInfo -->
<div id="voterCountForPartyDiv" class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;">
<h4 class="">Party Wise Voters</h4>
<div id="partyWiseAssignedVotersDiv" style="margin-top:30px;"></div>
<div id="partyWiseVotersDiv"  style="margin-top:20px;margin-bottom:50px;"></div>
</div>
<!-- End of party wise VotersInfo -->
<div id="customVoterFamilyDiv" class="widget blue whitegloss" style="display:inline-block;width: 96%;color:#000;position:relative;">
<h4 class="">Family Wise Custom Voters</h4>

<div id="ImportantFamiliesDiv" style="margin-top:30px;">
	<div id ="impFamilesBasicDetails" style="margin-left: auto; margin-right: auto; width: 950px;"></div>
	</br>
	</div>
</div>
</div>
<script  type="text/javascript">
getVotersCountForPartyByCustomGroupId();
getCasteWiseCustomVotersCount();
 getAgeWiseCustomVotersInGroup();
function getAgeWiseCustomVotersInGroup(){
	var jsObj={
			customGroupId:2,
			publicationDateId:8,
			task:'getAgeWiseCustomVotersInGroup'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgeWiseCustomVotersInGroupAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	

	
	
/* function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);
									
									if(jsObj.task=="getAgeWiseCustomVotersInGroup"){
										buildAgeWiseInGroupTable(myResults);
									}
								}catch (e) {
							    console.log(e);
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}*/

function buildAgeWiseInGroupTable(result){
	var str="";
	str+='<h4>Age Wise Voters Information</h4>';
	str+='<table class="table table-bordered" id="ageWiseInGroupTable">';
	str+='<thead><tr><th rowspan=2>AGE Range</th><th colspan=2>Total Voters</th><th colspan=2>Male</th>	<th colspan=2>Female</th></tr><tr>	<th>Total Voters</th><th>Total Percentage</th><th>Voters</th><th>Percentage</th><th>Voters</th>	<th>Percentage</th>	</tr></thead><tbody>';
	
		str+='<tr><td>18-25</td>';
		str+='<td>'+checkForNull(result.totalVotersFor18To25)+'</td>';
		str+='<td>'+result.votersPercentFor18To25+'</td>';
		str+='<td>'+checkForNull(result.maleVotersCountBetween18To25)+'</td>';
		str+='<td>'+result.maleVotersPercentFor18To25+'</td>';
		str+='<td>'+checkForNull(result.femaleVotersCountBetween18To25)+'</td>';
		str+='<td>'+result.femaleVotersPercentFor18To25+'</td>';
		str+='</tr>'
		
		str+='<tr><td>26-35</td>';
		str+='<td>'+checkForNull(result.totalVotersFor26To35)+'</td>';
		str+='<td>'+result.votersPercentFor26To35+'</td>';
		str+='<td>'+checkForNull(result.maleVotersCountBetween26To35)+'</td>';
		str+='<td>'+result.maleVotersPercentFor26To35+'</td>';
		str+='<td>'+checkForNull(result.femaleVotersCountBetween26To35)+'</td>';
		str+='<td>'+result.femaleVotersPercentFor26To35+'</td>';
		str+='</tr>'
		
		str+='<tr><td>36-45</td>';
		str+='<td>'+checkForNull(result.totalVotersFor36To45)+'</td>';
		str+='<td>'+result.votersPercentFor36To45+'</td>';
		str+='<td>'+checkForNull(result.maleVotersCountBetween36To45)+'</td>';
		str+='<td>'+result.maleVotersPercentFor36To45+'</td>';
		str+='<td>'+checkForNull(result.femaleVotersCountBetween36To45)+'</td>';
		str+='<td>'+result.femaleVotersPercentFor36To45+'</td>';
		str+='</tr>'
		
		str+='<tr><td>46-60</td>';
		str+='<td>'+checkForNull(result.totalVotersFor46To60)+'</td>';
		str+='<td>'+result.votersPercentFor46To60+'</td>';
		str+='<td>'+checkForNull(result.maleVotersCountBetween46To60)+'</td>';
		str+='<td>'+result.maleVotersPercentFor46To60+'</td>';
		str+='<td>'+checkForNull(result.femaleVotersCountBetween46To60)+'</td>';
		str+='<td>'+result.femaleVotersPercentFor46To60+'</td>';
		str+='</tr>'
		
		str+='<tr><td>60 Above</td>';
		str+='<td>'+checkForNull(result.totalVotersForAbove60)+'</td>';
		str+='<td>'+result.votersPercentForAbove60+'</td>';
		str+='<td>'+checkForNull(result.maleVotersCountAbove60)+'</td>';
		str+='<td>'+result.maleVotersPercentForAbove60+'</td>';
		str+='<td>'+checkForNull(result.femaleVotersCountAbove60)+'</td>';
		str+='<td>'+result.femaleVotersPercentForAbove60+'</td>';
		str+='</tr>'
		
	
	str+='</tbody>';
	str+='</table>';
	$('#ageWiseInGroupDiv').html(str);
}
function checkForNull(val){
	if(val!=null)
		return val;
	else
		return 0;
}
function getFamilies(){
	$("#basicInfoAjaxDiv").show();
				var jsObj1=
					{
						customVoterGroupId:customVoterGroupId,
						publicationDateId:publicationId,
						task:"CustomVoterImpFamilies",
			
					}
			var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj1);
					var url1 = "getCustomVoterFamilyAction.action?"+rparam1;						
				callAjax(jsObj1,url1);
}
getFamilies();
</script>
</body>
</html>