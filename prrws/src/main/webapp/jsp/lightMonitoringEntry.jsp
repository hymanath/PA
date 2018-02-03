<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LED Entry DashBoard</title>
<!--<link href="Assests/MaterialKit/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="Assests/MaterialKit/css/material-kit.css" rel="stylesheet"/>
<link href="Assests/MaterialKit/css/landingPage.css" rel="stylesheet"/>-->
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="Assests/css/style.css" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
</head>
<body>







<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<!--<script src="Assests/MaterialKit/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/MaterialKit/js/bootstrap.min.js" type="text/javascript"></script>
<script src="Assests/MaterialKit/js/material.min.js"></script>-->
<script src="Assests/Plugins/dragAndDrop/Sortable.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script>
//getTodayVendorDetails();
function getTodayVendorDetails(){
	
	var json = {
		
	}
	$.ajax({                
		type:'POST',    
		url: 'getTodayVendorDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		
	});	
}
</script>
</body>
</html>