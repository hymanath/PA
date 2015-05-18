<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Committee Dashboard</title>
	<!-- Bootstrap -->
    <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet"/>
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/css/style.css" rel="stylesheet"/>
    	<!--Bootstrap DatePicker-->
    <link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" />
		<!--Hover Menu-->
    <link href="css/cadreCommitee/css/jquery.smartmenus.bootstrap.css" rel="stylesheet" />
    	<!--Circle-->
    <link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
		
</head>
<body>
	
	<div class="container">
    	<div class="row" style="text-align:center;">
                <div class="col-md-6 col-md-offset-3 col-xs-8 col-xs-offset-2 col-sm-6 col-sm-offset-3 ">
                    <h3 class="panel-header">CADRE PERFORMANCE</h3>
                    <hr style="border-color:#F00;margin:0px 0px 10px 0px;" />
                </div>
        </div>
		<div class="row" style="text-align:center;">
			<div id="cadrePerfId"><img style="width:80px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/></div>
		</div>
	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src=" http://code.jquery.com/ui/1.11.1/jqueryui/1.11.1/jquery-ui.js "></script>
	<script src=" http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<!--Circle js file-->
	
    <script src="js/cadreCommittee/dist/js/jquery.circliful.min.js"></script>
    <!--Bootstrap Date Picker-->
   <script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>   
    <!--Jquery Sparkline-->
    <script src="js/cadreCommittee/js/jquery.sparkline.js" type="text/javascript"></script>
    <!-- Custom JS File-->
    <script src="js/cadreCommittee/js/custom.js" type="text/javascript"></script>
    <!--Hover Menu-->
    <script src="js/cadreCommittee/js/jquery.smartmenus.min.js" type="text/javascript"></script>
    <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>
	 <script src="js/jquery.classyloader.min.js"></script>
    <script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<script>
		var locationId = "${distId}";
		var locationTypeId = 11;
		gettingCadreDetailsPerformance(locationTypeId,locationId);
		function gettingCadreDetailsPerformance(lctnTypeId,lctnId){
			var jsObj={
					 locationId:lctnId,locationTypeId:lctnTypeId
				   };
				   
			 $.ajax({
				type : "GET",
				url : "gettingPerformanceOfCadreAction.action",
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				console.log(result);
				buildCadrePerformanceDetails(result);
			});
		}
		
		function buildCadrePerformanceDetails(result){
			var str = "";
			$("#cadrePerfId").html('<img style="width:80px;height:50px;"  src="./images/Loading-data.gif" alt="Processing Image"/>');
			if(result!=null){
				str+="<table class='table table-bordered'>";
					str+="<thead>";
					str+="<tr>";
						str+="<th>NAME</th>";
						str+="<th>VOTER CARD NO</th>";
						str+="<th>CONSTITUENCY</th>";
						str+="<th>COMMITTEE TYPE</th>";
						str+="<th>ROLE</th>";
						str+="<th>CASTE CATEGORY</th>";
						str+="<th>CASTE</th>";
						str+="<th>MOBILE</th>";
						str+="<th>OWN BOOTH NO</th>";
						str+="<th>OWN CONSTITUENCY %</th>";
						str+="<th>OWN MANDAL %</th>";
						str+="<th>OWN MUNCIPALITY %</th>";
						str+="<th>OWN PANCHAYAT %</th>";
						str+="<th>OWN BOOTH %</th>";
						str+="<th>OWN WARD %</th>";
					str+="</tr>";
					str+="</thead>";
					str+="<tbody>";
						for(var i in result){
							str+="<tr>";
								str+="<td>"+result[i].name+"</td>";
								str+="<td>"+result[i].voterCardNo+"</td>";
								str+="<td>"+result[i].constituencyName+"</td>";
								str+="<td>"+result[i].committe+"</td>";
								str+="<td>"+result[i].role+"</td>";
								str+="<td>"+result[i].casteGroupName+"</td>";
								str+="<td>"+result[i].casteName+"</td>";
								str+="<td>"+result[i].mobileNo+"</td>";
								str+="<td>"+result[i].partNo+"</td>";
								
								if(result[i].ownConstiPerc!=null){
									str+="<td>"+result[i].ownConstiPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}
								
								
								if(result[i].ownMandalPerc!=null){
									str+="<td>"+result[i].ownMandalPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}
								
								if(result[i].ownMunciPerc!=null){
									str+="<td>"+result[i].ownMunciPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}
								
								if(result[i].ownPanchPerc!=null){
									str+="<td>"+result[i].ownPanchPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}
								
								if(result[i].ownBoothPerc!=null){
									str+="<td>"+result[i].ownBoothPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}
								
								if(result[i].ownWardPerc!=null){
									str+="<td>"+result[i].ownWardPerc+"</td>";
								}else{
									str+="<td> - </td>";
								}
								
							str+="</tr>";
						}
					str+="<tbody>";
				str+="</table>";
			}else{
				str+="<h4>No Data Available</h4>";
			}
			
			$("#cadrePerfId").html(str);
		}
	</script>
	
	
</body>
</html>
