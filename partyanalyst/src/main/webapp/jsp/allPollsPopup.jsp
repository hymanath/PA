
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="keywords" content="Opinion polls on politics ,Polls result" />

<title>Openion Polls On Political</title>

	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">

	<!--BOOT STRAP START-->	
	
	<!--<link href="css/Assets/css/bootstrap-responsive.css" rel="stylesheet">-->

	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="Assets/ico/apple-touch-icon-114-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72" href="Assets/ico/apple-touch-icon-72-precomposed.png">
	<link rel="apple-touch-icon-precomposed" href="Assets/ico/apple-touch-icon-57-precomposed.png">


   <!--BOOT STRAP END-->

	<!-- YUI Dependency files (End) -->
	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<style type="text/css">
	
	#pollsDiv
	{
		text-align:left;		
		margin-top:40px;
		margin-left:40px;
		font-family:verdana;
		font-weight:bold;
		color:#0C67AC;
		text-align:left;
		font-size:13px;
	}
	#headingDiv
	{
		color:#247CD4;
		font-size:19px;
		font-weight:bold;
		text-align:center;
		padding-top:30px;
	}
/*	body
	{
		background-color:#EAEDEF;
		direction:ltr;
		font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
		font-size:11px;
		margin:0;
		padding:0;
	}*/
	#pollsDivBody
	{
		padding:10px;
	}
	#pollsDivBody table
	{
		width:52%;
	    margin-bottom: 24px;
        margin-top: -29px;
	}
	
	.span8-cuswidth{width:940px;}
	.span4-cuswidth{width:270px;height:360px;margin-top:3px;display:table-cell;}
	/*.pollquestion{width:100%;padding:5px;margin:0px 5px 0px 0px;background:#fff;}*/
	</style>
<script type="text/javascript">
	function getCompletePollResult(questionId){  
		var browser1 = window.open("completeResultForAPollAction.action?questionId="+questionId,"completeResultForAPoll","scrollbars=yes,height=350,width=450,left=200,top=200");
		browser1.focus();
	}
</script>
<script type="text/javascript"> 

function initializeResultsTable() {
	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("pollsDataTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	
	resultsDataSource.responseSchema = {
		fields : [ { 		
			key : "startDate",formatter:YAHOO.widget.DataTable.formatDate
		}, {
			key : "question"
		}, {
			key : "",formatter:YAHOO.widget.DataTable.formatLink
		}]
	};

	var resultsColumnDefs = [ {
		key : "startDate",
		label : "Poll Date",
		sortable : true
	}, {
		key : "question",
		label : "Question",
		sortable : true
	}, {
		key : "",
		label : "More Details..",
		sortable : true	
	}];

	
	var myDataTable = new YAHOO.widget.DataTable("pollsDivBody",resultsColumnDefs, resultsDataSource);  
	
}

</script>

<style>
.pollDiv{
background:#FFFFFF;
margin-right:12px;
position:relative;
margin-bottom:2px;
}
.qstnDiv{
margin-bottom:2px;

}
.totlaVotes{
	margin-bottom:5px;
	
}
.buttonsDiv{
	bottom:0;
	position:relative;
	width:281px;
	margin-bottom:3px;
	display:block;
	margin:20px 0px;
}

.opinionpoll .question{padding:5px;border-bottom:1px dashed #ccc;font:13px Arial;}
/*.opinionpoll .answer{border-bottom:1px dashed #ccc;padding:0px ;margin-bottom:5px;}*/
.opinionpoll .votebtn{margin:0px auto;display:block;width:75px;}
.resultdisplay a{display:inline-block;text-decoration:none;}
.resultdisplay .previouslink{ float:left;}
.resultdisplay .nextlink{ float:right;}
/*.opinionpoll>li:nth-child(4n+4){clear:left;}*/
.opinionpoll>li:nth-child(even){background: rgb(255,255,255); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(255,255,255,1) 0%, rgba(243,243,243,1) 50%, rgba(237,237,237,1) 51%, rgba(255,255,255,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(50%,rgba(243,243,243,1)), color-stop(51%,rgba(237,237,237,1)), color-stop(100%,rgba(255,255,255,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(255,255,255,1) 0%,rgba(243,243,243,1) 50%,rgba(237,237,237,1) 51%,rgba(255,255,255,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(255,255,255,1) 0%,rgba(243,243,243,1) 50%,rgba(237,237,237,1) 51%,rgba(255,255,255,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(255,255,255,1) 0%,rgba(243,243,243,1) 50%,rgba(237,237,237,1) 51%,rgba(255,255,255,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(255,255,255,1) 0%,rgba(243,243,243,1) 50%,rgba(237,237,237,1) 51%,rgba(255,255,255,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='#ffffff',GradientType=0 ); /* IE6-9 */
}



.main-title-sec {
  background-color:#06ABEA;
  background-position:initial initial;
  background-repeat:initial initial;
  border-bottom-left-radius:0;
  border-bottom-right-radius:0;
  border-top-left-radius:5px;
  border-top-right-radius:5px;
  display:table;
  height:36px;
  margin-left:auto;
  margin-right:auto;
  float:none;
  width:890px;
 
  margin-bottom:10px;

}

.main-mbg {
  color:#FFFFFF;
  display:table-cell;
  font-family:'Trebuchet MS', Arial, Helvetica, sans-serif;
  font-size:14px;
  font-style:normal;
  font-variant:normal;
  font-weight:bold;
  height:25px;
  line-height:normal;
  padding-left:10px;
  text-transform:uppercase;
 text-align:center;
  padding-top:9px;
}



</style>
</head>
<body class="background">

<div class="main-title-sec">
        <div class="main-mbg">Recent polls details</div></div>
        <div class="main-bbg"></div>
      </div>
<div class="container">
<div class="span8-cuswidth">


  <ul class="unstyled row opinionpoll">

		 <c:forEach var="polls" varStatus="stat" items="${qstnLst}">

		   <li class="span4-cuswidth breadcrumb pollDiv" >

		     <p class="qstnDiv question"><b>${polls.question}</b></p>

		     <p class="totlaVotes pull-right">Total Votes  Polled:<b> ${polls.totalVotesObtainedForPoll}</b></p>

		      <ul class="answer">
			 <c:forEach var="polls1" varStatus="stat" items="${polls.options}">			
               <li >
				  <h5 class="span3" > ${polls1.option} </h5>

				  <div class="span2 pull-left" style="margin-left:0px;">
						<div class="progress" style="margin:0px;">
						  <div id="option1" class="bar" style="width:${polls1.percentage}%"></div>
						</div>							
				  </div>	
					
				  <span class="label pull-right label-info">${polls1.percentage}% </span>	
				  </li>

			 </c:forEach>
			 </ul>

			<div class="buttonsDiv pager" style="margin-left:-9px;">
				  <a href="completeResultForAPollAction.action?questionId=${polls.questionId}&comments=getComments"  id="${polls.questionId}" class="btn" title="Post Your Comment On This Poll" >Post Your Comment </a>
				  <a href="completeResultForAPollAction.action?questionId=${polls.questionId}&comments=getComments" id="${polls.questionId}" class="btn" title="View All Comments On Thios Poll">View Comments</a>
			 </div>
					
		   </li>
			  
		 </c:forEach>
  </ul>	
</div>
</div>

	
	<!--	<div id="headingDiv">
			All Polls
		</div>
		<center style="margin-top:30px;">
		<div id="polls_body" class="yui-skin-sam">							
				<div id="pollsDivBody">
					<table id="pollsDataTable">						
				      <c:forEach var="polls" varStatus="stat" items="${allPolls}">
				      	<tr>
				      		<td style="padding-right:40px;">
				      		 	  ${polls.startDate}
				      		</td>
				      		<td style="padding-right:40px;">
				      		 	  ${polls.question}
				      		</td>
				      		<td>
				      			<a href="completeResultForAPollAction.action?questionId=${polls.questionId}" style="text-align:right;margin-top:15px;text-decoration:underline;"> click here</a>
				      		</td>
				      	</tr>		
				      </c:forEach>
				     </table>
				</div>
		</div>
		</center>
<script language="javascript">
initializeResultsTable();
</script>
-->
</body>
</html>