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
	<title>Cadre Search</title>
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
	<link href="js/cadreSearch/PageTransitions/css/animations.css" rel="stylesheet" type="text/css">
	<link href="js/cadreSearch/PageTransitions/css/component.css" rel="stylesheet" type="text/css"> 
<style>
.nl-field ul li{line-height:55px!important;}
.nl-field a:hover{text-decoration:none!important;}
.nl-ti-input input[type="text"]{height:57px! important; width:324px! important;}
#contenttable{min-height:450px;background:#e5e5e5 !important;}
.get-details h4{
	margin-top:10px;
}
.get-details a{
	color:#333;
}
</style>
</head>
	<body class="search-body-bg">			
        <div class="container">
        <div class="well search-heading m_top10"><h2 class="text-center search-head">SEARCH A CADRE/VOTER</h2></div>
        </div>
		<div id="pt-main" class="pt-perspective">
        <div class="pt-page pt-page-1 container">
            <div class="well search-content">
                <div class="main clearfix text-center">
                     <form id="nl-form" class="nl-form">
                        I WANT TO SEARCH
                        <select class="searchCls">
                            <option value="0" selected >ANY</option>
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
                    </form>
                    <button class="btn btn-success get-details m_top10" onclick="searchResults('searchDetailsDiv');">
						<i class="glyphicon glyphicon-arrow-right"></i>&nbsp;&nbsp;Get Details
                    </button>
					<div class="nl-overlay"></div>
                </div>              
			</div>
		</div>
		<div class="pt-page pt-page-2 container">    
			<div>
				<ol class="breadcrumb search-breadcrumb">
					  <span class="text-breadcrumb text-capitalize">your searching:</span>
					  <li><a href="cadreVoterSearchAction.action" class="text-breadcrumb">Cadre</a></li>
					  <li><a href="#" class="text-breadcrumb">Andhra Pradesh</a></li>
					  <li><a href="#" class="text-breadcrumb">Nellore</a></li>
					  <li class="active">Kavali</li>
				</ol>
				<div>
					<div class="constituency-box  get-details" id="searchDetailsDiv">
						<h4 class="district-box-name"></h4>
						<span class="pull-right"></span>
					</div>						
					
				</div>
			</div>
		</div>
            
		<div class="pt-page pt-page-3 container">
			<div>
				<ol class="breadcrumb search-breadcrumb">
				  <span class="text-breadcrumb text-capitalize">your searching:</span>
				  <li><a href="cadreVoterSearchAction.action" class="text-breadcrumb">Cadre</a></li>
				  <li><a href="#" class="text-breadcrumb">Andhra Pradesh</a></li>
				  <li class="active">District</li>
				</ol>
				<div>
					<div class="constituency-box  get-details">
						<h4 class="district-box-name">constituency 1</h4>
						<span class="pull-right">500</span>
					</div>			
				</div>
			</div>
		</div>
		<div class="pt-page pt-page-4 container">
			<div>
			<ol class="breadcrumb search-breadcrumb">
			  <span class="text-breadcrumb text-capitalize">your searching:</span>
			  <li><a href="cadreVoterSearchAction.action" class="text-breadcrumb">Cadre</a></li>
			  <li><a href="#" class="text-breadcrumb">Andhra Pradesh</a></li>
			  <li class="active">District</li>
			</ol>
			<div>
				<div class="constituency-box  get-details">
					<h4 class="district-box-name">tehsil 1</h4>
					<span class="pull-right">500</span>
				</div>			
			</div>
			</div>
		</div>
		<div class="pt-page pt-page-5 container">
			<div>
				<ol class="breadcrumb search-breadcrumb">
				  <span class="text-breadcrumb text-capitalize">your searching:</span>
				  <li><a href="cadreVoterSearchAction.action" class="text-breadcrumb">Cadre</a></li>
				  <li><a href="#" class="text-breadcrumb">Andhra Pradesh</a></li>
				  <li class="active">District</li>
				</ol>
				<div>
					<div class="constituency-box  get-details">
						<h4 class="district-box-name">panchayat 1</h4>
						<span class="pull-right">500</span>
					</div>			
				</div>
			</div>
		</div>
		<div class="pt-page pt-page-6 container">
			<div class="search-results">
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
		  <div class="panel panel-default search-panel">
			<div class="panel-heading search-panel-heading" role="tab" id="headingOne">
			  <h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
				  <h3 style="display:inline-block;margin:0px;">CASTE NAME_1</h3>
				  <h5 style="display:inline-block;margin:0px;"> [MEMBERS 200]</h5>
				  <span><i class="glyphicon glyphicon-chevron-up pull-right" style="color:#E5E5E5"></i></span>
				  <span><i class="glyphicon glyphicon-search pull-right" style="color:#E5E5E5"></i></span>
				</a>
			  </h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
			  <div class="panel-body">
					<table class="table table-custom">
						<tr>
							<td width="5%"><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td width="15%"><h4>Name Of the member</h4></td>
							<td width="15%"><h4>Guardian Name</h4></td>
							<td width="15%"><h4>+91 9632580147</h4></td>
						</tr>						
					</table>
			  </div>
			</div>
		  </div>
		  <div class="panel panel-default search-panel">
			<div class="panel-heading search-panel-heading" role="tab" id="headingTwo">
			  <h4 class="panel-title">
				<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
				 <h3 style="display:inline-block;margin:0px;">CASTE NAME_2</h3>
				  <h5 style="display:inline-block;margin:0px;"> [MEMBERS 2100]</h5>
				  <span><i class="glyphicon glyphicon-chevron-up pull-right" style="color:#E5E5E5"></i></span>
				  <span><i class="glyphicon glyphicon-search pull-right" style="color:#E5E5E5"></i></span>
				</a>
			  </h4>
			</div>
			<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
			  <div class="panel-body">
			   <table class="table table-custom">
						<tr>
							<td width="5%"><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td width="15%"><h4>Name Of the member</h4></td>
							<td width="15%"><h4>Guardian Name</h4></td>
							<td width="15%"><h4>+91 9632580147</h4></td>
						</tr>
						
					</table>
			  </div>
			</div>
		  </div>
		  <div class="panel panel-default search-panel">
			<div class="panel-heading search-panel-heading" role="tab" id="headingThree">
			  <h4 class="panel-title">
				<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
				  <h3 style="display:inline-block;margin:0px;">CASTE NAME_3</h3>
				  <h5 style="display:inline-block;margin:0px;"> [MEMBERS 500]</h5>
				  <span><i class="glyphicon glyphicon-chevron-up pull-right" style="color:#E5E5E5"></i></span>
				  <span><i class="glyphicon glyphicon-search pull-right" style="color:#E5E5E5"></i></span>
				</a>
			  </h4>
			</div>
			<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
			  <div class="panel-body">
				<table class="table table-custom">
						<tr>
							<td width="5%"><img class="profile-border" src="js/cadreSearch/images/profile.jpg" alt=""></img></td>
							<td width="15%"><h4>Name Of the member</h4></td>
							<td width="15%"><h4>Guardian Name</h4></td>
							<td width="15%"><h4>+91 9632580147</h4></td>
						</tr>
						
					</table>
			  </div>
			</div>
		  </div>	
		</div>
		</div></div>
	</div>   
	
	<!--<script src="js/cadreSearch/js/jquery-1.11.2.min.js" type="text/javascript"></script>-->
	<script src="js/cadreSearch/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/cadreSearch/Naturallanguage/js/modernizr.custom.js"></script>
    <script src="js/cadreSearch/Naturallanguage/js/nlform.js"></script>
    <script src="js/cadreSearch/PageTransitions/js/modernizr.custom.js" type="text/javascript"></script>
    <script src="js/cadreSearch/PageTransitions/js/jquery.dlmenu.js" type="text/javascript"></script>
    <script src="js/cadreSearch/PageTransitions/js/pagetransitions.js" type="text/javascript"></script>

	<script>
		var btnFlag = false ;
		var btnFlag1 = false ;
	var selectionArr = ["searchCls","stateCls","districtCls","constiCls","casteCls","nameCls"];
	var selectionArr1 = ["searchDivCls","stateDivCls","districtDivCls","constiDivCls","casteDivCls","nameDivCls"];
		var selectionArr2 = ["searchDivClsHidden","stateDivClsHidden","districtDivClsHidden","constiDivClsHidden","casteDivClsHidden","nameDivClsHidden"];
	var	casteSel = 0;
	$(document).ready(function(){
		var casteArr = new Array();
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
		getDistrictsAndConstis("District",locationValue);					
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
			}
		});
		
		//1111
		
		$('#stateId').click(function(){
			
			console.log(123);
		});
		
	});
	
	
	
	</script>
	<script>
	var districtArr;
	var constiArr;
	var divCount = 2;
	function getDistrictsAndConstis(type,locationValue){

	
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
				if(type == 'District'){
					districtArr = new Array();
					//$('.districtCls').remove();
					for(var i in result){
						var obj = {
						value :  result[i].id,
						label :  result[i].name
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
						value :  result[i].id,
						label :  result[i].name
						}			
						constiArr.push(obj);
					}
					
					var str='';
					str+='<a class="nl-field-toggle">ANY</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="constiCls" onkeyup="constiKeyUp();" /><button type="button" class="nl-field-go" onClick="getSelectedData1();">Go</button>';
					$(".constiDivCls").html(str);
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
		console.log(districtSel);
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
		var str ='<a class="nl-field-toggle">ANY</a><ul><li class="nl-ti-input"><span role="status" aria-live="polite" class="ui-helper-hidden-accessible"></span><input type="text" placeholder="ANY" class="constiCls" onkeyup="constiKeyUp();" /><button type="button" id="gobtn" class="nl-field-go" onClick="getSelectedData1();">ANY</button>';
		}
		$(".constiDivCls").html(str);
		
		
	}
	
	
	new NLForm(document.getElementById( 'nl-form' ));
	
	
	
	function searchResults(divId)
	{
		var searchType = $(".searchDivCls a").text();
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
			locationId = districtSel;
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
			if(result != null)
			{
				buildSearchResults(result,searchType,locationType,nextLocationType,stateId,casteStateId,divId);
			}
			else{
				//$('#searchDetailsDiv').html('No Data Available...');
					$('#searchDetailsDiv').html(' <button class="btn btn-success get-details m_top10" onclick="searchResults();">						<i class="glyphicon glyphicon-arrow-right"></i>&nbsp;&nbsp;Get Details</button>');
			}
		});
		
	}
	
	
	
	function getDetailsForSelection(locationId,locationType,stateId,searchType,casteStateId,isFinalValue,getDetailsAreaType)
	{		
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
		//$('#searchDetailsDiv').html('');
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
					buildSearchResults(result,searchType,locationType,nextLocationType,stateId,casteStateId);
				}
				
			}
			else{
				$('#searchDetailsDiv').html(' <button class="btn btn-success get-details m_top10" onclick="searchResults();">						<i class="glyphicon glyphicon-arrow-right"></i>&nbsp;&nbsp;Get Details</button>');
			}
		});
		
	}
	
	function buildSearchCandidateDetails(result)
	{
		var str='';
		str+='<div class="panel panel-default search-panel">';
		str+='<div class="panel-heading search-panel-heading" role="tab" id="headingOne">';
		str+='<h4 class="panel-title">';
		str+='<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">';
		str+='<h3 style="display:inline-block;margin:5px;">'+result[0].casteName+'</h3>';
		str+='<h5 style="display:inline-block;margin:10px;">  Total : '+result.length+'</h5>';
		str+='<span><i class="glyphicon glyphicon-chevron-up pull-right" style="color:#E5E5E5"></i></span>';
		str+='<span><i class="glyphicon glyphicon-search pull-right" style="color:#E5E5E5"></i></span>';
		str+='</a>';
		str+='</h4>';
		str+='</div>';
		str+='<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">';
		str+='<div class="panel-body">';
		str+='<table class="table table-custom">';
		for(var i in result)
		{
			str+='<tr>';
			str+='<td width="5%"><img class="profile-border" src="http://www.mytdp.com/images/cadre_images/'+result[i].imageURL+'" alt="" style="width: 40px;height: 50px"></img></td>';
			str+='<td width="15%"><h4>'+result[i].cadreName+'</h4></td>';
			str+='<td width="15%"><h4>'+result[i].relativeName+'</h4></td>';
			str+='<td width="15%"><h4>'+result[i].mobileNo+'</h4></td>';
			str+='<td width="15%"><h4>'+result[i].panchayat+'</h4></td>';
			str+='<td width="15%"><h4>'+result[i].tehsil+' </h4></td>';
			str+='</tr>';	
		}	
		str+='</table>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		
			$('#searchDetailsDiv').html(str);
	}
	
	
	function buildSearchResults(myResult,searchType,locationType,nextLocationType,stateId,casteStateId,divId)
	{
		var result = myResult[0].cadreSearchList;
		divCount = parseInt(divCount)+parseInt(1);
		
		var str ='';
		
			if(result.length>0)
			{
				for(var i in result)
				{
					/*str+='<div class="district-box get-details">';
					str+='<a href="javascript:{getDetailsForSelection('+result[i].constituencyId+',\''+nextLocationType+'\','+stateId+',\''+searchType+'\','+casteStateId+',0,\''+locationType+'\',\''+divId+''+divCount+'\');}" class="district-box-name get-details">';	
					str+='<h4 class="" style="display:inline-block;"> &nbsp;&nbsp;'+result[i].constituency+'</h4></a>';
					str+='<span class="pull-right" style="margin-top:8px;"><a href="javascript:{getDetailsForSelection('+result[i].constituencyId+',\''+nextLocationType+'\','+stateId+',\''+searchType+'\','+casteStateId+',1,\''+locationType+'\',\''+divId+''+divCount+'\');}"  class=" district-box get-details"> &nbsp;'+result[i].totalCount+' &nbsp;</a></span>';
					str+='</div>';	*/

					str+='<div class="constituency-box get-details">';
					str+='<h4 class="district-box-name"> &nbsp;&nbsp;'+result[i].constituency+'</h4>';
					str+='<span class="pull-right" style="margin-top:8px;">&nbsp;'+result[i].totalCount+' &nbsp;</span>';
					str+='</div>';							
				}				
			}
			$('#'+divId+'').after(str);
			

	}
	
	</script>
</body>
</html>