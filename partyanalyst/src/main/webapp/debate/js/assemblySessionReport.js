function globalOnLoadCalls()
{
	getElectionYears(1);
	getElectionYears(2);
	//getElectionYears1();
	//getSessionYears();
	//getAllSessions();
	//getDates();
	//getSessionDetails();
	//getDayWiseDetails();
}
function onLoadInitialisations()
{
	$("#dateRange").daterangepicker({
		locale:{
			format: 'DD/MM/YYYY'
		},
	});
	
	$(document).on("click",".expand-icon",function(){
		var count = $(this).attr("attr_id");
		if($(this).find("i").hasClass("glyphicon-plus"))
		{
			$(this).find("i").removeClass("glyphicon-plus").addClass("glyphicon-minus");
			$("#expandView"+count).show();
		}else{
			$(this).find("i").addClass("glyphicon-plus").removeClass("glyphicon-minus");
			$("#expandView"+count).hide();
		}
	});
	
	$("#UpdateStartdateRange").daterangepicker({
		 singleDatePicker: true,
		locale:{
			format: 'DD/MM/YYYY'
		},
	});
	$(document).on("click",".expand-Member-icon",function(){
		var count = $(this).attr("attr_id");
		if($(this).find("i").hasClass("glyphicon-plus"))
		{
			$(this).find("i").removeClass("glyphicon-plus").addClass("glyphicon-minus");
			$(".expandViewModal"+count).show();
		}else{
			$(this).find("i").addClass("glyphicon-plus").removeClass("glyphicon-minus");
			$(".expandViewModal"+count).hide();
		}
	});
}
function getElectionYears(id){
	
	var jObj = {
	};		
	$.ajax({
		type:'POST',
		url: 'getAllElectionYearsAction.action',
		data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		var str='';
		if(result != null && result.length >0)
		{
			
			//str+='<select class="chosen-select" onchange="getSessionYears();">';
			str+='<option value="0">Select Election Year</option>';
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].date+'</option>';
			}
			str+='</select>';
		}
		if(id == 1){
			$("#electionYear").html(str);
			$("#electionYear").trigger("chosen:updated");
		}if(id == 2){
			$("#electionYearId").html(str);
			$("#electionYearId").trigger("chosen:updated");
		}
		$(".chosen-select").chosen({width:'100%'});
	});
}


function getSessionYears(id){
	var electionYear;
	if(id == 1){
		$("#procesingImg1").show();
		 electionYear = $("#electionYear").val();
	}else if(id == 2){
		$("#procesingImg3").show();
		electionYear = $("#electionYearId").val();
	}
	
	var jObj = {
		elctionYearId : electionYear
	};		
	$.ajax({
		  type:'POST',
		  url: 'getSessionYearsAction.action',
		 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		$("#procesingImg1").hide();
		$("#procesingImg3").hide();
		var str='';
		if(result != null && result.length >0)
		{
			str+='<option value="">All</option>';
			for(var i in result)
			{
				str+='<option value="'+result[i].name+'">'+result[i].partyName+'</option>';
			}
			//str+='</select>';
		}
		if(id == 1){
			$("#sessionYear").html(str);
			$("#sessionYear").trigger("chosen:updated");
		}else if(id == 2){
			$("#sessionYearId").html(str);
			$("#sessionYearId").trigger("chosen:updated");
		}
		$(".chosen-select").chosen({width:'100%'});
	});
}


function getAllSessions(id){
	var electionYear;
	var sessionYear
	if(id == 1){
		$("#procesingImg2").show();
		 electionYear = $("#electionYear").val();
		 sessionYear = $("#sessionYear").val();
	}else if(id == 2){
		$("#procesingImg4").show();
		electionYear = $("#electionYearId").val();
		sessionYear = $("#sessionYearId").val();
	}
		
	var jObj = {
		elctionYearId : electionYear,
		sessionYear : sessionYear //"2017"
	};		
	$.ajax({
		type:'POST',
		url: 'getAllSessionListAction.action',
		data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		$("#procesingImg2").hide();
		$("#procesingImg4").hide();
		var str='';
		if(result != null && result.length >0)
		{
			//str+='<select class="chosen-select">';
			str+='<option value="0">All</option>';
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			//str+='</select>';
		}
		if(id == 1){
			$("#assemblySession").html(str);
			$("#assemblySession").trigger("chosen:updated");
		}else if(id == 2){
			alert(32)
			$("#assemblySessionId").html(str);
			$("#assemblySessionId").trigger("chosen:updated");
		}
		$(".chosen-select").chosen({width:'100%'});
	});
}


function getDates(){
	
	var electionYear = $("#electionYear").val();
	var sessionYear = $("#sessionYear").val();
	var sessionId = $("#assemblySession").val();
	
	var jObj = {
		elctionYearId : electionYear,
		sessionYear : sessionYear,//"2017",
		sessionId : sessionId
	};		
	$.ajax({
		  type:'POST',
		  url: 'getDatesAction.action',
		 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		
		var fYear = result[0].name.split('-')[0];
		var fMonth = result[0].name.split('-')[1];
		var fDate = result[0].name.split('-')[2].substring(0,2);
		var tYear = result[0].partyName.split('-')[0];
		var tMonth = result[0].partyName.split('-')[1];
		var tDate = result[0].partyName.split('-')[2].substring(0,2);
		
		$('#dateRange').data('daterangepicker').setStartDate(fDate+'/'+fMonth+'/'+fYear);
		$('#dateRange').data('daterangepicker').setEndDate(tDate+'/'+tMonth+'/'+tYear);
	});
}

function getSessionDetails(){
	$("#assmblySessionBlock").show();
	$('#sessionDetails').html("<img src='images/Loading-data.gif'/>");
	var startDate;
	var endDate;
	var electionYear = $("#electionYear").val();
	var sessionYear = $("#sessionYear").val();
	var sessionId = $("#assemblySession").val();
	var dateStr = $('#dateRange').val();
	var dateArr = dateStr.split("-");
	if(dateArr != null && dateArr.length > 0){
		startDate = dateArr[0];
		endDate =  dateArr[1]; 
	}
	var jObj = {
		elctionYearId : electionYear,
		sessionYear : sessionYear,//"2017",
		sessionId : sessionId,
		startDate : "",//23-02-2016
		endDate : ""//11-05-2016
	};		
	$.ajax({
		type:'POST',
		url: 'getSessionDetailsAction.action',
		data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null )
		{
			buildSessionDetails(result)
		}else{
			$("#sessionDetails").html("NO DATA AVAILABLE");
		}
	});
}
function buildSessionDetails(result)
{
	var str='';
	str+='<table class="table">';
		str+='<thead>';
			str+='<th>Session Start Date</th>';
			str+='<th>Season</th>';
			str+='<th>No Of Days</th>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result)
			{
				str+='<tr>';
					str+='<td><span class="expand-icon" attr_id="'+i+'"><i class="glyphicon glyphicon-plus"></i></span>'+result[i].startDate+'</td>';
					str+='<td>'+result[i].name+'</td>';
					str+='<td>'+result[i].count+'</td>';
				str+='</tr>';
				str+='<tr id="expandView'+i+'" style="display:none;" class="expandView"><td colspan="3">';
					str+='<table class="table table-hover table-striped" style="border:1px solid #ddd;">';
						str+='<thead>';
							str+='<th>Date</th>';
							str+='<th>Party-Members</th>';
							str+='<th>Total</th>';
							str+='<th>Submited Date</th>';
						str+='</thead>';
						for(var j in result[i].candidateList)
						{
							str+='<tr>';
								str+='<td>'+result[i].candidateList[j].date+'</td>';
								str+='<td>';
									str+='<ul class="list-inline">';
									for(var k in result[i].candidateList[j].partyList)
									{
										if(result[i].candidateList[j].partyList[k].count != null && result[i].candidateList[j].partyList[k].count > 0 )
											str+='<li>'+result[i].candidateList[j].partyList[k].partyName+'</li>';
									}
									str+='</ul>';
								str+='</td>';
								str+='<td atr_session_day_id="'+result[i].candidateList[j].adminHouseSessionDayId+'" class="sessionCls" style="cursor:pointer;"><u>'+result[i].candidateList[j].count+'</u></td>';
								str+='<td>'+result[i].candidateList[j].startDate+'</td>';
							str+='</tr>';
						}
					str+='</table>';
				str+='</td></tr>';
			}
		str+='</tbody>';
	str+='</table>';
	$("#sessionDetails").html(str);
}
$(document).on("click",".sessionCls",function(){
	$("#memberDetailsModalDiv").modal('show');
	$('#memberDetailsId').html("<img src='images/Loading-data.gif'/>");
	var sessionDayId = $(this).attr("atr_session_day_id");
	
	var jObj = {
				 adminHouseSessionDayId : sessionDayId
			};		
			$.ajax({
				  type:'POST',
				  url: 'getDayWiseDetailsAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				 buildMemberDetails(result,sessionDayId);
			 });
});
getPatries("partyId00");
function getPatries(id){
	//alert(id)
	var jObj = {
			};		
			$.ajax({
				  type:'POST',
				  url: 'getPartiesAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				 $('#'+id).empty();
				 $('#'+id).append('<option value="0">All</option>');
				 if(result != null && result.length >0)
					{
						for(var i in result)
						$('#'+id).append('<option value="'+result[i].partyId+'">'+result[i].partyName+'</option>');
					}
					$('#'+id).trigger("chosen:updated");
			 });
}
getCandidates("memberNameCls","");
function getCandidates(id,partyDivId){
	$('.'+id).empty();
	//alert(id)
	//alert(partyDivId)
	var partyId = 0;
	if(partyDivId != ""){
		 partyId = $("."+partyDivId).val();
	}
	//alert(partyId)
	var jObj = {
				partyId : partyId
			};		
			$.ajax({
				  type:'POST',
				  url: 'getcandidatesForPartyAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				 $('.'+id).empty();
				 $('.'+id).append('<option value="0">All</option>');
				 if(result != null && result.length >0)
					{
						for(var i in result)
						$('.'+id).append('<option value="'+result[i].adminHouseMemberId+'">'+result[i].name+'</option>');
					}
					$('.'+id).trigger("chosen:updated");
			 });
}

function buildMemberDetails(result,sessionDayId){
	var str='';
	var total;
	str+='<form name="submitAssemblySessionCanScoreByEdit" id="submitApplicationId"  method="post">';
	str+='<table class="table table-bordered ">';
		str+='<thead>';
			str+='<th style="background-color:#D3D3D3;">Party</th>';
			str+='<th style="background-color:#D3D3D3;">No of Member Participated</th>';
			//str+='<th>';
			//str+='<ul class="list-inline">';
				for(var k in result[0].partyList)
				{
					str+='<th style="background-color:#D3D3D3;">'+result[0].partyList[k].aspect+'</th>';
				}
				//str+='</ul>';
			//str+='</th>';
			str+='<th style="background-color:#D3D3D3;">total</th>';
			str+='<th style="background-color:#D3D3D3;">Edit</th>'; 
			str+='<th style="background-color:#D3D3D3;">Delete</th>'; 
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
			str+='<td>'+result[i].partyName+'</td>';
			str+='<td>'+result[i].count+'&nbsp;&nbsp;<span class="expand-Member-icon" attr_id="'+i+'"><i class="glyphicon glyphicon-plus"></i></span></td>';
			str+='<td>'+result[i].avgSubCount.toFixed(1)+'</td>';
			str+='<td>'+result[i].avgPresCount.toFixed(1)+'</td>';
			str+='<td>'+result[i].avgCunterAttCount.toFixed(1)+'</td>';
			str+='<td>'+result[i].avgCunterAttCount.toFixed(1)+'</td>';
			total=result[i].avgSubCount+result[i].avgPresCount+result[i].avgCunterAttCount+result[i].avgCunterAttCount;
			str+='<td>'+total.toFixed(1)+'</td>';
			str+='<td><span class="glyphicon glyphicon-edit"></span></td>';
			str+='<td><span class="glyphicon glyphicon-trash"></span></td>';
			//str+='<button type="button"  class="btn btn-custom btn-success" onClick="saveCandDetails();">Edit</button>';
			str+='</tr>';
			
			for(var j in result[i].candidateList)
			{
				str+='<tr id="expandViewId" style="display:none;" class="expandViewModal'+i+'">';
					str+='<td ></td>';
					str+='<td >'+result[i].candidateList[j].name+'</td>';
						for(var k in result[i].candidateList[j].partyList){
							str+='<td>'+result[i].candidateList[j].partyList[k].score+'</td>';
						}
					str+='<td>'+result[i].candidateList[j].total+'</td>';
					str+='<td><span class="glyphicon glyphicon-edit"></span></td>';
					str+='<td><span class="glyphicon glyphicon-trash"></span></td>';
				str+='</tr>';
			}
			
		}
		str+='</tbody>';
		str+='</table>';
		str+='</form>';
	$("#memberDetailsId").html(str);
}

	var appendId=0;
	$(document).on("click",".addObserversCls",function(){
		appendId=appendId+1;
		var str='';
		str+='<div class="col-md-3 col-xs-12 col-sm-6">';
		str+='<label>Observer - '+appendId+'</label>';
			str+='<select class="chosen-select">';
				str+='<option value="1">2014-2019</option>';
				str+='<option value="1">2014-2019</option>';
				str+='<option value="1">2014-2019</option>';
			str+='</select>';
			str+='</div>';
			$("#observerNamesId").append(str);
			$(".chosen-select").chosen({width:'100%'});
			
		
	});
	var toatlUpdatedCloneCount =0;
	var mainCloneCount =0;	
	var updatedCloneCount =0;  
	var totalIndividualCloneCount =1;  
	
	//add individual for default append
	$(document).on("click","#addMemberDetailsId",function(){
		updatedCloneCount = updatedCloneCount +1;
		var c = $("#updateAppendHtml").clone(true);
		c.attr({
			'id': 'updateAppendHtml'+updatedCloneCount
		});
		
		//getCandidates('memberNameId'+updatedCloneCount,'partyId'+mainCloneCount+''+toatlUpdatedCloneCount);
		//getCandidates('memberNameId'+updatedCloneCount,'partyId'+mainCloneCount+''+toatlUpdatedCloneCount);
		
		c.css("display","block");
		c.find(".memberNameCls").attr("id","memberNameId"+updatedCloneCount);
		c.find(".memberNameCls").attr("attr_count",updatedCloneCount);
		c.find(".memberNameCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].memberId');
		
		
		c.find(".subjectCls").attr("id","subjectId"+updatedCloneCount);
		c.find(".subjectCls").attr("attr_count",updatedCloneCount);
		c.find(".subjectCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[0].score');
		
		c.find(".presentationCls").attr("id","presentationId"+updatedCloneCount);
		c.find(".presentationCls").attr("attr_count",updatedCloneCount);
		c.find(".presentationCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[1].score');
		
			
		c.find(".counterAttackCls").attr("id","counterAttackId"+updatedCloneCount);
		c.find(".counterAttackCls").attr("attr_count",updatedCloneCount);
		c.find(".counterAttackCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[2].score');
		
		c.find(".bodyLanguageCls").attr("id","bodyLanguageId"+updatedCloneCount);
		c.find(".bodyLanguageCls").attr("attr_count",updatedCloneCount);
		c.find(".bodyLanguageCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[3].score');
		
		//c.find(".summaryCls").attr("id","summaryId"+updatedCloneCount);
		//c.find(".summaryCls").attr("attr_count",updatedCloneCount);
		
		
		c.find(".updatingRemoveBtnCls").attr("attr_div_id","updateAppendHtml"+updatedCloneCount);
		$("#updatingClonedElements").append(c);
		
    
		 $("#memberNameId"+updatedCloneCount).chosen();
		 $("#memberNameId"+updatedCloneCount).trigger("chosen:updated");
		
	});
	
	
	
	//total append block
	$(document).on("click","#addTotalOneMoreBlockId",function(){
		updatedCloneCount = updatedCloneCount +1;
		mainCloneCount = mainCloneCount +1;
		var generatedId = mainCloneCount+''+toatlUpdatedCloneCount;
		var c = $("#totalUpdateAppendHtml").clone(true);
		c.attr({
			'id': 'totalUpdateAppendHtml'+generatedId
		});
		c.css("display","block");
		c.find(".partyCls").attr("id","partyId"+generatedId)
		c.find(".partyCls").attr("attr_count",generatedId);
		getPatries("partyId"+generatedId);
		c.find(".partyCls").attr("onchange","getCandidates('memberNameCls','partyCls');");
		
		c.find(".memberNameCls").attr("id","memberNameId"+generatedId)
		c.find(".memberNameCls").attr("attr_count",generatedId)
		c.find(".memberNameCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].memberId');
		
		getCandidates('memberNameId'+generatedId,"");
		c.find(".subjectCls").attr("id","subjectId"+generatedId);
		c.find(".subjectCls").attr("attr_count",generatedId);
		c.find(".subjectCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[0].score');
		
		c.find(".presentationCls").attr("id","presentationId"+generatedId)
		c.find(".presentationCls").attr("attr_count",generatedId)
		c.find(".presentationCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[1].score');
			
		c.find(".counterAttackCls").attr("id","counterAttackId"+generatedId)
		c.find(".counterAttackCls").attr("attr_count",generatedId)
		c.find(".counterAttackCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[2].score');
		
		c.find(".bodyLanguageCls").attr("id","bodyLanguageId"+generatedId)
		c.find(".bodyLanguageCls").attr("attr_count",generatedId)
		c.find(".bodyLanguageCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[3].score');
		
		//c.find(".summaryCls").attr("id","summaryId"+generatedId)
		//c.find(".summaryCls").attr("attr_count",generatedId)
		
		c.find(".totalUpdatingRemoveBtnCls").attr("attr_div_id","totalUpdateAppendHtml"+generatedId);
		$("#totalUpdatingClonedElements").append(c);
    
		 $("#memberNameId"+generatedId).chosen();
		 $("#memberNameId"+generatedId).trigger("chosen:updated");
		
		
		$("#partyId"+generatedId).chosen();
		$("#partyId"+generatedId).trigger("chosen:updated");
		
		
	});
	
	//total block for individual append
	$(document).on("click","#totalAddMemberDetailscls",function(){
		
		totalIndividualCloneCount = totalIndividualCloneCount+1;
		var generatedId = mainCloneCount+''+toatlUpdatedCloneCount+''+totalIndividualCloneCount;
		var c = $("#updateAppendHtml").clone(true);
		c.attr({
			'id': 'updateAppendHtml'+generatedId
		});
		c.css("display","block");
		
		c.find(".memberNameCls").attr("id","memberNameId"+generatedId)
		c.find(".memberNameCls").attr("attr_count",generatedId)
		c.find(".memberNameCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].memberId');
		//getCandidates('memberNameId'+generatedId,'partyId'+mainCloneCount+''+toatlUpdatedCloneCount);
		
		c.find(".subjectCls").attr("id","subjectId"+updatedCloneCount);
		c.find(".subjectCls").attr("attr_count",updatedCloneCount);
		c.find(".subjectCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[0].score');
		
		c.find(".presentationCls").attr("id","presentationId"+generatedId)
		c.find(".presentationCls").attr("attr_count",generatedId)
		c.find(".presentationCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[1].score');
		
		c.find(".counterAttackCls").attr("id","counterAttackId"+generatedId)
		c.find(".counterAttackCls").attr("attr_count",generatedId);
		c.find(".counterAttackCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[2].score');
		
		c.find(".bodyLanguageCls").attr("id","bodyLanguageId"+generatedId)
		c.find(".bodyLanguageCls").attr("attr_count",generatedId);
		c.find(".bodyLanguageCls").attr("name",'assemblySessionReportVO.membersList['+updatedCloneCount+'].scalesList[3].score');
		
		//c.find(".summaryCls").attr("id","summaryId"+generatedId)
		//c.find(".summaryCls").attr("attr_count",generatedId)
		
		c.find(".updatingRemoveBtnCls").attr("attr_div_id","updateAppendHtml"+generatedId);
		$("#totalIndividualClonedElements").append(c);
    
		 $("#memberNameId"+generatedId).chosen();
		$("#memberNameId"+generatedId).trigger("chosen:updated");
		
		
	});
	
	$(document).on("click",".updatingRemoveBtnCls",function(){
		var divId = $(this).attr("attr_div_id");
		$("#"+divId).remove();
	});
	$(document).on("click",".totalUpdatingRemoveBtnCls",function(){
		var divId = $(this).attr("attr_div_id");
		$("#"+divId).remove();
	});
	
	function savingApplication(){
		$("#adminHouseSessionDayId").val(154);
		//alert($("#adminHouseSessionDayId").val());
		var uploadHandler = {
				upload: function(o) {
					$("#savingAjaxImg").css("display","none");
					uploadResult = o.responseText;
					
					showSbmitStatus(uploadResult);
				}
			};
			YAHOO.util.Connect.setForm('submitAssemblySessionCanScore',true);
			YAHOO.util.Connect.asyncRequest('POST','submitAssemblySessionCanScoreDetailsAction.action',uploadHandler);
		
	}
	
	function showSbmitStatus(uploadResult){
		var stringext = uploadResult.substr(6,7);
		
		if(stringext == "SUCCESS"){
			
		}
	}
	
	function clearFields(){
		$("#updateAppendHtml").empty();
	}

function getDatesForSaving(){
	
	var electionYear = $("#electionYearId").val();
	var sessionYear = $("#sessionYearId").val();
	var sessionId = $("#assemblySessionId").val();
	
	var jObj = {
		elctionYearId : electionYear,
		sessionYear : sessionYear,//"2017",
		sessionId : sessionId
	};		
	$.ajax({
		  type:'POST',
		  url: 'getDatesForSavingAction.action',
		 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null){
			/*for(var i in result){
				var fYear = result[i].date.split('-')[0];
				var fMonth = result[i].date.split('-')[1];
				var fDate = result[i].date.split('-')[2];
				//var tYear = result[0].partyName.split('-')[0];
				//var tMonth = result[0].partyName.split('-')[1];
				//var tDate = result[0].partyName.split('-')[2].substring(0,2);
				
				$('#UpdateStartdateRange').data('daterangepicker').setStartDate(fDate+'/'+fMonth+'/'+fYear);
				//$('#dateRange').data('daterangepicker').setEndDate(tDate+'/'+tMonth+'/'+tYear);
			}*/
		}
		
	});
}

function saveCandDetails(){
	var uploadHandler = {
		 upload: function(result) {
			//console.log(result);
			uploadResult = result.responseText; 
			var stringext = uploadResult.substr(6,7);
			//$("#errorModalId").modal("hide");
		}
	};
		YAHOO.util.Connect.setForm('submitAssemblySessionCanScoreByEdit',true);
		YAHOO.util.Connect.asyncRequest('POST','updateMemberDetailsAction.action',uploadHandler);
}