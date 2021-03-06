<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voter Average Age Report </title>

<!-- YUI Dependency files (Start) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
  
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/history/history.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>

<script type="text/javascript" src="../js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<script type="text/javascript" src="../js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="../styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

<!-- YUI Dependency files (End) -->

<script type="text/javascript" src="../js/constituencyPage/constituencyPage.js"></script>
<link rel="stylesheet" type="text/css" href="../styles/constituencyPage/constituencyPage.css">	
<link href="styles/indexPage/indexPage.css" rel="stylesheet" type="text/css" />
</HEAD>
<style>

</style>
 <BODY>

<center><div id="mainDiv" style="min-height:400px;">
<div class="row">
<div class="col-md-3 col-md-offset-4">
 <div style="margin-left:auto;margin-right:auto;margin-top:30px;">
	   <span style="font-weight:bold;font-size:14px;">Select Constituency</span> :<font id="requiredValue" class="requiredFont">*</font><select class="form-control" id="constituencyId" onChange="getAverageVoterDetails();"><option value="0"> Select Constituency </option></select></div>
</div>
</div>
  	
   <div><img style="margin-top:100px;margin-left:50px;display:none;" src="images/icons/goldAjaxLoad.gif" id="ajaxImg"></div>
   <div id="innerDiv" class="container" style="font-family: verdana; font-size: 15px; padding-top: 36px;display:none"></div>
</div></center>



 
  <script>

  $(document).ready(function(){
     getConstituencyList(); 
  });

  function getConstituencyList(){
    var jsObj= 
	{	
		task:"getConstituencies"		
	};
    $.ajax({
		   type: "POST",
		   url: "getConstituenciesByPartyNYearAction.action",		
		   data: {task:JSON.stringify(jsObj)},
		}).done(function(result) {
		   $("#constituencyId option").remove();
           for(var i in result)
           {
		       $("#constituencyId").append("<option value="+result[i].id+">"+result[i].name+"</option>");         
           }
		  
	});

}
   
	function getAverageVoterDetails(){
    var constiId= $("#constituencyId").val();
    $("#ajaxImg").css("display","block");
    $("#innerDiv").html("");
	var jsObj = {
			constiId : constiId,
			task : "getAverageVoterDetails"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getAverageVoterDetailsAction.action?"+rparam;
	callAjax(jsObj, url);

	}

function callAjax(jsObj,url){
		 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task =="getAverageVoterDetails")
								{
									buildAverageVoterDetails(myResults);
								}
							}catch (e) {
							     
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                		//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}









var reqfieldsArr = new Array();


function buildAverageVoterDetails(results){
$("#ajaxImg").css("display","none");
var str = "";

	str += '<h4 style="padding-bottom: 27px; margin-left: 163px;">'+results.name+' Constituency Voter Average Age Report</h4>';
	str += "<h4 style='margin-left: 28px;'>Constituency Wise Voter Average Age Report</h4>";
	str += "<div class='form-horizontal boothResults ' name='boothSelection' style='display:block;margin: 30px 30px;'>";
	str += "<table  class='table table-bordered' style='margin-bottom: -8px; margin-top: -20px;style='font-size:small''>";
	str += '<tr>';
	str += '<th>Constituency</th>';
	str += '<th>Total Voters</th>';
	str += '<th>Average Voters Age</th>';
	str += '</tr>';
	str += "<tr>";
	str += "<td>"+results.name+"</td>";
	str += "<td>"+results.totalCount+"</td>";
	str += "<td>"+results.perc+"</td>";
	str += "</tr>";
	str += "</table>";
str += '</div>';
//mandalwise
		if(results.selectOptionsList != null && results.selectOptionsList.length > 0)
		{
			str += "<h4 style='margin-left: 28px;'>Mandal Wise Voter Average Age Report</h4>";
			str += "<div class='form-horizontal boothResults ' name='boothSelection' style='display:block;margin: 30px 30px;'>";

			str += "<table class='table table-bordered' style='margin-bottom: -8px; margin-top: -20px;style='font-size:small''>";
			
			str += '<tr>';
			str += '<th>Mandal/Muncipality</th>';
			str += '<th>Total Voters</th>';
			str += '<th>Average Voters Age</th>';
			str += '</tr>';
			for(var i in results.selectOptionsList){
				str += "<tr>";
				str += "<td><span>"+results.selectOptionsList[i].name+"</span></td>";
				str += "<td><span>"+results.selectOptionsList[i].totalCount+"</span></td>";
				str += "<td><span>"+results.selectOptionsList[i].perc+"</span></td>";
				str += "</tr>";
			}
			str += "</table>";
			str += "</div>";
		}
		

//panchayatwise
		if(results.selectOptionsList1 != null && results.selectOptionsList1.length > 0)
		{
			str += "<h4 style='margin-left: 28px;'>Panchayat Wise Voter Average Age Report</h4>";
			str += "<div class='form-horizontal boothResults ' name='boothSelection' style='display:block;margin: 30px 30px;'>";

			str += "<table class='table table-bordered' style='margin-bottom: -8px; margin-top: -20px;style='font-size:small'>";
			str += '<tr>';
			str += '<th>Mandal/Muncipality</th>';
			str += '<th>Pancayat</th>';
			str += '<th>Total Voters</th>';
			str += '<th>Average Voters Age</th>';
			str += '</tr>';
			
			
			for(var i in results.selectOptionsList1)
			{
				var length = 0;
				for(var k in results.selectOptionsList1[i].selectOptionsList)
				{
					if(results.selectOptionsList1[i].selectOptionsList[k] != null)
					{
						length++;
					}
				}
				if(results.selectOptionsList1[i] != null)
				{
					str += "<tr>";
					str += "<td rowspan='"+length+"'>"+results.selectOptionsList1[i].name+"</td>";
					for(var j in results.selectOptionsList1[i].selectOptionsList)
					{
						if(results.selectOptionsList1[i].selectOptionsList[j] != null)
						{
							if(j > 0)
							{
								str += "<tr>";
							}
							str += "<td>"+results.selectOptionsList1[i].selectOptionsList[j].name+"</td>";
							str += "<td>"+results.selectOptionsList1[i].selectOptionsList[j].totalCount+"</td>";
							str += "<td>"+results.selectOptionsList1[i].selectOptionsList[j].perc+"</td>";
							if(j > 0)
							{
								str += "</tr>";
							}
						}
						str += "</tr>";
					}
					
				}
				
			}
		
			str += "</table>";

			str += "</div>";
		}

//boothwise
		if(results.selectOptionsList2 != null && results.selectOptionsList2.length > 0)
		{
			str += "<h4 style='margin-left: 28px;'>Booth Wise Voter Average Age Report</h4>";
			str += "<div class='form-horizontal boothResults ' name='boothSelection' style='display:block;margin: 30px 30px;'>";

			str += "<table class='table table-bordered' style='margin-bottom: -8px; margin-top: -20px;style='font-size:small'>";
			str += '<tr>';
			str += '<th>Mandal/Muncipality</th>';
			str += '<th>Booth</th>';
			str += '<th>Total Voters</th>';
			str += '<th>Average Voters Age</th>';
			str += '</tr>';
			for(var i in results.selectOptionsList2)
			{
				if(results.selectOptionsList2[i]!= null)
				{
					str += "<tr>";
					str += "<td rowspan='"+results.selectOptionsList2[i].selectOptionsList.length+"'>"+results.selectOptionsList2[i].name+"</td>";
					for(var j in results.selectOptionsList2[i].selectOptionsList)
					{
						if(results.selectOptionsList2[i].selectOptionsList[j] != null)
						{
							if(j > 0)
							{
								str += "<tr>";
							}
							str += "<td><span>Booth - "+results.selectOptionsList2[i].selectOptionsList[j].name+"</span></td>";
							str += "<td><span>"+results.selectOptionsList2[i].selectOptionsList[j].totalCount+"</span></td>";
							str += "<td><span>"+results.selectOptionsList2[i].selectOptionsList[j].perc+"</span></td>";
							if(j > 0)
							{
								str += "</tr>";
							}
						}
					}	
					str += "</tr>";
				}
			
			}
			str += "</table>";

			str += "</div>";	
		}
		
		str += "</div>";
		str += "</div>";

document.getElementById('innerDiv').innerHTML = str;
$("#innerDiv").show();

window.open(results.url);
}

</script>
</BODY>
</HTML>
