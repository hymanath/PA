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
 h1 {
    font: 1.2em Arial, Helvetica, sans-serif;
}
 
input.txt {
    color: #00008B;
    /*background-color: #E3F2F7;*/
    border: 1px solid #c3c3c3;
    width: 200px;
	border-radius:2px;
	height:17px;
}

select {
    color: #00008B;
    /*background-color: #E3F2F7;*/
    border: 1px solid #c3c3c3;
    width: 212px;
	height:27px;
	border-radius:2px;
}
 
input.btn {
    color: #00008B;
    background-color: #ADD8E6;
    border: 1px outset #00008B;
}
 
form {
    width: 40%;
	margin-left:250px;
}
 
form div {
    clear: left;
    margin: 0;
    padding: 0;
    padding-top: 0.6em;
}
 
form div label {
    float: left;
    width: 40%;
    font: bold 0.9em Arial, Helvetica, sans-serif;
}

.spanClass {
    float: left;   
    font-weight: bold;
	font-family:verdana;
	width: 140px;
}
 fieldset {
    border: 1px dotted #61B5CF;
    margin-top: 1.4em;
    padding: 0.6em;
	width: 130%;
}
 
.legendClass {
    font: bold 0.8em Arial, Helvetica, sans-serif;
    color: #00008B;
    background-color: #FFFFFF;
	border-style:none ;
	margin-bottom:0px;
	width:42%;
	font-weight:bold;
	font-size:16px;
	text-align:center;
}
.error{
	color:red;
	font-size:12px;
}

.template {display:none;}

 
 </style>
 
 <script type="text/javascript">
 
     $(function() {

	
	$( "#setValue" ).autocomplete({
            source: function( request, response ) {
			var value = $('#setValue').val();
				var userCategoryValues =$("#userCategoryValuesId1").val();
			    var jsobjn={
							letters:request.term,
							voterCategory:userCategoryValues,
							task:"getVoterCategories"
							};
                $.ajax({
                    url: "getVotersCategoryAction.action",
                    dataType: "json",
                    data: {
					    task:YAHOO.lang.JSON.stringify(jsobjn)									
                    },
                    success: function( data ) {

                        response( $.map( data, function( item ) {
                            return {
                                label: item.name,
                                value: item.name,
								id:item.id
                            }
                        }));
						
                    }
                });
            },
            minLength: 1,
           open: function() {
                $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
            },
            close: function() {
                $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
            }
        });
    });

 
 
 
function openProblemEditSubForm(id,name)
{
	var urlStr="votersEditSubAction.action?id='"+id+"' name='"+name+"'";
	var updateBrowser1 = window.open(urlStr,"subEditAnnouncement","scrollbars=yes,height=300,width=400,left=400,top=400");	
	updateBrowser1.focus();	
}	

 function clearSuccessMsg(){
	var probSuccessMsg = document.getElementById("probSuccessMsgDiv");
	if(probSuccessMsg !=null)
	  probSuccessMsg.innerHTML='';
}
function callAjax(jsObj,url){
		var callback = {			
				   success : function( o ) {
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

function callCandidateUpdatePageAjax(jsObj,url){
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
function openPopUp(){
$('#popupDiv').dialog({
			width:600,
			'title':'Voter Groups...'
		});
		clearFields();
	
}
function storeUpdatedValues(){
cType = $('#userCategoryValuesId1 :selected').text();
cValue = $('#setValue').val();
typeValue = $('#userCategoryValuesId1').val();
if($('#userCategoryValuesId1 :selected').text() == "Others")
{
	$('#otherDiv').append('<input type="text" id="otherTextBox" style="margin-left:129px;" onChange="storeValue()"/>');
	
}
}

function addFieldsToMainFileds(){

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
	$('#userCategoryValuesId1').val("Select Category");
	$('#setValue').val("");
	$('#otherTextBox').hide();
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

function storeCategoryValues(){
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

function addFieldsToMainDiv()
{
	name = $('#selectType :selected').text();
	value = $('#catrgoeryValue').val();
	
	$('#addFieds').append('<div><lable>'+name+':</lable><select value="'+value+'" id="0"  style="width: 165px; margin-left:131px;"></select></div>');
	$('#popupDiv').dialog("close");
}

 </script>
</head>
<body style="position: relative;">
<br><br>

<form id="voterDetailsForm" name="voterDetailsForm" method="POST" action="voterEditAction.action">

<c:if test="${requestScope.resultStr=='success'}">
<div id="probSuccessMsgDiv">

<DIV id="alertMessage" style="color:green;font-weight:bold;margin:5px;">Updated Successfully...</DIV>
</div>
</c:if>
<input type="hidden" name="boothId" value="${voterHouseInfoVO.boothId}"/>
<input type="hidden" name="voterId" value="${voterHouseInfoVO.voterId}"/>
<input type="hidden" name="voterHouseInfoVO.userId" value="${voterHouseInfoVO.userId}"/>
<input type="hidden" name="voterHouseInfoVO.userVoterDetailsId" value="${voterHouseInfoVO.userVoterDetailsId}"/>
<div id="mainDiv" style="float: right;">
<a style="color:red;float:right;" onClick="openPopUp();addFieldsToMainFileds();"><b>CreateNewCategory</b></a>
 <fieldset style="width: 335px;">
       <legend class="legendClass">Voter Information</legend>
 		<div>
			<label for="name">Voter Name:</label> 
			<input type="text" style="width: 165px;" name="voterHouseInfoVO.name" value="${voterHouseInfoVO.name}" readonly='true'/>
		</div>

		<div>
		<label for="name">Gender:</label> 
			<input type="text" style="width: 165px;"" name="voterHouseInfoVO.gender" value="${voterHouseInfoVO.gender}" readonly='true'/>
		</div>
		<div>
		<label for="name">Age:</label> 
			<input type="text" style="width: 165px;" name="voterHouseInfoVO.age" value="${voterHouseInfoVO.age}" readonly='true'/>
		</div>
		<div>
		<label for="name">House No:</label> 
			<input type="text" style="width: 165px;" name="voterHouseInfoVO.houseNo" value="${voterHouseInfoVO.houseNo}" readonly='true'/>
		</div>
		<div>
		<label for="name">Guardian Name:</label> 
			<input type="text" style=" width: 165px;" name="voterHouseInfoVO.gaurdian" value="${voterHouseInfoVO.gaurdian}" readonly='true'/>
		</div>
		<div>
		<label for="name">RelationShip:</label>
			<input type="text" style=" width: 165px;" name="voterHouseInfoVO.relationship" value="${voterHouseInfoVO.relationship}" readonly='true'/>
		</div>
		<div>
		<label for="name">Caste:</label> 
			<input type="text" style="width: 165px;" name="voterHouseInfoVO.cast" value="${voterHouseInfoVO.cast}"/>
		</div>
		<div>
		<label for="name">Caste Category:</label>
			<input type="text" style="width: 165px;" name="voterHouseInfoVO.castCategory" value="${voterHouseInfoVO.castCategory}" readonly='true'/>
		</div>
		<div>
		<label for="name">Party Name:</label>
				<s:select theme="simple" style="width: 169px;"
				label="Select  party" name="voterHouseInfoVO.partyId" 
				id="partyId" list="partyGroupList" 
				listKey="id" listValue="name"/>
		</div>
		<div>
			<label for="name">Booth Name:</label> 
			<input type="text" style="width: 165px;" name="voterHouseInfoVO.boothName" value="${voterHouseInfoVO.boothName}" readonly='true'/>
		</div>
		<div>
			<label for="name">Panchayat Name:</label> 
			<input type="text" style="width: 165px;" name="voterHouseInfoVO.panchayatName" value="${voterHouseInfoVO.panchayatName}" readonly='true'/>
		</div>
		<div>
			<label for="name">Villiage Covered:</label> 
			<input type="text" style="width: 165px;" name="voterHouseInfoVO.villiageCovered" value="${voterHouseInfoVO.villiageCovered}" readonly='true'/>
		</div>
				<div id="addFieds" style="float:left;"></div>
		
		</fieldset>
<div style="float:right;">
<input class="btn btn-success" type="submit" value="Update">
</div>
<!--PRASAD-->
<div id="popupDiv" style="display:none;">
	 <div id="groupCreation" >
	 <lable name="CreateNewGroup">CreateNewGroup : </lable>
	 <input type="text" id="CreateNewGroupText"style="width: 278px;"></input>
	 <input type="button" value="create" style="float:right;margin-top:4px;"onClick="storeGroupValue()"></input>
	 </div>
	 <div name="valuesDisplayDiv" style="border:1px solid;">
 		<div>
		<label for="name">Select Type:</label>
				<select id="selectType"></select>
				
		</div>
		<div id="otherDiv"></div>
		<div>
		<label for="name">Select value:</label> 
			<input type = "text" id="catrgoeryValue"></input>
			
		</div>
		
		<div style="">
			<input class="btn btn-success" type="submit" value="Add" onClick="addFieldsToMainFileds();storeCategoryValues();addFieldsToMainDiv()";></input>
		</div>
	</div>
</div>

<!--PRASAD-->
</div>
</form>
</body>
</html>