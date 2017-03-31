function globalOnLoadCalls()
{
	getElectionYears();
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
	
}
function getElectionYears(){
	
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
		$("#electionYear").html(str);
		//$(".chosen-select").chosen({width:'100%'});
	});
}


function getSessionYears(){
	
	var electionYear = $("#electionYear").val();
	
	var jObj = {
		elctionYearId : electionYear
	};		
	$.ajax({
		  type:'POST',
		  url: 'getSessionYearsAction.action',
		 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
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
		$("#sessionYear").html(str);
		//$(".chosen-select").chosen({width:'100%'});
	});
}


function getAllSessions(){
	var electionYear = $("#electionYear").val();
	var sessionYear = $("#sessionYear").val();
	
	var jObj = {
		elctionYearId : electionYear,
		sessionYear : sessionYear //"2017"
	};		
	$.ajax({
		type:'POST',
		url: 'getAllSessionListAction.action',
		data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
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
		$("#assemblySession").html(str);
		//$(".chosen-select").chosen({width:'100%'});
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
								str+='<td atr_session_day_id="'+result[i].candidateList[j].adminHouseSessionDayId+'" class="sessionCls">'+result[i].candidateList[j].count+'</td>';
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
	
	var sessionDayId = $(this).attr("atr_session_day_id");
	
	var jObj = {
				 adminHouseSessionDayId : sessionDayId
			};		
			$.ajax({
				  type:'POST',
				  url: 'getDayWiseDetailsAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
			 });
});
/* function getDayWiseDetails(){
	
	
} */
//getPatries();
function getPatries(){
	
	var jObj = {
			};		
			$.ajax({
				  type:'POST',
				  url: 'getPartiesAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				 if(result != null && result.length >0)
					{
						for(var i in result)
						$('#').append('<option value="'+result[i].partyId+'">'+result[i].PartyName+'</option>');
					}
			 });
}
//getCandidates();
function getCandidates(){
	var jObj = {
				partyId : 872
			};		
			$.ajax({
				  type:'POST',
				  url: 'getcandidatesForPartyAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				 if(result != null && result.length >0)
					{
						for(var i in result)
						$('#').append('<option value="'+result[i].adminHouseMemberId+'">'+result[i].Name+'</option>');
					}
			 });
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
		c.css("display","block");
		c.find(".memberNameCls").attr("id","memberNameId"+updatedCloneCount)
		c.find(".memberNameCls").attr("attr_count",updatedCloneCount)
		
		c.find(".presentationCls").attr("id","presentationId"+updatedCloneCount)
		c.find(".presentationCls").attr("attr_count",updatedCloneCount)
			
		c.find(".counterAttackCls").attr("id","counterAttackId"+updatedCloneCount)
		c.find(".counterAttackCls").attr("attr_count",updatedCloneCount)
		
		c.find(".bodyLanguageCls").attr("id","bodyLanguageId"+updatedCloneCount)
		c.find(".bodyLanguageCls").attr("attr_count",updatedCloneCount)
		
		c.find(".summaryCls").attr("id","summaryId"+updatedCloneCount)
		c.find(".summaryCls").attr("attr_count",updatedCloneCount)
		
		c.find(".updatingRemoveBtnCls").attr("attr_div_id","updateAppendHtml"+updatedCloneCount);
		$("#updatingClonedElements").append(c);
		
    
		// $("#memberNameId"+updatedCloneCount).chosen();
		// $("#memberNameId"+updatedCloneCount).trigger("chosen:updated");
		// $(".chosen-select").chosen({width:'100%'});
		
	});
	
	
	
	//total append block
	$(document).on("click","#addTotalOneMoreBlockId",function(){
		mainCloneCount = mainCloneCount +1;
		var generatedId = mainCloneCount+''+toatlUpdatedCloneCount;
		var c = $("#totalUpdateAppendHtml").clone(true);
		c.attr({
			'id': 'totalUpdateAppendHtml'+generatedId
		});
		c.css("display","block");
		c.find(".partyCls").attr("id","partyId"+generatedId)
		c.find(".partyCls").attr("attr_count",generatedId)
		
		c.find(".memberNameCls").attr("id","memberNameId"+generatedId)
		c.find(".memberNameCls").attr("attr_count",generatedId)
		c.find("#memberNameId"+generatedId).attr("onchange","getOrganizationsForState();");
		
		c.find(".presentationCls").attr("id","presentationId"+generatedId)
		c.find(".presentationCls").attr("attr_count",generatedId)
			
		c.find(".counterAttackCls").attr("id","counterAttackId"+generatedId)
		c.find(".counterAttackCls").attr("attr_count",generatedId)
		
		c.find(".bodyLanguageCls").attr("id","bodyLanguageId"+generatedId)
		c.find(".bodyLanguageCls").attr("attr_count",generatedId)
		
		c.find(".summaryCls").attr("id","summaryId"+generatedId)
		c.find(".summaryCls").attr("attr_count",generatedId)
		
		c.find(".totalUpdatingRemoveBtnCls").attr("attr_div_id","totalUpdateAppendHtml"+generatedId);
		$("#totalUpdatingClonedElements").append(c);
    
		/* $("#memberNameId"+generatedId).chosen();
		$("#memberNameId"+generatedId).trigger("chosen:updated");
		$(".chosen-select").chosen({width:'100%'}); */
		
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
		
		c.find(".presentationCls").attr("id","presentationId"+generatedId)
		c.find(".presentationCls").attr("attr_count",generatedId)
			
		c.find(".counterAttackCls").attr("id","counterAttackId"+generatedId)
		c.find(".counterAttackCls").attr("attr_count",generatedId)
		
		c.find(".bodyLanguageCls").attr("id","bodyLanguageId"+generatedId)
		c.find(".bodyLanguageCls").attr("attr_count",generatedId)
		
		c.find(".summaryCls").attr("id","summaryId"+generatedId)
		c.find(".summaryCls").attr("attr_count",generatedId)
		
		c.find(".updatingRemoveBtnCls").attr("attr_div_id","updateAppendHtml"+generatedId);
		$("#totalIndividualClonedElements").append(c);
    
		/* $("#memberNameId"+generatedId).chosen();
		$("#memberNameId"+generatedId).trigger("chosen:updated");
		$(".chosen-select").chosen({width:'100%'}); */
		
	});
	
	$(document).on("click",".updatingRemoveBtnCls",function(){
		var divId = $(this).attr("attr_div_id");
		$("#"+divId).remove();
	});
	$(document).on("click",".totalUpdatingRemoveBtnCls",function(){
		var divId = $(this).attr("attr_div_id");
		$("#"+divId).remove();
	});
	
	function getOrganizationsForState(){
		alert(4)
	}
	