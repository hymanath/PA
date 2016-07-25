var globalDistrictArr=[];
var globalAssmblyArr=[];
var globalMandalTownDivArr=[];
var globalLocationLevelValueArr =[];
var globalLocationLevel=5;
var globalLocationLevelId = 0;
var globalDepartmentId = 0 ;
var globalStateId = 0;
function getBoardLevelId(boardLevelId,stateId){
	globalStateId = stateId;
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
	   getDistrictsForStates(stateId);
	   setTimeout(function(){
	   getDistrictConstituencyAndMndlTwnDvsnLvlData(boardLevelId);
       }, 1500); 
	} 
}
function getDistrictsForStates(state){
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
             if(globalLocationLevelId !=1 && globalLocationLevelId !=2 && globalLocationLevelId !=3){
				getConstituenciesForDistricts(0,state);
			 }
	   });
}
$(document).on("change","#districtId",function(){
	var districtId = $(this).val();
	if(globalLocationLevelId !=1 && globalLocationLevelId !=2 && globalLocationLevelId !=3){
	 getConstituenciesForDistricts(districtId,globalStateId);
	}
});

function getConstituenciesForDistricts(district,stateId){
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
				   if(globalLocationLevelId == 4){
					   globalLocationLevelValueArr =[];
					   globalLocationLevelValueArr = globalAssmblyArr; 
				   }
			 }	
			if(globalLocationLevelId !=1 && globalLocationLevelId !=2 && globalLocationLevelId !=3 && globalLocationLevelId !=4){
				getMandalVillageDetails(0,stateId);
			}
		});
	}
$(document).on("change","#constituencyId",function(){
		var constituencyId=$(this).val();
		if(globalLocationLevelId !=1 && globalLocationLevelId !=2 && globalLocationLevelId !=3 && globalLocationLevelId !=4){
			getMandalVillageDetails(constituencyId,globalStateId);
		}
	});
function getMandalVillageDetails(constituencyId,stateId){
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
			  if(globalLocationLevelId >= 5){
			   globalLocationLevelValueArr =[];
			   globalLocationLevelValueArr = globalMandalTownDivArr; 
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
	var districtId = $("#districtId").val();
	var constituencyId = $("#constituencyId").val();
	var manTowDivId = $("#manTowDivId").val();
	 if(districtId != null){
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
				buildCandidatePositionRslt(result,boardId); 
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
}
function buildCandidatePositionRslt(result,boardId){
var str = '';
 str+='<div role="tabpane'+boardId+'" class="tab-pane active pad_15" id="board'+boardId+'">';
                	 str+='<h4 class="headingColor text-capital"><u>positions</u></h4>';
                     str+='<p class="text-danger pull-right">Ready for final review</p>';
                     str+='<ul class="positionsUl">';
					 for(var i in result){
						str+='<li>';
                        	str+='<div class="row">';
                            	str+='<div class="col-md-10 col-xs-12 col-sm-10 pad_right0 ">';
                                	str+='<div class="positionsCls modalViewBtn">';
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
