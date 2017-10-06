// please do not try to edit these options which may cause the entire page to stop working.	
var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
onLoadCalls();
function onLoadCalls()
{
	getAllNominatedStatusListLevelWiseDataDashBoard(); //1st block Call
	getAreaWiseDashboardCandidatesCountView();//second block
	getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails();
	getLocationWiseNominatedPostAnalysisDetails("ageGroup");
	getLocationWiseNominatedPostAnalysisDetails("casteGroup");
	getLocationWiseNominatedPostAnalysisDetails("mandal");
}
$(document).on("click",".casteGroupId",function(){
	var type = $(this).attr("attr_type");
	if(type == "casteGroup")
	{
		$("#subCasteBlockId").html('');
	}else{
		$("#casteGroupBlockId").html('');
	}
	getLocationWiseNominatedPostAnalysisDetails(type);
});	
function getAllNominatedStatusListLevelWiseDataDashBoard(){
	var jsObj={
		boardLevelId:2,  //state level 1 //parliament -4 or constituenct -4 // mandal Or muni -5 // panchayat/ward -7 //
		levelValues:[1],
		levelId:2
	}
	$.ajax({   
		type:'GET',
		url:'getAllNominatedStatusListLevelWiseDataDashBoardAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);
	});
}


function getAreaWiseDashboardCandidatesCountView(){
	$("#LocationWiseLevelBlockId").html(spinner);
	var jsObj={
		levelValues:["282"],
		levelId:4,
		statusIds:[3,4]
	}
	$.ajax({   
		type:'GET',
		url:'getAreaWiseDashboardCandidatesCountViewAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);
		if(result != null)
		{
			return buildData(result);
		}else{
			$("#LocationWiseLevelBlockId").html("NO DATA AVAILABLE");
		}
	});
	function buildData(result)
	{
		var str='';
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered" id="LocationWiseLevelTableId">';
				str+='<thead>';
					str+='<th>Location / Members</th>';
					str+='<th>Total</th>';
				for(var i in result.subList[0].subList)
				{
					str+='<th>'+result.subList[0].subList[i].name+'</th>';
				}
				str+='</thead>';
				for(var i in result.subList)
				{
					str+='<tr>';
						if(result.subList[i].name.length > 0)
						{
							str+='<td>'+result.subList[i].name+'</td>';
						}else{
							str+='<td>Others</td>';
						}
						
						str+='<td>'+result.subList[i].totalCount+'</td>';
						for(var j in result.subList[i].subList)
						{
							str+='<td>'+result.subList[i].subList[j].totalCount+'</td>';
						}
					str+='</tr>';
				}
			str+='</table>';
		str+='</div>';
		$("#LocationWiseLevelBlockId").html(str);
		$("#LocationWiseLevelTableId").dataTable({
			"iDisplayLength": 5,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			//"dom": 'lfBrtip',
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row m_top30'<'col-sm-5'i><'col-sm-7'p>>",
			buttons: [
				{
					extend:    'csvHtml5',
					text:      '<i class="fa fa-file-text-o tooltipCls"></i>',
					titleAttr: 'CSV',
					title:	   "LocationWiseLevelTableId",
					filename:  "LocationWiseLevelTableId"+moment().format("DD/MMMM/YYYY  HH:MM"),
				},
				{
					extend:    'pdfHtml5',
					text:      '<i class="fa fa-file-pdf-o tooltipCls"></i>',
					titleAttr: 'PDF',
					title:	   "LocationWiseLevelTableId",
					filename:  'LocationWiseLevelTableId'+moment().format("DD/MMMM/YYYY  HH:MM"),
					orientation: "landscape",
					pageSize:'A4',
					customize: function (doc) {
						doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
					}
				}
			]
		});
	}
}


function getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails(){
	var jobj = {
		locationValues : [218],
		statusIds :[3,4],
		levelId :4,
		type : "ageRange"//or(casteCategory)
	}	
	$.ajax({
		type : "POST",
		url  : "getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetailsAction.action",
		data : {task:JSON.stringify(jobj)}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			return buildData(result);
		}else{
			$("#LocationWiseLevelBlockId").html("NO DATA AVAILABLE");
		}
	});
	function buildData(result)
	{
		/* <table class="table table-bordered">
			<thead>
				<th></th>
			</thead>
		</table> */
	}
} 


function getLocationWiseNominatedPostAnalysisDetails(type){
	$("#"+type+"BlockId").html(spinner);
	var jsObj={
		boardLevelId:4,
		levelValues:["282"],
		levelId:4,
		dataType:type,
		statusIds:[3,4]
	}
	$.ajax({   
		type:'GET',
		url:'getLocationWiseNominatedPostAnalysisDetailsAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);
		if(result != null && result.length > 0)
		{
			return buildTable(result);
		}else{
			$("#"+type+"BlockId").html("NO DATA AVAILABLE");
		}
		
	});
	function buildTable(result)
	{
		var table='';
		table+='<table class="table table-bordered" id="dataTable'+type+'">';
			table+='<thead>';
				table+='<tr>';
					if(type == "casteGroup" || type == "subCaste")
					{
						table+='<th rowspan="2" class="f-12">Caste</th>';
					}else if(type == "ageGroup")
					{
						table+='<th rowspan="2" class="f-12">BETWEEN AGE</th>';
					}else if(type == "mandal")
					{
						table+='<th rowspan="2" class="f-12">LOCATION/ MANDALS</th>';
					}
					table+='<th colspan="3" class="f-12">TOTAL MEMBERS</th>';
					
					if(type == "mandal" )
					{
						for(var i in result[1].subList)
						{
							if(result[1].subList[i].name.length > 13)
							{
								table+='<th rowspan="2" class="f-12"><span class="tooltipCls" title="'+result[1].subList[i].name+'">'+result[1].subList[i].name.substr(0,13)+'..</span></th>';
							}else{
								table+='<th rowspan="2" class="f-12">'+result[1].subList[i].name+'</th>';
							}
							
						}
					}else{
						for(var i in result[0].subList)
						{
							if(result[0].subList[i].name.length > 13)
							{
								table+='<th rowspan="2" class="f-12"><span class="tooltipCls" title="'+result[0].subList[i].name+'">'+result[1].subList[i].name.substr(0,13)+'..</span></th>';
							}else{
								table+='<th rowspan="2" class="f-12">'+result[0].subList[i].name+'</th>';
							}
						}
					}
				table+='</tr>';
				table+='<tr>';
					table+='<th style="background-color:#F4F4F4"></th>';					
					table+='<th style="background-color:#F4F8FF"></th>';					
					table+='<th style="background-color:#FFF3F8"></th>';					
				table+='</tr>';
			table+='</thead>';
			table+='<tbody>';
				for(var i in result)
				{
					if(type == "mandal" )
					{
						if(i != 0)
						{
							table+='<tr>';
								table+='<td>'+result[i].name+'</td>';
								table+='<td style="background-color:#F4F4F4">'+result[i].finalOrGOCnt+'</td>';
								table+='<td style="background-color:#F4F8FF">'+result[i].maleCount+'</td>';
								table+='<td style="background-color:#FFF3F8">'+result[i].femaleCount+'</td>';
								for(var j in result[i].subList)
								{
									table+='<td>'+result[i].subList[j].totalCount+'</td>';
								}
							table+='</tr>';
						}
						
					}else{
						table+='<tr>';
							table+='<td>'+result[i].name+'</td>';
							table+='<td style="background-color:#F4F4F4">'+result[i].finalOrGOCnt+'</td>';
							table+='<td style="background-color:#F4F8FF">'+result[i].maleCount+'</td>';
							table+='<td style="background-color:#FFF3F8">'+result[i].femaleCount+'</td>';
							for(var j in result[i].subList)
							{
								table+='<td>'+result[i].subList[j].totalCount+'</td>';
							}
						table+='</tr>';
					}
					
				}
			table+='</tbody>';
		table+='</table>';
		$("#"+type+"BlockId").html(table);
		$("#dataTable"+type).dataTable({
			"iDisplayLength": 20,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			//"dom": 'lfBrtip',
			"scrollX":        true,
			"scrollCollapse": true,
			"fixedColumns":   {
				"leftColumns": 1,
			},
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row m_top30'<'col-sm-5'i><'col-sm-7'p>>",
			buttons: [
				{
					extend:    'csvHtml5',
					text:      '<i class="fa fa-file-text-o tooltipCls"></i>',
					titleAttr: 'CSV',
					title:	   type,
					filename:  type+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				},
				{
					extend:    'pdfHtml5',
					text:      '<i class="fa fa-file-pdf-o tooltipCls"></i>',
					titleAttr: 'PDF',
					title:	   type,
					filename:  type+''+moment().format("DD/MMMM/YYYY  HH:MM"),
					orientation: "landscape",
					pageSize:'A4',
					customize: function (doc) {
						doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
					}
				}
			]
		});
		$(".tooltipCls").tooltip();
	}
}
