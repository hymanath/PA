<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=utf-8" %>

<html>
 <head>
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
<title> Debate Report </title>
</head>
<body>
<style>
</style>
<script>
var debateId = '${debateId}';

getSelectedDebate();

function getSelectedDebate()
{
	var jsObj = {
				debateId :debateId,
				task : "getDebateDetails"	
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "retriveDebateDetailsAction.action?"+rparam;
		callAjax(jsObj,url);
}

function generateDebateReport(result)
{
	var str = '';
	str += '<div>';
	
		str += '<div>';
		str += '<p class="boxHeading">Subject : ';
		for(var m in result.debateNames)
		{
			str += ''+result.debateNames[m]+'';
		}
		str += '</div>';
		str += '<div class="row">';
		str += '<div class="span3 ">';
		str += 'Channel Name : '+result.channelName+'</div>';
		str += '<div class="span3 ">';
		str += 'Observer : ';
		for(var j in result.observorsList)
		{
			str +=''+result.observorsList[j]+'';
		}
		str += '</div>';
		str += '<div class="span3 ">Date : '+result.date+'</div>';
		
	
		str += '<div class="span3 ">';
		if(result.telecastTime == 'Prime')
		{
			str += 'Telecast Time : '+result.startTime+'  to '+result.endTime+'  ';
		}
		else
		{
			str += 'Telecast Time : '+result.startTime+'  to '+result.endTime+'  ';
		}
		str += '</div>';	
		str += '</div>';	
		
		str += '<div>';
		str += '<table class="table table-bordered table-hover">';
		str += '<tr>';
		str += '<th style="width: 234px;">Party</th>';
		for(var a in result.participantsList)
		{
			str += '<th>'+result.participantsList[a].partyName+'</th>';
		}
		str += '</tr>';
		
		str += '<tr>';
		str += '<th style="width: 234px;">Leader Name</th>';
		for(var a in result.participantsList)
		{
			str += '<th>'+result.participantsList[a].name+'</th>';
		}
		str += '</tr>';
		var size = '';
		var noOfParticepents = result.participantsList.length;
		for(var i in result.participantsList)
		{
			size = result.participantsList[i].scaleList.length;
		}
		for(var a = 0 ; a < size ; a++)
		{
			str += '<tr>';
			str += '<th style="width: 234px;">'+result.participantsList[0].scaleList[a].name+'</th>';	
			for(var i = 0 ; i<noOfParticepents ; i++)
			{
				str += '<td>'+result.participantsList[i].scaleList[a].perc+'</td>';	
			}
			str += '</tr>';
		}
		str += '<tr>';
		str += '<th style="width: 234px;">What was the role played  by the individual in over all programme(C.P/Critic/Philosopher/Bully/Nurse)</th>';
		for(var i in result.participantsList)
		{
			str += '<td>'+result.participantsList[i].prtiRoles+'</td>';
		}
		str += '</tr>';

		str += '<tr>';
		str += '<th style="width: 234px;">What was the role expected from TDP leader in this program!</th>';
		var count = 0;
		var expRole = '';
		for(var i in result.debateExpRolesList)
		{
			count ++ ;
			if(count == 1)
			{
				expRole += ''+result.debateExpRolesList[i].location+'';
			}
			else
			{
				expRole += '+' + ''+result.debateExpRolesList[i].location+'';
			}
		}
		str += '<td colspan='+noOfParticepents+'>'+expRole+'</td>';
		str += '</tr>';
		
		
		for(var i in result.questionAnswersList)
		{
			str += '<tr>';
			str += '<th style="width: 234px;">'+result.questionAnswersList[i].location+'</th>';
			str += '<td colspan='+noOfParticepents+'>'+result.questionAnswersList[i].name+'</td>';
			str += '</tr>';
		}
		
		str += '<tr>';
		str += '<th style="width: 234px;">Summary of performence</th>';
		str += '<td colspan='+noOfParticepents+'>'+result.debateSummery+'</td>';
		str += '</tr>';
		
		var smsStr = '';
		smsStr += '<p>'+result.smsPoleList[0].name+'</p>';
		smsStr += '<p>';
		for(var i in result.smsPoleList)
		{
			smsStr += ''+result.smsPoleList[i].type+':'+result.smsPoleList[i].perc+''
		}
		smsStr += '<p>';
		str += '<tr>';
		str += '<th style="width: 234px;">Sms Poll Question and Results</th>';
		str += '<td colspan='+noOfParticepents+'>'+smsStr+'</td>';
		str += '<tr>';
		str += '</div>';
		str += '</table>';
	str += '</div>';
	$('#debateDetails').html(str);
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
				if (jsObj.task == "getDebateDetails")
				{
					generateDebateReport(myResults);	
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

<div id="debateDetails" style="background-color: white;font-size: 17px;font-weight: bold;line-height: 1.5;"></div>

 </body>
