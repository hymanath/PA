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
		 "parentEl": ".advancedBlock",
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
$(document).on("click",".getDate",function(){
	$(".advancedBlock").find(".daterangepicker").css("display","block");	
	}); 
		
	$(document).on("click",".advnceDashboardCls",function(){
		
		getTotalAppointmentStatus();
		getAppointmentStatusCounts();
		getAllCandidateTypes();
		getPublicRepresentativeWiseAppointmentCnt();
		getCommitteeLevelCount();
		getCandidCountsByStatesAction();
		getCandiCountsByLocations();
		$("#roleWiseApptId").html("");
		
		
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
					str+='<td style="padding:25px;border:5px solid #fff;" colspan="2"><h5 class="text-capitalize">TOTAL <br/>REQUESTED MEMBERS</h5></td>';
				str+='</tr>';
				str+='<tr class="bg_f2">';
					str+='<td style="padding:20px;border:5px solid #fff;" colspan="2"><h5 class="text-capitalize">TOTAL <br/> APPOINTMENT MEMBERS SCHEDULED <i class="glyphicon glyphicon-plus-sign pull-right expandListClass" style="top:-16px;cursor:pointer"></i></h5></td>';
				str+='</tr>';
				if(result.scheduledStatusCountsVOList != null && result.scheduledStatusCountsVOList.length>0)
				{
					for(var i in result.scheduledStatusCountsVOList){
						str+='<tr style="background:#f9f9f9;display:none;" class="internalShowClass">';
							str+='<td style="padding:32px;border:5px solid #fff;" colspan="2"><h5 class="text-capitalize">'+result.scheduledStatusCountsVOList[i].name+'</h5></td>';
						str+='</tr>';
					}
				}
				
				str+='<tr class="bg_f2">';
					str+='<td style="padding:20px;border:5px solid #fff;" colspan="2"><h5 class="text-capitalize">TOTAL <br/> APPOINTMENT MEMBERS WAITING <i class="glyphicon glyphicon-plus-sign pull-right expandTotalListClass" style="top:-16px;cursor:pointer"></i></h5></td>';
				str+='</tr>';
				if(result.waitingStatusCountsVOList != null && result.waitingStatusCountsVOList.length>0)
				{
					for(var i in result.waitingStatusCountsVOList){
						str+='<tr style="background:#f9f9f9;display:none;" class="internalTotalShowClass">';
							str+='<td style="padding:32px;border:5px solid #fff;" colspan="2"><h5 class="text-capitalize">'+result.waitingStatusCountsVOList[i].name+'</h5></td>';
						str+='</tr>';
					}
				}else{
					
				}
				
			str+='</table>';
		str+='</div>';
		
		if(result.totalCountsVOList !=null && result.totalCountsVOList.length>0){
			for(var i in result.totalCountsVOList){
			str+='<div class="col-md-2" style="width: 205px;padding:2px;">';
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
				str+='<div class="col-md-2" style="width: 205px;padding:2px;">';
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
		
		
		/**COPY**/
		 if(result.scheduledStatusCountsVOList !=null && result.scheduledStatusCountsVOList.length>0){
				for(var i in result.scheduledStatusCountsVOList){
					if(result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList !=null && result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList.length>0){
						for(var j in result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList){
						str+='<div class="col-md-2 internalShowClass" style="width: 205px;display:none;padding:2px;">';
						str+='<table class="table table-condensed" >';	
						str+='<tr style="background:#f9f9f9">';
						if(result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].count !=null && result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].count != 0){
							str+='<td colspan="2" class="pad_10 text-center"><h4>'+result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].count+'</h4></td>';
						}else{
							str+='<td colspan="2" class="pad_10 text-center"><h4> - </h4></td>';
						}
						if(result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].uniqueCount !=null && result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].uniqueCount != 0){
							str+='<td colspan="2" class="pad_10 text-center"><h4>'+result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].uniqueCount+'</h4></td>';
						}else{
							str+='<td colspan="2" class="pad_10 text-center"><h4> - </h4></td>';
						}
						str+='</tr>';
						str+='<tr style="background:#f9f9f9">';
							if(result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].apCount !=null && result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].apCount != 0){
									str+='<td class="font10 pad_10 text-center">AP<br/>'+result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].apCount+'</td>';
							}else{
								str+='<td class="font10 pad_10 text-center">AP<br/> - </td>';
							}
							if(result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].tsCount !=null && result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].tsCount != 0){
									str+='<td class="font10 pad_10 text-center">TS<br/>'+result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].tsCount+'</td>';
							}else{
								str+='<td class="font10 pad_10 text-center">TS<br/> - </td>';
							}
							if(result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].uniqueApCount !=null && result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].uniqueApCount != 0){
									str+='<td class="font10 pad_10 text-center">AP<br/>'+result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].uniqueApCount+'</td>';;
							}else{
								str+='<td class="font10 pad_10 text-center">AP<br/> - </td>';
							}
							if(result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].uniqueTsCount !=null && result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].uniqueTsCount != 0){
									str+='<td class="font10 pad_10 text-center">TS<br/>'+result.scheduledStatusCountsVOList[i].scheduledStatusCountsVOList[j].uniqueTsCount+'</td>';
							}else{
								str+='<td class="font10 pad_10 text-center">TS<br/> - </td>';
							}
							
							str+='</tr>';
							str+='</table>';
							str+='</div>';
						 }
					}
					
				}
		}
		
		/**COPY**/
		
		
		
		
		if(result.waitingCountsVOList !=null && result.waitingCountsVOList.length>0){
			for(var i in result.waitingCountsVOList){
				str+='<div class="col-md-2" style="width: 205px;padding:2px;">';
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

		/**COPY**/
		 if(result.waitingStatusCountsVOList !=null && result.waitingStatusCountsVOList.length>0){
				for(var i in result.waitingStatusCountsVOList){
					if(result.waitingStatusCountsVOList[i].waitingStatusCountsVOList !=null && result.waitingStatusCountsVOList[i].waitingStatusCountsVOList.length>0){
						for(var j in result.waitingStatusCountsVOList[i].waitingStatusCountsVOList){
						str+='<div class="col-md-2 internalTotalShowClass" style="width: 205px;display:none;padding:2px;">';
						str+='<table class="table table-condensed" >';	
						str+='<tr style="background:#f9f9f9">';
						if(result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].count !=null && result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].count != 0){
							str+='<td colspan="2" class="pad_10 text-center"><h4>'+result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].count+'</h4></td>';
						}else{
							str+='<td colspan="2" class="pad_10 text-center"><h4> - </h4></td>';
						}
						if(result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].uniqueCount !=null && result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].uniqueCount != 0){
							str+='<td colspan="2" class="pad_10 text-center"><h4>'+result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].uniqueCount+'</h4></td>';
						}else{
							str+='<td colspan="2" class="pad_10 text-center"><h4> - </h4></td>';
						}
						str+='</tr>';
						str+='<tr style="background:#f9f9f9">';
							if(result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].apCount !=null && result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].apCount != 0){
									str+='<td class="font10 pad_10 text-center">AP<br/>'+result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].apCount+'</td>';
							}else{
								str+='<td class="font10 pad_10 text-center">AP<br/> - </td>';
							}
							if(result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].tsCount !=null && result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].tsCount != 0){
									str+='<td class="font10 pad_10 text-center">TS<br/>'+result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].tsCount+'</td>';
							}else{
								str+='<td class="font10 pad_10 text-center">TS<br/> - </td>';
							}
							if(result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].uniqueApCount !=null && result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].uniqueApCount != 0){
									str+='<td class="font10 pad_10 text-center">AP<br/>'+result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].uniqueApCount+'</td>';;
							}else{
								str+='<td class="font10 pad_10 text-center">AP<br/> - </td>';
							}
							if(result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].uniqueTsCount !=null && result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].uniqueTsCount != 0){
									str+='<td class="font10 pad_10 text-center">TS<br/>'+result.waitingStatusCountsVOList[i].waitingStatusCountsVOList[j].uniqueTsCount+'</td>';
							}else{
								str+='<td class="font10 pad_10 text-center">TS<br/> - </td>';
							}
							
							str+='</tr>';
							str+='</table>';
							str+='</div>';
						 }
					}
					
				}
		}
		
		/**COPY**/



		
		$("#candidateWiseCounts").html(str);
		
	}
	$(document).on("click",".expandListClass",function(){
		$(document).find(".internalShowClass").toggle();
		$(this).toggleClass("glyphicon-minus-sign");
	});
	$(document).on("click",".expandTotalListClass",function(){
		$(document).find(".internalTotalShowClass").toggle();
		$(this).toggleClass("glyphicon-minus-sign");
	});
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
		
		var totalUniqueArray=[];
		$("input[name='totalUniqueType']:checked").each(function() {
			totalUniqueArray.push($(this).val());
		});
		
		
		var isTotalBuild=false;
		var isuniqueBuild = false;
		
		if($('#totalValueId').is(":checked")){
			var totalname = $('#totalValueId').val();
			isTotalBuild = true;
		}
		if($('#uniqueValueId').is(":checked")){
			var uniquename = $('#uniqueValueId').val();
			isuniqueBuild = true;
		}
		var totalUniquelength = totalUniqueArray.length;
		
		
		var str='';
			str+='<table class="table table-bordered table-condensed tableC" id="candidateWiseDataTable">';
							str+='<thead>';
							
								str+='<tr>';
								
									str+='<th rowspan="3"></th>';
									
									str+='<th rowspan="2" colspan="'+totalUniquelength+'">Total Requested</th>';
									str+='<th rowspan="2" colspan="'+totalUniquelength+'">Total Scheduled</th>';
									str+='<th rowspan="2" colspan="'+totalUniquelength+'">Total Waiting</th>';
									
									var colspanForReqType = result[0].subList[0].subList.length * totalUniquelength;
									
									for(var i in result[0].subList){
										str+='<th class="text-center" colspan="'+colspanForReqType+'">'+result[0].subList[i].name+'</th>';
									}
								str+='</tr>';
								
								str+='<tr>';
								for(var i in result[0].subList){
										for(var j in result[0].subList[i].subList){
											str+='<th colspan="'+totalUniquelength+'">'+result[0].subList[i].subList[j].name.capitalize();+'</th>';
										}
									}
								str+='</tr>';
								
								str+='<tr>';
								
									if(isTotalBuild){  
										str+='<th class="text-center">T</th>';
									}
									if(isuniqueBuild){  
										str+='<th class="text-center">U</th>';
									}
									
									if(isTotalBuild){  
										str+='<th class="text-center">T</th>';
									}
									if(isuniqueBuild){  
										str+='<th class="text-center">U</th>';
									}
									
									if(isTotalBuild){  
										str+='<th class="text-center">T</th>';
									}
									if(isuniqueBuild){  
										str+='<th class="text-center">U</th>';
									}
									
									
									for(var i in result[0].subList){
										for(var j in result[0].subList[i].subList){
											if(isTotalBuild){  
												str+='<th class="text-center">T</th>';
											}
											if(isuniqueBuild){  
												str+='<th class="text-center">U</th>';
											}
										}
									}
								str+='</tr>';
								
							str+='</thead>';
							str+='<tbody>';
								
								   
								   for(var i in result){
									   str+='<tr>';
									   
									   str+='<td>'+result[i].name+'</td>';
									   
									   for(var j in result[i].typeList){
										   
										   if(isTotalBuild){  
											  if(result[i].typeList[j].count == 0){
											   str+='<td class="text-center"> - </td>';
										     }else{
											   str+='<td class="text-center">'+result[i].typeList[j].count+'</td>';
										     }
									       }
										   if(isuniqueBuild){  
												 if(result[i].typeList[j].uniqueCount == 0){
												   str+='<td class="text-center"> - </td>';
												}else{
												   str+='<td class="text-center">'+result[i].typeList[j].uniqueCount+'</td>';
												}
										  }
										  
										   
									   }
									    for(var k in result[i].subList){
											
											for(var m in result[i].subList[k].subList){
												
												if(isTotalBuild){
												  if(result[i].subList[k].subList[m].count==0){
													 str+='<td class="text-center"> - </td>';
												 }else{
													str+='<td class="text-center">'+result[i].subList[k].subList[m].count+'</td>';
												 }
												}
												if(isuniqueBuild){ 
													if(result[i].subList[k].subList[m].uniqueCount==0){
													  str+='<td class="text-center"> - </td>';
												   }else{
													str+='<td class="text-center">'+result[i].subList[k].subList[m].uniqueCount+'</td>';
												   }
												}
										        
											}
										   
									   }
									 str+='</tr>';  
								   }
								    str+='<tr>';
								    str+='<td style="font-weight:bold;">Total</td>';
								     for(var i in result[0].typeList){
										 if(isTotalBuild){
											 if(result[0].typeList[i].totalCount == 0){
												 str+='<td class="text-center"> - </td>'; 
											 }else{
												 str+='<td class="text-center" style="font-weight:bold;">'+result[0].typeList[i].totalCount+'</td>'; 
											 }
											
										 }
										 if(isuniqueBuild){ 
											if(result[0].typeList[i].totalUniqueCount == 0){
												 str+='<td class="text-center"> - </td>';
											}else{
												str+='<td class="text-center" style="font-weight:bold;">'+result[0].typeList[i].totalUniqueCount+'</td>'; 
											}
											
										 }
										   
									   }
										for(var i in result[0].subList){
											for(var j in result[0].subList[i].subList){
											 if(isTotalBuild){
												 if(result[0].subList[i].subList[j].totalCount == 0){
													 str+='<td class="text-center"> - </td>'; 
												 }else{
													str+='<td class="text-center" style="font-weight:bold;">'+result[0].subList[i].subList[j].totalCount+'</td>';  
												 }
											}
											if(isuniqueBuild){ 
												if(result[0].subList[i].subList[j].totalUniqueCount == 0){
													str+='<td class="text-center"> - </td>'; 
												}else{
													str+='<td class="text-center" style="font-weight:bold;">'+result[0].subList[i].subList[j].totalUniqueCount+'</td>'; 
												}
												 
											}
										}
									}
								
									
								str+='</tr>';
									
								
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
	
	
	
  function getMemebersByScheduleType(roleId,memberType,countType,scheduleType,aptUserId)
  {
	   
	$("#appointmentMembersDiv").html('<img src="images/search.gif"/>');
  $("#membersModelId").modal("show");
   var apptUserId = $("#appointmentUserSelectBoxId").val();
   var jsObj = {
     roleId : roleId,
    memberType : memberType,
    cntType : countType,
    scheduleType : scheduleType,
	aptUserId : apptUserId,
    task:""
   }
    $.ajax({
    type : 'GET',
    url : 'getAppointmentMembersByScheduleTypeAction.action',
    dataType : 'json',
    data : {task:JSON.stringify(jsObj)}  
    }).done(function(result){ 
       buildAppointmentMembersData(result);
       });   
  }

   function getMemebersByScheduleTypeForRole(roleId,memberType,countType,scheduleType,aptUserId,levelId)
  {
	 
	$("#appointmentMembersDiv").html('<img src="images/search.gif"/>');
  $("#membersModelId").modal("show");
   var apptUserId = $("#appointmentUserSelectBoxId").val();
   var jsObj = {
     roleId : roleId,
    memberType : memberType,
    cntType : countType,
    scheduleType : scheduleType,
	aptUserId : apptUserId,
	levelId:levelId,
    task:""
   }
    $.ajax({
    type : 'GET',
    url : 'getAppointmentMembersByScheduleTypeAction.action',
    dataType : 'json',
    data : {task:JSON.stringify(jsObj)}  
    }).done(function(result){ 
		  buildAppointmentMembersData(result);
      });   
  }
function buildAppointmentMembersData(result)
 {
	
   var str='';
  if(result != null){
			str +='<table class="table table-bordered" id="appntmntMmbrsTbId">';
            str +='<thead>';
				str+='<tr>';
					str+='<th class="text-capitalize">Image</th>';				
					str+='<th class="text-capitalize">NAME</th>';
					//str+='<th class="text-capitalize">Location</th>';
					str+='<th class="text-capitalize">Designation</th>';
					str+='<th class="text-capitalize">Last Visit</th>';
					str+='<th class="text-capitalize">Last Status</th>';
					str+='<th class="text-capitalize">Total Requested Appointment</th>';
					str+='<th class="text-capitalize">Total Attended Appointment</th>';
					//str+='<th class="text-capitalize">Mobile</th>';
				str+='</tr>';
			str+='</thead>';
						str+='<tbody>';
	for(var i in result)
	{
		str +='<tr>';
			str+='<td><img src="'+result[i].imageUrl+'" class="img-responsive img-border img-circle" style="height:40px;width:40px"></td>';
            str +='<td ><div class="col-md-1"><a attr-mobile="'+result[i].mobile+'" attr-designation="'+result[i].designation+'" attr-name="'+result[i].name+'" attr-id="'+result[i].id+'" style="cursor:pointer;" class="historyShowModalBtn" data-placement="top" data-toggle="tooltip" title="" data-original-title="Click here to View '+result[i].name+' History"><i style="color: rgb(142, 142, 142); font-size: 16px;" class="glyphicon glyphicon-time"></i></a></div>&nbsp;&nbsp;'+result[i].name+'</td>';
			/* if(result[i].location != null && result[i].location.length > 0){
			  //str+='<td >'+result[i].location+'</td>';
			}
			else{
			str+='<td>';
			if(result[i].state != null && result[i].state.length > 0)
			str+='S :'+result[i].state+'';
			if(result[i].district != null && result[i].district.length > 0)
			str+='<br>D :'+result[i].district+'';
			if(result[i].constituency != null && result[i].constituency.length > 0)
			str+='<br>C :'+result[i].constituency+'';
			if(result[i].mandal != null && result[i].mandal.length > 0)
			str+='<br>M :'+result[i].mandal+'';
			if(result[i].village != null && result[i].village.length > 0)
			str+='<br>V :'+result[i].village+'';
			str+='</td>';		
			} */
			
			str +='<td>'+result[i].designation+'</td>';
			
			if(result[i].candidateLastVisitDate!=null){
			str +='<td>'+result[i].candidateLastVisitDate+'</td>';
			}else{
			 str +='<td>-</td>'; 	
			}
			if(result[i].candidateLastUpdatedStatus!=null){
			str +='<td>'+result[i].candidateLastUpdatedStatus+'</td>';
			}else{
			 str +='<td>-</td>'; 	
			}
		 	if(result[i].totalRequestedAppCount!=null && result[i].totalRequestedAppCount >0){
				str +='<td>'+result[i].totalRequestedAppCount+'</td>';
			}else{
				str +='<td>-</td>';
			} 
			 if(result[i].totalCompletedAppCount!=null && result[i].totalCompletedAppCount>0){
				str +='<td>'+result[i].totalCompletedAppCount+'</td>';
			}else{
				str +='<td>-</td>';
			} 
           
			//str +='<td>'+result[i].mobile+'</td>';
            
			str +=' </tr>';
	}
	str +='</tbody>';
			str +='</table>';
  }
  else{
	  str+='No Data Available';
  }
			$("#appointmentMembersDiv").html(str);
			$("#appntmntMmbrsTbId").dataTable();
	}
	

/* function buildTtlAppntmntSttsFrAdncDshBrd(result){
	var str='';
	var totalAppts =0;
	$.each(result.statusList,function(index,value){
	    totalAppts = totalAppts + value.totalCount;
	});
	str+='<tr class= "text-center" style="font-weight:bold;"><td>Today Appointments</td><td>'+totalAppts+'</td></tr>';	
	$.each(result.statusList,function(index,value){	
	var color = getColorCodeByStatus(value.status);
		if(value.subList !=null && value.subList.length >0 ){
			
			str+='<tr style="color:'+color+';">';
		
			str+='<td><i class="glyphicon glyphicon-chevron-down changeIcon parentStatusClass pull-right"></i>'+value.status+'</td>';
			if(value.totalCount == 0){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td >'+value.totalCount+'</td>';
			}
			str+='</tr>';
			
			 for(var i in value.subList){
				 var color = getColorCodeByStatus(value.subList[i].status);
				 str+='<tr class="subStatusClass" style="color:'+color+';">';
				str+='<td style="background:#f8f8f8">&nbsp;&nbsp;&nbsp; '+value.subList[i].status+'</td>';
				if(value.subList[i].totalCount == 0){
					str+='<td class="text-center" style="background:#f8f8f8"> - </td>';
				}else{
					str+='<td class"text-center" >'+value.subList[i].totalCount+'</td>';
				} 
				str+='</tr>';
			 }
			
			
		}else{
			str+='<tr style="color:'+color+';">';
		
			str+='<td>'+value.status+'</td>';
			if(value.totalCount == 0){
				str+='<td class="text-center"> - </td>';
			}else{
				str+='<td class="text-center">'+value.totalCount+'</td>';
			}
			str+='</tr>';
		}
	});
	$("#todayApptsForAdvancedDashBrd").html(str);
} */	
	