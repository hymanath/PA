<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="keywords" content="Opinion polls on politics ,Polls result" />
<title>${questionsOptionsVO.question}</title>
<!-- YUI Dependency files (Start) -->

	

<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
  <script src="styles/assets/js/jquery.js"></script>

<script src="styles/assets/js/bootstrap-dropdown.js"></script>
<script src="styles/assets/js/bootstrap-tab.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/rating/jquery.rateit.js" type="text/javascript"></script>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<link href="styles/rating/rateit.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/fancybox/jquery.mousewheel-3.0.4.pack.js">
	</script>
	<script type="text/javascript" src="js/fancybox/jquery.fancybox-1.3.4.pack.js">
	</script>

	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">

	<!--BOOT STRAP START-->	
	
	<!--<link href="css/Assets/css/bootstrap-responsive.css" rel="stylesheet">-->

	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="Assets/ico/apple-touch-icon-114-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72" href="Assets/ico/apple-touch-icon-72-precomposed.png">
	<link rel="apple-touch-icon-precomposed" href="Assets/ico/apple-touch-icon-57-precomposed.png">


   <!--BOOT STRAP END-->

   <script type="text/javascript" src="js/homePage/homePage.js"> </script>

	<!-- YUI Dependency files (End) -->
	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<style type="text/css">

	/*body
	{
		background-color:#EAEDEF;
		direction:ltr;
		font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
		font-size:11px;
		margin:0;
		padding:0;
	}*/
	
	.heading
	{
		font-weight:bold;
		font-family:verdana;
		color:#003399;
		padding-top:4px;
		padding-left:10px;
		padding-right:10px;
		padding-top:4px;
		padding-bottom:4px;
	}
	#pollsTable
	{
		margin-left:40px;
		margin-top:20px;
	}
	.cellStyle
	{
		padding-left:10px;
		padding-right:10px;
		padding-top:4px;
		padding-bottom:4px;
		font-weight:normal;
	}
	#contenttable{width:980px;margin-right:auto;margin-left:auto;margin-bottom:5px;}
	#contenttable table{background:#ffffff;margin-right:auto;margin-left:auto;padding-bottom:10px;} 


	.pollquestion{width:100%;padding:5px;margin:0px 5px 0px 0px;background:#fff;}
	
.opinionpoll{width:80%;margin:0px auto;border-top-width:12px;border-bottom-width:6px;margin-top:35px;}
.opinionpoll .question{padding:10px 5px;border-bottom:1px dashed #ccc;font:13px Arial;}
.opinionpoll .answer{border-bottom:1px dashed #ccc;padding:0px ;margin-bottom:5px;}
.opinionpoll .votebtn{margin:0px auto;display:block;width:75px;}
.resultdisplay a{display:inline-block;text-decoration:none;}
.resultdisplay .previouslink{ float:left;}
.resultdisplay .nextlink{ float:right;}
#resultsDiv li{display:block;clear:both;margin:5px 10px;padding:5px;float:right:width:75%;}
#resultsDiv li h5{display:inline-block;float:left;width:150px;text-align:right;font-family:verdana;}
#resultsDiv li span{float:left;margin-left:10px;}
		
	.comments{position: relative; display: table; width: 780px; margin: 10px auto;background:#fff;}	
	.comments h2{padding:10px;}
	.commentsec{padding:10px; box-shadow:0px 2px 2px #ddd;margin:6px 5px;border:1px solid #CCC;border-radius:3px;}
	
	.abs{position:relative;}
	.abs textarea{background:#fff;border:1px solid #CCC !important;width:680px;}
	.abs .emsg{background:#ff0000;color:#fff;position:absolute;padding:10px;top:-40px;left:15px;display:none;}
	.abs .emsg:after{content:" "; border:5px solid transparent;position:absolute;border-top:5px solid #ff0000;border-right:5px solid #ff0000;top:100%;left:50%;}


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
  width:890px;
  margin-left:52px;
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
 text-align:left;
  padding-top:9px;
  padding-left:31px;
}
.pull-left abs{margin-bottom:15px;}
	
</style>
  
<script type="text/javascript">

google.load("visualization", "1", {packages:["corechart"]});
</script>
<script type="text/javascript"> 
$(document).ready(function(){

	var userId = '${sessionScope.USER.registrationID}';

  if(userId != null && userId.length > 0)
  {
  var firstName = '${sessionScope.USER.firstName}';
  var lastName = '${sessionScope.USER.lastName}';
  
  $("#lasttNmId").val(lastName);
  $("#fisrtNmId").val(firstName);
  
  $("#lasttNmId").attr("disabled", "disabled"); 
  $("#fisrtNmId").attr("disabled", "disabled"); 
	
  }
  else{
  
	placeDefaultValueForFirstName("fisrtNmId");
	placeDefaultValueForLarstName("lasttNmId");
	
  }
});


var constituencies = [];

var pollStatus = [];


function opinionPollChart()
{
	
var arrData = pollStatus;
var data = new google.visualization.DataTable();
data.addColumn('string','option');
data.addColumn('number','votesObtained');
		
data.addRows(arrData.length);

for(var j=0; j<arrData.length; j++)
		{
			
			data.setValue(j,0,arrData[j].option);
			data.setValue(j,1,arrData[j].votesObtained);
			
		}
			var chart = new google.visualization.PieChart(document.getElementById('chartDiv'));
	
chart.draw(data,{width: 400, height: 280,legend:'right', 
legendTextStyle:{fontSize:12},title:'${questionsOptionsVO.title}',titleTextStyle:{fontName:'verdana',fontSize:9}});



}

function initializeResultsTable() {
	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("pollsDataTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	
	resultsDataSource.responseSchema = {
		fields : [ { 		
			key : "option"
		}, {
			key : "votesObtained"
		}, {
			key : "percentage"
		}]
	};
	
	var resultsColumnDefs = [ {
		key : "option",
		label : "Option",
		sortable : false
	}, {
		key : "votesObtained",
		label : "Votes",
		sortable : false
	}, {
		key : "percentage",
		label : "Percentage",
		sortable : false	
	}];

	
	var myDataTable = new YAHOO.widget.DataTable("pollsDivBody",resultsColumnDefs, resultsDataSource);  
	
}


</script>

</script>
</head>

<body>






<div class="container" >


<!--for sharing on social networking sites START-->

<div style="position: relative; display: table; width: 780px; text-align: center; margin: 0px auto;">

<div class="main-title-sec">
        <div class="main-mbg">Leave your comment on this poll</div></div>
      
      </div>
<div style="margin-left:70px;">
<div id="alrdyVoted"></div>

<div id="pollSavedStatus" style="display:none;"></div>
</div>
  <div class="btn-group dropup pull-right" style="display:block;width:175px;margin:4px;margin-right:61px;">
          <button class="btn btn-primary"><i class="icon-share icon-white"></i> Share this Poll</button>
          <button data-toggle="dropdown" class="btn dropdown-toggle btn-primary" style="height:28px;"><span class="caret"></span></button>
          <ul class="dropdown-menu pull-right">
            <li><div style="padding-left:15px;"><a href="javascript:{}" onClick="shareInFacebook('www.partyanalyst.com/completeResultForAPollAction.action?questionId=${questionsOptionsVO.questionId}&comments=getComments}')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a></div></li>
			<li><div style="margin-left:31px;"><a href="https://twitter.com/share" class="twitter-share-button" data-url="www.partyanalyst.com/completeResultForAPollAction.action?questionId=${questionsOptionsVO.questionId}&comments=getComments">
				Tweet</a></div>
			<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
			</script> </li>
			<li><div style="margin-left:32px;margin-top: 5px;">
			<g:plusone size="medium"></g:plusone>

			<script type="text/javascript">
			 (function() {
			  var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
				 po.src = 'https://apis.google.com/js/plusone.js';
				 var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
			 })();
			</script>
		</div></li>
			<li class="divider"></li>            
          </ul>
      </div>


<!--for sharing on social networking sites END>-->
 <div class="pollquestion opinionpoll breadcrumb">

 
	<p class="question"><b>${questionsOptionsVO.question}</b> 
</p>
	<p class="pull-right">Total Votes Polled:<span class="badge badge-success"><b id="totalVotesId">${questionsOptionsVO.totalVotesObtainedForPoll}</b></span></p>
    
	    <ul id="resultsDiv">
	    <c:forEach var="polls" varStatus="stat" items="${questionsOptionsVO.options}">
		<li>

         <h5> ${polls.option} </h5>

		
		<div class="span2">

			<div class="progress" style="margin:0px;">
			  <div id="option1" class="bar" style="width:${polls.percentage}%"></div>
			</div>							
			</div>	

			<span class="label pull-right label-info">${polls.percentage}% </span>
	  
  </li>
     </c:forEach>
	 
</ul>
<div class="pager" style="margin-top:30px;display:block;clear:both;">
<c:if test="${questionsOptionsVO.pollExpire == false}">
		<c:if test="${opinionPollVO.avaliability != true }">

 <a id="voteNowId" href="javaScript:{voteNow();}" class="btn previouslink"   title="Click here To View Recent Poll Details">Vote Now 
 </a>
 </c:if> </c:if>
 <a href="getAllPollsAction.action" class="btn nextlink"  title="Click here To View Recent Poll Details">View Recent Polls 
 </a>
</div>  </div>
</div>

<div id="voteNow" style="display:none;">
        <c:if test="${questionsOptionsVO.pollExpire == false}">
		<c:if test="${opinionPollVO.avaliability != true }">

          <div id="pollDiv" style="background:#ffffff;margin:10px 0px 0px 10px;;border-radius:5px 5px 5px 5px;padding-top:10px;">

			<div class="span5">

			

				<!--<pre style="margin-top:5px;" class='alert'><b>${questionsOptionsVO.question}</b></pre>
				<pre class=" alert">Total Votes  Polled:<b> ${questionsOptionsVO.totalVotesObtainedForPoll}</b></pre>-->
				<h4>Q)${questionsOptionsVO.question}</h4>

				 <c:forEach  var="comment" varStatus="stat" items="${opinionPollVO.quesitons}">

				  <c:forEach  var="options" varStatus="stat" items="${comment.options}">

				  <c:if test='${stat.index == 0}'>
				    <label><input type="radio" name="pollradio"   checked
				     value="${options.optionId}"> </input>${options.option}  </label>
					</c:if>

					 <c:if test='${stat.index != 0}'>
				   <label> <input type="radio" name="pollradio"   
				     value="${options.optionId}"> </input>${options.option} </label>
					</c:if>
					
					<br>
			
				    </c:forEach>
				  </c:forEach>             
            </div>

			<div class="span5"><a  href="javaScript:{saveCurrentPollResult(${questionsOptionsVO.questionId});}"  class="pull-right btn btn-info" style="margin:6px 0px 7px 0px;" title="Click here to vote now">Vote Now</a></div>
			
			</div>
      </c:if></c:if>
</div>
</div>	
<div class="comments">

<h2 class="">Comments</h2>
   <div id="successDiv" style="height:25px;display:none;"></div>
         <div class="span9" style="padding-bottom: 30px;">

         <div class="span9" style="margin-bottom: 35px;">
           <div class="pull-left abs">
         <textarea id="userComment" style="border:1px solid #CCCCCC;margin-bottom:10px;" onClick="clearDefaultComment(this.id);" onBlur="placeDefaultValue(this.id);">Post your comment here ....</textarea><div id="commentErrMsg" class="emsg"></div>	
		 </div>	
	
			  <div class="pull-left abs">
			<input type="text" value='${sessionScope.USER.firstName}' id="fisrtNmId" onClick="clearDefaultFirstname(this.id);" onBlur="placeDefaultValueForFirstName(this.id);" /><div id="firstNmErrMsg" class="emsg"></div></div>
			
			<div class="pull-left abs">
			<input  style="margin-left:5px;" type="text" value='${sessionScope.USER.firstName}' id="lasttNmId" onClick="clearDefaultLastname(this.id);" onBlur="placeDefaultValueForLarstName(this.id);"/><div id="lastNmErrMsg" class="emsg"></div></div>
		
		
		<div class="pull-right">
		<a class="btn btn-success" href="javaScript:{validateCommentFields();}" title="Click Here To Post The Comment">Post Comment</a>
		
		</div>

		</div>

		 <div id="commentsDiv"></div>
	<c:forEach  var="comment" varStatus="stat" items="${commentList}">
	
	<div class='span9 commentsec'>

		 <div style="margin-bottom:8px;">${comment.comment}</div>
		 <span class="label label-info">Posted By: ${comment.firstName} ${comment.lastName}  </span>	
		 <span id="abuseSuccess${comment.commentId}"></span>	 
		
			

		<a class="label label-important pull-right"  style="margin-top:3px;margin-left:280px;" title="Click Here To Abuse This Comment" onClick="callAjaxToAbuse(${comment.commentId});" id="${comment.commentId}">Report Abuse</a>
   </div>
	
	 </c:forEach>
	 </div>
		
</div>
	<!--<table>
		<tr>
			<td valign="top" align="left">
				<table>
					<tr>
						<td style="color:#0C67AC;font-weight:bold;padding-top:40px;">
							Q) ${questionsOptionsVO.question}
						</td>						
					</tr>					
				</table>
				
							<table style="margin-top:10px;" >
								<tr>
									<td style="font-weight:bold;padding-bottom:20px;">
										 Total Votes Polled: ${questionsOptionsVO.totalVotesObtainedForPoll}
										 
										<div id="polls_body" class="yui-skin-sam">
											<div id="pollsDivBody">				
													<table   id="pollsDataTable">	
														 <c:forEach var="polls" varStatus="stat" items="${questionsOptionsVO.options}">
															 <tr>
									      						<td class="cellStyle">
									      		 	  				${polls.option}
									      						</td>
									      						<td class="cellStyle">
									      		 	  				${polls.votesObtained}
									      						</td>
									      						<td class="cellStyle">
									      		 	  				${polls.percentage} 
									      						</td>
									      					</tr>
														 </c:forEach>
													</table>		
											</div>
										</div>
									</td>
								</tr>
							</table>
			</td>	
			<td align="right" valign="top">
				<table style="margin-left:-58px;margin-top:37px;">
						<tr>
							<td>
								<div id="chartDiv" style="width: 400px; height: 500px;"></div>				
							</td>
						</tr>
				</table>
			</td>		
		</tr>
		<tr>
		<div id="pollsWidgetBody"></div>
</tr>

</table>-->
		
<!--<div>
<textarea id="commentId" style="background:#FFFFFF;margin-left:100px;height:28px;"></textarea>
<input type="button" value="submit" onClick="saveComment();"/>
</div>-->
<script language="javascript">

$('.abs').click(function(){

$(this).find(".emsg").hide();

});
function validateCommentFields(){

	$('#commentErrMsg').html('');
	$('#firstNmErrMsg').html('');
	$('#lastNmErrMsg').html('');

	var errorExist=false;

	var str='';

    if($('#userComment').val() == "Post your comment here ...." || $('#userComment').val() == ""){

		$('#commentErrMsg').html('<span style="font-size:12px;"><b>Please enter a comment </b></span>');
		//str+='Comment Required';
		errorExist = true;
		$('#commentErrMsg').show();
	}
	if($('#fisrtNmId').val() == "First Name" || $('#fisrtNmId').val() == ""){

		//str+='FirstName  Required';		

$('#firstNmErrMsg').html('<span style="font-size:12px;"><b>First Name is required</b></span>');
		errorExist = true;
		$('#firstNmErrMsg').show();
	}
	if($('#lasttNmId').val() == "Last Name" || $('#lasttNmId').val() ==""){

$('#lastNmErrMsg').html('<span style="font-size:12px;"><b>Last Name is required</b></span>');

		//str+='LastName Required';	
$('#lastNmErrMsg').show();
		errorExist = true;
	}

	if(errorExist == true){
		errorExist = false;	
		
	}else{

saveComment();
	}
}

function clearDefaultComment(id){

	var defaultValue = document.getElementById(id).value;

	if(defaultValue == "Post your comment here ....")
        document.getElementById(id).value="";
	
}

function placeDefaultValue(id){

	var defaultValue = document.getElementById(id).value;
	if(defaultValue == "")
		document.getElementById(id).value = "Post your comment here ....";
	else
		$('#commentErrMsg').html('');	

}

function clearDefaultFirstname(id){

	
	var defaultValue = document.getElementById(id).value;
	if(defaultValue == "First Name"){

        document.getElementById(id).value="";
	}
}
function placeDefaultValueForFirstName(id){
	var defaultValue = document.getElementById(id).value;

	if(defaultValue == "")
        document.getElementById(id).value="First Name";
	else

		$('#firstNmErrMsg').html('');
}

function clearDefaultLastname(id){
	var defaultValue = document.getElementById(id).value;

	if(defaultValue == "Last Name")
        document.getElementById(id).value="";
}
function placeDefaultValueForLarstName(id){
	var defaultValue = document.getElementById(id).value;

	if(defaultValue == "")
        document.getElementById(id).value="Last Name";
	else
		$('#lastNmErrMsg').html('');}


/*<c:forEach var="status" varStatus="stat" items="${questionsOptionsVO.options}">
			var obj =	{
							option:'${status.option}',
							votesObtained:${status.votesObtained}
						};
			pollStatus.push(obj);
		</c:forEach>*/
initializeResultsTable();
opinionPollChart();
</script>
<script type="text/javascript">


function saveComment(){

var jsObj =
			{   
			pollId:'${questionsOptionsVO.questionId}',
			comment:$('#userComment').val(),
			firstName:$('#fisrtNmId').val(),
			lastName:$('#lasttNmId').val(),
			task:"saveOpinionPollComment"

			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveOpenionPoleCommentAction.action?"+rparam;						
		callAjaxForComment(jsObj,url);

}

function callAjaxToAbuse(commentId){

	

	var jsObj =
			{   
			commentId:commentId,
			task:"abuseComment"

			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveAbuseCommentDetailsAction.action?"+rparam;
		
	
		callAjaxForComment(jsObj,url,commentId);
}

function callAjaxForComment(jsObj,url,commentId)
{

	
		var callback = {			
	 	success : function( o ) {
		try
		{ 
			myResults = YAHOO.lang.JSON.parse(o.responseText);
		    
			if(jsObj.task == "saveOpinionPollComment"){

			  var cssObj = {    
					'font-weight' : 'bold',
					'color' : 'green'
				}

				addDynamicDiv(jsObj,myResults);

				
				$('#successDiv').css('display','block');

			$('#successDiv').text("Your Comment Posted Successfully").css(cssObj).show().delay(2000).fadeOut(400);
			
			var userId = '${sessionScope.USER.registrationID}';

			if(userId != null && userId.length > 0)
			{
			$('#userComment').val("Post your comment here ....");
			}
			else
			{
			 $('#userComment').val("Post your comment here ....");
			 $('#fisrtNmId').val("First Name");
			 $('#lasttNmId').val("Last Name");
			}
				
			}else if(jsObj.task == "abuseComment"){
				
				
					 var cssObj = {    
					'font-weight' : 'bold',
					'color' : 'green'
				}

				
				
			$('#abuseSuccess'+commentId).text("Your Request Sent Successfully").css(cssObj).show().delay(2000).fadeOut(400);

				//alert("Your Request Sent Successfully");

			}
		    
		}catch(e){}
	  },
			scope : this,
			failure : function( o )
			{}
		  };

		 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function addDynamicDiv(jsObj,myResults){


	var str='';

	str+='<div class="span9 commentsec">';
	str+='<div style="margin-bottom:8px;">'+jsObj.comment+'</div>';
	str+='<span class="label label-info">Posted By:'+jsObj.firstName+' '+jsObj.lastName+'  </span>';	
	str+='<span id="abuseSuccess'+myResults+'"></span>';	
	str+='<a class="label label-important pull-right" style="margin-top:3px;margin-left:280px;" title="Click Here To Abuse This Comment" onClick="callAjaxToAbuse('+myResults+');" id="${comment.commentId}">Report Abuse</a>';
   str+='</div>';


$('#commentsDiv').prepend(str);
}

function saveCurrentPollResult(questionId){

	var elmts = document.getElementsByName("pollradio");
	var checkedElmtId = '';
	
	for(var i=0; i<elmts.length;i++)
	{
		if(elmts[i].checked == true)
			checkedElmtId = elmts[i].value;
	}
	var jsObj=
	{
			questionId:questionId,
			selectedPollId:checkedElmtId,
			task:"saveSelectedPoll"					
	};
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveSelectedPoll.action?"+rparam;						
	callAjaxToSaveSelectedPollDetails(rparam,jsObj,url,questionId);	
}

function callAjaxToSaveSelectedPollDetails(param,jsObj,url,questionId){

	var myResults;
		
		var callback = {			
		               success : function( o ) 
					  {
						try {			
								if(o.responseText)
								myResults = YAHOO.lang.JSON.parse(o.responseText);

								var cssObj = {    
									'font-weight' : 'bold',
									'color' : 'green'
								}

								if(myResults.availability == true){				

									 $('#alrdyVoted').text("You are already voted.").css(cssObj).show().delay(2000).fadeOut(400);

								}else{

									$('#pollSavedStatus').css('display','block');

								 $('#pollSavedStatus').text("Your vote saved Successfully").css(cssObj).show().delay(2000).fadeOut(400);
								}
								

                              getPollResults(questionId);
                                 
				     
                                $('#voteNow').dialog('close');
								$('#voteNowId').hide();
								
								
						}
						catch (e)
							{   
							  	//alert("Invalid JSON result" + e);   
							}	  
			              },
			               scope : this,
			               failure : function( o ) {

			            }
			               };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
}


function voteNow(){
	$('#voteNow').css('display','block');
	$('#voteNow').dialog({

		title:'Vote Now',
		height:'auto',
		width:'500px',
		show: { effect: 'drop', direction: "up" },
		modal:true
	});
}

function getPollResults(questionId){

	var jsObj =
			{				
			   questionId:questionId,
				task:"getPollDetails"

			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getQuestionAndPercentageOfVotesForChoices.action?"+rparam;						
		callAjax(jsObj,url);

}

function callAjax(jsObj,url)
{
	
		var callback = {			
	 	success : function( o ) {
		try
		{ 
			myResults = YAHOO.lang.JSON.parse(o.responseText);

		    
			if(jsObj.task == "getPollDetails"){					
			
				buildResult(myResults);		   
			}
		    
		}catch(e){}
	  },
			scope : this,
			failure : function( o )
			{}
		  };

		 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildResult(myResults){
$('#totalVotesId').html('');
$('#resultsDiv').html('');
$('#totalVotesId').html(myResults.totalVotesObtainedForPoll);

var str='';

for(var i=0;i < myResults.options.length;i++){
  str+='<li>';
	str+='<h5>'+myResults.options[i].option+'</h5>';
	str+='<div style="margin-left:-17px;">';
	str+='<div class="span2">';
	str+='<div class="progress" style="margin:0px;">';
	str+=' <div id="option1" class="bar" style="width:'+myResults.options[i].percentage+'%"></div>';
	str+='</div>';							
	str+='</div>	';

	str+='<span class="label pull-right label-info" style="margin-left:3px;">'+myResults.options[i].percentage+' </span>';
	str+='</div>';
	str+='</li>';
}


$('#resultsDiv').html(str);

}


</script>

</body>
</html>