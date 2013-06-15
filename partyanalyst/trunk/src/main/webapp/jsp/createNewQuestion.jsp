<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Question</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
<!-- YUI Dependency files (Start) -->
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
		
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	

	<!-- YUI Dependency files (End) -->
<script type="text/javascript">
   function saveQuestion(){
	   $("#errorDiv").html("");
	   var flag=true;
       var question = $("#questionTitle").val();
	   var questionType = $("#questionType option:selected").val();
	   var showRemark = $("#showRemark").is(':checked');
	   
if(question == ""){
	$("#errorDiv").html("Question is mandatory..").css({"display":"block","color":"red","font":"bold"});
	return;
}
if(questionType==0){
	$("#errorDiv").html("Question Type is mandatory..").css({"display":"block","color":"red","font":"bold"});
		return;
}

$(".mainoption").each(function(){
	var val = $(this).val();
	if($("#errorDiv").text()==""){
	if(val == ""){
	$("#errorDiv").html("Option is mandatory..").css({"display":"block","color":"red","font":"bold"});
	flag = false;
	}
	}
});

$(".subOptionsClass").each(function(){
	var val = $(this).val();
	if($("#errorDiv").text()==""){
	if(val == ""){
	$("#errorDiv").html("Sub Question is mandatory..").css({"display":"block","color":"red","font":"bold"});
	flag = false;
	}
	}
});
$(".subOptionsListClass").each(function(){
	var val = $(this).val();
	if($("#errorDiv").text()==""){
	if(val == 0){
	$("#errorDiv").html("Sub Question Type is mandatory..").css({"display":"block","color":"red","font":"bold"});
	flag = false;
	}
	}
});
$(".subOptionClass").each(function(){
	var val = $(this).val();
	if($("#errorDiv").text()==""){
	if(val == 0){
	$("#errorDiv").html("Sub Question option is mandatory..").css({"display":"block","color":"red","font":"bold"});
	flag = false;
	}
	}
});
if(flag == false)
	return false;

	if(flag == true)
		{
		$("#errorDiv").html("");
	   var mainOptionsArray = new Array();
	   	if(questionType == 4)
	{
	 $('.mainoption').each(function() {
		
	 var obj = {};
	  var key = $(this).attr("key");
	  obj["option"] = $(this).val();
	   var subQueArray = new Array();
	  $('.mainoption'+key).each(function() {
			      var subobj = {};
				  var subkey = $(this).attr("key");
				  subobj["subquestion"] = $(this).val();;
				  subQueArray.push(subobj);
			  });
			   obj["subQuestions"] = subQueArray;
			   mainOptionsArray.push(obj); 
	 });
	 
	 task = "saveQuestionForMultipleText";
	}
	else
	{
		task="saveQuestion";
       $('.mainoption').each(function() {
	      var obj = {};
		  var key = $(this).attr("key");
		  var subOptionsArray = new Array();
		  obj["option"] = $(this).val();
	      obj["showRemark"] = $("#optshowremark"+key).is(':checked');
		  obj["hasSubQuestion"] = $("#hassubDIV"+key).is(':checked');
            if($("#hassubDIV"+key).is(':checked')){
			  obj["subquestion"] = $("#subquestionTitle"+key).val();
	          obj["subquestionType"] = $("#subquestionType"+key+" option:selected").val();
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

		   var jsObj=
		  {
			question:question,
			questionType:questionType,
			showRemark:showRemark,
			mainOptionsArray:mainOptionsArray,
			task:task
		  };
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveSurveyQuestionAction.action?"+rparam;						
		callAjax(jsObj,url);
		console.log(mainOptionsArray);
   }
   
   function openSurveyQuestionAddWindow(){
      $( "#dialogWindowDiv" ).dialog({
	    height:445,
		width:612
	  });
   }
   var optionId = 0;
   var suboptionId = 0;
   function buildQuestion(){
      optionId = 0;
      var str = '';
      $("#questionOptionsDIV").html("");
	  $("#addNewOptionsDIV").html("");
	  if($("#questionType option:selected").val() == 0)
	   return;
	  if($("#questionType option:selected").val() != 4){
	    str+='<fieldset id="optionDIV0" class="alert alert-info" >';
	    str+='<div><div style="margin-bottom:5px;">Options : </div><div><input  key="'+optionId+'" class="mainoption" type="text" id="option0" /> <input type="checkbox" id="optshowremark0"/> Show remark  &nbsp;<input type="checkbox" id="hassubDIV0" onclick="showSubQuestion('+optionId+',this.id);"/> Has sub options</div></div>';
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
	  }else{
	    str+='<fieldset id="optionDIV0" class="alert alert-info" >';
	    str+='<div><div style="margin-bottom:5px;">Options : </div><div id="divoption0"><input  key="'+optionId+'" class="mainoption" type="text" id="option0" /> </div></div>';
		str+='<div class="span10 offset1">';
	    str+='<div id="addNewSubQuestOptDIV'+optionId+'" style="margin-bottom:5px;"><span onclick="buildTextOptions('+optionId+');" class="btn btn-small"><i class="icon-th-list"> </i> Add more text boxes to this option</span></div>';
		str+='</div>';
		str+='</fieldset>';
		$("#questionOptionsDIV").html(str);
		str ='<span onclick="buildMultipleOptions();" class="btn btn-small"><i class="icon-th-list"> </i> Add More Options</span>';
		//str ='<div><input type="button" class="btn" onclick="buildOptions();" value="Add Option" > </div>';
	    $("#addNewOptionsDIV").html(str);
	  
	  }
	  
   }
   
   function buildTextOptions(id){
    suboptionId = suboptionId+1;
    var str = '';
    str+='<div id="textbox'+suboptionId+'"><input  class="mainoption mainoption'+id+'" type="text"  /><span class="icon-minus-sign" style="cursor:pointer;" title="Remove Text Box" onclick="removeTextBox('+suboptionId+');" ></span></div>';
	$("#divoption"+id).append(str);
   }
   
   function removeTextBox(id){
    if(confirm("Do you want to delete this text box?")){
      $('#textbox'+id).remove();
	}
   }
   function buildMultipleOptions(){
      optionId = optionId+1;
	  var str = '';
        str+='<fieldset id="optionDIV'+optionId+'" class="alert alert-info" >';
	    str+='<div style="margin-bottom:5px;"><div id="divoption'+optionId+'"><input  key="'+optionId+'" class="mainoption" type="text" id="option'+optionId+'" /> <span class="icon-trash" style="cursor:pointer;" title="Remove Option" onclick="removeOption('+optionId+');" ></span></div></div>';
		str+='<div class="span10 offset1">';
	    str+='<div id="addNewSubQuestOptDIV'+optionId+'" style="margin-bottom:5px;"><span onclick="buildTextOptions('+optionId+');" class="btn btn-small"><i class="icon-th-list"> </i> Add more text boxes to this option</span></div>';
		str+='</div>';
		str+='</fieldset>';
		$("#questionOptionsDIV").append(str);
   }
   
   function buildOptions(){
      optionId = optionId+1;
	  var str = '';
	   str+='<fieldset id="optionDIV'+optionId+'" class="alert alert-info" >';
       str+='<div  style="margin-bottom:5px;"><div><input  key="'+optionId+'" class="mainoption" type="text" id="optionDIV'+optionId+'" /> <input type="checkbox" id="optshowremark'+optionId+'" /> Show remark  &nbsp;<input type="checkbox"  id="hassubDIV'+optionId+'" onclick="showSubQuestion('+optionId+',this.id);" /> Has sub options <span class="icon-trash" style="cursor:pointer;" title="Remove Option" onclick="removeOption('+optionId+');" ></span></div></div>';
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
	   str+='<div><label>Question</label> <input type="text" class="span12 subOptionsClass" id="subquestionTitle'+optionId+'" /> </div>';
       str+='<div>';
	   str+='  <label>Select Question Type</label> <select class="span12 subOptionsListClass" id="subquestionType'+optionId+'" name ="questionType" onchange="buildSubQuestion('+optionId+');">';
	  /* str+='   <option value="0">Select</option>';
	   str+='   <option value="1">Multiple choice with single select</option>';*/
	   str+='  </select>';
	   str+=' </div>';
	   $('#subQuestionTypeDIV'+optionId).html(str);
	   iterateQuestionType(optionTypeDetails,optionId);
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
	  
	  if($("#subquestionType"+optionId+" option:selected").val() != 4){
	    str+='<fieldset id="subquestDIV'+suboptionId+'" class="alert"><div><div style="margin-bottom:5px;">Sub Options : </div><div><input type="text" key="'+suboptionId+'" class="subOptionClass subquestcls'+optionId+'" id="subquest'+suboptionId+'" /> <input id="subquestremark'+suboptionId+'" type="checkbox" /> Show remark </div></div></fieldset>';
		$("#subQuestionOptDIV"+optionId).html(str);
		str ='<span onclick="buildSubOptions('+optionId+');" class="btn btn-small"><i class="icon-th-list"> </i> Add More Sub Options</span>';
		//str ='<div><input type="button" class="btn" onclick="buildSubOptions('+optionId+');" value="Add Sub Option" > </div>';
	    $("#addNewSubQuestOptDIV"+optionId).html(str);
	  }
	  
   }
   function buildSubOptions(id){
      suboptionId = suboptionId+1;
	  var str = '';
       str+='<fieldset id="subquestDIV'+suboptionId+'" class="alert"><div style="margin-bottom:5px;"><div><input type="text" key="'+suboptionId+'" class="subOptionClass subquestcls'+id+'" id="subquest'+suboptionId+'" /> <input id="subquestremark'+suboptionId+'" type="checkbox" /> Show remark  <span class="icon-minus-sign" style="cursor:pointer;" title="Remove Option" onclick="removeSubOption('+suboptionId+');" ></span></div></div></fieldset>';
		$("#subQuestionOptDIV"+id).append(str);
   }
   function removeSubOption(id){
     if(confirm("Do you want to delete this sub option?")){
      $('#subquestDIV'+id).remove();
	}
   }
   
   
function iterateQuestionType(optionTypeDetails,optionId){
	var elmt = document.getElementById("subquestionType"+optionId);
	var option = document.createElement('option');
	clearOptionsListForSelectElmtId(elmt);
	/*option.value="0";
	option.text="Select";
		try
	{
	elmt.add(option,null);	

	}
	catch (ex)
	{
		elmt.add(option);
	}*/
	//console.log(optionTypeDetails.optionTypeArr);	
	for(var i in optionTypeDetails.optionTypeArr)
	{	

		var option=document.createElement('option');
        if(optionTypeDetails.optionTypeArr[i].id != 3 && optionTypeDetails.optionTypeArr[i].id != 4){		
			option.value=optionTypeDetails.optionTypeArr[i].id;
			option.text=optionTypeDetails.optionTypeArr[i].value;		
			try
			{
			  elmt.add(option,null);	
			}
			catch (ex)
			{
			elmt.add(option);
			}
		}
	}

}

function clearOptionsListForSelectElmtId(elmtId)
{
	var elmt = elmtId;
	if(!elmt)
		return;	
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
}
function callAjax(jsObj,url)
{
	var myResults;

	var callback = {			
 		success : function( o ) 
		{
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);
			if (jsObj.task == "saveQuestionForMultipleText" || jsObj.task == "saveQuestion" )
			{
				showStatusForsaveQuestion(myResults);
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

function showStatusForsaveQuestion(myResults)
{
	var str='';
	if(myResults.resultCode == 0)
	{
		$("#msgDiv").html(" Question Saved Successfully").css("color","green");
	}
	else
		$("#msgDiv").html(" Data could not saved due to some error..").css("color","red");;

}
   var optionTypeDetails={
				optionTypeArr:[],
					};
					
   <c:forEach var="optiontypes" items="${questionType}">
	var ob={
			id:'${optiontypes.id}',
			value:'${optiontypes.name}'
			};
		optionTypeDetails.optionTypeArr.push(ob);	
</c:forEach>

</script>
</head>
<body>
	 <div id="dialogWindowDiv">
	   <div class="container">
	    <div class="row">
		  <div class="container row span8 offset2 well">
		  <span id="msgDiv"></span>
		    <form class="row-fluid">
			      <label>Question</label>
				  <input type="text" id="questionTitle" placeholder="Enter Question..." name="Question" class="span12">
					<div style="margin-bottom:8px;margin-left:5px;"><input type="checkbox" id="showRemark" /> Show remark</div>
					
				  <label>Select Question Type </label> 
				  <s:select  theme="simple" cssClass="span12" id="questionType" name ="questionType" list="questionType" listKey="id" listValue="name"  onchange="buildQuestion();">
					   <!--<option value="0">Select</option>
					   <option value="1">Multiple choice with single select</option> -->
				   </s:select>
					
					<div style="margin-top:5px;" id="questionOptionsDIV"></div>
					<div style="margin-top:5px;" id="addNewOptionsDIV"></div>
					<input type="button" style="margin-left:250px;" class="btn btn-success" value="Add Question" onclick="saveQuestion();"/>
			</form>		
		  </div>			
		</div>			
      </div>
	 </div>
</body>
</html>