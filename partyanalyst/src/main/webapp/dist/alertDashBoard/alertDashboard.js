
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

var GlobalAlertData;
function getLocationLevelAlertData(levelId,statusId,fromDate,toDate)
{
    GlobalAlertData = [];
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
			       GlobalAlertData = result;
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
	str+='<td><a  class="alertModel" style="cursor:pointer;" attr-id="'+result[i].id+'">'+result[i].desc+'</a></td>';
	str+='<td>'+result[i].alertType+'</td>';
	str+='<td>'+result[i].severity+'</td>';
	str+='<td>'+result[i].count+'</td>';
	str+='<td>'+result[i].userType+'</td>';
	str+='</tr>';	
	}
	$("#locationLevelDataId").html(str);
}
var GlobalalertId;
$(document).on("click",".alertModel",function(){
	
	$("#ModalShow").modal('show');
	GlobalalertId = $(this).attr("attr-id");
	showPopUpAlertData(GlobalalertId);
	
	
});
function showPopUpAlertData(alertId)
{
	
	for(var i in GlobalAlertData)
	{
		if(alertId == GlobalAlertData[i].id)
		{
			alert(GlobalAlertData[i].regionScope)
			$("#typeId").html(''+GlobalAlertData[i].alertType+'');
			$("#severityId").html(''+GlobalAlertData[i].severity+'');
			$("#createdDate").html(''+GlobalAlertData[i].date+'');
			
			$("#levelId").html(''+GlobalAlertData[i].regionScope+'');
			var location ='';
			if(GlobalAlertData[i].locationVO.localEleBodyName != null && GlobalAlertData[i].locationVO.localEleBodyName.length > 0)
			{
			location +='State:'+GlobalAlertData[i].locationVO.state+' Dist : '+GlobalAlertData[i].locationVO.districtName+' Constituency :'+GlobalAlertData[i].locationVO.constituencyName+' Town : '+GlobalAlertData[i].locationVO.localEleBodyName+' Ward : '+GlobalAlertData[i].locationVO.wardName+'';	
			}
			else{
				location +='State:'+GlobalAlertData[i].locationVO.state+' Dist : '+GlobalAlertData[i].locationVO.districtName+' Constituency :'+GlobalAlertData[i].locationVO.constituencyName+' Mandal : '+GlobalAlertData[i].locationVO.tehsilName+' Panchayat : '+GlobalAlertData[i].locationVO.villageName+'';
			}
			
			$("#LocationId").html(''+location+'');
			$("#statusId").val(''+GlobalAlertData[i].status+'');
			$("#descriptionId").html(''+GlobalAlertData[i].desc+'');
			
		}
	}
	
}
function updateAlertStatus()
{
	//alertId=$("#commentsId").val();
	//alertStatusId=$("#commentsId").val();
	var comments = $("#commentsId").val();
	if(comments.length==0||comments=='')
	{
		  $('#commentsId').html('please give comments');
            return;
	}
		var jsObj =
		     {
		alertId : GlobalalertId,
		alertStatusId :1,
		comments:comments,
			task : ""
		      }
			$.ajax({
					  type:'GET',
					  url: 'UpdateAlertStatusAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					//buildAlertData(result);
				});
				
}
$(document).on("click",".updateAlertStatusCls",function(){
	//$("#ModalShow").modal('show');
	updateAlertStatus();
});


