var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var levelWiseOverviewArr = ['state','district','constituency','mandal']

var startDate = moment().subtract(1,"month").format("DD-MM-YYYY");
var endDate = moment().format("DD-MM-YYYY");

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
$(document).on('cut copy paste', function (e) {
	e.preventDefault();
});
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



$("#dateRangePicker").daterangepicker({
	opens:'left',
	startDate: moment().subtract(1,"month"),
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
});
$('#dateRangePicker').on('apply.daterangepicker', function(ev, picker) {
	startDate = picker.startDate.format("DD-MM-YYYY");
	endDate = picker.endDate.format("DD-MM-YYYY");
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
			getSolidInfoLocationWise(levelWiseOverviewArr[i]+'BodyId',0,0,"district","1-06-2017","30-07-2017",0,"",0,"");
		}
		else if(levelWiseOverviewArr[i] == "district"){
			getSolidInfoLocationWise(levelWiseOverviewArr[i]+'BodyId',0,0,"district","1-06-2017","30-07-2017",0,"",0,"");
		}else if(levelWiseOverviewArr[i] == "constituency"){
			getAllDistricts(levelWiseOverviewArr[i]+'SelectDist');
			
			getSolidInfoLocationWise(levelWiseOverviewArr[i]+'BodyId',0,0,"assembly","1-06-2017","30-07-2017",0,"district",0,"");
			
		}else if(levelWiseOverviewArr[i] == "mandal"){
			//SelectCons
			getAllDistricts(levelWiseOverviewArr[i]+'SelectDist');
			
			getSolidInfoLocationWise(levelWiseOverviewArr[i]+'BodyId',0,0,"mandal","1-06-2017","30-07-2017",0,"",0,"");
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
					$("#swmModalContent #rfidTaggedHouses").html(result[i].rfidTags);
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
					$("#swmModalContent  #totalRegVehicles").html(totRegvehicles);
					$("#swmModalContent  #gpId").html("<span style='font-size:12px'>NO DATA</span>");
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
					$("#swmModalContent  #rfidTracking").html(result[i].rfidTracking);
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
			table+='<table class="table table-bordered" id="'+blockid+'dataTableId">';
				table+='<thead>';
					if(isState){
						table+='<th>STATE</th>';
					}
					if(blockid == 'districtBodyId'){
						table+='<th>DISTRICTS</th>';
					}else if(blockid == 'constituencyBodyId'){
						table+='<th>CONSTITUENCY</th>';
					}else if(blockid == 'mandalBodyId'){
						table+='<th>MANDALS</th>';
					}
					//table+='<th>District</th>';
					table+='<th>RFID TAGGED HOUSES</th>';
					table+='<th>REGISTERED FARMERS</th>';
					table+='<th>RFID TRACKING</th>';
					table+='<th>SWM COLLECTION (TONS)</th>';
					table+='<th>NADAP - PITS (TONS)</th>';
					table+='<th>VERMI - PITS (TONS)</th>';
					table+='<th>VERMI - STOCK (TONS)</th>';
				table+='</thead>';
				table+='<tbody>';
					
				if(isState){	
						var rfidTags = 0.0;
						var farmers =  0.0;
						var rfidTracking =  0.0;
						var swmCollection =  0.0;
						var nadap =  0.0;
						var vermi =  0.0;
						var vermiStock =  0.0;
					
						for(var i in result)
						{
							rfidTags = parseFloat(rfidTags)+parseFloat(result[i].rfidTags);
							farmers = parseFloat(farmers)+parseFloat(result[i].farmers);
							rfidTracking = parseFloat(rfidTracking)+parseFloat(result[i].rfidTracking);
							swmCollection = parseFloat(swmCollection)+parseFloat(result[i].swmCollection);
							nadap = parseFloat(nadap)+parseFloat(result[i].nadap);
							vermi = parseFloat(vermi)+parseFloat(result[i].vermi);
							//vermiStock = parseFloat(rfidTags)+parseFloat(result[i].vermiStock);
							vermiStock = parseFloat(vermiStock)+parseFloat(result[i].vermiStock);
							isState=true;
						}				
						table+='<tr>';
							table+='<td class="text-capital">Andhra Pardesh</td>';
							table+='<td>'+rfidTags+'</td>';
							table+='<td>'+farmers+'</td>';
							table+='<td>'+rfidTracking+'</td>';
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
							table+='<td attr_dist_name="'+result[i].name+'" attr_dist_id="'+result[i].id+'" attr_onclick_distname="'+blockid+'" style="cursor: pointer" class="text-capital" data-toggle="modal" data-target="#swmModal">'+result[i].name+'<i class="fa fa-info-circle pull-right" aria-hidden="true" title="Click for '+result[i].name+' Details" attr_onclick_distname="'+blockid+'" attr_dist_id="'+result[i].id+'" style="cursor: pointer"></i></td>';
							table+='<td attr_dist_rfid="'+result[i].rfidTags+'">'+result[i].rfidTags+'</td>';
							table+='<td attr_dist_farmer="'+result[i].farmers+'">'+result[i].farmers+'</td>';
							table+='<td attr_dist_rfidTracking="'+result[i].rfidTracking+'">'+result[i].rfidTracking+'</td>';
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
			"aaSorting": [],
			"order": [ 0, 'asc' ],
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
	var locIdClick = $(this).attr('attr_onclick_distname');
	var locationId = distId;
	//"1-06-2017","30-07-2017"
	if(locIdClick == 'districtBodyId'){
			getSolidInfoLocationWise(0,distId,locationId,"district","1-06-2017","30-07-2017",0,"",0,"");
		}else if(locIdClick == 'constituencyBodyId'){
			getSolidInfoLocationWise(0,distId,locationId,"assembly","1-06-2017","30-07-2017",0,"",0,"");
		}else if(locIdClick == 'mandalBodyId'){
			getSolidInfoLocationWise(0,distId,locationId,"mandal","1-06-2017","30-07-2017",0,"",0,"");
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
	$("#swmModalContent  #rfidTracking").html(spinner);
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
			$("#rfidTracking").html(spinner);
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
			console.log(result);
			//alert(result.rfidTags);
			$("#rfidTaggedHouses").html(result.rfidTags);
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
			$("#totalRegVehicles").html(totRegvehicles);
			$("#gpId").html("<span>No data available</span>");
			$("#blocksId").html(result.blocks);
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
			$("#rfidTracking").html(result.rfidTracking);
			
		}
	});
}

$(document).on('change','#constituencySelectDist',function(){
	
		var selconstId = $(this).val();
		for(var i in levelWiseOverviewArr){
			if(levelWiseOverviewArr[i] == 'constituency'){
				getSolidInfoLocationWise(levelWiseOverviewArr[i]+'BodyId',0,0,"assembly","1-06-2017","30-07-2017",selconstId,"district",0,"");
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
			getSolidInfoLocationWise(levelWiseOverviewArr[i]+'BodyId',0,0,"mandal","1-06-2017","30-07-2017",0,"district",selmandalId,"assembly");
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
