
$(document).on("change","#stateId",function(){
	var stateId = $(this).val();
	if(globalLevelId !=1 && globalLevelId !=2){
		getDistrictsForStates(stateId);
	}
});

function getDistrictsForStates(state){
		 
		$("#districtIdImg").show();
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
		    $("#districtIdImg").hide();
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
	   $("#constituencyIdImg").show();
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
		    $("#constituencyIdImg").hide();
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
		$("#manTowDivIdImg").show();
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
		   $("#manTowDivIdImg").hide();
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
	/*$("#departmentsBuildId").html("");
	$("#departmentsBuildSearchId").show();
	var jsObj={
		levelId:levelId,
		levelValues:levelValues,
		statusType:globalStatus,
		task:""
	}
	$.ajax({
          type:'POST',
          url: 'getAllDeptsAndBoardsByLevelAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#departmentsBuildSearchId").hide();
	   if(result != null && result.length > 0){
		   buildAllDeptsAndBoardsByLevel(result,levelId,levelValues);
	   }
   });*/
   
   
	$("#departmentsBuildId").html("");
	$("#departmentsBuildSearchId").show();
	var searchlevelId = parseInt('{lId}');
	var searchlevelValue = parseInt('{stId}');

	var stateId = $("#stateId").val();
	var districtId=$("#districtId").val();
	var constituencyId=$("#constituencyId").val();
	var mandalTownDivId=$("#manTowDivId").val();
	if(mandalTownDivId >0){
		searchlevelId = 5;
		searchlevelValue = mandalTownDivId;
	}else if(constituencyId >0){
		searchlevelId = 4;
		searchlevelValue = constituencyId;
	}else if(districtId >0){
		searchlevelId = 3;
		searchlevelValue = districtId;
	}else if(stateId >=0){
		searchlevelId = 2;
		searchlevelValue = stateId;
	}
	var jsObj={
		levelId:globalLevelId,
		levelValues:[],
		searchlevelId:searchlevelId,
		searchlevelValue:searchlevelValue,
		statusType:globalStatus ,
		task:""
	}
	$.ajax({
          type:'POST',
          url: 'getAllDeptsAndBoardsByLevelAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#departmentsBuildSearchId").hide();
	   if(result != null && result.length > 0){
		   buildAllDeptsAndBoardsByLevel(result,levelId,levelValues);
	   }
   });
   
}
function getDepartmentWiseBoardAndPositionDetails(levelId,levelValues,depts,boards,bodyId,searchId,deptName,boardName){
	
	$("#"+searchId).show();
	  
	var jsObj={
		levelId:levelId,
		levelValues:levelValues,
		depts:depts,
		boards:boards,
		statusType:globalStatus,
		task:""
	}
	$.ajax({
          type:'GET',
          url: 'getDepartmentWiseBoardAndPositionDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#"+searchId).hide();
	   if(result != null && result.length > 0){
		   buildDepartmentWiseBoardAndPositionDetails(result,bodyId,depts,boards,deptName,boardName);
	   }
   });
}
function buildAllDeptsAndBoardsByLevel(result,levelId,levelValues)
{
	var str='';
	if(result !=null && result.length>0){
		str+='<div class="row" style="border:1px solid #ddd;background:#fff">';
			str+='<div class="col-md-3 col-xs-12 col-sm-3 pad_0" style="background:#F9F9F9">';
			  str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="pad_15">';
					str+='<h4 class="headingColor text-capital"><u>departments</u></h4>';
				str+='</div>';
				//str+='<h4 class="headingColor text-capital">departments</h4>';
			  str+='</div>';
			  str+='<ul class="nav nav-tabs tabsCustom deptsUlCls" role="tablist" style="margin-top:10px;min-height:400px;">';
			  for(var i in result){
				if(result[i].availableCount != null)
					str+='<li role="presentation"><a class="tabShowCls" href="home'+i+'" aria-controls="home'+i+'" role="tab" data-toggle="tab" id="'+result[i].id+'" style="text-transform: uppercase;">'+result[i].name+'<span class="pull-right text-danger" title="Total Opened Positions" style="font-weight:bold;cursor:pointer;">'+result[i].availableCount+'</span></a></li>';
				else
					str+='<li role="presentation"><a class="tabShowCls" href="home'+i+'" aria-controls="home'+i+'" role="tab" data-toggle="tab" id="'+result[i].id+'" style="text-transform: uppercase;">'+result[i].name+'<span class="pull-right text-danger" title="Total Opened Positions" style="font-weight:bold;cursor:pointer;"> 0 </span></a></li>';	
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
								
								str+='<div class="panel-heading boardWiseDetailsCls" role="tab" id="headingOne'+i+''+j+'" attr_deptId='+result[i].id+' attr_dept_name="'+result[i].name+'" attr_boardId='+result[i].idnameList[j].id+' attr_board_name="'+result[i].idnameList[j].name+'" attr_id="boardDivBodyId'+i+''+j+'" attr_searchId="boardDivBodySearchId'+i+''+j+'">';
								
									str+='<a role="button" data-toggle="collapse" class="tabCollapseIcon" data-parent="#accordion'+i+''+i+'" href="#collapseOne'+i+''+j+'" aria-expanded="true" aria-controls="collapseOne">';
										str+='<h4 class="panel-title text-capital"  style="text-transform: uppercase;">'+result[i].idnameList[j].name+'';
										if(result[i].idnameList[j].availableCount != null && result[i].idnameList[j].availableCount >0)
											str+='<span class="text-danger" title="Total Opened Positions" style="font-weight:bold;cursor:pointer;"> ( '+result[i].idnameList[j].availableCount+' )</span>';
										else
											str+='<span class="text-danger" title="Total Opened Positions" style="font-weight:bold;cursor:pointer;"> ( '+result[i].idnameList[j].availableCount+' )</span>';
										if(result[i].idnameList[j].percentage != null && result[i].idnameList[j].percentage !="0.00" && 
										 result[i].idnameList[j].percentage !="0")
											str+='<span class="pull-right"><small class="text-danger">'+result[i].idnameList[j].percentage+'% Ready For Review</small></span>';
										
										str+='</h4>';
									str+='</a>';
								str+='</div>';
								str+='<div id="collapseOne'+i+''+j+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+i+''+j+'" >';
								  str+='<div class="panel-body pad_0">';
										str+='<div class="table-responsive"  id="boardDivBodyId'+i+''+j+'">';
											str+='<center><img src="images/Loading-data.gif" id="boardDivBodySearchId'+i+''+j+'" style="display:none;width:50px;height:50px; "/></center>';
										str+='</div>';
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
	var searchId = $(this).attr("attr_searchId"); 
	var deptName = $(this).attr("attr_dept_name");
	var boardName = $(this).attr("attr_board_name");
	
	var levelValuesArr=[];
	if(globalLevelId == 1){
		levelValuesArr.push(1);
	}
	else if(globalLevelId == 2){
		
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
	
	if(globalStatus !=null &&  globalStatus.trim().length>0 && globalStatus == "Total"){
		getDepartmentWiseBoardAndPositionDetailsForAll(globalLevelId,levelValuesArr,deptId,boardId,bodyId,searchId,deptName,boardName);
	}else{
		getDepartmentWiseBoardAndPositionDetails(globalLevelId,levelValuesArr,deptId,boardId,bodyId,searchId,deptName,boardName);
	}
	
});

function buildDepartmentWiseBoardAndPositionDetails(result,bodyId,depts,boards,deptName,boardName){
	var str='';
	
	if(result !=null && result.length>0){
		
		str+='<table class="table table-bordered tableCollapse">';
			str+='<thead>';
					str+='<th>Posts</th>';					
					str+='<th>Total Positions Available</th>';//Open Status					
					str+='<th>Total Applications Received</th>';					
					str+='<th>Ready to Shortlist</th>';
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
			var rdyToShortlist = 0;
			var shortListed = 0;
			var rejected =0;
			
				if(result[i].distList !=null && result[i].distList.length>0){
						for(var j in result[i].distList){
								if(result[i].distList[j].name =="Applied"){
									rdyToShortlist = result[i].distList[j].count;
								}else if(result[i].distList[j].name =="Rejected"){
									rejected  = result[i].distList[j].count;
								}else if(result[i].distList[j].name =="Shortlisted"){
									shortListed = result[i].distList[j].count;
								}
						}
					} 
			
				str+='<tr>';
			
				if(result[i].id != null){
					if(shortListed !=null && shortListed>0){
						str+='<td><label class="checkbox-inline"><input type="checkbox" class="positionUpdateCls" id="'+result[i].id+'" attr_shortListed='+shortListed+' attr_finalReviewCls="" attr_available_posts="'+availablePosts+'" attr_short_listedCount="'+shortListed+'" attr_position_name ="'+result[i].name+'"/>'+result[i].name+'</label></td>';
					}
					 else{
						str+='<td><label class="checkbox-inline"><input type="checkbox" class="positionUpdateCls" id="'+result[i].id+'" attr_shortListed='+shortListed+' disabled/><span style="cursor:default;" attr_available_posts="'+availablePosts+'" attr_short_listedCount="'+shortListed+'" attr_position_name="'+result[i].name+'">'+result[i].name+'</span></label></td>';
					}					
				}else{
						str+='<td><label>Any Post</td>';
				}
						
						str+='<td>'+availablePosts+'</td>';
						str+='<td>'+result[i].receivedCount+'</td>';
						if(rdyToShortlist>0){
							if(globalStatus != "Total" && globalStatus != "Open" &&  globalStatus != "notRecieved")
								str+='<td id="shortListPositinId" attr_position_id="'+result[i].id+'" attr_board_id="'+boards+'" attr_dept_name="'+deptName+'" attr_board_name="'+boardName+'" attr_position_name="'+result[i].name+'" attr_dept_id="'+depts+'" style="color:green;font-weight:bold;cursor:pointer;"> '+rdyToShortlist+'</td>';
							else
								str+='<td id="" attr_position_id="'+result[i].id+'" attr_board_id="'+boards+'" attr_dept_id="'+depts+'" > '+rdyToShortlist+'</td>';
						}
						else
							str+='<td>0</td>';
						
						str+='<td>'+rejected+'</td>';
						str+='<td>'+shortListed+'</td>';
						
						if(readyForFinalReview>0){
							if(globalStatus != "Total" && globalStatus != "Open" &&  globalStatus != "notRecieved")
								str+='<td id="readyTofinalReviewId"  attr_position_id="'+result[i].id+'" attr_board_id="'+boards+'" attr_dept_id="'+depts+'" style="color:green;font-weight:bold;cursor:pointer;">'+readyForFinalReview+'</td>';
							else
								str+='<td id=""  attr_position_id="'+result[i].id+'" attr_board_id="'+boards+'" attr_dept_id="'+depts+'" >'+readyForFinalReview+'</td>';
						}
						else
							str+='<td>0</td>';
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
		if(globalStatus !=null && globalStatus.trim().length>0 && (globalStatus != "Total" && globalStatus != "Open" && globalStatus != "notRecieved")){
			str+='<div class="pad_15">';
				str+='<button class="btn btn-success moveToFinalReviewCls" role="tab" data-toggle="tab" attr_position_id="'+result[i].id+'" attr_board_id="'+boards+'" attr_dept_id="'+depts+'" >Ready For Final Review</button>';
				str+='<span class="pull-right m_top10">Note: Click on count to view Applied candidate profile & Update application status</span>';
			str+='</div>';
		}
		
		$("#"+bodyId).html(str);
	
	}	
}


$(document).on("click","#readyTofinalReviewId",function(){
	var districtId=$("#districtId").val();
	var constituencyId=$("#constituencyId").val();
	var mandalTownDivId=$("#manTowDivId").val();
	var stateId = $("#stateId").val();
	var levelId = globalLevelId;
	var searchLevelId = 1;
	var deptId = $(this).attr('attr_dept_id');
	var boardId = $(this).attr('attr_board_id');
	var positionId = $(this).attr('attr_position_id');

	var searchLevelValue =stateId;
	if(stateId == 0){
		searchLevelValue =1;
		searchLevelId=1;
	}		
	if(mandalTownDivId >0){
		searchLevelValue = mandalTownDivId;
		searchLevelId= 5;
	}	
	else if(constituencyId >0){
		searchLevelValue = constituencyId;
		searchLevelId=4;
	}		
	else if(districtId >0){
		searchLevelValue = districtId;
		searchLevelId=3;
	}
	else if(stateId > 0){
		searchLevelValue = stateId;
		searchLevelId=2;
	}	
	
	window.open("nominatedReadyToFinalReviewAction.action?lId="+levelId+"&stId="+stateId+"&sts=readyToShortList&deptId="+deptId+"&boardId="+boardId+"&positionId="+positionId+"&searchLevelId="+searchLevelId+"&searchLevelValue="+searchLevelValue+"");
});

$(document).on("click","#shortListPositinId",function(){
	var districtId=$("#districtId").val();
	var constituencyId=$("#constituencyId").val();
	var mandalTownDivId=$("#manTowDivId").val();
	var stateId = $("#stateId").val();;
	var levelId = globalLevelId;
	var searchLevelId = 1;
	var deptId = $(this).attr('attr_dept_id');
	var boardId = $(this).attr('attr_board_id');
	var positionId = $(this).attr('attr_position_id');
	var deptName = $(this).attr("attr_dept_name");
	var brdName = $(this).attr("attr_board_name");
	var posName = $(this).attr("attr_position_name");
	var levelTxt = globalLvlTxt;

	var searchLevelValue = stateId;
	if(stateId >= 0){
		searchLevelValue = stateId;
		searchLevelId=2;
	}		
	if(mandalTownDivId >0){
		searchLevelValue = mandalTownDivId;
		searchLevelId= 5;
	}	
	else if(constituencyId >0){
		searchLevelValue = constituencyId;
		searchLevelId=4;
	}		
	else if(districtId >0){
		searchLevelValue = districtId;
		searchLevelId=3;
	}
		
	
	window.open("boardWiseNominatedPostAction.action?lId="+levelId+"&stId="+stateId+"&sts=readyToShortList&deptId="+deptId+"&boardId="+boardId+"&positionId="+positionId+"&searchLevelId="+searchLevelId+"&searchLevelValue="+searchLevelValue+"&deptName="+deptName+"&brdName="+brdName+"&posName="+posName+"&levelTxt="+levelTxt+"");
});

var globalReadyDeptId=0;
var globalReadyBoardId=0;
$(document).on("click",".moveToFinalReviewCls",function(){
	var districtId=$("#districtId").val();
	var constituencyId=$("#constituencyId").val();
	var mandalTownDivId=$("#manTowDivId").val();
	var stateId=$("#stateId").val();
	//var stateId = 
	var levelId = globalLevelId;
	var searchLevelId = 1;
	globalReadyDeptId = $(this).attr('attr_dept_id');
	globalReadyBoardId = $(this).attr('attr_board_id');
	//var searchLevelValue =stateId;
	
	
	var availableCount = 0;
	var shortListedCount=0;
	var positionName='';
	
		$("#readyToFinalReviewDiv").modal('show');
		
		$("#modelErrId").html("");
		
		$('.positionUpdateCls').each(function(){
		if($(this).is(":checked")){
			availableCount = $(this).attr('attr_available_posts');
			shortListedCount=$(this).attr('attr_short_listedcount');
			positionName=$(this).attr('attr_position_name');
			var str='';
			str+='<select attr_member_class_name="moveToFinalReviewCls" id="membersSelId" class="chosenSelect" multiple attr_no="">';
			for(var i=0;i<availableCount;i++){
				str+='<option value="'+(i+1)+'">Member '+(i+1) +'</option>';
			}
			str+='</select>';
			
			$("#readyToFinalRevieId").html(str);		
			$("#membersSelId").chosen();

			return;			
		}		
	});
	
	
	var levelValuesArr=[];

	if(globalLevelId == 1){
		levelValuesArr.push(1);
	}
	else if(globalLevelId == 2){
		if(stateId == 0){
			levelValuesArr.push(1);
			levelValuesArr.push(36);
		}else{
			levelValuesArr.push(stateId);		
		}				
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
		if(mandalTownDivId==0){
			levelValuesArr = globalMandalTowDivArr;	
		}else{
			levelValuesArr.push(mandalTownDivId);
		}
	}
	var positionArr = [];
	$(".positionUpdateCls").each(function(){
		var positionId = $(this).attr("id");
		positionArr.push(positionId);
	});
	/* var jsObj={		
		deptId :deptId,
		boardId :boardId,
		positionArr:positionArr,
		statusId:2,
		levelId : globalLevelId,
		levelValuesArr:levelValuesArr
	}
	$.ajax({
          type:'POST',
          url: 'updateNominatedPostStatusDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	  console.log(result);
	  if(result != null && result.resultCode==0){
		  alert("Suucessfully this position Moved to Final Review...");
	  }
	  else if(result != null && result.resultCode==1){
		  alert("Error Occured while moving this position to Final Review");
	  }
   }); */
	
});

$(document).on("click","#readyToFinalRevewBtn",function(){
	var districtId=$("#districtId").val();
	var constituencyId=$("#constituencyId").val();
	var mandalTownDivId=$("#manTowDivId").val();
	var stateId=$("#stateId").val();	
	
	var availableCount = 0;
	var shortListedCount=0;
	var positionName='';
	
		//$("#readyToFinalReviewDiv").modal('show');
	$('.positionUpdateCls').each(function(){
		if($(this).is(":checked")){
			availableCount = $(this).attr('attr_available_posts');
			shortListedCount=$(this).attr('attr_short_listedcount');
			positionName=$(this).attr('attr_position_name');			
		}		
	});
	
	var levelValuesArr=[];

	if(globalLevelId == 1){
		levelValuesArr.push(1);
	}
	else if(globalLevelId == 2){
		if(stateId == 0){
			levelValuesArr.push(1);
			levelValuesArr.push(36);
		}else{
			levelValuesArr.push(stateId);		
		}				
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
		if(mandalTownDivId==0){
			levelValuesArr = globalMandalTowDivArr;	
		}else{
			levelValuesArr.push(mandalTownDivId);
		}
	}
	var positionArr = [];
	$(".positionUpdateCls").each(function(){
		var positionId = $(this).attr("id");
		positionArr.push(positionId);
	});
	
	var sizeOfMember=0;
	var value =$('#membersSelId').val();
	
	if(value ==null || value=="" || value == undefined){
		$("#modelErrId").html("Please Select "+positionName);
		return;
	}
	
	if(value.length>0){
		sizeOfMember = value.length;
	}
	if(sizeOfMember >0 && sizeOfMember>shortListedCount){
		$("#modelErrId").html("Selected Positions Count Should be greater than or equal to Shortlisted ");
		return;
	}	
	$("#updateSearchId").show();
	$("#modelErrId").html("");
	var jsObj={		
		deptId :globalReadyDeptId,
		boardId :globalReadyBoardId,
		positionArr:positionArr,
		statusId:2,
		levelId : globalLevelId,
		levelValuesArr:levelValuesArr,
		sizeOfMember:sizeOfMember
	}
	
	$.ajax({
          type:'POST',
          url: 'updateNominatedPostStatusDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#updateSearchId").hide();
	   
	  if(result != null && result.resultCode==0){
		  $("#modelSuccessId").html("Suucessfully this position Moved to Final Review...");
		  setTimeout(function(){ 
			$("#readyToFinalReviewDiv").modal('hide');
		  }, 2000);
	  }
	  else if(result != null && result.resultCode==1){
		  $("#modelSuccessId").html("Error Occured while moving this position to Final Review");
	  }
   });
	
});

$(document).on("click","#locationWiseDataId",function(){
	var stateId = $("#stateId").val();
	var districtId=$("#districtId").val();
	var constituencyId=$("#constituencyId").val();
	var mandalTownDivId=$("#manTowDivId").val();

	var levelValuesArr=[];

	if(globalLevelId == 1){
		levelValuesArr.push(1);
	}
	else if(globalLevelId == 2){
		if(stateId == 0){
			levelValuesArr.push(1);
			levelValuesArr.push(36);
		}else{
			levelValuesArr.push(stateId);		
		}				
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
		if(mandalTownDivId==0){
			levelValuesArr = globalMandalTowDivArr;	
		}else{
			levelValuesArr.push(mandalTownDivId);
		}
	}
	if(globalStatus !=null && globalStatus.length>0 && globalStatus == "Total"){
		getAllDeptsAndBoardsByLevelForAll(globalLevelId,levelValuesArr);
	}else{
		getAllDeptsAndBoardsByLevel(globalLevelId,levelValuesArr);
	}
	
	
});

function getAllDeptsAndBoardsByLevelForAll(levelId,levelValues){

	$("#departmentsBuildId").html("");
	$("#departmentsBuildSearchId").show();
	var searchlevelId = parseInt('{lId}');
	var searchlevelValue = parseInt('{stId}');

	var stateId = $("#stateId").val();
	var districtId=$("#districtId").val();
	var constituencyId=$("#constituencyId").val();
	var mandalTownDivId=$("#manTowDivId").val();
	if(mandalTownDivId >0){
		searchlevelId = 5;
		searchlevelValue = mandalTownDivId;
	}else if(constituencyId >0){
		searchlevelId = 4;
		searchlevelValue = constituencyId;
	}else if(districtId >0){
		searchlevelId = 3;
		searchlevelValue = districtId;
	}else if(stateId >=0){
		searchlevelId = 2;
		searchlevelValue = stateId;
	}
	var jsObj={
		levelId:globalLevelId,
		levelValues:[],
		searchlevelId:searchlevelId,
		searchlevelValue:searchlevelValue,
		statusType:globalStatus ,
		task:"Total"
	}
	$.ajax({
          type:'POST',
          url: 'getAllDeptsAndBoardsByLevelAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#departmentsBuildSearchId").hide();
	   if(result != null && result.length > 0){
		   buildAllDeptsAndBoardsByLevel(result,levelId,levelValues);
	   }
   });
}
function getDepartmentWiseBoardAndPositionDetailsForAll(levelId,levelValues,depts,boards,bodyId,searchId,deptName,boardName){
	
	$("#"+searchId).show();
	
	var jsObj={
		levelId:levelId,
		levelValues:levelValues,
		depts:depts,
		boards:boards,
		statusType:globalStatus,
		task:"Total"
	}
	$.ajax({
          type:'GET',
          url: 'getDepartmentWiseBoardAndPositionDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#"+searchId).hide();
	   if(result != null && result.length > 0){
		   buildDepartmentWiseBoardAndPositionDetails(result,bodyId,depts,boards,deptName,boardName);
	   }
   });
}
	$(document).on("click",".positionUpdateCls",function(){
		var status = $(this).is(":checked");//get current status
		$(".positionUpdateCls").prop("checked", false);//uncheck all
		$(this).prop("checked", status);//change staus for clicked one
	});
