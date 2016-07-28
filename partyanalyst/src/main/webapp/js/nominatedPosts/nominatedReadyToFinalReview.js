var globalDistrictArr=[];
var globalAssmblyArr=[];
var globalMandalTownDivArr=[];
var globalLocationLevelValueArr =[];
var globalLocationLevel=5;
var globalLocationLevelId = 0;
var globalDepartmentId = 0 ;
function getBoardLevelId(boardLevelId,stateId){
	globalLocationLevelValueArr = [];
	globalLocationLevelId = boardLevelId;
	if(boardLevelId != null && boardLevelId == 1){
	  globalLocationLevelValueArr.push(1);	 
	  getFinalReviewCandidateCountLocationWise(globalLocationLevelId,globalLocationLevelValueArr,globalDepartmentId,0,"");	
	}else if(boardLevelId != null && boardLevelId == 2){
		 if(stateId != 0){
		   globalLocationLevelValueArr.push(stateId);	 
		 }else{
			globalLocationLevelValueArr.push(1);
            globalLocationLevelValueArr.push(36);			
		 }
		 getFinalReviewCandidateCountLocationWise(globalLocationLevelId,globalLocationLevelValueArr,globalDepartmentId,0,"");
	}else{
	   getDistrictsForStates(stateId,"true");
	} 
}
$(document).on("change","#stateId",function(){
	var stateId = $(this).val();
	if(globalLocationLevelId !=1 && globalLocationLevelId !=2){
		getDistrictsForStates(stateId,"false");
	}
});

function getDistrictsForStates(state,isFirst){
	  globalDistrictArr=[];
	   var jsObj=
	   {				
					stateId:state,				
					task:"getDistrictsForState"		
		}
		$.ajax({
			  type:'GET',
			  url: 'getNewDistrictsOfStateSplittedAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   	$("#districtId  option").remove();
		    $("#districtId").append('<option value="0">ALL</option>');			
			if(result !=null && result.length>0){
				for(var i in result){
					$("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
					globalDistrictArr.push(result[i].id);
					globalLocationLevelValueArr = globalDistrictArr;
				}
			}
		 	if(globalLocationLevelId == 3 && isFirst =="true"){
				 getDistrictConstituencyAndMndlTwnDvsnLvlData(globalLocationLevelId);
			 } 
		  if(globalLocationLevelId !=1 && globalLocationLevelId !=2 && globalLocationLevelId !=3){
				getConstituenciesForDistricts(0,state,isFirst);
			 }
			 
	   });
}
$(document).on("change","#districtId",function(){
	var districtId = $(this).val();
	var stateId = $("#stateId").val();
	if(globalLocationLevelId !=1 && globalLocationLevelId !=2 && globalLocationLevelId !=3){
	 getConstituenciesForDistricts(districtId,stateId,"false");
	}
});

function getConstituenciesForDistricts(district,stateId,isFirst){
	    globalAssmblyArr=[];
	 	var distArrTemp = [];
		if(district==0){
			distArrTemp = globalDistrictArr;
		}else{
			distArrTemp.push(district);
		}
		var jsObj={				
			districtId:distArrTemp,
			stateId:stateId,
			task:"getConstituenciesForDistricts"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getConstituenciesOfDistrictWithSplittedAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		     $("#manTowDivId  option").remove();
		     $("#constituencyId  option").remove();
		     $("#constituencyId").append('<option value="0">ALL</option>');
			 if(result !=null && result.length>0){
				 for(var i in result){			  
					$("#constituencyId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
					globalAssmblyArr.push(result[i].locationId);
				   }
				   if(globalLocationLevelId == 4 && isFirst=="true"){
					   globalLocationLevelValueArr =[];
					   globalLocationLevelValueArr = globalAssmblyArr; 
					   getDistrictConstituencyAndMndlTwnDvsnLvlData(globalLocationLevelId);
				   }
			 }	
			if(globalLocationLevelId !=1 && globalLocationLevelId !=2 && globalLocationLevelId !=3 && globalLocationLevelId !=4){
				getMandalVillageDetails(0,stateId,isFirst);
			}
		});
	}
$(document).on("change","#constituencyId",function(){
		var constituencyId=$(this).val();
		var stateId = $("#stateId").val();
		if(globalLocationLevelId !=1 && globalLocationLevelId !=2 && globalLocationLevelId !=3 && globalLocationLevelId !=4){
			getMandalVillageDetails(constituencyId,stateId,"false");
		}
	});
function getMandalVillageDetails(constituencyId,stateId,isFirst){
	   globalMandalTownDivArr = [];
		var districtId = $("#districtId").val();
		var distArrTemp = [];
		if(districtId==0){
			distArrTemp = globalDistrictArr;
		}else{
			distArrTemp.push(districtId);
		}
		
		if(globalLocationLevel==5){
			$("#manTowDivId").html("");
		}
		
		var assmblyArrTemp = [];
		if(constituencyId==0){
			assmblyArrTemp = globalAssmblyArr;	
		}else{
			assmblyArrTemp.push(constituencyId);
		}
		
	   var jsObj={				
			stateId : stateId,
			districtId : distArrTemp,
			constituencyId : assmblyArrTemp,//228
			//mandalId : mandalId,
			//locationLevel : locationLevel,
			locationLevel : globalLocationLevel,
			task:""
		}
		$.ajax({
			  type:'GET',
			  url: 'getSubLevelForConstituencyAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		    $("#manTowDivId  option").remove();
			$("#manTowDivId").append("<option value='0'>ALL</option>");			  
			for(var i in result){
				$("#manTowDivId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				globalMandalTownDivArr.push(result[i].locationId);
			}
			if(globalLocationLevelId >=5 && isFirst=="true"){
			   globalLocationLevelValueArr =[];
			   globalLocationLevelValueArr = globalMandalTownDivArr; 
			    getDistrictConstituencyAndMndlTwnDvsnLvlData(globalLocationLevelId);
			  }
		});
	}

function getDistrictConstituencyAndMndlTwnDvsnLvlData(boardLevelId){
	if(boardLevelId != null && boardLevelId == 3){
		getFinalReviewCandidateCountLocationWise(boardLevelId,globalDistrictArr,globalDepartmentId,0,"");	
	}else if(boardLevelId != null && boardLevelId == 4){
		getFinalReviewCandidateCountLocationWise(boardLevelId,globalAssmblyArr,globalDepartmentId,0,"");	
	}else if(boardLevelId != null && boardLevelId >= 5){
	    getFinalReviewCandidateCountLocationWise(boardLevelId,globalMandalTownDivArr,globalDepartmentId,0,"");	
	}
}
$(document).on("click","#submitBtnId",function(){
	var locationLevelValueArr = [];
	var stateId = $("#stateId").val();
	var districtId = $("#districtId").val();
	var constituencyId = $("#constituencyId").val();
	var manTowDivId = $("#manTowDivId").val();
	 
	 if(stateId != null){
		if(stateId == 0){
		locationLevelValueArr.push(1);
		locationLevelValueArr.push(36);
		}else{
		 locationLevelValueArr.push(stateId); 	
		}
	 }
	 if(districtId != null){
	   locationLevelValueArr =[];
		if(districtId == 0){
			 locationLevelValueArr = globalDistrictArr;
		 }else{
			 locationLevelValueArr.push(districtId); 
		 } 
	 }
	 if(constituencyId != null){	 
		 locationLevelValueArr =[];
		 if(constituencyId == 0){
			 locationLevelValueArr = globalAssmblyArr
		 }else{
			 locationLevelValueArr.push(constituencyId);
		 } 
	 }
	if(manTowDivId != null){
		locationLevelValueArr =[];
		if(manTowDivId == 0){
			 locationLevelValueArr = globalMandalTownDivArr
		 }else{
			 locationLevelValueArr.push(manTowDivId);
		 }
	}
	 globalLocationLevelValueArr = [];
	 globalLocationLevelValueArr = locationLevelValueArr;
	 getFinalReviewCandidateCountLocationWise(globalLocationLevelId,locationLevelValueArr,0,0," ");
});
function getFinalReviewCandidateCountLocationWise(LocationLevelId,locationLevelValueArr,departmentId,boardId,locationType){
	var jsObj =
	{
		LocationLevelId : LocationLevelId,
		locationLevelValueArr : locationLevelValueArr,
		departmentId : departmentId,
		boardId : boardId
	};
	$.ajax({
			type : "POST",
			url : "getFinalReviewCandidateCountLocationWiseAction.action",
			data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			 if(locationType == "department"){
				buildCandidateBoardRslt(result,departmentId); 
			 }else if(locationType == "board"){
				buildCandidatePositionRslt(result,departmentId,boardId); 
			 }else{
			   buildCandidateReviewRslt(result);  
			 }
		}else{
		   if(locationType == "department"){
				$("#boardRsltDivId").html('NO DATA AVAILABLE'); 
			 }else if(locationType == "board"){
				$("#positionRsltDivId").html('NO DATA AVAILABLE');
			 }else{
			   $("#candiateReadyToFinalReviewDivId").html("NO DATA AVAILABLE.");
			    $("#boardRsltDivId").html(' ');
                $("#positionRsltDivId").html(' ');
			 }
		}
	}); 
}
function buildCandidateReviewRslt(result){
 $("#boardRsltDivId").html(' ');
 $("#positionRsltDivId").html(' ');
 var str='';
    str+='<ul class="nav nav-tabs tabsCustomFinal" role="tablist">';
	  for(var i in result){
	   if(i==0){
		  str+='<li role="presentation" class="active"><a href="#departments'+result[i].id+'" aria-controls="departments'+result[i].id+'" class="deptHrfCls" attr_dept_id="'+result[i].id+'" role="tab" data-toggle="tab">'+result[i].name+'<span class="label label-primary pull-right labelCustom">&nbsp&nbsp&nbsp&nbsp'+result[i].count+'</span></a></li>';
		 }else{
		 str+='<li role="presentation"><a href="#departments'+result[i].id+'" aria-controls="departments'+result[i].id+'" role="tab" class="deptHrfCls" attr_dept_id="'+result[i].id+'" data-toggle="tab">'+result[i].name+' <span class="label label-primary pull-right labelCustom">&nbsp&nbsp&nbsp&nbsp'+result[i].count+'</span></a></li>';
		}
      }
	  str+='</ul>';
  $("#candiateReadyToFinalReviewDivId").html(str);
  setHeight()
}
function buildCandidateBoardRslt(result,departmentId){
	 $("#positionRsltDivId").html(' ');
	var str='';
	str+='<div role="tabpanel" class="tab-pane active pad_15" id="departments'+departmentId+'" style="padding-right:0px;padding-bottom:0px;">';
	   str+='<h4 class="headingColor text-capital"><u>board / corporation</u></h4>';
	   str+='<ul class="nav nav-tabs tabsCustomFinal1" role="tablist">';
	    for(var i in result){
			if(i==0){
		   str+='<li role="presentation" class="active">';	
			str+='<a href="#board'+result[i].id+'" class="text-capitalize boardHrfCls" attr_board_id="'+result[i].id+'" aria-controls="board'+result[i].id+'" role="tab" data-toggle="tab">';
				str+='<section>';
					str+='<div class="row">';
						str+='<div class="col-md-10 col-xs-12 col-sm-10">';
							str+='<span>'+result[i].name+'</span>';
						str+='</div>';
						str+='<div class="col-md-2 col-xs-12 col-sm-2">';
							str+='<span class="label label-primary pull-right labelCustom">'+result[i].count+'</span>';
						str+='</div>';
					str+='</div>';
				str+='</section>';
			str+='</a>';
		   str+='</li>'; 
		}else{
			 str+='<li role="presentation">';	
			  str+='<a href="#board'+result[i].id+'" class="text-capitalize boardHrfCls" attr_board_id="'+result[i].id+'" aria-controls="board'+result[i].id+'" role="tab" data-toggle="tab">';
				str+='<section>';
					str+='<div class="row">';
						str+='<div class="col-md-10 col-xs-12 col-sm-10">';
							str+='<span>'+result[i].name+'</span>';
						str+='</div>';
						str+='<div class="col-md-2 col-xs-12 col-sm-2">';
							str+='<span class="label label-primary pull-right labelCustom">'+result[i].count+'</span>';
						str+='</div>';
					str+='</div>';
				str+='</section>';
			str+='</a>';
		   str+='</li>'; 	
			}
		}
     str+='</ul>';
  str+='</div>';
  $("#boardRsltDivId").html(str);
  $(".boardCorporation").show();
  setHeight()
}
function buildCandidatePositionRslt(result,departmentId,boardId){
var str = '';
 str+='<div role="tabpane'+boardId+'" class="tab-pane active pad_15" id="board'+boardId+'">';
                	 str+='<h4 class="headingColor text-capital"><u>positions</u></h4>';
                     str+='<p class="text-danger pull-right">Ready for final review</p>';
                     str+='<ul class="positionsUl">';
					 for(var i in result){
						str+='<li>';
                        	str+='<div class="row">';
                            	str+='<div class="col-md-10 col-xs-12 col-sm-10 pad_right0 ">';
                                	str+='<div class="positionsCls modalViewBtn referenceCls" data-toggle="modal" data-target="#myModal" attr_department_id="'+departmentId+'" attr_board_id="'+boardId+'" attr_position_id="'+result[i].id+'">';
                                    	str+='<span>'+result[i].name+'</span>';
                                        str+='<span class="label label-primary labelCustom pull-right">'+result[i].count+'</span>';
                                    str+='</div>';
                                str+='</div>';
                                str+='<div class="col-md-2 col-xs-12 col-sm-2 pad_left0 ">';
                                	str+='<div class="positionsCls text-success">';
									str+='<span> - </span>';
                                    	//str+='<img src="dist/img/Icon1.png"/> - ';
                                    str+='</div>';
                                str+='</div>';
                           str+=' </div>';
                        str+='</li>'; 
					 }
             str+='</ul>';
        str+='</div>';	
  $("#positionRsltDivId").html(str);
  $(".positionsDivCls").show();
  setHeight()
}

$(document).on("click",".referenceCls",function(){
	var departmentId = $(this).attr("attr_department_id");
	var boardId = $(this).attr("attr_board_id");
	var positionId = $(this).attr("attr_position_id");
	var levelId = boardLevelId;
	var levelValue = 1;
	/*if(levelId == 1){
		levelValue = $("#stateId").val();	
	}*/
	if(levelId == 2){
		levelValue = $("#stateId").val();		
	}
	else if(levelId == 3){	
		levelValue = $("#districtId").val();
	}
	else if(levelId == 4){
		levelValue = $("#constituencyId").val();
	}
	else if(levelId >= 5){
		levelValue = $("#manTowDivId").val();
	}
	getReferralCandidateDetails(levelId,levelValue,departmentId,boardId,positionId)
	$("#myModal").modal("show");
});

function getReferralCandidateDetails(levelId,levelVal,deptId,boardId,positionId){
 var jsObj=
	{				
		levelId : 4,				
		levelValue : 299,
		departmentId : 2,
		boardId : 1,
		positionId : 1
	}
	$.ajax({
		  type:'GET',
		  url: 'getAllReferredMemberDetailsForPositionAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null){
		   buildNominatedPostMemberDetails(result,levelId,levelVal,deptId,boardId,positionId)
	   }
   });
}

$(document).on('click','.showPdfCls',function(){        
	var str = '';
	var filePath = $(this).attr("attr_filePath");
	str += '<iframe src="http://mytdp.com/'+filePath+'" width="100%" height="800">';    
	str += '</iframe>';
	$("#pdfReportDetailsId").html(str);
}); 

function buildNominatedPostMemberDetails(result,type,levelId,levelValue,departmentId,boardId,positionId){
	var str='';
	
	str+='<table class="table table-bordered table-condensed tableShort">';
		str+='<thead>';
			str+='<th>Name</th>';
			str+='<th>Mobile</th>';
			str+='<th>Gender</th>';
			str+='<th>Age</th>';
			str+='<th>Caste</th>';
			str+='<th>Sub Caste</th>';
			str+='<th>Party Designations</th>';
			str+='<th style="width:80px">Reports</th>';
			str+='<th>Applied Any Dep/Corp</th>';
			str+='<th>Shortlisted in any dep/ Corp</th>';
			str+='<th>Current Status For this post</th>';
			str+='<th>Reference</th>';
			str+='<th>Comments/ Update Status/ Wishlist</th>';
		str+='</thead>';
	if(result.subList != null && result.subList.length > 0){
		for(var i in result.subList){
			str+='<tr>';
			if(result.subList[i].tdpCadreId != null && result.subList[i].tdpCadreId > 0){
					str+='<td><i class="glyphicon glyphicon-user"></i>  '+result.subList[i].cadreName+'</td>';
					str+='<td>'+result.subList[i].cadreMobile+'</td>';
					str+='<td>'+result.subList[i].cadreGender+'</td>';
					str+='<td>'+result.subList[i].age+'</td>';
					str+='<td>'+result.subList[i].caste+'</td>';
					str+='<td>'+result.subList[i].casteName+'</td>';
					if(result.subList[i].partyPosition != null && result.subList[i].partyPosition.trim.length > 0)
						str+='<td>'+result.subList[i].partyPosition+'</td>';
					else
						str+='<td> - </td>';
					str+='<td>';
					if(result.subList[i].idNamevoList != null && result.subList[i].idNamevoList.length > 0){
						for(var j in result.subList[i].idNamevoList){
							str+='<p class="showPdfCls" attr_filePath="'+result.subList[i].idNamevoList[j].mobileNo+'" data-toggle="modal" data-target="#pdfModelId">'+result.subList[i].idNamevoList[j].status+'<i class="glyphicon glyphicon-list-alt pull-right" style="background-color:green;cursor:pointer;"></i></p>';
						}
					}
					else
						str+=' - ';
					str+='</td>';
					//Suitable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>';
			}
			else{
				str+='<td><i class="glyphicon glyphicon-user"></i>  '+result.subList[i].voterName+'</td>';
					str+='<td>'+result.subList[i].voterMoblie+'</td>';
					str+='<td> - </td>';
					str+='<td> - </td>';
					str+='<td> - </td>';
					str+='<td> - </td>';
					str+='<td> - </td>';
					str+='<td> - </td>';
			}
				str+='<td>'+result.subList[i].appliedDeptCount+'</td>';
				str+='<td>'+result.subList[i].shortListedDeptCount+'</td>';
				/*str+='<td style="position:relative" class="text-center">';
					str+='<span class="appliedCount" attr_cand_id="'+result.subList[i].nominatedPostCandidateId+'" attr_divId="departmentsTableId'+i+'">'+result.subList[i].otherDepartmentsCount+'</span>';
					str+='<div class="appliedPostPopup">';
						str+='<div class="appliedPostPopupArrow" id="departmentsTableId'+i+'">';
						str+='</div>';
					str+='</div>';
				str+='</td>';*/
				/*if(result.subList[i].referCandCount != null)
					str+='<td><a class="referenceCls" data-toggle="modal" data-target="#referModelId" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'">'+result.subList[i].referCandCount+'</a></td>';
				else
					str+='<td> - </td>';*/
				/*if(result.subList[i].otherDeptShortListed != null && result.subList[i].otherDeptShortListed == 'YES')
					str+='<td>'+result.subList[i].otherDeptShortListed+'</td>';
				else
					str+='<td> NO </td>';*/
				str+='<td>'+result.subList[i].status+'</td>';
				if(result.subList[i].referenceCount != null)
					str+='<td><a class="referenceDetailsCls" style="cursor:pointer;" data-toggle="modal" data-target="#referModelId" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'">'+result.subList[i].referenceCount+'</a></td>';
				else
					str+='<td> - </td>';
				str+='<td style="position:relative;width:150px">';
					str+='<span class="commentsDetailsCls" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'"><img src="dist/nominatedImages/Icon5.png" class="commentsBtn" style="height:28px;"/></span>'; 
					str+='<span class="commentCount">'+result.subList[i].commentCount+'</span>';
					str+='<div class="commentsDiv">';
						str+='<div class="commentDropDownArrow" id="commentsDivId'+i+'">';
							str+='<p>COMMENTS <span class="pull-right">X</span></p>';
							str+='<ul class="commentsUl">';
								str+='<li class="shortList">';
									str+='<div class="panel-group" id="commentsAccordion" role="tablist" aria-multiselectable="true">';
									  str+='<div class="panel panel-default commentsPanel">';
										str+='<div class="panel-heading" role="tab" id="CommentsCollapseHeading">';
											str+='<a role="button" data-toggle="collapse" class="CommentsModalIcon" data-parent="#commentsAccordion" href="#CommentsCollapse" aria-expanded="true" aria-controls="CommentsCollapse">';
											  str+='<h4>Shortlisting</h4>';
											str+='</a>';
										str+='</div>';
										str+='<div id="CommentsCollapse" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="CommentsCollapseHeading">';
										  str+='<div class="panel-body">';
											str+='<div class="Comment">';
												str+='Shortlisting  Shortlisting Shortlisting Shortlisting Shortlisting';
												str+='<p class="text-danger"><i>- 10 July 2016 11:30Am</i></p>';
												str+='<p class="text-danger"><i>Updated By Ramesh</i></p>';
											str+='</div>';
											str+='<div class="Comment">';
												str+='Shortlisting  Shortlisting Shortlisting Shortlisting Shortlisting';
												str+='<p class="text-danger"><i>- 10 July 2016 11:30Am</i></p>';
												str+='<p class="text-danger"><i>Updated By Ramesh</i></p>';
											str+='</div>';
											str+='<div class="Comment">';
												str+='Shortlisting  Shortlisting Shortlisting Shortlisting Shortlisting';
												str+='<p class="text-danger"><i>- 10 July 2016 11:30Am</i></p>';
												str+='<p class="text-danger"><i>Updated By Ramesh</i></p>';
											str+='</div>';
										  str+='</div>';
										str+='</div>';
									  str+='</div>';
									str+='</div>';
								str+='</li>';
								str+='<li class="finaLize">';
									str+='<div class="panel-group" id="commentsAccordion12" role="tablist" aria-multiselectable="true">';
									  str+='<div class="panel panel-default commentsPanel">';
										str+='<div class="panel-heading" role="tab" id="CommentsCollapseHeading112">';
											str+='<a class="collapsed CommentsModalIcon" role="button" data-toggle="collapse" data-parent="#commentsAccordion12" href="#CommentsCollapseHeading112" aria-expanded="false" aria-controls="CommentsCollapseHeading2">';
												str+='<h4>Finalization</h4>';
											str+='</a>';
										str+='</div>';
										str+='<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="CommentsCollapseHeading112">';
										  str+='<div class="panel-body">';
											str+='<div class="Comment">';
												str+='Shortlisting  Shortlisting Shortlisting Shortlisting Shortlisting';
												str+='<p class="text-danger"><i>- 10 July 2016 11:30Am</i></p>';
												str+='<p class="text-danger"><i>Updated By Ramesh</i></p>';
											str+='</div>';
											str+='<div class="Comment">';
												str+='Shortlisting  Shortlisting Shortlisting Shortlisting Shortlisting';
												str+='<p class="text-danger"><i>- 10 July 2016 11:30Am</i></p>';
												str+='<p class="text-danger"><i>Updated By Ramesh</i></p>';
											str+='</div>';
										  str+='</div>';
										str+='</div>';
									  str+='</div>';
									str+='</div>';
								str+='</li>';
							str+='</ul>';
						str+='</div>';
					str+='</div>';
					str+='<img src="dist/nominatedImages/Icon4.png" style="height:28px;"/> ';
					str+='<button class="btn btn-success updateBtnDrop">UPDATE</button>';
					str+='<div class="updateDropDown">';
						str+='<div class="updateDropDownArrow">';
							str+='<label class="m_top10">Select Status</label>';
							str+='<select class="form-control">';
								str+='<option>Status</option>';
							str+='</select>';
							str+='<label class="m_top10">Comments</label>';
							str+='<textarea class="form-control"></textarea>';
							str+='<button class="btn btn-success btn-block">SUBMIT</button>';
						str+='</div>';
					str+='</div>';
				str+='</td>';
				/*str+='<td style="position:relative;">';
				//if(type == "this"){
					str+='<button class="btn btn-success btnPopup updateButtonCls" attr_selected_status_id="updatedStatusSelectId'+i+'">UPDATE</button>';
					str+='<div class="updateDropDown" id="updateDropDownId'+i+'">';
						str+='<div class="updateDropDownArrow">';
						str+='<div class="text-success" id="successDivId'+i+'"></div>';
						//str+='<div class="statusUpdateDivCls" id="statusUpdateDivId'+i+'"></div>';
							str+='<i class="glyphicon glyphicon-remove pull-right closeDivCls" id="updateDropDownId'+i+'" style="cursor:pointer;"></i>';
							str+='<label>Select Status</label>';
							str+='<select class="chosenSelect" id="updatedStatusSelectId'+i+'">';
								str+='<option value="0">Select Status</option>';
							str+='</select>';
							str+='<label>Comments</label>';
							str+='<textarea class="form-control" id="statusCommentId'+i+'"></textarea>';
							str+='<button class="btn btn-success btn-block m_top10 updateStatusCls" attr_application_id="'+result.subList[i].nominatePostApplicationId+'" attr_selected_status_id="updatedStatusSelectId'+i+'" attr_comment_id="statusCommentId'+i+'" attr_success_div="successDivId'+i+'" attr_levelId="'+levelId+'" attr_level_value="'+levelValue+'" attr_departmentId="'+departmentId+'" attr_boardId="'+boardId+'" attr_positionId="'+positionId+'" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'">SUBMIT</button>';
						str+='</div>';
					str+='</div>';*/
				//}
				/*else if(type == "any"){
					str+='<button class="btn btn-success btnPopupThisAny updateButtonThisAnyCls" attr_count="'+i+'">ASSIGN THIS POSITION</button>';
					str+='<button class="btn btn-success btnPopupAny updateButtonAnyCls m_top10" attr_count="'+i+'">ASSIGN A POSITION</button>';
					str+='<div class="updateDropDownThisAny" id="updateDropDownThisAny'+i+'">';
						str+='<div class="updateDropDownArrow">';
						str+='<div class="text-success" id="successDivThisAnyId'+i+'"></div>';
								str+='<i class="glyphicon glyphicon-remove pull-right closeDivThisAnyCls" id="updateDropDownThisAny'+i+'" style="cursor:pointer;"></i>';
								str+='<label>Select Status</label>';
								str+='<select class="chosenSelect" id="updatedStatusThisAnyId'+i+'">';
									str+='<option value="0">Select Status</option>';
								str+='</select>';
								str+='<label>Comments</label>';
								str+='<textarea class="form-control" id="statusCommentThisAnyId'+i+'"></textarea>';
								str+='<button class="btn btn-success btn-block m_top10 updateStatusThisAnyCls" attr_application_id="'+result.subList[i].nominatePostApplicationId+'" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'" attr_count="'+i+'" attr_levelId="'+levelId+'" attr_level_value="'+levelValue+'" attr_departmentId="'+departmentId+'" attr_boardId="'+boardId+'" attr_positionId="'+positionId+'">SUBMIT</button>';
						str+='</div>';
					str+='</div>';
					str+='<div class="updateDropDownAny" id="updateDropDownAny'+i+'">';
						str+='<div class="updateDropDownArrow">';
						str+='<div class="text-success" id="successDivAnyId'+i+'"></div>';
							str+='<i class="glyphicon glyphicon-remove pull-right closeDivAnyCls" id="updateDropDownAny'+i+'" style="cursor:pointer;"></i>';
								str+='<div class="row">';
								str+='<div class="col-md-3 col-xs-12 col-sm-3">';
									str+='<label>Department</label>';
									str+='<select class="chosenSelect" id="departmentAnyId'+i+'" onchange="getBoardsForDepartments('+i+')">';
										str+='<option value="0">Select Department</option>';
									str+='</select>';
								str+='</div>';
								str+='<div class="col-md-3 col-xs-12 col-sm-3">';
									str+='<label>Corporation/Board</label>';
									str+='<select class="chosenSelect" id="boardAnyId'+i+'" onchange="getPositionsForBoard('+i+')">';
										str+='<option value="0">Select Board</option>';
									str+='</select>';
								str+='</div>';
								str+='<div class="col-md-3 col-xs-12 col-sm-3">';
									str+='<label>Position</label>';
									str+='<select class="chosenSelect" id="positionAnyId'+i+'">';
										str+='<option value="0">Select Position</option>';
									str+='</select>';
								str+='</div>';
								str+='<div class="col-md-3 col-xs-12 col-sm-3">';
									str+='<label>Select Status</label>';
									str+='<select class="chosenSelect" id="updatedStatusAnyId'+i+'">';
										str+='<option value="0">Select Status</option>';
									str+='</select>';
								str+='</div>';
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									str+='<label>Comments</label>';
									str+='<textarea class="form-control" id="statusCommentAnyId'+i+'"></textarea>';
									str+='<button class="btn btn-success btn-block m_top10 updateStatusAnyCls" attr_application_id="'+result.subList[i].nominatePostApplicationId+'" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'" attr_count="'+i+'" attr_levelId="'+levelId+'" attr_level_value="'+levelValue+'">SUBMIT</button>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				}	*/
				//str+='</td>';
			str+='</tr>';
			/*str+='<tr>';
				str+='<td>Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>';
			str+='</tr>';*/
		}
	}
	str+='</table>';
	
	$("#resultDivId").html(str);
}

/*$(document).on('click','.commentsDetailsCls',function(){
	var candidateId = $(this).attr("attr_candidate_id");
});*/

$(document).on('click','.referenceDetailsCls',function(){
	var candidateId = $(this).attr("attr_candidate_id");
	
	var jsObj=
	   {				
		candidateId:candidateId
		}
    $.ajax({
          type:'GET',
          url: 'getReferCadreDetailsForCandidateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null && result.length > 0){
		   buildReferenceCandidateDetails(result);
	   }
   });
});

function buildReferenceCandidateDetails(result){
	var str='';
	
	str+='<table class="table table-condensed table-bordered">';
		str+='<thead>';
			str+='<th>Image</th>';
			str+='<th>Name</th>';
			str+='<th>Membership No</th>';
			str+='<th>Mobile No</th>';
			str+='<th>Position</th>';
			str+='<th>Total Refered</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				str+='<td><i class="glyphicon glyphicon-user"></i></td>';
				str+='<td>'+result[i].name+'</td>';
				str+='<td>'+result[i].percentage+'</td>';
				str+='<td>'+result[i].mobileNo+'</td>';
				if(result[i].publicRepr == null && result[i].partyPos == null)
					str+='<td> - </td>';
				else{
					str+='<td>';
					if(result[i].publicRepr != null)
						str+='<p>'+result[i].publicRepr+'</p>';
					if(result[i].partyPos != null)
						str+='<p>'+result[i].partyPos+'</p>';
					str+='<td>';
				}
				str+='<td>'+result[i].count+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	
	$("#referenceDetailsId").html(str);
}

$(document).on("click",".deptHrfCls",function(){
	 var deptId = $(this).attr("attr_dept_id");
	 globalDepartmentId = deptId;
	 getFinalReviewCandidateCountLocationWise(globalLocationLevelId,globalLocationLevelValueArr,deptId,0,"department");
});
$(document).on("click",".boardHrfCls",function(){
	var boardId = $(this).attr("attr_board_id");
	 getFinalReviewCandidateCountLocationWise(globalLocationLevelId,globalLocationLevelValueArr,globalDepartmentId,boardId,"board");
});
