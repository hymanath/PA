var globalDistrictArr=[];
var globalAssmblyArr=[];
var globalMandalTownDivArr=[];
var globalLocationLevelValueArr =[];
var globalLocationLevel=5;
var globalLocationLevelId = 0;
var globalDepartmentId = 0 ;
var globalDepartmentName;
var globalBoardName;

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
		boardId : boardId,
		status : gblStatus
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
		  str+='<li class="active"><a href="#departments'+result[i].id+'" aria-controls="departments'+result[i].id+'" class="deptHrfCls" attr_dept_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" role="tab" data-toggle="tab"><span data-toggle="tooltip" data-placement="top" title="click here to get board details">'+result[i].name+'</span><span class="label label-primary pull-right labelCustom" title="Total Opened Positions" data-toggle="tooltip" data-placement="top">'+result[i].count+'</span></a></li>';
		 }else{
		 str+='<li><a href="#departments'+result[i].id+'" aria-controls="departments'+result[i].id+'" role="tab" class="deptHrfCls" attr_dept_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" data-toggle="tab"><span data-toggle="tooltip" data-placement="top" title="click here to get board details">'+result[i].name+' </span><span class="label label-primary pull-right labelCustom" title="Total Opened Positions" data-toggle="tooltip" data-placement="top">'+result[i].count+'</span></a></li>';
		}
      }
	  str+='</ul>';
  $("#candiateReadyToFinalReviewDivId").html(str);
  $(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})
  //setHeight()
}
function buildCandidateBoardRslt(result,departmentId){
	 $("#positionRsltDivId").html(' ');
	var str='';
	str+='<div role="tabpanel" class="tab-pane active pad_15" id="departments'+departmentId+'" style="padding-right:0px;padding-bottom:0px;">';
	   str+='<h4 class="headingColor text-capital"><u>board / corporation</u></h4>';
	   str+='<ul class="nav nav-tabs tabsCustomFinal1" role="tablist">';
	    for(var i in result){
			if(i==0){
		   str+='<li class="active">';	
			str+='<a href="#board'+result[i].id+'" class="text-capitalize boardHrfCls" attr_board_id="'+result[i].id+'" attr_board_name="'+result[i].name+'" aria-controls="board'+result[i].id+'" role="tab" data-toggle="tab">';
				str+='<section>';
					str+='<div class="row">';
						str+='<div class="col-md-10 col-xs-12 col-sm-10">';
							str+='<span data-toggle="tooltip" data-placement="top" title="click here to get positions details">'+result[i].name+'</span>';
						str+='</div>';
						str+='<div class="col-md-2 col-xs-12 col-sm-2">';
							str+='<span class="label label-primary pull-right labelCustom" title="Total Opened Positions" data-toggle="tooltip" data-placement="top">'+result[i].count+'</span>';
						str+='</div>';
					str+='</div>';
				str+='</section>';
			str+='</a>';
		   str+='</li>'; 
		}else{
			 str+='<li role="presentation">';	
			  str+='<a href="#board'+result[i].id+'" class="text-capitalize boardHrfCls" attr_board_id="'+result[i].id+'" attr_board_name="'+result[i].name+'" aria-controls="board'+result[i].id+'" role="tab" data-toggle="tab">';
				str+='<section>';
					str+='<div class="row">';
						str+='<div class="col-md-10 col-xs-12 col-sm-10">';
							str+='<span data-toggle="tooltip" data-placement="top" title="click here to get positions details">'+result[i].name+'</span>';
						str+='</div>';
						str+='<div class="col-md-2 col-xs-12 col-sm-2">';
							str+='<span class="label label-primary pull-right labelCustom" title="Total Opened Positions" data-toggle="tooltip" data-placement="top">'+result[i].count+'</span>';
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
   $(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})
  //setHeight()
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
					str+='<div class="col-md-10 col-xs-12 col-sm-9 pad_right0 ">';
						str+='<div class="positionsCls modalViewBtn referenceCls" style="cursor:pointer;" attr_position_name="'+result[i].name+'" data-toggle="modal" data-target="#myModal" attr_department_id="'+departmentId+'" attr_board_id="'+boardId+'" attr_position_id="'+result[i].id+'">';
							str+='<span>'+result[i].name+'</span>';
							str+='<span class="label label-primary labelCustom pull-right" title="Total Opened Positions" data-toggle="tooltip" data-placement="top">'+result[i].count+'</span>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-3 pad_left0 ">';
						str+='<div class="positionsCls text-success">';
						if(result[i].wishCount != null && result[i].wishCount > 0)
							str+='<span><img src="dist/nominatedImages/Icon4.png"/> &nbsp;&nbsp;'+result[i].wishCount+'</span>';
						else
							str+='<span><img src="dist/nominatedImages/Icon7.png"/> &nbsp;&nbsp;0</span>';
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
  $(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})
  setHeight()
}

$(document).on("click",".referenceCls",function(){
	var departmentId = $(this).attr("attr_department_id");
	var boardId = $(this).attr("attr_board_id");
	var positionId = $(this).attr("attr_position_id");
	var positionName = $(this).attr("attr_position_name");
	
	var levelId = boardLevelId;
	var levelValue = 1;
	var level = "central";
	/*if(levelId == 1){
		levelValue = $("#stateId").val();	
	}*/
	if(levelId == 2){
		levelValue = $("#stateId").val();	
		level = "state";
	}
	else if(levelId == 3){	
		levelValue = $("#districtId").val();
		level = "District";
	}
	else if(levelId == 4){
		levelValue = $("#constituencyId").val();
		level = "Constituency";
	}
	else if(levelId >= 5){
		levelValue = $("#manTowDivId").val();
		level = "Mandal";
	}
	getReferralCandidateDetails(levelId,levelValue,departmentId,boardId,positionId)
	$("#myModal").modal("show");
	$("#headingPostId").html(positionName+" POST");
	$("#totalHeadingId").html(level+" Level - "+globalDepartmentName+" Department - "+globalBoardName+" Board");
});
//111
function getReferralCandidateDetails(levelId,levelVal,deptId,boardId,positionId){
 var jsObj=
	{				
		levelId : levelId,				
		levelValue : levelVal,
		departmentId : deptId,
		boardId : boardId,
		positionId : positionId
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
	str += '<iframe src="https://mytdp.com/'+filePath+'" width="100%" height="800">';    
	str += '</iframe>';
	$("#pdfReportDetailsId").html(str);
}); 
var totalWishListCount = 0;
function buildNominatedPostMemberDetails(result,type,levelId,levelValue,departmentId,boardId,positionId){
	var str='';
	totalWishListCount = 0;
	
	str+='<table class="table table-bordered table-condensed tableShort">';
		str+='<thead class="text-capitalize" style="background-color:#f2f2f2">';
			str+='<th style="width:150px" class="text-center">Name</th>';
			str+='<th class="text-center">Mobile</th>';
			str+='<th class="text-center">Gender</th>';
			str+='<th class="text-center" style="width:24px">Age</th>';
			str+='<th class="text-center" style="width:90px">Caste</th>';
			str+='<th class="text-center">Sub Caste</th>';
			str+='<th class="text-center">Party Designations</th>';
			str+='<th class="text-center" style="width:80px">Reports</th>';
			str+='<th  class="text-center" style="width:175px">Applied Any Dep/Corp</th>';
			str+='<th style="width:175px" class="text-center">Shortlisted any dep/ Corp</th>';
			str+='<th class="text-center">Status</th>';
			str+='<th class="text-center">Reference</th>';
			str+='<th class="text-center">Comments/ Update Status/ Wishlist</th>';
		str+='</thead>';
	if(result.subList != null && result.subList.length > 0){
		for(var i in result.subList){
			str+='<tr class="text-center">';
				//str+='<td><i class="glyphicon glyphicon-user"></i>  '+result.subList[i].voterName+'</td>';
				if(result.subList[i].tdpCadreId != null && result.subList[i].tdpCadreId > 0){
					str+='<td style="width:150px;"><a target="_blank" href="cadreDetailsAction.action?cadreId='+result.subList[i].tdpCadreId+'" >';
					if(result.subList[i].imageURL != null && result.subList[i].imageURL.length>0)
						str +='<div  class="media"><div class="media-left"><img style="width: 50px;height:50px;border:1px solid #ddd;" src="https://mytdp.com/images/cadre_images/'+ result.subList[i].imageURL+'" class="img-circle" alt="Profile"/></div>';
					else
						str+='<i class="glyphicon glyphicon-user"></i> ';
						str+=' <div class="media-body">'+result.subList[i].voterName+'</div></div></a>';
				}else{
					str +='<td style="width:150px;"><div  class="media"><div class="media-left"><img style="width: 50px;height:50px;border:1px solid #ddd;" src="https://mytdp.com/not_cadre_images/'+ result.subList[i].imageURL+'" class="img-circle" alt="Profile"/> </div><div class="media-body">'+result.subList[i].voterName+'</div></div>';
				}
				
				str+=' </td>';
				
			if(result.subList[i].tdpCadreId != null && result.subList[i].tdpCadreId > 0){
					//str+='<td><i class="glyphicon glyphicon-user"></i>  '+result.subList[i].cadreName+'</td>';
					str+='<td>'+result.subList[i].cadreMobile+'</td>';
					str+='<td>'+result.subList[i].cadreGender+'</td>';
					str+='<td>'+result.subList[i].cadreAge+'</td>';
					str+='<td>'+result.subList[i].caste+'</td>';
					str+='<td>'+result.subList[i].casteName+'</td>';
					str+='<td>';
					if(result.subList[i].publicReprStr != null && result.subList[i].publicReprStr.length > 0)
						str+='<p>'+result.subList[i].publicReprStr+'</p>';
					if(result.subList[i].partyPosition != null && result.subList[i].partyPosition.length > 0)
						str+='<p>'+result.subList[i].partyPosition+'</p>';
					if(result.subList[i].publicReprStr == null && result.subList[i].partyPosition == null)
						str+=' - ';
					str+='</td>';
					str+='<td>';
					if(result.subList[i].idNamevoList != null && result.subList[i].idNamevoList.length > 0){
						for(var j in result.subList[i].idNamevoList){
							str+='<p class="showPdfCls" attr_filePath="'+result.subList[i].idNamevoList[j].mobileNo+'" data-toggle="modal" data-target="#pdfModelId">'+result.subList[i].idNamevoList[j].status+'<i class="glyphicon glyphicon-list-alt pull-right" style="background-color:green;cursor:pointer;"></i></p>';
						}
					}
					if(result.subList[i].nomDocsList != null && result.subList[i].nomDocsList.length > 0){
						for(var j in result.subList[i].nomDocsList){
							str+='<p class="showPdfCls" attr_filePath="'+result.subList[i].nomDocsList[j].mobileNo+'" data-toggle="modal" data-target="#pdfModelId">'+result.subList[i].nomDocsList[j].status+'<i class="glyphicon glyphicon-list-alt pull-right" style="background-color:green;cursor:pointer;"></i></p>';
						}
					}
					if(result.subList[i].idNamevoList == null && result.subList[i].nomDocsList == null)
						str+=' - ';
					str+='</td>';
					//Suitable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>';
			}
			else{
				str+='<td>'+result.subList[i].voterMoblie+'</td>';
				str+='<td>'+result.subList[i].voterGender+'</td>';
				str+='<td>'+result.subList[i].age+'</td>';
				str+='<td>'+result.subList[i].candCaste+'</td>';
				str+='<td>'+result.subList[i].candCasteName+'</td>';
					str+='<td> - </td>';
					str+='<td>';
					if(result.subList[i].nomDocsList != null && result.subList[i].nomDocsList.length > 0){
						for(var j in result.subList[i].nomDocsList){
							str+='<p class="showPdfCls" attr_filePath="'+result.subList[i].nomDocsList[j].mobileNo+'" data-toggle="modal" data-target="#pdfModelId">'+result.subList[i].nomDocsList[j].status+'<i class="glyphicon glyphicon-list-alt pull-right" style="background-color:green;cursor:pointer;"></i></p>';
						}
					}
					else
						str+=' - ';
					str+='</td>';
					//str+='<td> - </td>';
					//str+='<td> - </td>';
					//str+='<td> - </td>';
					//str+='<td> - </td>';
			}
				str+='<td>'+result.subList[i].appliedDeptCount+'</td>';
				
				if(result.subList[i].shortListedDeptCount!=null && result.subList[i].shortListedDeptCount.length>0){
					str+='<td>'+result.subList[i].shortListedDeptCount+'</td>';
				}else{
					str+='<td> - </td>'
				}
				
				//str+='<td>'+result.subList[i].shortListedDeptCount+'</td>';
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
				str+='<td style="position:relative;width:180px">';
					
					if(result.subList[i].commentCount != null){
						str+='<img src="dist/nominatedImages/Icon5.png" class="commentsBtn commentsDetailsCls" style="height:28px;margin-right:10px;cursor:pointer;" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'" attr_div_id="commentsDivId'+i+'"/>'; 
						str+='<span class="commentCount">'+result.subList[i].commentCount+'</span>';
					}
					else						
						str+='<img src="dist/nominatedImages/Icon8.png" style="height:28px;margin-right:10px;"/>'; 
					
					str+='<div class="commentsDiv">';
						str+='<div class="commentDropDownArrow" id="commentsDivId'+i+'">';
							
						str+='</div>';
					str+='</div>';
					if(result.subList[i].isPrefered == "Y"){
						totalWishListCount = parseInt(totalWishListCount)+parseInt(1);
						str+='<img src="dist/nominatedImages/Icon4.png" class="wishListCls" id="wishListId'+i+'" attr_remark="Y" attr_final_id="'+result.subList[i].nominatedPostFinalId+'" style="height:28px;cursor:pointer;"/> ';
					}
					else{
						str+='<img src="dist/nominatedImages/Icon7.png" class="wishListCls" id="wishListId'+i+'" attr_remark="N" attr_final_id="'+result.subList[i].nominatedPostFinalId+'" style="height:28px;cursor:pointer;"/> ';
					}
					//str+='<img src="dist/nominatedImages/Icon4.png" style="height:28px;"/> ';
					str+='<button class="btn btn-success updateBtnDrop statusUpdateBntCls" attr_status_id="statusSelectId'+i+'">UPDATE</button>';
					str+='<div class="updateDropDown">';
						str+='<div class="updateDropDownArrow">';
						str+='<div class="text-success" id="successDivId'+i+'"></div>';
						str+='<div id="popUpErrorId" style="color:red;"></div>';
							str+='<label class="m_top10">Select Status</label>';
							str+='<select class="chosenSelect" id="statusSelectId'+i+'">';
								str+='<option value="0">Select Status</option>';
								str+='<option value="4">Rejected-Final Review</option>';
								str+='<option value="5">Confirmed</option>';
							str+='</select>';
							str+='<label class="m_top10">Comments</label>';
							str+='<textarea class="form-control" id="commentAreaId'+i+'"></textarea>';
							str+='<button class="btn btn-success btn-block submitBtnCls" attr_final_id="'+result.subList[i].nominatedPostFinalId+'" attr_status_id="statusSelectId'+i+'" attr_comment_id="commentAreaId'+i+'" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'" attr_application_id="'+result.subList[i].nominatedPostApplicationId+'" attr_success_div_id="successDivId'+i+'">SUBMIT</button>';
							str+='<img src="images//icons//ajaxImg.gif" id="imgModelPopUpId" style="display:none;"></img>';
						str+='</div>';
					str+='</div>';
				str+='</td>';
			str+='</tr>';
			/*str+='<tr>';
				str+='<td>Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>';
			str+='</tr>';*/
		}
	}
	str+='</table>';
	
	$("#wishListCountId").html(totalWishListCount);
	$("#resultDivId").html(str);
	if(result.subList.length > 15)
	{
		$("#resultDivId").css("height","400px");
	}
}

$(document).on("click",".wishListCls",function(){
	var postFinalId = $(this).attr("attr_final_id");
	var remark = $(this).attr("attr_remark");
	var id = $(this).attr("id");
	
	var jsObj={
		postFinalId : postFinalId,
		remark : remark
	}
	$.ajax({
          type:'GET',
          url: 'updateWishListForCandidateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null && result == 'success'){
		   if(remark == 'Y'){
			    $("#"+id).attr("src","dist/nominatedImages/Icon7.png");
			    $("#"+id).attr("attr_remark","N");
				totalWishListCount = parseInt(totalWishListCount)-parseInt(1);
				$("#wishListCountId").html(totalWishListCount);
		   }
		   else if(remark == 'N'){
			   $("#"+id).attr("src","dist/nominatedImages/Icon4.png");
			   $("#"+id).attr("attr_remark","Y");
			   totalWishListCount = parseInt(totalWishListCount)+parseInt(1);
				$("#wishListCountId").html(totalWishListCount);
		   }
		}
	});
});

$(document).on("click",".statusUpdateBntCls",function(){
	var selectDivId = $(this).attr("attr_status_id");
	$("#"+selectDivId).chosen();
	//getApplicationStatus(selectDivId);
});

function getApplicationStatus(divId){
	$("#"+divId+" option").remove();
	
	var jsObj={}
	$.ajax({
          type:'GET',
          url: 'getAllApplicationStatusListAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result != null && result.length > 0){
		   $("#"+divId).append('<option value="0">Select Status</option>');
		   for(var i in result){
			   $("#"+divId).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
		   }
		   $("#"+divId).chosen();
	   }
   });
}

$(document).on("click",".submitBtnCls",function(){
	var postFinalId = $(this).attr("attr_final_id");
	var statusId = $(this).attr("attr_status_id");
	var commentId = $(this).attr("attr_comment_id");
	var divId = $(this).attr("attr_success_div_id");
	var candidateId = $(this).attr("attr_candidate_id");
	var applicationId = $(this).attr("attr_application_id");
	
	var status = $("#"+statusId).val();
	var comment = $("#"+commentId).val();
	
	var statsIdElemt=$("#statusSelectId0").val();
	var statscommentId=$("#commentAreaId0").val().trim();
	
	if(statsIdElemt == 0){
		$("#popUpErrorId").html('Please select status');
		return;
	}else{
		$("#popUpErrorId").html(' ');
	}
	if(statscommentId ==0 && statscommentId.length==0){
		$("#popUpErrorId").html('Comment is required');
		return;
	}else{
		$("#popUpErrorId").html(' ');
	}
	
	  var errStr='';
       if(errStr.length>0){
         $("#popUpErrorId").html(errStr);
            return;
          }
	
       $("#imgModelPopUpId").show();
	var jsObj=
	   {				
		postFinalId:postFinalId,
		statusId:status,
		comment :comment,
		applicationId :applicationId,
		candidateId :candidateId
	   }
    $.ajax({
          type:'GET',
          url: 'updateFinalyzationStatusForPostAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   
	   setTimeout(function() {
			$("#imgModelPopUpId").hide();
	      }, 750);	
	   if(result != null && result == 'success'){
			$("#"+divId).html("Successfully Updated...");
	        $(".submitBtnCls").attr("disabled","disabled");
	   }else{
		   $("#"+divId).html("<span style='color:red;'>Sorry,Exception Occured...Please try again...</span>");
		}
			
   });
});

$(document).on('click','.commentsDetailsCls',function(){
	var candidateId = $(this).attr("attr_candidate_id");
	var divId = $(this).attr("attr_div_id");
	getOverAllCommentDetails(candidateId,divId);
});

function getOverAllCommentDetails(candidateId,divId){
	$("#"+divId).html("");
	var jsObj=
	   {				
		candidateId:candidateId
		}
    $.ajax({
          type:'GET',
          url: 'getOverAllCommentsForCandidateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null){
		   buildOverAllComments(result,divId);
	   }
   });
}

function buildOverAllComments(result,divId){
	var str='';
	
	str+='<p>COMMENTS</p>';
	str+='<ul class="commentsUl">';
		str+='<li class="finaLize">';
			str+='<div class="panel-group" id="commentsAccordion12" role="tablist" aria-multiselectable="true">';
			  str+='<div class="panel panel-default commentsPanel">';
				str+='<div class="panel-heading" role="tab" id="CommentsCollapseHeading112">';
					str+='<a class="CommentsModalIcon" role="button" data-toggle="collapse" data-parent="#commentsAccordion12" href="#CommentsCollapseHeading12" aria-expanded="false" aria-controls="CommentsCollapseHeading12">';
						str+='<h4>Finalization</h4>';
					str+='</a>';
				str+='</div>';
				str+='<div id="CommentsCollapseHeading12" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="CommentsCollapseHeading112">';
				  str+='<div class="panel-body">';
				  if(result.idnameList != null && result.idnameList.length > 0){
					  for(var i in result.idnameList){
						str+='<div class="Comment">';
							str+=''+result.idnameList[i].status+'';
							str+='<p class="text-danger"><i>- '+result.idnameList[i].dateStr+'</i></p>';
							str+='<p class="text-danger"><i>Updated By '+result.idnameList[i].name+' '+result.idnameList[i].mobileNo+'</i></p>';
						str+='</div>';
					    }
				    }
					str+='</div>';
				str+='</div>';
			  str+='</div>';
			str+='</div>';
		str+='</li>';
		str+='<li class="shortList">';
			str+='<div class="panel-group" id="commentsAccordion" role="tablist" aria-multiselectable="true">';
			  str+='<div class="panel panel-default commentsPanel">';
				str+='<div class="panel-heading" role="tab" id="CommentsCollapseHeading">';
					str+='<a role="button" data-toggle="collapse" class="CommentsModalIcon collapsed" data-parent="#commentsAccordion" href="#CommentsCollapse" aria-expanded="true" aria-controls="CommentsCollapse">';
					  str+='<h4>Shortlisting</h4>';
					str+='</a>';
				str+='</div>';
				str+='<div id="CommentsCollapse" class="panel-collapse collapse" role="tabpanel" aria-labelledby="CommentsCollapseHeading">';
				  str+='<div class="panel-body">';
				    if(result.subList1 != null && result.subList1.length > 0){
					  for(var i in result.subList1){
						str+='<div class="Comment">';
							str+=''+result.subList1[i].status+'';
							str+='<p class="text-danger"><i>- '+result.subList1[i].dateStr+'</i></p>';
							str+='<p class="text-danger"><i>Updated By '+result.subList1[i].name+' '+result.subList1[i].mobileNo+'</i></p>';
						str+='</div>';
					    }
				    }
					str+='</div>';
				str+='</div>';
			  str+='</div>';
			str+='</div>';
		str+='</li>';
	str+='</ul>';
	
	$("#"+divId).html(str);
	
}

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
	 var deptName = $(this).attr("attr_department_name");
	 globalDepartmentId = deptId;
	 globalDepartmentName = deptName;
	 getFinalReviewCandidateCountLocationWise(globalLocationLevelId,globalLocationLevelValueArr,deptId,0,"department");
});
$(document).on("click",".boardHrfCls",function(){
	var boardId = $(this).attr("attr_board_id");
	var boardName = $(this).attr("attr_board_name");
	globalBoardName = boardName;
	 getFinalReviewCandidateCountLocationWise(globalLocationLevelId,globalLocationLevelValueArr,globalDepartmentId,boardId,"board");
});
