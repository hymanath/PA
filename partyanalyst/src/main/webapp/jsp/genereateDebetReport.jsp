<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=utf-8" %>

<html>
 <head>
 <title> Debate Report </title>
 <link rel="icon" type="image/png" href="https://mytdp.com/dist/img/logo.png">
 	<!--Bootstrap styles file-->
	<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet">
	<link href="newCoreDashBoard/Plugins/Rating/bootstrap-rating.css" type="text/css" rel="stylesheet"/>
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/loginpopup.js"> </script>
	<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>
	<script type="text/javascript" src="js/jquery.google.api/jquery.min.js"></script>
	<!--Script file-->
	<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
	<script src="newCoreDashBoard/js/bootstrap.min.js"></script>
	<script src="newCoreDashBoard/Plugins/Rating/bootstrap-rating.js" type="text/javascript"></script>

<style type="text/css">
.spinner {
  margin: 30px auto;
  width: 40px;
  height: 40px;
  position: relative;
  text-align: center;
  
  -webkit-animation: sk-rotate 2.0s infinite linear;
  animation: sk-rotate 2.0s infinite linear;
}

.dot1, .dot2 {
  width: 60%;
  height: 60%;
  display: inline-block;
  position: absolute;
  top: 0;
  background-color: #1ABC9C;
  border-radius: 100%;
  
  -webkit-animation: sk-bounce 2.0s infinite ease-in-out;
  animation: sk-bounce 2.0s infinite ease-in-out;
}

.dot2 {
  top: auto;
  bottom: 0;
  -webkit-animation-delay: -1.0s;
  animation-delay: -1.0s;
}

@-webkit-keyframes sk-rotate { 100% { -webkit-transform: rotate(360deg) }}
@keyframes sk-rotate { 100% { transform: rotate(360deg); -webkit-transform: rotate(360deg) }}

@-webkit-keyframes sk-bounce {
  0%, 100% { -webkit-transform: scale(0.0) }
  50% { -webkit-transform: scale(1.0) }
}

@keyframes sk-bounce {
  0%, 100% { 
    transform: scale(0.0);
    -webkit-transform: scale(0.0);
  } 50% { 
    transform: scale(1.0);
    -webkit-transform: scale(1.0);
  }
}
.table tr td
{
	min-width:115px !important;
	font-weight:400 !important;
}
/*.rating-symbol-foreground , .rating-symbol-background
{
	color:#1ABC9C
}*/
.labelCustom
{
	margin-left:5px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading" style="background-color:#CCC">
						<h4 class="panel-title"><span class="subName"></span> <small>(<span class="debateDate"></span><span class="debateTime"></span>)</small></h4>
					</div>
					<div class="panel-body">
						<div id="debateDetails"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

<script>
var debateId = '${debateId}';

getSelectedDebate();

function getSelectedDebate()
{
	var stateId =0;
	$("#debateDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jsObj = {
				debateId :debateId,
				stateId :stateId,
				task : "getDebateDetails"	
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "retriveDebateDetailsAction.action?"+rparam;
		callAjax(jsObj,url);
}


function generateDebateReport(result)
{
	var str = '';
		for(var m in result.debateNames)
		{
			$(".subName").html(result.debateNames[m]);
		}
		str +='<h5><b>Channel Name</b> : '+result.channelName+'&nbsp;&nbsp;&nbsp;';
		for(var j in result.observorsList)
		{
			str +='<b>Observer</b> : '+result.observorsList[j]+'</h5>';
		}
		
		$(".debateDate").html(result.date);
		if(result.telecastTime == 'Prime')
		{
			 $(".debateTime").html(' ('+result.startTime+' to '+result.endTime+')');
		}
		else
		{
			$(".debateTime").html(' ('+result.startTime+' to '+result.endTime+')');
		}
		str += '<div class="table-responsive">';
		str += '<span class="pull-right">(5 points scale : 0 Poor - 5 Excellent)</span>';
		str += '<table class="table table-bordered table-hover">';
		str += '<tr style="background-color:#f2f2f2">';
		str += '<th style="width: 234px;">PARTY</th>';
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
			str += '<th style="width: 234px;">'+result.participantsList[0].scaleList[a].name.split("(")[0]+'</th>';	
			for(var i = 0 ; i<noOfParticepents ; i++)
			{
				str += '<td><input class="performanceRating" value="'+result.participantsList[i].scaleList[a].perc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom"  data-readonly>'+result.participantsList[i].scaleList[a].perc+'</span></td>';
			}
			str += '</tr>';
		}
		
		str += '<tr>';
		str += '<th style="width: 234px;">Total</th>';
		for(var i  in result.participantsList )
		{
			str += '<td>'+result.participantsList[i].perc+'</td>';
		}
		str += '</tr>';
		str += '<tr>';
		str += '<th style="width: 234px;">What was the role played  by the individual in over all programme(C.P/Critic/Philosopher/Bully/Nurse)</th>';
		for(var i in result.participantsList)
		{
			str += '<td>'+result.participantsList[i].prtiRoles+'</td>';
		}
		str += '</tr>';

		
		
		if(result.noTdpLeaders > 1)
		{
			str += '<tr>';
			str += '<th style="width: 234px;">What was the role expected from TDP leader in this program!</th>';
			for(var i in result.participantsList)
			{
				if(result.participantsList[i].expRoles == null)
				{
					str += '<td></td>';
				}
				else
				{
					str += '<td>'+result.participantsList[i].expRoles+'</td>';
				}
				
			}
			str += '</tr>';
		}
		else
		{
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
		}
		
		
		
		
		for(var i in result.questionAnswersList)
		{
			str += '<tr>';
			str += '<th style="width: 234px;">'+result.questionAnswersList[i].location+'</th>';
			str += '<td colspan='+noOfParticepents+'>'+result.questionAnswersList[i].name+'</td>';
			str += '</tr>';
		}
		var summaryLength = result.candidateSummery.length + 1;
		str += '<tr>';
		str += '<th style="width: 234px;" rowspan='+summaryLength+'>Summary of performence</th>';
		str += '<td colspan='+noOfParticepents+'>'+result.debateSummery+'</td>';
		str += '</tr>';
		for(var n in result.candidateSummery)
		{
			str += '<tr>';
			str += '<td colspan='+noOfParticepents+'><b>'+result.candidateSummery[n].location+' , '+result.candidateSummery[n].type+' - </b>'+result.candidateSummery[n].name +'</td>';
			str += '</tr>';
		}
		
		if(result.smsPoleList[0].name != "")
		{
			var smsStr = '';
			smsStr += '<p>'+result.smsPoleList[0].name+'</p>';
			smsStr += '<p>';
			for(var i in result.smsPoleList)
			{
				smsStr += ''+result.smsPoleList[i].type+' : '+result.smsPoleList[i].perc+'%&nbsp;&nbsp;'
			}
			smsStr += '<p>';
			str += '<tr>';
			str += '<th style="width: 234px;">Sms Poll Question and Results</th>';
			str += '<td colspan='+noOfParticepents+'>'+smsStr+'</td>';
			str += '<tr>';
		}
		
		
		str += '</div>';
		str += '</table>';
	str += '</div>';
	$('#debateDetails').html(str);
	$(".performanceRating").rating({
		showClear: false,
		showCaption:false,
		hoverOnClear: true,
		animate:false
	});	
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
 </body>
