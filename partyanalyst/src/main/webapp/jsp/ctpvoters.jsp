<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voters Search</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
 
 <script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
 <script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.2.0/js/tab.js"></script>
 <style>
 .titleHeading{
    color: steelblue;
    font-family: verdana;
    font-size: 13px;
    font-weight: bold;
    margin-bottom: 20px;
	font-size: 16px;
	text-align:center;
	margin-top: 20px;
 }
 </style>
 </head>
 <body>
 <div class="container" style="margin-top:10px;">
   <span style='display:none;' class="offset4" id='ajaxLoad'><img src='./images/icons/goldAjaxLoad.gif' /></span>
 <div id="voterDetailsDiv"></div>
 </div>
 <script type="text/javascript">
 var id = '${id}';
 var type='${type}';
 var constituencyId = '${constituencyId}';
 var casteId = '${casteId}'; 
 var gender = '${gender}';
 
 function getVoterDetails()
 {
	 $("#voterDetailsDiv").html('');
	 $("#ajaxLoad").show();
	 var jObj = {
			 id : id,
			 type : type,
			 casteId:casteId,
			 constituencyId:constituencyId,
			 gender:gender,
			 task : "voterDetails"
				}
				$.ajax({
				type : 'GET',
				url : 'ctpvotersInCasteAjaxAction.action',
				dataType:'json',
				data:{task:JSON.stringify(jObj)},
				success:function(result)
					{
					$("#ajaxLoad").hide();
					 buildVoterDetails(result.votersList);
							  },
				          error:function() { 
				           console.log('error', arguments);
				         }
				});
				
 }
 function buildVoterDetails(result)
	{
		var str = '';
		str+='<div class="titleHeading"> VOTER DETAILS</div>';
		str+='<table class="table table-bordered" id="voterDataTable">';
		str+='<thead>';
		str+='<th>Name</th>';
		str+='<th>VoterId</th>';
		str+='<th>booth</th>';
		str+='<th>Serial Number</th>';
		str+='<th>HNO</th>';
		str+='<th>gender</th>';
		str+='<th>age</th>';
		str+='<th>caste</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
		{
		
		str+='<tr>';
		str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].voterIdCardNo+'</td>';
		str+='<td>'+result[i].boothName+'</td>';
		str+='<td>'+result[i].fromSno+'</td>';
		str+='<td>'+result[i].houseNo+'</td>';
		str+='<td>'+result[i].gender+'</td>';
		str+='<td>'+result[i].age+'</td>';
		str+='<td>'+result[i].casteName+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		$("#voterDetailsDiv").html(str);
		if(result.length > 20)
		{
		$("#voterDataTable").dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 20,
		"aLengthMenu": [[20, 30, 90, -1], [20, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null,null,null
		] 
		});
		}
	}
 </script>
 <script>
 getVoterDetails();
 </script>
 </body>
 </html>