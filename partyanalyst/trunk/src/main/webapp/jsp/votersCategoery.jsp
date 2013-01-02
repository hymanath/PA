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
#valuesDisplayDiv{
border:1px solid #a1a1a1;
padding:10px 40px; 
width:590px;
height:170px;
border-radius:25px;
-moz-border-radius:25px;
}
#groupCreation
{
border:1px solid #a1a1a1;
padding:10px 40px;
height:100px; 
border-radius:25px;
-moz-border-radius:25px;
}
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
							}
							else if(jsObj.task == "getCategoryValues")
							{
								getCategoryValues(myResults);
							}
							else if(jsObj.task == "storeCategoeryValues")
							{
							
							}
							else if(jsObj.task == "storeVoterDate")
							{
								
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
	var groupName = $('#CreateNewGroupText').val();
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
function closeWindow()
{
	window.close();
}
</script>
</head>
<body style="position: relative;">
<br><br>
	<div id="popupDiv" style="float: left;">
		 <div id="groupCreation" style="border:" >
		 <h4>Cerate New Group</h4>
		 <lable name="CreateNewGroup">CreateNewGroup : 
		 <input type="text" id="CreateNewGroupText" style="width: 175px;margin-left:30px;"></input></lable>
		 <input type="button" value="create" class="btn btn-success" style="float:right;margin-top:4px;"onClick="storeGroupValue();clearFields();"></input>
	 </div>
	 </br></br>
	 
		<div id="valuesDisplayDiv" >
		   <h4>Add Values To Group</h4>
			Select Type:<select id="selectType" style="width:187px;margin-left:78px;"></select>
			</br>
			Group value: <input type = "text" id="catrgoeryValue" style="width: 175px;margin-left:69px;"></input>
			</br>
			<input class="btn btn-success" type="submit" value="Add" onClick="storeCategoryValues();closeWindow();"; style ="float:right;margin-top:-30px"></input>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function() {
 addFieldsToMainFileds();
});
</script>
</body>
</html>