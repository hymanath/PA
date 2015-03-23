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
                    <button class="btn btn-success get-details m_top10" onclick="searchResults();">
						<i class="glyphicon glyphicon-arrow-right"></i>&nbsp;&nbsp;Get Details
                    </button>
					<div class="nl-overlay"></div>
                </div>              
			</div>
		</div>
		<div class="pt-page pt-page-3 container">  
		<div id="searchDetailsDiv">
        </div></div>
            
		<div class="pt-page pt-page-4 container">
		<div>
		<ol class="breadcrumb search-breadcrumb">
		  <span class="text-breadcrumb text-capitalize">your searching:</span>
		  <li><a href="#" class="text-breadcrumb">Cadre</a></li>
		  <li><a href="#" class="text-breadcrumb">Andhra Pradesh</a></li>
		  <li class="active">District</li>
		</ol>
		<div>			
			<div class="district-box  get-details">
				<h4 class="district-box-name">WEST GODAVARI</h4>
				<span class="pull-right">3201</span>
			</div>
		</div>
		</div></div>
		<div class="pt-page pt-page-2 container">
			<div class="search-results">
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
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
		</div>
		</div>
	</div>   
	
	<!--<script src="js/cadreSearch/js/jquery-1.11.2.min.js" type="text/javascript"></script>-->
	<script src="js/cadreSearch/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/cadreSearch/Naturallanguage/js/modernizr.custom.js"></script>
    <script src="js/cadreSearch/Naturallanguage/js/nlform.js"></script>
    <script src="js/cadreSearch/PageTransitions/js/modernizr.custom.js" type="text/javascript"></script>
    <script src="js/cadreSearch/PageTransitions/js/jquery.dlmenu.js" type="text/javascript"></script>
    <script src="js/cadreSearch/PageTransitions/js/pagetransitions.js" type="text/javascript"></script>
	
	<script>
		<c:forEach var="caste" items="${casteDetails}">
				var obj = {
					value :  '${caste.id}',
					label :  '${caste.name}'
				}
				casteDetailsArr.push( '${caste.name}');
				casteArr.push(obj);
		</c:forEach>
		
	</script>
	
	<script>
		var btnFlag = false ;
		var btnFlag1 = false ;
	var selectionArr = ["searchCls","stateCls","districtCls","constiCls","casteCls","nameCls"];
	var selectionArr1 = ["searchDivCls","stateDivCls","districtDivCls","constiDivCls","casteDivCls","nameDivCls"];
		var selectionArr2 = ["searchDivClsHidden","stateDivClsHidden","districtDivClsHidden","constiDivClsHidden","casteDivClsHidden","nameDivClsHidden"];
	$(document).ready(function(){
		var casteArr = new Array();
		var casteDetailsArr = new Array();
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
			//$('.tags_id').text(mapping[ui.item.value]);
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
							//$('.tags_id').text(mapping[ui.item.value]);
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
	
	
	
	function searchResults()
	{
		var searchType = "Cadre";
		var stateId = "2";
		var locationType = "state";
		var locationId = "0";
		var casteStateId = "143";
		var searchName = "";
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
				buildSearchResults(result,searchType,locationType,nextLocationType,stateId,casteStateId);
			}
			else{
				//$('#searchDetailsDiv').html('No Data Available...');
					$('#searchDetailsDiv').html(' <button class="btn btn-success get-details m_top10" onclick="searchResults();">						<i class="glyphicon glyphicon-arrow-right"></i>&nbsp;&nbsp;Get Details</button>');
			}
		});
		
	}
	
	function buildSearchResults(result,searchType,locationType,nextLocationType,stateId,casteStateId)
	{
		var str ='';
		str+='<ol class="breadcrumb search-breadcrumb">';
        str+='          <span class="text-breadcrumb text-capitalize">Your Searching:</span>';
        str+='          <li><a href="#" class="text-breadcrumb">'+searchType+'</a></li>';
         str+='         <li><a href="#" class="text-breadcrumb">Andhra Pradesh</a></li>';
         //str+='         <li><a href="#" class="text-breadcrumb">Nellore</a></li>';
        str+='  <li class="active">'+locationType+'</li>';
         str+='   </ol>';
			
			if(result.length>0)
			{
				for(var i in result)
				{
					str+='<div class="constituency-box get-details '+locationType+'Id" >';
					str+='<a href="javascript:{getDetailsForSelection('+result[i].constituencyId+',\''+nextLocationType+'\','+stateId+',\''+searchType+'\','+casteStateId+',0,\''+locationType+'\');}"><h4 class="constituency-box-name get-details">'+result[i].constituency+'</h4></a>';
					str+='<span class="pull-right"><a href="javascript:{getDetailsForSelection('+result[i].constituencyId+',\''+nextLocationType+'\','+stateId+',\''+searchType+'\','+casteStateId+',1,\''+locationType+'\');}">'+result[i].totalCount+'</a></span>';
					str+='</div>';
					
				}
			}

		$('#searchDetailsDiv').html(str);

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
		
	}
	</script>
</body>
</html>