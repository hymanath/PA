<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Tracking</title>
</head>
<body>
<div class="container">
	<div class="span12">
		<div class="row-fluid ">
			<div class="span12 widgetservey_Red m_top20">
				<h4 >SURVEY DETAILS MAP</h4>
				<div id="map" style="width: 1000px; height: 700px;cursor: pointer;"></div>
			</div>
		</div>
	</div>
	
	
	<div class="span12">
		<div class="row-fluid ">
			<div class="span12 widgetservey_Red m_top20">
				<h4 >USER TRACKING DETAILS MAP</h4>
				<div id="map1" style="width: 1000px; height: 700px;cursor: pointer;"></div>
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
var date = '${date}'
var campus = {
"type": "Point",
"crs": { "type": "name", "properties": { "name": "urn:ogc:def:crs:OGC:1.3:CRS84" } },
"features": []
}
getUserDataCollectionDetails();
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
	getUserTrackingDetails();
	
}
function onEachFeature(feature, layer)
{    
}

</script>
<script>

function getUserTrackingDetails()
{
	var jObj = 
	{
	 surveyUserId: 9,
	 dateStr : "08-07-2014"
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

var lat = new Array();
var longt = new Array();
var polyline = '';
var map1 = '';
function buildUserLocationDetails(userLocations)
{
	$.each(userLocations,function(index,value){
	
	  lat.push(value.desc);
	  longt.push(value.name);
	});

	
	var map = new L.Map('map').setView(new L.LatLng(16.25,80.15), 6);
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
	 console.log(campus);
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


	map1 = L.map('map1', {
		center: [longt[0],lat[0]],
		zoom: 10
	});
		
	map1.addControl(new L.Control.Scale({width: 100, position: 'bottomleft'}));
	map1.addControl(new L.Control.Permalink());

	map1.addControl(new L.Control.Layers({ 'Mapnik':mpn, 'MapQuest':qst,  'Google':new L.Google()},{}
	 ));
	 polyline = L.polyline([]).addTo(map1);
	var a = 0;
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
		map1.addLayer(markers);	
		
		polyline.addLatLng(
        L.latLng(
            longt[j],lat[j]));
	}
	
}

</script>
</body>
</html>