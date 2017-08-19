<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>VOTER SLIP GENERATION - 2016 </title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/mobileApp/css/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/newmultiselect/chosen.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/jQ_datatables/css/jquery.dataTables.css"/>
</head>
<style>
.chosen-choices{
	height:35px !important;
}
</style>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default m_top10">
				<div class="panel-heading bg_cc">
					<h4 class="panel-title">
						<span id="userHeadingId"></span>
						<div class="pull-right" style="margin-top:-8px">
						<button type="button" class="btn btn-info" id="datesMultiSelectId">GO</button>
						</div>
						<div class="pull-right col-md-3" style="margin-top:-6px">
							<select  class="form-control " multiple id="chosenselectId"></select>
							
						</div>
					</h4>
					
				</div>
				<div class="panel-body bg_EF">
					<div class="row">
						<div class="col-md-4 col-xs-12 col-sm-4">
							<table class="table table-bordered tableDiv bg_ff" id="useDetailsTableId"></table>
						</div>
						<div class="col-md-4 col-xs-12 col-sm-4">
						 <div id="map" style="width: 700px; height: 500px;"></div>
						 
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div id="voterTableDivId"></div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div class="panel-heading bg_cc">
							<h4 class="panel-title">USER TRACKING MAP</h4>
							</div>
							<div id="map1" style="width: 100%; height: 500px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>
  <script src="dist/mobileApp/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/newmultiselect/chosen.jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>

 <!--<script src="http://maps.google.com/maps/api/js?sensor=false"></script>-->
<script async defer
        src="https://maps.googleapis.com/maps/api/js?signed_in=true&callback=initMap"></script>
<script type="text/javascript">
$("#chosenselectId").chosen();
var locations = [];
var markersArr = [];
var pathArr = [];
  var markers = new Array();
  var map;
	showMapForMobileAppUserVoter("onload");
	function showMapForMobileAppUserVoter(fromType)
	{	
		var datesArr=[];
		if(fromType=="onload"){
			datesArr.push("${param.surveyDate}");
		}
		if(fromType=="multiSelect"){
			var dates=$("#chosenselectId").val();
			for(var s in dates){
				datesArr.push(dates[s]);
			}
		}
			
		var jsObj={
			userId:"${param.userId}",
			locationId:"${param.divisonId}",
			levelId:"${param.levelIdId}",
			datesArr:datesArr
		}
						
		$.ajax({
		 type: "POST",
		 url: "showMapForMobileAppUserVoterAction.action",
		 data: {task:JSON.stringify(jsObj)},
		}).done(function( result ) {
			if(result!=null && result.length>0){
				locations = [];
				for(var i in result){
					var temp=[];
					var data="<b>voter Name : "+result[i].votername+"<br/>Voter Card No : "+result[i].votercardnum+"<br/>Mobile No : "+result[i].voterMobileNo+"<br/>Ward No : "+result[i].syncSource+"("+result[i].uniqueKey+")<br/>Booth No:"+result[i].boothpartno+"<br/>Rating : "+result[i].rating+"<br/>DATE : "+result[i].surveyDate+"</b>";
					temp.push(data);
					temp.push(result[i].latitude);
					temp.push(result[i].longitude);
					locations.push(temp);
				}
				buildMap(locations,result[i].latitude,result[i].longitude);
				
				var str='';
				str+='<tr><td>USER ID</td><td>'+result[0].currentTabUserId+'</td></tr>';
				str+='<tr><td>USERNAME</td><td>'+result[0].name+'</td></tr>';
				if(result[0].userName != null && result[0].userName.trim().length > 0){
					str+='<tr><td>NAME</td><td>'+result[0].userName+'</td></tr>';
				}
				else{
					str+='<tr><td>NAME</td><td> - </td></tr>';
				}
				str+='<tr><td>MOBILE NO</td><td>'+result[0].mobileNo+'</td></tr>';
				str+='<tr><td>START TIME</td><td>'+result[0].availableDates[0]+'</td></tr>';
				str+='<tr><td>END TIME</td><td>'+result[0].availableDates[result[0].availableDates.length-1]+'</td></tr>';
				//str+='<tr><td>END TIME</td><td>'+result[0].availableDates[0]+'</td></tr>';
				if(result[0].noofSmsSent==null)result[0].noofSmsSent=0;
				str+='<tr><td>NO OF SMS SENT</td><td>'+result[0].noofSmsSent+'</td></tr>';
				if(result[0].mobileNosCount==null)result[0].mobileNosCount=0;
				str+='<tr><td>NO OF MOBILES NO COLLECTED</td><td>'+result[0].mobileNosCount+'</td></tr>';
				
				$("#userHeadingId").html("USER - "+result[0].name);
				$("#useDetailsTableId").html(str);
				
				var str1='';
				str1+='<table class="table table-bordered bg_ff" id="votersDataTable">';
				str1+='<thead class="bg_cc">';
				str1+='<th>VOTER ID</th>';
				str1+='<th>VOTER NAME</th>';
				str1+='<th>MOBILE NO</th>';
				str1+='<th>RATING</th>';
				str1+='<th>LATITUDE LONGITUDE</th>';
				str1+='<th>SURVEY TIME</th>';
				str1+='</thead>';
				str1+='<tbody id="voterTableBodyId">';
				for(var j in result){
					str1+='<tr>';
						str1+='<td>'+result[j].votercardnum+'</td>';
						str1+='<td>'+result[j].votername+'</td>';
						if(result[j].mobileNo==null)result[j].mobileNo="-";
						str1+='<td>'+result[j].voterMobileNo+'</td>';
						str1+='<td>'+result[j].rating+'</td>';
						str1+='<td>'+result[j].latitude+' , '+result[j].longitude+'</td>';
						str1+='<td>'+result[j].surveyDate+'</td>';
					str1+='</tr>';
				}
				str1+='</tbody>';
				str1+='</table>';
				$("#voterTableDivId").html(str1);
				$("#votersDataTable").dataTable();
				$("#votersDataTable").removeClass("dataTable");
				
				var str2='';
				if(result[0].availableDates!=null && result[0].availableDates.length>0){
					for(var k in result[0].availableDates){
						str2+='<option value="'+result[0].availableDates[k]+'">'+result[0].availableDates[k]+'</option>';
					}
				}
				
				$("#chosenselectId").html(str2);
				$("#chosenselectId").chosen();
				$("#chosenselectId").trigger("chosen:updated");
				
				
			}else{
				$("#useDetailsTableId").html("<h4>No Data Available</h4>");
				$("#voterTableDivId").html("<h4>No Data Available</h4>");
			}
			 
		});
	
	}

    function buildMap(locations,latitude,longitude){
		var map='';
		map = new google.maps.Map(document.getElementById('map'), {
		  zoom: 8,
		  center: new google.maps.LatLng(latitude,longitude),
		  mapTypeId: google.maps.MapTypeId.ROADMAP
		});

		var infowindow = new google.maps.InfoWindow();

		var marker, i;

		for (i = 0; i < locations.length; i++) { 
		  marker = new google.maps.Marker({
			position: new google.maps.LatLng(locations[i][1], locations[i][2]),
			map: map
		  });

		  google.maps.event.addListener(marker, 'click', (function(marker, i) {
			return function() {
			  infowindow.setContent(locations[i][0]);
			  infowindow.open(map, marker);
			}
		  })(marker, i));
		}
	}
	
	$("#datesMultiSelectId").click(function(){
		showMapForMobileAppUserVoter("multiSelect");
	});
	
	getUserTrackingDetails();
	function getUserTrackingDetails(){
		var jsObj={
			userId:"${param.userId}"
		}
		
		$.ajax({
		 type: "POST",
		 url: "getUserTrackingDetailsAction.action",
		 data: {task:JSON.stringify(jsObj)},
		}).done(function( result ) {
			markersArr = [];
			pathArr = [];
			if(result != null && result.length > 0){
				for(var i in result){
					var obj={lat:parseFloat(result[i].latitude), lng:parseFloat(result[i].longitude)};
					pathArr.push(obj);
					var temparr=[];
					temparr.push(result[i].surveyDate);
					temparr.push(result[i].latitude);
					temparr.push(result[i].longitude);
					markersArr.push(temparr);
				}
			}
			buildUserTrackingMap(markersArr,pathArr);
		});
	}
	
	function buildUserTrackingMap(markersArray,pathArray){
		//console.log(markersArray);
		//console.log(pathArray);
		var infoWindow;
		var map1 = new google.maps.Map(document.getElementById('map1'), {
			zoom: 8,
			//center: {lat: 17.3700, lng: 78.4800},
			center: {lat: pathArray[0].lat, lng:pathArray[0].lng},
			//center: {lat: 15.478569, lng:78.483093},//Nadyal 
			mapTypeId: google.maps.MapTypeId.TERRAIN
		});
		
		var flightPath = new google.maps.Polyline({
			path: pathArray,
			geodesic: true,
			strokeColor: '#FF0000',
			strokeOpacity: 1.0,
			strokeWeight: 2
		});

		  
			var infowindow = new google.maps.InfoWindow();
			var marker,i;
			for (i = 0; i < markersArray.length; i++) {
				marker = new google.maps.Marker({
					position: new google.maps.LatLng(markersArray[i][1], markersArray[i][2]),
					map: map1
				});
				  
				google.maps.event.addListener(marker, 'click', (function(marker, i) {
					return function() {
						infowindow.setContent(markersArray[i][0]);
						infowindow.open(map1, marker);
					}
				})(marker, i));
			}
			
			flightPath.setMap(map1);
	}
	
	function initMap() {
  var map1 = new google.maps.Map(document.getElementById('map1'), {
    zoom: 10,
    center: {lat: 17.3700, lng: 78.4800},
    mapTypeId: google.maps.MapTypeId.TERRAIN
  });


	//array to build line
  var flightPlanCoordinates = [];
  
  //array to build markers
  var locations = [];
  

  var flightPath = new google.maps.Polyline({
    path: flightPlanCoordinates,
    geodesic: true,
    strokeColor: '#FF0000',
    strokeOpacity: 1.0,
    strokeWeight: 2
  });

  
	var infowindow = new google.maps.InfoWindow();
	var marker,i;
	for (i = 0; i < locations.length; i++) {
		marker = new google.maps.Marker({
			position: new google.maps.LatLng(locations[i][1], locations[i][2]),
			map: map1
		});
		  
		google.maps.event.addListener(marker, 'click', (function(marker, i) {
			return function() {
				infowindow.setContent(locations[i][0]);
				infowindow.open(map1, marker);
			}
		})(marker, i));
	}
	
	flightPath.setMap(map1);
}
</script>
</body>
</html>
