<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

 <link href="css/bootstrap.min.css" rel="stylesheet">	
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
 <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
 <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<title>IVR Report</title>
<style>
</style>
</head>

<body>

<div class="container ">
    <div id="prevCallDetailsShowOuter" style="display:none;"><div id="prevCallDetailsShowInner"></div></div>	
	<!-- Title Row -->
		<div class="row-fluid" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-0 ">
				<h3 class="text-center text-uppercase">IVR UPLOAD</h3>
			</div>
		</div><!-- Title Row End-->
		
		<!-- Filters Row -->
		<div>
	
		<div id="fadeInDown" class="row-fluid">		
				
			<div>	
				<label class="span2 offset3" style="margin-left: 259px;">IVR Camapign:</label><select id="campaignSelect" class="form-control"  onchange="getCampaignDetls();"><option value="0">Select IVR Campaign </option></select>
				<div id="optionsId"  class="offset3"></div>
				
			</div> 
			</div>
		</div>

<script>
	$(document).ready(function(){		
		
	});
	
	function getAllCampaigns()
	{
		var jObj = {
			task:"campaignDetails"
		};
		$.ajax({
          type:'GET',
          url: 'getAllCampaignsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result != null && result.length > 0){
				$('#campaignSelect').find('option').remove();
				$('#campaignSelect').append('<option value="0">Select IVR Campaign</option>');
				for(var i=0;i<result.length;i++){
					$('#campaignSelect').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
				}
		});
	}
	
	function getCampaignDetls()
	{
		var campaignId = $("#campaignSelect").val();
		var jObj = {
			campaignId:campaignId,
			task:"campaignDetails"
		};
		$.ajax({
          type:'GET',
          url: 'getAllIVROptionsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result != null && result.length > 0){
			var str='<div><h4>Options</h4><br>';
				for(var i=0;i<result.length;i++){
					str+='<input type="text" id="'+result[i].id+'" class="oldIds" value="'+result[i].name+'" readonly />&nbsp;&nbsp;&nbsp;<input class="newIds" type="text" style="width:30px;"/><br>';
				}
				str+='<button class="btn btn-default btn-success" style="margin-top:10px" onClick="getNewValues();">Submit</button></div>';
				$("#optionsId").html(str);
				}
		});
	}
	
	function getNewValues(){
	var arr = new Array();
	$('.oldIds').each(function() {
        arr.push({
            oldId: $(this).attr('id'), 
            newId: $(this).next(".newIds").val()
        });
    }); 
console.log(arr);	
	
	
	}
	getAllCampaigns();
</script>
</body>
</html>
