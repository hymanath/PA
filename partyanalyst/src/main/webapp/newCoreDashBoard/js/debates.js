//var customStartDate =  moment().subtract(1, 'month').startOf('month').format('DD/MM/YYYY')
//var customEndDate = moment().subtract(1, 'month').endOf('month').format('DD/MM/YYYY');

var customStartDate = moment().format('DD/MM/YYYY')
var customEndDate = moment().format('DD/MM/YYYY');

function globalDebateCalls(type)
{
	if(type == "default"){
		$('#dateRangeIdForDebates').data('daterangepicker').setStartDate(moment());
		$('#dateRangeIdForDebates').data('daterangepicker').setEndDate(moment());
		customStartDate = moment().format("DD/MM/YYYY")
		customEndDate = moment().format("DD/MM/YYYY")
		$(".debatesDate").html("TODAY"+" ( "+moment().format("DD/MM/YYYY")+"-"+moment().format("DD/MM/YYYY")+" )");
	}else if(type == "currentMonth"){
		$('#dateRangeIdForDebates').data('daterangepicker').setStartDate(moment().startOf("month"));
		$('#dateRangeIdForDebates').data('daterangepicker').setEndDate(moment().endOf("month"));
		customStartDate = moment().startOf("month").format("DD/MM/YYYY")
		customEndDate = moment().endOf("month").format("DD/MM/YYYY")
		$(".debatesDate").html("THIS MONTH"+" ( "+moment().startOf("month").format("DD/MM/YYYY")+"-"+moment().endOf("month").format("DD/MM/YYYY")+" )");
	}else if(type == "lastMonth"){
		$('#dateRangeIdForDebates').data('daterangepicker').setStartDate(moment().subtract(1,'month').startOf("month"));
		$('#dateRangeIdForDebates').data('daterangepicker').setEndDate(moment().subtract(1,'month').endOf("month"));
		customStartDate = moment().subtract(1,'month').startOf("month").format("DD/MM/YYYY")
		customEndDate = moment().subtract(1,'month').endOf("month").format("DD/MM/YYYY")
		$(".debatesDate").html("LAST MONTH"+" ( "+moment().subtract(1,'month').startOf("month").format("DD/MM/YYYY")+"-"+moment().subtract(1,'month').endOf("month").format("DD/MM/YYYY")+" )");
	}
	$("#dateRangeIdForDebates").val(customStartDate+"/"+customEndDate);
	getPartyWiseTotalDebateDetails();
	if($(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		getSpokesPersonWiseDebate("top");
		 if($(".moreDebatesBlocksIcon").hasClass("unExpandDebatesBlock"))
		{
			getScaleBasedPerformanceCohort();
			getCandidateOverAllPerformanceCohort();
			getChannelAndPartyWiseDetails();
			getRoleBasedPerformanceCohort();
			getRolesPerformanceOfCandidate(0);
			getDebateRolesNew();
			getDebateDesignationWiseTotalDebateDetails();
		}
	}
}
$(document).on("click",".debatesSettingsIcon",function(e){
	e.stopPropagation();
	$(this).closest("[expand-block-heading1='debates']").find(".debatesSettingsBody").show();
});
$(document).on("click",".debatesSettingsBody",function(e){
	e.stopPropagation();
});
$(document).on("click",function(){
	$(".debatesSettingsBody").hide();
});
$(document).on("click",".debatesRefresh",function(){
	globalDebateCalls('');
});
$(document).on("click",".debateradioCls",function(){
	
		if($(this).is(':checked')){
			var checkedVal = $(this).val();
			if(checkedVal == 1){
				$("#debatesAP").prop("checked",true);
				$("#debatesParticipantAP").prop("checked",true);
				
			}else if(checkedVal == 36){
				$("#debatesTS").prop("checked",true);
				$("#debatesParticipantTS").prop("checked",true);
				
			}
		}else{
			var checkedVal = $(this).val();
			if(checkedVal == 1){
				$("#debatesAP").prop("checked",false);
				$("#debatesParticipantAP").prop("checked",false);
				
			}else if(checkedVal == 36){
				$("#debatesTS").prop("checked",false);
				$("#debatesParticipantTS").prop("checked",false);
				
			}
		}
	
	globalDebateCalls('');
	
});
$(document).ready(function(){
	
	
	$("#dateRangeIdForDebates").daterangepicker({
		opens: 'left',
	   // startDate: moment().subtract(1, 'month').startOf('month'),
      //  endDate: moment().subtract(1, 'month').endOf('month'),
		startDate: moment(),
        endDate: moment(),
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
		   'Today' : [moment(), moment()],
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Month': [moment().startOf('month'), moment()],
           'This Year': [moment().startOf('Year'), moment()]
        }
	});
	
	 $(document).on("click",".datesClass",function(){
		var type = $(this).attr("attr_type");
		if(type == "currentMonth"){
			customStartDate = moment().format('DD/MM/YYYY');
			customEndDate = moment().format('DD/MM/YYYY');
			$(".debatesDate").html(" TODAY ( "+customStartDate+" )");
			 $("#dateRangeIdForDebates").val(customStartDate+" - "+customEndDate);
			getPartyWiseTotalDebateDetails();
		}else if(type == "lastMonth"){
			customStartDate = moment().subtract(1, 'month').startOf('month').format('DD/MM/YYYY');
			customEndDate = moment().subtract(1, 'month').endOf('month').format('DD/MM/YYYY');
			$(".debatesDate").html("Last Month ( "+customStartDate+" to "+customEndDate+" )");
			$("#dateRangeIdForDebates").val(customStartDate+" - "+customEndDate);
			getPartyWiseTotalDebateDetails();
		}
	  });
	
	//$(".debatesDate").html(" LAST MONTH ( "+customStartDate+" to "+customEndDate+" )");
	$(".debatesDate").html(" TODAY ( "+customStartDate+" )");
	$('#dateRangeIdForDebates').on('apply.daterangepicker', function(ev, picker) {
	  customStartDate = picker.startDate.format('DD/MM/YYYY');
	  customEndDate = picker.endDate.format('DD/MM/YYYY');
	  getPartyWiseTotalDebateDetails()
	  if(picker.chosenLabel == "Today"){
			$(".debatesDate").html(" TODAY ( "+customStartDate+" )");
		}else{
			$(".debatesDate").html(picker.chosenLabel+" ( "+customStartDate+" to "+customEndDate+" )");
		}
	  if($(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		  $(".debateSpokesCls li").hasClass("active")
		  {
			  if($(".debateSpokesCls li").attr("id") == "debateTopId")
			  {
				  searchType = 'top'  
			  }else{
				  searchType = 'poor'
			  }
		  }
		 getSpokesPersonWiseDebate(searchType);
	  };
	  if($(".moreDebatesBlocksIcon").hasClass("unExpandDebatesBlock"))
	  {
		  getScaleBasedPerformanceCohort();
		  getCandidateOverAllPerformanceCohort();
		  getChannelAndPartyWiseDetails();
		  getRoleBasedPerformanceCohort();
		  getRolesPerformanceOfCandidate(0);
		  getDebateRolesNew();
		  getDebateDesignationWiseTotalDebateDetails();
	  }
	});
});	
 function getPartyWiseTotalDebateDetails(){
		
		$("#partyWiseTotalDebateDetails").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		
		var debateLocationIdArry =[];
     $(".radioStateCls").each(function(){
        if($(this).prop('checked')==true){
               debateLocationIdArry.push($(this).val());
        }else{
			debateLocationIdArry.push(0);
		}
        
      });
	  var participantLocIdArry =[];
     $(".radioStateCls1").each(function(){
        if($(this).prop('checked')==true){
               participantLocIdArry.push($(this).val());
        }else{
			participantLocIdArry.push(0);
		}
        
      });
		var jsObj={
			startDate: customStartDate,
			endDate: customEndDate,
			state:globalState,
			debateLocationIdArry:debateLocationIdArry,
			debateParticipantLocIdArry:participantLocIdArry
		}
		$.ajax({
			type : 'POST',
			url : 'getPartyWiseTotalDebateDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildPartyWiseTotalDebateDetails(result,debateLocationIdArry);			
		});
	}


function buildPartyWiseTotalDebateDetails(result,debateLocationIdArry)
{
	var str='';
	if(result !=null){
		for(var i in result){
			
			if(i==0)
			{
				str+='<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0">';
			}else{
				str+='<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top20">';
			}
			
				//str+='<h4 class="text-capital"><img src="newCoreDashBoard/img/'+result[i].name+'.png" alt="'+result[i].name+' Icon" class="debatesPartyIcon"/>'+result[i].name+'</h4>';
				str+='<table class="table tableTraining bg_ED tableDebatesMain">';
				  str+='<tbody>';
					str+='<tr>';
						str+='<td style="vertical-align:middle;">';
							str+='<img src="newCoreDashBoard/img/'+result[i].name+'.png" alt="'+result[i].name+' Icon" class="debatesPartyIcon"/>'+result[i].name+'';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">total debates</p>';
							str+='<h4><span style="cursor:pointer;color:#337ab7" class="partyWiseDebateCls" attr_partyId='+result[i].id+' attr_state_id ="'+debateLocationIdArry+'" attr_type="debate">'+result[i].debateCount+'</span></h4>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">total spokes persons</p>';
							str+='<h4><span style="cursor:pointer;color:#337ab7" class="partyWiseDebateCls" attr_partyId='+result[i].id+'  attr_state_id ="'+debateLocationIdArry+'" attr_type="candidate">'+result[i].candidateCount+'</span></h4>';
						str+='</td>';
						str+='<td>';
						if(result[i].scalePerc !=null){
						if(result[i].scalePerc.toString().split(".")[1] ==null  || result[i].scalePerc.toString().split(".")[1] == undefined || result[i].scalePerc.toString().split(".")[1].length<0){								
								result[i].scalePerc = result[i].scalePerc.toString().concat(".0");								
							}
						}
							str+='<p class="text-capital">performance</p>';
							str+='<input class="performanceRating" value="'+result[i].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result[i].scalePerc+'</span>';
						str+='</td>';
					str+='</tr>';
				 str+='</tbody>';
				str+='</table>';
			str+='</div>';
			
		}
		$("#partyWiseTotalDebateDetails").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:true,
			hoverOnClear: true,
			animate:false
		});
		/* if(result.length> 6)
		{
			$("#partyWiseTotalDebateDetails").mCustomScrollbar({setHeight:'300px'})
		} */
	}else{
			$("#partyWiseTotalDebateDetails").html('<h3>NO DATA AVAILABLE</h3>')
		}

}
$(document).on("click","#collapseOneId",function(){
	$("#collapseOneBodyId").collapse("toggle");
	$(".arrowChange").find("i").toggleClass("glyphicon-plus").toggleClass("glyphicon-minus");
});
function getScaleBasedPerformanceCohort(){
		
		$("#scaleBasedPerformanceCohort").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		
		var debateLocationIdArry =[];
     $(".radioStateCls").each(function(){
        if($(this).prop('checked')==true){
              debateLocationIdArry.push($(this).val());
        }else{
			debateLocationIdArry.push(0);
		}
        
      });
	  var participantLocIdArry =[];
     $(".radioStateCls1").each(function(){
        if($(this).prop('checked')==true){
               participantLocIdArry.push($(this).val());
        }else{
			participantLocIdArry.push(0);
		}
        
      });
	  
		var jsObj={
			startDate: customStartDate ,
			endDate: customEndDate,
			state:globalState,
			debateLocationIdArry :debateLocationIdArry,
			debateParticipantLocationIdArray :participantLocIdArry
		}
	    $.ajax({
			type : 'POST',
			url : 'getScaleBasedPerformanceCohortAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildScaleBasedPerformanceCohort(result,debateLocationIdArry);
		});
	}
function buildScaleBasedPerformanceCohort(result,debateLocationIdArry)
{
	var str='';
	if(result !=null){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div style="overflow:auto">';
			for(var i in result){
				//str+='<h4 class="text-capital"><img  src="newCoreDashBoard/img/'+result[i].name+'.png" alt="'+result[i].name+'" class="debatesPartyIcon"/>'+result[i].name+'</h4>';
				str+='<table class="table tableDebates tableDebatesMain m_top10">';
				  str+='<tbody>';
					str+='<tr>';
						str+='<td style="width:100px;border-right:1px solid #ddd;vertical-align:middle;">';
							str+='<img  src="newCoreDashBoard/img/'+result[i].name+'.png" alt="'+result[i].name+'" class="debatesPartyIcon"/>'+result[i].name+'';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">overall debates</p>';
							str+='<h4><span class="partyWiseDebateCls" attr_partyId='+result[i].id+' attr_state_id="'+debateLocationIdArry+'" attr_type="debate" style="cursor:pointer;"><a>'+result[i].debateCount+'</a></span></h4>';
						str+='</td>';
						
						str+='<td>';
							str+='<p class="text-capital">overall Performance</p>';
							if(result[i].overAllPerc !=null && result[i].overAllPerc>0.0){
								str+='<p class="text-capital"><input class="performanceRating" value="'+(result[i].overAllPerc / (result[i].coreDebateVOList).length).toFixed(1)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+(result[i].overAllPerc / (result[i].coreDebateVOList).length).toFixed(1)+'</span></p>';
							}else{
								str+='<p class="text-capital"><input class="performanceRating" value="0.0" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">0.0</span></p>';
							}
							
							
						str+='</td>';
						
						for(var j in result[i].coreDebateVOList){
							str+='<td>';
							if(result[i].coreDebateVOList[j].name !=null && result[i].coreDebateVOList[j].name.length>0){
								str+='<p class="text-capital">'+result[i].coreDebateVOList[j].name.split("(")[0];+'</p>';
							}
							
								//New One
								if(result[i].coreDebateVOList[j].scalePerc !=null){
								if(result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1] ==null  || result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1] == undefined || result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1].length<0){								
										result[i].coreDebateVOList[j].scalePerc = result[i].coreDebateVOList[j].scalePerc.toString().concat(".0");								
									}
								}
								
								str+='<p class="text-capital"><input class="performanceRating" value="'+result[i].coreDebateVOList[j].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result[i].coreDebateVOList[j].scalePerc+'</span></p>';
							str+='</td>';
						}
					str+='</tr>';
				 str+='</tbody>';
				str+='</table>';
				str+='<hr class="m_0"/>';
			}
			str+='</div>';
		str+='</div>';
		$("#scaleBasedPerformanceCohort").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});
		if(result.length> 6)
		{
			$("#scaleBasedPerformanceCohort").mCustomScrollbar({setHeight:'300px'})
		}
	}else{
		$("#scaleBasedPerformanceCohort").html('<h3>NO DATA AVAILABLE</h3>')
	}
}
function getCandidateOverAllPerformanceCohort(){
		
		$("#candidateOverAllPerformanceCohort").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var participantLocIdArry =[];
     $(".radioStateCls1").each(function(){
        if($(this).prop('checked')==true){
               participantLocIdArry.push($(this).val());
        }else{
			participantLocIdArry.push(0);
		}
        
      });
	  var debateLocationIdArry =[];
     $(".radioStateCls").each(function(){
        if($(this).prop('checked')==true){
               debateLocationIdArry.push($(this).val());
        }else{
			debateLocationIdArry.push(0);
		}
        
      });
		var jsObj={
			startDate: customStartDate ,
			endDate: customEndDate,
			state:globalState,
			participantLocIdArry:participantLocIdArry,
			debateLocationIdArry:debateLocationIdArry
		}
	    $.ajax({
			type : 'POST',
			url : 'getCandidateOverAllPerformanceCohortAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			BuildCandidateOverAllPerformanceCohort(result,participantLocIdArry);
		});
	}
function BuildCandidateOverAllPerformanceCohort(result,participantLocIdArry)
{
	var str='';
	
	if(result !=null){
		str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
		for(var i in result){
			str+='<div class="panel panel-default collapseDebates">';
				str+='<div class="panel-heading" role="tab" id="heading'+i+'">';
					if(i == 0)
					{
						str+='<a role="button" class="collapseDebatesIcon" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
					}else{
						str+='<a role="button" class="collapseDebatesIcon collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
					}
					
						str+='<h4 class="panel-title"><img  src="newCoreDashBoard/img/'+result[i].coreDebateVOList[0].coreDebateVOList[0].name+'.png" alt="'+result[i].coreDebateVOList[0].coreDebateVOList[0].name+'" class="debatesPartyIcon"/> '+result[i].coreDebateVOList[0].coreDebateVOList[0].name+' spokespersons</h4>';
					str+='</a>';
				str+='</div>';
				if(i == 0)
				{
					str+='<div id="collapse'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+i+'">';
				}else{
					str+='<div id="collapse'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+i+'">';
				}
					str+='<div class="panel-body">';
						str+='<div class="row">';
							str+='<div class="col-md-12 col-xs-12 col-sm-12">';
								str+='<div class="table-responsive scroller'+i+'">';
									str+='<table class="table table-bordered tableDebatesMainText m_top10 dataTableSortingOverAll text-center">';
										str+='<thead>';
											str+='<th>NAME</th><th>DEBATES </th><th>OVERALL PERFORMANCE </th><th>SUBJECT</th><th>PRESENTATION</th><th>COUNTER ATTACK</th><th>BODY LANGUAGE</th>';
										str+='</thead>';
										str+='<tbody>';
											for(var j in result[i].coreDebateVOList){
												str+='<tr>';
													var canidateName = '';
													if(result[i].coreDebateVOList[j].coreDebateVOList[0].candidateName !=null &&
													result[i].coreDebateVOList[j].coreDebateVOList[0].candidateName.length>0){
													  canidateName = getTitleContent(result[i].coreDebateVOList[j].coreDebateVOList[0].candidateName,20);
													}
													//str+='<td class="text-capitalize">'+canidateName+'</td>';
													str+='<td class="text-capitalize">'+canidateName.toUpperCase()+'</td>';
													str+='<td>';
															str+='<h5 ><a class="overAllCandidateCls" attr_party_id ='+result[i].coreDebateVOList[0].coreDebateVOList[0].id+' attr_candidate_id='+result[i].coreDebateVOList[j].coreDebateVOList[0].candidateId+'  style="cursor:pointer;">'+result[i].coreDebateVOList[j].coreDebateVOList[0].debateCount+'</a></h5>';
													str+='</td>';
													
													if(result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc !=null){
													if(result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc.toString().split(".")[1] ==null  || result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc.toString().split(".")[1] == undefined || result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc.toString().split(".")[1].length<0){								
															result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc = result[i].coreDebateVOList[j].coreDebateVOList[0].scalePerc.toString().concat(".0");								
														}
													}
													
													
													str+='<td>';
															str+='<span class="">'+result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc+'</span>';
													str+='</td>';
													
													for(var k in result[i].coreDebateVOList[j].coreDebateVOList){
													
													//Digit Adding
													if(result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc !=null){
													if(result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc.toString().split(".")[1] ==null  || result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc.toString().split(".")[1] == undefined || result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc.toString().split(".")[1].length<0){								
															result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc = result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc.toString().concat(".0");								
														}
													}
													
													str+='<td>';
														/* str+='<p class="text-capital">'+result[i].coreDebateVOList[j].coreDebateVOList[k].charecterName.split("(")[0].toUpperCase()+'</p>'; */
														str+='<input class="performanceRating" value="'+result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc+'</span>';
													str+='</td>';
													}
												str+='</tr>';
											}
										str+='</tbody>';
									str+='</table>';
								str+='</div>';
							str+='<hr class="m_0"/>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		}
		str+='</div>';
		
		
		$("#candidateOverAllPerformanceCohort").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});
		$(".dataTableSortingOverAll").dataTable();
		for(var i in result){
			for(var j in result[i].coreDebateVOList){
				for(var k in result[i].coreDebateVOList[j].coreDebateVOList){
					if(result[i].coreDebateVOList[j].coreDebateVOList[k].length > 6)
					{
						$(".scroller"+i+"").mCustomScrollbar({setHeight:'300px'})
					}
				}
				
			}
		}
		
		
	}else{
		$("#candidateOverAllPerformanceCohort").html('<h3>NO DATA AVAILABLE</h3>')
	}
}
function getChannelAndPartyWiseDetails(){
		
		$("#channelAndPartyWiseDetails").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var debateLocationIdArry =[];
     $(".radioStateCls").each(function(){
        if($(this).prop('checked')==true){
              debateLocationIdArry.push($(this).val());
        }else{
			debateLocationIdArry.push(0);
		}
        
      });
	  var debateParticipantLocIdArry =[];
     $(".radioStateCls1").each(function(){
        if($(this).prop('checked')==true){
               debateParticipantLocIdArry.push($(this).val());
        }else{
			debateParticipantLocIdArry.push(0);
		}
        
      });
		var jsObj={
			startDate: customStartDate,
			endDate: customEndDate,
			state:globalState,
			debateLocationIdArry:debateLocationIdArry,
			debateParticipantLocIdArry:debateParticipantLocIdArry
		}
	    $.ajax({
			type : 'POST',
			url : 'getChannelAndPartyWiseDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildChannelAndPartyWiseDetails(result);
		});
	}
function buildChannelAndPartyWiseDetails(result)
{
	var str='';
	if(result !=null){
		str+='<div class="table-responsive">';
			str+='<table class="table tableDebatesVs m_top10">';
			  str+='<tbody>';
				
					for(var i in result){
						if(result[i].name !=null){
						str+='<tr>';
							
							str+='<td class="b_right1"><img src="newCoreDashBoard/img/'+result[i].name+'.png" class="channelLogo" alt="Ntv Logo" onerror="setDefaultImageOfChannel(this)"/>'+result[i].name+'</td>';
							for(var j in result[i].coreDebateVOList){
								
								//Digit Adding
								if(result[i].coreDebateVOList[j].scalePerc !=null){
								if(result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1] ==null  || result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1] == undefined || result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1].length<0){								
										result[i].coreDebateVOList[j].scalePerc = result[i].coreDebateVOList[j].scalePerc.toString().concat(".0");								
									}
								}
								
								
								str+='<td>';
								if(result[i].coreDebateVOList[j].candidateName == "JANASENA"){
									str+='<p class="text-capital"><img  src="newCoreDashBoard/img/'+result[i].coreDebateVOList[j].candidateName+'.png" style="height:30px;width:30px;" class="debatesPartyIcon"/> '+result[i].coreDebateVOList[j].candidateName+'</p>';
								}else{
									str+='<p class="text-capital"><img  src="newCoreDashBoard/img/'+result[i].coreDebateVOList[j].candidateName+'.png" class="debatesPartyIcon"/> '+result[i].coreDebateVOList[j].candidateName+'</p>';
								}
									str+='<input class="performanceRating" value="'+result[i].coreDebateVOList[j].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result[i].coreDebateVOList[j].scalePerc+'</span>';
								str+='</td>';
							}
						str+='</tr>';
						}
					}
			  str+='</tbody>';
			str+='</table>';
		str+='</div>';
		$("#channelAndPartyWiseDetails").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});
	}else{
		$("#channelAndPartyWiseDetails").html('<h3>NO DATA AVAILABLE</h3>')
	}
	
}
function getSpokesPersonWiseDebate(searchType){
		
		$("#SpokesPersonWiseDebate").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var debateParticipantLocIdArry =[];
     $(".radioStateCls1").each(function(){
        if($(this).prop('checked')==true){
               debateParticipantLocIdArry.push($(this).val());
        }else{
			debateParticipantLocIdArry.push(0);
		}
        
      });
	   var debateLocationIdArry =[];
     $(".radioStateCls").each(function(){
        if($(this).prop('checked')==true){
               debateLocationIdArry.push($(this).val());
        }else{
			debateLocationIdArry.push(0);
		}
        
      });
		var jsObj={
			startDate: customStartDate ,
			endDate: customEndDate,
			searchType:searchType,
			state:globalState,
			debateParticipantLocIdArry :debateParticipantLocIdArry,
			debateLocationIdArry:debateLocationIdArry
		}
	    $.ajax({
			type : 'POST',
			url : 'getSpokesPersonWiseDebateAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildSpokesPersonWiseDebate(result);
		});
	}
function buildSpokesPersonWiseDebate(result){
	var str='';
	if(result != null){		
		for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			if(result[i].coreDebateVOList != null && result[i].coreDebateVOList.length>0)
				str+='<h5 class="text-capital">'+result[i].coreDebateVOList[0].name+'</h5>';
			else
				str+='';
				if($(window).width() < 500)
				{
					str+='<div id="debates'+i+'" style="width:100%;height:150px;"></div>';
				}else if($(window).width() > 500){
					str+='<div id="debates'+i+'" style="width:100%;height:120px;"></div>';
				}else if($(window).width() > 900){
					str+='<div id="debates'+i+'" style="width:100%;height:80px;"></div>';
				}
				
			str+='</div>';
		}		
		$("#SpokesPersonWiseDebate").html(str);
	}	
	if(result != null && result.length > 0){
		for(var i in result){
			var candidateNameAndCompletedCountArray1 =[];
				for(var j in result[i].coreDebateVOList){
					 var obj1 = {							
							name: result[i].coreDebateVOList[j].candidateName.toUpperCase(),
							y: result[i].coreDebateVOList[j].scalePerc
						};
					
					candidateNameAndCompletedCountArray1.push(obj1);
				}
				var getWidth = $("#debates"+i).parent().width()+'px';
				$("#debates"+i).width(getWidth);
				if(candidateNameAndCompletedCountArray1.length !=0){
					$(function () {
						 $("#debates"+i).highcharts({
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
												if($(window).width() < 799)
												{
													return this.value.toString();
												}else if($(window).width() > 800){
													return this.value.toString().substring(0, 10)+'...';
												}
												
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
							

							tooltip: {
								headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
								pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b>'
							},

							series: [{
								name: '',
								dataLabels: {
									enabled: true,
									 formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return Highcharts.numberFormat(this.y);
										}
									}
								},
								data: candidateNameAndCompletedCountArray1
							}],
						 
						});
					});
				}
				
		}
	}else{
		$("#SpokesPersonWiseDebate").html("No Data Available");
	}
	
}
function getRoleBasedPerformanceCohort(){
		
		$("#roleBasedPerformanceCohort").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var debateLocationIdArry =[];
     $(".radioStateCls").each(function(){
        if($(this).prop('checked')==true){
               debateLocationIdArry.push($(this).val());
        }else{
			 debateLocationIdArry.push(0);
		}
        
      });
	   var participantLocIdArry =[];
     $(".radioStateCls1").each(function(){
        if($(this).prop('checked')==true){
               participantLocIdArry.push($(this).val());
        }else{
			participantLocIdArry.push(0);
		}
        
      });
		var jsObj={
			startDate: customStartDate ,
			endDate: customEndDate,
			state:globalState,
			debateLocationIdArry:debateLocationIdArry,
			debateParticipantLocationIdArray:participantLocIdArry
		}
	    $.ajax({
			type : 'POST',
			url : 'getRoleBasedPerformanceCohortAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildRoleBasedPerformanceCohort(result,debateLocationIdArry);
		});
	}
function buildRoleBasedPerformanceCohort(result,debateLocationIdArry)
{
	var str='';
	if(result !=null){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div style="overflow:auto;">';
			for(var i in result){
			
				//str+='<h4 class="text-capital"><img  src="newCoreDashBoard/img/'+result[i].coreDebateVOList[0].name+'.png" alt="'+result[i].coreDebateVOList[0].name+'" class="debatesPartyIcon"/>'+result[i].coreDebateVOList[0].name+'</h4>';
		
				str+='<table class="table tableDebates tableDebatesMain m_top10">';
				  str+='<tbody>';
					str+='<tr>';
						str+='<td>';
							str+='<img  src="newCoreDashBoard/img/'+result[i].coreDebateVOList[0].name+'.png" alt="'+result[i].coreDebateVOList[0].name+'" class="debatesPartyIcon"/>'+result[i].coreDebateVOList[0].name+'';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">overall debates</p>';
							if(result[i].coreDebateVOList[0].overAllDebateCount !=null && result[i].coreDebateVOList[0].overAllDebateCount>0){
							  str+='<h4><span class="partyWiseDebateCls" attr_partyId='+result[i].coreDebateVOList[0].id+' attr_state_id ='+debateLocationIdArry+' attr_type="debate" style="cursor:pointer;"><a>'+result[i].coreDebateVOList[0].overAllDebateCount+'</a></span></h4>';
							}
							
						str+='</td>';
						for(var j in result[i].coreDebateVOList){
						str+='<td>';
						
						if(result[i].coreDebateVOList[j].scalePerc !=null){
						if(result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1] ==null  || result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1] == undefined || result[i].coreDebateVOList[j].scalePerc.toString().split(".")[1].length<0){								
								result[i].coreDebateVOList[j].scalePerc = result[i].coreDebateVOList[j].scalePerc.toString().concat(".0");								
							}
						}
							str+='<p class="text-capital">'+result[i].coreDebateVOList[j].candidateName+' ( '+result[i].coreDebateVOList[j].debateCount+' ) </p>';
							str+='<input class="performanceRating" value="'+result[i].coreDebateVOList[j].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom"  data-readonly>'+result[i].coreDebateVOList[j].scalePerc+'</span>';
						str+='</td>';
						}
					str+='</tr>';
				 str+='</tbody>';
				str+='</table>';
			str+='<hr class="m_0"/>';
			}
			str+='</div>';
		str+='</div>';
		$("#roleBasedPerformanceCohort").html(str)
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});
		if(result.length> 6)
		{
			$("#roleBasedPerformanceCohort").mCustomScrollbar({setHeight:'300px'})
		}
	}else{
		$("#roleBasedPerformanceCohort").html('<h3>NO DATA AVAILABLE</h3>')
	}
}
/* General Click FUnctionalities*/

/* $(document).on("click",".debatesIconExpand",function(){
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".debatesHead").toggleClass('col-md-9 col-sm-9').toggleClass('col-md-8 col-sm-8');
	$(".debatesHead1").toggleClass('col-md-3 col-sm-3').toggleClass('col-md-4 col-sm-4');
	$(".dateRangePickerClsForDebates").toggleClass("hide");
	$(".debatesBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	setTimeout(function(){
		$(".debatesHiddenBlock,.moreMeetingsBlocksIcon").toggle();
		getSpokesPersonWiseDebate("top");
		
	},800);
	if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".debatesMoreHiddenBlock").hide();	
	}else{
		getSpokesPersonWiseDebate("top");
		setTimeout(function(){
			$('html,body').animate({
				scrollTop: $(".debatesBlock").offset().top},
			'slow');
		},500);
	}
	if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
		$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".dateRangePickerCls").toggleClass("hide");
		$(".moreBlocksIcon").removeClass("unExpandBlock");
	}else if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
		$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
		$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
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
	
}); */
$(document).on("click",".moreDebatesBlocksIcon",function(){
	$(this).addClass("unExpandDebatesBlock")
	$(".debatesMoreHiddenBlock").toggle();
	getScaleBasedPerformanceCohort();
	getCandidateOverAllPerformanceCohort();
	getChannelAndPartyWiseDetails();
	getRoleBasedPerformanceCohort();
	getRolesPerformanceOfCandidate(0);
	getDebateRolesNew();
	getDebateDesignationWiseTotalDebateDetails();
});

$(document).on("click","#debateTopId",function(){
	getSpokesPersonWiseDebate("top");
});
$(document).on("click","#debateLowId",function(){
	getSpokesPersonWiseDebate("poor");
});

function setDefaultImageOfChannel(img){
	 img.src = "newCoreDashBoard/img/channel.png";
}
function getTitleContent(name,showCharVal){
	ellipsetext=". ."
	var showChar = showCharVal;
	var content = name;
	if(content!=null){
		if(content.length > showChar) {
		var c = content.substr(0, showChar);
		var html = c + ellipsetext;
		return html;
		}
	}
   
	return name;
}

function getRolesPerformanceOfCandidate(roleId){
		
		$("#candidateRolesPerformanceNewId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var participantLocIdArry =[];
     $(".radioStateCls1").each(function(){
        if($(this).prop('checked')==true){
               participantLocIdArry.push($(this).val());
        }else{
			participantLocIdArry.push(0);
		}
        
      });
	  var debateLocationIdArry =[];
     $(".radioStateCls").each(function(){
        if($(this).prop('checked')==true){
               debateLocationIdArry.push($(this).val());
        }else{
			debateLocationIdArry.push(0);
		}
        
      });
		var jsObj={
			startDate: customStartDate ,
			endDate: customEndDate,
			state:globalState,
			roleId:roleId,
			participantLocIdArry:participantLocIdArry,
			debateLocationIdArry:debateLocationIdArry
		}
		$.ajax({
			type : 'POST',
			url : 'getRolesPerformanceOfCandidateAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildRolesPerformanceOfCandidate(result,participantLocIdArry,roleId);			
		});
	}
	function buildRolesPerformanceOfCandidate(result,participantLocIdArry,roleId){
		var str='';
		if(result !=null && result.length>0){
			str+='<div class="table-responsive">';
				str+='<table class="table tableTopDebates tableDebatesMainText">';			
				for(var i in result){
					str+='<tr>';
							str+='<td class="text-capital" style="width:25% !important" id="'+result[i].id+'">'+result[i].name+'</td>';
							str+='<td class="text-capital">';
								str+='<p>PARTY</p>';
								str+='<p><img src="newCoreDashBoard/img/'+result[i].candidateName+'.png" class="debatesPartyIcon"/>'+result[i].candidateName+'</p>';
							str+='</td>';
							str+='<td class="text-capital">';
								str+='<p>debates</p>';
								str+='<p class="text-muted"><span class="perforamnceDebateCls" attr_partyId='+result[i].candidateId+'  attr_type="debate" style="cursor:pointer;" attr_candidateId='+result[i].id+' attr_performanceId='+roleId+'><a>'+result[i].debateCount+'</a></span></p>';
							str+='</td>';
							
							//Digit Adding
								if(result[i].scalePerc !=null){
							if(result[i].scalePerc.toString().split(".")[1] ==null  || result[i].scalePerc.toString().split(".")[1] == undefined || result[i].scalePerc.toString().split(".")[1].length<0){
										result[i].scalePerc = result[i].scalePerc.toString().concat(".0");				
									}
								}
							
							str+='<td class="text-capital">';
								str+='<p>performance</p>';
								str+='<input class="performanceRating" value="'+result[i].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom"  data-readonly>'+result[i].scalePerc+'</span>';
							str+='</td>';
						str+='</tr>';
				}
				str+='</table>';
			str+='</div>';
		}
		else{
			str+='<div class="text-capital">No Data Available</div>';
		}		
		$("#candidateRolesPerformanceNewId").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});		
	}
	
	function getDebateRolesNew(){
		
		//$("#candidateRolesBuildId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		
		$.ajax({
			type : 'POST',
			url : 'getDebateRolesNewAction.action',
			dataType : 'json',
			data : {}
		}).done(function(result){
			buildDebateRolesNew(result);			
		});
	}
	function buildDebateRolesNew(result){
		var str='';
		if(result !=null && result.length>0){
			str+='<div class="col-md-6 col-xs-12 col-sm-6 pull-right">';
				str+='<ul class="activeUlCls list-inline pull-right candidateRolesCls">';	
				str+='<li id="0" class="active" style="margin-right:3px;">All</li>';				
				for(var i in result){								
					str+='<li id="'+result[i].id+'" style="margin-right:3px;" >'+result[i].name+'</li>';									
				}	
				str+='</ul>';
			str+='</div>';
			
			$("#candidateRolesBuildId").html(str);
		}
		
	}
	 $(document).on("click",".candidateRolesCls li",function(){
		/* if($(this).hasClass("active")){
			$(".perforamnceDebateCls").attr("attr_performanceId",$(this).attr('id'));
		}	 */
		getRolesPerformanceOfCandidate($(this).attr('id'));
			
	 });
	/*Notes Functionality*/
	function displayDashboardCommentsForDebates(dashBoardComponentId){
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
      		 
	     str+='<ul class="notesUlDebates m_top20" style="text-transform: none;font-weight: normal;font-size: 14px;">';  	
            	     
					for(var i in result){ 
                        str+='<li style="margin-top:3px;">'; 
                        str+='<span class="notesTextDebates" id="editTextDebateId'+i+'"  attr_commentId="'+result[i].dashBoardCommentId+'">'+result[i].comment+' </span>- <span class="text-muted"><i>'+result[i].insertedTime+'</i></span>';
					    str+='<i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesDebates" attr_cmt_id="editTextDebateId'+i+'" id="'+result[i].dashBoardCommentId+'" onClick="deleteDashBoardcomments(this.id);"></i>';
                        str+='<i class="glyphicon glyphicon-edit pull-right hoverBlock editNotesDebates" attr_cmt_id="editTextDebateId'+i+'" attr_comment="'+result[i].comment+'"></i>';
                        str+='</li>';
					}
                        str+='</ul>';
						/*str+='<hr/>';
						str+='<div id="debateUpId" style="color:red;"></div>';
                        str+='<label>Create Notes</label>';
                        str+='<textarea class="form-control notesAreaDebates"></textarea>';
                        str+='<button class="btn btn-default btnCustomCreateDebates btn-sm "  onClick="savingDashboardCommentFordebates(3);">create</button>';*/
			
			$("#notesDebatesId").html(str);	 
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

function savingDashboardCommentFordebates(dashboardComponentId){  
  var comment=$(".notesAreaDebates").val();
  if(comment.trim() ==""){
		  $("#debateUpId").html("Notes Required.");
		  return;
	  }
	var editId = $("#cmtDebateId").val();
	//$("#"+editId).parent().html(' ');
	$("#"+editId).html(comment);
	 var dashboardCommentId=0;
	 if($(".notesAreaDebates").attr("attr_commentid")>0)
	 {
		dashboardCommentId=$(".notesAreaDebates").attr("attr_commentid");		
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
				
				//$("#debateUpId").html('update succuss');
				displayDashboardCommentsForDebates(3);
			}
		}			
	});
}
$(document).on("click",".notesIconDebates",function(){
	$(this).closest(".panel-heading").find(".notesDropDown").toggle();
});
$(document).on("click",".deleteNotesDebates",function(){
	$(this).closest("li").remove();
});
$(document).on("click",".editNotesDebates",function(){ 
	var commentId = $(this).attr("attr_cmt_id");
	var commentId1 = $(this).parent().find(".notesTextDebates").attr("attr_commentid");
	var notesHtml = $("#"+commentId).html();
	$(".notesAreaDebates").val(notesHtml);  
	$(".notesAreaDebates").attr("attr_commentid",commentId1);  
	$("#cmtId").val(commentId);
	//$("#cmtId").val();
	$("#debateUpId").html('');		
});

$(document).on("click",".btnCustomCreateDebates",function(){
	var getNewNotes = $(".notesAreaDebates").val();
	var todayDate = moment().format("DD MMMM YYYY");
	var cmtId = $("#cmtId").val();
	var commentText = '<span class="notesText" id="'+cmtId+'" >'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i  class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotesDebates"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes" attr_cmt_id="'+cmtId+'"></i>'; 
	if(cmtId>0)
	$(".notesUlDebates").append("<li>"+commentText+"</li>");
	$(".notesAreaDebates").val('');
});
getLatestDebate();
function getLatestDebate(){	
	$.ajax({
	 type: "POST",
	 url: "getLatestDebateAction.action",
	 data: {}
	}).done(function(result){
		if(result != null){
			$("#lastUpdatedDebateId").html("Last Updated : "+ result.split("/")[1]);
		}
	});	
}

$(document).on("click",".partyWiseDebateCls",function(){
	var popupLocationId =[];
	
	var designationId=0;
	var partyId = $(this).attr("attr_partyId");
	var type = $(this).attr("attr_type");
	var stateId =$(this).attr("attr_state_id");
	var str = stateId;
    var str_array = str.split(',');

   for(var i = 0; i < str_array.length; i++) {
	   popupLocationId.push(str_array[i]);
   }
   $("#debateModelDivId").modal("show");
	getCoreDebateBasicDetailsOfPartyDetails(designationId,partyId,type,popupLocationId);
});
function getCoreDebateBasicDetailsOfPartyDetails(designationId,partyId,type,popupLocationId){
	$(".debateModelCls").html("");	
	$(".debateModelCls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var participantLocIdArry =[];
     $(".radioStateCls1").each(function(){
        if($(this).prop('checked')==true){
               participantLocIdArry.push($(this).val());
        }else{
			participantLocIdArry.push(0);
		}
        
      });
	  var roleId = 0;
	var jsObj={
		partyId:partyId,
		startDate:customStartDate,
		endDate:customEndDate,
		searchType:type,
		candidateId:0,
		popupLocationIdArry:popupLocationId,
		participantLocIdArry:participantLocIdArry,
		roleId:roleId,
		designationId:designationId,
		type : " "
	}		
	$.ajax({
	 type: "POST",
	 url: "getCoreDebateBasicDetailsOfPartyAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		buildCoreDebatesBasicDetailsOfPartyDebates(result,type);
	});	
}
function getCoreDebateBasicDetailsOfParty(designationId,partyId,type,popupLocationId){
	$(".debateModelCls").html("");	
	$(".debateModelCls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var participantLocIdArry =[];
     $(".radioStateCls1").each(function(){
        if($(this).prop('checked')==true){
               participantLocIdArry.push($(this).val());
        }else{
			participantLocIdArry.push(0);
		}
        
      });
	  var roleId = 0;
	  var othersTypeId;
	  if(designationId == 0){
		  othersTypeId ="others";
	  }else{
		 othersTypeId =" "; 
	  }
	var jsObj={
		partyId:partyId,
		startDate:customStartDate,
		endDate:customEndDate,
		searchType:type,
		candidateId:0,
		popupLocationIdArry:popupLocationId,
		participantLocIdArry:participantLocIdArry,
		roleId:roleId,
		designationId:designationId,
		type : othersTypeId
	}		
	$.ajax({
	 type: "POST",
	 url: "getCoreDebateBasicDetailsOfPartyAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		buildCoreDebatesBasicDetailsOfPartyDebates(result,type);
	});	
}

$(document).on("click",".debateDetailsCls",function(){
		var debateId = $(this).attr("attr_debateId");
        var  stateId = $(this).attr("attr_state_id");
		window.open("debateReportAction.action?debateId="+debateId+"&stateId="+stateId+"");
		//window.open('debateReportAction.action?debateId='+debateId+'','_blank');		 
	});
	
	$(document).on("click",".overAllCandidateCls",function(){
		$("#debateModelDivId").modal("show");
		var partyId = $(this).attr("attr_party_id");		
		var candidateId = $(this).attr("attr_candidate_id");
        var designationId=0;		
		getCandidateWiseDebateDetailsOfCore(partyId,candidateId,designationId);		
	});
	$(document).on("click",".overAllDesignationCandidateCls",function(){
		$("#debateParticipantModelDivId").modal("show");
		var partyId =0;
		var designationId = $(this).attr("attr_party_id");	
		var candidateId = $(this).attr("attr_candidate_id");	
		getCandidateWiseDebateDetailsOfCore(partyId,candidateId,designationId);		
	});
function getCandidateWiseDebateDetailsOfCore(partyId,candidateId,designationId){
	$(".debateParticipantModelCls").html("");	
	$(".debateParticipantModelCls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var debateLocationIdArry =[];
     $(".radioStateCls").each(function(){
        if($(this).prop('checked')==true){
               debateLocationIdArry.push($(this).val());
        }else{
			debateLocationIdArry.push(0);
		}
        
      }); 	
  var participantLocIdArry =[];
     $(".radioStateCls1").each(function(){
        if($(this).prop('checked')==true){
               participantLocIdArry.push($(this).val());
        }else{
			participantLocIdArry.push(0);
		}
        
      });
	  var roleId = 0;
	   if(designationId != null){
		   var desiognationId =0;
		}else{
			var desiognationId=designationId;
		}
	var jsObj={
		partyId:partyId,
		startDate:customStartDate,
		endDate:customEndDate,
		candidateId:candidateId,
		debateLocationIdArry:debateLocationIdArry,
		participantLocIdArry:participantLocIdArry,
		roleId:roleId,
		designationId:desiognationId
	}		
	$.ajax({
	 type: "POST",
	 url: "getCandidateWiseDebateDetailsOfCoreAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		buildDebateModelDetails(result,"debate");
	});
}
function buildDebateModelDetails(result,type){
		var str = '';
		if(result !=null && result.length>0){
								
			str+= '<div class="col-md-12 col-xs-12 col-sm-12">';
		
			str+= '<div class="table-responsive">';
			str+= '<table class="table table-bordered table-condensed debateFirstBlockNewCls">';
				str+= '<thead style="background:#ccc">';
				if(type =="candidate"){
					str+='<th>Candidate Name</th>';
				}
					str+='<th>Subject</th>';
					str+='<th>Start Time</th>';
					str+='<th>End Time</th>';
					str+='<th>Observer</th>';
					str+='<th>Channel</th>';

				str+= '</thead>';
				str+= '<tbody>';					
					for(var i in result){
						str+='<tr>';
							var name='';
							var subject='';
							var candiName='';
							if(result[i].debateSubject !=null && result[i].debateSubject.length>0){
								for(var j in result[i].debateSubject){
										name=name.concat(result[i].debateSubject);
								}
									subject=getTitleContent(name,30);
									if(result[i].candidateName !=null && result[i].candidateName.length>0)
										candiName=getTitleContent(result[i].candidateName,30);
							}		
						if(type =="candidate"){
							str+='<td class="debateDetailsCls" attr_debateId='+result[i].id+'  style="cursor:pointer;"><a>'+candiName.toUpperCase()+'</a></td>';
						}							
							str+='<td class="debateDetailsCls" attr_debateId='+result[i].id+'  style="cursor:pointer;"><a>'+subject+'</a></td>';
							str+='<td>'+result[i].startTime+'</td>';
							str+='<td>'+result[i].endTime+'</td>';
							str+='<td>'+result[i].observerName+'</td>';
							str+='<td>'+result[i].charecterName+'</td>';
						str+='</tr>';						
					}
					
				str+= '</tbody>';
			str+= '</table>';
			str+= '</div>';""
			str+= '</div>';
			str+= '</div>';
			str+= '</div>';
			
			$(".debateParticipantModelCls").html(str);
			$('.debateFirstBlockNewCls').dataTable({
				"iDisplayLength": 15,
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			 });
		}
		
}
function buildCoreDebatesBasicDetailsOfPartyDebates(result,type){
	var str = '';
		if(result !=null && result.length>0){
								
			
		
			str+= '<div class="table-responsive">';
			str+= '<table class="table table-bordered table-condensed debateFirstBlockCls'+type+'">';
				str+= '<thead style="background:#ccc">';
				str+='<tr>';
				if(type =="candidate"){
					str+='<th>Candidate Name</th>';
				}
					str+='<th>Subject</th>';
					str+='<th>Start Time</th>';
					str+='<th>End Time</th>';
					str+='<th>Observer</th>';
					str+='<th>Channel</th>';
				str+='</tr>';
				str+= '</thead>';
				str+= '<tbody>';					
					for(var i in result){
						str+='<tr>';
							var name='';
							var name='';
							var subject='';
							var candiName='';
							if(result[i].debateSubject !=null && result[i].debateSubject.length>0){
								for(var j in result[i].debateSubject){
										name=name.concat(result[i].debateSubject);
								}
									subject=getTitleContent(name,30);
									if(result[i].candidateName !=null && result[i].candidateName.length>0)
										candiName=getTitleContent(result[i].candidateName,30);
							}		
						if(type =="candidate"){
							str+='<td class="debateDetailsCls" attr_debateId='+result[i].id+'  style="cursor:pointer;"><a>'+candiName.toUpperCase()+'</a></td>';
						}							
							str+='<td class="debateDetailsCls" attr_debateId='+result[i].id+'   style="cursor:pointer;"><a>'+subject+'</a></td>';
							str+='<td>'+result[i].startTime+'</td>';
							str+='<td>'+result[i].endTime+'</td>';
							str+='<td>'+result[i].observerName+'</td>';
							str+='<td>'+result[i].charecterName+'</td>';
						str+='</tr>';						
					}
					
				str+= '</tbody>';
			str+= '</table>';
			str+= '</div>';
			
			
			$(".debateModelCls").html(str);
			$('.debateFirstBlockCls'+type).dataTable({
				"iDisplayLength": 15,
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			 });
		}
		
}
$(document).on("click",".perforamnceDebateCls",function(){
	$("#debateModelDivId").modal("show");
	var partyId = $(this).attr("attr_partyId");
	var type = $(this).attr("attr_type");
	var candidateId = $(this).attr("attr_candidateId");
	var roleId = $(this).attr("attr_performanceId");
	var designationId=0;
	$(".debateModelCls").html("");	
	$(".debateModelCls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   var debateLocationIdArry =[];
     $(".radioStateCls").each(function(){
        if($(this).prop('checked')==true){
               debateLocationIdArry.push($(this).val());
        }else{
			debateLocationIdArry.push(0);
		}
        
      }); 
  var participantLocIdArry =[];
     $(".radioStateCls1").each(function(){
        if($(this).prop('checked')==true){
               participantLocIdArry.push($(this).val());
        }else{
			participantLocIdArry.push(0);
		}
        
      });
	var jsObj={
		partyId:partyId,
		startDate:customStartDate,
		endDate:customEndDate,
		searchType:type,
		candidateId:candidateId,
		popupLocationIdArry:debateLocationIdArry, 
		participantLocIdArry:participantLocIdArry,
		roleId:roleId,
		designationId:designationId,
		type : " "//others
	}		
	$.ajax({
	 type: "POST",
	 url: "getCoreDebateBasicDetailsOfPartyAction.action",
	 data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		buildCoreDebatesBasicDetailsOfPartyDebates(result,type);
	});	
});
$(document).on("click",".debatesSettingsCloseBody",function(){
	$(this).closest(".debatesSettingsBody").hide();
});

function getDesignationWiseCandidateOverAllPerformanceCohort(divId,id){
		//$("#designationWisecandidateOverAllPerformanceCohort").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$(".debateModelCls").html("");	
		$(".debateModelCls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var participantLocIdArry =[];
     $(".radioStateCls1").each(function(){
        if($(this).prop('checked')==true){
               participantLocIdArry.push($(this).val());
        }else{
			participantLocIdArry.push(0);
		}
        
      });
	  var debateLocationIdArry =[];
     $(".radioStateCls").each(function(){
        if($(this).prop('checked')==true){
               debateLocationIdArry.push($(this).val());
        }else{
			debateLocationIdArry.push(0);
		}
        
      });
	  var othersTypeId;
	 if(id == 0){
		 othersTypeId = "others";
	 }else{
		 othersTypeId = "";
	 }
		var jsObj={
			startDate:customStartDate,
		    endDate:customEndDate,
			state:globalState,
			participantLocIdArry:participantLocIdArry,
			debateLocationIdArry:debateLocationIdArry,
			type : othersTypeId
		}
	    $.ajax({
			type : 'POST',
			url : 'getDesignationWiseCandidateOverAllPerformanceCohortAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildDesignationWiseCandidateOverAllPerformanceCohort(result,participantLocIdArry,divId,id);
		});
	}

	function getDebateDesignationWiseTotalDebateDetails(){
		$("#designationWiseTotalDebateDetails").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var debateLocationIdArry =[];
     $(".radioStateCls").each(function(){
        if($(this).prop('checked')==true){
               debateLocationIdArry.push($(this).val());
        }else{
			debateLocationIdArry.push(0);
		}
        
      });
	  var participantLocIdArry =[];
     $(".radioStateCls1").each(function(){
        if($(this).prop('checked')==true){
               participantLocIdArry.push($(this).val());
        }else{
			participantLocIdArry.push(0);
		}
        
      });
		var jsObj={
			startDate:customStartDate,
		    endDate:customEndDate,
			state:globalState,
			debateLocationIdArry:debateLocationIdArry,
			debateParticipantLocIdArry:participantLocIdArry
		}
		$.ajax({
			type : 'POST',
			url : 'getDebateDesignationWiseTotalDebateDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length>0){
			 buildDebateDesignationWiseTotalDebateDetails(result,debateLocationIdArry);
			}else{
				$("#designationWiseTotalDebateDetails").html('NO DATA AVAILABLE');
			}			 
		});
	}
	function buildDebateDesignationWiseTotalDebateDetails(result,debateLocationIdArry)
{

	var str='';
	if(result !=null){
		//for(var i in result){
			
			/* if(i==0)
			{
				str+='<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0">';
			}else{
				str+='<div class="col-md-12 col-xs-12 col-sm-12 col-md-offset-0 m_top20">';
			}
				 */
			str+='<div class="table-responsive m_top20">';
				//str+='<h4 class="text-capital"><img src="newCoreDashBoard/img/'+result[i].name+'.png" alt="'+result[i].name+' Icon" class="debatesPartyIcon"/>'+result[i].name+'</h4>';
				//str+='<table class="table table-bordered  tableDebatesMain dataTableSorting" id="debatesDesignationWiseDtlsId">';
				str+='<table class="table table-bordered  dataTableSorting" id="debatesDesignationWiseDtlsId">';
				str+='<thead>';
					str+='<tr>';
						str+='<th class="text-capital">designations</th>';
						str+='<th class="text-capital">total debates</th>';
						str+='<th class="text-capital">total spokes persons</th>';
						str+='<th class="text-capital">performance</th>';
					str+='</tr>';
				str+='</thead>';
				
				  str+='<tbody>';
					for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].name+'</td>';
						str+='<td><span style="cursor:pointer;color:#337ab7" class="designationWiseDebateCls" attr_partyId='+result[i].id+' attr_state_id ="'+debateLocationIdArry+'" attr_type="debate">'+result[i].debateCount+'</span></td>';
						if(result[i].candidateCount>0){
						str+='<td style="cursor:pointer;color:#337ab7" class="designationWiseCandidatesCls designationCandidateCls" attr_divId="'+result[i].name.replace(/\s+/g, '')+'" attr_id="'+result[i].id+'">'+result[i].candidateCount+'</td>';
						str+='<td>';
						}else{
						str+='<td style="color:#337ab7"  attr_divId="'+result[i].name.replace(/\s+/g, '')+'" attr_id="'+result[i].id+'">'+result[i].candidateCount+'</td>';
						str+='<td>';
						}
						str+='<input class="performanceRating" value="'+result[i].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result[i].scalePerc+'</span>';
						str+='</td>';
					str+='</tr>';
					}
					/*str+='<tr>';
						str+='<td style="vertical-align:middle;">';
							str+=''+result[i].name+'';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">total debates</p>';
							str+='<h4><span style="cursor:pointer;color:#337ab7" class="designationWiseDebateCls" attr_partyId='+result[i].id+' attr_state_id ="'+debateLocationIdArry+'" attr_type="debate">'+result[i].debateCount+'</span></h4>';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">total spokes persons</p>';
							str+='<h4><span style="cursor:pointer;color:#337ab7" class="designationWiseDebateCls" attr_partyId='+result[i].id+'  attr_state_id ="'+debateLocationIdArry+'" attr_type="candidate">'+result[i].candidateCount+'</span></h4>';
						str+='</td>';
						str+='<td>';
						if(result[i].scalePerc !=null){
						if(result[i].scalePerc.toString().split(".")[1] ==null  || result[i].scalePerc.toString().split(".")[1] == undefined || result[i].scalePerc.toString().split(".")[1].length<0){								
								result[i].scalePerc = result[i].scalePerc.toString().concat(".0");								
							}
						}
							str+='<p class="text-capital">performance</p>';
							str+='<input class="performanceRating" value="'+result[i].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result[i].scalePerc+'</span>';
						str+='</td>';
					str+='</tr>';*/
				 str+='</tbody>';
				str+='</table>';
				//str+='<div class="row">';
					//str+='<h4><span class="pull-right designationWiseCandidatesCls" attr_divId="'+result[i].name.replace(/\s+/g, '')+'" attr_id="'+result[i].id+'" style="border: 2px solid #ddd;padding: 6px;margin-top: 10px;cursor:pointer;">+</span></h4>';
					//str+='<div class="'+result[i].name.replace(/\s+/g, '')+'">';
					//str+='<div id="'+result[i].name.replace(/\s+/g, '')+'" style="display:none;margin-top:50px;"></div>';
				//str+='</div>';
				
				
			str+='</div>';
			
		//}
		$("#designationWiseTotalDebateDetails").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:true,
			hoverOnClear: true,
			animate:false
		});
		//$("#debatesDesignationWiseDtlsId").dataTable();
	}else{
			$("#designationWiseTotalDebateDetails").html('<h3>NO DATA AVAILABLE</h3>')
		}

}
function buildDesignationWiseCandidateOverAllPerformanceCohort(result,participantLocIdArry,divId,id)
{
	var str='';
	var othersStr= "others";
	if(result !=null){
		str+='<div class="panel-group">';
		for(var i in result){
			if(id == result[i].id){
					str+='<div class="panel panel-default">';
					str+='<div class="panel-heading">';
					if(result[i].coreDebateVOList[0].coreDebateVOList[0].name != null){
					str+='<h4 class="panel-title">'+result[i].coreDebateVOList[0].coreDebateVOList[0].name+' spokespersons</h4>';
					}else{
					str+='<h4 class="panel-title">'+othersStr+' spokespersons</h4>';
					}
					str+='</div>';
						str+='<div class="panel-body">';
							str+='<div class="row">';
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									str+='<div class="table-responsive scroller'+i+'">';
										str+='<table class="table table-bordered tableDebatesMainText m_top10 dataTableSorting text-center">';
											str+='<thead>';
												str+='<th>NAME</th><th>DEBATES </th><th>OVERALL PERFORMANCE </th><th>SUBJECT</th><th>PRESENTATION</th><th>COUNTER ATTACK</th><th>BODY LANGUAGE</th>';
											str+='</thead>';
											str+='<tbody>';
												for(var j in result[i].coreDebateVOList){
													str+='<tr>';
														var canidateName = '';
														var partyName='';
														if(result[i].coreDebateVOList[j].coreDebateVOList[0].candidateName !=null &&
														result[i].coreDebateVOList[j].coreDebateVOList[0].candidateName.length>0){
														  canidateName = getTitleContent(result[i].coreDebateVOList[j].coreDebateVOList[0].candidateName,20);
														}
														if(result[i].coreDebateVOList[j].coreDebateVOList[0].partyName != null){
															partyName= result[i].coreDebateVOList[j].coreDebateVOList[0].partyName;
														}
														  //candiDesignationName =result[i].coreDebateVOList[j].candidateDesignation;
														/* if(canidateName != null && candiDesignationName != null && candiDesignationName != " "){
														  str+='<td class="text-capitalize">'+canidateName.toUpperCase()+' ('+candiDesignationName+')</td>';
														}else{ */
														  str+='<td class="text-capitalize">'+canidateName.toUpperCase()+' ('+partyName+')</td>';
														//}
														str+='<td>';
																str+='<h5 ><a class="overAllDesignationCandidateCls" attr_party_id ='+result[i].coreDebateVOList[0].coreDebateVOList[0].id+' attr_candidate_id='+result[i].coreDebateVOList[j].coreDebateVOList[0].candidateId+'  style="cursor:pointer;">'+result[i].coreDebateVOList[j].coreDebateVOList[0].debateCount+'</a></h5>';
														str+='</td>';
														
														if(result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc !=null){
														if(result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc.toString().split(".")[1] ==null  || result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc.toString().split(".")[1] == undefined || result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc.toString().split(".")[1].length<0){								
																result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc = result[i].coreDebateVOList[j].coreDebateVOList[0].scalePerc.toString().concat(".0");								
															}
														}
														
														
														str+='<td>';
																str+='<span class="">'+result[i].coreDebateVOList[j].coreDebateVOList[0].overAllPerc+'</span>';
														str+='</td>';
														
														for(var k in result[i].coreDebateVOList[j].coreDebateVOList){
														//Digit Adding
														if(result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc !=null){
														if(result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc.toString().split(".")[1] ==null  || result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc.toString().split(".")[1] == undefined || result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc.toString().split(".")[1].length<0){								
																result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc = result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc.toString().concat(".0");								
															}
														}
														
														str+='<td>';
															/* str+='<p class="text-capital">'+result[i].coreDebateVOList[j].coreDebateVOList[k].charecterName.split("(")[0].toUpperCase()+'</p>'; */
															str+='<input class="performanceRating" value="'+result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result[i].coreDebateVOList[j].coreDebateVOList[k].scalePerc+'</span>';
														str+='</td>';
														}
													str+='</tr>';
												}
											str+='</tbody>';
										str+='</table>';
									str+='</div>';
								str+='<hr class="m_0"/>';
								//str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			}
			
		}
		str+='</div>';
		
		$(".debateModelCls").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});
		$(".dataTableSorting").dataTable();
		for(var i in result){
			for(var j in result[i].coreDebateVOList){
				for(var k in result[i].coreDebateVOList[j].coreDebateVOList){
					if(result[i].coreDebateVOList[j].coreDebateVOList[k].length > 6)
					{
						$(".scroller"+i+"").mCustomScrollbar({setHeight:'300px'})
					}
				}
				
			}
		}
		
		
	}else{
		$("#designationWisecandidateOverAllPerformanceCohort").html('<h3>NO DATA AVAILABLE</h3>')
	}
}
$(document).on("click",".designationWiseDebateCls",function(){
	var popupLocationId =[];
	
	var designationId = $(this).attr("attr_partyId");
	var partyId=0;
	var type = $(this).attr("attr_type");
	var stateId =$(this).attr("attr_state_id");
	var str = stateId;
    var str_array = str.split(',');

   for(var i = 0; i < str_array.length; i++) {
	   popupLocationId.push(str_array[i]);
   }
   $("#debateModelDivId").modal("show");
	getCoreDebateBasicDetailsOfParty(designationId,partyId,type,popupLocationId);
});
$(document).on("click",".designationWiseCandidatesCls",function(){
//$(document).on("click",".designationCandidateCls",function(){
	$("#debateModelDivId").modal("show");
	var divId = $(this).attr("attr_divId");
	var id = $(this).attr("attr_id");
	var blockType = $(this).html();
	/*if(blockType == '+')
	{
		$(this).html("-");
		$("#"+divId).toggle();
	}else{
		$(this).html("+");
		$("#"+divId).toggle();
	}*/
	getDesignationWiseCandidateOverAllPerformanceCohort(divId,id);
	
});
$(document).on("click",".closeModalDeb",function(){
	setTimeout(function(){
		$('body').addClass("modal-open");
	}, 400);                     
});
$(document).on("click","#debatesTabViewDebateId",function(){
	 $("#headingId").html("Debate Location");                   
});
$(document).on("click","#debatesTabViewParticipantId",function(){
	 $("#headingId").html("Participant Location");                   
});