<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FMS DASHBOARD</title>
<link href="Assests/css/bootstrap.css" type="text/css" rel="stylesheet"/>
<link href="Assests/css/custom.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/less.js/2.7.2/less.min.js"></script>
</head>
<body>
<header>
	<nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-1"></div>
				<div class="col-sm-1"></div>
			</div>
		</div>
	</nav>
</header>
<main>
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<table class="table table-bordered table-overview">
						<tr>
							<td>
								<h4 class="panel-title">High Funded District</h4>
								<div id="highFundDist"></div>
							</td>
							<td>
								<h4 class="panel-title">High Funded Constituency</h4>
								<div id="highFundCons"></div>
							</td>
							<td>
								<h4 class="panel-title">High Funded Mandal</h4>
								<div id="highFundMandal"></div>
							</td>
							<td>
								<h4 class="panel-title">High Funded Village</h4>
								<div id="highFundVillage"></div>
							</td>
							<td>
								<h4 class="panel-title">High Funded Source</h4>
								<div id="highFundSource"></div>
							</td>
							<td>
								<h4 class="panel-title">High Funded Scheme</h4>
								<div id="highFundScheme"></div>
							</td>
						</tr>
						<tr>
							<td>
								<h4 class="panel-title">Low Funded District</h4>
								<div id="lowFundDist"></div>
							</td>
							<td>
								<h4 class="panel-title">Low Funded Costituency</h4>
								<div id="lowFundCons"></div>
							</td>
							<td>
								<h4 class="panel-title">Low Funded Mandal</h4>
								<div id="lowFundMandal"></div>
							</td>
							<td>
								<h4 class="panel-title">Low Funded Village</h4>
								<div id="lowFundVillage"></div>
							</td>
							<td>
								<h4 class="panel-title">Low Funded Source</h4>
								<div id="highFundSource"></div>
							</td>
							<td>
								<h4 class="panel-title">Low Funded Scheme</h4>
								<div id="highFundScheme"></div>
							</td>
						</tr>
						<tr>
							<td>
								<h4 class="panel-title">Average Funded District</h4>
								<div id="avgFundDist"></div>
							</td>
							<td>
								<h4 class="panel-title">Average Funded Costituency</h4>
								<div id="avgFundCons"></div>
							</td>
							<td>
								<h4 class="panel-title">Average Funded Mandal</h4>
								<div id="avgFundMandal"></div>
							</td>
							<td>
								<h4 class="panel-title">Average Funded Village</h4>
								<div id="avgFundScheme"></div>
							</td>
							<td>
								<h4 class="panel-title">Average Funded Source</h4>
								<div id="avgFundScheme"></div>
							</td>
							<td>
								<h4 class="panel-title">Average Funded Scheme</h4>
								<div id="avgFundScheme"></div>
							</td>
						</tr>
						<tr>
							<td>
								<h4 class="panel-title">Total Funded District</h4>
								<div id="totFundDist"></div>
							</td>
							<td>
								<h4 class="panel-title">Total Funded Costituency</h4>
								<div id="totFundCons"></div>
							</td>
							<td>
								<h4 class="panel-title">Total Funded Mandal</h4>
								<div id="totFundMandal"></div>
							</td>
							<td>
								<h4 class="panel-title">Total Funded Village</h4>
								<div id="totFundVillage"></div>
							</td>
							<td>
								<h4 class="panel-title">Total Funded Source</h4>
								<div id="totFundSource"></div>
							</td>
							<td>
								<h4 class="panel-title">Total Funded Scheme</h4>
								<div id="totFundScheme"></div>
							</td>
						</tr>
					</table>
				</div>
				<div class="col-sm-12">
					<div class="panel panel-default panel-black">
						<div class="panel-heading">
							<h4 class="panel-title text-capital">state level overview</h4>
						</div>
						<div class="panel-body">
							<div id="stateLevlOvervw"></div>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="panel panel-default panel-black">
						<div class="panel-heading">
							<h4 class="panel-title text-capital">district level overview</h4>
						</div>
						<div class="panel-body">
							<div id="distLevlOvervw"></div>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="panel panel-default panel-black">
						<div class="panel-heading">
							<h4 class="panel-title text-capital">mandal level overview</h4>
						</div>
						<div class="panel-body">
							<div id="mandalLevlOvervw"></div>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="panel panel-default panel-black">
						<div class="panel-heading">
							<h4 class="panel-title text-capital">constituency level overview</h4>
						</div>
						<div class="panel-body">
							<div class="consLevlOvervwScroll">
								<div id="consLevlOvervw"></div>
							</div>
						</div>
					</div>
				</div>				
			</div>
		</div>
	</section>	
</main>
<footer></footer>
<script type="text/javascript" src="Assests/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="Assests/js/bootstrap.js"></script>
<script src="Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script type="text/javascript" src="Assests/fundManagament/fundManagementDashboard.js"></script>

<!--Please do write the onload calls in the onLoadCalls function and the clicks in the onLoadClicks and initialisation of any kind of plugin in the onLoadInitialisations-->
</body>
</html>