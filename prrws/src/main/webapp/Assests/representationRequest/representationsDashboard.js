var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
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
		$("#completeOverviewId").html(spinner);
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
		str+='<div class="col-sm-2">';
										str+='<div class="panel panel-default">';
											str+='<div class="panel-heading" style="background-color:#FFF8EF">';
											if(result.list[i].id ==1){
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4363.png">';
											}else if(result.list[i].id ==2){
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4362.png">';
											}else if(result.list[i].id ==8 ){
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4364.png">';
											}else if(result.list[i].id ==4 ){
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4369.png">';
											}else if(result.list[i].id ==5 ){
												str+='<h5 attr_id="'+result.list[i].id+'"><img src="Assests/icons/Group 4427.png">';
											}
											
												str+='<b>'+result.list[i].name+'</b></h5>';
											
												
											str+='</div>';
											str+='<div class="panel-body">';
												str+='<div class="col-sm-8">';
													str+='<p>Representations</p>';
													
														str+='<h4><b>'+result.list[i].totalRepresents+'</b></h4>';
													
												str+='</div>';
												str+='<div class="col-sm-4">';
													str+='<p>Works</p>';
													
														str+='<h4><b>'+result.list[i].noOfWorks+'</b></h4>';
													
													
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
											str+='<div class="col-sm-6">';
												str+='<div class="media">';
													str+='<div class="media-left">';
														str+='<img src="Assests/icons/Group 4370.png">';
													str+='</div>';
													str+='<div class="media-body">';
														str+='<h4 attr_id="'+result.statusList[i].id+'"><b>'+result.statusList[i].name+'</b></h4>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
											str+='<div class="col-sm-3">';
												str+='<div class="white-block">';
													str+='<div class="media">';
														str+='<div class="media-left">';
														str+='<img src="Assests/icons/Group 4377.png">';
														str+='</div>';
														str+='<div class="media-body">';
															str+='<p>Representations</p>';
															str+='<h4><b>'+result.statusList[i].totalRepresents+'</b></h4>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
											str+='<div class="col-sm-3">';
												str+='<div class="white-block">';
													str+='<div class="media">';
														str+='<div class="media-left">';
															str+='<img src="Assests/icons/Group 4378.png">';
														str+='</div>';
														str+='<div class="media-body">';
															str+='<p>Works</p>';
															str+='<h4><b>'+result.statusList[i].noOfWorks+'</b></h4>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
										str+='</div>';
										
									str+='</div>';
									str+='<div class="row m_top10 ">';
										str+='<div class="">';
											str+='<div class="white-block">';
												str+='<div class="row pad_10">';
													str+='<div class="col-sm-12">';
													for(var j in result.statusList[i].referrerList){
														str+='<div class="col-sm-2">';
														str+='	<div class="border_right" style="border-right: 0.5px inset #000;">';
															str+='<p>'+result.statusList[i].referrerList[j].name.toUpperCase()+'</p>';
															str+='<h4><span title="Represents">'+result.statusList[i].referrerList[j].totalRepresents+'</span> - <span title="Works">'+result.statusList[i].referrerList[j].noOfWorks+'</span></h4>';
														str+='</div>';
														str+='</div>';
													}
													str+='</div>';
												str+='</div>';
												
												str+='<div class="row pad_10">';
													str+='<div class="col-sm-12">';
													for(var j in result.statusList[i].subList){
														str+='<div class="col-sm-2">';
														str+='	<div class="border_right" style="border-right: 0.5px inset #000;">';
															str+='<p>'+result.statusList[i].subList[j].name.toUpperCase()+'</p>';
															str+='<h4><span title="Represents">'+result.statusList[i].subList[j].totalRepresents+'</span> - <span title="Works">'+result.statusList[i].subList[j].noOfWorks+'</span></h4>';
														str+='</div>';
														str+='</div>';
													}
													str+='</div>';
													
												str+='</div>';
												str+='<div class="row pad_10">';
													str+='<div class="col-sm-12">';
													for(var j in result.statusList[i].deptList){
														str+='<div class="col-sm-2">';
														str+='	<div class="border_right" style="border-right: 0.5px inset #000;">';
															str+='<p>'+result.statusList[i].deptList[j].name.toUpperCase()+'</p>';
															str+='<h4><span title="Represents">'+result.statusList[i].deptList[j].totalRepresents+'</span> - <span title="Works">'+result.statusList[i].deptList[j].noOfWorks+'</span></h4>';
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
											str+='<div class="col-sm-6">';
												str+='<div class="media">';
													str+='<div class="media-left">';
														str+='<img src="Assests/icons/Group 4370.png">';
													str+='</div>';
													str+='<div class="media-body">';
														str+='<h4><b>Total Reprasentations</b></h4>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
											str+='<div class="col-sm-3">';
												str+='<div class="white-block">';
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
												str+='<div class="white-block">';
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
											
											str+='	<div class="row pad_10">';
													str+='<div class="col-sm-12">';
													str+='	<h5 style="background-color:#E2F2F9">Referral wise</h5>';
													for(var i in result.referrerList){
													str+='	<div class="col-sm-2">';
													str+='	<div class="border_right" style="border-right: 0.5px inset #000;">';
														str+='	<p attr_id='+result.referrerList[i].id+'>'+result.referrerList[i].name.toUpperCase()+'</p>';
														str+='	<h4><span title="Represents">'+result.referrerList[i].totalRepresents+'</span> - <span title="Works">'+result.referrerList[i].noOfWorks+'</span></h4>';
														str+='</div>';
														str+='</div>';
														}
													str+='</div>';
												str+='</div>';
												
												
												str+='<div class="row pad_10">';
												str+='	<div class="col-sm-12">';
												str+='	<h5 style="background-color:#E2F2F9">Subject wise</h5>';
												for(var i in result.subList){
													str+='	<div class="col-sm-2">';
													str+='	<div class="border_right" style="border-right: 0.5px inset #000;">';
														str+='	<p attr_id="'+result.subList[i].id+'">'+result.subList[i].name.toUpperCase()+'</p>';
														str+='	<h4><span title="Represents">'+result.subList[i].totalRepresents+'</span> - <span title="Works">'+result.subList[i].noOfWorks+'</span></h4>';
														str+='</div>';
														str+='</div>';
												}
													str+='</div>';
													
												str+='</div>';
												str+='<div class="row pad_10">';
												str+='	<div class="col-sm-12">';
												str+='	<h5 style="background-color:#E2F2F9">Department wise</h5>';
												for(var i in result.deptList){
													str+='	<div class="col-sm-2">';
													str+='	<div class="border_right" style="border-right: 0.5px inset #000;">';
														str+='	<p attr_id="'+result.deptList[i].id+'">'+result.deptList[i].name.toUpperCase()+'</p>';
														str+='	<h4><span title="Represents">'+result.deptList[i].totalRepresents+'</span> - <span title="Works">'+result.deptList[i].noOfWorks+'</span></h4>';
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