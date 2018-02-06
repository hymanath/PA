var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalDistrictLatLagArr=[];
var globalOverviewResult;

$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});
getNTRSujalaOverviewDetails();
$(".chosen-select").chosen();


function getNTRSujalaOverviewDetails(){
	
	
	var json = {};
	$.ajax({                
		type:'POST',    
		url: 'getNtrSujalaOverview',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			globalOverviewResult = result;
			buildNTRSujalaOverviewDetails(result);
			buildMapDetails(result.districtList);
			buildDistrictWiseMpDetails(result.districtList);
			buildMotherPlantsOverviewDetails(result.motherPlantsList);
		}
	});
}
function buildNTRSujalaOverviewDetails(result){
	var str='';
	str+='<div class="row">';
		str+='<div class="col-sm-8">';
			str+='<div class="pad_border">';
				str+='<div class="row">';
					str+='<div class="col-sm-5 border_right_yash">';
						str+='<div class="media">';
						  str+='<div class="media-left">';
							str+='<a href="#">';
							  str+='<img class="media-object" src="Assests/images/icon-motherPlanet.png" alt="...">';
							str+='</a>';
						  str+='</div>';
						  str+='<div class="media-body">';
							str+='<h4 class="media-heading font_weight">Mother Plant</h4>';
							str+='<h5 class="m_top5">One Mother Plant with attached Multiple RDUs is called one Cluster </h5>';
								str+='<h3 class="pull-right bg_yash_color m_top5" data-toggle="modal" data-target="#mother_plants" style="cursor:pointer;">'+result.totalMotherPlants+'</h3>';
						  str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4 border_right_yash">';
						str+='<h4><img src="Assests/images/icon-healthStatus.png"/><span class="font_weight">Health Status</span></h4>';
						str+='<div class="row">';
							str+='<div class="col-sm-6 m_top20">';
								str+='<div class="bg_yash_color_10">';
									str+='<h5 class="text-center">Plant Health</h5>';
									str+='<div class="row">';
										str+='<div class="col-sm-6 m_top10">';
											str+='<h5 class="good_color">Good</h5>';
											str+='<h4 class="m_top20 good_color">'+result.activeMotherPlants+'</h4>';
										str+='</div>';
										str+='<div class="col-sm-6 m_top10">';
											str+='<h5 class="bad_color">Bad</h5>';
											str+='<h4 class="m_top20 bad_color">'+result.inActiveMotherPlants+'</h4>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
								//bgclose
							str+='</div>';
							str+='<div class="col-sm-6 m_top20">';
								str+='<div class="bg_yash_color_10">';
									str+='<h5 class="text-center">Water Quality</h5>';
									str+='<div class="row">';
										str+='<div class="col-sm-6 m_top10">';
											str+='<h5 class="good_color">Good</h5>';
											str+='<h4 class="m_top20 good_color">'+result.mpGoodWaterQuality+'</h4>';
										str+='</div>';
										str+='<div class="col-sm-6 m_top10">';
											str+='<h5 class="bad_color">Bad</h5>';
											str+='<h4 class="m_top20 bad_color">'+result.mpBadWaterQuality+'</h4>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
								//bgclose
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<h4 class="font_weight m_top10">Water Generated</h4>';
						if(result.mpSafeWaterDispenced != null && result.mpSafeWaterDispenced != 'N/A' && result.mpSafeWaterDispenced > 0)
							str+='<h3 class="bg_yash_color m_top30">'+result.mpSafeWaterDispenced+'<p class="pull-right m_top10" style="font-weight:normal;font-size:12px;">Lts Per Day</p></h3>';
						else
							str+='<h3 class="bg_yash_color m_top30"> - <p class="pull-right m_top10" style="font-weight:normal;font-size:12px;">Lts Per Day</p></h3>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<div class="row">';
				str+='<div class="col-sm-6 m_top10">';
					str+='<div class="pad_border">';
						str+='<div class="media">';
						  str+='<div class="media-left">';
							str+='<a href="#">';
							  str+='<img class="media-object" src="Assests/images/icon-customers.png" alt="..." style="width:50px;height:50px;">';
							str+='</a>';
						  str+='</div>';
						  str+='<div class="media-body">';
							str+='<h4 class="media-heading font_weight m_top10">Customers</h4>';
							str+='<h2 class="pull-right">'+result.totalCustomers+'</h2>';
						  str+='</div>';
						
							str+='<div class="col-sm-8 m_top10">';
								str+='<div class="row">';
									str+='<div class="col-sm-6">';
										str+='<div class="border_left_width_green">';
											str+='<h5 class="font_weight good_color margin_left_8">Active</h5>';
											str+='<h4 class="good_color m_top10 margin_left_8">'+result.activeCustomers+'</h4>';
										str+='</div>';
									str+='</div>';
									str+='<div class="col-sm-6">';
										str+='<div class="border_left_width_bad">';
											str+='<h5 class="font_weight bad_color margin_left_8">In&nbsp;Active</h5>';
											str+='<h4 class="bad_color m_top10 margin_left_8">'+result.inActiveCustomers+'</h4>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				
				str+='<div class="col-sm-6 m_top10">';
					
					str+='<div class="pad_border">';
						str+='<div class="media">';
						  str+='<div class="media-left">';
							str+='<a href="#">';
							  str+='<img class="media-object" src="Assests/images/icon-customers.png" alt="..." style="width:50px;height:50px;">';
							str+='</a>';
						  str+='</div>';
						  str+='<div class="media-body">';
							str+='<h4 class="media-heading font_weight m_top10">Agents</h4>';
							str+='<h2 class="pull-right">-</h2>';
						  str+='</div>';
						
							str+='<div class="col-sm-8 m_top10">';
								str+='<div class="row">';
									str+='<div class="col-sm-6">';
										str+='<div class="border_left_width_green">';
											str+='<h5 class="font_weight good_color margin_left_8">Active</h5>';
											str+='<h4 class="good_color m_top10 margin_left_8">-</h4>';
										str+='</div>';
									str+='</div>';
									str+='<div class="col-sm-6">';
										str+='<div class="border_left_width_bad" >';
											str+='<h5 class="font_weight bad_color margin_left_8">In&nbsp;Active</h5>';
											str+='<h4 class="bad_color m_top10 margin_left_8">-</h4>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
				str+='</div>';
			str+='</div>';
			//row closed
		str+='</div>';
		str+='<div class="col-sm-4">';
			str+='<div class="pad_border">';
				str+='<div class="row">';
					str+='<div class="col-sm-8">';
						str+='<div class="media">';
						  str+='<div class="media-left">';
							str+='<a href="#">';
							  str+='<img class="media-object" src="Assests/images/icon-rdu.png" alt="..." style="width: 50px;height: 50px;">';
							str+='</a>';
						  str+='</div>';
						  str+='<div class="media-body">';
							str+='<h4 class="media-heading font_weight">RDU</h4>';
							str+='<h5 class="m_top5">Remote Dispensing Unit</h5>';
						  str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<div class="bg_yash_color_10">';
							str+='<h3 class="m_top5 text-center">'+result.totalRDUs+'</h3>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				//row closed
				str+='<hr style="margin-bottom: 0px;margin-top: 10px;"/>';
				str+='<div class="row">';
					str+='<div class="">';
						str+='<div class="col-sm-6 m_top20">';
							str+='<div class="pad_10">';
								str+='<h5 class="font_weight">Sell Water</h5>';
								str+='<h3 class="m_top10">'+result.rduSellWater+' <span class="f_12">Lts</span></h3>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-6 m_top20">';
							str+='<div class="pad_10">';
								str+='<h5 class="font_weight">Revenue Generated</h5>';
								str+='<h3 class="m_top10"> - </h3>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
				//row closed
			str+='</div>';
		str+='</div>';
		str+='<div class="pad_border m_top10">';
			str+='<div class="row">';
				str+='<div class="col-sm-6">';
					str+='<div class="bg_yash_color" style="padding-top: 15px;padding-bottom: 15px;">';
						str+='<h5 class="text-center">Plant Health</h5>';
						str+='<div class="row">';
							str+='<div class="col-sm-6 m_top20">';
								str+='<h5 class="good_color">Good</h5>';
								str+='<h4 class="m_top20 good_color">'+result.activeRDUs+'</h4>';
							str+='</div>';
							str+='<div class="col-sm-6 m_top20">';
								str+='<h5 class="bad_color">Bad</h5>';
								str+='<h4 class="m_top20 bad_color">'+result.inActiveRDUs+'</h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-6">';
					str+='<div class="bg_yash_color_10" style="padding-top: 2px;padding-bottom: 2px;">';
						str+='<div class="border_left_width_yash">';
							str+='<h5 class="margin_left_8">High Dispance RDU</h5>';
							str+='<h4 class="m_top5 margin_left_8">'+result.highRDUDispanceLtrs+'<span class="f_12"> Lts</span></h4>';
						str+='</div>';
						str+='<div class="row">';
							str+='<div class="col-sm-12">';
								str+='<h5 class="pull-right ">'+result.highDispanceRDUName+'</h5>';
							str+='</div>';
						str+='</div>';
						
					str+='</div>';
					str+='<div class="bg_yash_color_10 m_top5" style="padding-top: 2px;padding-bottom: 2px;">';
						str+='<div class="border_left_width_yash">';
							str+='<h5 class="margin_left_8">Low Dispance RDU</h5>';
							str+='<h4 class="m_top5 margin_left_8">'+result.lowRDUDispanceLtrs+'<span class="f_12"> Lts</span></h4>';
						str+='</div>';
						str+='<div class="row">';
							str+='<div class="col-sm-12">';
								str+='<h5 class="pull-right">'+result.lowDispanceRDUName+'</h5>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		//row closed
	str+='</div>';
	str+='</div>';
	//row closed
	$("#overViewDetails").html(str);
	
}
function buildMapDetails(result){
	var str='';
	str+='<div class="row m_top10">';
		str+='<div class="col-sm-12">';
			str+='<div id="map_canvas"></div>';
		str+='</div>';
	str+='</div>';
	$("#mapDetailsDivId").html(str);
	
	var str1 = '';
	if(result != null && result.length > 0){
		for(var i in result){
			if(i == 1)
				str1+='<option value="'+result[i].name+'" selected>'+result[i].name+'</option>';
			else
				str1+='<option value="'+result[i].name+'">'+result[i].name+'</option>';
		}
		$("#districtId").html(str1);
		$("#districtId").trigger("chosen:updated");
		
		str1 = '';
		if(result[1].subList != null && result[1].subList.length > 0){
			for(var i in result[1].subList){
				if(i == 1)
					str1+='<option value="'+result[1].subList[i].id+'" selected>'+result[1].subList[i].name+'</option>';
				else
					str1+='<option value="'+result[1].subList[i].id+'">'+result[1].subList[i].name+'</option>';
			}
			$("#motherPlantId").html(str1);
			$("#motherPlantId").trigger("chosen:updated");
			buildMotherPlantWiseDetails(result[1].subList[1]);
		}
	}
	
	var myOptions = {
        zoom: 10,
        //center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        mapTypeControl: false
    };
    var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
    var infowindow = new google.maps.InfoWindow();
    var marker, i;
    var bounds = new google.maps.LatLngBounds();
	if(result != null && result.length > 0){
		var result1 = result[1].subList;
		if(result1 != null && result1.length > 0){
			for(var i in result1){
				var pos = new google.maps.LatLng(result1[i].latitude, result1[i].longitude);
				bounds.extend(pos);
				marker = new google.maps.Marker({
					position: pos,
					map: map
				});
				google.maps.event.addListener(marker, 'click', (function (marker, i) {
					return function () {
						infowindow.setContent(result1[i].name);
						infowindow.open(map, marker);
					}
				})(marker, i));
				 //new google.maps.event.trigger( marker, 'click' );	
			}
		map.fitBounds(bounds);
		}
	}
}

$(document).on("change","#districtId",function(){
	 var districtName = $(this).val();
	 for(var i in globalOverviewResult.districtList){
		if(globalOverviewResult.districtList[i].name == districtName){
			var str='';
			var result = globalOverviewResult.districtList[i].subList;
			if(result != null && result.length > 0){
				for(var j in result){
					str+='<option value="'+result[j].id+'">'+result[j].name+'</option>';
				}
				$("#motherPlantId").html(str);
				$("#motherPlantId").trigger("chosen:updated");
				buildMotherPlantWiseDetails(result[0]);
			}
			
			var myOptions = {
				zoom: 10,
				//center: latlng,
				mapTypeId: google.maps.MapTypeId.ROADMAP,
				mapTypeControl: false
			};
			var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
			var infowindow = new google.maps.InfoWindow();
			var marker, k;
			var bounds = new google.maps.LatLngBounds();
			if(result != null && result.length > 0){
				for(var l in result){
					var pos = new google.maps.LatLng(result[l].latitude, result[l].longitude);
					bounds.extend(pos);
					marker = new google.maps.Marker({
						position: pos,
						map: map
					});
					google.maps.event.addListener(marker, 'click', (function (marker, k) {
						return function () {
							infowindow.setContent(result[l].name);
							infowindow.open(map, marker);
						}
					})(marker, k));
					 //new google.maps.event.trigger( marker, 'click' );	
				}
			map.fitBounds(bounds);
			}
		} 
	 }
	 
});

$(document).on("change","#motherPlantId",function(){
	 var mpId = $(this).val();
	 var districtName = $("#districtId").val();
	 for(var i in globalOverviewResult.districtList){
		if(globalOverviewResult.districtList[i].name == districtName){
			var result = globalOverviewResult.districtList[i].subList;
			if(result != null && result.length > 0){
				for(var j in result){
					if(result[j].id == mpId)
						buildMotherPlantWiseDetails(result[j]);
				}
			}
		}
	 }
});

function buildMotherPlantWiseDetails(result){
	var str='';
	str+='<div class="pad_5_fff">';
		str+='<div class="row">';
				str+='<div class="col-sm-7">';
					str+='<div class="pad_yash_5">';
						str+='<div class="media">';
						  str+='<div class="media-left">';
							str+='<a href="#">';
							  str+='<img class="media-object" src="Assests/images/icon-motherPlanet.png" alt="..." style="width: 40px;height: 40px;">';
							str+='</a>';
						  str+='</div>';
						  str+='<div class="media-body">';
							str+='<h4 class="media-heading font_weight">'+result.name+'</h4>';
							str+='<h5 class="m_top5 pull-right">Mother Plant</h5>';
						  str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-5">';
					str+='<h5 class="font_weight text-right">Water Generated</h5>';
					if(result.mpSafeWaterDispenced != null && result.mpSafeWaterDispenced != 'N/A' && result.mpSafeWaterDispenced > 0)
						str+='<h3 class="text-right">'+result.mpSafeWaterDispenced+'</h3>';
					else
						str+='<h3 class="text-right"> - </h3>';
					str+='<h5 class="text-right">Lts Per Day</h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="row m_top10">';
				str+='<div class="col-sm-12">';
					str+='<div class="row">';
						str+='<div class="col-sm-2">';
							str+='<img class="media-object" src="Assests/images/icon-healthStatus.png" alt="..." style="width: 40px;height: 40px;">';
						str+='</div>';
						str+='<div class="col-sm-6">';
							str+='<h5 class="m_top10">Health Status</h5>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="row">';
						str+='<div class="col-sm-4 m_top10">';
							str+='<div class="pad_5_F6F7FF">';
								str+='<h5 class="text-center">Plant Health</h5>';
								if(result.health != null && result.health == 'OK')
									str+='<h5 class="good_color m_top10 text-center">Good</h5>';
								else
									str+='<h5 class="bad_color m_top10 text-center">Bad</h5>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-8 m_top10">';
							str+='<div class="pad_5_F6F7FF">';
								str+='<div class="row">';
									if($(window).width>800){
										str+='<div class="col-sm-5 m_top10">';
									}else{
										str+='<div class="col-sm-5 ">';
									}
									str+='<h5 class="margin_left_5">Water&nbsp;Quality</h5>';
									if(result.waterQuality != null && result.waterQuality == 'OK')
										str+='<h5 class="good_color margin_left_5 m_top10">Good</h5>';
									else
										str+='<h5 class="bad_color margin_left_5 m_top10">Bad</h5>';
									str+='</div>';
									if($(window).width>800){
										str+='<div class="col-sm-3 m_top10">';
									}else{
										str+='<div class="col-sm-3 ">';
									}
										str+='<div class="border_width_tds">';
											str+='<h5 class="margin_left_5">TDS</h5>';
											str+='<h5 class="margin_left_5 m_top10">'+result.tds+'</h5>';
										str+='</div>';
									str+='</div>';
									if($(window).width>800){
										str+='<div class="col-sm-3 m_top10">';
									}else{
										str+='<div class="col-sm-3 ">';
									}
										str+='<div class="border_width_ph">';
											str+='<h5 class="margin_left_5">pH</h5>';
											str+='<h5 class="good_color margin_left_5 m_top10">'+result.ph+'</h5>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
	str+='</div>';
	str+='<div class="pad_5_fff">';
		str+='<div class="">';
			str+='<div class="" style="background-color:#EEF0FF;">';
				str+='<h5 class="font_weight pad_15">Remote Dispensing Units - '+result.subList.length+'</h5>';
				str+='<div style="border:1px solid #ccc;padding:5px;">';
					str+='<div style="background-color:#F0F0F0;padding:3px;">';
					if(result.subList != null && result.subList.length > 0){
						for(var i in result.subList){
							str+='<div class="row">';
								str+='<div class="col-sm-4">';
									str+='<div class="media">';
									  str+='<div class="media-left">';
										str+='<h5 class="numberCircle">01</h5>';
									  str+='</div>';
									  str+='<div class="media-body">';
										str+='<h5 class="media-heading font_weight">RDU Location</h5>';
										str+='<h5 class="m_top5">'+result.subList[i].name+'</h5>';
									  str+='</div>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-sm-4">';
									str+='<div class="border_width_ph">';
										str+='<h5>Water&nbsp;Tank&nbsp;Capacity</h5>';
										str+='<h5 class="m_top10">'+result.subList[i].waterTankCapacity+'</h5>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-sm-4">';
									str+='<div style="background-color:#AED2FF;padding:3px;">';
										str+='<h5>Water&nbsp;Tank&nbsp;Capacity</h5>';
										str+='<h5 class="m_top10">'+result.subList[i].mpSafeWaterDispenced+' lts</h5>';
										str+='<h5>Avg&nbsp;per&nbsp;Day</h5>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="row">';
								str+='<div class="col-sm-3">';
									str+='<div class="">';
										str+='<h5>Old Customers</h5>';
										str+='<h5 class="m_top10">'+result.subList[i].oldCustomers+'</h5>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-sm-3">';
									str+='<div class="">';
										str+='<h5>New Customers</h5>';
										str+='<h5 class="m_top10">'+result.subList[i].newCustomers+'</h5>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-sm-3">';
									str+='<div class="">';
										str+='<h5>Customers Visit</h5>';
										str+='<h5 class="m_top10">'+result.subList[i].totalCustomers+' lts</h5>';
										str+='<h5>Avg&nbsp;per&nbsp;Day</h5>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-sm-3">';
									str+='<div class="">';
										str+='<h5>Revenue Generated</h5>';
										str+='<h5 class="m_top10"> - </h5>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						}
					}
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#mpWiseDetailsDivId").html(str);
}

function buildDistrictWiseMpDetails(result){
	var str='';
	str+='<div class="table-responsive">';
		str+='<table class="table tbl-border">';
			str+='<thead>';
				str+='<tr>';
					str+='<th rowspan="3" class="text-align-center">Districts</th>';
					str+='<th colspan="3" class="text-align-center mp_bg">Mother Plants';
					str+='</th>';
					str+='<th colspan="3" class="text-align-center RDU_bg">RDU</th>';
					str+='<th colspan="3" class="text-align-center customer_bg">Customers</th>';
					str+='<th rowspan="3" class="text-align-center">Revenue</th>';
				str+='</tr>';
				str+='<tr>';
					str+='<th rowspan="2" class="text-align-center mp_bg">Name</th>';
					str+='<th rowspan="2" class="text-align-center mp_bg">Health</th>';
					str+='<th rowspan="2" class="text-align-center mp_bg">Water Quality</th>';
					str+='<th rowspan="2" class="text-align-center RDU_bg">Total</th>';
					str+='<th colspan="2" class="text-align-center RDU_bg">Health</th>';
					str+='<th rowspan="2" class="text-align-center customer_bg">Total</th>';
					str+='<th rowspan="2" class="text-align-center customer_bg">Active</th>';
					str+='<th rowspan="2" class="text-align-center customer_bg">In-Active</th>';
				str+='</tr>';
				str+='<tr>';
					str+='<th class="text-align-center RDU_bg">Good</th>';
					str+='<th class="text-align-center RDU_bg">Bad</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			if(result != null && result.length > 0){
				for(var i in result){
					var rdusCount = result[i].subList.length;
					if(rdusCount > 1){
						rdusCount = rdusCount + 1;
						str+='<tr>';
							str+='<td rowspan="'+rdusCount+'">'+result[i].name+'</td>';
						str+='</tr>';
						if(result[i].subList != null && result[i].subList.length > 0){
							for(var j in result[i].subList){
								str+='<tr>';
									str+='<td>'+result[i].subList[j].name+'</td>';
									if(result[i].subList[j].health != null && result[i].subList[j].health == 'OK')
										str+='<td class="good_color"><p><i class="fa fa-check-circle" aria-hidden="true"></i> Good </p></td>';
									else
										str+='<td class="bad_color"><p><i class="fa fa-times-circle" aria-hidden="true"></i> Bad </p></td>';
									if(result[i].subList[j].waterQuality != null && result[i].subList[j].waterQuality == 'OK')
										str+='<td class="good_color"><p><i class="fa fa-check-circle" aria-hidden="true"></i> Good </p></td>';
									else
										str+='<td class="bad_color"><p><i class="fa fa-times-circle" aria-hidden="true"></i> Bad </p></td>';
									str+='<td>'+result[i].subList[j].totalRDUs+'</td>';
									str+='<td>'+result[i].subList[j].activeRDUs+'</td>';
									str+='<td>'+result[i].subList[j].inActiveRDUs+'</td>';
									str+='<td>'+result[i].subList[j].totalCustomers+'</td>';
									str+='<td>'+result[i].subList[j].activeCustomers+'</td>';
									str+='<td>'+result[i].subList[j].inActiveCustomers+'</td>';
									str+='<td> - </td>';
								str+='</tr>';
							}
						}
					}else{
						str+='<tr>';
							str+='<td>'+result[i].name+'</td>';
							if(result[i].subList != null && result[i].subList.length > 0){
								for(var j in result[i].subList){
									str+='<td>'+result[i].subList[j].name+'</td>';
									if(result[i].subList[j].health != null && result[i].subList[j].health == 'OK')
										str+='<td class="good_color"><p ><i class="fa fa-check-circle" aria-hidden="true"></i> Good </p></td>';
									else
										str+='<td class="bad_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i> Bad </p></td>';
									if(result[i].subList[j].waterQuality != null && result[i].subList[j].waterQuality == 'OK')
										str+='<td class="good_color"><p><i class="fa fa-check-circle" aria-hidden="true"></i> Good </p></td>';
									else
										str+='<td class="bad_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i> Bad </p></td>';
									str+='<td>'+result[i].subList[j].totalRDUs+'</td>';
									str+='<td>'+result[i].subList[j].activeRDUs+'</td>';
									str+='<td>'+result[i].subList[j].inActiveRDUs+'</td>';
									str+='<td>'+result[i].subList[j].totalCustomers+'</td>';
									str+='<td>'+result[i].subList[j].activeCustomers+'</td>';
									str+='<td>'+result[i].subList[j].inActiveCustomers+'</td>';
									str+='<td> - </td>';
								}
							}
						str+='</tr>';
					}
					
				}
			}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	$("#districtWiseMpDetailsId").html(str);
}

function buildMotherPlantsOverviewDetails(result){
	var str='';
	str+='<div class="table-responsive">';
		str+='<table class="table table-bordered">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>Name</th>';
					str+='<th>Health</th>';
					str+='<th>Water Quality</th>';
					str+='<th>Water Generation Lts</th>';
					str+='<th>Connected RDU s</th>';
					str+='<th>Customers</th>';
					str+='<th>Location</th>';
					str+='<th>Mandal</th>';
					str+='<th>District</th>';
					str+='<th>Contact No</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			if(result != null && result.length > 0){
				for(var i in result){
					str+='<tr>';
						str+='<td><a href="">'+result[i].name+'</a></td>';
						if(result[i].health != null && result[i].health == 'OK')
							str+='<td class="good_color"><p ><i class="fa fa-check-circle" aria-hidden="true"></i> Good </p></td>';
						else
							str+='<td class="bad_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i> Bad </p></td>';
						if(result[i].waterQuality != null && result[i].waterQuality == 'OK')
							str+='<td class="good_color"><p><i class="fa fa-check-circle" aria-hidden="true"></i> Good </p></td>';
						else
							str+='<td class="bad_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i> Bad </p></td>';
						if(result[i].mpSafeWaterDispenced != null && result[i].mpSafeWaterDispenced != 'N/A' && result[i].mpSafeWaterDispenced > 0)
							str+='<td>'+result[i].mpSafeWaterDispenced+'</td>';
						else
							str+='<td> - </td>';
						str+='<td>'+result[i].totalRDUs+'</td>';
						str+='<td>'+result[i].totalCustomers+'</td>';
						str+='<td>'+result[i].location+'</td>';
						str+='<td>'+result[i].mandal+'</td>';
						str+='<td>'+result[i].district+'</td>';
						str+='<td>'+result[i].mobileNo+'</td>';
					str+='</tr>';
				}
			}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	$("#motherPlantsOverviewId").html(str);
}