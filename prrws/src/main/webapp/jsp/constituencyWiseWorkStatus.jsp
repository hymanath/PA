<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ConstituencyWiseWorkStatus</title>
<link href="Assests/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/less" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
</head>
<body>
	<main> 
		 <div class="container">
			<section>
				<div class="row">
					<div class="col-sm-3">
						<div class="form-group">
							<select id="districtSelId" class="form-control chosenSelect">
								<option value="0"> SELECT DISTRICT</option>
							</select>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<select id="constituencySelId" class="form-control chosenSelect">
								<option value="0"> SELECT CONSTITUENCY</option>
							</select>
						</div>
					</div>						
				</div>
			</section>
		</div>
	</main>
<script src="Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/constituencyWorks/constituencyWiseWorkStatus.js" type="text/javascript" ></script>
</body>
</html>