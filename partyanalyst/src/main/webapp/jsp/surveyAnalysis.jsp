<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Survey Analysis</title>
<!--<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>-->
<script type="text/javascript" src="js/surveyAnalysis/surveyAnalysis.js"></script>
<script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jQuery/jquery_validation_1.7.js"></script>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<style>
	#myModal 
	{
		width: 700px; 
		margin-top: -300px !important;
		margin-left:  -350px !important;
		height:470px;
		
	} 

	#myModal .modal-body {
		//max-height: 500px;
	}
	
	table {
	
	border:1px solid #d3d3d3 !important;
	font-size:13px;

	}
	.table th {
    font-weight: normal;
}
.table thead.info th, .impFamilesMainDiv th, #censusTab th {
    background: none repeat scroll 0 0 #D9EDF7;
    color: #454545;
}
.table-bordered th, .table-bordered td {
    border-left: 1px solid #DDDDDD;
}
.table th {
    font-weight: bold;
}
.table th, .table td {
    border-top: 1px solid #DDDDDD;
    line-height: 20px;
    padding: 8px;
    text-align: left;
    vertical-align: top;
}

</style>
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
   function saveQuestion(){
       var question = $("#questionTitle").val();
	   var questionType = $("#questionType option:selected").val();
	   var showRemark = $("#showRemark").is(':checked');
	   var mainOptionsArray = new Array();
       $('.mainoption').each(function() {
	      var obj = {};
		  var key = $(this).attr("key");
		  var subOptionsArray = new Array();
		  obj["option"] = $(this).val();
	      obj["showRemark"] = $("#optshowremark"+key).is(':checked');
		  obj["hasSubQuestion"] = $("#hassubDIV"+key).is(':checked');
            if($("#hassubDIV"+key).is(':checked')){
			  obj["question"] = $("#subquestionTitle"+key).val();
	          obj["questionType"] = $("#subquestionType"+key+" option:selected").val();
		      obj["showSubRemark"] = $("#showsubRemark"+key).is(':checked');
			  $('.subquestcls'+key).each(function() {
			      var subobj = {};
				  var subkey = $(this).attr("key");
				  subobj["option"] = $(this).val();
	              subobj["showRemark"] = $("#subquestremark"+subkey).is(':checked');
				  subOptionsArray.push(subobj);
			  });
			   obj["subOptions"] = subOptionsArray;
			}
			mainOptionsArray.push(obj);
        });
   }
   
   function openSurveyQuestionAddWindow(id){
      
      var browser1 = window.open("createNewQuestionAction.action?surveyId="+id+"","addnewquestionwindow","scrollbars=yes,height=600,width=1050,left=200,top=200");	
      browser1.focus();
   }
   function openSurveyForm(id)
	{
		var browser1 = window.open("surveyFormAction.action?surveyId="+id+"","addnewquestionwindow","scrollbars=yes,height=750,width=1050,left=200,top=200");	
		browser1.focus();
	} 
   var optionId = 0;
   var suboptionId = 0;
   function buildQuestion(){
      optionId = 0;
      var str = '';
      $("#questionOptionsDIV").html("");
	  $("#addNewOptionsDIV").html("");
	  
	  if($("#questionType option:selected").val() == 1){
	    str+='<fieldset id="optionDIV0" class="alert alert-info" >';
	    str+='<div><div style="margin-bottom:5px;">Options : </div><div><input  key="'+optionId+'" class="mainoption" type="text" id="option0" /> <input type="checkbox" id="optshowremark0"/> Check to show remark  <input type="checkbox" id="hassubDIV0" onclick="showSubQuestion('+optionId+',this.id);"/> Has sub options</div></div>';
		str+='<div class="span10 offset1">';
		str+='<div id="subQuestionTypeDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
	    str+='<div id="subQuestionOptDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
	    str+='<div id="addNewSubQuestOptDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
		str+='</div>';
		str+='</fieldset>';
		$("#questionOptionsDIV").html(str);
		str ='<span onclick="buildOptions();" class="btn btn-small"><i class="icon-th-list"> </i> Add More Options</span>';
		//str ='<div><input type="button" class="btn" onclick="buildOptions();" value="Add Option" > </div>';
	    $("#addNewOptionsDIV").html(str);
	  }
	  
   }
   
   function buildOptions(){
      optionId = optionId+1;
	  var str = '';
	   str+='<fieldset id="optionDIV'+optionId+'" class="alert alert-info" >';
       str+='<div  style="margin-bottom:5px;"><div><input  key="'+optionId+'" class="mainoption" type="text" id="optionDIV'+optionId+'" /> <input type="checkbox" id="optshowremark'+optionId+'" /> Check to show remark  <input type="checkbox"  id="hassubDIV'+optionId+'" onclick="showSubQuestion('+optionId+',this.id);" /> Has sub options <span class="icon-trash" style="cursor:pointer;" title="Remove Option" onclick="removeOption('+optionId+');" ></span></div></div>';
	   str+='<div class="span10 offset1">';
	   str+='<div id="subQuestionTypeDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
	   str+='<div id="subQuestionOptDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
	   str+='<div id="addNewSubQuestOptDIV'+optionId+'" style="margin-bottom:5px;margin-left:40px;"></div>';
	   str+='</div>';
	   str+='</fieldset>';
		$("#questionOptionsDIV").append(str);
   }
   
   function removeOption(id){
    if(confirm("Do you want to delete this option?")){
      $('#optionDIV'+id).remove();
	  //$('#subQuestionTypeDIV'+id).remove();
	  //$('#subQuestionOptDIV'+id).remove();
	  //$('#addNewSubQuestOptDIV'+id).remove();
	  
	}
   }
   
   function showSubQuestion(optionId,id){
     if($("#"+id).is(':checked')){
       var str ='';
	   str+='<div><label>Question</label> <input type="text" class="span12" id="subquestionTitle'+optionId+'" /><input type="checkbox" id="showsubRemark'+optionId+'" /> Check to show remark </div>';
       str+='<div>';
	   str+='  <label>Select Question Type</label> <select class="span12" id="subquestionType'+optionId+'" onchange="buildSubQuestion('+optionId+');">';
	   str+='   <option value="0">Select</option>';
	   str+='   <option value="1">Multiple choice with single select</option>';
	   str+='  </select>';
	   str+=' </div>';
	   $('#subQuestionTypeDIV'+optionId).html(str);
	  }else{
	      $('#subQuestionTypeDIV'+optionId).html("");
		  $('#subQuestionOptDIV'+optionId).html("");
		  $('#addNewSubQuestOptDIV'+optionId).html("");
	  }
   }
   
   function buildSubQuestion(optionId){
      suboptionId = suboptionId+1;
      var str = '';
      $("#subQuestionOptDIV"+optionId).html("");
	  $("#addNewSubQuestOptDIV"+optionId).html("");
	  
	  if($("#subquestionType"+optionId+" option:selected").val() == 1){
	    str+='<fieldset id="subquestDIV'+suboptionId+'" class="alert"><div><div style="margin-bottom:5px;">Sub Options : </div><div><input type="text" key="'+suboptionId+'" class="subquestcls'+optionId+'" id="subquest'+suboptionId+'" /> <input id="subquestremark'+suboptionId+'" type="checkbox" /> Check to show remark </div></div></fieldset>';
		$("#subQuestionOptDIV"+optionId).html(str);
		str ='<span onclick="buildSubOptions('+optionId+');" class="btn btn-small"><i class="icon-th-list"> </i> Add More Sub Options</span>';
		//str ='<div><input type="button" class="btn" onclick="buildSubOptions('+optionId+');" value="Add Sub Option" > </div>';
	    $("#addNewSubQuestOptDIV"+optionId).html(str);
	  }
	  
   }
   function buildSubOptions(id){
      suboptionId = suboptionId+1;
	  var str = '';
       str+='<fieldset id="subquestDIV'+suboptionId+'" class="alert"><div style="margin-bottom:5px;"><div><input type="text" key="'+suboptionId+'" class="subquestcls'+id+'" id="subquest'+suboptionId+'" /> <input id="subquestremark'+suboptionId+'" type="checkbox" /> Check to show remark  <span class="icon-minus-sign" style="cursor:pointer;" title="Remove Option" onclick="removeSubOption('+suboptionId+');" ></span></div></div></fieldset>';
		$("#subQuestionOptDIV"+id).append(str);
   }
   function removeSubOption(id){
     if(confirm("Do you want to delete this sub option?")){
      $('#subquestDIV'+id).remove();
	}
   }
function buildSurveyDetailsDivMain()
{
	
	console.log('${surveyList}');
var str='';
if('${surveyList}' != null && '${surveyList}'.length>0){
str+="<table class=\"table table-bordered table-striped table-hover\"  			style=\"font-size: small; font-weight: normal;\" border=\"1\">";
str+="<tr>";
str+="<td>Survey title</td>";
str+="<td>Add Question</td>";
str+="<td>Survey Form</td>";
str+="<td>Delete Servey</td>";
str+="<td>Analyse Servey</td>";
str+="</tr>";
str+="<c:forEach var='surveyLists' items='${surveyList}'>";
str+="<tr>";
str+="<td>${surveyLists.name}</td>";
str+="<td><a href=\"javascript:{}\"  onclick=\"openSurveyQuestionAddWindow(${surveyLists.id});\">Add Question</a></td>";
str+="<td><a href=\"javascript:{}\"  onclick=\"openSurveyForm(${surveyLists.id});\">Survey Form</a></td>";
str+="<td><a href=\"javascript:{}\"  onclick=\"removeSurvey(${surveyLists.id});\">Delete</a></td>";
str+="<td><a href=\"javascript:{}\"  onclick=\"analyseSurvey(${surveyLists.id});\">Analyse</a></td>";
str+="</tr>";
str+="</c:forEach>";
str+="</table>";
$("#surveyDetails").html(str);
}
else{
 $("#surveyDetails").html('<div style="font-weight:bold; font-size: 13px;">No Serveys are assigned for User.Please create Servey.</div>');
}
}
function analyseSurvey(id)
{

	var jsObj =
	{
		surveyId:id,
		task :"analyseSurvey"
    }
   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "analyseSurveyAction.action?"+rparam;						
   callAjax(jsObj,url);
   surveyAnalysis(id);
	/* catsteWiseSurveyAnalysis(id);
	surveyAnalysisBasedOnAge(id);
	surveyAnalysisBasedOnGender(id);
	surveyAnalysisBasedOnOption(id); */
}

function surveyAnalysis(id)
{
	var jsObj=
	{	
		surveyId     : id,
		task         : "getCasteWiseSurveyAnalysis" 
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSurveyDetailsAction.action?"+rparam;
	callAjax(jsObj,url); 
}

  function removeSurvey(id){
	var jsObj=
	      {
		    surveyId:id,
			task :"deleteSurvey"
           }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "deleteSurveyAction.action?"+rparam;						
      callAjax(jsObj,url);
  }
  function callAjax(jsObj,url)
{
	var myResults;

	var callback = {			
 		success : function( o ) 
		{
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);
			if (jsObj.task == "deleteSurvey" )
			{
				buildSurveyDetailsDiv(myResults);
			}
			else if(jsObj.task == "analyseSurvey" )
			{
				buildSurveyDetails(myResults);
			}
			/* else if(jsObj.task == "getAgeWiseSurveyAnalysis")
			{
				buildAgeWiseSurveyAnalysis(myResults);
			}
			else if(jsObj.task == "getGenderWiseSurveyAnalysis")
			{
				buildGenderWiseSurveyAnalysis(myResults);
			}
			else if(jsObj.task == "getOptionWiseSurveyAnalysis")
			{
				buildOptionWiseSurveyAnalysis(myResults);
			}*/
			else if(jsObj.task == "getCasteWiseSurveyAnalysis")
			{
				buildCasteWiseSurveyAnalysis(myResults);
			} 
		}
		catch (e)
			{
							     
			}  
 		},
 		scope : this,
		failure : function( o ) 
		{
			//alert( "Failed to load result" + o.status + " " + o.statusText);
		}
	   };

 	YAHOO.util.Connect.asyncRequest('POST', url, callback);
}


function buildSurveyDetailsDiv(myResults){
	
	var str='';
if(myResults != null){
	$("#surveyDetails").html("");
str+="<table class=\"table table-bordered table-striped table-hover\"  			style=\"font-size: small; font-weight: normal;\" border=\"1\">";
str+="<tr>";
str+="<td>Survey title</td>";
str+="<td>Add Question</td>";
str+="<td>Survey Form</td>";
str+="<td>delete Servey</td>";
str+="</tr>";
for(var i in myResults){
str+="<tr>";
str+="<td>"+myResults[i].name+"</td>";
str+="<td><a href=\"javascript:{}\"  onclick=\"openSurveyQuestionAddWindow("+myResults[i].id+");\">Add Question</a></td>";
str+="<td><a href=\"javascript:{}\"  onclick=\"openSurveyForm(${surveyLists.id});\">Survey Form</a></td>";
str+="<td><a href=\"javascript:{}\"  onclick=\"removeSurvey("+myResults[i].id+");\">Delete</a></td>";
str+="</tr>";
}
str+="</table>";
$("#surveyDetails").html(str);
}
else{
 $("#surveyDetails").html('<div style="font-weight:bold;">Data Not Available</div>');
}
}
function surveyorMethod(){
var urlstr = "surveyorProfileSaveAction.action";
 var browser1 = window.open(urlstr,"surveyorProfileSaving","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
}
function surveyorMethod1(){
var urlstr = "surveyorProfileSaveAction.action?surveyorId=36";
 var browser1 = window.open(urlstr,"surveyorProfileSaving","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
}



/* function surveyAnalysisBasedOnAge(id)
{
	
	var jsObj=
	{	
		surveyId     : id,
		task         : "getAgeWiseSurveyAnalysis" 
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSurveyDetailsAction.action?"+rparam;
	callAjax(jsObj,url); 
}
function surveyAnalysisBasedOnGender(id)
{
	
	var jsObj=
	{	
		surveyId     : id,
		task         : "getGenderWiseSurveyAnalysis" 
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSurveyDetailsAction.action?"+rparam;
	callAjax(jsObj,url); 
}
function surveyAnalysisBasedOnOption(id)
{
	
	var jsObj=
	{	
		surveyId     : id,
		task         : "getOptionWiseSurveyAnalysis" 
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSurveyDetailsAction.action?"+rparam;
	callAjax(jsObj,url); 
} */
function buildSurveyDetails(result)
{
	if(result != null)
	{
		var str = '';
		var k=0;
    	for(var i in result)
		{
		 k++;
		str +='<div><div id="casteAnalysisDiv'+k+'" class="widget blue whitegloss"  style="display: inline-block; color: rgb(0, 0, 0); margin-left: -38px; margin-top: 36px;width: 942px;padding-bottom: 12px;"><h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;" id="SelectionHeading">'+result[i].question+'</h4>';
		str +='<table class="table table-bordered table-striped table-hover" style="background-color: transparent; width: 560px; margin-top: 15px;" id="tableId'+k+'">'
		str+='<tr>';
		str+='<th>Option</th>';
		str+='<th>Votes </th>';
		str+='<th> percentage</th>';
		str+='</tr>';
		
		for(var j in result[i].subOptionList)
		{
		str+='<tr>';
		str+='<td>'+result[i].subOptionList[j].option+'';
		str+='</td>';
		str+='<td>'+result[i].subOptionList[j].votesObtained+'';
		str+='</td>';
		str+='<td>'+result[i].subOptionList[j].percentage+'';
		str+='</td>';
		str+='</tr>';
		
		}
		str+='</table>';
		str+='<div id="chartDiv'+k+'" style="position: relative; float: right; margin-top: -182px;"></div>';
		str+='</div>';
		}
		$('#analyseDiv').html(str);	
		var k1=0;
		for(var i in result)
		{
			k1++;
		var subList = result[i].subOptionList;
		buildChart(subList,'chartDiv'+k1+'');
		}
	}
	
}
/* function buildAgeWiseSurveyAnalysis(myResults)
{
	if(myResults != null)
	{
		$('#ageWiseAnalysisHeading').show();
		var k = 0;
		var str = "";
		for(var i in myResults)
		{
			k++;
			str +='<div><div id="AgeAnalysisDiv'+k+'" class="widget blue whitegloss"  style="display: inline-block; color: rgb(0, 0, 0); margin-left: 10px; margin-top: 36px;width: 942px;padding-bottom: 12px;"><h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;" id="SelectionHeading">'+myResults[i].question+'</h4>';
			str +='<table class="table table-bordered table-striped table-hover" style="background-color: transparent; width: 560px; margin-top: 15px;" id="agetableId'+k+'">'
			str +='<tr>';
			str +='<td>Options</td>';
			str +='<td>18-22%</td>';
			str +='<td>23-30%</td>';
			str +='<td>31-45%</td>';
			str +='<td>45-50%</td>';
			str +='<td>Above60%</td>';
			str +='</tr>';
			for(var j in myResults[i].surveyAgeWiseDetailsVO)
			{
			str +='<tr>';
			str +='<td>'+myResults[i].surveyAgeWiseDetailsVO[j].option+'</td>';
			str +='<td>'+myResults[i].surveyAgeWiseDetailsVO[j].ageBt18To25Perc+'</td>';
			str +='<td>'+myResults[i].surveyAgeWiseDetailsVO[j].ageBt26To35Perc+'</td>';
			str +='<td>'+myResults[i].surveyAgeWiseDetailsVO[j].ageBt36To45Perc+'</td>';
			str +='<td>'+myResults[i].surveyAgeWiseDetailsVO[j].ageBt46To60Perc+'</td>';
			str +='<td>'+myResults[i].surveyAgeWiseDetailsVO[j].ageAbove60Perc+'</td>';
			str +='</tr>';
			}
			str +='</table>'
			str+='<div id="ageChartDiv'+k+'" style="position: relative; float: right; margin-top: -212px;"></div>';
			str +='</div>';
		}
		$('#ageWiseSurveyAnalysis').html(str);
		var k1=0;
		for(var i in myResults)
		{
			k1++;
		var subList = myResults[i].surveyAgeWiseDetailsVO;
		buildChartForSurveyAnalysis(subList,'ageChartDiv'+k1+'');
		}
	}
}
function buildGenderWiseSurveyAnalysis(myResults)
{
	if(myResults != null)
	{
		$('#genderWiseAnalysisHeading').show();
		var k=0;
		var str = "";
		for(var i in myResults)
		{
			k++;
			str +='<div><div id="genderAnalysisDiv'+k+'" class="widget blue whitegloss"  style="display: inline-block; color: rgb(0, 0, 0); margin-left: 10px; margin-top: 36px;width: 942px;padding-bottom: 12px;"><h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;" id="SelectionHeading">'+myResults[i].question+'</h4>';
			str +='<table class="table table-bordered table-striped table-hover" style="background-color: transparent; width: 560px; margin-top: 15px;" id="gendertableId'+k+'">';
			str +='<tr>';
			str +='<td>Options</td>';
			str +='<td>Male</td>';
			str +='<td>Male %</td>';
			str +='<td>Female</td>';
			str +='<td>Female %</td>';
			str +='</tr>';
			for(var j in myResults[i].surveyAgeWiseDetailsVO)
			{
			str +='<tr>';
			str +='<td>'+myResults[i].surveyAgeWiseDetailsVO[j].option+'</td>';
			str +='<td>'+myResults[i].surveyAgeWiseDetailsVO[j].maleresponderTotal+'</td>';
			str +='<td>'+myResults[i].surveyAgeWiseDetailsVO[j].maleperc+'</td>';
			str +='<td>'+myResults[i].surveyAgeWiseDetailsVO[j].femaleRespondersTotal+'</td>';
			str +='<td>'+myResults[i].surveyAgeWiseDetailsVO[j].femaleperc+'</td>';
			str +='</tr>';			
			}
			str +='</table>'
			str+='<div id="genderChartDiv'+k+'" style="position: relative; float: right; margin-top: -212px;"></div>';
			str +='</div>';
		}
		$('#genderWiseSurveyAnalysis').html(str);
		var k1=0;
		for(var i in myResults)
		{
			k1++;
		var subList = myResults[i].surveyAgeWiseDetailsVO;
		buildChartForSurveyAnalysis(subList,'genderChartDiv'+k1+'');
		}
	}
}
function buildOptionWiseSurveyAnalysis(myResults)
{
	if(myResults != null)
	{
		$('#optionWiseAnalysisHeading').show();
		var k=0;
		var str = "";
		for(var i in myResults)
		{
			k++;
			str +='<div><div id="optionAnalysisDiv'+k+'" class="widget blue whitegloss"  style="display: inline-block; color: rgb(0, 0, 0); margin-left: 10px; margin-top: 36px;width: 942px;padding-bottom: 12px;"><h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;" id="SelectionHeading">'+myResults[i].question+'</h4>';
			str +='<table class="table table-bordered table-striped table-hover" style="background-color: transparent; width: 560px; margin-top: 15px;" id="optiontableId'+k+'">'	
			str +='<tr>';
			str +='<td>Options</td>';
			str +='<td>Total</td>';
			str +='<td>percentage</td>';
			str +='</tr>';
			for(var j in myResults[i].surveyAgeWiseDetailsVO)
			{
			str +='<tr>';
			str +='<td>'+myResults[i].surveyAgeWiseDetailsVO[j].option+'</td>';
			str +='<td>'+myResults[i].surveyAgeWiseDetailsVO[j].optionCount+'</td>';
			str +='<td>'+myResults[i].surveyAgeWiseDetailsVO[j].optionPerc+'</td>';
			str +='</tr>';
			}
			str +='</table>';
			str+='<div id="optionChartDiv'+k+'" style="position: relative; float: right; margin-top: -212px;"></div>';
			str +='</div>';
		}
		$('#optionWiseSurveyAnalysis').html(str);
		var k1=0;
		for(var i in myResults)
		{
			k1++;
		var subList = myResults[i].surveyAgeWiseDetailsVO;
		buildChartForOptionSurveyAnalysis(subList,'optionChartDiv'+k1+'');
		}
	}
}
*/
function buildCasteWiseSurveyAnalysis(myResults)
{
	if(myResults != null)
	{
		var str = "";
		var k = 0;
		for(var i in myResults)
		{
			str +='<div id="surveyAnalysis'+k+'" class="widget blue whitegloss"  style="display: inline-block; color: rgb(0, 0, 0); margin-left: 10px; margin-top: 36px;width: 942px;padding-bottom: 12px;"><h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;" id="SelectionHeading"><b style="color:blue;">Question : </b>'+myResults[i].question+'</h4>';
				
			if(myResults[i].ageWiseSurveyVO != null)
			{
				str +='<div id="ageWiseAnalysis'+k+'" class="widget green whitegloss"  style="display: inline-block; color: rgb(0, 0, 0); margin-left: 10px; margin-top: 36px;width: 897px;padding-bottom: 12px;"><h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;" id="SelectionHeading">Age Wise Survey Analysis</h4>';
				str += '<table class="table table-bordered table-striped table-hover" style="background-color: transparent; width: 560px; margin-top: 15px;" id="ageWiseTable'+k+'">';
				str +='<tr>';
				str += '<thead class="info">';
				str +='<th>Options</th>';
				str +='<th>18-22%</th>';
				str +='<th>23-30%</th>';
				str +='<th>31-45%</th>';
				str +='<th>45-50%</th>';
				str +='<th>Above60%</th>';
				str += '</thead>';	
				str +='</tr>';
					
				for(var m in myResults[i].ageWiseSurveyVO.agewiseSurveyAnalysisVO)
				{
				str +='<tr>';
				str +='<td>'+myResults[i].ageWiseSurveyVO.agewiseSurveyAnalysisVO[m].option+'</td>';
				str +='<td>'+myResults[i].ageWiseSurveyVO.agewiseSurveyAnalysisVO[m].ageBt18To25Perc+'</td>';
				str +='<td>'+myResults[i].ageWiseSurveyVO.agewiseSurveyAnalysisVO[m].ageBt26To35Perc+'</td>';
				str +='<td>'+myResults[i].ageWiseSurveyVO.agewiseSurveyAnalysisVO[m].ageBt36To45Perc+'</td>';
				str +='<td>'+myResults[i].ageWiseSurveyVO.agewiseSurveyAnalysisVO[m].ageBt46To60Perc+'</td>';
				str +='<td>'+myResults[i].ageWiseSurveyVO.agewiseSurveyAnalysisVO[m].ageAbove60Perc+'</td>';
				str +='</tr>';	
				}
				str += '</table>';
				/* str+='<div id="ageChartDiv'+k+'" style="position: relative; float: right; margin-top: -212px;"></div>';
				
				 var k1=0;
				for(var z in myResults[i].ageWiseSurveyVO)
				{alert("yes");
					k1++;
				var subList = myResults[i].ageWiseSurveyVO.agewiseSurveyAnalysisVO;
				alet(subList);
				buildChartForSurveyAnalysis(subList,'ageChartDiv'+k1+'');
				}  */
				str +='</div>';
			}
			if(myResults[i].genderWiseSurveyVO != null)
			{
				str +='<div id="genderWiseAnalysis'+k+'" class="widget green whitegloss"  style="display: inline-block; color: rgb(0, 0, 0); margin-left: 10px; margin-top: 36px;width: 897px;padding-bottom: 12px;"><h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;" id="SelectionHeading">Gender Wise Survey Analysis</h4>';
				str +='<table class="table table-bordered table-striped table-hover" style="background-color: transparent; width: 560px; margin-top: 15px;" id="gendertableId'+k+'">';
				str +='<tr>';
				str += '<thead class="info">';
				str +='<th>Options</th>';
				str +='<th>Male</th>';
				str +='<th>Male %</th>';
				str +='<th>Female</th>';
				str +='<th>Female %</th>';
				str += '</thead>';	
				str +='</tr>';
				
				for(var j in myResults[i].genderWiseSurveyVO.genderWiseSurveyAnalysisVO)
				{
				str +='<tr>';
				str +='<td>'+myResults[i].genderWiseSurveyVO.genderWiseSurveyAnalysisVO[j].option+'</td>';
				str +='<td>'+myResults[i].genderWiseSurveyVO.genderWiseSurveyAnalysisVO[j].maleresponderTotal+'</td>';
				str +='<td>'+myResults[i].genderWiseSurveyVO.genderWiseSurveyAnalysisVO[j].maleperc+'</td>';
				str +='<td>'+myResults[i].genderWiseSurveyVO.genderWiseSurveyAnalysisVO[j].femaleRespondersTotal+'</td>';
				str +='<td>'+myResults[i].genderWiseSurveyVO.genderWiseSurveyAnalysisVO[j].femaleperc+'</td>';
				str +='</tr>';	
				}
				str += '</table>';
				
				/* str+='<div id="genderChartDiv'+k+'" style="position: relative; float: right; margin-top: -212px;"></div>';
				var k1=0;
				for(var v in myResults[i].genderWiseSurveyVO.surveyAgeWiseDetailsVO)
				{
					k1++;
				var subList = myResults[i].genderWiseSurveyVO.surveyAgeWiseDetailsVO[v];
				buildChartForSurveyAnalysis(subList,'genderChartDiv'+k1+'');
				} */
				str +='</div>';
			}
			if(myResults[i].optionWiseSurveyVO != null)
			{
				str +='<div id="optionWiseAnalysis'+k+'" class="widget green whitegloss"  style="display: inline-block; color: rgb(0, 0, 0); margin-left: 10px; margin-top: 36px;width: 897px;padding-bottom: 12px;"><h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;" id="SelectionHeading">Option Wise Survey Analysis</h4>';
				str +='<table class="table table-bordered table-striped table-hover" style="background-color: transparent; width: 560px; margin-top: 15px;" id="optiontableId'+k+'">'	
				str +='<tr>';
				str += '<thead class="info">';
				str +='<th>Options</th>';
				str +='<th>Total</th>';
				str +='<th>percentage</th>';
				str += '</thead>';
				str +='</tr>';
				for(var x in myResults[i].optionWiseSurveyVO.optionWiseSurveyAnalysisVO)
				{
				str +='<tr>';
				str +='<td>'+myResults[i].optionWiseSurveyVO.optionWiseSurveyAnalysisVO[x].option+'</td>';
				str +='<td>'+myResults[i].optionWiseSurveyVO.optionWiseSurveyAnalysisVO[x].optionCount+'</td>';
				str +='<td>'+myResults[i].optionWiseSurveyVO.optionWiseSurveyAnalysisVO[x].optionPerc+'</td>';
				str +='</tr>';
				}
				str += '</table>';
				/* str+='<div id="optionChartDiv'+k+'" style="position: relative; float: right; margin-top: -212px;"></div>';
				var k1=0;
				for(var n in myResults[i].optionWiseSurveyVO.surveyAgeWiseDetailsVO)
				{
					k1++;
				var subList = myResults[i].surveyAgeWiseDetailsVO[n];
				buildChartForOptionSurveyAnalysis(subList,'optionChartDiv'+k1+'');
				} */
				str +='</div>';
			}
			if(myResults[i].casteWiseSurveyVO != null)
			{
			str +='<div id="casteWiseAnalysis'+k+'" class="widget green whitegloss"  style="display: inline-block; color: rgb(0, 0, 0); margin-left: 10px; margin-top: 36px;width: 897px;padding-bottom: 12px;"><h4 class="" style="margin: 0px -20px; padding: 10px 10px 10px 20px;" id="SelectionHeading">Caste Wise Survey Analysis</h4>';
			str +='<table class="table table-bordered table-striped table-hover" style="background-color: transparent; width: 560px; margin-top: 15px;" id="castetableId'+k+'">'
			str += '<tr>';
			str += '<thead class="info">';
			str +='<th>Options</th>';
			for(var a in myResults[i].casteWiseSurveyVO.casteWiseSurveyAnalysisVO[0].subList)
			{
			str +='<th>'+myResults[i].casteWiseSurveyVO.casteWiseSurveyAnalysisVO[0].subList[a].name+'</th>';
			} 
			str += '</thead>';
			str += '</tr>';
			
			for(var b in myResults[i].casteWiseSurveyVO.casteWiseSurveyAnalysisVO)
			{
			str += '<tr>';	
			str +='<td>'+myResults[i].casteWiseSurveyVO.casteWiseSurveyAnalysisVO[b].name+'</td>';
			for(var c in myResults[i].casteWiseSurveyVO.casteWiseSurveyAnalysisVO[i].subList)
			{
			str +='<td>'+myResults[i].casteWiseSurveyVO.casteWiseSurveyAnalysisVO[b].subList[c].percentage+'</td>';
			}
			str += '</tr>';	
			}
			str += '</table>';
			str +='</div>';
			}
			
			
			str +='</div>';
			
		}
		
		$('#casteWiseSurveyAnalysis').html(str);
	}
}
 </script>
</head>
<body>
<div>
<div style="margin-left: 236px; margin-bottom: -26px; padding-bottom: 26px;">
<!--<span id="surveyorSpan" class='btn btn-info' onclick='openModal()' style="margin-bottom: -26px; margin-top: 32px;">Creating Surveyor</span>-->
<span class='btn' onclick='surveyorMethod()'>Surveyor1</span>
<span class='btn' onclick='surveyorMethod1()'>Surveyor1</span>
<div><input type="button" value="Creating Surveyor" class='btn btn-info' style="margin-bottom: -26px; margin-top: 32px;" onClick="openModal();"></input></div>
<div style="margin-left: 276px;"><input type="button" value="Creating Survey" id="surveyBtn" class="btn btn-info"  style="margin-left: 119px;"/></div>
</div>
     <!-- <input type="button" onclick="openSurveyQuestionAddWindow();"  value="Add Question" />-->
	 
	 
	 <div style="display:none;" id="dialogWindowDiv" title="Add New Question">
	   <div class="container">
	    <div class="row">
		  <div class="container row span8 offset2 well">
		    <form class="row-fluid">
			      <label>Question</label>
				  <input type="text" id="questionTitle" placeholder="Enter Question..." name="Question" class="span12">
					<div><input type="checkbox" id="showRemark" /> Check to show remark</div>
					
				  <label>Select Question Type </label> 
				  <select class="span12" id="questionType" onchange="buildQuestion();">
					  <option value="0">Select</option>
					  <option value="1">Multiple choice with single select</option>
				   </select>
					
					<div style="margin-top:5px;" id="questionOptionsDIV"></div>
					<div style="margin-top:5px;" id="addNewOptionsDIV"></div>
					<input type="button" class="btn" value="Add Question" onclick="saveQuestion();"/>
			</form>		
		  </div>			
		</div>			
      </div>
	 </div>
	
<div id="errorDiv"></div>


<div style="width: 530px; margin-left: auto; margin-right: auto;margin-top: 35px;">
<div id="surveyDetails"></div>

</div>



	 </div>
<!--	 
 <div class="modal hide fade" id="myModal">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">X</a>
			<h4>Surveyor Personal Information </h4>
		</div>
		<div class="modal-body">
			<p style="font-size:16px;font-weight:bold;"></p>
			 <form class="form-horizontal" name='personalInfoForm' action='saveSurveyorInfoAction.action' method='post'>
				<legend>Personal Information</legend>
				<div class="control-group">
					<label class="control-label" for="name">Name</label>
					<div class="controls">
						<input type="text" id="name" placeholder="Your Name" name='surveyorPersonalInfoVO.name'>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="contactnumber">Contact Number</label>
					<div class="controls">
						<!--<label class="control-label" for="mobileno">Mobile Number</label>-->
						<!--<input type="text" id="mobileNumber" placeholder="Mobile Number" name='surveyorPersonalInfoVO.mobileNumber'>
						<!--<label class="control-label" for="phoneno">Phone Number</label>-->
						<!--<input type="text" id="phoneNumber" placeholder="Phone Number" name='surveyorPersonalInfoVO.phoneNumber'>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="age" >Age</label>
					<div class="controls">
						<input type="text" id="age" placeholder="Age" name='surveyorPersonalInfoVO.age'>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="emailid">Email-Id</label>
					<div class="controls">
						<input type="text" id="emailId" placeholder="Email-Id" name='surveyorPersonalInfoVO.email'>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="qualification">Qualification</label>
					<div class="controls">
						<select class='select' id='qualificationId' name='surveyorPersonalInfoVO.qualification'>
							<option value="0">Select</option>
							<option value="1" selected='selected'>X</option>
							<option value="2">Inter</option>
							<option value="3">UG</option>
							<option value="4">PG</option>
						</select>	
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="occupation">Occupation</label>
					<div class="controls">
						<select class='select' id='occupationId' name='surveyorPersonalInfoVO.occupation'>
							<option value="0">Select</option>
							<option value="1" selected='selected'>Farmer</option>
							<option value="2">Job</option>
						</select>	
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="caste">Caste</label>
					<div class="controls">
						<select class='select' id='casteId' name='surveyorPersonalInfoVO.caste'>
							<option value="0">Select</option>
							<option value="1" >SC/ST</option>
							<option value="2">BC</option>
							<option value="3" selected='selected'>GENERAL</option>
						</select>	
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="gender">Gender</label>
					<div class="controls">
						<select class='select' id='genderId' name='surveyorPersonalInfoVO.gender'>
							<option value="0">Select</option>
							<option value="1" selected='selected'>Male</option>
							<option value="2">Female</option>
						</select>	
					</div>
				</div>
				
				<legend>Address</legend>
				<div class="control-group">
					<label class="control-label" for="state">State</label>
					<div class="controls">
						<s:select theme="simple" list="statesList"name="surveyorPersonalInfoVO.state" listKey="id" listValue="name" headerKey="0"headerValue="Select State"/> 

					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="district">District</label>
					<div class="controls">
						<select class='select' id='districtId' name='surveyorPersonalInfoVO.district'>
							<option value="0">Select</option>
							<option value="1" selected='selected'>Prakasam</option>
							<option value="2">Srikakulam</option>
						</select>	
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="tehsil">Tehsil</label>
					<div class="controls">
						<select class='select' id='tehsilId' name='surveyorPersonalInfoVO.tehsil'>
							<option value="0">Select</option>
							<option value="1" selected='selected'>YPUDI</option>
							<option value="2">MARTUR</option>
						</select>	
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="village">Village</label>
					<div class="controls">
						<select class='select' id='villageId' name='surveyorPersonalInfoVO.township'>
							<option value="0">Select</option>
							<option value="1" selected='selected'>CHPALEM</option>
							<option value="2">VPADU</option>
						</select>	
					</div>
				</div>
				
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			<button class="btn btn-primary" onclick="personalInfoForm.submit()">Save changes</button>
		</div>
	</div>
	
	<div>
		   
		<div id="analyseDiv" style="width:900px;margin-left:auto;margin-right:auto;"></div>
		
		<div class="" align="center" style="background: none repeat scroll 0px 0px rgb(49, 152, 182); border-radius: 4px 4px 4px 4px; margin-left: 10px; width: 977px; font-family: arial; font-size: 18px; color: white; padding-bottom: 4px; margin-bottom: 5px; margin-top: 19px; font-weight: bolder; padding-top: 10px; height: 25px;display:none;" id="casteWiseAnalysisHeading">SURVEY ANALYSIS BASED ON CASTE WISE</div>
		<div id="casteWiseSurveyAnalysis"></div>
		
		<div class="" align="center" style="background: none repeat scroll 0px 0px rgb(49, 152, 182); border-radius: 4px 4px 4px 4px; margin-left: 10px; width: 977px; font-family: arial; font-size: 18px; color: white; padding-bottom: 4px; margin-bottom: 5px; margin-top: 19px; font-weight: bolder; padding-top: 10px; height: 25px;display:none;" id="ageWiseAnalysisHeading">SURVEY ANALYSIS BASED ON AGE WISE</div>
		<div id="ageWiseSurveyAnalysis"></div>


		
		<div class="" align="center" style="background: none repeat scroll 0px 0px rgb(49, 152, 182); border-radius: 4px 4px 4px 4px; margin-left: 10px; width: 977px; font-family: arial; font-size: 18px; color: white; padding-bottom: 4px; margin-bottom: 5px; margin-top: 19px; font-weight: bolder; padding-top: 10px; height: 25px;display:none;" id="genderWiseAnalysisHeading" >SURVEY ANALYSIS BASED ON GENDER WISE</div>
		<div id="genderWiseSurveyAnalysis"></div>

		
		<div class="" align="center" style="background: none repeat scroll 0px 0px rgb(49, 152, 182); border-radius: 4px 4px 4px 4px; margin-left: 10px; width: 977px; font-family: arial; font-size: 18px; color: white; padding-bottom: 4px; margin-bottom: 5px; margin-top: 19px; font-weight: bolder; padding-top: 10px; height: 25px;display:none;"  id="optionWiseAnalysisHeading">SURVEY ANALYSIS BASED ON OPTION WISE</div>
		<div id="optionWiseSurveyAnalysis"></div>
	</div>
-->

<script type="text/javascript">
$(document).ready(function(){
				
  $("#surveyBtn").click(function(){
	 var browser1 = window.open("surveyDetailsAction.action?","surveyDetails","scrollbars=yes,height=600,width=980,left=200,top=200");	
    browser1.focus();
  });

});//End of ready
/*function openModal(){
		$('#myModal').modal('show');
	}*/
</script>
<script type="text/javascript">
buildSurveyDetailsDivMain();
</script>
</body>
</html>