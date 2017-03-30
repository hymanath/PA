alert(1)
//getElectionYears();
function getElectionYears(){
alert(5)
	var jObj = {
			};		
			$.ajax({
				  type:'POST',
				  url: 'getAllElectionYearsAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				 if(result != null && result.length >0)
					{
						for(var i in result)
						$('#elctonId').append('<option value="'+result[i].id+'">'+result[i].date+'</option>');
					}
			 });
}

function getSessionYears(){
	$('#sessionYearId').find('option').remove();
	$('#sessionYearId').append('<option value="0">All</option>');
	var termId = $("#elctonId").val();
	var jObj = {
				elctionYearId : termId
			};		
			$.ajax({
				  type:'POST',
				  url: 'getSessionYearsAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				 if(result != null && result.length >0)
					{
						for(var i in result)
						$('#sessionYearId').append('<option value="'+result[i].name+'">'+result[i].partyName+'</option>');
					}
			 });
}

function getAllSessions(){
	
	var termId = $("#elctonId").val();
	var sessionyear = $('#sessionYearId').val();
	var jObj = {
				 elctionYearId : termId,
				 sessionYear : sessionyear
			};		
			$.ajax({
				  type:'POST',
				  url: 'getAllSessionListAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				 if(result != null && result.length >0)
					{
						for(var i in result)
						$('#sessionId').append('<option value="'+result[i].name+'">'+result[i].partyName+'</option>');
					}
			 });
}

function getDates(){
	var termId = $("#elctonId").val();
	var sessionyear = $('#sessionYearId').val();
	var sessionId = $('#sessionId').val();
	var jObj = {
				 elctionYearId : termId,
				 sessionYear : sessionyear,
				 sessionId : sessionId
			};		
			$.ajax({
				  type:'POST',
				  url: 'getAllSessionListAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
				 if(result != null && result.length >0)
					{
						for(var i in result)
						$('#dateId').append('<option value="'+result[i].name+'">'+result[i].partyName+'</option>');
					}
			 });
}
getSessionDetails();
function getSessionDetails(){
	alert(2)
	
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
				 elctionYearId : 1,
				 sessionYear : "2016",
				 sessionId : 7,
				 startDate : "23-02-2016",
				 endDate : "11-05-2016"
			};		
			$.ajax({
				  type:'POST',
				  url: 'getSessionDetailsAction.action',
				 data : {task:JSON.stringify(jObj)} ,
			 }).done(function(result){
			 });
}