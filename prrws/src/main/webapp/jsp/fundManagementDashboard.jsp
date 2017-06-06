<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="Assests/css/bootstrap.css" type="text/css" rel="stylesheet"/>
<link href="Assests/css/custom.less" type="text/less" rel="stylesheet"/>
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
					<div class="panel panel-default panel-black">
						<div class="panel-heading">
							<h4 class="panel-title"></div>
						</div>
						<div class="panel-body">
							<button class="btn btn-success" onclick="getSchemeWiseLocationWiseAmountDetails();"> Get Scheme Data </button>
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
<script type="text/javascript" src="Assests/fundManagament/fundManagementDashboard.js"></script>
<!--Please do write the onload calls in the onLoadCalls function and the clicks in the onLoadClicks and initialisation of any kind of plugin in the onLoadInitialisations-->
</body>
</html>