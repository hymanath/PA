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
width: 100px;
}
input, button, select, textarea {
font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
}
.textID,#relationShipId{
margin-left: 55px;
}
.titleHeading {
    background: none repeat scroll 0 0 #06ABEA;
    border-radius: 4px 4px 4px 4px;
    color: white;
    font-family: verdana;
    font-size: 18px;
    font-weight: bold;
    margin-left: 355px;
    margin-top: 5px;
    text-align: center;
    text-transform: uppercase;
    width: 357px;
}

#tdHeader{
height: 40px;
}
#SelectionDiv,#missingVoterDiv{
font-size: 15px;
}
#mainDiv{
border: 1px solid #CFD6DF;
margin:40px;
margin-top:15px;
padding: 10px;
}
#errmsg{
font-size:15px;
font-weight:bold;
}
#errorMsgDiv{
font-size:15px;
color:red;
font-weight:bold;
}
#requestDiv{
font-size:15px;
color:blue;
margin-left: 70px;
font-weight:bold;
}
.table th {
    background: none repeat scroll 0 0 #D9EDF7;
    font-weight: bold;
    text-align: center;
}
.female
{
	margin-left:10px;
}
#genderMale,#genderFemale
{
	margin-left: 3px;
    margin-top: -2px;
}
</style>
<script type="text/javascript">
var flag=false;
var rowLength;
/* This Method is used for calling the ajax method for getting publication dates */
function getPublicationDate()
{
	$('#voterForm').hide();
	$('#buttonsDiv').hide();
	$('#publicationDateList').val('0');
	$('#booth').val('0');
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
	$('#voterForm').hide();
	$('#buttonsDiv').hide();
	$('#booth').val('0');
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
	$('#booth option').remove();
	$('#booth').append('<option value=0>Select Booth</option>');
	for (i in myResults)
	{
		var boothName = myResults[i].name;
		var boothId = myResults[i].id;
		$('#booth').append('<option value='+boothId+'>BOOTH-'+boothName+'</option>');
	}
}
/* This method is used for calling the ajax method for saving the voter details into the voters table */
/*function saveVoterDetails()
{
	if(!flag){
		flag=true;
		$('#processingImgId').show();
		var boothId = $('#booth').val();
		var name = $('#nameId').val();
		var voterCardNo= $('#voterCardId').val();
		var houseNo= $('#houseNoId').val();
		var gaurdian = $('#gaurdianId').val();
		var relationShip = $('#relationShip option:selected').text();
		var gender = $('.gender:checked').val();
		var age = $('#age').val();
		var serialno=$('#serialno').val();
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
			serialno:serialno,
			age:age,
			boothId:boothId,
			task:"saveVoterDetails"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getBoothsForConstituencyIdAndPublicationId.action?"+rparam;
		callAjax(jsObj,url);
	}
	else{
		$('#requestDiv').html('Please wait ...');
	}
}*/
function clearErrDiv()
{
	$('#errorMsgDiv').html('&nbsp;');
}
/* This Method is used to inform to the user weather voter details stored or not */
function buildVotersDetailsInformation(myResults)
{
	var voterIdCars = new Array();
	if(myResults.length > 0)
	{
		for(var i in myResults)
		{
			voterIdCars.push(myResults[i]);
		}
		$('#errorMsgDiv').html('Voter already exists With This Voter Card nos '+voterIdCars);
	}
	else
	{
		$('#errorMsgDiv').html('<b id="errmsg" style="color:green;">Voter information added Successfully</b>');
		$('#processingImgId').hide();
		clearAllFields();
	}
	
}
/*
	This method is used to make the ajax call for saving voter detials weather sane serial nos are not there in the particular booths
*/
function buildVoterSerialNosInfo(myResults)
{
	var snos = new Array();
	if(myResults.length > 0)
	{
		for(var i in myResults)
		{
			snos.push(myResults[i]);
		}
		$('#errorMsgDiv').html('Voter already exists With This Serial Nos '+snos);
	}
	else
	{
		saveAllValues();
	}
}
/* This is method is used for clearing the all fields after clicking the save button */
function clearAllFields()
{
	$('.name').val('');
	$('.voterAge').val('');
	$('.voterId').val('');
	$('.houseNo').val('');
	$('.guardianName').val('');
	$('#genderMale').attr('checked', true);
	$('.relationType').val('');
	$('.serialNo').val('');
}
/* This method is used for validating the all fields for voters details */
var count;
var count1;
function validationCheck()
{
	var voterInfo = new Array(); 
	count = 0;
	count1 = 0;
    $('.voterEnterForm').each(function () {
    var name =  $(this).closest("tr").find(".name").val();
    var gender =  $(this).closest("tr").find(".gender").val();
    var age =  $(this).closest("tr").find(".voterAge").val();
    var voterId =  $(this).closest("tr").find(".voterId").val();
    var houseNo =  $(this).closest("tr").find(".houseNo").val();
    var gurdianName =  $(this).closest("tr").find(".guardianName").val();
    var relationType =  $(this).closest("tr").find(".relationType option:selected").text();
	var relationTypeId =  $(this).closest("tr").find(".relationType option:selected").val();
    var serialNo =  $(this).closest("tr").find(".serialNo").val();
	if(gender == 'male')
	{
	gender = 'M';
	}
	else
	{
	gender = 'F';
	}
	if(name == '')
	{
		$('#errorMsgDiv').html('Please Enter The Name');
		return false;
	}
	if(name.length > 0)
	{
		if(/[^a-z A-Z]/.test(name)){
		$('#errorMsgDiv').html('Name Accepts only Characters');
		return false;
		}
		else{
		count = count+1;
		}
	}
	if(age == '')
	{
		$('#errorMsgDiv').html('Please Enter The Age');
		return false;
	}
	if(age.length > 0)
	{
		if(isNaN(age) === true)
		{
			$('#errorMsgDiv').html('Age Accepts only Numbers');
			return false;
		}
	}
	if(age > 150 || age < 18)
	{
			$('#errorMsgDiv').html('Please enter valid Age');
			return false;
	}
	if(voterId == ''){
			$('#errorMsgDiv').html('Please Enter The voter Card No.');
			return false;
	}
	if(voterId.length>0){
	var letters = /([a-zA-Z])([0-9])/;
	var iChars = "!`@#$%^ &*()+=-[]\\\';,./{}|\":<>?~";
	for (var i = 0; i < voterId.length; i++)
		{
			if (iChars.indexOf(voterId.charAt(i)) != -1)
			{
			$('#errorMsgDiv').html('Please Enter A Valid Voter Id');
			return false;
			}
		}
	if (!voterId.match(letters))
		{
		$('#errorMsgDiv').html('Please Enter A Valid Voter Id');
		return false;
		}
	}
	if(houseNo == ''){
		$('#errorMsgDiv').html('Please Enter The House No.');
		return false;
	}	
	if(houseNo.length>0){
		var houseNoValue=trim(houseNo);
		if( /[^a-zA-Z0-9,. :\/\-]/.test(houseNoValue)) {
			$('#errorMsgDiv').html('Enter A Valid House No');
			return false;
		}		
	}
	if(gurdianName == '')
	{
		$('#errorMsgDiv').html('Please Enter The Guardian');
		return false;
	}
	if(gurdianName.length > 0)
	{
		if(/[^a-z A-Z]/.test(gurdianName))
		{
			$('#errorMsgDiv').html('Guardian Accepts only Charactors');
			return false;
		}
	}	
	
	
	if(relationTypeId == '0')
	{
		$('#errorMsgDiv').html('Please Select The Relationship Type');
		return false;		
	}
	/*if(relationTypeId != '0')
	{
		count1= count1+1;
		return true;		
	}	*/
	if(serialNo == '')
	{
		$('#errorMsgDiv').html('Please Enter The Serial No');
		return false;
	}	
	if(serialNo.length > 0)
	{
		if(isNaN(serialNo) === true)
		{
			$('#errorMsgDiv').html('Serial No Accepts only Numbers');
			return false;
		}
		else
		{
			count1= count1+1;
			return true;
		}
		
	}
   });
   if(count == count1 && count == rowLength)
   {
	//getAllValues();
	checkForSerialNos();
	$('#errorMsgDiv').html('&nbsp;');
   }  
	
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
		else if(jsObj.task == "checkForSerialNos")
		{
			buildVoterSerialNosInfo(myResults);
		}
		else if(jsObj.task == "checkForVoterId")
		{
			
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
/*
	This method is used to show the voter form to the end user
*/
function showVoterForm()
{
	$('#voterForm').show();
	$('#buttonsDiv').show();
	$('#addMoreButton').show();
	appendAnotherVoterForm();
}
/*
	This method is used to make a ajax cal for storing voter date into the database
*/
function saveAllValues()
{
	  $('#processingImgId').show();
	  var voterInfo = new Array(); 
	  var boothId = $('#booth').val();
      $('.voterEnterForm').each(function () {
       var name =  $(this).closest("tr").find(".name").val();
       var gender =  $(this).closest("tr").find(".gender").val();
       var age =  $(this).closest("tr").find(".voterAge").val();
       var voterId =  $(this).closest("tr").find(".voterId").val();
       var houseNo =  $(this).closest("tr").find(".houseNo").val();
       var gurdianName =  $(this).closest("tr").find(".guardianName").val();
       var relationType =  $(this).closest("tr").find(".relationType option:selected").text();
       var serialNo =  $(this).closest("tr").find(".serialNo").val();
	   if(gender == 'male')
			gender = 'M';
		else
			gender = 'F';
	   var voterDetails = {
		name:name,
		gender:gender,
		age:age,
		voterId:voterId,
		houseNo:houseNo,
		gurdianName:gurdianName,
		relationType:relationType,
		serialNo:serialNo
	   };
	  voterInfo.push(voterDetails);
      });
	 var jsObj=
		{
			voterInfo:voterInfo,
			boothId:boothId,
			task:"saveVoterDetails"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getBoothsForConstituencyIdAndPublicationId.action?"+rparam;
		callAjax(jsObj,url);
}
/*
	This method is used to build the voter date table for user to enter the voter details
*/
function appendAnotherVoterForm()
{
	rowLength = $('#noOfVoters option:selected').text();
	var divEle = document.getElementById('voterForm');
	var str = '';
	str+='<table  class="table table-bordered table-hover">';
	str+='<tr>';
	//str+='<th>SNO</th>';
	str+='<th>NAME</th>';
	str+='<th>GENDER</th>';
	str+='<th>AGE</th>';
	str+='<th>VOTER ID</th>';
	str+='<th>H NO</th>';
	str+='<th>GUARDIAN NAME</th>';
	str+='<th>RELATION</th>';
	str+='<th>S NO</th>';
	str+='<th>DELETE</th>';
	str+='</tr>';
	for(var i = 1 ; i <= rowLength ; i++)
	{
	str+='<tr class="voterEnterForm" id="voterdetailsRow_'+i+'">';
	//str+='<td align="center">'+i+'</td>';
	str+='<td><input id="voterName" class="name" style="width: 100px;height: 24px;" type="text"></input></td>';
	str+='<td>';
	str+='<span class="male">MALE</span><input type="radio" id="genderMale" name="gender_'+i+'" class="gender" checked= "true"  value="male"></input>';
	str+='<span class="female" >FEMALE</span><input type="radio" id="genderFemale" name="gender_'+i+'" class="gender" value="female"></input>';
	str+='</td>';
	str+='<td><input id="voterAge" class="voterAge"  style="width:30px;height:24px;" onKeyUp="checkForValideAge();" type="text"></input></td>';
	str+='<td><input id="voterId" type="text" class="voterId" style="width:84px;height:24px;" onKeyUp="checkForDuplicateVoters();" ></input></td>';
	str+='<td><input id="voterHNo"  type="text" class="houseNo" style="width:40px;height:24px;"></input></td>';
	str+='<td><input id="voterGName"  type="text" class="guardianName" style="width:100px;height:24px;"></input></td>';
	str+='<td><select id="voterRelation"  class="relationType"  style="height:30px;">';
	str+='<option value="0"> Select Relationship</option>';
	str+='<option value="1"> Father </option>';
	str+='<option value="2"> Mother </option>';
	str+='<option value="3"> Husband </option>';
	str+='<option value="4"> Others </option>';
	str+='</select></td>';
	str+='<td><input id="voterSNo"  type="text" class="serialNo" style="width:40px;height:24px;" onKeyUp="checkForDuplicateSerialNos();"></input></td>';
	str+='<td align="center"><img alt="deleteImg" src="images/icons/delete.png" id="deleteImg_'+i+'" class="deleteImg"></img></td>';
	str+='</tr>';
	str+='<div id="appendVoterForm"></div>';
	}
	str+='</table>';
	divEle.innerHTML = str;
}
/*
	This Block Of code is used to delete the specifed row for the user selection
*/
$(".deleteImg").live('click', function(event) {
	var r=confirm("Do You Want To Delete Row ?");
	if (r == true)
	{
	$(this).closest("tr").remove();
	rowLength = rowLength-1;
	}
});
/*
	This method is used to make a ajax call for checking the voter already exists or not
*/
function checkingForVoterId()
{
	var voterId = $('.voterId').val();
	 var jsObj=
		{
			
			voterId:voterId,
			task:"checkForVoterId"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getBoothsForConstituencyIdAndPublicationId.action?"+rparam;
		callAjax(jsObj,url);
}
/*
	This method is used to build No Of Voters Select box for no of voters selection
*/
function getNoOfVotersToSelect()
{
	$('#voterForm').hide();
	$('#buttonsDiv').hide();
	$('#noOfVoters option').remove();
	$('#noOfVoters').append('<option value=0>Select No Of Voters</option>');
	$('#noOfVoters').append('<option value=1>5</option>');
	$('#noOfVoters').append('<option value=2>10</option>');
	$('#noOfVoters').append('<option value=3>15</option>');
	$('#noOfVoters').append('<option value=4>20</option>');
	$('#noOfVoters').append('<option value=4>25</option>');
	$('#noOfVoters').append('<option value=4>30</option>');
}
/*
	This Method is used to check for voter , with same voter card Id or not 
*/
function checkForDuplicateVoters()
{
	 $('#errorMsgDiv').html('&nbsp;');
	 var voterArray = new Array();
	 $('.voterEnterForm').each(function () {
     var voterId =  $(this).closest("tr").find(".voterId").val();
	 if($.trim(voterId).length > 0)
	 {
	 voterArray.push($.trim(voterId));
	 }
	 });
	 for(var i = 0 ; i < voterArray.length ; i++)
	 {
		for(var j = 0 ; j < voterArray.length ; j++)
		{
		 if(i != j)
		  {
		    if(voterArray[i] == voterArray[j])
				{
			    	$('#errorMsgDiv').html('Voter already exists With This Voter Id '+voterArray[i]);
				}
		   }
		}
     }	
		 
}
/*
	This Method is used to check for voter , with same Serial no Exist or not  
*/
function checkForDuplicateSerialNos()
{
	$('#errorMsgDiv').html('&nbsp;');
	var serialNoArray = new Array();
	$('.voterEnterForm').each(function () {
     var snos =  $(this).closest("tr").find(".serialNo").val();
	 if($.trim(snos).length > 0)
	 {
	 serialNoArray.push($.trim(snos));
	 }
	 });
	 for(var i = 0 ; i< serialNoArray.length ; i++)
	 {
		for(var j = 0 ; j < serialNoArray.length ; j++)
		{
		  if(i != j)
		  {
			if(serialNoArray[i] == serialNoArray[j])
			{
				$('#errorMsgDiv').html('Voter already exists With This Serial No '+serialNoArray[i]);
			}
		  }
		  
		}
	 }
	 checkForValiedSerialNos();
}
/*
	This Methos is used to send a ajax call For checking weather the serial no is exist or not in that particular booth
*/
function checkForSerialNos()
{
	var serialNoList = new Array();
	var boothId = $('#booth').val();
	$('.voterEnterForm').each(function () {
    var serialNo =  $(this).closest("tr").find(".serialNo").val();
	var snos = {
		serialNo : serialNo
	};
	serialNoList.push(snos);
	});
	var jsObj=
		{
			serialNoList:serialNoList,
			boothId:boothId,
			task:"checkForSerialNos"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getBoothsForConstituencyIdAndPublicationId.action?"+rparam;
		callAjax(jsObj,url);
}
/*
	This Method is used to valiadte the Age For Age should not accept characters and Special Characters
*/
function checkForValideAge()
{
	$('.voterEnterForm').each(function () {
    var age =  $(this).closest("tr").find(".voterAge").val();
	if(isNaN(age) === true)
		{
			$('#errorMsgDiv').html('Age Accepts only Numbers');
			return false;
		}
	if(age.length > 3)
	{
		$('#errorMsgDiv').html('Age Accepts only 3 Digits');
			return false;
	}
	});
}
/*
	This Method is used to valiadte the Serial nos For Serial no should not accept characters and Special Characters
*/
function checkForValiedSerialNos()
{
	
	$('.voterEnterForm').each(function () {
    var serialNo =  $(this).closest("tr").find(".serialNo").val();
	if(isNaN(serialNo) === true)
		{
			$('#errorMsgDiv').html('SNo Accepts only Numbers');
			return false;
		}
	});
}
</script>
<body>
<div class="titleHeading">Add Missing Voters Details </div>
<div id="mainDiv">

<div id="SelectionDiv" align="center" style="margin-top: -10px;">
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
Booth : <font class="requiredFont" style="color:red;font-size:large;">*</font>
</td>
<td>
<select id="booth"  onChange="clearErrDiv();getNoOfVotersToSelect();" style="width: 225px; height: 30px; margin-left: 20px;">
</select>
</td>
</tr>
<tr>
<td id="tdHeader">
No Of Voters : <font class="requiredFont" style="color:red;font-size:large;">*</font>
</td>
<td>
<select id="noOfVoters"  onChange="clearErrDiv();showVoterForm();" style="width: 225px; height: 30px; margin-left: 20px;">
</select>
</td>
</tr>
</table>
</div>
<div></div>
<div id="errorMsgDiv"  align="center"  style="margin-top: 10px;">&nbsp;</div>
<div id="voterForm"  align="center"></div>
<!--<div>
<input type="button" value="Add More" style=" width: 70px;float: right;margin-top: -24px;margin-right: 79px;display:none;" id="addMoreButton" onClick="appendAnotherVoterForm();">
</input>
</div>-->
<div id="buttonsDiv" style="margin-top: 10px; border-bottom-width: 0px; height: 50px;display:none" align="center">
<input type="button" value="save" id="submitButtonId" class="btn btn-primary" style="margin-top: 10px;width:100px" onClick="validationCheck();"/>
<input type="button" value="Clear" class="btn btn-primary" style="margin-top: 10px;width:100px" onClick="clearAllFields();"/>
<img alt="processimg" src="images/icons/search.gif" id="processingImgId"style="display: none; margin-left: 5px; margin-top:10px;"></img>
<div id="requestDiv" style="float: right; position: absolute; margin-left: 600px; width: 200px; margin-top: -20px;"></div>
</div>
</div>
</body>
</html>
