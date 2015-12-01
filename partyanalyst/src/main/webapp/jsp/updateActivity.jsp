<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Activity</title>
	<link href="dist/activity/css/bootstrap.min.css" rel="stylesheet"/>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/TDP.gif">
	<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
	<link href='dist/activity/css/activity.custom.css' rel='stylesheet' type='text/css'>
	<link href='dist/activity/Date/daterangepicker-bs3.css' rel='stylesheet' type='text/css'>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>	
	<script src="dist/activity/js/bootstrap.min.js" type="text/javascript"></script> 
	<!--<script type="text/javascript" src="js/bootstrap.js" ></script> -->
	 <script src="dist/activity/Date/moment.min.js" type="text/javascript"></script>
	 <script src="dist/activity/Date/daterangepicker.js" type="text/javascript"></script>
	 
	 <style type="text/css">
		.m_top10{margin-top:10px}
		.input-g1 .form-control{border-radius:0px;border-left:0px}
		.input-g1 .input-group-addon{border-radius:0px;background:#fff;}
	 </style>
	 <!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">  
</head>
<body>
	<form method="POST" enctype="multipart/form-data" name="uploadInsureeDetailsForm" action="saveActivityDetailsAction.action">
<div class="container">


	<div class="row">
   		<div class="col-md-12">
        	<div class="panel panel-default panel-custom">
            	<div class="panel-heading">
                	<h4 class="panel-title">SEARCH TO UPDATE PROGRAM ACTIVITIES</h4>
                </div>
                <div class="panel-body">
                	<div class="row">
                    	<div class="col-md-3">
                        	<img src="img/searchicon.png" style="border-right:1px solid #00B17D">
                        </div>
                        <div class="col-md-9">
                        	<div class="row">
							<div class="col-md-4">
                                	<label>Activity Type</label>
                                    <s:select theme="simple" headerKey="0" headerValue="Select Activity Type" name="surveyType" id="activityTypeList" value="surveyTypeId" list="basicVO.panchayatVoterInfo" listKey="id" listValue="name" onchange="get();" cssClass="input-block-level form-control"/>
                                </div>
                            	<div class="col-md-4">
                                	<label>Activity Level</label>
                                    <s:select theme="simple" headerKey="0" headerValue="Select Activity Level" name="surveyType" id="activityLevelList" value="surveyTypeId" list="idNameVOList" listKey="id" listValue="name" onchange="getActivityNames(this.value);" cssClass="input-block-level form-control"/>
                                </div>
                                <div class="col-md-4">
                                	<label> Activity Name </label>
                                    <select id="ActivityList" class="form-control">
                                    	<option value="0"> Select Activity </option>
                                    </select>
                                </div>
								<!--
                                <div class="col-md-12 m_top10">
                                	<label class="radio-inline">
                                    	<input type="radio">Constituency
                                    </label>
                                    <label class="radio-inline">
                                    	<input type="radio">Mandal/ Town / Division
                                    </label>
                                    <label class="radio-inline">
                                    	<input type="radio">Panchayat/ Ward
                                    </label>
                                </div>
								-->
                                <div class="col-md-4">
                                	<label>Constituency</label>
                                    <select id="constiList" class="form-control" onchange="getMunciMandalsList(this.value)">
                                    	
                                    </select>
                                </div>
                                <div class="col-md-4">
                                	<label >Mandal/ Town/ Division</label>
                                    <select id="mandalsList" class="form-control" onchange="getPanchayatWardByMandal(this.value);">
                                    	
                                    </select>
                                </div>
                                <div class="col-md-4">
                                	<label>Panchayat/ Ward</label>
                                    <select id="villageWardsList" class="form-control">
                                    	
                                    </select>
                                </div>
                                <div class="col-md-4 m_top10">
                                	<button class="btn btn-block btn-custom btn-success" type="button" onclick="getLocationDetailsForActivity();">SEARCH</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12">
        	<div class="panel panel-default panel-custom">
            	<div class="panel-heading">
                	<h4 class="panel-title">SEARCH RESULTS<span class="font-12">- Activity Name(Activity level)</span>
                    <span class="pull-right">
                    	<label class="checkbox-inline">
                        	<input type="checkbox">Show Committee Members
                        </label>
                    </span>
                    </h4>
                </div>
                <div class="panel-body">
                	<div>
                      <!-- Nav tabs -->
                      <ul class="nav nav-tabs nav-tabs-custom" role="tablist">
                        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Planed</a></li>
                        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Conducted</a></li>
                        <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Lately Conducted</a></li>
                        <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Not Conducted</a></li>
                        <button class="btn btn-sm btn-custom btn-success pull-right">SEARCH</button>
                        <li class="pull-right">
                        	<div class="input-group input-g">
                            	<input type="text" class="form-control">
                                <span class="input-group-addon">
                                	<i class="glyphicon glyphicon-search"></i>
                                </span>
                                
                            </div>
                        </li>
                      </ul>
                    
                      <!-- Tab panes -->
                      <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="home">                  	
                     
                        </div>
						       <button class="btn btn-custom btn-success">SAVE</button>     
                        <div role="tabpanel" class="tab-pane" id="profile">...</div>
                        <div role="tabpanel" class="tab-pane" id="messages">...</div>
                        <div role="tabpanel" class="tab-pane" id="settings">...</div>
                      </div>
                    
                    </div>
                </div>
            </div>
        </div>
    </div>
	</form>
	
</div>

<div id="dialogSummaryDistsrict" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">

			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					
					 <div class="modal-header">
					  <button aria-label="Close" data-dismiss="modal" class="close" type="button"><span aria-hidden="true">x</span></button>
						<h3 class="panel-header text-center"></h3>
					  </div>
						<div id="cadreDetailsDiv" style="margin-top:25px;padding:10px;"></div>
				</div>
			</div>
    </div>
<footer>
	<p>All &copy; 2015 Telugu Desam Party</p>
</footer>
<script src="dist/activity/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activity/js/custom.js" type="text/javascript"></script>
<script src="dist/activity/Date/moment.min.js" type="text/javascript"></script>
<script src="dist/activity/Date/daterangepicker.js" type="text/javascript"></script>

<script>
$(document).ready(function(){
	
	
});
function getActivityNames()
{
	var jObj = {
			activityTypeId : $('#activityTypeList').val(),
			activityLevelId:$('#activityLevelList').val(),
			task:"activityDetails"
		};
		
		$.ajax({
          type:'GET',
          url: 'getLocationWiseAsOfNowDetailsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			console.log(result);
		});
		
}
function submitForm(){
	var uploadHandler = {
		upload: function(result) {
			console.log(result);
			uploadResult = result.responseText;
			var myResult = (String)(uploadResult);
			
			if(myResult.search('success') != -1){
				
			}else{
			}
		},
		error: function(){
			console.log('upload error');
		}
		};
	YAHOO.util.Connect.setForm('uploadInsureeDetailsForm',true);
	YAHOO.util.Connect.asyncRequest('POST','saveActivityDetailsAction.action',uploadHandler);
}

function gettingCadreDetails(locationId,locationName){	
	locationId = ""+locationId+"";
	 locationId = locationId.slice(1);
	 
	var locationTypeId = $('#activityLevelList').val();
	var locationType = 5;
	if(locationTypeId == 2)
		locationType = 5;
	
		 var jsObj={
		         locationId:locationId,locationType:locationType,basicCommitteeTypeId:1,type:"committeembrs",casteStateId:0,gender:"",fromAge:0,toAge:0
		       };
			   
		 $.ajax({
			type : "GET",
			url : "gettingCadreDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
			buildingResults(result,locationName);
		});
	}
	
	
function getActivityNames()
{
	$('#ActivityList').find('option').remove();
	$('#ActivityList').append('<option value="0"> Select Activity </option>');	
	var jObj = {
			activityTypeId : $('#activityTypeList').val(),
			activityLevelId:$('#activityLevelList').val(),
			task:"activityDetails"
		};
		
		$.ajax({
          type:'GET',
          url: 'getActivityDetails.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){			
			if(result != null && result.length >0)
			{
				for(var i in result)
					$('#ActivityList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
			}
		});
		
}


function getUserAccessConstituencyList()
{
	$('#constiList').find('option').remove();
	$('#constiList').append('<option value="0"> Select Constituency</option>');	
	var jObj = {
			task:"getUserAccessConstituencyList",
			stateId:1
		};
		
		$.ajax({
          type:'GET',
          url: 'getUserAccessListConstituency.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			
			if(result != null && result.hamletVoterInfo != null && result.hamletVoterInfo.length >0)
			{
				for(var i in result.hamletVoterInfo)
					$('#constiList').append('<option value="'+result.hamletVoterInfo[i].id+'">'+result.hamletVoterInfo[i].name+'</option>');
			}
		});
		
}


function getMunciMandalsList(constituencyId)
{
	$('#mandalsList').find('option').remove();
		$('#mandalsList').append('<option value="0"> Select Mandal/Town/Division</option>');
	var jObj = {
			task:"getUserAccessConstituencyList",
			locationId:constituencyId
		};
		
		$.ajax({
          type:'GET',
          url: 'getMandalCorporationsByConstituencyAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			
			if(result != null && result.length >0)
			{
				for(var i in result)
					$('#mandalsList').append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
			}
		});
		
}

function getPanchayatWardByMandal(mandalId){
		     $('#villageWardsList').find('option').remove();
			 $('#villageWardsList').append('<option value="0"> Select Mandal/Town/Division</option>');	
			var jsObj={
				mandalId:mandalId
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				
				if(result != null && result.length >0)
				{
					for(var i in result)
						$('#villageWardsList').append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
				}
		});	
			
	}
	
	
function getLocationDetailsForActivity()
{
	
	var jObj = {
		checkedId:"2",
		activityScopeId:$('#ActivityList').val(),
		//activityLevelId:$('#activityLevelList').val(),
		activityLevelId:5,
		searchBy:"Constituency",
		locationId:$('#constiList').val(),
		task:"getLocationDetailsForActivity"
	};		
	$.ajax({
          type:'GET',
          url: 'getLocationDetailsForActivity.action',
         data : {task:JSON.stringify(jObj)} ,
     }).done(function(result){			
			//console.log(result);
			var str='';
			if( result!= null)
			{
				str+='<table class="table table-bordered bg_ff" id="locationsTab">';
				str+='<thead>';
				str+='<tr>';
				//str+='<th>CONSTITUENCY</th>';
				str+='<th>MANDAL/ TOWN/ DIVISION</th>';
				//str+='<th>PANCHAYAT/ WARD</th>';
				str+='<th>PLANNED DATE</th>';
				str+='<th>CONDUCTED DATE</th>';
				str+='<th>PRESIDENT</th>';
				str+='<th>GENERAL SECRETARY</th>';
				str+='<th>OTHER MEMBERS</th>';
				str+='</tr>';
				str+='</thead>';
				
				if(result.result != null && result.result.length>0){
					for(var i in result.result)
					{
						str+='<tr>';
						//str+='<td></td>';
						str+='<td>'+result.result[i].locationName+'</td>';
						str+='<td  style="text-align:center;">';
						str+='<div class="input-g1 input-group">';
							str+='<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>';
							str+='<input type="text" class="dateCls form-control" name="'+i+'" />';
						str+='</div></td>';
						str+='<td  style="text-align:center;">';
						str+='<div class="input-g1 input-group">';
							str+='<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>';
							str+='<input type="text" class="dateCls form-control" name="'+i+'" />';
						str+='</div></td>';
						str+='<td  style="text-align:center;"> President </td>';
						str+='<td  style="text-align:center;"> General Secretary </td>';
						str+='<td style="text-align:center;"> <input type="button" value="View" class="btn btn-success btn-xs" onclick="gettingCadreDetails('+result.result[i].locationId+',\''+result.result[i].locationName+'\');"/></td>';
						str+='</tr>';
					}
					str+='</table>';
				}
			}
			$('#home').html(str);
			$('.dateCls').daterangepicker({singleDatePicker:true});
		});		
}

getUserAccessConstituencyList();

function buildingResults(result,locationName){
	
	var str = '';
	
			str+='<table class="table table-bordered" id="constiTableId">';
		str+='<thead>';
		str+='<th style="width:50px;"> </th>';
		str+='<th style="padding-left: 72px;"> MEMBER </th>';
		str+='<th style="padding-left: 19px;"> MOBILE NO </th>';
		str+='<th style="padding-left: 19px;"> AGE </th>';
		str+='<th style="padding-left: 19px;"> GENDER </th>';
		str+='<th style="padding-left: 19px;"> CASTE NAME </th>';
		//str+='<th style="padding-left: 19px;"> VOTER ID </th>';
		str+='</thead>';
		for(var i in result){
		 str+='<tr>';
		str+='<td><img  style="margin-top: 5px;" width="50"  height="50" src="http://www.mytdp.com/images/cadre_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);"/>';
		
		 str+=' </td>';
		 str+='<td> '+result[i].name+' ';
		 //if(basicCmmtyId != 1){
		 if(result[i].commiteeName!=null){
			 str+='<br>'+result[i].commiteeName+' - ';
		 }else{
			 str+='<br>';
		 }
		/* }
		else{
			 str+='<br>';
		 }*/
		 
		  //if(basicCmmtyId == 1){
		 if(result[i].role!=null){
			 str+=' '+result[i].role+'';
		 }else{
			 str+='';
		 }
		str+=' <br/> <span> Constituency : '+result[i].constituencyName+' </span>';
		str+=' <br/> <span> MemberShipNo : '+result[i].id+' </span>';
		//str+=' <br/> <span> MemberShipNo : <a target="_blank" href="cadreDetailsAction.action?cadreId='+result[i].id+'"> '+result[i].id+' </a> </span>';
		  str+=' </td>';	  
		str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].mobileNo+'</td>';
		 str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].age+' </td>';
		 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].gender+' </td>';
		 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].casteName+'('+result[i].casteGroupName+') </td>';
		 // str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].voterCardNo+' </td>';
		 str+='</tr>';
		}
	   str+='</tbody>';
	   str+='</table>';
	
	$("#cadreDetailsDiv").html(str);
	
	$('#dialogSummaryDistsrict').find('h3').html('<span>'+locationName+' Main Committee Members </span>');
	 $("#dialogSummaryDistsrict").modal("show");
}
</script>
</body>
</html>
