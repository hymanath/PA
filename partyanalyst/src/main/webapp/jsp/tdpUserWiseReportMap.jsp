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
							<h4 class="panel-title">
							<span id="usernameHeading"></span>-FIELD MONITORING USER <label style="margin-left: 20px;">SELECT USER</label>
							 <select class="" id="mapId" onchange="showMapDetails(this.value);"> 
                    	    <option value="0">All</option>
                             </select>&nbsp&nbsp<input type="checkbox" name="checkbox" value="2" id="checkboxId" style="margin-left: 10px;"><label style="margin-left: 10px;">WITH ROUTE &nbsp<img src="images/sample_rout.png" style="width: 20px; height: 20px;"/></label>&nbsp&nbsp<input type="checkbox" name="timeCheckbox" id="timeCheckboxId"><label style="margin-left: 10px;">WITH ROUTE FOR EVERY 10 MINS &nbsp<img src="images/user_rout.png" style="width: 20px; height: 20px;"/></label>
							 </h4>
							 <h5><span id="userTrackingId"></span>-USER TRACKING
							 <label style="margin-left: 110px;">FROM HOUR</label>
							 <select id="fromTimeId"> 
								<option value="0">Select Hour</option><option value="1">1</option><option value="2">2</option><option value="3">3</option>
								<option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option>
								<option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option>
								<option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option>
								<option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option>
								<option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option>
								<option value="24">24</option>
							 </select>
							 <label style="margin-left: 30px;">TO HOUR</label>
							 <select id="toTimeId"> 
								<option value="0">Select Hour</option><option value="1">1</option><option value="2">2</option><option value="3">3</option>
								<option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option>
								<option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option>
								<option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option>
								<option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option>
								<option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option>
								<option value="24">24</option>
							 </select>
							 <button class="btn btn-xs btn-info" style="margin-left: 30px;" onclick="getMapDetailsId();">Get Map</button>
							 </h5>
							 <div class="text-danger" id="errorDivId"></div>
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
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD_ELXOA5iHgPThVcenSQjMwkev64EcZbE&callback=buildUserTrackingMap"
    async defer></script>
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
	//inputs to build path
  var flightPlanCoordinates = [];

$(document).ready(function(){
	$("#fromTimeId").val(fromHour);
	$("#toTimeId").val(toHour);
});


  
  getUserTrackingDetails();
  
  
  function generateAddress(result,searchTypeId,i,type)
  {
	//  console.log(latitude+"  :  "+longitude);
		var request = new XMLHttpRequest();
	
        var method = 'GET';
        var url = 'http://maps.googleapis.com/maps/api/geocode/json?latlng='+result.latitude+','+result.longititude+'&sensor=true';
        var async = true;
		
        request.open(method, url, async);
        request.onreadystatechange = function(){
          if(request.readyState == 4 && request.status == 200){
            var data = JSON.parse(request.responseText);
            var addressData = data.results[0];
			
			
				var temparr=[];	
				var obj={lat:parseFloat(result.latitude), lng:parseFloat(result.longititude)};
				if(withTimeRoute == "true" || withRoute == "true")
				{
					pathArr.push(obj);
				}else{
					pathArr.push();
				}
				var userId = "${param.userId}";
				if(userId == null || userId == ''){
					userId =$('#mapId').val();
				}	
					
				if(searchTypeId==0){
					$('#checkboxId').prop("disabled", true);
					
					if(addressData != "undefined" && addressData !== undefined){
						var addressStr = addressData.formatted_address;
						if(userId == 0)
							temparr.push(" <b>Tab User Name</b> : "+result.tdpCadreName+"<br/> <b>Mobile No </b> : "+result.tdpCadreMbl+"<br/> <b>Survey Time </b> : "+result.surveyTime+" </br> <b>Location :</b>"+addressStr+"");	
						else
							temparr.push("<b>S.No  </b> : "+(parseInt(i)+1)+"<br/> <b> Cadre Name </b> : "+result.subList2[i].tdpCadreName+"<br/> <b> Cadre Mobile No </b> :"+result.subList2[i].tdpCadreMbl+"<br/> <b>Survey Time </b> : "+result.subList2[i].surveyTime+" </br> <b>Location :</b>"+addressStr+"");
					}
					else{
						if(userId == 0)							
							temparr.push("<b>Tab User Name</b> : "+result.tdpCadreName+"<br/> <b>Mobile No </b> : "+result.tdpCadreMbl+"<br/> <b>Survey Time </b> : "+result.surveyTime);	
						else
							temparr.push("<b>S.No  </b> : "+(parseInt(i)+1)+"<br/> <b>Voter Name</b> : "+result.tdpCadreName+"<br/> <b>Voter Mobile No </b> : "+result.tdpCadreMbl+"<br/> <b>Survey Time </b> : "+result.surveyTime+" ");
					}
				}	
				else{
					if($("#timeCheckboxId").is(':checked')){
						if(addressData != "undefined" && addressData !== undefined){
						var addressStr = addressData.formatted_address;
						if(userId == 0)
							temparr.push("<b>Survey Time </b> : "+result.surveyTime+" </br> <b>Location :</b>"+addressStr+"");	
						else
							if(result.tdpCadreName != null && result.tdpCadreMbl != null)
								temparr.push("<b>S.No  </b> : "+(parseInt(i)+1)+"<br/> <b>Cadre Name</b> : "+result.tdpCadreName+"<br/> <b>Cadre Mobile No </b> : "+result.tdpCadreMbl+"<br/> <b>Survey Time </b> : "+result.surveyTime+" </br> <b>Location :</b>"+addressStr+"");
							else
								temparr.push("<b>S.No  </b> : "+(parseInt(i)+1)+"<br/> <b>Survey Time </b> : "+result.surveyTime+" </br> <b>Location :</b>"+addressStr+"");
						}else{
							if(userId == 0)
								temparr.push("<b>Survey Time </b> : "+result.surveyTime);
							else
								if(result.tdpCadreName != null && result.tdpCadreMbl != null)
									temparr.push("<b>S.No  </b> : "+(parseInt(i)+1)+"<br/> <b>Cadre Name</b> : "+result.tdpCadreName+"<br/> <b>Cadre Mobile No </b> : "+result.tdpCadreMbl+"<br/> <b>Survey Time </b> : "+result.surveyTime+" ");
								else
									temparr.push("<b>S.No  </b> : "+(parseInt(i)+1)+"<br/> <b>Survey Time </b> : "+result.surveyTime+" ");
						}
					}
					else{
						if(addressData != "undefined" && addressData !== undefined){
						var addressStr = addressData.formatted_address;
						if(userId == 0)
							temparr.push("<b>Tab User Name</b> : "+result.tdpCadreName+"<br/> <b>Mobile No </b> : "+result.tdpCadreMbl+"<br/> <b>Survey Time </b> : "+result.surveyTime+" </br> <b>Location :</b>"+addressStr+"");	
						else
							temparr.push("<b>Tab User Name</b> : "+result.tdpCadreName+"<br/> <b>Mobile No </b> : "+result.tdpCadreMbl+"<br/> <b>S.No  </b> : "+(parseInt(i)+1)+"<br/>  <b>Survey Time </b> : "+result.surveyTime+" </br> <b>Location :</b>"+addressStr+"");
						}else{
							if(userId == 0)
								temparr.push("<b>Survey Time </b> : "+result.surveyTime);
							else
								temparr.push("<b>S.No  </b> : "+(parseInt(i)+1)+"<br/> <b>Cadre Name</b> : "+result.tdpCadreName+"<br/> <b>Cadre Mobile No </b> : "+result.tdpCadreMbl+"<br/> <b>Survey Time </b> : "+result.surveyTime+" ");							
						}
					}
				}
				temparr.push(result.latitude);
				temparr.push(result.longititude);
				temparr.push(result.surveyTime);
				temparr.push(type);
				console.log(type);
				markersArr.push(temparr);
				flightPlanCoordinates.push(temparr);
				
          }
        };
        request.send();
	}

	function getUserTrackingDetails(){
		var jsObj={
			constId : "${param.constistuencyId}",
			fieldUserId : "${param.fieldUserId}",
			surveyUserId:"${param.userId}",
			fromDate:"",
			toDate:"",
			fromTime : "${param.fromHour}",
			toTime : "${param.toHour}"
		}
		$('#map1').html('<div id=""><img class="ajaxImgStyle" src="images/icons/search.gif"/></div>');
		$.ajax({
          type:'GET',
          url: 'getUserTrackingDtslBySurveyUserIdAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
			markersArr = [];
			flightPlanCoordinates = [];
			pathArr = [];
			var searchTypeId = $('#mapId').val();
			if(result != null){
				$('#map1').html('');
				if($("#timeCheckboxId").is(':checked')){
					if(result.subList2 != null && result.subList2.length > 0){
						for(var i in result.subList2){
							var obj={lat:parseFloat(result.subList2[i].latitude), lng:parseFloat(result.subList2[i].longititude)};
							if(withTimeRoute == "true"){
								pathArr.push(obj);
							}else{
								pathArr.push();
							}
							//pathArr.push(obj);
							if(i == 0){
								glblLat = parseFloat(result.subList2[i].latitude).toFixed(2);
								glblLon = parseFloat(result.subList2[i].longititude).toFixed(2);
							}
							 generateAddress(result.subList2[i],searchTypeId,i,"rout");
							 /*
							var temparr=[];
							if(searchTypeId==0){								
								$('#checkboxId').prop("disabled", true);
								temparr.push("<b>Survey Time </b> : "+result.subList2[i].surveyTime);
								
							}	
							else {
								temparr.push("<b>S.No  </b> : "+(parseInt(i)+1)+"<br/><b>Survey Time </b> : "+result.subList2[i].surveyTime);
							}
							temparr.push(result.subList2[i].latitude);
							temparr.push(result.subList2[i].longititude);
							temparr.push(result.subList2[i].surveyTime);
							temparr.push("route");
							markersArr.push(temparr);
							flightPlanCoordinates.push(temparr);
							*/
						}
					}
				}
				//if($("#checkboxId").is(':checked')){
					if(result.subList1 != null && result.subList1.length > 0){
						for(var i in result.subList1){
							var obj={lat:parseFloat(result.subList1[i].latitude), lng:parseFloat(result.subList1[i].longititude)};
							if(withRoute == "true"){
								pathArr.push(obj);
							}else{
								pathArr.push();
							}
							;
							if(i == 0){
								glblLat = parseFloat(result.subList1[i].latitude).toFixed(2);
								glblLon = parseFloat(result.subList1[i].longititude).toFixed(2)
							}
							 generateAddress(result.subList1[i],searchTypeId,i,"sample");
							 
							 /*
							var temparr=[];
							if(searchTypeId==0){
								$('#checkboxId').prop("disabled", true);
								temparr.push("<b>Tab User Name</b> : "+result.subList1[i].tdpCadreName+"<br/> <b>Mobile No </b> : "+result.subList1[i].tdpCadreMbl+"<br/> <b>Survey Time </b> : "+result.subList1[i].surveyTime);
								
							}	
							else {
								temparr.push("<b>S.No  </b> : "+(parseInt(i)+1)+"<br/> <b> Cadre Name </b> : "+result.subList1[i].tdpCadreName+"<br/> <b> Cadre Mobile No </b> :"+result.subList1[i].tdpCadreMbl+"<br/> <b>Survey Time </b> : "+result.subList1[i].surveyTime);
							}
							temparr.push(result.subList1[i].latitude);
							temparr.push(result.subList1[i].longititude);
							temparr.push(result.subList1[i].surveyTime);
							temparr.push("sample");
							markersArr.push(temparr);
							flightPlanCoordinates.push(temparr);
							*/
						}
					}
				//}
				
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
					//alert('No Registration staff are available in this Constituency.Please try later...');
				}
				if($("#timeCheckboxId").is(':checked') && $("#checkboxId").is(':checked')){
					setTimeout(function(){
						buildUserTrackingMap(markersArr,pathArr);
					},10000);
				}
				else{
					setTimeout(function(){
						buildUserTrackingMap(markersArr,pathArr);
					},5000);
				}
				
			//initMap(markersArr,flightPlanCoordinates);
		});
	}

function buildUserTrackingMap(markersArr,flightPlanCoordinates) {

		glblLat = parseFloat(glblLat+"00");
		glblLon = parseFloat(glblLon+"00");
		//console.log(glblLat);
		//console.log(glblLon);
		
		  var map1 = new google.maps.Map(document.getElementById('map1'), {
			zoom: 12,
			center: {lat: glblLat, lng: glblLon},
			mapTypeId: google.maps.MapTypeId.TERRAIN
		  });
	  
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
		var pinColor = "AE6558";
		for (i = 0; i < locations.length; i++) {
			if(locations[i][4] == 'sample'){
				pinColor = "A6BAFF";
			}
			
			
		var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
        new google.maps.Size(21, 34),
        new google.maps.Point(0,0),
        new google.maps.Point(10, 34));
		marker = new google.maps.Marker({
				icon: pinImage,
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

/*
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
}*/ 
	 
getFieldMonitoringMapReportDetails();
function getFieldMonitoringMapReportDetails(){
	var userName = "${param.username}";
	var constitunecyId = "${param.constistuencyId}";
	var fieldUserId ="${param.fieldUserId}";

	var jsObj ={
		constitunecyId : "${param.constistuencyId}",
		fieldUserId : "${param.fieldUserId}"
	}
	$.ajax({
			type : 'GET',
			url : 'getFieldMonitoringReportMapDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			if(result != null && result.length > 0){
				for(var i in result){
					if("${param.userId}" == ""){
						var urlStr = "tdpUserWiseReportMapAction.action?fieldUserId="+fieldUserId+"&username="+userName+"&constistuencyId="+constitunecyId+"&userId="+result[i].id+"&cadreName="+result[i].cadreName+"&withRoute="+withRoute+"&withTimeRoute="+withTimeRoute+"&fromHour="+fromHour+"&toHour="+toHour;
						var browser2 = window.open(urlStr,"Survey Map","scrollbars=yes,height=650,width=1100,left=150,top=100");
						$("#userTrackingId").text("${param.cadreName}");
					}
				$("#mapId").append('<option value='+result[i].id+'>'+result[i].cadreName+'</option>');
				}
				$("#mapId").val("${param.userId}");
				$("#userTrackingId").text("${param.cadreName}");
			}
				
		});
}
var withRoute = "${param.withRoute}";
var withTimeRoute = "${param.withTimeRoute}";
var fromHour = "${param.fromHour}";
var toHour = "${param.toHour}";
function showMapDetails(value){
	var withRoute = false;
	var cadreUserId=$( "#mapId option:selected" ).text();
	var userName = "${param.username}";
	var constitunecyId = "${param.constistuencyId}";
	var fieldUserId ="${param.fieldUserId}";
	var urlStr = "tdpUserWiseReportMapAction.action?fieldUserId="+fieldUserId+"&username="+userName+"&constistuencyId="+constitunecyId+"&userId="+value+"&cadreName="+cadreUserId+"&withRoute="+withRoute+"&withTimeRoute="+withTimeRoute+"&fromHour="+fromHour+"&toHour="+toHour;
	var browser2 = window.open(urlStr,"Survey Map","scrollbars=yes,height=650,width=1100,left=150,top=100");
	$("#userTrackingId").text("${param.userId}");
}

function getMapDetailsId(){
	$("#errorDivId").html("");
	if ($("#timeCheckboxId").is(':checked')){
		withRoute = false;
		withTimeRoute = true;
		fromHour = $("#fromTimeId").val();
		toHour = $("#toTimeId").val();
		var cadreUserId=$( "#mapId option:selected" ).text();
		var userName = "${param.username}";
		var constitunecyId = "${param.constistuencyId}";
		var fieldUserId ="${param.fieldUserId}";
		var value ="${param.userId}";
		var urlStr = "tdpUserWiseReportMapAction.action?fieldUserId="+fieldUserId+"&username="+userName+"&constistuencyId="+constitunecyId+"&userId="+value+"&cadreName="+cadreUserId+"&withRoute="+withRoute+"&withTimeRoute="+withTimeRoute+"&fromHour="+fromHour+"&toHour="+toHour;
		var browser2 = window.open(urlStr,"Survey Map","scrollbars=yes,height=650,width=1100,left=150,top=100");
		$("#userTrackingId").text("${param.userId}");
	}
	else{
		$("#errorDivId").html("Please check WITH ROUTE FOR EVERY 5 MINS");
	}
}

 $('#checkboxId').click(function() {
  if ($(this).is(':checked')) {
	  $(this).prop('checked', true);
	 withRoute = true;
	var cadreUserId=$( "#mapId option:selected" ).text();
	var userName = "${param.username}";
	var constitunecyId = "${param.constistuencyId}";
	var fieldUserId ="${param.fieldUserId}";
	var value ="${param.userId}";
	var urlStr = "tdpUserWiseReportMapAction.action?fieldUserId="+fieldUserId+"&username="+userName+"&constistuencyId="+constitunecyId+"&userId="+value+"&cadreName="+cadreUserId+"&withRoute="+withRoute+"&withTimeRoute="+withTimeRoute+"&fromHour="+fromHour+"&toHour="+toHour;
	var browser2 = window.open(urlStr,"Survey Map","scrollbars=yes,height=650,width=1100,left=150,top=100");
 
  } else {
	  withRoute = false;
	  $(this).prop('checked', false);
	var cadreUserId=$( "#mapId option:selected" ).text();
	var userName = "${param.username}";
	var constitunecyId = "${param.constistuencyId}";
	var fieldUserId ="${param.fieldUserId}";
	var value ="${param.userId}";
	var urlStr = "tdpUserWiseReportMapAction.action?fieldUserId="+fieldUserId+"&username="+userName+"&constistuencyId="+constitunecyId+"&userId="+value+"&cadreName="+cadreUserId+"&withRoute="+withRoute+"&withTimeRoute="+withTimeRoute+"&fromHour="+fromHour+"&toHour="+toHour;
	var browser2 = window.open(urlStr,"Survey Map","scrollbars=yes,height=650,width=1100,left=150,top=100");
  }
});
if(withRoute == "true")
{
	$('#checkboxId').prop('checked', true);
}else{
		$('#checkboxId').prop('checked', false);
	}
	
$('#timeCheckboxId').click(function() {
  if ($(this).is(':checked')) {
	  $(this).prop('checked', true);
	 withTimeRoute = true;
	// withRoute = $("#checkboxId").is(':checked');
	var cadreUserId=$( "#mapId option:selected" ).text();
	var userName = "${param.username}";
	var constitunecyId = "${param.constistuencyId}";
	var fieldUserId ="${param.fieldUserId}";
	var value ="${param.userId}";
	var urlStr = "tdpUserWiseReportMapAction.action?fieldUserId="+fieldUserId+"&username="+userName+"&constistuencyId="+constitunecyId+"&userId="+value+"&cadreName="+cadreUserId+"&withRoute="+withRoute+"&withTimeRoute="+withTimeRoute+"&fromHour="+fromHour+"&toHour="+toHour;
	var browser2 = window.open(urlStr,"Survey Map","scrollbars=yes,height=650,width=1100,left=150,top=100");
 
  } else {
	  withTimeRoute = false;
	  $(this).prop('checked', false);
	 // withRoute = $("#checkboxId").is(':checked');
	var cadreUserId=$( "#mapId option:selected" ).text();
	var userName = "${param.username}";
	var constitunecyId = "${param.constistuencyId}";
	var fieldUserId ="${param.fieldUserId}";
	var value ="${param.userId}";
	var urlStr = "tdpUserWiseReportMapAction.action?fieldUserId="+fieldUserId+"&username="+userName+"&constistuencyId="+constitunecyId+"&userId="+value+"&cadreName="+cadreUserId+"&withRoute="+withRoute+"&withTimeRoute="+withTimeRoute+"&fromHour="+fromHour+"&toHour="+toHour;
	var browser2 = window.open(urlStr,"Survey Map","scrollbars=yes,height=650,width=1100,left=150,top=100");
  }
});
if(withTimeRoute == "true")
{
	$('#timeCheckboxId').prop('checked', true);
}else{
		$('#timeCheckboxId').prop('checked', false);
	}

</script>
</body>
</html>