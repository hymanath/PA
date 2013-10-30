<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- YUI Dependency files (Start) -->
<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script>  
<script type="text/javascript" src="js/yahoo/element-min.js"></script> 	
<script src="js/yahoo/resize-min.js"></script> 
<script src="js/yahoo/layout-min.js"></script>  
<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
<script type="text/javascript" src="js/json/json-min.js"></script>
<script type="text/javascript" src="js/yahoo/connection-min.js"></script>  
<script type="text/javascript" src="js/yahoo/datasource-min.js"></script>   
<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
<!-- Skin CSS files resize.css must load before layout.css -->  
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
<!-- YUI Dependency files (End) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
<style type="text/css">
#partyWiseLocalCastStatsTab table th {
    background-color: #CDE6FC;
    color: #333333;
    font-family: arial;
    font-size: 13px;
    font-weight: bold;
    text-align: left;
}
#partyWiseLocalCastStatsTab table td 
{
    color: #676A67;
    font: small-caption;
	font-family: arial;
    font-size: 12px;
}	
 #partyWiseLocalCastStatsTabTitle
{
	background: none repeat scroll 0 0 #49AFCD;
    border-radius: 4px 4px 4px 4px;
    color: white;
    font-family: arial;
    font-size: 15px;
}
.dataTables_length {
    float: left;
    font-family: arial;
    font-size: 14px;
    margin-bottom: 10px;
}
.dataTables_filter {
    font-family: arial;
    text-align: right;
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
</style>
<title>Caste Vs Party Analysis</title>
<script type="text/javascript">
var constituencyId     =  '${constituencyId}';
var publicationDateId  =  '${publicationDateId}';
var publicationYear    =  '${publicationYear}';
var type               =  '${type}';
var typename           =  '${typename}';
var id                 =  '${id}';

/*
	This Method is used for making a ajax call foe getting caste and party info of each lavel in a constituency
*/
function getPartyWiseCastInfo()
{
	var jsObj=
	{
		type:type,	
		id:id,
		typename:typename,
		publicationDateId:publicationDateId,
		constituencyId:constituencyId,
		task:"getPartyCastInfo"
	}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getvotersCastInfoByConstituency.action?"+rparam;	callAjax(jsObj,url)
}
/*
	This Method is used for building a data table for the result
*/
function buildPartyWiseCastDetailsTable(myresults,jsObj)
{
	$("#ajaxImageDiv").css('display','none');
	if(myresults != null && myresults.voterCastInfodetails != null && myresults.voterCastInfodetails.castVOs != null && myresults.voterCastInfodetails.castVOs.length > 0){
	var result = myresults.voterCastInfodetails.castVOs;
	var str ='<div>';
	
	str+='<div id="partyWiseLocalCastStatsTabTitle" align="center" style=""><h2 id="subHeading" >Caste V/s Party Analysis Of '+jsObj.typename+' in '+publicationYear+'</h2></div>';
	str+=' <table id="partyWiseCastJqTable" cellpadding="0" cellspacing="0" border="0" width="100%">';
	str+='  <thead>';
	str+='   <tr>';
	str+='     <th>Caste</th>';
	str+='     <th>Voters</th>';
	str+='     <th>Party not assigned voters</th>';
	str+='     <th>Party assigned voters</th>';
	if(result[0].partiesList != null && result[0].partiesList.length > 0)
	{
	for(var p in result[0].partiesList)
	str+='     <th>'+result[0].partiesList[p].partyName+'</th>';
	}
	str+='   </tr>';
	str+='  </thead>';
	str+='  <tbody>';
	for(var i in result){
	str +='   <tr>';
	str +='		<td>'+result[i].castName+'</td>';
	str +='		<td>'+result[i].castCount+'</td>';
	str +='		<td>'+result[i].partyNotAssigCount+'</td>';
	str +='		<td>'+result[i].partyCount+'</td>';
	  if(result[i].partiesList != null && result[i].partiesList.length > 0){
		for(var k in result[i].partiesList) 
		  str +=' <td>'+result[i].partiesList[k].partyCount+'</td>';
      }
	  str+='   </tr>';
    }
	str+='  </tbody>';
	str+=' </table>';
	str+='</div>';
	$("#partyWiseLocalCastStatsTab").html(str);
	$("#partyWiseLocalCastStatsTab").css("margin-top","25px;");
	$('#partyWiseCastJqTable').dataTable({
	"aaSorting": [[ 1, "asc" ]],
	"iDisplayLength": 15,
	"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]]
	});
	$("#partyWiseCastJqTable_wrapper").css("border","1px solid #D3D3D3");
    $("#partyWiseCastJqTable_wrapper").css("padding","8px 8px 25px");
	}else
	{
			$("#partyWiseLocalCastStatsTab").html('<div style="font-weight:bold;margin-left:375px;margin-top:70px;">Data Not Available</div>');
	}
}
/*
	This Method is used for handling all ajax calls
*/
function callAjax(jsObj,url)
{
	var myResults;
	var callback = {			
 	success : function( o ) {
	try {												
	myResults = YAHOO.lang.JSON.parse(o.responseText);					
	if(jsObj.task == "getPartyCastInfo")
	{
		buildPartyWiseCastDetailsTable(myResults,jsObj);
	}
								
	}catch (e) 
	{
		$("#votersEditSaveAjaxImg").hide();
		$("#votersEditSaveButtnImg").removeAttr("disabled");
	}  
 	},
	scope : this,
 	failure : function( o )
	{
 		                			
 	}
 	};
	YAHOO.util.Connect.asyncRequest('POST', url, callback);	   
}
getPartyWiseCastInfo();
</script>
</head>
<body>
 <div id="ajaxImageDiv" align="center" style="margin-top: 100px;"><img src="./images/icons/goldAjaxLoad.gif" alt="Processing Image"/> </div>
<div id='partyWiseLocalCastStatsTab' class="yui-skin-sam yui-dt-sortable" >	</div>
</body>
</html>