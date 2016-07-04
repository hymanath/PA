
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
			fromDate:fromDate,
			toDate :toDate
			
		          }
		
				$.ajax({
					  type:'GET',
					  url: 'getLocationLevelWiseAlertsAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					if(result != null && result.length>0){
						buildLocationLevelAlert(result);
					}
				});
}
function buildLocationLevelAlert(result){
	
	var str='';
	str+='<table class="table table-bordered">';
	str+='<thead>';
	str+='<th>Level</th>';
	str+='<th>Total</th>';
	for(var i in result[0].locationsList)
	str+='<th>'+result[0].locationsList[i].name+'</th>';
	str+='</thead>';
	str+='<tbody>';
	str+='<tr>';
	for(var i in result){
		str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].count+'</td>';
		var returnList=result[i].locationsList;
		for(var j in returnList){
			str+='<td>'+returnList[j].count+'</td>';
			
		}
		str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>'
	$("#locationLevelId").html(str);
}


