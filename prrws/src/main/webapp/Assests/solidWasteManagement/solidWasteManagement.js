var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var levelWiseOverviewArr = ['district','constituency','mandal']

onLoadCalls()
function onLoadCalls()
{
	levelWiseOverview()
	getSolidWasteManagementOverAllCounts(3,19);
}
$(document).keydown(function(event){
	if(event.keyCode==123){
		alert("Hoo no! don't try to expose me");
		return false;
	}
	else if(event.ctrlKey && event.shiftKey && event.keyCode==73){        
		alert("Hoo no! don't try to expose me");
		return false;  //Prevent from ctrl+shift+i
	}
});
$(document).on("contextmenu",function(){
	alert("Sorry! We have prevented right click usage");
	return false;
}); 

function levelWiseOverview()
{
	var collapse = '';
	collapse+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
		for(var i in levelWiseOverviewArr)
		{
			collapse+='<div class="panel panel-default panel-black">';
				collapse+='<div class="panel-heading" role="tab" id="heading'+levelWiseOverviewArr[i]+'">';
					if(i ==0)
					{
						collapse+='<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordion" href="#collapse'+levelWiseOverviewArr[i]+'" aria-expanded="true" aria-controls="collapse'+levelWiseOverviewArr[i]+'">';
					}else{
						collapse+='<a role="button" class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapse'+levelWiseOverviewArr[i]+'" aria-expanded="true" aria-controls="collapse'+levelWiseOverviewArr[i]+'">';
					}
					
						collapse+='<h4 class="panel-title text-capital">'+levelWiseOverviewArr[i]+' level overview</h4>';
					collapse+='</a>';
				collapse+='</div>';
				if(i == 0)
				{
					collapse+='<div id="collapse'+levelWiseOverviewArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+levelWiseOverviewArr[i]+'">';
				}else{
					collapse+='<div id="collapse'+levelWiseOverviewArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+levelWiseOverviewArr[i]+'">';
				}
				
					collapse+='<div class="panel-body">';
						collapse+='<div class="row">';
							if(levelWiseOverviewArr[i] == 'constituency' || levelWiseOverviewArr[i] == 'mandal')
							{
								collapse+='<div class="col-sm-3">';
									collapse+='<label>District</label>';
									collapse+='<select class="chosen-select" id="'+levelWiseOverviewArr[i]+'SelectDist">';
										collapse+='<option></option>';
									collapse+='</select>';
								collapse+='</div>';
							}
							if(levelWiseOverviewArr[i] == 'mandal')
							{
								collapse+='<div class="col-sm-3">';
									collapse+='<label>Constituency</label>';
									collapse+='<select class="chosen-select" id="'+levelWiseOverviewArr[i]+'SelectCons">';
										collapse+='<option></option>';
									collapse+='</select>';
								collapse+='</div>';
							}
						collapse+='</div>';
						collapse+='<div class="row">';
							collapse+='<div class="col-sm-12">';
								collapse+='<div id="'+levelWiseOverviewArr[i]+'BodyId"></div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		}
	collapse+='</div>';
	$("#levelWiseOverviewId").html(collapse);
	$(".chosen-select").chosen({width :'100%'});
	for(var i in levelWiseOverviewArr)
	{
		getSolidInfoLocationWise(levelWiseOverviewArr[i]+'BodyId');
	}
	
}
function getSolidInfoLocationWise(blockid)
{
	$("#"+blockid).html(spinner);
	var json ={
		
	}
	$.ajax({                
		type:'Post',    
		url: 'getSolidInfoLocationWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			buildTable(result,blockid);
		}else{
			$("#"+blockid).html("NO DATA AVAILABLE");
		}
	});
	function buildTable(result,blockid)
	{
		var table = '';
		table+='<div class="table-responsive">';
			table+='<table class="table table-bordered" id="'+blockid+'dataTableId">';
				table+='<thead>';
					table+='<th>District</th>';
					table+='<th>RFID TAGGED HOUSES</th>';
					table+='<th>REGISTERED FARMERS</th>';
					table+='<th>RFID TRACKING</th>';
					table+='<th>SWM COLLECTION (TONS)</th>';
					table+='<th>NADAP - PITS (TONS)</th>';
					table+='<th>VERMI - PITS (TONS)</th>';
					table+='<th>VERMI - STOCK (TONS)</th>';
				table+='</thead>';
				table+='<tbody>';
				for(var i in result)
				{
					table+='<tr>';
						table+='<td>'+result[i].name+'</td>';
						table+='<td>'+result[i].rfidTags+'</td>';
						table+='<td>'+result[i].farmers+'</td>';
						table+='<td>'+result[i].rfidTracking+'</td>';
						table+='<td>'+result[i].swmCollection+'</td>';
						table+='<td>'+result[i].nadap+'</td>';
						table+='<td>'+result[i].vermi+'</td>';
						table+='<td>'+result[i].vermiStock+'</td>';
					table+='</tr>';
				}
				table+='</tbody>';
			table+='</table>';
		table+='</div>';
		$("#"+blockid).html(table);
		$("#"+blockid+"dataTableId").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [],
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			buttons: [
				{
					extend		:'csvHtml5',
					text		:'<i class="fa fa-file-text-o"></i>',
					titleAttr	: 'CSV',
					title		:  blockid,
					filename	:  blockid+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				},
				{
					extend		:'pdfHtml5',
					text		:'<i class="fa fa-file-pdf-o"></i>',
					titleAttr	:'PDF',
					title		: blockid,
					filename	: blockid+''+moment().format("DD/MMMM/YYYY  HH:MM"),
					orientation	: "landscape",
					pageSize	: 'A4',
					customize	: function (doc) {
								doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
								}
				}
			]
		});
	}
}
function getSolidWasteManagementOverAllCounts(locId,locationType){
	
	var json = {
		fromDate : "",
		toDate : "",
		locationType :locationType ,
		locationId:locId
	}
	$.ajax({                
		type:'POST',    
		url: 'getSolidWasteManagementOverAllCounts',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null){
			console.log(result);
		}
	});
}