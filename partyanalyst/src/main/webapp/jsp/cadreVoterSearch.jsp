<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
	<title>Cadre / Voter Search</title>
	<meta name="description" content="Natural Language Form with custom text input and drop-down lists" />
	<meta name="keywords" content="Natural Language UI, sentence form, text input, contenteditable, html5, css3, jquery" />
	<meta name="author" content="Codrops" />
	<link rel="shortcut icon" href="../favicon.ico"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">	
	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>	
    <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

	<!--
	<script src="js/StaticFiles/StaticJsFiles/jquery-ui.1.10.4.js"></script>
	<script src="js/StaticFiles/StaticJsFiles/jquery.min.1.11.1.js"></script>
	<link href="js/StaticFiles/StaticCssFiles/jquery-ui-1.10.4.css" rel="stylesheet" type="text/css" />
	<script src="js/StaticFiles/StaticJsFiles/jquery-1.10.2.js"></script>
	-->
	
	<link rel="stylesheet" type="text/css" href="js/cadreSearch/Naturallanguage/css/component.css" />
	<link href="js/cadreSearch/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="js/cadreSearch/css/custom.css" rel="stylesheet" type="text/css">
	<!--<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css' />-->
	<link href="js/StaticFiles/StaticCssFiles/family=Roboto.css" rel='stylesheet' type='text/css' />
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	
	  <link rel="stylesheet" type="text/css" href="css/cadreRegistrationCSS/animate.css" >
	<!--
	<link href="js/cadreSearch/PageTransitions/css/animations.css" rel="stylesheet" type="text/css">
	<link href="js/cadreSearch/PageTransitions/css/component.css" rel="stylesheet" type="text/css"> 
	-->
<style>
body{background:#ededed}
#searchDiv li{display:inline-block}
.label-success[href], .badge-success[href]{background-color:#356635}
.nl-field ul li{line-height:35px!important;}
.nl-field a:hover{text-decoration:none!important;}
.nl-ti-input input[type="text"]{height:45px! important; width:324px! important;}
#contenttable{min-height:450px;background:#e5e5e5 !important;}
.get-details h4{
	margin-top:10px;
}
.get-details a{
	color:#333;
}

.dataTables_length, .dataTables_filter , .dataTables_info {
	color : #666666 !important;
}
.casteTableCls th {
    background-color: #ccc;
}

.dataTables_filter input,.dataTables_length select{
	height:25px !important;
}

table.dataTable tr.odd{background-color:#eee;}
table.dataTable tr.odd td.sorting_1 {background-color: #eee;}

/*--------*/
.styled-select {
   background: url(https://i62.tinypic.com/15xvbd5.png) no-repeat 96% 0;
   height: 29px;
   overflow: hidden;
   width: 180px;
   background-color:#E5E5E5;
   
   
}
.modifySearchBreadcrumb li {
	width:180px;
}

.styled-select select {
   background: transparent;
   border: none;
   font-size: 14px;
   height: 29px;
   padding: 5px; /* If you add too much padding here, the options won't show in IE */
   width: 268px;
   outline:none;
}
.styled-select option{padding:5px;}

.rounded {
   -webkit-border-radius: 20px;
   -moz-border-radius: 20px;
   border-radius: 20px;
}

.black { background-color: #E5E5E5; border:1px solid #333}
.black select   { color: #333; }


table.dataTable tr.odd td.sorting_1 {background-color: #ccc;}
table.dataTable tr.even td.sorting_1 {background-color: #ccc;}
.styled-select
{
	margin-left:5px;
}
.nl-overlay {
background: none repeat scroll 0 0 !important;
z-index:2;
}

</style>
</head>
	<body class="search-body-bg">			
        <div class="container">
        <div class="well search-heading m_top10"><h2 class="text-center search-head">SEARCH A CADRE / VOTER</h2></div>
        
		<div id="pt-main" class="pt-perspective" style="margin-left:-15px;">
        <div class="pt-page pt-page-1 container " style="left:-11px;">
		<a class="btn pull-right" style="z-index: 999;display:none;" id="hideModifiSearchId" href="javascript:{modifySearchDiv('hideModifiSearchId');}" title="Close Search"> X </a>
		<a class="btn btn-default pull-right" style="z-index: 999;display:none;" id="showModifiSearchId" href="javascript:{modifySearchDiv('showModifiSearchId');}"> Modify Search </a>
            <div class="well search-content" id="modifySearchId">
                <div class="main clearfix text-center ">
				
                     <form id="nl-form" class="nl-form">
                        I WANT TO SEARCH
                        <select class="searchCls">
                           <!-- <option value="0" selected >ANY</option>-->
                            <option value="1" >CADRE</option>
                            <option value="2">VOTER</option>
                        </select>
                        IN 
                        <select class="stateCls">
                            <option value="0" selected>ANY</option>
                            <option value="1">ANDHRA PRADESH</option>
                            <option value="2">TELANGANA</option>
                        </select>
                        STATE
                        <br />IN  
                        <input type="textarea"  value="" placeholder="ANY"/>
                        DISTRICT,IN 
                       <input type="textarea"  value="" placeholder="ANY"/>
                        CONSTITUENCY.<br/>
                        WITH
						<input type="textarea"  value="" placeholder="ANY"/>
						  
                        CASTE AND 
                        <input  type="text"  value="" placeholder="ANY"/>
                        NAME.
                        <br/> 
						<div class="nl-overlay"></div>
                    </form>					
                    <button class="btn btn-success get-details m_top10" onclick="searchResults('searchDetailsDiv');">
						<i class="glyphicon glyphicon-arrow-right"></i>&nbsp;&nbsp;Get Details
                    </button>
					
                </div>              
			</div>
		</div>
		<div class="pt-page pt-page-2 container" id="locationsDiv">    
			<div>
					<span class="badge modifySearchBreadscrumb" style="display:none;margin-left: 5px;margin-top: -18px;"> YOU ARE SEARCHING: </span>
					<ul class="list-inline modifySearchBreadcrumb" style="display:none;margin-bottom: 0px; margin-left: 10px; margin-top: 10px;">					
					<li class="searchId" style="display:none;" > Cadre / Voter :  </li>
					<li  class="stateId1" style="display:none;" > State: </li>
					<li  class="districtId1"  style="display:none;"> District : </li>
					<li  class="constiId1"  style="display:none;"> Constituency : </li>
					<li  class="tehsilId1"  style="display:none;"> Mandal / Muncipality : </li>
					<li class="casteId1" style="display:none;"> Caste : </li>
					</ul>
				<ol class="breadcrumb search-breadcrumb">				
					<div id="searchDiv"></div>
				</ol>
			<!--	<ol class="breadcrumb search-breadcrumb">				
					<li> Name: <input type="text" id="serachNameId"/> </li>
				</ol>
				-->
				<div  style="margin-top:10px;">
					<input type="hidden" id="enteredText" value=""/>
					<div id="searchDetailsDiv" class="earchDetailsDiv">						
					</div>						
					<div style="" class="col-md-3 col-md-offset-5">
						<img id="ajaxImageIdAPmandalconstiRoleSummary" src="./images/Loading-data.gif" alt="Processing Image" style="display:none;">
					</div>
				</div>
				<div id="casteWiseDetailsDiv" class="">						
					</div>	
			</div>
		</div>
		<input type="button" value="Top" class="btn btn-primary pull-right" style="position:fixed;bottom:25px;right:10px; z-index: 999;display:none;"id="positoinDivId" onclick="scrollToDiv();">
	</div>   
	
	<!--<script src="js/cadreSearch/js/jquery-1.11.2.min.js" type="text/javascript"></script>-->
	<script src="js/cadreSearch/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/cadreSearch/Naturallanguage/js/modernizr.custom.js"></script>
    <script src="js/cadreSearch/Naturallanguage/js/nlform.js"></script>
  <!--  <script src="js/cadreSearch/PageTransitions/js/modernizr.custom.js" type="text/javascript"></script>
    <script src="js/cadreSearch/PageTransitions/js/jquery.dlmenu.js" type="text/javascript"></script>
    <script src="js/cadreSearch/PageTransitions/js/pagetransitions.js" type="text/javascript"></script>
-->
	<script>
	var btnFlag = false ;
	var btnFlag1 = false ;
	var btnFlag2 = false ;
	var btnFlag3 = false ;
	var selectionArr = ["searchCls","stateCls","districtCls","constiCls","casteCls","nameCls"];
	var selectionArr1 = ["searchDivCls","stateDivCls","districtDivCls","constiDivCls","casteDivCls","nameDivCls"];
	var selectionArr2 = ["searchDivClsHidden","stateDivClsHidden","districtDivClsHidden","constiDivClsHidden","casteDivClsHidden","nameDivClsHidden"];
	
	var	casteSel = 0;
	var casteArr ;
	var glblLocationId=0;
	var glblLocationType='';
	var glblLocationName='';
	$(document).ready(function(){
		alert = function(){};
		
		getDistrictsAndConstis("district",0);
		getDistrictsAndConstis("constituencyByState",0);
		$('.fadeInRight').addClass('animated  fadeInRight ');
		casteArr = new Array();
		var casteDetailsArr = new Array();
		var obj = {
					value :  '0',
					label :  'Any'
				}
		casteDetailsArr.push('Any');
		casteArr.push(obj);
		<c:forEach var="caste" items="${casteDetails}">
				var obj = {
					value :  '${caste.id}',
					label :  '${caste.name}'
				}
				casteDetailsArr.push( '${caste.name}');
				casteArr.push(obj);
		</c:forEach>
	
	
	$('.stateCls').click(function(){				
		var locationValue = $(this).attr('key');		
		getDistrictsAndConstis("district",locationValue);
		getDistrictsAndConstis("constituencyByState",locationValue);		
	});
	$('.nameDivCls').click(function(){	
		if(!$('.nameDivCls').hasClass('nl-field-open'))	{		
			if(btnFlag3 == true)
			{
			$('.nameDivCls').removeClass('nl-field-open');
			$('.nameDivCls').open = false;	
			btnFlag3 = false;
			}	
			else
			{
			$('.nameDivCls').open = true;			
			$('.nameDivCls').addClass('nl-field-open');
			$('.nameCls').focus();
			}
			}
		});
			$('.nameDivCls').keydown(function (e) {			
					if ( e.keyCode == 13 ) {
						//alert(e.keyCode);
						$('.nameDivCls').removeClass('nl-field-open');
						$('.nameDivCls').open = false;
						getNameSelectedDetails();
					}
			});
			
	$('.districtDivCls').click(function(){	
		if(!$('.districtDivCls').hasClass('nl-field-open'))	{		
			if(btnFlag == true)
			{
			$('.districtDivCls').removeClass('nl-field-open');
			$('.districtDivCls').open = false;	
			btnFlag = false;
			}	
			else
			{
			$('.districtDivCls').open = true;			
			$('.districtDivCls').addClass('nl-field-open');
			$('.districtCls').focus();
			}
			
			$('.districtDivCls').keydown(function (e) {			
					if ( e.keyCode == 13 ) {
						//alert(e.keyCode);
						$('.districtDivCls').removeClass('nl-field-open');
						$('.districtDivCls').open = false;
					}
			});
			}
		});
		$('.casteDivCls').click(function(){	
		if(!$('.casteDivCls').hasClass('nl-field-open'))	{		
			if(btnFlag2 == true)
			{
			$('.casteDivCls').removeClass('nl-field-open');
			$('.casteDivCls').open = false;	
			btnFlag = false;
			}	
			else
			{
			$('.casteDivCls').open = true;			
			$('.casteDivCls').addClass('nl-field-open');
			$('.casteCls').focus();			
			}
			
			$('.casteDivCls').keydown(function (e) {			
					if ( e.keyCode == 13 ) {						
						$('.casteDivCls').removeClass('nl-field-open');
						$('.casteDivCls').open = false;
					}
			});
			}
		});
	$('.constiDivCls').click(function(){	
		if(!$('.constiDivCls').hasClass('nl-field-open'))	{	
			if(btnFlag1 == true)
			{
			$('.constiDivCls').removeClass('nl-field-open');
			$('.constiDivCls').open = false;	
			btnFlag1 = false;
			}	
			else
			{
			$('.constiDivCls').open = true;			
			$('.constiDivCls').addClass('nl-field-open');
			$('.constiCls').focus();			
			}
			
			$('.constiDivCls').keydown(function (e) {			
					if ( e.keyCode == 13 ) {
						//alert(e.keyCode);
						$('.constiDivCls').removeClass('nl-field-open');
						$('.constiDivCls').open = false;
					}
			});
			
			}
		});
		
		$('.nl-overlay').click(function(){
		if($('.constiDivCls').hasClass('nl-field-open')){			
			if(btnFlag1 == false)
			{
				$('.constiDivCls').removeClass('nl-field-open');
				$('.constiDivCls').open = false;	
				//btnFlag1 = true;
			}	
		}
		else if($('.districtDivCls').hasClass('nl-field-open'))	{		
			if(btnFlag == false)
			{
				$('.districtDivCls').removeClass('nl-field-open');
				$('.districtDivCls').open = false;	
				//btnFlag = true;
			}
			
		}
		else if($('.casteDivCls').hasClass('nl-field-open'))	{		
			if(btnFlag == false)
			{
				$('.casteDivCls').removeClass('nl-field-open');
				$('.casteDivCls').open = false;	
				//btnFlag2 = true;
			}
			
		}
		else if($('.nameDivCls').hasClass('nl-field-open'))	{		
			if(btnFlag3 == false)
			{
				$('.nameDivCls').removeClass('nl-field-open');
				$('.nameDivCls').open = false;	
				//btnFlag3 = true;
			}
			
		}
		});
		getCasteSelectedDetails();
	
	});
	
	
	
	</script>
	<script>
	var districtArr;
	var constiArr;
	var tehsilArr;
	var divCount = 2;
	var mainConstiArr ;
	var mainTehsilArr ;	
	var dinamicDiv='';
	  
	function getDistrictsAndConstis(type,locationValue){
	if(typeof districtArr == 'undefined'){
		districtArr = new Array();
	}
	
	
	$('.districtCls').find('option').remove();
	$('.districtCls').append('<option value="0"> ANY </option>');
		var jObj = {
			type:type,			
			id:locationValue,
			task:"getDistrictsAndConstis"
		}				
		$.ajax({
          type:'GET',
          url: 'getDistrictsAndConstituenciesAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result != null){
				if(type == 'district'){
					districtArr = new Array();
					var obj = {
						value :  0,
						label :  'Any'
						}			
					districtArr.push(obj);
					for(var i in result){
						var obj = {
						value :  result[i].locationId,
						label :  result[i].locationName
						}			
						districtArr.push(obj);
					}
					
					var str='';
					str+='<a class="nl-field-toggle">ANY</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="districtCls" onkeyup="districtsKeyUp();" />';
					$(".districtDivCls").html(str);
					var str1='';
					str1+='<a class="nl-field-toggle">ANY</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="constiCls" onkeyup="constiKeyUp();" />';
					$(".constiDivCls").html(str1);
				}
				else if(type == 'Constituency' || type == 'constituencyByState'){
					constiArr = new Array();
					//$('.districtCls').remove();
					var obj = {
						value :  0,
						label :  'Any'
						}			
						constiArr.push(obj);
						
					for(var i in result){
						var obj = {
						value :  result[i].locationId,
						label :  result[i].locationName
						}			
						constiArr.push(obj);
					}
					
					var str='';
					str+='<a class="nl-field-toggle">ANY</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="constiCls" onkeyup="constiKeyUp();" />';
					$(".constiDivCls").html(str);
				}
				else if(type == 'panchayat'){
					tehsilArr = new Array();
					//$('.districtCls').remove();
					for(var i in result){
						var obj = {
						value :  result[i].locationId,
						label :  result[i].locationName
						}			
						tehsilArr.push(obj);
					}
					
				}	
			}
			//new NLForm(document.getElementById( 'nl-form' ));			
		});
	
	}
var districtSel = 0;
var constiSel =0;
	function districtsKeyUp(){

		var source  = [ ];
					var mapping = { };
					for(var i = 0; i < districtArr.length; ++i) {
						source.push(districtArr[i].label);
						mapping[districtArr[i].label] = districtArr[i].value;
					}	
					$('.districtCls').autocomplete({
						minLength: 1,
						source: source,
						select: function(event, ui) {
							districtSel = mapping[ui.item.value];
							for(var i = 0; i < districtArr.length; ++i) {
								if(districtArr[i].value == districtSel){
									var districtName = districtArr[i].label;
									$('.districtCls').val(districtName);							
									getSelectedData();
								}
							}
						}
					});					
	}
	function constiKeyUp(){
		var source = [ ];
					var mapping = { };
					for(var i = 0; i < constiArr.length; ++i) {
						source.push(constiArr[i].label);
						mapping[constiArr[i].label] = constiArr[i].value;
					}	
					$('.constiCls').autocomplete({
						minLength: 1,
						source: source,
						select: function(event, ui) {
							constiSel = mapping[ui.item.value];
							for(var i = 0; i < constiArr.length; ++i) {
								if(constiArr[i].value == constiSel)
								{
									var constName = constiArr[i].label;
									$('.constiCls').val(constName);							
									getSelectedData1();
								}
							}
						}
					});
	}

	function casteKeyUp(){
		var source = [ ];
					var mapping = { };
					for(var i = 0; i < casteArr.length; ++i) {
						source.push(casteArr[i].label);
						mapping[casteArr[i].label] = casteArr[i].value;
					}	
					$('.casteCls').autocomplete({
						minLength: 1,
						source: source,
						select: function(event, ui) {
							casteSel = mapping[ui.item.value];	
							for(var i = 0; i < casteArr.length; ++i) {
								if(casteArr[i].value == casteSel)
								{
									var casteName = casteArr[i].label;
									$('.casteCls').val(casteName);							
									getCasteSelectedDetails();
								}
							}
						}
					});
	}
	
	
	function getSelectedData(){
	
		if(btnFlag)
			btnFlag= true;
		else
			btnFlag = false;
		$('.districtDivCls').removeClass('nl-field-open');
		$('.districtDivCls').open = false;		
		var validVal = $(".districtCls").val();
		if(!validVal == ""){	
		var str ='<a class="nl-field-toggle">'+validVal+'</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="districtCls" onkeyup="districtsKeyUp();" />';
		}else{
		var str ='<a class="nl-field-toggle">ANY</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="districtCls" onkeyup="districtsKeyUp();" />';
		
		}
		
		$(".districtDivCls").html(str);

		getDistrictsAndConstis("Constituency",districtSel);
		
	}
	
	function getSelectedData1(){
		if(btnFlag1)
			btnFlag1 = true;
		else
		btnFlag1 = false;
		$('.constiDivCls').removeClass('nl-field-open');
		$('.constiDivCls').open = false;		
		var validVal = $(".constiCls").val();
		if(!validVal == ""){
		var str ='<a class="nl-field-toggle">'+validVal+'</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="constiCls" onkeyup="constiKeyUp();" />';
		}
		else{
		var str ='<a class="nl-field-toggle">ANY</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="constiCls" onkeyup="constiKeyUp();" />';
		}
		$(".constiDivCls").html(str);
		
		
	}
	
	function getCasteSelectedDetails(){
		if(btnFlag2)
			btnFlag2 = true;
		else
			btnFlag2 = false;
		$('.casteDivCls').removeClass('nl-field-open');
		$('.casteDivCls').open = false;		
		var validVal = $(".casteCls").val();
		if(!validVal == ""){
		var str ='<a class="nl-field-toggle">'+validVal+'</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY"  onkeyup="casteKeyUp();" class="casteCls"  />';
		}
		else{
		var str ='<a class="nl-field-toggle">ANY</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" onkeyup="casteKeyUp();" class="casteCls"  />';
		}
		$(".casteDivCls").html(str);

	}
	
	function getNameSelectedDetails(){
		//alert(btnFlag3);
		if(btnFlag3)
			btnFlag3 = true;
		else
			btnFlag3 = false;
		$('.nameDivCls').removeClass('nl-field-open');
		$('.nameDivCls').open = false;		
		var validVal = $(".nameCls").val();
		if(!validVal == ""){
		var str ='<a class="nl-field-toggle">'+validVal+'</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY"   class="nameCls"  />';
		}
		else{
		var str ='<a class="nl-field-toggle">ANY</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY"   class="nameCls"  />';
		}
		$(".nameDivCls").html(str);
		
	}

	new NLForm(document.getElementById( 'nl-form' ));
	function searchResults(divId)
	{ 
		if(dinamicDiv.length>0)
		{		
			divId = dinamicDiv ;			
			var divStr;			
			divStr = dinamicDiv.substr(0, dinamicDiv.length - 1);	

			$('#'+divStr+'').html('');  
			$('#'+dinamicDiv+'').html('');  
		}
		$('#'+dinamicDiv+'').html('');
		$('.modifySearchBreadcrumb').show();
		$('.modifySearchBreadscrumb').show();
		
		var searchType = $(".searchDivCls a").text();
		$('#ajaxImageIdAPmandalconstiRoleSummary').show();
		$('#modifySearchId').hide();
		$('.districtId1').hide();
		$('.constiId1').hide();
		$('.tehsilId1').hide();
		
		$('#hideModifiSearchId').hide();
		$('#showModifiSearchId').show();
		var searchTypeVal ="";
		searchTypeVal = searchType;
		var searchName = $(".nameDivCls a").text();
		var searchNameVal ="";
		if(searchName == 'ANY'){
			searchNameVal = "";
		}
		else{
			searchNameVal = searchName;
		}
		var casteStateId=casteSel;

		var state= $(".stateDivCls a").text();
		var stateId = 0;
		if(state == 'ANY'){
			stateId = 0;
		}
		else if(state == 'ANDHRA PRADESH'){
			stateId = 1;
		}
		else if(state == 'TELANGANA'){
			stateId = 2;
		}

		var locationId = "";
		var locationType = "";
		if(constiSel != 0 ){
			locationType = "tehsil";
			locationId = constiSel;
		}
		else if(districtSel != 0){
			locationType = "constituency";
			locationId = districtSel;
		}
		else {
			locationType = "state";
			locationId = stateId;
		}

		var isFinal = "";
		var nextLocationType = "";
		if(locationType == 'state')
		{
			nextLocationType ="constituency";
		}
		else if(locationType == 'constituency')
		{
			nextLocationType ="tehsil";
		}
		else if(locationType == 'tehsil')
		{
			nextLocationType ="panchayat";
		}

		$('#'+divId+'').html('');
		$('#enteredText').val(searchNameVal);
		$('#positoinDivId').hide();
		var jObj = {
			searchType:searchTypeVal,	
			stateId:stateId,
			locationId:locationId,
			locationType:locationType,
			casteStateId:casteStateId,
			searchName:searchNameVal,
			isFinal:isFinal
		}				
		$.ajax({
          type:'GET',
          url: 'getCadreVoterDetailsBySearchAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			
			$('.searchId').show();
			$('.casteId1').show();
			$('.stateId1').show();
			  
			$('#ajaxImageIdAPmandalconstiRoleSummary').hide();
			buildSearchDetails(searchType,locationType,stateId,casteStateId,locationId,districtSel,constiSel,0,divId);
			if(result != null)
			{
				var isGreater = false;
				if(result.length>0)
				{
					var greaterId = result[0].constituencyId;
					var greaterId = result[0].constituencyId;
					if(greaterId != null && greaterId == 1)
					{
						isGreater = true;
						locationType = "LOCAL_ELECTION_BODY";
					}
					else if(greaterId != null && greaterId == 2)
					{						
						locationType = "ward";
					}
				}
				if(isGreater)
				{					
					buildSearchCandidateDetails(result,divId,"",locationType,searchType,nextLocationType,stateId,casteStateId,locationId,0,greaterId);
				}				
				else if(isFinal != 0)
				{					
					buildSearchCandidateDetails(result,dinamicDiv,"",locationType,searchType,nextLocationType,stateId,casteStateId,locationId,0,0);
				}
				else{
					buildSearchResults(result,searchType,locationType,nextLocationType,stateId,casteStateId,divId,'',locationId);
				$('#showModifiSearchId').show();
				}
				
			}
			else{
				//$('#searchDetailsDiv').html('No Data Available...');
					$('#'+divId+'').html(' <div style="align-text:center;font-weight:bold;">No Data Available ...</div>');
			}
		});
		
	}
	
	function buildSearchDetails(searchType,locationType,stateId,casteStateId,locationId,districtId,constiId,tehsilId,divId){
		var str1 ='';  
		str1+='<li><div class="styled-select black rounded" ><select id="searchId1" onchange="getCasteDetailsForSelection(0,\'\',\''+divId+'\',\''+locationType+'\')">';
		if(searchType == "ANY"){
       // str1+='<option value="0" selected >ANY</option> <option value="1" >CADRE</option><option value="2">VOTER</option>';
       str1+=' <option value="1" >CADRE</option><option value="2">VOTER</option>';
		}
		else if(searchType == "CADRE"){
		 str1+=' <option value="1" selected>CADRE</option><option value="2">VOTER</option>';
     
		}
		else if(searchType == "VOTER"){
		 str1+=' <option value="1" >CADRE</option><option value="2" selected>VOTER</option>';
        	
		}
		str1+='</select></div></li><li><div class="styled-select black rounded"><select id="stateId1" onchange="getCadreVoterDetailsForSelection1(\'stateId1\',\'state\',0,\'\',\''+divId+'\')">';
		if(stateId == 0){
        str1+='<option value="0" selected>ANY</option><option value="1">ANDHRA PRADESH</option><option value="2">TELANGANA</option>';
       
		}
		else if(stateId == 1){
		 str1+='<option value="0" >ANY</option><option value="1" selected>ANDHRA PRADESH</option><option value="2">TELANGANA</option>';
        
		
		}
		else if(stateId == 2){
		 str1+='<option value="0" >ANY</option><option value="1" selected>ANDHRA PRADESH</option><option value="2">TELANGANA</option>';
        		
		}
		str1+='</select></div></li>';
	
	
		str1+='<li><div class="styled-select black rounded" style="display:none;"  id="districtDivId1"><select id="districtId1" onchange="getCadreVoterDetailsForSelection1(\'districtId1\',\'constituency\',0,\'\',\''+divId+'\')"></select></div></li>';
		
		str1+='<li><div class="styled-select black rounded"  style="display:none;"  id="constiDivId1"><select id="constiId1" onchange="getCadreVoterDetailsForSelection1(\'constiId1\',\'tehsil\',0,\'\',\''+divId+'\')")"></select></div></li>';
		
		str1+='<li><div class="styled-select black rounded" style="display:none;"  id="tehsilDivId1"><select onchange="getCadreVoterDetailsForSelection1(\'tehsilId1\',\'panchayat\',0,\'\',\''+divId+'\')" id="tehsilId1" ></select></div></li> ';
		
		
		str1+='<li><div class="styled-select black rounded"><select id="casteId1" onchange="getCasteDetailsForSelection(0,\'\',\''+divId+'\',\''+locationType+'\')"></select></div></li>';
		$('#searchDiv').html(str1);
		if(locationType == 'state'){		
			getDistrictsAndConstis("district",stateId);
			var distOptions ='';		
				for(var i = 0; i < districtArr.length; ++i) {
				
					if(locationId == districtArr[i].value){
						 distOptions+='<option value="'+districtArr[i].value+'" selected>'+districtArr[i].label+'</option>';	
					}
					else{
						distOptions+='<option value="'+districtArr[i].value+'" >'+districtArr[i].label+'</option>';				
					}
				}
				$("#districtId1").append(distOptions);
			
			$("#constiDivId1").hide();
			$("#districtDivId1").hide();
			$("#tehsilDivId1").hide;
			
			$(".constiId1").hide();
			$(".districtId1").hide();
			$(".tehsilId1").hide;
		
		}
		
			if(locationType == 'constituency'){
			
				$("#constiDivId1").hide();
				$("#districtDivId1").show();
				$("#tehsilDivId1").hide();
				$(".constiId1").hide();
				$(".districtId1").show();
				$(".tehsilId1").hide;
			
				getDistrictsAndConstis("district",stateId);
				var distOptions ='';		
				
				for(var i = 0; i < districtArr.length; ++i) {
				
					if(locationId == districtArr[i].value){
						 distOptions+='<option value="'+districtArr[i].value+'" selected>'+districtArr[i].label+'</option>';	
					}
					else{
						distOptions+='<option value="'+districtArr[i].value+'" >'+districtArr[i].label+'</option>';				
					}
				}
				$("#districtId1").append(distOptions);
				
			}
			else if(locationType == 'tehsil' ){
		
				$("#constiDivId1").show();
				$("#districtDivId1").show();
				$("#tehsilDivId1").hide(); 
				$(".constiId1").show();
				$(".districtId1").show();
				$(".tehsilId1").hide;
				var distOptions ='';		
				
				for(var i = 0; i < districtArr.length; ++i) {
				
					if(districtId == districtArr[i].value){
						 distOptions+='<option value="'+districtArr[i].value+'" selected>'+districtArr[i].label+'</option>';	
					}
					else{
						distOptions+='<option value="'+districtArr[i].value+'" >'+districtArr[i].label+'</option>';				
					}
				}
				$("#districtId1").append(distOptions);
				var constiOptions ='';		
			
				for(var i = 0; i < constiArr.length; ++i) {				
					if(locationId == constiArr[i].value){
						 constiOptions+='<option value="'+constiArr[i].value+'" selected>'+constiArr[i].label+'</option>';	
					}
					else{
						constiOptions+='<option value="'+constiArr[i].value+'" >'+constiArr[i].label+'</option>';			
					}
				}				
				$("#constiId1").append(constiOptions);
			
			}
			else if(locationType == 'panchayat' || locationType == 'ward'){
				$("#constiDivId1").show();
				$("#districtDivId1").show();
				$("#tehsilDivId1").show();
		
				$(".constiDivId1").show();
				$(".districtDivId1").show();
				$(".tehsilDivId1").show;
				//getDistrictsAndConstis("panchayat",locationId);
				
				var distOptions ='';		
				
				for(var i = 0; i < districtArr.length; ++i) {
				
					if(districtId == districtArr[i].value){
						 distOptions+='<option value="'+districtArr[i].value+'" selected>'+districtArr[i].label+'</option>';	
					}
					else{
						distOptions+='<option value="'+districtArr[i].value+'" >'+districtArr[i].label+'</option>';				
					}
				}
				
				
				var constiOptions ='';		
			
				for(var i = 0; i < constiArr.length; ++i) {				
					if(constiId == constiArr[i].value){
						 constiOptions+='<option value="'+constiArr[i].value+'" selected>'+constiArr[i].label+'</option>';	
					}
					else{
						constiOptions+='<option value="'+constiArr[i].value+'" >'+constiArr[i].label+'</option>';			
					}
				}				
				$("#constiId1").append(constiOptions);
			
				var tehsilOptions ='';		
			
				for(var i = 0; i < mainArr.length; ++i) {				
					if(locationId == mainArr[i].value){
						 tehsilOptions+='<option value="'+mainArr[i].value+'" selected>'+mainArr[i].label+'</option>';	
					}
					else{
						tehsilOptions+='<option value="'+mainArr[i].value+'" >'+mainArr[i].label+'</option>';			
					}
				}				
				$("#tehsilId1").append(constiOptions);
			
			}
			
			
				$("#casteId1").show();
				var casteOptions ='';	
				var casteBoolean = false;				
				for(var i = 0; i < casteArr.length; ++i) {				
					if(casteStateId == casteArr[i].value){
						 casteOptions+='<option value="'+casteArr[i].value+'" selected>'+casteArr[i].label+'</option>';
						casteBoolean = true;						 
					}
					else{
						casteOptions+='<option value="'+casteArr[i].value+'" >'+casteArr[i].label+'</option>';			
					}
				}	
					if(casteBoolean == false){
				//$("#casteId1").prepend('<option value="0" selected>Any</option>');
				}else{
				//$("#casteId1").prepend('<option value="0">Any</option>');
				}
				$("#casteId1").append(casteOptions);
			
		
	}
	
	
	
	function buildSearchDetailsSecondLevel(locationId,locationType,divId){

		var searchType = $("#searchId1 option:selected").text();
		var stateId =  $("#stateId1 option:selected").val();
		var casteStateId =$("#casteId1 option:selected").val();
		var constiId = $("#constiId1").val();
		
		var districtId = $("#districtId1").val();
	
		var str1 ='';
       str1+='<li><div class="styled-select black rounded"><select id="searchId1" onchange="getCasteDetailsForSelection(0,\'\',\''+divId+'\',\''+locationType+'\')">';
		if(searchType == "ANY"){
        str1+=' <option value="1" >CADRE</option><option value="2">VOTER</option>';
       
		}
		else if(searchType == "CADRE"){
		 str1+=' <option value="1" selected>CADRE</option><option value="2">VOTER</option>';
     
		}
		else if(searchType == "VOTER"){
		 str1+=' <option value="1" >CADRE</option><option value="2" selected>VOTER</option>';
        	
		}
		str1+='</select></div> </li><li><div class="styled-select black rounded"><select id="stateId1" onchange="getCadreVoterDetailsForSelection1(\'stateId1\',\'state\',0,\'\',\''+divId+'\')">';
	
		if(stateId == 0){
        str1+='<option value="0" selected>ANY</option><option value="1">ANDHRA PRADESH</option><option value="2">TELANGANA</option>';
       
		}
		else if(stateId == 1){
		 str1+='<option value="0" >ANY</option><option value="1" selected>ANDHRA PRADESH</option><option value="2">TELANGANA</option>';
        
		
		}
		else if(stateId == 2){
		 str1+='<option value="0" >ANY</option><option value="1" >ANDHRA PRADESH</option><option value="2" selected>TELANGANA</option>';
        		
		}
		str1+='</select></div></li>';

		str1+='<li><div class="styled-select black rounded" style="display:none;"  id="districtDivId1"><select id="districtId1" onchange="getCadreVoterDetailsForSelection1(\'districtId1\',\'constituency\',0,\'\',\''+divId+'\')"></select></div></li>';
		str1+='<li><div class="styled-select black rounded"  style="display:none;"  id="constiDivId1"><select  id="constiId1" onchange="getCadreVoterDetailsForSelection1(\'constiId1\',\'tehsil\',0,\'\',\''+divId+'\')")"></select></div></li>';
		str1+='<li><div class="styled-select black rounded" style="display:none;"  id="tehsilDivId1"><select onchange="getCadreVoterDetailsForSelection1(\'tehsilId1\',\'panchayat\',0,\'\',\''+divId+'\')" id="tehsilId1" ></select></div></li> ';

		str1+='<li><div class="styled-select black rounded"><select id="casteId1" onchange="getCasteDetailsForSelection(0,\'\',\''+divId+'\',\''+locationType+'\')"></select></div></li>';
		//str1+='<button class="btn btn-success get-details m_top10" onclick="getDetailsForSelection(\''+locationId+'\',\''+locationType+'\',\''+stateId+'\',\''+searchType+'\',\''+casteStateId+'\',0,\'\')">Get Details</button>';
		
		$('#searchDiv').html(str1);
		if(locationType == 'state'){
			$("#constiDivId1").hide();
			$("#districtDivId1").hide();
			$("#tehsilDivId1").hide;
		
			$(".constiId1").hide();
			$(".districtId1").hide();
			$(".tehsilId1").hide;
			getDistrictsAndConstis("district",stateId);
			var distOptions ='';		
				
				for(var i = 0; i < districtArr.length; ++i) {
				
					if(locationId == districtArr[i].value){
						 distOptions+='<option value="'+districtArr[i].value+'" selected>'+districtArr[i].label+'</option>';	
					}
					else{
						distOptions+='<option value="'+districtArr[i].value+'" >'+districtArr[i].label+'</option>';				
					}
				}
				$("#districtId1").append(distOptions);
			
		
		}
		
		
		
		$('#searchDiv').html(str1);
		if(locationType == 'constituency'){
		
				$("#constiDivId1").hide();
				$("#districtDivId1").show();
				$("#tehsilDivId1").hide;
				
				$(".constiId1").hide();
				$(".districtId1").show();
				$(".tehsilId1").hide;
			
				$("#districtId1").find('option').remove();
				getDistrictsAndConstis("district",stateId);
				
				var distOptions ='';		
				
				for(var i = 0; i < districtArr.length; ++i) {
				
					if(locationId == districtArr[i].value){
						 distOptions+='<option value="'+districtArr[i].value+'" selected>'+districtArr[i].label+'</option>';	
					}
					else{
						distOptions+='<option value="'+districtArr[i].value+'" >'+districtArr[i].label+'</option>';				
					}
				}
				$("#districtId1").append(distOptions);
				
		}
		
		else if(locationType == 'tehsil'){
		
				$("#constiDivId1").show();
				$("#districtDivId1").show();
				$("#tehsilDivId1").hide;
				
				$(".constiId1").show();
				$(".districtId1").show();
				$(".tehsilId1").hide;
			
				$("#districtId1").find('option').remove();
				$("#constiId1").find('option').remove();
				
				var distOptions ='';		
				
				for(var i = 0; i < districtArr.length; ++i) {
				
					if(districtId == districtArr[i].value){
						 distOptions+='<option value="'+districtArr[i].value+'" selected>'+districtArr[i].label+'</option>';	
					}
					else{
						distOptions+='<option value="'+districtArr[i].value+'" >'+districtArr[i].label+'</option>';				
					}
				}
				$("#districtId1").append(distOptions);
				var constiOptions ='';		
				if(typeof mainConstiArr !== 'undefined' && mainConstiArr.length > 0 )
				{
				for(var i = 0; i < mainConstiArr.length; ++i) {				
					if(locationId == mainConstiArr[i].value){
						 constiOptions+='<option value="'+mainConstiArr[i].value+'" selected>'+mainConstiArr[i].label+'</option>';	
					}
					else{
						constiOptions+='<option value="'+mainConstiArr[i].value+'" >'+mainConstiArr[i].label+'</option>';			
					}
				}
				}else{
					if(typeof constiArr !== 'undefined' && constiArr.length > 0 )
					{
						for(var i = 0; i < constiArr.length; ++i) {				
							if(constiId == constiArr[i].value){
								constiOptions+='<option value="'+constiArr[i].value+'" selected>'+constiArr[i].label+'</option>';	
							}
							else{
								constiOptions+='<option value="'+constiArr[i].value+'" >'+constiArr[i].label+'</option>';			
							}
					}
				
				}
				}
				$("#constiId1").append(constiOptions);
		}
		else if(locationType == 'panchayat'  || locationType == 'ward'){
			
				$("#constiDivId1").show();
				$("#districtDivId1").show();
				$("#tehsilDivId1").show();
				
				$(".constiId1").show();
			$(".districtId1").show();
			$(".tehsilId1").show();
			
				$("#tehsilId1").find('option').remove();
				$("#districtId1").find('option').remove();
				$("#constiId1").find('option').remove();
				
				var distOptions ='';		
				
				for(var i = 0; i < districtArr.length; ++i) {
				
					if(districtId == districtArr[i].value){
						 distOptions+='<option value="'+districtArr[i].value+'" selected>'+districtArr[i].label+'</option>';	
					}
					else{
						distOptions+='<option value="'+districtArr[i].value+'" >'+districtArr[i].label+'</option>';				
					}
				}
				$("#districtId1").append(distOptions);
				var constiOptions ='';		
					if(typeof mainConstiArr !== 'undefined' && mainConstiArr.length > 0 ){
					for(var i = 0; i < mainConstiArr.length; ++i) {				
						if(constiId == mainConstiArr[i].value){
							 constiOptions+='<option value="'+mainConstiArr[i].value+'" selected>'+mainConstiArr[i].label+'</option>';	
						}
						else{
							constiOptions+='<option value="'+mainConstiArr[i].value+'" >'+mainConstiArr[i].label+'</option>';			
						}
					}
					}else{
						
					if(typeof constiArr !== 'undefined' && constiArr.length > 0 )
					{
						for(var i = 0; i < constiArr.length; ++i) {				
							if(constiId == constiArr[i].value){
								 constiOptions+='<option value="'+constiArr[i].value+'" selected>'+constiArr[i].label+'</option>';	
							}
							else{
								constiOptions+='<option value="'+constiArr[i].value+'" >'+constiArr[i].label+'</option>';			
							}
						}
					}
				}				
				$("#constiId1").append(constiOptions);
				var tehsilOptions ='';		
			
				for(var i = 0; i < mainTehsilArr.length; ++i) {				
					if(locationId == mainTehsilArr[i].value1){
						 tehsilOptions+='<option value="'+mainTehsilArr[i].value1+'" selected>'+mainTehsilArr[i].label1+'</option>';	
					}
					else{
						tehsilOptions+='<option value="'+mainTehsilArr[i].value1+'" >'+mainTehsilArr[i].label1+'</option>';			
					}
				}				
				$("#tehsilId1").append(tehsilOptions);
		}
			
			
			
				var casteBoolean = false;		
				var casteOptions ='';		
				for(var i = 0; i < casteArr.length; ++i) {				
					if(casteStateId == casteArr[i].value){
						 casteOptions+='<option value="'+casteArr[i].value+'" selected>'+casteArr[i].label+'</option>';
						casteBoolean = true;								 
					}
					else{
						casteOptions+='<option value="'+casteArr[i].value+'" >'+casteArr[i].label+'</option>';			
					}
				}	
				if(casteBoolean == false){
				//$("#casteId1").prepend('<option value="0" selected>Any</option>');
				}else{
				//$("#casteId1").prepend('<option value="0">Any</option>');
				}
				$("#casteId1").append(casteOptions);				
				$("#casteId1").append(casteOptions);
			
	
	}
	
	//sri2
	function getCadreVoterDetailsForSelection1(locId,locationType,isFinalValue,getDetailsAreaType,divId)
	{	
		$('#positoinDivId').show();
		$('#'+divId+'').html('');
		var divStr;			
			divStr = dinamicDiv.substr(0, dinamicDiv.length - 1);	

		$('#'+divStr+'').html('');  
		$('#'+dinamicDiv+'').html('');  
		$('#ajaxImageIdAPmandalconstiRoleSummary').show();
		var locationId = $("#"+locId).val();
		var locationName = $("#"+locId+' option:selected').text();
		var searchType = $("#searchId1 option:selected").text();
		var stateId =  $("#stateId1 option:selected").val();
		var casteStateId =$("#casteId1 option:selected").val();
		$('.tehsilId1').hide();
		buildSearchDetailsSecondLevel(locationId,locationType,divId);
		
		var searchName =  $('#enteredText').val();
		var isFinal = "";
		if(isFinalValue != 0)
		{
			isFinal = "Yes";
			locationType = getDetailsAreaType;
		}
		var nextLocationType = "";
		var isMuncipalityStr = "";
		if(locationType == 'state') 
		{
			nextLocationType ="constituency";
		}
		else if(locationType == 'constituency')
		{
			nextLocationType ="tehsil";
		}
		else if(locationType == 'tehsil')
		{
			nextLocationType ="panchayat";
		}

		if(locationName.indexOf('muncipality') != -1 || locationName.indexOf('corporation') != -1){
			locationType = "ward";
				if(searchType =='VOTER')
				{
					isFinalValue = "votersCount";					
				}
				else if(searchType =='CADRE')
				{
					isFinalValue = "cadreCount";
				}
			}

		$('#positoinDivId').hide();			
		var jObj = {
			searchType:searchType,			
			stateId:stateId,
			locationId:locationId,
			locationType:locationType,
			casteStateId:casteStateId,
			searchName:searchName,
			isFinal:isFinal
		}	
		
		$.ajax({
          type:'GET',
          url: 'getCadreVoterDetailsBySearchAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
		//debugger;
        $('#ajaxImageIdAPmandalconstiRoleSummary').hide();
			if(locationName.indexOf('muncipality') != -1 || locationName.indexOf('corporation') != -1){
				locationType = "LOCAL_ELECTION_BODY";
				if(searchType =='VOTER')
				{
					isFinalValue = "votersCount";					
				}
				else if(searchType =='CADRE')
				{
					isFinalValue = "cadreCount";
				}
			}  
			
			if(result != null)
			{
				var isGreater = false;
				if(result.length>0)
				{
					var greaterId = result[0].constituencyId;
					var greaterId = result[0].constituencyId;
					if(greaterId != null && greaterId == 1)
					{
						isGreater = true;
						locationType = "ward";
					}
					else if(greaterId != null && greaterId == 2)
					{						
						locationType = "ward";
						isFinalValue = "defaultCount";
					}
					else if(greaterId != null && greaterId == 3)
					{			
						isGreater = true;				
						locationType = "ward";
					}
				}
				console.log("locationType 2222 :"+locationType);
				console.log("isFinalValue 2222 :"+isFinalValue);
				console.log("isGreater 2222 :"+isGreater);
				if(isGreater)
				{					
					buildSearchCandidateDetails(result,dinamicDiv,locationName,locationType,searchType,nextLocationType,stateId,casteStateId,locationId,0,greaterId);
				}				
				else if(isFinalValue != 0)
				{					
					buildSearchCandidateDetails(result,dinamicDiv,locationName,locationType,searchType,nextLocationType,stateId,casteStateId,locationId,0,0);
				}
				else{				
					buildSearchResults(result,searchType,locationType,nextLocationType,stateId,casteStateId,dinamicDiv,locationName,locationId);
				}
				
			}
			else{
				$('#'+dinamicDiv+'').html(' <div style="align-text:center;font-weight:bold;">No Data Available ...</div>');
			}
		});
		
	}
	
	function buildForGreaterSearchCandidateDetails(myresults,divId,locationName,locationType,searchType,nextLocationType,stateId,casteStateId,locationId,isMuncipality,greaterId)
	{
		$('#positoinDivId').show();	
		var result = myresults[0].cadreSearchList;
		divCount = parseInt(divCount)+1;
		if(divCount > 2)
		{
			divCount = 2;
		}	
		var isDataAvailable = false;
		var localLocationType
		if(locationType =='state')
		{
			localLocationType = 'District';
		}
		else
		{
			localLocationType = locationType;
		}
		
		var str='';
		if(result != null && result.length>0)
		{
			isDataAvailable = true;
			str+='<div> <h4 class="text-center "> '+capitalize(locationName.toLowerCase())+' Cadre Details </h4></div>';	
			str+='<div style="font-weight:bold;margin-left:" class="offset10"> Search Caste : <input type="text" id="searchCasteDivId" style="height: 30px;">  </div>';				
			str+='<div class="pt-page pt-page-6 container" id="casteDiv" style="margin-top:25px;">';		
			str+='<div class="search-results">';
			str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
			
			for(var i in result)
			{
				//
				str+='<div class="panel panel-default search-panel" id="'+result[i].casteName+'DivId">';
				str+='<div class="panel-heading search-panel-heading" role="tab" id="heading'+i+'">';
				str+='<h4 class="panel-title">';
				str+='<a class="collapsed" aria-expanded="false" data-parent="#accordion" aria-controls="collapse'+i+'" onclick="getCasteMembers(\'collapse'+i+'\');getTotalCandidateDetailsForCaste('+locationId+',\''+locationType+'\','+stateId+',\''+searchType+'\','+result[i].id+',\'casteStateDiv'+i+'\',\''+locationName+'\',\''+isMuncipality+'\',\'cadreDetails\','+greaterId+');" style="cursor: pointer">';
				
				if(result[i].casteName != null && result[i].casteName.length>0)
					str+='<h3 style="display:inline-block;margin:0px;">'+result[i].casteName+'</h3>';
				else
					str+='<h3 style="display:inline-block;margin:0px;"> Others </h3>';
				str+='<h5 style="display:inline-block;margin-left:50px;" class="badge pull-right"> Total : '+result[i].totalCount+'</h5>';
				str+='</a>';
				str+='</h4>';
				str+='</div>';

				str+='<div id="collapse'+i+'" class="collapse panel-collapse" aria-labelledby="heading'+i+'" style="width: 1120px;">';
				str+='<div class="panel-body detailsCls" id="casteStateDiv'+i+'" >';				
				str+='</div>';
				str+='</div>';
				str+='</div>';
					
			}
			
			str+='</div>';
			str+='</div>';
			str+='</div>';
		}
		
		var result = myresults[0].voterSearchList

		if(result != null && result.length>0)
		{
			isDataAvailable = true;
			str+='<div> <h4 class="text-center "> '+locationName+'  '+locationType+' Voter Details </h4></div>';
			str+='<div style="font-weight:bold;margin-left:" class="offset10"> Search Caste : <input type="text" id="searchCasteDivId" style="height: 30px;">  </div>';				
			str+='<div class="pt-page pt-page-6 container" id="casteDiv" style="margin-top:25px;margin-bottom:15px;">';		
			str+='<div class="search-results" >';
			str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
		
			for(var i in result)
			{
				
				str+='<div class="panel panel-default search-panel"  id="'+result[i].casteName+'DivId">';
				str+='<div class="panel-heading search-panel-heading" role="tab" id="heading1'+i+'">';
				str+='<h4 class="panel-title">';
				str+='<a class="collapsed" aria-expanded="false" data-parent="#accordion" aria-controls="collapse'+i+'" onclick="getCasteMembers(\'collapse1'+i+'\');getTotalCandidateDetailsForCaste('+locationId+',\''+locationType+'\','+stateId+',\''+searchType+'\','+result[i].id+',\'casteStatesDiv'+i+'\',\''+locationName+'\',\''+isMuncipality+'\',\'votersDetails\','+greaterId+');" style="cursor: pointer">';
				
				if(result[i].casteName != null && result[i].casteName.length>0)
					str+='<h3 style="display:inline-block;margin:0px;">'+result[i].casteName+'</h3>';
				else
					str+='<h3 style="display:inline-block;margin:0px;"> Others </h3>';
				
				str+='<h5 style="display:inline-block;margin-left:50px;" class="badge pull-right"> Total : '+result[i].totalCount+'</h5>';
				str+='</a>';
				str+='</h4>';
				str+='</div>';
					str+='<div id="collapse1'+i+'" class="collapse panel-collapse" aria-labelledby="heading1'+i+'"  style="width: 1120px;">';
				str+='<div class="panel-body detailsCls"  id="casteStatesDiv'+i+'">';				
				str+='</div>';
				str+='</div>';
				str+='</div>';
					
			}
			
			str+='</div>';
			str+='</div>';
			str+='</div>';
		}
		
		if(isDataAvailable)
		{			
			$('#'+divId+'').html(str);			
		}
		else{
			$('#'+divId+'').html(' <div style="align-text:center;font-weight:bold;">No Data Available ...</div>');
		}
		
		$('#'+divId+'').addClass('animated  fadeInRight');

		$('#'+divId+'').after('<div id="'+divId+''+divCount+'"></div>');
		
		dinamicDiv = ''+divId+''+divCount+'';
		
		var mapping = { };
		var casteSource = new Array();
					for(var i = 0; i < casteArr.length; ++i) {
						casteSource.push(casteArr[i].label);
						mapping[casteArr[i].label] = casteArr[i].value;
					}	
					
		$('#searchCasteDivId').autocomplete({
						minLength: 1,
						source: casteSource,
						select: function(event, ui) {
							var candiCasteName = mapping[ui.item.value];	
							for(var i = 0; i < casteArr.length; ++i) {
								if(casteArr[i].value == candiCasteName)
								{
									var casteName = casteArr[i].label;
									$('html, body').animate({scrollTop:$('#'+casteName+'DivId').offset().top}, 'slow');
									$('#searchCasteDivId').val('');
								}
							}
						}
					});
			$('#positoinDivId').show();		
									
	}
	
	function getCastwiswDetailsSelection(locationId,locationType,stateId,searchType,casteStateId,isFinalValue,getDetailsAreaType,divId,isMuncipality,presentDiv,locationName)
	{		
		$('#'+presentDiv+'').html('');
	
		$('#ajaxImageIdAPmandalconstiRoleSummary').show();
		var searchName = $('#enteredText').val();
		var isFinal = "";
		if(isFinalValue != 0)
		{
			isFinal = isFinalValue;
			locationType = getDetailsAreaType;
		}
		
		var nextLocationType = "";
		if(locationType == 'state')
		{
			nextLocationType ="constituency";
		}
		else if(locationType == 'constituency')
		{
			nextLocationType ="tehsil";
		}
		else if(locationType == 'tehsil')
		{
			nextLocationType ="panchayat";
		}
		
		if(isMuncipality != 0)
				locationType ="ward";
			
		$('#positoinDivId').hide();
		var jObj = {
			searchType:searchType,			
			stateId:stateId,
			locationId:locationId,
			locationType:locationType,
			casteStateId:casteStateId,
			searchName:searchName,
			isFinal:isFinal
		}	
		
		$.ajax({
          type:'GET',
          url: 'getCadreVoterDetailsBySearchAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildSearchDetailsSecondLevel(locationId,locationType,divId);
			if(locationName.indexOf('muncipality') != -1 || locationName.indexOf('corporation') != -1){
				if(searchType =='VOTER')
					{
						isFinalValue = "votersCount";
						locationType = "ward";
					}
					else if(searchType =='CADRE')
					{
						isFinalValue = "cadreCount";
						locationType = "ward";
					}			
			}	
			$('#ajaxImageIdAPmandalconstiRoleSummary').hide();
			
			if(result != null)
			{
				var isGreater = false;
				var greaterId = 0;
				if(result.length>0)
				{
					greaterId = result[0].constituencyId;
					if(greaterId != null && greaterId == 1)
					{
						isGreater = true;
						locationType = "LOCAL_ELECTION_BODY";
					}
					else if(greaterId != null && greaterId == 2)
					{
						locationType = "ward";
					}
					else if(greaterId != null && greaterId == 3)
					{			
						isGreater = true;				
						locationType = "LOCAL_ELECTION_BODY";
					}
				}	
					buildSearchCandidateDetails(result,divId,locationName,locationType,searchType,nextLocationType,stateId,casteStateId,locationId,isMuncipality,greaterId);
							
			}
			else{
				buildSearchDetailsSecondLevel(locationId,locationType,divId);
				$('#'+divId+'').html(' <div style="align-text:center;font-weight:bold;">No Data Available ...</div>');
			}
		});		
	}
	
	function getDetailsForSelection(locationId,locationType,stateId,searchType,casteStateId,isFinalValue,getDetailsAreaType,divId,isMuncipality,presentDiv,locationName)
	{		
		$('#'+presentDiv+'').html('');
	
		$('#ajaxImageIdAPmandalconstiRoleSummary').show();
		var searchName = $('#enteredText').val();
		var isFinal = "";
		if(isFinalValue != 0)
		{
			isFinal = isFinalValue;
			locationType = getDetailsAreaType;
		}
		
		var nextLocationType = "";
		if(locationType == 'state')
		{
			nextLocationType ="constituency";
		}
		else if(locationType == 'constituency')
		{
			nextLocationType ="tehsil";
		}
		else if(locationType == 'tehsil')
		{
			nextLocationType ="panchayat";
		}
		
		if(isMuncipality != 0)
				locationType ="ward";
			
		$('#positoinDivId').hide();
		var jObj = {
			searchType:searchType,			
			stateId:stateId,
			locationId:locationId,
			locationType:locationType,
			casteStateId:casteStateId,
			searchName:searchName,
			isFinal:isFinal
		}	
		
		$.ajax({
          type:'GET',
          url: 'getCadreVoterDetailsBySearchAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildSearchDetailsSecondLevel(locationId,locationType,divId);
			if(locationName.indexOf('muncipality') != -1 || locationName.indexOf('corporation') != -1){
				if(searchType =='VOTER')
					{
						isFinalValue = "votersCount";
						locationType = "ward";
					}
					else if(searchType =='CADRE')
					{
						isFinalValue = "cadreCount";
						locationType = "ward";
					}			
			}	
			$('#ajaxImageIdAPmandalconstiRoleSummary').hide();
			
			if(result != null)
			{
				var isGreater = false;
				if(result.length>0)
				{
					var greaterId = result[0].constituencyId;
					if(greaterId != null && greaterId == 1)
					{
						isGreater = true;
						locationType = "constituency";
					}
					else if(greaterId != null && greaterId == 2)
					{
						locationType = "ward";
					}
					else if(greaterId != null && greaterId == 3)
					{			
						isGreater = true;				
						locationType = "LOCAL_ELECTION_BODY";
					}
				}
				 
				console.log("locationType 222 :"+locationType);
				console.log("isFinalValue 222 :"+isFinalValue);
				console.log("isGreater 222 :"+isGreater);
				
				if(isGreater)
				{					
					buildSearchCandidateDetails(result,divId,locationName,locationType,searchType,nextLocationType,stateId,casteStateId,locationId,isMuncipality,greaterId);
				}
				else if(locationType == 'ward' || isFinalValue != 0)
				{	
					buildSearchCandidateDetails(result,divId,locationName,locationType,searchType,nextLocationType,stateId,casteStateId,locationId,isMuncipality,0);
				}
				else{
					buildSearchDetailsSecondLevel(locationId,locationType,divId);
					buildSearchResults(result,searchType,locationType,nextLocationType,stateId,casteStateId,divId,locationName,locationId);
				}				
			}
			else{
				buildSearchDetailsSecondLevel(locationId,locationType,divId);
				$('#'+divId+'').html(' <div style="align-text:center;font-weight:bold;">No Data Available ...</div>');
			}
		});		
	}
	
	function capitalize(str) {
		strVal = '';
		str = str.split(' ');
			for (var chr = 0; chr < str.length; chr++) {
				strVal += str[chr].substring(0, 1).toUpperCase() + str[chr].substring(1, str[chr].length) + ' '
			}
		return strVal
	}
//srishailam
	function buildSearchResults(myResult,searchType,locationType,nextLocationType,stateId,casteStateId,divId,locationName,LocalLocationId)
	{
		$('#positoinDivId').hide();
			$('#'+divId+'').html('');
		var result = myResult[0].cadreSearchList;
			divCount = parseInt(divCount)+1;
			if(divCount > 2)
			{
				divCount = 2;
			}
			if(locationType == "constituency")
				mainConstiArr = new Array();
			else if(locationType == "tehsil")
				mainTehsilArr = new Array();
		var str ='';
		if(typeof locationName == 'undefined')
		{
			locationName = " ";
			
		}
		
		glblLocationId=LocalLocationId;
		glblLocationType=locationType;
			
		var areaType ='';		
		var displayLevel = '';
		
		
		if(locationType == 'state')
		{
			locationName = $('#stateId1 option:selected').text();
			if(locationName == 'ANY' || locationName == 'Any' || locationName == 'any' )
			{
				locationName = " AP & TS ";
			}
			displayLevel =" State District ";
		}
		else if(locationType == 'constituency')
		{
			displayLevel =" District ";
		}
		else if(locationType == 'tehsil')
		{
			displayLevel ="Constituency";
		}
		else if(locationType == 'panchayat' || locationType == 'ward')
		{
			displayLevel =" Mandal/Muncipality ";
		}
		if(locationName.indexOf('muncipality') != -1 || locationName.indexOf('corporation') != -1){
			displayLevel ='';
		}
		
			if(result.length>0)
			{
				str+='<div class="text-center"><h4> '+capitalize(locationName.toLowerCase())+' '+capitalize(displayLevel)+' '+capitalize(locationType)+' wise Cadre Details </h4></div>';
				for(var i in result)
				{
					var isMuncipality = 0;
					if(result[i].responseCode != null)
					{
						isMuncipality = 1;
					}
					
					str+='<div class="district-box get-details">';
					if(locationType == 'panchayat' || locationType == 'ward')
					{	
						str+='<a href="javascript:{getCastwiswDetailsSelection('+result[i].constituencyId+',\''+nextLocationType+'\','+stateId+',\''+searchType+'\','+casteStateId+',\'cadreCount\',\''+locationType+'\',\''+divId+''+divCount+'\','+isMuncipality+',\''+divId+'\',\''+result[i].constituency+'\');}"  title="Click here to get Cadre Details"  style="display:inline-block;">&nbsp;&nbsp; <h5 class="" style="display:inline-block;"> &nbsp;&nbsp;'+result[i].constituency+'  <span style="margin-left:8px;"> '+areaType+'</span></h5></a>';
					}
					else{
						str+='<a href="javascript:{getDetailsForSelection('+result[i].constituencyId+',\''+nextLocationType+'\','+stateId+',\''+searchType+'\','+casteStateId+',0,\''+locationType+'\',\''+divId+''+divCount+'\','+isMuncipality+',\''+divId+'\',\''+result[i].constituency+'\');}" class="district-box-name get-details" title="Click here to get Sub Level Details">';	
						str+='<h5 class="" style="display:inline-block;"> &nbsp;&nbsp;'+result[i].constituency+'  <span style="margin-left:8px;"> '+areaType+'</span></h5></a>';
					}
					
					str+='<span class="pull-right" style="margin-top:8px;"><a href="javascript:{getCastwiswDetailsSelection('+result[i].constituencyId+',\''+nextLocationType+'\','+stateId+',\''+searchType+'\','+casteStateId+',\'cadreCount\',\''+locationType+'\',\''+divId+''+divCount+'\','+isMuncipality+',\''+divId+'\',\''+result[i].constituency+'\');}"  class="badge badge-success" title="Click here to get Cadre Details"  style="color: #fff; margin-top: 5px;"> &nbsp;'+result[i].totalCount+' &nbsp;</a></span>';
					str+='</div>';	
						if(typeof mainConstiArr != 'undefined' && (mainConstiArr.length ==0 || mainConstiArr.length < result.length))
						{
							if(locationType == "constituency")
							{
		
								var obj = {
								value :  result[i].constituencyId,
								label :  result[i].constituency,
								responseCode : result[i].responseCode
								}			
								mainConstiArr.push(obj);
							}								
						}
						
						if(typeof mainTehsilArr != 'undefined' && (mainTehsilArr.length ==0 || mainTehsilArr.length < result.length))
						{
							if(locationType == "tehsil"){
								var obj1 = {
									value1 :  result[i].constituencyId,
									label1 :  result[i].constituency,
									responseCode : result[i].responseCode
								}			
								mainTehsilArr.push(obj1);
							}
						}
				}				
			}
		
			var result = myResult[0].voterSearchList;
			if(result.length>0)
			{
				str+='<br><br>';
				str+='<div class="text-center"><h4> '+capitalize(locationName.toLowerCase())+' '+capitalize(displayLevel)+' '+capitalize(locationType)+' wise Voter Details </h4></div>';
				if(locationType != 'ward')
				{
					for(var i in result)
					{
						var isMuncipality = 0;
						if(result[i].responseCode != null)
						{
							isMuncipality = 1;
						}
						
						str+='<div class="district-box get-details">';
						if(locationType == 'panchayat' || locationType == 'ward')
						{	
							str+='<a href="javascript:{getCastwiswDetailsSelection('+result[i].constituencyId+',\''+nextLocationType+'\','+stateId+',\''+searchType+'\','+casteStateId+',\'votersCount\',\''+locationType+'\',\''+divId+''+divCount+'\','+isMuncipality+',\''+divId+'\',\''+result[i].constituency+'\');}"  title="Click here to get Voter Details"  style="display:inline-block;">&nbsp;&nbsp;<h5 class="" style="display:inline-block;"> &nbsp;&nbsp;'+result[i].constituency+'  <span style="margin-left:8px;"> '+areaType+'</span></h5></a>';
						}
						else{
							str+='<a href="javascript:{getDetailsForSelection('+result[i].constituencyId+',\''+nextLocationType+'\','+stateId+',\''+searchType+'\','+casteStateId+',0,\''+locationType+'\',\''+divId+''+divCount+'\','+isMuncipality+',\''+divId+'\',\''+result[i].constituency+'\');}" class="district-box-name get-details" title="Click here to get Sub Level Details">';	
							str+='<h5 class="" style="display:inline-block;"> &nbsp;&nbsp;'+result[i].constituency+'   <span style="margin-left:8px;"> '+areaType+'</span></h5></a>';
						}
						
						str+='<span class="pull-right" style="margin-top:8px;"><a href="javascript:{getCastwiswDetailsSelection('+result[i].constituencyId+',\''+nextLocationType+'\','+stateId+',\''+searchType+'\','+casteStateId+',\'votersCount\',\''+locationType+'\',\''+divId+''+divCount+'\','+isMuncipality+',\''+divId+'\',\''+result[i].constituency+'\');}"  class="badge badge-success" title="Click here to get Voter Details"  style="color:#fff; margin-top: 5px;"> &nbsp;'+result[i].totalCount+' &nbsp;</a></span>';
						str+='</div>';	
						
							if(typeof mainConstiArr != 'undefined' && (mainConstiArr.length ==0 || mainConstiArr.length < result.length))
						{
							if(locationType == "constituency")
							{
		
								var obj = {
								value :  result[i].constituencyId,
								label :  result[i].constituency,
								responseCode : result[i].responseCode
								}			
								mainConstiArr.push(obj);
							}	
							
						}
						
						if(typeof mainTehsilArr != 'undefined' && (mainTehsilArr.length ==0 || mainTehsilArr.length < result.length))
						{
							if(locationType == "tehsil"){
								var obj1 = {
									value1 :  result[i].constituencyId,
									label1 :  result[i].constituency,
									responseCode : result[i].responseCode
								}			
								mainTehsilArr.push(obj1);
							}
						}					
					}	
				}							
			}
			
			if(str.length>0)
				$('#'+divId+'').html(str);
			else
				$('#'+divId+'').html(' <div style="align-text:center;font-weight:bold;">No Data Available ...</div>');
			
			$('#'+divId+'').after('<div id="'+divId+''+divCount+'"></div>');		
			
			$('.fadeInRight').addClass('animated  fadeOutLeft ');			
			//$('.pt-page-1').hide();
			$('.fadeInRight').remove();
			$('#'+divId+'').addClass('animated  fadeInRight');
			var divStr;			
			divStr = divId.substr(0, divId.length - 1);	
			
			$('#'+divStr+'').hide();
			
			dinamicDiv = ''+divId+''+divCount+'';
	}
	
	
	function getCasteDetailsForSelection(isFinalValue,getDetailsAreaType,divId,locationType){

		var locationId = 0;
			
		var searchType = $("#searchId1 option:selected").text();
		var stateId =  $("#stateId1 option:selected").val();
		var casteStateId =$("#casteId1 option:selected").val();
		 $('#ajaxImageIdAPmandalconstiRoleSummary').show();
		//buildSearchDetailsSecondLevel(locationId,locationType,divId);
		var searchName =  $('#enteredText').val();
		var locationName ='';
		var actualLocationType = locationType;
		var isFinal = "";
		if(isFinalValue != 0)
		{
			isFinal = "Yes";
			locationType = getDetailsAreaType;
		}

		var nextLocationType = "";
		if(locationType == 'state')
		{
			nextLocationType ="constituency";
			locationId = $("#stateId1 option:selected").val();
		}
		else if(locationType == 'constituency')
		{
			nextLocationType ="tehsil";
			locationId = $("#districtId1 option:selected").val();
			locationName = $("#districtId1 option:selected").text();
		}
		else if(locationType == 'tehsil')
		{
			nextLocationType ="panchayat";
			locationId = $("#constiId1 option:selected").val();
			locationName = $("#constiId1 option:selected").text();
		}
		else if(locationType == 'panchayat')
		{
			nextLocationType ="";
			locationId = $("#tehsilId1 option:selected").val();
			locationName = $("#tehsilId1 option:selected").text();
		}
		else if(locationType == 'ward')
		{
			nextLocationType ="";
			locationId = $("#tehsilId1 option:selected").val();
			locationName = $("#tehsilId1 option:selected").text();
		}
		
		if(searchType == 'VOTER')
		{
			if(actualLocationType == 'LOCAL_ELECTION_BODY')
			{
				locationType ="ward";
			}				
			else if(actualLocationType == 'ward')
			{
				locationType ="ward";
			}				
		}
		
		
		$('#positoinDivId').hide();
		$('#searchDetailsDiv').html('');
		$('#'+dinamicDiv+'').html('');
		$('#positoinDivId').hide();
		var jObj = {  
			searchType:searchType,			
			stateId:stateId,
			locationId:locationId,
			locationType:locationType,
			casteStateId:casteStateId,
			searchName:searchName,
			isFinal:isFinal
		}				
		$.ajax({
          type:'GET',
          url: 'getCadreVoterDetailsBySearchAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			$('#ajaxImageIdAPmandalconstiRoleSummary').hide();
			if(result != null)
			{
				var isGreater = false;
				if(result.length>0)
				{
					var greaterId = result[0].constituencyId;
					if(greaterId != null && greaterId == 1)
					{
						isGreater = true;
						locationType = "ward";
					}
					else if(greaterId != null && greaterId == 2)
					{						
						locationType = "ward";
						isFinalValue = "defaultCount";
					}
					else if(greaterId != null && greaterId == 3)
					{			
						isGreater = true;				
						locationType = "ward";
					}
				}
				console.log("locationType 1111 :"+locationType);
				console.log("isFinalValue 1111 :"+isFinalValue);
				console.log("isGreater 1111 :"+isGreater);  
				console.log("searchType 1111 :"+searchType);
				if(isGreater)
				{
					if(searchType == 'CADRE')
						buildSearchCandidateDetails(result,divId,locationName,locationType,searchType,nextLocationType,stateId,casteStateId,locationId,0,greaterId);
					else if(searchType == 'VOTER')
					{
						buildSearchCandidateDetails(result,divId,locationName,locationType,searchType,nextLocationType,stateId,casteStateId,locationId,0,greaterId);
					}
				}
				else if(isFinalValue != 0)
				{
					if(searchType == 'CADRE')
						buildSearchCandidateDetails(result,divId,'',locationType,searchType,nextLocationType,stateId,casteStateId,locationId,locationName,greaterId);
					else if(searchType == 'VOTER')
					{
						buildSearchCandidateDetails(result,divId,'',locationType,searchType,nextLocationType,stateId,casteStateId,locationId,locationName,greaterId);
					}
					
				}
				else{
				
					buildSearchResults(result,searchType,locationType,nextLocationType,stateId,casteStateId,dinamicDiv,locationName,locationId);
				}
				
			}
			else{
				$('#'+dinamicDiv+'').html(' <div style="align-text:center;font-weight:bold;">No Data Available ...</div>');
			}
		});
	}
	//sri3
	function buildSearchCandidateDetails(myresults,divId,locationName,locationType,searchType,nextLocationType,stateId,casteStateId,locationId,isMuncipality,greaterId)
	{
		console.log("entered into buliding caste details ");
		console.log("entered into buliding caste details divId : "+divId);
		$('#positoinDivId').show();	
		var result = myresults[0].cadreSearchList;
		divCount = parseInt(divCount)+1;
		if(divCount > 2)
		{
			divCount = 2;
		}	
		var isDataAvailable = false;
		var localLocationType
		if(locationType =='state')
		{
			localLocationType = 'District';
		}
		else
		{
			localLocationType = locationType;
		}
		if(localLocationType == 'LOCAL_ELECTION_BODY')
		{
			 localLocationType="";
		}
		var str='';
		if(result != null && result.length>0)
		{
			isDataAvailable = true;
			str+='<div> <h4 class="text-center "> '+capitalize(locationName.toLowerCase())+' '+capitalize(localLocationType.toLowerCase())+' Cadre Details </h4></div>';	
			str+='<div style="font-weight:bold;margin-left:" class="offset10"> Search Caste : <input type="text" id="searchCasteDivId" style="height: 30px;">  </div>';				
			str+='<div class="pt-page pt-page-6 container" id="casteDiv" style="margin-top:25px;">';		
			str+='<div class="search-results">';
			str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
			
			for(var i in result)
			{
				//
				str+='<div class="panel panel-default search-panel" id="'+result[i].casteName+'DivId">';
				str+='<div class="panel-heading search-panel-heading" role="tab" id="heading'+i+'">';
				str+='<h4 class="panel-title">';
				str+='<a class="collapsed" aria-expanded="false" data-parent="#accordion" aria-controls="collapse'+i+'" onclick="getCasteMembers(\'collapse'+i+'\');getTotalCandidateDetailsForCaste('+locationId+',\''+locationType+'\','+stateId+',\''+searchType+'\','+result[i].id+',\'casteStateDiv'+i+'\',\''+locationName+'\',\''+isMuncipality+'\',\'cadreDetails\','+greaterId+');" style="cursor: pointer">';
				
				if(result[i].casteName != null && result[i].casteName.length>0)
					str+='<h3 style="display:inline-block;margin:0px;">'+result[i].casteName+'</h3>';
				else
					str+='<h3 style="display:inline-block;margin:0px;"> Others </h3>';
				str+='<h5 style="display:inline-block;margin-left:50px;" class="badge pull-right"> Total : '+result[i].totalCount+'</h5>';
				str+='</a>';
				str+='</h4>';
				str+='</div>';

				str+='<div id="collapse'+i+'" class="collapse panel-collapse" aria-labelledby="heading'+i+'" style="width: 1120px;">';
				str+='<div class="panel-body detailsCls" id="casteStateDiv'+i+'" >';				
				str+='</div>';
				str+='</div>';
				str+='</div>';
					
			}
			
			str+='</div>';
			str+='</div>';
			str+='</div>';
		}
		
		var result = myresults[0].voterSearchList;

		if(result != null && result.length>0)
		{
			isDataAvailable = true;
			str+='<div> <h4 class="text-center "> '+locationName+'  '+locationType+' Voter Details </h4></div>';
			str+='<div style="font-weight:bold;margin-left:" class="offset10"> Search Caste : <input type="text" id="searchCasteDivId" style="height: 30px;">  </div>';				
			str+='<div class="pt-page pt-page-6 container" id="casteDiv" style="margin-top:25px;margin-bottom:15px;">';		
			str+='<div class="search-results" >';
			str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
		
			for(var i in result)
			{
				
				str+='<div class="panel panel-default search-panel"  id="'+result[i].casteName+'DivId">';
				str+='<div class="panel-heading search-panel-heading" role="tab" id="heading1'+i+'">';
				str+='<h4 class="panel-title">';
				str+='<a class="collapsed" aria-expanded="false" data-parent="#accordion" aria-controls="collapse'+i+'" onclick="getCasteMembers(\'collapse1'+i+'\');getTotalCandidateDetailsForCaste('+locationId+',\''+locationType+'\','+stateId+',\''+searchType+'\','+result[i].id+',\'casteStatesDiv'+i+'\',\''+locationName+'\',\''+isMuncipality+'\',\'votersDetails\','+greaterId+');" style="cursor: pointer">';
				
				if(result[i].casteName != null && result[i].casteName.length>0)
					str+='<h3 style="display:inline-block;margin:0px;">'+result[i].casteName+'</h3>';
				else
					str+='<h3 style="display:inline-block;margin:0px;"> Others </h3>';
				
				str+='<h5 style="display:inline-block;margin-left:50px;" class="badge pull-right"> Total : '+result[i].totalCount+'</h5>';
				str+='</a>';
				str+='</h4>';
				str+='</div>';
					str+='<div id="collapse1'+i+'" class="collapse panel-collapse" aria-labelledby="heading1'+i+'"  style="width: 1120px;">';
				str+='<div class="panel-body detailsCls"  id="casteStatesDiv'+i+'">';				
				str+='</div>';
				str+='</div>';
				str+='</div>';
					
			}
			
			str+='</div>';
			str+='</div>';
			str+='</div>';
		}
		
		if(isDataAvailable)
		{			
			$('#'+divId+'').html(str);			
		}
		else{
			$('#'+divId+'').html(' <div style="align-text:center;font-weight:bold;">No Data Available ...</div>');
		}
		
		$('#'+divId+'').addClass('animated  fadeInRight');

		$('#'+divId+'').after('<div id="'+divId+''+divCount+'"></div>');
		
		dinamicDiv = ''+divId+''+divCount+'';
		
		var mapping = { };
		var casteSource = new Array();
					for(var i = 0; i < casteArr.length; ++i) {
						casteSource.push(casteArr[i].label);
						mapping[casteArr[i].label] = casteArr[i].value;
					}	
					
		$('#searchCasteDivId').autocomplete({
						minLength: 1,
						source: casteSource,
						select: function(event, ui) {
							var candiCasteName = mapping[ui.item.value];	
							for(var i = 0; i < casteArr.length; ++i) {
								if(casteArr[i].value == candiCasteName)
								{
									var casteName = casteArr[i].label;
									$('html, body').animate({scrollTop:$('#'+casteName+'DivId').offset().top}, 'slow');
									$('#searchCasteDivId').val('');
								}
							}
						}
					});
			$('#positoinDivId').show();		
									
	}
	
	function getCasteMembers(collapseDivId)
	{
		$(".casteTableCls").html('');
		if ($('#'+collapseDivId+'').hasClass('in'))
		{
			$('#'+collapseDivId+'').removeClass('in');
		}
		else{
			$('.collapse').removeClass('in');
			$('#'+collapseDivId+'').addClass('in');
		}
		
	}
	
	function modifySearchDiv(searcvyhDivId)
	{
		$(".casteTableCls").html('');
		if(searcvyhDivId == 'hideModifiSearchId')
		{
			$('#hideModifiSearchId').hide();
			$('#showModifiSearchId').show();
			$('#modifySearchId').hide();
		}
		else if(searcvyhDivId == 'showModifiSearchId')
		{
			$('#hideModifiSearchId').show();
			$('#showModifiSearchId').hide();
			$('#modifySearchId').show();
		}	
	}

	function getTotalCandidateDetailsForCaste(locationId,locationType,stateId,searchType,casteStateId,divId,locationName,isMuncipality,isFinalStr,greaterId)
	{		
		$('.detailsCls').html('');
		$('#'+divId+'').html('<img id="ajaxImg" src="./images/Loading-data.gif" alt="Processing Image"  class="offset6" style="height: 80px;">');		
		var searchName = $('#enteredText').val();
		var actualLocationType = locationType;
		if(locationType != 'panchayat' && locationType !='state' && locationType !='constituency')
		{
			if(isMuncipality != 0)
			{
				locationType ="ward";
			}				
			else if(greaterId == 0)
			{
				locationType ="constituency";
			}
		}
		console.log("locationType 8888 :"+locationType);
		
		if(greaterId == 1 || greaterId == 3)
		{
			locationType ="constituency";
		}
		if(greaterId == 2)
		{
			locationType ="ward";
		}
		console.log("greaterId 9999 :"+greaterId);
		console.log("actualLocationType 9999 :"+actualLocationType);
		if(searchType == 'VOTER')
		{
			if(actualLocationType == 'LOCAL_ELECTION_BODY')
			{
				if(greaterId == 3)
				{
					locationType ="LOCAL_ELECTION_BODY";
				}
				else{
					locationType ="ward";
				}
			}
			else if(locationName.indexOf('muncipality') != -1 || locationName.indexOf('corporation') != -1)
			{
				locationType = 'LOCAL_ELECTION_BODY';
			}
			else if(actualLocationType == 'ward')
			{
				locationType ="ward";
			}				
		}
		
			console.log("locationType 9999 :"+locationType);
		var jObj = {
			searchType:searchType,			
			stateId:stateId,
			locationId:locationId,
			locationType:locationType,
			casteStateId:casteStateId,
			searchName:searchName,
			isFinal:isFinalStr
		}	
		
		$.ajax({
          type:'GET',
          url: 'getCadreVoterDetailsBySearchAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			$('#'+divId+'').html('');
			if(result != null)
			{
				buildCasteWiseCandidateDetails(result,divId);
			}
			else{
				$('#'+divId+'').html(' <div style="align-text:center;font-weight:bold;">No Data Available ...</div>');
			}
		});
		
	}
	
	function buildCasteWiseCandidateDetails(result,divId)
	{
		$(".casteTableCls").html('');
		
		var str='';
		str+='<table class="table table-custom casteTableCls" >';
		str+='<thead>';
		str+='<tr>';
				str+='<th width="">  </td>';
				str+='<th width=""> Candidate Name </th>';
				str+='<th width=""> Relative Name </th>';
				str+='<th width=""> Mobile No </th>';
				str+='<th width=""> Constituency </th>';
				str+='<th width=""> Mandal/Muncipality</th>';
				str+='<th width=""> Panchayat </th>';
		str+='</tr>	';	
		str+='</thead>';
		str+='<tbody>';
		
		var votersList = result[0].cadreSearchList;
		var imageUrL='images/cadre_images';
		var isVoter = false;
		if(votersList == null || votersList.length == 0)
		{
			isVoter = true;
			votersList = result[0].voterSearchList;			
		}
		
		if(votersList != null && votersList.length>0)
		{
			for(var k in votersList)
			{
				var myresult = votersList[k].voterSearchList;
				
				if(myresult != null &&  myresult.length>0)
				{
					for(var j in myresult)
					{
						str+='<tr>';
						if(!isVoter)
							str+='<td width="5%"><img class="profile-border" src="'+imageUrL+'/'+myresult[j].imageURL+'" alt="" style="width:40px;height:50px;"></img></td>';
						else{
							str+='<td width="5%"><img class="profile-border" src="'+myresult[j].imageURL+'" alt="" style="width:40px;height:50px;"></img></td>';
						}
						
						str+='<td width="">'+myresult[j].cadreName+'</td>';
						str+='<td width="">'+myresult[j].relativeName+'</td>';
						if(myresult[j].mobileNo != null)
							str+='<td width="">'+myresult[j].mobileNo+'</td>';
						else
							str+='<td width=""> - </td>';
						str+='<td width="">'+myresult[j].constituency+'</td>';
						str+='<td width="">'+myresult[j].tehsil+'</td>';
						str+='<td width="">'+myresult[j].panchayat+'</td>';
						str+='</tr>	';	
					}
				}
			}
		}
		
		str+='</tbody>';
		str+='</table>';
		$('#'+divId+'').html(str);
		$(".casteTableCls").dataTable({
			"iDisplayLength": 20,
			"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		});
		$('html, body').animate({scrollTop:$('.casteTableCls').offset().top}, 'slow');
	}
	
	function scrollToDiv()
	{
		$('html, body').animate({scrollTop:$('#showModifiSearchId').offset().top}, 'slow');		
	}
	
	getNameSelectedDetails();
	
	
	</script>
</body>
</html>
