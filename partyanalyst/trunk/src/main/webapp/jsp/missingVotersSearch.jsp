<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Add Missing Voters </title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script src="js/cleditor/jquery.cleditor.min.js"></script>
<script src="js/sendUpdatesByEmail.js"></script>
<link rel="stylesheet" type="text/css" href="css/cleditor/jquery.cleditor.css">
</head>
<style>
select {
background-color: #FFFFFF;
border: 1px solid #CCCCCC;
width: 150px;
}
select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input,#fromDate,#toDate {
border-radius: 4px 4px 4px 4px;
color: #000000;
display: inline-block;
font-size: 13px;
line-height: 18px;
padding: 4px;
}
input, button, select, textarea {
font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
}
.textID,#relationShipId{
margin-left: 55px;
}
.titleHeading{
color: steelblue;
text-align: center;
text-transform: uppercase;
font-family: sans-serif,verdana,serif;
font-size: 20px;
font-weight: bold;
margin-left: 200px;
width: 800px;
margin-top:5px;
margin-left:97px;
}

#tdHeader{
height: 40px;
}
#SelectionDiv,#missingVoterDiv{
font-size: 15px;
}
#mainDiv{
border: 3px solid #CFD6DF;
margin:40px;
margin-top:15px;
}
#errmsg{
margin-left:-260px;
}
#errmsg{
margin-left:-260px;
}
</style>
<script type="text/javascript">
/* This Method is used for calling the ajax method for getting publication dates */
function getPublicationDate()
{
	$('#submitButtonId').removeAttr('disabled');
	$('#errorMsgDiv').html('&nbsp;');
	var constituencyID = document.getElementById("constituencyList");
	var name=constituencyID.options[constituencyID.selectedIndex].name;
	var value=constituencyID.options[constituencyID.selectedIndex].value;
	var choice=false;
	var locationAlertEl = document.getElementById("locationAlertMsg");

	var jsObj=
		{
		selected:value,
		task:"getPublicationDate"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+rparam;
	callAjax(jsObj,url);
}
/* This method is used for building the publication date in the selected text box */
function buildPublicationDateList(results)
{
	var selectedElmt=document.getElementById("publicationDateList");
	removeSelectElements(selectedElmt);
	for(var val in results)
	{
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
}
/* This method is used for removing the previous publication dates in the select box */
function removeSelectElements(selectedElmt)
{
	var len = selectedElmt.length;
	for(var i=len-1;i>=0;i--)
	{
		selectedElmt.remove(i);
	}
}
/* This method is used for calling the ajax method for building the booth detais based on constituency id and publication date id */
function getBoothsForConstituency()
{
	$('#submitButtonId').removeAttr('disabled');
	$('#errorMsgDiv').html('&nbsp;');
	var constituencyId = $('#constituencyList').val();
	var publicationId = $('#publicationDateList').val();
	var jsObj=
		{
			constituencyId:constituencyId,
			publicationId:publicationId,
			task:"getBoothsForConstituency"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getBoothsForConstituencyIdAndPublicationId.action?"+rparam;
	callAjax(jsObj,url);
}
/* This method is used for building the booths information in the selectbox */
function buildBoothsForConstituencyAndPublicationDate(myResults)
{	
	$('#booth').append('<option value=0>Select Booth</option>');
	for (i in myResults)
	{
		var boothName = myResults[i].name;
		var boothId = myResults[i].id;
		$('#booth').append('<option value='+boothId+'>BOOTH-'+boothName+'</option>');
	}
}
/* This method is used for calling the ajax method for saving the voter details into the voters table */
function saveVoterDetails()
{
	$('#processingImgId').show();
	var boothId = $('#booth').val();
	var name = $('#nameId').val();
	var voterCardNo= $('#voterCardId').val();
	var houseNo= $('#houseNoId').val();
	var gaurdian = $('#gaurdianId').val();
	var relationShip = $('#relationShip option:selected').text();
	var gender = $('.gender:checked').val();
	var age = $('#age').val();
	if(gender == 'male')
		gender = 'M';
	else
		gender = 'F';
	var jsObj=
	{
		name:name,
		voterCardNo:voterCardNo,
		houseNo:houseNo,
		gaurdian:gaurdian,
		relationShip:relationShip,
		gender:gender,
		age:age,
		boothId:boothId,
		task:"saveVoterDetails"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getBoothsForConstituencyIdAndPublicationId.action?"+rparam;
	callAjax(jsObj,url);
}
/* This Method is used to inform to the user weather voter details stored or not */
function buildVotersDetailsInformation(myResults)
{
	$('#processingImgId').hide();
	var result = myResults.resultCode;
	if(result == 1)
	{
		$('#errorMsgDiv').html('<b>Voter already exists please enter another voter id</b>');
	}
	else
	{
		$('#errorMsgDiv').html('<b id="errmsg" style="color:green;">Voter information added successfully</b>');
		clearAllFields();
	}
}
/* This is method is used for clearing the all fields after clicking the save button */
function clearAllFields()
{
	$('#constituencyList').val('0');
	$('#publicationDateList').val('0');
	$('#booth').val('');
	$('#nameId').val('');
	$('#voterCardId').val('');
	$('#houseNoId').val('');
	$('#gaurdianId').val('');
	$('#relationShipId').val('0');
	$('#genderMale').attr('checked', true);
	$('#age').val('');
}
/* This method is used for validating the all fields for voters details */
function validationCheck()
{
	var constituencyId = $('#constituencyList').val();
	var publicationId = $('#publicationDateList').val();
	var boothId = $('#booth').val();
	var name = $('#nameId').val();
	var voterCardNo= $('#voterCardId').val();
	var houseNo= $('#houseNoId').val();
	var gaurdian = $('#gaurdianId').val();
	var relationShipId = $('#relationShipId').val();
	var gender = $('.gender:checked').val();
	var age = $('#age').val();
	$('#errorMsgDiv').html('&nbsp;');
	if(constituencyId == '0')
	{
		$('#errorMsgDiv').html('<b id="errmsg">Please Select The Constituency </b>');
		return false;
	}
	if(publicationId == '0')
	{
		$('#errorMsgDiv').html('<b id="errmsg">Please Select The Publication Date </b>');
		return false;
	}
	if(boothId == '0')
	{
		$('#errorMsgDiv').html('<b id="errmsg">Please Select The Booth </b>');
		return false;
	}
	if(name == '')
	{
		$('#errorMsgDiv').html('<b id="errmsg">Please Enter The Name </b>');
		return false;
	}
	if(name.length > 0)
	{
		if(/[^a-zA-Z]/.test(name)){
		$('#errorMsgDiv').html('<b id="errmsg">Name Accepts only Characters</b>');
		return false;
		}
	}
	if(voterCardNo == ''){
		$('#errorMsgDiv').html('<b id="errmsg">Please Enter The voter Card No. </b>');
		return false;
	}	
	if(voterCardNo.length>0){
		var letters = /([^a-zA-Z])([0-9])/; 
		var iChars = "!`@#$%^&*()+=-[]\\\';,./{}|\":<>?~";
		for (var i = 0; i < voterCardNo.length; i++)
            {      
                if (iChars.indexOf(voterCardNo.charAt(i)) != -1)
                {   
					$('#errorMsgDiv').html('<b id="errmsg">Please Enter A Valid Voter Id</b>');
					return false;
                } 
            }
		if (!voterCardNo.match(letters))
		{   
			$('#errorMsgDiv').html('<b id="errmsg">Please Enter A Valid Voter Id</b>');
			return false;
		}	
	}
	if(houseNo == ''){
		$('#errorMsgDiv').html('<b id="errmsg">Please Enter The House No. </b>');
		return false;
	}	
	if(houseNo.length>0){
		var houseNoValue=trim(houseNo);
		if( /[^a-z A-Z0-9,.:\/\-]/.test(houseNoValue)) {
			$('#errorMsgDiv').html('<b id="errmsg">Enter A Valid House No</b>');
			return false;
		}
		/*else if(isNaN(houseNoValue) === true)
		{
			$('#errorMsgDiv').html('<b id="errmsg">Enter A Valid House No</b>');
			return false;
		}*/
	}
	if(gaurdian == '')
	{
		$('#errorMsgDiv').html('<b id="errmsg">Please Enter The Guardian </b>');
		return false;
	}
	if(gaurdian.length > 0)
	{
		if(/[^a-zA-Z]/.test(gaurdian))
		{
			$('#errorMsgDiv').html('<b id="errmsg">Guardian Accepts only Charactors</b>');
			return false;
		}
	}
	if(relationShipId == '0')
	{
		$('#errorMsgDiv').html('<b id="errmsg">Please Select The Relationship Type</b>');
		return false;
	}
	if(age == '')
	{
		$('#errorMsgDiv').html('<b id="errmsg">Please Enter The Age </b>');
		return false;
	}
	if(age.length > 0)
	{
		if(isNaN(age) === true)
		{
			$('#errorMsgDiv').html('<b id="errmsg">Age Accepts only Numbers</b>');
			return false;
		}
	}
	if(age > 150 || age < 18)
	{
			$('#errorMsgDiv').html('<b id="errmsg">Please enter valid Age</b>');
			return false;
	}
	$('#errorMsgDiv').html('&nbsp;');
	/*$('#submitButtonId').click(function() {
    $(this).attr("disabled", true); 
	});*/
	saveVoterDetails();
}
/* This method is used for building the ajax calls */
function callAjax(jsObj,url)
{
	var myResults;
	var callback = {
	success : function( o ) {
	try {
		myResults = YAHOO.lang.JSON.parse(o.responseText);
		if(jsObj.task == "getPublicationDate")
		{
			buildPublicationDateList(myResults);
		}
		else if(jsObj.task == "getBoothsForConstituency")
		{
			buildBoothsForConstituencyAndPublicationDate(myResults);
		}
		else if(jsObj.task == "saveVoterDetails")
		{
			buildVotersDetailsInformation(myResults);
		}
		}
	catch (e) {
	}
	},
	scope : this,
	failure : function( o ) {
	//alert( "Failed to load result" + o.status + " " + o.statusText);
	}
	};

	YAHOO.util.Connect.asyncRequest('POST', url, callback);
}
</script>
<body>
<div class="titleHeading">Adding Missing Voters Details </div>
<div id="mainDiv">
<div id="errorMsgDiv" align="center" style="margin-top: 15px; width: 450px; margin-left: 220px;">&nbsp;</div>
<div id="SelectionDiv" align="center">
<table style="margin-top: 15px; margin-left: 70px;">
<tr>
<td id="tdHeader">
Select Constituency : <font class="requiredFont" style="color:red;font-size:large;" >*</font>
</td>
<td><s:select theme="simple" style="width: 225px; height: 30px; margin-left: 20px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onChange="getPublicationDate();"/>
</td>
</tr>
<tr>
<td id="tdHeader">
Select Publication Date : <font class="requiredFont" style="color:red;font-size:large;" >*</font>
</td>
<td>
<select id="publicationDateList" class="selectWidth" style="width: 225px; height: 30px; margin-left: 20px;" name="publicationDateList" onChange="getBoothsForConstituency();">
</select>
</td>
</tr>
<tr>
<td id="tdHeader">
Booth : <font class="requiredFont" style="color:red;font-size:large;" >*</font>
</td>
<td>
<select id="booth" style="width: 225px; height: 30px; margin-left: 20px;">
</select>
</td>
</tr>
</table>
</div>
<div id="missingVoterDiv" align="center">
<table style="margin-top: 10px; margin-left: 75px;">
<tr>
<td id="tdHeader">
Name : <font class="requiredFont" style="color:red;font-size:large;" >*</font>
</td>
<td id="tdHeader">
<input class="textID" type="text" name="voterName"id="nameId"/>
</td>
</tr>
<tr>
<td id="tdHeader">
Voter ID Card No: <font class="requiredFont" style="color:red;font-size:large;" >*</font>
</td>
<td id="tdHeader">
<input class="textID" type="text" name="voterCardNo" id="voterCardId"/>
</td>
</tr>
<tr>
<td id="tdHeader">
House No: <font class="requiredFont" style="color:red;font-size:large;" >*</font>
</td>
<td>
<input class="textID" type="text" name="houseNo" id="houseNoId"/>
</td>
</tr>

<tr>
<td id="tdHeader">
Guardian Name: <font class="requiredFont" style="color:red;font-size:large;" >*</font>
</td>
<td>
<input class="textID" type="text" name="gardianName" id="gaurdianId"/>
</td>
</tr>
<tr>
<td id="tdHeader">
RelationShip Type : <font class="requiredFont" style="color:red;font-size:large;" >* </font>
</td>
<td>
<select class="relationShip" style="width: 225px" id="relationShipId">
<option value="0"> Select RelationShip</option>
<option value="1"> Father </option>
<option value="2"> Mother </option>
<option value="3"> Husband </option>
<option value="4"> Others </option>
</select>
</td>
</tr>
<tr>
<td class="tdHeader">
Gender : <font class="requiredFont" style="color:red;font-size:large;" >*</font>
</td>
<td style="height: 40px;">
<input type="radio" name="gender" value="male" checked="true" id="genderMale" class="gender" style="margin-left: 60px;"
/> Male
<input type="radio" name="gender" value="female" id="genderFemale" class="gender"/> Female
</td>
</tr>
<tr>
<td class="tdHeader">
Age: <font class="requiredFont" style="color:red;font-size:large;" >*</font>
</td>
<td>
<input class="textID" type="text" name="age" id="age" id="ageId"/>
</td>
</tr>
</table>
<div id="buttonsDiv" style="margin-top: 10px; border-bottom-width: 0px; height: 50px;">
<input type="button" value="save" id="submitButtonId" class="btn btn-primary" style="margin-top: 10px;width:100px" onClick="validationCheck();"/>
<input type="button" value="Clear" class="btn btn-primary" style="margin-top: 10px;width:100px" onClick="clearAllFields();"/>
<img alt="processimg" src="images/icons/search.gif" id="processingImgId"style="display:none;"></img>
</div>
</div>
</div>
</body>
</html>
