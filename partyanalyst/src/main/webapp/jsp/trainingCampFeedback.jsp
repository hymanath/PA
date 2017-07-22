<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FEEDBACK FORM</title>
<link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="training/dist/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/newmultiselect/chosen.css" rel="stylesheet" type="text/css">

<style type="text/css">
header.eventsheader {  
    background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto  #fed501;
    background-origin: border-box;
    background-repeat: no-repeat;
    height: 71px;   
}
.panel .panel-heading
{
	background:#f5f5f5
}
.bg_cc
{
	background:#ccc !important;
}
.bg_ee
{
	background:#eee
}
</style>
</head>

<body>
	<!-- language convertion-->
  <script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

// Load the Google Transliterate API
   google.load("elements", "1", {
         packages: "transliteration"
       });

</script> 
  <script>

 var control;
	var lang;
   function onLoad() {
	
       lang = $("input[name=language]:checked").val();
     var options = {
         sourceLanguage:
             google.elements.transliteration.LanguageCode.ENGLISH,
         destinationLanguage:
             [''+lang+''],
         shortcutKey: 'alt+t',
         transliterationEnabled: true
     };

     // Create an instance on TransliterationControl with the required
     // options.
     control =
         new google.elements.transliteration.TransliterationControl(options);

     // Enable transliteration in the textbox with id
     // 'descrptionId'.
$(".answers").each(function()
{
	//alert('aa')
	//if ($('#answerId').length){
	//	alert($(this).attr("id"))
		var id = $(this).attr("id");
control.makeTransliteratable([''+id+'']);
});
	 	
 

//}
   }
   function languageChangeHandler() {
  
        var lang1 = $("input[name=language]:checked").val();
		if(lang1 =="en")
	   {
		control.disableTransliteration();
		}
		else
	   {
		   control.enableTransliteration();
           control.setLanguagePair(
            google.elements.transliteration.LanguageCode.ENGLISH,
            lang1);
			}
      }
 google.setOnLoadCallback(onLoad);
</script>	
<header class="eventsheader">
	<div class="container">
        <div class="row">
            <div class="col-md-2 col-xs-4 col-sm-1">
                <img src="dist/img/logo.png" class="img-responsive">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1">
                <img src="dist/img/CBN1.png" class="img-responsive">
            </div>
            <div class="col-md-6 col-xs-7 col-sm-7 text-center">               
                 <p class="header-text display-style" id="mainheading" style="font-size:32px;">FEEDBACK FORM</p>               
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1"><img src="dist/img/NTR1.png" class="img-responsive" />   
            </div>
			<div class="col-md-2 col-xs-1 col-sm-1">
				<div class="" style="color:white;margin-top: 5px;"><b> Welcome ${sessionScope.UserName} </b></div>
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top10" data-toggle="dropdown" aria-expanded="false" style="margin-top: 5px;">
                    Menu <img src="images/menu_icon.png" />
                    </a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);">
					<li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
					  <c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_SUPER_ADMIN')}">
								
								<li><a tabindex="-1" href="callCenterTrainingAdmin.action"> CALLERS ADMIN DASHBOARD </a></li>
								<li><a tabindex="-1" href="callCenterTrainingAgentDashBoard.action"> CALLERS DASHBOARD </a></li>
								<li><a tabindex="-1" href="trainingCenterDashBoardAction.action"> TRAINING CAMP DASHBOARD </a></li>
								<li><a tabindex="-1" href="trainingCampMainDashboard.action"> TRAINING CAMP FEEDBACK </a></li>
						    </c:if>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_ADMIN')}">
								<li><a tabindex="-1" href="trainingCenterDashBoardAction.action"> TRAINING CAMP DASHBOARD </a></li>
						    </c:if>
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_CALLER_ADMIN')}">
							<li><a tabindex="-1" href="callCenterTrainingAgentDashBoard.action"> CALLERS DASHBOARD </a></li>
								<li><a tabindex="-1" href="callCenterTrainingAdmin.action"> CALLERS ADMIN DASHBOARD </a></li>
						    </c:if>				
							<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_COMMITTEE_AREAWISE_ACCESS')}">
							<li><a href="committeeDashBoardAction.action"><span>COMMITTEES DASHBOARD</span></a> </li>
							<li><a href="meetingList.action"><span>Party Meeting ATR & MOM</span></a> </li>
					    </c:if>
					   <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
					
                    </ul>   
            </div>			
        </div>       
    </div>
</header>
<section>
	<div class="container">


    	<div class="row">
		
        	<div class="col-md-12">
            	<div class="panel panel-default">
                	<div class="panel-heading bg_cc">
                    	<h4 class="panel-title">FEEDBACK</h4>
                    </div>
                    <div class="panel-body">
							<!--<div class="row">
								<div class="col-md-12">
									<div class="col-md-3">
										<label>Program</label>
										<div id="programSelDivId"></div>
										<div class="mandatory" id="programSelErrDivId"></div>
									</div>
									<div class="col-md-3">
										<label>Center</label>
										<div id="campSelDivId"></div>
										<select class="form-control" id="centerId" onchange="getBatchesForCentre(this.value)">
											<option value="0">Select Center</option>
										</select>
										<div class="mandatory" id="centerSelErrDivId"></div>
									</div>
									<div class="col-md-3" id="batchDisplayDiv">
										<label>Batch</label>
										<div id="batchSelDivId"></div>
										<select class="form-control" id="batchId">
											<option value="0">Select Batch</option>
										</select>
										<div class="mandatory" id="batchSelErrDivId"></div>
									</div>
									<div class="col-md-2">
								<button  type="button" id="submitId" class="btn btn-success btn-sm btn-block text-bold" onclick="getFeedbackQuestions();">Submit</button>
								</div>
								</div>
								</div>
								-->
                    	<div class="row">
						<div class="col-xs-offset-1"><b>Select Language: </b> <input type="radio" onclick="languageChangeHandler();" checked="" id="telugu" class="lang" name="language" value="te"> Telugu <input type="radio" onclick="languageChangeHandler();" id="eng" class="lang" name="language" value="en"> English </div>
                        	<div class="col-md-12">
							
                            	<div class="select-category bg_ee m_top10">
                                    <label>Select Category</label>
                                    <select class="chosen-select" id="categoryId" multiple onchange="getQuestionOptionsForCategories();">
                                       <!-- <option>మీ నియోజకవర్గం పార్టీ, పార్టీ నాయకులు</option>-->
                                    </select>
                                </div>
								<div id="savingErrId" style="color:red;"></div>
                            </div>
                        </div>
						
                        <div class="row row col-md-12 m_top20" id="categoryquestionOptionsDiv">
						</div>
						<!--<div class="panel panel-default m_top20">
							<div class="panel-heading">
								<h4 class="panel-title">CATEGORY TITLE</h4>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
										<label>INPUT LABLE</label>
										<ul class="options-form">
											<li>
												<div class="input-group">
													<input class="form-control" type="text">
													<div class="input-group-addon">
														<i class="glyphicon glyphicon-remove"></i>
													</div>
												</div>
											</li>
											
										</ul>
									</div>
								</div>
							</div>
						</div>-->
					
                        <div class="row">
                        	<div class="col-md-2 col-md-offset-5 m_top10">
                            	<button class="btn btn-success btn-block btn-custom" onclick="saveFeedback();">
                                	SUBMIT
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<footer>
	<p class="text-center">All &copy; 2015 Telugu Desam Party</p>
</footer>
	<script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
<script src="dist/newmultiselect/chosen.jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(".chosen-select").chosen({no_results_text: "Oops, nothing found!"}); 
 //on load calls.
getPrograms();
getFeedbackQuestions();
function getPrograms(){
			 $.ajax({
			  type:'POST',
			  url :'getProgramsAction.action',
			  data:{},
		    }).done(function(result){
				
			  <c:if test="${fn:contains(sessionScope.USER.isAdmin, 'true' )}">
				
				var str='';
				str+='<select class="form-control" id="programId">';
				str+='<option value="0">Select Program</option>';
				if(result.simpleVOList1!=null &&result.simpleVOList1.length>0){
                 for(var i in result.simpleVOList1){
                    str+='<option value="'+result.simpleVOList1[i].id+'">'+result.simpleVOList1[i].name+'</option>';
                  }
                }
               str+='</select>';				
			$("#programSelDivId").html(str);
			
			$('#centerId').find('option').remove();
			$('#centerId').append('<option value="0">Select Center</option>');
			if(result.simpleVOList2!=null &&result.simpleVOList2.length>0){
			 for(var i in result.simpleVOList2){
			   $('#centerId').append('<option value="'+result.simpleVOList2[i].id+'">'+result.simpleVOList2[i].name+'</option>');
			  }
			}
			  </c:if>
			  <c:if test="${fn:contains(sessionScope.USER.entitlements, 'TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT' )}">
				var str='';
				str+='<select class="form-control" id="programId" onChange="getCampsByProgramAndUser()">';
				str+='<option value="0">Select Program</option>';
				if(result.simpleVOList1!=null &&result.simpleVOList1.length>0){
                 for(var i in result.simpleVOList1){
                    str+='<option value="'+result.simpleVOList1[i].id+'">'+result.simpleVOList1[i].name+'</option>';
                  }
                }
               str+='</select>';				
			$("#programSelDivId").html(str);
				
		      </c:if>	
			});
		 }
		 
		   function getCampsByProgramAndUser(){
		 
		  $('#centerId').find('option').remove();
          $('#centerId').append('<option value="0">Select Center</option>');
		  var campProgramId=$("#programId option:selected").val();
		  if(campProgramId==0){
			  return;
		  }
		  var jsObj={campProgramId:campProgramId}
	      $.ajax({
		    type:'POST',
		    url :'getCampsByProgramAndUserAction.action',
		    data:{task:JSON.stringify(jsObj)},
	      }).done(function(results){
			   
			  if(results!=null && results.length>0){
				  for(var i in results){
					 $('#centerId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
				  }
			  }
			  
		  });
	  }
	  
	    function getBatchesForCentre(campId){
	  
			$("#exportExcelDivId").hide();
			$('#batchId').find('option').remove();
			$('#batchId').append('<option value="0">Select Batch</option>');
			
			var programId = $("#programId").val();
			var campId = $("#centerId").val();
				  
		  var jsObj={
			  programId :programId,
			  campId :campId
		  }
		  $.ajax({
		    type:'POST',
		    url :'getBatchesForCentreAction.action',
		    data:{task:JSON.stringify(jsObj)},
	      }).done(function(results){
			  if(results !=null){
					for(var i in results){
				   if(results[i].id == 0){
					  $("#batchId").append('<option value='+results[i].id+'>ALL</option>');
				   }else{
					  $("#batchId").append('<option value='+results[i].id+'>'+results[i].name+'</option>');
				   }
				 }
			  }
		  });
	  }
	  
	  function getFeedbackQuestions()
	  {
		   $("#categoryId").html('');
		  var batchId= '${param.batchId}';
		var campId= '${param.campId}';
		var programId= '${param.programId}';
		
		
		
		  var jsObj={
			  programId :0,
			  campId :0,
			  batchId:0,
			  task:""
		  }
		  $.ajax({
		    type:'POST',
		    url :'getFeedbackCategoriesAction.action',
		    data:{task:JSON.stringify(jsObj)},
	      }).done(function(results){
			  	  $("#categoryId").append('<option value="0">Select</option>');
				  var str='';
					if(results !=null){
					for(var i in results){
						$("#categoryId").append('<option value='+results[i].id+'>'+results[i].name+'</option>');
						str+='<div div="categoriDivId'+results[i].id+'"></div>';
					}
					$("#categoryId").chosen(); 
					$("#categoryId").trigger("chosen:updated");
					}
					
					$('#categoryquestionOptionsDiv').html(str);
		       }); 
	  }
	  
	  var GcategotyArr = new Array() ;
	  function getQuestionOptionsForCategories()
	  {
		
		   var categoryIds ;
			var batchId = 0;

			var finalCategoryIdsList = new Array();
			categoryIds= $("#categoryId").val();
			console.log(GcategotyArr)
			if(GcategotyArr != null && GcategotyArr.length>0)
			{
				if(categoryIds != null && categoryIds.length>0)
				{
					if(GcategotyArr.length > categoryIds.length )
					{						
						var isAvailable=true;
						for(var k in GcategotyArr)
						{
							isAvailable = false;
							var existingId = GcategotyArr[k];
							for(var k in categoryIds)
								{									
									var newId = categoryIds[i];
									if(newId == existingId)
									{
										isAvailable = true;
									}
								}	
								if(!isAvailable)
								{
									GcategotyArr.splice(k,1);
								}									
						}
						/*
						for(var k in GcategotyArr)
						{
							finalCategoryIdsList.push(GcategotyArr[k]);
						}
							*/	
					}
					else 
					{
						for(var i in categoryIds)
						{					
							if(GcategotyArr != null && GcategotyArr.length>0)
							{			
								var isAvailable=false;
								for(var k in GcategotyArr)
								{
									var existingId = GcategotyArr[k];
									var newId = categoryIds[i];
									if(existingId == newId)
									{
										isAvailable = true;
									}
								}								
								if(!isAvailable)
								{
									GcategotyArr.push(categoryIds[i]);
									finalCategoryIdsList.push(categoryIds[i]);
								}						
							}
							else
							{
								GcategotyArr.push(categoryIds[i]);
								finalCategoryIdsList.push(categoryIds[i]);
							}					
						}
					}
				}
			}
			else 
			{
		
				for(var i in categoryIds)
				{					
					if(GcategotyArr != null && GcategotyArr.length>0)
					{			
						var isAvailable=false;
						for(var k in GcategotyArr)
						{
							var existingId = GcategotyArr[k];
							var newId = categoryIds[i];
							if(existingId == newId)
							{
								isAvailable = true;
							}
						}
						
						if(!isAvailable)
						{
							GcategotyArr.push(categoryIds[i]);
							finalCategoryIdsList.push(categoryIds[i]);
						}						
					}
					else
					{
						
						GcategotyArr.push(categoryIds[i]);
						finalCategoryIdsList.push(categoryIds[i]);
					}					
				}
			}
		
			$("#categoriDivId"+parseInt(finalCategoryIdsList[0])+"").html('');
			 //console.log(categoryIds)
			//  console.log(finalCategoryIdsList)
			
			var programId = '${programId}';
			var campId = '${campId}';
			var batchId = '${batchId}';
			var GcadreId = '${cadreId}';

		  var jsObj={
			  programId :programId,
			  campId :campId,
			  batchId:batchId,
			  categoryIds:finalCategoryIdsList,
			  task:"",
			  cadreId:GcadreId
		  }
		  removeCategroy();
		 if(finalCategoryIdsList != null  && finalCategoryIdsList.length>0)
			  $.ajax({
				type:'POST',
				url :'getQuestionOptionsForCategoryAction.action',
				data:{task:JSON.stringify(jsObj)},
			  }).done(function(result){
					buildQuestionOptions(result,parseInt(finalCategoryIdsList[0]))
				   }); 
				  
	  }
	  	var index =0;
	  function buildQuestionOptions(result,divId)
	  {
		  var textFieldArr = [];
		  $('#categoriDivId'+divId+'').html('');
		  var str = '';
		  if(result != null && result.length > 0)
		  {
			  for(var i in result)
			  {
				  if(result[i].optionsList != null && result[i].optionsList.length > 0)
					{
							str+='<div class="panel panel-default categoryremove" id="categoryDiv'+result[i].id+'">';
							str+='<div class="panel-heading">';
							str+='<h4 class="panel-title"><b>'+result[i].category+'</b></h4>';
							str+='</div>';
							str+='<div class="panel-body">';
							str+='<div class="row QuestionOptionsDiv">';
							str+='<div class="col-md-12">';
							str+='<div >';
							
							str+='<div class="row m_top10">';
							for(var j in result[i].optionsList)
							{
								str+='<div class="col-md-12">';
								str+='<label>'+result[i].optionsList[j].option+'</label>';
								str+='<ul class="options-form">';
								str+='<li>';
								str+='<div class="input-group">';
								
								var existingAnswersList = result[i].childCategoryList;
								if(existingAnswersList != null && existingAnswersList.length>0)
								{
									for(var k in existingAnswersList)
									{
										if(result[i].optionsList[j].id == existingAnswersList[k].subCategoryId)
										{
											var answersList = existingAnswersList[k].description;
											if(answersList != null && answersList.length>0)
											{
													
												for(var s in answersList)
												{													
													str+='<input class="form-control answers" feedbackId = "'+result[i].optionsList[j].trainingCampFeedbackCategoryId+'"  type="textarea" id="cateforyTextId'+index+'" value="'+answersList[s]+'"/>';
													
													if(s==0)
													{
														str+=' <div class="input-group-addon">';
														str+=' <a href="javascript:{addNewField(\'addCateforyTextId'+result[i].id+i+''+j+'\',\''+result[i].optionsList[j].trainingCampFeedbackCategoryId+'\')}"><i class="glyphicon glyphicon-plus"></i></a>';
														str+=' </div>';
														str+=' </div>';
														str+='</li>';
														str+='<div id="addCateforyTextId'+result[i].id+i+''+j+'">';
														str+=' <div class="input-group ">';
													}
													else{
													
														str+=' <div class="input-group-addon removeicon">';
															str+=' <i class="glyphicon glyphicon-remove"></i></a>';
														str+=' </div>';		
														str+='</div>';	
														str+=' <div class="input-group ">';														
													}													
												}
												str+='</div>';
											}
										}										
									}
								}
								else
								{
									str+='<input class="form-control answers" feedbackId = "'+result[i].optionsList[j].trainingCampFeedbackCategoryId+'"  type="textarea" id="cateforyTextId'+index+'">';
									str+=' <div class="input-group-addon">';
									str+=' <a href="javascript:{addNewField(\'addCateforyTextId'+result[i].id+i+''+j+'\',\''+result[i].optionsList[j].trainingCampFeedbackCategoryId+'\')}"><i class="glyphicon glyphicon-plus"></i></a>';
									str+=' </div>';
									str+='</div>';
									str+='<div id="addCateforyTextId'+result[i].id+i+''+j+'">';
									str+='</div>';
								}
																
								str+='</ul>';
								str+='</div>';
								
								//textFieldArr.push("cateforyTextId"+result[i].id+"a"+i+""+j+"");
								index++;
							}
							
							str+='</div>';
							str+='</div>';
							str+='</div>';
							str+='</div>';
							str+='</div>';
							str+='</div>';
					}
				else{
				str+=' <div class="panel panel-default m_top20 categoryremove" id="categoryDiv'+result[i].id+'">';
				str+=' <div class="panel-heading">';
				str+=' <h4 class="panel-title">'+result[i].category+'</h4>';
				str+=' </div>';
				str+=' <div class="panel-body">';
				str+=' <div class="row">';
				str+=' <div class="col-md-12">';
				str+=' <label>ANSWER</label>';
				str+=' <ul class="options-form">';
				str+=' <li>';
				str+=' <div class="input-group">';
				
				var answersList = result[i].mainCategoryAnswers;
				if(answersList != null && answersList.length>0)
				{
					for(var s in answersList)
					{
			
						str+='<input class="form-control answers" feedbackId = "'+result[i].id+'"  type="textarea" id="cateforyTextId'+index+'" value="'+answersList[s]+'"/>';
						
						if(s==0)
						{
							str+=' <div class="input-group-addon">';
							str+=' <a href="javascript:{addNewField(\'addCateforyTextId'+result[i].id+i+''+0+'\',\''+result[i].id+'\')}"><i class="glyphicon glyphicon-plus"></i></a>';
							str+=' </div>';
							str+=' </div>';
							str+='</li>';
							str+='<div id="addCateforyTextId'+result[i].id+i+''+s+'">';
							str+=' <div class="input-group ">';
						}
						else{
							str+=' <div class="input-group-addon removeicon">';
								str+=' <i class="glyphicon glyphicon-remove"></i></a>';
							str+=' </div>';														
						}													
					
						str+='</div>';
																						
					}
				}
				else
				{
					str+='<input class="form-control answers" type="textarea" feedbackId = "'+result[i].id+'" id="cateforyTextId'+index+'">';
					str+=' <div class="input-group-addon">';
					str+=' <a href="javascript:{addNewField(\'addCateforyTextId'+result[i].id+i+''+0+'\',\''+result[i].id+'\')}"><i class="glyphicon glyphicon-plus"></i></a>';
					str+=' </div>';
					str+='</div>';
				}

				str+=' </ul>';
				str+=' </div>';
				str+=' </div>';
				str+=' </div>';
				str+=' </div>';
					index++;
				//textFieldArr.push("cateforyTextId"+result[i].id+"a"+i+"0");
			  }
		}
		
	  }  

	   $('#categoriDivId6').html(str);
	  $("#categoryquestionOptionsDiv").append(str);
	   $(".optionSelect").chosen(); 
	   $(".optionSelect").trigger("chosen:updated");

	  $(".answers").each(function()
		{
				var id = $(this).attr("id");
				
				control.makeTransliteratable([''+id+'']);
		});
	
	} 
	
	function removeCategroy()
	{
		var categoryIds= $("#categoryId").val();
		$(".categoryremove").each(function()
		{
		var divId = $(this).attr("id").replace(/[^0-9]/g,'');
		if($.inArray(divId, categoryIds) == -1)
		{
			$(this).remove();
			for(var j in GcategotyArr)
			{
				if(GcategotyArr[j] == divId)
					GcategotyArr.splice(j,1);
			}
			
		}
			
		})
		
	}

	function addNewField(divId,feedbackID)
	{
		
		var str='';
		str+='<div class="input-group">';
		str+='<input class="form-control answers" type="textarea" feedbackId = "'+feedbackID+'" id="cateforyTextId'+index+'">';
		str+='<div class="input-group-addon removeicon">';
		str+='<i class="glyphicon glyphicon-remove"></i>';
		str+='</div>';
		str+='</div>';	
		 $("#"+divId).append(str);	
		 index++;
		   $(".answers").each(function()
		{
				var id = $(this).attr("id");
				
				control.makeTransliteratable([''+id+'']);
		});
	$(".removeicon").click(function(){
		$(this).parent().remove();
	})
		
	}
	function saveFeedback()
	{
		$("#savingErrId").html("");
		var categoryId =$("#categoryId").val();
		if(categoryId == 0 || categoryId == null){
			$("#savingErrId").html("select category");
			return;
		}
		var cadreId ='${cadreId}';
		var arr = [];
		var isError="false";
		$(".answers").each(function(){
			var id = $(this).attr("feedbackId");
			var answer = $(this).val().trim();
		 if(answer == null || answer.length == 0 || answer == ""){
			  $("#savingErrId").html("Please enter all fields.");
			 isError="true"
		   } 			  
			var obj = {
				id : id,
				answer:answer,
			}
			if(answer != null && answer.length > 0 ||answer == "")
			arr.push(obj)  
		});
		
		if(isError == "true")
			return;
		
		var jsObj = {
			tdpCadreId:cadreId,
			arr:arr,
			task:""
		}
		 $.ajax({
				type:'POST',
				url :'saveCategorysDetailsAction.action',
				data:{task:JSON.stringify(jsObj)},
			  }).done(function(result){
				  
				  if(result != null && result == 'success'){
				  		alert("Feed back saved Successfully...");
						$("#categoryId").val('').trigger("chosen:updated");
						$(".answers").each(function(){
							$(this).val('');
						});
				  }
					//buildQuestionOptions(result,parseInt(finalCategoryIdsList[0]))
				   }); 
		
	}
	

</script>
<script>

</script>
</body>
</html>
