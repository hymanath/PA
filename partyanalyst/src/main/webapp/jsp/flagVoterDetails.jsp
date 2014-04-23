<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title> Party Analyst - Voter Tagging</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
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
 <div class="widget">
  <div id="errorDiv"></div>
<div id="voterDetailsDiv" style="display:none;"></div>
<div id="paginationDivId" style="display:none;"></div>
</div>


</div>

<script type="text/javascript">
var publicationId = "${publicationId}";
var constituencyId = "${constituencyId}";
var flagId = "${flagId}";
var locationId = "${locationId}";
var maxr = 10;
var type = "${maintype}";
 
function callAjax(param,jsObj,url){
	
	var myResults;					
		var callback = {			
 		               success : function( o ) {
							try {												
						myResults = YAHOO.lang.JSON.parse(o.responseText);	
						if(jsObj.task == "getVoterDeatils")
						{
							
							buildVoterDetails(myResults,jsObj);
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


function getVoterDeatils(startIndex)
{
var jsObj= 
	{	
	        locationId:locationId,
			publicationId:publicationId,
			flagId:flagId,
			startIndex:startIndex,
		    results:maxr,
			type:type,
			constituencyId:constituencyId,
			task:"getVoterDeatils"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getFlaggedvoterDetailsAction.action?"+param;
	callAjax(param,jsObj,url);
}

function buildVoterDetails(results,jsObj)
{
    if(results.length > 0)
	{
	$("#voterDetailsDiv").css("display","block");
	$("#paginationDivId").css("display","block");
	var str = '';
	str+='<div>';
	str+='<table class="table table-bordered" style="margin-top:10px;">';
	str+='<th>Name</th>';
	str+='<th>VoterID </th>';
	str+='<th>Gender</th>';
	str+='<th>Age</th>';
	str+='<th>HNo</th>';
	str+='<th>Guardian Name</th>';
	str+='<th>Mobile No</th>';
	str+='<th>FlagName</th>';
	str+='<th>Color</th>';
	
	str+='</thead>';
	str+='<tbody>';
	
	//var j = jsObj.startIndex;
	
	for(var i in results)
		{
	str+='<tr>';	
    str+='<td>'+results[i].name+'</td>';
	
	str+='<td>'+results[i].voterIDCardNo+'</td>';
	str+='<td>'+results[i].gender+'</td>';
	str+='<td>'+results[i].age+'</td>';
    str+='<td>'+results[i].houseNo+'</td>';
	str+='<td>'+results[i].firstName+'</td>';
	str+='<td>'+results[i].mobileNo+'</td>';
	str+='<td>'+results[i].flagName+'</td>';
	str+='<td><span style="background-color:#'+results[i].color+'">&nbsp;&nbsp;&nbsp;</td>';
	str+='</tr>';
	   }
	str+='</tbody>';
	str+='</table>';
	str+='</div>';
	str+='<br/>';
	
	$("#voterDetailsDiv").html(str);
	 var itemsCount=results[0].totalVoters;
	    var maxResults=jsObj.results;
	   
	     if(jsObj.startIndex==0){
		   $("#paginationDivId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)* maxResults;
				getVoterDeatils(num);
				
			}
		});

		 }
	}
		 }

	
</script>
<script>
getVoterDeatils(0);
</script>
</body>
</html>
