
$(document).on("change","#stateId",function(){
	var stateId = $(this).val();
	if(globalLevelId !=1 && globalLevelId !=2){
		getDistrictsForStates(stateId);
	}
});

function getDistrictsForStates(state){
		
		$("#districtMainId").show();
		
		$("#districtId  option").remove();
		//$("#districtId").append('<option value="">Select District</option>');
		$("#constituencyId  option").remove();
		<!--$("#constituencyId").append('<option value="">Select Constituency</option>');-->
		$("#manTowDivId  option").remove();
		//$("#manTowDivId").append('<option value="">Select Mandal/Town/Divison</option>');

		grlobalDistrictArr=[];
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
		    $("#districtId").append('<option value=0>ALL</option>');			
			if(result !=null && result.length>0){
				for(var i in result){
					$("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
					grlobalDistrictArr.push(result[i].id);
				}
			}
			if(globalLevelId !=1 && globalLevelId !=2 && globalLevelId !=3){
				getConstituenciesForDistricts(0,state);
			}
			
	   });
	  
}

$(document).on("change","#districtId",function(){
	var districtId = $(this).val();
	var stateId = $("#stateId").val();
	if(globalLevelId !=1 && globalLevelId !=2 && globalLevelId !=3){
		getConstituenciesForDistricts(districtId,stateId);
	}
});

function getConstituenciesForDistricts(district,stateId){
	   
	   $("#constituencyMainId").show();
	   
	   $("#manTowDivId  option").remove();
		//$("#manTowDivId").append('<option value="">Select Mandal/Town/Divison</option>');		
	   
		$("#constituencyId  option").remove();
		//$("#constituencyId").append('<option value="">Select Constituency</option>');
		//var stateId = $("#statesDivId").val();
		
		var distArrTemp = [];
		if(district==0){
			distArrTemp = grlobalDistrictArr;
		}else{
			distArrTemp.push(district);
		}
		 $("#searchDataImgForcons").show();
		 
		 globalAssmblyArr=[];
		 
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
		   $("#searchDataImgForcons").hide();
		   $("#constituencyId").append('<option value=0>ALL</option>');
			 if(result !=null && result.length>0){
				 for(var i in result){			  
							$("#constituencyId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
							globalAssmblyArr.push(result[i].locationId);
				   }
			 }	

			if(globalLevelId !=1 && globalLevelId !=2 && globalLevelId !=3 && globalLevelId !=4){
				getMandalVillageDetails(0,stateId);
			}
			 
		});
	}
	
	
	$(document).on("change","#constituencyId",function(){
		var constituencyId = $(this).val();
		var stateId = $("#stateId").val();
		if(globalLevelId !=1 && globalLevelId !=2 && globalLevelId !=3 && globalLevelId !=4){
			getMandalVillageDetails(constituencyId,stateId);
		}
	});

	
	
	
	function getMandalVillageDetails(constituencyId,stateId){
		
		$("#manTowDivMainId").show();
	
	/* 	var stateId = $("#statesDivId").val();*/
		var districtId = $("#districtId").val();
		//var constituencyId = $("#constituencyId").val(); 
		var distArrTemp = [];
		if(districtId==0){
			distArrTemp = grlobalDistrictArr;
		}else{
			distArrTemp.push(districtId);
		}
		
		if(globalLevelId==5){
			$("#manTowDivId").html("");
		}
		
		var assmblyArrTemp = [];
		if(constituencyId==0){
			assmblyArrTemp = globalAssmblyArr;	
		}else{
			assmblyArrTemp.push(constituencyId);
		}
		
		globalMandalTowDivArr=[];
		
	   var jsObj={				
			stateId : stateId,
			districtId : distArrTemp,
			constituencyId : assmblyArrTemp,//228
			//mandalId : mandalId,
			//locationLevel : locationLevel,
			locationLevel : 5,
			task:""
		}
		$.ajax({
			  type:'GET',
			  url: 'getSubLevelForConstituencyAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
			//$("#manTowDivId").append("<option value=''>Select Mandal/Town/Divison</option>");
			$("#manTowDivId").append("<option value='0'>ALL</option>");			  
			for(var i in result){
				$("#manTowDivId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				globalMandalTowDivArr.push(result[i].locationId);
			}
		});
	}

function getAllDeptsAndBoardsByLevel(levelId,levelValues){
	/* var levelId=4;
	var levelValues = 299; */
	var jsObj={
		levelId:levelId,
		levelValues:levelValues
	}
	$.ajax({
          type:'GET',
          url: 'getAllDeptsAndBoardsByLevelAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null && result.length > 0){
		   buildAllDeptsAndBoardsByLevel(result,levelId,levelValues);
	   }
   });
}
function getDepartmentWiseBoardAndPositionDetails(levelId,levelValues,depts,boards,bodyId){
	var jsObj={
		levelId:levelId,
		levelValues:levelValues,
		depts:depts,
		boards:boards
	}
	$.ajax({
          type:'GET',
          url: 'getDepartmentWiseBoardAndPositionDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null && result.length > 0){
		   buildDepartmentWiseBoardAndPositionDetails(result,bodyId);
	   }
   });
}
function buildAllDeptsAndBoardsByLevel(result,levelId,levelValues)
{
	var str='';
	if(result !=null && result.length>0){
		str+='<div class="row" style="border:1px solid #ddd;">';
			str+='<div class="col-md-3 col-xs-12 col-sm-3 pad_0" style="background:#F9F9F9">';
			
			  str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="pad_15">';
					str+='<h4 class="headingColor text-capital"><u>departments</u></h4>';
				str+='</div>';
				//str+='<h4 class="headingColor text-capital">departments</h4>';
			  str+='</div>';
			  str+='<ul class="nav nav-tabs tabsCustom deptsUlCls" role="tablist" style="margin-top:10px">';
			  for(var i in result){
				  str+='<li role="presentation"><a class="tabShowCls" href="home'+i+'" aria-controls="home'+i+'" role="tab" data-toggle="tab" id="'+result[i].id+'">'+result[i].name+'</a></li>';
			  }
			  str+='</ul>';
			str+='</div>';
			str+='<div class="col-md-9 col-xs-12 col-sm-9 pad_0 bg_ff">';
				str+='<div class="tab-content">';
				 for(var i in result){
					 str+='<div role="tabpanel" class="tab-pane tabShowClsActive  pad_15" id="home'+i+'">';
						str+='<div class="panel-group" id="accordion'+i+'" role="tablist" aria-multiselectable="true">';
						if(result[i].idnameList !=null && result[i].idnameList.length>0){
							for(var j in result[i].idnameList){
							 str+='<div class="panel panel-default">';
								/* str+='<div class="panel-heading boardWiseDetailsCls" role="tab" id="headingOne'+i+''+j+'" attr_levelId='+levelId+' attr_levelValue='+levelValues+' attr_deptId='+result[i].id+' attr_boardId='+result[i].idnameList[j].id+' attr_id="boardDivBodyId'+i+''+j+'">'; */
								
								str+='<div class="panel-heading boardWiseDetailsCls" role="tab" id="headingOne'+i+''+j+'" attr_deptId='+result[i].id+' attr_boardId='+result[i].idnameList[j].id+' attr_id="boardDivBodyId'+i+''+j+'">';
								
									str+='<a role="button" data-toggle="collapse" class="tabCollapseIcon" data-parent="#accordion'+i+''+i+'" href="#collapseOne'+i+''+j+'" aria-expanded="true" aria-controls="collapseOne">';
										str+='<h4 class="panel-title text-capital">'+result[i].idnameList[j].name+'';
										  str+='<span class="pull-right"><small class="text-danger"></small></span>';
										str+='</h4>';
									str+='</a>';
								str+='</div>';
								str+='<div id="collapseOne'+i+''+j+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+i+''+j+'" >';
								  str+='<div class="panel-body pad_0" id="boardDivBodyId'+i+''+j+'">';
									
								  str+='</div>';
								str+='</div>';
							  str+='</div>';
							
							}
						}
						
						str+='</div>';
					str+='</div>';
					 }
						
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		$("#departmentsBuildId").html(str);
		$(".deptsUlCls li:first-child a").trigger("click")
	}else{
		$("#departmentsBuildId").html("<div>No Data Available</div>");
	}		
	
}
$(document).on("click",".tabShowCls",function(){
	var idValue = $(this).attr("aria-controls")
	$(".tabShowClsActive").removeClass("active");
	$("#"+idValue).addClass("active")
});

$(document).on("click",".boardWiseDetailsCls",function(){
	//var levelId = $(this).attr("attr_levelId");
	//var levelValue = $(this).attr("attr_levelValue");
	var deptId = $(this).attr("attr_deptId");
	var boardId = $(this).attr("attr_boardId");
	var bodyId = $(this).attr("attr_id"); 
	var levelValuesArr=[];
	if(globalLevelId == 2){
		
		levelValuesArr.push(globalStateId);
		
	}else if(globalLevelId == 3){
		var districtId=$("#districtId").val();
		
		if(districtId==0){
			levelValuesArr = grlobalDistrictArr;
		}else{
			levelValuesArr.push(districtId);
		}
		
	}else if(globalLevelId == 4){	
	
		var constituencyId=$("#constituencyId").val();
		
		if(constituencyId==0){
			levelValuesArr = globalAssmblyArr;	
		}else{
			levelValuesArr.push(constituencyId);
		}		
	}else if(globalLevelId == 5){
		var mandalTownDivId=$("#manTowDivId").val();
		if(mandalTownDivId==0){
			levelValuesArr = globalMandalTowDivArr;	
		}else{
			levelValuesArr.push(mandalTownDivId);
		}
	}
	
	
	getDepartmentWiseBoardAndPositionDetails(globalLevelId,levelValuesArr,deptId,boardId,bodyId);
});

function buildDepartmentWiseBoardAndPositionDetails(result,bodyId){
	var str='';
	if(result !=null && result.length>0){
		
		str+='<table class="table table-bordered tableCollapse">';
			str+='<thead>';
					str+='<th>Posts</th>';					
					str+='<th>Total Positions Available</th>';//Open Status					
					str+='<th>Total Applications Received</th>';					
					str+='<th>Reports Pending</th>';
					str+='<th>Rejected</th>';					
					str+='<th>Shortlisted</th>';					
					
					/* if(result[0].distList !=null && result[0].distList.length>0){
							for(var j in result[0].distList){	
									if(result[0].idNameVoList[j].name !="Applied"){
										str+='<td>'+result[0].distList[j].name+'</td>';
									}
							}
					} */
					
					str+='<th>Final Review</th>';
					str+='<th>Finalized</th>';
					str+='<th>G/O Passed/ Position Completed</th>';
					
					
					/* if(result!=null &&result[0].idNameVoList !=null && result[0].idNameVoList.length>0){
						for(var j in result[0].idNameVoList){
							if(result[0].idNameVoList[j].name !="Open"){
								str+='<th>'+result[0].idNameVoList[j].name+'</th>';
							}	
						}
												
					}else{
						str+='<th>Final Review</th>';
						str+='<th>Finalized</th>';
						str+='<th>G/O Passed/ Position Completed</th>';
					} */
					
			str+='</thead>';
			
		str+='<tbody>';
		for(var i in result){
			
			var availablePosts = 0;
			var readyForFinalReview= 0;
			var finalized = 0;
			var goPassed = 0;
					if(result[i].idNameVoList !=null && result[i].idNameVoList.length>0){
						for(var j in result[i].idNameVoList){
								if(result[i].idNameVoList[j].name =="Open"){
									availablePosts = result[i].idNameVoList[j].count;
								}else if(result[i].idNameVoList[j].name =="Final Review"){
									readyForFinalReview = result[i].idNameVoList[j].count;
								}else if(result[i].idNameVoList[j].name =="Confirmed"){
									finalized = result[i].idNameVoList[j].count;
								}else if(result[i].idNameVoList[j].name =="GO Issued"){
									goPassed = result[i].idNameVoList[j].count;
								}
						}
					}
			var reportsPending = 0;
			var shortListed = 0;
			var rejected =0;
			
				if(result[i].distList !=null && result[i].distList.length>0){
						for(var j in result[i].distList){
								if(result[i].distList[j].name =="Applied"){
									reportsPending = result[i].distList[j].count;
								}else if(result[i].distList[j].name =="Rejected"){
									rejected  = result[i].distList[j].count;
								}else if(result[i].distList[j].name =="Shortlisted"){
									shortListed = result[i].distList[j].count;
								}
						}
					} 
			
				str+='<tr>';
						str+='<td><label class="checkbox-inline"><input type="checkbox"/>'+result[i].name+'</label></td>';
						str+='<td>'+availablePosts+'</td>';
						str+='<td>'+result[i].receivedCount+'</td>';
						str+='<td>'+reportsPending+'</td>';
						str+='<td>'+rejected+'</td>';
						str+='<td>'+shortListed+'</td>';
						
						str+='<td>'+readyForFinalReview+'</td>';
						str+='<td>'+finalized+'</td>';
						str+='<td>'+goPassed+'</td>';
						
						
						
						/* if(result[i].distList !=null && result[i].distList.length>0){
							for(var j in result[i].distList){	
									if(result[i].idNameVoList[j].name !="Applied"){
										str+='<td>'+result[i].distList[j].count+'</td>';
									}
							}
						}else{
							str+='<td>-</td>';
							str+='<td>-</td>';
						} */
						
						/* if(result[i].idNameVoList !=null && result[i].idNameVoList.length>0){
							for(var j in result[i].idNameVoList){
									if(result[i].idNameVoList[j].name !="Open"){
										str+='<td>'+result[i].idNameVoList[j].count+'</td>';
									}
							}
						}else{
							str+='<td>-</td>';
							str+='<td>-</td>';
							str+='<td>-</td>';
						} */
						
				str+='</tr>';
		}		
		str+='</tbody>';
		str+='</table>';
		str+='<div class="pad_15">';
			str+='<button class="btn btn-success">Ready For Final Review</button>';
			str+='<span class="pull-right m_top10">Note: Click on count to view Applied candidate profile & Update application status</span>';
		str+='</div>';
		$("#"+bodyId).html(str);
	}	
}
$(document).on("click","#locationWiseDataId",function(){
	var districtId=$("#districtId").val();
	var constituencyId=$("#constituencyId").val();
	var mandalTownDivId=$("#manTowDivId").val();
/* 	var stateArrTemp=[];
	var distArrTemp=[];
	var assmblyArrTemp=[]; */
	var levelValuesArr=[];

	if(globalLevelId == 2){		
		levelValuesArr.push(globalStateId);		
	}else if(globalLevelId == 3){
		
		if(districtId==0){
			levelValuesArr = grlobalDistrictArr;
		}else{
			levelValuesArr.push(districtId);
		}
		
	}else if(globalLevelId == 4){		
		if(constituencyId==0){
			levelValuesArr = globalAssmblyArr;	
		}else{
			levelValuesArr.push(constituencyId);
		}		
	}else if(globalLevelId == 5){
		if(constituencyId==0){
			levelValuesArr = globalMandalTowDivArr;	
		}else{
			levelValuesArr.push(constituencyId);
		}
	}
	getAllDeptsAndBoardsByLevel(globalLevelId,levelValuesArr);
});
