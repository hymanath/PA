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
<script type="text/javascript">
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
	  
	  if($("#questionType option:selected").val() == 1){
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
	  }
	  
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
	   str+='<div><label>Question</label> <input type="text" class="span12" id="subquestionTitle'+optionId+'" /> </div>';
       str+='<div>';
	   str+='  <label>Select Question Type</label> <select class="span12" id="subquestionType'+optionId+'" name ="questionType" onchange="buildSubQuestion('+optionId+');">';
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
	  
	  if($("#subquestionType"+optionId+" option:selected").val() == 1){
	    str+='<fieldset id="subquestDIV'+suboptionId+'" class="alert"><div><div style="margin-bottom:5px;">Sub Options : </div><div><input type="text" key="'+suboptionId+'" class="subquestcls'+optionId+'" id="subquest'+suboptionId+'" /> <input id="subquestremark'+suboptionId+'" type="checkbox" /> Show remark </div></div></fieldset>';
		$("#subQuestionOptDIV"+optionId).html(str);
		str ='<span onclick="buildSubOptions('+optionId+');" class="btn btn-small"><i class="icon-th-list"> </i> Add More Sub Options</span>';
		//str ='<div><input type="button" class="btn" onclick="buildSubOptions('+optionId+');" value="Add Sub Option" > </div>';
	    $("#addNewSubQuestOptDIV"+optionId).html(str);
	  }
	  
   }
   function buildSubOptions(id){
      suboptionId = suboptionId+1;
	  var str = '';
       str+='<fieldset id="subquestDIV'+suboptionId+'" class="alert"><div style="margin-bottom:5px;"><div><input type="text" key="'+suboptionId+'" class="subquestcls'+id+'" id="subquest'+suboptionId+'" /> <input id="subquestremark'+suboptionId+'" type="checkbox" /> Show remark  <span class="icon-minus-sign" style="cursor:pointer;" title="Remove Option" onclick="removeSubOption('+suboptionId+');" ></span></div></div></fieldset>';
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
	console.log(optionTypeDetails.optionTypeArr);	
	for(var i in optionTypeDetails.optionTypeArr)
	{	

		var option=document.createElement('option');		
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