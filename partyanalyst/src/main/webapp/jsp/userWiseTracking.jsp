<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Tracking</title>
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
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
	<div class="span12">
		<div class="row-fluid ">
			<div class="span12 widgetservey_Red m_top20">
				<h4 id="heading">SURVEY DETAILS</h4>
				<div id="map" style="width: 100%; height: 400px;cursor: pointer;"></div>
				<div id="tableDiv"></div>
			</div>
			<!--<div class="span6 widgetservey_Red m_top20">
				<h4 >USER TRACKING DETAILS</h4>
				<div id="map1" style="width: 100%; height: 400px;cursor: pointer;"></div>
			</div>-->
		</div>
	</div>

	
	</div>
	
</div>


<div id="votersDetailsDiv"></div>
<script src="js/maps/leaflet.js"></script>
<link rel="stylesheet" href="css/leaflet.css"></link>
<script src="js/maps/google.js"></script>
<script src="js/maps/Permalink.js"></script>
<script src="js/maps/jqueryForMap.js"></script>
<script src="js/maps/googleMap.js"></script>
<script>


var userId = '${userId}';
var date = '${date}';
var id = '${userTypeId}';
var imgId = '${leaderId}';
var campus = {
"type": "Point",
"crs": { "type": "name", "properties": { "name": "urn:ogc:def:crs:OGC:1.3:CRS84" } },
"features": []
}
if(id == 1)
{
	$('#heading').html("Survey Data Collected Map");
	getUserDataCollectionDetails();
}
else
{
	$('#heading').html("User Tracking Map");
	getUserTrackingDetails();
}

function getUserDataCollectionDetails()
{
	var jObj = 
	{
	 surveyUserId: userId,
	 dateStr : date
	}
	$.ajax({
			type:'GET',
			url: 'getLatLongForSurveyDetailsAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
			buildLocationDetails(result);
		});
}

function buildLocationDetails(result)
{
	$.each(result,function(index,value){
	if(value.latitude != null && value.longitude != null)
	{
		var voterDetails = 
		{ "type": "Feature",
		"properties": {},
			"geometry": { "type": "Polygon", "coordinates": [[[value.longitude,value.latitude]]] }
		};
		campus.features.push(voterDetails);
	}
	});
	
	var map = new L.Map('map').setView(new L.LatLng(result[0].latitude,result[0].longitude), 15);
	var osm = new L.TileLayer('http://{s}.tile.osmosnimki.ru/kosmo/{z}/{x}/{y}.png');
	var mpn = new L.TileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png');
	var qst = new L.TileLayer('http://otile1.mqcdn.com/tiles/1.0.0/osm/{z}/{x}/{y}.png', {attribution:'Tiles Courtesy of <a href="http://www.mapquest.com/" target="_blank">MapQuest</a> <img src="http://developer.mapquest.com/content/osm/mq_logo.png">'}).addTo(map);
	

	map.addControl(new L.Control.Scale({width: 100, position: 'bottomleft'}));
	var link = new L.Control.Permalink();
	map.addControl(link);
	map.addControl(new L.Control.Layers({ 'Mapnik':mpn, 'MapQuest':qst,  'Google':new L.Google()},{}
	 ));
	L.geoJson(campus, {
		
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
		if(result[i].latitude != null && result[i].longitude != null)
		{
			var iconImg = '';
			
			if(imgId == 1)
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
			var markers = new L.Marker([result[i].latitude,result[i].longitude],{icon: icon});
			var popuoContent = "<table class='table table-bordered m_top20 table-hover table-striped username'><tr><td>Name : </td><td>"+result[i].name+"</td></tr>";
			//popuoContent += "<tr><td>Mobile : </td><td>"+result[i].mandalName+"</td></tr>";
			//popuoContent += "</table>";
			//markers.bindPopup(popuoContent);
			map.addLayer(markers);	
		}
		
	}
	
	
	
}
function onEachFeature(feature, layer)
{    
}


function getUserTrackingDetails()
{
	var jObj = 
	{
	 surveyUserId: userId,
	 dateStr : date
	}
	$.ajax({
			type:'GET',
			url: 'getLatLongForUserTrackungAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
		  buildUserLocationDetails(result)
		});
}


var polyline = '';
function buildUserLocationDetails(userLocations)
{
	
	var lat = new Array();
	var longt = new Array();
	$.each(userLocations,function(index,value){
	  lat.push(value.desc);
	  longt.push(value.name);
	});
	var map = new L.Map('map').setView(new L.LatLng(longt[0],lat[0]), 10);
	var osm = new L.TileLayer('http://{s}.tile.osmosnimki.ru/kosmo/{z}/{x}/{y}.png');
	var mpn = new L.TileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png');
	var qst = new L.TileLayer('http://otile1.mqcdn.com/tiles/1.0.0/osm/{z}/{x}/{y}.png', {attribution:'Tiles Courtesy of <a href="http://www.mapquest.com/" target="_blank">MapQuest</a> <img src="http://developer.mapquest.com/content/osm/mq_logo.png">'}).addTo(map);
	

	map.addControl(new L.Control.Scale({width: 100, position: 'bottomleft'}));
	map.addControl(new L.Control.Permalink());
	map.addControl(new L.Control.Layers({ 'Mapnik':mpn, 'MapQuest':qst,  'Google':new L.Google()},{}
	 ));
	/* L.geoJson(campus, {
		
		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,
		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}
	}).addTo(map); */
	var a = 0;
	polyline = L.polyline([]).addTo(map);
	for(var j = 0; j < longt.length; j++)
	{
			a++;
			var icon = L.icon({
			iconUrl: 'numberIcons/number'+a+'.JPG',

			iconSize:     [20, 20], // size of the icon
			shadowSize:   [10, 10], // size of the shadow
			iconAnchor:   [10, 10], // point of the icon which will correspond to marker's location
			shadowAnchor: [4, 62],  // the same for the shadow
			popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
		   });
			
	
		var markers = new L.Marker([longt[j], lat[j]],{icon: icon});
		map.addLayer(markers);	
		
		polyline.addLatLng(
        L.latLng(
            longt[j],lat[j]));
	}
	buildTable(userLocations);
}

function buildTable(result)
{
		var str = '';
		str+='<table class="table table-bordered m_top20 table-hover table-striped username" id="voterDetlsTab">';
		str+='<thead class="alert alert-success">';
		str+='<tr>';
		str+='<th>S.NO</th>';
		str+='<th>Time</th>';
		str+='<th>Longititude</th>';
		str+='<th>Latitude</th>';
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		var count = 1;
		for(var i in result)
		{
			str += '<tr>';
			str += '<td>'+count+'</td>';
			str += '<td>'+result[i].percent+'</td>';
			str += '<td>'+result[i].desc+'</td>';
			str += '<td>'+result[i].name+'</td>';
			str += '</tr>';
			count ++;
		}
		 str+='</tbody>';
		 str+='</table>';
		 $('#tableDiv').html(str);
		$('#voterDetlsTab').dataTable({
			"iDisplayLength": 100,
			"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
		});
}
$(".eventsheader").find(".span2").removeClass("span2")
$(".eventsheader").find(".span1").removeClass("span1")
$(".eventsheader").find(".span3").removeClass("span3")
$(".eventsheader").find(".span5").removeClass("span5")
</script>
</body>
</html>