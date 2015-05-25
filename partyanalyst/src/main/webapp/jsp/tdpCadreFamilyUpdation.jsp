<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>TDP Cadre Family Details</title>
	 <!-- Bootstrap -->
   
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	 <script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	
	<script>
	
	</script>
	<style>
		
	</style>
	
  </head>
  <body>
<div class="container">
			<!-------ADD BLOCK------>

	<div class="row m_top20" id="searchcadreDiv" class="row m_top20">	
	
	
		<div class="span12" style="margin-top: 20px;">
			<h4 id="headingDiv" class="text-uppercase"> Search Cadre</h4>
		
		</div>
		<div class="span12" >
			<div class="span4" id="errorDiv" style="margin-top: 10px;margin-bottom: 10px; margin-left: 0px;"></div>
		</div>
		<div class="span12" style="margin-bottom: 20px;">
			
			<div class="row-fluid">  
				<div class="span4"> 
					<input type="text" id="membershipNo" class="input-block-level" placeholder="ENTER MEMBERSHIP ID ">
				</div>
				
				<div class="span4" >
					<input type="text" placeholder="ENTER MOBILE NO " class="input-block-level" id="mobileNo">
				</div>
				
				<div class="span4">
					<input type="text" placeholder=" ENTER VOTERID" class="input-block-level" id="voterId">
				</div>
			</div>
			
			
			<div class="row-fluid">
				<div class="span4 offset4">
					<input type="button" id="searchbtn" style="margin-bottom: 20px;" class="btn btn-success btn-block" onclick="getCadreDetails()" value="SEARCH">
				</div>
				<div class="span12" id="tableDataDiv"></div>
			</div>
		</div>
		
	<script>
	
	function getCadreDetails()
	{
				
		var mobileNo =	$('#mobileNo').val().trim();
		var voterId = $('#voterId').val().trim();
		var membershipNo =$('#membershipNo').val().trim();
		
		if(mobileNo.trim().length == 0 && voterId.trim().length == 0 && membershipNo.trim().length == 0)
		{
				$('#errorDiv').html('Please Enter Any Search Criteria').css("color","red");	
				return false;
		}
	
		$('#errorDiv').html('');	
		//$("#ajaxImage").show();
		var jsObj = {
			mobileNo:mobileNo,
			membershipNo:membershipNo,
			trNumber:voterId,			
			task:"getDetailsForCallCenter"            
		}
  
		$.ajax({
			type : "POST",
			url : "searchCadreForFamilyDetlsUpdationAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			//$("#ajaxImage").hide();			
			buildTableCadreInfo(result);
		});
		
	}

	
	function buildTableCadreInfo(result){
		var str="";	
		str+='<div class="span12">';
			
		str+=' <table class="table table-bordered"  id="tableId">';
		str+=' <thead>';
		str+=' <tr>';
		str+=' <th class="alert-info"> Cadre Name  </th>';
		str+=' <th class="alert-info"> Image  </th>';
		str+=' <th class="alert-info" > Membership Number  </th>';
		str+=' <th class="alert-info" > Mobile Number </th>';
		str+=' <th class="alert-info" > Constituency </th>';
		str+=' </tr>';
		str+=' </thead>';
		str+=' <tbody>';
	
		for(var i in result){
			str+=' <tr>';
			
			str+=' <td><a href="javascript:{};" style="cursor:pointer;" onclick="getCardDetails(\''+result[i].cadreId+'\');">'+result[i].nameType+'</a></td>';
			str+='<td><img src="http://www.mytdp.com/images/cadre_images/'+result[i].imageBase64String+'"  /></td>';
			str+=' <td>'+result[i].address+'</td>';
			str+=' <td>'+result[i].mobileNumber+'</td>';
			str+=' <td>'+result[i].constituencyId+'</td>';
			
			str+=' </tr>';
		}
		str+='</tbody></table></div>';
		$("#tableDataDiv").html(str);
		$("#tableId").dataTable();
	}
	


	</script>
	
  </body>
 </html>