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
	$("#overViewDetails").html(spinner);
	//$("#mpWiseDetailsDivId").html(spinner);
	$("#districtWiseMpDetailsId").html(spinner);
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
			//buildMapDetails(result.districtList);
			buildDistrictWiseMpDetails(result.districtList);
			//buildMotherPlantsOverviewDetails(result.motherPlantsList);
			//buildRDUsOverviewDetails(result.rdusList);
		}else{
			$("#overViewDetails").html('');
			$("#mpWiseDetailsDivId").html('');
			$("#districtWiseMpDetailsId").html('');
		}
	});
}
$(document).on("click",".motherPlantsClass",function(){
	var type = $(this).attr("attr_type");
	buildMotherPlantsOverviewDetails(globalOverviewResult.motherPlantsList,type);
});

$(document).on("click",".rdusClass",function(){
	var type = $(this).attr("attr_type");
	buildRDUsOverviewDetails(globalOverviewResult.rdusList,type);
});
function buildNTRSujalaOverviewDetails(result){
	var str='';
	str+='<div class="row">';
		str+='<div class="col-sm-6">';
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
							str+='<h6 class="m_top5">One Mother Plant with attached Multiple RDUs is called one Cluster </h6>';
								str+='<h3 class="pull-right bg_yash_color_37 m_top_mp motherPlantsClass" attr_type="total" data-toggle="modal" data-target="#mother_plants" style="cursor:pointer;text-decoration:underline;">'+result.totalMotherPlants+'</h3>';
						  str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-7">';
						str+='<h4><img src="Assests/images/icon-healthStatus.png"/><span class="font_weight">Health Status</span></h4>';
						str+='<div class="row">';
							str+='<div class="col-sm-12">';
								str+='<div class="bg_yash_color_10">';
									str+='<h5>Plant Health</h5>';
									str+='<div class="row m_top5">';
										str+='<div class="col-sm-6 m_top5">';
											str+='<h5 class="good_color" style="float:left; margin-right:20px;">Good</h5>';
											str+='<h5 class="good_color motherPlantsClass" attr_type="good" data-toggle="modal" data-target="#mother_plants" style="cursor:pointer;text-decoration:underline;">'+result.activeMotherPlants+'</h5>';
										str+='</div>';
										str+='<div class="col-sm-6 m_top5">';
											str+='<h5 class="bad_color" style="float:left; margin-right:20px;">Bad</h5>';
											str+='<h5 class="bad_color motherPlantsClass" attr_type="bad" data-toggle="modal" data-target="#mother_plants" style="cursor:pointer;text-decoration:underline;">'+result.inActiveMotherPlants+'</h4>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
								//bgclose
							str+='</div>';
							str+='<div class="col-sm-12 m_top5">';
								str+='<div class="bg_yash_color_10">';
									str+='<h5>Water Quality</h5>';
									str+='<div class="row m_top5">';
										str+='<div class="col-sm-6 m_top5">';
											str+='<h5 class="good_color" style="float:left; margin-right:20px;">Good</h5>';
											str+='<h5 class="good_color">'+result.mpGoodWaterQuality+'</h5>';
										str+='</div>';
										str+='<div class="col-sm-6 m_top5">';
											str+='<h5 class="bad_color" style="float:left; margin-right:20px;">Bad</h5>';
											str+='<h5 class="bad_color">'+result.mpBadWaterQuality+'</h5>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
								//bgclose
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
				str+='</div>';
			str+='</div>';
			str+='<div class="row">';
				str+='<div class="col-sm-12 m_top10">';
					str+='<div class="pad_border">';
						str+='<div class="media">';
							str+='<div class="col-sm-4 m_top10">';
							  str+='<div class="media-left">';
								str+='<a href="#">';
								  str+='<img class="media-object" src="Assests/images/icon-customers.png" alt="..." style="width:50px;height:50px;">';
								str+='</a>';
							  str+='</div>';
							  str+='<div class="media-body">';
								str+='<h4 class="media-heading font_weight m_top10">Customers</h4>';
								
							  str+='</div>';
							str+='</div>';
						
							str+='<div class="col-sm-6 m_top10">';
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
							
							str+='<div class="col-sm-2 m_top10">';
								str+='<h2 class="pull-right">'+result.totalCustomers+'</h2>';
							str+='</div>';
							
						str+='</div>';
					str+='</div>';
				str+='</div>';
				
				
			str+='</div>';
			//row closed
		str+='</div>';
		str+='<div class="col-sm-6">';
			str+='<div class="pad_border" style="border-radius:5px 5px 0px 0px !important;">';
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
							str+='<h6 class="m_top5">Remote Dispensing Unit</h6>';
						  str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<div class="bg_yash_color_10">';
							str+='<h3 class="m_top5 text-center rdusClass" attr_type="total" data-toggle="modal" data-target="#rdus_modal" style="cursor:pointer;text-decoration:underline;">'+result.totalRDUs+'</h3>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				//row closed
				str+='<hr style="margin-bottom: 0px;margin-top: 10px;"/>';
				str+='<div class="row">';
					str+='<div class="">';
						str+='<div class="col-sm-6 m_top20">';
							str+='<div class="bg_yash_color_10">';
								str+='<h5 class="font_weight">Sell Water</h5>';
								str+='<h3 class="m_top10">'+result.rduSellWater+' <span class="f_12">Lts</span></h3>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-6 m_top20">';
							str+='<div class="bg_yash_color_10">';
								str+='<h5>Plant Health</h5>';
								str+='<div class="row">';
									str+='<div class="col-sm-6 m_top20">';
										str+='<h5 class="good_color" style="float:left; margin-right:20px;">Good</h5>';
										str+='<h5 class="good_color rdusClass" attr_type="good" attr_type="total" data-toggle="modal" data-target="#rdus_modal" style="cursor:pointer;text-decoration:underline;">'+result.activeRDUs+'</h5>';
									str+='</div>';
									str+='<div class="col-sm-6 m_top20">';
										str+='<h5 class="bad_color" style="float:left; margin-right:20px;">Bad</h5>';
										str+='<h5 class="bad_color rdusClass" attr_type="bad" attr_type="total" data-toggle="modal" data-target="#rdus_modal" style="cursor:pointer;text-decoration:underline;">'+result.inActiveRDUs+'</h5>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						/*str+='<div class="col-sm-6 m_top20">';
							str+='<div class="pad_10">';
								str+='<h5 class="font_weight">Revenue Generated</h5>';
								str+='<h3 class="m_top10"><img class="media-object" src="Assests/images/not_applicable.png" alt="..." style="width:40px;height:40px;"></h3>';
							str+='</div>';
						str+='</div>';*/
					str+='</div>';
					
				//row closed
			str+='</div>';
		str+='</div>';
		str+='<div class="pad_border pad_res" style="border-radius:0px 0px 5px 5px !important;">';
			str+='<div class="row">';
				str+='<div class="col-sm-6">';
					str+='<div class="bg_yash_color_10">';
						str+='<div class="border_left_width_yash pad_5">';
							str+='<div class="row">';
								str+='<div class="col-sm-4">';
									str+='<h5 class="">High Dispence RDU</h5>';
								str+='</div>';
								str+='<div class="col-sm-3">';
									str+='<h4 class="">'+result.highRDUDispanceLtrs+'<span class="f_12"> Lts</span></h4>';
								str+='</div>';
								str+='<div class="col-sm-5">';
									str+='<h5 class="f_12">'+result.highDispanceRDUName+'</h5>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-6">';
					str+='<div class="bg_yash_color_10" >';
						str+='<div class="border_left_width_yash pad_5">';
							str+='<div class="row">';
								str+='<div class="col-sm-4">';
									str+='<h5 class="">Low Dispence RDU</h5>';
								str+='</div>';
								str+='<div class="col-sm-3">';
									str+='<h4 class="">'+result.lowRDUDispanceLtrs+'<span class="f_12"> Lts</span></h4>';
								str+='</div>';
								str+='<div class="col-sm-5" style="padding-right:0px !important;">';
									str+='<h5 class="pull-right f_12">'+result.lowDispanceRDUName+'</h5>';
								str+='</div>';
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
	$(".toolTipCls").tooltip();
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
	str+='<div class="pad_5_fff" style="border-radius: 3px;">';
		str+='<div class="row">';
				str+='<div class="col-sm-12">';
					str+='<div class="pad_yash_5">';
						str+='<div class="media">';
						  str+='<div class="media-left">';
							str+='<a href="#">';
							  str+='<img class="media-object" src="Assests/images/icon-motherPlanet.png" alt="..." style="width: 40px;height: 40px;">';
							str+='</a>';
						  str+='</div>';
						  str+='<div class="media-body">';
							str+='<h4 class="media-heading font_weight motherPlantDetailsCls"  attr_mp_id="'+result.id+'" style="cursor:pointer;text-decoration:underline;">'+result.name+'</h4>';
							str+='<h5 class="m_top5 pull-right">Mother Plant</h5>';
						  str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				/*str+='<div class="col-sm-5">';
					str+='<h5 class="font_weight text-right">Water Generated</h5>';
					if(result.mpSafeWaterDispenced != null && result.mpSafeWaterDispenced != 'N/A' && result.mpSafeWaterDispenced > 0)
						str+='<h3 class="text-right">'+result.mpSafeWaterDispenced+'</h3>';
					else
						str+='<h3 class="text-right m_top5" style="margin-left: 120px;"><img class="media-object" src="Assests/images/not_applicable.png" alt="..." style="width:25px;height:25px;"></h3>';
					//str+='<h5 class="text-right">Lts Per Day</h5>';
				str+='</div>';*/
			str+='</div>';
			str+='<div class="row m_top10">';
				str+='<div class="col-sm-12">';
					str+='<div class="row">';
						str+='<div class="col-sm-1">';
							str+='<img class="media-object" src="Assests/images/icon-healthStatus.png" alt="..." style="width: 40px;height: 40px;">';
						str+='</div>';
						str+='<div class="col-sm-6">';
							str+='<h4 class="font_weight m_top10">Health Status</h4>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="row">';
						str+='<div class="col-sm-4 m_top5">';
							str+='<div class="pad_5_F6F7FF">';
								str+='<h5 class="text-center">Plant Health</h5>';
								if(result.health != null && result.health == 'OK')
									str+='<h5 class="good_color m_top10 text-center">Good</h5>';
								else
									str+='<h5 class="bad_color m_top10 text-center">Bad</h5>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-8 m_top5">';
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
	str+='<div class="pad_5_fff" style="background-color:#EEF0FF;border-radius: 3px;">';
		str+='<h5 class="font_weight pad_15">Remote Dispensing Units - '+result.subList.length+'</h5>';
		str+='<div class="desig-scroll">';
		var k=0;
			if(result.subList != null && result.subList.length > 0){
				for(var i in result.subList){
					k=k+1;
					str+='<div class="row">';
						str+='<div class="col-sm-12">';
							str+='<div class="pad_5_border" style="background: #F0F0F0;border-bottom: none;">';
								str+='<div class="row">';
									str+='<div class="col-sm-8">';
										str+='<div class="pad_yash_5">';
											str+='<div class="row">';
												str+='<div class="col-sm-6">';
													str+='<div class="media">';
													  str+='<div class="media-left">';
														str+='<h5 class="numberCircle">'+k+'</h5>';
													  str+='</div>';
													  str+='<div class="media-body">';
														str+='<h5 class="media-heading font_weight">RDU Location</h5>';
														str+='<h5 class="m_top5 f_12">'+result.subList[i].name+'</h5>';
													  str+='</div>';
													str+='</div>';
												str+='</div>';
												str+='<div class="col-sm-6">';
													str+='<div class="border_left_width_water_tank">';
														str+='<h5 class="font_weight m_left_5">Water&nbsp;Tank&nbsp;Capacity</h5>';
														str+='<h5 class="m_top10 m_left_5">'+result.subList[i].waterTankCapacity+'</h5>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
									str+='<div class="col-sm-4" >';
										str+='<div class="pad_blue_5">';
											str+='<h5 class="text-center font_weight">Water&nbsp;Dispence:</h5>';
											str+='<h5 class="text-center">'+result.subList[i].mpSafeWaterDispenced+' Lts</h5>';
											str+='<h5 class="text-center f_10 font_weight">Avg&nbsp;per&nbsp;Day</h5>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="pad_5_border" style="background: #fff;border:none;border-left: 1px solid #ccc;border-right: 1px solid #ccc;">';
								str+='<div class="row">';
									str+='<div class="col-sm-4">';
										str+='<h5 class="font_weight f_11">Old Customers</h5>';
										str+='<h4 class="m_top10">'+result.subList[i].oldCustomers+'</h4>';
									str+='</div>';
									str+='<div class="col-sm-4">';
										str+='<h5 class="font_weight f_11">New Customers</h5>';
										str+='<h4 class="m_top10">'+result.subList[i].newCustomers+'</h4>';
									str+='</div>';
									str+='<div class="col-sm-4">';
										str+='<h5 class="font_weight f_11">Customers&nbsp;Visit</h5>';
										str+='<h4 class="m_top10">'+result.subList[i].totalCustomers+'</h4>';
									str+='</div>';
									/*str+='<div class="col-sm-4">';
										str+='<div style="background-color: #86DBC6;padding: 5px;text-align: center;">';
											str+='<h5 class="font_weight f_11 text-center">Revenue Generated</h5>';
											str+='<h4 class="m_top10 text-center"><img class="media-object" src="Assests/images/not_applicable.png" alt="..." style="width:25px;height:25px;"></h4>';
										str+='</div>';
									str+='</div>';*/
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
				}
			}
		str+='</div>';	
	str+='</div>';
	$("#mpWiseDetailsDivId").html(str);
	if(result.subList.length>1){
		$(".desig-scroll").mCustomScrollbar({setHeight:'180px'});
	}
	
}

function buildDistrictWiseMpDetails(result){
	var str='';
	str+='<div class="">';
		str+='<table class="table table-bordered table_Ntr_Custom districtWiseMpTableCls">';
			str+='<thead>';
				str+='<tr>';
					str+='<th rowspan="3" class="default_white" style="min-width:20% !important;">Districts</th>';
					str+='<th colspan="3" class="mother_pant_color">Mother Plants</th>';
					str+='<th colspan="3" class="rdu_pant_color">RDU</th>';
					str+='<th colspan="3" class="customer_pant_color">Customers</th>';
					//str+='<th rowspan="3" class="default_white">Revenue</th>';
				str+='</tr>';
				str+='<tr>';
					str+='<th rowspan="2" class="mother_pant_color">Name</th>';
					str+='<th rowspan="2" class="mother_pant_color">Health</th>';
					str+='<th rowspan="2" class="mother_pant_color">Water Quality</th>';
					str+='<th rowspan="2" class="rdu_pant_color">Total</th>';
					str+='<th colspan="2" class="rdu_pant_color">Health</th>';
					str+='<th rowspan="2" class="customer_pant_color">Total</th>';
					str+='<th rowspan="2" class="customer_pant_color">Active</th>';
					str+='<th rowspan="2" class="customer_pant_color">In-Active</th>';
				str+='</tr>';
				str+='<tr>';
					str+='<th class="rdu_pant_color">Good</th>';
					str+='<th class="rdu_pant_color">Bad</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			if(result != null && result.length > 0){
				for(var i in result){
					/*var rdusCount = result[i].subList.length;
					if(rdusCount > 1){
						rdusCount = rdusCount + 1;
						str+='<tr>';
							str+='<td rowspan="'+rdusCount+'"><h4 class="font_weight"><img src="Assests/icons/ITC/'+result[i].name+'.png" style="width: 20%;"/>'+result[i].name+'</h4></td>';
						str+='</tr>';
						if(result[i].subList != null && result[i].subList.length > 0){
							for(var j in result[i].subList){
								str+='<tr>';
									str+='<td class="mother_pant_color motherPlantDetailsCls" attr_mp_id="'+result[i].subList[j].id+'" style="cursor:pointer;text-decoration:underline;">'+result[i].subList[j].name+'</td>';
									if(result[i].subList[j].health != null && result[i].subList[j].health == 'OK')
										str+='<td class="good_color mother_pant_color"><p><i class="fa fa-check-circle" aria-hidden="true"></i> Good </p></td>';
									else
										str+='<td class="bad_color mother_pant_color"><p><i class="fa fa-times-circle" aria-hidden="true"></i> Bad </p></td>';
									if(result[i].subList[j].waterQuality != null && result[i].subList[j].waterQuality == 'OK')
										str+='<td class="good_color mother_pant_color"><p><i class="fa fa-check-circle" aria-hidden="true"></i> Good </p></td>';
									else
										str+='<td class="bad_color mother_pant_color"><p><i class="fa fa-times-circle" aria-hidden="true"></i> Bad </p></td>';
									str+='<td class="rdu_pant_color">'+result[i].subList[j].totalRDUs+'</td>';
									str+='<td class="rdu_pant_color">'+result[i].subList[j].activeRDUs+'</td>';
									str+='<td class="rdu_pant_color">'+result[i].subList[j].inActiveRDUs+'</td>';
									str+='<td class="customer_pant_color">'+result[i].subList[j].totalCustomers+'</td>';
									str+='<td class="customer_pant_color">'+result[i].subList[j].activeCustomers+'</td>';
									str+='<td class="customer_pant_color">'+result[i].subList[j].inActiveCustomers+'</td>';
									str+='<td> - </td>';
								str+='</tr>';
							}
						}
					}else{
						str+='<tr>';
							str+='<td><h4 class="font_weight"><img src="Assests/icons/ITC/'+result[i].name+'.png" style="width: 20%;"/>    '+result[i].name+'</h4></td>';
							if(result[i].subList != null && result[i].subList.length > 0){
								for(var j in result[i].subList){
									str+='<td class="mother_pant_color motherPlantDetailsCls" attr_mp_id="'+result[i].subList[j].id+'" style="cursor:pointer;text-decoration:underline;">'+result[i].subList[j].name+'</td>';
									if(result[i].subList[j].health != null && result[i].subList[j].health == 'OK')
										str+='<td class="good_color mother_pant_color"><p ><i class="fa fa-check-circle" aria-hidden="true"></i> Good </p></td>';
									else
										str+='<td class="bad_color mother_pant_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i> Bad </p></td>';
									if(result[i].subList[j].waterQuality != null && result[i].subList[j].waterQuality == 'OK')
										str+='<td class="good_color mother_pant_color"><p><i class="fa fa-check-circle" aria-hidden="true"></i> Good </p></td>';
									else
										str+='<td class="bad_color mother_pant_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i> Bad </p></td>';
									str+='<td class="rdu_pant_color">'+result[i].subList[j].totalRDUs+'</td>';
									str+='<td class="rdu_pant_color">'+result[i].subList[j].activeRDUs+'</td>';
									str+='<td class="rdu_pant_color">'+result[i].subList[j].inActiveRDUs+'</td>';
									str+='<td class="customer_pant_color">'+result[i].subList[j].totalCustomers+'</td>';
									str+='<td class="customer_pant_color">'+result[i].subList[j].activeCustomers+'</td>';
									str+='<td class="customer_pant_color">'+result[i].subList[j].inActiveCustomers+'</td>';
									str+='<td> - </td>';
								}
							}
						str+='</tr>';
					}*/
					if(result[i].subList != null && result[i].subList.length > 0){
						for(var j in result[i].subList){
							str+='<tr>';
								str+='<td>'+result[i].name+'</td>';
								str+='<td class="mother_pant_color motherPlantDetailsCls" attr_mp_id="'+result[i].subList[j].id+'" style="cursor:pointer;text-decoration:underline;">'+result[i].subList[j].name+'</td>';
								if(result[i].subList[j].health != null && result[i].subList[j].health == 'OK')
									str+='<td class="good_color mother_pant_color"><p ><i class="fa fa-check-circle" aria-hidden="true"></i> Good </p></td>';
								else
									str+='<td class="bad_color mother_pant_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i> Bad </p></td>';
								if(result[i].subList[j].waterQuality != null && result[i].subList[j].waterQuality == 'OK')
									str+='<td class="good_color mother_pant_color"><p><i class="fa fa-check-circle" aria-hidden="true"></i> Good </p></td>';
								else
									str+='<td class="bad_color mother_pant_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i> Bad </p></td>';
								str+='<td class="rdu_pant_color">'+result[i].subList[j].totalRDUs+'</td>';
								str+='<td class="rdu_pant_color">'+result[i].subList[j].activeRDUs+'</td>';
								str+='<td class="rdu_pant_color">'+result[i].subList[j].inActiveRDUs+'</td>';
								str+='<td class="customer_pant_color">'+result[i].subList[j].totalCustomers+'</td>';
								str+='<td class="customer_pant_color">'+result[i].subList[j].activeCustomers+'</td>';
								str+='<td class="customer_pant_color">'+result[i].subList[j].inActiveCustomers+'</td>';
							str+='</tr>';
						}
					}
				}
			}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	$("#districtWiseMpDetailsId").html(str);
	$(".districtWiseMpTableCls").dataTable({
		"paging":   true,
		"info":     true,
		"searching": true,
		"autoWidth": true,
		"order": [[ 7, "desc" ]],
		//"sDom": '<"top"iflp>rt<"bottom"><"clear">',
		"iDisplayLength": 15,
		"aaSorting": [],
		"aLengthMenu": [[15, 50, 100, -1], [15, 50, 100, "All"]]
	});
}

function buildMotherPlantsOverviewDetails(result,type){
	var str='';
	str+='<div class="table-responsive">';
		str+='<table class="table table-bordered table_Ntr_Custom1 motherPlantsTableCls">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>Name</th>';
					str+='<th>Health</th>';
					str+='<th>Water&nbsp;Quality</th>';
					//str+='<th>Water&nbsp;Generation&nbsp;Lts</th>';
					str+='<th>Connected&nbsp;RDU&nbsp;s</th>';
					str+='<th>Customers</th>';
					str+='<th>Location</th>';
					str+='<th>Mandal</th>';
					str+='<th>District</th>';
					str+='<th>Contact No</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			if(result != null && result.length > 0){
				if(type != null && type == "total"){
					for(var i in result){
						str+='<tr>';
							str+='<td class="motherPlantDetailsCls" attr_mp_id="'+result[i].id+'" style="cursor:pointer;text-decoration:underline;color:#004F9E;">'+result[i].name+'</td>';
							if(result[i].health != null && result[i].health == 'OK')
								str+='<td class="good_color"><p ><i class="fa fa-check-circle" aria-hidden="true"></i>&nbsp;Good </p></td>';
							else
								str+='<td class="bad_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;'+result[i].health+' </p></td>';
							if(result[i].waterQuality != null && result[i].waterQuality == 'OK')
								str+='<td class="good_color"><p><i class="fa fa-check-circle" aria-hidden="true"></i>&nbsp;Good </p></td>';
							else
								str+='<td class="bad_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;Bad </p></td>';
							/*if(result[i].mpSafeWaterDispenced != null && result[i].mpSafeWaterDispenced != 'N/A' && result[i].mpSafeWaterDispenced > 0)
								str+='<td>'+result[i].mpSafeWaterDispenced+'</td>';
							else
								str+='<td> - </td>';*/
							str+='<td>'+result[i].totalRDUs+'</td>';
							str+='<td>'+result[i].totalCustomers+'</td>';
							str+='<td>'+result[i].location+'</td>';
							str+='<td>'+result[i].mandal+'</td>';
							str+='<td>'+result[i].district+'</td>';
							str+='<td>'+result[i].mobileNo+'</td>';
						str+='</tr>';
					}
				}else if(type != null && type == "good"){
					for(var i in result){
						if(result[i].health != null && result[i].health == 'OK'){
							str+='<tr>';
								str+='<td class="motherPlantDetailsCls" attr_mp_id="'+result[i].id+'" style="cursor:pointer;text-decoration:underline;color:#004F9E;">'+result[i].name+'</td>';
								if(result[i].health != null && result[i].health == 'OK')
									str+='<td class="good_color"><p ><i class="fa fa-check-circle" aria-hidden="true"></i>&nbsp;Good </p></td>';
								else
									str+='<td class="bad_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;Bad </p></td>';
								if(result[i].waterQuality != null && result[i].waterQuality == 'OK')
									str+='<td class="good_color"><p><i class="fa fa-check-circle" aria-hidden="true"></i>&nbsp;Good </p></td>';
								else
									str+='<td class="bad_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;Bad </p></td>';
								/*if(result[i].mpSafeWaterDispenced != null && result[i].mpSafeWaterDispenced != 'N/A' && result[i].mpSafeWaterDispenced > 0)
									str+='<td>'+result[i].mpSafeWaterDispenced+'</td>';
								else
									str+='<td> - </td>';*/
								str+='<td>'+result[i].totalRDUs+'</td>';
								str+='<td>'+result[i].totalCustomers+'</td>';
								str+='<td>'+result[i].location+'</td>';
								str+='<td>'+result[i].mandal+'</td>';
								str+='<td>'+result[i].district+'</td>';
								str+='<td>'+result[i].mobileNo+'</td>';
							str+='</tr>';
						}
					}
				}else if(type != null && type == "bad"){
					for(var i in result){
						if(result[i].health != null && result[i].health != 'OK'){
							str+='<tr>';
								str+='<td class="motherPlantDetailsCls" attr_mp_id="'+result[i].id+'" style="cursor:pointer;text-decoration:underline;color:#004F9E;">'+result[i].name+'</td>';
								if(result[i].health != null && result[i].health == 'OK')
									str+='<td class="good_color"><p ><i class="fa fa-check-circle" aria-hidden="true"></i>&nbsp;Good </p></td>';
								else
									str+='<td class="bad_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;'+result[i].health+' </p></td>';
								if(result[i].waterQuality != null && result[i].waterQuality == 'OK')
									str+='<td class="good_color"><p><i class="fa fa-check-circle" aria-hidden="true"></i>&nbsp;Good </p></td>';
								else
									str+='<td class="bad_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;Bad </p></td>';
								/*if(result[i].mpSafeWaterDispenced != null && result[i].mpSafeWaterDispenced != 'N/A' && result[i].mpSafeWaterDispenced > 0)
									str+='<td>'+result[i].mpSafeWaterDispenced+'</td>';
								else
									str+='<td> - </td>';*/
								str+='<td>'+result[i].totalRDUs+'</td>';
								str+='<td>'+result[i].totalCustomers+'</td>';
								str+='<td>'+result[i].location+'</td>';
								str+='<td>'+result[i].mandal+'</td>';
								str+='<td>'+result[i].district+'</td>';
								str+='<td>'+result[i].mobileNo+'</td>';
							str+='</tr>';
						}
					}
				}
			}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	$("#motherPlantsOverviewId").html(str);
	$(".motherPlantsTableCls").dataTable({
		"paging":   true,
		"info":     true,
		"searching": true,
		"autoWidth": true,
		//"sDom": '<"top"iflp>rt<"bottom"><"clear">',
		"iDisplayLength": 15,
		"aaSorting": [],
		"aLengthMenu": [[15, 50, 100, -1], [15, 50, 100, "All"]]
	});
}

$(document).on("click",".motherPlantDetailsCls",function(){
	$("#mother_plants_Details").modal("show");
	var mpId = $(this).attr("attr_mp_id");
	$(".motherPlantLowLevelDtsCls").attr("attr_mpId",mpId);
	var result;
	if(globalOverviewResult.motherPlantsList != null && globalOverviewResult.motherPlantsList.length > 0){
		for(var i in globalOverviewResult.motherPlantsList){
			if(globalOverviewResult.motherPlantsList[i].id == mpId)
				result = globalOverviewResult.motherPlantsList[i];
		}
	}
	buildMotherPlantAndRDUDetails(result);
});
function buildMotherPlantAndRDUDetails(result){
	var str='';
	if(result != null){
		$("#mpDetailsHeadingId").html(result.name);
		$("#mpDetailsHeadingMobileId").html(" +91 "+result.mobileNo);
		
		str+='<div class="pad_border">';
			str+='<div class="row">';
				str+='<div class="col-sm-4">';
					str+='<h5 class=""><img src="Assests/images/icon-healthStatus.png" style="width:25px;"/><span class="font_weight m_left_5">Health Status</span></h5>';
					str+='<div class="row">';
						str+='<div class="col-sm-4 m_top10">';
							str+='<div class="pad_5_F6F7FF">';
								str+='<h5 class="text-center f_12 font_weight">Plant Health</h5>';
								if(result.health != null && result.health == "OK")
									str+='<h5 class="good_color m_top15 text-center">Good</h5>';
								else
									str+='<h5 class="bad_color m_top15 text-center">'+result.health+'</h5>';
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
										str+='<h5 class="margin_left_5 f_12 font_weight">Water&nbsp;Quality</h5>';
										if(result.waterQuality != null && result.waterQuality == "OK")
											str+='<h5 class="good_color margin_left_5 m_top15">Good</h5>';
										else
											str+='<h5 class="bad_color margin_left_5 m_top15">Bad</h5>';
									str+='</div>';
									if($(window).width>800){
										str+='<div class="col-sm-3 m_top10">';
									}else{
										str+='<div class="col-sm-3 ">';
									}
										str+='<div class="border_width_tds">';
											str+='<h5 class="margin_left_5 f_12 font_weight">TDS</h5>';
											str+='<h5 class="margin_left_5 m_top15">'+result.tds+'</h5>';
										str+='</div>';
									str+='</div>';
									if($(window).width>800){
										str+='<div class="col-sm-3 m_top10">';
									}else{
										str+='<div class="col-sm-3 ">';
									}
										str+='<div class="border_width_ph">';
											str+='<h5 class="margin_left_5 f_12 font_weight">pH</h5>';
											str+='<h5 class="good_color margin_left_5 m_top15">'+result.ph+'</h5>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				if(result.subList != null && result.subList.length > 0){
					str+='<div class="col-sm-6">';
						str+='<h5 class=""><img src="Assests/images/RMD.png" style="width:25px;"/><span class="font_weight m_left_5">Remote Dispensing Units - '+result.subList.length+'</span></h5>';
						str+='<div class="row">';
							str+='<div class="col-sm-6 m_top10">';
								str+='<div class="pad_5_F6F7FF">';
									str+='<div class="border_left_width_water_tank">';
									if(result.highDispanceRDUName != null && result.highDispanceRDUName.length > 0){
										if(result.highDispanceRDUName.length > 10)
											str+='<h5 class="m_left_5 f_12 font_weight">High Dispence RDU - <span class="toolTipCls" data-toggle="tooltip" data-placement="top" title="'+result.highDispanceRDUName+'">'+result.highDispanceRDUName.substr(0,10)+'..</span></h5>';
										else
											str+='<h5 class="m_left_5 f_12 font_weight">High Dispence RDU - <span>'+result.highDispanceRDUName+'</span></h5>';
										str+='<h4 class="m_top10  m_left_5">'+result.highRDUDispanceLtrs+' Lts</h4>';
									}
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-6 m_top10">';
								str+='<div class="pad_5_F6F7FF">';
									str+='<div class="border_left_width_red">';
									if(result.lowDispanceRDUName != null && result.lowDispanceRDUName.length > 0){
										if(result.lowDispanceRDUName.length > 10)
											str+='<h5 class="m_left_5 f_12 font_weight">Low Dispence RDU - <span class="toolTipCls" data-toggle="tooltip" data-placement="top" title="'+result.lowDispanceRDUName+'">'+result.lowDispanceRDUName.substr(0,10)+'..</h5>';
										else
											str+='<h5 class="m_left_5 f_12 font_weight">Low Dispence RDU - '+result.lowDispanceRDUName+'</h5>';
										str+='<h4 class="m_top10  m_left_5">'+result.lowRDUDispanceLtrs+' Lts</h4>';
									}
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				}
				
				str+='<div class="col-sm-2">';
					//str+='<div class="row">';
						/*str+='<div class="col-sm-8">';
							str+='<h5 class=""><img src="Assests/images/water_icon.png" style="width:25px;"/><span class="font_weight m_left_5">Water Generated</span></h5>';
							str+='<div class="pad_5_F6F7FF m_top10">';
								str+='<div class="m_top_bottom">';
									if(result.mpSafeWaterDispenced != null && result.mpSafeWaterDispenced != 'N/A' && result.mpSafeWaterDispenced > 0)
										str+='<h4 class="m_left_5">'+result.mpSafeWaterDispenced+' Per&nbsp;Lts</h4>';
									else
										str+='<h4 class="m_left_5"><img class="media-object toolTipCls" data-toggle="tooltip" data-placement="top" title="IOT device not fitted." src="Assests/images/not_applicable.png" alt="..." style="width:25px;height:25px;"></h4>';
								str+='</div>';
							str+='</div>';
						str+='</div>';*/
						//str+='<div class="col-sm-4">';
						str+='<h5 class=""><span class="font_weight m_left_5">Customers</span></h5>';
						str+='<div class="pad_5_F6F7FF m_top20">';
							str+='<div class="m_top_bottom">';
								str+='<h4 class="">'+result.totalCustomers+'</h4>';
							str+='</div>';
						str+='</div>';
					//str+='</div>';
					/*str+='<div class="col-sm-3">';
						str+='<h5 class=""><span class="font_weight m_left_5">Revenue</span></h5>';
						str+='<div class="pad_5_F6F7FF m_top20">';
							str+='<div class="m_top_bottom_5">';
								str+='<h4 class=""> - </h4>';
								str+='<h5 class="f_11">In Lac</h5>';
							str+='</div>';
						str+='</div>';
					str+='</div>';*/
				//str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	/*str+='<div class="row">';
		str+='<div class="scrollerDivCls">';
		var k = 0;
		if(result.subList != null && result.subList.length > 0){
			for(var i in result.subList){
				k=k+1;
				str+='<div class="col-sm-6 m_top10">';
					str+='<div class="row">';
							str+='<div class="col-sm-12">';
								str+='<div class="pad_5_border" style="background: #F0F0F0;border-bottom: none;">';
									str+='<div class="row">';
										str+='<div class="col-sm-8">';
											str+='<div class="pad_yash_5">';
												str+='<div class="row">';
													str+='<div class="col-sm-6">';
														str+='<div class="media">';
														  str+='<div class="media-left">';
															str+='<h5 class="numberCircle">'+k+'</h5>';
														  str+='</div>';
														  str+='<div class="media-body">';
															str+='<h5 class="media-heading font_weight">RDU Location</h5>';
															str+='<h5 class="m_top5 f_12">'+result.subList[i].name+'</h5>';
														  str+='</div>';
														str+='</div>';
													str+='</div>';
													str+='<div class="col-sm-6">';
														str+='<div class="border_left_width_water_tank">';
															str+='<h5 class="font_weight m_left_5">Water&nbsp;Tank&nbsp;Capacity</h5>';
															str+='<h5 class="m_top10 m_left_5">'+result.subList[i].waterTankCapacity+' Lts</h5>';
														str+='</div>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
										str+='</div>';
										str+='<div class="col-sm-4" >';
											str+='<div class="pad_blue_5">';
												str+='<h5 class="text-center font_weight">Water&nbsp;Dispence: <span class="motherPlantWaterTankDtsCls pull-right" attr_rdu_id="'+result.subList[i].id+'"><i style="color: #fff;font-size: 12px;cursor:pointer;" class="glyphicon glyphicon-resize-full"></i></span></h5>';
												str+='<h5 class="text-center">'+result.subList[i].mpSafeWaterDispenced+' Lts</h5>';
												str+='<h5 class="text-center f_10 font_weight">Avg&nbsp;per&nbsp;Day</h5>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
								str+='<div class="pad_5_border" style="background: #fff;border:none;border-left: 1px solid #ccc;border-right: 1px solid #ccc;border-bottom:1px solid #ccc;">';
									str+='<div class="row">';
										str+='<div class="col-sm-3">';
											str+='<h5 class="font_weight f_11">Old Customers</h5>';
											str+='<h4 class="m_top10">'+result.subList[i].oldCustomers+'</h4>';
										str+='</div>';
										str+='<div class="col-sm-3">';
											str+='<h5 class="font_weight f_11">New Customers</h5>';
											str+='<h4 class="m_top10">'+result.subList[i].newCustomers+'</h4>';
										str+='</div>';
										str+='<div class="col-sm-2">';
											str+='<h5 class="font_weight f_11">Customers&nbsp;Visit</h5>';
											str+='<h4 class="m_top10">'+result.subList[i].totalCustomers+'</h4>';
										str+='</div>';
										str+='<div class="col-sm-4">';
											str+='<div style="background-color: #86DBC6;padding: 5px;text-align: center;">';
												str+='<h5 class="font_weight f_11 text-center">Revenue Generated</h5>';
												str+='<h4 class="m_top10 text-center"> - </h4>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
				str+='</div>';
			}
		}
		str+='</div>';
	str+='</div>';*/
	
	
	str+='<div class="table-responsive m_top10">';
		str+='<table class="table table-bordered table_Ntr_Custom1 mpWiseRdusCls">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>RDU&nbsp;Location</th>';
					str+='<th>Health</th>';
					str+='<th>Water&nbsp;Tank&nbsp;Capacity</th>';
					str+='<th>Water&nbsp;Dispence</th>';
					str+='<th>Customers Visit</th>';
					str+='<th>Old Customers</th>';
					str+='<th>New Customers</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			if(result.subList != null && result.subList.length > 0){
				for(var i in result.subList){
					str+='<tr>';
						str+='<td class="motherPlantWaterTankDtsCls" attr_rdu_id="'+result.subList[i].id+'" style="cursor:pointer;text-decoration:underline;" title="Click Here to get Last 30 Days RDU Details">'+result.subList[i].name+'</td>';
						if(result.subList[i].health != null && result.subList[i].health == 'OK')
							str+='<td class="good_color"><p ><i class="fa fa-check-circle" aria-hidden="true"></i>&nbsp;Good </p></td>';
						else
							str+='<td class="bad_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;'+result.subList[i].health+' </p></td>';
						str+='<td>'+result.subList[i].waterTankCapacity+'</td>';
						str+='<td>'+result.subList[i].mpSafeWaterDispenced+'</td>';
						str+='<td>'+result.subList[i].totalCustomers+'</td>';
						str+='<td>'+result.subList[i].oldCustomers+'</td>';
						str+='<td>'+result.subList[i].newCustomers+'</td>';
					str+='</tr>';
				}
			}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	}
	
	$("#motherPlantsDetailsId").html(str);
	$(".toolTipCls").tooltip();
	$(".mpWiseRdusCls").dataTable({
		"paging":   true,
		"info":     true,
		"searching": true,
		"autoWidth": true,
		//"sDom": '<"top"iflp>rt<"bottom"><"clear">',
		"iDisplayLength": 15,
		"aaSorting": [],
		"aLengthMenu": [[15, 50, 100, -1], [15, 50, 100, "All"]]
	});
	/*if(result.subList.length>6){
		$(".scrollerDivCls").mCustomScrollbar({setHeight:'360px'});
	}*/
}
$(document).on("click",".motherPlantLowLevelDtsCls",function(){
	$("#mother_plants_Details_low_level").modal("show");
	var mpId = $(this).attr("attr_mpId");
	getLast30DaysMpDetails(mpId);
	//buildMotherPlantLowLevelDetails();
});
function getLast30DaysMpDetails(mpId){
	$("#motherPlantsLowLevelDetailsId").html(spinner);
	var json = {
		locationId : mpId
	};
	$.ajax({                
		type:'POST',    
		url: 'getLast30DaysMotherPlantDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null)
			buildMotherPlantLowLevelDetails(result);
		else
			$("#motherPlantsLowLevelDetailsId").html('NO DATA AVAILABLE...');
	});
}
function buildMotherPlantLowLevelDetails(result){
	var str='';
	$("#mp30DaysHeadingId").html(result.name);
	str+='<div class="scrollerDivCls1">';
	if(result.subList != null && result.subList.length > 0){
		for(var i in result.subList){
			str+='<div class="row m_top10">';
				str+='<div class="col-sm-2">';
					str+='<div class="dateMotherCss">';
						str+='<h4 class="" style="">'+result.subList[i].date+'</h4>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<div class="pad_border">';
				str+='<div class="row">';
					str+='<div class="col-sm-4">';
						str+='<h5 class=""><img src="Assests/images/icon-healthStatus.png" style="width:25px;"/><span class="font_weight m_left_5">Health Status</span></h5>';
						str+='<div class="row">';
							str+='<div class="col-sm-4 m_top10">';
								str+='<div class="pad_5_F6F7FF">';
									str+='<h5 class="text-center f_12 font_weight">Plant Health</h5>';
									if(result.subList[i].plantHealthStatus != null && result.subList[i].plantHealthStatus == "OK")
										str+='<h5 class="good_color m_top15 text-center">Good</h5>';
									else
										str+='<h5 class="bad_color m_top15 text-center">Bad</h5>';
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
											str+='<h5 class="margin_left_5 f_12 font_weight">Water&nbsp;Quality</h5>';
											if(result.subList[i].waterQuanStatus != null && result.subList[i].waterQuanStatus == "OK")
												str+='<h5 class="good_color margin_left_5 m_top15">Good</h5>';
											else
												str+='<h5 class="bad_color margin_left_5 m_top15">Bad</h5>';
										str+='</div>';
										if($(window).width>800){
											str+='<div class="col-sm-3 m_top10">';
										}else{
											str+='<div class="col-sm-3 ">';
										}
											str+='<div class="border_width_tds">';
												str+='<h5 class="margin_left_5 f_12 font_weight">TDS</h5>';
												str+='<h5 class="margin_left_5 m_top15">'+result.subList[i].tdsCount+'</h5>';
											str+='</div>';
										str+='</div>';
										if($(window).width>800){
											str+='<div class="col-sm-3 m_top10">';
										}else{
											str+='<div class="col-sm-3 ">';
										}
											str+='<div class="border_width_ph">';
												str+='<h5 class="margin_left_5 f_12 font_weight">pH</h5>';
												str+='<h5 class="good_color margin_left_5 m_top15">'+result.subList[i].phCount+'</h5>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<h5 class=""><img src="Assests/images/RMD.png" style="width:25px;"/><span class="font_weight m_left_5">Remote Dispensing Units - '+result.subList[i].rduCount+'</span></h5>';
						str+='<div class="row">';
							str+='<div class="col-sm-6 m_top10">';
								str+='<div class="pad_5_F6F7FF">';
									str+='<div class="border_left_width_water_tank">';
										str+='<h5 class="m_left_5 f_12 font_weight">High Dispence RDU - '+result.subList[i].highDispanceRDUName+'</h5>';
										str+='<h4 class="m_top10  m_left_5">'+result.subList[i].highDispanceRDUCount+' Lts</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-6 m_top10">';
								str+='<div class="pad_5_F6F7FF">';
									str+='<div class="border_left_width_red">';
										str+='<h5 class="m_left_5 f_12 font_weight">Low Dispence RDU - '+result.subList[i].lowDispanceRDUName+'</h5>';
										str+='<h4 class="m_top10  m_left_5">'+result.subList[i].lowDispanceRDUCount+' Lts</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<div class="row">';
							str+='<div class="col-sm-6">';
								str+='<h5 class=""><img src="Assests/images/water_icon.png" style="width:25px;"/><span class="font_weight m_left_5">Water Generated</span></h5>';
								str+='<div class="pad_5_F6F7FF m_top10">';
									str+='<div class="m_top_bottom">';
										if(result.subList[i].mpWaterDispenced != null && result.subList[i].mpWaterDispenced != 'N/A' && result.subList[i].mpWaterDispenced > 0)
											str+='<h4 class="m_left_5">'+result.subList[i].mpWaterDispenced+' Per&nbsp;Lts</h4>';
										else
											str+='<h4 class="m_left_5"> - Per&nbsp;Lts</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-3">';
							str+='<h5 class=""><span class="font_weight m_left_5">Customers</span></h5>';
							str+='<div class="pad_5_F6F7FF m_top20">';
								str+='<div class="m_top_bottom">';
									str+='<h4 class="">'+result.subList[i].customers+'</h4>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-3">';
							str+='<h5 class=""><span class="font_weight m_left_5">Revenue</span></h5>';
							str+='<div class="pad_5_F6F7FF m_top20">';
								str+='<div class="m_top_bottom_5">';
									str+='<h4 class=""> - </h4>';
									str+='<h5 class="f_11">In Lac</h5>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		}
	}
	str+='</div>';
	$("#motherPlantsLowLevelDetailsId").html(str);
	if(result.subList.length>6){
		$(".scrollerDivCls1").mCustomScrollbar({setHeight:'350px'});
	}
}
$(document).on("click",".motherPlantWaterTankDtsCls",function(){
	$("#mother_plants_Details_water_tank").modal("show");
	var rduId = $(this).attr("attr_rdu_id");
	getLast30DaysRDUDetails(rduId);
	//buildMothePantWaterTankDetails();
});
function getLast30DaysRDUDetails(rduId){
	$("#motherPlantsRDUDetailsId").html(spinner);
	var json = {
		locationId : rduId
	};
	$.ajax({                
		type:'POST',    
		url: 'getLast30DaysRDUDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null)
			buildMothePantRDUDetails(result);
		else
			$("#motherPlantsRDUDetailsId").html('NO DATA AVAILABLE...');
	});
}
function buildMothePantRDUDetails(result){
	var str='';
	$("#rduHeadingId").html(result.name);
	$("#rduheadingCapacityId").html(result.waterTankCapacity+" Lts");
	str+='<div class="scrollerDivCls2">';
	if(result.subList != null && result.subList.length > 0){
		for(var i in result.subList){
			str+='<div class="pad_border">';
				str+='<div class="row">';
					str+='<div class="col-sm-2">';
						str+='<div class="pad_border" style="padding: 22px;">';
							str+='<h4 class="text-center">'+result.subList[i].date+'</h4>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-1">';
						str+='<div class="pad_5_F6F7FF" style="padding-top: 10px;padding-bottom: 10px;">';
						str+='<h5 class="text-center f_12 font_weight">RDU&nbsp;Health</h5>';
						if(result.subList[i].rduHealthStatus != null && result.subList[i].rduHealthStatus == 'OK')
							str+='<h5 class="good_color m_top15 text-center">Good</h5></div>';
						else
							str+='<h5 class="bad_color m_top15 text-center">Bad</h5></div>';
					str+='</div>';
					
					str+='<div class="col-sm-3">';
						str+='<div style="background-color:#F0F0F0;padding:12px;border-radius: 5px;">';
							str+='<div class="row">';
								str+='<div class="col-sm-6">';
									str+='<h5 class="font_weight f_11">Old Customers</h5>';
									str+='<h4 class="m_top10">'+result.subList[i].oldCustomers+'</h4>';
								str+='</div>';
								str+='<div class="col-sm-6">';
									str+='<h5 class="font_weight f_11">New Customers</h5>';
									str+='<h4 class="m_top10">'+result.subList[i].newCustomers+'</h4>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="col-sm-2">';
						str+='<div style="background-color:#F0F0F0;padding:12px;border-radius: 5px;">';
							str+='<h5 class="font_weight f_11">Customers Visit</h5>';
							str+='<h4 class="m_top10">'+result.subList[i].totalCustomers+'</h4>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="col-sm-2">';
						str+='<div style="background-color:#AED2FF;padding:12px;border-radius: 5px;">';
							str+='<h5 class="font_weight f_11">Water Dispence</h5>';
							str+='<h4 class="m_top10">'+result.subList[i].waterDispence+' Lts</h4>';
						str+='</div>';
					str+='</div>';
					
					str+='<div class="col-sm-2">';
						str+='<div style="background-color:#86DBC6;padding:12px;border-radius: 5px;">';
							str+='<h5 class="font_weight f_11">Revenue Generated</h5>';
							str+='<h4 class="m_top10"> - </h4>';
						str+='</div>';
					str+='</div>';
				
				str+='</div>';
			str+='</div>';
		}
	}
	str+='</div>';
	$("#motherPlantsRDUDetailsId").html(str);
	if(result.subList.length>6){
		$(".scrollerDivCls2").mCustomScrollbar({setHeight:'400px'});
	}
}
 $(document).on("click",".closeSecondModal",function(){
    setTimeout(function(){
      $("body").addClass("modal-open");
    },1000);
  });
  
function buildRDUsOverviewDetails(result,type){
	var str='';
	str+='<div class="table-responsive">';
		str+='<table class="table table-bordered table_Ntr_Custom1 rdusTableCls">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>Mother&nbsp;Plant</th>';
					str+='<th>RDU&nbsp;Name</th>';
					str+='<th>Health</th>';
					str+='<th>Location</th>';
					str+='<th>Water&nbsp;Tank&nbsp;Capacity</th>';
					str+='<th>Water&nbsp;Dispence</th>';
					str+='<th>Total Customers</th>';
					str+='<th>Old Customers</th>';
					str+='<th>New Customers</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			if(result != null && result.length > 0){
				if(type != null && type == 'total'){
					for(var i in result){
						str+='<tr>';
							str+='<td>'+result[i].mpName+'</td>';
							str+='<td>'+result[i].name+'</td>';
							if(result[i].health != null && result[i].health == 'OK')
								str+='<td class="good_color"><p ><i class="fa fa-check-circle" aria-hidden="true"></i>&nbsp;Good </p></td>';
							else
								str+='<td class="bad_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;'+result[i].health+' </p></td>';
							str+='<td>'+result[i].location+'</td>';
							str+='<td>'+result[i].waterTankCapacity+'</td>';
							str+='<td>'+result[i].mpSafeWaterDispenced+'</td>';
							str+='<td>'+result[i].totalCustomers+'</td>';
							str+='<td>'+result[i].oldCustomers+'</td>';
							str+='<td>'+result[i].newCustomers+'</td>';
						str+='</tr>';
					}
				}
				else if(type != null && type == 'good'){
					for(var i in result){
						if(result[i].health != null && result[i].health == 'OK'){
							str+='<tr>';
								str+='<td>'+result[i].mpName+'</td>';
								str+='<td>'+result[i].name+'</td>';
								str+='<td class="good_color"><p ><i class="fa fa-check-circle" aria-hidden="true"></i>&nbsp;'+result[i].health+'</p></td>';
								/*if(result[i].health != null && result[i].health == 'OK')
									str+='<td class="good_color"><p ><i class="fa fa-check-circle" aria-hidden="true"></i>&nbsp;Good </p></td>';
								else
									str+='<td class="bad_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;Bad </p></td>';*/
								str+='<td>'+result[i].location+'</td>';
								str+='<td>'+result[i].waterTankCapacity+'</td>';
								str+='<td>'+result[i].mpSafeWaterDispenced+'</td>';
								str+='<td>'+result[i].totalCustomers+'</td>';
								str+='<td>'+result[i].oldCustomers+'</td>';
								str+='<td>'+result[i].newCustomers+'</td>';
							str+='</tr>';
						}
					}
				}
				else if(type != null && type == 'bad'){
					for(var i in result){
						if(result[i].health != null && result[i].health != 'OK'){
							str+='<tr>';
								str+='<td>'+result[i].mpName+'</td>';
								str+='<td>'+result[i].name+'</td>';
								str+='<td class="bad_color"><p ><i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;'+result[i].health+' </p></td>';
								str+='<td>'+result[i].location+'</td>';
								str+='<td>'+result[i].waterTankCapacity+'</td>';
								str+='<td>'+result[i].mpSafeWaterDispenced+'</td>';
								str+='<td>'+result[i].totalCustomers+'</td>';
								str+='<td>'+result[i].oldCustomers+'</td>';
								str+='<td>'+result[i].newCustomers+'</td>';
							str+='</tr>';
						}
					}
				}
			}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	$("#rdusOverviewId").html(str);
	$(".rdusTableCls").dataTable({
		"paging":   true,
		"info":     true,
		"searching": true,
		"autoWidth": true,
		//"sDom": '<"top"iflp>rt<"bottom"><"clear">',
		"iDisplayLength": 15,
		"aaSorting": [],
		"aLengthMenu": [[15, 50, 100, -1], [15, 50, 100, "All"]]
	});
}