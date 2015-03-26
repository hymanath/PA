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
	<link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	
	
	<link rel="stylesheet" type="text/css" href="js/cadreSearch/Naturallanguage/css/component.css" />
	<link href="js/cadreSearch/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="js/cadreSearch/css/custom.css" rel="stylesheet" type="text/css">
	<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css' />
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	
	  <link rel="stylesheet" type="text/css" href="css/cadreRegistrationCSS/animate.css" >
	<!--
	<link href="js/cadreSearch/PageTransitions/css/animations.css" rel="stylesheet" type="text/css">
	<link href="js/cadreSearch/PageTransitions/css/component.css" rel="stylesheet" type="text/css"> 
	-->
<style>
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
    background-color: #E2E4FF;
}

.dataTables_filter input,.dataTables_length select{
	height:25px !important;
}

table.dataTable tr.odd{background-color:#eee;}
table.dataTable tr.odd td.sorting_1 {background-color: #eee;}

/*--------*/
.styled-select {
   background: url(http://i62.tinypic.com/15xvbd5.png) no-repeat 96% 0;
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

.rounded {
   -webkit-border-radius: 20px;
   -moz-border-radius: 20px;
   border-radius: 20px;
}

.black { background-color: #E5E5E5; border:1px solid #333}
.black select   { color: #333; }
.nl-overlay {
    margin-left: 1%;
    width: 98%;
background: none repeat scroll 0 0 !important;
}


table.dataTable tr.odd td.sorting_1 {background-color: #ccc;}
table.dataTable tr.even td.sorting_1 {background-color: #ccc;}
.styled-select
{
	margin-left:5px;
}
</style>
</head>
	<body class="search-body-bg">			
        <div class="container">
        <div class="well search-heading m_top10"><h2 class="text-center search-head">SEARCH A CADRE/VOTER</h2></div>
        
		<div id="pt-main" class="pt-perspective" style="margin-left:-15px;">
        <div class="pt-page pt-page-1 container " style="left:-11px;">
		<a class="btn pull-right" style="z-index: 999;display:none;" id="hideModifiSearchId" href="javascript:{modifySearchDiv('hideModifiSearchId');}"> Close Search </a>
		<a class="btn pull-right" style="z-index: 999;display:none;" id="showModifiSearchId" href="javascript:{modifySearchDiv('showModifiSearchId');}"> Modify Search </a>
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
					<span class="badge"> YOUR SEARCHING: </span>
					<ul class="list-inline modifySearchBreadcrumb" style="margin-bottom: 0px;margin-left: 10px">					
					<li> Cadre / Voter :  </li>
					<li  class="stateId1"> State: </li>
					<li  class="districtId1"  style="display:none;"> District : </li>
					<li  class="constiId1"  style="display:none;"> Constituency : </li>
					<li  class="tehsilId1"  style="display:none;"> Mandal / Muncipality : </li>
					<li> Caste : </li>
					</ul>
				<ol class="breadcrumb search-breadcrumb">				
					<div id="searchDiv"></div>
				</ol>
				<div>
					<input type="hidden" id="enteredText" value=""/>
					<div id="searchDetailsDiv" class="earchDetailsDiv">						
					</div>						
					<div style="" class="col-md-3 col-md-offset-5">
						<img id="ajaxImageIdAPmandalconstiRoleSummary" src="./images/Loading-data.gif" alt="Processing Image" style="display:none;">
					</div>
				</div>
			</div>
		</div>
		
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
	var selectionArr = ["searchCls","stateCls","districtCls","constiCls","casteCls","nameCls"];
	var selectionArr1 = ["searchDivCls","stateDivCls","districtDivCls","constiDivCls","casteDivCls","nameDivCls"];
	var selectionArr2 = ["searchDivClsHidden","stateDivClsHidden","districtDivClsHidden","constiDivClsHidden","casteDivClsHidden","nameDivClsHidden"];
	
	var	casteSel = 0;
	var casteArr ;
	
	$(document).ready(function(){

		getDistrictsAndConstis("district",0)
		$('.fadeInRight').addClass('animated  fadeInRight ');
		casteArr = new Array();
		var casteDetailsArr = new Array();
		<c:forEach var="caste" items="${casteDetails}">
				var obj = {
					value :  '${caste.id}',
					label :  '${caste.name}'
				}
				casteDetailsArr.push( '${caste.name}');
				casteArr.push(obj);
		</c:forEach>
	var source  = [ ];
	var mapping = { };
	for(var i = 0; i < casteArr.length; ++i) {
		source.push(casteArr[i].label);
		mapping[casteArr[i].label] = casteArr[i].value;
	}	
	$('.casteCls').autocomplete({
		minLength: 1,
		source: source,
		select: function(event, ui) {
			casteSel  = mapping[ui.item.value];
		}

	});

	$('.stateCls').click(function(){				
		var locationValue = $(this).attr('key');		
		getDistrictsAndConstis("district",locationValue);					
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
		});
		
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
					for(var i in result){
						var obj = {
						value :  result[i].locationId,
						label :  result[i].locationName
						}			
						districtArr.push(obj);
					}
					
					var str='';
					str+='<a class="nl-field-toggle">ANY</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="districtCls" onkeyup="districtsKeyUp();" /><button type="button" class="nl-field-go" onClick="getSelectedData();">Go</button>';
					$(".districtDivCls").html(str);
				}
				else if(type == 'Constituency'){
					constiArr = new Array();
					//$('.districtCls').remove();
					for(var i in result){
						var obj = {
						value :  result[i].locationId,
						label :  result[i].locationName
						}			
						constiArr.push(obj);
					}
					
					var str='';
					str+='<a class="nl-field-toggle">ANY</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="constiCls" onkeyup="constiKeyUp();" /><button type="button" class="nl-field-go" onClick="getSelectedData1();">Go</button>';
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
							//$(".districtCls").attr('disabled', 'disabled');
							districtSel = mapping[ui.item.value];
							
							for(var i = 0; i < districtArr.length; ++i) {
								if(districtArr[i].value == districtSel)
								{
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
	
	function getSelectedData(){
	
		btnFlag = true;
		$('.districtDivCls').removeClass('nl-field-open');
		$('.districtDivCls').open = false;		
		var validVal = $(".districtCls").val();
		if(!validVal == ""){	
		var str ='<a class="nl-field-toggle">'+validVal+'</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="districtCls" onkeyup="districtsKeyUp();" /><button type="button" id="gobtn" class="nl-field-go" onClick="getSelectedData();">'+validVal+'</button>';
		}else{
		var str ='<a class="nl-field-toggle">ANY</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="districtCls" onkeyup="districtsKeyUp();" /><button type="button" id="gobtn" class="nl-field-go" onClick="getSelectedData();">ANY</button>';
		
		}
		
		$(".districtDivCls").html(str);
		//console.log(districtSel);
		//console.log($('#districtHiddenVal').text());
		getDistrictsAndConstis("Constituency",districtSel);
		
	}
	
	function getSelectedData1(){

		btnFlag1 = true;
		$('.constiDivCls').removeClass('nl-field-open');
		$('.constiDivCls').open = false;		
		var validVal = $(".constiCls").val();
		if(!validVal == ""){
		var str ='<a class="nl-field-toggle">'+validVal+'</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="constiCls" onkeyup="constiKeyUp();" /><button type="button" id="gobtn" class="nl-field-go" onClick="getSelectedData1();">'+validVal+'</button>';
		}
		else{
		var str ='<a class="nl-field-toggle">ANY</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="constiCls" onkeyup="constiKeyUp(0);" /><button type="button" id="gobtn" class="nl-field-go" onClick="getSelectedData1();">ANY</button>';
		}
		$(".constiDivCls").html(str);
		
		
	}
	new NLForm(document.getElementById( 'nl-form' ));
	function searchResults(divId)
	{ 
		if(dinamicDiv.length>0)
		{
			divId = dinamicDiv ;
		}
		var searchType = $(".searchDivCls a").text();
		$('#ajaxImageIdAPmandalconstiRoleSummary').show();
		$('#modifySearchId').hide();
		$('#hideModifiSearchId').hide();
		$('#showModifiSearchId').hide();
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
			//console.log(result);
			$('#ajaxImageIdAPmandalconstiRoleSummary').hide();
			buildSearchDetails(searchType,locationType,stateId,casteStateId,locationId,districtSel,constiSel,0,divId);
			if(result != null)
			{
				buildSearchResults(result,searchType,locationType,nextLocationType,stateId,casteStateId,divId);
				//$('#showModifiSearchId').show();
			}
			else{
				//$('#searchDetailsDiv').html('No Data Available...');
					$('#'+divId+'').html(' <div style="align-text:center;font-weight:bold;">No Data Available ...</div>');
			}
		});
		
	}
	
	function buildSearchDetails(searchType,locationType,stateId,casteStateId,locationId,districtId,constiId,tehsilId,divId){
		var str1 ='';  
		console.log(88888);
		str1+='<li><div class="styled-select black rounded" ><select id="searchId1" onchange="getCasteDetailsForSelection(0,\'\',\''+divId+'\',\''+locationType+'\')">';
		if(searchType == "ANY"){
        str1+='<option value="0" selected >ANY</option> <option value="1" >CADRE</option><option value="2">VOTER</option>';
       
		}
		else if(searchType == "CADRE"){
		 str1+='<option value="0">ANY</option> <option value="1" selected>CADRE</option><option value="2">VOTER</option>';
     
		}
		else if(searchType == "VOTER"){
		 str1+='<option value="0">ANY</option> <option value="1" >CADRE</option><option value="2" selected>VOTER</option>';
        	
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
			else if(locationType == 'tehsil'){
		
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
			else if(locationType == 'panchayat'){
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
				$("#casteId1").prepend('<option value="0" selected>Any</option>');
				}else{
				$("#casteId1").prepend('<option value="0">Any</option>');
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
        str1+='<option value="0" selected >ANY</option> <option value="1" >CADRE</option><option value="2">VOTER</option>';
       
		}
		else if(searchType == "CADRE"){
		 str1+='<option value="0">ANY</option> <option value="1" selected>CADRE</option><option value="2">VOTER</option>';
     
		}
		else if(searchType == "VOTER"){
		 str1+='<option value="0">ANY</option> <option value="1" >CADRE</option><option value="2" selected>VOTER</option>';
        	
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
				if(typeof mainConstiArr !== 'undefined' && mainConstiArr.length > 0 ){
				for(var i = 0; i < mainConstiArr.length; ++i) {				
					if(locationId == mainConstiArr[i].value){
						 constiOptions+='<option value="'+mainConstiArr[i].value+'" selected>'+mainConstiArr[i].label+'</option>';	
					}
					else{
						constiOptions+='<option value="'+mainConstiArr[i].value+'" >'+mainConstiArr[i].label+'</option>';			
					}
				}
				}else{
				for(var i = 0; i < constiArr.length; ++i) {				
					if(constiId == constiArr[i].value){
						 constiOptions+='<option value="'+constiArr[i].value+'" selected>'+constiArr[i].label+'</option>';	
					}
					else{
						constiOptions+='<option value="'+constiArr[i].value+'" >'+constiArr[i].label+'</option>';			
					}
				}
				}
				$("#constiId1").append(constiOptions);
		}
		else if(locationType == 'panchayat'){
			
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
					for(var i = 0; i < constiArr.length; ++i) {				
						if(constiId == constiArr[i].value){
							 constiOptions+='<option value="'+constiArr[i].value+'" selected>'+constiArr[i].label+'</option>';	
						}
						else{
							constiOptions+='<option value="'+constiArr[i].value+'" >'+constiArr[i].label+'</option>';			
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
				$("#casteId1").prepend('<option value="0" selected>Any</option>');
				}else{
				$("#casteId1").prepend('<option value="0">Any</option>');
				}
				$("#casteId1").append(casteOptions);				
				$("#casteId1").append(casteOptions);
			
	
	}
	
	
	function getCadreVoterDetailsForSelection1(locId,locationType,isFinalValue,getDetailsAreaType,divId)
	{	
		$('#'+divId+'').html('');
		var locationId = $("#"+locId).val();
		var searchType = $("#searchId1 option:selected").text();
		var stateId =  $("#stateId1 option:selected").val();
		var casteStateId =$("#casteId1 option:selected").val();
		
		buildSearchDetailsSecondLevel(locationId,locationType,divId);
		var searchName = "";
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
		}
		else if(locationType == 'constituency')
		{
			nextLocationType ="tehsil";
		}
		else if(locationType == 'tehsil')
		{
			nextLocationType ="panchayat";
		}
		$('#searchDetailsDiv').html('');
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
			//console.log(result);
			if(result != null)
			{
				if(isFinalValue != 0)
				{
					buildSearchCandidateDetails(result);
				}
				else{
				
					buildSearchResults(result,searchType,locationType,nextLocationType,stateId,casteStateId,dinamicDiv);
				}
				
			}
			else{
				$('#'+dinamicDiv+'').html(' <div style="align-text:center;font-weight:bold;">No Data Available ...</div>');
			}
		});
		
	}
	
	function getDetailsForSelection(locationId,locationType,stateId,searchType,casteStateId,isFinalValue,getDetailsAreaType,divId,isMuncipality,presentDiv)
	{		
		$('#'+presentDiv+'').html('');
	
		$('#ajaxImageIdAPmandalconstiRoleSummary').show();
		var searchName = $('#enteredText').val();
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
			console.log(11111);
			if(result != null)
			{
				if(isFinalValue != 0)
				{
					buildSearchCandidateDetails(result,divId); 
				}
				else{
					buildSearchDetailsSecondLevel(locationId,locationType,divId);
					buildSearchResults(result,searchType,locationType,nextLocationType,stateId,casteStateId,divId);
				}
				
			}
			else{
				buildSearchDetailsSecondLevel(locationId,locationType,divId);
				$('#'+divId+'').html(' <div style="align-text:center;font-weight:bold;">No Data Available ...</div>');
			}
		});
		
	}

	function buildSearchResults(myResult,searchType,locationType,nextLocationType,stateId,casteStateId,divId)
	{
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
		
		console.log("divId :"+divId);
		var areaType ='';
		/*if(locationType =='state')
		{
			areaType = 'District';
		}else
		{
			areaType = locationType;
		}*/
			if(result.length>0)
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
						str+='<h5 class="" style="display:inline-block;"> &nbsp;&nbsp;'+result[i].constituency+'  <span style="margin-left:8px;"> '+areaType+'</span></h5>';
					}
					else{
						str+='<a href="javascript:{getDetailsForSelection('+result[i].constituencyId+',\''+nextLocationType+'\','+stateId+',\''+searchType+'\','+casteStateId+',0,\''+locationType+'\',\''+divId+''+divCount+'\','+isMuncipality+',\''+divId+'\');}" class="district-box-name get-details" title="Click here to get Sub Level Details">';	
						str+='<h5 class="" style="display:inline-block;"> &nbsp;&nbsp;'+result[i].constituency+'  <span style="margin-left:8px;"> '+areaType+'</span></h5></a>';
					}
					
					str+='<span class="pull-right" style="margin-top:8px;"><a href="javascript:{getDetailsForSelection('+result[i].constituencyId+',\''+nextLocationType+'\','+stateId+',\''+searchType+'\','+casteStateId+',1,\''+locationType+'\',\''+divId+''+divCount+'\','+isMuncipality+',\''+divId+'\');}"  class="badge badge-success" title="Click here to get Cadre Details"  style="color: #fff; margin-top: 5px;"> &nbsp;'+result[i].totalCount+' &nbsp;</a></span>';
					str+='</div>';	
						if(locationType == "constituency")
						{
	
						var obj = {
						value :  result[i].constituencyId,
						label :  result[i].constituency
						}			
						mainConstiArr.push(obj);
						}	
						else if(locationType == "tehsil"){
						var obj1 = {
						value1 :  result[i].constituencyId,
						label1 :  result[i].constituency
						}			
						mainTehsilArr.push(obj1);
						}
				}				
			}
		
			var result = myResult[0].voterSearchList;
			if(result.length>0)
			{
				str+='<br><br>';
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
						str+='<h5 class="" style="display:inline-block;"> &nbsp;&nbsp;'+result[i].constituency+'  <span style="margin-left:8px;"> '+areaType+'</span></h5>';
					}
					else{
						str+='<a href="javascript:{getDetailsForSelection('+result[i].constituencyId+',\''+nextLocationType+'\','+stateId+',\''+searchType+'\','+casteStateId+',0,\''+locationType+'\',\''+divId+''+divCount+'\','+isMuncipality+',\''+divId+'\');}" class="district-box-name get-details" title="Click here to get Sub Level Details">';	
						str+='<h5 class="" style="display:inline-block;"> &nbsp;&nbsp;'+result[i].constituency+'   <span style="margin-left:8px;"> '+areaType+'</span></h5></a>';
					}
					
					str+='<span class="pull-right" style="margin-top:8px;"><a href="javascript:{getDetailsForSelection('+result[i].constituencyId+',\''+nextLocationType+'\','+stateId+',\''+searchType+'\','+casteStateId+',1,\''+locationType+'\',\''+divId+''+divCount+'\','+isMuncipality+',\''+divId+'\');}"  class="badge badge-success" title="Click here to get Voter Details"  style="color:#fff; margin-top: 5px;"> &nbsp;'+result[i].totalCount+' &nbsp;</a></span>';
					str+='</div>';							
				}				
			}
			
			$('#'+divId+'').html(str);
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
		
		//buildSearchDetailsSecondLevel(locationId,locationType,divId);
		var searchName = "";
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
		}
		else if(locationType == 'tehsil')
		{
			nextLocationType ="panchayat";
			locationId = $("#constiId1 option:selected").val();
		}
		else if(locationType == 'panchayat')
		{
			nextLocationType ="";
			locationId = $("#tehsilId1 option:selected").val();
		}
		$('#searchDetailsDiv').html('');
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
			//console.log(result);
			if(result != null)
			{
				if(isFinalValue != 0)
				{
					buildSearchCandidateDetails(result);
				}
				else{
				
					buildSearchResults(result,searchType,locationType,nextLocationType,stateId,casteStateId,dinamicDiv);
				}
				
			}
			else{
				$('#'+dinamicDiv+'').html(' <div style="align-text:center;font-weight:bold;">No Data Available ...</div>');
			}
		});
	}
	
	
	function buildSearchCandidateDetails(myresult,divId)
	{
		var result = myresult[0].cadreSearchList;
		divCount = parseInt(divCount)+1;
		if(divCount > 2)
		{
			divCount = 2;
		}
			
		var str='';
		str+='<div class="pt-page pt-page-6 container" id="casteDiv" style="margin-top:25px;">';		
		str+='<div class="search-results">';
		str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
		
		if(result != null && result.length>0)
		{
			for(var i in result)
			{
				//
				str+='<div class="panel panel-default search-panel">';
				str+='<div class="panel-heading search-panel-heading" role="tab" id="heading'+i+'">';
				str+='<h4 class="panel-title">';
				/*if(i == 0)
					str+='<a class="collapsed" data-toggle="collapse" href="#collapse'+i+'" aria-expanded="false" data-parent="#accordion" aria-controls="collapse'+i+'" onclick="getCasteMembers(\'collapse'+i+'\')">';
				else*/
					str+='<a class="collapsed" aria-expanded="false" data-parent="#accordion" aria-controls="collapse'+i+'" onclick="getCasteMembers(\'collapse'+i+'\')" style="cursor: pointer">';
				
				str+='<h3 style="display:inline-block;margin:0px;">'+result[i].casteName+'</h3>';
				str+='<h5 style="display:inline-block;margin-left:50px;" class="badge pull-right"> Total : '+result[i].voterSearchList.length+'</h5>';
				//str+='<span><i class="glyphicon glyphicon-chevron-up pull-right" style="color:#E5E5E5"></i></span>';
				//str+='<span><i class="glyphicon glyphicon-search pull-right" style="color:#E5E5E5"></i></span>';
				str+='</a>';
				str+='</h4>';
				str+='</div>';
				/*if(i == 0)
					str+='<div id="collapse'+i+'" class="collapse panel-collapse in" aria-labelledby="heading'+i+'">';
				else*/
					str+='<div id="collapse'+i+'" class="collapse panel-collapse" aria-labelledby="heading'+i+'" style="width: 1120px;">';
				str+='<div class="panel-body">';
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
				
				var myresult = result[i].voterSearchList;
				if(myresult.length>0)
				{
					for(var j in myresult)
					{
						str+='<tr>';
						str+='<td width="5%"><img class="profile-border" src="http://www.mytdp.com/images/cadre_images/'+myresult[j].imageURL+'" alt="" style="width:40px;height:50px;"></img></td>';
						str+='<td width="">'+myresult[j].cadreName+'</td>';
						str+='<td width="">'+myresult[j].relativeName+'</td>';
						str+='<td width="">'+myresult[j].mobileNo+'</td>';
						str+='<td width="">'+myresult[j].constituency+'</td>';
						str+='<td width="">'+myresult[j].tehsil+'</td>';
						str+='<td width="">'+myresult[j].panchayat+'</td>';
						str+='</tr>	';	
					}
				}
				
				str+='</table>';
				str+='</div>';
				str+='</div>';
				str+='</div>';
					
			}
		}
		str+='</div>';
		str+='</div>';
		str+='</div>';
		
		
		
		var result = myresult[0].voterSearchList
		str+='<div class="pt-page pt-page-6 container" id="casteDiv" style="margin-top:25px;margin-left: -15px;margin-bottom:15px;">';		
		str+='<div class="search-results">';
		str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
		
		if(result != null && result.length>0)
		{
			for(var i in result)
			{
				
				str+='<div class="panel panel-default search-panel">';
				str+='<div class="panel-heading search-panel-heading" role="tab" id="heading1'+i+'">';
				str+='<h4 class="panel-title">';
				/*if(i == 0)
					str+='<a class="collapsed" data-toggle="collapse" href="#collapse'+i+'" aria-expanded="false" data-parent="#accordion" aria-controls="collapse'+i+'" onclick="getCasteMembers(\'collapse1'+i+'\')">';
				else*/
					str+='<a class="collapsed" aria-expanded="false" data-parent="#accordion" aria-controls="collapse'+i+'" onclick="getCasteMembers(\'collapse1'+i+'\')" style="cursor: pointer">';
				
				str+='<h3 style="display:inline-block;margin:0px;">'+result[i].casteName+'</h3>';
				str+='<h5 style="display:inline-block;margin-left:50px;" class="badge pull-right"> Total : '+result[i].voterSearchList.length+'</h5>';
				//str+='<span><i class="glyphicon glyphicon-chevron-up pull-right" style="color:#E5E5E5"></i></span>';
				//str+='<span><i class="glyphicon glyphicon-search pull-right" style="color:#E5E5E5"></i></span>';
				str+='</a>';
				str+='</h4>';
				str+='</div>';
				/*if(i == 0)
					str+='<div id="collapse1'+i+'" class="collapse panel-collapse in" aria-labelledby="heading1'+i+'">';
				else*/
					str+='<div id="collapse1'+i+'" class="collapse panel-collapse" aria-labelledby="heading1'+i+'"  style="width: 1120px;">';
				str+='<div class="panel-body">';
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
				var myresult = result[i].voterSearchList;
				if(myresult.length>0)
				{
					for(var j in myresult)
					{
						str+='<tr>';
						str+='<td width="5%"><img class="profile-border" src="http://www.mytdp.com/images/cadre_images/'+myresult[j].imageURL+'" alt="" style="width:40px;height:50px;"></img></td>';
						str+='<td width="">'+myresult[j].cadreName+'</td>';
						str+='<td width="">'+myresult[j].relativeName+'</td>';
						str+='<td width="">'+myresult[j].mobileNo+'</td>';
						str+='<td width="">'+myresult[j].constituency+'</td>';
						str+='<td width="">'+myresult[j].tehsil+'</td>';
						str+='<td width=""><'+myresult[j].panchayat+'</td>';
						str+='</tr>	';	
					}
				}
				
				str+='</table>';
				str+='</div>';
				str+='</div>';
				str+='</div>';
					
			}
		}
		str+='</div>';
		str+='</div>';
		str+='</div>';
		
		$('#'+divId+'').html(str);
		$('#'+divId+'').addClass('animated  fadeInRight');
		//$('.casteTableCls').dataTable({});
		$(".casteTableCls").dataTable({
			"iDisplayLength": -1,
			"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		});
		
		$('#'+divId+'').after('<div id="'+divId+''+divCount+'"></div>');
		
		dinamicDiv = ''+divId+''+divCount+'';
	}
	
	function getCasteMembers(collapseDivId)
	{
		//console.log($('#'+collapseDivId+'').hasClass('in'));
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

	</script>
</body>
</html>