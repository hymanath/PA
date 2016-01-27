<html>
<head>
<title>SMS MAP</title>
<meta charset="utf-8" />
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.6.4/leaflet.css" />
<style>
<style>
/*table styles start*/
.CSSTableGenerator {
	margin:0px;padding:0px;
	width:100%;
	/*box-shadow: 10px 10px 5px #888888;*/
	border:1px solid #bfbf00;
	
	-moz-border-radius-bottomleft:0px;
	-webkit-border-bottom-left-radius:0px;
	border-bottom-left-radius:0px;
	
	-moz-border-radius-bottomright:0px;
	-webkit-border-bottom-right-radius:0px;
	border-bottom-right-radius:0px;
	
	-moz-border-radius-topright:0px;
	-webkit-border-top-right-radius:0px;
	border-top-right-radius:0px;
	
	-moz-border-radius-topleft:0px;
	-webkit-border-top-left-radius:0px;
	border-top-left-radius:0px;
}
.CSSTableGenerator table{
    border-collapse: collapse;
        border-spacing: 0;
	width:100%;
	height:100%;
	margin:0px;padding:0px;
}.CSSTableGenerator tr:last-child td:last-child {
	-moz-border-radius-bottomright:0px;
	-webkit-border-bottom-right-radius:0px;
	border-bottom-right-radius:0px;
}
.CSSTableGenerator table tr:first-child td:first-child {
	-moz-border-radius-topleft:0px;
	-webkit-border-top-left-radius:0px;
	border-top-left-radius:0px;
}
.CSSTableGenerator table tr:first-child td:last-child {
	-moz-border-radius-topright:0px;
	-webkit-border-top-right-radius:0px;
	border-top-right-radius:0px;
}.CSSTableGenerator tr:last-child td:first-child{
	-moz-border-radius-bottomleft:0px;
	-webkit-border-bottom-left-radius:0px;
	border-bottom-left-radius:0px;
}.CSSTableGenerator tr:hover td{
	
}
.CSSTableGenerator tr:nth-child(odd){ background-color:#ffffaa; }
.CSSTableGenerator tr:nth-child(even)    { background-color:#ffffff; }.CSSTableGenerator td{
	vertical-align:middle;
	border:1px solid #bfbf00;
	border-width:0px 1px 1px 0px;
	text-align:left;
	font-size:10px;
	font-family:Arial;
	font-weight:bold;
	color:#000000;
}.CSSTableGenerator tr:last-child td{
	border-width:0px 1px 0px 0px;
}.CSSTableGenerator tr td:last-child{
	border-width:0px 0px 1px 0px;
}.CSSTableGenerator tr:last-child td:last-child{
	border-width:0px 0px 0px 0px;
}
.CSSTableGenerator tr:first-child td{
		background:-o-linear-gradient(bottom, #ffff00 5%, #e8e800 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #ffff00), color-stop(1, #e8e800) );
	background:-moz-linear-gradient( center top, #ffff00 5%, #e8e800 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#ffff00", endColorstr="#e8e800");	background: -o-linear-gradient(top,#ffff00,e8e800);

	background-color:#ffff00;
	border:0px solid #bfbf00;
	text-align:center;
	border-width:0px 0px 1px 1px;
	font-size:14px;
	font-family:Arial;
	font-weight:bold;
	color:#000000;
}
.CSSTableGenerator tr:first-child:hover td{
	background:-o-linear-gradient(bottom, #ffff00 5%, #e8e800 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #ffff00), color-stop(1, #e8e800) );
	background:-moz-linear-gradient( center top, #ffff00 5%, #e8e800 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#ffff00", endColorstr="#e8e800");	background: -o-linear-gradient(top,#ffff00,e8e800);

	background-color:#ffff00;
}
.CSSTableGenerator tr:first-child td:first-child{
	border-width:0px 0px 1px 0px;
}
.CSSTableGenerator tr:first-child td:last-child{
	border-width:0px 0px 1px 1px;
}
.CSSTableGenerator table td{
	font-family:Arial;
	font-size:11px;
	text-align:center;
}
.CSSTableGenerator table{
	border:1px solid #bfbf00;	
}
/*table styles end*/
.noteDiv
{
	color:red;
	font-weight:bold;
}
.mandatory{
	color:red;
}

</style>
</style>
</head>
<body>

<div id="surveymap" >
<h4 align="center">SURVEY DETAILS MAP</h4>
<div id="map" style="width: 1000px; height: 700px;cursor: pointer;"></div>
</div>

<!--<div id= "usertracingMap" >
<h4 align="center">USER TRACKING DETAILS MAP</h4>
<div id="map1" style="width: 1000px; height: 700px;cursor: pointer;"></div>
</div>-->

<div id="votersDetailsDiv"></div>
<script src="js/leaflet.js"></script>
<link rel="stylesheet" href="css/leaflet.css"></link>
<script src="js/maps/google.js"></script>
<script src="js/maps/Permalink.js"></script>
<script src="js/jqueryForMap.js"></script>
<script src="js/apLocation.js"></script>
<script src="js/googleMap.js"></script>
<script>

showMapForMobileAppUserVoter("onload");
function showMapForMobileAppUserVoter(fromType)
{	
	var datesArr=[];
	if(fromType=="onload"){
		datesArr.push("${param.surveyDate}");
	}
		
	var jsObj={
		userId:"${param.userId}",
		divisonId:"${param.divisonId}",
		datesArr:datesArr
	}
					
	$.ajax({
     type: "POST",
     url: "showMapForMobileAppUserVoterAction.action",
     data: {task:JSON.stringify(jsObj)},
    }).done(function( result ) {
         buildLocationDetails(result);	
    });

}
var features = new Array();

function buildLocationDetails(result)
{
	
	$.each(result,function(index,value){
		
		var voterDetails = 
			{ "type": "Feature",
				"properties": {"constNameId":value.syncSource,"constName":value.uniqueKey},
				"geometry": { "type": "Polygon", "coordinates": [ [value.locationDetails]] }
			};
		campus.features.push(voterDetails);


});



}

function onEachFeature(feature, layer) {
	if(!(feature.properties.mobileNo == undefined))
	{
	    var popupContent = "<table class='table table-info'><tr><td>Divison Id : </td><td>"+feature.properties.constNameId+"</td>";
        popupContent += "<tr><td>Divison Name : </td><td>"+feature.properties.constName+"</td>";
	    popupContent += "</tr></table>";
		if (feature.properties && feature.properties.popupContent) {
			popupContent += feature.properties.popupContent;
		 }
		layer.setStyle({
		weight:7,
		color: '#FF0000',
		dashArray: '',
		});
		
		layer.bindPopup(popupContent);
		
		/* layer.on({
			  click: zoomToFeature
		}); */
			
		
	}
    
}
</script>


<script>

//getVotersCompleteDetails();
/* function getVotersCompleteDetails()
{
	//var surveyDate = ${month}+"/"+${date}+"/"+${year};
	var uname='${userName}';
	
	
	 $.ajax({
     type: "GET",
     url: "getVotersCompleteDetails.action",
     data: { userId :${userId} , surveyId:${surveyId},surveyDate:surveyDate }
     })
    .done(function( voterDetails ) {

	buildVotersDetails(voterDetails,surveyDate,uname);

    });

} */
/* function buildVotersDetails(voterDetails,surveyDate,uname)
{	var temp=0;
	var str = '';

	str+='<h4 style="text-align:center;">Voter Details Collected by <span style="font-weight:bold;color:red;text-transform:uppercase;">'+uname+'</span> on '+surveyDate+'</h4>';


    str+='<div class="CSSTableGenerator" style="width:500px;height:200px;overflow:auto;margin:10px 10px 10px 220px;">';

		str+='<table id="voterDetailsTable">';

		 str+='<tbody>';

			$.each(voterDetails,function(index,value){
				if(value.isSpecialUser != null && temp == 0){
				 str+='<tr>';
					str+='<td>Survey Time</td>';
					str+='<td>Longitude</td>';
					str+='<td>Latitude</td>';
					str+='<td>Respondent Name</td>';
					str+='<td>Designation</td>';
					str+='<td>Mobile Number</td>';
				  str+='</tr>';
				  temp=1;
				  }else if(value.isSpecialUser == null && temp == 0){
					str+='<tr>';
					str+='<td>Voter Name</td>';
					str+='<td>Survey Time</td>';
					str+='<td>Longitude</td>';
					str+='<td>Latitude</td>';
					 str+='</tr>';
					 temp=1;
				  }
				  
				 str+='<tr>';
				 
				 if(value.isSpecialUser != null){
						
					  str+='<td>'+value.time+'</td>';
					  str+='<td>'+value.longitude+'</td>';
					  str+='<td>'+value.latitude+'</td>';
					  if(value.respondeName == null)
						str+='<td> - </td>';
					else
					  str+='<td>'+value.respondeName+'</td>';
					  if(value.designation == null)
						str+='<td> - </td>';
					else
					  str+='<td>'+value.designation+'</td>';
					  if(value.respondentMobileNo == null)
						str+='<td> - </td>';
					else
					  str+='<td>'+value.respondentMobileNo+'</td>';
				 }else{
					str+='<td>'+value.respondentName+'</td>';
					str+='<td>'+value.time+'</td>';
					  str+='<td>'+value.longitude+'</td>';
					  str+='<td>'+value.latitude+'</td>';
				 }
				  
				 str+='</tr>';		
			});
		 str+='</tbody>';
		str+='</table>';
	str+='</div>';

	$('#votersDetailsDiv').html(str);

    getUserLocations();
} */


/* function getUserLocations()
{
	/*
	{ "type": "Feature", "properties": { }, "geometry": { "type": "Polygon", "coordinates": [ [ [ 80.071161819157368, 17.594996140487275 ] ] ] } }*/
    /*var surveyDate = ${month}+"/"+${date}+"/"+${year};
	$.ajax({
     type: "POST",
     url: "getUserTrackingDetails.action",
     data: {userId:${userId},surveyId:${surveyId},surveyDate:surveyDate }
    })
    .done(function( result ) {
        buildUserLocationDetails(result);
    });

} */

/* var lat = new Array();
var longt = new Array();
var polyline = '';
var map1 = '';
function buildUserLocationDetails(userLocations)
{
	
	$.each(userLocations,function(index,value){
	
	  lat.push(value.latitude);
	  longt.push(value.longitude);
	});

	
	 var map = new L.Map('map').setView(new L.LatLng(16.25,80.15), 6);

	//var osmUrl = 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
	//var attrib = 'Leaflet, Map (c) OSM contributors';
   // L.tileLayer(osmUrl, { attirbution : attrib }).addTo(map)
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
	
	

	//add();
		
} */

/* var i = 0;

function add()
{
	if (i < lat.length)
	window.setTimeout(add,100);
    i++;
}

function showUserTrackingMap()
{
	$('#surveymap').hide();
	$('#usertracingMap').show();
}

function showSurveyMap()
{
	$('#usertracingMap').hide();
	$('#surveymap').show();
}

function zoomToFeature(e)
{
   map.fitBounds(e.target.getBounds());
} */
</script>

</body>
</html>
