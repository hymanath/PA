<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ResourceBundle;" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CadreSearch Details</title>
		<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<script src="http://code.jquery.com/jquery.js"></script>
		<script src="http://code.jquery.com/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jquery.dataTables.js"></script>
		<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
</head>
<body>
	<div class="container" style="margin-top:10px;background-color:#F7F8F9;">
		<div class="row offset3">
				<div id="errdiv" style="height:20px;margin-left: 20px;"" class="offset0"></div>
				<div class="row-fluid span7">
					<div class="span3">
						<label>Select Constituency</label>
					</div>
					<div class="span3">
						<select id="const" onchange="getAllPanchayat()"></select>
					</div>
				
				</div>
				<div class="row-fluid span7">
					<div class="span3">
						<label>Select Panchayat</label>
					</div>
					<div class="span3">
						<select id="panchayat"></select>
					</div>
				</div>
				<div class="offset3">
					<button name="search" class="btn btn-primary" onclick="getCadreSearchDretails()">Search</button>
					<div id="ajaxcallimage"  class = "span3" style="display:none;font-weight:bold;color: #0174DF;font-size:small;width: 345px; height: 17px;">
						<img src="images/icons/loading.gif" style="padding-left:10px;" width="18" height="11"/>
					</div>						
				</div>
			<div id="ajaxcallimage"  class = "span3" style="display:none;font-weight:bold;color: #0174DF;font-size:small;width: 345px; height: 17px;">
				<img src="images/icons/loading.gif" style="padding-left:10px;" width="18" height="11"/>
			</div>				
		</div>		
	</div>
	<div id="tableDiv" class="offset2" style="margin-top:10px;"></div>
<script>

	$(document).ready(function(){
		getAllconstituenys();
		$("#panchayat").html('');
		$("#errdiv").html('');
	});
	
	function getAllconstituenys()
	{
		$.ajaxSetup({jsonp: null,jsonpCallback: null});
		$.ajax({
		type: "GET",
		url: "getAllConstituencyaction.action",
		dataType: 'json',
		data: {electionscopeId:2,stateId:1},

			success:function(result){

				for(var i in result){
				
				$("#const").append("<option value="+result[i].id+">"+result[i].name+"</option>");
				
				}
			}
		});
	
	}
	
	function getAllPanchayat()
	{
			var constituencyIds=$("#const option:selected").val();
			
		var jsObj = 
		{
			constituencyId:constituencyIds,
			task:"getAllPanchayaties"
		}
				$.ajax({
					type: "GET",
					url:"getAllPanchayatiesAction.action",
					dataType:'json',
					 data: {task:JSON.stringify(jsObj)},
					 }).done(function(result,jsObj){
					 $("#panchayat").find('option').remove();
					 $("#panchayat").append("<option value='0'> Select Panchayat </option>");
						 if(result !=null && result.length >0){
							for(var i in result){
								$("#panchayat").append("<option value="+result[i].id+">"+result[i].name+"</option>");
							}
						}
					});		
	}

	function getCadreSearchDretails()
	{
		$("#tableDiv").html('');
		$("#errdiv").html('');
		
		var constituencyIds=$("#const option:selected").val();
		var panchayatIds=$("#panchayat option:selected").val();	
	
					 
		if(constituencyIds != null && constituencyIds== 0)
		{
			$('#errdiv').html('please select the constituency').css('color','red');
			return;
		}
		if(panchayatIds != null && panchayatIds == 0)
		{
			$('#errdiv').html('please select the panchayat').css('color','red');
			return;
		}
		
			$("#ajaxcallimage").show();
			
				
	var jsObj = 
		{
			panchayatId:panchayatIds,
			task:"getCadreDetails"
		}
		
				$.ajax({
					type: "GET",
					url:"getCadreDetailsByPanchayatAction.action",
					dataType:'json',
					 data: {task:JSON.stringify(jsObj)},
					 }).done(function(result,jsObj){
						$("#ajaxcallimage").hide();
							cadreDetails(result);
							
					 });
	}
	function  cadreDetails(result)
	{
		var str='';
		str+="<table id='cadretable' class='table table-striped table-bordered table-condensed'>";
		str+="<thead>";
			str+="<tr>";
				str+="<th>CadreId</th>";
				str+="<th>FirstName</th>";
				str+="<th>LastName</th>";
				str+="<th>MobileNumber</th>";
				str+="<th>Age</th>";
				str+="<th>VoterId</th>";
				str+="<th>FatherName</th>";
			str+="</tr>"
		str+="</thead>";
		str+="<tbody>";
		for(var i=0;i<result.length;i++)
		{
			str+="<tr>";
				str+="<td>"+result[i].cadreId+"</td>";
				str+="<td>"+result[i].firstName+"</td>";
				str+="<td>"+result[i].lastName+"</td>";
				str+="<td>"+result[i].mobileNo+"</td>";
				str+="<td>"+result[i].age+"</td>";
				str+="<td>"+result[i].voterCardId+"</td>";
				str+="<td>"+result[i].fatherName+"</td>";
			str+="</tr>";
		}
		str+="</tbody>";
		str+="</table>";
		$("#tableDiv").html(str);
		$("#cadretable").dataTable();
	}


</script>
</body>
</html>