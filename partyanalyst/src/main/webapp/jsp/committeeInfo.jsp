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

    <!-- Bootstrap -->
    <link href="css/cadreCommitee/bootstrap.min.css" rel="stylesheet">
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/style.css" rel="stylesheet">
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/bootstrap.min.js"></script>
	</head>
	  <body>
	  <div class="container">
		<div class="row-fluid m_top20">
			<div class="form-group col-md-3 col-md-offset-3  col-sm-3 col-xs-3">
				<label for="">Select Level <span style="color:red">*</span></label>
				<select id="committeLevel" class="form-control" onchange="handlechange();">
					<option value="2"> DISTRICT </option>
					<option value="3"> MANDAL/TOWN/DIVISION </option>
					<option value="4"> VILLAGE/WARD </option>
				</select>				
		</div>
		<div><img src="images/icons/search.gif" id="ajaxImgId"/></div>
		
		
		
			<div class="form-group col-md-3  col-sm-3 col-xs-3" id="distdisplaydivid">
			
				<label for="">Select District <span style="color:red">*</span></label>
				<select id="districtsDispalyId" class="form-control"><option value="0">Select District</option>
				
				</select>
				</div>


			<div class="form-group col-md-3  col-sm-3 col-xs-3" id="constdisplaydivid">
				<label for="">Select Constituency <span style="color:red">*</span></label>
				<select id="displayconstbox" class="form-control"><option value="0">Select Constituency</option>
				
				</select>
				</div>
				
				<div class="form-group col-md-2 col-sm-3 col-xs-3 m_top20">

				<!--<a onclick="redirectToSummary()" value="submit" class="btn btn-success" target="_blank">Summary Report</a>-->

				<a onclick="redirectToPage();" value="submit" class="btn btn-success" target="_blank">View Committee</a>
				</div>
				</div> 
			</div>	  
	  <script>
	var accessType = "${sessionScope.USER.accessType}";
	var accessValue = "${sessionScope.USER.accessValue}";
	var accessState = "${sessionScope.USER.stateName}";
	function getDistrictsForStates(state){
  $("#ajaxImgId").show();
     $("#districtsDispalyId").html('<img src="images/icons/search.gif" id="ajaxImg"/>');
   var jsObj=
   {				
				stateId:state,
				elmtId:"districtList_d",
                type:"default",
				task:"getDistrictsForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
  $("#ajaxImgId").hide();
	   $("#districtsDispalyId").empty();
     for(var i in result){
	   /*if(result[i].id == 0){
          $("#districtsDispalyId").append('<option value='+result[i].id+'>Select District</option>');
	   }else{
		   if(accessValue == result[i].id)
	      $("#districtsDispalyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }*/
	    if(accessValue == result[i].id)
	      $("#districtsDispalyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	 }
	 	$("#distdisplaydivid").show();
	});
  }

  function handlechange()
  {
	  $("#distdisplaydivid").hide();
	  $("#constdisplaydivid").hide();
	var level =  $("#committeLevel").val();
	
	 if(level == 2)
	  {
		getDistrictsForStates(0);
	  }
	  else
	  {
		  if(accessType == "DISTRICT")
				getAssemblyParlConstituencies(accessValue,"Assembly");
		  else
			getConstituenciesUWS();
	  }

  }
  	function getConstituenciesUWS(){
		
		$("#displayconstbox").html("");//ajaxImgId
		$("#ajaxImgId").show();
		var jObj ={
			stateid:0,				  
			task:"getConstituencieForUWS"            
		}	
		$.ajax({
			type : "POST",
			url : "getConstsAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			var str='';
			//var str = "<option value='0'>Select Constituency</option>";
		   for(var i in result){
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#displayconstbox").html(str);
			$("#constdisplaydivid").show();
			$("#ajaxImgId").hide();
		});
	}

	function redirectToPage()
	{
		var url;
		var level = $("#committeLevel").val();
		if(level != 2){//2 --> District
		  var constituenyId = $("#displayconstbox").val();
		  if(constituenyId > 0){
			  url = "cadreCommitteeAction.action?locationId="+constituenyId+"";
		  }
		}else{
			var distId = $("#districtsDispalyId").val();
			url = "committeeManagementAction.action?locationId="+distId+"& reqLocationType=district";
		}
		window.open(url,'_blank');
	}
	

	function redirectToSummary()
	{
		var url;
		var level = $("#committeLevel").val();
		if(level != 2)
		{
		var constituenyId = $("#displayconstbox").val();
		if(constituenyId > 0)
		url = "/cadreCommitteeSummaryAction.action?locationId="+constituenyId+"&locationType=constituency";
		}
		else
		{
			var distId = $("#districtsDispalyId").val();
			url = "/cadreCommitteeSummaryAction.action?locationId="+distId+"&locationType=DISTRICT";
			
		}
		window.open(url,'_blank');
	}
	  function getAssemblyParlConstituencies(districtId,type){
	   $("#ajaxImgId").show();
	  $("#displayconstbox").html('<img src="images/icons/search.gif" id="ajaxImg"/>');
	 $("#constituencyId  option").remove();
		var str='';
		var jsObj={
			mainUserLocationId:districtId,
			reportLevel:type
		}
		$.ajax({
			  type:'GET',
			  url: 'getSubUserAccessValueAction.action',
			  data: {task:JSON.stringify(jsObj)}
			
	   }).done(function(result){
	  // str +='<option value=0>Select Constituency</option>';
	   $("#ajaxImgId").hide();
				for(var i in result)
				{
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				
				if(type=="Assembly"){
					//$("#constituencyId").html(str);
					$("#displayconstbox").html(str);
					$("#constdisplaydivid").show();
				}		
	   });		
	}
	
	function buildOoptions(){
		$('#committeLevel').find('option').remove();
		if(accessType == 'STATE'){
			$('#committeLevel').append('<option value="3" > MANDAL/TOWN/DIVISION </option>');
			$('#committeLevel').append('<option value="4" selected="true" > VILLAGE/WARD </option>');
		}else{
			$('#committeLevel').append('<option value="2" selected="true" > DISTRICT </option>');
			$('#committeLevel').append('<option value="3"> MANDAL/TOWN/DIVISION </option>');
			$('#committeLevel').append('<option value="4"> VILLAGE/WARD </option>');
		}
		
		handlechange();
	}
	
	 buildOoptions();
	</script>
  </body>
</html>