<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<%@taglib prefix="s" uri="/struts-tags" %>
		<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
 <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
		<style>
			body{background:#f0f0f0;}
			.m_top20{margin-top:20px;}
			.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
			.widgetservey_Red{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px; border-top:3px solid #ff0000;}
			.widgetservey h4{font-size:26px; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.widgetservey_Red h4{font-size:26px; color:#ff0000; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.username thead tr:nth-child(2){ background:#eee;}		
			.username td:first-child{ min-width: 200px; }		
			.username th small{ font-size:11px; }				
			.username th{ text-align:center; }
		</style>	
	
  </head>
  
  <body><div class="container">
		<!---- Survey monitoring---->		
		<div class="row">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>Verifier Report</h4>
								<div class="row">
								<div class="span8 offset2">
									<div class="row-fluid">
										
										<div class="span5">
											<label>Select Constituency</label>
												<s:select theme="simple"  name="constituency" id="constituencyId"  headerKey="0" headerValue="Select Constituency" list="constituenciesList" listKey="id" listValue="name" />
										</div>
										<div class="span3">
											<label>User Type</label>
											<select class="input-block-level" id="userId"> <option value="0">Select User Type</option></select>
										</div>
										<div class="span2">
											<label>From Date</label>
											<div class="input-append">
											<input type="text" placeholder="User Name..." class="input-block-level date" id="fromDate">
											</div>
										</div>
										<div class="span2">
											<label>To Date</label>
											<div class="input-append">
											 <input type="text" placeholder="User Name..." class="input-block-level date" id="toDate">
											</div>
										</div>
									</div>	
									<div class="row-fluid">
										
										
									</div>
									</div>
									</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-success">SUBMIT</button></div>
					</div>
				</div>
				
				<div class="row-fluid ">
					<div class="span12 m_top20 widgetservey">
						<h4>Verifier Report For Daily Verification </br><small style="color:#333;padding-bottom:10px;display:inline-block;">  From 26 - June - 2014 to 3 - July - 2014 </small> </h4>
							
						<div class="row-fluid" style="overflow:scroll; height:500px;">
							<table class="table table-bordered m_top20 table-hover table-striped username">
							
								<thead class="alert alert-success">
									<tr> 
										<th rowspan="2">USER NAME</th>										
										<th colspan="1"rowspan="1">DATE</th>										
										<th colspan="2">26-June-2014</th>
										<th colspan="2">26-June-2014</th>
										<th colspan="2">26-June-2014</th>
										<th colspan="2">26-June-2014</th>
										<th colspan="2">26-June-2014</th>
										<th colspan="2">26-June-2014</th>
										
														
										
									</tr>
									<tr> 
																			
										<th>Booth No</th>
										<th><small>Total Voter</small></th>
										<th><small>Verified Voters</small></th>
										<th><small>Total Voter</small></th>
										<th><small>Verified Voters</small></th>
										<th><small>Total Voter</small></th>
										<th><small>Verified Voters</small></th>
										<th><small>Total Voter</small></th>
										<th><small>Verified Voters</small></th>
										<th><small>Total Voter</small></th>
										<th><small>Verified Voters</small></th>
										<th><small>Total Voter</small></th>
										<th><small>Verified Voters</small></th>
										
													
														
										
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>user Name</td>
										<td>10</td>
										<td>500</td>
										<td>500</td>
										<td>900</td>
										<td>900</td>
										<td>900</td>
										<td>900</td>
										<td>700</td>
										<td>900</td>
										<td>500</td>
										<td>500</td>
										<td>800</td>
										<td>500</td>
										
									</tr>
									<!--------->
									
									<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
									<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
									<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
									<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
									<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
									<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
									
									
										<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
									
										<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
										<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
										<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
										<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
										<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
										<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
									
									
									<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
									
									<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
									
									<tr>
										<td>user Name</td>
									
										<td>105</td>
										<td>1000</td>
										<td>1000</td>
										<td>500</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>1200</td>
										<td>800</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									<!-------->
									
									<tr>
										<td>user Name</td>
										
										<td>25</td>
										<td>500</td>
										<td>500</td>
										<td>500</td>
										<td>500</td>
										<td>500</td>
										<td>500</td>
										<td>600</td>
										<td>900</td>
										<td>900</td>
										<td>800</td>
										<td>200</td>
										<td>1400</td>
									</tr>
									
								</tbody>
							</table>	
						</div>
							
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<script>
/*	function getBoothDetails()
{
	var jObj =
	{
	 constituencyId:$('#constituencyId').val()
	}
	$.ajax({
			type:'GET',
			url: 'getBoothsByConstituency.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
			$("#boothId option").remove();
			$("#boothId").append("<option value='0'>Select Booth</option>");
			for(var i in result){
                $("#boothId").append("<option value="+result.id+">"+result.partiesInMandal[i].name+"</option>");
			}
	});
		
}*/
$(function() {
	$(".date").datepicker({ 
	dateFormat: 'dd-mm-yy',
   }).datepicker('setDate', new Date());
  
});

	
	
	</script>
	
  </body>
 </html>