
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
	   }else{
		   var str='';
		str+='<div class="row" style="border:1px solid #ddd;background:#fff">';
			str+='<div class="col-md-4 col-xs-12 col-sm-4 pad_0" style="background:#F9F9F9">';
			  str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="pad_15">';
					str+='<h4 class="headingColor text-capital font_weight font_17">departments</h4>';
				str+='</div>';
				//str+='<h4 class="headingColor text-capital">departments</h4>';
			  str+='</div>';
			  str+='<ul class="nav nav-tabs tabsCustom deptsUlCls" role="tablist" style="margin-top:10px;min-height:400px;border:1px solid #ddd;">';
			  
			    str+='<li  style="margin-left:25px;font-weight:bold;"> No Data available...';
			    str+='</li>';
			    str+='</ul>';
			str+='</div>';
		   $("#departmentsBuildId").html(str);
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
		   if(boards !=null && boards>0){
			   buildDepartmentWiseBoardAndPositionDetails(result,bodyId,depts,boards,deptName,boardName);
		   }else{
			   buildDepartmentWiseBoardAndPositionDetailsForAny(result,bodyId,depts,boards,deptName,boardName);
		   }
	   }
   });
}
function buildAllDeptsAndBoardsByLevel(result,levelId,levelValues)
{

	var str='';
	if(result !=null && result.length>0){
		str+='<div class="row" style="border:1px solid #ddd;background:#fff">';
			str+='<div class="col-md-4 col-xs-12 col-sm-4 pad_0" style="background:#F9F9F9">';
			  str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="pad_15">';
					str+='<h4 class="headingColor text-capital font_weight font_17">departments</h4>';
				str+='</div>';
				//str+='<h4 class="headingColor text-capital">departments</h4>';
			  str+='</div>';
			  str+='<ul class="nav nav-tabs tabsCustom deptsUlCls" role="tablist" style="margin-top:10px;min-height:400px;border:1px solid #ddd;">';
			  
			  var titleStr =" Total Posts - Ready For Final Review";
			  var boardTitleStr =" Total Posts - Ready For Final Review";
			  
			  if(globalStatus == "running"){
				  titleStr =" Total Running Posts";
				  boardTitleStr =" RUNNING ";
			  }
			  if(globalStatus == "notRecieved"){
				  titleStr =" Applications not Received Posts";
			  }
			  else  if(globalStatus == "finalReview"){
				  titleStr ="Ready to final review Posts";
			  }
			  else if(globalStatus == "notYet"){
				  titleStr ="Total Available Posts";
				   boardTitleStr =" ready to shortlist ";
			  }
			  for(var i in result){
				  if(result[i].id !=null && result[i].id>0){
					  if(result[i].availableCount != null){
						
						if(result[i].name!=null && result[i].name.length>35){
							str+='<li role="presentation" style="margin-left: 3px;"><a class="tabShowCls font_13" href="home'+i+'" aria-controls="home'+i+'" role="tab" data-toggle="tab" id="'+result[i].id+'" style="text-transform: uppercase;"><span style="text-transform: uppercase;cursor:pointer" data-toggle="tooltip" data-placement="top" title="'+result[i].name+'">'+result[i].name.substring(0,35)+'...</span><span class="pull-right text-danger depcount" data-toggle="tooltip" data-placement="top" title="'+titleStr+'" >'+result[i].availableCount+'</span></a></li>';
						}else{
							str+='<li role="presentation" style="margin-left: 3px;"><a class="tabShowCls font_13" href="home'+i+'" aria-controls="home'+i+'" role="tab" data-toggle="tab" id="'+result[i].id+'" style="text-transform: uppercase;">'+result[i].name+'<span class="pull-right text-danger depcount" title="'+titleStr+'" data-toggle="tooltip" data-placement="top" >'+result[i].availableCount+'</span></a></li>';
						}
						
					}
					else{
						str+='<li role="presentation" style="margin-left: 3px;"><a class="tabShowCls font_13" href="home'+i+'" aria-controls="home'+i+'" role="tab" data-toggle="tab" id="'+result[i].id+'" style="text-transform: uppercase;">'+result[i].name+'</a></li>';	
					}
					  
				  }
			  }
			  str+='</ul>';
			str+='</div>';
			str+='<div class="col-md-8 col-xs-12 col-sm-8 pad_0 bg_ff">';
				str+='<div class="tab-content">';
				
				 for(var i in result){
					 str+='<div role="tabpanel" class="tab-pane tabShowClsActive  pad_15 " id="home'+i+'">';
					 str+='<h4 class="headingColor text-capital font_weight font_17">'+result[i].name+' department - corporation wise '+boardTitleStr+' positions</h4>';
						str+='<div class="panel-group m_top20" id="accordion'+i+'" role="tablist" aria-multiselectable="true">';
						if(result[i].idnameList !=null && result[i].idnameList.length>0){
							for(var j in result[i].idnameList){
							 str+='<div class="panel panel-default">';
								
								str+='<div class="panel-heading boardWiseDetailsCls panel_heading_color" role="tab" id="headingOne'+i+''+j+'" attr_deptId='+result[i].id+' attr_dept_name="'+result[i].name+'" attr_boardId='+result[i].idnameList[j].id+' attr_board_name="'+result[i].idnameList[j].name+'" attr_id="boardDivBodyId'+i+''+j+'" attr_searchId="boardDivBodySearchId'+i+''+j+'">';
								
									str+='<a role="button" data-toggle="collapse" class="tabCollapseIcon " data-parent="#accordion'+i+''+i+'" href="#collapseOne'+i+''+j+'" aria-expanded="true" aria-controls="collapseOne">';
										str+='<h4 class="panel-title text-capital"  style="text-transform: uppercase;">'+result[i].idnameList[j].name+' ';
										if(globalStatus=="notYet"){
											if(result[i].idnameList[j].availableCount != null && result[i].idnameList[j].availableCount >0)
												str+='  <br><br> <span class="text-danger" title=" Total Available Posts " style="font-weight:bold;cursor:pointer;font-size: 12px;">  <b style="color:green;"> OPEN : </b> '+result[i].idnameList[j].availableCount+' , </span>';
												if(result[i].idnameList[j].applicationsCount != null && result[i].idnameList[j].applicationsCount >0)
											str+='<span class="text-danger " title=" Ready to Shortlist" style="font-weight:bold;cursor:pointer;font-size: 12px;"> <b style="color:orange;"> READY TO SHORTLIST :</b> '+result[i].idnameList[j].applicationsCount+' </span>';
										}
										else{
											//if(result[i].idnameList[j].applicationsCount != null && result[i].idnameList[j].applicationsCount >0)
												//str+=' - <span class="text-danger " title=" Ready to Shortlist" style="font-weight:bold;cursor:pointer;font-size: 12px;"> <b style="color:green;">  FINAL REVIEW :</b> '+result[i].idnameList[j].availableCount+' </span>';
										}
											//str+='<span class="text-danger" title="Total Opened Positions" style="font-weight:bold;cursor:pointer;"> ( '+result[i].idnameList[j].availableCount+' )</span>';
										//if(result[i].idnameList[j].percentage != null && result[i].idnameList[j].percentage !="0.00" && result[i].idnameList[j].percentage !="0")
											//str+='<span class="pull-right"><small class="text-danger">'+result[i].idnameList[j].percentage+'% Ready For Review</small></span>';
										
										str+='</h4>';
									str+='</a>';
								str+='</div>';

								if(globalStatus == "running" && i==0 && j==0){
									str+='<div id="collapseOne'+i+''+j+'" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingOne'+i+''+j+'" aria-expanded="true" >';
								}
								else
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
		$('[data-toggle="tooltip"]').tooltip();
		$(".deptsUlCls li:first-child a").trigger("click");
		setTimeout(function(){
			$("#headingOne00 a").trigger("click");
			//$("#headingOne01 a").trigger("click");
		},500);
		 if(globalStatus == "running")
			$('#headingOne00').trigger('click');
	}else{
		$("#departmentsBuildId").html("<div class='text-center'> No departments data available ... </div>");
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
	
	var boardId=0;
	var board = $(this).attr("attr_boardId");	
	if(board == "null" || board == undefined || board ==""){
		boardId = 0;
	}else{
		boardId = board;
	}
	
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


$(document).on("click","#headingOnes00",function(){

	//var levelId = $(this).attr("attr_levelId");
	//var levelValue = $(this).attr("attr_levelValue");
	var deptId = $(this).attr("attr_deptId");
	
	var boardId=0;
	var board = $(this).attr("attr_boardId");	
	if(board == "null" || board == undefined || board ==""){
		boardId = 0;
	}else{
		boardId = board;
	}	
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
		var isDataAvailable ="false";
		for(var i in result){					
			if(result[i].receivedCount > 0){
			isDataAvailable = "true";
			break;
			}
		}
		if(isDataAvailable == "true" && globalStatus !=null && globalStatus.length>0 &&
		(globalStatus != "notRecieved" && globalStatus != "Total")){
				str+='<table class="table table-bordered tableCollapse text-capital">';
					str+='<thead>';
							str+='<th>Position</th>';					
							str+='<th>Total Posts</th>';					
							str+='<th>Total  Available Posts </th>';//Open Status					
							str+='<th>Total  Received Applications </th>';					
							str+='<th>Ready to Shortlist Applications </th>';
							str+='<th>Shortlisted Applications </th>';					
							str+='<th>Final Review Applications </th>';
							str+='<th>Finalized Applications </th>';
							str+='<th>G.O  Passed Applications</th>';
							str+='<th style="font-weight:bold">Rejected Applications </th>';
							str+='<th style="font-weight:bold">Rejected in Final Review </th>';
							//str+='<th style="font-weight:bold">Rejected in Finalized </th>';

							
					str+='</thead>';
					
				str+='<tbody>';
				for(var i in result){
					
					if(result[i].receivedCount > 0){
					var availablePosts = 0;
					
					var totalPositions=0;
							if(result[i].idNameVoList !=null && result[i].idNameVoList.length>0){
								
								for(var j in result[i].idNameVoList){
										if(result[i].idNameVoList[j].name =="Open"){
											availablePosts = availablePosts+result[i].idNameVoList[j].count;
										}
										
										totalPositions = totalPositions + result[i].idNameVoList[j].count;
										
								}
							}
							
							if(readyForFinalReview>0)
								availablePosts = availablePosts+readyForFinalReview;
					var rdyToShortlist = 0;
					var shortListed = 0;
					var rejected =0;
					var readyForFinalReview= 0;
					var finalized = 0;
					var goPassed = 0;
					var rejectedInFinalRev = 0;
					//var rejectedInFinalized = 0;
					
						if(result[i].distList !=null && result[i].distList.length>0){
								for(var j in result[i].distList){
										if(result[i].distList[j].name =="Applied"){
											rdyToShortlist = rdyToShortlist+result[i].distList[j].count;
										}else if(result[i].distList[j].name =="Rejected" ){
											rejected  = rejected+result[i].distList[j].count;
										}else if(result[i].distList[j].name =="Rejected in Final Review" ){
											rejectedInFinalRev  = rejectedInFinalRev+result[i].distList[j].count;
										}/*else if(result[i].distList[j].name =="Rejected in Finalized" ){
											rejectedInFinalized  = rejectedInFinalized+result[i].distList[j].count;
										}*/else if(result[i].distList[j].name =="Shortlisted"){
											shortListed = shortListed+result[i].distList[j].count;
										}else if(result[i].distList[j].name =="Final Review"){
											readyForFinalReview = readyForFinalReview+result[i].distList[j].count;
										}else if(result[i].distList[j].name =="Confirmed"){
											finalized = finalized+result[i].distList[j].count;
										}else if(result[i].distList[j].name =="GO Passed"){
											goPassed = goPassed+result[i].distList[j].count;
										}
								}
							} 
					
						str+='<tr>';
					
						if(result[i].id != null){
							if(shortListed !=null && shortListed>0 && globalStatus != 'notYet'){
								str+='<td><label class="checkbox-inline"><input type="checkbox" class="positionUpdateCls" id="'+result[i].id+'" attr_shortListed='+shortListed+' attr_btn_id="readytoFinalReviewBtnId'+boards+'" attr_finalReviewCls="" attr_available_posts="'+availablePosts+'" attr_short_listedCount="'+shortListed+'" attr_position_name ="'+result[i].name+'"/>'+result[i].name+'</label></td>';
							}
							 else{
								//str+='<td><label class="checkbox-inline"><input type="checkbox" class="positionUpdateCls" id="'+result[i].id+'" attr_shortListed='+shortListed+' disabled/><span style="cursor:default;" attr_available_posts="'+availablePosts+'" attr_short_listedCount="'+shortListed+'" attr_position_name="'+result[i].name+'">'+result[i].name+'</span></label></td>';
								str+='<td>'+result[i].name+'</td>';
							}					
						}else{
								str+='<td><label>Any Position</td>';
						}
								if(totalPositions >0){
									str+='<td>'+totalPositions+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(availablePosts >0){
									if(availablePosts > totalPositions)
										str+='<td>'+totalPositions+'</td>';
									else
										str+='<td>'+availablePosts+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(result[i].receivedCount >0){
									str+='<td>'+result[i].receivedCount+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(rdyToShortlist>0){
									if(globalStatus != "Total" && globalStatus != "Open" &&  globalStatus != "notRecieved" && globalStatus != "running")
										str+='<td id="shortListPositinId" attr_position_id="'+result[i].id+'" attr_board_id="'+boards+'" attr_dept_name="'+deptName+'" attr_board_name="'+boardName+'" attr_position_name="'+result[i].name+'" attr_dept_id="'+depts+'" style="color:green;font-weight:bold;cursor:pointer;"> <span title="Ready to Shortlist Applications Count">'+rdyToShortlist+'</span></td>';
									else
										str+='<td id="" attr_position_id="'+result[i].id+'" attr_board_id="'+boards+'" attr_dept_id="'+depts+'" >  <span title=" Ready to Shortlist Applications Count">'+rdyToShortlist+'</span></td>';
								}
								else
									str+='<td> - </td>';
								
								if(shortListed >0){
									str+='<td>'+shortListed+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								
								if(readyForFinalReview>0){
									//if(globalStatus != "Total" && globalStatus != "Open" &&  globalStatus != "notRecieved")
									//	str+='<td id="readyTofinalReviewId"  attr_position_id="'+result[i].id+'" attr_board_id="'+boards+'" attr_dept_id="'+depts+'" style="color:green;font-weight:bold;cursor:pointer;">'+readyForFinalReview+'</td>';
									//else
										str+='<td id=""  attr_position_id="'+result[i].id+'" attr_board_id="'+boards+'" attr_dept_id="'+depts+'" >'+readyForFinalReview+'</td>';
								}
								else
									str+='<td> - </td>';
								if(finalized >0){
									str+='<td>'+finalized+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(goPassed >0){
									str+='<td>'+goPassed+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(rejected >0){
									str+='<td>'+rejected+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(rejectedInFinalRev >0){
									str+='<td>'+rejectedInFinalRev+'</td>';
								}else{
									str+='<td> - </td>';
								}
								/*if(rejectedInFinalized >0){
									str+='<td>'+rejectedInFinalized+'</td>';
								}else{
									str+='<td> - </td>';
								}*/
								
								
						str+='</tr>';
					}
				}		
				str+='</tbody>';
				str+='</table>';
		}
		else if(globalStatus !=null && globalStatus.length>0 &&
		(globalStatus == "notRecieved")){
			
				str+='<table class="table table-bordered tableCollapse">';
					str+='<thead>';
							str+='<th>Position</th>';					
							str+='<th>Total Posts<a href="javascript:{}" style="margin-left:10px;" class="btn appleNewPostBtn">Apply For Post</a></th>';					
							
					str+='</thead>';
					
				str+='<tbody>';
				for(var i in result){
					if(result[i].id !=null){
						str+='<tr>';
							str+='<td>'+result[i].name+'</td>';
							if(result[i].count>0){
								str+='<td>'+result[i].count+'</td>';
							}else{
								str+='<td> - </td>';
							}
							
						str+='</tr>';
					}						
				}		
				str+='</tbody>';
				str+='</table>';
			
		}else{
			str+='<table class="table table-bordered tableCollapse text-capital">';
					str+='<thead>';
							str+='<th>Position</th>';					
							str+='<th>Total Posts</th>';					
							str+='<th>Total Available Posts</th>';//Open Status					
							str+='<th>Total Received Applications </th>';					
							str+='<th>Ready to Shortlist Applications</th>';
							str+='<th>Shortlisted Applications</th>';
							str+='<th>Final Review Applications </th>';
							str+='<th>Finalized Applications </th>';
							str+='<th>G.O  Passed Applications</th>';
							str+='<th style="font-weight:bold">Rejected Applications </th>';
							str+='<th style="font-weight:bold">Rejected in Final Review </th>';
							//str+='<th style="font-weight:bold">Rejected in Finalized </th>';							
					str+='</thead>';
					
				str+='<tbody>';
				for(var i in result){
					
					var availablePosts = 0;
					var readyForFinalReview= 0;
					var finalized = 0;
					var goPassed = 0;
					var totalPositions=0;
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
										
										totalPositions = totalPositions + result[i].idNameVoList[j].count;
										
								}
							}
							if(readyForFinalReview>0)
								availablePosts = availablePosts+readyForFinalReview;
					var rdyToShortlist = 0;
					var shortListed = 0;
					var rejected =0;
					var rejectedInFinalRev =0;
					//var rejectedInFinalized =0;
					
						if(result[i].distList !=null && result[i].distList.length>0){
								for(var j in result[i].distList){
										if(result[i].distList[j].name =="Applied"){
											rdyToShortlist = result[i].distList[j].count;
										}else if(result[i].distList[j].name =="Rejected"){
											rejected  = result[i].distList[j].count;
										}else if(result[i].distList[j].name =="Rejected in Final Review" ){
											rejectedInFinalRev  = rejectedInFinalRev+result[i].distList[j].count;
										}/*else if(result[i].distList[j].name =="Rejected in Finalized" ){
											rejectedInFinalized  = rejectedInFinalized+result[i].distList[j].count;
										}*/else if(result[i].distList[j].name =="Shortlisted"){
											shortListed = result[i].distList[j].count;
										}
								}
							} 
					
						
					
						if(result[i].id != null){
							str+='<tr>';							
								str+='<td><label><span style="cursor:default;">'+result[i].name+'</span></label></td>';								
								if(totalPositions >0){
									str+='<td>'+totalPositions+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(availablePosts >0){
									if(availablePosts > totalPositions)
										str+='<td>'+totalPositions+'</td>';
									else
										str+='<td>'+availablePosts+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(result[i].receivedCount >0){
									str+='<td>'+result[i].receivedCount+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(rdyToShortlist>0){
									if(globalStatus != "Total" && globalStatus != "Open" &&  globalStatus != "notRecieved")
										str+='<td id="shortListPositinId" attr_position_id="'+result[i].id+'" attr_board_id="'+boards+'" attr_dept_name="'+deptName+'" attr_board_name="'+boardName+'" attr_position_name="'+result[i].name+'" attr_dept_id="'+depts+'" style="color:green;font-weight:bold;cursor:pointer;"> <span title="Ready to Shortlist Applications count ">'+rdyToShortlist+' </span></td>';
									else
										str+='<td id="" attr_position_id="'+result[i].id+'" attr_board_id="'+boards+'" attr_dept_id="'+depts+'" > <span title="Ready to Shortlist Applications count ">'+rdyToShortlist+' </span></td>';
								}
								else
									str+='<td> - </td>';
								
								if(shortListed >0){
									str+='<td <span title="Shortlisted Applications count "> >'+shortListed+' </span></td>';
								}else{
									str+='<td> - </td>';
								}
								
								
								if(readyForFinalReview>0){
									//if(globalStatus != "Total" && globalStatus != "Open" &&  globalStatus != "notRecieved")
									//	str+='<td id="readyTofinalReviewId"  attr_position_id="'+result[i].id+'" attr_board_id="'+boards+'" attr_dept_id="'+depts+'" style="color:green;font-weight:bold;cursor:pointer;"><span title="Final review Applications count ">'+readyForFinalReview+'</span></td>';
									//else
										str+='<td id=""  attr_position_id="'+result[i].id+'" attr_board_id="'+boards+'" attr_dept_id="'+depts+'" ><span title="Final review Applications count ">'+readyForFinalReview+'</span></td>';
								}
								else
									str+='<td> - </td>';
								if(finalized >0){
									str+='<td><span title="Finalized Applications count ">'+finalized+'</span></td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(goPassed >0){
									str+='<td> <span title="G.O Passed Applications count "> '+goPassed+'</span></td>';
								}else{
									str+='<td> - </td>';
								}
								if(rejected >0){
									str+='<td> <span title="Rejected Applications count ">'+rejected+' </span></td>';
								}else{
									str+='<td> - </td>';
								}
								if(rejectedInFinalRev >0){
									str+='<td> <span title="Rejected in Final review count ">'+rejectedInFinalRev+' </span></td>';
								}else{
									str+='<td> - </td>';
								}
								/*if(rejectedInFinalized >0){
									str+='<td> <span title="Rejected in Finalized count ">'+rejectedInFinalized+' </span></td>';
								}else{
									str+='<td> - </td>';
								}*/
								
							str+='</tr>';								
						}	
						
				}		
				str+='</tbody>';
				str+='</table>';
			
		}

		if(globalStatus !=null && globalStatus.trim().length>0 && (globalStatus != "Total" && globalStatus != "Open" && globalStatus != "notRecieved" && globalStatus != "notYet" )){
			str+='<div class="pad_15">';
				str+='<button id="readytoFinalReviewBtnId'+boards+'" class="btn btn-success moveToFinalReviewCls" role="tab" data-toggle="tab" attr_position_id="'+result[i].id+'" attr_body_id="'+bodyId+'" attr_board_id="'+boards+'" attr_dept_id="'+depts+'" disabled>Ready For Final Review</button>';
				//str+='<span class="pull-right m_top10">Note: Click on count to view Applied candidate profile & Update application status</span>';
				str+='<span id="statusMsgDivId'+depts+''+boards+'" style="color:green;"></span>';
				str+='<span id="updateSearchId'+depts+''+boards+'" style="display:none"><img src="images/search.gif"/></span>';
			str+='</div>';
			//
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
	if(deptId == "null")
		deptId = 0;
	if(boardId == "null")
		boardId = 0;
	if(positionId == "null")
		positionId = 0;
	
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
		
	
	window.location.replace("boardWiseNominatedPostAction.action?lId="+levelId+"&stId="+stateId+"&sts=readyToShortList&deptId="+deptId+"&boardId="+boardId+"&positionId="+positionId+"&searchLevelId="+searchLevelId+"&searchLevelValue="+searchLevelValue+"&deptName="+deptName+"&brdName="+brdName+"&posName="+posName+"&levelTxt="+levelTxt+"&stN="+globalStatus+"");
});


$(document).on("click","#positionLinkCls",function(){
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
	if(deptId == "null")
		deptId = 0;
	if(boardId == "null")
		boardId = 0;
	if(positionId == "null")
		positionId = 0;
	
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
		
	
	window.location.replace("boardWiseNominatedPostAction.action?lId="+levelId+"&stId="+stateId+"&sts=positionLink&deptId="+deptId+"&boardId="+boardId+"&positionId="+positionId+"&searchLevelId="+searchLevelId+"&searchLevelValue="+searchLevelValue+"&deptName="+deptName+"&brdName="+brdName+"&posName="+posName+"&levelTxt="+levelTxt+"&stN="+globalStatus+"");
});

var globalReadyDeptId=0;
var globalReadyBoardId=0;
var globalReadyBodyId='';
$(document).on("click",".moveToFinalReviewCls",function(){
	
	var retVal = confirm("Are you sure want to move post to final review ?");
	if( retVal == true ){
	var districtId=$("#districtId").val();
	var constituencyId=$("#constituencyId").val();
	var mandalTownDivId=$("#manTowDivId").val();
	var stateId=$("#stateId").val();
	//var stateId = 
	var levelId = globalLevelId;
	var searchLevelId = 1;
	var deptId = $(this).attr('attr_dept_id');
	var boardId = $(this).attr('attr_board_id');
	var bodyId = $(this).attr('attr_body_id');
	var positionId = 1;
	//var searchLevelValue =stateId;
		
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
	var sizeOfMember=0;
	$(".positionUpdateCls").each(function(){
		if($(this).is(":checked")){
			var positionId = $(this).attr("id");
			positionArr.push(positionId);
			sizeOfMember = $(this).attr('attr_shortlisted');
		}
	});
	
	
	$("#updateSearchId"+deptId+""+boardId).show();
	var jsObj={		
		deptId :deptId,
		boardId :boardId,
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
	  $("#updateSearchId"+deptId+""+boardId).hide();
	  if(result != null && result.resultCode==0){
		  //alert("Successfully this position Moved to Final Review.");
		  $("#statusMsgDivId"+deptId+""+boardId).html("  Successfully this position Moved to Final Review.");
		  window.location.reload();
		  setTimeout(function(){ 
				getDepartmentWiseBoardAndPositionDetails(globalLevelId,levelValuesArr,deptId,boardId,bodyId,'','','');
			  }, 2000);
		}
	  else if(result != null && result.resultCode==1){
		  $("#statusMsgDivId"+deptId+""+boardId).html("Error Occured while moving this position to Final Review.");
	  }
   });
			return true;
       }
   else{
          return false;
       }
	 
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
	/*var value =$('#membersSelId').val();
	
	if(value ==null || value=="" || value == undefined){
		$("#modelErrId").html("Please Select "+positionName);
		return;
	}
	
	if(value.length>0){
		sizeOfMember = value.length;
	} */
	/* if(sizeOfMember >0 && sizeOfMember>shortListedCount){
		$("#modelErrId").html("Selected Positions Count Should be greater than or equal to Shortlisted ");
		return;
	} */	
	$("#updateSearchId").show();
	$("#modelErrId").html("");
	
	return;
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
		  $("#modelSuccessId").html("  Successfully this position Moved to Final Review.");
		   window.location.reload();
			  setTimeout(function(){ 
				//$("#readyToFinalReviewDiv").modal('hide');
				//globalReadyBodyId
				//getDepartmentWiseBoardAndPositionDetails(globalLevelId,levelValuesArr,globalReadyDeptId,globalReadyBoardId,globalReadyBodyId,'','','');
			  }, 2000);
			  
	  }
	  else if(result != null && result.resultCode==1){
		  $("#modelErrId").html("Error Occured while moving this position to Final Review.");
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
	
	if(globalStatus == "notYet")
		getAnyDeptApplicationOverviewCountLocationWise();
	
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
	   }else{
		
		   var str='';
		str+='<div class="row" style="border:1px solid #ddd;background:#fff">';
			str+='<div class="col-md-4 col-xs-12 col-sm-4 pad_0" style="background:#F9F9F9">';
			  str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<div class="pad_15">';
					str+='<h4 class="headingColor text-capital font_weight font_17">departments</h4>';
				str+='</div>';
				//str+='<h4 class="headingColor text-capital">departments</h4>';
			  str+='</div>';
			  str+='<ul class="nav nav-tabs tabsCustom deptsUlCls" role="tablist" style="margin-top:10px;min-height:400px;border:1px solid #ddd;">';
			  
			    str+='<li  style="margin-left:25px;font-weight:bold;"> No Data available...';
			    str+='</li>';
			    str+='</ul>';
			str+='</div>';
		   $("#departmentsBuildId").html(str);
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
		   if(boards !=null && boards>0){
			   buildDepartmentWiseBoardAndPositionDetails(result,bodyId,depts,boards,deptName,boardName);
		   }else{
			   buildDepartmentWiseBoardAndPositionDetailsForAny(result,bodyId,depts,boards,deptName,boardName);
		   }
	   }
	   
   });
}
	$(document).on("click",".positionUpdateCls",function(){
		var status = $(this).is(":checked");//get current status
		$(".positionUpdateCls").prop("checked", false);//uncheck all
		$(this).prop("checked", status);//change staus for clicked one
		if($(".positionUpdateCls").is(":checked")){
			var btnId = $(this).attr('attr_btn_id');
			$("#"+btnId+'').prop('disabled',false);
		}else{
			var btnId = $(this).attr('attr_btn_id');
			$("#"+btnId+'').prop('disabled',true);
		}
	});
function getAnyDeptApplicationOverviewCountLocationWise(){
	$("#anyDeptCorTblId").html(" ");
	$("#anyPostDivId").show();
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
	}else if(stateId >0){
		searchlevelId = 2;
		searchlevelValue = stateId;
	}else if(stateId ==0){
		searchlevelId = 1;
		searchlevelValue = 1;
	}
		var jsObj={
		departmentId:0,
		boardId:0,
		positionId:0,
		boardLevelId:globalLevelId,
		searchLevelId:searchlevelId,
		locationValue:searchlevelValue,
		statuss:globalStatus		
	}
	$.ajax({
          type:'GET',
          url: 'getAnyDeptApplicationOverviewCountLocationWiseAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	  if(result != null && result.length > 0){
		  buildAnyPostPositionRslt(result);
	  }else{
		$("#anyDeptCorTblId").html("<center>NO DATA AVAILABLE</center>");  
	  }
   });  
}
   function buildAnyPostPositionRslt(result){
	var str='';
	var isDataAvaialable=false;
	for(var i in result){
		if(!isDataAvaialable && result[i].totalApplicationReceivedCnt != null && result[i].totalApplicationReceivedCnt > 0){
			isDataAvaialable=true;
		}
	}
	if(isDataAvaialable){
	    str+='<div class="row" id="anyPostDivId" > ';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		str+='<div class="panel panel-default">';
		str+='<div class="" style="padding: 12px;">';
		str+='<h4 class="text-capital headingColor filterBtnCls font_17" style="cursor:pointer;">';
		str+='any department any board nominated post details<b><span id="anyDeptHeadingId"></span></b>';
		str+='<i class="pull-right glyphicon glyphicon-chevron-down " style="cursor:pointer;font-size:12px;top:-5px;"></i>';
		str+='</h4>';
		str+='</div>';
		str+='<div class="panel-body">';
	 str+='<table class="table table-bordered tableFontSize text-capital">';
		  str+='<thead class="text-capital" style="background-color:#EFF3F4;">';// class="text-capital"
		  str+='<th>Position  </th>';
		  str+='<th>Total Applications Received </th>';
		 // str+='<th>Reports Pending</th>';
		  str+='<th>Position Linked</th>';
		  str+='<th>Ready To Shortlist</th>';
		  str+='<th>Rejected</th>';
		  str+='<th>Shortlisted</th>';
		  str+='<th>FinalReview</th>';
		  str+='<th>Finalized</th>';
	      str+='</thead>';		  
	  str+='<tbody>';
	 
		for(var i in result){
			var readyToShortListedCnt = 0;
			var pstnLnkedAndRjctdCnt = 0;
			var pstnLnkedAndShrtLstdCnt = 0;
			var pstnLnkedAndFinalReview = 0;
			var pstnLnkedAndFinalized = 0;
			
				if(result[i].totalApplicationReceivedCnt != null && result[i].totalApplicationReceivedCnt > 0){
					
					if(result[i].readyToShortListedCnt == 0)
						readyToShortListedCnt = result[i].totalApplicationReceivedCnt;
					else
						readyToShortListedCnt = result[i].readyToShortListedCnt;
					
					if(pstnLnkedAndRjctdCnt>0)
						pstnLnkedAndRjctdCnt = (readyToShortListedCnt - pstnLnkedAndRjctdCnt);
					else
						pstnLnkedAndRjctdCnt = result[i].pstnLnkedAndRjctdCnt;
					
					if(pstnLnkedAndShrtLstdCnt>0)
						pstnLnkedAndShrtLstdCnt = (readyToShortListedCnt - pstnLnkedAndShrtLstdCnt);
					else
						pstnLnkedAndShrtLstdCnt = result[i].pstnLnkedAndShrtLstdCnt;
					
					if(pstnLnkedAndFinalReview>0)
						pstnLnkedAndFinalReview = (readyToShortListedCnt - pstnLnkedAndFinalReview);
					else
						pstnLnkedAndFinalReview = result[i].pstnLnkedAndFinalReview;
					
					if(pstnLnkedAndFinalized>0)
						pstnLnkedAndFinalized = (readyToShortListedCnt - pstnLnkedAndFinalized);
					else
						pstnLnkedAndFinalized = result[i].pstnLnkedAndFinalized;
								
					readyToShortListedCnt = result[i].totalApplicationReceivedCnt - (pstnLnkedAndFinalized+pstnLnkedAndFinalReview+pstnLnkedAndShrtLstdCnt+pstnLnkedAndRjctdCnt);
					
					str+='<tr  style="background-color:#EFF3F4;">';
					if(result[i].name == "Any Post" )
						str+='<td id="'+result[i].id+'"> Any Position  </td>';
					else
						str+='<td id="'+result[i].id+'">'+result[i].name+' </td>';
					/*if(result[i].totalApplicationReceivedCnt != null && result[i].totalApplicationReceivedCnt > 0)
						str+='<td class="anyDeptBrdCls" attr_position_id="'+result[i].id+'" attr_position_name="'+result[i].name+'" style="color:green;font-weight:bold;cursor:pointer;">'+result[i].totalApplicationReceivedCnt+'</td>';
					else*/
					str+='<td>'+result[i].totalApplicationReceivedCnt+'</td>';
					str+='<td>'+result[i].positionLinkedCnt+'</td>';
					
					if(readyToShortListedCnt >0)
						str+='<td class="anyDeptBrdCls" attr_position_id="'+result[i].id+'" attr_position_name="'+result[i].name+'" style="color:green;font-weight:bold;cursor:pointer;"  attr_board_id="0" attr_dept_id="0"  > <u><b>'+readyToShortListedCnt+'</b></u></td>';
					else
						str+='<td>'+readyToShortListedCnt+'</td>';
						str+='<td>'+pstnLnkedAndRjctdCnt+'</td>';					
						str+='<td>'+pstnLnkedAndShrtLstdCnt+'</td>';
						str+='<td>'+pstnLnkedAndFinalReview+'</td>';
						str+='<td>'+pstnLnkedAndFinalized+'</td>';
					str+='</tr>';
				}
			 }
			// str+='<tr  style="background-color:#EFF3F4;"><td colspan="8" style="padding:15px !important;font-style:italic" class="text-capitalize">Note: Click on count to view applied candidate profile & update application status</td></tr>';
str+='<tr  style="background-color:#EFF3F4;"><td colspan="8" style="padding:15px !important;font-style:italic" class="text-capitalize"></td></tr>';
	    str+='</tbody>';	 
		str+='</table>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		$("#anyDeptCorTblId").html(str);
	}/* else{
		$("#anyDeptCorTblId").html("<center>No Data Available</center>");
	} */
}
function buildDepartmentWiseBoardAndPositionDetailsForAny(result,bodyId,depts,boards,deptName,boardName){
	var str='';
	var isDataAvaialable=false;
	for(var i in result){
		if(result[i].totalApplicationReceivedCnt != null && result[i].totalApplicationReceivedCnt > 0){
			isDataAvaialable=true;
		}
	}
	if(isDataAvaialable){
	 str+='<table class="table table-bordered text-capital">';
		  str+='<thead class="text-capital" style="background-color:#eee;font-size:11px">';
		  str+='<td>Position</td>';
		  str+='<td>Total Applications Received </td>';
		 // str+='<td>Reports Pending</td>';
		  str+='<td>Position Linked</td>';
		  str+='<td>Ready To Shortlist</td>';
		  str+='<td>Position Linked & Rejected</td>';
		  str+='<td>Position Linked & Shortlisted</td>';
		  str+='<td>Position Linked & FinalReview</td>';
		  str+='<td>Position Linked & Finalized</td>';
	      str+='</thead>';		  
	  str+='<tbody>';
	  
		for(var i in result){
			
			
			var readyToShortListedCnt = 0;
			var pstnLnkedAndRjctdCnt = 0;
			var pstnLnkedAndShrtLstdCnt = 0;
			var pstnLnkedAndFinalReview = 0;
			var pstnLnkedAndFinalized = 0;
			
				if(result[i].totalApplicationReceivedCnt != null && result[i].totalApplicationReceivedCnt > 0){
					
					if(result[i].readyToShortListedCnt == 0)
						readyToShortListedCnt = result[i].totalApplicationReceivedCnt;
					else
						readyToShortListedCnt = result[i].readyToShortListedCnt;
					
					if(pstnLnkedAndRjctdCnt>0)
						pstnLnkedAndRjctdCnt = (readyToShortListedCnt - pstnLnkedAndRjctdCnt);
					else
						pstnLnkedAndRjctdCnt = result[i].pstnLnkedAndRjctdCnt;
					
					if(pstnLnkedAndShrtLstdCnt>0)
						pstnLnkedAndShrtLstdCnt = (readyToShortListedCnt - pstnLnkedAndShrtLstdCnt);
					else
						pstnLnkedAndShrtLstdCnt = result[i].pstnLnkedAndShrtLstdCnt;
					
					if(pstnLnkedAndFinalReview>0)
						pstnLnkedAndFinalReview = (readyToShortListedCnt - pstnLnkedAndFinalReview);
					else
						pstnLnkedAndFinalReview = result[i].pstnLnkedAndFinalReview;
					
					if(pstnLnkedAndFinalized>0)
						pstnLnkedAndFinalized = (readyToShortListedCnt - pstnLnkedAndFinalized);
					else
						pstnLnkedAndFinalized = result[i].pstnLnkedAndFinalized;
								
					readyToShortListedCnt = result[i].totalApplicationReceivedCnt - (pstnLnkedAndFinalized+pstnLnkedAndFinalReview+pstnLnkedAndShrtLstdCnt+pstnLnkedAndRjctdCnt);
					
					str+='<tr class="bg_ff">';
					if(result[i].name == "Any Post" )
						str+='<td id="'+result[i].id+'"> Any Position  </td>';
					else
						str+='<td id="'+result[i].id+'">'+result[i].name+' </td>';
					/*if(result[i].totalApplicationReceivedCnt != null && result[i].totalApplicationReceivedCnt > 0)
						str+='<td class="anyDeptBrdCls" attr_position_id="'+result[i].id+'" attr_position_name="'+result[i].name+'" style="color:green;font-weight:bold;cursor:pointer;"  attr_board_id="'+boards+'" attr_dept_id="'+depts+'" >'+result[i].totalApplicationReceivedCnt+'</td>';
					else*/
					str+='<td>'+result[i].totalApplicationReceivedCnt+'</td>';
					str+='<td>'+result[i].positionLinkedCnt+'</td>';
					
					if(readyToShortListedCnt >0)
						str+='<td class="anyDeptBrdCls" attr_position_id="'+result[i].id+'" attr_position_name="'+result[i].name+'" style="color:green;font-weight:bold;cursor:pointer;" attr_board_id="'+boards+'" attr_dept_id="'+depts+'" > <u><b>'+readyToShortListedCnt+'</b></u></td>';
					else
						str+='<td>'+readyToShortListedCnt+'</td>';
						str+='<td>'+pstnLnkedAndRjctdCnt+'</td>';					
						str+='<td>'+pstnLnkedAndShrtLstdCnt+'</td>';
						str+='<td>'+pstnLnkedAndFinalReview+'</td>';
						str+='<td>'+pstnLnkedAndFinalized+'</td>';
					str+='</tr>';
				}
			 
			/*
				if(result[i].totalApplicationReceivedCnt){
					str+='<tr class="bg_ff">';
					str+='<td id="'+result[i].id+'">'+result[i].name+'</td>';
					if(result[i].totalApplicationReceivedCnt > 0)
						str+='<td id="positionLinkCls" attr_position_id="'+result[i].id+'" attr_board_id="'+boards+'" attr_dept_name="'+deptName+'" attr_board_name="'+boardName+'" attr_position_name="'+result[i].name+'" attr_dept_id="'+depts+'" style="color:green;font-weight:bold;cursor:pointer;"> '+result[i].totalApplicationReceivedCnt+'  </td>';
					else
						str+='<td> - </td>';
					if(result[i].positionLinkedCnt >0)
						str+='<td>'+result[i].positionLinkedCnt+'</td>';
					else
						str+='<td> - </td>';
					
					if(result[i].readyToShortListedCnt >0)
						str+='<td>'+result[i].readyToShortListedCnt+'</td>';
					else
						str+='<td> - </td>';
					
					if(result[i].pstnLnkedAndRjctdCnt >0)
						str+='<td>'+result[i].pstnLnkedAndRjctdCnt+'</td>';
					else
						str+='<td> - </td>';
					
					if(result[i].pstnLnkedAndShrtLstdCnt >0)
						str+='<td>'+result[i].pstnLnkedAndShrtLstdCnt+'</td>';
					else
						str+='<td> - </td>';
					
					if(result[i].pstnLnkedAndFinalReview >0)
						str+='<td>'+result[i].pstnLnkedAndFinalReview+'</td>';
					else
						str+='<td> - </td>';
					
					if(result[i].pstnLnkedAndFinalized >0)
						str+='<td>'+result[i].pstnLnkedAndFinalized+'</td>';
					else
						str+='<td> - </td>';
					
					str+='</tr>';
				}	*/			
			 }
	    str+='</tbody>';	 
		str+='</table>';
		$("#"+bodyId).html(str);
	}else{
		$("#"+bodyId).html("<center>No Data Available</center>");
	}
}

$(document).on("click",".anyDeptBrdCls",function(){
	
	var districtId=$("#districtId").val();
	var constituencyId=$("#constituencyId").val();
	var mandalTownDivId=$("#manTowDivId").val();
	var stateId = $("#stateId").val();;
	var levelId = globalLevelId;
	var searchLevelId = 1;
	var deptId = 0;
	
	var boardId = 0;
	var positionId = $(this).attr('attr_position_id');
	var deptsId = $(this).attr('attr_dept_id');
	var deptName ="";
	var brdName = "";
	var posName = $(this).attr("attr_position_name");
	var levelTxt = globalLvlTxt;
	
	if(positionId == "null")
		positionId = 0;
	
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
	
	if(deptsId >0)
		deptId = deptsId;
	window.location.replace("boardWiseNominatedPostAction.action?lId="+levelId+"&stId="+stateId+"&sts=readyToShortList&deptId="+deptId+"&boardId="+boardId+"&positionId="+positionId+"&searchLevelId="+searchLevelId+"&searchLevelValue="+searchLevelValue+"&deptName="+deptName+"&brdName="+brdName+"&posName="+posName+"&levelTxt="+levelTxt+"&stN="+globalStatus+"");
});

