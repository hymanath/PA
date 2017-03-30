function globalOnLoadCalls()
{
	getElectionYears();
	getSessionYears();
	getAllSessions();
	getDates();
	getSessionDetails();
	getDayWiseDetails();
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
			str+='<select class="chosen-select">';
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].date+'</option>';
			}
			str+='</select>';
		}
		$("#electionYear").html(str);
		$(".chosen-select").chosen({width:'100%'});
	});
}


function getSessionYears(){
	var jObj = {
		elctionYearId : 3
	};		
	$.ajax({
		  type:'POST',
		  url: 'getSessionYearsAction.action',
		 data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		var str='';
		if(result != null && result.length >0)
		{
			str+='<select class="chosen-select">';
			for(var i in result)
			{
				str+='<option value="'+result[i].name+'">'+result[i].partyName+'</option>';
			}
			str+='</select>';
		}
		$("#sessionYear").html(str);
		$(".chosen-select").chosen({width:'100%'});
	});
}


function getAllSessions(){
	var jObj = {
		elctionYearId : 3,
		sessionYear : "2017"
	};		
	$.ajax({
		type:'POST',
		url: 'getAllSessionListAction.action',
		data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		var str='';
		if(result != null && result.length >0)
		{
			str+='<select class="chosen-select">';
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
			str+='</select>';
		}
		$("#assemblySession").html(str);
		$(".chosen-select").chosen({width:'100%'});
	});
}


function getDates(){
	var jObj = {
		elctionYearId : 3,
		sessionYear : "2017",
		sessionId : 21
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
	//var termId = $("#elctonId").val();
	//var sessionyear = $('#sessionYearId').val();
	//var sessionId = $('#sessionId').val();
	//var dateStr = $('#dateId').val();
	//var dateArr = dateStr.split("-");
	//if(dateArr != null && dateArr.length > 0){
		//startDate = dateArr[0];
		//endDate =  dateArr[1]; 
	//}
	var jObj = {
		elctionYearId : 3,
		sessionYear : "2017",
		sessionId : 21,
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
										str+='<li>'+result[i].candidateList[j].partyList[k].partyName+'</li>';
									}
									str+='</ul>';
								str+='</td>';
								str+='<td>'+result[i].candidateList[j].count+'</td>';
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

function getDayWiseDetails(){
	var jObj = {
				 adminHouseSessionDayId : 145
			};		
			$.ajax({
				  type:'POST',
				  url: 'getDayWiseDetailsAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
			 });
}
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