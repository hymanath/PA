<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Committee Management</title>
   <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
  
	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	</head>
	<body>
	  MemberShip Number:<input type="text" id="membershipId"  ></input>  &nbsp &nbsp  &nbsp 
	 <input type="button" value="GetMemberShipDetails" onClick="validateMembershipNo()"><br><br><br><br>
	 
	<div id="detailsId"></div>
	  <script>
	  function validateMembershipNo()
	  {
		  var memberShipNumber=$("#membershipId").val();
		  $('#detailsId').html(' Please wait....');
		  $.ajax({
				type : "POST",
			    url: "http://192.168.11.61:8080/PartyAnalyst/WebService/Auth/getMemberData",
				data: '{"membershipNo":"'+memberShipNumber+'","isAddress":"true"}',
				contentType: "application/json; charset=utf-8",
                dataType: "json", 
                username: "itgrids",
                password: "servey@android",
			}).done(function(result){
				
				if(result != null)				
				{
				    var str='';
					str+='<table border="1">';
					str+='<thead>';
					    str+='<th>NAME</th>';
						str+='<th>AGE</th>';
						str+='<th>GENDER</th>';
						str+='<th>Mobile NO</th>';
						str+='<th>Address</th>';

					str+='</thead>';
					
					str+='<tbody>';
					str+='<tr>';
					str+='<td>'+result.name+'</td>';
					str+='<td>'+result.age+'</td>';
					str+='<td>'+result.gender+'</td>';
					str+='<td>'+result.mobileNo+'</td>';
					
					str+='<td>'+result.address +'</td>';
					str+='</tr>';
					str+='</tbody>';
					str+='</table>';	
                    $("#detailsId").html(str);					
				}
		   });
		  
	  }
	  </script>
	</body>
	
	
	</html>