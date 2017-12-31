function getActivityAttribute()
{
	     var  activityScopeId = $("#ActivityList").val();
	     var jObj = {
				activityScopeId:activityScopeId
			};		
			$.ajax({
				  type:'POST',
				  url: 'getActivityAttributeAction.action',
				  data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				 var flag = "false";
				  if (result != null && result.length > 0) {
					  for(var i in result) {
						 var attributeId = result[i].reqAttributeId;
						 if (attributeId == 3 || attributeId==4) {
							 //we are checking activity has question or not .if question is there ,then we are showing link
							 flag = "true";
						 }
					  }
				  }
				  if (flag=="false") {
					$(".activityLevelIdCls").hide();
				  } else {
					$(".activityLevelIdCls").show();
				  }
			 });
}

function getQuestionByAcivityScope(activityLocationInfoId,activityName)
{
	  $('#jbAnswerQuestionDivId').html("<img src='images/Loading-data.gif'/>");
	  var activityScopeId = $("#ActivityList").val();
	//  var activityLocationInfoId = 5514;
	   $("#jbModalHeaderDivId").html('');
		var jObj = {
			activityScopeId:activityScopeId,
			activityLocationInfoId:activityLocationInfoId,
			activityDate :"05-01-2016"
		};		
		$.ajax({
			  type:'POST',
			  url: 'getQuestionByAcivityScopeAction.action',
			 data : {task:JSON.stringify(jObj)} ,
		 }).done(function(result){
			  $('#jbAnswerQuestionDivId').html(" ");
			  if (result != null && result.length > 0) {
				  buildAnswerDtls(result,activityLocationInfoId,activityName);
			  }
		 });
}
  function buildAnswerDtls(result,activityLocationInfoId,activityName) {
	  
	  var jsonObj = result[0];
	  
	  /* $("#jbModalHeaderDivId").html("<b style='text-transform:uppercase;'>"+activityName+"  <small style='text-transform:lowercase;'>("+jsonObj.activityStarteDate+"to"+jsonObj.activityEndDate+")</small></b>"); */
	  
	   var headingStr = '';
	  headingStr+='<div class="modal-header" style="background-color:#ddd;">';
				headingStr+='<div class="row">';
					headingStr+='<div class="col-sm-12">';
						headingStr+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"></span></button>';
					headingStr+='</div>';
				headingStr+='</div>';
				headingStr+='<div class="row">';
					headingStr+='<div class="col-sm-12">';
						headingStr+='<h4 class="panel-title">ACTIVITY NAME&nbsp:&nbsp&nbsp'+activityName+'</h4>';
					headingStr+='</div>';
				headingStr+='</div>';
				headingStr+='<div class="row m_top20">';
					headingStr+='<div class="col-sm-6">';
						headingStr+='<p><b>ACTIVITY (StartDate - EndDate)</b>&nbsp:&nbsp&nbsp<span>'+jsonObj.activityStarteDate+"-"+jsonObj.activityEndDate+'</span></p>';
					headingStr+='</div>';
					/* headingStr+='<div class="col-sm-6 ">';
						headingStr+='<div class="pull-right">';
							headingStr+='<p>Conducted Date<span> 26-12-2017</span></p>';
						headingStr+='</div>';
					headingStr+='</div>';*/ 
				headingStr+='</div>';
			headingStr+='</div>';
			$("#jbModalHeaderDivId").html(headingStr); 
			
			  //building answer question
			  var questionStr = '';
			   questionStr+='<form name="saveJBanswerFormId" method="post" id="saveJBanswerFormId">';
			   var questionNo = 1;
			  for(var i in result) {
					 questionStr+='<div class="col-sm-6 m_top10">';
					 if (result[i].questionType != null && result[i].questionType=="DAYWISEQUESTION") {
					   questionStr+='<h4 class="panel-title" style="color:#00B17E;font-size:14px;"><span><b>'+questionNo+'</b>.&nbsp</span>'+result[i].question+'('+result[i].activityDate+')</h4>';
						 questionStr+='<input name="activiyDetailsVOList['+i+'].activityDaywiseQuestionnaireId" type="hidden" value="'+result[i].activityDaywiseQuestionnaireId+'">';
					 } else {
						  questionStr+='<h4 class="panel-title" style="color:#00B16G;font-size:14px;"><span><b>'+questionNo+'</b>.&nbsp</span>'+result[i].question+'</h4>';
					 }
					 
					 questionStr+='<input name="activiyDetailsVOList['+i+'].activityQuestionnaireId" type="hidden" value="'+result[i].activityQuestionnaireId+'">';
					 questionStr+='<input name="activiyDetailsVOList['+i+'].activityLocationInfoId" type="hidden" value="'+activityLocationInfoId+'">';
					 questionStr+='<input name="activiyDetailsVOList['+i+'].optionTypeId" type="hidden" value="'+result[i].optionTypeId+'">';
					
					// questionStr+='<div class="col-sm-6">'; 
				   if (result[i].optionList != null && result[i].optionList.length > 0) {
					    var tempStr = getQuestionOptionType(result[i].optionList,result[i].subList,result[i].optionTypeId,result[i].hasRemarks,i,result[i].activityQuestionnaireId,activityLocationInfoId);
						 questionStr+='<ul class="list-group m_top20">';
						 questionStr = questionStr+ " " + tempStr;
						 questionStr+='</ul>';
					 	
				   }
				   //questionStr+='</div">';
				   questionStr+='</div>'; 
				   questionNo = questionNo +1;
			  } 
			  questionStr+='</form>';
			 $("#jbAnswerQuestionDivId").html(questionStr);
  }	

  function getQuestionOptionType(optionObjArr,answerObjArr,optionTypeId,hasRemarks,questionStartSeqNo,activityQuestionnaireId,activityLocationInfoId) {
	   var str='';
	    
	   if (optionTypeId == 1) {//selectBox
	    str+='<li class="list-group-item">';
		     str+='<select class="form-control" name="activiyDetailsVOList['+questionStartSeqNo+'].subList[0].optionId">';
			  str+='<option  value="0">Select Option</option>';
		     for(var j in optionObjArr) {
				  var answerOption = getSelectedAnswerOption(answerObjArr,optionObjArr[j].optionId);
				   if (answerOption=="true") {
					   str+='<option value="'+optionObjArr[j].optionId+'" selected>'+optionObjArr[j].option+'</option>';
				   } else {
					   str+='<option value="'+optionObjArr[j].optionId+'">'+optionObjArr[j].option+'</option>';
				   }
				  
			 }
			 str+='</select>';
			  str+='</li>';
	   } else if (optionTypeId == 2) {//checkBox
		     for(var j in optionObjArr) {
				  str+='<li class="list-group-item">';
				  var answerOption = getSelectedAnswerOption(answerObjArr,optionObjArr[j].optionId);
				  var answerText = getSelectedAnswertext(answerObjArr,optionObjArr[j].optionId);
				   if (answerOption=="true") {
					   str+='<div class="row">';
							str+='<div class="col-sm-2">';
								str+='<input name="activiyDetailsVOList['+questionStartSeqNo+'].subList['+j+'].optionId" type="checkbox" checked value="'+optionObjArr[j].optionId+'">'+optionObjArr[j].option+'</>';
							str+='</div>';
							str+='<div class="col-sm-2">';
								 if (hasRemarks != null && hasRemarks=="true") {
									 str+='<input style="margin-left:50px" name="activiyDetailsVOList['+questionStartSeqNo+'].subList['+j+'].hasRemarks" type="text" value="'+answerText+'">';
								   }
							str+='</div>';
					   str+='</div>';
					   
					  
				   } else {
					   
						str+='<div class="row">';
							str+='<div class="col-sm-2">';
								 str+='<input name="activiyDetailsVOList['+questionStartSeqNo+'].subList['+j+'].optionId" type="checkbox" value="'+optionObjArr[j].optionId+'">'+optionObjArr[j].option+'</>';
							str+='</div>';
							str+='<div class="col-sm-2">';
								 if (hasRemarks != null && hasRemarks=="true") {
									str+='<input style="margin-left:50px" name="activiyDetailsVOList['+questionStartSeqNo+'].subList['+j+'].hasRemarks" type="text">';
								 }
							str+='</div>';
					   str+='</div>';
					   
					    
						
				   }
				   str+='</li>';
			 }
	   } else if (optionTypeId==3) {//Text Description Box
	         str+='<li class="list-group-item">';
	         for(var j in optionObjArr) {
				     var answerText = getSelectedAnswertext(answerObjArr,optionObjArr[j].optionId);
				  		str+='<li attr_text_type="count" class="list-group-item"><input  name="activiyDetailsVOList['+questionStartSeqNo+'].subList['+j+'].hasRemarks" type="text" value="'+answerText+'" class="form-control" />';	  
			 }
			 str+='</li>';
	     
					
	   } else if (optionTypeId==4) {//Count Description Box
	     str+='<li class="list-group-item">';
	         for(var j in optionObjArr) {
				    var answerText = getSelectedAnswertext(answerObjArr,optionObjArr[j].optionId);
					str+='<li attr_text_type="text" class="list-group-item"><input name="activiyDetailsVOList['+questionStartSeqNo+'].subList['+j+'].hasRemarks" type="text" value="'+answerText+'" class="form-control" />';
			 }
		    str+='</li>';
		}
		
	    return str;
   }
   function getSelectedAnswerOption(answerOptionObj,optionId) {
	     var flag = "false";
	      for(var i in answerOptionObj) {
			  var answerOptionId = answerOptionObj[i].answerOptionId;	
			   if (answerOptionId == optionId) {
				   flag = "true";
			   }
		  }
		  return flag;
   }
      function getSelectedAnswertext(answerOptionObj,optionId) {
	     var text = "";
	      for(var i in answerOptionObj) {
			  var answerOptionId = answerOptionObj[i].answerOptionId;	
			   if (answerOptionId == optionId) {
				   text = answerOptionObj[i].answerText;
			   }
		  }
		  return text;
   }
$(document).on("click","#saveJbAnswerDtlsDivId",function(){
	saveActivityAnswerDetails();
	
});
function saveActivityAnswerDetails(){
	 	var uploadHandler = { 
			upload: function(object) {
				//$("#savingAjaxImg").css("display","none");
				uploadResult = object.responseText;
				showStatus(uploadResult);
			}
		};
		YAHOO.util.Connect.setForm('saveJBanswerFormId',true);  
		YAHOO.util.Connect.asyncRequest('POST','saveActivityAnswerDetailsAction.action',uploadHandler);
	}
	function showStatus(uploadResult){
		$("#statusSpandId").show();
		if(uploadResult !=null && uploadResult.search("success") != -1){
				setTimeout(function () {
				$("#statusSpandId").html("<span style='margin-right:50px;color:green;' class='text-capital'><b>Saved Successfully</b></span>").fadeOut(4000);
				//location.reload(true);	
          }, 600);
		} else {
			setTimeout(function () {
				$("#statusSpandId").html("<span style='margin-right:50px;color:red;'>Error occurred.Please Try Again </span>").fadeOut(3000);
			}, 600);
		}
	    setTimeout(function () {
			$('#updateactivitymodel').modal('hide');
		}, 1000);
		
	}
