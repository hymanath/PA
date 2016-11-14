<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CADRE REGISTRATION VOTER TRACKING</title>
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
.eventsheader , .footerCls,.line_heightDiv
{
	display:none;
}

</style>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default m_top10 chosen-choices">
				<!--<div class="panel-heading bg_cc">
					<h4 class="panel-title">
						<span id="userHeadingId"></span>
						<div class="pull-right" style="margin-top:-8px">
						<button type="button" class="btn btn-info" id="datesMultiSelectId">GO</button>
						</div>
						<div class="pull-right col-md-3" style="margin-top:-6px">
							<select  class="form-control " multiple id="chosenselectId"></select>
							
						</div>
					</h4>
					
				</div>-->
				<div class="panel-body bg_EF">
					<!--<div class="row">
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
					</div>-->
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div class="panel-heading bg_cc">
							<h4 class="panel-title"><span id="usernameHeading"></span> -USER TRACKING MAP</h4>
							</div>
							<div id="map1" style="width: 100%; height: 800px;"></div>
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
<script async src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCZ4cuu1YVAs0qkYQdXpSpy2XU0MeuRpt8&libraries=geometry"></script>
<script type="text/javascript">
var cadreSurveyUserName = '${param.username}';
$("#usernameHeading").html(cadreSurveyUserName);
var locations = [];
var markersArr = [];
var pathArr = [];
  var markers = new Array();
  var map;
var glblLat = 17.3700;
var glblLon = 78.4800;
  
  getUserTrackingDetails();
	function getUserTrackingDetails(){
		var jsObj={
			surveyUserId:"${param.userId}",
			fromDate:"",
			toDate:""
		}
		$('#map1').html('<div id=""><img class="ajaxImgStyle" src="images/icons/search.gif"/></div>');
		$.ajax({
          type:'GET',
          url: 'getUserTrackingDtslBySurveyUserIdAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
			markersArr = [];
			pathArr = [];
			if(result != null){
				$('#map1').html('');
				if(result.subList1 != null && result.subList1.length > 0){
					for(var i in result.subList1){
						//var obj={lat:parseFloat(result.subList1[i].latitude), lng:parseFloat(result.subList1[i].longititude)};
						//pathArr.push(obj);
						if(i == 0){
							glblLat = parseFloat(result.subList1[i].latitude).toFixed(2);
							glblLon = parseFloat(result.subList1[i].longititude).toFixed(2)
						}
						/*if((result.subList1.length-1)==i){
							displayLocation(result.subList1[i],"last");
						}else{
							displayLocation(result.subList1[i],"");
						}*/
						var temparr=[];
						temparr.push("S.No:"+(parseInt(i)+1)+"<br/>"+result.subList1[i].tdpCadreName+"<br/>"+result.subList1[i].tdpCadreMbl+"<br/>"+result.subList1[i].surveyTime);
						temparr.push(result.subList1[i].latitude);
						temparr.push(result.subList1[i].longititude);
						temparr.push(result.subList1[i].surveyTime);
						markersArr.push(temparr);
						//displayLocation(result.subList1[i].latitude,result.subList1[i].longititude);
					}
				}
				/*for(var i in result.subList1){
					//var obj={lat:parseFloat(result[i].latitude), lng:parseFloat(result[i].longitude)};
					//pathArr.push(obj);
						if((result.length-1)==i){//alert((result.length-1)+" - - "+i);
							displayLocation(result[i],"last");
						}else{
							displayLocation(result[i],"");
						}
					
					/* var temparr=[];
					temparr.push(result[i].name+"<br/>"+result[i].mobileNo);
					temparr.push(result[i].lattitude);
					temparr.push(result[i].longitude);
					markersArr.push(temparr); */
					//displayLocation(result[i].lattitude,result[i].longitude);
				//}
				
			}
			else{
					alert('No Registration staff are available in this Constituency.Please try later...');
				}
			//buildUserTrackingMap(markersArr,pathArr);
			initMap(markersArr);
		});
		
	}
	
	function initMap(markersArr) {
		glblLat = parseFloat(glblLat+"00");
		glblLon = parseFloat(glblLon+"00");
		//console.log(glblLat);
	//	console.log(glblLon);
  var map1 = new google.maps.Map(document.getElementById('map1'), {
    zoom: 12,
    center: {lat: glblLat, lng: glblLon},
    mapTypeId: google.maps.MapTypeId.TERRAIN
  });


	//inputs to build path
  var flightPlanCoordinates = [];
  
  //inputs to build markers
  var locations = markersArr;
  
//logic to build path
  var flightPath = new google.maps.Polyline({
    path: flightPlanCoordinates,
    geodesic: true,
    strokeColor: '#FF0000',
    strokeOpacity: 1.0,
    strokeWeight: 2
  });

//logic to build markers  
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

function displayLocation(result,status){
	
	var temparr=[];
					
					
					
					
        var request = new XMLHttpRequest();

        var method = 'GET';
        var url = 'http://maps.googleapis.com/maps/api/geocode/json?latlng='+result.latitude+','+result.longititude+'&sensor=true';
        var async = true;
		
        request.open(method, url, async);
        request.onreadystatechange = function(){
          if(request.readyState == 4 && request.status == 200){
            var data = JSON.parse(request.responseText);
            var address = data.results[0];
			
			
			if(address != "undefined" && address !== undefined){
				temparr.push("Name : "+result.tdpCadreName+"<br/>Mobile No : "+result.tdpCadreMbl+"<br/> SurveyTime: "+result.surveyTime+"<br/> Location : "+address.formatted_address+"");
			}else{
				temparr.push("Name :"+result.tdpCadreName+"<br/>Mobile No : "+result.tdpCadreMbl+"<br/> SurveyTime: "+result.surveyTime+"<br/>");
			}
			temparr.push(result.latitude);
			temparr.push(result.longititude);
				
          }
        };
        request.send();
		setTimeout(function(){ 
			markersArr.push(temparr);//alert("t");
			if(status == "last"){//alert("s");
				initMap(markersArr);
			}
			
		}, 1000);
		
      };

</script>
</body>
</html>