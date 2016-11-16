function getFieldMonitoringReport(){
	$("#fieldUserDetailsImgId").show();
	$("#fieldUserDetailsId").html("");
	
	var jsObj = { 
	}
	$.ajax({
		type : 'GET',
		url : 'getFieldMonitoringUserWiseDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null && result.length > 0){
			buildFieldMonitoringReport(result);
		}
		else{
			$("#fieldUserDetailsImgId").hide();
			$("#fieldUserDetailsId").html('<h4>NO DATA AVAILABLE...</h4>');
		}
	});
}

function buildFieldMonitoringReport(result){
	var str = '';
	
	str+='<div class="table-responsive">';
		str+='<span class="btn btn-info excelId form-inline pull-right btn-sm btn-xs" style="float:left;margin-top: 10px" onclick="exportToExcel(\'tabWiseDtlsId\')"><b> Export To Excel </b></span>';
		str+='<table class="table table-bordered b_1 m_top10" id="tabWiseDtlsId">';
			str+='<thead>';
			str+='<tr>';
				str+='<td rowspan="2">FM USER ID</td>';
				//str+='<th>FM USER CONTACT NUMBER</th>';
				str+='<td rowspan="2">DISTRICT</td>';
				str+='<td rowspan="2">CONSTITUENCY</td>';
				str+='<td rowspan="2">TOTAL USERS</td>';
				str+='<td rowspan="2">REGISTERED</td>';
				str+='<td rowspan="2">STARTED</td>';
				str+='<td rowspan="2">NOT YET STARTED</td>';
				str+='<td rowspan="2">LAST HOUR INACTIVE</td>';
				//str+='<td rowspan="2">ISSUES IN STARTED</td>';
				//str+='<td rowspan="2">ISSUES IN NOT STARTED</td>';
				if(result[0].idnameList != null && result[0].idnameList.length > 0){
					for(var i in result[0].idnameList){
						str+='<td colspan="2" style="text-transform:uppercase;">'+result[0].idnameList[i].name+'</td>'
					}
				}
				if(result[0].idnameList1 != null && result[0].idnameList1.length > 0){
					for(var i in result[0].idnameList1){
						str+='<td rowspan="2" style="text-transform:uppercase;">'+result[0].idnameList1[i].name+'</td>'
					}
				}
				str+='</tr>';
				str+='<tr>';
					if(result[0].idnameList != null && result[0].idnameList.length > 0){
						for(var i in result[0].idnameList){
							str+='<td>STARTED</td>'
							str+='<td>NOT STARTED</td>'
						}
					}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){   
				str+='<tr>';
					str+='<td>'+result[i].name+'</td>';
					//str+='<td> - </td>';
					str+='<td>'+result[i].districtName+'</td>';
					str+='<td>'+result[i].constituencyName+'</td>';
				//	str+='<td>'+result[i].totalDataCollectors+'<span class="glyphicon glyphicon-map-marker mapClass" attr_id="'+result[i].id+'" attr_userName="'+result[i].name+'" attr_constistuencyId="'+result[i].constituencyId+'" title="Click here For User Tracking" style="cursor:pointer;"></span>&nbsp</td>';
					str+='<td>'+result[i].totalDataCollectors+'</td>';
					str+='<td>'+result[i].todayRegCount+'</td>';
					str+='<td>'+result[i].startedUsers+'</td>';
					str+='<td>'+result[i].notYetStartedUsers+'</td>';
					//var lastHourInActive = parseInt(result[i].totalDataCollectors) - parseInt(result[i].lastOneHrActUsers);
					str+='<td>'+result[i].lastHourInActive+'</td>';
					//str+='<td>'+result[i].notYetStartedIssues+'</td>';
					//str+='<td>'+result[i].startedIssues+'</td>';
					if(result[i].idnameList != null && result[i].idnameList.length > 0){
						for(var j in result[i].idnameList){
							str+='<td>'+result[i].idnameList[j].inviteeAttendeeCnt+'</td>';
							str+='<td>'+result[i].idnameList[j].inviteeCount+'</td>';
						}
					}
					if(result[i].idnameList1 != null && result[i].idnameList1.length > 0){
						for(var j in result[i].idnameList1){
							str+='<td>'+result[i].idnameList1[j].attenteeCount+'</td>';
						}
					}
				str+='</tr>';  
			}
		str+='</tbody>';
		str+='</table>';
	str+='</div>';
	
	$("#fieldUserDetailsImgId").hide();
	$("#fieldUserDetailsId").html(str);
	$("#tabWiseDtlsId").dataTable({
		"aaSorting": [[ 0, "asc" ]],
		 "iDisplayLength": 20,
		 "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
	});
}

function exportToExcel(constiTableId)
{
tableToExcel(''+constiTableId+'', 'Constituency Wise Committees');
	
}

var tableToExcel = (function() {
	var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
$(document).on("click",".mapClass",function(){  
	var cadreUserId = $(this).attr("attr_id");
	var userName = $(this).attr("attr_userName");
	var constistuencyId =$(this).attr("attr_constistuencyId");	
	//var urlStr = "tdpUserWiseReportMapAction.action?userId="+cadreUserId+"&username="+userName;
	var urlStr = "tdpUserWiseReportMapAction.action?fieldUserId="+cadreUserId+"&username="+userName+"&constistuencyId="+constistuencyId+"&userId="+"" ;
	var browser2 = window.open(urlStr,"Survey Map","scrollbars=yes,height=650,width=1100,left=150,top=100");
	//getFieldMonitoringMapReportDetails();
});