var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var levelWiseOverviewArr = ['state','district','constituency','mandal']

//var startDate = moment().subtract(1,"month").format("DD-MM-YYYY");
var startDate = moment().format("DD-MM-YYYY");
var endDate =startDate;
onLoadCalls()
function onLoadCalls()
{
	levelWiseOverview();
	getSolidWasteManagementOverAllCounts(0,"district");
}
$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});
/*$(document).on('cut copy paste', function (e) {
	e.preventDefault();
});*/
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

/* $("#dateRangePicker").daterangepicker({
	opens:'left',
	startDate: moment(),
	endDate: moment(),
	locale: {
        format: "DD-MM-YYYY",
	},
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	}
});*/
$('#dateRangePicker').on('apply.daterangepicker', function(ev, picker) {
	startDate = picker.startDate.format("DD-MM-YYYY");
	endDate = picker.endDate.format("DD-MM-YYYY");
	onLoadCalls();
}); 
$('#singleDateRangePicker').daterangepicker({
	singleDatePicker: true,
	maxDate: new Date,
	locale: {
	  format: 'DD-MM-YYYY'
	}
});
$('#singleDateRangePicker').on('apply.daterangepicker', function(ev, picker) {
	
/* 	glStartDate = picker.startDate.format('DD-MM-YYYY');
	glEndDate = picker.endDate.format('DD-MM-YYYY'); */
	startDate = picker.startDate.format('DD-MM-YYYY');
	endDate =picker.endDate.format('DD-MM-YYYY');
	//var selectDate = picker.endDate.format('DD-MM-YYYY');
	onLoadCalls();
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
		if(levelWiseOverviewArr[i] == "state"){
			getSolidInfoLocationWise(levelWiseOverviewArr[i]+'BodyId',0,0,"district",startDate,endDate,0,"",0,"");
		}
		else if(levelWiseOverviewArr[i] == "district"){
			getSolidInfoLocationWise(levelWiseOverviewArr[i]+'BodyId',0,0,"district",startDate,endDate,0,"",0,"");
		}else if(levelWiseOverviewArr[i] == "constituency"){
			getAllDistricts(levelWiseOverviewArr[i]+'SelectDist');
			
			getSolidInfoLocationWise(levelWiseOverviewArr[i]+'BodyId',0,0,"constituency",startDate,endDate,0,"district",0,"");
			
		}else if(levelWiseOverviewArr[i] == "mandal"){
			//SelectCons
			getAllDistricts(levelWiseOverviewArr[i]+'SelectDist');
			
			getSolidInfoLocationWise(levelWiseOverviewArr[i]+'BodyId',0,0,"mandal",startDate,endDate,0,"",0,"");
		}
	}
	
}

function getSolidInfoLocationWise(blockid,distId,locationId,locationType,fromDate,toDate,filterId,filterType,subFilterId,subFilterType)
{
	
	$("#"+blockid).html(spinner);
	$("#swmModalContent").html(spinner);
	var json ={
		locationId:locationId,
		locationType:locationType,
		fromDate:startDate,
		toDate:endDate,
		
		filterId:filterId,
		filterType:filterType,
		
		subFilterId:subFilterId,
		subFilterType:subFilterType
		
		//locationId:"0",
		//locationType:"assembly",
		//filterId:"22",
		//filterType:"district",
		//fromDate:"1-06-2017",
		//toDate:"30-07-2017"
		
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
			if(distId ==0){
				
				
				buildTable(result,blockid);
			}else if(distId>0){
				for(var i in result){
					if(distId == result[i].id){
					$("#onclickDistName").html(result[i].name+" OVERVIEW");
					if(result[i].totalRfidTags!=null && result[i].totalRfidTags> 0){
				     $("#swmModalContent #rfidTaggedHouses").html(result[i].totalRfidTags);
			        }else{
				    $("#swmModalContent  #rfidTaggedHouses").html("-");
			        }			
					//$("#swmModalContent #rfidTaggedHouses").html(result[i].totalRfidTags);
					$("#swmModalContent  #registeredFarmers").html(result[i].farmers);
					$("#swmModalContent  #mgnrgsId").html(result[i].mgnres);
					$("#swmModalContent  #prId").html(result[i].pr);
					$("#swmModalContent  #publicId").html(result[i].publicType);
					var totalmanPower = result[i].mgnres+result[i].pr+result[i].publicType;
					$("#swmModalContent  #totalManPower").html(totalmanPower);
					$("#swmModalContent  #tractorId").html(result[i].tractor);
					$("#swmModalContent  #autoId").html(result[i].auto);
					$("#swmModalContent  #trycycleId").html(result[i].tricycle);
					$("#swmModalContent  #evehicleId").html(result[i].evehicle);
					var totRegvehicles = result[i].tractor+result[i].auto+result[i].tricycle+result[i].evehicle;
					var achieve=result[i].achieve;
					var target=result[i].target;
					var  achievetargetper=achieve/target*100;
					$("#swmModalContent  #totalRegVehicles").html(totRegvehicles);
					//$("#swmModalContent  #gpId").html("<span style='font-size:12px'>NO DATA</span>");
					$("#swmModalContent  #blocksId").html(result[i].blocks);
					$("#swmModalContent  #solidWasteId").html(result[i].houseCollecion);
					$("#swmModalContent  #farmerCattleDung").html(result[i].farmerCollection);
					$("#swmModalContent  #totSwmId").html(result[i].swmCollection);
					$("#swmModalContent  #stageOneId").html(result[i].nadap);
					$("#swmModalContent  #stageTwoId").html(result[i].vermi);
					$("#swmModalContent  #stageThreeId").html(result[i].vermiStock);
					$("#swmModalContent  #onekgCount").html(result[i].onekg);
					$("#swmModalContent  #fivekgCount").html(result[i].fivekg);
					$("#swmModalContent  #tenkgcount").html(result[i].tenkg);
					$("#swmModalContent  #twentyfivekgCount").html(result[i].twentyFivekg);
					$("#swmModalContent  #fiftykgCount").html(result[i].fiftykg);
					//$("#swmModalContent  #gpId").html(result[i].gpCnt);
					//$("#swmModalContent  #blocksId").html(result[i].blockNo);
					if(result[i].gpCnt!=null && result[i].gpCnt > 0){
				$("#swmModalContent #gpId").html(result[i].gpCnt);
			}else{
				$("#swmModalContent  #gpId").html("-");
			}
			if(result[i].blockNo!=null && result[i].blockNo > 0){
				$("#swmModalContent #blocksId").html(result[i].blockNo);
			}else{
				$("#swmModalContent  #blocksId").html("-");
			}	//$("#swmModalContent  #trackingId").html(achievetargetper.toFixed(2)+' %');
				if(target !=null && target > 0){
				$("#swmModalContent #trackingId").html(achievetargetper.toFixed(2)+' %');
			}else{
				$("#swmModalContent  #trackingId").html("-");
			}
			if(target !=null && target > 0){
				$("#swmModalContent #intimeId").html(result[i].inTimePer+' %');
			}else{
				$("#swmModalContent #intimeId").html("-");
			}
			if(target !=null && target > 0){
				$("#swmModalContent #outtimeId").html(result[i].outTimePer+' %');
			}else{
				$("#swmModalContent #outtimeId").html("-");
			}
			}
			} 
			}
				
			
		}else{
			$("#"+blockid).html("NO DATA AVAILABLE");
		}
	});
	function buildTable(result,blockid)
	{
		var table = '';
		var isState=false;
		if (blockid.indexOf('state') > -1 ) {
			 isState=true;
		}
		
		table+='<div class="table-responsive">';
			table+='<table class="table table-bordered" id="'+blockid+'dataTableId" style="width:100%">';
				table+='<thead>';
					table+='<tr>';
					if(isState){
							table+='<th class="font_sz" rowspan="2">STATE</th>';
							table+='<th class="font_sz" rowspan="2">TOTAL RFID TAGGED HOUSES</th>';
							table+='<th class="font_sz" colspan="4" style="text-align:center;">TODAY RFID ACHIEVED</th>';
							table+='<th class="font_sz" rowspan="2">REGISTERED FARMERS</th>';
							table+='<th class="font_sz" rowspan="2">SWM COLLECTION (TONS)</th>';
							table+='<th class="font_sz" rowspan="2">NADAP - PITS (TONS)</th>';
							table+='<th class="font_sz" rowspan="2">VERMI - PITS (TONS)</th>';
							table+='<th class="font_sz" rowspan="2">VERMI - STOCK (TONS)</th>';
					table+='</tr>';
					table+='<tr>';
							table+='<th class="font_sz">TODAY TARGET</th>';
							table+='<th class="font_sz">ACHIEVED</th>';
							table+='<th class="font_sz">ACHIEVED IN TIME</th>';
							table+='<th class="font_sz">ACHIEVED OUT TIME</th>';
							//table+='<th class="font_sz">OUT OF TARGET</th>';
					table+='</tr>';
					}else{
						if(blockid == 'districtBodyId'){
							table+='<th rowspan="2" class="font_sz">DISTRICTS</th>';
						}else if(blockid == 'constituencyBodyId'){
							table+='<th rowspan="2" class="font_sz">CONSTITUENCY</th>';
						}else if(blockid == 'mandalBodyId'){
							table+='<th rowspan="2" class="font_sz">MANDALS</th>';
						}
							table+='<th class="font_sz" rowspan="2">TOTAL RFID TAGGED HOUSES</th>';
							table+='<th class="font_sz" colspan="4" style="text-align:center;">TODAY RFID ACHIEVED</th>';
							table+='<th class="font_sz" rowspan="2">REGISTERED FARMERS</th>';
							table+='<th class="font_sz" rowspan="2">SWM COLLECTION (TONS)</th>';
							table+='<th class="font_sz" rowspan="2">NADAP - PITS (TONS)</th>';
							table+='<th class="font_sz" rowspan="2">VERMI - PITS (TONS)</th>';
							table+='<th class="font_sz" rowspan="2">VERMI - STOCK (TONS)</th>';
					table+='</tr>';
						table+='<tr>';
							table+='<th class="font_sz">TODAY TARGET</th>';
							table+='<th class="font_sz">ACHIEVED</th>';
							table+='<th class="font_sz">ACHIEVED IN TIME</th>';
							table+='<th class="font_sz">ACHIEVED OUT TIME</th>';
						//	table+='<th class="font_sz">OUT OF TARGET</th>';
					table+='</tr>';	
					}
					
				table+='</thead>';
				table+='<tbody>';
				if(isState){	
						//var rfidTags = 0.0;
						var farmers =  0.0;
						var trackingPer= 0.0;
						//var rfidTracking =  0.0;
						var swmCollection =  0.0;
						var nadap =  0.0;
						var vermi =  0.0;
						var vermiStock =  0.0;
						var totalRfidTags = 0;
						var inTime =0;
						var outTime= 0;
						var outOfTarget =0;
					    var achivements=0;
						var target=0;
						var inTimePer=0;
						var outTimePer=0;
						var   achieve=0;
						var targetAchievePer=0.0;
						var inTime =0;
						var targetinTimePer=0.0;
						for(var i in result)							
						{
							
							totalRfidTags=parseFloat(totalRfidTags)+parseFloat(result[i].totalRfidTags);
							target=parseFloat(target)+parseFloat(result[i].target);
							achieve=parseFloat(achieve)+parseFloat(result[i].achieve);
							inTimePer=parseFloat(inTimePer)+parseFloat(result[i].inTimePer);
							outTimePer=parseFloat(outTimePer)+parseFloat(result[i].outTimePer);							
							farmers = parseFloat(farmers)+parseFloat(result[i].farmers);
							//trackingPer = parseFloat(trackingPer)+parseFloat(result[i].trackingPer);
							swmCollection = parseFloat(swmCollection)+parseFloat(result[i].swmCollection);
							nadap = parseFloat(nadap)+parseFloat(result[i].nadap);
							vermi = parseFloat(vermi)+parseFloat(result[i].vermi);
						    vermiStock = parseFloat(vermiStock)+parseFloat(result[i].vermiStock);
							inTime = parseFloat(inTime)+parseFloat(result[i].inTime);						
							outTime = parseFloat(outTime)+parseFloat(result[i].outTime);	
							//inTime =inTime+result[i].inTime;
							//outTime=outTime+result[i].outTime;
							//outOfTarget=outOfTarget+result[i].outOfTarget;
							
							isState=true;
						}

						targetinTimePer=(inTime/target)*100;
						targetAchievePer=(achieve/target)*100;	
						targetoutTimePer=(outTime/target)*100;	
						table+='<tr>';
							table+='<td class="text-capital">Andhra Pardesh</td>';
							if(totalRfidTags>0){
							table+='<td>'+totalRfidTags+'</td>'; 
							}
							else
							{
							table+='<td>-</td>';
							}							
							if(target>0 || targetAchievePer>0 || targetinTimePer>0 || targetoutTimePer>0)
							{
								table+='<td style="background-color:#D90022; color:#fff;">'+target+'</td>';
								table+='<td style="background-color:#FFC0CB;">'+targetAchievePer.toFixed(2)+'%('+achieve+')</td>';
								table+='<td style="background-color:#92DD5A;">'+targetinTimePer.toFixed(2)+'%('+inTime+')</td>';
								table+='<td style="background-color:#1076F1;">'+targetoutTimePer.toFixed(2)+'%('+outTime+')</td>';
								}
							else
							{
							table+='<td style="background-color:#D90022; color:#fff;">-</td>';
							table+='<td style="background-color:#FFC0CB;">-</td>';
							table+='<td style="background-color:#92DD5A;">-</td>';
							table+='<td style="background-color:#1076F1; color:#fff;">-</td>';
							}
							//table+='<td style="background-color:#1076F1; color:#fff;">'+outTimePer+'%</td>';
							//table+='<td style="background-color:#FFA600;">'+result[i].outOfTarget+'</td>';
							table+='<td>'+farmers+'</td>';
							table+='<td>'+swmCollection.toFixed(2)+'</td>';
							table+='<td>'+nadap.toFixed(2)+'</td>';
							table+='<td>'+vermi.toFixed(2)+'</td>';
							table+='<td>'+vermiStock.toFixed(2)+'</td>';
						table+='</tr>';
				    }else{					
					for(var i in result)
						
					{			
						if(blockid == 'districtBodyId'){
							table+='<tr attr_onclick_distname="'+blockid+'">';
						}else if(blockid == 'constituencyBodyId'){
							table+='<tr attr_onclick_distname="'+blockid+'">';
						}else if(blockid == 'mandalBodyId'){
							table+='<tr attr_onclick_distname="'+blockid+'">';
						}
							table+='<td attr_dist_name="'+result[i].name+'" attr_dist_id="'+result[i].id+'" attr_onclick_distname="'+blockid+'" style="cursor: pointer" class="text-capital" data-toggle="modal" data-target="#swmModal">'+result[i].name+'<i class="fa fa-info-circle pull-right" aria-hidden="true" title="Click for '+result[i].name+' Details" attr_onclick_distname="'+blockid+'" attr_onclick_mandname="gpblockMand"  attr_dist_id="'+result[i].id+'" style="cursor: pointer"></i></td>';
							if(result[i].totalRfidTags>0){
							table+='<td attr_dist_rfid="'+result[i].totalRfidTags+'">'+result[i].totalRfidTags+'</td>'; 
							}
							else{
							table+='<td attr_dist_rfid="'+result[i].totalRfidTags+'">-</td>'; 
							}
							//table+='<td>0</td>';
							if(result[i].target>0 || result[i].trackingPer>0 || result[i].inTimePer>0 || result[i].outTimePer>0)
							{
							table+='<td style="background-color:#D90022; color:#fff;">'+result[i].target+'</td>';
							table+='<td style="background-color:#FFC0CB;">'+result[i].trackingPer+'%('+result[i].achieve+')</td>';
							table+='<td style="background-color:#92DD5A;">'+result[i].inTimePer+'%('+result[i].inTime+')</td>';
							table+='<td style="background-color:#1076F1; color:#fff;">'+result[i].outTimePer+'%('+result[i].outTime+')</td>';
							}
							else
							{
							table+='<td style="background-color:#D90022; color:#fff;">-</td>';
							table+='<td style="background-color:#FFC0CB;">-</td>';
							table+='<td style="background-color:#92DD5A;">-</td>';
							table+='<td style="background-color:#1076F1; color:#fff;">-</td>';
							}
                           //table+='<td style="background-color:#FFA600;">'+result[i].outOfTarget+'</td>';
							/* table+='<td>0</td>';
							table+='<td>0</td>';
							table+='<td>0</td>';
							table+='<td>0</td>'; */
							table+='<td attr_dist_rfidTracking="'+result[i].farmers+'">'+result[i].farmers+'</td>';
							table+='<td attr_dist_swmCollection="'+result[i].swmCollection+'">'+result[i].swmCollection.toFixed(2)+'</td>';
							table+='<td attr_dist_nadap="'+result[i].nadap+'">'+result[i].nadap.toFixed(2)+'</td>';
							table+='<td attr_dist_vermi="'+result[i].vermi+'">'+result[i].vermi.toFixed(2)+'</td>';
							table+='<td attr_dist_vermiStock="'+result[i].vermiStock+'">'+result[i].vermiStock.toFixed(2)+'</td>';					
						table+='</tr>';
					}
					
				}
				table+='</tbody>';
			table+='</table>';
		table+='</div>';
		$("#"+blockid).html(table);
		$("#"+blockid+"dataTableId").dataTable({ 
			"iDisplayLength": 15,
			"aaSorting" : [[ 1, "desc"] ],
			//"order": [ 0, 'asc' ],
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			buttons: [
				{
					extend		:'csvHtml5',
					text		:'<i class="fa fa-file-text-o"></i>',
					titleAttr	: 'CSV',
					title		:  "SOLID WASTE MANAGEMENT OVERVIEW",
					filename	:  blockid+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				},
				{
					extend		:'pdfHtml5',
					text		:'<i class="fa fa-file-pdf-o"></i>',
					titleAttr	:'PDF',
					title		: "SOLID WASTE MANAGEMENT OVERVIEW",
					filename	: blockid+''+moment().format("DD/MMMM/YYYY  HH:MM"),
					orientation	: "landscape",
					pageSize	: 'A3',
					customize	: function (doc) {
								doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
								}
				}
			]
		});
	}
}
$(document).on('click','[attr_onclick_distname]',function(){
	
	var distId = $(this).attr('attr_dist_id');
	
	//alert(distId);
	var locIdClick = $(this).attr('attr_onclick_distname');
	var locationId = distId;
	//"1-06-2017","30-07-2017"
	if(locIdClick == 'districtBodyId'){
			$("#gpsCountBlockId").html('');
			getSolidInfoLocationWise(0,distId,locationId,"district","1-06-2017","30-07-2017",0,"",0,"");
		}else if(locIdClick == 'constituencyBodyId'){
				$("#gpsCountBlockId").html('');
			getSolidInfoLocationWise(0,distId,locationId,"constituency","1-06-2017","30-07-2017",0,"",0,"");
		}else if(locIdClick == 'mandalBodyId'){
			//$("#gpsCountBlockId").html('');
			getSolidInfoLocationWise(0,distId,locationId,"mandal","1-06-2017","30-07-2017",0,"",0,"");
			
			
		}
		if($(this).attr('attr_onclick_mandname')=="gpblockMand" && locIdClick == 'mandalBodyId')
		{
			
			getGpWiseRfidTrackingOverData(locationId);
			
		}
		
		
	var appendModal = $("#swmInfraustructure").html();
	$("#swmModalContent").html(appendModal);
	$("#onclickDistName").html(spinner);
	$("#swmModalContent  #rfidTaggedHouses").html(spinner);
	$("#swmModalContent  #registeredFarmers").html(spinner);
	$("#swmModalContent  #mgnrgsId").html(spinner);
	$("#swmModalContent  #prId").html(spinner);
	$("#swmModalContent  #publicId").html(spinner);
	$("#swmModalContent  #totalManPower").html(spinner);
	$("#swmModalContent  #tractorId").html(spinner);
	
	$("#swmModalContent  #autoId").html(spinner);
	$("#swmModalContent  #trycycleId").html(spinner);
	$("#swmModalContent  #evehicleId").html(spinner);
	$("#swmModalContent  #totalRegVehicles").html(spinner);
	$("#swmModalContent  #gpId").html(spinner);
	$("#swmModalContent  #blocksId").html(spinner);
	$("#swmModalContent  #solidWasteId").html(spinner);
	$("#swmModalContent  #farmerCattleDung").html(spinner);
	$("#swmModalContent  #totSwmId").html(spinner);
	$("#swmModalContent  #stageOneId").html(spinner);
	$("#swmModalContent  #stageTwoId").html(spinner);
	$("#swmModalContent  #stageThreeId").html(spinner);
	$("#swmModalContent  #onekgCount").html(spinner);
	$("#swmModalContent  #fivekgCount").html(spinner);
	$("#swmModalContent  #tenkgcount").html(spinner);
	$("#swmModalContent  #twentyfivekgCount").html(spinner);
	$("#swmModalContent  #fiftykgCount").html(spinner);
	$("#swmModalContent  #trackingId").html(spinner);
	$("#swmModalContent  #intimeId").html(spinner);
	$("#swmModalContent  #outtimeId").html(spinner);
	
}) 
function getSolidWasteManagementOverAllCounts(locId,locationType){
	$("#rfidTaggedHouses").html(spinner);
	$("#registeredFarmers").html(spinner);
			$("#mgnrgsId").html(spinner);
			$("#prId").html(spinner);
			$("#publicId").html(spinner);
			
			$("#totalManPower").html(spinner);
			$("#tractorId").html(spinner);
			$("#autoId").html(spinner);
			$("#trycycleId").html(spinner);
			$("#evehicleId").html(spinner);
			
			$("#totalRegVehicles").html(spinner);
			$("#gpId").html(spinner);
			$("#blocksId").html(spinner);
			$("#solidWasteId").html(spinner);
			$("#farmerCattleDung").html(spinner);
			$("#totSwmId").html(spinner);
			$("#stageOneId").html(spinner);
			$("#stageTwoId").html(spinner);
			$("#stageThreeId").html(spinner);
			$("#onekgCount").html(spinner);
			$("#fivekgCount").html(spinner);
			$("#tenkgcount").html(spinner);
			$("#twentyfivekgCount").html(spinner);
			$("#fiftykgCount").html(spinner);
			//$("#rfidTracking").html(spinner);intimeId
			$("#trackingId").html(spinner);
			$("#intimeId").html(spinner);
			$("#outtimeId").html(spinner);
	var json = {
		fromDate : startDate,
		toDate : endDate,
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
		//	console.log(result);
			if(result.totalRfidTags !=null && result.totalRfidTags > 0){
				$("#rfidTaggedHouses").html(result.totalRfidTags);
			}else{
				$("#rfidTaggedHouses").html("-");
			}
			//$("#rfidTaggedHouses").html(result.totalRfidTags);
			$("#registeredFarmers").html(result.farmers);
			$("#mgnrgsId").html(result.mgnres);
			$("#prId").html(result.pr);
			$("#publicId").html(result.publicType);
			var totalmanPower = result.mgnres+result.pr+result.publicType;
			$("#totalManPower").html(totalmanPower);
			$("#tractorId").html(result.tractor);
			$("#autoId").html(result.auto);
			$("#trycycleId").html(result.tricycle);
			$("#evehicleId").html(result.evehicle);
			var totRegvehicles = result.tractor+result.auto+result.tricycle+result.evehicle;
			var achieve=0;
			achieve=(result.achieve);
			var target=0;
			target=(result.target);
			var achieverfid=0;
			achieverfid= (achieve/target*100);
			var inTime=0;
			var outTime=0;
			var inTimePer=0;
			var outTimePer=0;
			var initalDistrictId=0;
			var blocksCountDistrictwise=0;
			var gpCountDistrictwise=0;
			inTime=(result.inTime);
			outTime=(result.outTime);
			inTimePer=(inTime/target*100);
			outTimePer=(outTime/target*100);
			$("#totalRegVehicles").html(totRegvehicles);
			if(result.gpCnt !=null && result.gpCnt > 0){
				$("#gpId").html(result.gpCnt);
			}else{
				$("#gpId").html("-");
			}
			if(result.blockNo !=null && result.blockNo > 0){
				$("#blocksId").html(result.blockNo);
			}else{
				$("#blocksId").html("-");
			}
			//$("#gpId").html(result.gpCnt);
			//$("#blocksId").html(result.blockNo);
			$("#solidWasteId").html(result.houseCollecion.toFixed(2));
			$("#farmerCattleDung").html(result.farmerCollection.toFixed(2));
			$("#totSwmId").html(result.swmCollection.toFixed(2));
			$("#stageOneId").html(result.nadap.toFixed(2));
			$("#stageTwoId").html(result.vermi.toFixed(2));
			$("#stageThreeId").html(result.vermiStock.toFixed(2));
			$("#onekgCount").html(result.onekg);
			$("#fivekgCount").html(result.fivekg);
			$("#tenkgcount").html(result.tenkg);
			$("#twentyfivekgCount").html(result.twentyFivekg);
			$("#fiftykgCount").html(result.fiftykg);
			$("#intimeId").html(inTimePer.toFixed(2)+' %');
			$("#outtimeId").html(outTimePer.toFixed(2)+' %');
			if(target !=null && target > 0){
				$("#trackingId").html(achieverfid.toFixed(2)+' %');
			}else{
				$("#trackingId").html("-");
			}
			if(target !=null && target > 0){
				$("#intimeId").html(inTimePer.toFixed(2)+' %');
			}else{
				$("#intimeId").html("-");
			}
			if(target !=null && target > 0){
				$("#outtimeId").html(outTimePer.toFixed(2)+' %');
			}else{
				$("#outtimeId").html("-");
			}
			
		//alert("blocksCountDistrictwise:"+blocksCountDistrictwise);
		//alert("gpCountDistrictwise:"+gpCountDistrictwise);
	
	}
	});
	
}

$(document).on('change','#constituencySelectDist',function(){
	
		var selconstId = $(this).val();
		for(var i in levelWiseOverviewArr){
			if(levelWiseOverviewArr[i] == 'constituency'){
				getSolidInfoLocationWise(levelWiseOverviewArr[i]+'BodyId',0,0,"constituency","1-06-2017","30-07-2017",selconstId,"district",0,"");
			}
		}
			
})
$(document).on('change','#mandalSelectDist',function(){
	var selmandalId = $(this).val();
	getAllConstituenciesForDistrict("mandalSelectCons",selmandalId);
	for(var i in levelWiseOverviewArr){
		if(levelWiseOverviewArr[i] == 'mandal'){
			getSolidInfoLocationWise(levelWiseOverviewArr[i]+'BodyId',0,0,"mandal","1-06-2017","30-07-2017",selmandalId,"district",0,"");
		}
	}
})
$(document).on('change','#mandalSelectCons',function(){
	var selmandalId = $(this).val();
	for(var i in levelWiseOverviewArr){
		if(levelWiseOverviewArr[i] == 'mandal'){
			getSolidInfoLocationWise(levelWiseOverviewArr[i]+'BodyId',0,0,"mandal","1-06-2017","30-07-2017",0,"district",selmandalId,"constituency");
		}
	}
})

function getAllDistricts(divId){
		$("#"+divId).html('');
		
		var json = {}
		$.ajax({                
			type:'POST',    
			url: 'getAllDistrictsForPris',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			
			if(result !=null && result.length>0){
				 $("#"+divId).append('<option value="0">ALL</option>');
					for(var i in result){
						$("#"+divId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
			}
			$("#"+divId).trigger('chosen:updated');
		});
	}
	
	function getAllConstituenciesForDistrict(divId,distId){
		$("#"+divId).html("");
		$("#"+divId).trigger('chosen:updated');
		var json = {
			districtId :distId,
			type:"URBAN"
		}
		$.ajax({                
			type:'POST',    
			url: 'getAllConstituenciesForDistrict',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null && result.length>0){
				 $("#"+divId).append('<option value="0">ALL</option>');
					for(var i in result){
						$("#"+divId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
			}
			$("#"+divId).trigger('chosen:updated');
		});
	}
	
	// getGpWiseRfidTrackingOverData("668");
function getGpWiseRfidTrackingOverData(locationId) {
	$("#gpsCountBlockId").html(spinner);
	var json = {
		fromDate : startDate,
		locationId : locationId.toString()
	}
	$.ajax({
		type : 'POST',
		url : 'getGpWiseRfidTrackingOverData',
		dataType : 'json',
		data : JSON.stringify(json),
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result) {
		buildGpWiseRfidTrackingOverData(result);
	});
}
function buildGpWiseRfidTrackingOverData(result) {
	var totalgpCount = 0;
	var str = '';
	str += '<div class="col-sm-12 m_top20 white-block ">';
	str += '<div class="row m_top10">';
	str += '<div class="col-sm-12 m_top10 white-block">';
	str += '<div class="row">';
	str += '<div class="col-sm-4">';
	str += '<h4 class="m_top10"><b>GP TRACKING DETAILS</b></h4>';
	str += '</div>';
	str += '<div class="col-sm-2 pull-right">';
	str += "<h4 class='m_top10' id='totalGpCountId'><b></b></h4>";
	str += '</div>';
	str += '</div>';
	str += '<div class="row">';

	str += '<div class="col-sm-12 custom_border scrolingGpBlockCls">';

	for ( var i in result) {

		str += '<div class="col-sm-4 m_top10"  >';
		str += '<div class="pad_5 custom_border scrolingGpBlockCls">';
		str += '<div class="row">';
		str += '<div class="col-sm-6">';
		str += '<h5 class="m_top5"><b>' + result[i].subList[0].locationName
				+ '- GP</b></h5>';
		str += '</div>';
		str += '<div class="col-sm-5 pull-right" style="background-color:#F1F1F1;">';
		str += '<div class="media">';
		str += '<div class="media-left">';
		str += '<img src="Assests/images/home.PNG" class="media-object" style="width:35px">';
		str += '</div>';
		str += '<div class="media-body">';
		if (result[i].totalRfidTags != null && result[i].totalRfidTags != 0) {
			str += '<h4 class="media-heading">' + result[i].totalRfidTags
					+ '</h4>';
		}
		str += '</div>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		str += '<div class="row m_top10" style="background-color:#F7F8F7; padding-right:7px;">';
		str += '<div class="col-sm-3">';
		str += '<h6><b>Target</b></h6>';
		str += '</div>';
		str += '<div class="col-sm-3">';
		str += '<h6><b>Achievement</b></h6>';
		str += '</div>';
		str += '<div class="col-sm-3">';
		str += '<h6><b>In&nbsp;Time</b></h6>';
		str += '</div>';
		str += '<div class="col-sm-3">';
		str += '<h6><b>Out&nbsp;Time</b></h6>';
		str += '</div>';

		str += '</div>';
		str += '<div class="row m_top10" style="background-color:#F7F8F7; padding-right:7px;">';
		str += '<div class="col-sm-3">';
		str += '<h6><b>' + result[i].target + '</b></h6>';
		str += '</div>';
		str += '<div class="col-sm-3">';
		str += '<h6><b>' + result[i].trackingPer + '</b></h6>';
		str += '</div>';
		str += '<div class="col-sm-3">';
		str += '<h6><b>' + result[i].inTimePer + '</b></h6>';
		str += '</div>';
		str += '<div class="col-sm-3">';
		str += '<h6><b>' + result[i].outTimePer + '</b></h6>';
		str += '</div>';

		str += '</div>';

		for ( var j in result[i].subList) {
			if (j == 0) {
				totalgpCount = totalgpCount + 1;
			}
			if (result[i].subList[j].blockNo != null
					&& result[i].subList[j].blockNo != 0) {
				str += '<div class="row m_top10">';
				str += '<div class="col-sm-2 m_top10" style="margin-right:20px;">';
				str += '<div class="pad_top3r">';

				str += '<h6 style="font-size:10px;"><b>BLOCK-'
						+ result[i].subList[j].blockNo + '</b></h6>';

				str += '</div>';
				str += '</div>';
				str += '<div class="col-sm-8 m_top5">';
				str += '<div style="padding:0px 0px 0px 10px;">';
				str += '<div class="row" style="background-color:red;">';
				str += '<div class="col-sm-12 bor_LeftF bor_LeftN">';

				str += '<div class="row pad_top_bottom5" style="background-color:#D90022; padding:5px;">';
				str += '<h6 style="color:#fff;"><b>Target</b><span class="pull-right"><b>'
						+ result[i].subList[j].target + '</b></span></h6>';
				str += '</div>';
				str += '<div class="row pad_top_bottom5" style="background-color:#FFC0CB; padding:5px;">';
				str += '<h6 style="color:#fff;"><b>Achievement</b><span class="pull-right"><b>'
						+ result[i].subList[j].trackingPer
						+ '%('
						+ result[i].subList[j].achieve + ')</b></span></h6>';
				str += '</div>';
				str += '<div class="row pad_top_bottom5" style="background-color:#92DD5A; padding:5px;">';
				str += '<h6 style="color:#fff;"><b>In&nbsp;Time</b><span class="pull-right"><b>'
						+ result[i].subList[j].inTimePer
						+ '%('
						+ result[i].subList[j].inTime + ')</b></span></h6>';
				str += '</div>';
				str += '<div class="row pad_top_bottom5" style="background-color:#1076F1; padding:5px;">';
				str += '<h6 style="color:#fff;"><b>Out&nbsp;Time</b><span class="pull-right"><b>'
						+ result[i].subList[j].outTimePer
						+ '%('
						+ result[i].subList[j].outTime + ')</b></span></h6>';
				str += '</div>';

				str += '</div>';

				str += '</div>';
				str += '</div>';
				str += '</div>';
				str += '</div>';
			} else {
				str += '<h3 style="margin-top:30px;" class="text-center">No Data Available</h3>';
			}

		}
		str += '</div>';
		$('.scrolingGpBlockCls').mCustomScrollbar({
			setHeight : '200px'
		});
		str += '</div>';

	}
	str += '</div>';
	str += '</div>';
	str += '</div>';
	str += '</div>';
	str += '</div>';
	$("#gpsCountBlockId").html(str);
	$("#totalGpCountId").html("TOTAL -" + totalgpCount + "GP'S");
	$('.scrolingGpBlockCls').mCustomScrollbar({
		setHeight : '300px'
	})

}