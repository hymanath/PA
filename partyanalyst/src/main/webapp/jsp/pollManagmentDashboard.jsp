<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> POLL MANAGMENT DASHBOARD  </title>

<link href="dist/pollManagment/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/pollManagment/css/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">

</head>

<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default">
            	<div class="panel-heading bg_cc">
                	<h4 class="panel-title">POLL MANAGEMENT</h4>
                </div>
                <div class="panel-body bg_EF">
					<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<table class="table table-bordered bg_ff">
                            	<tr>
                                	<td rowspan="3" class="text-left">
                                    	<h3>150</h3>
                                        <p>TOTAL DIVISIONS</p>
                                    </td>
                                    <td rowspan="3">
                                    	<h3>500000</h3>
                                        <p>TOTAL BOOTHS</p>
                                    </td>
									<td rowspan="3">
                                    	<h3>1500</h3>
                                        <p>TOTAL VOTERS</p>
                                    </td>
                                    <td rowspan="3">
                                    	<h3>1500</h3>
                                        <p>TOTAL COLLECTED VOTERS</p>
                                    </td>
                                    <td>
                                    	<p>10000 - INCLINED VOTERS</p>
                                    </td>
                                    <td rowspan="3">
                                    	<h3>1500</h3>
                                        <p>TOTAL CADRE</p>
                                    </td>
                                </tr>
                                <tr>
                                	<td>
                                    	<p>10000 - UNDECIDED VOTERS</p>
                                    </td>
                                </tr>
                                <tr>
                                	<td>
                                    	<p>10000 - OTHER PARTY</p>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div class="panel panel-default ">
								<div class="panel-heading">
									<h4 class="panel-title">DIVISION WISE VOTING ACTIVITY</h4>
								</div>
								<div class="panel-body pad_0">
									<table class="table table-bordered table-condensed">
										<thead>
											<tr class="font-12">
												<th rowspan="2">DIVISION NAME</th>
												<th colspan="3">TOTAL VOTERS</th>
												<th colspan="3">CADRES</th>
												<th colspan="3">INCLINED VOTERS</th>
												<th colspan="3">UNDECIDED VOTERS</th>
												<th colspan="3">OTHER PARTY VOTERS</th>
												<th colspan="3">NOT CAPTURED VOTERS</th>
											</tr>
											<tr class="font-10">
												<th>TOTAL</th>
												<th>POLLED</th>
												<th>YET TO POLL</th>
												<th>TOTAL</th>
												<th>POLLED</th>
												<th>YET TO POLL</th>
												<th>TOTAL</th>
												<th>POLLED</th>
												<th>YET TO POLL</th>
												<th>TOTAL</th>
												<th>POLLED</th>
												<th>YET TO POLL</th>
												<th>TOTAL</th>
												<th>POLLED</th>
												<th>YET TO POLL</th>
												<th>TOTAL</th>
												<th>POLLED</th>
												<th>YET TO POLL</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>KAPRA</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
											</tr>
											<tr>
												<td>CHARLAPALLI</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
											</tr>
											<tr>
												<td>MALLAPUR</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
											</tr>
											<tr>
												<td>NACHARAM</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
											</tr>
											<tr>
												<td>UPPAL</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
											</tr>
											<tr>
												<td>HABSIGUDA</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
											</tr>
											<tr>
												<td>RAMANTHAPUR</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
											</tr>
											<tr>
												<td>KOTHAPET</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
											</tr>
											<tr>
												<td>KAPRA</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
											</tr>
											<tr>
												<td>KAPRA</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
											</tr>
											<tr>
												<td>KAPRA</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
												<td>10000</td>
												<td>20%</td>
												<td>80%</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
            </div>
        </div>
    </div>
</div>

<script src="dist/pollManagment/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/pollManagment/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/pollManagment/js/custom.js" type="text/javascript"></script>

<script type="text/javascript">
var locationIds = [];
getAccessValues();

function getAccessValues(){
	var jsObj={
			type:"ward"
		}
		$.ajax({
		  type:'GET',
		  url: 'getAccessValuesOfUserIdAction.action',
		  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				for(var i in result){
					var locationVal = result[i].districtid;
					locationIds.push(locationVal);
				}
			}
		});
	}
</script>
</body>
</html>