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

<!-- YUI SCRIPT FILES -->
<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
	

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
    /* width: 40%;
	margin-left:250px;*/
	
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
	/* width: 130%;*/
	margin-bottom: 9px;
    width: 500px;

}
 
.legendClass {
    font: bold 0.8em Arial, Helvetica, sans-serif;
    color: #00008B;
    background-color: #FFFFFF;
	border-style:none ;
	margin-bottom:0px;
	width:30%;
	font-weight:bold;
	font-size:16px;
	text-align:center;
}
.error{
	color:red;
	font-size:12px;
}

.template {display:none;}
.fontStyle{ margin-right: 6px;}
 
 #votersEditMainDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 600px;}
	#voterInfoTable th{text-align:left;}
	
	#impFamDtls select{width:150px;}

textarea { resize: none; }
 </style>
 
 <script type="text/javascript">
 
    $(function() {
	$( "#setValue" ).autocomplete({
            source: function( request, response ) {
			 //var userCategoryValuesId1 = document.getElementById("userCategoryValuesId1");
	
				//var userCategoryValues = userCategoryValuesId1.options[userCategoryValuesId1.selectedIndex].value;
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
							}else if(jsObj.task == "getVotersInAFamilySearch")
							{
								buildVotersInFamilySearch(myResults.parentsList,jsObj.hno);
								if(myResults.childrenList!=null){
									buildChildrenTable(myResults.childrenList,jsObj.hno);
								}
								/*if($("#"+'${voterId}'+"fmlyRltn").val()==0){
									$("#"+'${voterId}'+"fmlyRltn").val(1);
								}*/
							}
							else if(jsObj.task == "storeCategoeryValues")
							{
							
							}else if(jsObj.task == "getVotersMatched"){
								$("#ajaxImg").css("display","none");
								buildTableForSearchedVoters(myResults);
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


function buildChildrenTable(myResults,hno){
	if(myResults.length>0){
	$("#childrenTable").css("display","block");
	}

	var str="";
	str+="<table class='table table-bordered'>";
		str+="<thead>";
			str+="<tr>";
				str+="<td>Select</td><td>Name</td><td>Age</td><td>Guardian</td><td>Relation</td><td>Education</td><td>Occupation</td><td>Social Position</td>";
			str+="</tr>";
		str+="</thead>";
		str+="<tbody>";
	for(var i in myResults){
					var occuSelId=0;
					var eduSelId=0;
					var familyRelsSel=0;
					var socialPosSel=0;
					
					if(myResults[i].voterFamilyRelId!=null||myResults[i].voterFamilyRelId!=""){
						familyRelsSel=myResults[i].voterFamilyRelId;
					}
					
					if(myResults[i].categoriesList.length>0){
					for(var j in myResults[i].categoriesList){
						if(myResults[i].categoriesList[j].categoryValuesId==7){
							occuSelId=myResults[i].categoriesList[j].categoryValueId;
						}
						if(myResults[i].categoriesList[j].categoryValuesId==8){
							eduSelId=myResults[i].categoriesList[j].categoryValueId;
						}
						if(myResults[i].categoriesList[j].categoryValuesId==9){
							socialPosSel=myResults[i].categoriesList[j].categoryValueId;
						}
						
					}
					}
	
		str+="<tr>";
			str+="<td><input type='checkbox' value="+myResults[i].HHFamilyDetailsId+" class='familyMemberChkbx' checked></td>";
			
			str+="<td><input type='text' id='memberPrsnName"+myResults[i].HHFamilyDetailsId+"' value='"+myResults[i].name+"'/></td>";

			str+="<td><input type='text' id='memberPrsnAge"+myResults[i].HHFamilyDetailsId+"' value='"+myResults[i].age+"'/></td>";

			str+="<td><input type='text' id='memberPrsnGrdn"+myResults[i].HHFamilyDetailsId+"' value='"+myResults[i].gaurdian+"'/></td>";

					str+="<td><select id='memberPrsnrltnType"+myResults[i].HHFamilyDetailsId+"'>";
					for(var j in myResults[i].familyRelsList){
						if(familyRelsSel==myResults[i].familyRelsList[j].id){
							str+="<option value="+myResults[i].familyRelsList[j].id+" selected>"+myResults[i].familyRelsList[j].name+"</option>";
						}else{
							str+="<option value="+myResults[i].familyRelsList[j].id+">"+myResults[i].familyRelsList[j].name+"</option>";
						}
					}
					str+="</select></td>";
					
					
					str+="<td><select id='memberPrsnEdctn"+myResults[i].HHFamilyDetailsId+"'>";
						for(var j in myResults[i].educationList){
							if(eduSelId==myResults[i].educationList[j].id){
								str+="<option value="+myResults[i].educationList[j].id+" selected>"+myResults[i].educationList[j].name+"</option>";
							}else{
								str+="<option value="+myResults[i].educationList[j].id+">"+myResults[i].educationList[j].name+"</option>";
							}
						}
						str+="</select></td>";
					
					str+="<td><select id='memberPrsnOctpn"+myResults[i].HHFamilyDetailsId+"'>";
						for(var j in myResults[i].occupationList){
							if(occuSelId==myResults[i].occupationList[j].id){
								str+="<option value="+myResults[i].occupationList[j].id+" selected>"+myResults[i].occupationList[j].name+"</option>";
							}else{
								str+="<option value="+myResults[i].occupationList[j].id+">"+myResults[i].occupationList[j].name+"</option>";
							}
						}
						str+="</select ></td>";
					
					str+="<td><select id='memberPrsnSclPstn"+myResults[i].HHFamilyDetailsId+"'>";
						for(var j in myResults[i].socialPositionList){
							if(socialPosSel==myResults[i].socialPositionList[j].id){
								str+="<option value="+myResults[i].socialPositionList[j].id+" selected>"+myResults[i].socialPositionList[j].name+"</option>";
							}else{
								str+="<option value="+myResults[i].socialPositionList[j].id+">"+myResults[i].socialPositionList[j].name+"</option>";
							}
						}
						str+="</select></td>";
			
		str+="</tr>"
	}
		str+="</tbody>";
		str+="</table>";
		
		$("#childrenTable").html(str);
}

function buildAgeWiseVoterAnalysisChart(chartInfo,jsObj){

$("#AgeWisetitle").html("Age Wise Voters Information Of "+jsObj.name+" in "+publicationYear+" ");
// Create the data table.
var data = google.visualization.arrayToDataTable([
['Task', 'Percentage'],
[chartInfo.votersDetailsVO[0].ageRange, chartInfo.votersDetailsVO[0].totalVotersPercent],
[chartInfo.votersDetailsVO[1].ageRange, chartInfo.votersDetailsVO[1].totalVotersPercent],
[chartInfo.votersDetailsVO[2].ageRange, chartInfo.votersDetailsVO[2].totalVotersPercent],
[chartInfo.votersDetailsVO[3].ageRange, chartInfo.votersDetailsVO[3].totalVotersPercent],
[chartInfo.votersDetailsVO[4].ageRange, chartInfo.votersDetailsVO[4].totalVotersPercent]
]);


// Set chart options
var title = " Age wise detail chart of in "+publicationYear+"";
var options = {'title':title,
'width':450,
'height':280};
// Instantiate and draw our chart, passing in some options.
var chart = new google.visualization.PieChart(document.getElementById('ageWiseVotersBasicInfoSubChartDiv'));
chart.draw(data, options);

}
var i=-1;
function openPopUp(){
alert("dialogue");
i++;
$('#popupDiv').dialog({
			width:600,
			'title':'Voter Groups...'
		});
		clearFields();
	
	
	
	
}

function addFieldsToMainFileds(){

if($('#userCategoryValuesId1 :selected').text() == "other"){
	value = $('#otherTextBox').val();
	cValue = $('#setValue').val();
	cType = $('#userCategoryValuesId1 :selected').text();
	cValue = $('#setValue').val();
$('#addFieds').append('<div><lable><strong>Category Type:</strong></lable><input type="text" value="'+cType+'" name="voterHouseInfoVO.category['+i+'].type" readonly="true" style="width: 165px; margin-left: 31px;" ></input></div>');
$('#addFieds').append('<div><lable><strong>Category Value:</strong></lable><input type="text" value="'+cValue+'" name="voterHouseInfoVO.category['+i+'].value" style="width: 165px; margin-left: 28px;" ></input></div>');
	$('#popupDiv').dialog("close");
}
else
{
	cType = $('#userCategoryValuesId1 :selected').text();
	value = $('#userCategoryValuesId1 :selected').val();
	cValue = $('#setValue').val();
$('#addFieds').append('<div><lable><strong>Category Type:</strong></lable><input type="text" value="'+cType+'" name="voterHouseInfoVO.category['+i+'].type" readonly="true" style="width: 165px; margin-left: 31px;" ></input></div>');
$('#addFieds').append('<div><lable><strong>Category Value:</strong></lable><input type="text" value="'+cValue+'" name="voterHouseInfoVO.category['+i+'].value" style="width: 165px; margin-left: 28px;" ></input></div>');
	$('#popupDiv').dialog("close");
}

}


function storeUpdatedValues(){
cType = $('#userCategoryValuesId1 :selected').text();
cValue = $('#setValue').val();
typeValue = $('#userCategoryValuesId1').val();
if($('#userCategoryValuesId1 :selected').text() == "other")
{
alert("yes");
	$('#otherDiv').append('<input type="text" id="otherTextBox" onChange="storeValue()"/>');
	
}
}
function clearFields(){
	$('#userCategoryValuesId1').val("Select Category");
	$('#setValue').val("");
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

getVotersInAFamilySearch();
function getVotersInAFamilySearch(){

	var hno="${houseNo}";
	var publicationDateId="${publicationDateId}";
	var id="${boothId}";
	var voterId="${voterId}";
    var jsObj=
			{
					
				hno:hno,
				id:id,
				voterId:voterId,
				publicationDateId:publicationDateId,
				task:"getVotersInAFamilySearch"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "votersFamilyDetailsForHHSurveyAction.action?"+rparam;						
		callAjax(jsObj,url);

}


function buildVotersInFamilySearch(results,hno){
   var temp = false;
   
    $("#impFamDtlsTitle").html("<h3>Voters in house no "+hno+"</h3>");
	/*YAHOO.widget.DataTable.DeleteRow = function(elLiner, oRecord, oColumn, oData) 
	{
		var id=oRecord.getData("voterId");
		// str+='<a href="javascript:{deleteDiv('+newPersonsCount+')}"style="margin-left:20px;"><img src="images/icons/delete.png"/></a>';
		elLiner.innerHTML ='<a href="javascript:{deleteVtrDiv('+id+')}"style="margin-left:20px;"><img src="images/icons/delete.png"/></a>';
		
	}*/
	YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var id=oRecord.getData("voterId");
		var boothId=oRecord.getData("boothId"); 
		var name = oRecord.getData("name");
		//var publicationDateId = oRecord.getData("publicationDateId");
		var publicationDateId="${publicationDateId}";
		var houseNo = oRecord.getData("houseNo");
		
		elLiner.innerHTML ='<a id="openProblemEditFormId" onclick=" openProblemEditFormNew('+id+','+boothId+','+publicationDateId+',\''+houseNo+'\');">'+name+'</a>';
		
	}
	YAHOO.widget.DataTable.CheckVoter = function(elLiner, oRecord, oColumn, oData) 
	{
		var id=oRecord.getData("voterId");
		var boothId=oRecord.getData("boothId"); 
		var name = oRecord.getData("name");
		//var publicationDateId = oRecord.getData("publicationDateId");
		var publicationDateId="${publicationDateId}";
		var houseNo = oRecord.getData("houseNo");
		
		elLiner.innerHTML ='<input type="checkbox" checked name="voters" value='+id+' class="voterChkbx"></input>';
		
	}
    YAHOO.widget.DataTable.Occupation = function(elLiner, oRecord, oColumn, oData)
	    {
			var categ = oRecord.getData("occupationList");
			
			var categ1 = oRecord.getData("categoriesList");
			var val="";
			if(categ1 != null && categ1.length > 0){
			 for(var i in categ1){
			    if(categ1[i].categoryValuesId == 7)
				   //if(categ1[i].name != null)
				    val = categ1[i].categoryValueId;
			  }
			 }

			 var voterID = oRecord.getData("voterId");
			 
			var str="";
			if(categ != null && categ.length > 0){
			str+="<select id='"+voterID+"occup'>";
			 for(var i in categ){
			    if(categ[i].id==val){
					str+="<option value="+categ[i].id+" selected>"+categ[i].name+"</option>";
				}else{
					str+="<option value="+categ[i].id+">"+categ[i].name+"</option>";
				}
			  }
			  str+="</select>";
			 }
			 
			 elLiner.innerHTML = str;
			
	    }
	 YAHOO.widget.DataTable.Education = function(elLiner, oRecord, oColumn, oData)
	    {
		
			var categ = oRecord.getData("educationList");
			
			var categ1 = oRecord.getData("categoriesList");
			var val="";
			if(categ1 != null && categ1.length > 0){
			 for(var i in categ1){
			    if(categ1[i].categoryValuesId == 8)
				  // if(categ1[i].name != null)
				    val = categ1[i].categoryValueId;
			  }
			 }
			 
			  var voterID = oRecord.getData("voterId");
			var str="";
			if(categ != null && categ.length > 0){
			str+="<select id='"+voterID+"edctn'>";
			 for(var i in categ){
			   if(categ[i].id==val){
					str+="<option value="+categ[i].id+" selected>"+categ[i].name+"</option>";
				}else{
					str+="<option value="+categ[i].id+">"+categ[i].name+"</option>";
				}
			  }
			  str+="</select>";
			 }
			 
			 elLiner.innerHTML = str;
			
	    }
		
		YAHOO.widget.DataTable.SocialPosition = function(elLiner, oRecord, oColumn, oData)
	    {
			var categ = oRecord.getData("socialPositionList");
			
			var categ1 = oRecord.getData("categoriesList");
			var val="";
			if(categ1 != null && categ1.length > 0){
			 for(var i in categ1){
			    if(categ1[i].categoryValuesId == 9)
				  // if(categ1[i].name != null)
				    val = categ1[i].categoryValueId;
			  }
			 }
			  
			
			var voterID = oRecord.getData("voterId");

			var str="";
			if(categ != null && categ.length > 0){
			str+="<select id='"+voterID+"sclCtg'>";
			 for(var i in categ){
				if(categ[i].id==val){
					str+="<option value="+categ[i].id+" selected>"+categ[i].name+"</option>";
				}else{
					str+="<option value="+categ[i].id+">"+categ[i].name+"</option>";
				}
			    
			  }
			  str+="</select>";
			 }
			 
			 elLiner.innerHTML = str;
			
			
	    }
		
		YAHOO.widget.DataTable.Relation = function(elLiner, oRecord, oColumn, oData)
	    {
			var categ = oRecord.getData("familyRelsList");
			
			var val = oRecord.getData("voterFamilyRelId");

			var voterID = oRecord.getData("voterId");
			
			var str="";
			if(categ != null && categ.length > 0){
			str+="<select id='"+voterID+"fmlyRltn'>";
			 for(var i in categ){
				
					if(val==categ[i].id){
						str+="<option value="+categ[i].id+" selected>"+categ[i].name+"</option>";
					}else{
						str+="<option value="+categ[i].id+">"+categ[i].name+"</option>";
					}
				
				
			  }
			  str+="</select>";
			 }
			 
			 elLiner.innerHTML = str;
			
			
	    }
			
     var votersResultColumnDefs = [ 
							{label:"Select Voter",formatter:YAHOO.widget.DataTable.CheckVoter},
		    	           	{key:"name", label: "Name", sortable: true,formatter:YAHOO.widget.DataTable.NameLink},
							<!--{key:"gender", label: "Gender", sortable: true},-->
		    				{key:"age", label: "Age",sortable:true},
							<!--{key:"houseNo", label: "House No",sortable:true},-->
							{key:"gaurdian", label: "Guardian Name",sortable:true},
							<!--{key:"relationship", label: "Relationship",sortable:true},-->
							<!--{key:"mobileNo",label:"mobileNo",sortable:true}
							{key:"Relation", label: "Relation",formatter:YAHOO.widget.DataTable.Relation},
							{key:"voterIdCardNo", label: "Voter Card No",sortable:true},
							{key:"Education", label: "Education",formatter:YAHOO.widget.DataTable.Education},
							{key:"Occupation", label: "Occupation",formatter:YAHOO.widget.DataTable.Occupation},
							{key:"Social Position", label: "SocialPosition",formatter:YAHOO.widget.DataTable.SocialPosition}
							//{key:"Delete", label: "Delete",formatter:YAHOO.widget.DataTable.DeleteRow}
						]; 

    var myConfigs = { 
			    
				};
	var myDataSource = new YAHOO.util.DataSource(results);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : ["name","gender","age","houseNo","gaurdian","relationship","voterId","boothId"]
					};

		var familesDataSource = new YAHOO.widget.DataTable("impFamDtls", votersResultColumnDefs,myDataSource, myConfigs);
    
}
function openProblemEditFormNew(id,boothId,publicationDateId,houseNo)
{
	var urlStr="votersEditNewHHAction.action?voterId="+id+"&boothId="+boothId+"&publicationDateId="+publicationDateId+"&houseNo="+houseNo+"&selIds=7,8";
	var updateBrowser = window.open(urlStr,"Voter Details","scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();	
}

getHouseHoldInfoPage();

function getHouseHoldInfoPage(){
	
	var surveyDetails={surveyId:'',boothId:'',houseNo:'',voterId:''};
	surveyDetails.surveyId=1;
	surveyDetails.boothId="${boothId}";
	surveyDetails.houseNo="${houseNo}";
	surveyDetails.voterId="${voterId}";
	 $.ajax({
          type:'POST',
          url: 'getHouseHoldQuestionsAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(surveyDetails)},

          success: function(result){ 
			   if(result.length == 0)
			   {
                $('#analyseDiv').html("<h3>No Data Available</h3>");
			   }
			  else
			   buildQuestionAnswers(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}
function buildQuestionAnswers(results){
	var str="";
	str+="<div class='span10 row'>"
	str+="<form name='QuestionAnswersForm' id='QuestionAnswersFormId' action='hhQstnAnswrsForm.action'>";

	str+='<input type="hidden" name="houseHoldsId" id="houseHoldsId"/>';
	var queLoopId = 0;
	for(var i in results){
	str+="<div class='span12'>";
		str+="<h3>"+(parseInt(i)+1)+".  "+results[i].mainQues+"</h3>";
		
		for(var j in results[i].subQuestList){
			
			str+="<h4>"+results[i].subQuestList[j].subQues+"</h4>";
			for(var k in results[i].subQuestList[j].questionsList){
				str+="<input type='hidden' name='questionOptionsList["+queLoopId+"].questionId' value="+results[i].subQuestList[j].questionsList[k].questionId+">";
				
				str+="<input type='hidden' name='questionOptionsList["+queLoopId+"].optionTypeId' value="+results[i].subQuestList[j].questionsList[k].optionTypeId+">";
				if(results[i].subQuestList[j].questionsList.length > 1)
				{
				  str+="<h6>"+(parseInt(k)+1)+".  "+results[i].subQuestList[j].questionsList[k].question+"</h6>";
				}
				else {
                 str+="<h6>"+results[i].subQuestList[j].questionsList[k].question+"</h6>";
				}
				if(results[i].subQuestList[j].questionsList[k].optionTypeId!=3){
				
				str+="<div class='span12'>";
				for(var l in results[i].subQuestList[j].questionsList[k].options){
					
					if(results[i].subQuestList[j].questionsList[k].optionTypeId==1){
						if(results[i].subQuestList[j].questionsList[k].options[l].optSelected){
							str+="<input type='radio' style='margin:5px;' id=QUES"+results[i].subQuestList[j].questionsList[k].questionId+" name='questionOptionsList["+queLoopId+"].options[0].optionId' value="+results[i].subQuestList[j].questionsList[k].options[l].optionId+" checked><span style='margin-right:5px;'>"+results[i].subQuestList[j].questionsList[k].options[l].option+"</span></input>";
						}else{
							str+="<input type='radio' style='margin:5px;' id=QUES"+results[i].subQuestList[j].questionsList[k].questionId+" name='questionOptionsList["+queLoopId+"].options[0].optionId' value="+results[i].subQuestList[j].questionsList[k].options[l].optionId+"><span style='margin-right:5px;'>"+results[i].subQuestList[j].questionsList[k].options[l].option+"</span></input>";
						}
					}else if(results[i].subQuestList[j].questionsList[k].optionTypeId==2){
						if(results[i].subQuestList[j].questionsList[k].options[l].optSelected){
							str+="<input type='checkbox' style='margin:5px;' id=QUES"+results[i].subQuestList[j].questionsList[k].questionId+" name='questionOptionsList["+queLoopId+"].options["+l+"].optionId' value="+results[i].subQuestList[j].questionsList[k].options[l].optionId+" checked><span style='margin-right:5px;'>"+results[i].subQuestList[j].questionsList[k].options[l].option+"</span></input>";
						}else{
							str+="<input type='checkbox' style='margin:5px;' id=QUES"+results[i].subQuestList[j].questionsList[k].questionId+" name='questionOptionsList["+queLoopId+"].options["+l+"].optionId' value="+results[i].subQuestList[j].questionsList[k].options[l].optionId+"><span style='margin-right:5px;'>"+results[i].subQuestList[j].questionsList[k].options[l].option+"</span></input>";
						}
					}
					
				}
				str+="</div>";

				}else if(results[i].subQuestList[j].questionsList[k].optionTypeId==3){
					if(results[i].mainQuesId==11){
					str+="<div class='span12'>";
					if(results[i].subQuestList[j].questionsList[k].optsSelected!=null){						
						str+="<textarea cols='10' rows='2' maxlength='250' name='questionOptionsList["+queLoopId+"].option' id=QUES"+results[i].subQuestList[j].questionsList[k].questionId+">"+results[i].subQuestList[j].questionsList[k].optsSelected.options[0].option+"</textarea>"; 						
					}else{                       
                        str+="<textarea cols='10' rows='2' maxlength='250' name='questionOptionsList["+queLoopId+"].option' id=QUES"+results[i].subQuestList[j].questionsList[k].questionId+" ></textarea>"; 
					}
					
					str+="</div>";
					}else{
					
					str+="<div class='span12'>";
					if(results[i].subQuestList[j].questionsList[k].optsSelected!=null){
						str+="<input type='text' name='questionOptionsList["+queLoopId+"].option' id=QUES"+results[i].subQuestList[j].questionsList[k].questionId+" value="+results[i].subQuestList[j].questionsList[k].optsSelected.options[0].option+">";
					}else{
						str+="<input type='text' name='questionOptionsList["+queLoopId+"].option' id=QUES"+results[i].subQuestList[j].questionsList[k].questionId+">";
					}
					
					str+="</div>";
					}
				}
				queLoopId = queLoopId+1;
			}
		}
		
		for(var j in results[i].directSubQuestList){
			<!--str+="<h5>"+results[i].directSubQuestList[j].subQues+"</h5>";-->
			for(var k in results[i].directSubQuestList[j].questionsList){
				str+="<input type='hidden' name='questionOptionsList["+queLoopId+"].questionId' value="+results[i].directSubQuestList[j].questionsList[k].questionId+">";
				
				str+="<input type='hidden' name='questionOptionsList["+queLoopId+"].optionTypeId' value="+results[i].directSubQuestList[j].questionsList[k].optionTypeId+">";
				
				if(results[i].directSubQuestList[j].questionsList.length > 1){
				str+="<h6>"+(parseInt(k)+1)+". "+results[i].directSubQuestList[j].questionsList[k].question+"</h6>";
				}
				else{                
				str+="<h6>"+results[i].directSubQuestList[j].questionsList[k].question+"</h6>";
				}
				if(results[i].directSubQuestList[j].questionsList[k].optionTypeId!=3){
				for(var l in results[i].directSubQuestList[j].questionsList[k].options){
					str+="<div class='span12'>";
					if(results[i].directSubQuestList[j].questionsList[k].optionTypeId==1){
						if(results[i].directSubQuestList[j].questionsList[k].options[l].optSelected){
							str+="<input type='radio' style='margin:5px;' id=QUES"+results[i].directSubQuestList[j].questionsList[k].questionId+" name='questionOptionsList["+queLoopId+"].options[0].optionId' value="+results[i].directSubQuestList[j].questionsList[k].options[l].optionId+" checked><span style='margin-right:5px;'>"+results[i].directSubQuestList[j].questionsList[k].options[l].option+"</span></input>";
						}else{
							str+="<input type='radio' style='margin:5px;' id=QUES"+results[i].directSubQuestList[j].questionsList[k].questionId+" name='questionOptionsList["+queLoopId+"].options[0].optionId' value="+results[i].directSubQuestList[j].questionsList[k].options[l].optionId+"><span style='margin-right:5px;'>"+results[i].directSubQuestList[j].questionsList[k].options[l].option+"</span></input>";
						}
					}else if(results[i].directSubQuestList[j].questionsList[k].optionTypeId==2){
						if(results[i].directSubQuestList[j].questionsList[k].options[l].optSelected){
							str+="<input type='checkbox' style='margin:5px;' id=QUES"+results[i].directSubQuestList[j].questionsList[k].questionId+" name='questionOptionsList["+queLoopId+"].options["+l+"].optionId' value="+results[i].directSubQuestList[j].questionsList[k].options[l].optionId+"><span style='margin-right:5px;'>"+results[i].directSubQuestList[j].questionsList[k].options[l].option+"</span></input>";
						}else{
							str+="<input type='checkbox' style='margin:5px;' id=QUES"+results[i].directSubQuestList[j].questionsList[k].questionId+" name='questionOptionsList["+queLoopId+"].options["+l+"].optionId' value="+results[i].directSubQuestList[j].questionsList[k].options[l].optionId+" checked><span style='margin-right:5px;'>"+results[i].directSubQuestList[j].questionsList[k].options[l].option+"</span></input>";
						}	
					}
					str+="</div>";
				}
				}else if(results[i].directSubQuestList[j].questionsList[k].optionTypeId==3){
					str+="<div class='span12'>";
					if(results[i].directSubQuestList[j].questionsList[k].optsSelected!=null){
                       str+="<textarea cols='10' rows='2' maxlength='180' name='questionOptionsList["+queLoopId+"].option' id=QUES"+results[i].directSubQuestList[j].questionsList[k].questionId+">"+results[i].directSubQuestList[j].questionsList[k].optsSelected.options[0].option+"</textarea>"; 
					}else{
						str+="<textarea cols='10' rows='2' maxlength='180' name='questionOptionsList["+queLoopId+"].option' id=QUES"+results[i].directSubQuestList[j].questionsList[k].questionId+"></textarea>";
					}
					str+="</div>";
				}
				queLoopId = queLoopId+1;
			}
			
		}
		str+="</div>";
	}
	var boothId="${boothId}";
	var houseNo="${houseNo}";
	
	str+="<input type='hidden' name='boothId' value="+boothId+"></input>";
	str+="<input type='hidden' name='houseNo' value="+houseNo+"></input>";
	
	str+="<span class='btn btn-info' onclick=submitQuestionDetails()> Save </span>";
	str+="</form>"
	str+="</div>";
	$("#qstnAnswer").html(str);
}

function saveHouseHoldInfo(){

	var uploadHandler = {
				success: function(o) {
					var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
					if(uploadResult=="success"){
						$('.statusMsg').html("<h5 style='text-align:center;color:green;'>Saved Successfully..</h5>");
					}else if(uploadResult=="error"){
						$('.statusMsg').html("<h5 style='text-align:center;color:red;'>Internal Error .. Please Try Again Later</h5>");
					}
				}
			};

		
		YAHOO.util.Connect.setForm('QuestionAnswersForm',false);
		YAHOO.util.Connect.asyncRequest('POST','hhQstnAnswrsForm.action',uploadHandler);
		
	//$( "#QuestionAnswersFormId" ).submit();
}
 </script>
</head>
<body style="position: relative;">

<div id="votersEditMainDiv">
<form id="voterDetailsForm" name="voterDetailsForm" method="POST" action="voterEditAction.action">

<c:if test="${requestScope.resultStr=='success'}">
<div id="probSuccessMsgDiv">
<DIV id="alertMessage" style="color:green;font-weight:bold;margin:5px;">Updated Successfully...</DIV>
</div>
</c:if>
<input type="hidden" name="boothId" value="${voterHouseInfoVO.boothId}"/>
<input type="hidden" name="voterId" value="${voterHouseInfoVO.voterId}"/>
<input type="hidden" name="save" value="save"/>
<!--<input type="hidden" name="voterHouseInfoVO.userId" value="${voterHouseInfoVO.userId}"/>
<input type="hidden" name="voterHouseInfoVO.userVoterDetailsId" value="${voterHouseInfoVO.userVoterDetailsId}"/>
<input type="hidden" name="voterHouseInfoVO.categoryValuesId" value="${voterHouseInfoVO.categoryValuesId}"/>-->
<div id="mainDiv" >
<div id="errorDiv" style="color:red"></div>

<div>
  <span class="fontStyle"><b>Panchayat Name: </b></span> ${voterHouseInfoVO.panchayatName}
  <span class="fontStyle" style="margin-left: 25px;"><b>Booth Name:</b></span> ${voterHouseInfoVO.boothName}
</div>
<div>
	<span class="fontStyle"><b>Villiage Covered:</b></span> ${voterHouseInfoVO.villiageCovered}
</div>

<fieldset >
       <legend class="legendClass">Voter Information</legend>
<table id="voterInfoTable" style="width: 100%; height: 90px;">
	<tr>
		<th style="width: 23%;">Voter Name</th>
		<td style="width: 3%;"><b>:</b></td>
		<td style="width: 50%;">${voterHouseInfoVO.name}</td>
		<th>House No</th>
		<td><b>:</b></td>
		<td>${voterHouseInfoVO.houseNo}</td>
		<!--<th style="width: 14%;">Age</th>
		<td style="width: 3%;"><b>:</b></td>
		<td style="width: 9%;">${voterHouseInfoVO.age}</td>-->
	</tr>
	<!--<tr>
		<th>Guardian Name</th>
		<td><b>:</b></td>
		<td>${voterHouseInfoVO.gaurdian}</td>
		<th>Voter Card No</th>
		<td><b>:</b></td>
		<td>${voterHouseInfoVO.voterIdCardNo}</td>
	</tr>
	<tr>
		<th>RelationShip</th>
		<td><b>:</b></td>
		<td>${voterHouseInfoVO.relationship}</td>
		<th>House No</th>
		<td><b>:</b></td>
		<td>${voterHouseInfoVO.houseNo}</td>
	</tr>-->
	
	<tr>
		<th>Mobile NO</th>
		<td><b>:</b></td>
		<td><input type="text" id="ownerMobileNo" value=${voterHouseInfoVO.mobileNo}></input></td>
		<th>Caste</th>
		<td><b>:</b></td>
		<td>${voterHouseInfoVO.casteName}</td>
	</tr>
	
	<tr>
		<th>Booth Leader</th>
		<td><b>:</b></td>
			<td><s:select theme="simple" style="width: 169px;"
				 name="BoothLeadersList" 
				id="BoothLeadersListId" list="voterHouseInfoVO.BoothLeaderList" 
				listKey="id" listValue="name"/>
			</td>
		
	</tr>

</table>


</fieldset>

 
 	<!--<fieldset>
		<div>
		<label for="name">Caste:</label> 
			<s:select theme="simple" style="width: 169px;"
				 name="voterHouseInfoVO.casteStateId" 
				id="voterHouseInfoVO.casteStateId" list="voterHouseInfoVO.casteGroupNameList" 
				listKey="id" listValue="name"/>
		</div>
		<div>
		<label for="name">Party Name:</label>
				<s:select theme="simple" style="width: 169px;"
				 name="voterHouseInfoVO.partyId" 
				id="partyId" list="voterHouseInfoVO.parties" 
				listKey="id" listValue="name"/>
		</div>
		<s:if test="voterHouseInfoVO.categoriesList != null && voterHouseInfoVO.categoriesList.size() > 0">
 		  <s:iterator value="voterHouseInfoVO.categoriesList" var="category" status="stat">
		 <div>
		   <label for="name"><s:property value="userCategoryValueName"/>:</label>
		        <s:hidden name="voterHouseInfoVO.categoriesList[%{#stat.index}].userCategoryValueId" value="%{#category.userCategoryValueId}" />
				<s:select theme="simple" style="width: 169px;"
				 name="voterHouseInfoVO.categoriesList[%{#stat.index}].categoryValuesId"
				 list="#category.category" 
				listKey="id" listValue="name"/>
		 </div>
		  </s:iterator>
	   </s:if>
		
	</fieldset>	-->
<div style="margin-bottom: 50px; width: 79px; float: right; margin-right: 70px;">


<!--<input class="btn btn-success" type="submit" value="Update">-->
</div>

</div>
</form>
		<div id="impFamDtlsOuterPopUp">
		   <div id="impFamDtlsTitle"></div>
		   <div id="impFamDtls"  class="table table-striped" style="margin-left:-200px;"></div>
		  
		</div>
		
		 <div id="childrenTable" style="display:none;margin-left:-200px;"></div>
</div>

<!--
<div><span class="btn btn-info" onclick="searchVoter()">Add Voter To This Family</span></div>-->
<!--<span class="btn btn-info span3 offset5" onclick="getHouseHoldInfoPage()"> Get House Hold Information </span>-->

<div style="clear:both;">
 <a href="javascript:{addNewPersonOfFamily()}" class="btn"  style="margin:5px;">Add New Person To This Family</a>
  <a href="javascript:{searchVoter()}" class="btn pull-right"  style="margin:5px;">Add Voter To This Family</a>

</div>


	<div style="border:1px solid #000;padding:4px;border-radius:2px 2px 2px 2px;margin-bottom:5px;">
			<div  class="row-fluid" style="clear:both;font-weight:bold;margin:6px 0 9px 34px;">
				<div style="float:left;width:80px;margin:1px;"> Select </div>
				<div style="float:left;width:89px;">Name</div>
				<div style="float:left;width:40px;">Age</div>
				<div style="float:left;width:89px;">Gender</div>
				<div style="float:left;width:89px;">Gaurdian</div>
				<div style="float:left;width:112px;">RelationShip</div>
				<div style="float:left;width:108px;">Education</div>
				<div style="float:left;width:108px;">Occupation</div>
				<div style="float:left;width:123px;"">Social Category</div>
				<div style="float:left;width:120px;">MobileNo</div>
				<div style="float:left;width:35px;">Delete</div>				
		   </div>
		   <div id="newprsnDetailsId" style="margin-left:27px;"></div>

	</div>

<div id="qstnAnswer" class="span10 offset2" style="border:1px solid gray"></div>
<div class="statusMsg span12"></div>

<div id="dialog">
	<div class="span12 row searchVtrCrit" style="display:none;">
		<div class="span8 row-fluid"><span class="span4 ">Voter Id</span><input type="text" id="voterId" class="span4 vtrIdCls"/></div> (or)
		<div class="span8 row-fluid"><span class="span4 ">Voter Name</span><input type="text" id="voterNameId" class="span4 vtrNameCls"/></div>
		<div class="span8"><span class="btn btn-info searchNowBtn" onclick="searchNow()"> Search </span><img id='ajaxImg' style='width: 25px;display:none;margin-left:5px;' src='images/icons/ajaxImg.gif'></div>
		
	</div>
	
	<div class="span12 row searchedVotersDiv"></div>
	
	<div class="span8 row-fluid errorDiv"></div>
</div>

</div>
	

<script>
	function searchNow(){
		
		
	
		var voterCardNo=$(".vtrIdCls").val();
		var voterName=$(".vtrNameCls").val();
		$("#searchedVotersDiv").html("");
		
		if(voterCardNo=="" && voterName==""){
			$(".errorDiv").html("<span style='color:red'> Please Enter Name or Voter Id to Search </span>");
			return;
		}
		$("#ajaxImg").css("display","inline-block");
		var publicationDateId="${publicationDateId}";
		var boothId="${boothId}";
		//var constituencyId = 228;
		
		var jsObj =
		{
			voterCardNo:voterCardNo,
			voterName:voterName,
			boothId:boothId,
			publicationDateId:publicationDateId,
			task:"getVotersMatched"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getVotersForSearchedCriteriaAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	function searchVoter(){
		$('.searchVtrCrit').css("display","block");
		 $(function() {
			$( "#dialog" ).dialog({
				title:"Search Voter",
				height: 500,
				width: 1000,
				modal: true
			});
			
		});
		/*var urlStr="houseHoldSurveyReportActionSearch.action";
		var updateBrowser = window.open(urlStr,"SearchVoter","scrollbars=yes,height=600,width=700,left=200,top=200");	
		updateBrowser.focus();	*/
	}
	var temp;
	function buildTableForSearchedVoters(results){
		var voterArr=[];
		 $('.voterChkbx').each(function(index,value){
			voterArr.push(this.value);
		 });
		
		
		var str="";
		str+="<table class='table table-bordered' searchedVotersTable>";
			str+="<thead>";
				str+="<tr><th>Select</th><th>Name</th><th>Serial No</th><th>Booth No</th><th>House No</th></tr>";
				for(var i in results){
					str+="<tr>";
						if(voterArr.indexOf(results[i].voterId.toString())==-1){
							str+="<td><input type='checkbox' name='searchedVoter' value="+results[i].voterId+"></td>";
						}else{
							str+="<td><input type='checkbox' name='searchedVoter' value="+results[i].voterId+" disabled='true'></td>";
						}
						str+="<td>"+results[i].name+"</td>";
						str+="<td>"+results[i].sNo+"</td>";
						str+="<td>"+results[i].boothName+"</td>";
						str+="<td>"+results[i].houseNo+"</td>";
					str+="</tr>";
				}
			str+="</thead>";
		str+="</table>";
		temp = results;
		str+="<span onclick=addVoter() class='btn btn-info'>Add Voter</span>";
		$(".searchedVotersDiv").html(str);
	}
	
	function addVoter(){
		
		
		var str="";
		$('input[name="searchedVoter"]:checked').each(function() {
			
			for(var i in temp){
				if(temp[i].voterId==this.value){
					
					var occuSelId=0;
					var eduSelId=0;
					var familyRelsSel=0;
					var socialPosSel=0;
					if(temp[i].voterFamilyRelId!=null||temp[i].voterFamilyRelId!=""){
						familyRelsSel=temp[i].voterFamilyRelId;
					}
					
					if(temp[i].categoriesList.length>0){
					for(var j in temp[i].categoriesList){
						if(temp[i].categoriesList[j].categoryValuesId==7){
							occuSelId=temp[i].categoriesList[j].categoryValueId;
						}
						if(temp[i].categoriesList[j].categoryValuesId==8){
							eduSelId=temp[i].categoriesList[j].categoryValueId;
						}
						if(temp[i].categoriesList[j].categoryValuesId==9){
							socialPosSel=temp[i].categoriesList[j].categoryValueId;
						}
						
					}
					}
					
				str+="<tr>";
					
					str+="<td><input type='checkbox' class='voterChkbx' name='searchedVoter' value="+temp[i].voterId+" checked></td>";
					str+="<td>"+temp[i].name+"</td>";
					str+="<td>"+temp[i].age+"</td>";
					str+="<td>"+temp[i].gaurdian+"</td>";
					
					str+="<td><select id="+temp[i].voterId+"fmlyRltn>";
					for(var j in temp[i].familyRelsList){
						if(familyRelsSel==temp[i].familyRelsList[j].id){
							str+="<option value="+temp[i].familyRelsList[j].id+" selected>"+temp[i].familyRelsList[j].name+"</option>";
						}else{
							str+="<option value="+temp[i].familyRelsList[j].id+">"+temp[i].familyRelsList[j].name+"</option>";
						}
					}
					str+="</select></td>";
					
					
					str+="<td>"+temp[i].voterIdCardNo+"</td>";
					
					str+="<td><select id="+temp[i].voterId+"edctn>";
						for(var j in temp[i].educationList){
							if(eduSelId==temp[i].educationList[j].id){
								str+="<option value="+temp[i].educationList[j].id+" selected>"+temp[i].educationList[j].name+"</option>";
							}else{
								str+="<option value="+temp[i].educationList[j].id+">"+temp[i].educationList[j].name+"</option>";
							}
						}
						str+="</select></td>";
					
					str+="<td><select id="+temp[i].voterId+"occup>";
						for(var j in temp[i].occupationList){
							if(occuSelId==temp[i].occupationList[j].id){
								str+="<option value="+temp[i].occupationList[j].id+" selected>"+temp[i].occupationList[j].name+"</option>";
							}else{
								str+="<option value="+temp[i].occupationList[j].id+">"+temp[i].occupationList[j].name+"</option>";
							}
						}
						str+="</select ></td>";
					
					str+="<td><select id="+temp[i].voterId+"sclCtg>";
						for(var j in temp[i].socialPositionList){
							if(socialPosSel==temp[i].socialPositionList[j].id){
								str+="<option value="+temp[i].socialPositionList[j].id+" selected>"+temp[i].socialPositionList[j].name+"</option>";
							}else{
								str+="<option value="+temp[i].socialPositionList[j].id+">"+temp[i].socialPositionList[j].name+"</option>";
							}
						}
						str+="</select></td>";
				
				str+="</tr>";
				}
			}
		});
		$(".yui-dt-data").append(str);
		if(str!=""){
			$(".errorDiv").html("<span style='color:green'> Added Successfully </span>");
			$(".searchNowBtn").click();
		}
		
		
	}
</script>
<script>

if('${voterHouseInfoVO.ownerMobNo}'){
	$("#ownerMobileNo").val('${voterHouseInfoVO.ownerMobNo}');
}
if('${voterHouseInfoVO.leaderId}'){
	$("#BoothLeadersListId").val('${voterHouseInfoVO.leaderId}');
}

var voterDtls={
	boothId:'',
	voters:[],
	houseHoldsId:''
};
function submitQuestionDetails()
{
  var errorDivEle = document.getElementById('errorDiv');
  var flag= true;
  var str= '';
  var pattern1= /^\d{10}$/;
  if($('#BoothLeadersListId').val() == 0)
  {
       str+='Booth Leader Name is Required<br>';
	   flag=false;
  }
  $('.ageCls').each(function() {
    if($.trim($(this).val()).length == 0)
	 {
		str+='Age is required for adding new person<br>';
		flag=false;
		return false;
	 }
  });

   $('.ageCls').each(function() {
    if($.trim($(this).val()).length > 0)
	 {
		if(isNaN($.trim($(this).val()))){
		str+='Age must be integer<br>';
		flag=false;
		return false;
		}
	 }
   });
      
  $('.nameCls').each(function() {
     if($.trim($(this).val()).length == 0)
	  {
	    str+='Name is required for adding new person<br>';
	    flag=false;
		return false;
	 }
  });
  $('.gaurdianCls').each(function(){
     if($.trim($(this).val()).length == 0)
	 {
         str += 'Gaurdian Name is required for adding new person<br>';
		 flag=false;
		 return false;
     }
  });
   $('.mobileCls').each(function(){
     if($.trim($(this).val()).length == 0)
	 {
         str += 'Mobile No is required for adding new person<br>';
		 flag=false;
		 return false;
     }
  });

   $('.mobileCls').each(function(){
	  if ($.trim($(this).val()).length>0){
		if( (!pattern1.test($(this).val())) || (($(this).val()).length !=10 )) {  
					      str += 'Please enter valid mobile number.';
			              flag=false;
		                  return false; 
					 }
			}
   });
  errorDivEle.innerHTML = str;
  if(flag == false)
  {
     return;
  }
	 voterDtls.voters = [];
	 voterDtls.boothId = "${boothId}";
	 voterDtls.houseNo = "${houseNo}";
	 var houseHoldOfVoter='${houseHoldIdOfVoter}';
	 
	 if(houseHoldOfVoter){
		voterDtls.houseHoldsId = '${houseHoldIdOfVoter}';
	 }else{
		voterDtls.houseHoldsId = "0";
	 }
	 
	var leader=$("#BoothLeadersListId").val();
	var ownerMob=$("#ownerMobileNo").val();
	
	var relationsArr=[];
    $('.voterChkbx').each(function(index,value){
		var leader=$("#BoothLeadersListId").val();
		var ownerMob=$("#ownerMobileNo").val();
		
	   if(this.checked)
	   {
				relationsArr.push($('#'+this.value+"fmlyRltn").val());
				selectedVoterDtls =
			       {
			         voterId:this.value,
					 voterFamilyRelationId:$('#'+this.value+"fmlyRltn").val(),
					 voterEdctn:$('#'+this.value+"edctn").val(),
					 voterOccptn:$('#'+this.value+"occup").val(),
					 voterSocialPstn: $('#'+this.value+"sclCtg").val(),
					 isNew: 'false',	
					 leaderId:leader,
					 ownerMobileNo:ownerMob
			       };

				 voterDtls.voters.push(selectedVoterDtls);
	   }
	   
   });
    var count=countElement("1",relationsArr);
      if(count>1)
	  {
	   //$("#errorDiv").html('Family Head Relation must be assigned to only one person<br>');
	   alert('Family Head Relation must be assigned to only one person');
		 return ;
	  }
	  if(count<1){
		alert('Please Assign Family Head to Atleast One Person');
		return;
	  }
	   
  $('.familyMemberChkbx').each(function(index,value){
		
	   if(this.checked)
	   {
			
				selectedVoterDtls =
			       {
			         familyMemberId:this.value,
                     name:$('#memberPrsnName'+this.value).val().trim(),
                     age:$('#memberPrsnAge'+this.value).val(),
					// gender:$('#memberPrsnGndr'+this.value).val(),
					 gender:'M',
					 relativeName:$('#memberPrsnGrdn'+this.value).val().trim(),
					 education:$('#memberPrsnEdctn'+this.value).val(),
					 occupation:$('#memberPrsnOctpn'+this.value).val(),
					 socialPosistion: $('#memberPrsnSclPstn'+this.value).val(),
					 familyRelationId:$('#memberPrsnrltnType'+this.value).val(),
					// mobileNo:$('#memberPrsnMblNO'+this.value).val().trim(), 
					 mobileNo:"test", 
					 relationShipType:"1",
					 isNew: 'true',
					 leaderId:leader,
					 ownerMobileNo:ownerMob
			       };

				 voterDtls.voters.push(selectedVoterDtls);
	   }
  });
  

  for(var i=0;i<newPersons.length;i++)
  {
		relationsArr.push($('#newPrsnrltnType'+newPersons[i]).val());
		selectedVoterDtls =
			       {
					
					 isNew: 'true',
					 name:$('#newPrsnName'+newPersons[i]).val().trim(),
                     age:$('#newPrsnAge'+newPersons[i]).val(),
					 gender:$('#newPrsnGndr'+newPersons[i]).val(),
					 relativeName:$('#newPrsnGrdn'+newPersons[i]).val().trim(),
					 relationShipType:$('#newPrsnrltnType'+newPersons[i]).val(),
					 education:$('#newPrsnEdctn'+newPersons[i]).val(),
					 occupation:$('#newPrsnOctpn'+newPersons[i]).val(),
					 socialPosistion: $('#newPrsnSclPstn'+newPersons[i]).val(),
					 familyRelationId:$('#newPrsnrltnType'+newPersons[i]).val(),
					 mobileNo:$('#newPrsnMblNO'+newPersons[i]).val().trim(),
					 familyMemberId:0,
					 leaderId:leader,
					 ownerMobileNo:ownerMob					 
			       };

				 voterDtls.voters.push(selectedVoterDtls);

  }

	 $.ajax({
          type:'POST',
          url: 'saveHouseHoldsVotersDetailsAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(voterDtls)},

          success: function(result){ 
			  setHiddenFieldInForm(result);
          },
          error:function() { 
           console.log('error', arguments);
          }
    });
}
function countElement(item,array) {
    var count = 0;
    $.each(array, function(i,v) { if (v == item) count++; });
    return count;
}
function setHiddenFieldInForm(houseHoldsId)
{
	$('#houseHoldsId').val(houseHoldsId);
	
	if($('#houseHoldsId').val()!=null || $('#houseHoldsId').val()!=""){
		saveHouseHoldInfo();
	}
	

}
var newPersonsCount=0;
var newPersons = [];
function addNewPersonOfFamily()
{

		var str='';

		   str+='<div class="row-fluid " id="newPersonDetails'+newPersonsCount+'">';

			str+='<div style="float:left;width:80px;margin-top:5px;"><input type="checkbox" disabled="true" checked /></div>';
			str+='<div style="float:left;width:89px;"><input type="text"  style="width:69px;" class="nameCls" id="newPrsnName'+newPersonsCount+'"/></div>';
			str+='<div style="float:left;width:40px;"><input type="text"  style="width:19px;" class="ageCls" id="newPrsnAge'+newPersonsCount+'"/></div>';
			str+='<div style="float:left;width:89px;">';
				str+='<select style="width:48px;" id="newPrsnGndr'+newPersonsCount+'">';
				 str+='<option value="M">M</option>';
 				 str+='<option value="F">F</option>';
				str+='</select>';
			str+='</div>';
			str+='<div style="float:left;width:89px;"><input type="text"  style="width:65px;" class="gaurdianCls" id="newPrsnGrdn'+newPersonsCount+'"/></div>';

			str+='<div style="float:left;width:112px;">';
            str+='<select style="width:100px;" id="newPrsnrltnType'+newPersonsCount+'">';  
				<c:forEach items="${relationList}" var="relation"> 
					str+='<option value="${relation.id}">${relation.name}</option>'; 
				</c:forEach>
			str+='</select>';
            str+='</div>'; 

			str+='<div style="float:left;width:108px;">';
            str+='<select style="width:100px;" id="newPrsnEdctn'+newPersonsCount+'">';  
				<c:forEach items="${educationsList}" var="education"> 
					str+='<option value="${education.id}">${education.name}</option>'; 
				</c:forEach>
			str+='</select>';
            str+='</div>'; 

			str+='<div style="float:left;width:123px;">';
            str+='<select style="width:100px;" id="newPrsnOctpn'+newPersonsCount+'">';  
				<c:forEach items="${occupationsList}" var="occupation"> 
					str+='<option value="${occupation.id}">${occupation.name}</option>'; 
				</c:forEach>
			str+='</select>';
            str+='</div>'; 

			str+='<div style="float:left;width:110px;">';
            str+='<select style="width:100px;" id="newPrsnSclPstn'+newPersonsCount+'">';  
				<c:forEach items="${socialPositionsList}" var="socialPosition"> 
					str+='<option value="${socialPosition.id}">${socialPosition.name}</option>'; 
				</c:forEach>
			str+='</select>';
            str+='</div>';

			str+='<div style="float:left;width:120px;"><input type="text"  style="width:99px;" id="newPrsnMblNO'+newPersonsCount+'"/></div>';
           

			str+='<div style="float:left;width:35px;">';
              str+='<a href="javascript:{deleteDiv('+newPersonsCount+')}"style="margin-left:20px;"><img src="images/icons/delete.png"/></a>';
            str+='</div>';
			 str+='</div>';



	$('#newprsnDetailsId').append(str);
		newPersons.push(newPersonsCount);
		     newPersonsCount++;

}

function deleteDiv(count)
{

	newPersons = jQuery.grep(newPersons, function(value) {
	  return value != count;
	});

	var r=confirm("Are You Sure To Delete ?");

	if(r)
		$('#newPersonDetails'+count).remove();

}


</script>
</body>
</html>