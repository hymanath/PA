<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title> Party Analyst - Suggestive Model</title>
	
 <script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
 <script type="text/javascript" src="js/jqueryDataTable/jquery.dataTables.js"></script>

<link rel="stylesheet" href="/resources/demos/style.css" />

<link rel="stylesheet" href="styles/jQ_datatables/css/demo_page.css" />
<link rel="stylesheet" href="styles/jQ_datatables/css/demo_table.css" />
<link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css">
 <script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
 <script type="text/javascript" src="js/suggestiveModel.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
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
	<script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
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
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
		<link type="text/css" href="styles/bootstrapInHome/bootstrap-responsive.min.css" rel="stylesheet" />

    <link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
	
	<!-- YUI Dependency files (End) -->


<script type="text/javaScript" >
google.load("visualization", "1", {packages:["corechart"]});
var maxr = 1000;
</script>
<style type="text/css">	
#suggestiveMainDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 990px;margin-top:20px;margin-bottom:20px;}
.requiredFont{color :red}
	#paginationDivId{float: none;
    margin-left: auto;
    margin-right: auto;
  
    width: 800px;}
	
</style>
</head>
<body>
<div id="suggestiveMainDiv" align="center">
 <div class="widget blue">
  <div id="errorDiv"></div>
 <div id="mainDiv" align="center"  style="margin-top:15px;color:#222222;">

 <table style="width:58%;">
           <tr> <td>Constituency <font id="requiredValue" class="requiredFont">*</font> </td>
			<td></td><td><select id="listConstituencyNames" onchange="getPublicationDate();">
			<option value="0"> Select Constituency </option>
			</select></td>
			<td> Publication Date <font id="requiredValue" class="requiredFont">*</font> </td>
			<td></td><td><select id="publicationDateList">
			<option value="0"> Select Publication </option>
			</select></td>
		</tr>
			
	</table>

	<input type="button" value="submit" onclick="getVoterLocations();" class="btn btn-success"/>
	<div id="showAjaxImg" style="display:none"><img src="./images/icons/search.gif"/></div>
 </div>

  <br/> <br/>
 <div id="voterLocationsDiv" style="overflow-x: scroll; height: 500px;display:none;">
 
 </div>


 </div>

</div>

<script type="text/javascript">
var type= "${type}";

function getConstituencyList(){

var jsObj= 
	{	
		task:"getConstituencies"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getConstituenciesByPartyNYearAction.action?"+param;
	callAjax(param,jsObj,url);

}

function callAjax(param,jsObj,url){
	var myResults;					
		var callback = {			
				success : function( o ) {
					try 
					{												
						if(o.responseText)
							myResults = YAHOO.lang.JSON.parse(o.responseText);
					    if(jsObj.task == "getConstituencies")
						{	
							populateConstituencyListDropdown(myResults);
						}
						else if(jsObj.task == "getPublicationDate")
								{
								//$('#publicationAjaxImage').css('display','none');

									buildPublicationDateList(myResults);
								}
						else if(jsObj.task == "getVoterLocations")
						{
							
							buildVoterLocations(myResults,jsObj);
						}
						
						}catch (e){
					//alert("Invalid JSON result" + e);   
					
					}  
				},
			       scope : this,
			       failure : function( o ) {
			        			//alert( "Failed to load result" + o.status + " " + o.statusText);
			        }
			    };
		YAHOO.util.Connect.asyncRequest('POST', url, callback);
	}
function populateConstituencyListDropdown(results){

	var list = document.getElementById("listConstituencyNames");
	removeSelectElements(list);
	if(results!=null)
		for(var i in results)
		{
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
			addOptions(list,opElmt);			
		}	
}
function removeSelectElements(elmt)
{
	if(!elmt)
		return;

	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	

}
function addOptions(list,opElmt){
	try
		{
		list.add(opElmt,null); // standards compliant
		}
	catch(ex)
		{
		list.add(opElmt); // IE only
		}
}
function getVoterLocations()
{
	var errorDiv =$("#errorDiv");
	$("#voterLocationsDiv").css("display","none");
	var constituencyId = $("#listConstituencyNames").val();
	var publicationId = $("#publicationDateList").val();
	
	var str ='<font color="red">';
	var flag = true;
	if(constituencyId == 0)
	{
		str+='Select Constituency<br/>';
		flag =false;
	}
	if(publicationId == 0)
	{
	str+='Select Publication<br/>';
	flag =false;
	}
	if(flag == false)
	{
			errorDiv.html(str);
			return;
	}
	else
	{

$("#showAjaxImg").css("display","inline-block");
errorDiv.html('');
var jsObj= 
	{	
	        constituencyId:constituencyId,
			publicationId:publicationId,
			
			
			task:"getVoterLocations"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getVoterLocationsAction.action?"+param;
	callAjax(param,jsObj,url);
	}
}
function getPublicationDate()
	{
	var constituencyId= $("#listConstituencyNames").val();
	var choice=false;
	var jsObj=
	{
		selected:constituencyId,
		task:"getPublicationDate"
	};
	var param ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+param;	
	//$('#publicationAjaxImage').css('display','block');
	callAjax(param,jsObj,url);
}

	var publicationDatesList;
	function buildPublicationDateList(results)
	{
	publicationDatesList=results;
	var selectedElmt=document.getElementById("publicationDateList");

	removeSelectElements(selectedElmt);
	var publicationIdsArray = new Array();
	for(var val in results)
	{	
	publicationIdsArray.push(results[val].id);
		var opElmt = document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;
		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}	
	}

	var largest = Math.max.apply(Math, publicationIdsArray);
	$('#publicationDateList').val(largest);
	$('#publicationDateList').trigger("change");

}
function buildVoterLocations(results,jsObj)
{
	
	$("#showAjaxImg").css("display","none");
	$("#voterLocationsDiv").css("display","block");
	var str = '';
	str+='<table class="table table-bordered">';
	str+='<tr>';
	str+='<td>Range</td>';
	str+='<td>Panchayat</td>';
	str+='<td>Total Panchayats</td>';
	str+='</tr>';
	for(var i in results)
	{
		for(var j=0;j<results[i].range1.length;j++)
		{
		    str+='<tr>';
            str+='<td>'+results[i].range+'</td>';
			str+='<td>'+results[i].range1[j].panchayat+'</td>';
			if(j == 0)
			str+='<td rowspan='+results[i].range1.length+'>'+results[i].totalVoters+'</td>';
		    str+='</tr>';
		}
	
	}
	str+='</table>';
	str+='<input type="button" value="Export To Excel" onclick="generateExcel();" class="btn btn-success"/>';
	$("#voterLocationsDiv").html(str);
	
	
	
}
</script>
<script>

var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,' , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()

function generateExcel()
{
 tableToExcel('voterLocationsDiv', 'Report');
}
</script>
<script>
 getConstituencyList();
</script>
</body>
</html>
