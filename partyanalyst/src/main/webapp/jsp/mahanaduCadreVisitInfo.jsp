<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MAHANADU VISITORS INFO</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
 <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	 <script src="js/cadreCommittee/js/jquery.smartmenus.min.js" type="text/javascript"></script>
    <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>
	 <script src="js/jquery.classyloader.min.js"></script>
<style>
  #userNameId,#imeiId{
     height: 30px;
     width: 220px;
}
</style>
</head>
<body>
    <div class="container m_top10">
      <div id="locationWiseCadreInfoDiv">
		   <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase">MAHANADU VISITORS INFO</h3>
				</div>
			</div>
	  </div>
	   <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
			<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;min-height:200px;">
				<div>
				     <div><input style="display:none;" type="button" onclick="populate();" value="Update Latest Info"/></div>
				     <table id="mainInfo"  style="margin-left: 450px;margin-top: 15px;display:none;">
				       <tr>
					      <td><b>Total Visitors :  &nbsp;<span id="totalVisitors" /></b></td>
						  <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					      <td><b>Current Visitors :  &nbsp;<span id="currentVisitors" /></b></td>
					  </tr>
					</table>
					<div id="resultTableDiv" style="margin-top:15px;background-color: #fff;"><div  style="text-align: center;"><img style="width: 70px; height: 60px;" src="images/Loading-data.gif" /></div></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	  $(".dropdown-menu").html('<li><a href="eventDashboardAction.action?eventId=1">EVENTS DASHBOARD</a></li><li><a href="dashBoardAction.action">DASHBOARD</a></li><li><a href="newlogoutAction.action">LOGOUT</a></li>');
	  function getDetails(){

		  $.ajax({
			  type:'GET',
			  url: 'getTodayTotalAndCurrentUsersInfo.action'
          }).done(function(result){
		       var str ="";
	           if(result != null){
				   $("#mainInfo").show();
	        	   $("#totalVisitors").html(result.totalVisitors);
	        	   $("#currentVisitors").html(result.currentVisitors);
			     str+="<table class='table table-bordered m_top20 '>";
				  str+=" <tr>";
				  if(result.lastUpdated != null){
				    str+="   <th colspan='2'><b>Last Updated On : "+result.lastUpdated+"</b></th>";
				  }else{
					 str+="   <th colspan='2'><b>Last Updated On : N/A</b></th>"; 
				  }
				  str+=" </tr>";
				  str+=" <tr>";
				  str+="   <th>STATUS</th>";
				  str+="   <th>COUNT</th>";
				  str+=" </tr>";
				  str+="<tr>";
				  str+="   <td>Above 8 Hours</td>";
				  if(result.above8hrs != null){
				    str+="   <td>"+result.above8hrs+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>7 to 8 Hours</td>";
				  if(result.seventoeight != null){
				    str+="   <td>"+result.seventoeight+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>6 to 7 Hours</td>";
				  if(result.sixtoseven != null){
				    str+="   <td>"+result.sixtoseven+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>5 to 6 Hours</td>";
				  if(result.fivetosix != null){
				    str+="   <td>"+result.fivetosix+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>4 to 5 Hours</td>";
				  if(result.fourtofive != null){
				    str+="   <td>"+result.fourtofive+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>3 to 4 Hours</td>";
				  if(result.threetofour != null){
				    str+="   <td>"+result.threetofour+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>2 to 3 Hours</td>";
				  if(result.twotothree != null){
				    str+="   <td>"+result.twotothree+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>1 to 2 Hours</td>";
				  if(result.onetotwo != null){
				     str+="   <td>"+result.onetotwo+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>Half An Hour To One Hours</td>";
				  if(result.halfanhour != null){
				     str+="   <td>"+result.halfanhour+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>Below Half An Hour</td>";
				  if(result.belowhalfanhour != null){
				     str+="   <td>"+result.belowhalfanhour+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				 
				 str+="</table>";
			   }
			   $("#resultTableDiv").html(str);
	      });
	  }
	  getDetails();
	  function populate(){
		  $.ajax({
			  type:'GET',
			  url: 'populateMahanaduLatestInfoAction.action'
          }).done(function(result){
			  
		  });
	  }
	</script>
</body>
</html>