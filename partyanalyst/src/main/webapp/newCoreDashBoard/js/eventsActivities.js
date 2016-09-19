$(document).on("click",".eventsIconExpand",function(){
	$(".dateRangePickerClsForEvents").toggleClass("hide");
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		getUserTypeWiseTotalInviteeAndInviteeAttendedCnt();
		$(".eventsHiddenBlock,.moreEventsBlocksIcon").show();
	}else{
		$(".eventsHiddenBlock").hide();
	}
	if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".dateRangePickerClsForTraining").addClass("hide");
		$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
		$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
		$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
		$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	}else if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
		$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerCls").toggleClass("hide");
		$(".moreBlocksIcon").removeClass("unExpandBlock");
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
	}
});

$(document).on("click",".moreEventsBlocksIcon",function(){
	$(".moreEventsBlocks").toggle();
	getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType();
});

$(document).on("click",".eventsListExpandIcon",function(){
	
	$(".eventsListExpandIcon").find("i").addClass("glyphicon-fullscreen").removeClass("glyphicon-resize-small");
	$(this).find("i").removeClass("glyphicon-fullscreen").addClass("glyphicon-resize-small");
	if($(".eventsIconExpand").find("i").hasClass("glyphicon-fullscreen"))
	{
		$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".eventsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	}
	$(".eventsIconExpand").find("i").removeClass("glyphicon-fullscreen").addClass("glyphicon-resize-small");
});
$("#dateRangeIdForEvents").daterangepicker({
	opens: 'left',
	startDate: moment().subtract(1, 'month').startOf('month'),
	endDate: moment().subtract(1, 'month').endOf('month'),
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: {
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
	   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
	   'This Month': [moment().startOf('month'), moment()],
	   'This Year': [moment().startOf('Year'), moment()]
	}
})
$('#dateRangeIdForEvents').on('apply.daterangepicker', function(ev, picker) {
  customStartDate = picker.startDate.format('DD/MM/YYYY');
  customEndDate = picker.endDate.format('DD/MM/YYYY');  
});
function getEventBasicCntDtls(){
	$("#mainEventsList").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var eventIds=[];
	eventIds.push(7);
	eventIds.push(30);
	
	var jsObj ={ 
				 activityMemberId : globalActivityMemberId,
				 stateId : 1,
				 eventIds:eventIds
				 
			  }
	$.ajax({
		type : 'POST',
		url : 'getEventBasicCntDtlsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildEventBasicCntDtls(result);
	});
}
function buildEventBasicCntDtls(result)
{
	var str=' ';
	str+='<div class="panel-group m_top10" id="accordion" role="tablist" aria-multiselectable="true">';
		for(var i in result)
		{
			str+='<div class="panel panel-default panelNewEvents">';
				str+='<div class="panel-heading" role="tab" id="headingEvents'+i+'">';
					if(i == 0)
					{
						str+='<h4 class="panel-title">';
							str+='<a role="button" class="collapseDebatesIcon collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEvents'+i+'" aria-expanded="true" aria-controls="collapseEvents'+i+'">'+result[i].name+'';
							str+='</a>';
							str+='<span class="eventsListExpandIcon" style="background-color:#fff;font-size:10px;margin-left:5px;"><i class="glyphicon glyphicon-fullscreen"></i></span>';
						str+='</h4>';
					}else{
						str+='<h4 class="panel-title">';
							str+='<a role="button" class="collapseDebatesIcon collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEvents'+i+'" aria-expanded="true" aria-controls="collapseEvents'+i+'">'+result[i].name+'';
							str+='</a>';
							str+='<span class="eventsListExpandIcon" style="background-color:#fff;font-size:10px;margin-left:5px;"><i class="glyphicon glyphicon-fullscreen"></i></span>';
						str+='</h4>';
					}
					
				str+='</div>';
				if(i == 0)
				{
					str+='<div id="collapseEvents'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingEvents'+i+'">';
				}else{
					str+='<div id="collapseEvents'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingEvents'+i+'">';
				}
					str+='<div class="panel-body pad_5">';
						str+='<div class="row">';
							str+='<div class="col-md-12 col-xs-12 col-sm-12">';
								str+='<table class="table tableTraining bg_ED">';
									str+='<tr>';
										str+='<td>';
											str+='<h4>'+result[i].inviteeCount+'</h4>';
											str+='<p class="text-capital text-muted">invited</p>';
										str+='</td>';
										str+='<td>';
											str+='<h4>'+result[i].inviteeAttendedCount+'';
											str+=' <small class="text-danger responsiveFont">'+result[i].inviteeAttendedCounPer+'</small></h4>';
											str+='<p class="text-capital text-muted">invitees attended</p>';
										str+='</td>';
										str+='<td>';
											str+='<h4>'+result[i].nonInviteeAttendedCount+'';
											str+=' <small class="text-danger responsiveFont">'+result[i].nonInviteeAttendedCountPer+'</small></h4>';
											str+='<p class="text-capital text-muted">non invitees attended</p>';
										str+='</td>';
									str+='</tr>';
								str+='</table>';
							str+='</div>';
						str+='</div>';
						
					str+='</div>';
				str+='</div>';
			str+='</div>';
		}
	str+='</div>';
	str+='';
	$("#mainEventsList").html(str)
}

function getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(){
	$("#UserTypeWiseCommittee").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var eventIds=[];
	eventIds.push(7);
	eventIds.push(30);
	
	var jsObj ={ 
				 activityMemberId : globalActivityMemberId,
				 stateId : 1,
				 eventIds:eventIds,
				 userTypeId : globalUserTypeId
				 
			  }
	$.ajax({
		type : 'POST',
		url : 'getUserTypeWiseTotalInviteeAndInviteeAttendedCntAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildUserTypeWiseTotalInviteeAndInviteeAttendedCnt(result);
	});
}

function buildUserTypeWiseTotalInviteeAndInviteeAttendedCnt(result){
	var str='';
	if(result != null && result.length > 0){
		for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<h5 class="text-capital">'+result[i][0].locationLevelName+'</h5>';
				str+='<div id="eventsCountGraph'+i+'" style="height:100px;"></div>';
			str+='</div>'
				
		}
		
	}
	$("#UserTypeWiseCommittee").html(str);
	if(result != null && result.length > 0){
		for(var i in result){
			var UserTypeWiseCommittees = [];
			var countVar =0;
			if(result[i] !=null && result[i].length>0){
				for(var j in result[i]){
					 var obj1 = {
							name: result[i][j].name,
							y: result[i][j].inviteeAttendedCntPer
						};
					UserTypeWiseCommittees.push(obj1);
					countVar =countVar+1;
					if (countVar === 5) {
						break;
					}
				}
			}
			
				
			if( result[i][j].inviteeAttendedCntPer !=0){
				$(function () {
					 $("#eventsCountGraph"+i).highcharts({
						 colors: ['#0066DC'],
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
								stacking: 'normal',
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

						tooltip: {
							headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
							pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}%</b>'
						},

						series: [{
							name: 'Completed',
							data: UserTypeWiseCommittees
						}],
					 
					});
				});
			} else{
				$("#eventsCountGraph"+i).html("No Data Available");
				$("#eventsCountGraph"+i).css("height","35px");
					
			} 
			
		}
		
	}else{
		$("#UserTypeWiseCommittee").html("No Data Available");
	}
	
}
function getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(){
	$("#eventsDistWiseCohort").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
   var eventIds=[];
	eventIds.push(7);
	eventIds.push(30);
	
	var jsObj ={ 
		 activityMemberId : globalActivityMemberId,
		 stateId : 1,
		 eventIds:eventIds,
		 userTypeId : globalUserTypeId
		 
	  }
	$.ajax({
		type : 'POST',
		url : 'getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserTypeAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(result);
	});
}
function buildLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(result)
{
	var str='';
	for(var i in result)
	{
		str+='<h4 class="panel-title">'+result[i].name+'</h4>';
		str+='<div id="eventsGraph'+i+'" style="height:120px"></div>';
	}
	$("#eventsDistWiseCohort").html(str)
	if(result != null && result.length > 0){
		for(var i in result){
			
			var inviteesCounts = [];
			var nonInviteesCounts = [];
			var candidateNames = [];
			var countVar =0;
			
			
			for(var j in result[i].locationList){
				candidateNames.push(result[i].locationList[j].name)
				inviteesCounts.push(result[i].locationList[j].inviteeAttendedCounPer)
				nonInviteesCounts.push(result[i].locationList[j].nonInviteeAttendedCountPer)
			}
			console.log(nonInviteesCounts)
			
			
			$(function () {
				 $("#eventsGraph"+i).highcharts({
					colors: ['#D33E39','#64C664'],
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
						categories: candidateNames,
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
										return Highcharts.numberFormat(this.percentage,1) + '%';
									}
								}
							  
							}
						}
					},

					 tooltip: {
						formatter: function () {
							var s = '<b>' + this.x + '</b>';

							$.each(this.points, function () {
								s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
									Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
									(this.y);
							});

							return s;
						},
						shared: true
					},

					series: [{
						name: 'Invitees',
						data: inviteesCounts,
						
					},{
						name: 'Non Invitees',
						data: nonInviteesCounts,
						
					}],
				 
				});
			});
		
			
		}
		
	}else{
		$("#eventsDistWiseCohort").html("No Data Available");
	}
}


/*Notes Functionality*/
function displayDashboardCommentsForEvents(dashBoardComponentId){
	var jsObj={
		dashBoardComponentId:dashBoardComponentId
	}	
	$.ajax({
	 type: "POST",
	 url: "displayDashboardCommentsAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length >0){
		 var str=''; 
			 
		 str+='<ul class="notesUlEvents m_top20" style="text-transform: none;font-weight: normal;font-size: 14px;">';  	
			for(var i in result){ 
				str+='<li style="margin-top:3px;">'; 
				str+='<span class="notesTextEvents" id="editTextEventsId'+i+'"  attr_commentId="'+result[i].dashBoardCommentId+'">'+result[i].comment+' </span>- <span class="text-muted"><i>'+result[i].insertedTime+'</i></span>';
				str+='<i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesEvents" attr_cmt_id="editTextEventsId'+i+'" id="'+result[i].dashBoardCommentId+'" onClick="deleteDashBoardcomments(this.id);"></i>';
				str+='<i class="glyphicon glyphicon-edit pull-right hoverBlock editNotesEvents" attr_cmt_id="editTextEventsId'+i+'" attr_comment="'+result[i].comment+'"></i>';
				str+='</li>';
			}
		str+='</ul>';
			
			$("#notesEventsId").html(str);	 
		}
	});
}
function deleteDashBoardcomments(dashboardCommentId)
{
	var jsObj={
		dashboardCommentId : dashboardCommentId
	}	
	$.ajax({
	 type: "POST",
	 url: "deleteDashBoardcommentsAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null){	
			if(result.message == "success"){
				
			}
		}
	});
}

function savingDashboardCommentForEvents(dashboardComponentId){  
	var comment=$(".notesAreaEvents").val();
	if(comment.trim() ==""){
		  $("#eventsUpId").html("Notes Required.");
		  return;
	  }
	var editId = $("#cmtEventsId").val();
	//$("#"+editId).parent().html(' ');
	$("#"+editId).html(comment);
	 var dashboardCommentId=0;
	 if($(".notesAreaEvents").attr("attr_commentid")>0)
	 {
		dashboardCommentId=$(".notesAreaEvents").attr("attr_commentid");		
	 }

	var jsObj={
		comment:comment,
		dashboardComponentId: dashboardComponentId,
		dashboardCommentId : dashboardCommentId
	}	
	$.ajax({
	 type: "POST",
	 url: "savingDashboardCommentAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null){	
			if(result.message == "success"){
				displayDashboardCommentsForEvents(6);
			}
		}			
	});
}
$(document).on("click",".notesIconEvents",function(){
	$(this).closest(".panel-heading").find(".notesDropDown").toggle();
});
$(document).on("click",".deleteNotesEvents",function(){
	$(this).closest("li").remove();
});
$(document).on("click",".editNotesEvents",function(){ 
	var commentId = $(this).attr("attr_cmt_id");
	var commentId1 = $(this).parent().find(".notesTextEvents").attr("attr_commentid");
	var notesHtml = $("#"+commentId).html();
	$(".notesAreaEvents").val(notesHtml);  
	$(".notesAreaEvents").attr("attr_commentid",commentId1);  
	$("#cmtId").val(commentId);
	//$("#cmtId").val();
	$("#eventsUpId").html('');		
});

$(document).on("click",".btnCustomCreateEvents",function(){
	var getNewNotes = $(".notesAreaEvents").val();
	var todayDate = moment().format("DD MMMM YYYY");
	var cmtId = $("#cmtId").val();
	var commentText = '<span class="notesText" id="'+cmtId+'" >'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i  class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesEvents"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes" attr_cmt_id="'+cmtId+'"></i>'; 
	if(cmtId>0)
	$(".notesUlEvents").append("<li>"+commentText+"</li>");
	$(".notesAreaEvents").val('');	
});

/*Notes Functionality End*/