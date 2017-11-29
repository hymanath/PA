    var finalGlobalUrl='';
	var boothcommiteType= "tdpCommittee";
     function passRequiredUrl(finalURL){
		  finalGlobalUrl = finalURL; 
	  }	
	function getLevelWiseBasicCommitteesArray(){
		
		var levelWiseBasicCommitteesArray = new Array();
		var jsObj={committeeLevelId:"",basicCommitteeIds:""};
		
		//district level
		var districtBasicCommitteeIds =[];
		$(".districtCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				districtBasicCommitteeIds.push( $(this).val() );
			}
		}); 
		
		$(".villageCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				districtBasicCommitteeIds.push( $(this).val() );
			}
		}); 
		
		var districtCommitteeLevelObject = new Object();
		districtCommitteeLevelObject.committeeLevelId = 11;
		districtCommitteeLevelObject.basicCommitteeIds = districtBasicCommitteeIds;
		levelWiseBasicCommitteesArray.push(districtCommitteeLevelObject);
		
		//mandal/town/division level.
		var mandalBasicCommitteeIds =[];
		$(".mandalCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				mandalBasicCommitteeIds.push( $(this).val() );
			}
		}); 
		
		$(".villageCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				mandalBasicCommitteeIds.push( $(this).val() );
			}
		}); 
		
		var mandalCommitteeLevelObject = new Object();
		mandalCommitteeLevelObject.committeeLevelId = 5;
		mandalCommitteeLevelObject.basicCommitteeIds = mandalBasicCommitteeIds;
		levelWiseBasicCommitteesArray.push(mandalCommitteeLevelObject);
		
		//village/ward level.
		var villageBasicCommitteeIds =[];
		$(".villageCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				villageBasicCommitteeIds.push( $(this).val() );
			}
		}); 
		var villageCommitteeLevelObject = new Object();
		villageCommitteeLevelObject.committeeLevelId = 6;
		villageCommitteeLevelObject.basicCommitteeIds = villageBasicCommitteeIds;
		levelWiseBasicCommitteesArray.push(villageCommitteeLevelObject);
		
		
		return levelWiseBasicCommitteesArray;
	}
	$(document).on("click","#checkAllAffliatedVillagelevelId",function(){
		if($(this).is(':checked')){
		   $('.villageCommitteeAffliatedcheckBoxClass').prop('checked', true);
		}else{
		   $('.villageCommitteeAffliatedcheckBoxClass').prop('checked', false);
		}
	});
	$(document).on("click","#checkAllAffliatedMandallevelId",function(){
		if($(this).is(':checked')){
		   $('.mandalCommitteeAffliatedcheckBoxClass').prop('checked', true);
		}else{
		   $('.mandalCommitteeAffliatedcheckBoxClass').prop('checked', false);
		}
	});
	$(document).on("click","#checkAllAffliatedDistrictlevelId",function(){
		if($(this).is(':checked')){
		   $('.districtCommitteeAffliatedcheckBoxClass').prop('checked', true);
		}else{
		   $('.districtCommitteeAffliatedcheckBoxClass').prop('checked', false);
		}
	});
	
	function getCommitteesBasicCountReport(){
		
		$("#basicCommitteeCountsDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	    var state = globalState;
        var dateString = $("#dateRangeId").val();
		
		var levelWiseBasicCommitteesArray = getLevelWiseBasicCommitteesArray();
		var committeeEnrollmentYearArray = new Array();
		committeeEnrollmentYearArray.push($("#tdpCommitteeYearId").val());
		// alert(888);
		var jsObj ={  userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  state:state,
					  levelWiseBasicCommitteesArray : levelWiseBasicCommitteesArray,
					  dateString : dateString,
					  committeeEnrollmentYearArray :committeeEnrollmentYearArray
					}
		
		$.ajax({
			type : 'POST',
			url : 'getCommitteesBasicCountReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#basicCommitteeCountsDiv").html('');
			buildgetCommitteesBasicCountReport(result);
			
		});
	}
	function getUserTypeWiseCommitteesCompletedCounts(){
		
		var state = globalState;
	    var dateString = $("#dateRangeId").val();
    
		var jsObj ={  
			          activityMemberId : globalActivityMemberId,
					  userTypeId : globalUserTypeId,
			          userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  
					  state:state,
					  basicCommitteeIdsArray : globalBasicCommitteeIdsArray,
					  dateString : dateString
					}
		
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseCommitteesCompletedCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
			
		});
	}
	var globalUserWiseMemberRslt;
	function getUserTypeWiseCommitteesCompletedCounts1(){
		$("#userTypeWiseCommitteesForTopFiveStrongAndPoorDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	   var state = globalState;
       var dateString = $("#dateRangeId").val();
	   var levelWiseBasicCommitteesArray = getLevelWiseBasicCommitteesArray();
	  // alert(1111);
	   var committeeEnrollmentYearArray = new Array();
	   if($("#tdpCommitteeYearId").val() != null)
		   committeeEnrollmentYearArray.push($("#tdpCommitteeYearId").val());
		var jsObj ={  
			          activityMemberId : globalActivityMemberId,
					  userTypeId : globalUserTypeId,
			          userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  state:state,
					  levelWiseBasicCommitteesArray : levelWiseBasicCommitteesArray,
					  dateString : dateString,
					  committeeEnrollmentYearArray :committeeEnrollmentYearArray,
					  commiteType: "tdpCommittee"
 			         
					}
		
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseCommitteesCompletedCountsAction1.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#userTypeWiseCommitteesForTopFiveStrongAndPoorDiv").html('');
			buildgetUserTypeWiseCommitteesCompletedCountsForTopFiveStrongResults(result);
			
			//buildgetUserTypeWiseCommitteesCompletedCountsForTopFivePoorResults(result);
			 globalUserWiseMemberRslt = result;
		});
	}
	function getLevelWiseBasicCommitteesCountReport(){
		
		$('#overviewReportDiv').hide();
		if(globalUserAccessLevelId != null && parseInt(globalUserAccessLevelId) == 2){
			getCommitteeDetailedReport();
			$('#overviewReportDiv').show();
		}
		
		$("#levelWiseBasicCommittees").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var state = globalState;
	   
       var dateString = $("#dateRangeId").val();
	   var levelWiseBasicCommitteesArray = getLevelWiseBasicCommitteesArray();
	   // alert(222);
	   var committeeEnrollmentYearArray = new Array();
		   committeeEnrollmentYearArray.push($("#tdpCommitteeYearId").val());
		var jsObj ={ userAccessLevelId:globalUserAccessLevelId,
					 userAccessLevelValuesArray:globalUserAccessLevelValues,
					 state:state,
					 levelWiseBasicCommitteesArray : levelWiseBasicCommitteesArray,
					 dateString : dateString,
					  committeeEnrollmentYearArray :committeeEnrollmentYearArray,
					  commiteType: "tdpCommittee"
					}
		
		$.ajax({
			type : 'POST',
			url : 'getLevelWiseBasicCommitteesCountReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#levelWiseBasicCommittees").html('');
			buildgetLevelWiseBasicCommitteesCountReport(result);
			
		});
	}
	function getcommitteesPerformanceCohort(tdpCommitteeLevelIdsClickedArray){
		
		
		$("#districtWiseCommitteesReport").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		
		var state = globalState;
		
		var userLocationLevelId = globalUserAccessLevelId;
		var userLocationLevelValuesArray = globalUserAccessLevelValues;
		
		var committeeStatus = 'all';
        var dateString = $("#dateRangeId").val();
		var levelWiseBasicCommitteesArray = getLevelWiseBasicCommitteesArray();
		 //alert(333);
		var committeeEnrollmentYearArray = new Array();
		    committeeEnrollmentYearArray.push($("#tdpCommitteeYearId").val());
		var jsObj ={tdpCommitteeLevelIdsClickedArray:tdpCommitteeLevelIdsClickedArray,
					levelWiseBasicCommitteesArray : levelWiseBasicCommitteesArray,
					committeeStatus:committeeStatus,
					userLocationLevelId:userLocationLevelId,
					userLocationLevelValuesArray:userLocationLevelValuesArray,
					dateString : dateString,
					state:state,
					committeeEnrollmentYearArray:committeeEnrollmentYearArray,
					commiteType: "tdpCommittee"
					}
		
		$.ajax({
			type : 'POST',
			url : 'committeesPerformanceCohortAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#districtWiseCommitteesReport").html('');
			buildCommitteesPerformanceCohort(result);
			
		});
	}
	
	function getAllItsSubUserTypeIdsByParentUserTypeId(){
		  $("#childUserTypeDetailsDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj = { parentUserTypeId : globalUserTypeId }
		$.ajax({
			type : 'POST',
			url : 'getAllItsSubUserTypeIdsByParentUserTypeIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
			 buildgetChildUserTypesByItsParentUserType(result)
			}else{
			 $("#childUserTypeDetailsDiv").html('NO DATA AVAILABLE.');	
			}
		});			 
	}
	
	
	function getSelectedChildUserTypeMembers(childUserTypeIdString,childUserType){
		
	$("#SelectedUserTypeDetailsDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
     var parentActivityMemberId = globalActivityMemberId;
	 
	 var childUserTypeIdsArray = childUserTypeIdString.split(",");
	 
	 var date = $("#dateRangeId").val();
	 var state = globalState;
  	 var levelWiseBasicCommitteesArray = getLevelWiseBasicCommitteesArray();
	 var committeeEnrollmentYearArray = new Array();
		 committeeEnrollmentYearArray.push($("#tdpCommitteeYearId").val());
		  //alert(444);
	  var jsObj ={ 
	               parentActivityMemberId : parentActivityMemberId,
				   childUserTypeIdsArray : childUserTypeIdsArray,
				   dateString : date,
				   state:state,
				   levelWiseBasicCommitteesArray:levelWiseBasicCommitteesArray,
				  committeeEnrollmentYearArray:committeeEnrollmentYearArray,
				  commiteType: "tdpCommittee"
				 }
	  $.ajax({
			type : 'POST',
			url : 'getSelectedChildUserTypeMembersAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#SelectedUserTypeDetailsDiv").html('');
			buildgetSelectedChildUserTypeMembers(result,childUserType);
		});
	}
	function getDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId){
	   $("#"+childActivityMemberId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   var state = globalState
	   
	   var dateString = $('#dateRangeId').val();
	   var levelWiseBasicCommitteesArray = getLevelWiseBasicCommitteesArray();
	   var committeeEnrollmentYearArray = new Array();
		   committeeEnrollmentYearArray.push($("#tdpCommitteeYearId").val());
		  
	   var jsObj ={  activityMemberId : activityMemberId,
			         userTypeId : userTypeId,
					 state:state,
					 levelWiseBasicCommitteesArray : levelWiseBasicCommitteesArray,
 			         dateString :   dateString,
					 committeeEnrollmentYearArray:committeeEnrollmentYearArray,
					 commiteType: "tdpCommittee"
				  }
	   
	   	$.ajax({
			type : 'POST',
			url : 'getDirectChildActivityMemberCommitteeDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#"+childActivityMemberId).html('');
			buildgetDirectChildActivityMemberCommitteeDetails(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId);
		});
	}
	function getTopPoorPerformancecommittees(activityMemberId,selectedMemberName,selectedUserType){
	   $("#topPoorPerformanceDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   var state = globalState;
	  
	   var dateString = $('#dateRangeId').val();
	   var levelWiseBasicCommitteesArray = getLevelWiseBasicCommitteesArray();
	   var committeeEnrollmentYearArray = new Array();
		committeeEnrollmentYearArray.push($("#tdpCommitteeYearId").val());
		
	   var jsObj ={  activityMemberId : activityMemberId,
					 state:state,
					 levelWiseBasicCommitteesArray : levelWiseBasicCommitteesArray,
 			         dateString :   dateString,
					 committeeEnrollmentYearArray:committeeEnrollmentYearArray,
					  commiteType: "tdpCommittee"
				  }
	   
	   	$.ajax({
			type : 'POST',
			url : 'getTopPoorPerformancecommitteesAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 $("#topPoorPerformanceDiv").html('');
			buildgetTopPoorPerformancecommittees(result,selectedMemberName,selectedUserType);
			
		});
	}
	function getTopPoorCommitteeLocations(activityMemberId,selectedMemberName,selectedUserType){
	   $("#topPoorLocationsDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   var state = globalState;
	   
	   var dateString = $('#dateRangeId').val();
	   var levelWiseBasicCommitteesArray = getLevelWiseBasicCommitteesArray();
	    var committeeEnrollmentYearArray = new Array();
		committeeEnrollmentYearArray.push($("#tdpCommitteeYearId").val());
	   var jsObj ={  activityMemberId : activityMemberId,
					 state:state,
					 levelWiseBasicCommitteesArray : levelWiseBasicCommitteesArray,
 			         dateString :   dateString,
					 committeeEnrollmentYearArray:committeeEnrollmentYearArray,
					 commiteType: "tdpCommittee"
				  }
	   
	   	$.ajax({
			type : 'POST',
			url : 'getTopPoorCommitteeLocationsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 $("#topPoorLocationsDiv").html('');
			buildTopPoorCommitteeLocations(result,selectedMemberName,selectedUserType);
			
		});
	}
	function buildgetCommitteesBasicCountReport(result){
		$("#basicCommitteeCountsDiv").html('');
		var str='';
		var locationLevelNameArray =[];
		if(result !=null){
				str+='<ul class="committesBlockUl">';
				
				   //MAIN
				   if(result.mainVO != null){
					 if(result.mainVO == null || result.affliatedVO == null){
						 str+='<li style="width:100%;">';
					 }else{
						 str+='<li>';
					 }  
					
						str+='<h4 class="text-capital bg_49 pad_custom">main committees</h4>';
						
						str+='<table class="table table-condensed">';
						   str+='<tr>';
								str+='<td>';
								if(result.mainVO != null && result.mainVO.totalCount !=null && result.mainVO.totalCount >0){
									str+='<h4 class="responsiveFont">'+result.mainVO.totalCount+'</h4>';
								}else{
									str+='<h4 class="responsiveFont"> - </h4>';
								}
									str+='<p class="text-muted text-capitalize responsiveFont m_top10">total</p>';
								str+='</td>';
								str+='<td>';
								if(result.mainVO.startedCount !=null && result.mainVO.startedCount >0){
									str+='<h4 class="responsiveFont">'+result.mainVO.startedCount+'</h4>';
									str+='<small class="text-danger responsiveFont">'+result.mainVO.startedPerc+'%</small>';
									str+='<p class="text-muted text-capitalize responsiveFont">started</p>';
								}else{
									str+='<h4 class="responsiveFont"> - </h4>';
									str+='<p class="text-muted text-capitalize responsiveFont">started</p>';
								}
								str+='</td>';
								str+='<td>';
								if(result.mainVO.completedCount !=null && result.mainVO.completedCount  >0){
									str+='<h4 class="responsiveFont">'+result.mainVO.completedCount+'</h4>';
									str+='<small class="text-success responsiveFont">'+result.mainVO.completedPerc+'%</small>';
									str+='<p class="text-muted responsiveFont text-capitalize">Completed</p>';
								}else{
									str+='<h4 class="responsiveFont"> - </h4>';
									str+='<p class="text-muted responsiveFont text-capitalize">Completed</p>';
								}
								str+='</td>';
							str+='</tr>';
						str+='</table>';
						if(result.subList != null && result.subList.length >0){
							
							var length = result.subList.length - 1;
								for(var i = length; i >= 0; i--){
									if(result.subList[i].id !=10){
										//str+='<li>';
											var properName = getProperLocationLevelName(result.subList[i].name);
											if( $.inArray(''+properName+'', locationLevelNameArray) == -1){
												locationLevelNameArray.push(properName);
												str+='<h4 class="text-capitalize m_top10">'+properName+' Level</h4>';
											}
											str+='<table class="table table-condensed bg_ED">';
												str+='<tr>';
													str+='<td>';
														str+='<p class="text-muted text-capitalize responsiveFont">Total</p>';
														if(result.subList[i].mainVO.totalCount !=null && result.subList[i].mainVO.totalCount >0){
															str+='<p class="responsiveFont" >'+result.subList[i].mainVO.totalCount+'</p>';
														}else{
																str+='<p> - </p>';
														}
														
												   str+='</td>';
													str+='<td>';
														str+='<p class="text-muted text-capitalize responsiveFont">Started';
														if(result.subList[i].mainVO.startedCount !=null && result.subList[i].mainVO.startedCount >0){
															if(result.subList[i].name == "Village")
																str+='<i  id=\''+'villageAll'+'IdAPstarted\' onClick="getMainCommitteeMembersCount(\''+globalState+'\',\'villageAll\',\'main\','+1+'\,\'started\')" style="cursor:pointer;margin-left:3px" class="glyphicon glyphicon-info-sign"></i></p>';
															if(result.subList[i].name == "Mandal")
																str+='<i style="cursor:pointer;margin-left:3px" id=\''+'mandalAll'+'IdAPstarted\' onClick="getMainCommitteeMembersCount(\''+globalState+'\',\'mandalAll\',\'main\','+1+'\,\'started\')"  class="glyphicon glyphicon-info-sign "></i></p>';
															if(result.subList[i].name == "District")
																str+='<i style="cursor:pointer;margin-left:3px" id=\''+'district'+'IdAPstarted\' onClick="getMainCommitteeMembersCount(\''+globalState+'\',\'district\',\'main\','+1+'\,\'started\')" class="glyphicon glyphicon-info-sign "></i></p>';
															if(result.subList[i].name == "State")
																str+='<i style="cursor:pointer;margin-left:3px" id=\''+'state'+'IdAPstarted\' onClick="getMainCommitteeMembersCount(\''+globalState+'\',\'state\',\'main\','+1+'\,\'started\')" class="glyphicon glyphicon-info-sign "></i></p>';
														
															str+='<p class="responsiveFont">'+result.subList[i].mainVO.startedCount+' <small class="text-danger"> '+result.subList[i].mainVO.startedPerc+'%</small></p>';
														}else{
															str+='</p><p> - </p>';
														}
														
													str+='</td>';
													str+='<td>';
														str+='<p class="text-muted text-capitalize responsiveFont">Completed';
														if(result.subList[i].mainVO.completedCount !=null && result.subList[i].mainVO.completedCount >0){
															if(result.subList[i].name == "Village")
																str+='<i style="cursor:pointer;margin-left:3px" id=\''+'villageAll'+'IdAPcompleted\' onClick="getMainCommitteeMembersCount(\''+globalState+'\',\'villageAll\',\'main\','+1+'\,\'completed\')" class="glyphicon glyphicon-info-sign "></i></p>';
															if(result.subList[i].name == "Mandal")
																 str+='<i style="cursor:pointer;margin-left:3px" id=\''+'mandalAll'+'IdAPcompleted\' onClick="getMainCommitteeMembersCount(\''+globalState+'\',\'mandalAll\',\'main\','+1+'\,\'completed\')" class="glyphicon glyphicon-info-sign "></i></p>';
															 if(result.subList[i].name == "District")
																 str+='<i style="cursor:pointer;margin-left:3px" id=\''+'district'+'IdAPcompleted\' onClick="getMainCommitteeMembersCount(\''+globalState+'\',\'district\',\'main\','+1+'\,\'completed\')"  class="glyphicon glyphicon-info-sign "></i></p>';
															 if(result.subList[i].name == "State")
																 str+='<i style="cursor:pointer;margin-left:3px" id=\''+'state'+'IdAPcompleted\' onClick="getMainCommitteeMembersCount(\''+globalState+'\',\'state\',\'main\','+1+'\,\'completed\')" class="glyphicon glyphicon-info-sign "></i></p>';
														
															str+='<p class="responsiveFont">'+result.subList[i].mainVO.completedCount+'<small class="text-success"> '+result.subList[i].mainVO.completedPerc+'%</small></p>';
														}else{
															str+='</p><p> - </p>';
														}
														
													str+='</td>';
												str+='</tr>';
											str+='</table>';
										//str+='</li>';
										//str+='<li>';
											
										//str+='</li>';
									}
								}
						}
					str+='</li>';
				 }
					//AFFILIATED
					if(result.affliatedVO != null){
					if(globalBasicCommitteeIdsArray.length == 1){
						 str+='<li style="width:100%;">';
					 }else{
						 str+='<li>';
					 }  
						str+='<h4 class="text-capital bg_49 pad_custom">affiliated <span class="hiddenIpad">committees</span></h4>';
						str+='<table class="table table-condensed">';
							str+='<tr>';
							str+='<td>';
								if(result.affliatedVO.totalCount !=null && result.affliatedVO.totalCount >0){
									str+='<h4 class="responsiveFont">'+result.affliatedVO.totalCount+'</h4>';
								}else{
									str+='<h4 class="responsiveFont"> - </h4>';
								}
									str+='<p class="text-muted text-capitalize responsiveFont m_top10">total</p>';
								str+='</td>';
								
								str+='<td>';
								if(result.affliatedVO.startedCount !=null && result.affliatedVO.startedCount  >0){
									str+='<h4 class="responsiveFont">'+result.affliatedVO.startedCount+'</h4>';
									str+='<small class="text-danger responsiveFont">'+result.affliatedVO.startedPerc+'%</small>';
									str+='<h5 class="text-muted text-capitalize responsiveFont">started</h5>';
									
								}else{
									str+='<h4> - </h4>';
									str+='<p class="text-muted text-capitalize responsiveFont">Started</p>';
								}
									
								str+='</td>';
								str+='<td>';
								if(result.affliatedVO.completedCount !=null && result.affliatedVO.completedCount >0){
									str+='<h4 class="responsiveFont">'+result.affliatedVO.completedCount+'</h4>';
									str+='<small class="text-success responsiveFont">'+result.affliatedVO.completedPerc+'%</small>';
									str+='<p class="text-muted text-capitalize responsiveFont">Completed</p>';
									
								}else{
									str+='<h4> - </h4>';
									str+='<p class="text-muted text-capitalize responsiveFont">Completed</p>';
								}
								
								str+='</td>';
						   str+='</tr>';
						str+='</table>';
						if(result.subList != null && result.subList.length >0){
							
							var length = result.subList.length - 1;
								for(var i = length; i >= 0; i--){
									if(result.subList[i].id !=10){
										   var properName = getProperLocationLevelName(result.subList[i].name);
											if( $.inArray(''+properName+'', locationLevelNameArray) == -1){
												locationLevelNameArray.push(properName);
											}
											str+='<h4 class="text-capitalize m_top10">'+properName+' Level</h4>';
										var getWidth = $(document).width();
										if(getWidth < 500){
											
											str+='<table class="table table-condensed bg_ED">';
										}else{
											str+='<table class="table table-condensed bg_ED">';
										}
											
											str+='<tr>';
											str+='<td>';
												str+='<p class="text-muted text-capitalize responsiveFont">Total</p>';
												if(result.subList[i].affliatedVO.totalCount !=null && result.subList[i].affliatedVO.totalCount >0){
													str+='<p class="responsiveFont">'+result.subList[i].affliatedVO.totalCount+'</p>';
												}else{
														str+='<p> - </p>';
												}
												
										   str+='</td>';
												str+='<td>';
													str+='<p class="text-muted text-capitalize responsiveFont">Started';
													if(result.subList[i].affliatedVO.startedCount !=null && result.subList[i].affliatedVO.startedCount >0){
														if(result.subList[i].name == "Village")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'villageAll'+'IdAPAfflstarted\' onClick="getAflCommitteeCount(\''+globalState+'\',\'villageAll\',\'started\')" class="glyphicon glyphicon-info-sign "></i></p>';
														if(result.subList[i].name == "Mandal")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'mandalAll'+'IdAPAfflstarted\' onClick="getAflCommitteeCount(\''+globalState+'\',\'mandalAll\',\'started\')" class="glyphicon glyphicon-info-sign "></i></p>';
														 if(result.subList[i].name == "District")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'district'+'IdAPAfflstarted\' onClick="getAflCommitteeCount(\''+globalState+'\',\'district\',\'started\')"  class="glyphicon glyphicon-info-sign "></i></p>';
														if(result.subList[i].name == "State")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'state'+'IdAPAfflstarted\' onClick="getAflCommitteeCount(\''+globalState+'\',\'state\',\'started\')" class="glyphicon glyphicon-info-sign "></i></p>'; 
													
														str+='<p>'+result.subList[i].affliatedVO.startedCount+' <small class="text-danger"> '+result.subList[i].affliatedVO.startedPerc+'%</small></p>';
													}else{
														str+='</p><p> - </p>';
													}
													
												str+='</td>';
												str+='<td>';
													str+='<p class="text-muted text-capitalize responsiveFont">Completed';
													if(result.subList[i].affliatedVO.completedCount !=null && result.subList[i].affliatedVO.completedCount >0){
														 if(result.subList[i].name == "Village")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'villageAll'+'IdAPAfflcompleted\' onClick="getAflCommitteeCount(\''+globalState+'\',\'villageAll\',\'completed\')"  class="glyphicon glyphicon-info-sign "></i></p>';
														if(result.subList[i].name == "Mandal")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'mandalAll'+'IdAPAfflcompleted\' onClick="getAflCommitteeCount(\''+globalState+'\',\'mandalAll\',\'completed\')"  class="glyphicon glyphicon-info-sign "></i></p>';
														 if(result.subList[i].name == "District")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'district'+'IdAPAfflcompleted\' onClick="getAflCommitteeCount(\''+globalState+'\',\'district\',\'completed\')" class="glyphicon glyphicon-info-sign "></i></p>';
														if(result.subList[i].name == "State")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'state'+'IdAPAfflcompleted\' onClick="getAflCommitteeCount(\''+globalState+'\',\'state\',\'completed\')" class="glyphicon glyphicon-info-sign "></i></p>';
													
														str+='<p>'+result.subList[i].affliatedVO.completedCount+' <small class="text-success"> '+result.subList[i].affliatedVO.completedPerc+'%</small></p>';
													}else{
														str+='</p><p> - </p>';
													}
													
												str+='</td>';
											str+='</tr>';
										str+='</table>';
										}
									}
						}
					str+='</li>';
					
				 }
					
				str+='</ul>';
				$("#basicCommitteeCountsDiv").html(str);
		}else{
			$("#basicCommitteeCountsDiv").html("No Data Available");
		}
		
	}
	
	function getProperLocationLevelName(levelName){
		var properName = "";
		if(levelName == "Village"){
			properName = "Village / Ward ";
		}else if(levelName == "Mandal"){
			properName = "Mandal / Town / Division ";
		}else{
			properName = levelName;
		}
		return properName;
	}
	
	function buildgetLevelWiseBasicCommitteesCountReport(result)
	{
		$("#levelWiseBasicCommittees").html('');
		
		var firstLevelForCohort = '';
		//Building Level Names.
		var locationLevelNameArray =[];
		if(result != null && result.length > 0){
			var str='';
			str+='<ul class="villageWardUl">';
			var length = result.length - 1;
			for(var i = length; i >= 0; i--){
				if(result[i].id !=10){
					if(result[i].subList !=null && result[i].subList.length > 0){
						var properName = getProperLocationLevelName(result[i].name);
						str+='<li class="customLi" attr_committee_level_name="'+properName+'">';
						if( $.inArray(''+properName+'', locationLevelNameArray) == -1){
							locationLevelNameArray.push(properName);
							str+='<h4>'+properName+' Level</h4>';
						}
						//getting name for performance cohort.
						if($.trim(firstLevelForCohort).length <= 0){
						  firstLevelForCohort = properName;	
						}
						
						str+='<div class="scroll-div">';
							str+='<ul class="list-inline best-matched-profile ">';
							for(var j in result[i].subList){
								str+='<li><div id="levelWiseCommittesDetailed'+i+''+j+'" class="chartLi"></div></li>';
							}
							str+='</ul>';
						str+='</div>';
						str+='</li>';
					}
				}					
            }
			str+='</ul>';
		}
	    $("#levelWiseBasicCommittees").html(str);
		
		
	 
		  if(result != null && result.length > 0){
			var length = result.length - 1;
			for(var i = length; i >= 0; i--){
				if(result[i].id !=10){
					if(result[i].subList !=null && result[i].subList.length > 0){
						for(var j in result[i].subList){
							
							var committeeName = result[i].subList[j].name;
							var levelWiseBasicCompletedPercArray = [];
							var levelWiseBasicStartedPercArray = [];
							var levelWiseBasicNotStartedPercArray = [];
							
							levelWiseBasicCompletedPercArray.push(result[i].subList[j].completedPerc);
							levelWiseBasicStartedPercArray.push(result[i].subList[j].startedPerc);
							levelWiseBasicNotStartedPercArray.push(result[i].subList[j].notStartedPerc); 
							
							if( levelWiseBasicCompletedPercArray.length !=0 && levelWiseBasicStartedPercArray.length !=0 && levelWiseBasicNotStartedPercArray.length !=0){
								$(function () {
								$('#levelWiseCommittesDetailed'+i+''+j+'').highcharts({
									colors: ['#0061D0','#7DDF7D','#C53A36'],
									chart: {
										type: 'column',
										
									},
									title: {
										text: committeeName,
										style: {
												fontSize: '16px',
												fontFamily: '"Helvetica Neue",Helvetica,Arial,sans-serif',
												textTransform: "uppercase"
												
										}
									},
									subtitle: {
										text: null
									},
									 xAxis: {
										 min: 0,
										gridLineWidth: 0,
										minorGridLineWidth: 0,
										categories: null,
										labels: {
											enabled: false,
										}
									},
									yAxis: {
										min: 0,
										gridLineWidth: 0,
										minorGridLineWidth: 0,
										title: {
											text: ''
										},
										stackLabels: {
											enabled: true,
											style: {
												fontWeight: 'bold',
												color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
											}
										}
									},
									tooltip: {
										pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b><br/>',
										shared: true
									},
									legend: {
										enabled: true,
										align: 'left'
								
									},
									plotOptions: {
										column: {
											stacking: 'percent',
											dataLabels:{
												enabled: true,
												formatter: function() {
													if (this.y === 0) {
														return null;
													} else {
														return Highcharts.numberFormat(this.percentage,2) + '%';
													}
												}
											},
											
										},
									},
									 series: [{
										name: 'Started',
										data: levelWiseBasicStartedPercArray 
									}, {
										name: 'Completed',
										data: levelWiseBasicCompletedPercArray
									}, {
										name: 'Not Started',
										data: levelWiseBasicNotStartedPercArray
									}]
								});
							});	
							}else{
								$('#levelWiseCommittesDetailed'+i+''+j+'').html("<b>"+committeeName+"</b> (<span style='text-align:center'>No Data Available</span>)");
							}
						}
					}
				}
			}
		
		}else{
			$("#levelWiseBasicCommittees").html("No Data Available");
		}  
		$(".villageWardUl").slick({
			 slide: '.customLi',
			 slidesToShow: 1,
			 slidesToScroll: 1,
			 infinite: false,
			 swipeToSlide:false,
			 swipe:false,
			 touchMove:false
		}); 
		$(".moreBlocksDistrictlevel").show();
		 //calling function for performance cohort.
		var tdpCommitteeLevelIdsClickedArray = [];
		if(firstLevelForCohort.toLowerCase().indexOf("mandal") >= 0){
			tdpCommitteeLevelIdsClickedArray.push(5);
			tdpCommitteeLevelIdsClickedArray.push(7);
			tdpCommitteeLevelIdsClickedArray.push(9);
		}else if(firstLevelForCohort.toLowerCase().indexOf("village") >= 0){
			tdpCommitteeLevelIdsClickedArray.push(6);
			tdpCommitteeLevelIdsClickedArray.push(8);
		}else if(firstLevelForCohort.toLowerCase().indexOf("district") >= 0){
			tdpCommitteeLevelIdsClickedArray.push(11);
		}else if(firstLevelForCohort.toLowerCase().indexOf("state") >= 0){
			tdpCommitteeLevelIdsClickedArray.push(10);
		}
		getcommitteesPerformanceCohort(tdpCommitteeLevelIdsClickedArray); 
		
	}
	
	function buildCommitteesPerformanceCohort(result){
		$("#districtWiseCommitteesReport").html('');
		if(result != null && result.length > 0){
			var str='';
			
			for(var i in result){
				str+=result[i].name;
				str+='<div id="mainCommittees'+i+'" class="chartLiD" style="height:300px" ></div>';
			}
									
		}
		$("#districtWiseCommitteesReport").html(str);
		
		
		
	if(result != null && result.length > 0){
		for(var i in result){
			var districtNamesArray =[];
			var districtWiseCompletedPercArray = [];
			var districtWiseStartedPercArray = [];
			var districtWiseNotStartedPercArray = [];
			if(result[i].subList !=null && result[i].subList.length > 0){
				for(var j in result[i].subList){
						
						if(result[i].subList[j].locationLevelName == "CORPORATION" || result[i].subList[j].locationLevelName == "Mandal" ||  result[i].subList[j].locationLevelName == "MUNCIPALITY"){
							var locationlevelName =  ''+result[i].subList[j].name+' ('+result[i].subList[j].locationLevelName+')';
							
							districtNamesArray.push(locationlevelName);
						}else{
							districtNamesArray.push(result[i].subList[j].name);
						}
						
						
						//if(result[i].subList[j].completedPerc !=null && result[i].subList[j].completedPerc >0){
							districtWiseCompletedPercArray.push(result[i].subList[j].completedPerc);
						//}
						//if(result[i].subList[j].startedPerc !=null && result[i].subList[j].startedPerc >0){
							districtWiseStartedPercArray.push(result[i].subList[j].startedPerc);
						//}
						//if(result[i].subList[j].notStartedPerc !=null && result[i].subList[j].notStartedPerc >0){
							districtWiseNotStartedPercArray.push(result[i].subList[j].notStartedPerc);
						//}
					}
			}
						$(function () {
							$('#mainCommittees'+i+'').highcharts({
								colors: ['#0061D0','#7DDF7D','#C53A36'],
								chart: {
									type: 'column'
								},
								title: {
									text: ''
								},
								xAxis: {
									 min: 0,
										 gridLineWidth: 0,
										 minorGridLineWidth: 0,
										categories: districtNamesArray,
									labels: {
											rotation: -45,
											style: {
												fontSize: '13px',
												fontFamily: 'Verdana, sans-serif'
											}
										}
								},
								yAxis: {
									min: 0,
										   gridLineWidth: 0,
											minorGridLineWidth: 0,
									title: {
										text: ''
									},
									stackLabels: {
										enabled: false,
										style: {
											fontWeight: 'bold',
											color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
										}
									}
								},
								legend: {
									enabled: true,
									/* //align: 'right',
									x: -40,
									y: 30,
									verticalAlign: 'top',
									//y: -32,
									floating: true, */
									backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
									borderColor: '#CCC',
									borderWidth: 1,
									shadow: false
								},
								tooltip: {
									headerFormat: '<b>{point.x}</b><br/>',
									pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b><br/>',
									shared: true
								},
								plotOptions: {
									column: {
										stacking: 'percent',
										dataLabels: {
											enabled: true,
											 formatter: function() {
												if (this.y === 0) {
													return null;
												} else {
													return Highcharts.numberFormat(this.percentage,2);
												}
											}
										  
										}
									}
								},
								series: [{
									name: 'Started',
									data: districtWiseStartedPercArray
								}, {
									name: 'Completed',
									data: districtWiseCompletedPercArray
								}, {
									name: 'Not Started',
									data: districtWiseNotStartedPercArray
								}]
							});
						});
				
			
		}
	}else{
		$("#districtWiseCommitteesReport").html("No Data Available")
	}
		
		$("#districtWiseCommitteesReport").each(function(){
			var scrollengthDiv = $(this).find(".chartLiD").length;
			if(scrollengthDiv >= 4){
				$(".verticalScrollBar").mCustomScrollbar({setHeight:'560px'})
				
			}else{
				$(".verticalScrollBar").css("height","auto");
			
			}
		});
}
	
	function buildgetChildUserTypesByItsParentUserType(result){
		$("#childUserTypeDetailsDiv").html('');
		var str='';
		 str+='<ul class="comparisonSelect">';
		 
		 var firstChildUserTypeIdString;
		 var userType;
		 if(result !=null && result.length >0){
			 firstChildUserTypeIdString = result[0].shortName;
			 userType=result[0].userType;
			 for(var i in result){
				 str+='<li attr_usertypeid="'+result[i].shortName+'" attr_userType=\''+result[i].userType+'\' class="childUserTypeCls">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#childUserTypeDetailsDiv").html(str);
		if(finalGlobalUrl=="committeesMonitoringAction"){
			var lengthSize = result.length;
			firstChildUserTypeIdString = result[lengthSize-1].shortName; 
			userType = result[lengthSize-1].userType;
		 $(".comparisonSelect li:last-child").addClass("active");	
		}else{
		 $(".comparisonSelect li:first-child").addClass("active");	
		}
		getSelectedChildUserTypeMembers(firstChildUserTypeIdString,userType);
		
	}
	
	
	
	function buildgetSelectedChildUserTypeMembers(result,childUserType){
		
		$("#SelectedUserTypeDetailsDiv").html('');
	var str='';
	    var firstActivityMemberId;
		var firstUserTypeId;
		var firstChildActivityMemberId = "directChildActivityMemberDiv";
		var firstuserType;
		var firstUserMemberName;
			firstActivityMemberId = result[0].activityMemberId;
			firstUserTypeId = result[0].userTypeId;
			firstuserType = result[0].userType;
			firstUserMemberName = result[0].name;
	if(childUserType != null && childUserType.trim()=="MLA/CI" || childUserType.trim()=="MLA" || childUserType.trim()=="CONSTITUENCY INCHARGE"){
	     str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed tableHoverLevels" id="committeeMemberDataTblId">';
		 str+='<thead>';
		     str+='<th>Rank</th>';
			 str+='<th>Name</th>';
			 str+='<th>Designation</th>';
			 str+='<th>Location</th>';
			 str+='<th>Total</th>';
			 str+='<th>Yet To Start</th>';
			 str+='<th>Started</th>';
			 str+='<th>Completed</th>';
			 str+='<th>%</th>';
		 str+='</thead>';
		 str+='<tbody>';
		 var rank=1;
		  for(var i in result){
			str+='<tr style="cursor:pointer;" class="compareActivityMemberCls" attr_selectedusertype="'+result[i].userType+'"  attr_id="directChildActivityMemberDiv"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+'>';
			 str+='<td><span class="counts">'+rank+'</span></td>';
			 str+='<td>'+result[i].name+'</td>';
			 str+='<td>'+result[i].userType+'</td>';
			 str+='<td>'+result[i].locationName+'</td>';
			if(result[i].totalCount !=null && result[i].totalCount >0){
				str+='<td>'+result[i].totalCount+'</td>';
			}else{
				str+='<td> - </td>';
			}
			if( result[i].notStartedCount != null && result[i].notStartedCount >0){
					str+='<td>'+result[i].notStartedCount+'</td>';
			}else{
				str+='<td> - </td>';
			} 
			if(result[i].startedCount !=null && result[i].startedCount >0){
				str+='<td>'+result[i].startedCount+'</td>';
			}else{
				str+='<td> - </td>';
			}
			if(result[i].completedCount !=null && result[i].completedCount >0){
				str+='<td>'+result[i].completedCount+'</td>';
			}else{
				str+='<td> - </td>';
			}
			if(result[i].completedPerc !=null && result[i].completedPerc >0){
				str+='<td>'+result[i].completedPerc+'</td>';
			}else{
				str+='<td> - </td>';
			}
			 str+='</tr>';
             rank=rank+1;			 
			}
			str+='</tbody>';
			str+='</table>';
	    $("#SelectedUserTypeDetailsDiv").html(str);
		$("#committeeMemberDataTblId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 5	
		});
		getTopPoorPerformancecommittees(firstActivityMemberId,firstUserMemberName,firstuserType);
		getTopPoorCommitteeLocations(firstActivityMemberId,firstUserMemberName,firstuserType);
	 }else{	
	   if(result !=null && result.length >0){
		str+='<ul class="list-inline slickPanelSliderCommittee">';
		var rankVar =0;
		for(var i in result){
			rankVar =rankVar+1;
			var locationNamevaraiable =result[i].locationName;
			if(i == 0){
				str+='<li  style="cursor:pointer;" class="compareActivityMemberCls panelActiveSlick" attr_id ="directChildActivityMemberDiv" attr_selectedmembername="'+result[i].name+'" attr_selectedusertype="'+result[i].userType+'" attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' >';
			}else{
				
				str+='<li  style="cursor:pointer;" class="compareActivityMemberCls" attr_id ="directChildActivityMemberDiv" attr_selectedmembername="'+result[i].name+'" attr_selectedusertype="'+result[i].userType+'" attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' >';
				
			}
			
			
				str+='<div class="panel panel-default panelSlick">';
					str+='<div class="panel-heading">';
					str+='<h4 class="panel-title"  >'+result[i].name+'</h4>';
						if(result[i].completedPerc !=null && result[i].completedPerc >0){
							str+='<span class="count">'+rankVar+'</span>';
						}else{
							str+='<span class="count"> - </span>';
						}
						
					str+='</div>';
					str+='<div class="panel-body">';
						
						if( locationNamevaraiable.indexOf(',') == -1){
							locationNamevaraiable = locationNamevaraiable.substring(0, locationNamevaraiable.lastIndexOf(" "));
							str+='<h4 class="panel-title"  >'+result[i].userType+' (<b style="font-size: 12px;text-transform: uppercase;">'+locationNamevaraiable+'</b>)</h4>';
						}else{
							str+='<h4 class="panel-title"  >'+result[i].userType+'</h4>';
						}
						str+='<table class="table table-condensed">';
							str+='<thead>';
								str+='<th>Total</th>';
								str+='<th>Started</th>';
								str+='<th>Completed</th>';
								str+='<th>%(<small>completed</small>)</th>';
							str+='</thead>';
							str+='<tr>';
							if(result[i].totalCount !=null && result[i].totalCount >0){
								str+='<td>'+result[i].totalCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].startedCount !=null && result[i].startedCount >0){
								str+='<td>'+result[i].startedCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].completedCount !=null && result[i].completedCount >0){
								str+='<td>'+result[i].completedCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].completedPerc !=null && result[i].completedPerc >0){
								str+='<td>'+result[i].completedPerc+'</td>';
							}else{
								str+='<td> - </td>';
							}
							str+='</tr>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
			str+='</li>';
		
		}
		str+='</ul>';
		
		$("#SelectedUserTypeDetailsDiv").html(str);
			$(".slickPanelSliderCommittee").slick({
				 slide: 'li',
				 slidesToShow: 3,
				 slidesToScroll: 3,
				 infinite: false,
				  responsive: [
					{
					  breakpoint: 1024,
					  settings: {
						slidesToShow: 3,
						slidesToScroll: 3,
						infinite: false,
						dots: false
					  }
					},
					{
					  breakpoint: 800,
					  settings: {
						slidesToShow: 2,
						slidesToScroll: 2
					  }
					},
					{
					  breakpoint: 600,
					  settings: {
						slidesToShow: 1,
						slidesToScroll: 1
					  }
					},
					{
					  breakpoint: 480,
					  settings: {
						slidesToShow: 1,
						slidesToScroll: 1
					  }
					}
					// You can unslick at a given breakpoint now by adding:
					// settings: "unslick"
					// instead of a settings object
				  ]
			});
		getDirectChildActivityMemberCommitteeDetails(firstActivityMemberId,firstUserTypeId,firstUserMemberName,firstuserType,firstChildActivityMemberId);
		getTopPoorPerformancecommittees(firstActivityMemberId,firstUserMemberName,firstuserType);
		getTopPoorCommitteeLocations(firstActivityMemberId,firstUserMemberName,firstuserType);
	}
	}
	/* else{
		$("#SelectedUserTypeDetailsDiv").html("No Data Available");
	} */
	
	}
	
	$(document).on("click",".childUserTypeCls",function(){
		
		if(!$(this).hasClass("active")){
			$("#directChildActivityMemberDiv").html('');
			$("#topPoorPerformanceDiv").html('');
			$("#topPoorLocationsDiv").html(''); 
			var childUserTypeIdString = $(this).attr("attr_usertypeid");
			var childUserType = $(this).attr("attr_userType");
			 getSelectedChildUserTypeMembers(childUserTypeIdString,childUserType);
		}else{
			$("#SelectedUserTypeDetailsDiv").html('');
			$("#directChildActivityMemberDiv").html('');
			$("#topPoorPerformanceDiv").html('');
			$("#topPoorLocationsDiv").html(''); 
		}
		
	});
	
	function buildgetUserTypeWiseCommitteesCompletedCountsForTopFiveStrongResults(result){
		$("#userTypeWiseCommitteesForTopFiveStrongAndPoorDiv").html('');
		
		if(result != null && result.length > 0){
			for(var i in result){
				
				var candidateNameAndCompletedCountArray = [];
				var countVar =0;
				var candidateNameStartedCountArray =[];
				var candidateNameNotStartedCountArray =[];
				var candidateNameArray=[];
				if(result[i] !=null && result[i].length>0){
					for(var j in result[i]){
							candidateNameArray.push(result[i][j].name)
						 var obj1 = {
								name: result[i][j].name,
								y: result[i][j].completedPerc
							};
						var obj2 = {
								name: result[i][j].name,
								y: result[i][j].startedPerc
							};
						var obj3 = {
								name: result[i][j].name,
								y: result[i][j].notStartedPerc
							};
						candidateNameAndCompletedCountArray.push(obj1);
						candidateNameStartedCountArray.push(obj2);
						candidateNameNotStartedCountArray.push(obj3);
						
							
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}
				}
				
					
				if( result[i][j].completedPerc !=0 || result[i][j].startedPerc !=0 || result[i][j].notStartedPerc !=0){
					
					var str='';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					if(result[i][0].userTypeId == 4 || result[i][0].userTypeId == 11){
						str+='<h5 class="text-capital">ORGANIZING SECRETARY / SECRETARY</h5>';
					}else{
						str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';
					}	
					str+='<div id="genSec'+i+'" style="height:170px;"></div>';
					str+='</div>'
					$("#userTypeWiseCommitteesForTopFiveStrongAndPoorDiv").append(str);
					
					$(function () {
						 $("#genSec"+i).highcharts({
							 colors: ['#0061D0','#7DDF7D','#C53A36'],
							chart: {
								type: 'column'
							},
							title: {
								text: ''
							},
							subtitle: {
								text: ''
							},
							xAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								categories: candidateNameArray,
								type: 'category',
								labels: {
											formatter: function() {
												return this.value.toString().substring(0, 10)+'...';
											},
											
										}
								
							},
							yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								title: {
									text: ''
								},
								labels: {
									enabled:false
								}
							},
							legend: {
								enabled: false
							},
							
									
							plotOptions: {
								column: {
									stacking: 'percent',
									dataLabels: {
										enabled: true,
										 formatter: function() {
											if (this.y === 0) {
												return null;
											} else {
												return Highcharts.numberFormat(this.y,1) + '%';
											}
										}
									  
									}
								}
							},

							/* tooltip: {
								headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
								pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}%</b>'
							}, */
							
							 tooltip: {
									formatter: function () {
										var s = '<b>' + this.x + '</b>';
										$.each(this.points, function () {
											s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
												(this.y)+'%';
										});

										return s;
									},
									shared: true
								},
								
								series: [{
									name: 'Started',
									data: candidateNameStartedCountArray
								},{
									name: 'Completed',
									data: candidateNameAndCompletedCountArray
								},{
									name: 'Not Started',
									data: candidateNameNotStartedCountArray
								}],
						 
						});
					});
				} else{
					$("#genSec"+i).html("No Data Available");
					$("#genSec"+i).css("height","35px");
						
				} 
				
			}
			
		}else{
			//$("#userTypeWiseCommitteesForTopFiveStrongAndPoorDiv").html("No Data Available");
			$("#userTypeWiseCommitteesForTopFiveStrongAndPoorDiv").html("");
		}
		
	}
	
	function buildgetUserTypeWiseCommitteesCompletedCountsForTopFivePoorResults(result){
		$("#userTypeWiseCommitteesForTopFiveStrongAndPoorDiv").html('');
		if(result != null && result.length > 0){
			for(var i in result){
				
				var candidateNameAndCompletedCountArray =[];
				var candidateNameStartedCountArray =[];
				var candidateNameNotStartedCountArray =[];
				var candidateNameArray=[];
				var countVar = 0;
				var completedper = 0;
				var strtdPerc = 0;
				var ntStrtedPrc = 0;
				if(result[i] !=null && result[i].length  >0){
					for(var j = result[i].length -1; j >= 0; j--){
						
						completedper = result[i][j].completedPerc;
						strtdPerc = result[i][j].startedPerc;
						ntStrtedPrc = result[i][j].notStartedPerc;
						
						 var obj1 = {
								name: result[i][j].name,
								y: result[i][j].completedPerc
							};
						var obj2 = {
								name: result[i][j].name,
								y: result[i][j].startedPerc
							};
						var obj3 = {
								name: result[i][j].name,
								y: result[i][j].notStartedPerc
							};
						candidateNameAndCompletedCountArray.push(obj1);
						candidateNameStartedCountArray.push(obj2);
						candidateNameNotStartedCountArray.push(obj3);
						
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}
				}
			  		
				if( completedper !=0 || strtdPerc !=0 || ntStrtedPrc !=0){
					var str='';
					
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
						if(result[i][0].userTypeId == 4 || result[i][0].userTypeId == 11){
						str+='<h5 class="text-capital">ORGANIZING SECRETARY / SECRETARY</h5>';
						}else{
							str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';
						}
					str+='<div id="genSec1'+i+'" class="m_top20" style="height:170px;"></div>';
					str+='</div>';
				
					$("#userTypeWiseCommitteesForTopFiveStrongAndPoorDiv").append(str);
					
					var getWidth = $("#genSec1"+i).parent().width()+'px';
					$("#genSec1"+i).width(getWidth);
					$(function () {
						 $("#genSec1"+i).highcharts({
							 colors: ['#0061D0','#7DDF7D','#C53A36'],
							chart: {
								type: 'column'
							},
							title: {
								text: ''
							},
							subtitle: {
								text: ''
							},
							xAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								categories: candidateNameArray,
								type: 'category',
								labels: {
											formatter: function() {
												return this.value.toString().substring(0, 10)+'...';
											},
											
										}
								
							},
							yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								title: {
									text: ''
								}

							},
							legend: {
								enabled: false
							},
							
									
							plotOptions: {
								column: {
									stacking: 'percent',
									dataLabels: {
										enabled: true,
										 formatter: function() {
											if (this.y === 0) {
												return null;
											} else {
												return Highcharts.numberFormat(this.y,1) + '%';
											}
										}
									  
									}
								}
							},

							/* tooltip: {
								headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
								pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}%</b>'
							}, */
							tooltip: {
									formatter: function () {
										var s = '<b>' + this.x + '</b>';
										$.each(this.points, function () {
											s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
												(this.y)+'%';
										});

										return s;
									},
									shared: true
								},
								series: [{
									name: 'Started',
									data: candidateNameStartedCountArray
								},{
									name: 'Completed',
									data: candidateNameAndCompletedCountArray
								},{
									name: 'Not Started',
									data: candidateNameNotStartedCountArray
								}],
						 
						});
					});
				}else{
					$("#genSec1"+i).html("No Data Available");
					$("#genSec1"+i).css("height","35px");
						
				} 
				
			}
			
		}else{
			$("#userTypeWiseCommitteesForTopFiveStrongAndPoorDiv").html("No Data Available");
		}
		
	}
	$(document).on("click",".liCls1",function(){
		var memberType=$(this).attr("attr_value");
		 if(memberType != null && memberType == "strong"){
			buildgetUserTypeWiseCommitteesCompletedCountsForTopFiveStrongResults(globalUserWiseMemberRslt); 
		 }else if(memberType == "poor"){
			buildgetUserTypeWiseCommitteesCompletedCountsForTopFivePoorResults(globalUserWiseMemberRslt)
		 }
	});

	$(document).on("click",".topFivePoorResults",function(){
		$("#userTypeWiseCommitteesForTopFiveStrongDiv").hide();
		$("#userTypeWiseCommitteesForTopFivePoorDiv").show();
		getUserTypeWiseCommitteesCompletedCounts1();
	})
	$(document).on("click",".topFiveStrongResults",function(){
		$("#userTypeWiseCommitteesForTopFiveStrongDiv").show();
		$("#userTypeWiseCommitteesForTopFivePoorDiv").hide();
		getUserTypeWiseCommitteesCompletedCounts1();
	})
	
	
	$(document).on("click",".compareActivityMemberCls",function(){
		//$(".slickPanelSlider").find("li").removeClass("active");
		//$(this).addClass("active");
		$(".slickPanelSliderCommittee").find("li").removeClass("panelActiveSlick");
		$(this).addClass("panelActiveSlick");
		var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
		var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype");  
		var childActivityMemberId = $(this).attr("attr_id");  
		//$(".showChildBlockAndTopPoorBlock").show();
		if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
		  getTopPoorPerformancecommittees(activityMemberId,selectedMemberName,selectedUserType);
		  getTopPoorCommitteeLocations(activityMemberId,selectedMemberName,selectedUserType);
		 }else{ 
	     getDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
		 getTopPoorPerformancecommittees(activityMemberId,selectedMemberName,selectedUserType);
		 getTopPoorCommitteeLocations(activityMemberId,selectedMemberName,selectedUserType);
		}
	})
	function buildgetDirectChildActivityMemberCommitteeDetails(result,selectedMemberName,selectedUserType,childActivityMemberId){
		$("#"+childActivityMemberId).html('');
		var str ='';
		
		if(result != null && result.length >0){
			var rankVar =0;
			str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
			if(childActivityMemberId != "directChildActivityMemberDiv"){
				str+='<span class="removeSelecUserType pull-right" attr_removeSelecUserType = "'+childActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
			}
			
				if(childActivityMemberId != "directChildActivityMemberDiv")
				{
					str+='<table class="table table-condensed tableLevels m_top20">';
				}else{
					str+='<table class="table table-condensed tableHoverLevels m_top20">';
				}
				
					str+='<thead class="bg_D8 text-capital">';
						str+='<th>Rank</th>';
						str+='<th>Designation</th>';
						str+='<th>Name</th>';
						str+='<th>total</th>';
						str+='<th>Yet To Start</th>';
						str+='<th>started</th>';
						str+='<th>completed</th>';
						str+='<th>%</th>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result){
						rankVar = rankVar+1;
						 var locationNamevar = result[i].locationName;
						str+='<tr class="compareLowLevelActivityMemberCls"  attr_activitymemberid = "'+result[i].activityMemberId+'" attr_usertypeid = "'+result[i].userTypeId+'" attr_selectedmembername = "'+result[i].name+'" attr_selectedusertype = "'+result[i].userType+'">';
							str+='<td>';
							str+='<span class="tableCount">'+rankVar+'</span>';	
							str+='</td>';
							if( locationNamevar.indexOf(',') == -1){
								str+='<td>'+result[i].userType+' (<b>'+result[i].locationName+'</b>)</td>';
							}else{
								str+='<td>'+result[i].userType+'</td>';
							}
							if( result[i].name != null && $.trim(result[i].name).length > 0 ){
									str+='<td>'+result[i].name+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if( result[i].totalCount != null && result[i].totalCount >0){
									str+='<td>'+result[i].totalCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if( result[i].notStartedCount != null && result[i].notStartedCount >0){
									str+='<td>'+result[i].notStartedCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if( result[i].startedCount != null && result[i].startedCount >0){
									str+='<td>'+result[i].startedCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if( result[i].completedCount != null && result[i].completedCount >0){
									str+='<td>'+result[i].completedCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if( result[i].completedPerc != null && result[i].completedPerc >0){
									str+='<td>'+result[i].completedPerc+'</td>';
							}else{
								str+='<td> - </td>';
							}
						str+='</tr>';
						
						str+='<tr class="showHideTr" style="display:none" attr_id = "districtpositionId'+result[i].userTypeId+''+i+'">';
							
							str+='<td colspan="8"  id="districtpositionId'+result[i].userTypeId+''+i+'">';
							
							str+='</td>';
						str+='</tr>';
					
			}
			str+='</tbody>';
				str+='</table>';
				$("#"+childActivityMemberId).html(str);
		}else{
			if(childActivityMemberId == "directChildActivityMemberDiv"){
				$("#"+childActivityMemberId).html("<h5><span  class='text-capital'>"+selectedMemberName+"</span> - <span class='text-capitalize'>"+selectedUserType+"</span> - ( No Data Available )</h5>");
			}
			
		}
	
	}
	 
	 $(document).on("click",".compareLowLevelActivityMemberCls",function(){
		 $(this).closest('tr').next('tr.showHideTr').show(); 
		 
		var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
		var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype");  
		var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id");  
		if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
		  getTopPoorPerformancecommittees(activityMemberId,selectedMemberName,selectedUserType);
		  getTopPoorCommitteeLocations(activityMemberId,selectedMemberName,selectedUserType);
		 }else{ 
	     getDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
		 getTopPoorPerformancecommittees(activityMemberId,selectedMemberName,selectedUserType);
		 getTopPoorCommitteeLocations(activityMemberId,selectedMemberName,selectedUserType);
		}
	})
	
	$(document).on("click",".removeSelecUserType",function(){
		 
		// var removeSelected = $(this).attr("attr_removeSelecUserType"); 
		 //$("#"+removeSelected).remove();
		 //$("#topPoorPerformanceDiv").html('');
		// $("#topPoorLocationsDiv").html('');
		  var removeSelected = $(this).attr("attr_removeSelecUserType"); 
		  $("#"+removeSelected).html(' ');
		 $("#"+removeSelected).closest('.showHideTr').hide();
	});
	
	function buildgetTopPoorPerformancecommittees(result,selectedMemberName,selectedUserType){
		$("#topPoorPerformanceDiv").html('');
		var str='';
		 	
		if(result != null && result.subList1 != null && result.subList1.length >0){
			str+='<b><span class="color_333 pad_5 bg_CC text-capital">top five <span class="text-danger">poor</span> performance  committees - (<span style="font-size:11px;"><i> '+selectedMemberName+' - '+selectedUserType+'</i></span>)</span></b>';
			str+='<div class="row m_top20">';
			
			str+='<div class="col-md-6 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0">';
			str+='<p class="text-capital"><b>all levels cumulative</b></p>';
			str+='<table class="table tableCumulative">';
			var rankingvar =0;
			var BGColor = 1;
			var countVar  = 0;
				for(var i in result.subList1){
					//top 5 should build.
					countVar =countVar+1;
					if (countVar === 6) {
						break;
					}
					str+='<tr>';
					rankingvar = rankingvar+1;
				
					str+='<td><span class="count" style="background-color:rgba(237, 29, 38,'+BGColor+')">'+rankingvar+'</span></td>';
					str+='<td>'+result.subList1[i].name+'</td>';
					str+='<td>';
						str+='<div class="progress progressCustom"  data-toggle="tooltip" data-placement="top" title="'+result.subList1[i].completedPerc+'%">';
						if(result.subList1[i].completedCount !=null && result.subList1[i].completedCount >0){
							str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+result.subList1[i].completedCount+'" aria-valuemin="0" aria-valuemax="100" style="width: '+result.subList1[i].completedPerc+'%;">';
							str+='<span class="sr-only">'+result.subList1[i].completedPerc+'% Complete</span>';
							str+='</div>';
							str+='</div>';
							str+='</td>';
							str+='<td class="text-danger">'+result.subList1[i].completedCount+'</td>';
						}else{
							str+='<td class="text-danger"> - </td>';
						}
				str+='</tr>';
				BGColor = BGColor - 0.2;
				}
				
				
			str+='</table>';
		str+='</div>';
		}
		if(result != null && result.subList != null && result.subList.length >0){
			var locationLevelNameArray =[];
			for(var i in result.subList){
				str+='<div class="col-md-6 col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0 m_top10">';
				var properName = getProperLocationLevelName(result.subList[i].name);
					if( $.inArray(''+properName+'', locationLevelNameArray) == -1){
						locationLevelNameArray.push(properName);
						str+='<p class="text-capital"><b>'+properName+'</b></p>';
					}
					var number =0;
					var BGColor = 1;
					var countVar = 0;
				str+='<table class="table tableCumulative">';
					for(var j in result.subList[i].subList){
						//top 5 should build.
						countVar =countVar+1;
						if (countVar === 6) {
							break;
						}
						
						str+='<tr>';
						number = number+1;
						str+='<td><span class="count" style="background-color:rgba(237, 29, 38,'+BGColor+')">'+number+'</span></td>';
						str+='<td>'+result.subList[i].subList[j].name+'</td>';
						str+='<td>';
						if(result.subList[i].subList[j].completedCount !=null && result.subList[i].subList[j].completedCount >0){
							str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+result.subList[i].subList[j].completedPerc+'%">';
							  str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+result.subList[i].subList[j].completedCount+'" aria-valuemin="0" aria-valuemax="100" style="width: '+result.subList[i].subList[j].completedPerc+'%;">';
								str+='<span class="sr-only">'+result.subList[i].subList[j].completedPerc+'% Complete</span>';
							  str+='</div>';
							str+='</div>';
							str+='</td>';
							str+='<td class="text-danger">'+result.subList[i].subList[j].completedCount+'</td>';
						}else{
							str+='<td class="text-danger"> - </td>';
						}
							
					str+='</tr>';
					BGColor = BGColor - 0.2;
					}
				str+='</table>';
				str+='</div>';
			}
		
		}
		str+='</div>';
		$("#topPoorPerformanceDiv").html(str);
		$('.progressCustom').tooltip()
	
		
	}
	
	function buildTopPoorCommitteeLocations(result,selectedMemberName,selectedUserType){
		$("#topPoorLocationsDiv").html('');
		var str ='';
		
		if(result !=null && result.length >0){
			str+='<b><span class="color_333 pad_5 bg_CC text-capital">top five <span class="text-danger">poor</span> locations - (<span style="font-size:11px;"><i> '+selectedMemberName+' - '+selectedUserType+'</i></span>)</span></b>';
			str+='<div class="row m_top20">';
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<p class="text-capital"><b>'+result[0].requiredName+'</b></p>';
					str+='<table class="table tableCumulative">';
			var countVar =0;
			var BGColor = 1;
			
				for(var i in  result){
					
					//top 5 should build.
					countVar =countVar+1;
					if (countVar === 6) {
						break;
					}
						str+='<tr>';
							str+='<td><span class="count" style="background-color:rgba(237, 29, 38,'+BGColor+')">'+countVar+'</span></td>';
							
							if(result[0].requiredName == "Mandals/Muncipalitys/Divisions" || result[0].requiredName == "Villages/Wards"){
								str+='<td>'+result[i].name+' ('+result[i].locationLevelName+')</td>';
							}else{
								str+='<td>'+result[i].name+'</td>';
							}
							
							str+='<td>';
							if(result[i].completedCount !=null && result[i].completedCount >0){
								str+='<div class="progress progressCustom" data-toggle="tooltip" data-placement="top" title="'+result[i].completedPerc+'%">';
								  str+='<div class="progress-bar" role="progressbar" aria-valuenow="'+result[i].completedCount+'" aria-valuemin="0" aria-valuemax="100" style="width: '+result[i].completedPerc+'%;">';
									str+='<span class="sr-only">'+result[i].completedPerc+'% Complete</span>';
								  str+='</div>';
								str+='</div>';
							str+='</td>';
							str+='<td class="text-danger">'+result[i].completedCount+'</td>';
							}else{
								str+='<td class="text-danger"> - </td>';
							}
								
						str+='</tr>';
						BGColor = BGColor - 0.2;
				}
				str+='</table>';
				str+='</div>';
			str+='</div>';
			$("#topPoorLocationsDiv").html(str);
			$('.progressCustom').tooltip()
		}/* else{
			$("#topPoorLocationsDiv").html("<span class='color_333 pad_5 bg_CC text-capital'>top five <span class='text-danger'>poor</span> locations - (<span style='font-size:11px;'><i> "+selectedMemberName+" - "+selectedUserType+"</i></span>) - (No Data Available)</span>");
		}	 */		
			
	}
	
	
	// DETAILD BLOCK : clicked on '... dots' and 'detailed block' or 'click on slick' START
	
	//... dots.
	var isCommitteeMoreBlockExpand = false;
	var isCommitteeMoreBlockClosed= false;
	$(document).on("click",".moreBlocksIcon",function(){
		isCommitteeMoreBlockExpand = true;
		isCommitteeMoreBlockClosed = false;
		$(this).addClass("unExpandBlock");
		$(".moreBlocks").show();
		$(".moreBlocksDetailAndComp").show();
		$(".moreBlocks1").hide();
		$(".committeeSeetingBlock").show();
		customBuildGraph();
		$("#levelWiseBasicCommittees").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		setTimeout(function(){ committeeDetailsBlockdefaultCall(); }, 1000);
		/* if($(this).hasClass("unExpandBlock")){
			getLevelWiseBasicCommitteesCountReport();
		} */
		
	});
	$(document).on("click",".unExpandBlock",function(){
		$(this).removeClass("unExpandBlock");
		isCommitteeMoreBlockClosed = true;
		$(".moreBlocks").hide();
		$(".moreBlocks1").hide();
		$(".moreBlocksDetailAndComp").hide();
		$(".committeeSeetingBlock").hide();
		$(".moreBlocksDistrictlevel").hide();
		$(".comparisionBlock").removeClass("active");
		$(".detailedBlock").addClass("active");
	});
	
	function committeeDetailsBlockdefaultCall(){
		if(isCommitteeMoreBlockExpand==true && isCommitteeMoreBlockClosed==false){
			getLevelWiseBasicCommitteesCountReport();
		}	
	}
	
	$(document).on("click",".detailedBlock",function(){
		$(".moreBlocks1").hide();
		$(".moreBlocks").show();
		getLevelWiseBasicCommitteesCountReport();
	});
	
	
	$(document).on("click",".comparisionBlock",function(){
		$("#SelectedUserTypeDetailsDiv").html(''); 
		$("#directChildActivityMemberDiv").html('');
		$("#topPoorPerformanceDiv").html('');
		$("#topPoorLocationsDiv").html(''); 
		
		$(".moreBlocks").hide();
		$(".moreBlocksDistrictlevel").hide();
		$(".moreBlocks1").show();
		getAllItsSubUserTypeIdsByParentUserTypeId();
		
	});
	
	$("#levelWiseBasicCommittees").on("click",".slick-next,.slick-prev",function(){
		//var currentSliderLevel = $(".slick-current").find("h4").html();
		var currentSliderLevel = $(".customLi.slick-current").attr("attr_committee_level_name");
		
		var tdpCommitteeLevelIdsClickedArray = [];
		
		if(currentSliderLevel.toLowerCase().indexOf("mandal") >= 0){
			tdpCommitteeLevelIdsClickedArray.push(5);
			tdpCommitteeLevelIdsClickedArray.push(7);
			tdpCommitteeLevelIdsClickedArray.push(9);
		}else if(currentSliderLevel.toLowerCase().indexOf("village") >= 0){
			tdpCommitteeLevelIdsClickedArray.push(6);
			tdpCommitteeLevelIdsClickedArray.push(8);
		}else if(currentSliderLevel.toLowerCase().indexOf("district") >= 0){
			tdpCommitteeLevelIdsClickedArray.push(11);
		}else if(currentSliderLevel.toLowerCase().indexOf("state") >= 0){
			tdpCommitteeLevelIdsClickedArray.push(10);
		}
		
		getcommitteesPerformanceCohort(tdpCommitteeLevelIdsClickedArray);
		
	});
	
	// clicked on '... dots' and 'detailed block' or 'click on slick' END
	
	//filtering in tdp committees start.
	$(".basicCommittessDiv").click(function(event){
		
		var allSelectedCommitteeIdsArray = [];
		//district level
		$(".districtCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				allSelectedCommitteeIdsArray.push( $(this).val() );
			}
		}); 
		//mandal/town/division level.
		$(".mandalCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				allSelectedCommitteeIdsArray.push( $(this).val() );
			}
		}); 
		//village/ward level.
		$(".villageCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				allSelectedCommitteeIdsArray.push( $(this).val() );
			}
		}); 
		
		if(allSelectedCommitteeIdsArray!=null && allSelectedCommitteeIdsArray.length>0){
			 $(".basicCommitteesBlockDropDown").hide();	
			 defaultCommitteeCalls();
		}else{
			//show error msg
			$("#committeeErrMsg").modal("show");
		}
    });
	
	
	function defaultCommitteeCalls(){
		
		committeeBasicCall();
		
		if($(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".liCls1").each(function(){
				var topvalue = $(this).attr("attr_value");
				if(topvalue == "poor"){
					$(".removeactive").removeClass("active");
				}else{
					$(".addactive").addClass("active");
				}
			});
			   getUserTypeWiseCommitteesCompletedCounts1();	
			

		}
		var URLArr = windowUrl.split('/');
		var finalURL = URLArr[parseInt(URLArr.length) - 1].replace('.action','');
		if(finalURL =="committeesMonitoringAction")
		{
			getUserTypeWiseCommitteesCompletedCounts1();	
		}
		if($(".moreBlocksIcon").hasClass("unExpandBlock")){
			if($(".detailedBlock").hasClass("active")){
				$("#levelWiseBasicCommittees").html('');
				$(".moreBlocksDistrictlevel").hide();
				
				getLevelWiseBasicCommitteesCountReport();
				
			}
			if($(".comparisionBlock").hasClass("active")){
				$("#directChildActivityMemberDiv").html('');
				$("#topPoorPerformanceDiv").html('');
				$("#topPoorLocationsDiv").html('');
				getAllItsSubUserTypeIdsByParentUserTypeId();
			}
		}
	}
	function committeeBasicCall(){
			getCommitteesBasicCountReport();
			
	}
	$(document).on("click",".comparisonSelect li",function(){
		if($(this).hasClass("active") == true)
		{
			$(this).removeClass("active");
		}else{
			$(".comparisonSelect li").removeClass("active");
			$(this).addClass("active");
		}
	});
	$(document).on("click",".basicCommitteesBlockDiv",function(e){
		$(this).closest(".committeesBlock").find(".basicCommitteesBlockDropDown").toggle();
		e.stopPropagation();
	});
	$(document).on("click",function(){
		$(".documentCloseClass").hide();
	});
	$(document).on("click",".documentCloseClass",function(e){
		e.stopPropagation();
	});
	//$(".committeesDate").html("up to date( "+customStartDatemMeetings+" )");
	 $('#dateRangeId').on('apply.daterangepicker', function(ev, picker) {
		customStartDatemMeetings = picker.startDate.format('DD/MM/YYYY');
			//do something, like clearing an input
		defaultCommitteeCalls();
		$(".committeesDate").html("( "+customStartDatemMeetings+" )");
	});
	 
	 /* $(document).on("click",".iconExpand",function(){
			$(".dateRangePickerCls").toggleClass("hide");
			$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".committeesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".basicCommitteesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".userTypeCommitteesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".committeesBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
			$(".basicCommitteesBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
			$(".userTypeCommitteesBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
			$(".showDatePicker").toggle();
			if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".moreBlocks").hide();
				$(".moreBlocksDistrictlevel").hide();
				$(".moreBlocks1").hide();
				$(".moreBlocksDetailAndComp").hide();
			}else{
				 getUserTypeWiseCommitteesCompletedCounts1();	
				setTimeout(function(){
					$('html,body').animate({
						scrollTop: $(".committeesBlock").offset().top},
					'slow');
				},500);
			}
			if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
				$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
				$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".debatesMoreHiddenBlock,.debatesHiddenBlock").hide();
				$(".dateRangePickerClsForDebates").toggleClass("hide");
				$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
				$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			}else if( $(".meetingsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".meetingsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".meetingsHiddenBlock,.moreMeetingsBlocksIcon").hide();
				$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
				$(".dateRangePickerClsForMeetings").toggleClass("hide");
				$(".moreMeetingsBlocks1").hide();
				$(".moreMeetingsBlocksDetailed").hide();
				$(".moreMeetingsBlocksComparision").hide();
			}else if( $(".newsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".newsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".newsHiddenBlock,.morenewsBlocksIcon,.newsHiddenMoreBlock").hide();
				$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
				$(".dateRangePickerClsForNews").toggleClass("hide");
			}else if( $(".eventsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".eventsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents,.detailedBlockEvents,.comparisonBlockActivities ").hide();
				$(".panelBlockCollapseIcon").addClass("collapsed");
				$(".activitesExpandIcon").parent().parent().parent().parent().find(".collapse").removeClass("in").addClass("collapsed");
				$(".activitesExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
				$(".eventsListExpandIcon").find("i").removeClass("glyphicon-resize-small").addClass("glyphicon-fullscreen");
				$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
				$(".dateRangePickerClsForEvents").toggleClass("hide");
			}else if( $(".cadreExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".cadreExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".moreCadreBlock,.moreBlocksCadre,.moreBlocksCadreIcon").hide();
				$(".cadreBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			}else if( $(".attendaceIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".attendaceIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".attendanceBlockMore,.moreAttBlocks,.moreAttBlocksIcon").hide();
				$(".dateRangePickerClsForAttendance").toggleClass('hide');
				$(".attendanceBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			}else if( $(".NewTourExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".NewTourExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".NewTourExpandCls,.NewToursHiddenBlock,.moreNewToursBlocksDetailed").hide();
				$(".NewToursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			}else if( $(".alertsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".alertsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".districtAltCtnCls ,.alertLocationDiv,.dateRangePickerClsForAlert,.alertComparisonblock").hide();
				$(".alertsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			}else if( $(".tourExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".tourExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".tourExpandCls ,.toursHiddenBlock,.moreToursBlocks1,.moreToursBlocksDetailed ,.comparisonBlockTours ,.toursDateRangePickerCls").hide();
				$(".toursBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			}else if( $(".emnIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
				$(".emnIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
				$(".moreBlockEMN ,.newEmnHideCls,.dateRangePickerClsForEmn,.newsComparisonUl").hide();
				$(".electronicMediaBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			}
	
			 setTimeout(function(){
				$(".committeesHiddenBlock,.moreBlocksIcon").toggle();
				//initialiseGraph();
			},500); 
		}); */
		
		function getCadreEnrolmentYears(){
			 var jsObj={
			
				   };
				   
			 $.ajax({
				type : "GET",
				url : "getCadreEnrollmentYearsAction.action",
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result != null && result.length > 0){
					for(var i in result){
						if(parseInt(result[i].id) == 2)
							$("#tdpCommitteeYearId").append('<option value='+result[i].id+' selected="selected">'+result[i].electionYear+'</option>');
						else
							$("#tdpCommitteeYearId").append('<option value='+result[i].id+'>'+result[i].electionYear+'</option>');
					}
				}
				if(finalGlobalUrl=="committeesMonitoringAction"){
				    $(".comparisionBlock ").trigger("click");
				}
				getCommitteeDetailsBiEnrollement(2);
			});
		}
		$(document).on("change","#tdpCommitteeYearId",function(){
			getCommitteeDetailsBiEnrollement(0);
		});
		
		function getCommitteeDetailsBiEnrollement(id){
			var enrollmentIdsArr = new Array();
			if(id == 0)
				enrollmentIdsArr.push($("#tdpCommitteeYearId").val());
			else{
				enrollmentIdsArr.push(id);
			}
			var jsObj={
				enrollmentIdsArr:enrollmentIdsArr
			};
				   
			 $.ajax({
				type : "GET",
				url : "getCommitteeDetailsByEnrollementIdAction.action",
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				
				if(result != null && result.length > 0){
					for(var i in result){
						
						var fromDate = result[i].fromDate.split('-');
						var toDate = result[i].toDate.split('-');
				
				
						if(fromDate.length ==0){
							fromDate=[];
							fromDate.push('2017');
							fromDate.push('02');
							fromDate.push('01');
							
							var fYear = fromDate[0];
							var fMonth = fromDate[1];
							var fDate = fromDate[2];
						 
						}
						else{
							 var fYear = result[i].fromDate.split('-')[0];
							var fMonth = result[i].fromDate.split('-')[1];
							var fDate = result[i].fromDate.split('-')[2].substring(0,2);
						}
						if(toDate == '' || toDate.length ==0){
							toDate=[];
							toDate.push('2018');
							toDate.push('02');
							toDate.push('01');
							
							var tYear = toDate[0];
							var tMonth = toDate[1];
							var tDate = toDate[2];
						 
						}else{
							var tYear = result[i].toDate.split('-')[0];
							var tMonth = result[i].toDate.split('-')[1];
							var tDate = result[i].toDate.split('-')[2].substring(0,2);
						}
						
						$('#dateRangeId').data('daterangepicker').setStartDate(fDate+'/'+fMonth+'/'+fYear);
						$('#dateRangeId').data('daterangepicker').setEndDate(tDate+'/'+tMonth+'/'+tYear);
						$('#dateRangeId').val(fDate+'/'+fMonth+'/'+fYear+' - '+tDate+'/'+tMonth+'/'+tYear)
					}
					defaultCommitteeCalls();
				}
			});
		}
		$(document).on("change","#tdpCommitteeYearId",function(){
			/* alert(12);
			var id =0; */
			getCommitteeDetailsBiEnrollement(0);
		});
		$('body').on('click', function (e) {

		if(!$(e.target).is('.multiLevelLiA') || !$(e.target).has('.multilevelCls') || !$(e.target).has('.multilevelli')){
			$('.clearCls').each(function () {
				var attrId= $(this).attr("id");
				var trgt = e.target;

				if(!$(this).is(trgt) && $(this).has(trgt).length === 0 && !$(trgt).is('.multiLevelCls') && !$(trgt).is('.multilevelli') && !$(trgt).is('.multiLevelLiA') ) {
				
					$("#"+attrId).popover('hide');
					$("#"+attrId).removeClass('clearCls');
					$(".multiLevelLiA").popover('hide');
					
				}
				
			});
		}
		$('.popOverCls').each(function () {				
				if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {					
					//$("#"+attrId).popover('hide');
					$(this).popover('hide');
				}
				
		});
	
	
	});
		
 function getAflCommitteeCount(state,level,committeeType){
	//	var startDate=$(".dp_startDate").val();
	//	var endDate=$(".dp_endDate").val();
	var dateStr = $('#dateRangeId').val();		
	var dateStrArr = dateStr.split('-');
	var startDate = dateStrArr[0];
	var endDate = dateStrArr[1];
        if(startDate != null){
			 var startDateSplitArray = startDate.split("/");
			 startDate = startDateSplitArray[1].trim() + "/" + startDateSplitArray[0].trim() + "/" + startDateSplitArray[2].trim();
		 }
		 if(endDate != null){
			 var endDateSplitArray = endDate.split("/");
			 endDate = endDateSplitArray[1].trim() + "/" + endDateSplitArray[0].trim() + "/" + endDateSplitArray[2].trim();
		 }

		var levelIdsArr = new Array();
		var state = state; 
		if(level == 'mandal')
		{
		   levelIdsArr.push(5);
		}else if(level == 'town')
		{
		   levelIdsArr.push(7);
		}else if(level == 'division')
		{
		   levelIdsArr.push(9);
		}else if(level == 'village')
		{
		   levelIdsArr.push(6);
		}else if(level == 'ward')
		{
		   levelIdsArr.push(8);
		}else if(level == 'mandalAll')
		{
		    levelIdsArr.push(5);
			levelIdsArr.push(7);
			levelIdsArr.push(9);
		}else if(level == 'villageAll')
		{
		    levelIdsArr.push(6);
			levelIdsArr.push(8);
		}
		else if(level == 'district')
		{
		    levelIdsArr.push(11);
		    
		}
		else if(level == 'state')
		{
		    levelIdsArr.push(10);
		    
		}
		var state = state; 
		var committeeSpanTypeIdsArr = [];
		committeeSpanTypeIdsArr.push($('#tdpCommitteeYearId').val());
		var jObj = {
			state:globalStateId,
			levelIdsArr:levelIdsArr,
			startDate  :startDate,
			endDate    :endDate,
			committeeType:committeeType,
			task:"mainCommitteeMemberCnt",
			committeeSpanTypeIdsList :committeeSpanTypeIdsArr,
			activityMemberId : globalActivityMemberId,
	        userTypeId : globalUserTypeId
		}
			//alert(444)	;	
		$.ajax({
          type:'GET',
          url: 'getStartedAffliCommitteesCountByLocation.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
    		      location.reload(); 
    	        }
			}
					var str1='';
					str1+='<ul class="onHoverPopUp">';
					//str1+='<ul multilevelul style="padding-left:0px; width:272px;margin-left:-14px; font-size: 11px; ">';	
					//str1+='<ul class="dropdown-menu arrow_box list-group"><div class="panel panel-default m_bottom0"><div class="panel-heading m_top0">COMMITTEE TYPE<span class="pull-right">TOTAL</span></div></div>';
					for(var i in result){ 					
					str1+='<li class="list-group-item multiLevelLiA" attr_state='+state+' attr_level='+level+' attr_type="affl" attr_resId='+result[i].id+' attr_commType='+committeeType+' id="'+level+'IdAffl'+state+committeeType+''+result[i].id+'" ><a  class="multilevelli" >'+result[i].name+'<span class="badge pull-right">'+result[i].afflCommittees+'</span></a></li>';
					
					//str1+='<li class="list-group-item multiLevelLiA" attr_state='+state+' attr_level='+level+' attr_type="affl" attr_resId=1 id="'+level+'IdAffl'+state+'1"><a     class="multilevelli" >'+result[i].name+' 1 <span class="pull-right badge">'+result[i].afflCommittees+'</span></a></li>';
					} 
					str1+='</ul>';
					$('#'+level+'Id'+state+'Affl'+committeeType).popover({
						
						html: true,
						placement: "bottom",
						title: '',
						content: str1
					});
					
					if(!$('#'+level+'Id'+state+'Affl'+committeeType).hasClass("clearCls")){
						$('#'+level+'Id'+state+'Affl'+committeeType).addClass("clearCls");
						$('#'+level+'Id'+state+'Affl'+committeeType).popover("show");
					}
					
		});
	}
	
	function getMainCommitteeMembersCount(state,level,type,committeeId,committeeType){
		
		//var startDate=$(".dp_startDate").val();
		//var endDate=$(".dp_endDate").val();
		var dateStr = $('#dateRangeId').val();
		var dateStrArr = dateStr.split('-');
		var startDate = dateStrArr[0];
		var endDate = dateStrArr[1];
		 
		if(startDate != null){
			 var startDateSplitArray = startDate.split("/");
			 startDate = startDateSplitArray[1].trim() + "/" + startDateSplitArray[0].trim() + "/" + startDateSplitArray[2].trim();
		 }
		 if(endDate != null){
			 var endDateSplitArray = endDate.split("/");
			 endDate = endDateSplitArray[1].trim() + "/" + endDateSplitArray[0].trim() + "/" + endDateSplitArray[2].trim();
		 }
		
		var levelIdsArr = new Array();
		var state = state; 
		if(level == 'mandal')
		{
		   levelIdsArr.push(5);
		}else if(level == 'town')
		{
		   levelIdsArr.push(7);
		}else if(level == 'division')
		{
		   levelIdsArr.push(9);
		}else if(level == 'village')
		{
		   levelIdsArr.push(6);
		}else if(level == 'ward')
		{
		   levelIdsArr.push(8);
		}else if(level == 'mandalAll')
		{
		    levelIdsArr.push(5);
			levelIdsArr.push(7);
			levelIdsArr.push(9);
		}else if(level == 'villageAll')
		{
		    levelIdsArr.push(6);
			levelIdsArr.push(8);
		}
		else if(level == 'district')
		{
		    levelIdsArr.push(11);
			
		}
		else if(level == 'state')
		{
		    levelIdsArr.push(10);
			
		}
		
		var state = state; 
		var committeeSpanTypeIdsArr = [];
		committeeSpanTypeIdsArr.push($('#tdpCommitteeYearId').val());
		var jObj = {
			state:globalStateId,
			levelIdsArr:levelIdsArr,
			startDate  :startDate,
			endDate    :endDate,
			committeeId:committeeId,
			committeeType:committeeType,
			task:"mainCommitteeMemberCnt",
			committeeSpanTypeIdsList :committeeSpanTypeIdsArr,
			activityMemberId : globalActivityMemberId,
	        userTypeId : globalUserTypeId
		}
			//alert(222)	;	
		$.ajax({
          type:'GET',
          url: 'getMembersRangeCountByLocationAction1.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				if(type == 'main'){
					var str='';
					str+='<ul class="onHoverPopUp">';
						for(var i in result){
							str+='<li class="list-group-item "><span class="badge">'+result[i].membersCount+'</span>1 MEMBER COMMITTEE</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount1+'</span>2-4 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount2+'</span>5-7 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount3+'</span>7-14 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount4+'</span>ABOVE 14 MEMBER COMMITTEES</li>';
						}
					str+='</ul>';
					
					$('#'+level+'Id'+state+committeeType+'').popover({
						html: true,
						placement: "bottom",
						title: '',
						content: str
								
					});
					
					if(!$('#'+level+'Id'+state+committeeType+'').hasClass("clearCls")){
						$('#'+level+'Id'+state+committeeType+'').addClass("clearCls");
						$('#'+level+'Id'+state+committeeType+'').popover("show");
					}else{
						$('#'+level+'Id'+state+committeeType+'').removeClass("clearCls");
					}
				}
				else if(type == 'affl'){
					var str='';
					str+='<ul class="onHoverPopUp">';
						for(var i in result){
							str+='<li class="list-group-item "><span class="badge">'+result[i].membersCount+'</span>1 MEMBER COMMITTEE</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount1+'</span>2-4 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount2+'</span>5-7 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount3+'</span>7-14 MEMBER COMMITTEES</li>';
							str+='<li class="list-group-item"><span class="badge">'+result[i].membersCount4+'</span>ABOVE 14 MEMBER COMMITTEES</li>';
						}
					str+='</ul>';
					var popOverSettings = { 
						placement: 'left',
						container: 'body',
						html: true,
						title : '',
						content:"<div style='color:red'>This is your div content</div>"
						/* content: function () {
							return $('#popover-content').html();
						} */
					}
				
					$("#"+id).popover(popOverSettings);
				}
				
					
		});
	}
	
	function getCommitteeDetailledReport(){
		
		var basiccommitteeTypeIds = [];
		var committeeTypeIds = [];
		
		//basiccommitteeTypeIds.push(2);// affiliated 
		var enrollmentIds = [];
		enrollmentIds.push(2); // committee_enrollmentYearId
		
		var locationValuesArr = [];
		locationValuesArr.push(1);	// ap state 
		
		var dateStr = $('#dateRangeId').val();
		var dateStrArr = dateStr.split('-');
		var date1 = dateStrArr[0].split('/');;
		var date2 = dateStrArr[1].split('/');;
		
		var startDate = date1[0]+'-'+date1[1]+'-'+date1[2]
		var endDate = date2[0]+'-'+date2[1]+'-'+date2[2]
		
	
	
	   var levelWiseBasicCommitteesArray = getLevelWiseBasicCommitteesArray();
		
		var allSelectedCommitteeIdsArray = [];
		//district level
		$(".districtCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				allSelectedCommitteeIdsArray.push( $(this).val() );
			}
		}); 
		//mandal/town/division level.
		$(".mandalCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				allSelectedCommitteeIdsArray.push( $(this).val() );
			}
		}); 
		//village/ward level.
		$(".villageCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				allSelectedCommitteeIdsArray.push( $(this).val() );
			}
		}); 
		var committeeLevelId='';
		
		$("[role='tablist'] li").each(function(){
			if($(this).hasClass("active")){
				var type = $(this).find("a").html();
				if(type == "District Level"){
					committeeLevelId = "11";
				}else if(type == "Mandal/town/division level"){
					committeeLevelId = "5";
				}else if(type == "village/ward level"){
					committeeLevelId = "6";
				}
			}
		}); 
		
		var jObj = {
			
			fromDate  : startDate,
			toDate    : endDate,
			basiccommitteeTypeIdsList : levelWiseBasicCommitteesArray,
			committeeTypeIdsList :allSelectedCommitteeIdsArray,
			committeeLevelId :committeeLevelId,
			locationScopeId: 2,
			LocationValuesList :locationValuesArr,
			enrollmentIdsLst :enrollmentIds
			//activityMemberId : globalActivityMemberId,
	        //userTypeId : globalUserTypeId
			
		}
		
		$.ajax({
          type:'GET',
          url: 'getCommitteeDetailedReportAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result !=null && result.length>0){
				buildCommitteeDetailedReport(result);
			}else{
				$("#commiteeWiseDetailedReportId").html("No Data Available");
			}
			
	    });
	}
	
	
	function getCommitteeDetailedReport(){
		
		var basiccommitteeTypeIds = [];
		var committeeTypeIds = [];
		
		//basiccommitteeTypeIds.push(2);// affiliated 
		var enrollmentIds = [];
		enrollmentIds.push(2); // committee_enrollmentYearId
		
		var locationValuesArr = [];
		locationValuesArr.push(1);	// ap state 
		
		var dateStr = $('#dateRangeId').val();
		var dateStrArr = dateStr.split('-');
		var startDate  = dateStrArr[0];
		var endDate = dateStrArr[1];
	
		var jObj = {
			
			fromDate  : '12-12-2014',
			toDate    : '12-12-2055',
			basiccommitteeTypeIdsList : basiccommitteeTypeIds,
			committeeTypeIdsList :committeeTypeIds,
			committeeLevelId :"11",
			locationScopeId: 2,
			LocationValuesList :locationValuesArr,
			enrollmentIdsLst :enrollmentIds
			
		}
		
		$.ajax({
          type:'GET',
          url: 'getCommitteeDetailedReportAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result !=null && result.length>0){
				buildCommitteeDetailedReport(result);
			}else{
				$("#commiteeWiseDetailedReportId").html("No Data Available");
			}
			
	    });
	}
	
function buildCommitteeDetailedReport(result){
	
	var str='';
	str+='<div class="table-responsive">';
		str+='<table class="table table-condensed table_custom table-bordered commiteeWiseDetailedReportDT">';
			str+='<thead>';
				str+='<tr>';
					str+='<th rowspan="2">Location</th>';
					for(var i in result[0].subList){
						str+='<th colspan="2">'+result[0].subList[i].name+'</th>';
					}
				str+='</tr>';
				str+='<tr>';
				for(var i in result[0].subList){
					str+='<th>Vancancy</th>';
					str+='<th>Filled</th>';
				}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].name+'</td>';
						for(var j in result[i].subList){
							if(result[i].subList[j].totalCount !=null && result[i].subList[j].totalCount>0){
								str+='<td>'+result[i].subList[j].totalCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].subList[j].completedCount !=null && result[i].subList[j].completedCount>0){
								str+='<td>'+result[i].subList[j].completedCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							
						}
					str+='</tr>';
				}
				
			str+='</tbody>';
			//Export Excel
			str+='<table class="table table-condensed table_custom table-bordered" id="commiteeWiseDetailedReportExportExcel" style="display:none;">';
			str+='<thead>';
				str+='<tr>';
					str+='<th rowspan="2">Location</th>';
					for(var i in result[0].subList){
						str+='<th colspan="2">'+result[0].subList[i].name+'</th>';
					}
				str+='</tr>';
				str+='<tr>';
				for(var i in result[0].subList){
					str+='<th>Vancancy</th>';
					str+='<th>Filled</th>';
				}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].name+'</td>';
						for(var j in result[i].subList){
							if(result[i].subList[j].totalCount !=null && result[i].subList[j].totalCount>0){
								str+='<td>'+result[i].subList[j].totalCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].subList[j].completedCount !=null && result[i].subList[j].completedCount>0){
								str+='<td>'+result[i].subList[j].completedCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							
						}
					str+='</tr>';
				}
				
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	
	$("#commiteeWiseDetailedReportId").html(str);
	$(".tooltipCls").tooltip();
	$(".commiteeWiseDetailedReportDT").dataTable({
		"iDisplayLength": 16,
		"aaSorting": [],
		"aLengthMenu": [[16, 20, -1], [16, 20, "All"]],
		"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
		"<'row'<'col-sm-12'tr>>" +
		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				//extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o generateExcel"></i>',
				//titleAttr: 'CSV',
			}
		]
	});
}
$(document).on("click",".generateExcel",function(){
	tableToExcel('commiteeWiseDetailedReportExportExcel', 'Commitee WiseDetailed Report');
});