<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Voters </title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<!-- Then get JQuery UI -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script src="js/jQuery/image-crop/js/jquery.Jcrop.js" type="text/javascript"></script>
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="js/jQuery/image-crop/css/jquery.Jcrop.css" type="text/css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>	
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>
<style>
#valuesDisplayDiv, #casteCreation,#localityDiv ,#catrgoeryValuesMainDiv ,#groupCreation,#customVoterGroup{
border:1px solid #a1a1a1;
padding:10px 40px; 
width:560px;
border-radius:10px;
-moz-border-radius:25px;
margin-left:15px;
}

#casteCreation,#localityDiv,#customVoterGroup{margin-bottom:30px;}
</style>
<script type="text/javascript">
function openProblemEditSubForm(id,name)
{
	var urlStr="votersEditSubAction.action?id='"+id+"' name='"+name+"'";
	var updateBrowser1 = window.open(urlStr,"subEditAnnouncement","scrollbars=yes,height=300,width=400,left=400,top=400");	
	updateBrowser1.focus();	
}	

function clearSuccessMsg()
{
	var probSuccessMsg = document.getElementById("probSuccessMsgDiv");
	if(probSuccessMsg !=null)
	  probSuccessMsg.innerHTML='';
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
							
							if(jsObj.task == "getVoterCategories")
							{
							
								callCandidateUpdatePageAjax(myResults,jsObj.val);
							}
							else if(jsObj.task == "storeValues")
							{
								buildCategoryValues(myResults);
								 $("#groupCreationErr").html('');
								$("#groupCreationMsg").html("Group created successfully");
							}
							else if(jsObj.task == "getCategoryValues")
							{
								getCategoryValues(myResults);
							}
							else if(jsObj.task == "getCategoeryValues")
							{
								buildCategoeryValues(myResults);
							}
							else if(jsObj.task == "storeCategoeryValues")
							{
								$("#catrgoeryValue").val('');
                                $("#groupValueCreationMsg").html("Group value added successfully");
								getCategoeryValues();
							}
							else if(jsObj.task == "storeVoterDate")
							{
								
							}
							else if(jsObj.task == "saveCasteName")
							{
								showCasteStatus(myResults);
							}
							else if(jsObj.task == "getMandalList")
							{
								buildMandalList(myResults,jsObj);
										
							}
							else if(jsObj.task == "getHamletList")
							{
								buildTehsilList(myResults,jsObj);
										
							}

							else if(jsObj.task == "getWardList")
							{
							buildwardList(myResults,jsObj);	
							}
							else if(jsObj.task == "saveLocality")
							{
								showLocalityStatus(myResults);
										
							}
							else if(jsObj.task == "saveCategoeryValues")
							{
								showStatusOfCategoeryValues(myResults);			
							}	
							else if(jsObj.task == "deleteCategoeryValues")
							{
								showStatusOfDeletedCategores(myResults);		
							}
							else if(jsObj.task == "saveCustomVoterGroup")
							{
								showGroupStatus(myResults);
							}
							
							}catch(e){   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };
	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function callCandidateUpdatePageAjax(jsObj,url)
{
	var elmt = document.getElementById("UserCategoryValuesId");
	
	if( !elmt || optionsList == null)
		return;
		clearOptionsListForSelectElmtId("UserCategoryValuesId");
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].userCategoryValuesId;
		option.text=optionsList[i].userCategoryValuesName;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
}
function storeUpdatedValues()
{
cType = $('#userCategoryValuesId1 :selected').text();
cValue = $('#setValue').val();
typeValue = $('#userCategoryValuesId1').val();
if($('#userCategoryValuesId1 :selected').text() == "Others")
{
	$('#otherDiv').append('<input type="text" id="otherTextBox" style="margin-left:129px;" onChange="storeValue()"/>');
}
}
function addFieldsToMainFileds()
{

	var jsObj=
	{
		
		task:"getCategoryValues"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getVotersCategoryAction.action?"+rparam;						
		callAjax(jsObj,url);
}

function clearFields()
{
	$('#CreateNewGroupText').val("");
	
}
function storeValue()
{
	value = $('#otherTextBox').val();
	
}
function storeGroupValue()
{
    $("#groupCreationErr").html("");
	$("#groupCreationMsg").html("");
	var groupName = $('#CreateNewGroupText').val();
	 if($.trim(groupName).length == 0){
	    $("#groupCreationErr").html("Group Name is required");
		return;
	 }
	else if($.trim(groupName).length!=null){
			var iChars = "!`@#$%^&*()+=-[]\\\';,./{}|\":<>?~";  
	
		            for (var i = 0; i < groupName.length; i++)
                {      
                    if (iChars.indexOf(groupName.charAt(i)) != -1)
                    {   
						$("#groupCreationErr").html("Special Characters not allowed");
						return false;									
                    } 
                }
	 }
	 $("#groupCreationErr").html('&nbsp;');
		var jsObj=
			{
				name:groupName,
				task:"storeValues"
			};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "getVotersCategoryAction.action?"+rparam;						
				callAjax(jsObj,url);				
}

function buildCategoryValues(myResults)
{
	if(myResults.id != null && myResults.name != null)
	{
		$('#selectType').append('<option value='+myResults.id+'>'+myResults.name+'</option>');
	}
}
function getCategoryValues(myResults)
{
	$('#selectType option').remove();
	for(var i in myResults){
		$('#selectType').append('<option value='+myResults[i].id+'>'+myResults[i].name+'</option>');
	
	}
}

function storeCategoryValues()
{
	var categoryId = $('#selectType').val();
	var categoryValue = $('#catrgoeryValue').val();
	
	$("#groupValueCreationMsg").html("");
	if(categoryId == 0){
	  $("#groupValueCreationErr").html("Please select Group");
	   return;
	}
	 if($.trim(categoryValue).length == 0){
	    $("#groupValueCreationErr").html("Group Value is required");
		return;
	 }
	  else if($.trim(categoryValue).length!=null){
			var iChars = "!`@#$%^&*()+=-[]\\\';,./{}|\":<>?~";  
	
		            for (var i = 0; i < categoryValue.length; i++)
                {      
                    if (iChars.indexOf(categoryValue.charAt(i)) != -1)
                    {   
						 $("#groupValueCreationErr").html("Special Characters not allowed");
					return false; 								
                    } 
                }
	 }
	 
	$("#groupValueCreationErr").html('');
	$("#groupValueCreationMsg").html('&nbsp;');
	var jsObj =
	{
		categoryId:categoryId,
		categoryValue:categoryValue,
		task:"storeCategoeryValues"
	};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getVotersCategoryAction.action?"+rparam;						
		callAjax(jsObj,url);
}

function showCasteStatus(result)
{
	$("#casteId").val('');
	$("#casteCreationErr").html('');
	document.getElementById("userAccessStates_List").selectedIndex = 0;
	document.getElementById("castCategoryGroup_List").selectedIndex = 0;
	if(result.resultCode == 0)
	{
		$("#casteCreationMsg").html("Caste Saved Successfully.").css("color","green");
		return;
	}
	else if(result.resultCode == 1)
	{
		$("#casteCreationMsg").html("Caste already avaliable").css("color","red");
		return;
	}
	else
	{
		$("#casteCreationMsg").addClass('casteCreationMsg').html('Data could not be Saved due to some technical difficulties').css('color','red');
		return;
	}
}

function showLocalityStatus(result)
{
$("#localityId").val('');
if(result.resultCode == 0)
	{
		$("#errorMsg").html("Locality Saved Successfully.").css("color","green");
		
		return;
	}
	else
	{
		$("#errorMsg").addClass('casteCreationMsg').html('Data could not be Saved due to some technical difficulties').css('color','red');
		return;
	}
	
}

function showGroupStatus(result)
{
if(result.resultCode == 121)
	{
$("#grouperrorMsg").html("Group Name already Exists").css("color","red");
return;
	}
else if(result.resultCode == 0)
	{
		$("#grouperrorMsg").html("Group Name Saved Successfully.").css("color","green");
		
		return;
	}
	else
	{
		$("#grouperrorMsg").addClass('casteCreationMsg').html('Data could not be Saved due to some technical difficulties').css('color','red');
		return;
	}
}
</script>
</head>
<body style="position: relative;">
<br><br>
	<div id="popupDiv" style="float: left;margin-left:6px;">
		 <div id="groupCreation" style="border:" >
		 <h4>Create New Group</h4>
		   <div style="margin-bottom:5px;"><b>NOTE :</b> Group Name should not contain any special characters</div>
		  <div style="color:red" id="groupCreationErr">&nbsp;</div>
		  <div style="color:green" id="groupCreationMsg"></div>
		 <lable name="CreateNewGroup"><b>Group Name<span style="color:red">*</span> :</b> 
		 <input type="text" id="CreateNewGroupText" style="width: 175px;margin-left:30px;"></input></lable>
		 <input type="button" value="create" class="btn btn-success" style="float:right;margin-top:4px;"onClick="storeGroupValue();clearFields();"></input>
	 </div>
	 </br></br>
	 
		<div id="valuesDisplayDiv" >
		   <h4>Add Values To Group</h4>
		   <div style="margin-bottom:5px;"><b>NOTE :</b> Group Value should not contain any special characters</div>
		   
			<div><b>Select Group<span style="color:red">*</span> :</b><select id="selectType" style="width:187px;margin-left:32px;" onChange="getCategoeryValues();"></select></div>
		</div>
		<div id="catrgoeryValuesMainDiv" style="margin-top:30px;display:none;">
			<div style="color:red" id="groupValueCreationErr">&nbsp;</div>
			<div style="color:green" id="groupValueCreationMsg"></div>
			<div id="catrgoeryValuesDiv"></div>
			<div><!--<b>Group value<span style="color:red">*</span> :</b> <input type = "text" id="catrgoeryValue" style="width: 175px;margin-left:33px;"></input>
			<b>Order No<span style="color:red">*</span> :</b>
			<input type="text" id="orderNumberId" style="width:20px"></input>-->
			<input class="btn btn-success" type="submit" value="Add More" onClick="getMoreRecoredsToStore();" style ="float:right;margin-top:-30px;margin-right:143px;"></input></div>
			<div>
			<input type="submit" class="btn btn-success" value="submit" onClick="validationForCategoeryValues();"></input>
			<input type="button" class="btn btn-success" value="Delete" onClick="deleteAllCategoeryValues();"></input>
			</div>
		</div>

		<br><br>
		<div id="popupDiv" style="float: left;margin-left:6px;">
		 <div id="casteCreation" style="border:" >
		 <h4>Create Caste</h4>
		   <div style="margin-bottom:5px;"><b>NOTE :</b> Caste Name should not contain any special characters</div>
		  <div style="color:red" id="casteCreationErr">&nbsp;</div>
		  <div style="color:green" id="casteCreationMsg"></div>
		<table cellpadding="3">
			<tr>
				<td><b>States <span style="color:red">*</span></b></td>
				<td><b>:</b></td>
				<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select " name="userAccessStates" id="userAccessStates_List" list="userAccessStates" listKey="id" listValue="name" headerKey="0" headerValue="Select"></s:select></td>
			</tr>
			<tr>
				<td><b>Cast Category Group <span style="color:red">*</span></b></td>
				<td><b>:</b></td>
				<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select " name="castCategoryGroupList" id="castCategoryGroup_List" list="castCategoryGroupList" listKey="id" listValue="name" headerKey="0" headerValue="Select"></s:select></td>
			</tr>
			<tr>
				<td><b>Caste Name<span style="color:red">*</span></b></td>
				<td><b>:</b></td>
				<td><input type="text" id="casteId" style="width: 175px;"></input></td>
			</tr>
		</table>

		 <!-- <b>States <span style="color:red">*</span> :</b> <s:select cssClass="selectBoxWidth" theme="simple" label="Select " name="userAccessStates" id="userAccessStates_List" list="userAccessStates" listKey="id" listValue="name" headerKey="0" headerValue="Select"></s:select>
		 <br>
		 <b>Cast Category Group <span style="color:red">*</span> :</b><s:select cssClass="selectBoxWidth" theme="simple" label="Select " name="castCategoryGroupList" id="castCategoryGroup_List" list="castCategoryGroupList" listKey="id" listValue="name" headerKey="0" headerValue="Select"></s:select>
		 <br>
		  <lable name="Caste Name"><b>Caste Name<span style="color:red">*</span> :</b> 
		 <input type="text" id="casteId" style="width: 175px;margin-left:30px;"></input></lable> -->
		
		 <input type="button" value="create" class="btn btn-success" style="clear: both; float: right; margin-top: -30px;" id="createCaste"></input>
	 </div>
	 
	 <!-- Locality Div start-->
	 <div id="popupDiv" style="float: left;margin-left:6px;">
	 <div id="localityDiv" style="border:">
	<h4>Create Locality</h4>
			<div id="ConstituencyDiv" class="selectDiv">
				<div id="errorMsg"></div>
				<b>Select Constituency</b><font style="color:red">*</font><s:select theme="simple" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" style="margin-left:27px;" onchange="getMandalList();"/> &nbsp;&nbsp;
			</div>
		<div id="mandalDiv" class="selectDiv" >
		
			<b>Select Mandal</b><font style="color:red">*</font> <select id="mandalField" class="selectWidth" name="mandal" style="margin-left:63px;" onchange="getHamletsInATehsil();"></select>
		</div>
		
		
		<div id="tehsilDiv" class="selectDiv" style="display:none;">
		
		<b>Select Hamlet</b><font style="color:red">*</font> <select id="hamletField" class="selectWidth" name="hamlet" style="margin-left:66px;"></select>
		</div>
		
		<div id="wardDiv" class="selectDiv" style="display:none;">
		
		<b>Select Ward</b><font style="color:red">*</font> <select id="wardField" class="selectWidth" name="ward" style="margin-left:78px;"></select>
		</div>
		
		
			<div id="localityNameDiv"><b>Name</b><span style="color:red">*</span>
				
			 <input type="text" id="localityId" style="width: 175px;margin-left:123px;"></input>
			
			</div>
			
		<input type="button" onclick="saveLocality();" value="create" class="btn btn-success" style="clear: both; float: right; margin-top: -30px;" id="createLocality"></input>
		
	 </div>
	 </div>
	 <!-- Locality Div End-->

	 
	 <!--Custom Voter Group Div Start-->
	  <div id="popupDiv" style="float: left;margin-left:6px;">
	
	 <div id="customVoterGroup" style="border:">
	<h4>Create Voter Custom Group</h4>

			<div id="ConstituencyDiv" class="selectDiv">
				<div id="grouperrorMsg"></div>
				<b>Select Constituency</b><font style="color:red">*</font><s:select theme="simple" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="customVoterConstituency" list="constituencyList" listKey="id" listValue="name" style="margin-left:27px;" onchange="getMandalListForCustomVoter();"/> &nbsp;&nbsp;
			</div>
		<div id="mandalDiv" class="selectDiv" >
		
			<b>Select Mandal</b><font style="color:red">*</font> <select id="customVotermandal" class="selectWidth" name="mandal" style="margin-left:63px;"></select>
		</div>
		
		
			<div id="groupNameDiv"><b>custom group Name</b><span style="color:red">*</span>
				
			 <input type="text" id="groupName" style="width: 175px;margin-left:23px;"></input>
			
			</div>
			
		<input type="button" onclick="saveCustomVoterGroup();" value="create" class="btn btn-success" style="clear: both; float: right; margin-top: -30px;" id="createGroup"></input>
		</div>
		</div>
		 <!--Custom Voter Group Div  End-->
		 
	  <!-- Validation Div start-->
	 <div id="popupDiv" style="float: right;clear:both;">
	 <a href="validationToolsAction.action"><input type="button" value="Get Hamlet Assigned Info" class="btn btn-success" style="clear: both; float: right; margin-top: -26px;" id="getHamletInfo"></input></a>
	</div>
	 <br>
	 <!-- Validation Div End-->
	</div>
<script type="text/javascript">
$(document).ready(function() {
 addFieldsToMainFileds();

 $("#createCaste").click(function(){
	var casteName = $.trim($("#casteId").val());
	var stateId = $("#userAccessStates_List").val();
	var categoryId = $("#castCategoryGroup_List").val();
	$("#casteCreationMsg").html('');
	var validCharacters = /[a-zA-Z]/;

	 if(stateId == 0)
	 {
		$("#casteCreationErr").html('Please Select State.');
		return;
	 }
	 else if(categoryId == 0)
	 {
		$("#casteCreationErr").html('Please Select Caste Category Group.');
		return;
	 }
	else if(casteName == '' || casteName.length == 0)
	 {
		$("#casteCreationErr").html('Caste Name is Required.');
		return;
	 }
		else if($.trim(casteName).length!=null){	
		
		var iChars = "!`@#$%^&*()+=-[]\\\';,./{}|\":<>?~";  
	
		            for (var i = 0; i < casteName.length; i++)
                {      
                    if (iChars.indexOf(casteName.charAt(i)) != -1)
                    {   
					 $("#casteCreationErr").html('Special Characters not allowed');
					return false; 
					}
				}
	 }

	 $("#casteCreationErr").html('&nbsp;');
	 var jsObj=
	{
		casteName        : casteName,
		stateId          : stateId,
		casteCateGroupId : categoryId,
		task:"saveCasteName"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveCasteNameAction.action?"+rparam;						
		callAjax(jsObj,url);
 });
});

	function getMandalList()
	{
		var constituencyID = document.getElementById("constituencyList");
		var name=constituencyID.options[constituencyID.selectedIndex].name;
		var value=constituencyID.options[constituencyID.selectedIndex].value;
		var choice=false;
		
		if(value == 0)
		{
			return false;
		}
		var jsObj=
			{
					
					selected:value,
					select : "mandalField",
					task:"getMandalList"
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
		
	}
	
	function getMandalListForCustomVoter()
	{
	var constituencyID = document.getElementById("customVoterConstituency");
		var name=constituencyID.options[constituencyID.selectedIndex].name;
		var value=constituencyID.options[constituencyID.selectedIndex].value;
		var choice=false;
		
		if(value == 0)
		{
			return false;
		}
		var jsObj=
			{
					
					selected:value,
					select : "customVotermandal",
					task:"getMandalList"
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	function getHamletsInATehsil()
	{
		
		var errorDiv = document.getElementById('errorMsg');
		errorDiv.innerHTML ='';
		var mandalField = document.getElementById("mandalField");
		var task ;
		var tehsilId=mandalField.options[mandalField.selectedIndex].value;
			
		var choice=false;
		var type;
		if(tehsilId == 0)
		{
			return false;
		}
		if(tehsilId.charAt(0) == "1")
		{
			document.getElementById("tehsilDiv").style.display = 'none';
			document.getElementById("wardDiv").style.display = 'block';
			task = "getWardList";
			
			
		}
		if(tehsilId.charAt(0) == "2")
		{
			
			document.getElementById("tehsilDiv").style.display = 'block';
			document.getElementById("wardDiv").style.display = 'none';
			task="getHamletList";
			
		}
		tehsilId = tehsilId.substring(1);
		var jsObj=
			{
					
					tehsilId:tehsilId,
					type:type,
					task:task
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getHamletsInATehsilAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
		
	}
	
	
	
	
	function buildMandalList(results,jsObj)
	{
		var selectBoxId = jsObj.select;
		var selectElmt =document.getElementById(selectBoxId);
		
		removeSelectElements(selectElmt);
		for(var val in results)
		{
			var opElmt = document.createElement('option');
			opElmt.value=results[val].id;
			opElmt.text=results[val].name;

			try
			{
				selectElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectElmt.add(opElmt); // IE only
			}	
		}
	}

	function buildTehsilList(results,jsObj)
	{
		
		var selectElmt =document.getElementById("hamletField");
		
		removeSelectElements(selectElmt);
		for(var val in results)
		{
			var opElmt = document.createElement('option');
			opElmt.value=results[val].id;
			opElmt.text=results[val].name;

			try
			{
				selectElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectElmt.add(opElmt); // IE only
			}	
		}
	}

	function buildwardList(results,jsObj)
	{
		
		var selectElmt =document.getElementById("wardField");
		
		removeSelectElements(selectElmt);
		for(var val in results)
		{
			var opElmt = document.createElement('option');
			opElmt.value=results[val].id;
			opElmt.text=results[val].name;

			try
			{
				selectElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectElmt.add(opElmt); // IE only
			}	
		}
	}
	function removeSelectElements(selectedElmt)
	{
		var len = selectedElmt.length;
		for(var i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}
	}	


	function saveLocality()
	{
	var str ='';
	var localbodyId = $("#mandalField").val();
	var hamletID = 0;
	var wardID =0;
	var name =$("#localityId").val();
	var errorDiv = document.getElementById('errorMsg');
	if(localbodyId.charAt(0) == "2")
		{
	 hamletID = $("#hamletField").val();

		}
	else if(localbodyId.charAt(0) == "1")
		{
		wardID = $("#wardField").val();
		}
		if(localbodyId == 0)
		{
			str +='<font color="red">Select mandal Name</font>';
			errorDiv.innerHTML = str;
			return;
		}
		 else if(name == "")
		 {
			str +='<font color="red">Name should not be empty</font>';
			errorDiv.innerHTML = str;
			return;
		}
		
		else
		errorDiv.innerHTML = '';	
		
	var jsObj=
	{
		
		localbodyId     : localbodyId,
		name            : name,
		hamlet			: hamletID,
		ward			: wardID,
		task			: "saveLocality"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveLocalityAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	function saveCustomVoterGroup()
	{
	var str = '';
	var errorDiv = document.getElementById("grouperrorMsg");
	var constituencyId = $("#customVoterConstituency").val();
	var name = $("#groupName").val();
	var locationValue = $("#customVotermandal").val();
	if(constituencyId == 0)
		{
			str +='<font color="red">Select Constituency Name</font>';
			errorDiv.innerHTML = str;
			return;
		}
		else if(locationValue == 0)
		{
			str +='<font color="red">Select Mandal/Muncipality Name</font>';
			errorDiv.innerHTML = str;
			return;
		}
		else if(name == "")
		 {
			str +='<font color="red">Name should not be empty</font>';
			errorDiv.innerHTML = str;
			return;
		}
		else
	errorDiv.innerHTML = '';
	var jsObj=
	{
		
		constituencyId  : constituencyId,
		locationValue   : locationValue,
		groupName		: name,
		task			: "saveCustomVoterGroup"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveCustomVoterGroupAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	/*
		This Method is used For making a ajax call to get All Categoery Values
	*/
	function getCategoeryValues()
	{
		var categoryId = $('#selectType').val();
		//var categoryValue = $('#catrgoeryValue').val();
		var jsObj =
		{
			categoryId:categoryId,
			//categoryValue:categoryValue,
			task:"getCategoeryValues"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getVotersCategoryAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	/*
		This method is used for building the categoery values in table formet
	*/
	function buildCategoeryValues(myResults)
	{
		if(myResults != null && myResults.length > 0)
		{
			$('#catrgoeryValuesMainDiv').show();
			var str = "";
			var catgegoeryString = document.getElementById('catrgoeryValuesDiv');
			str += '<table>';
			str += '<tr><div><th style="margin-left:20px;"><span>Select</span></th>';
			str += '<th style="margin-left:20px;"><span>Group Values</span></th>';
			str += '<th><span>Order No</span></th></div></tr>';
			str += '</br>';
			for(var i in myResults)
			{
				str += '<tr class="categoerysList"><div>';
				str += '<td><span><input type="checkbox" class="selectedValuesChk" checked="true" value="'+myResults[i].id+'"> </input></span></td>';
				if(myResults[i].name != null)
				{
					str += '<td><span><input type="text" class="groupValueId" value="'+myResults[i].name+'" style="width:150px;" ></input></span></td>';
				}
				else
				{
					str += '<td><span><input type="text" class="groupValueId" value="" style="width:150px;" ></input></span></td>';
				}
				if(myResults[i].orderId != null)
				{
					str += '<td><span><input type="text" class="orderId" value="'+myResults[i].orderId+'" style="width:20px" ></input></span></td></div></tr>';
				}
				else
				{
					str += '<td><span><input type="text" class="orderId" value="" style="width:20px" ></input></span></td></div></tr>';
				}
			}
			str += '</table>';
			catgegoeryString.innerHTML = str;
		}
		else
		{
			$('#catrgoeryValuesMainDiv').show();
			var str = "";
			var catgegoeryString = document.getElementById('catrgoeryValuesDiv');
			str += '<table>';
			str += '<tr><div><th><span>Select</span></th>';
			str += '<th><span>Group Values</span></th>';
			str += '<th><span>Order No</span></th></div></tr>';
			str += '</br></table>';
			catgegoeryString.innerHTML = str;
			getMoreRecoredsToStore();
		}
		
	}
	/*
		This Method is used For making a ajax call to store selected Categoery Values
	*/
	function storeAllCategoeryValues()
	{
		var categoryValues = new Array();
		var id = $('#selectType').val();
		$('.categoerysList').each(function () {
		
		var categoryCheckVal  = $(this).closest("tr").find(".selectedValuesChk").is(':checked');
		if(categoryCheckVal == true)
		{
			var categoryId = $(this).closest("tr").find(".selectedValuesChk").val();
			var categoryname = $(this).closest("tr").find(".groupValueId").val();
			var orderNos     = $(this).closest("tr").find(".orderId").val();
			var categoeryDetails = {
			categoryId   : categoryId,
			categoryname : categoryname,
			orderNos     : orderNos
		}
		categoryValues.push(categoeryDetails);
		}
		
		
		});
		var jsObj=
		{
			categoryValues       : categoryValues,
			categoeryId          : id,
			task	             : "saveCategoeryValues"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getVotersCategoryAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	/*
		This Method is used For making a ajax call to Delete selected Categoery Values
	*/
	function deleteAllCategoeryValues()
	{
		var categoryId = $('#selectType').val();
		var categoryValues = new Array();
		$('.categoerysList').each(function () {
		var categoryCheckVal  = $(this).closest("tr").find(".selectedValuesChk").is(':checked');
			if(categoryCheckVal == true)
			{
				var categoeryId =  $(this).closest("tr").find(".selectedValuesChk").val();
			
				var categoeryDetails = {
				categoeryId : categoeryId
				}
				categoryValues.push(categoeryDetails);
			}
		});
		var jsObj=
		{
			categoryValues       : categoryValues,
			categoryId           : categoryId,
			task	             : "deleteCategoeryValues"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getVotersCategoryAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	/*
		this method is used for show the result state of a selected categoery values for delete
	*/
	function showStatusOfDeletedCategores(results)
	{
		if(results != null && results.length > 0)
		{
			var str = "";
			if(results[0].value == null)
			{
				
				for(var i in results)
				{
					str += '<div>';
					str += '<span>Group Value </span>';
					str += '<span style="color:red;">'+results[i].name+'</span>';
					str += '<span> is Already Assigned  To Voter.</span>';
					str += '<div>';
				}
				$('#groupValueCreationMsg').html(str);
				$('#groupValueCreationMsg').show().delay("5000").hide('slow');
			}
			else
			{
				str += '<div>';
				str += '<span style="color:red">Successfully Deleted</span>';
				str += '</div>';

				$('#groupValueCreationMsg').html(str);
				$('#groupValueCreationMsg').show().delay("1000").hide('slow');
			}
		}
	}
	/*
		This method is used for adding more records for adding new caregoery values.
	*/
	function getMoreRecoredsToStore()
	{
		
		var categoryId = $('#selectType').val();
		if(categoryId != 0)
		{
			var str = "";
			for( var i = 0 ; i < 1 ; i++)
			{
				str += '<tr class="categoerysList"><div>';
					str += '<td><span><input type="checkbox" class="selectedValuesChk" checked="true" value="0"> </input></span></td>';
					str += '<td><span><input type="text" class="groupValueId"  style="width:150px;margin-left:30px;" ></input></span></td>';
					str += '<td><span><input type="text" class="orderId"  style="width:20px" ></input></span></td>';
					str += '</div></tr>';
			}
			str += '</table>';
			$('#catrgoeryValuesDiv').append(str);
		}
		else
		{
			$('#catrgoeryValuesMainDiv').hide();
		}
	}
	/*
		This method is used for validating the all fields to submit the categorey values.
	*/
	function validationForCategoeryValues()
	{
		var str = "";
		var flag = true;
		var count = 0;
		$('.categoerysList').each(function () {
		var categoryCheckVal  = $(this).closest("tr").find(".selectedValuesChk").is(':checked');
		if(categoryCheckVal == true)
		{
			count++;
			var categoryname = $(this).closest("tr").find(".groupValueId").val();
			var orderNo     = $(this).closest("tr").find(".orderId").val();
			if(categoryname == '')
			{
				/* $('#groupValueCreationErr').html('<span>please enter Group Value</span>');
				$('#groupValueCreationErr').show().delay("2000").hide('slow'); */
				str += '<span>please enter Group Value</span></br>';
				flag = false;
			}
			if(categoryname.length > 0)
			{
				if(/[^a-z A-Z]/.test(categoryname))
				{
					/* $('#groupValueCreationErr').html('<span>Group Value Accepts Only Characters</span>');
					$('#groupValueCreationErr').show().delay("2000").hide('slow'); */
					str += '<span>Group Value Accepts Only Characters</span></br>';
					flag = false;
				}
				
			}
			
			if($.trim(orderNo) == '')
			{
				/* $('#groupValueCreationErr').html('<span>please enter Order No</span>');
				$('#groupValueCreationErr').show().delay("2000").hide('slow'); */
				str += '<span>please enter Order No</span></br>';
				flag = false;
			}
			
			if(isNaN(orderNo) == true)
			{
				/* $('#groupValueCreationErr').html('<span>Order No Accepts Only Numbers</span>');
				$('#groupValueCreationErr').show().delay("2000").hide('slow'); */
				str += '<span>Order No Accepts Only Numbers</span></br>';
				flag = false;
			}
			if($.trim(orderNo) < 1)
			{
					str += '<span>Please Enter a Valid Order Number.</span></br>';
				flag = false;
			}
			if($.trim(orderNo).length > 2)
			{
				/* $('#groupValueCreationErr').html('<span>Order No Accepts Only Two Digits</span>');
				$('#groupValueCreationErr').show().delay("2000").hide('slow'); */
				str += '<span>Order No Accepts Only Two Digits</span></br>';
				flag = false;
			}
			/* if(categoryname.length > 0)
			{
			   if(!checkForDuplicateGroupValues())
			    flag = false;
			}
			if(orderNo.length > 0)
			{
				if(!ckeckForDuplicateOrderIds())
				flag = false;
			} */
			
		}
		});
		if(!checkForDuplicateGroupValues())
		{
			str += '<span>Group Name is Already Exists</span></br>';
			flag = false;
		}
		if(!ckeckForDuplicateOrderIds())
		{
			str += '<span>Order No is Already Exists</span></br>';
			flag = false;
		}
		if(!chechForOrderNumbers())
		{
			str += '<span>Please Enter Order Numbers in a Serial Manner</span></br>';
			flag = false;
		}
		if(count == 0)
		{
			/* $('#groupValueCreationErr').html('<span>Please Select Atleast one Check Box</span>');
			$('#groupValueCreationErr').show().delay("2000").hide('slow'); */
			str += '<span>Please Select Atleast one Check Box</span></br>';
				flag = false;
		}
		$('#groupValueCreationErr').html(str);
		if(flag)
		{
			storeAllCategoeryValues();
		} 
	}
	/*
		this method is used for show the saved status of a categoery values.
	*/
	function showStatusOfCategoeryValues(results)
	{
		if(results.resultCode == 0)
		{
			$('#groupValueCreationMsg').html('<span>Successfully Saved</span>');
			$('#groupValueCreationMsg').show().delay("1000").hide('slow');
		}
	}
	/*
		this method is used for checking for duplicate Group names in the list of all group names.
	*/
	function checkForDuplicateGroupValues()
	{
		var groupName = new Array();
		$('.categoerysList').each(function () {
		var categoryCheckVal  = $(this).closest("tr").find(".selectedValuesChk").is(':checked');
		var categoryname = $(this).closest("tr").find(".groupValueId").val();
		groupName.push($.trim(categoryname));
		});
		for(var i = 0 ; i < groupName.length ; i++)
		{
			for(var j = 0 ; j < groupName.length ; j++)
			{
				if(i != j)
				{
					if(groupName[i] == groupName[j])
					{
						/* $('#groupValueCreationErr').html('<span>Group Name is Already Exists</span>'); */
						
						return false;
					}
				}
			}
		}
		return true;
	}
	/*
		this method is used for checking for duplicate Order numbers in the list of selected Order numbers..
	*/
	function ckeckForDuplicateOrderIds()
	{
		var orderIdsArray = new Array();
		$('.categoerysList').each(function () {
		var categoryCheckVal  = $(this).closest("tr").find(".selectedValuesChk").is(':checked');
		var orderId = $(this).closest("tr").find(".orderId").val();
		orderIdsArray.push($.trim(orderId));
		});
		for(var i = 0 ; i < orderIdsArray.length ; i++)
		{
			for(var j = 0 ; j < orderIdsArray.length ; j++)
			{
				if(i != j)
				{
					if(orderIdsArray[i] == orderIdsArray[j])
					{
						/* $('#groupValueCreationErr').html('<span>Order No is Already Exists</span>'); */
						
						return false;
					}
				}
			}
		}
		
		return true;
	}
	/*
		This method is used for checking for order numbers in a serial number or not....
	*/
	function chechForOrderNumbers()
	{
		var flag = true;
		var orderIdsArray = new Array();
		$('.categoerysList').each(function () {
		var categoryCheckVal  = $(this).closest("tr").find(".selectedValuesChk").is(':checked');
		if(categoryCheckVal == true)
		{
			var orderId = $(this).closest("tr").find(".orderId").val();
			orderIdsArray.push($.trim(orderId));
		}
		});
		for(var i = 0 ; i < orderIdsArray.length ; i++)
		{
			if(orderIdsArray[i] > orderIdsArray.length)
			{
				flag = false;
			}
			/* if(orderIdsArray.indexOf(orderIdsArray[i]) == -1)
			{
				flag = false;
			} */
			
		}
		return flag;
	}
	
	
</script>
</body>
</html>