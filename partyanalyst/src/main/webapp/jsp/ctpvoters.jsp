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
 
 <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
 <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.2.0/js/tab.js"></script>
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
	text-transform: uppercase;
 }
 #voterDataTable{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

#voterDataTable tr:nth-child(even){background:#EdF5FF;}

#voterDataTable td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}

#voterDataTable th{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: left;
	color:#333333;
	}



table.dataTable tr.odd {
    background-color: #ffffff;
}
table.dataTable tr.even {
    background-color:#EdF5FF;
}
table.dataTable tr.odd td.sorting_1 {
    background-color: #ffffff;
}
table.dataTable tr.even td.sorting_1 {
    background-color: #EdF5FF;
}
 .selectDiv{
	 width:80%;
	 padding-top:5px;
	 padding-bottom:0px;
	 font-family: verdana;
	 font-size: 12px;
	 margin-left:auto;
	 margin-right:auto;
	 font-weight:bold;
	 color:#333333;
 }
 
  fieldset{
    border: 3px solid #CFD6DF;
    margin-bottom: 10px;
    padding: 10px;
}
.widget {
    background: none repeat scroll 0 0 #fafafa;
    border-top: 2px solid #ffdc2d;
    box-shadow: 0 0 1px rgba(0, 0, 0, 0.3);
    margin: 5px 0 20px;
    padding: 0 20px 20px;
    position: relative;
}
 </style>
 </head>
 <body>
 <div class="container" style="margin-top:10px;">

 <div class="widget">
  <div class="titleHeading"> SELECTION CRITERIA</div>
  <div id="errorMsgAlert" style="font-family: verdana;font-size:14px;color:red;margin-left:100px;"></div>
  
 <div class="selectDiv">
 
	   VoterId<input style="margin-left: 24px; width: 141px;" type="text" id="voterId" /> &nbsp&nbsp;
	   
	    House No<input style="margin-left: 14px; width: 105px;" type="text" id="reqHouseNo" />
	 
	  </div>
	  
	 
	  
	  <div class="selectDiv">
	   Name<input style="margin-left: 40px; width: 141px;" type="text" id="voterName" />
	   <div class="row pull-right">
	   <div class="span2">
	   <label class="radio"  style="margin-left: -89px;">
	   <input type="radio"name="voterNameChkBox" checked="true" id="startWith"  value="start" /><b style="font-size: 12px; font-family: verdana;"> Start With</b>
         </label>
		 </div>
		 <div class="span3">
		<label class="radio"style="margin-left: -140px;">
	   <input type="radio"  id="anyWhere" name="voterNameChkBox" value="any"/><b style="font-size: 12px; font-family: verdana;"> Any Where</b>
	 </label>
	 </div>
	 </div>
	  </div>
	  
	 
	  <div class="selectDiv">
	   Gender 
	    <div class="row "style="padding: 0px 0px 0px 83px; margin-top: -22px; ">
		   <div class="span">
		   <label class="radio">
	   <input type="radio" checked="true"  value="all" name="genderChkBox" id="allGenderId"/><b style="font-size: 12px; font-family: verdana;"> All </b>
	    </label>
		 </div>
		 	   <div class="span">
		 <label class="radio">
	   <input type="radio" id="maleSelect" name="genderChkBox"  value="male"  /><b style="font-size: 12px; font-family: verdana;"> Male </b>
	    </label>
			 </div>
			 	   <div class="span">
			 <label class="radio">
	   <input type="radio" id="femaleSelect" name="genderChkBox"  value="female" /><b style="font-size: 12px; font-family: verdana;"> Female</b>
	       </label>
		    </div>
	  </div>
	    </div>
	  <div class="selectDiv">
	   Age Between <input style="width:67px;margin-left:4px;" type="text" id="fromAge" /> <input style="width:67px;" type="text" id="toAge" />
	  </div>
	  <input onclick="getVoterDetails()" class="btn btn-success offset5" type="button"  value="submit"/>
	 </div> 
	  
   <span style='display:none;' class="offset4" id='ajaxLoad'><img src='./images/icons/goldAjaxLoad.gif' /></span>
 <div id="voterDetailsDiv"></div>
 <div id="errorMessageDiv" style="display:none;font-weight:bold;color:red" align="center"></div>
 </div>
 <script type="text/javascript">
 var id = '${id}';
 var type='${type}';
 var constituencyId = '${constituencyId}';
 var casteId = '${casteId}'; 
 var gender = '${gender}';
 
 function getVoterDetails()
 {
	
	  $('#errorMessageDiv').html('');
	  $("#errorMsgAlert").html('');
	 var searchEles = new Array();
	  var flag =true;
	 var voterCardId = '';
	var voterName = '';
	var voterNameType = '';
	var searchGender = '';
	var startAge = 0;
	var endAge = 0;
	var houseNo ='';
	voterCardId = $.trim($("#voterId").val());
	voterName = $.trim($("#voterName").val());
	houseNo = $.trim($("#reqHouseNo").val());
	var str ='';
	if($("#startWith").is(':checked')){
	   voterNameType = 'start';
	}else{
	  voterNameType = 'anywhere';
	}
	if($("#maleSelect").is(':checked')){
	   searchGender = 'M';
	}else if($("#femaleSelect").is(':checked')){
	  searchGender = 'F';
	}
	var ageStart = $.trim($("#fromAge").val());
	var ageEnd = $.trim($("#toAge").val());
	if(ageStart.length > 0){
	   if(isNaN(ageStart)){
	        str +='<div>Please Enter Valid Start Age</div>';
			flag =false;
	   }else{
	     startAge = ageStart;
	   }
	}
	if(ageEnd.length > 0){
	   if(isNaN(ageEnd)){
	        str +='<div>Please Enter Valid End Age</div>';
			flag =false;
	   }else{
	     endAge = ageEnd;
	   }
	}
	
	if(ageStart.length > 0 && ageEnd.length > 0){
		if(ageStart > ageEnd || (ageStart == 0 && ageEnd == 0)){
			str +='<div>Please Enter Valid Ege Range</div>';
			flag =false;
		}
	}
	if(ageStart.length == 0)
		startAge == 18;
	if(ageEnd.length == 0)
	endAge = 153;
	
	
	if(!flag){
	  $("#errorMsgAlert").html(str);
	 
	}
	else
	 {
	$("#ajaxLoad").show();
	$("#voterDetailsDiv").html('');
	 
 var obj = {
	 voterCardId: voterCardId,
	  voterName:voterName,
	  voterNameType:voterNameType,
	  searchGender:searchGender,
	  startAge:startAge,
	  endAge:endAge,
	  houseNo:houseNo,
	 
 }
	 
 searchEles.push(obj);
	 var jObj = {
			 id : id,
			 type : type,
			 casteId:casteId,
			 constituencyId:constituencyId,
			 gender:gender,
			 searchEles:searchEles,
			 task : "voterDetails"
				}
				$.ajax({
				type : 'GET',
				url : 'ctpvotersInCasteAjaxAction.action',
				dataType:'json',
				data:{task:JSON.stringify(jObj)},
				success:function(result)
					{
					clearFields();
					$("#ajaxLoad").hide();
					  if(result.votersList== null || result.votersList.length == 0)
					 $('#errorMessageDiv').show().html('No Data Avalible For Given Search Details');
					  else
					 buildVoterDetails(result.votersList);
							  },
				          error:function() { 
				           console.log('error', arguments);
				         }
				});
	 }		
 }
 function buildVoterDetails(result)
	{
		var str = '';
		var caste ="";
		if(casteId > 0)
		caste = result[0].casteName;
		var type1 =  "";
		if(type == "Panchayat")
		{
			if(caste != "")
		str+='<h4 class="titleHeading"> '+caste+' CASTE VOTER DETAILS IN '+'${locationName}'+' '+type1+' </h4>';
			else
			str+='<h4 class="titleHeading"> '+caste+' VOTER DETAILS IN '+'${locationName}'+' '+type1+' </h4>';
		}
		else
		{
			if(caste != "")
			str+='<h4 class="titleHeading"> '+caste+' CASTE VOTER DETAILS IN BOOTH - '+'${locationName}'+' '+type1+' </h4>';
			else
			str+='<h4 class="titleHeading"> '+caste+' VOTER DETAILS IN BOOTH - '+'${locationName}'+' '+type1+' </h4>';
		}
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
	function clearFields()
	{
		$("#voterId").val('');
		$("#reqHouseNo").val('');
		$("#voterName").val('');
		$("#fromAge").val('');
		$("#toAge").val('');
		$("#startWith").attr('checked', 'checked'); 
		$("#allGenderId").attr('checked', 'checked'); 
	}
 </script>
 <script>
 getVoterDetails();
 </script>
 </body>
 </html>