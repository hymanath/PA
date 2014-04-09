	
	var receiverSmsArray = null;
	var maxcount = 0;
	function buildUsers(results,jobj)
	{
	receiverSmsArray = new Array();

		
		var str='';
		str+='<table class="table table-striped table-bordered" style="width:77% !important;">';
        str+='<thead>';
	    str+=' <tr>';
        str+='<th style="width:8px;">';
        //str+='<input id="selectAllId" type="checkbox" class="selectAllCheck" data-original-title="Tooltip on top" data-toggle="tooltip" data-placement="top">&nbsp;Select</th>';
		str+=' <input type="checkbox" id="parentUserCheck"/>';
		 str+='<label id="label" for="button"></label>';
        // str+='<th>First Name</th>';
        // str+='<th>Last Name</th>';
		str+='<th>Incharge</th>';
		str+='<th  style="display:none;">Contact Number</th>';
		str+='<th>Designation</th>';
        str+='</tr>';
        str+='</thead>';
		str+='<tbody>';
	  for(var i in results)
		{
			var obj = {
				userId : results[i].registrationID,
				name : results[i].accessValue,
				mobileNo : results[i].mobile
			};
		receiverSmsArray.push(obj);
        str+='<tr >';
        str+='<td style="width:8px;"><input type="checkbox" class="userCheck" id=user'+results[i].registrationID+' value='+results[i].registrationID+' ></td>';
       // str+='<td >'+results[i].firstName+'</td>';
       // str+='<td>'+results[i].lastName+'</td>';
	   str+='<td>'+results[i].accessValue+'</td>';
        str+='<td style="display:none;" class="mobile" value='+results[i].mobile+'>'+results[i].mobile+'</td>';
		 str+='<td>'+results[i].designation+'</td>';
        str+='</tr>'
	     }   
        str+='</tbody>';
        str+='</table>';
   /*str+='<div class="row-fluid"><div class="span3"><a class="btn btn-mini btn-block" id="addOtherContact" onclick="addOtherContact();">Click Here to add new contact nummber</a></div>'; 
   str+='<div class="span8" id="otherContactsDiv" style="display:none;"><input type="text" placeholder="Name"> &nbsp;&nbsp;&nbsp; <input type="text" placeholder="Contact Number" id="mobileNumber"></div>';
   str+='</div><textarea rows="3" id="description" class="span10" placeholder="Enter Your Message Here" style="background:#fff;"></textarea>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><button type="submit" class="btn btn-large btn-primary " onclick="sendSMS()">Send SMS</button>';
   str+='</div>';*/

 /*str+='<div class="row-fluid"> ';
 str+='<div class="span12 bs-docs-example form-inline"><label>Do you want to add new Name &amp; Contact number please enter and submit details: &nbsp;';
 str+='<input type="text" class="input-small" placeholder="First Name">';
 str+='<input type="text" class="input-small" placeholder="Last Name">';
 str+='<input type="password" placeholder="Phone Number" class="input-small">';
 str+=' <button type="submit" class="btn">submit</button>';

          
 str+='</label></div>';
 str+='</div><textarea rows="3" class="span9 m-top15" placeholder="Enter Your Message Here"></textarea>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-large btn-info" type="submit">Send SMS</button>';*/
 /*str+='<div class="row-fluid"><div class="span3"><a class="btn btn-mini btn-block" id="showOtherContact" onclick="showOtherContact();">Click Here to add new contact nummber</a></div></div>'; 
 str+='<div class="row-fluid" id="otherContactsDiv" style="display:none;">';
 str+='<div class="span12 bs-docs-example form-inline otherInfo">';
 //str+='<label>Do you want to add new Name &amp; Contact number please enter and submit details: &nbsp; ';
str+=' <input type="text" class="input-small firstname" placeholder="First Name" id="firstName" >&nbsp;&nbsp;';
str+='<input type="text" class="input-small lastName" placeholder="Last Name" id="lastName">&nbsp;&nbsp;';
str+='<input type="text" placeholder="Phone Number" class="input-small mobileNumber" id="mobileNumber" >&nbsp;&nbsp;';
str+='<button type="submit" class="btn" onclick="addOtherContact(\'otherContactsDiv\');">add&nbsp;&nbsp;</button>';
str+='<button type="submit" class="btn" onclick="deleteOtherContact(\'otherContactsDiv\');">delete&nbsp;&nbsp;</button>';
str+='<textarea rows="3" id="description" onkeyup="limitText(\'description\',\'maxcount\','+maxcount+')" class="span9 m-top15" placeholder="Enter Your Message Here" style="background:#fff;"></textarea>';
str+='</label></div></div>';*/

str+='<span id="alertMsg" style="color:red;"></span>';
str+='<textarea rows="3" id="description" class="span9 m-top15" placeholder="Enter Your Message Here" style="background:#fff;" onkeyup="limitText(\'description\',\'maxcount\','+maxcount+')"></textarea>';
str+='&nbsp;&nbsp; &nbsp;&nbsp;<button class="btn btn-small btn-info" type="submit" onclick="sendSMS()">Send SMS</button><img id="ajaxImage" src="./images/icons/search.gif" alt="Processing Image" style="display:none;float:right;"/>';
str+='<div id="limitDiv">';
str+='<table style="width:100%;"><tr>';
str+='<td style="width:50%;"><div id="remainChars" style="margin-left: 9px;">';
str+='<span id="maxcount">'+maxcount+' Characters</span> </div></td>';
//str+='<td style="width:50%;"><div>Remaining no. of characters</div></td>';
str+='</tr></table>';
str+='</div><br/><br/>';

//str+='&nbsp;&nbsp; &nbsp;&nbsp;<button class="btn btn-small btn-info" type="submit" onclick="getSmsDetails();">View SMS</button><img id="ajaxImage" src="./images/icons/search.gif" alt="Processing Image" style="display:none;float:right;"/>';
$("#userInfoDiv").html(str);

	}

	function showOtherContact()
	{
		$("#otherContactsDiv").css("display","block");
	}
	var k=0;
	function addOtherContact(id)
	{
	
	var str='';
	var divid = 'otherContactsDiv'+k+'';
	str+='<br/><br/><div class="row-fluid" id="otherContactsDiv'+k+'">';
	str+='<div class="span12 bs-docs-example form-inline otherInfo">';
  // str+='<label>Do you want to add new Name &amp; Contact number please enter and submit details: &nbsp; ';
	
	str+=' <input type="text" class="input-small firstname" placeholder="First Name" id="firstName'+k+'" >&nbsp;&nbsp;';
str+='<input type="text" class="input-small lastName" placeholder="Last Name" id="lastName'+k+'">&nbsp;&nbsp;';
str+='<input type="text" placeholder="Phone Number" class="input-small mobileNumber" id="mobileNumber'+k+'" >&nbsp;&nbsp;';
	
	str+='<button type="submit" class="btn" onclick="addOtherContact(\''+divid+'\')">add</button>&nbsp;&nbsp;';
	str+='<button type="submit" class="btn" onclick="deleteOtherContact(\''+divid+'\');">delete</button>&nbsp;&nbsp;';
	str+='</label></div></div>';
	$("#"+id+"").append(str);
	k++;
	}
	function deleteOtherContact(id)
	{
	$("#"+id+"").remove();
	}

function getSearchDetails()
{
$("#viewSmsDiv").html('');
var nameText = $("#nameSearch").val().trim();
var mobileText = $("#MobileSearch").val().trim();
/*$("#errorDivName").css("display","block");
$("#errorDivMobile").css("display","block");

var flag = true;
if(nameText.length > 0 && !isNaN(nameText))
{
		$("#errorDivName").text("Enter Name").css("color", "red");
		flag =false;
}
else
	{
$("#errorDivName").text("");
	}
if(mobileText.length > 0)
	{
		if(isNaN(mobileText)) {
		$("#errorDivMobile").text("Enter valid MobileNo").css("color", "red");
		flag =false;
		}
		else if(!(mobileText.length >=10 && mobileText.length<=12))
		{
		$("#errorDivMobile").text("Enter valid MobileNo").css("color", "red");
		flag =false;
		}

	}
	else
	{
$("#errorDivMobile").text("");
	}
if(flag == false)
return;
if(flag == true)
{
$("#errorDivName").css("display","none");
$("#errorDivMobile").css("display","none");
}*/
var jsObj=
{
typeId : 2,
namesearchText :nameText,
mobilesearchText :mobileText,
task : "getSmsDetails"
}
var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getSmsDetailsSeacrhAction.action?"+rparam;

callAjax(jsObj,url);

}
	
function isNumber(evt) {
evt = (evt) ? evt : window.event;
var charCode = (evt.which) ? evt.which : evt.keyCode;
if (charCode > 31 && (charCode < 48 || charCode > 57)) {
return false;
}
return true;
}

//sravanthi code



var count = 0;
function buildSmsDetails(myResults)
{
 if(myResults.length == 0)
{

$("#viewSmsDiv").html('No Data available');
$("#deleteAll").css("display","none");
return;
}
	
	if(myResults != null)
	{

	$("#viewSmsMainDiv").css("display","block");
	$("#viewSmsDiv").html('');
		$("#tableID").html('');
var str = "";
str += '<div id="successDiv"></div>';
str += '<table class="table table-striped table-bordered m-top15 clearfix" id="tableID"  style="float: left;">';
str += '<thead>';
 /*str+='<tr id="wonCandidatesTablehead">';
 str+=' <th><input id="parentViewSmsCheckbox" type="checkbox" /></th>';
		  str+=' <th>MESSAGE</th>';
		   str+='<th>	SENT ON</th>';
		  str+='<th>	NAME</th>';
		   str+='	<th>	CONTACT NUMBER</th>';
		    str+='<th>	RE-SEND</th>';
			 str+='<th>	FORWARD</th>';
			  str+='<th>	DELETE</th>';
		  str+='</tr>';*/

		  str+='<tr id="wonCandidatesTableSearch">';
		  str+=' <th><input id="parentViewSmsCheckbox" type="checkbox" /></th>';
str+=' <th >MESSAGE</th>';
str+='<th >SENT ON</th>';
str+=' <th > Incharge</th>';
//str+='<th > CONTACT NUMBER</th>';


str+=' <th > RE-SEND</th>';
str+='<th >FORWARD</th>';
str+=' <th >DELETE</th>';

str+='</tr>';

str+='</thead>';
str+='<tbody>';
for(var i in myResults){

str += '<tr id="row'+i+'" value="'+myResults[i].responseId+'">';
str+=' <td style="width: 20px;"><input type="checkbox" class="viewSmsCheckbox" value="'+myResults[i].responseCount+'" style="width:4px !imporatnt;"></td>';
str += '<td class="span8">'+myResults[i].description+'</td>';
str += '<td style="width: 114px;">'+myResults[i].dateSent+'</td>';
str+='<td style="width: 114px;">'+myResults[i].locationName+'</td>';
//str+='<td>'+myResults[i].numbers+'</td>';
str += '<td style="width: 114px;"><button class="btn btn-small" onClick="messageResend('+myResults[i].responseCount+',\''+myResults[i].description+'\','+myResults[i].numbers+');" ><i class="icon-repeat"></i> </button></td>';
str += '<td style="width: 114px;"><button type="submit" class="btn btn-small btn-success" id="forward'+i+'" onClick="messageForward(\'row'+i+'\','+myResults[i].responseId+',\''+myResults[i].description+'\','+myResults[i].numbers+',\'forward'+i+'\');" ><i class="icon-share icon-white"></i></button></td>';
str += '<td style="width: 114px;"><button type="submit" class="btn btn-small btn-danger" onClick="deleteRow('+myResults[i].responseCount+',\'forward'+i+'\');"><i class="icon-trash icon-white"></i> </button></td></tr>';

}
str += '</tbody>';

str+='</table>';
$("#viewSmsDiv").html(str);
$('#tableID').dataTable({
			"iDisplayLength": 15,
			"aaSorting": [[ 7, "desc" ]],
			
			"aoColumns":[
			{"bSortable": false},
			{"bSortable": false},
			{"bSortable": false},
			{"bSortable": false},
			{"bSortable": false},
			{"bSortable": false},
			{"bSortable": false}
			
				],
			"aLengthMenu": [[15, 30, -1], [15, 30, "All"]]

});

}
}

function getAssemblyConstituencies()
{
	var constituencyId = $('#pConstituencyList option:selected').val();
	var year = $('#electionYearField option:selected').val();
	var jsObj=
	{
		parliamentId   : 495,
		year           : 2012,
		task           : "getAssemblysForParliment"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getConstituenciesAndParties.action?"+rparam;	

	callAjax(jsObj,url);
}
function buildAssemblies(myResults)
{
	if(myResults != null)
	{
		var str = "";
		$("#aConstituencyList option").remove();
		str += '<option value="0">Select Location</option>';
		for(var i in myResults)
		{
			str += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
		}
		$('#Aconstituency').html(str);
	}
}



function getSmsDetails(){
	$("#viewSmsDiv").html('');
var jsObj=
	{
		typeId         : 2,
		constituencyId : 495,
		task           : "getSmsDetails"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSmsDetailsAction.action?"+rparam;	

	callAjax(jsObj,url);

}

 var j = 1;
 var count = 0;
function messageForward(value,id,msg,mobileno,fId)
{
	/*
	if($(".dataTable tr").closest('.odd').length > 0)
	$(".dataTable tr").closest('.odd').remove();*/

var arr = new Array();	
 arr = receiverSmsArray;

arr = jQuery.grep(arr , function (value) {
        return value.userId != id;
});
var arrUnique =new Array();
arrUnique = uniqBy(arr, JSON.stringify)

var str = '';
str += '<tr id="subrow'+value.match(/\d+/)+j+'">';
str += '<td></td><td class="span3" colspan="2"><textarea rows="3" class="comnClss" id="input'+value+'" type="text" style="background-color: white; width: 383px ! important; font-family: verdana;">'+msg+'</textarea></td>';
str += '<td><select id="mulSelect" multiple="multiple" style="height: 92px;">';
for ( var i in arrUnique) {
		str += '<option value='
				+ arrUnique[i].userId + '>'
				+ arrUnique[i].name + '</option>';
	}
str+='</select></td>';
str += '<td><span class="btn btn-small" onClick="forwardMessage('+id+',\'input'+value+'\','+mobileno+');"><i class="icon-share-alt"></i></span></td>';
str += '<td></td><td><span class="btn btn-small btn-danger"  onClick="deleteRow(\'subrow'+value.match(/\d+/)+j+'\',\''+fId+'\');"><i class="icon-trash icon-white"></i></span></td></tr>';
$('#'+value).after(str);

	$('#'+fId).attr("disabled", true);
	
}
   

function deleteRow(id,fId){
	var r=confirm("Are you sure to delete ?");
if (r)
{
var val = new Array();
val.push(id);
	if(isNaN(id)){
		$("#"+id).remove();
		$('#'+fId).attr("disabled", false);
		
	}
	else{
		var jsObj=
		{
			ids			   : val,
			task           : "deleteSmsDetails"
		}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "deleteSmsDetailsAction.action?"+rparam;	

		callAjax(jsObj,url);
	}
}
}

function messageResend(id,msg,mobileNo){
var userIds =new Array();
var obj ={
	receiverId :id,
	mobileNo:mobileNo
}
userIds.push(obj);

var jsObj=
{
userIds:userIds,
description : msg,
task:"reSendSms"
}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "reSendSmsToInformationManagers.action?"+rparam;
callAjax(jsObj,url);

}

var receiverIds = null;
function forwardMessage(id,msg,mobileNo){
var userIds =new Array();
var message = $('#'+msg).val();
receiverIds =new Array();
$('#mulSelect option:selected').each(function(i, selected){ 
 receiverIds[i] = $(this).val(); 
});
	if(receiverIds.length==0){
	setTimeout(function(){
		$("#successDiv").html("Please Select atleast one User..").css({"color":"red","width":"600px","float":"right"}).scrollTop();
		$('html, body,#successDiv').animate({scrollTop:208}, 'slow');
			setTimeout(function(){
				$("#successDiv").html('');
			},3000);
		},1000);
	return;
	}

var jsObj=
{
attributeIds:receiverIds,
	userIds:userIds,
description : message,
task:"forwardSms"
}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "forwardMailToInformationManagerAction.action?"+rparam;
callAjax(jsObj,url);

}
uniqBy = function(ary, key) {
    var seen = {};
    return ary.filter(function(elem) {
        var k = key(elem);
        return (seen[k] === 1) ? 0 : seen[k] = 1;
    })
}

function getSortedInfoByDate(date){
var jsObj=
{

date			:date,
typeId         : 2,
task:"datewiseSorting"
}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "datewiseSortingAction.action?"+rparam;
callAjax(jsObj,url);

}

function deleteAllRows(){

var r=confirm("Are you sure to delete ?");
if (r)
{

	if(!$('input.viewSmsCheckbox:checked').length > 0){
		setTimeout(function(){
		$("#successDiv").html("Please Select atleast one message..").css({"color":"red","width":"600px","float":"right"}).scrollTop();
		$('html, body,#successDiv').animate({scrollTop:308}, 'slow');
			setTimeout(function(){
				$("#successDiv").html('');
			},3000);
		},1000);
		return;
	}
	var total = new Array();
	$('input.viewSmsCheckbox:checked').each(function() {
				total.push($(this).val());
	});

		var jsObj=
		{
			ids			   : total,
			task           : "deleteSmsDetails"
		}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "deleteSmsDetailsAction.action?"+rparam;	

		callAjax(jsObj,url);
	
}

}