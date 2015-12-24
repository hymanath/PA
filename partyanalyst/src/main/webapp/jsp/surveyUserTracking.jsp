<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
 <html>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
	<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" type="text/css"/>

		<style>
			body{background:#f0f0f0;}
			.m_top10{margin-top:10px;}
			.m_top20{margin-top:20px;}
			.m_top30{margin-top:30px;}
			.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
			.widgetservey_Red{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px; border-top:3px solid #ff0000;}
			.widgetservey h4{font-size:26px; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.widgetservey_Red h4{font-size:26px; color:#ff0000; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.username thead tr:nth-child(2){ background:#eee;}		
			.username td:first-child{ min-width: 200px; }		
			.username th small{ font-size:11px; }				
			.username th{ text-align:center; }
			/*----*/
			table tr td a{color:#333;}

			.wiget-yellow{background:#ffcc00; border:1px solid #ccc; width:100%; height:370px;padding:10px;}
			.wiget-yellow:hover{box-shadow: 0px -1px 5px #333;}
			.wiget-yellow small{color:red; font-size:18px;}
			.wiget-yellow h4{font-size:22px;border-bottom:2px solid #eee; text-align:center;}
			/*-----*/
			.boothdetails-nav li a{color:#333333; background:#eee;padding:10px; width:136px; display:table;line-height:20px;border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin-bottom: 10px;text-decoration:none; font-size:16px;}
			.boothdetails-nav li a:hover{ background:#ccc; border:1px solid #ffcc00;text-show:0px 1px #fff;}
			.booths-Overview-widget{background:#ddd;padding:10px; width:100%;}
			.booths-Overview-widget-nav li{color:#333333; background:#F6DD78;padding:10px; width:140px; display:table;line-height:20px;border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin: 10px;text-decoration:none; font-size:16px;}
			.booths-Overview-widget-nav li hgroup h4,h5{font-size:15px;}
	

		</style>	


<script src="js/maps/leaflet.js"></script>
<link rel="stylesheet" href="css/leaflet.css"></link>
<script src="js/maps/google.js"></script>
<script src="js/maps/Permalink.js"></script>

<script src="js/maps/googleMap.js"></script>
<script>

$(document).ready(function(){
$(".eventsheader").find(".span2").removeClass("span2")
$(".eventsheader").find(".span1").removeClass("span1")
$(".eventsheader").find(".span3").removeClass("span3")
$(".eventsheader").find(".span5").removeClass("span5")
   $('.datePickerCls').datepicker({
   dateFormat: 'dd-mm-yy'
   }).datepicker('setDate', new Date());
   
    var constiD =  '${userId}';
	var dateStr =  '${date}';
	var userId ='${userTypeId}';
	if(constiD != null && constiD != '')
	{
		$('#appendedInput').val(dateStr);
		getDetailsByConstituencyForSelectedUser(constiD,dateStr,userId);
	}
});

var map = '';
var apaccampus = {
"type": "Point",
"crs": { "type": "name", "properties": { "name": "urn:ogc:def:crs:OGC:1.3:CRS84" } },
"features": []
}

function getDetailsByConstituencyForSelectedUser(constiD,dateStr,userId)
{
	var jObj = 
	{
	 constituencyId: constiD,
	 dateStr :dateStr,
	 userId : userId
	}
	$.ajax({
			type:'GET',
			url: 'getLatLongForSurveyUsersByConstituencyAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				if(result != null && result.length > 0)
				buildLocationDetails(result);
				else
				alert("No Data Avaliable");
		});
}
function getDetailsByConstituency()
{
	var constituencyId = $('#userTrackingConstituencyId').val();
	if(constituencyId == 0)
	{
		$("#errorMsgDiv").html('<font style="color:red;font-size:14px;"> Select Constituency </font>');
		return;
	}
	var userId = new Array();
	var jObj = 
	{
	 constituencyId: $('#userTrackingConstituencyId').val(),
	 dateStr : $('#appendedInput').val(),
	 userId : userId,
	}
	$("#userTrackingImage").show();
	$("#errorMsgDiv").html('');
	$.ajax({
			type:'GET',
			url: 'getLatLongForSurveyUsersByConstituencyAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				$("#userTrackingImage").hide();
				if(result != null && result.length > 0)
				buildLocationDetails(result);
				else
				alert("No Data Avaliable");
		});
}
var features = new Array();
function buildLocationDetails(result)
{ 
	document.getElementById('weathermap').innerHTML = "<div class='span12 m_top20 widgetservey' id='map' style='height:500px'></div>";
	$('#detaildDiv123').show();
	$.each(result,function(index,value){
	if(value.latitude != null && value.longititude != null)
	{
		var voterDetails = 
		{ "type": "Feature",
		"properties": {"name":value.name,"location":value.location,"partno":value.partno,"url":value.url,"value":value.value,"villageCovered":value.villageCovered},
			"geometry": { "type": "Polygon", "coordinates": [[[value.latitude,value.longititude]]] }
		};
		apaccampus.features.push(voterDetails);
	}
	});
	map = new L.Map('map').setView(new L.LatLng(result[0].longititude,result[0].latitude), 10);
	var osm = new L.TileLayer('http://{s}.tile.osmosnimki.ru/kosmo/{z}/{x}/{y}.png');
	var mpn = new L.TileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png');
	var qst = new L.TileLayer('http://otile1.mqcdn.com/tiles/1.0.0/osm/{z}/{x}/{y}.png', {attribution:'Tiles Courtesy of <a href="http://www.mapquest.com/" target="_blank">MapQuest</a> <img src="http://developer.mapquest.com/content/osm/mq_logo.png">'}).addTo(map);
	var hyb = new L.TileLayer('http://{s}.tile.osmosnimki.ru/hyb/{z}/{x}/{y}.png');
	var irs = new L.TileLayer('http://tile.osmosnimki.ru/basesat/{z}/{x}/{y}.jpg');
	var wms = new L.TileLayer.WMS('http://wms.latlon.org/', {layers:'irs', crs: L.CRS.EPSG4326});
	var kadastr = new L.TileLayer.WMS('http://maps.rosreestr.ru/arcgis/services/Cadastre/CadastreWMS/MapServer/WMSServer', {format:'image/png', transparent:'true', layers:'16,15,14,13,11,10,9,22,21,20,19,18,7,6', tileSize:512});

	map.addControl(new L.Control.Scale({width: 100, position: 'bottomleft'}));
	map.addControl(new L.Control.Permalink());

	map.addControl(new L.Control.Layers({ 'Mapnik':mpn, 'MapQuest':qst,  'Google':new L.Google()},{}
	 ));
	L.geoJson(apaccampus, {
		
		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,
		
		
	   pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}
	}).addTo(map);
	for(var i in result)
	{
		if(result[i].latitude != null && result[i].longititude != null)
		{
			var iconImg = '';
			if(result[i].type == "Data Collectors")
			{
				iconImg = 'images/DC.png';
			}
			else
			{
				iconImg = 'images/DV.png';
			}
			var icon = L.icon({
			iconUrl: iconImg,

			iconSize:     [30, 30], // size of the icon
			shadowSize:   [10, 10], // size of the shadow
			iconAnchor:   [10, 10], // point of the icon which will correspond to marker's location
			shadowAnchor: [4, 62],  // the same for the shadow
			popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
		   });
			
	
			var markers = new L.Marker([result[i].longititude,result[i].latitude],{icon: icon});
			var popuoContent = "<table class='table table-bordered m_top20 table-hover table-striped username'><tr><td>Name : </td><td>"+result[i].name+"</td></tr>";
			popuoContent += "<tr><td>Mobile : </td><td>"+result[i].mandalName+"</td></tr>";
			popuoContent += "</table>";
			markers.bindPopup(popuoContent);
			map.addLayer(markers);	
		}
		
	}
	
	buildTable(result);
} 

function buildTable(result)
{
	var date = $('#appendedInput').val();
	var str = '';
	 str += '<div class="span12 m_top20 widgetservey">';
	 str += '<h4>Logged in users Details </h4>';
	 str += '<div class="row-fluid" >';
	 str += '<table class="table table-bordered m_top20 table-hover table-striped username">';	
	 str += '<thead class="alert alert-success">';
	 str += '<tr> ';
	 str += '<th>User Name </th>	';
	 str += '<th>Booth</th>	';
	 str += '<th>Mandal</th>';	
	 str += '<th>Panchayat</th>	';
	 str += '<th>Area Covered</th>	';
	 str += '<th>Location</th>	';				
	 str += '<th>Data Collected Map</th>	';	
	str += '<th>Tracking MAP</th>	';		 
	 str += '</tr>	';						
	 str += '</thead>';
	 str += '<tbody>';
	 for(var i in result)
	 {
		 str += '<tr>';
		 str += '<td><a onClick="getUserDetails('+result[i].id+','+result[i].orderId+',\'resultDiv'+i+'\',\'responceCountDiv'+i+'\')" style="cursor: pointer;">'+result[i].name+'</a> ';
		 if(result[i].type == "Data Collectors")
		 {
			str += '<span class="label label-info pull-right">DC</span>';
		 }
		 else
		 {
			str += '<span class="label pull-right" style="background-color:#FF0000">D.V</span>';
		 }
		 
		 str += '</td>	';
		 str += '<td>'+result[i].partno+'</td>	';
		 str += '<td>'+result[i].value+'</td>	';
		 str += '<td>'+result[i].url+'</td>	';
		 str += '<td>'+result[i].villageCovered+'</td>	';
		 str += '<td>'+result[i].location+'</td>	';
		 if(result[i].type == "Data Collectors"){
			str += '<td><a onClick="openTrackinWindow('+result[i].id+',\''+date+'\',1) " style="cursor: pointer;"><img src="images/DC.png"></img></a></td>	';
		 }
		 else
		 {
			str += '<td><a onClick="openTrackinWindow('+result[i].id+',\''+date+'\',1) " style="cursor: pointer;"><img src="images/DV.png"></img></a></td>	';
		 }
		 if(result[i].type == "Data Collectors"){
		 str += '<td><a onClick="openTrackinWindow('+result[i].id+',\''+date+'\',2) " style="cursor: pointer;"><img src="images/DC.png"></img></a></td>	';
		 }
		 else
		 {
			 str += '<td><a onClick="openTrackinWindow('+result[i].id+',\''+date+'\',2) " style="cursor: pointer;"><img src="images/DV.png"></img></a></td>	';
		 }
		 str += '</tr>	';	
		 str += '<tr id="resultDiv'+i+'" style="display:none;" class="resultDivCls">	';	
			str += '<td colspan="8"><div id="responceCountDiv'+i+'"></div></td>	';	
		 str += '</tr>	';	 
	 }		
	 str += '</tbody>';
	 str += '</table>	';
	 str += '</div>';
	 str += '</div>';
	 
	 $('#tableDiv').html(str);
}
function onEachFeature(feature, layer) 
{
	layer.bindPopup(); 
}

function getUserDetails(userId,boothId,resultDiv,buildDiv)
{
$('.resultDivCls').hide();
$('#'+resultDiv+'').show();

	var jObj = 
	{
	 userId: userId,
	 boothId : boothId
	}
	$.ajax({
			type:'GET',
			url: 'getRespectiveCountForBoothAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
			}).done(function(result){
			//console.log(result);
			$('#'+buildDiv+'').html('');
			if(result != null)
			{
				var str = '';
				str += '<div class="row-fluid">';
				str += '<div class="span12 booths-Overview-widget">';
				str += '<div class="row-fluid">';
				str += '<h4>Total Booth Voters - '+result[0]+'</h4>';
				str += '</div>';
				str += '<div class="row-fluid">';
				str += '<ul class="inline unstyled booths-Overview-widget-nav">';
				str += '<li>';
				str += '<hgroup>';
				str += '<h4>CASTE</h4>';
				str += '<h2>'+result[1]+'</h2>';
				str += '<h5>Completed</h5>';
				str += '</hgroup>';
				str += '</li>';
				str += '<li>';
				str += '<hgroup>';
				str += '<h4>HAMLET</h4>';
				str += '<h2>'+result[2]+'</h2>';
				str += '<h5>Mapped</h5>';
				str += '</hgroup>';
				str += '</li>';
				str += '<li>';
				str += '<hgroup>';
				str += '<h4>LOCAL AREA</h4>';
				str += '<h2>'+result[3]+'</h2>';
				str += '<h5>Identified</h5>';
				str += '</hgroup>';
				str += '</li>';
				str += '<li>';
				str += '<hgroup>';
				str += '<h4>CADRE</h4>';
				str += '<h2>'+result[4]+'</h2>';
				str += '<h5>Identified</h5>';
				str += '</hgroup>';
				str += '</li>';
				str += '<li>';
				str += '<hgroup>';
				str += '<h4 style="font-size:14px;">INFLUENCE PEOPLE</h4>';
				str += '<h2>'+result[5]+'</h2>';
				str += '<h5>Identified</h5>';
				str += '</hgroup>';
				str += '</li>';
				str += '</ul>';
				str += '</div>';
				str += '</div>';
				str += '</div>';
				$('#'+buildDiv+'').html(str);
			}
			
		});
		
		
}

function openTrackinWindow(userId,date,id)
{
	window.open('userWiseTrackingAction.action?userId='+userId+'&date='+date+'&userTypeId='+id+'');
}


function getconstituencies()
{
	var jsObj =
	{
	
	task : "getConstituencies"
	}
	$.ajax({
	type:'GET',
	url: 'getsurveyuserConstituenciesAction.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){

	$("#userTrackingConstituencyId").append('<option value="0">Select Constituency</option>');
	if(result != null && result.length > 0)
	{
		for(var i in result)
		{
			
			$("#userTrackingConstituencyId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
		}

	}
	
	});
}
function getDataAvalaiableConstituencyesUser()
{
	var jsObj = {}
	$.ajax({
			type:'GET',
			url: 'dataAvaliableConstituencyes.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
			$("#userTrackingConstituencyId option").remove();
				$("#userTrackingConstituencyId").append("<option value='0'>Select Constituency</option>");
				for(var i in result){
					$("#userTrackingConstituencyId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
				}
			
		});	
}

</script>
	<div class="container">
		<!---- Survey monitoring---->		
		<div class="row">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>User Tracking</h4>
							<div class="row">
								<div class="span8 offset3">
								<div id="errorMsgDiv" class="clearCls" ></div>
									<div class="row-fluid">
									
										<div class="span5">
											<label>Select Constituency</label>
										<select name="constituency" id="userTrackingConstituencyId" list="constituenciesList" style="width:230px;"><option value="0">Select Constituency</option></select>
										
								
										</div>
										
										<div class="span6">
											<label>Select Date</label>
											<div class="input-append">
											  <input type="text" id="appendedInput" class="datePickerCls" readonly="readonly">
											 <!-- <span class="add-on"><i class="icon-calendar icon-block"></i></span>-->
											</div>
										</div>	
									</div>	
										<div class="row text-center m_top10" style="margin-right:111px;"><button type="button" class="btn btn-success" onClick="getDetailsByConstituency();">SUBMIT</button><img id="userTrackingImage" style="width:70px;height:60px;display:none" src="images/Loading-data.gif" alt="Processing Image"></img></div>
								</div>
							</div>
						
					</div>
				</div>
				
				<div class="row-fluid hide" id="detaildDiv123" >
					<div class=" row-fluid clearClsspan12 m_top20 widgetservey " id="weathermap" style="height:500px">
						<!--<h5>Currently Logged in Users <span class="badge badge-success"> 15</span></h5><hr>-->
					</div>
					<p class="m_top10"> <span class="label label-info">D.C - Data Collector</span> &nbsp; &nbsp;<span class="label label-important"> D.V - Data Verifier</span></p>
				</div>
				<div class="row-fluid clearCls" id= "tableDiv"></div>
				<div id="responceCountDiv" class="clearCls"></div>
			</div>
		</div>
	</div>
	<script>
	 getDataAvalaiableConstituencyesUser();
	</script>