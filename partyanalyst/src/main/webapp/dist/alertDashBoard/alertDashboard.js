
$(document).ready(function(){
	getLocationLevelAlertCount();
});
$(document).change('#dateRangePickerId',function(){
	getLocationLevelAlertCount();
})
function getLocationLevelAlertCount()
{
	
	    var fromDate='';
		var toDate='';
		var dateStr = $("#dateRangePickerId").val(); 
		
		if(dateStr !=null && dateStr.length>0){
			fromDate = dateStr.split("-")[0];
			toDate = dateStr.split("-")[1];
		}
		var jsObj = {
			fromDate:'',
			toDate :''
			
		          }
		
				$.ajax({
					  type:'GET',
					  url: 'getLocationLevelWiseAlertsAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					if(result != null && result.length>0){
						buildLocationLevelAlert(result,jsObj);
					}
				});
}
function buildLocationLevelAlert(result,jsObj){
	
	var str='';
	str+='<table class="table table-bordered">';
	str+='<thead>';
	str+='<th>Level</th>';
	str+='<th>Total</th>';
	for(var i in result[0].locationsList)
	str+='<td>'+result[0].locationsList[i].name+'</td>';
	str+='</thead>';
	str+='<tbody>';
	str+='<tr>';
	for(var i in result){
		str+='<td>'+result[i].name+'</td>';
		str+='<td><a class="locationLevelCls" style="cursor:pointer;" attr-levelId="'+result[i].id+'" attr-statusId="0" attr-fromDate="'+jsObj.fromDate+'" attr-toDate="'+jsObj.toDate+'">'+result[i].count+'</a></td>';
		var returnList=result[i].locationsList;
		for(var j in returnList){
			str+='<td><a class="locationLevelCls" style="cursor:pointer;" attr-levelId="'+result[i].id+'" attr-statusId="'+returnList[j].id+'" attr-fromDate="'+jsObj.fromDate+'" attr-toDate="'+jsObj.toDate+'">'+returnList[j].count+'</a></td>';
			
		}
		str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>'
	$("#locationLevelId").html(str);
}
$(document).on("click",".locationLevelCls",function(){
	var levelId = $(this).attr("attr-levelId");
	var statusId=$(this).attr("attr-statusId");
	var fromDate = $(this).attr("attr-fromDate");
	var toDate=$(this).attr("attr-toDate");
	getLocationLevelAlertData(levelId,statusId,fromDate,toDate);
});


function getLocationLevelAlertData(levelId,statusId,fromDate,toDate)
{
		var jsObj =
		     {
			levelId  : levelId,
			statusId :statusId,
			fromDate :"",
			toDate   :"",
			task : ""
		      }
			$.ajax({
					  type:'GET',
					  url: 'getLocationLevelAlertDataAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					buildAlertData(result);
				});
}
function buildAlertData(result)
{
	var str='';
	str+='<table class="table table-bordered">';
	str+='<thead>';
	str+='<th>S.NO</th>';
	str+='<th>Desc</th>';
	str+='<th>Alert Type</th>';
	str+='<th>Severity</th>';
	str+='<th>No. Of Candidate</th>';
	str+='<th>User Type</th>';
	str+='</thead>';
	var j=0;
	for(var i in result)
	{
		j++;
	str+='<tr>';	
	str+='<td>'+j+'</td>';
	str+='<td><a href="#" class="alertModel" style="cursor:pointer;">'+result[i].desc+'</a></td>';
	str+='<td>'+result[i].alertType+'</td>';
	str+='<td>'+result[i].severity+'</td>';
	str+='<td>'+result[i].count+'</td>';
	str+='<td>'+result[i].userType+'</td>';
	str+='</tr>';	
	}
	$("#locationLevelDataId").html(str);
}

$(document).on("click",".alertModel",function(){
	$("#ModalShow").modal('show');
});

