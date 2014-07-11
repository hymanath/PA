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
 </head>
 <body>
 <script>
  $(document).ready(function(){

	  $('#scopeId').change(function(){
		   if(this.value ==  9)
			 $('#constituencyId').show();
			else
		  $('#constituencyId').hide();
	  });
 });
 </script>

 Select Scope:<select id="scopeId">
  <option value=4>State</option>
  <option value=9>Constituency</option>
 </select>

  <s:select theme="simple"  name="constituency" id="constituencyId" list="constituenciesList" listKey="id" listValue="name" />
 <a class="btn btn-primary" href="javascript:{updateBoothsDetails()}">UPDATE</a>
 <a  class="btn btn-primary" href="javascript:{getCompletionDetailsByConstituencyId();}">TEST</a>
  <a  class="btn btn-primary" href="javascript:{updateBoothsDetails();}">TEST</a>
  <a class="btn btn-primary" href="javascript:{getCompletionDetails()}">GET DETAILS</a>

 <div id="constnLvlDtls" class="span4 offset4"></div>
 <script>
 function getCompletionDetails()
 {
	 if($('#scopeId').val() == 9)
		 getCompletionDetailsByConstituencyId();
	 else
		 getCompletionDetailsForAllConstituencies();

 }
 function getCompletionDetailsByConstituencyId()
 {
	 var jsObj = 
	{
		constituencyId : $('#constituencyId').val()
	}
	
	 $.ajax({
		type:'GET',
		url: 'getSurveyCompletionDetailsOfConstituency.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			buildCompletionDetails(result,"booth");
		});

 }
 function getCompletionDetailsForAllConstituencies()
 {
	
	 $.ajax({
		type:'GET',
		url: 'getSurveyCompletionDetailsForAllConstituencies.action',
		dataType: 'json',
		data: {},
		}).done(function(result){
			buildCompletionDetails(result,"constituency");
		});

 }/*
 function getConstituencyWiseCompletionDetails()
 {
	 
	$.ajax({
		type:'GET',
		url: 'getSurveyCompletionDetailsForAllConstituencies.action',
		dataType: 'json',
		data: {},
		}).done(function(result){
			buildConstituencyLevelCompletionDetails(result);
			console.log(result);
		});
 }*/
 /*
 function buildConstituencyLevelCompletionDetails(result)
 {
	 var str ='';

	 str+='<table class="table table-bordered table-hover table-striped">';
	  str+='<thead class="alert alert-success">';
	   str+='<tr>';
		str+='<th>Constituency</th>';
		str+='<th>Data Collector</th>';
		str+='<th>Verifier</th>';
	   str+='</tr>';
 	  str+='</thead>';

	  str+='<tbody>';
	  $.each(result,function(index,value){
		str+='<tr>';
			str+='<td>'+value.locationName+'</td>';
			if(value.dataCollectorCompleted)
				str+='<td><input type="checkbox" checked/></td>';
			else
				str+='<td><input type="checkbox"/></td>';

		if(value.verifierCompleted)
				str+='<td><input type="checkbox" checked/></td>';
			else
				str+='<td><input type="checkbox"/></td>';
	   str+='</tr>';
	  })
	  str+='</tbody>';
    str+='<table>';

	$('#constnLvlDtls').html(str);
 }
*/
 function buildCompletionDetails(result,type)
 {

	var str ='';

	 str+='<table class="table table-bordered table-hover table-striped">';
	  str+='<thead class="alert alert-success">';
	   str+='<tr>';
		str+='<th>'+type+'</th>';
		str+='<th>Data Collector</th>';
		str+='<th>Verifier</th>';
	   str+='</tr>';
 	  str+='</thead>';

	  str+='<tbody>';
	  $.each(result,function(index,value){
		str+='<tr>';
			str+='<td>'+value.locationName+'</td>';
			if(value.dataCollectorCompleted)
				str+='<td><input type="checkbox" checked value="'+value.locationValue+'" class="completedCollectorChkbox" id="clctr'+value.locationValue+'"/></td>';
			else
				str+='<td><input type="checkbox" value="'+value.locationValue+'" class="completedCollectorChkbox" id="clctr'+value.locationValue+'"/></td>';

		if(value.verifierCompleted)
				str+='<td><input type="checkbox" checked value="'+value.locationValue+'" class="completedVerifierChkbox" id="vrfer'+value.locationValue+'"/></td>';
			else
				str+='<td><input type="checkbox" value="'+value.locationValue+'" class="completedVerifierChkbox" id="vrfer'+value.locationValue+'"/></td>';

	   str+='</tr>';

	  })
	  str+='</tbody>';
    str+='<table>';

	$('#constnLvlDtls').html(str);
 }
 var completedDtls={
	 collectorCompletedBoothIds:[],
     verifierCompletedBoothIds:[],
     constituencyId:'',
	 scopeId:''
 };
 function updateBoothsDetails()
 {
	completedDtls.collectorCompletedBoothIds = [];
	completedDtls.verifierCompletedBoothIds = [];

	completedDtls.scopeId = $('#scopeId').val();

	if( $('#scopeId').val() == 4)
		completedDtls.constituencyId = 0;
	else
		completedDtls.constituencyId = $('#constituencyId').val();

	 $('.completedCollectorChkbox').each(function(index,value){
		 if(this.checked)
			 completedDtls.collectorCompletedBoothIds.push(this.value);
	 });

	  $('.completedVerifierChkbox').each(function(index,value){
		 if(this.checked)
			 completedDtls.verifierCompletedBoothIds.push(this.value);
	 });
	
	
	 $.ajax({
		type:'GET',
		url: 'saveSurveyCompletionDetails.action',
		dataType: 'json',
		data: {task:JSON.stringify(completedDtls)},
		}).done(function(result){
			console.log(result);
		});



	//saveSurveyCompletionDetails
 }
 </script>
 </body>
</html>