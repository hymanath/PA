	var finalGlobalUrl='';
	var boothcommiteType= "boothCommittee";
	 function passRequiredUrl(finalURL){
		  finalGlobalUrl = finalURL; 
	  }	
	function getBoothLevelWiseBasicCommitteesArray(){
		
		var levelWiseBasicCommitteesArray = new Array();
		var jsObj={committeeLevelId:"",basicCommitteeIds:""};
		
		//district level
		var districtBasicCommitteeIds =[];
		$(".districtBoothCommitteecheckBoxClass").each(function(){
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
		/*$(".mandalCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				mandalBasicCommitteeIds.push( $(this).val() );
			}
		}); */
		mandalBasicCommitteeIds.push(1);
		var mandalCommitteeLevelObject = new Object();
		mandalCommitteeLevelObject.committeeLevelId = 5;
		mandalCommitteeLevelObject.basicCommitteeIds = mandalBasicCommitteeIds;
		levelWiseBasicCommitteesArray.push(mandalCommitteeLevelObject);
		
		//village/ward level.
		var villageBasicCommitteeIds =[];
		$(".villageBoothCommitteecheckBoxClass").each(function(){
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
	$(document).on("click","#checkAllBoothAffliatedVillagelevelId",function(){
		if($(this).is(':checked')){
		   $('.villageBoothCommitteeAffliatedcheckBoxClass').prop('checked', true);
		}else{
		   $('.villageBoothCommitteeAffliatedcheckBoxClass').prop('checked', false);
		}
	});
	$(document).on("click","#checkAllBoothAffliatedMandallevelId",function(){
		if($(this).is(':checked')){
		   $('.mandalBoothCommitteeAffliatedcheckBoxClass').prop('checked', true);
		}else{
		   $('.mandalBoothCommitteeAffliatedcheckBoxClass').prop('checked', false);
		}
	});
	$(document).on("click","#checkAllBoothAffliatedDistrictlevelId",function(){
		if($(this).is(':checked')){
		   $('.districtBoothCommitteeAffliatedcheckBoxClass').prop('checked', true);
		}else{
		   $('.districtBoothCommitteeAffliatedcheckBoxClass').prop('checked', false);
		}
	});
	function getBoothCommitteesBasicCountReport(){
		boothDefaultCommitteeCalls();
	}
	function getBoothUserTypeWiseCommitteesCompletedCounts(){
		
		var state = globalState;
	    var dateString = "";//$("#dateRangeBoothId").val();
    
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
	function getUserTypeWiseCommitteesCompletedCounts2(){
		//active top5 block
		$('ul.boothCommitteeTopPoorLiCls li').removeClass('active');
		$('ul.boothCommitteeTopPoorLiCls li:first-child').addClass('active');
		$("#boothUserTypeWiseCommitteesForTopFiveStrongAndPoorDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	   var state = globalState;
       var dateString = "";//$("#dateRangeBoothId").val();
	   var levelWiseBasicCommitteesArray = getBoothLevelWiseBasicCommitteesArray();
	 
	 
	   var committeeEnrollmentYearArray = new Array();
	   if($("#tdpBoothCommitteeYearId").val() != null)
		   committeeEnrollmentYearArray.push($("#tdpBoothCommitteeYearId").val());
		    
			var jsObj ={  
			          activityMemberId : globalActivityMemberId,
					  userTypeId : globalUserTypeId,
			          userAccessLevelId:globalUserAccessLevelId,
					  userAccessLevelValuesArray:globalUserAccessLevelValues,
					  state:state,
					  levelWiseBasicCommitteesArray : levelWiseBasicCommitteesArray,
					  dateString : dateString,
					  committeeEnrollmentYearArray :committeeEnrollmentYearArray,
					  commiteType: boothcommiteType
 			         
					}
		
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWiseCommitteesCompletedCountsAction1.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#boothUserTypeWiseCommitteesForTopFiveStrongAndPoorDiv").html('');
			buildgetBoothUserTypeWiseCommitteesCompletedCountsForTopFiveStrongResults(result);
			
			//buildgetUserTypeWiseCommitteesCompletedCountsForTopFivePoorResults(result);
			 globalUserWiseMemberRslt = result;
		});
	}
	function getBoothLevelWiseBasicCommitteesCountReport(){
		$("#boothLevelWiseBasicCommittees").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var state = globalState;
	   
       var dateString = "";//$("#dateRangeBoothId").val();
	   var levelWiseBasicCommitteesArray = getBoothLevelWiseBasicCommitteesArray();
	   // alert(222);
	   var committeeEnrollmentYearArray = new Array();
		   committeeEnrollmentYearArray.push($("#tdpBoothCommitteeYearId").val());
		var jsObj ={
			         userAccessLevelId:globalUserAccessLevelId,
					 userAccessLevelValuesArray:globalUserAccessLevelValues,
					 state:state,
					 levelWiseBasicCommitteesArray : levelWiseBasicCommitteesArray,
					 dateString : dateString,
					 committeeEnrollmentYearArray :committeeEnrollmentYearArray,
					 commiteType: boothcommiteType
					}
		
		$.ajax({
			type : 'POST',
			url : 'getLevelWiseBasicCommitteesCountReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#boothLevelWiseBasicCommittees").html('');
			buildgetBoothLevelWiseBasicCommitteesCountReport(result);
		});
	}
	function getBoothcommitteesPerformanceCohort(tdpCommitteeLevelIdsClickedArray){
	
		$("#boothDistrictWiseCommitteesReport").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		
		var state = globalState;
		
		var userLocationLevelId = globalUserAccessLevelId;
		var userLocationLevelValuesArray = globalUserAccessLevelValues;
		
		var committeeStatus = 'all';
        var dateString = "";//$("#dateRangeBoothId").val();
		var levelWiseBasicCommitteesArray = getBoothLevelWiseBasicCommitteesArray();
		 //alert(333);
		var committeeEnrollmentYearArray = new Array();
		    committeeEnrollmentYearArray.push($("#tdpBoothCommitteeYearId").val());
		var jsObj ={tdpCommitteeLevelIdsClickedArray:tdpCommitteeLevelIdsClickedArray,
					levelWiseBasicCommitteesArray : levelWiseBasicCommitteesArray,
					committeeStatus:committeeStatus,
					userLocationLevelId:userLocationLevelId,
					userLocationLevelValuesArray:userLocationLevelValuesArray,
					dateString : dateString,
					state:state,
					committeeEnrollmentYearArray:committeeEnrollmentYearArray,
					commiteType: boothcommiteType
					}
		
		$.ajax({
			type : 'POST',
			url : 'committeesPerformanceCohortAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#boothDistrictWiseCommitteesReport").html('');
			buildBoothCommitteesPerformanceCohort(result);
			
		});
	}
	function getBoothAllItsSubUserTypeIdsByParentUserTypeId(){
		  $("#boothChildUserTypeDetailsDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj = { parentUserTypeId : globalUserTypeId }
		$.ajax({
			type : 'POST',
			url : 'getAllItsSubUserTypeIdsByParentUserTypeIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
			 buildgetboothChildUserTypesByItsParentUserType(result)
			}else{
			 $("#boothChildUserTypeDetailsDiv").html('NO DATA AVAILABLE.');	
			}
		});			 
	}
	
	
	function getBoothSelectedChildUserTypeMembers(childUserTypeIdString,childUserType){
		
	$("#boothSelectedUserTypeDetailsDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
     var parentActivityMemberId = globalActivityMemberId;
	 
	 var childUserTypeIdsArray = childUserTypeIdString.split(",");
	 
	 var date = "";// $("#dateRangeBoothId").val();
	 var state = globalState;
  	 var levelWiseBasicCommitteesArray = getBoothLevelWiseBasicCommitteesArray();
	 var committeeEnrollmentYearArray = new Array();
		 committeeEnrollmentYearArray.push($("#tdpBoothCommitteeYearId").val());
		  //alert(444);
	  var jsObj ={ 
					parentActivityMemberId : parentActivityMemberId,
					childUserTypeIdsArray : childUserTypeIdsArray,
					dateString : date,
					state:state,
					levelWiseBasicCommitteesArray:levelWiseBasicCommitteesArray,
					committeeEnrollmentYearArray:committeeEnrollmentYearArray,
					commiteType: boothcommiteType
				 }
	  $.ajax({
			type : 'POST',
			url : 'getSelectedChildUserTypeMembersAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#boothSelectedUserTypeDetailsDiv").html('');
			buildgetBoothSelectedChildUserTypeMembers(result,childUserType);
		});
	}
	function getBoothDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId){
	   $("#"+childActivityMemberId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   var state = globalState
	   
	   var dateString = "";//$('#dateRangeBoothId').val();
	   var levelWiseBasicCommitteesArray = getBoothLevelWiseBasicCommitteesArray();
	   var committeeEnrollmentYearArray = new Array();
		   committeeEnrollmentYearArray.push($("#tdpBoothCommitteeYearId").val());
		  //  alert(555);
	   var jsObj ={  
	                 activityMemberId : activityMemberId,
			         userTypeId : userTypeId,
					 state:state,
					 levelWiseBasicCommitteesArray : levelWiseBasicCommitteesArray,
 			         dateString :   dateString,
					 committeeEnrollmentYearArray:committeeEnrollmentYearArray,
					 commiteType: boothcommiteType
				  }
	   	$.ajax({
			type : 'POST',
			url : 'getDirectChildActivityMemberCommitteeDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#"+childActivityMemberId).html('');
			buildgetBoothDirectChildActivityMemberCommitteeDetails(result,selectedMemberName,selectedUserType,childActivityMemberId,userTypeId);
		});
	}
	function getBoothTopPoorPerformancecommittees(activityMemberId,selectedMemberName,selectedUserType){
	   $("#boothTopPoorPerformanceDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   var state = globalState;
	  
	       var dateString = "";//$('#dateRangeBoothId').val();
	   var levelWiseBasicCommitteesArray = getBoothLevelWiseBasicCommitteesArray();
	   var committeeEnrollmentYearArray = new Array();
		committeeEnrollmentYearArray.push($("#tdpBoothCommitteeYearId").val());
		//alert(boothcommiteType);
		// alert(666);
	   var jsObj ={  activityMemberId : activityMemberId,
					 state:state,
					 levelWiseBasicCommitteesArray : levelWiseBasicCommitteesArray,
 			         dateString :   dateString,
					 committeeEnrollmentYearArray:committeeEnrollmentYearArray,
					 commiteType: boothcommiteType
				  }
	   
	   	$.ajax({
			type : 'POST',
			url : 'getTopPoorPerformancecommitteesAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 $("#boothTopPoorPerformanceDiv").html('');
			buildgetBoothTopPoorPerformancecommittees(result,selectedMemberName,selectedUserType);
			
		});
	}
	function getBoothTopPoorCommitteeLocations(activityMemberId,selectedMemberName,selectedUserType){
	   $("#boothTopPoorLocationsDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   var state = globalState;
	   //alert(boothcommiteType);
	   //alert(top);
	       var dateString = "";//$('#dateRangeBoothId').val();
	   var levelWiseBasicCommitteesArray = getBoothLevelWiseBasicCommitteesArray();
	    var committeeEnrollmentYearArray = new Array();
		committeeEnrollmentYearArray.push($("#tdpBoothCommitteeYearId").val());
		// alert(777);
	   var jsObj ={  activityMemberId : activityMemberId,
					 state:state,
					 levelWiseBasicCommitteesArray : levelWiseBasicCommitteesArray,
 			         dateString :   dateString,
					 committeeEnrollmentYearArray:committeeEnrollmentYearArray,
					 commiteType: boothcommiteType
				  }
	   
	   	$.ajax({
			type : 'POST',
			url : 'getTopPoorCommitteeLocationsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 $("#boothTopPoorLocationsDiv").html('');
			buildBoothTopPoorCommitteeLocations(result,selectedMemberName,selectedUserType);
			
		});
	}
	//srujana
	function buildgetBoothCommitteesBasicCountReport(result){
		$("#boothBasicCommitteeCountsDiv").html('');
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
																str+='<i  id=\''+'villageAll'+'IdAPstarted\' onClick="getBoothMainCommitteeMembersCount(\''+globalState+'\',\'villageAll\',\'main\','+1+'\,\'started\')" style="cursor:pointer;margin-left:3px" class="glyphicon glyphicon-info-sign"></i></p>';
															if(result.subList[i].name == "Mandal")
																str+='<i style="cursor:pointer;margin-left:3px" id=\''+'mandalAll'+'IdAPstarted\' onClick="getBoothMainCommitteeMembersCount(\''+globalState+'\',\'mandalAll\',\'main\','+1+'\,\'started\')"  class="glyphicon glyphicon-info-sign "></i></p>';
															if(result.subList[i].name == "District")
																str+='<i style="cursor:pointer;margin-left:3px" id=\''+'district'+'IdAPstarted\' onClick="getBoothMainCommitteeMembersCount(\''+globalState+'\',\'district\',\'main\','+1+'\,\'started\')" class="glyphicon glyphicon-info-sign "></i></p>';
															if(result.subList[i].name == "State")
																str+='<i style="cursor:pointer;margin-left:3px" id=\''+'state'+'IdAPstarted\' onClick="getBoothMainCommitteeMembersCount(\''+globalState+'\',\'state\',\'main\','+1+'\,\'started\')" class="glyphicon glyphicon-info-sign "></i></p>';
														
															str+='<p class="responsiveFont">'+result.subList[i].mainVO.startedCount+' <small class="text-danger"> '+result.subList[i].mainVO.startedPerc+'%</small></p>';
														}else{
															str+='</p><p> - </p>';
														}
														
													str+='</td>';
													str+='<td>';
														str+='<p class="text-muted text-capitalize responsiveFont">Completed';
														if(result.subList[i].mainVO.completedCount !=null && result.subList[i].mainVO.completedCount >0){
															if(result.subList[i].name == "Village")
																str+='<i style="cursor:pointer;margin-left:3px" id=\''+'villageAll'+'IdAPcompleted\' onClick="getBoothMainCommitteeMembersCount(\''+globalState+'\',\'villageAll\',\'main\','+1+'\,\'completed\')" class="glyphicon glyphicon-info-sign "></i></p>';
															if(result.subList[i].name == "Mandal")
																 str+='<i style="cursor:pointer;margin-left:3px" id=\''+'mandalAll'+'IdAPcompleted\' onClick="getBoothMainCommitteeMembersCount(\''+globalState+'\',\'mandalAll\',\'main\','+1+'\,\'completed\')" class="glyphicon glyphicon-info-sign "></i></p>';
															 if(result.subList[i].name == "District")
																 str+='<i style="cursor:pointer;margin-left:3px" id=\''+'district'+'IdAPcompleted\' onClick="getBoothMainCommitteeMembersCount(\''+globalState+'\',\'district\',\'main\','+1+'\,\'completed\')"  class="glyphicon glyphicon-info-sign "></i></p>';
															 if(result.subList[i].name == "State")
																 str+='<i style="cursor:pointer;margin-left:3px" id=\''+'state'+'IdAPcompleted\' onClick="getBoothMainCommitteeMembersCount(\''+globalState+'\',\'state\',\'main\','+1+'\,\'completed\')" class="glyphicon glyphicon-info-sign "></i></p>';
														
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
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'villageAll'+'IdAPAfflstarted\' onClick="getBoothAflCommitteeCount(\''+globalState+'\',\'villageAll\',\'started\')" class="glyphicon glyphicon-info-sign "></i></p>';
														if(result.subList[i].name == "Mandal")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'mandalAll'+'IdAPAfflstarted\' onClick="getBoothAflCommitteeCount(\''+globalState+'\',\'mandalAll\',\'started\')" class="glyphicon glyphicon-info-sign "></i></p>';
														 if(result.subList[i].name == "District")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'district'+'IdAPAfflstarted\' onClick="getBoothAflCommitteeCount(\''+globalState+'\',\'district\',\'started\')"  class="glyphicon glyphicon-info-sign "></i></p>';
														if(result.subList[i].name == "State")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'state'+'IdAPAfflstarted\' onClick="getBoothAflCommitteeCount(\''+globalState+'\',\'state\',\'started\')" class="glyphicon glyphicon-info-sign "></i></p>'; 
													
														str+='<p>'+result.subList[i].affliatedVO.startedCount+' <small class="text-danger"> '+result.subList[i].affliatedVO.startedPerc+'%</small></p>';
													}else{
														str+='</p><p> - </p>';
													}
													
												str+='</td>';
												str+='<td>';
													str+='<p class="text-muted text-capitalize responsiveFont">Completed';
													if(result.subList[i].affliatedVO.completedCount !=null && result.subList[i].affliatedVO.completedCount >0){
														 if(result.subList[i].name == "Village")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'villageAll'+'IdAPAfflcompleted\' onClick="getBoothAflCommitteeCount(\''+globalState+'\',\'villageAll\',\'completed\')"  class="glyphicon glyphicon-info-sign "></i></p>';
														if(result.subList[i].name == "Mandal")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'mandalAll'+'IdAPAfflcompleted\' onClick="getBoothAflCommitteeCount(\''+globalState+'\',\'mandalAll\',\'completed\')"  class="glyphicon glyphicon-info-sign "></i></p>';
														 if(result.subList[i].name == "District")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'district'+'IdAPAfflcompleted\' onClick="getBoothAflCommitteeCount(\''+globalState+'\',\'district\',\'completed\')" class="glyphicon glyphicon-info-sign "></i></p>';
														if(result.subList[i].name == "State")
															str+='<i  style="cursor:pointer;margin-left:3px" id=\''+'state'+'IdAPAfflcompleted\' onClick="getBoothAflCommitteeCount(\''+globalState+'\',\'state\',\'completed\')" class="glyphicon glyphicon-info-sign "></i></p>';
													
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
					
				str+='</ul> srishailam';
				$("#boothBasicCommitteeCountsDiv").html(str);
		}else{
			$("#boothBasicCommitteeCountsDiv").html("No Data Available");
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
	
	function buildgetBoothLevelWiseBasicCommitteesCountReport(result)
	{
		$("#boothLevelWiseBasicCommittees").html('');
		
		var firstLevelForCohort = '';
		//Building Level Names.
		var locationLevelNameArray =[];
		if(result != null && result.length > 0){
			var str='';
			str+='<ul class="boothVillageWardUl">';
			var length = result.length - 1;
			for(var i = length; i >= 0; i--){
				if(result[i].id !=10){
					if(result[i].subList !=null && result[i].subList.length > 0){
						str+='<li class="customLi">';
						var properName = getProperLocationLevelName(result[i].name);
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
								str+='<li><div id="boothLevelWiseCommittesDetailed'+i+''+j+'" class="chartLi"></div></li>';
							}
							str+='</ul>';
						str+='</div>';
						str+='</li>';
					}
				}					
            }
			str+='</ul>';
		}
	    $("#boothLevelWiseBasicCommittees").html(str);
		
		
	 
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
								$('#boothLevelWiseCommittesDetailed'+i+''+j+'').highcharts({
									colors: ['#7DDF7D','#C53A36'],
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
									 series: [ {
										name: 'Completed',
										data: levelWiseBasicCompletedPercArray 
									},{
										name: 'Started',
										data: levelWiseBasicStartedPercArray 
									},{
										name: 'Not Started',
										data: levelWiseBasicNotStartedPercArray
									}]
								});
							});	
							}else{
								$('#boothLevelWiseCommittesDetailed'+i+''+j+'').html("<b>"+committeeName+"</b> (<span style='text-align:center'>No Data Available</span>)");
							}
						}
					}
				}
			}
		
		}else{
			$("#boothLevelWiseBasicCommittees").html("No Data Available");
		}  
		$(".boothVillageWardUl").slick({
			 slide: '.customLi',
			 slidesToShow: 1,
			 slidesToScroll: 1,
			 infinite: false,
			 swipeToSlide:false,
			 swipe:false,
			 touchMove:false
		}); 
		$(".moreBlocksDistrictlevels").show();
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
		getBoothcommitteesPerformanceCohort(tdpCommitteeLevelIdsClickedArray); 
		
	}
	
	function buildBoothCommitteesPerformanceCohort(result){
		$("#boothDistrictWiseCommitteesReport").html('');
		if(result != null && result.length > 0){
			var str='';
			
			for(var i in result){
				str+=result[i].name;
				str+='<div id="boothMainCommittees'+i+'" class="chartLiD" style="height:300px" ></div>';
			}
									
		}
		$("#boothDistrictWiseCommitteesReport").html(str);
		
		
		
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
						
						districtWiseCompletedPercArray.push(result[i].subList[j].completedPerc);
						//if(result[i].subList[j].startedPerc !=null && result[i].subList[j].startedPerc >0){
							districtWiseStartedPercArray.push(result[i].subList[j].startedPerc);
						//}
						//if(result[i].subList[j].notStartedPerc !=null && result[i].subList[j].notStartedPerc >0){
							districtWiseNotStartedPercArray.push(result[i].subList[j].notStartedPerc);
						//}
					}
			}
						$(function () {
							$('#boothMainCommittees'+i+'').highcharts({
								colors: ['#338ecf','#7DDF7D','#C53A36',],
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
								},{
									name: 'Not Started',
									data: districtWiseNotStartedPercArray
								}]
							});
						});
				
			
		}
	}else{
		$("#boothDistrictWiseCommitteesReport").html("No Data Available")
	}
		
		$("#boothDistrictWiseCommitteesReport").each(function(){
			var scrollengthDiv = $(this).find(".chartLiD").length;
			if(scrollengthDiv >= 4){
				$(".verticalScrollBar").mCustomScrollbar({setHeight:'560px'})
				
			}else{
				$(".verticalScrollBar").css("height","auto");
			
			}
		});
}
	
	function buildgetboothChildUserTypesByItsParentUserType(result){
		$("#boothChildUserTypeDetailsDiv").html('');
		var str='';
		 str+='<ul class="comparisonSelect">';
		 
		 var firstChildUserTypeIdString;
		 var userType;
		 if(result !=null && result.length >0){
			 firstChildUserTypeIdString = result[0].shortName;
			 userType=result[0].userType;
			 for(var i in result){
				 str+='<li attr_usertypeid="'+result[i].shortName+'" attr_userType=\''+result[i].userType+'\' class="boothchildUserTypeCls">'+result[i].userType+'<span class="closeIconComparison"></span></li>';
			 }
		 }
		str+='</ul>';
		$("#boothChildUserTypeDetailsDiv").html(str);
		if(finalGlobalUrl=="committeesMonitoringAction"){
			var lengthSize = result.length;
			firstChildUserTypeIdString = result[lengthSize-1].shortName; 
			userType = result[lengthSize-1].userType;
		 $(".comparisonSelect li:last-child").addClass("active");	
		}else{
		 $(".comparisonSelect li:first-child").addClass("active");	
		}
		getBoothSelectedChildUserTypeMembers(firstChildUserTypeIdString,userType);
		
	}
	
		
	function buildgetBoothSelectedChildUserTypeMembers(result,childUserType){
		
		$("#boothSelectedUserTypeDetailsDiv").html('');
	var str='';
	    var firstActivityMemberId;
		var firstUserTypeId;
		var firstChildActivityMemberId = "boothDirectChildActivityMemberDiv";
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
			 str+='<th>Not Started</th>';
			 str+='<th>Started</th>';
			 str+='<th>Completed</th>';
			 str+='<th>%</th>';
		 str+='</thead>';
		 str+='<tbody>';
		 var rank=1;
		  for(var i in result){
			str+='<tr style="cursor:pointer;" class="compareActivityMemberCls1" attr_selectedusertype="'+result[i].userType+'"  attr_id="boothDirectChildActivityMemberDiv"  attr_selectedmembername="'+result[i].name+'"  attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+'>';
			 str+='<td><span class="counts">'+rank+'</span></td>';
			 str+='<td>'+result[i].name+'</td>';
			 str+='<td>'+result[i].userType+'</td>';
			 str+='<td>'+result[i].locationName+'</td>';
			if(result[i].totalCount !=null && result[i].totalCount >0){
				str+='<td>'+result[i].totalCount+'</td>';
			}else{
				str+='<td> - </td>';
			}
			if(result[i].notStartedCount !=null && result[i].notStartedCount >0){
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
	    $("#boothSelectedUserTypeDetailsDiv").html(str);
		
		$("#committeeMemberDataTblId").dataTable({
			"aaSorting": [],
			"iDisplayLength" : 5	
		});
		//getBoothTopPoorPerformancecommittees(firstActivityMemberId,firstUserMemberName,firstuserType);
		getBoothTopPoorCommitteeLocations(firstActivityMemberId,firstUserMemberName,firstuserType);
	 }else{	
	   if(result !=null && result.length >0){
		str+='<ul class="list-inline slickPanelSliderCommittee">';
		var rankVar =0;
		for(var i in result){
			rankVar =rankVar+1;
			var locationNamevaraiable =result[i].locationName;
			if(i == 0){
				str+='<li  style="cursor:pointer;" class="compareActivityMemberCls1 panelActiveSlick" attr_id ="boothDirectChildActivityMemberDiv" attr_selectedmembername="'+result[i].name+'" attr_selectedusertype="'+result[i].userType+'" attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' >';
			}else{
				
				str+='<li  style="cursor:pointer;" class="compareActivityMemberCls1" attr_id ="boothDirectChildActivityMemberDiv" attr_selectedmembername="'+result[i].name+'" attr_selectedusertype="'+result[i].userType+'" attr_activitymemberid='+result[i].activityMemberId+'  attr_usertypeid='+result[i].userTypeId+' >';
				
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
								str+='<th>Not Started</th>';
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
							if(result[i].notStartedCount !=null && result[i].notStartedCount >0){
								str+='<td>'+result[i].notStartedCount+'</td>';
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
		
		$("#boothSelectedUserTypeDetailsDiv").html(str);
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
		getBoothDirectChildActivityMemberCommitteeDetails(firstActivityMemberId,firstUserTypeId,firstUserMemberName,firstuserType,firstChildActivityMemberId);
		//getBoothTopPoorPerformancecommittees(firstActivityMemberId,firstUserMemberName,firstuserType);
		getBoothTopPoorCommitteeLocations(firstActivityMemberId,firstUserMemberName,firstuserType);
	}
	}
	/* else{
		$("#SelectedUserTypeDetailsDiv").html("No Data Available");
	} */
	
	}
	
	$(document).on("click",".boothchildUserTypeCls",function(){
		if($(this).hasClass("active")){
			$("#boothDirectChildActivityMemberDiv").html('');
			$("#boothTopPoorPerformanceDiv").html('');
			$("#boothTopPoorLocationsDiv").html(''); 
			var childUserTypeIdString = $(this).attr("attr_usertypeid");
			var childUserType = $(this).attr("attr_userType");
			 getBoothSelectedChildUserTypeMembers(childUserTypeIdString,childUserType);
		}else{
			$("#boothSelectedUserTypeDetailsDiv").html('');
			$("#boothDirectChildActivityMemberDiv").html('');
			$("#boothTopPoorPerformanceDiv").html('');
			$("#boothTopPoorLocationsDiv").html(''); 
		}
		
	});
	
	function buildgetBoothUserTypeWiseCommitteesCompletedCountsForTopFiveStrongResults(result){
		$("#boothUserTypeWiseCommitteesForTopFiveStrongAndPoorDiv").html('');
		
		if(result != null && result.length > 0){
			for(var i in result){
				var candidateNameAndCompletedCountArray = [];
				var countVar =0;
				var candidateNameStartedCountArray =[];
				var candidateNameCompletedCountArray =[];
				var candidateNameNotStartedCountArray =[];
				var candidateNameArray=[];
				if(result[i] !=null && result[i].length>0){
					for(var j in result[i]){
							candidateNameArray.push(result[i][j].name);
							//console.log(result[i][j].name);
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
							
						candidateNameStartedCountArray.push(obj2);
						candidateNameCompletedCountArray.push(obj1);
						candidateNameNotStartedCountArray.push(obj3);
						
							
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}
				}
				
				if(result[i][j].startedPerc !=0 || result[i][j].notStartedPerc !=0){
					var str='';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					if(result[i][0].userTypeId == 4 || result[i][0].userTypeId == 11){
						str+='<h5 class="text-capital">ORGANIZING SECRETARY / SECRETARY</h5>';
					}else{
						str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';
					}	
					str+='<div id="genSecBooth'+i+'" style="height:170px;"></div>';
					str+='</div>'
					$("#boothUserTypeWiseCommitteesForTopFiveStrongAndPoorDiv").append(str);
					
					$(function () {
						 $("#genSecBooth"+i).highcharts({
							colors: ['#338ecf','#7DDF7D','#C53A36'],
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
									data: candidateNameCompletedCountArray
								},{
									name: 'Not Started',
									data: candidateNameNotStartedCountArray
								}],
						 
						});
					});
				} else{
					$("#genSecBooth"+i).html("No Data Available");
					$("#genSecBooth"+i).css("height","35px");
						
				} 
				
			}
			
		}else{
			//$("#boothUserTypeWiseCommitteesForTopFiveStrongAndPoorDiv").html("No Data Available");
			$("#boothUserTypeWiseCommitteesForTopFiveStrongAndPoorDiv").html("");
		}
		
	}
	
	function buildgetBoothUserTypeWiseCommitteesCompletedCountsForTopFivePoorResults(result){
		
		$("#boothUserTypeWiseCommitteesForTopFiveStrongAndPoorDiv").html('');
		
		if(result != null && result.length > 0){
			for(var i in result){
				
				var candidateNameAndCompletedCountArray =[];
				var candidateNameStartedCountArray =[];
				var candidateNameNotStartedCountArray =[];
				var candidateNameArray=[];
				var countVar = 0;
				if(result[i] !=null && result[i].length  >0){
					for(var j = result[i].length -1; j >= 0; j--){
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
						candidateNameArray.push(result[i][j].name);
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}
				}
			  		
				//if( result[i][j].completedPerc !=0){
					
					var str='';
					
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
						if(result[i][0].userTypeId == 4 || result[i][0].userTypeId == 11){
						str+='<h5 class="text-capital">ORGANIZING SECRETARY / SECRETARY</h5>';
						}else{
							str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';
						}
					str+='<div id="genSecBooth1'+i+'" class="m_top20" style="height:170px;"></div>';
					str+='</div>';
				
					$("#boothUserTypeWiseCommitteesForTopFiveStrongAndPoorDiv").append(str);
					
					var getWidth = $("#genSecBooth1"+i).parent().width()+'px';
					$("#genSecBooth1"+i).width(getWidth);
					$(function () {
						 $("#genSecBooth1"+i).highcharts({
							colors: ['#338ecf','#7DDF7D','#C53A36'],
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
									name: 'Completed',
									data: candidateNameAndCompletedCountArray
								},{
									name: 'Started',
									data: candidateNameStartedCountArray
								},{
									name: 'Not Started',
									data: candidateNameNotStartedCountArray
								}],
						 
						});
					});
				// }else{
					// $("#genSecBooth1"+i).html("No Data Available");
					// $("#genSecBooth1"+i).css("height","35px");
						
				// } 
				
			}
			
		}else{
			$("#boothUserTypeWiseCommitteesForTopFiveStrongAndPoorDiv").html("No Data Available");
		}
		
	}
	$(document).on("click",".boothCommitteeTopPoorCls",function(){
		var memberType=$(this).attr("attr_value");
		 if(memberType != null && memberType == "strong"){
			buildgetBoothUserTypeWiseCommitteesCompletedCountsForTopFiveStrongResults(globalUserWiseMemberRslt); 
		 }else if(memberType == "poor"){
			buildgetBoothUserTypeWiseCommitteesCompletedCountsForTopFivePoorResults(globalUserWiseMemberRslt)
		 }
	});

	$(document).on("click",".topFivePoorResults",function(){
		$("#userTypeWiseCommitteesForTopFiveStrongDiv").hide();
		$("#userTypeWiseCommitteesForTopFivePoorDiv").show();
		getUserTypeWiseCommitteesCompletedCounts2();
	})
	$(document).on("click",".topFiveStrongResults",function(){
		$("#userTypeWiseCommitteesForTopFiveStrongDiv").show();
		$("#userTypeWiseCommitteesForTopFivePoorDiv").hide();
		getUserTypeWiseCommitteesCompletedCounts2();
	})
	
	
	$(document).on("click",".compareActivityMemberCls1",function(){
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
		  //getBoothTopPoorPerformancecommittees(activityMemberId,selectedMemberName,selectedUserType);
		  getBoothTopPoorCommitteeLocations(activityMemberId,selectedMemberName,selectedUserType);
		 }else{ 
	     getBoothDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
		 //getBoothTopPoorPerformancecommittees(activityMemberId,selectedMemberName,selectedUserType);
		 getBoothTopPoorCommitteeLocations(activityMemberId,selectedMemberName,selectedUserType);
		}
	})
	function buildgetBoothDirectChildActivityMemberCommitteeDetails(result,selectedMemberName,selectedUserType,childActivityMemberId){
		$("#"+childActivityMemberId).html('');
		var str ='';
		
		if(result != null && result.length >0){
			var rankVar =0;
			str+='<h4><span  class="text-capital">'+selectedMemberName+'</span> - <span class="text-capitalize">'+selectedUserType+'</span></h4>';
			if(childActivityMemberId != "boothDirectChildActivityMemberDiv"){
				str+='<span class="removeSelecUserType pull-right" attr_removeSelecUserType = "'+childActivityMemberId+'" style="margin-top: -5px;"><i class="glyphicon glyphicon-remove"></i></span>';
			}
			
				if(childActivityMemberId != "boothDirectChildActivityMemberDiv")
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
						str+='<th>Not Started</th>';
						str+='<th>started</th>';
						str+='<th>completed</th>';
						str+='<th>%</th>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result){
						rankVar = rankVar+1;
						 var locationNamevar = result[i].locationName;
						str+='<tr class="boothcompareLowLevelActivityMemberCls"  attr_activitymemberid = "'+result[i].activityMemberId+'" attr_usertypeid = "'+result[i].userTypeId+'" attr_selectedmembername = "'+result[i].name+'" attr_selectedusertype = "'+result[i].userType+'">';
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
			if(childActivityMemberId == "boothDirectChildActivityMemberDiv"){
				$("#"+childActivityMemberId).html("<h5><span  class='text-capital'>"+selectedMemberName+"</span> - <span class='text-capitalize'>"+selectedUserType+"</span> - ( No Data Available )</h5>");
			}
			
		}
	
	}
	 
	 $(document).on("click",".boothcompareLowLevelActivityMemberCls",function(){
		 $(this).closest('tr').next('tr.showHideTr').show(); 
		 
		var activityMemberId = $(this).attr("attr_activitymemberid");  
		var userTypeId = $(this).attr("attr_usertypeid"); 
		var selectedMemberName = $(this).attr("attr_selectedmembername");  
		var selectedUserType = $(this).attr("attr_selectedusertype");  
		var childActivityMemberId = $(this).closest('tr').next('tr.showHideTr').attr("attr_id");  
		if(selectedUserType != null && selectedUserType.trim()=="MLA/CI" || selectedUserType.trim()=="MLA" || selectedUserType.trim()=="CONSTITUENCY INCHARGE"){
		  //getBoothTopPoorPerformancecommittees(activityMemberId,selectedMemberName,selectedUserType);
		  getBoothTopPoorCommitteeLocations(activityMemberId,selectedMemberName,selectedUserType);
		 }else{ 
	     getBoothDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId,selectedMemberName,selectedUserType,childActivityMemberId);
		 //getBoothTopPoorPerformancecommittees(activityMemberId,selectedMemberName,selectedUserType);
		 getBoothTopPoorCommitteeLocations(activityMemberId,selectedMemberName,selectedUserType);
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
	
	function buildgetBoothTopPoorPerformancecommittees(result,selectedMemberName,selectedUserType){
		$("#boothTopPoorPerformanceDiv").html('');
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
		$("#boothTopPoorPerformanceDiv").html(str);
		$('.progressCustom').tooltip()
	
		
	}
	
	function buildBoothTopPoorCommitteeLocations(result,selectedMemberName,selectedUserType){
		$("#boothTopPoorLocationsDiv").html('');
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
			$("#boothTopPoorLocationsDiv").html(str);
			$('.progressCustom').tooltip()
		}/* else{
			$("#topPoorLocationsDiv").html("<span class='color_333 pad_5 bg_CC text-capital'>top five <span class='text-danger'>poor</span> locations - (<span style='font-size:11px;'><i> "+selectedMemberName+" - "+selectedUserType+"</i></span>) - (No Data Available)</span>");
		}	 */		
			
	}
	
	
	// DETAILD BLOCK : clicked on '... dots' and 'detailed block' or 'click on slick' START
	
	//... dots.//moreBoothBlocksDetailAndComp //moreBlocksDistrictlevels // moreBoothBlocks1
	var isCommitteeMoreBlockExpand = false;
	var isCommitteeMoreBlockClosed= false;
	$(document).on("click",".moreBlocksIcons",function(){
		isCommitteeMoreBlockExpand = true;
		isCommitteeMoreBlockClosed = false;
		$(this).addClass("unExpandBlocksBoothC");
		$(".moreBoothBlocksDetailAndComp").show();
		$(".moreBlocksDistrictlevels").show();
		//customBuildGraph();
		setTimeout(function(){ committeeDetailsBlockdefaultCall11(); }, 1000);
		
		
	});
	function committeeDetailsBlockdefaultCall11(){
		if(isCommitteeMoreBlockExpand==true && isCommitteeMoreBlockClosed==false){
			getBoothLevelWiseBasicCommitteesCountReport();
		}	
	}
	
	$(document).on("click",".unExpandBlocksBoothC",function(){
		$(this).removeClass("unExpandBlocksBoothC");
		isCommitteeMoreBlockClosed = true;
		$(".moreBoothBlocks1").hide();
		$(".moreBoothBlocksDetailAndComp").hide();
		$(".moreBlocksDistrictlevels").hide();
		$(".comparisionBlocks").removeClass("active");
		$(".detailedBlocks").addClass("active");
	});
	$(document).on("click",".detailedBlocks",function(){
		$(".moreBoothBlocks1").hide();
		$(".moreBlocksDistrictlevels").show();
		getBoothLevelWiseBasicCommitteesCountReport();
	});
	
	
	$(document).on("click",".comparisionBlocks",function(){
		$("#boothSelectedUserTypeDetailsDiv").html(''); 
		$("#boothDirectChildActivityMemberDiv").html('');
		$("#boothTopPoorPerformanceDiv").html('');
		$("#boothTopPoorLocationsDiv").html(''); 
		
		$(".moreBoothBlocks1").show();
		$(".moreBlocksDistrictlevels").hide();
		
		getBoothAllItsSubUserTypeIdsByParentUserTypeId();
		
	});
	
	$("#boothLevelWiseBasicCommittees").on("click",".slick-next,.slick-prev",function(){
		var currentSliderLevel = $(".slick-current").find("h4").html();
		
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
		
		getBoothcommitteesPerformanceCohort(tdpCommitteeLevelIdsClickedArray);
		
	});
	
	// clicked on '... dots' and 'detailed block' or 'click on slick' END
	
	//filtering in tdp committees start.
	$(".basicBoothCommittessDiv").click(function(event){
		
		var allSelectedCommitteeIdsArray = [];
		//district level
		$(".districtBoothCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				allSelectedCommitteeIdsArray.push( $(this).val() );
			}
		}); 
		//mandal/town/division level.
		$(".mandalBoothCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				allSelectedCommitteeIdsArray.push( $(this).val() );
			}
		}); 
		//village/ward level.
		$(".villageBoothCommitteecheckBoxClass").each(function(){
			if($(this).is(':checked')){
				allSelectedCommitteeIdsArray.push( $(this).val() );
			}
		}); 
		
		if(allSelectedCommitteeIdsArray!=null && allSelectedCommitteeIdsArray.length>0){
			 $(".basicBoothCommitteesBlockDropDown").hide();	
			 boothDefaultCommitteeCalls();
		}else{
			//show error msg
			$("#committeeErrMsg").modal("show");
		}
    });
	
	
	function boothDefaultCommitteeCalls(){
		
		//boothDefaultCommitteeCalls();//srujana
		getUserTypeWiseBoothCommitteesInchargeDetails();
		
		if($(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".boothCommitteeTopPoorCls").each(function(){
				var topvalue = $(this).attr("attr_value");
				if(topvalue == "poor"){
					$(".removeactive").removeClass("active");
				}else{
					$(".addactive").addClass("active");
				}
			});
			   getUserTypeWiseCommitteesCompletedCounts2();	
			

		}
		var URLArr = windowUrl.split('/');
		var finalURL = URLArr[parseInt(URLArr.length) - 1].replace('.action','');
		if(finalURL =="committeesMonitoringAction")
		{
			getUserTypeWiseCommitteesCompletedCounts2();	
		}
		if($(".moreBlocksIcons").hasClass("unExpandBlocksBoothC")){
			if($(".detailedBlocks").hasClass("active")){
				$("#boothLevelWiseBasicCommittees").html('');
				$(".moreBlocksDistrictlevels").hide();
				
				getBoothLevelWiseBasicCommitteesCountReport();//srujana
			}
			if($(".comparisionBlocks").hasClass("active")){
				$("#boothDirectChildActivityMemberDiv").html('');
				$("#boothTopPoorPerformanceDiv").html('');
				$("#boothTopPoorLocationsDiv").html('');
				getBoothAllItsSubUserTypeIdsByParentUserTypeId();
			}
		}
	}
	/* function boothCommitteeBasicCall(){
			getBoothCommitteesBasicCountReport(); 
	}  */
	/* $(document).on("click",".comparisonSelect li",function(){
		if($(this).hasClass("active") == true)
		{
			$(this).removeClass("active");
		}else{
			$(".comparisonSelect li").removeClass("active");
			$(this).addClass("active");
		}
	}); */
	$(document).on("click",function(){
		$(".documentCloseClass").hide();
	});
	$(document).on("click",".documentCloseClass",function(e){
		e.stopPropagation();
	});
	
	$("#dateRangeBoothId").daterangepicker({
		opens: 'left',
	    startDate: moment(),
        endDate: moment(),
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
			'Today': [moment(), moment()],
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'This Year': [moment().startOf('Year'), moment()],
		   'Overall' : [moment().subtract(30, 'years').startOf('year'), moment()],
        }
	})
	 $('#dateRangeBoothId').on('apply.daterangepicker', function(ev, picker) {
		var customStartDateBoothCommittee = picker.startDate.format('DD/MM/YYYY');
		var customEndDateBoothCommittee = picker.endDate.format('DD/MM/YYYY');
		var selectedDate = customStartDateBoothCommittee+ " - "+customEndDateBoothCommittee;
			//do something, like clearing an input
		boothDefaultCommitteeCalls();
		$(".boothCommitteesDate").html("( "+selectedDate+" )");
	});
	 		
	function getBoothCadreEnrolmentYears(){
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
						$("#tdpBoothCommitteeYearId").append('<option value="1" selected="selected">'+result[i].electionYear+'</option>');
						/*if(parseInt(result[i].id) >1 ){
							if(parseInt(result[i].id) == 2)
								$("#tdpBoothCommitteeYearId").append('<option value='+result[i].id+' selected="selected">'+result[i].electionYear+'</option>');
							else
								$("#tdpBoothCommitteeYearId").append('<option value='+result[i].id+'>'+result[i].electionYear+'</option>');
						}*/
					}
				}
				if(finalGlobalUrl=="committeesMonitoringAction"){
				    $(".comparisionBlocks ").trigger("click");
				}
				getBoothCommitteeDetailsBiEnrollement(2);
			});
		}
		$(document).on("change","#tdpBoothCommitteeYearId",function(){
			getBoothCommitteeDetailsBiEnrollement(0);
		});
		
		function getBoothCommitteeDetailsBiEnrollement(id){
			var enrollmentIdsArr = new Array();
			if(id == 0)
				enrollmentIdsArr.push($("#tdpBoothCommitteeYearId").val());
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
						
						$('#dateRangeBoothId').data('daterangepicker').setStartDate(fDate+'/'+fMonth+'/'+fYear);
						$('#dateRangeBoothId').data('daterangepicker').setEndDate(tDate+'/'+tMonth+'/'+tYear);
						$('#dateRangeBoothId').val(fDate+'/'+fMonth+'/'+fYear+' - '+tDate+'/'+tMonth+'/'+tYear)
					}
					boothDefaultCommitteeCalls();
				}
			});
		}
		$(document).on("change","#tdpBoothCommitteeYearId",function(){
			/* alert(12);
			var id =0; */
			getBoothCommitteeDetailsBiEnrollement(0);
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
		
 function getBoothAflCommitteeCount(state,level,committeeType){
	//	var startDate=$(".dp_startDate").val();
	//	var endDate=$(".dp_endDate").val();
	var dateStr = $('#dateRangeBoothId').val();		
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
		committeeSpanTypeIdsArr.push($('#tdpBoothCommitteeYearId').val());
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
	
	function getBoothMainCommitteeMembersCount(state,level,type,committeeId,committeeType){
		
		//var startDate=$(".dp_startDate").val();
		//var endDate=$(".dp_endDate").val();
		var dateStr = $('#dateRangeBoothId').val();
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
		committeeSpanTypeIdsArr.push($('#tdpBoothCommitteeYearId').val());
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
	
	
	function getBoothCommitteeInchargesCount(){
			$("#boothCommitteesAssdDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
   var state = globalState;
   if(state == "AP"){
	   state=1;
   }else if(state == "TS"){
	   state=36;
   } 
      var dateString = "";//$("#dateRangeBoothId").val();
    
     var committeeEnrollmentYearArray = new Array();
	 if($("#tdpBoothCommitteeYearId").val() != null)
       committeeEnrollmentYearArray.push($("#tdpBoothCommitteeYearId").val());
     var jsObj ={  
            activityMemberId : globalActivityMemberId,
            state:state,
            dateString : dateString,
            committeeEnrollmentYearArray :committeeEnrollmentYearArray
           }
    
    $.ajax({
      type : 'POST',
      url : 'getBoothCommitteeInchargesCountAction.action',
      dataType : 'json',
      data : {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result != null && result.length >0){
			buildBasicCommitteesCountDetails(result);
		}
     });
  }
  
  function buildBasicCommitteesCountDetails(result){
	var str="";
	var totalBooths =$("#totalBoothsCount").text();
	str+='<table class="table table-condensed table-bordered">';
		str+='<thead class="bg_CC">';
			str+='<th>Booth Incharge Assigned</th>';
			str+='<th>Booths</th>';
			str+='<th>No of booths Incharges</th>';
			str+='<th>Male</th>';
			str+='<th>Female</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
			var buildStr = "";
			var buildStr1 = "";
			var buildStr2 = "";
			var buildStr3 = "";
			var boothInchargesAssnd = result[i].boothInchargesAssnd;
			if(boothInchargesAssnd == "5-10" || boothInchargesAssnd == "10-Above"){
				buildStr=" "+boothInchargesAssnd+" Range Members added booths count.";
				buildStr1=" "+boothInchargesAssnd+" Range  added Members count.";
				buildStr2=" "+boothInchargesAssnd+" Range  added Members Male count.";
				buildStr3=" "+boothInchargesAssnd+" Range  added Members Female count.";
			}else{
				buildStr=" Only "+boothInchargesAssnd+" Member(s) added booths count.";
				buildStr1=" Total added Members Count in "+boothInchargesAssnd+" Member(s) added booths ."
				buildStr1=" Total added Members Count in "+boothInchargesAssnd+" Member(s) added booths ."
				buildStr2=" Total added Members Male Count in "+boothInchargesAssnd+" Member(s) added booths ."
				buildStr3=" Total added Members Female Count in "+boothInchargesAssnd+" Member(s) added booths ."
			}
				str+='<td>'+result[i].boothInchargesAssnd+'</td>';
				if(parseInt(result[i].totalBooths) > 0){
					var assigdPer = ((parseInt(result[i].totalBooths)*100.0)/totalBooths).toFixed(2);
					
					str+='<td> <span class="tooltipCls" title="'+buildStr+'">  '+parseInt(result[i].totalBooths)+'&nbsp;&nbsp;<small class="text-danger responsiveFont "  >('+assigdPer+')%</small> </span></td>';
					
				}else {
					str+='<td>-</td>';
				}
				if(result[i].tdpCadreId != null)
					str+='<td> <span class="tooltipCls" title="'+buildStr1+'"> '+result[i].tdpCadreId+'</span></td>';
				else
					str+='<td>-</td>';
				if(result[i].maleCnt > 0){
					var malePer = ((result[i].maleCnt*100.0)/result[i].tdpCadreId).toFixed(2);
					str+='<td> <span class="tooltipCls" title="'+buildStr2+'"> '+result[i].maleCnt+'&nbsp;&nbsp;<small class="text-danger responsiveFont">('+malePer+')%</small></span></td>';
				}else {
					str+='<td>-</td>';
				}
				if(result[i].femaleCnt > 0){
					var femalePer = ((result[i].femaleCnt*100.0)/result[i].tdpCadreId).toFixed(2);
					str+='<td> <span class="tooltipCls" title="'+buildStr3+'"> '+result[i].femaleCnt+'&nbsp;&nbsp;<small class="text-danger responsiveFont">('+femalePer+')%</small></span></td>';
				}else{ 
					str+='<td>-</td>';
				}
			str+='</tr>';		
		}
	   
		str+='</tbody>';
	str+='</table>';
	  
	$("#boothCommitteesAssdDiv").html(str);
	$('.tooltipCls').tooltip();
  }
  function getUserTypeWiseBoothCommitteesInchargeDetails()
  {

	$("#boothBasicCommitteeCountsDiv").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var state = globalState;
	    var dateString = "";//$("#dateRangeBoothId").val();
	var committeeEnrollmentYearArray = new Array();
	if($("#tdpBoothCommitteeYearId").val() != null)
	  committeeEnrollmentYearArray.push($("#tdpBoothCommitteeYearId").val());

    var jsObj ={  
		activityMemberId : globalActivityMemberId,
		state:state,
		dateString : dateString,
		committeeEnrollmentYearArray :committeeEnrollmentYearArray
	}
    
    $.ajax({
		type : 'POST',
		url : 'getUserTypeWiseBoothCommitteesInchargeSummaryAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
    }).done(function(result){
		buildUserTypeWiseBoothCommitteesInchargeDetails(result)
    });
  }
  function buildUserTypeWiseBoothCommitteesInchargeDetails(result)
  {
	  console.log(result);
		$("#boothBasicCommitteeCountsDiv").html('');
		var str='';
		
		var locationLevelNameArray =[];
		if(result !=null){
			
			var startedPerc='0.00';
			var completedPerc='0.00';
			var notstartedPerc='0.00';
			str+='<h4 class="text-capital bg_49 pad_custom">booth committee Incharges</h4>';
				
			str+='<table class="table table-condensed">';
			   str+='<tr>';
					str+='<td>';
					if(result.totalCount != null){
						str+='<h4 class="responsiveFont" id="totalBoothsCount">'+result.totalCount+'</h4>';
					}else{
						str+='<h4 class="responsiveFont"> - </h4>';
					}
						str+='<p class="text-muted text-capitalize responsiveFont m_top10">total  </p>';
					str+='</td>';
					str+='<td>';
					if(result.startedCount !=null){
						startedPerc = (parseInt(result.startedCount)*100/result.totalCount).toFixed(2);
						str+='<h4 class="responsiveFont">'+result.startedCount+'</h4>';
						str+='<small class="text-primary responsiveFont">('+startedPerc+'%)</small>';
						str+='<p class="text-muted text-capitalize responsiveFont">Started</p>';
					}else{
						str+='<h4 class="responsiveFont"> - </h4>';
						str+='<p class="text-muted text-capitalize responsiveFont"> Started  </p>';
					}
					str+='</td>';
					str+='<td>';
					if(result.completedCount !=null){
						completedPerc = (parseInt(result.completedCount)*100/result.totalCount).toFixed(2);
						str+='<h4 class="responsiveFont">'+result.completedCount+'</h4>';
						str+='<small class="text-success responsiveFont">('+completedPerc+'%)</small>';
						str+='<p class="text-muted text-capitalize responsiveFont">Completed</p>';
					}else{
						str+='<h4 class="responsiveFont"> - </h4>';
						str+='<p class="text-muted text-capitalize responsiveFont"> Completed  </p>';
					}
					str+='</td>';
					
					str+='<td>';
					if(result.notStartedCount !=null){
						notstartedPerc = (parseInt(result.notStartedCount)*100/result.totalCount).toFixed(2);
						str+='<h4 class="responsiveFont">'+result.notStartedCount+'</h4>';
						str+='<small class="text-danger responsiveFont">('+notstartedPerc+'%)</small>';
						str+='<p class="text-muted responsiveFont text-capitalize">Not Started  </p>';
					}else{
						str+='<h4 class="responsiveFont"> - </h4>';
						str+='<p class="text-muted responsiveFont text-capitalize">Not Started  </p>';
					}
					str+='</td>';
				str+='</tr>';
			str+='</table>';
				
				$("#boothBasicCommitteeCountsDiv").html(str);
		}else{
			$("#boothBasicCommitteeCountsDiv").html("No Data Available");
		} 
      getBoothCommitteeInchargesCount();		
  }