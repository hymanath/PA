<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Committee Member Details</title>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/css/normalize.css" rel="stylesheet" type="text/css">
<link href="dist/css/cs-skin-slide.css" rel="stylesheet" type="text/css">
<link href="dist/css/cs-select.css" rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
<link href="dist/MultiSelect/css/component.css" rel="stylesheet" type="text/css">
<link href="dist/css/fm.scrollator.jquery.css" rel="stylesheet" type="text/css">

<script src="dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/js/classie.js" type="text/javascript"></script>
<script src="dist/js/selectFx.js" type="text/javascript"></script>
<script src="dist/MultiSelect/js/magicselection.js" type="text/javascript"></script>
<script src="dist/MultiSelect/js/modernizr.custom.js" type="text/javascript"></script>

<script src="dist/js/fm.scrollator.jquery.js" type="text/javascript"></script>

	<!-- Bootstrap -->
    <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet"/>
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/css/style.css" rel="stylesheet"/>
    	<!--Bootstrap DatePicker-->
    <link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" />
		<!--Hover Menu-->
    <link href="css/cadreCommitee/css/jquery.smartmenus.bootstrap.css" rel="stylesheet" />
    	<!--Circle-->
    <link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" />	
    <script type="text/javascript" src="js/exportexcel.js"></script>
		<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
			<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<style>
.paginate_disabled_previous,.paginate_enabled_previous,.paginate_enabled_next{
   padding-bottom: 10px;
}
.prev,.next {width:50px !important}
.prev,.next {padding-right: 36px !important}
.table-bordered > thead > tr > th,
.table-bordered > tbody > tr > th,
.table-bordered > tfoot > tr > th,
.table-bordered > thead > tr > td,
.table-bordered > tbody > tr > td,
.table-bordered > tfoot > tr > td { border: 1px solid #cfcfcf;}

.table-yellow-bordered > thead > tr > th,
.table-yellow-bordered > tbody > tr > th,
.table-yellow-bordered > tfoot > tr > th,
.table-yellow-bordered > thead > tr > td,
.table-yellow-bordered > tbody > tr > td,
.table-yellow-bordered > tfoot > tr > td { border: 1px solid #cfcfcf;}
	.circle-text{
		line-height: 180px; font-size: 34px; top: 32px;  left: 0px; !important
	}
	.circle-info-half{
		line-height: 225px; left: 0px;  top: 34px;!important
	}
	.highlight{background-color: #fff !important;
	border-color: #ccc !important;
	color: #333 !important;}
	.navbar-nav > li > a {text-decoration:none;}
		a:hover {text-decoration:none;}
		.multiLevelLiA a{text-transform: uppercase;color:black;}
		body {
    background: url("") repeat scroll 0 0 !important;
}
#constiTableId tr.odd td.sorting_1,#districtTableId tr.odd td.sorting_1{
    background-color: #d3d3d3 !important;
}
#constiTableId tr.even td.sorting_1 , #districtTableId tr.even td.sorting_1{
    background-color: #fafafa !important;
}
#constiTableId tr.odd,#districtTableId tr.odd {
    background-color: #f3f3f3 !important;
}

.height-auto{height:auto !important;}
.height-0{height:0px !important;}

.cs-options li span {
    font-size: 65%;
    font-weight: 500;
	color:#FFDB2B;
}

</style>
</head>
<body class="background-bg">

<div class="container m_top30" style="min-height:340px;">
	<div aria-multiselectable="true" role="tablist" id="" class="panel-group">
      <div class="panel panel-default" style="margin-top: 25px;">
       

          <div class="panel-body">
							
				<div class="col-md-3" id="levelDivId">
					<section>
						<label class="select-label">Level</label>
						<select class="cs-select cs-skin-slide" id="levelId">
						   <option value="0" >ALL</option>							  
						</select>
					</section>
				</div>
				
				
				<div class="col-md-3" id="districtDiv">
					<section>
							<label class="select-label">District</label>
							<select class="cs-select cs-skin-slide" id="districtId">
							   <option value="0" >ALL</option>
							  
							</select>
					</section>
				</div>
				
				<div class="col-md-2" id="constituencyDiv">
					<section>
							<label class="select-label">Assembly</label>
							<select class="cs-select cs-skin-slide">
								<option value="0" >ALL</option>
							   
							</select>
					</section>
				</div>
				<div class="col-md-2" id="mandalDiv">
					<section>
							<label class="select-label">Mandal/Muncipality</label>
							<select class="cs-select cs-skin-slide">
								<option value="0">ALL</option>
													  
							   
							</select>
					</section>
				</div>
				<div class="col-md-2" id="panchayatDiv">
					<section>
							<label class="select-label">Panchayat</label>
							<select class="cs-select cs-skin-slide">
								<option value="0" >ALL</option>
							   
							</select>
					</section>
				</div>
				
				<div class="col-md-12 m_top20">
				
					  
					  <label class="checkbox-inline">
						<input type="checkbox" id="cadreCommitteeId" value="2" onclick="getDetails('cadreCommitteeId');"> From Committees
					  </label>
					  <!-- <label class="checkbox-inline">
						<input type="checkbox" id="publicRepresentativesId" value="1" onclick="getDetails('publicRepresentativesId');">  Public Repracentative
					  </label>
					  <label class="checkbox-inline">
						<input type="checkbox" id="groupId" value="3" onclick="getDetails('groupId');"> Existing Groups
					  </label>
					  -->
				  
				</div>
			   
				<div class="col-md-4 m_top20" style="background-color:#FFF;display:none;" id="cadreCommitteeDiv">
				
					<div  id="committeeDiv">
					<label class="select-label" style="margin-left:0px "> From Committees </label>
					<select class="cs-select cs-skin-slide">
						<option value="0" >ALL</option>
					</select>
					</div>
					
					<div>
						<form class="me-select">
							<ul id="me-select-list" class="">
								<li class="">
									<input id="checkAll" class="" type="checkbox" name="cb10" >
									<label class="m_0" style="padding:0px 10px 0px 45px;margin-top:10px;" for="cb10">
										<span class="text-col-head" id="checkText">Select All</span>
									</label>
								</li>
							</ul>
						</form>
					<hr class="m_0" style="border-top:1px solid #01b6ad;">
					<form class="me-select" id="rolesForm">
						
					</form>
				  </div>
					<!--<div class="text-bold" style="padding-top:30px;">Select Committee Members Designation to Create Group <br/>(or) Download Members Details</div>-->
				</div>
				
				<div class="col-md-4 m_top20" style="background-color:#FFF;display:none;" id="representativesDiv">
					<div  id="committeeDiv">					
					<label class="select-label" style="margin-left:0px "> From Public Representatives </label>
					<select class="cs-select cs-skin-slide">
						<option value="0" >ALL</option>
					</select>
					</div>
					
					<div>
					<form class="me-select">
					<ul id="me-select-list" class="">
					<li class="">
					<input id="checkAll" class="" type="checkbox" name="cb10" >
					<label class="m_0" style="padding:0px 10px 0px 45px;margin-top:10px;" for="cb10">
					<span class="text-col-head" id="checkText">Select All</span>
					</label>
					</li>
					</ul>
						</form>
					<hr class="m_0" style="border-top:1px solid #01b6ad;">
					<form class="me-select" id="rolesForm">
						
					</form>
				  </div>
					<!--<div class="text-bold" style="padding-top:30px;">Select Committee Members Designation to Create Group <br/>(or) Download Members Details</div>-->
				</div>
				
				<div class="col-md-4 m_top20" style="background-color:#FFF;display:none;" id="existingGroupsDiv" >
					<div  id="committeeDiv">
					<label class="select-label" style="margin-left:0px ">From  Existing Groups  </label>
					<select class="cs-select cs-skin-slide">
						<option value="0" >ALL</option>
					</select>
					</div>
					
					<div>
					<form class="me-select">
					<ul id="me-select-list" class="">
					<li class="">
					<input id="checkAll" class="" type="checkbox" name="cb10" >
					<label class="m_0" style="padding:0px 10px 0px 45px;margin-top:10px;" for="cb10">
					<span class="text-col-head" id="checkText">Select All</span>
					</label>
					</li>
					</ul>
						</form>
					<hr class="m_0" style="border-top:1px solid #01b6ad;">
					<form class="me-select" id="rolesForm">
						
					</form>
				  </div>
					<!--<div class="text-bold" style="padding-top:30px;">Select Committee Members Designation to Create Group <br/>(or) Download Members Details</div>-->
				</div>
				
				<div class="col-md-12 m_top20" id="candidateDetailsDiv" style="display:none">
						<div class="border-box">
							
							 <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" style="margin-top:-20px;display:none;">
						  </div>
							<div id="countDiv"  class="panel panel-success"></div>	
							 <div class="row">
							
								<div class="col-md-12 col-xs-12 col-md-12" style="">
								 <center><img id="summaryAjax" style="width:100px;height:80px;display:none;"  src="./images/Loading-data.gif" alt="Processing Image"/></center>

								<div id="buildSearchDetailsStateId"  class="panel panel-success" style="margin-top: 0px;display:none;"></div>	
								<div id="buildSearchDetailsdistrictId"  class="panel panel-success" style="margin-top: 0px;display:none;"></div>	
								<div id="buildSearchDetailsMandalId"  class="panel panel-success" style="margin-top: 0px;display:none;"></div>	
								<div id="buildSearchDetailsPanchayatId"  class="panel panel-success" style="margin-top: 0px;display:none;"></div>	
								
								</div>
								<div id="paginationDivId" class="paginationDiv" style="width:600px;margin-left:380px;"></div>
							</div>
							
							<div class="download-box display-style" style="display:none;">
								<div class="display-style">
									<span class="text-italic text-bold" style="padding-left:10px">Download Now</span><br>
									<div style="padding-top:10px">
										<div class="download-image display-style">
											<img src="dist/images/pdf.png">
											<span>PDF</span>
										</div>
										<div class="download-image display-style">
											<img src="dist/images/word.png">
											<span>Word</span>
										</div>
										<div class="download-image display-style">
											<a href="javascript:{exportConstituencyToExcel('searchTableId');}" ><img src="dist/images/excel.png" >
											<span >Excel</span></a>
										</div>
									</div>
								</div>
								<img src="dist/images/or.png">
								<div class="pull-right" style="padding-top:10px;padding-left:10px">
									<span>ADD TO GROUP</span><br/>
										<div class="input-group pull-right" style="width:300px;">
										  <input type="text" id="groupNameId" class="form-control" placeholder="Enter Group Name" aria-describedby="basic-addon2" style="height: 33px;"/>
										  <span class="input-group-addon" id="addGroupId" style="background-color:#01b6ad;color:#fff;cursor:pointer" onclick="getMembersDetails();">ADD TO GROUP</span>
										</div><br/><br/>
									<span class="viewlink">Click To View Existed group Names</span>    
								</div>
							</div>
							
						</div>
				</div>
		 
				  </div>
			  </div>
	
    </div>

	<!---<div class="well br_0 container m_top30" style="background-color:#fff">
    	<h3 class="m_0 text-left">COMMITTEE MEMBER DETAILS <span aria-hidden="true" class="rotate pull-right glyphicon glyphicon-menu-down"></span></h3>
		
    </div> ---->
	<div id="smsDialogueBoxDiv">
		<div id="smsMessageDiv" style="display:none;"> 
				
		</div>
	</div>
	</div>

<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/js/classie.js" type="text/javascript"></script>
<script src="dist/js/selectFx.js" type="text/javascript"></script>
<script src="dist/MultiSelect/js/magicselection.js" type="text/javascript"></script>
<script src="dist/MultiSelect/js/modernizr.custom.js" type="text/javascript"></script>
<script src="dist/js/fm.scrollator.jquery.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	
		<script>
			(function() {
				[].slice.call( document.querySelectorAll( 'select.cs-select' ) ).forEach( function(el) {	
					new SelectFx(el);
				} );
				
				$(".cs-placeholder").click(function(){
					$(".scrollbar").scrollator({
					zIndex: '10000',
					});
					
				});
	//$(".scrollbar").niceScroll({touchbehavior:false,cursorcolor:"#FFF",cursoropacitymax:0.7,cursorwidth:6,autohidemode:true,zindex:"12000",oneaxismousemode:false});
						
				/*Inputs Multi Select*/
				var selList = document.getElementById( 'me-select-list' ),
					items = selList.querySelectorAll( 'li' );
				
				// fill the initial checked elements (mozilla)
				[].slice.call( items ).forEach( function( el ) {
					el.parentNode.className = el.checked ? 'selected' : '';
				} );

				function checkUncheck( el ) {
					el.parentNode.className = el.checked ? '' : 'selected';
					el.checked = !el.checked;
				}

				new magicSelection( selList.querySelectorAll( 'li > input[type="checkbox"]' ), {
					onSelection : function( el ) { checkUncheck( el ); },
					onClick : function( el ) {
						el.parentNode.className = el.checked ? 'selected' : '';
					}
				} );
			
				
			})();
			
		</script>
<script type="text/javascript">

 var locationsArr = new Array();
 var glstateId = 0;
 var gldistrictId = 0;
 function getDistricts(){
	  $("#districtId").find('option').remove();
	 
     var jsObj=
		{				
				stateId:1,
				stateTypeId :1,
				elmtId:"districtList_d",
                type:"default",
				task:"findDistrictsForAState"				
		}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForUserAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   var str ='';
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
   if(result != null)
   {
	   if(result.length == 11)
	   {
		   glstateId=2;
	   }
	   else if(result.length == 14)
	   {
		   glstateId=1;
	   }
	   else if(result.length == 2)
	   {
		   gldistrictId=result[1].id;
	   }	   
   }
   console.log("result.length  :"+result.length);
   console.log("glstateId  :"+glstateId);
		  str+='<section>';
          str+='<label class="select-label">District</label>';
		  str+=' <div class="cs-select cs-skin-slide distSlide" tabindex="0" onclick="selectChange(\'distSlide\')">';
		  str+='<span class="cs-placeholder distName" value="0">ALL</span><div class="cs-options"><ul class="scrollbar distList">';
		  str+='<li data-value="0" data-option="" class="distEle"><span>ALL</span></li>';
		  for(var i in result)
		  {
		   if(result[i].id > 0)
		  str+='<li data-value="'+result[i].id+'" data-option="" class="distEle"><span>'+result[i].name+'</span></li>';
		  }
		  str+='</ul>';
		  str+='</div><select class="cs-select cs-skin-slide" id="districtId">';
		  for(var i in result)
		  {
		   if(result[i].id > 0)
          str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		  }
		 str+='</select></div></section>';
		$("#districtDiv").html(str);
		
		/*str+='<select class="cs-select cs-skin-slide">';
       str+='<option value="district">District</option>';
       str+='<option value="mandal">Mandal</option>';
       str+='</select>';
	   str+='</div>';*/
	   
	   /*if(result[i].id == 0){
          $("#districtId").append('<option value='+result[i].id+'>Select District</option>');
	   }else{
	      $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }*/
	 
   });
  }

  var isEntered=false;
  function getConstituenciesForDistricts(districtId){
  
  if(!isEntered)
  {
	isEntered = true;
	  setDefault();
		$("#constituencyId").find('option').remove();
		//var districtId =  $("#districtId").val();
		var jsObj=
	   {				
					districtId:districtId,
					elmtId:"districtList_d",
					type:"default",
					task:"getConstituenciesForDistricts"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getConstituenciesForADistrictAjaxAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   isEntered = false;
	   var str='';
		   str+='<section>';
			  str+='<label class="select-label">Assembly</label>';
			  str+=' <div class="cs-select cs-skin-slide constiSlide" tabindex="0" onclick="selectChange(\'constiSlide\')">';
			  str+='<span class="cs-placeholder constiName" value="0">ALL</span><div class="cs-options"><ul class="scrollbar constiList">';
			  str+='<li data-value="0" data-option="" class="constiEle"><span>ALL</span></li>';
			  for(var i in result)
			  {
			   if(result[i].id > 0)
			  str+='<li data-value="'+result[i].id+'" data-option="" class="constiEle"><span>'+result[i].name+'</span></li>';
			  }
			  str+='</ul>';
			  str+='</div><select class="cs-select cs-skin-slide" id="constituencyId">';
			  for(var i in result)
			  {
			   if(result[i].id > 0)
			  str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			  }
			 str+='</select></div></section>';
			$("#constituencyDiv").html(str);
		
		 /*for(var i in result){
		   if(result[i].id == 0){
			  $("#constituencyId").append('<option value='+result[i].id+'>Select Constituency</option>');
		   }else{
			  $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		 }*/
	   });
  }
	
  }

  function getTehsils(constiId){
	  if(!isEntered)
	  {
		isEntered = true;
		  $("#mandalId").find('option').remove();
			//var constiId =  $("#constituencyId").val();

			  if(constiId == 0){
				return;
			  }
			  var jsObj=
				{
					id:constiId,
					task:"subRegionsInConstituency",
					taskType:"",
					selectElementId:"",
					address:"",
					areaType:"null",
					constId:constiId				
				}
			$.ajax({
				  type:'GET',
				  url: 'locationsHierarchiesAjaxAction.action',
				  dataType: 'json',
				  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   isEntered = false;
		   var str='';
			   str+='<section>';
				  str+='<label class="select-label">Mandal/Muncipality</label>';
				  str+=' <div class="cs-select cs-skin-slide mandalSlide" tabindex="0" onclick="selectChange(\'mandalSlide\')">';
				  str+='<span class="cs-placeholder mandalName" value="0">ALL</span><div class="cs-options"><ul class="scrollbar mandalList">';
				   str+='<li data-value="0" data-option="" class="mandalEle"><span>ALL</span></li>';
				  for(var i in result)
				  {
				  if(result[i].id > 0)
				  str+='<li data-value="'+result[i].id+'" data-option="" class="mandalEle"><span>'+result[i].name+'</span></li>';
				  }
				  str+='</ul>';
				  str+='</div><select class="cs-select cs-skin-slide" id="mandalId">';
				  for(var i in result)
				  {
				   if(result[i].id > 0)
				  str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				  }
				 str+='</select></div></section>';
				$("#mandalDiv").html(str);
			
			/* for(var i in result){
			   if(result[i].id == 0){
				  $("#mandalId").append('<option value='+result[i].id+'>Select Mandal</option>');
			   }else{
				  $("#mandalId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
			 }*/
		   });	
	  }
  }
    function getPanchayats(mandalId){
		
		if(!isEntered)
		{
			isEntered = true;
			 $("#panchayatId").find('option').remove();
			//var mandalId =  $("#mandalId").val();
			  if(mandalId == 0){
				return;
			  }
			  var jsObj={
						mandalId :mandalId
					}
			 $.ajax({
						type:"POST",
						url :"getPanchayatDetailsAction.action",
						 dataType: 'json',
						data: {task:JSON.stringify(jsObj)}
					}).done(function(result){
						isEntered = false;
			  var str='';
			   str+='<section>';
				  str+='<label class="select-label">Panchayat</label>';
				  str+=' <div class="cs-select cs-skin-slide panchayatSlide" tabindex="0" onclick="selectChange(\'panchayatSlide\')">';
				  str+='<span class="cs-placeholder panchayatName" value="0">ALL</span><div class="cs-options"><ul class="scrollbar panchayatList">';
				  str+='<li data-value="0" data-option="" class="panchayatEle"><span>ALL</span></li>';
				  for(var i in result)
				  {
				   if(result[i].id > 0)
				  str+='<li data-value="'+result[i].id+'" data-option="" class="panchayatEle"><span>'+result[i].name+'</span></li>';
				  }
				  str+='</ul>';
				  str+='</div><select class="cs-select cs-skin-slide" id="panchayatId">';
				  for(var i in result)
				  {
					if(result[i].id > 0)
				  str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				  }
				 str+='</select></div></section>';
				$("#panchayatDiv").html(str);
				
			/* $("#panchayatId").append('<option value="0">Select Panchayat/Ward</option>');
			 for(var i in result){
			   $("#panchayatId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			 }*/
		   });
		}
  } 

    
 /* function getdistricts(){
	var selState = $("#stateId").val();
	var jsObj={
			stateid:selState
		}
		$.ajax({
			  type:'GET',
			  url: 'getDistrictsByStateWiseAction.action',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
			 $("#districtId").append('<option value="0">Select District</option>');
			 for(var i in result){
			   $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			 }
	   });	
	
  }*/

    function getCommittees(){
	isEntered=false;
	var jsObj={
			task:"allCommittess"
		}
		$.ajax({
			  type:'GET',
			  url: 'getAllCommitteesAction.action',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
	     var str='';
	   str+='<section>';
          str+='<label class="select-label">From Committees</label>';
		  str+=' <div class="cs-select cs-skin-slide committeeSlide" tabindex="0" onclick="selectChange(\'committeeSlide\')">';
		  str+='<span class="cs-placeholder committeeName" value="0">ALL</span><div class="cs-options"><ul class="scrollbar comitteeList">';
		  str+='<li data-value="0" data-option="" class="committeeEle"><span value="0">ALL</span></li>';
		  for(var i in result)
		  {
		  if(result[i].id > 0)
		  str+='<li data-value="'+result[i].id+'" data-option="" class="committeeEle"><span value="'+result[i].id+'">'+result[i].name+'</span></li>';
		  }
		  str+='</ul>';
		  str+='</div><select class="cs-select cs-skin-slide" id="committeeId">';
		  for(var i in result)
		  {
		   if(result[i].id > 0)
          str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		  }
		 str+='</select></div></section>';
		$("#committeeDiv").html(str);
			 /*$("#committeeId").append('<option value="0">Select Committee</option>');
			 for(var i in result){
			   $("#committeeId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			 }*/
	   });	
	
  }
    function getCommitteeRoles(){
    	
    	var jsObj={
    			task:"roles"
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getAllCommitteesAction.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
		   var str ='';
    		str+='<ul id="me-select-list" >';
			for(var i in result)
			{
            str+='<li><input id="role'+result[i].id+'" name="'+result[i].name+'" type="checkbox" value="'+result[i].id+'" class="roleCheck" onclick="addCommitteeDivs(\'roleCheck\');"><label for="cb10"  style="padding:0px 10px 0px 45px;margin-top:10px;"><span class="roleName">'+result[i].name+'</span></li>';
			}
           str+=' </ul>';
		    $("#rolesForm").html(str);
    	   });	
		  
    	
      }
	  
	  
  function setDefault()
  {
	  $("#constituencyId").find('option').remove();
	  $("#mandalId").find('option').remove();
	  $("#panchayatId").find('option').remove();
	  $("#constituencyId").append('<option value=0>Select Constituency</option>');
	  $("#mandalId").append('<option value=0>Select Mandal</option>');
	  $("#panchayatId").append('<option value=0>Select Panchayat</option>');
  }
  /*$(".distSlide div").click(function(){
			alert('a');
//$(this).addClass("cs-active");
			});*/
			function selectChange(divEle)
			{
			//alert('se')
			$(".roleCheck").prop('checked', false);
			$(".scrollbar").scrollator({
					zIndex: '10000',
					});
			if($("."+divEle).hasClass("cs-active"))
			$("."+divEle).removeClass("cs-active");
			else
			$("."+divEle).addClass("cs-active");
		
		 $(".stateEle").click(function(e)
			 {			
				$(".stateName").html($(this).text());
				$(".stateName").attr("value",$(this).attr("data-value"));
				
				$(".stateEle").removeClass('cs-selected');
				$(this).addClass('cs-selected');
				
			  });
			 $(".distEle").click(function(e)
			 {	

				$(".distName").html($(this).text());
				$(".distName").attr("value",$(this).attr("data-value"));
				
				$(".distEle").removeClass('cs-selected');
				$(this).addClass('cs-selected');
				getConstituenciesForDistricts($(this).attr("data-value"));
			  });
			  $(".constiEle").click(function()
				{	
				$(".constiName").html($(this).text());
				$(".constiName").attr("value",$(this).attr("data-value"));
				$(".constiEle").removeClass('cs-selected');
				$(this).addClass('cs-selected');
				//alert($(this).attr("data-value"));
				 getTehsils($(this).attr("data-value"));
			  });
			    $(".mandalEle").click(function()
				{	
				$(".mandalName").html($(this).text());
				$(".mandalName").attr("value",$(this).attr("data-value"));
				$(".mandalEle").removeClass('cs-selected');
				$(this).addClass('cs-selected');
				//alert($(this).attr("data-value"));
				 getPanchayats($(this).attr("data-value"));
			  });
			  $(".panchayatEle").click(function()
				{	
				$(".panchayatName").html($(this).text());
				$(".panchayatEle").removeClass('cs-selected');
				$(".panchayatName").attr("value",$(this).attr("data-value"));
				$(this).addClass('cs-selected');
				//alert($(this).attr("data-value"));
				
			  });
			   $(".committeeEle").click(function()
				{	
				$(".committeeName").html($(this).text());
				$(".committeeName").attr("value",$(this).attr("data-value"));
				$(".committeeEle").removeClass('cs-selected');
				$(this).addClass('cs-selected');
				//alert($(this).attr("data-value"));
				
			  });
			 
			}
			$(document).ready(function()
			{
			
				$("#checkAll").click(function()
				{
					if($(this).is(":checked"))
					{
				
						$(".roleCheck").prop('checked', true);
						$("#checkText").html("UnSelect All");
							addCommitteeDivs('roleCheck');
					}
					else{
						$(".roleCheck").prop('checked', false);
						$("#checkText").html("Select All");
					}
				});
			
		
		
		});
		
		$(document).on('click', function (e) {
					if ($(e.target).closest(".cs-skin-slide").length === 0) {
					 $(".cs-skin-slide").removeClass("cs-active");;
				}
							
			});
			
			var totalCommiteCnt = 0;
			var roleArr = new Array();
			function addCommitteeDivs(className)
			{
			$(".toggleCls").removeClass("in");
			var commite = $(".committeeName").html();
			var commiteId = $(".committeeName").attr("value");
			
			$("#"+commiteId+"Div").remove();
		    
			var str ='';
			
			str+='<div class="panel panel-default border_0 commiteHeadDiv" id="'+commiteId+'Div">';
			
            str+='<div class="panel-heading collapse-head" role="tab" id="headingComm'+commiteId+'">';
            str+='<h4 class="panel-title">';
            str+='<form class="me-select display-style">';
            str+='<ul id="me-select-list">';
            str+='<li><input id="cb11" name="cb11" checked="true" type="checkbox" class="addedcommite">';
            str+='<label for="cb11" class="m_0 collapse-select"><span class="text-col-head"><a data-toggle="collapse" data-parent="#accordion" href="#collapseComm'+commiteId+'" aria-controls="collapseComm'+commiteId+'" class="col-drop-head" onClick="toggleDiv(\'collapseComm'+commiteId+'\')">'+commite+'</a></span></label></li>';
           str+=' </ul>';
          str+=' </form>';
         str+='<a data-toggle="collapse" data-parent="#accordion" href="#collapseComm'+commiteId+'" aria-expanded="true" aria-controls="collapseComm'+commiteId+'" onClick="toggleDiv(\'collapseComm'+commiteId+'\')">';
         str+='<i class="glyphicon glyphicon-chevron-down pull-right display-style col-drop-color"></i>';
          str+='</a>';
        str+='</h4>';
      str+='</div>';
      str+='<div id="collapseComm'+commiteId+'" class="panel-collapse collapse toggleCls in" role="tabpanel" aria-labelledby="headingComm'+commiteId+'" >';
      str+='<div class="panel-body">';
      str+='<form class="me-select display-style">';
      str+='<ul id="me-select-list">';
	  
	  $("."+className).each(function()
			{
			 if($(this).is(":checked")) {
			  var roleId = $(this).val();
			  var role = $(this).attr("name");
			  str+=' <li><input  class="checkedCls" name="cb11" checked="true" type="checkbox" id="comm'+commiteId+'role'+roleId+'" committeeId = "'+commiteId+'" value="'+roleId+'">';
			  str+='<label for="cb12" class="m_0 collapse-select"><span class="col-drop-select-name">'+role+'<span class="text-italic text-selectbox-bracket">('+role+')</span></span></label></li>';
	   }
	   })
	   
	   str+='</ul></form>';
      str+='</div>';
     str+=' </div>';
	 
   str+='</div>';
  $("#accordion").append(str);
  
  $("#candidateDetailsDiv").show();
  
  getMembersDetails(0);
}
	var globalJsonArr = new Array();
	function getMembersDetails(startIndex)
	{
		isEntered=false;
		var groupName = $('#groupNameId').val();
		var committeeIdsArr = new Array();
		$('#summaryAjax').show();
		$('.checkedCls').each(function(){

			if($(this).is(':checked'))
			{
				var roleId = $(this).attr('value');
				var committeeId = $(this).attr('committeeid');
				var isCommitteeExist = true;
				if(committeeIdsArr.length>0)
				{
					for(var i in committeeIdsArr)
					{						
						if(committeeIdsArr[i].committeeId == committeeId)
						{
							isCommitteeExist = false;
							rolesIdsArr = committeeIdsArr[i].rolesIds;
							rolesIdsArr.push(roleId);
						}
					}
				}	
				
				if(isCommitteeExist)				
				{
					var rolesIdsArr = new Array();
						rolesIdsArr.push(roleId);
						var committeeObj = {
						committeeId : committeeId,
						rolesIds:rolesIdsArr
						}
						committeeIdsArr.push(committeeObj);
				}
			}
		});
		//$('#addGroupId').hide();
		var levelValue = 0;
		var divId = '';
		var levelId = $(".stateName").attr('value');
		var locationLevel = '';
		if(levelId == 10)
		{
			levelValue = 0;
			divId="buildSearchDetailsStateId";
			locationLevel = "State";
		}
		else if(levelId == 11)
		{
			levelValue = $(".distName").attr("value");
			divId="buildSearchDetailsdistrictId";
			locationLevel = "District";
		}
		else if(levelId == 5)
		{
			levelValue = $(".mandalName").attr("value");
			divId="buildSearchDetailsMandalId";
			locationLevel = "Mandal/Town/Division";
		}
		else if(levelId == 6)
		{
			levelValue = $(".panchayatName").attr("value");
			divId="buildSearchDetailsPanchayatId";
			locationLevel = "Village/Ward";
		}	
		
		var districtId = $(".distName").attr("value");		
		var constituencyId = $(".constiName").attr("value");
		var mandalId = $(".mandalName").attr("value");
		var panchayatId = $(".panchayatName").attr("value");
		
		if(typeof districtId == 'undefined')
		{
			districtId = 0;
		}
		if(typeof constituencyId == 'undefined')
		{
			constituencyId = 0;
		}
		if(typeof mandalId == 'undefined')
		{
			mandalId = 0;
		}
		if(typeof panchayatId == 'undefined')
		{
			panchayatId = 0;
		}
		if(typeof levelValue == 'undefined')
		{
			levelValue = 0;
		}
		//$('#buildSearchDetailsId').html('');
		var jsObj =
		{
			searchType:"getDetails",
			groupName:groupName,
			eventId :0,
			committeeLevelId : levelId,
			committeeLevelValue:levelValue,
			committeeIdsArr : committeeIdsArr,
			stateId:glstateId,
			districtId:districtId,
			constituencyId:constituencyId,
			mandalId:mandalId,
			panchayatId:panchayatId,
			startIndex:startIndex,
			maxIndex : 100,	
			sortBy:"",
					
		};
		
		 	$('html, body').animate({scrollTop:$('#summaryAjax').offset().top}, 'slow');
		$.ajax({
          type:'GET',
          url: 'getinviteesMembersDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   //console.log(result);
			  
			   $('#addGroupId').show();
			   	$('#summaryAjax').hide();
			   if(result != null && result.length>0)
			   {
					if(result[0].responseCode == 0)
					{
						globalJsonArr.push(jsObj);
						 $('#'+divId+'').show();
						buildSearchDetails(result[0].cadreComitteeVOList,divId,locationLevel,jsObj,result[0].totalCount);
					}
					else if(result[0].responseCode == 2)
					{
							$('#'+divId+'').html(" <span style='margin-left:500px ;font-weight:bold;'> Group Name already exist...</span>");
					}
					else
					{
							//$('#'+divId+'').html(" <span style='margin-left:500px ;font-weight:bold;'> No data available in "+locationLevel+" Level </span>");
							alert(" No data available in "+locationLevel+" Level");
					}
			   }
			   else
			   { 
							$('#'+divId+'').html("<span style='margin-left:500px ;font-weight:bold;'> Group Name already exist...</span>");
				}
			}
			);
			
	}
	
	function createGroup(index)
	{	

		 var jsObj = globalJsonArr[index];
		var groupName = $('#groupNameId'+index+'').val();
		var groupJsObj =
		{
			searchType:"New Group",
			groupName:groupName,
			eventId :jsObj.eventId,
			committeeLevelId : jsObj.committeeLevelId,
			committeeLevelValue:jsObj.committeeLevelValue,
			committeeIdsArr : jsObj.committeeIdsArr,
			stateId:jsObj.stateId,
			districtId:jsObj.districtId,
			constituencyId:jsObj.constituencyId,
			mandalId:jsObj.mandalId,
			panchayatId:jsObj.panchayatId,
			startIndex:jsObj.startIndex,
			maxIndex : jsObj.maxIndex,	
			sortBy:jsObj.sortBy,
					
		};

		$.ajax({
          type:'GET',
          url: 'getinviteesMembersDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(groupJsObj)}
		   }).done(function(result){
			   if(result != null && result.length>0)
			   {
					if(result[0].responseCode == 0)
					{
						alert("Group Successfully Created...");
						$('#groupNameId'+index+'').val('');
					}
					else if(result[0].responseCode == 2)
					{
							$('#'+divId+'').html(" <span style='margin-left:500px ;font-weight:bold;'> Group Name already exist...</span>");
					}
					else
					{							
							alert(" No data available in "+locationLevel+" Level");
					}
			   }
			   else
			   { 
					alert("<span style='margin-left:500px ;font-weight:bold;'> Group Name already exist...</span>");
				}
			}
			);
			
	}
	
	var indexValue=0;
	function buildSearchDetails(result,divId,locationLevel,jsObj,totalCnt)
	{		
	console.log(jsObj);
		var str ='';
		$(".paginationDivId").show();
		var count=0;
		if(result != null && result.length>0)
		{				
				//str+='<h4 style="text-align:center;"> GROUP MEMBERS DETAILS </h4><br>';
				str+='<div class="panel-heading"> '+locationLevel.toUpperCase()+' LEVEL GROUP MEMBERS DETAILS ';
				str+='<div style="padding-top:10px;padding-left:10px" class="pull-right">';
				str+='<div style="width:300px;margin-top:-18px;margin-right:-10px" class="input-group pull-right">';
				str+='<input type="text" style="height: 35px;" aria-describedby="basic-addon2" placeholder="Enter Group Name" class="form-control" id="groupNameId'+indexValue+'">';
				str+='<span style="background-color: rgb(1, 182, 173); color: rgb(255, 255, 255); cursor: pointer;" id="addGroupId" class="input-group-addon" onclick="createGroup('+indexValue+')">ADD TO GROUP</span>';
				str+='</div><br><br>';
				//str+='<span class="viewlink">Click To View Existed group Names</span>    ';
				str+='</div>';
				
				//str+='<a class="btn btn-xs btn-success pull-right" href="javascript:{dispatchAddressDetails();}">Download Address Patches</a>';
												
				//str+='<a class="btn btn-xs btn-success pull-right" href="javascript:{sendSmsForCandidtes(\'allContacts'+locationLevel+'Cls\',\'contacts'+locationLevel+'Cls\');}" style="margin-right: 10px;">Send SMS</a>';
				str+='<a class="btn btn-xs btn-success pull-right" href="javascript:{exportConstituencyToExcel(\'searchTableId'+divId+'\',\''+locationLevel+'\');}" style="margin-right: 15px;"> Export Excel </a></div>';
				str+='<table class="table table-bordered " id="searchTableId'+divId+'">';
				str+='<thead>';
				str+='<tr>';
				str+='<th> <input type="checkbox" checked="true" value="" class="allContacts'+locationLevel+'Cls" onclick="checkallCheckBoxes(\'allContacts'+locationLevel+'Cls\',\'contacts'+locationLevel+'Cls\');"/> SELECT ALL </th>';
				str+='<th> DISTRICT </th>';
				str+='<th> CONSTITUENCY </th>';
				str+='<th> MANDAL/MUNCIPALITY</th>';
				str+='<th> CANDIDATE NAME	</th>';
				str+='<th> COMMITTEE </th>';
				str+='<th> DESIGNATION	</th>';
				str+='<th> MOBILE NO </th>';
				str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				
				for(var i in result)
				{
					if(result[i].committeeName != null && result[i].committeePosition != null)
					{
						count = count+1;
						str+='<tr>';
							str+='<td> <input type="checkbox" checked="true" value="'+result[i].mobileNo+'" class="contacts'+locationLevel+'Cls" id="contacts'+locationLevel+'Id'+i+'" onclick="checkCheckBoxes(\'contacts'+locationLevel+'\','+i+',\'allContacts'+locationLevel+'Cls\');"/> </td>';
							str+='<td> '+result[i].address+' </td>';
							str+='<td> '+result[i].constituency+' </td>';
							str+='<td> '+result[i].tehsil+' </td>';
							str+='<td> '+result[i].cadreName+' </td>';
							str+='<td> '+result[i].committeeName+' </td>';
							str+='<td> '+result[i].committeePosition+' </td>';
							str+='<td> '+result[i].mobileNo+' </td>';
						str+='</tr>';
					}
					
				}
				
				str+='</tbody>';
				str+='</table>';

	    var maxResults=jsObj.maxIndex;
	   
	     if(jsObj.startIndex==0){
		   $("#paginationDivId").pagination({
			items: totalCnt,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*100;
				getMembersDetails(num);
				
			}
		});
	}
		
			$('#'+divId+'').html(str);
			$("#searchTableId"+divId+"").dataTable({
				"iDisplayLength": 50,
				"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
			});			
		}
		
		$('html, body').animate({scrollTop:$('#'+divId+'').offset().top}, 'slow');
		
		str ='';
		str+='<div class="box-sub text-center">';
			str+='<span class="text-capital m_0 text-center text-head"> You are selected committee with members</span>';
			str+='<div class="text-center">';
			//str+='<span class="display-style text-italic box-subhead">Total Selected Committees:<span class="count-color">1202</span></span>';
			str+='<span class="display-style text-italic box-subhead">Selected Committees Members:<span class="count-color">'+count+'</span></span>';
			str+='</div>';
			str+='</div>';
		
		$('#countDiv').html(str);
		indexValue =indexValue+1;
	}
	
	function deleteCommite()
	{
		$(".addedcommite").each(function()
		{
		if($(this).is(":checked")) {
			$(this).parent().closest('.commiteHeadDiv').remove();
		 }
	})
	}
	function toggleDiv(id)
		{
		
			var height = $("#"+id).css('height').match(/\d+/);
			$(".toggleCls").removeClass("height-auto");
			$(".toggleCls").removeClass("in");
			
			if(height == 0 || $("#"+id).hasClass('height-0'))
			{
			$("#"+id).addClass("height-auto").addClass("in");
			
			$("#"+id).removeClass("height-0").addClass("in");
			
			}
			else
			{
				$("#"+id).removeClass("height-auto").addClass("in");
				$("#"+id).addClass("height-0").addClass("in");
				
			}
		}
		
		function checkCheckBoxes(selectedId,index,neddtoChangeCls)
		{
			var isChecked = $('#'+selectedId+'Id'+index+'').is(':checked');
			if(isChecked)
			{
				var atleastOneNotChecked=false;
				$('.'+selectedId+'Cls').each(function(){
					var isAllChecked = $(this).is(':checked');
					if(!isAllChecked && !atleastOneNotChecked)
					{
						atleastOneNotChecked = true;
					}
				});
				
				if(atleastOneNotChecked)
				{
					$('.'+neddtoChangeCls+'').removeProp('checked');
				}
				else
				{
					$('.'+neddtoChangeCls+'').prop('checked', 'checked');
				}
			}
			else
			{
				$('.'+neddtoChangeCls+'').removeProp('checked');
			}
		}
		
		function checkallCheckBoxes(selectedCld,neddtoChangeCls)
		{	
			if($('.'+selectedCld+'').is(':checked'))
			{
				$('.'+neddtoChangeCls+'').prop('checked', 'checked');
			}
			else
			{
				$('.'+neddtoChangeCls+'').removeProp('checked');
			}
			
		}
		function exportConstituencyToExcel(divId,level)
		{
			tableToExcel(divId, ' '+level+' Level Group Details ');
		}
		
		function sendSms(messageBoxId,allCls, individualCls)
		{
			
			var sms = $('#'+messageBoxId+'').val();
			$("#smsMessageDiv").hide();
			$("#smsDialogueBoxDiv").dialog('close');
			if(confirm('Are you want to send SMS?'))
			{
				var mobileNumbersArr = new Array();
				$('.'+individualCls+'').each(function(){
					
					if($(this).is(":checked"))
					{
						var mobileNo = $(this).val();
						if(mobileNo == 10 || mobileNo == 12)
							mobileNumbersArr.push(mobileNo);
					}
				});
				
				console.log(mobileNumbersArr.length);
				var myArr=new Array();
				myArr.push(9959796608);
				myArr.push(9581434970);
				var jsObj ={					
					mobileNumbersArr:myArr,
					message:sms
				};
				//console.log(jsObj);
				 $.ajax({
					  type:'GET',
					  url: 'sendSmsForInviteesAction.action',
					  dataType: 'json',
					  data: {task:JSON.stringify(jsObj)}
			   }).done(function(result){
				   console.log(result);
			   });
			   
			}
		}
		function sendSmsForCandidtes(allCls, individualCls)
		{	
			$("#smsMessageDiv").show();
			$("#smsMessageDiv").html('Enter Message : <input type="textarea" maxlength="50" id="messaggeId" placeholder="Enter Message for SMS " style="height:50px;"/><input type="button" id="smsButton" value="Send SMS" onclick="sendSms(\'messaggeId\',\''+allCls+'\',\''+individualCls+'\')" class="btn btn-success  btn-xs" style="margin-left: 200px;margin-top: 10px"/>');
			$('#messaggeId').val('');
			$("#smsDialogueBoxDiv").dialog({
				title:" Invitation through SMS ",
				width:400,
				height:150
			});
	
		}
	
	function dispatchAddressDetails()
	{
		alert(" Address Details Report....");
	}
	
	function getDetails(divId)
	{
			if(divId =='publicRepresentativesId'){
				if($('#'+divId+'').is(":checked"))
				{
					$('#representativesDiv').show();
				}
				else
				{
					$('#representativesDiv').hide();
				}
			}
			else if(divId =='cadreCommitteeId')
			{
				if($('#'+divId+'').is(":checked"))
				{
					$('#cadreCommitteeDiv').show();
				}
				else
				{
					$('#cadreCommitteeDiv').hide();
				}
			}
			else if(divId == 'groupId')
			{
					if($('#'+divId+'').is(":checked"))
					{
						$('#existingGroupsDiv').show();
					}
					else
					{
						$('#existingGroupsDiv').hide();
					}
			}
	}
	
	
	function hideAndShow(id)
	{
		alert(id);
	}
	
	
	function buildLevel()
	{
		if(gldistrictId != 0)
		  {
			  var str='';
				str+='<section>';
				  str+='<label class="select-label">Level</label>';
				  str+=' <div class="cs-select cs-skin-slide stateSlide" tabindex="0" onclick="selectChange(\'stateSlide\')">';
				  
				  str+='<span class="cs-placeholder stateName" value="11">District</span>';
				  str+='<div class="cs-options">';
					  str+='<ul class="scrollbar stateList">';
					  // str+='<li data-value="10" data-option="" class="stateEle"><span>State</span></li>';
					   str+='<li data-value="11" data-option="" class="stateEle"><span>District</span></li>';
					   str+='<li data-value="5" data-option="" class="stateEle"><span>Mandal/Town/Division</span></li>';
					   str+='<li data-value="6" data-option="" class="stateEle"><span>Village/Ward</span></li>';
					  str+='</ul>';
				  str+='</div><select class="cs-select cs-skin-slide" id="levelId">';
					str+='<option value="11">District</option>';
					str+='<option value="5">Mandal/Town/Division</option>';
					str+='<option value="6">Village/Ward</option>';
				 str+='</select></div></section>';
				 
				$("#levelDivId").html(str);
		  }
		  else
		  {
			  var str='';
				str+='<section>';
				  str+='<label class="select-label">Level</label>';
				  str+=' <div class="cs-select cs-skin-slide stateSlide" tabindex="0" onclick="selectChange(\'stateSlide\')">';
				  
				  str+='<span class="cs-placeholder stateName" value="10">State</span>';
				  str+='<div class="cs-options">';
					  str+='<ul class="scrollbar stateList">';
					   str+='<li data-value="10" data-option="" class="stateEle"><span>State</span></li>';
					   str+='<li data-value="11" data-option="" class="stateEle"><span>District</span></li>';
					   str+='<li data-value="5" data-option="" class="stateEle"><span>Mandal/Town/Division </span></li>';
					   str+='<li data-value="6" data-option="" class="stateEle"><span>Village/Ward</span></li>';
					  str+='</ul>';
				  str+='</div><select class="cs-select cs-skin-slide" id="levelId">';
					str+='<option value="10">State</option>';
					str+='<option value="11">District</option>';
					str+='<option value="5">Mandal/Town/Division</option>';
					str+='<option value="6">Village/Ward</option>';
				 str+='</select></div></section>';
				 
				$("#levelDivId").html(str);
		  }
		
	}
</script>
<script>
getDistricts();
getCommittees();
getCommitteeRoles();
buildLevel();
//getMembersDetails();
</script>

</body>
</html>