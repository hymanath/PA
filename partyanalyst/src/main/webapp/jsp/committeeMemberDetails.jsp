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

<style>
</style>
</head>
<body class="background-bg">

	<div class="well br_0 container" style="background-color:#fff;margin-top:5px;">
    	<h3 class="m_0 text-center">COMMITTEE MEMBER DETAILS</h3>
    </div>
<div class="container m_top10" style="background-color:#fff;margin-top:-18px;">
    <article>
    	<div class="col-md-3" style="background-color:#FFF">
            <section>
                    <label class="select-label">Committee Level</label>
                    <select class="cs-select cs-skin-slide">
                        <option value="district">District</option>
                        <option value="mandal" >Mandal</option>
                    </select>
            </section>
        </div>
        <div class="col-md-3" id="districtDiv">
          <!--  <section>
                    <label class="select-label">District</label>
                    <select class="cs-select cs-skin-slide" id="districtId">
                       <option value="sightseeing" >Guntur</option>
                        <option value="business">Prakasham</option>
                        <option value="honeymoon">Nellore</option>
                        <option value="food">Kadapa</option>
                        <option value="shopping">vijayawada</option>
                        <option value="shopping">Kurnool</option>
                        <option value="shopping">Kurnool</option>
                    </select>
            </section>-->
        </div>
        <div class="col-md-2" id="constituencyDiv">
            <section>
                    <label class="select-label">Assembly</label>
                    <select class="cs-select cs-skin-slide">
                        <option value="0" >Constituency</option>
                       
                    </select>
            </section>
        </div>
        <div class="col-md-2" id="mandalDiv">
            <section>
                    <label class="select-label">Mandal/Muncipality</label>
                    <select class="cs-select cs-skin-slide">
                        <option value="0">Mandal/Muncipality</option>
                                              
                       
                    </select>
            </section>
        </div>
        <div class="col-md-2" id="panchayatDiv">
            <section>
                    <label class="select-label">Panchayat</label>
                    <select class="cs-select cs-skin-slide">
                        <option value="0" >Panchayat</option>
                       
                    </select>
            </section>
        </div>
       
        <div class="col-md-6 m_top20" style="background-color:#FFF">
            <div  id="committeeDiv">
            <label class="select-label" style="margin-left:0px ">Committee</label>
            <select class="cs-select cs-skin-slide">
                <option value="0" >Committee</option>
            </select>
            </div>
			
            <div>
			<form class="me-select">
			<ul id="me-select-list" class="">
			<li class="">
			<input id="checkAll" class="" type="checkbox" name="cb10">
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
            <div class="text-bold" style="padding-top:30px;">Select Committee Members Designation to Create Group <br/>(or) Download Members Details</div>
        </div>
        <div class="col-md-6 m_top20">
        		<div class="border-box">
                	<div class="box-sub text-center">
                        <span class="text-capital m_0 text-center text-head"> You are selected committee with members</span>
                        <div class="text-center">
                            <span class="display-style text-italic box-subhead">Total Selected Committees:<span class="count-color">1202</span></span>
                            <span class="display-style text-italic box-subhead">Selected Committees Members:<span class="count-color">1202</span></span>
                        </div>
                    </div>
                    <div class="trashicon"><i class="glyphicon glyphicon-trash"></i></div>
                     <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" style="margin-top:-20px;">
                  <div class="panel panel-default border_0">
                    <div class="panel-heading collapse-head" role="tab" id="headingOne">
                      <h4 class="panel-title">
                          <form class="me-select display-style">
                            <ul id="me-select-list">
                                <li><input id="cb11" name="cb11" type="checkbox">
                                	<label for="cb11" class="m_0 collapse-select"><span class="text-col-head"><a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-controls="collapseOne" class="col-drop-head">Committee Designation</a></span></label></li>
                            </ul>
                          </form>
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        	<i class="glyphicon glyphicon-chevron-down pull-right display-style col-drop-color"></i>
                        </a>
                      </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                      <div class="panel-body">
                        	<form class="me-select display-style">
                                <ul id="me-select-list">
                                    <li><input id="cb12" name="cb11" type="checkbox">
                                        <label for="cb12" class="m_0 collapse-select"><span class="col-drop-select-name">President<span class="text-italic text-selectbox-bracket">(President Name)</span></span></label></li>
                                    <li><input id="cb13" name="cb11" type="checkbox">
                                        <label for="cb13" class="m_0 collapse-select"><span class="col-drop-select-name">Vice-President<span class="text-italic text-selectbox-bracket">(Vice-President Name)</span></span></label></li>
                                    <li><input id="cb14" name="cb11" type="checkbox">
                                        <label for="cb14" class="m_0 collapse-select"><span class="col-drop-select-name">General Secretary<span class="text-italic text-selectbox-bracket">(General Secretary Name)</span></span></label></li>
                                </ul>
                            </form>
                      </div>
                    </div>
                  </div>
                   <div class="panel panel-default border_0">
                    <div class="panel-heading collapse-head" role="tab" id="headingTwo">
                      <h4 class="panel-title">
                          <form class="me-select display-style">
                            <ul id="me-select-list">
                                <li>
                                	<input id="cb15" name="cb11" type="checkbox">
                                	<label for="cb15" class="m_0 collapse-select"><span class="text-col-head"><a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-controls="collapseTwo" class="col-drop-head">TNTUC</a></span></label>
                                </li>
                            </ul>
                          </form>
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-controls="collapseTwo">
                        	<i class="glyphicon glyphicon-chevron-down pull-right display-style col-drop-color"></i>
                        </a>
                      </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                      <div class="panel-body">
                        	<form class="me-select display-style">
                                <ul id="me-select-list">
                                    <li><input id="cb16" name="cb11" type="checkbox">
                                        <label for="cb16" class="m_0 collapse-select"><span class="col-drop-select-name">President<span class="text-italic text-selectbox-bracket">(President Name)</span></span></label></li>
                                    <li><input id="cb17" name="cb11" type="checkbox">
                                        <label for="cb17" class="m_0 collapse-select"><span class="col-drop-select-name">Vice-President<span class="text-italic text-selectbox-bracket">(Vice-President Name)</span></span></label></li>
                                    <li><input id="cb18" name="cb11" type="checkbox">
                                        <label for="cb18" class="m_0 collapse-select"><span class="col-drop-select-name">General Secretary<span class="text-italic text-selectbox-bracket">(General Secretary Name)</span></span></label></li>
                                </ul>
                            </form>
                      </div>
                    </div>
                  </div>
                     <section style="margin-left:10px;">
                            <label class="select-label" style="margin-left:0px;margin-right:0px;">SELECT COMMITTEE MEMBER DETAILS TYPE</label>
                            <select class="cs-select cs-skin-slide">
                                <option value="sightseeing" >Address Patches</option>
                            </select>
                    </section>
                    <div class="download-box display-style">
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
                                	<img src="dist/images/excel.png">
                                    <span>Excel</span>
                                </div>
                            </div>
                        </div>
                        <img src="dist/images/or.png">
                        <div class="pull-right" style="padding-top:10px;padding-left:10px">
                            <span>ADD TO GROUP</span><br/>
                                <div class="input-group pull-right" style="width:300px;">
                                  <input type="text" class="form-control" placeholder="Enter Group Name" aria-describedby="basic-addon2">
                                  <span class="input-group-addon" style="background-color:#01b6ad;color:#fff;cursor:pointer" >ADD TO GROUP</span>
                                </div><br/><br/>
                            <span class="viewlink">Click To View Existed group Names</span>    
                        </div>
                    </div>
                </div>
        </div>
    </article>
</div>

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

 
 function getDistricts(){
	  $("#districtId").find('option').remove();
	 
     var jsObj=
		{				
				stateId:1,
				elmtId:"districtList_d",
                type:"default",
				task:"findDistrictsForAState"				
		}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForAStateAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   var str ='';
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
   
		  str+='<section>';
          str+='<label class="select-label">Committee Level</label>';
		  str+=' <div class="cs-select cs-skin-slide distSlide" tabindex="0" onclick="selectChange(\'distSlide\')">';
		  str+='<span class="cs-placeholder distName">District</span><div class="cs-options"><ul class="scrollbar distList">';
		  for(var i in result)
		  {
		  str+='<li data-value="'+result[i].id+'" data-option="" class="distEle"><span>'+result[i].name+'</span></li>';
		  }
		  str+='</ul>';
		  str+='</div><select class="cs-select cs-skin-slide" id="districtId">';
		  for(var i in result)
		  {
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

  function getConstituenciesForDistricts(districtId){
  
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
   var str='';
	   str+='<section>';
          str+='<label class="select-label">Assembly</label>';
		  str+=' <div class="cs-select cs-skin-slide constiSlide" tabindex="0" onclick="selectChange(\'constiSlide\')">';
		  str+='<span class="cs-placeholder constiName">Constituency</span><div class="cs-options"><ul class="scrollbar constiList">';
		  for(var i in result)
		  {
		  str+='<li data-value="'+result[i].id+'" data-option="" class="constiEle"><span>'+result[i].name+'</span></li>';
		  }
		  str+='</ul>';
		  str+='</div><select class="cs-select cs-skin-slide" id="constituencyId">';
		  for(var i in result)
		  {
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

  function getTehsils(constiId){
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
   var str='';
	   str+='<section>';
          str+='<label class="select-label">Mandal/Muncipality</label>';
		  str+=' <div class="cs-select cs-skin-slide mandalSlide" tabindex="0" onclick="selectChange(\'mandalSlide\')">';
		  str+='<span class="cs-placeholder mandalName">Mandal/Muncipality</span><div class="cs-options"><ul class="scrollbar mandalList">';
		  for(var i in result)
		  {
		  if(result[i].id != 0)
		  str+='<li data-value="'+result[i].id+'" data-option="" class="mandalEle"><span>'+result[i].name+'</span></li>';
		  }
		  str+='</ul>';
		  str+='</div><select class="cs-select cs-skin-slide" id="mandalId">';
		  for(var i in result)
		  {
		   if(result[i].id != 0)
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
    function getPanchayats(mandalId){
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
	  var str='';
	   str+='<section>';
          str+='<label class="select-label">Panchayat</label>';
		  str+=' <div class="cs-select cs-skin-slide panchayatSlide" tabindex="0" onclick="selectChange(\'panchayatSlide\')">';
		  str+='<span class="cs-placeholder panchayatName">Panchayat</span><div class="cs-options"><ul class="scrollbar panchayatList">';
		  for(var i in result)
		  {
		  if(result[i].id != 0)
		  str+='<li data-value="'+result[i].id+'" data-option="" class="panchayatEle"><span>'+result[i].name+'</span></li>';
		  }
		  str+='</ul>';
		  str+='</div><select class="cs-select cs-skin-slide" id="panchayatId">';
		  for(var i in result)
		  {
		   if(result[i].id != 0)
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
          str+='<label class="select-label">Committee</label>';
		  str+=' <div class="cs-select cs-skin-slide committeeSlide" tabindex="0" onclick="selectChange(\'committeeSlide\')">';
		  str+='<span class="cs-placeholder committeeName">Committee</span><div class="cs-options"><ul class="scrollbar panchayatList">';
		  for(var i in result)
		  {
		  if(result[i].id != 0)
		  str+='<li data-value="'+result[i].id+'" data-option="" class="committeeEle"><span>'+result[i].name+'</span></li>';
		  }
		  str+='</ul>';
		  str+='</div><select class="cs-select cs-skin-slide" id="committeeId">';
		  for(var i in result)
		  {
		   if(result[i].id != 0)
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
            str+='<li><input id="committee'+result[i].id+'" name="commiteCheck" type="checkbox" value="'+result[i].id+'" class="commiteCheck"><label for="cb10"  style="padding:0px 10px 0px 45px;margin-top:10px;"><span >'+result[i].name+'</span></li>';
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
			if($("."+divEle).hasClass("cs-active"))
			$("."+divEle).removeClass("cs-active");
			else
			$("."+divEle).addClass("cs-active");
			 $(".distEle").click(function(e)
			 {			
				$(".distName").html($(this).text());
				$(".distEle").removeClass('cs-selected');
				$(this).addClass('cs-selected');
				getConstituenciesForDistricts($(this).attr("data-value"));
			  });
			  $(".constiEle").click(function()
				{	
				$(".constiName").html($(this).text());
				$(".constiEle").removeClass('cs-selected');
				$(this).addClass('cs-selected');
				//alert($(this).attr("data-value"));
				 getTehsils($(this).attr("data-value"));
			  });
			    $(".mandalEle").click(function()
				{	
				$(".mandalName").html($(this).text());
				$(".mandalEle").removeClass('cs-selected');
				$(this).addClass('cs-selected');
				//alert($(this).attr("data-value"));
				 getPanchayats($(this).attr("data-value"));
			  });
			  $(".panchayatEle").click(function()
				{	
				$(".panchayatName").html($(this).text());
				$(".panchayatEle").removeClass('cs-selected');
				$(this).addClass('cs-selected');
				//alert($(this).attr("data-value"));
				
			  });
			   $(".committeeEle").click(function()
				{	
				$(".committeeName").html($(this).text());
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
				$(".commiteCheck").prop('checked', true);
				$("#checkText").html("UnSelect All");
			}
			else{
				$(".commiteCheck").prop('checked', false);
				$("#checkText").html("Select All");
			}
			});
			});
		
</script>
<script>
getDistricts();
getCommittees();
getCommitteeRoles();
</script>

</body>
</html>