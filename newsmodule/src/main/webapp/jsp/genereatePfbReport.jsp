<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PFB Report</title>
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
	<!--Bootstrap styles file-->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/loginpopup.js"> </script>
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
	<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>
	<script type="text/javascript" src="js/jquery.google.api/jquery.min.js"></script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"> </script>
	<!--Script file
	<script type="text/javascript" src="js/jquery.js"></script>-->
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
	<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<link href="Green/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="Green/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"/>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>

</head>
<body>
<div id='mainDiv' style="background-color: white;font-size: 15px;font-weight: bold;line-height: 1.5;">
<div id="politicalFeedBackResultDiv" ></div>
</div>
<script>
var id = '${debateId}';
var date = '${fromDate}';
var key  = '${key}';
getPoliticalFeedBack();

function getPoliticalFeedBack()
{
		var jsObj =
		{ 
			constituencyId : id,
			date  : date,
			key   : key,
			task:"getPoliticalFeedBack"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPoliticatFeedBackAction.action?"+rparam;						
		callAjax(jsObj,url);
	
}

function deleteRecord()
{
	var jsObj =
		{ 
			key : key,
			task:"deleteRecord"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "deltePfbReportDetailsAction.action?"+rparam;						
		callAjax(jsObj,url);
}
function buildPoliticalFeedBack(result)
{
	deleteRecord();
	var str = '';
	for(var i in result)
	{
		/* str += '<div style="float:right;">';
		str += '<a class="btn btn-success" style="margin-right: 15px;" onClick="editSelectedPoliticalFeedBack('+result[i].id+')">Edit</a>';
		str += '<a class="btn btn-success" onClick="deletedPoliticalFeedBack('+result[i].id+')" style="margin-right: 15px;">Delete</a>';
		str += '<a class="btn btn-success" onClick="generateUrl('+result[i].id+')">Generate Url</a>';
		str += '</div>'; */
		str += '<legend class="boxHeading"> Parliment Wise Daily Political Feedback </legend>';
		str += '<div class="row-fluid">';
		str += '<table class="table table-bordered ">';
		str += '<tr>';
		str += '<th rowspan = 2>P_CODE</th>';
		str += '<th rowspan = 2>PARLIMENT_NAME</th>';
		str += '<th colspan=2 style="text-align: center;" >ACTION ITEMS</th>';
		str += '<th rowspan = 2>SUMMARY</th>';
		str += '</tr>';
		str += '<tr>';
		str += '<th>ACTION ITEM</th>';
		str += '<th>SOURCE</th>';
		str += '</tr>';
		var length = result[i].actionItemsList.length;
		str += '<tr>';
		str += '<td rowspan='+length+'>'+result[i].constituencyNo+'</td>';
		str += '<td rowspan='+length+'>'+result[i].parlimentName+'</td>';
		for(var j in result[i].actionItemsList)
		{
			if(j  != 0)
			{
				str += '<tr>';
			}
			if(result[i].actionItemsList[j].name != null)
			{
				str += '<td>'+result[i].actionItemsList[j].name+'</td>';
				str += '<td>'+result[i].actionItemsList[j].location+'</td>';
			}
			else
			{
				str += '<td></td>';
				str += '<td></td>';
			}
			if(j  != 0)
			{
				str += '</tr>';
			}
			if(j == 0)
			{
				str += '<td rowspan='+length+'>'+result[i].summary+'</td>';
				str += '</tr>';
			}
		}
		
		str += '</table>';
		str += '</div>';
		str += '<legend class="boxHeading"> Assembly Wise Daily Political Feedback </legend>';
		str += '<div class="row-fluid">';
		str += '<table class="table table-bordered ">';
		str += '<tr>';
		str += '<th>C_CODE</th>';
		str += '<th>CONSTITUENCY_NAME</th>';
		str += '<th>IMP NEWS</th>';
		str += '<th>SOURCE</th>';
		str += '<th>CM POLITICAL FEEDBACK</th>';
		str += '<th>SOURCE</th>';
		str += '<th>OTHER POLITICAL FEEDBACK</th>';
		str += '<th>SOURCE</th>';
		str += '</tr>';
		for(var j in result[i].politicalFeedBackVOList)
		{
			str += '<tr>';
			str += '<td>'+result[i].politicalFeedBackVOList[j].constituencyNo+'</td>';
			str += '<td>'+result[i].politicalFeedBackVOList[j].assemblyName+'</td>';
			if(result[i].politicalFeedBackVOList[j].impNews != null)
			{
				str += '<td>'+result[i].politicalFeedBackVOList[j].impNews+'</td>';
			}
			else
			{
				str += '<td></td>';
			}
			if(result[i].politicalFeedBackVOList[j].impSource != null)
			{
				str += '<td>'+result[i].politicalFeedBackVOList[j].impSource+'</td>';
			}
			else
			{
				str += '<td></td>';
			}
			if(result[i].politicalFeedBackVOList[j].cmPoliticalFeedBack != null)
			{
				str += '<td>'+result[i].politicalFeedBackVOList[j].cmPoliticalFeedBack+'</td>';
			}
			else
			{
				str += '<td></td>';
			}
			if(result[i].politicalFeedBackVOList[j].cmSource != null)
			{
				str += '<td>'+result[i].politicalFeedBackVOList[j].cmSource+'</td>';
			}
			else
			{
				str += '<td></td>';
			}
			if(result[i].politicalFeedBackVOList[j].otherPoliticalFeedBack != null)
			{
				str += '<td>'+result[i].politicalFeedBackVOList[j].otherPoliticalFeedBack+'</td>';
			}
			else
			{
				str += '<td></td>';
			}
			if(result[i].politicalFeedBackVOList[j].otherSource != null)
			{
				str += '<td>'+result[i].politicalFeedBackVOList[j].otherSource+'</td>';
			}
			else
			{
				str += '<td></td>';
			}
			str += '</tr>';
		}
		
		str += '<table>';
		str += '</div>';
	}
	$('#politicalFeedBackResultDiv').html(str);
}
function callAjax(jsObj,url)
	{

	var callback =
	{			
		success : function( o )
		{
			try
			{ 
				myResults = YAHOO.lang.JSON.parse(o.responseText); 
				if (jsObj.task == "getPoliticalFeedBack")
				{
					buildPoliticalFeedBack(myResults);	
				}
			}catch(e)
			{   
			 $("#submitDataImg").hide();
			}  
		},
		scope : this,
		failure : function( o )
		{
			
		}
	};

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
</script>
</body>
</html>