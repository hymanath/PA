<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cards Print Dispatch</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/CardPrint/custom.css" rel="stylesheet" type="text/css">
<link href="dist/CardPrint/dropkick.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h3 class="panel-title text-capital">dispatch of smart card</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-4 col-xs-12 col-sm-4">
								<label>Select Vendor</label>
								<select class="select">
									<option>Vendor 1</option>
								</select>
							</div>
							<div class="col-md-4 col-xs-12 col-sm-4">
								<label>Select DIstrict</label>
								<select class="select">
									<option>Distrct 1</option>
								</select>
							</div>
							<div class="col-md-4 col-xs-12 col-sm-4">
								<label>Select Constituency</label>
								<select class="select">
									<option>Cons 1</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
								<table class="table tablePrinting text-capital">
									<thead>
										<th>Select</th>
										<th>BOX ID</th>
										<th>mandal/town/division</th>
										<th>village/ward</th>
										<th>no of cards</th>
										<th>qa passed</th>
										<th>error %</th>
										<th>validated cards</th>
										<th>status</th>
									</thead>
									<tbody>
										<tr>
											<td>
												<input type="checkbox"/>
											</td>
											<td>52522</td>
											<td>kavali</td>
											<td>kavali</td>
											<td>200</td>
											<td>yes</td>
											<td>1%</td>
											<td>12</td>
											<td>ready to dispatch</td>
										</tr>
									</tbody>
								</table>
								<button class="btn btn-success btn-lg text-capital">update & generate tracking agent</button>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="panel panel-default panelWhite">
					<div class="panel-heading">
						<h3 class="panel-title text-capital"><b>kavali mandal tracking sheet</b><span class="pull-right">X</span></h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-4 col-xs-12 col-sm-4">
								<caption class="text-capital"><b>heading</b></caption>
								<table class="table table-bordered text-capital">
									<tr>
										<td>box tracking id</td>
										<td>bt2249</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(".select").dropkick();
if($(window).width() < 500)
{
	$("table").wrap( "<div class='table-responsive'></div>" );
}
</script>
</body>
</html>
