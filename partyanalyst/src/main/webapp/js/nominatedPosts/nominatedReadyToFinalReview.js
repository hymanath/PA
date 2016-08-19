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
				buildCandidateBoardRslt(result,departmentId,gblStatus); 
			 }else if(locationType == "board"){
				buildCandidatePositionRslt(result,departmentId,boardId,gblStatus); 
			 }else{
			   buildCandidateReviewRslt(result,gblStatus);  
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
function buildCandidateReviewRslt(result,status){
 $("#boardRsltDivId").html(' ');
 $("#positionRsltDivId").html(' ');
 var str='';
 if(status != "Total"){
	str+='<p class="font_13 text-danger pull-right"><small>Ready For Final Review</small></p>';
 }
    str+='<ul class="nav nav-tabs tabsCustomFinal" role="tablist">';
	  for(var i in result){
	   if(i==0){
		   if(status != "Total"){
		  str+='<li class="active"><a href="#departments'+result[i].id+'" aria-controls="departments'+result[i].id+'" class="deptHrfCls" attr_dept_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" role="tab" data-toggle="tab"><span data-toggle="tooltip" data-placement="top" title="click here to get board details" style="font-weight:bold;cursor:pointer;">'+result[i].name+'</span><span class="label label-primary pull-right labelCustom" title="Ready To Final Review Count" data-toggle="tooltip" data-placement="top">'+result[i].count+'</span></a></li>';
		   }else{
			   str+='<li class="active"><a href="#departments'+result[i].id+'" aria-controls="departments'+result[i].id+'" class="deptHrfCls" attr_dept_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" role="tab" data-toggle="tab"><span data-toggle="tooltip" data-placement="top" title="click here to get board details" style="font-weight:bold;cursor:pointer;">'+result[i].name+'</span><span class="label label-primary pull-right labelCustom" title="Total Boards" data-toggle="tooltip" data-placement="top">'+result[i].count+'</span></a></li>';
		   }
		 }else{
			 if(status != "Total"){
		 str+='<li><a href="#departments'+result[i].id+'" aria-controls="departments'+result[i].id+'" role="tab" class="deptHrfCls" attr_dept_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" data-toggle="tab"><span data-toggle="tooltip" data-placement="top" title="click here to get board details" style="font-weight:bold;cursor:pointer;">'+result[i].name+'</span><span class="label label-primary pull-right labelCustom" title="Ready To Final Review Count" data-toggle="tooltip" data-placement="top">'+result[i].count+'</span></a></li>';
		 }else{
		  str+='<li><a href="#departments'+result[i].id+'" aria-controls="departments'+result[i].id+'" role="tab" class="deptHrfCls" attr_dept_id="'+result[i].id+'" attr_department_name="'+result[i].name+'" data-toggle="tab"><span data-toggle="tooltip" data-placement="top" title="click here to get board details" style="font-weight:bold;cursor:pointer;">'+result[i].name+'</span><span class="label label-primary pull-right labelCustom" title="Total Boards" data-toggle="tooltip" data-placement="top">'+result[i].count+'</span></a></li>';
		 
		 }
		}
      }
	  str+='</ul>';
  $("#candiateReadyToFinalReviewDivId").html(str);
  $(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})
  //setHeight()
}
function buildCandidateBoardRslt(result,departmentId,status){
	 $("#positionRsltDivId").html(' ');
	var str='';
	str+='<div role="tabpanel" class="tab-pane active pad_15" id="departments'+departmentId+'" style="padding-right:0px;padding-bottom:0px;">';
	   str+='<h4 class="headingColor text-capital">board / corporation</h4>';
	   if(status != "Total"){
	   str+='<p class="font_13 text-danger pull-right"><small>Ready For Final Review</small></p>';
	   }
	   str+='<ul class="nav nav-tabs tabsCustomFinal1" style="margin-top:10px !important" role="tablist">';
	    for(var i in result){
			if(i==0){
		   str+='<li class="active">';	
			str+='<a href="#board'+result[i].id+'" class="text-capitalize boardHrfCls" attr_board_id="'+result[i].id+'" attr_board_name="'+result[i].name+'" aria-controls="board'+result[i].id+'" role="tab" data-toggle="tab">';
				str+='<section>';
					str+='<div class="row">';
						str+='<div class="col-md-10 col-xs-12 col-sm-10">';
							str+='<span data-toggle="tooltip" data-placement="top" title="click here to get positions details" style="font-weight:bold;cursor:pointer;">'+result[i].name+'</span>';
						str+='</div>';
						str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						if(status != "Total"){
							str+='<span class="label label-primary pull-right labelCustom" title="Ready To Final Review Count" data-toggle="tooltip" data-placement="top" style="cursor:pointer;">'+result[i].count+'</span>';
						}else{
							str+='<span class="label label-primary pull-right labelCustom" title="Total Positions" data-toggle="tooltip" data-placement="top" style="cursor:pointer;">'+result[i].count+'</span>';
						}
							
						str+='</div>';
					str+='</div>';
				str+='</section>';
			str+='</a>';
			//if(gblStatus=="finaliZed")
				//str+='<button class="btn btn-xs assignGOBtnCls" attr_department_id="'+departmentId+'" attr_board_id="'+result[i].id+'" id="assignGOBtnId'+result[i].id+'">click</button>';
		   str+='</li>'; 
		}else{
			 str+='<li role="presentation">';	
			  str+='<a href="#board'+result[i].id+'" class="text-capitalize boardHrfCls" attr_board_id="'+result[i].id+'" attr_board_name="'+result[i].name+'" aria-controls="board'+result[i].id+'" role="tab" data-toggle="tab">';
				str+='<section>';
					str+='<div class="row">';
						str+='<div class="col-md-10 col-xs-12 col-sm-10">';
							str+='<span data-toggle="tooltip" data-placement="top" title="click here to get positions details" style="font-weight:bold;cursor:pointer;">'+result[i].name+'</span>';
						str+='</div>';
						str+='<div class="col-md-2 col-xs-12 col-sm-2">';
						if(status != "Total"){
							str+='<span class="label label-primary pull-right labelCustom" title="Ready To Final Review Count" data-toggle="tooltip" data-placement="top" style="cursor:pointer;">'+result[i].count+'</span>';
						}else{
							str+='<span class="label label-primary pull-right labelCustom" title="Total Positions" data-toggle="tooltip" data-placement="top" style="cursor:pointer;">'+result[i].count+'</span>';
						}
						str+='</div>';
					str+='</div>';
				str+='</section>';
			str+='</a>';
			//if(gblStatus=="finaliZed")
				//str+='<button class="btn btn-xs assignGOBtnCls" attr_department_id="'+departmentId+'" attr_board_id="'+result[i].id+'" id="assignGOBtnId'+result[i].id+'">click</button>';
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
function buildCandidatePositionRslt(result,departmentId,boardId,status){
var str = '';
	str+='<div role="tabpane'+boardId+'" class="tab-pane active pad_15" id="board'+boardId+'">';
		 str+='<h4 class="headingColor text-capital">positions</h4>';
		 if(status != "Total"){
		 str+='<p class="text-danger font13 col-md-6 pull-right"><small>Ready for final review</small></p>';
		 }
		 str+='<ul class="positionsUl">';
		 for(var i in result){
			str+='<li style="padding:0px">';
				str+='<div class="row">';
					str+='<div class="col-md-10 col-xs-12 col-sm-9 pad_right0 ">';
					if(status != "Total"){
						str+='<div class="positionsCls modalViewBtn referenceCls" attr_position_name="'+result[i].name+'" data-toggle="modal" data-target="#myModal" attr_department_id="'+departmentId+'" attr_board_id="'+boardId+'" attr_position_id="'+result[i].id+'">';
							str+='<span style="font-weight:bold;cursor:pointer;">'+result[i].name+'</span>';
							str+='<span class="label label-primary labelCustom pull-right" title="Ready To Final Review Count" data-toggle="tooltip" data-placement="top" style="cursor:pointer;">'+result[i].count+'</span>';
							}else{
								str+='<div class="positionsCls"  attr_position_name="'+result[i].name+'"  attr_department_id="'+departmentId+'" attr_board_id="'+boardId+'" attr_position_id="'+result[i].id+'">';
							str+='<span style="font-weight:bold;">'+result[i].name+'</span>';
								str+='<span class="label label-primary labelCustom pull-right" title="Members Count" data-toggle="tooltip" data-placement="top">'+result[i].count+'</span>';
							}
						str+='</div>';
					str+='</div>';
					str+='<div class="col-md-2 col-xs-12 col-sm-3 pad_left0 pad_right0">';
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
		positionId : positionId,
		statusId:6
	}
	$.ajax({
		  type:'GET',
		  url: 'getAllReferredMemberDetailsForPositionAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null){
		   buildNominatedPostMemberDetails(result,levelId,levelVal,deptId,boardId,positionId);
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
function buildNominatedPostMemberDetails(result,levelId,levelValue,departmentId,boardId,positionId){
	var str='';
	totalWishListCount = 0;
	
	str+='<table class="table table-bordered table-condensed tableShort">';
		str+='<thead class="text-capitalize" style="background-color:#f2f2f2">';
		if(gblStatus=="finaliZed"){
			str+='<th style="" class="text-center"></th>';
		}
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
			str+='<th class="text-center">Reference</th>';
			str+='<th class="text-center">Current Status</th>';
			str+='<th class="text-center">Comments/ Update Status/ Wishlist</th>';
		str+='</thead>';
	if(result.subList != null && result.subList.length > 0){
		for(var i in result.subList){
			str+='<tr class="text-center">';
			if(gblStatus=="finaliZed"){
				str+='<td><input type="checkbox" class="candiCheckBoxCls" attr_nominatedPostApplicationId="'+result.subList[i].nominatedPostCandidateId+'"></td>';
			}
				//str+='<td><i class="glyphicon glyphicon-user"></i>  '+result.subList[i].voterName+'</td>';
				if(result.subList[i].tdpCadreId != null && result.subList[i].tdpCadreId > 0){
					str+='<td style="width:150px;"><a target="_blank" href="cadreDetailsAction.action?cadreId='+result.subList[i].tdpCadreId+'" >';
					if(result.subList[i].imageURL != null && result.subList[i].imageURL.length>0)
						str +='<div  class="media"><div class="media-left"><img style="width: 50px;height:50px;border:1px solid #ddd;" src="https://mytdp.com/images/cadre_images/'+ result.subList[i].imageURL+'" class="img-circle" alt="Profile"/></div>';
					else
						str+='<i class="glyphicon glyphicon-user"></i> ';
						str+=' <div class="media-body">'+result.subList[i].cadreName+'</div></div></a>';
				}else{
					str +='<td style="width:150px;"><div  class="media"><div class="media-left"><img style="width: 50px;height:50px;border:1px solid #ddd;" src="images/User.png'+ result.subList[i].imageURL+'" class="img-circle" alt="Profile"/> </div><div class="media-body">'+result.subList[i].voterName+'</div></div>';
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
			}
				/* if(result.subList[i].appliedCount != null && result.subList[i].appliedCount > 0)
					str+='<td>'+result.subList[i].appliedCount+'</td>';
				else
					str+='<td> NO </td>';
				
				if(result.subList[i].shortListedCount!=null && result.subList[i].shortListedCount > 0){
					str+='<td>'+result.subList[i].shortListedCount+'</td>';
				}else{
					str+='<td> NO </td>'
				} */
				
				
				
				str+='<td style="position:relative" class="text-center">';
				if(result.subList[i].appliedCount != null && result.subList[i].appliedCount > 0)
					str+='<span class="appliedCount" attr_cand_id="'+result.subList[i].nominatedPostCandidateId+'" attr_divId="departmentsTableId'+i+'" attr_type="applied" style="font-weight:bold;color:green;">'+result.subList[i].appliedCount+'</span>';
				else
					str+='<span> NO </span>';
					str+='<div class="appliedPostPopup">';
						str+='<div class="appliedPostPopupArrow" id="departmentsTableId'+i+'">';
						str+='</div>';
					str+='</div>';
				str+='</td>';
				
				str+='<td style="position:relative" class="text-center">';
					if(result.subList[i].shortListedCount != null && result.subList[i].shortListedCount >0)
						str+='<span class="appliedCount" attr_cand_id="'+result.subList[i].nominatedPostCandidateId+'" attr_divId="shortyListedTableId'+i+'" attr_type="shortlisted" style="font-weight:bold;color:green;">'+result.subList[i].shortListedCount+'</span>';
					
						//str+='<td>'+result.subList[i].shortListedCount+'</td>';
					else
						str+='<span> NO </span>';
						str+='<div class="appliedPostPopup">';
							str+='<div class="appliedPostPopupArrow" id="shortyListedTableId'+i+'">';
							str+='</div>';
						str+='</div>';
					
				str+='</td>';
				
				
				
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
				if(result.subList[i].referenceCount != null && result.subList[i].referenceCount>0)
					str+='<td><a class="referenceDetailsCls" style="cursor:pointer;" data-toggle="modal" data-target="#referModelId" attr_application_id="'+result.subList[i].nominatedPostApplicationId+'">'+result.subList[i].referenceCount+'</a></td>';
				else
					str+='<td> - </td>';
				str+='<td>'+result.subList[i].status+'</td>';
				str+='<td style="position:relative;width:180px">';
					if(result.subList[i].commentCount != null){
						str+='<span style="position:relative"><img src="dist/nominatedImages/Icon5.png" class="commentsBtn commentsDetailsCls" style="height:28px;margin-right:10px;cursor:pointer;" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'" attr_div_id="commentsDivId'+i+'"/>'; 
						str+='<span class="commentCount" style="left:15px;top:-11px">'+result.subList[i].commentCount+'</span></span>';
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
					str+='<button class="btn btn-success updateBtnDrop statusUpdateBntCls" attr_nominatedPostApplicationId="'+result.subList[i].nominatedPostCandidateId+'" attr_department_id="'+departmentId+'" attr_doard_id="'+boardId+'" attr_position_id="'+positionId+'">UPDATE</button>';
					str+='<div class="updateDropDown">';
						if(gblStatus!="finaliZed"){
							str+='<div class="updateDropDownArrow">';
							str+='<div class="text-success updtCmmntErrCls'+i+'" id="successDivId'+i+'"></div>';
							str+='<label calss="m_top10">Select Status</label>';
							str+='<select class="chosenSelect" id="statusSelectId'+i+'">';
							str+='<option value="0">Select Status</option>';
							str+='<option value="4">Rejected-Final Review</option>';
							str+='<option value="5">Confirmed</option>';
							str+='</select>';
							str+='<label class="m_top10">Comments</label>';
							str+='<textarea class="form-control" id="commentAreaId'+i+'"></textarea>';
							str+='<button id="commentStatusSubmitBtnId'+i+'" attr_current_position_id='+i+' class="btn btn-success btn-block submitBtnCls" attr_final_id="'+result.subList[i].nominatedPostFinalId+'" attr_status_id="statusSelectId'+i+'" attr_comment_id="commentAreaId'+i+'" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'" attr_application_id="'+result.subList[i].nominatedPostApplicationId+'" attr_success_div_id="successDivId'+i+'">SUBMIT</button>';
							str+='<img src="images//icons//ajaxImg.gif" id="processingImgId'+i+'" style="display:none;"></img>';
							str+='</div>';
						}
					str+='</div>';
				str+='</td>';
			str+='</tr>';
		}
	}
	str+='</table>';
	str+='<div class="row">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		if(gblStatus=="finaliZed"){
			str+='<button class="btn btn-success btnUpdateAll m_top20" attr_department_id="'+departmentId+'" attr_doard_id="'+boardId+'" attr_position_id="'+positionId+'">UPDATE</button>';
		}
			str+='<div class="updateDropDown updateAllShowPopup">';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#wishListCountId").html(totalWishListCount);
	$("#resultDivId").html(str);
	$(".updateDropDown").width($("#resultDivId").width());
	$(".updateDropDown").css("right","0px");
	if(result.subList.length > 10)
	{
		$("#resultDivId").css("height","400px");
	}else if(result.subList.length > 6)
	{
		$("#resultDivId").addClass("table-responsive");
	}
	$(".chosenSelect").chosen();
				$(".dateR").daterangepicker({
					opens:'left'
				});
				
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

$(document).on("click",".saveGoForCandidateCls",function(){
	
	if($("#goStatusSelId").val() == 0){
		alert("Please Select Status.");
		return;
	}
		
	var startDate = $('input:text[name=daterangepicker_start]').val();
	var endDate = $('input:text[name=daterangepicker_end]').val();
		
	$("#fromDateHidden").val(startDate);
	$("#toDateHidden").val(endDate);
		
	$("#locationLevelIdHidden").val(globalLocationLevelId);
	
	$("#departmentIdHidden").val($(this).attr("attr_department_id"));
	$("#boardIdHidden").val($(this).attr("attr_doard_id"));
	$("#positionIdsHidden").val($(this).attr("attr_position_id"));
	
	var str="";
	if(globalLocationLevelValueArr != null && globalLocationLevelValueArr.length > 0){
		for(var i in globalLocationLevelValueArr){
			str=str+','+globalLocationLevelValueArr[i];
		}
	}
	$("#locationLevelValuesHidden").val(str);
	
	var nominatedPostCandidateId = $(this).attr("attr_nominatedPostApplicationId");
	var applicationIds = "";
	if(nominatedPostCandidateId == 0){
		$(".candiCheckBoxCls").each(function(){
			if($(this).is(":checked")){alert($(this).attr("attr_nominatedPostApplicationId"));
				applicationIds = applicationIds+","+$(this).attr("attr_nominatedPostApplicationId");
			}
		});
	}else{
		applicationIds = nominatedPostCandidateId;
	}
	if(typeof applicationIds == "undefined" || applicationIds.trim() == ""){
		alert("Please Select Atleast One candidate To Assign GO.");
		return;
	}
	
	$("#applicationIdsHidden").val(applicationIds);
	
	
	var uploadHandler = {
		upload: function(o) {
			uploadResult = o.responseText;
			showSbmitStatus(uploadResult);
		}
	};
		
	YAHOO.util.Connect.setForm('goSubmitApplicationForCandi',true);
	YAHOO.util.Connect.asyncRequest('POST','assginGOToNominationPostCandidateAction.action',uploadHandler);
	   
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
    var btnId=$(this).attr('id');   
	var position = $(this).attr("attr_current_position_id");
	var postFinalId = $(this).attr("attr_final_id");
	var statusId = $(this).attr("attr_status_id");
	var commentId = $(this).attr("attr_comment_id");
	var divId = $(this).attr("attr_success_div_id");
	var candidateId = $(this).attr("attr_candidate_id");
	var applicationId = $(this).attr("attr_application_id");
	
	var status = $("#"+statusId).val();
	var comment = $("#"+commentId).val();

	 if(status == 0){
		$(".updtCmmntErrrCls"+position).html('<span style="color:red">Please select status</span>');
		return;
	}else{
		$(".updtCmmntErrrCls"+position).html(' ');
	}
	if(comment ==0 || comment.trim().length==0){
		$(".updtCmmntErrrCls"+position).html('<span style="color:red">Comment is required</span>');
		return;
	}else{
		$(".updtCmmntErrrCls"+position).html(' ');
	}
     $("#processingImgId"+position).show();
	 
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
			$("#processingImgId"+position).hide();
	      }, 1000);	 
	   if(result != null && result == 'success'){
			$("#"+divId).html("Successfully Updated...");
	        $("#"+btnId).attr("disabled","disabled");
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
	var applicationId = $(this).attr("attr_application_id");
	
	var jsObj=
	   {				
		applicationId:applicationId
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
	
	str+='<table class="table table-bordered">';
		str+='<thead class="text-capital" style="background-color:#f4f4f4">';
			str+='<th>Image</th>';
			str+='<th>Name</th>';
			str+='<th>Membership No</th>';
			str+='<th>Mobile No</th>';
			str+='<th>Position</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				//str+='<td><i class="glyphicon glyphicon-user"> </i></td>';
				str+='<td> <a target="_blank" href="cadreDetailsAction.action?cadreId='+result[i].id+'" >';
				str +='<div class="media"><div class="media-left"><img style="width: 50px;height:50px;border:1px solid #ddd;" src="https://mytdp.com/images/cadre_images/'+ result[i].status+'" class=" img-circle"  onerror="setDefaultImage(this);" alt="Profile"/></div>';
				str+='</a></td>';
				if(result[i].name != null)
					str+='<td> <a target="_blank" href="cadreDetailsAction.action?cadreId='+result[i].id+'" >'+result[i].name+'</a></td>';
				else
					str+='<td> - </td>';
				if(result[i].percentage != null)
					str+='<td>'+result[i].percentage+'</td>';
				else
					str+='<td> - </td>';
				if(result[i].mobileNo != null)
					str+='<td>'+result[i].mobileNo+'</td>';
				else
					str+='<td> - </td>';
				str+='<td>';
				if(result[i].publicRepr != null )
					str+='<p>'+result[i].publicRepr+'</p>';
				else
					str+='<p>  </p>';
				
				if(result[i].partyPos != null)
					str+='<p>'+result[i].partyPos+'</p>';
				else
					str+='<p>  </p>';
				str+='</td>';
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
	 var status = gblStatus;
	 getFinalReviewCandidateCountLocationWise1(globalLocationLevelId,globalLocationLevelValueArr,deptId,0,status);
});
function getFinalReviewCandidateCountLocationWise1(globalLocationLevelId,globalLocationLevelValueArr,deptId,boardId,status){     
	//alert(globalLocationLevelId+":"+globalLocationLevelValueArr+":"+deptId+":"+boardId+":"+status) ; 
	var jsObj =
	{
		LocationLevelId : globalLocationLevelId,
		locationLevelValueArr : globalLocationLevelValueArr,
		departmentId : deptId,
		boardId : boardId,
		status : status
	};
	$.ajax({
			type : "POST",
			url : "getFinalReviewCandidateCountLocationWiseAction.action",
			data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if( result.length > 0){  
			var boardIdArray = [];
			for(var i in result){
				boardIdArray.push(result[i].id);  
			}
			
			//getGoPassedCandidateDetails(globalLocationLevelId,globalLocationLevelValueArr,deptId,boardIdArray,3);
			/*console.log(boardIdArray);
			alert(globalLocationLevelId+":"+globalLocationLevelValueArr+":"+deptId+":"+boardIdArray) ;     */
		}
		
	});	
}  
  
$(document).on("click",".boardHrfCls",function(){
	var boardId = $(this).attr("attr_board_id");
	var boardName = $(this).attr("attr_board_name");
	globalBoardName = boardName;
	 getFinalReviewCandidateCountLocationWise(globalLocationLevelId,globalLocationLevelValueArr,globalDepartmentId,boardId,"board");
});
	
	$(document).on("click",".assignGOBtnCls",function(){
		var departmentId = $(this).attr("attr_department_id");
		var boardId = $(this).attr("attr_board_id");
		var jsObj={				
				locationLevelId : globalLocationLevelId,
				locationLevelValueArr : globalLocationLevelValueArr,
				departmentId : departmentId,
				boardId : boardId
		}
		$.ajax({
			  type:'GET',
			  url: 'getPositionsForABoardAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   var str='';
				str+='<form name="goSubmitApplication" id="goSubmitApplication"  method="post" enctype="multipart/form-data">';
				str+='<div class="row">';
					str+='<div class="col-md-3 col-xs-12 col-sm-6">';
						str+='<label>Select Positions</label>';
						str+='<select class="chosenSelect" multiple id="positionsMulSelId">';
						if(result != null && result.length > 0){
							for(var i in result){
								str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
							}
						}
						str+='</select>';
					str+='</div>';
					str+='<div class="col-md-3 col-xs-12 col-sm-6">';
						str+='<label>G.O Name</label>';
						str+='<input type="text" id="goNameTxtId" name="govtOrderVO.goName" class="form-control"/>';
					str+='</div>';
					str+='<div class="col-md-3 col-xs-12 col-sm-6">';
						str+='<label>G.O Code</label>';
						str+='<input type="text" id="goCodeTxtId" name="govtOrderVO.goCode" class="form-control"/>';
					str+='</div>';
					str+='<div class="col-md-3 col-xs-12 col-sm-6">';
						str+='<label>Duration</label>';
						str+='<div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span><input type="text" class="form-control dateR"/></div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-sm-8 m_top20">';
						str+='<label>Remarks/Comments</label>';
						str+='<textarea id="goRemarksTextId" class="form-control" name="govtOrderVO.remarks" name="comment" placeholder="Enter Comments Here..."></textarea>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-8 m_top20">';
						str+='<input type="file" name="inout" class="inputFiler" id="filer_input"/>';
					str+='</div>';
					
				str+='</div>';
				
				str+='<input type="hidden" id="positionIdsHidden" name="govtOrderVO.positionIdsString"/>';
				str+='<input type="hidden" id="fromDateHidden" name="govtOrderVO.fromDate"/>';
				str+='<input type="hidden" id="toDateHidden" name="govtOrderVO.toDate"/>';
				str+='<input type="hidden" id="locationLevelIdHidden" name="govtOrderVO.locationLevelId"/>';
				str+='<input type="hidden" id="locationLevelValuesHidden" name="govtOrderVO.locationLevelValuesStr"/>';
				str+='<input type="hidden" id="departmentIdHidden" name="govtOrderVO.departmentId"/>';
				str+='<input type="hidden" id="boardIdHidden" name="govtOrderVO.boardId"/>';

				str+='</form>';
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<button id="confirmGOBtnId" class="btn btn-success m_top20" attr_department_id="'+departmentId+'" attr_board_id="'+boardId+'">Confirm</button>';
				str+='</div>';
				$("#goAssignPopupInDivId").html(str);
				$("#goAssignPopup").modal("show");
				$(".chosenSelect").chosen();
				$(".dateR").daterangepicker({
					opens:'left'
				});
				$('#filer_input').filer({
					changeInput: '<div class="jFiler-input-dragDrop"><div class="jFiler-input-inner"><div class="jFiler-input-icon"><i class="icon-jfi-folder"></i></div><div class="jFiler-input-text"><h3>Click on this box</h3> <span style="display:inline-block; margin: 15px 0">or</span></div><a class="jFiler-input-choose-btn blue">Browse Files</a></div></div>',
					showThumbs: true,
					theme: "dragdropbox",
					templates: {
						box: '<ul class="jFiler-items-list jFiler-items-grid"></ul>',
						item: '<li class="jFiler-item">\
									<div class="jFiler-item-container">\
										<div class="jFiler-item-inner">\
											<div class="jFiler-item-thumb">\
												<div class="jFiler-item-status"></div>\
												<div class="jFiler-item-info">\
													<span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
													<span class="jFiler-item-others">{{fi-size2}}</span>\
												</div>\
												{{fi-image}}\
											</div>\
											<div class="jFiler-item-assets jFiler-row">\
												<ul class="list-inline pull-left">\
													<li>{{fi-progressBar}}</li>\
												</ul>\
												<ul class="list-inline pull-right">\
													<li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
												</ul>\
											</div>\
										</div>\
									</div>\
								</li>',
						itemAppend: '<li class="jFiler-item">\
							<div class="jFiler-item-container">\
								<div class="jFiler-item-inner">\
									<div class="jFiler-item-thumb">\
										<div class="jFiler-item-status"></div>\
										<div class="jFiler-item-info">\
											<span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
											<span class="jFiler-item-others">{{fi-size2}}</span>\
										</div>\
										{{fi-image}}\
									</div>\
									<div class="jFiler-item-assets jFiler-row">\
										<ul class="list-inline pull-left">\
											<li><span class="jFiler-item-others">{{fi-icon}}</span></li>\
										</ul>\
										<ul class="list-inline pull-right">\
											<li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
										</ul>\
									</div>\
								</div>\
							</div>\
						</li>',
						progressBar: '<div class="bar"></div>',
						itemAppendToEnd: true,
							removeConfirmation: true,
							_selectors: {
								list: '.jFiler-items-list',
								item: '.jFiler-item',
								progressBar: '.bar',
								remove: '.jFiler-item-trash-action'
							}
						},
						dragDrop: {
							dragEnter: null,
							dragLeave: null,
							drop: null,
						},
					  /*  uploadFile: {
						  
							url: "nominatedPostUploadFormAction.action",
							//autoProcessQueue: false,
							data: {"nominatedCandId":globalNominatedCandId},
							type: 'POST',
							
						   enctype: 'multipart/form-data',
							beforeSend: function(){},
							success: function(data, el){
								if(data.resultCode==0){
									$("#savingStatusDivId").html("");
									var parent = el.find(".jFiler-jProgressBar").parent();
									el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
										$("<div class=\"jFiler-item-others text-success\"><i class=\"icon-jfi-check-circle\"></i> Success</div>").hide().appendTo(parent).fadeIn("slow");    
									});
								}else{
									var parent = el.find(".jFiler-jProgressBar").parent();
									el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
										$("<div class=\"jFiler-item-others text-error\"><i class=\"icon-jfi-minus-circle\"></i> Error</div>").hide().appendTo(parent).fadeIn("slow");    
									});
								}
								
								//window.location.href="eventFieUploadAction.action";
							},
							error: function(el){
								var parent = el.find(".jFiler-jProgressBar").parent();
								el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
									$("<div class=\"jFiler-item-others text-error\"><i class=\"icon-jfi-minus-circle\"></i> Error</div>").hide().appendTo(parent).fadeIn("slow");    
								});
							},
							statusCode: null,
							onProgress: null,
							onComplete: null
						},
						  */
						
						files: null,
						addMore: true,
						clipBoardPaste: true,
						excludeName: null,
						beforeRender: null,
						afterRender: null,
						beforeShow: null,
						beforeSelect: null,
						onSelect: null,
						afterShow: null,
					   
						onEmpty: null,
						options: null,
						captions: {
							button: "Choose Files",
							feedback: "Choose files To Upload",
							feedback2: "files were chosen",
							drop: "Drop file here to Upload",
							removeConfirmation: "Are you sure you want to remove this file?",
							errors: {
								filesLimit: "Only {{fi-limit}} files are allowed to be uploaded.",
								filesType: "Only Images are allowed to be uploaded.",
								filesSize: "{{fi-name}} is too large! Please upload file up to {{fi-maxSize}} MB.",
								filesSizeAll: "Files you've choosed are too large! Please upload files up to {{fi-maxSize}} MB."
							}
						}
						
				});
	   });
		
	});
	
	$(document).on("click","#confirmGOBtnId",function(){
		
		var positionIds = "";
		
		$('#positionsMulSelId option:selected').each(function(){
			positionIds=positionIds+","+$(this).val();			
		});
		
		$("#positionIdsHidden").val(positionIds);
		
		var startDate = $('input:text[name=daterangepicker_start]').val();
		var endDate = $('input:text[name=daterangepicker_end]').val();
		
		$("#fromDateHidden").val(startDate);
		$("#toDateHidden").val(endDate);
		
		$("#locationLevelIdHidden").val(globalLocationLevelId);
		
		$("#departmentIdHidden").val($(this).attr("attr_department_id"));
		$("#boardIdHidden").val($(this).attr("attr_board_id"));
		
		var str="";
		if(globalLocationLevelValueArr != null && globalLocationLevelValueArr.length > 0){
			for(var i in globalLocationLevelValueArr){
				str=str+','+globalLocationLevelValueArr[i];
			}
		}
		$("#locationLevelValuesHidden").val(str);
		
		var uploadHandler = {
			upload: function(o) {
				uploadResult = o.responseText;
				showSbmitStatus(uploadResult);
			}
		};
		
		YAHOO.util.Connect.setForm('goSubmitApplication',true);
		YAHOO.util.Connect.asyncRequest('POST','confirmGOForNominatedPostsAction.action',uploadHandler);
		
	});
	
	function showSbmitStatus(result){
		if(result.indexOf("SUCCESS") > -1){
			saveFlag =true;
			if (confirm('Application Received Successfully...')) {
				$("#goAssignPopup").modal("hide");
			}
		}else {
			alert("Something goes wrong, Please try again....");
		}
	}
	var strglob = ' '
	strglob+='<div class="updateDropDownArrow ">';
		strglob+='<div class="row ">';
		strglob+='<form name="goSubmitApplicationForCandi" id="goSubmitApplicationForCandi"  method="post" enctype="multipart/form-data">';
			strglob+='<div class="col-md-3 col-xs-12 col-sm-6">';
				strglob+='<label>Select Status</label>';
				strglob+='<select class="chosenSelect" id="goStatusSelId" name="govtOrderVO.status">';
					strglob+='<option value="0">Select Status</option>';
					strglob+='<option value="1">G.O Issue</option>';
					strglob+='<option value="2">Reject</option>';
				strglob+='</select>';
			strglob+='</div>';
			strglob+='<div class="col-md-3 col-xs-12 col-sm-6">';
				strglob+='<label>G.O Name</label>';
				strglob+='<input type="text" name="govtOrderVO.goName" class="form-control"/>';
			strglob+='</div>';
			strglob+='<div class="col-md-3 col-xs-12 col-sm-6">';
				strglob+='<label>G.O Code</label>';
				strglob+='<input type="text" name="govtOrderVO.goCode" class="form-control"/>';
			strglob+='</div>';
			strglob+='<div class="col-md-3 col-xs-12 col-sm-6">';
				strglob+='<label>Positions Duration</label>';
				strglob+='<div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span><input type="text" class="form-control dateR"/></div>';
			strglob+='</div>';
			strglob+='<div class="col-md-12 col-xs-12 col-sm-6">';
				strglob+='<label>Comments</label>';
				strglob+='<textarea id="goRemarksTextId" class="form-control" name="govtOrderVO.remarks" name="comment" placeholder="Enter Comments Here..."></textarea>';
			strglob+='</div>';
			strglob+='<div class="col-md-12 col-xs-12 col-sm-6">';
				strglob+='<label>Upload G.O</label>';
				strglob+='<input type="file" name="inout" class="inputFiler" id="filer_input"/>';
			strglob+='</div>';
				strglob+='<input type="hidden" id="positionIdsHidden" name="govtOrderVO.positionIdsString"/>';
				strglob+='<input type="hidden" id="fromDateHidden" name="govtOrderVO.fromDate"/>';
				strglob+='<input type="hidden" id="toDateHidden" name="govtOrderVO.toDate"/>';
				strglob+='<input type="hidden" id="locationLevelIdHidden" name="govtOrderVO.locationLevelId"/>';
				strglob+='<input type="hidden" id="locationLevelValuesHidden" name="govtOrderVO.locationLevelValuesStr"/>';
				strglob+='<input type="hidden" id="departmentIdHidden" name="govtOrderVO.departmentId"/>';
				strglob+='<input type="hidden" id="boardIdHidden" name="govtOrderVO.boardId"/>';
				strglob+='<input type="hidden" id="applicationIdsHidden" name="govtOrderVO.applicationIds"/>';
				
			strglob+='</form>';
			strglob+='<div class="col-md-5 col-xs-12 col-sm-6">';
				strglob+='<button class="btn btn-success m_top20 saveGoForCandidateCls">SUBMIT</button>';
			strglob+='</div>';
		strglob+='</div>';
	strglob+='</div>';
	
	
	
	
$(document).on("click",".appliedCount",function(e){
	$(".appliedPostPopup").hide();
	$(this).closest('td').find(".appliedPostPopup").show();
	$(this).closest('td').find(".appliedPostPopupArrow").html(' ');
	e.stopPropagation();
	var candidateId = $(this).attr("attr_cand_id");
	var divId = $(this).attr("attr_divId");
	var searchType = $(this).attr("attr_type");
	getBrdWisNominPstAppliedDepOrCorpDetails(candidateId,divId,searchType);
});
	
	
function getBrdWisNominPstAppliedDepOrCorpDetails(candidateId,divId,searchType){
	
	var jsObj=
	{				
		candidateId:candidateId,
		searchType:searchType
	}
    $.ajax({
          type:'GET',
          url: 'getBrdWisNominPstAppliedDepOrCorpDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null && result.length > 0){
		   buildDepartmentDetails(result,divId);
	   }
   });
}

function buildDepartmentDetails(result,divId){
	var str='';
	
	//str+='<i class="glyphicon glyphicon-remove pull-right"></i>';
	str+='<div id="tableScrollId">';
	str+='<table class="table table-condensed">';
		str+='<thead style="background-color:#f4f4f4;" class="text-capital">';
			str+='<th>Level</th>';
			str+='<th>Location</th>';
			str+='<th>Department</th>';
			str+='<th>Corporation</th>';
			str+='<th>Position</th>';
			str+='<th>Status</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				str+='<td>'+result[i].hno+'</td>';
				str+='<td>'+result[i].name+'</td>';
				if(result[i].mobileNo != null && result[i].mobileNo != "")
					str+='<td>'+result[i].mobileNo+'</td>';
				else
					str+='<td> Any </td>';
				if(result[i].pincode != null && result[i].pincode != "")
					str+='<td>'+result[i].pincode+'</td>';
				else
					str+='<td> Any </td>';
				if(result[i].voterCardNo != null && result[i].voterCardNo != "")
					str+='<td>'+result[i].voterCardNo+'</td>';
				else
					str+='<td> Any </td>';
				str+='<td>'+result[i].perc+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	str+='</div>';
	$("#"+divId).html(str);
	if(result.length > 5)
	{
		$("#tableScrollId").mCustomScrollbar({setHeight: '200px'})
	}
}

$(document).on("click",function(){
	$(".updateDropDown").hide();
	$(".appliedPostPopup").hide();
});
$(document).on("click",".updateDropDown,.appliedPostPopup",function(e){
	e.stopPropagation()
});
$(document).on("click",".closeIcon",function(e){
	$(this).closest(".updateDropDown").hide();
});
	