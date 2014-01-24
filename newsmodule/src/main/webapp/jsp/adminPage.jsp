<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title> TDP News Portal - Admin Page</title>
	<meta name="" content="">
	<!-- Bootstrap -->
	
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.carousel.js"></script>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
	<script type="text/javascript" src="js/jquery.google.api/jquery-1.9.1.js"></script>
	<!--<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>-->
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 
	<!-------PT-sans font---->
	<!-- <link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'> -->
	<link  rel="stylesheet" type="text/css" href="js/jquery.google.api/googleAPIStyles.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"> </script>
<style type="text/css">
	#signin{clear: both;
    float: left;}
	
	#innerCreationDiv {
    margin: 25px 25px 25px 200px;
    padding: 30px 15px 15px 15px;
	border-radius:5px;
	border:1px solid #005C99;
	}
	.errDiv{
	color: red;
	font-weight: bold;
	}
	.succDiv{
	color: Green;
	font-weight: bold;
	}
</style>

	
</head>

<body>
	
		<!-- <div class="row m_top10" >
		<table class="table table-bordered" >
		<tr>
		<td>
		<a  class="btn btn-info" target="_blank" href="userRegistration.action"> Registration Form</a>
		</td>
		<!--<td><a  class="btn btn-info" href="javascript:{}" onClick="createNewSource();">Create New Source</a></td>-->
		<!--<td><a  class="btn btn-info" href="javascript:{uploadMLCCandidateDetails();}">Upload Party Candidates </a></td>
		</tr>
		</table>
		</div>
		<div id="sourceDetails"  style="width: 400px; border: 1px solid #CCCCCC; border-radius: 4px 4px 4px 4px; padding: 4px;display:none; margin-left: 298px;"></div>
		-->
		
		<div class="row m_top10" >
			<div class="offset3 span6 text-center">
				<a  class="btn btn-info" target="_blank" href="userRegistration.action"> Registration Form</a>
				<a  class="btn btn-info" href="javascript:{uploadMLCCandidateDetails();}">Upload Party Candidates </a>
				
				<div class="btn-group">
					<button type="button" class="btn btn-default btn-info dropdown-toggle" data-toggle="dropdown">
					  Debate Actions
					  <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" style="text-align:left">
					  <li><a href="javascript:{}" onclick="createRole();">Create Role</a></li>
					  <li><a href="javascript:{}" onclick="createCharacteristics();">Create Characteristics</a></li>
					  <li><a href="javascript:{}" onclick="createDebateQuestion();">Create Question</a></li>
					  <li><a href="javascript:{}" onclick="createObserver();">Create Observer</a></li>
					  <li><a href="javascript:{}" onclick="createChannel();">Create Channel</a></li>
					</ul>
				</div>
				
			</div>
			
			  <div id="creationDiv" class="span10" align="center"></div> 



<script type="text/javascript">
function createNewSource()
{
	$('#sourceDetails').show();
	var str = "";
	str += "<div id='errorDiv' style='color:red'> </div>";
	str +=  '<span>Source Name : </span><input type="text" id="sourceName"></input></br>';
	str +=  '<input type="button" value="create new Source" onClick="saveNewSourceDetails();" class="btn btn-info" style="margin-left: 244px;"></input>';
	$('#sourceDetails').html(str);
}
function saveNewSourceDetails()
{
	$('#errorDiv').html('');
	var sourceName =  $.trim($('#sourceName').val());
	if(sourceName.length <=0){
		$('#errorDiv').html('Source Name Should not be empty');
		return false;
	}
	var jsObj=
	{
		name  : sourceName,
		task  : "storeSource"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "storeSourceDetails.action?"+rparam;	

	callAjax(jsObj,url);
}
function callAjax(jsObj,url)
{
	 var myResults;
	 var callback = {			
	success : function( o ) {
		try {												
				myResults = YAHOO.lang.JSON.parse(o.responseText);	
			
				if(jsObj.task == "storeSource")
				{
					alert("Created Successfully");
					$('#sourceDetails').hide();
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

function uploadMLCCandidateDetails()
{


var win=window.open("uploadMLCCandidateDetailsAction.action", '_blank');
  win.focus();

}

function createRole(){
$("#creationDiv").html('');
	var str='';
	str +='<div id="innerCreationDiv"> ';
	str +='<div id="RerrDiv"></div>';
	str +='<h5 class="text-success"> Create New Debate Role </h5>';
	str +='Role Name :  <input type="text" name="roleName" id="newRoleId"/><div><input type="button" class="btn btn-success" value="Submit" onClick="createNewRole();"/></div>';
	str +='</div>';
	
	$("#creationDiv").html(str);
}

function createCharacteristics(){
$("#creationDiv").html('');
	var str='';
	
	str +='<div id="innerCreationDiv"> ';
	str +='<div id="CerrDiv" ></div>';
	str +='<h5 class="text-success">Create New Characteristic </h5>';
	str +='Characteristic Name :  <input type="text" name="CharacteristicName" id="CharacteristicId"/>';
	str +='<div><input type="button" class="btn btn-success" value="Submit"  onClick="createNewCharacteristic();"/></div>';
	str +='</div>';
	
	$("#creationDiv").html(str);
}

function createDebateQuestion(){
$("#creationDiv").html('');
	var str='';
	str +='<div id="innerCreationDiv">';
	str +='<div id="QerrDiv"></div>';
	str +='<h5 class="text-success"> Create New Debate Question </h5>';
	str +='	Debate Question :  <input type="text" name="debateQuestion" id="debateQstinId"/><div><input type="button" class="btn btn-success" value="Submit" onClick="createNewDebateQuestion();"/></div>';
	str +='</div>';
	
	$("#creationDiv").html(str);
}

function createObserver(){
$("#creationDiv").html('');
	var str='';
	str +='<div id="innerCreationDiv">';
	str +='<div id="OerrDiv"></div>';
	str +='<h5 class="text-success"> Create New Observer </h5>';
	str +='	Observer Name :  <input type="text" name="observerName" id="observerName"/><div><input type="button" class="btn btn-success" value="Submit" onClick="insertObserverDetails();"/></div>';
	str +='</div>';
	
	$("#creationDiv").html(str);
}

function createChannel(){
$("#creationDiv").html('');
	var str='';
	str +='<div id="innerCreationDiv">';
	str +='<div id="CHerrDiv"></div>';
	str +='<h5 class="text-success"> Create New Channel </h5>';
	str +='	Channel Name :  <input type="text" name="channel" id="channelName"/><div><input type="button" class="btn btn-success" value="Submit" onClick="insertChannelDetails();"/></div>';
	str +='</div>';
	
	$("#creationDiv").html(str);
}					  

function createNewRole(){

		var role = $("#newRoleId").val();

		if(role.trim().length<=0){
			$("#RerrDiv").html(' Role  Name is Required.').addClass("errDiv");
			return;	
		}

		createNewOne(role,"createNewRole","Role","RerrDiv");							
}
function createNewCharacteristic(){
		$("#debateStatus").html('');
		var characteristic = $("#CharacteristicId").val();

		if(characteristic.trim().length<=0){
			$("#CerrDiv").html(' Characteristic  Name is Required.').addClass("errDiv");
			return;	
		}

		createNewOne(characteristic,"createNewCharacteristic","Characteristic","CerrDiv");
}

function createNewDebateQuestion(){
		$("#debateStatus").html('');
		var debateQuestion = $("#debateQstinId").val();

		if(debateQuestion.trim().length<=0){
			$("#QerrDiv").html(' Debate Question is Required.').addClass("errDiv");
			return;	
		}

		createNewOne(debateQuestion,"createNewDebateQuestion","Debate Question","QerrDiv");
}

function insertChannelDetails(){

		var channel = $("#channelName").val();

		if(channel.trim().length<=0){
			$("#CHerrDiv").html(' Channel  Name is Required.').addClass("errDiv");
			return;	
		}

		createNewOne(channel,"createNewChannel","Channel","CHerrDiv");							
}
function insertObserverDetails(){

		var observer = $("#observerName").val();

		if(observer.trim().length<=0){
			$("#OerrDiv").html(' Observer  Name is Required.').addClass("errDiv");
			return;	
		}

		createNewOne(observer,"createNewObserver","Observer","OerrDiv");							
}


function createNewOne(name,task,type,errDiv){
	$("#debateStatus").html('');
	$.ajax({
		type: "POST",
		url:"saveDebateRelatedAttributesAction.action",
		data:{attributeName:name,task:task}
	}).done(function(result){

			if(result != null){
				if(result.resultCode == 0){
					$("#"+errDiv+"").html('New '+type+' Created Successfully.').addClass("succDiv").fadeOut(3000);
					return;
				}else
				{
					$("#"+errDiv+"").html('Error occured while saving new '+type+'').addClass("errDiv");
					return;
				}
			}else
				{
					$("#"+errDiv+"").html('Error occured while saving new '+type+'').addClass("errDiv");
					return;
				}
	});

}
</script>
</body>


</html>