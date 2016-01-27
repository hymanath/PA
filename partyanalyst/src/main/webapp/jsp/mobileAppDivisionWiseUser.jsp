<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>
<head>

<link href="dist/mobileApp/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/mobileApp/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/mobileApp/Daterange/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">

<body>
<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading bg_cc">
					<h4 class="panel-title">CHERLAPALLI DIVISION REPORT</h4>
				</div>
				<div class="panel-body bg_EF">
					<table class="table table-bordered tableVM bg_ff">
						<tr>
							<td rowspan="3">
								<h3 id="ttlUsrs">150</h3>
								<h5>TOTAL USERS</h5>
							</td>
							<td rowspan="3">
								<h3 id="ttlVtrs">800</h3>
								<h5>TOTAL VOTERS</h5>
							</td>
							<td rowspan="3">
								<h3 id="ttlVtrsCaptrd">100000</h3>
								<h5>TOTAL VOTER ID CAPTURED</h5>
							</td>
							<td rowspan="3">
								<h3 id="ttlMblCaptrd">100000</h3>
								<h5>TOTAL MOBILE NUMBERS COLLECTED</h5>
							</td>
							<td colspan="10">
								<p class="text-center">
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
									RATING
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
									<i class="glyphicon glyphicon-star"></i>
								</p>
							</td>
						</tr>
						<tr>
							<td>None</td>
							<td>01</td>
							<td>02</td>
							<td>03</td>
							<td>04</td>
							<td>05</td>
						</tr>
						<tr id="mainRtng"></tr>
					</table>
				</div>
			</div>	
		</div>
	</div>
	
</div>
<script src="dist/mobileApp/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/mobileApp/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/mobileApp/Daterange/moment.js" type="text/javascript"></script>
<script src="dist/mobileApp/Daterange/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript">
$("#Date").daterangepicker({opens:"left"});
getUsersSummary();
function getUsersSummary(){
	var locationId = 31917;
	var locationType = "ward";
	var startDate = "01/26/2016";
	var endDate = "01/27/2016";
	var jsObj = {
		locationId : locationId,
		locationType:locationType,
		startDate:startDate,
		endDate:endDate
	};
	$.ajax({
		type : "GET",
		url : "getUserWiseDivisionSummaryAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		buildSummary(result);
	});
	
	function buildSummary(result){
		$("#ttlUsrs").html(result.usersCount);
		$("#ttlVtrs").html(result.voterIdsCollected);
		$("#ttlVtrsCaptrd").html(result.voterIdsCollected);
		$("#ttlMblCaptrd").html(result.noOfMobiles);
		
		var str = "";
		for(var i in result.ratings){
			str+="<td>"+result.ratings[i].ratingCount+"</td>";
		}
		$("#mainRtng").html(str);
		
		
		var str1 = "";
		for(var i in result.userRslt){
			str1+="<tr class='bg_FFF'>";
			str1+="<td>"+result.userRslt[i].date+"</td>";
			str1+="<td>"+result.userRslt[i].name+"</td>";
			str1+="<td>"+result.userRslt[i].voterIdsCollected+"</td>";
			str1+="<td>"+result.userRslt[i].noOfMobiles+"</td>";
			for(var j in result.userRslt[i].ratings){
				str1+="<td>"+result.userRslt[i].ratings[j].ratingCount+"</td>";
			}
			str1+="</tr>";
		}
		$(str1).insertAfter("#mainRtng");
	}
}
</script>
</body>
</html>