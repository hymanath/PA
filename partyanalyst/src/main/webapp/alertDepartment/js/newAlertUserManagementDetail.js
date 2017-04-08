onLoadCalls()
function onLoadCalls()
{
	"use strict";
	onLoadClicks();
}
function onLoadClicks()
{
	"use strict";
	
	$(document).on("click",".alert-status-change",function(){
		if($(this).is(':checked'))
		{
			$(".alert-status-change-body").show();
		}else{
			$(".alert-status-change-body").hide();
		}
	});
	$(document).on("click","div.comment-area",function(e){
		e.stopPropagation()
		$(this).hide();
		$(".panel-border-white .panel-heading,.panel-border-white .panel-footer,.panel-border-white textarea").show();
	});
	$(document).on("click","div.assign-user",function(e){
		e.stopPropagation()
		$(this).find(".assign-user-body").show();
	});
	$(document).on("click",".panel-border-white",function(e){
		e.stopPropagation()
	});
	
	$(document).on("click",".comment-btn",function(e){
		$("div.comment-area").show();
		$(".panel-border-white .panel-heading,.panel-border-white .panel-footer,.panel-border-white textarea").hide();
		$(".panel-border-white textarea").val('');
	});
	$(document).on("click",function(e){
		e.stopPropagation()
		$("div.comment-area").show();
		$(".panel-border-white .panel-heading,.panel-border-white .panel-footer,.panel-border-white textarea,.assign-user-body,.alert-status-change-list,.assign-subtask").hide();
		$(".panel-border-white textarea").val('');
	});
	$(document).on("click","[basic-switch] li",function(){
		$(this).addClass("active");
	});
	$(document).on("click","[filer-selection]",function(e){
		e.stopPropagation();
		var selectionType = $(this).attr("filer-selection");
		$(this).closest("li").removeClass("active");
		$(this).remove();
	});
	$(document).on("click",".assign-subtask",function(e){
		e.stopPropagation();
	});
	$(document).on("click","[filters-list] li",function(e){
		e.stopPropagation();
		
		var blockName = $(this).closest("ul").attr("filters-list");
		$(this).addClass("active");
		if(!$(this).find("span").hasClass("remove"))
		{
			$(this).append("<span class='remove' filer-selection='true'><i class='glyphicon glyphicon-remove'></i></span>")
		}
		if(blockName != null && blockName != undefined)
		{
			if(!$("[filters-list-title='all'] small").hasClass("clear"))
			{
				$("[filters-list-title='all']").append("<small class='clear' filer-selection-clear='all'>(Clear All)</small>")
			}
			if(!$("[filters-list-title="+blockName+"] small").hasClass("clear"))
			{
				$("[filters-list-title="+blockName+"]").append("<small class='clear' filer-selection-clear="+blockName+">(Clear All)</small>")
			}
			if(blockName == '')
			{
			}
		}
	});
	$(document).on("click",".assign-subtask-btn",function(e){
		e.stopPropagation();
		$(this).closest("li").find(".assign-subtask").show();
	});
	$(document).on("click","[filer-selection-clear]",function(e){
		e.stopPropagation();
		var blockName = $(this).attr("filer-selection-clear");
		if(blockName == 'all')
		{
			$("[filters-list]").find("span.remove").remove();
			$("[filters-list]").find("li").removeClass("active");
			$("[filters-list-title]").find("small").remove();
		}
		$("[filters-list="+blockName+"]").find("span.remove").remove();
		$("[filters-list="+blockName+"]").find("li").removeClass("active");
		$(this).remove();
	});
	$(document).on("click","[status-icon] li",function(e){
		e.stopPropagation();
		var status = $(this).attr("status-icon-block")
		if(status != null && status != undefined)
		{
			if(status == 'alertHistory')
			{
				$("#alertManagementPopup1").modal({
					show: true,
					keyboard: false,
					backdrop: 'static'
				});
				$("#alertManagementPopupHeading").html('ALERT HISTORY')
				$("#alertManagementPopup1 .modal-footer").hide();
				viewAlertHistory();
			}else if(status == 'alertStatus')
			{
				$("#alertManagementPopup1").modal({
					show: true,
					keyboard: false,
					backdrop: 'static'
				});
				$("#alertManagementPopupHeading").html('ALERT STATUS HISTORY')
				alertStatus();
			}else if(status == 'alertStatusChange')
			{
				$(this).find('ul').toggle();
			}else if(status == 'task')
			{
				statusBody(status);
			}else if(status == 'subTask')
			{
				statusBody(status);
			}
		}
	});
	$(document).on("click","[expand-icon]",function(){
		var expandBlockName = $(this).attr("expand-icon");
		
		if($("[expand-main]").attr("expand-main") === 'true')
		{
			$("[expand-main]").attr("expand-main","false");
			$("[expanded-channel]").attr("expanded-channel","false");
			$("[expanded-block="+expandBlockName+"]").hide().css("transition"," ease-out, width 0.7s ease-in-out");
			$("[expand-main]").removeClass("col-sm-4").addClass("col-sm-12").css("transition"," ease-in-out, width 0.7s ease-in-out");
		}else{
			$("[expand-main]").attr("expand-main","true");
			$("[expanded-channel]").attr("expanded-channel","true");
			setTimeout(function(){
				$("[expanded-block="+expandBlockName+"]").show().css("transition"," ease-in, width 0.7s ease-in-out");
			},750);
			$("[expand-main]").addClass("col-sm-4").removeClass("col-sm-12").css("transition"," ease-in-out, width 0.7s ease-in-out");
		}
	});
	$(document).on("click","[expanded-close]",function(){
		var expandBlockName = $(this).attr("expanded-close");
		if($("[expand-main]").attr("expand-main") === 'true')
		{
			$("[expand-main]").attr("expand-main","false");
		}else{
			$("[expand-main]").attr("expand-main","true");
		}
		$("[expanded-block="+expandBlockName+"]").hide();
		$("[expand-main]").removeClass("col-sm-4").addClass("col-sm-12").css("transition"," ease-in-out, width 0.7s ease-in-out");
	});
	$(document).on("click",".filters-icon",function(){
		popUpFilter('body')
	});
}
function popUpFilter(type)
{
	var str='';
	var str1='';
	if(type == 'heading')
	{
		str1+='<div class="row">';
			str1+='<div class="col-sm-8">';
				str1+='<h4 class="modal-title text-capital" id="modalHeadingTotal">total alerts</h4>';
			str1+='</div>';
			str1+='<div class="col-sm-4">';
				str1+='<ul class="list-icons pull-right list-inline">';
					str1+='<li><i class="glyphicon glyphicon-filter filters-icon"></i></li>';
					str1+='<li data-dismiss="modal" aria-label="Close"><i class="glyphicon glyphicon-remove"></i></li>';
				str1+='</ul>';
			str1+='</div>';
		str1+='</div>';
		$("#alertManagementPopup .modal-header").html(str1);
	}else if(type == 'body')
	{
		str+='<div class="filter-block">';
			str+='<div class="row">';
				str+='<div class="col-sm-8">';
					str+='<h4 filters-list-title="all" class="panel-title text-capital"><i class="glyphicon glyphicon-filter"></i> filters</h4>';	
				str+='</div>';
				str+='<div class="col-sm-4">';
				str+='</div>';
			str+='</div>';
			str+='<div class="row">';
				str+='<div class="col-sm-3">';
					str+='<h5 class="text-capitalize" filters-list-title="impactLevel">Impact Level</h5>';
					str+='<ul class="filters-list" filters-list="impactLevel">';
						str+='<li>State</li>';
						str+='<li>District</li>';
					str+='</ul>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<h5 class="text-capitalize" filters-list-title="priority">priority</h5>';
					str+='<ul class="filters-list" filters-list="priority">';
						str+='<li>State</li>';
						str+='<li>State</li>';
					str+='</ul>';
					str+='<h5 class="text-capitalize m_top20" filters-list-title="alertSourceType">alert source type</h5>';
					str+='<ul class="filters-list" filters-list="alertSourceType">';
						str+='<li>State</li>';
						str+='<li>State</li>';
					str+='</ul>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<h5 class="text-capitalize" filters-list-title="printMedia">Level</h5>';
					str+='<ul class="filters-list" filters-list="printMedia">';
						str+='<li>State</li>';
						str+='<li>State</li>';
					str+='</ul>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<h5 class="text-capitalize" filters-list-title="electronicMedia">Level</h5>';
					str+='<ul class="filters-list" filters-list="electronicMedia">';
						str+='<li>State</li>';
						str+='<li>State</li>';
					str+='</ul>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#filter").html(str);
	}
}
function buildAlertDtlsBasedOnStatusClick(result,statusName,statuscount)
{
	var str='';
	popUpFilter('heading');
	$("#modalHeadingTotal").html("Total "+statusName+' - '+statuscount)
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12" expand-main="false">';
			str+='<div class="panel-group panel-white panel-left" id="accordion" role="tablist" aria-multiselectable="true">';
			for(var i in result)
			{
				str+='<div class="panel panel-default">';
					str+='<div class="panel-heading" role="tab" id="heading'+result[i].id+'">';
						str+='<a role="button" data-toggle="collapse" class="collapseArrow" data-parent="#accordion" href="#collapse'+result[i].id+'" aria-expanded="true" aria-controls="collapse'+result[i].id+'">';
							str+='<h4 class="panel-title">';
								str+=''+result[i].name+'';
								str+='<small>- '+result[i].subList[0].updatedDate+'</small>';
								str+='<small><span class="pull-right">1 - 5 of About 5</span></small>';
							str+='</h4>';
						str+='</a>';
					str+='</div>';
					str+='<div id="collapse'+result[i].id+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+result[i].id+'">';
						str+='<div class="panel-body pad_0">';
							str+='<div class="row">';
								str+='<div class="col-sm-12">';
									str+='<ul class="alerts-list">';
										for(var j in result[i].subList)
										{
											str+='<li>';
												str+='<div class="row">';
													str+='<div class="col-sm-7">';
														str+='<h4>';
															str+='<i class="glyphicon glyphicon-cog text-danger"  style="color:'+result[i].subList[j].severtyColor+';margin-right:3px;"></i>';
															str+='<span class="alert-title">'+result[i].subList[j].title+'</span>';
															str+='<span class="label label-default channel-name" data-toggle="tooltip" data-placement="top" title="'+result[i].source+'">'+result[i].subList[j].source+'</span>';
														str+='</h4>';
													str+='</div>';
													str+='<div class="col-sm-5">';
														str+='<span class="arrow-icon pull-right" expand-icon="block1">';
															str+='<i class="glyphicon glyphicon-menu-right"></i>';
														str+='</span>';
														str+='<span class="status-icon pull-right"></span>';
														str+='<span class="location-name pull-right" data-toggle="tooltip" data-placement="top" title="'+result[i].subList[j].location+'">'+result[i].subList[j].location+'</span>';
													str+='</div>';
												str+='</div>';
											str+='</li>';
										}
									str+='</ul>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				  str+='</div>';
			}

			  
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-8 pad_left0" expanded-block="block1" style="display: none;">';
			str+='<div class="panel-right">';
				str+='<div class="arrow_box_left">';
					str+='<i class="glyphicon glyphicon-remove pull-right"  expanded-close="block1"></i>';
					str+='<div class="panel panel-default">';
						str+='<div class="panel-heading">';
							str+='<div class="row">';
								str+='<div class="col-sm-4">';
									str+='<div class="media">';
										str+='<div class="media-left">';
											str+='<span class="icon-name icon-primary">Ra</span>';
										str+='</div>';
										str+='<div class="media-body">';
											str+='<p>Raja Mahendara</p>';
											str+='<p>-Designation</p>';
										str+='</div>';
									str+='</div>';
									str+='<div class="assign-user">';
										str+='<ul class="list-icons list-inline">';
											str+='<li>';
												str+='<i class="glyphicon glyphicon-user"></i>Click To Assignee';
											str+='</li>';
										str+='</ul>';
										str+='<div class="assign-user-body">';
											str+='<div class="arrow_box_top">';
												str+='<div class="row">';
													str+='<div class="col-sm-4">';
														str+='<select class="chosen-select">';
															str+='<option></option>';
														str+='</select>';
													str+='</div>';
													str+='<div class="col-sm-4">';
														str+='<select class="chosen-select">';
															str+='<option></option>';
														str+='</select>';
													str+='</div>';
													str+='<div class="col-sm-4">';
														str+='<select class="chosen-select">';
															str+='<option></option>';
														str+='</select>';
													str+='</div>';
													str+='<div class="col-sm-4">';
														str+='<select class="chosen-select">';
															str+='<option></option>';
														str+='</select>';
													str+='</div>';
													str+='<div class="col-sm-4">';
														str+='<select class="chosen-select">';
															str+='<option></option>';
														str+='</select>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
											str+='<div class="panel-footer text-right pad_5 border_1 bg_EE">';
												str+='<button class="btn btn-primary btn-sm text-capital">assign alert</button>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-sm-8">';
									str+='<ul class="list-icons list-inline pull-right" status-icon="block1">';
										str+='<li status-icon-block="alertStatus" data-toggle="tooltip" data-placement="top" title="alert status">';
											str+='<span class="status-icon arrow-icon"></span>Pending';
										str+='</li>';
										str+='<li class="list-icons-calendar" data-toggle="tooltip" data-placement="top" title="due date">';
											str+='<i class="glyphicon glyphicon-calendar"></i><span class="modal-date">DUe date</span>';
										str+='</li>';
										str+='<li status-icon-block="alertStatusChange" data-toggle="tooltip" data-placement="top" title="status change">';
											str+='<i class="glyphicon glyphicon-cog"></i>';
											str+='<ul class="alert-status-change-list arrow_box_top" style="display:none;">';
												str+='<li>high <input type="radio" name="alert-status-change-list" value="1" class="pull-right" /></li>';
												str+='<li>medium <input type="radio" name="alert-status-change-list" value="2" class="pull-right" /></li>';
												str+='<li>low <input type="radio" name="alert-status-change-list" value="3" class="pull-right" /></li>';
												str+='<li><button class="btn btn-primary btn-sm text-capital" id="priorityChangeSaveId">save</button></li>';
											str+='</ul>';
										str+='</li>';
										str+='<li status-icon-block="task">';
											str+='<i class="fa fa-level-down" data-toggle="tooltip" data-placement="top" title="Task"></i>';
										str+='</li>';
										str+='<li status-icon-block="subTask" class="status-icon-subtask" data-toggle="tooltip" data-placement="top" title="sub task">';
											str+='<i class="fa fa-mail-forward"></i>';
											str+='<i class="fa fa-level-down"></i>';
										str+='</li>';
										str+='<li status-icon-block="alertHistory">';
											str+='<i class="fa fa-road" data-toggle="tooltip" data-placement="top" title="Alert History"></i>';
										str+='</li>';
										str+='<li>';
											str+='<i class="glyphicon glyphicon-paperclip" data-toggle="tooltip" data-placement="top" title="Attachments"></i>';
										str+='</li>';
										str+='<li>';
											str+='<i class="fa fa-comment" data-toggle="tooltip" data-placement="top" title="Comments"></i>';
										str+='</li>';
									str+='</ul>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						str+='<div class="panel-body">';
							str+='<p><i class="fa fa-fire"></i> Impact Level : State';
								str+='<span class="text-danger pull-right"><i class="glyphicon glyphicon-cog"></i> Priority:<span id="priorityBodyId"> HIGH</span></span>';
							str+='</p>';
							str+='<div status-body="task" class="m_top20"></div>';
							str+='<div status-body="subTask" class="m_top20"></div>';
						str+='</div>';
						str+='<div class="panel-footer">';
							str+='<div class="row">';
								str+='<div class="col-sm-1 text-center">';
									str+='<span class="icon-name icon-primary">Ra</span>';
								str+='</div>';
								str+='<div class="col-sm-11">';
									str+='<div class="panel panel-default panel-border-white">';
										str+='<div class="panel-heading">';
											str+='<label class="radio-inline" name="language">';
												str+='<input type="radio"/>Telugu';
											str+='</label>';
											str+='<label class="radio-inline" name="language">';
												str+='<input type="radio"/>English';
											str+='</label>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<div class="comment-area">Comment Here</div>';
											str+='<textarea class="form-control comment-area" id="alertCommentId" placeholder="Comment here..."></textarea>';
										str+='</div>';
										str+='<div class="panel-footer text-right">';
											str+='<button class="btn btn-primary comment-btn" id="commentChangeId">Comment</button>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#alertManagementPopupBody").html(str);
	$('[data-toggle="tooltip"]').tooltip()
	dateRangePicker();
}
function dateRangePicker()
{
	$(function() {
		var start = moment();
		
		function cb(start) {
			$('.modal-date').html(start.format('MMM D, YYYY'));
		}

		$('.modal-date').daterangepicker({
			startDate: start,
			singleDatePicker:true
		}, cb);

		cb(start);
		
	});
	$('.modal-date').on('apply.daterangepicker', function(ev, picker) {
		selectedDate = picker.startDate.format('DD/MM/YYYY');
		var jsObj ={
			alertId : 11346,
			date:selectedDate
		}
		$.ajax({
			type:'GET',
			url: 'updateAlertDueDateAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.exceptionMsg == 'success')
			{
				alert("date updated successfully")
			}else{
				alert("try again")
			}
		});
	});
}
function alertHistory(result)
{
	var str='';
	for(var i in result)
	{
		str+='<ul class="alert-history">';
			str+='<span class="alert-history-date">'+result[i].date+'</span>';
			if(result[i].timeList != null && result[i].timeList.length > 0)
			{
				for(var j in result[i].timeList)
				{
					str+='<li>';
						str+='<span class="alert-history-time">'+result[i].timeList[j].date+'</span>';
						if(result[i].timeList[j].statusList != null && result[i].timeList[j].statusList.length > 0)
						{
							for(var k in result[i].timeList[j].statusList)
							{
								if(result[i].timeList[j].statusList[k].strList != null && result[i].timeList[j].statusList[k].strList.length > 0)
								{
									for(var l in result[i].timeList[j].statusList[k].strList)
									{
										str+='<p class="alert-history-status m_top20 text-capital"><span class="status-icon arrow-icon"></span>status: '+result[i].timeList[j].statusList[k].strList+'</p>';
									}
								}
							}
						}
						if(result[i].timeList[j].commentList != null && result[i].timeList[j].commentList.length > 0)
						{
							for(var k in result[i].timeList[j].commentList)
							{
								str+='<p class="alert-history-body m_top5 text-capital">'+result[i].timeList[j].commentList[k].strList+'</p>';
							}
						}
						if(result[i].timeList[j].statusList != null && result[i].timeList[j].statusList.length > 0)
						{
							for(var k in result[i].timeList[j].statusList)
							{
								str+='<p class="alert-history-user m_top5 text-capital">'+result[i].timeList[j].statusList[k].designation+'</p>';
								str+='<p class="alert-history-user m_top5 text-capital">'+result[i].timeList[j].statusList[k].userName+'</p>';
							}
						}
					str+='</li>';
				}
			}
		str+='</ul>';
	}
	
	$("#alertManagementPopup1 .modal-dialog").css("width","60%")
	$("#alertManagementPopupBody1").html(str);
}
function alertStatus()
{
	var str='';
	var str1='';
	str+='<table class="table border_1">';
		str+='<thead class="text-capitalize">';
			str+='<th>Date</th>';
			str+='<th>Status</th>';
			str+='<th>Comments</th>';
			str+='<th>Updated By</th>';
		str+='</thead>';
		str+='<tr>';
			str+='<td>12/03/2017</td>';
			str+='<td>InProgress</td>';
			str+='<td>Comments</td>';
			str+='<td>';
				str+='<p class="text-primary text-capitalize">officer name_01</p>';
				str+='<p class="text-muted text-capitalize">-<u>Designation & Department</u></p>';
			str+='</td>';
		str+='</tr>';
	str+='</table>';
	$("#alertManagementPopup1 .modal-dialog").css("width","60%")
	$("#alertManagementPopupBody1").html(str);
	str1+='';
	str1+='<div class="text-left" id="updateStatusChangeBody">';
		str1+='<label class="checkbox-inline">';
			str1+='<input type="checkbox" class="alert-status-change"/> I Want to change alert Status';
		str1+='</label>';
		str1+='<div class="panel panel-default panel-white m_top20 alert-status-change-body" style="display:none">';
			str1+='<div class="panel-heading">';
				str1+='<label class="checkbox-inline">';
					str1+='<input type="radio" value="4" name="statusChange"/> InProgress';
				str1+='</label>';
			str1+='</div>';
			str1+='<div class="panel-body pad_0">';
				str1+='<textarea class="form-control" id="updateStatusChangeComment" placeholder="Comment.."></textarea>';
			str1+='</div>';
		str1+='</div>';
	str1+='</div>';
	str1+='<button class="btn btn-primary btn-sm text-capital" id="updateStatusChange">update</button>';
	$("#alertManagementPopup1 .modal-footer").show();
	$("#alertManagementPopup1 .modal-footer").html(str1);
	
}
function statusBody(name)
{
	var str='';
	if(name == 'task')
	{
		str+='<div class="col-sm-1 text-center body-icons">';
			str+='<i class="fa fa-users fa-2x"></i>';
		str+='</div>';
		str+='<div class="col-sm-11">';
			str+='<h4 class="panel-title text-capital"><b>involved members in this alert</b></h4>';
			str+='<ul class="list-inline involved-members-list m_top20">';
				str+='<li><img src=""/></li>';
				str+='<li><img src=""/></li>';
				str+='<li><img src=""/></li>';
				str+='<li><img src=""/></li>';
				str+='<li><img src=""/></li>';
				str+='<li><img src=""/></li>';
			str+='</ul>';
		str+='</div>';
	}else if(name == 'subTask')
	{
		str+='<h4 class="text-capital text-muted panel-title"><i class="fa fa-level-down"></i>&nbsp;&nbsp; assign subtask</h4>';
		str+='<ul class="assign-subtask-list">';
			str+='<li class="assigned">';
				str+='<i class="glyphicon glyphicon-trash remove-task"></i>';
				str+='<div class="row">';
					str+='<div class="col-sm-1">';
						str+='<i class="glyphicon glyphicon-ok"></i>';
					str+='</div>';
					str+='<div class="col-sm-9">';
						str+='<p>sgibdda sgf</p>';
					str+='</div>';
					str+='<div class="col-sm-2">';
						str+='<i class="glyphicon glyphicon-menu-right pull-right"></i>';
						str+='<span class="icon-name icon-primary"></span>';
						str+='<span class="label label-default">...</span>';
					str+='</div>';
				str+='</div>';
			str+='</li>';
			str+='<li class="new">';
				str+='<div class="row">';
					str+='<div class="col-sm-1">';
						str+='<i class="glyphicon glyphicon-plus"></i>';
					str+='</div>';
					str+='<div class="col-sm-9">';
						str+='<input type="text" class="form-control" placeholder="Enter Sub Task Title"/>';
					str+='</div>';
					str+='<div class="col-sm-2">';
						str+='<i class="glyphicon glyphicon-menu-right pull-right"></i>';
						str+='<i class="glyphicon glyphicon-user assign-subtask-btn"></i>';
						str+='<i class="glyphicon glyphicon-calendar"></i>';
					str+='</div>';
				str+='</div>';
				str+='<div class="assign-subtask">';
					str+='<div class="arrow_box_top">';
						str+='<div class="row">';
							str+='<div class="col-sm-4 m_top10">';
								str+='<select class="chosen-select">';
									str+='<option></option>';
								str+='</select>';
							str+='</div>';
							str+='<div class="col-sm-4 m_top10">';
								str+='<select class="chosen-select">';
									str+='<option></option>';
								str+='</select>';
							str+='</div>';
							str+='<div class="col-sm-4 m_top10">';
								str+='<select class="chosen-select">';
									str+='<option></option>';
								str+='</select>';
							str+='</div>';
							str+='<div class="col-sm-4 m_top10">';
								str+='<select class="chosen-select">';
									str+='<option></option>';
								str+='</select>';
							str+='</div>';
							str+='<div class="col-sm-4 m_top10">';
								str+='<select class="chosen-select">';
									str+='<option></option>';
								str+='</select>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="panel-footer text-right">';
						str+='<button class="btn btn-primary btn-sm text-capital">assign subtask</button>';
					str+='</div>';
				str+='</div>';
			str+='</li>';
		str+='</ul>';
	}
	
	$("[status-body]").html(' ');
	$("[status-body="+name+"]").html(str);
}