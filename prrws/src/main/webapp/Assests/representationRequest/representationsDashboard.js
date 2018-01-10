var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});
getDeptIdsListBYUserIdsLst();
function getDeptIdsListBYUserIdsLst(){
	$("#designationrepresent").html('');
	  var json = {
			 
	  };
	$.ajax({              
		type:'POST',    
		url: 'getDeptIdsListBYUserIdsLst',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		
	});	

}

//getPmDeptStatusIdsByUserIdsLst();
function getPmDeptStatusIdsByUserIdsLst(){
	
	  var json = {
			 
	  };
	$.ajax({              
		type:'POST',    
		url: 'getPmDeptStatusIdsByUserIdsLst',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		
	});	

}
getCompleteOrStatusOverviewDetails();
function getCompleteOrStatusOverviewDetails(){
	$("#completeOverviewId").html(spinner);
	$("#statusOverviewId").html(spinner);
	$("#myActionsId").html(spinner);
var json = {
		 fromDate :"",
		 toDate:""
		}           
	$.ajax({              
		type:'POST',    
		url: 'getCompleteOrStatusOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		//$("#completeOverviewId").html(spinner);
		$("#statusOverviewId").html(spinner);
		$("#completeOverviewId").html(spinner);
			buildMyActionsDetails(result);
			buildCompleteOrStatusOverviewDetails(result);
			buildStatusOverviewDetails(result)
		
	});	
}
function buildStatusOverviewDetails(result){
	var str='';
	if(result != null && result.list != null && result.list.length >0){
	
		for(var i in result.list){
		str+='<div class="col-sm-2 status_blocks">';
										str+='<div class="panel panel-default">';
											//str+='<div class="panel-heading" style="background-color:#FFF8EF">';
											if(result.list[i].id ==1){
												str+='<div class="panel-heading" style="background-color:#ECEEEF">';
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4363.png">';
											}else if(result.list[i].id ==2){
												str+='<div class="panel-heading" style="background-color:#FFF8EF">';
												
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4362.png">';
											}else if(result.list[i].id ==8 ){
												str+='<div class="panel-heading" style="background-color:#E5F6Ed">';
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4364.png">';
											}else if(result.list[i].id ==4 ){
												str+='<div class="panel-heading" style="background-color:#FCEDFF">';
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4369.png">';
											}else if(result.list[i].id ==5 ){
												str+='<div class="panel-heading" style="background-color:#FDE8F5">';
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4427.png">';
											}
											
												str+='<b style="padding-left:5px;font-size:13px">'+result.list[i].name+'</b></h5>';
											
												
											str+='</div>';
											str+='<div class="panel-body">';
												str+='<div class="row">';
												str+='<div class="col-sm-8" style="padding-left:5px">';
													str+='<p>Representations</p>';
													
														str+='<h4><b>'+result.list[i].totalRepresents+'</b></h4>';
													
												str+='</div>';
												str+='<div class="col-sm-4" style="padding-left:5px">';
													str+='<p>Works</p>';
													
														str+='<h4><b>'+result.list[i].noOfWorks+'</b></h4>';
													
													
												str+='</div>';
												str+='</div>';
												
											str+='</div>';
										str+='</div>';
									//str+='</div>';
									
								//str+='</div>';
									str+='</div>';
									
		}
		
		$("#statusOverviewId").html(str);
	}else{
		$("#statusOverviewId").html("No data available");
	}
}
function buildMyActionsDetails(result){
	var str='';
	if(result != null && result.statusList.length >0){
	str+='<div class="row">';
										str+='<div class="col-sm-12">';
										for(var i in result.statusList){
											if(result.statusList[i].statusType == "UserStatus"){
											str+='<div class="col-sm-5">';
												str+='<div class="media">';
													str+='<div class="media-left">';
														str+='<img src="Assests/icons/Group 4370.png">';
													str+='</div>';
													str+='<div class="media-body">';
														str+='<h4 attr_id="'+result.statusList[i].id+'"><b>'+result.statusList[i].name+'</b></h4>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
											str+='<div class="col-sm-4">';
												str+='<div class="white-block pad_5">';
													str+='<div class="media">';
														str+='<div class="media-left">';
														str+='<img src="Assests/icons/Group 4377.png">';
														str+='</div>';
														str+='<div class="media-body">';
															str+='<p>Representations</p>';
															str+='<h4><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=total&desigId=0&statusId='+result.statusList[i].id+'&deptId=0" target="_blank">'+result.statusList[i].totalRepresents+'</a></h4>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
											str+='<div class="col-sm-3">';
												str+='<div class="white-block pad_5">';
													str+='<div class="media">';
														str+='<div class="media-left">';
															str+='<img src="Assests/icons/Group 4378.png">';
														str+='</div>';
														str+='<div class="media-body">';
															str+='<p>Works</p>';
															str+='<h4><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=total&desigId=0&statusId='+result.statusList[i].id+'&deptId=0" target="_blank">'+result.statusList[i].noOfWorks+'</a></h4>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
										str+='</div>';
										
									str+='</div>';
									str+='<div class="row m_top10 ">';
									
										str+='<div class="">';
											str+='<div class="white-block">';
												str+='<h5 style="background-color:#E2F2F9;" class="grad_bg_orange">Referral wise</h5>';
												str+='<div class="row pad_10">';
													str+='<div class="col-sm-12">';
													for(var j in result.statusList[i].referrerList){
														str+='<div class="col-sm-2 petition_border">';
														str+='	<div class="">';
															str+='<p>'+result.statusList[i].referrerList[j].name.toUpperCase()+'</p>';
															str+='<h5><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referral&desigId='+result.statusList[i].referrerList[j].id+'&statusId='+result.statusList[i].id+'" target="_blank">'+result.statusList[i].referrerList[j].totalRepresents+'</a> - <a title="Works" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referral&desigId='+result.statusList[i].referrerList[j].id+'&statusId='+result.statusList[i].id+'" target="_blank">'+result.statusList[i].referrerList[j].noOfWorks+'</a></h5>';
														str+='</div>';
														str+='</div>';
													}
													str+='</div>';
												str+='</div>';
												
												str+='<div class="row pad_10">';
												str+='<div class="border-styling" style="border-top:1px solid #FFBB00"></div>';
												str+='<h5 style="background-color:#E2F2F9;margin-left:5px" class="grad_bg_orange">Subject wise</h5>';
													str+='<div class="col-sm-12">';
													for(var j in result.statusList[i].subList){
														str+='<div class="col-sm-2 petition_border">';
														str+='	<div class="">';
															str+='<p>'+result.statusList[i].subList[j].name.toUpperCase()+'</p>';
															str+='<h5><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=subject&subjId='+result.statusList[i].subList[j].id+'&statusId='+result.statusList[i].id+'" target="_blank">'+result.statusList[i].subList[j].totalRepresents+'</a> - <a title="Works" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=subject&desigId='+result.statusList[i].subList[j].id+'&statusId='+result.statusList[i].id+'" target="_blank">'+result.statusList[i].subList[j].noOfWorks+'</a></h5>';
														str+='</div>';
														str+='</div>';
													}
													str+='</div>';
													
												str+='</div>';
												str+='<div class="row pad_10">';
												str+='<div class="border-styling" style="border-top:1px solid #FFBB00"></div>';
												str+='<h5 style="background-color:#E2F2F9;margin-left:5px" class="grad_bg_orange">Department wise</h5>';
													str+='<div class="col-sm-12">';
													for(var j in result.statusList[i].deptList){
														str+='<div class="col-sm-2 petition_border">';
														str+='	<div class="">';
															str+='<p style="font-size:11px"><b>'+result.statusList[i].deptList[j].name.toUpperCase()+'</b></p>';
															str+='<h5><a title="Represents" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=department&desigId=0&statusId='+result.statusList[i].id+'&deptId='+result.statusList[i].deptList[j].id+'" target="_blank">'+result.statusList[i].deptList[j].totalRepresents+'</a> - <a title="Works" href="'+wurl+'/representationRequestEntryViewMembers?searchBy=department&desigId=0&statusId='+result.statusList[i].id+'&deptId='+result.statusList[i].deptList[j].id+'" target="_blank">'+result.statusList[i].deptList[j].noOfWorks+'</a></h5>';
														str+='</div>';
														str+='</div>';
													}
													str+='</div>';
													
												str+='</div>';
											str+='</div>';
										}
										}
										str+='</div>';
									str+='</div>';
									$("#myActionsId").html(str);
	}else{
		$("#myActionsId").html("No data available");
	}
}
function buildCompleteOrStatusOverviewDetails(result){
	
	var str ='';
	if(result != null){
	 str+='<div class="row">';
										str+='<div class="col-sm-12">';
											str+='<div class="col-sm-5">';
												str+='<div class="media">';
													str+='<div class="media-left">';
														str+='<img src="Assests/icons/Group 4370.png">';
													str+='</div>';
													str+='<div class="media-body">';
														str+='<h4><b>Total Reprasentations</b></h4>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
											str+='<div class="col-sm-4">';
												str+='<div class="white-block pad_5">';
													str+='<div class="media">';
														str+='<div class="media-left">';
															str+='<img src="Assests/icons/Group 4377.png">';
														str+='</div>';
														str+='<div class="media-body">';
															str+='<p>Representations</p>';
															str+='<h4><b>'+result.totalRepresents+'</b></h4>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
											str+='<div class="col-sm-3">';
												str+='<div class="white-block pad_5">';
												str+='	<div class="media">';
													str+='	<div class="media-left">';
															str+='<img src="Assests/icons/Group 4378.png">';
														str+='</div>';
														str+='<div class="media-body">';
														str+='	<p>Works</p>';
														str+='	<h4><b>'+result.noOfWorks+'</b></h4>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
									str+='<div class="row m_top10 ">';
										str+='<div class="">';
											str+='<div class="white-block">';
											
											str+='	<div class="row" style="padding:0px 10px 10px 10px">';
													str+='	<h5 style="background-color:#E2F2F9;margin-left:5px" class="grad_bg_lightblue">Referral wise</h5>';
													str+='<div class="col-sm-12 m_top5">';
													//str+='	<h5 style="background-color:#E2F2F9" class="grad_bg_lightblue">Referral wise</h5>';
													str+='<div class="clearfix"></div>'
													for(var i in result.referrerList){
													str+='	<div class="col-sm-2 petition_border">';
													str+='	<div class="">';
														str+='	<p attr_id='+result.referrerList[i].id+'>'+result.referrerList[i].name.toUpperCase()+'</p>';
														str+='	<h5><b title="Represents">'+result.referrerList[i].totalRepresents+'</b> - <b title="Works">'+result.referrerList[i].noOfWorks+'</b></h5>';
														str+='</div>';
														str+='</div>';
														}
													str+='</div>';
												str+='</div>';
												
												
												str+='<div class="row pad_10">';
												str+='<div class="border-styling" style="border-top:1px solid #1283C9;"></div>';
												str+='	<h5 class="grad_bg_lightblue" style="background-color:#E2F2F9;margin-left:5px">Subject wise</h5>';
												str+='	<div class="col-sm-12 m_top5">';
												//str+='	<h5 class="grad_bg_lightblue" style="background-color:#E2F2F9">Subject wise</h5>';
												str+='<div class="clearfix"></div>';
												for(var i in result.subList){
													str+='	<div class="col-sm-2 petition_border">';
													str+='	<div class="">';
														str+='	<p attr_id="'+result.subList[i].id+'">'+result.subList[i].name.toUpperCase()+'</p>';
														str+='	<h5><b title="Represents">'+result.subList[i].totalRepresents+'</b> - <b title="Works">'+result.subList[i].noOfWorks+'</b></h5>';
														str+='</div>';
														str+='</div>';
												}
													str+='</div>';
													
												str+='</div>';
												str+='<div class="row pad_10">';
												str+='<div class="border-styling" style="border-top:1px solid #1283C9;"></div>';
												str+='<h5 class="grad_bg_lightblue" style="background-color:#E2F2F9;margin-left:5px">Department wise</h5>';
												str+='	<div class="col-sm-12 m_top5">';
												//str+='<h5 class="grad_bg_lightblue" style="background-color:#E2F2F9;margin-left:5px">Department wise</h5>';
												str+='<div class="clearfix"></div>';
												for(var i in result.deptList){
													str+='	<div class="col-sm-2 petition_border">';
													str+='	<div class="">';
														str+='	<p attr_id="'+result.deptList[i].id+'" style="font-size:11px"><b>'+result.deptList[i].name.toUpperCase()+'</b></p>';
														str+='	<h5><b title="Represents">'+result.deptList[i].totalRepresents+'</b> - <b title="Works">'+result.deptList[i].noOfWorks+'</b></h5>';
														str+='</div>';
														str+='</div>';
												}
													str+='</div>';
													
												str+='</div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
									$("#completeOverviewId").html(str);
	}else{
		$("#completeOverviewId").html("No data available");
	}
}
getLeadWiseOverviewDetails();
function getLeadWiseOverviewDetails(){
	$("#completeOverviewId").html(spinner);
	$("#leadWiseOverviewId").html(spinner);
    $("#myActionsId").html(spinner);
var json = {
		 fromDate :"",
		 toDate:""
		}           
	$.ajax({              
		type:'POST',    
		url: 'getLeadWiseOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		
		 if(result!= null){
			buildLeadWiseOverviewDetails(result);
		}
		else{
			$("#leadWiseOverviewId").html("No data available");
		} 
	});	
}

function buildLeadWiseOverviewDetails(result){
	var str='';
	var count=0;
	//str+='<div class="row">';
	for(var i in result){
		count++;
		str+='<div class="col-sm-3">';
			str+='<div class="panel panel-default">';
				str+='<div class="panel-heading" style="background-color:#D6E8F2">';
					if(result[i].name.length > 28)
					{
						str+='<h5><span class="count_cls">'+count+'</span><b class="tooltipCls" title="'+result[i].name+'">'+result[i].name.substring(0,26)+'..</b></h5>';
						
					}else{
						str+='<h5><span class="count_cls">'+count+'</span><b  class="tooltipCls" >'+result[i].name+'</b></h5>';
						}
					
				str+='</div>';
				str+='<div class="panel-body">';
				
					str+='<div class="row m_top5">';
						str+='<div class="col-sm-6">';
								str+='<p>Representations</p>';
								str+='<h4><b>'+result[i].petitionIds.length+'</b></h4>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<p>Works</p>';
								str+='<h4><b>'+result[i].noOfWorks+'</b></h4>';
							str+='</div>';
					str+='</div>';
					str+='<div class="row m_top5">';
						for(var j in result[i].statusList){
							str+='<div class="col-sm-6" style="padding:2px">';
								str+='<div class="well pad_5 m_bottom_0">';
									str+='<div class="row">';
										if(result[i].statusList[j].id == 1){
											str+='<div class="col-sm-6"><span>Pending</span><br><b>'+result[i].statusList[j].petitionIds.length+'</b></div>';
										}else if(result[i].statusList[j].id == 2){
											str+='<div class="col-sm-6"><span>Completed</span><br><b>'+result[i].statusList[j].petitionIds.length+'</b></div>';
										}
										//str+='<div class="col-sm-6"><span>Pending</span><br><b>'+result[i].statusList[j].totalRepresents+'</b></div>';
										str+='<div class="col-sm-6"><span>Works</span><br><b>'+result[i].statusList[j].noOfWorks+'</b></div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						}
					str+='</div>';
					
                str+='</div>';
				
			str+='</div>';
		str+='</div>';

	}
	//str+='</div>';
	$("#leadWiseOverviewId").html(str);
	$(".tooltipCls").tooltip();
}
//generateCoveringLetterForPetition();
function generateCoveringLetterForPetition(){
 var  schemeIdsListArr =[1,9397];
var json = {
   sourceId :1,
   schemeIdsList:schemeIdsListArr,
   leadName:"10",
   groupName:"3" ,
	endValue:"5"
  }           
 $.ajax({              
  type:'POST',    
  url: 'generateCoveringLetterForPetition',
  dataType: 'json',
  data : JSON.stringify(json),
  beforeSend :   function(xhr){
   xhr.setRequestHeader("Accept", "application/json");
   xhr.setRequestHeader("Content-Type", "application/json");
  }
 }).done(function(result){
 }); 
}
$(document).on("click",".desigClsDivId",function(){
	var designationId = $(this).attr("attr_desigId");
	getReferralWiseOverviewDetails(designationId);
});
getReferralWiseOverviewDetails("");
function getReferralWiseOverviewDetails(desigId){
	if(desigId == ""){
		$("#desigWiseCountId").html(spinner);	
	}else if(desigId >=0){
		$("#desigWiseCandidatesView").html(spinner);
	}
	var desigIds = [];
	if(desigId != ""){
		desigIds.push(desigId);
	}
	var json = {
			designationIds:desigIds	  
	  };
	$.ajax({              
		type:'POST',    
		url: 'getReferralWiseOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		 if(result!= null){
			 if(desigId =="")
			 buildDesignationsWiseCount(result);
			 if(desigId >=0)
			 buildDesignationsWiseInformation(result);
		}
		else{
			$("#desigWiseCountId").html("No data available");
		} 		
		
	});	

}

 function buildDesignationsWiseCount(result){
	var str ='';
	
	  for(var i in result.subList){
		
	   str+='<div class="col-sm-3" id="column2">';
		str+='<div class="panel panel-default" style="cursor:pointer;">';
	str+='<div class="panel-heading desigClsDivId" style="background-color:#D2DEF1;" attr_desigId="'+result.subList[i].deptDesigId+'"><h4><b>'+result.subList[i].desigName+'-'+result.subList[i].subWorkIds.length+'</b></h4></div>';
			str+='<div class="panel-body" style="background-color:#E7EDF8;">';
				
				   str+='<div class="row">';			
					str+='<div class="col-sm-6">';
					str+='<p><b>Representations</b></p>';
					str+='<h4><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referral&desigId='+result.subList[i].deptDesigId+'" target="_blank">'+result.subList[i].petitionIds.length+'</a></h4>';
					str+='</div>';
					str+='<div class="col-sm-6">';
					str+='<p><b>Works</b></p>';
					str+='<h4><a  href="'+wurl+'/representationRequestEntryViewMembers?searchBy=referral&desigId='+result.subList[i].deptDesigId+'" target="_blank">'+result.subList[i].noOfWorks+'</a></h4>';
					str+='</div>';				
				str+='</div>';
					
			str+='</div>';
		str+='</div>';
	str+='</div>';
	  }
	  if(result.subList != null && result.subList.length>0){
		  getReferralWiseOverviewDetails(result.subList[0].deptDesigId);
	  }
	  
	$("#desigWiseCountId").html(str);
	} 
	
function buildDesignationsWiseInformation(result){
	
	var str='';
	str+='<div class="scrollCls">';
	
			for(var i in result.referrerList){
							str+='<div class="col-sm-12" id="status">';
								str+='<div class="col-sm-2">';
									str+='<h5><b>'+result.referrerList[i].referrerName+'</b><h5><p>'+result.referrerList[i].desigName+'</p>';
								str+='</div>';
								str+='<div class="col-sm-9">';
									str+='<div class="col-sm-3">';
										str+='<div class="col-sm-8" style="background-color:#F3F3F3; padding:6px;">';
											str+='<h6>Total Representations</h6><b>'+result.referrerList[i].petitionIds.length+'</b>';
										str+='</div>';
										str+='<div class="col-sm-4 pad_bag">';
											str+='<h6>Works</h6><b>'+result.referrerList[i].noOfWorks+'</b>';
										str+='</div>';	
									str+='</div>';
									for(var j in result.referrerList[i].statusList){
									str+='<div class="col-sm-3">';
										str+='<div class="col-sm-8" style="background-color:#F3F3F3;">';
											str+='<h6>'+result.referrerList[i].statusList[j].name+' Representations</h6><b>'+result.referrerList[i].statusList[j].petitionIds.length+'</b>';
										str+='</div>';
										str+='<div class="col-sm-4 pad_bag">';
											str+='<h6>Works</h6><b>'+result.referrerList[i].statusList[j].noOfWorks+'</b>';
										str+='</div>';
									str+='</div>';
									}
								str+='</div>';	
								for(var j in result.referrerList[i].statusList){
									if(result.referrerList[i].statusList[j].id ==3 && typeof(result.referrerList[i].statusList.estimationCost) != "undefined"){
										str+='<div class="col-sm-1" id="amount">';
										str+='<h6>Amount</h6><b>'+result.referrerList[i].statusList[j].estimationCost+'</b>';
										str+='</div>';
									}else if(result.referrerList[i].statusList[j].id ==3){
										str+='<div class="col-sm-1" id="amount">';
										str+='<h6>Amount</h6><b>-</b>';
										str+='</div>';
									}
								}
							str+='</div>';
			}
	
	str+='</div>';
		$("#desigWiseCandidatesView").html(str);
$(".scrollCls").mCustomScrollbar({setHeight:'600px'})
}