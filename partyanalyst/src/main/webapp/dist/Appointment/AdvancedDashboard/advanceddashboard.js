$("#requestedTypeId").chosen();
initiateDateRangePicker();
function initiateDateRangePicker(){
	
	//$(".getDate").daterangepicker({opens:"left"});
var cb = function(start, end, label) {
			  $('.getDate').html(start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
			  }

		 var optionSet1 = {
		 startDate: moment(),
		 endDate: moment(),
		 //dateLimit: { days: 60 },
		 showDropdowns: true,
		 showWeekNumbers: true,
		 timePicker: false,
		 timePickerIncrement: 1,
		 timePicker12Hour: true,
		 ranges: {
		 'Today': [moment(), moment()],
		 'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		 'Last 7 Days': [moment().subtract(6, 'days'), moment()],
		 'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		 'This Month': [moment().startOf('month'), moment().endOf('month')],
		 'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
		 },
		 opens: 'left',
		 buttonClasses: ['btn btn-default'],
		 applyClass: 'btn-sm btn-success btn-custom newsSubmitBtn',
		 cancelClass: 'btn-sm btn-cancel',
		 format: 'DD/MM/YYYY',
		 separator: ' to ',
		 locale: {
		 applyLabel: 'Submit',
		 cancelLabel: 'Clear',
		 fromLabel: 'From',
		 toLabel: 'To',
		 customRangeLabel: 'Custom',
		 daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		 monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		 firstDay: 1
		 }
		 };
 
		

		 // $('.getDate').val(moment().format('MM/DD/YYYY') + ' to  ' + moment().format('MM/DD/YYYY'));

		 $('.getDate').daterangepicker(optionSet1, cb);

		 $('.getDate').on('show.daterangepicker', function() { console.log("show event fired"); });
		 $('.getDate').on('hide.daterangepicker', function() { console.log("hide event fired"); });
		 $('.getDate').on('apply.daterangepicker', function(ev, picker) { 
		
		 });
		
		 $('.daterangepicker').css("right","0px !important;");
		 
		
}

		 $(".ranges").find("ul").prepend('<li class="activeCls">Total</li>');
		 $(".ranges").find("ul li").removeClass("active");


	$(document).on("click",".advnceDashboardCls",function(){
		
		getTotalAppointmentStatus();
		getAppointmentStatusCounts();
		getAllCandidateTypes();
		getPublicRepresentativeWiseAppointmentCnt();
		getCommitteeLevelCount();
		getCandidCountsByStatesAction();
		getCandiCountsByLocations();
		
	});

$(document).on("click",".ranges li",function(){

	$(".ranges").find("ul li").removeClass("active");
	$(this).addClass("active");
	
	var selectedDay=$(this).html().trim();
	if(selectedDay == 'Total'){
		$(".getDate").val('');
	}
	
	 if(selectedDay != 'Custom'){
		getCandidCountsByStatesAction(); 
		getCandiCountsByLocations();
	 }
});
$(document).on("click",".applyBtn",function(){
	getCandidCountsByStatesAction();
	getCandiCountsByLocations();
});
	 $(".ranges li:nth-child(1)").addClass("active");
	$(document).on("click",".activeCls",function(){
	$(".daterangepicker").css("display","none");
	
	}); 

	 $(document).on("click",".getDate",function(){
	$(".daterangepicker").css("display","block");	
	$(".show-calendar").css("display","none");	
	
	}); 

function getCandidCountsByStatesAction(){
		
		var appointmentUserId = $("#appointmentUserSelectBoxId").val();
		var startDateString = "";
		var endDateString = "";
		var dates = $(".getDate").val();
		
		if(dates != null && dates.length > 0){
			var datesArr = dates.split("to");
			startDateString = datesArr[0].trim();
			endDateString = datesArr[1].trim();
		}
	
		var jsObj = {
			startDateString:startDateString,
			endDateString:endDateString,
			appointmentUserId:appointmentUserId
		}
		$.ajax({
		type : 'GET',
		url : 'getCandidCountsByStatesAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){ 
		 buildCandidCountsByStatesAction(result);
	});     
	}	

	function buildCandidCountsByStatesAction(result){
		
		var str='';
		str+='<div class="col-md-3 pad_5">';
			str+='<table class="table table-condensed" style="margin-top:60px;">';
				str+='<tr class="bg_f2">';
					str+='<td style="padding:20px;border:5px solid #fff;" colspan="2"><h5 class="text-capitalize">TOTAL <br/>REQUESTED MEMBERS</h5></td>';
				str+='</tr>';
				str+='<tr class="bg_f2">';
					str+='<td style="padding:20px;border:5px solid #fff;" colspan="2"><h5 class="text-capitalize">TOTAL <br/> APPOINTMENT MEMBERS SCHEDULED</h5></td>';
				str+='</tr>';
				str+='<tr class="bg_f2">';
					str+='<td style="padding:20px;border:5px solid #fff;" colspan="2"><h5 class="text-capitalize">TOTAL <br/> APPOINTMENT MEMBERS WAITING </h5></td>';
				str+='</tr>';
				
			str+='</table>';
		str+='</div>';
		
		if(result.totalCountsVOList !=null && result.totalCountsVOList.length>0){
			for(var i in result.totalCountsVOList){
				str+='<div class="col-md-2 pad_5" style="width: 205px;">';
				str+='<table class="table table-condensed">';
			str+='<tr class="bg_f2">';
				str+='<td colspan="4" class="text-center text-capitalize"><b>'+result.totalCountsVOList[i].name+'</b></td>';
			str+='</tr>';
			str+='<tr class="bg_f2">';
				str+='<td colspan="2">Total</td>';
				str+='<td colspan="2">Unique</td>';
			str+='</tr>';
			str+='<tr class="bg_e6">';
			if(result.totalCountsVOList[i].count !=null && result.totalCountsVOList[i].count !=0){
				str+='<td colspan="2" class="pad_10 text-center"><h4>'+result.totalCountsVOList[i].count+'</h4></td>';
			}else{
				str+='<td colspan="2" class="pad_10 text-center"><h4> - </h4></td>';
			}
			if(result.totalCountsVOList[i].uniqueCount !=null && result.totalCountsVOList[i].uniqueCount !=0){
				str+='<td colspan="2" class="pad_10 text-center"><h4>'+result.totalCountsVOList[i].uniqueCount+'</h4></td>';
			}else{
				str+='<td colspan="2" class="pad_10 text-center"><h4> - </h4></td>';
			}
				
			str+='</tr>';
			str+='<tr class="bg_e6">';
				if(result.totalCountsVOList[i].apCount !=null && result.totalCountsVOList[i].apCount !=0){
					str+='<td class="font10 pad_10 text-center">AP<br/>'+result.totalCountsVOList[i].apCount+'</td>';
				}else{
					str+='<td class="font10 pad_10 text-center">AP<br/> - </td>';
				}
				 if(result.totalCountsVOList[i].tsCount !=null && result.totalCountsVOList[i].tsCount !=0){
						str+='<td class="font10 pad_10 text-center">TS<br/>'+result.totalCountsVOList[i].tsCount+'</td>';
				}else{
						str+='<td class="font10 pad_10 text-center">TS<br/> - </td>';
				}
				if(result.totalCountsVOList[i].uniqueApCount !=null && result.totalCountsVOList[i].uniqueApCount !=0){
					str+='<td class="font10 pad_10 text-center">AP<br/>'+result.totalCountsVOList[i].uniqueApCount+'</td>';
				}else{
					str+='<td class="font10 pad_10 text-center">AP<br/> - </td>';
				}
				if(result.totalCountsVOList[i].uniqueTsCount !=null && result.totalCountsVOList[i].uniqueTsCount !=0){
					str+='<td class="font10 pad_10 text-center">TS<br/>'+result.totalCountsVOList[i].uniqueTsCount+'</td>';
				}else{
					str+='<td class="font10 pad_10 text-center">TS<br/> - </td>';
				} 
			
			str+='</tr>';
			
		str+='</table>';
		str+='</div>';
			}
		}
		
		 if(result.scheduledCountsVOList !=null && result.scheduledCountsVOList.length>0){
			for(var i in result.scheduledCountsVOList){
				str+='<div class="col-md-2 pad_5" style="width: 205px;">';
				str+='<table class="table table-condensed" >';	
			str+='<tr class="bg_e6">';
			if(result.scheduledCountsVOList[i].count !=null && result.scheduledCountsVOList[i].count != 0){
				str+='<td colspan="2" class="pad_10 text-center"><h4>'+result.scheduledCountsVOList[i].count+'</h4></td>';
			}else{
				str+='<td colspan="2" class="pad_10 text-center"><h4> - </h4></td>';
			}
			if(result.scheduledCountsVOList[i].uniqueCount !=null && result.scheduledCountsVOList[i].uniqueCount != 0){
				str+='<td colspan="2" class="pad_10 text-center"><h4>'+result.scheduledCountsVOList[i].uniqueCount+'</h4></td>';
			}else{
				str+='<td colspan="2" class="pad_10 text-center"><h4> - </h4></td>';
			}
			str+='</tr>';
			str+='<tr class="bg_e6">';
				if(result.scheduledCountsVOList[i].apCount !=null && result.scheduledCountsVOList[i].apCount != 0){
						str+='<td class="font10 pad_10 text-center">AP<br/>'+result.scheduledCountsVOList[i].apCount+'</td>';
				}else{
					str+='<td class="font10 pad_10 text-center">AP<br/> - </td>';
				}
				if(result.scheduledCountsVOList[i].tsCount !=null && result.scheduledCountsVOList[i].tsCount != 0){
						str+='<td class="font10 pad_10 text-center">TS<br/>'+result.scheduledCountsVOList[i].tsCount+'</td>';
				}else{
					str+='<td class="font10 pad_10 text-center">TS<br/> - </td>';
				}
				if(result.scheduledCountsVOList[i].uniqueApCount !=null && result.scheduledCountsVOList[i].uniqueApCount != 0){
						str+='<td class="font10 pad_10 text-center">AP<br/>'+result.scheduledCountsVOList[i].uniqueApCount+'</td>';;
				}else{
					str+='<td class="font10 pad_10 text-center">AP<br/> - </td>';
				}
				if(result.scheduledCountsVOList[i].uniqueTsCount !=null && result.scheduledCountsVOList[i].uniqueTsCount != 0){
						str+='<td class="font10 pad_10 text-center">TS<br/>'+result.scheduledCountsVOList[i].uniqueTsCount+'</td>';
				}else{
					str+='<td class="font10 pad_10 text-center">TS<br/> - </td>';
				}
				
			str+='</tr>';
			str+='</table>';
			str+='</div>';
			}
		}
		if(result.waitingCountsVOList !=null && result.waitingCountsVOList.length>0){
			for(var i in result.waitingCountsVOList){
				str+='<div class="col-md-2 pad_5" style="width: 205px;">';
				str+='<table class="table table-condensed" >';	
			str+='<tr class="bg_e6">';
				if(result.waitingCountsVOList[i].count !=null && result.waitingCountsVOList[i].count !=0 ){
					str+='<td colspan="2" class="pad_10 text-center"><h4>'+result.waitingCountsVOList[i].count+'</h4></td>';
				}else{
					str+='<td colspan="2" class="pad_10 text-center"><h4> - </h4></td>';
				}
				if(result.waitingCountsVOList[i].uniqueCount !=null && result.waitingCountsVOList[i].uniqueCount !=0){
					str+='<td colspan="2" class="pad_10 text-center"><h4>'+result.waitingCountsVOList[i].uniqueCount+'</h4></td>';
				}else{
					str+='<td colspan="2" class="pad_10 text-center"><h4> - </h4></td>';
				}
				
			str+='</tr>';
			str+='<tr class="bg_e6">';
				if(result.waitingCountsVOList[i].apCount !=null && result.waitingCountsVOList[i].apCount !=0){
					str+='<td class="font10 pad_10 text-center">AP<br/>'+result.waitingCountsVOList[i].apCount+'</td>';
				}else{
					str+='<td class="font10 pad_10 text-center">AP<br/> - </td>';
				}
				if(result.waitingCountsVOList[i].tsCount !=null && result.waitingCountsVOList[i].tsCount !=0){
					str+='<td class="font10 pad_10 text-center">TS<br/>'+result.waitingCountsVOList[i].tsCount+'</td>';
				}else{
					str+='<td class="font10 pad_10 text-center">TS<br/> - </td>';
				}
				if(result.waitingCountsVOList[i].uniqueApCount !=null && result.waitingCountsVOList[i].uniqueApCount !=0){
					str+='<td class="font10 pad_10 text-center">AP<br/>'+result.waitingCountsVOList[i].uniqueApCount+'</td>';
				}else{
					str+='<td class="font10 pad_10 text-center">AP<br/> - </td>';
				}
				if(result.waitingCountsVOList[i].uniqueTsCount !=null && result.waitingCountsVOList[i].uniqueTsCount !=0){
					str+='<td class="font10 pad_10 text-center">TS<br/>'+result.waitingCountsVOList[i].uniqueTsCount+'</td>';
				}else{
					str+='<td class="font10 pad_10 text-center">TS<br/> - </td>';
				}
				
			str+='</tr>';
			str+='</table>';
			str+='</div>';
		
			}
		} 
		$("#candidateWiseCounts").html(str);
	}
	
	$(document).on("click","#locationWiseDetailsDiv",function(){
	    getCandiCountsByLocations();
    });
	function getCandiCountsByLocations(){
		
		$("#candiCountsWiseLocations").html("");
		
		var appointmentUserId = $("#appointmentUserSelectBoxId").val();
		var candidateTypeArray = $("#candidateType").val();
		var requestedTypeArray = $("#requestedTypeId").val();
		var stateId = $("#state option:selected").val();
	  
		var locationType = $("input[name='locationType']:checked").val();
		
		var startDateString = "";
		var endDateString = "";
		var dates = $(".getDate").val();
		
		if(dates != null && dates.length > 0){
			var datesArr = dates.split("to");
			startDateString = datesArr[0].trim();
			endDateString = datesArr[1].trim();
		}
	
		var jsObj = {
			startDateString    :startDateString,
			endDateString      :endDateString,
			appointmentUserId  :appointmentUserId,
			candidateTypeArray :candidateTypeArray,
			requestedTypeArray :requestedTypeArray,
			stateId            :stateId,
			locationType       :locationType
		}
		$.ajax({
		type : 'GET',
		url : 'getCandiCountsByLocationsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){ 
		   if(result!=null && result.length>0){
			   buildCandiCountsByLocations(result);
		   }else{
			   $("#candiCountsWiseLocations").html("NO DATA AVAILABLE.");
		   }
	     });     
	}	
	String.prototype.capitalize = function() {
       return this.charAt(0).toUpperCase() + this.slice(1);
   }

	function buildCandiCountsByLocations(result){
		var str='';
		
		str+='<table class="table table-bordered table-condensed tableC" id="candidateWiseDataTable">';
							str+='<thead>';
							
								str+='<tr>';
								
									str+='<th rowspan="3"></th>';
									
									str+='<th rowspan="2" colspan="2">Total Requested</th>';
									str+='<th rowspan="2" colspan="2">Total Scheduled</th>';
									str+='<th rowspan="2" colspan="2">Total Waiting</th>';
									
									var colspanForReqType = result[0].subList[0].subList.length * 2;
									for(var i in result[0].subList){
										str+='<th class="text-center" colspan="'+colspanForReqType+'">'+result[0].subList[i].name+'</th>';
									}
								str+='</tr>';
								
								str+='<tr>';
								for(var i in result[0].subList){
										for(var j in result[0].subList[i].subList){
											str+='<th colspan="2">'+result[0].subList[i].subList[j].name.capitalize();+'</th>';
										}
									}
								str+='</tr>';
								
								str+='<tr>';
								
									str+='<th>T</th>';
									str+='<th>U</th>';
									
									str+='<th>T</th>';
									str+='<th>U</th>';
									
									str+='<th>T</th>';
									str+='<th>U</th>';
									for(var i in result[0].subList){
										for(var j in result[0].subList[i].subList){
											str+='<th>T</th>';
											str+='<th>U</th>';
										}
									}
								str+='</tr>';
								
							str+='</thead>';
							str+='<tbody>';
								
								   
								   for(var i in result){
									   str+='<tr>';
									   
									   str+='<td>'+result[i].name+'</td>';
									   
									   for(var j in result[i].typeList){
										   if(result[i].typeList[j].count == 0){
											   str+='<td> - </td>';
										   }else{
											   str+='<td>'+result[i].typeList[j].count+'</td>';
										   }
										   if(result[i].typeList[j].uniqueCount == 0){
											   str+='<td> - </td>';
										   }else{
											   str+='<td>'+result[i].typeList[j].uniqueCount+'</td>';
										   }
										   
									   }
									    for(var k in result[i].subList){
											
											for(var m in result[i].subList[k].subList){
												
												if(result[i].subList[k].subList[m].count==0){
													str+='<td> - </td>';
												}else{
													str+='<td>'+result[i].subList[k].subList[m].count+'</td>';
												}
												if(result[i].subList[k].subList[m].uniqueCount==0){
													str+='<td> - </td>';
												}else{
													str+='<td>'+result[i].subList[k].subList[m].uniqueCount+'</td>';
												}
										        
											}
										   
									   }
									 str+='</tr>';  
								   }
									
								
							str+='</tbody>';
						str+='</table>';
						
						$("#candiCountsWiseLocations").html(str);
						
						 $('#candidateWiseDataTable').dataTable({
							"aaSorting": [[ 1, "asc" ]],
							"iDisplayLength" : 20	,
							 "bDestroy": true,
							"aLengthMenu": [[20,50,100, 200, 500, -1], [20,50,100, 200, 500, "All"]]		
						});
						 //$('#candidateWiseDataTable').removeClass("dataTable");
						
	}
	
  function getMemebersByScheduleType(roleId,memberType,countType,scheduleType)
  {
  $("#membersModelId").modal("show");
   var jsObj = {
     roleId : roleId,
    memberType : memberType,
    cntType : countType,
    scheduleType : scheduleType,
    task:""
   }
    $.ajax({
    type : 'GET',
    url : 'getAppointmentMembersByScheduleTypeAction.action',
    dataType : 'json',
    data : {task:JSON.stringify(jsObj)}  
    }).done(function(result){ 
       
       });   
  }
function buildAppointmentMembersData(result)
 {
   var str='';
  if(result != null){
			str +='<table class="table table-bordered">';
            str +='<thead>';
            str+='<tr>';
							
			str+='<th class="text-capitalize">NAME</th>';
			str+='<th class="text-capitalize">Designation</th>';
			str+='<th class="text-capitalize">Image</th>';
			str+='<th class="text-capitalize">Mobile</th>';
								
			str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
	for(var i in result)
	{
		str +='<tr>';
		 
            str +='<td style="text-align:center">'+result[i].name+'</td>';
            str +='<td style="text-align:center">'+result[i].designation+'</td>';
            str +='<td style="text-align:center">'+result[i].imageUrl+'</td>';
			str +='<td style="text-align:center">'+result[i].mobile+'</td>';
            
			str +=' </tr>';
	}
	str +='</tbody>';
			str +='</table>';
	}