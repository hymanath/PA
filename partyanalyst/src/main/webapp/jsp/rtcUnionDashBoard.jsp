<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rtc Union DashBoard</title>
</head>

<link href="css/bootstrap.min.css" rel="stylesheet"/>	
<!-- Custom Styles-->
	<link rel="stylesheet" type="text/css" href="css/style.css"> 
<body>

<!-- Title Row -->
		<div class="row-fluid" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-10 " style="background:#ffffff;">
				<h3 class="text-center text-uppercase">RTC UNION DASHBOARD</h3>
			</div>
		</div><!-- Title Row End-->

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script>

getRtcUnionRegisteredBasicDetails();
getRtcUnionAllLocationDetails();
getRtcUnionZoneWiseDetails();
function getRtcUnionRegisteredBasicDetails(){
	var jObj={
		task : "basicDetails"
	};
	$.ajax({
		type:"POST",
		url:"getRtcUnionRegisteredBasicDetailsAction.action",
		dataType: 'json',
		data:{task:JSON.stringify(jObj)}	
	}).done(function(result) {
		
	});
	
}
function getRtcUnionZoneWiseDetails(){
	var jObj={
		task : "zoneDetails"
	};
	$.ajax({
		type:"POST",
		url:"getRtcUnionRegisteredBasicDetailsAction.action",
		dataType: 'json',
		data:{task:JSON.stringify(jObj)}	
	}).done(function(result) {
		
	});
	
}
function getRtcUnionAllLocationDetails(){
	
	$.ajax({
		type:"POST",
		url:"getRtcUnionAllLocationDetailsAction.action",
		dataType: 'json',
		data:{}	
	}).done(function(result) {
		
	});
	
}

</script>
</body>
</html>