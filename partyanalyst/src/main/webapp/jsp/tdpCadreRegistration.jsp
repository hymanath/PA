<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tdp Cadre Registration</title>

    <link href="css/bootstrap.min.css" rel="stylesheet"/>	
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/animate.css" rel="stylesheet"/>	
	<link href="styles/icheck_skins/all.css?v=1.0.2" rel="stylesheet"/>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <script src="js/icheck/icheck.js"></script>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	 	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
		<script src="js/icheck/icheck.js"></script>
	 
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

	<!-- YUI Dependency files (End) -->
	
	
    <script>$('#yourElement').addClass('animated fadeInDown');</script>
    <script>$('#fadeInLeft').addClass('animated fadeInLeft');</script>
    <script>$('#fadeInRight').addClass('animated fadeInRight');</script>
    <script>$('#fadeInUp').addClass('animated fadeInUp');</script>
    <script>$('#fadeInUp1').addClass('animated fadeInUp');</script>


	<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	 .bgc{background-color:#3598db !important;}
	 
	 .datePickerCls{
			 cursor: text !important;
			}
	.header-bg{background:#3598DB url('./images/cadre_images/2014-Header-BG.png') repeat-x; height:179px;}
	.color-white{color:#f9f9f9;}
	
	.levelCls{width:160px;float:left;margin:5px;}
	.levelClsDt{width:140px;float:left;}
	select.levelCls{border-radius:4px;}
	
	</style>
	
		
	<script>
		var constituencyArray = new Array();
			<c:forEach var="constituency" items="${constituencyesList}">
				var constituencys ={
				id:"${constituency.id}",
				name:"${constituency.name}"
				}
				constituencyArray.push(constituencys);
			</c:forEach>
		var electionArray = new Array();	
			<c:forEach var="election" items="${eletionTypesList}">
				var elections ={
				id:"${election.id}",
				name:"${election.name}"
				}
				electionArray.push(elections);
			</c:forEach>

		var casteArr = new Array();
		var casteDetailsArr = new Array();
		<c:forEach var="caste" items="${voterInfoVOList[0].genericVOList}">
				var obj = {
					id :  '${caste.id}',
					name :  '${caste.name}'
				}
				casteDetailsArr.push( '${caste.name}');
				casteArr.push(obj);
		</c:forEach>

		
		var occupationArr = new Array();
		var occupationDetailsArr = new Array();
		<c:forEach var="occupation" items="${selectOptionVOList}">
				var obj = {
					id :  '${occupation.id}',
					name :  '${occupation.name}'
				}
				occupationDetailsArr.push( '${occupation.name}');
				occupationArr.push(obj);
		</c:forEach>
	var participationCount = 0;
		<s:if test="%{voterInfoVOList[0].previousParticipationInfoList != null && voterInfoVOList[0].previousParticipationInfoList.size() > 0}">
					<c:forEach var="role" items="${voterInfoVOList[0].previousParticipationInfoList}" varStatus="indexValue">
						participationCount = participationCount + 1;
					</c:forEach>
		</s:if>

	var isRolesCount = 0 ;
		
		<s:if test="%{voterInfoVOList[0].previousParticipationInfoList != null && voterInfoVOList[0].previousParticipationInfoList.size() > 0}">
			<c:forEach var="role" items="${voterInfoVOList[0].previousParticipationInfoList}" varStatus="indexValue">
				isRolesCount = isRolesCount+1;
			</c:forEach>
		</s:if>	
	</script>
	
   	<script>
	
	var constituencyId = '${constiteucnyId}';
	var panchayatId    = '${constiteucnyId}';
	var boothId 	   = '${boothId}';
	$(document).ready(function(){
	    $('.datePickerCls').datepicker({
		dateFormat: 'dd-mm-yy',
		maxDate: new Date(),
		changeMonth: true,
		changeYear: true,
		yearRange: "-100:+0"
	  });
		
		$( "#casteIdValue" ).autocomplete({ 
			source:casteDetailsArr,
			select: function (event, ui) {
				$('#casteIdValue').val(ui.item.value);	
				for(var i in casteArr)
				{
					if(casteArr[i].name == ui.item.value)
					{
						$('#casteId').val(casteArr[i].id);
						break;
					}
				}
				return false;
			}
		});
		
		$( "#occupationValue" ).autocomplete({ 
		source:occupationDetailsArr,
		select: function (event, ui) {
				$('#occupationValue').val(ui.item.value);	
				for(var i in occupationArr)
				{
					if(occupationArr[i].name == ui.item.value)
					{
						$('#occupationId').val(occupationArr[i].id);
						break;
					}
				}
				return false;
			}
			
		});
		
		$( ".familyOccupationsCls" ).autocomplete({ 
		source:occupationDetailsArr,
		select: function (event, ui) {
				$(this ).val(ui.item.value);
				
				for(var i in occupationArr)
				{
					if(occupationArr[i].name == ui.item.value)
					{
						var fieldId = $(this).attr('id');
						$('#'+fieldId+'0').val(occupationArr[i].id);
						break;
					}
				}
					return false;
			}
		});
		
		
		//prepopulateOptions();
		//prepopulateElctionOptions();
		/*$('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
			increaseArea: '20%' // optional
		  });*/
		 
		 $('#voterRelationId').val(parseInt(${voterInfoVOList[0].voterRelationId}));
	
	});

	var rolesSize = 1;
	var isRolesSet = true;
	
	function enableSearchByName(){
		$('#preEnrollNoValue').removeAttr('readOnly');
	}
	function enableLookupName(){
		$('#cardNumber').removeAttr('readOnly');
	}
	
	function createNewForm(){
	
		if(participationCount >= 1 &&  isRolesSet)
		{
			rolesSize = participationCount;
			isRolesSet = false;
		}
		console.log(cadreCmmittLvlArr);
		var str = '';
		str += '<div class="rolesList'+rolesSize+'" style="margin-top:0px;float:left;margin-left:-5px;">';
		str += '<div class="levelCls">';
		str += '<div class=" text-align1 " >';
		//str += '<h5 class="text-align1">Select Level </h5>';
		str += '<select class="form-control border-radius-0 levelCls" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].cadreCommitteeLevelId">';
		str += '<option value = "0"> Select </option>';
			if(cadreCmmittLvlArr != null && cadreCmmittLvlArr.length>0)
			{
				for(var i in cadreCmmittLvlArr)
				{
					str += '<option value = "'+cadreCmmittLvlArr[i].id+'">'+cadreCmmittLvlArr[i].name+'</option>';
				}
			}
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="levelCls">';
		str += '<div class=" text-align1" >';
		//str += '<h5 class="text-align1">Party Designation </h5>';
		str += '<select class="form-control border-radius-0 levelCls" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].cadreCommitteeId">';
		str += '<option value = "0"> Select </option>';
			if(cadreCmmittArr != null && cadreCmmittArr.length>0)
			{
				for(var i in cadreCmmittArr)
				{
					str += '<option value = "'+cadreCmmittArr[i].id+'">'+cadreCmmittArr[i].name+'</option>';
				}
			}
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="levelCls">';
		str += '<div class=" text-align1" >';
		//str += '<h5 class="text-align1">Party Designation </h5>';
		str += '<select class="form-control border-radius-0 levelCls" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].cadreRoleId">';
		str += '<option value = "0"> Select </option>';
			if(cadreRolesArr != null && cadreRolesArr.length>0)
			{
				for(var i in cadreRolesArr)
				{
					str += '<option value = "'+cadreRolesArr[i].id+'">'+cadreRolesArr[i].name+'</option>';
				}
			}
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="levelCls">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">From Date</h5>';
		str += '<div class="input-prepend text-align2 ">';
		str += '<input type="text" style="width:120px;float:left;margin-left:6px;margin-top:5px;" class="form-control span2 border-radius-0 border-right-0 datePickerCls" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].fromDateStr"  readOnly="true"></input></span>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		str += '<div class="levelCls ">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">To Date</h5>';
		str += '<div class="input-prepend  ">';
		str += '<input type="text" style="width:120px;float:left;margin-left:6px;margin-top:5px;" class="form-control span2  border-radius-0 border-right-0 datePickerCls" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].toDateStr"  readOnly="true"></input></span>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		/*str += '<div class="span2">';
		str += '<a onClick="deleteRollesForm(\'rolesList'+rolesSize+'\')" class="btn btn-success"></a>';
		str += '<div>';*/
		str += '<div class="" style="width:60px;float:left;margin-top:-10px;">';
		str += '<div class=" " >';
		str += '<div class="input-prepend text-align2 ">';	
		str += '<a class="icon-minus-sign" style="float:left;margin-top:30px;" onClick="deleteRollesForm(\'rolesList'+rolesSize+'\')" title="Remove Details"></a>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		
		rolesSize++;
		//alert(rolesSize);
		
		$('#rollesDiv').append(str);
		//$('.datePickerCls').datepicker({dateFormat: 'dd-mm-yy',minDate: '01-01-1900',maxDate: new Date()});
		
		 $('.datePickerCls').datepicker({
		dateFormat: 'dd-mm-yy',
		maxDate: new Date(),
		changeMonth: true,
		changeYear: true,
		yearRange: "-100:+0"
	  });
	  
	}
	
	function deleteRollesForm(id)
	{
		if(confirm('Are you sure want to delete it? '))
		{
			$('.'+id).remove();
		}		
	}
	
	function submitCadreForm()
	{
	
		if(!isNumber()){
		
			return false;
		}
		var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					console.log(uploadResult);
					showUploadStatus(uploadResult);	
				}
			};

		YAHOO.util.Connect.setForm('uploadCadreForm',true);
		YAHOO.util.Connect.asyncRequest('POST','tdpCadreSaveRegistrationAction.action',uploadHandler);
	}
	
	function showUploadStatus(myResult)
	{
		$('#mainDiv').html('');
		var result = (String)(myResult);
		var errorDivEle = document.getElementById('errorMsgDiv');
		var str = '';
		var resultArr = result.split(',');
		if(result.search('SUCCESS') != -1)
		{
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<p class="text-align">Thank You For Your Registration</p>';
			str+= '<h3 class="text-align">Successfully Registration Completed</h3>';
			str+= '</div>';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<p class="text-align">Your Enrollment No :'+resultArr[1]+' </p>';
			str+= '</div>';
			str+= '</div>';
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<a href="tdpCadreSearchAction.action" class="btn btn-success  offset5 border-radius-0"  >Continue  <span class="glyphicon glyphicon-chevron-right"></span></a>';
			str+= '</div>';
			str+= '</div>';
		}
		else if(result.search('FAILURE') != -1)
		{
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<h3 class="text-align">Error raised while cadre registration</h3>';
			str+= '</div>';
			str+= '</div>';
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<a href="tdpCadreSearchAction.action" class="btn btn-success  offset5 border-radius-0"  >Continue  <span class="glyphicon glyphicon-chevron-right"></span></a>';
			str+= '</div>';
			str+= '</div>';
		}
		/*else
		{
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<p class="text-align">Thank You For Your Registration</p>';
			str+= '<h3 class="text-align">Data Not Found For your Voter Id</h3>';
			str+= '</div>';
			str+= '</div>';
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<a href="tdpCadreSearchAction.action" class="btn btn-success  offset5 border-radius-0"  >Continue  <span class="glyphicon glyphicon-chevron-right"></span></a>';
			str+= '</div>';
			str+= '</div>';
			
		}*/
		$('#statusDiv').html(str);
	}
	var cadreLevelArr = [];
	var partyDesignationArr = [];
	
	function openSearchForm()
	{
		window.location.assign('tdpCadreSearchAction.action')
	}
	/* function prepopulateOptions()
	{
		var jsObj = 
			   {				
				  task:"getConstituncyWiseDetails"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getOptionDetailsForCadreAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
						$('#cadreLevelId').append('<option value="0"> Select Level </option>');
							$('#partyDesignationId').append('<option value="0"> Select Designation </option>');
						if(result != null && result.length >0)
						{
							if(result[0].selectOptionsList != null && result[0].selectOptionsList.length >0)
							{
								for(var i in result[0].selectOptionsList)
								{
									$('#cadreLevelId').append('<option value="'+result[0].selectOptionsList[i].id+'">'+result[0].selectOptionsList[i].name+'</option>');
									var cadreObj = 
									{
										id		:	result[0].selectOptionsList[i].id,
										name	:	result[0].selectOptionsList[i].name								
									}
									
									cadreLevelArr.push(cadreObj);
									
								}
							
							}
							
							if(result[0].selectOptionsList1 != null && result[0].selectOptionsList1.length >0)
							{
							
								for(var i in result[0].selectOptionsList1)
								{
									$('#partyDesignationId').append('<option value="'+result[0].selectOptionsList1[i].id+'">'+result[0].selectOptionsList1[i].name+'</option>');
									
									var cadreObj = 
									{
										id		:	result[0].selectOptionsList1[i].id,
										name	:	result[0].selectOptionsList1[i].name								
									}
									
									partyDesignationArr.push(cadreObj);
									
								}							
							}	
						}
				});
	} */
	
	function prepopulateOptions(){
		var jsObj ={}				   
			$.ajax({
				type : "POST",
				url : "getCadreLevelsForCadreSearchAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result.length>0){
					buildSelectBoxes(result);
				}
			});
	}
	
	var cadreCmmittArr = [];
	var cadreCmmittLvlArr = [];
	var cadreRolesArr = [];
	
	function buildSelectBoxes(results){
		
		for(var i in results){
		
			if(results[i].name=="CadreCommitteeList"){
				var str = "";
					str+="<option value = '0'> Select </option>";
					for(var j in results[i].selectOptionsList){
						str+="<option value="+results[i].selectOptionsList[j].id+">"+results[i].selectOptionsList[j].name+"</option>";
						
						var cadreCmmtObj = {
								id		:	results[i].selectOptionsList[j].id,
								name	:	results[i].selectOptionsList[j].name								
							}
							
						cadreCmmittArr.push(cadreCmmtObj);
					}
					
				$("#CadreCommitteeId").html(str);
			}
			if(results[i].name=="CadreCommitteeLevelsList"){
				var str = "";
					str+="<option value = '0'> Select </option>";
					for(var j in results[i].selectOptionsList){
						str+="<option value="+results[i].selectOptionsList[j].id+">"+results[i].selectOptionsList[j].name+"</option>";
						
						var cadreCmmtObj = {
								id		:	results[i].selectOptionsList[j].id,
								name	:	results[i].selectOptionsList[j].name								
							}
							
						cadreCmmittLvlArr.push(cadreCmmtObj);
					}
					
				$("#CadreCommitteeLevelsId").html(str);
			}
			if(results[i].name=="CadreRolesList"){
				var str = "";
					str+="<option value = '0'> Select </option>";
					for(var j in results[i].selectOptionsList){
						str+="<option value="+results[i].selectOptionsList[j].id+">"+results[i].selectOptionsList[j].name+"</option>";
						
						var cadreCmmtObj = {
								id		:	results[i].selectOptionsList[j].id,
								name	:	results[i].selectOptionsList[j].name								
							}
							
						cadreRolesArr.push(cadreCmmtObj);
					}
					
				$("#CadreRolesId").html(str);
			}
			
		}
	}
	
	function prepopulateElctionOptions()
	{
		var jsObj = 
			   {				
				  task:"getConstituncyWiseDetails"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getElectionOptionDetailsForCadreAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
						$('#electionTypeId').append('<option value="0"> Select Election </option>');
							$('#electionYearId').append('<option value="0"> Select Year </option>');
						if(result != null && result.length >0)
						{
							if(result[0].selectOptionsList != null && result[0].selectOptionsList.length >0)
							{
								for(var i in result[0].selectOptionsList)
								{
									$('#electionTypeId').append('<option value="'+result[0].selectOptionsList[i].id+'">'+result[0].selectOptionsList[i].name+'</option>');		
								}
							
							}
							
							if(result[0].selectOptionsList1 != null && result[0].selectOptionsList1.length >0)
							{
								for(var i in result[0].selectOptionsList1)
								{
									$('#electionYearId').append('<option value="'+result[0].selectOptionsList1[i].id+'">'+result[0].selectOptionsList1[i].name+'</option>');														
								}
							
							}
							
						}
				});
	}
	
	function changeImg()
	{
		var photoElmt = document.getElementById("uploadFileId");
		var file = photoElmt.files[0];
		var reader = new FileReader();
		reader.onloadend = handleReaderLoadEnd;
		reader.readAsDataURL(file);
	}
	function handleReaderLoadEnd(evt)
	{
		var img = document.getElementById("actuploadImg");
		img.src = evt.target.result;
		evt=null;
	} 
	var eletionCont = 1;
	var isRolesCountSet = true;
	function createNewFormForElections()
	{
		if(isRolesCount >=1 && isRolesCountSet)
		{
			isRolesCountSet = false;
			eletionCont = isRolesCount;
		}
		var str = '';
		str += '<div class="row-fluid electionsList'+eletionCont+'">';
		str += '<div class="span3">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">Election Type</h5>';
		str += '<select id="electionTypeId'+eletionCont+'" style="margin-left: 12px;" onChange="getElectionYears(this.value,\'electionYears'+eletionCont+'\')">';
		if(electionArray != null && electionArray.length>0)
		{
			str += '<option value = "0">Select Election Year</option>';
			for(var i in electionArray)
			{
				str += '<option value = "'+electionArray[i].id+'">'+electionArray[i].name+'</option>';
			}
		}
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span3">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">Year</h5>';
		str += '<select  id="electionYears'+eletionCont+'" name="cadreRegistrationVO.previousParicaptedElectionsList['+eletionCont+'].electionTypeId" >	';	
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span3">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">Constituency</h5>';
		str += '<select class="" name="cadreRegistrationVO.previousParicaptedElectionsList['+eletionCont+'].constituencyId" style="margin-left: 12px;">';
		str += '<option value = "0"> Select Constituency </option>';
			if(constituencyArray != null && constituencyArray.length>0)
			{
				for(var i in constituencyArray)
				{
					str += '<option value = "'+constituencyArray[i].id+'">'+constituencyArray[i].name+'</option>';
				}
			}
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span2">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">Add More</h5>';
		str += '<a class="icon-minus-sign" style="float:left;" onClick="deleteRollesForm(\'electionsList'+eletionCont+'\')" title="Remove Details"></a>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		
		$('#electionsDiv').append(str);
		eletionCont ++;
	}
	
	function getElectionYears(id,divId)
	{
		var jsObj = 
			   {			
				  eletionTypeId : id,
				  task          : "getConstituncyWiseDetails"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getElectionYearsByElectionType.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
						$('#'+divId+'').html('');
						$('#'+divId+'').append('<option value="0"> Select Election </option>');
						if(result != null && result.length >0)
						{
							for(var i in result)
							{
								$('#'+divId+'').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');		
							}
						}
				});
	}

	var familyArr =new Array();
	
	<c:forEach var="votersInfo" items="${voterInfoVOList[0].voterInfoVOList}" >
		familyArr.push(${votersInfo.voterId});
	</c:forEach>
		
	var voterCount = familyArr.length - 1;

	function addMoreVoters()
	{
	if(voterCount >= 0 )
		voterCount = voterCount+1;
	else
		voterCount = 1;
		
	var str ='';

	str +=' <tr class="voterDev'+voterCount+'">';
	str +=' <td style="width:100px;">  <input id="countVar" type="hidden" value="'+voterCount+'">';
	str +=' <input type="text" class="form-control border-radius-0 text-align2" placeholder=" Enter Voter Name " name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].voterName" ></input> ';
	str +=' <input type="hidden" class="form-control border-radius-0 text-align2"  name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].voterId"></input> ';
	str +=' </td>';
	str +=' <td style="width:100px;"> ';
	str +=' <input type="text" class="form-control border-radius-0 text-align2" placeholder=" Enter Voter Id " name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].voterCadreNO" style="width:121px;"></input>';
	str +=' </td>';
	str +=' <td style="width:80px;"> ';
	str +=' <input type="text" class="form-control border-radius-0 text-align2" placeholder=" Age " style="width:55px;"></input> ';
	str +=' </td>';
	str +=' <td style="width:82px;">';
	str +=' <input type="text" class="form-control border-radius-0 text-align2" placeholder="Gender"  style="width:53px;"> </input>';
	str +=' </td>';
	str +=' <td style="width:100px;"> ';

	str +=' <select name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].educationId" style="width:160px;">';
	str +=' <option value="0">Select Education</option>';
	
	<c:forEach var="educationList" items="${genericVOList}" >
		str +=' <option value="${educationList.id}">${educationList.name}</option>';
	</c:forEach>
	str +=' </select>';

	str +=' </td>';
	str +=' <td style="width:100px;"> ';

	str +='<input type="text" id="familyOccupation'+voterCount+'" class="familyOccupationsCls form-control border-radius-0 text-align2" placeholder=" Enter Occupation "  style="width:151px;"  value=""> </input>';												 
	str +='<input type="hidden"  id="familyOccupation'+voterCount+'0" name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].occupationId" class="form-control border-radius-0 text-align2" placeholder="Enter Occupation " style="width:50px;" value=""> </input>';

	str +=' </select>';
	str +=' </td>';
	str +='<td style="width:15px;"> ';
		str +='<a class="icon-minus-sign" style="float:right;margin-top:-10px;" onClick="deleteRollesForm(\'voterDev'+voterCount+'\');" title="Remove Details"></a>';
	str +='</td>';
	str +=' </tr>';

	
	$('#addVotersDiv').after(str);
	
	$( ".familyOccupationsCls" ).autocomplete({ 
		source:occupationDetailsArr,
		select: function (event, ui) {
				$(this ).val(ui.item.value);
				
				for(var i in occupationArr)
				{
					if(occupationArr[i].name == ui.item.value)
					{
						var fieldId = $(this).attr('id');
						$('#'+fieldId+'0').val(occupationArr[i].id);
						break;
					}
				}
					return false;
			}
		});
		
	
	}
	
	
	function isNumber()
	{
		var numberFlag = true;
		var mobileNumber = $('#mobileNumberId').val().trim();
		$('#mobileErr').html('');
		if (mobileNumber.length == 0) 
		{
			$('#mobileErr').html('Please Enter Mobile No');		
			numberFlag= false;
		}		 
		else if (isNaN(mobileNumber) || mobileNumber.length != 10) 
		{
			$('#mobileErr').html('Invalid Mobile No.');			
			numberFlag = false;
		}
	
			return numberFlag;
	}
	
	function getConstiteuncyListForElection(eletionId,constiListId)
	{
			$('#'+constiListId+'').find('option').remove();
			$('#'+constiListId+'').append('<option value="0"> Select Constituency </option>');
			
			var jsObj = 
			   {			
				  electionId : eletionId,
				  constituencyId : constituencyId,
				  task       : "getConstiteuncyListForElection"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getConstiteuncyListForElectionAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){

					
					if(result != null)
					{
						for(var i in result)
						{
							$('#'+constiListId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						}
					}					
				});
	}
	
	function getCandidateDetailsForElection(constituencyId,candidateId,electionId,electionTypeId)
	{
		var electionValue = $('#'+electionId+'').val();
		var electionTypeId = $('#'+electionTypeId+'').val();
		$('#'+candidateId+'').find('option').remove();
		$('#'+candidateId+'').append('<option value="0"> Select Candidate </option>');
		
			var jsObj = 
			   {	
				  electionTypeId : electionTypeId,
				  electionId : electionValue,
				  constituencyId : constituencyId,
				  task         : "getCandidateDetailsForElection"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getCandidateDetailsForElectionAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					if(result != null)
					{
						for(var i in result)
						{
							$('#'+candidateId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						}
					}		
					
				});
	}
	
	</script>

</head>
  <body class="bgc">
		<!-- Header Row -->
		<div class="row-fluid">
			<div class="span12 header-bg text-center">
				<img src="images/cadre_images/2014-cadre-Registration-Logo.png" />
			</div>
		</div><!-- Header Row End-->
	<div class="container m_top10 " id="yourElement">
		<div class="span12 show-grid" style="position: relative;">
			<h3 class="text-align">CADRE REGISTRATION</h3>
		</div>
	</div>
	<div id="mainDiv">
		<div>
			<form action="tdpCadreSaveRegistrationAction.action" method="POST" enctype="multipart/form-data" name="uploadCadreForm">	
		<div class="container m_top10"style="position: relative;">
		
			<div class="span12" >
				<div class="row-fluid">
					<div class="span6   show-grid"  id="fadeInLeft">
					<input type="hidden" class="form-control border-radius-0 text-align2" value="${voterInfoVOList[0].voterId}" > 
					
					<input type="hidden" class="form-control border-radius-0 text-align2" value="${constiteucnyId}" name="cadreRegistrationVO.constituencyId"> 
					
					<input type="hidden" class="form-control border-radius-0 text-align2" value="${panchayatId}" name="cadreRegistrationVO.panchayatId"> 
					
					<input type="hidden" class="form-control border-radius-0 text-align2" value = "${boothId}" name="cadreRegistrationVO.boothId" > 
										<div class="span12">
										<div class="span6">
											<h5 style="color: #9a9a9a;">  CANDIDATE NAME </h5>
											<input type="text" class="form-control border-radius-0" placeholder="Candidate Name" name="cadreRegistrationVO.voterName"  value="${voterInfoVOList[0].name}"></input>
										</div>	
										<div class="span4">	
											<h5 class="text-align1">Age</h5>
											<input style="width:180px;" type="text" class="form-control border-radius-0 text-align2"  placeholder="Age" name="cadreRegistrationVO.age"   value="${voterInfoVOList[0].age}"></input>
										</div>
										</div>
									<div class="row-fluid">
										<div class="span7">
											<h5 class="text-align1">DATE OF BIRTH</h5>
												
												<div class="input-prepend text-align2 ">
													
													<input type="text" class="datePickerCls" name="cadreRegistrationVO.dobStr" value="${voterInfoVOList[0].dateOfBirth}" placeholder="Date of Birth" readOnly="true"></input>
													</div>
													
													<h5 class="text-align1">GENDER</h5>	 
												<div class="row-fluid form-inline" style="margin-left:5px;">
												<s:if test="%{voterInfoVOList[0].gender != null}">
												
													<c:if test="${voterInfoVOList[0].gender == 'M' || voterInfoVOList[0].gender == 'Male'}">
													   <label class="radio"><input type="radio" value="MALE"  name="cadreRegistrationVO.gender" checked="true" > MALE</input></label>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="radio"><input type="radio" value="FEMALE"  name="cadreRegistrationVO.gender"> FEMALE</input></label>
													</c:if>
													<c:if test="${voterInfoVOList[0].gender == 'F' || voterInfoVOList[0].gender == 'Female'}">												
														<label class="radio"><input type="radio" value="MALE"  name="cadreRegistrationVO.gender" > MALE</input></label>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="radio"><input type="radio" value="FEMALE"  name="cadreRegistrationVO.gender"  checked="true"> FEMALE</input></label>
													</c:if>
												
												</s:if>
												<s:else>
												  <label class="radio"><input type="radio" value="MALE"  name="cadreRegistrationVO.gender" > MALE</input></label>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<label class="radio"><input type="radio" value="FEMALE"  name="cadreRegistrationVO.gender" > FEMALE</input></label>
												</s:else>
														
												</div>			
										</div>
								
								
										<div class="span4  m_top10">
											<div class="well  pad-5">
												<!--<img src="user.jpg" class="img-responsive" />-->
												<span id="uploadImg"><img  style="width: 140px; height: 120px;" id="actuploadImg" src="images/mahaNadu/user image.jpg"></span>
												<input type="file" style="width: 79px;margin-left: 10px;" id="uploadFileId" onchange="changeImg();" name="cadreRegistrationVO.uploadImage" class="m_top10">
												<!--<button class="btn btn-primary btn-xs btn-block border-radius-0 m_top10 " type="button" >Upload Photo </button>-->
											</div>
										</div>
								
									</div>
										
										
								<div class="m_top10">
									<div class="row-fluid">
										
										<div class="span6">
										<h5 class="text-align1">GUARDIAN NAME</h5>
										<input type="text" class="form-control border-radius-0 text-align2" placeholder="Guardian Name" name="cadreRegistrationVO.relativeName"   value="${voterInfoVOList[0].relativeName}"></input>
										</div>
										<input type="hidden" value="${voterInfoVOList[0].voterId}" name="cadreRegistrationVO.voterId"></input>
										<div class="span6">
										<h5 class="text-align1">Relationship Type</h5>
											<input type="text" class="form-control border-radius-0 " placeholder="Relationship Type" name="cadreRegistrationVO.relationType"   value="${voterInfoVOList[0].relationType}"></input>
										</div>
									</div>
								</div>
								<div class="m_top10">
										<div class="row-fluid">
										
											<div style="width:150px;float:left;">
											<h5 class="text-align1">VOTER ID</h5>
												<input type="text" class="form-control border-radius-0 text-align2 " placeholder="Voter Id" name="cadreRegistrationVO.voterCardNumber"   id="cardNumber" value="${voterInfoVOList[0].voterCardNo}" readonly style="width:135px;"></input>
												
												<!--<input type="hidden" id="cardNo" class="form-control border-radius-0 input-block-level" placeholder="Text input" value="${voterInfoVOList[0].voterCardNo}" style="width:260px;" ></input>-->
											</div>
											<div style="width: 120px; float: left; margin-top: 20px;">
												<a id="searchByNameId" class="btn btn-success" href="javascript:{enableLookupName();}" style="margin-top: 20px; width: 120px; margin-left: 16px;">LookUp By Name</a>
											</div>
											<div style="width: 120px; float: left; margin-left: 49px;">
											<h5 class="text-align1">H NO</h5>
												<input type="text" class="form-control border-radius-0 " placeholder="House Number" name="cadreRegistrationVO.houseNo" style="width: 120px; float: left; margin-left: 0px;"  value="${voterInfoVOList[0].houseNo}"></input>
											</div>
											<div class="span6"> <input type="checkbox" id="relativeTypeChecked" name="relativeTypeChecked" onclick="showHideFamRelatinoSts();"/> Is Family Member</div>
											<div  class="span6" id="showHideFammemberType" style="display:none; margin-left: 232px;margin-top: -33px;"><select name="relativeTypeChecked" id="relativeTypeId"> </select></div>
										</div>
								</div>	
								
								<div class="m_top10">
										<div class="row-fluid">
										
											<div class="span6">
											<h5 class="text-align1">PARTY MEMBER SINCES</h5>
												<input type="text" class="form-control border-radius-0 text-align2 datePickerCls" placeholder="Party Member Since " name="cadreRegistrationVO.partyMemberSinceStr"  value="${voterInfoVOList[0].activeDate}"  readOnly="true"></input>
											</div>
											
											<div class="span6">
											<h5 class="text-align1">Blood Group</h5>
											<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="bloodgroupId" list="voterInfoVOList[0].selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Blood Group" style="width:220px;" name="cadreRegistrationVO.bloodGroupId"  value="%{voterInfoVOList[0].blodGroupId}"/>
											
											</div>
										</div>
								</div>	
								<!--<div class="m_top10">
									
											<h5 class="text-align1">REFERED BY</h5>
												<input type="text" class="form-control border-radius-0 text-align1" placeholder="">
							
								</div>-->
						
					</div>
					<div class="span6 show-grid pad-10b" id="fadeInRight"  >
					
								<div class=" m_top20" >
										<h5 class="text-align1">STREET/HAMLET</h5>
										<input type="text" class="form-control border-radius-0  input-block-level" placeholder=" Street / Hamlet " name="cadreRegistrationVO.street"  value="${voterInfoVOList[0].location}" style="width:260px;"></input>
								</div>	
							<div class=" m_top20" >
										<h5 class="text-align1">CASTE NAME</h5>
										<input type="text" class="form-control border-radius-0  input-block-level" id="casteIdValue" placeholder=" Caste Name "   value="${voterInfoVOList[0].casteName}" style="width:260px;"></input>
										
										<input type="hidden" class="form-control border-radius-0  input-block-level" id="casteId" placeholder="Enter Caste" name="cadreRegistrationVO.casteId"  value="${voterInfoVOList[0].casteId}" style="width:260px;"></input>
										
							</div>
							<div class=" m_top20" >
										<h5 class="text-align1">MOBILE NUMBER  <span id="mobileErr" style="color:red;font-size:12px;"></span> </h5>
										<input type="text" id="mobileNumberId" class="form-control border-radius-0 input-block-level" placeholder=" Mobile Number "  name="cadreRegistrationVO.mobileNumber"  value="${voterInfoVOList[0].mobileNo}" style="width:260px;" maxlength="10" onKeyup="isNumber()"></input>
							</div>
							<div class=" m_top20" >
								<h5 class="text-align1">EDUCATION</h5>
								<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="educationId" list="genericVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Education " style="width:260px;" name="cadreRegistrationVO.educationId" value="%{voterInfoVOList[0].education}"/>
							</div>
							<div class=" m_top20" >
								<h5 class="text-align1">OCCUPATION</h5>

							<input type="hidden" class="form-control border-radius-0 input-block-level"  id="occupationId" placeholder="Occupation" name="cadreRegistrationVO.occupationId" value="${voterInfoVOList[0].occupationId}" style="width:260px;"></input>
							
							<input type="text" class="form-control border-radius-0 input-block-level"  id="occupationValue"  placeholder=" Enter Occupation "  value="${voterInfoVOList[0].occupation}" style="width:260px;"></input>
							
							</div>
							<div class=" m_top20" > 
									<h5 class="text-align1">PREVIOUS ENROLLMENT NUMBER</h5>
									<input type="text" id="preEnrollNoValue" class="form-control border-radius-0 input-block-level" placeholder="Previous Enrollment No."  value="${voterInfoVOList[0].memberShipId}" style="width:260px;"  onkeyup="getExistingCadreInfo();" name="cadreRegistrationVO.previousEnrollmentNumber" readonly></input>
									<a id="searchByNameId" class="btn btn-success" href="javascript:{enableSearchByName();}" style="margin-top: -10px;"> Search By Name </a>
									<input type="hidden" id="preEnrollNo" class="form-control border-radius-0 input-block-level" placeholder="Text input"  value="${voterInfoVOList[0].memberShipId}" style="width:260px;" ></input>
									
							</div>
						
					</div>
				</div>
		
		</div>
	</div>
	<!----  srishailam starts  -->
	
		<div class="span12" >
				<div class="row-fluid">
					<div class="span  offset3 show-grid m_top10"  id="fadeInLeft" style="margin-left:204px;margin-bottom:10px;">						
						<div class="m_top10">
						<div class="span12 " style="position: relative;">
							<h3 class="text-align "> FAMILY DETAILS </h3>
						</div>
								<div class="row-fluid" id="addVotersDiv">		
											<table>
											<thead>
											<tr>
												<th style="width:223px;"> VOTER NAME </th>
												<th  style="width:145px;"> VOTER CARD NO</th>
												<th  style="width:80px;"> AGE </th>
												<th  style="width:80px;"> GENDER </th>
												<th  style="width:156px;"> EDUCATION  </th>
												<th  style="width:182px;"> OCCUPATION </th>
												<th> <a class="icon-plus-sign" style="float:right;margin-right:0px;margin-top:-13px;" onClick="addMoreVoters();"  title="Add More Voter Details"> </a></th>
													
							<s:if test="%{voterInfoVOList[0].voterInfoVOList != null && voterInfoVOList[0].voterInfoVOList.size() > 0}">
											</tr>
											</thead>
											<tbody>		
											
									<c:forEach var="familyVO" items="${voterInfoVOList[0].voterInfoVOList}" varStatus="commentLoop">
									
										<input type="hidden" value="${commentLoop.index}" id="countVar"></input>
											<tr class="voterDev${commentLoop.index}">
												<td style="width:100px;">   
													<input type="text" class="form-control border-radius-0 text-align2" placeholder="Voter Name " value="${familyVO.name}" name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].voterName"></input> 
													<input type="hidden" class="form-control border-radius-0 text-align2" value="${familyVO.voterId}"  name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].voterId"></input> 
												</td>
												<td style="width:100px;"> 
													<input type="text" class="form-control border-radius-0 text-align2" placeholder="Voter Card No " value="${familyVO.voterCardNo}"name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].voterCadreNO" style="width:120px;"></input>
												</td>
												<td style="width:80px;"> 
													<input type="text" class="form-control border-radius-0 text-align2" placeholder="Age " value="${familyVO.age}" style="width:53px;"></input> 
												</td>
												<td style="width:80px;">
													<input type="text" class="form-control border-radius-0 text-align2" placeholder="Gender " value="${familyVO.gender}" style="width:50px;"> </input>
												</td>
												<td style="width:100px;"> 
												
													<select name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].educationId" style="width:160px;">
													<option value="0">Select Education</option>
														<c:forEach var="educationList" items="${genericVOList}" >															
															<c:if test="${educationList.id == familyVO.education }">																
																<option value="${educationList.id}" selected="selected">${educationList.name}</option>
															</c:if>	
															<c:if test="${educationList.id != familyVO.education }">																
																<option value="${educationList.id}">${educationList.name}</option>
															</c:if>	
														</c:forEach>
													</select>
													 
												</td>
												<td style="width:100px;"> 
												
													 <input type="text" id="familyOccupation${commentLoop.index}" class="familyOccupationsCls form-control border-radius-0 text-align2" placeholder=" Occupation "  style="width:150px;"  value="${familyVO.occupation}"> </input>
													 
													 <input type="hidden"  id="familyOccupation${commentLoop.index}0" name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].occupationId" class="form-control border-radius-0 text-align2" placeholder="Enter Occupation " style="width:50px;" value="${familyVO.occupationId}"> </input>
													 
													 
												</td>
												<td style="width:15px;"> 
												<a class="icon-minus-sign" style="float:right;margin-top:-10px;" onClick="deleteRollesForm('voterDev${commentLoop.index}');" title="Remove Details"></a>
												</td>
											</tr>
											
									</c:forEach>
									</tbody>									
								</table>
								
								
									
								</s:if>
								<s:else>
											
											</tr>
											</thead>
											<tbody>	
										<input type="hidden" value="0" class="countVar"></input>
											<tr class="voterDev0">
												<td> 
													<input type="text" class="form-control border-radius-0 text-align2" placeholder=" Voter Name "  ></input> 
													<input type="hidden" class="form-control border-radius-0 text-align2"  name="cadreRegistrationVO.cadreFamilyDetails[0].voterId"  ;"></input> 
												</td>
												<td> 
													<input type="text" class="form-control border-radius-0 text-align2" placeholder=" Voter Id " name="cadreRegistrationVO.cadreFamilyDetails[0].voterCadreNO"  style="width:120px;"></input>
												</td>
												<td style="width:80px;"> 
													<input type="text" class="form-control border-radius-0 text-align2" placeholder=" Age " style="width:50px;"></input> 
												</td>
												<td style="width:80px;">
													<input type="text" class="form-control border-radius-0 text-align2" placeholder="Gender"  style="width:50px;"> </input>
												</td>
												<td style="width:100px;"> 
												
													<select name="cadreRegistrationVO.cadreFamilyDetails[0].educationId" style="width:160px;">
													<option value="0">Select Education</option>
														<c:forEach var="educationList" items="${genericVOList}" >
															<option value="${educationList.id}">${educationList.name}</option>
														</c:forEach>
													</select>
													 
												</td>
												<td style="width:100px;"> 
													<input type="text" id="familyOccupation0" class="familyOccupationsCls form-control border-radius-0 text-align2" placeholder="  Occupation "  style="width:150px;"  value=""> </input>
													 
													<input type="hidden"  id="familyOccupation00" name="cadreRegistrationVO.cadreFamilyDetails[0].occupationId" class="form-control border-radius-0 text-align2" placeholder="Enter Occupation " style="width:50px;" value=""> </input>
												</td>
												<td style="width:15px;"> 
													<a class="icon-minus-sign" style="float:right;margin-top:-10px;" onClick="deleteRollesForm('voterDev0');" title="Remove Details">
												</td>
											</tr>
											
									</tbody>									
									</table>	
																							
								</s:else>
								
								</div>
						</div>
					</div>
				
	</div>
	</div>
	<!----  srishailam end  -->
	
	
	<div id="fadeInUp">
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<h3 class="text-align ">ACCIDENTAL INSURANCE DETAILS</h3>
			</div>
		</div>
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;" id="accDiv">
				<div class="span2">
					<h5 class="text-align1"> Aadhar Card  </h5>
					<input type="text" class="" style="width: 138px;" placeholder="Aadheer Number"  name="cadreRegistrationVO.aadheerNo" value="${voterInfoVOList[0].aadharNo}"></input> 
				</div>
				
				<div class="span2">
					<h5 class="text-align1"> Nominee Name </h5>
					<input type="text" class="" style="width: 138px;"placeholder="Nominee Name"  name="cadreRegistrationVO.nomineeName" value="${voterInfoVOList[0].nomineeName}"></input>
				</div>
				
				
				
				<div class="span2">
					<h5 class="text-align1"> Gender </h5>
					<select name="cadreRegistrationVO.nomineeGender" style="width: 138px;">
							<option value="0">Select Gender</option>	
							<option value="1">Male</option>
							<option value="2">Female</option>
					</select>
				</div>
				
				<div class="span2">
					<h5 class="text-align1"> Age </h5>
					<input type="text" class=""  style="width: 100px;" placeholder="Nominee Name"  name="cadreRegistrationVO.nomineeAge" value="${voterInfoVOList[0].nomineAge}"></input>
				</div>
				
				<div class="span2">
					<h5 class="text-align1"> Relation Type </h5>
					<select name="cadreRegistrationVO.voterRelationId" style="width:160px;" id="voterRelationId">
							<option value="0">Select Relation</option>	
							<option value="1">Father</option>
							<option value="2">Mother</option>
							<option value="3">Wife</option>
							<option value="4">Brother</option>
							<option value="5">Sister</option>
							<option value="6">Friend</option>
					</select>
				</div>
			</div>
		</div>
	</div>
	
	
	<div id="fadeInUp">
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<h3 class="text-align ">PREVIOUS ROLES PARTICIPATED IN PARTIES</h3>
			</div>
		</div>
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;" id="rollesDiv">	
			<s:if test="%{voterInfoVOList[0].cadreRolesList != null && voterInfoVOList[0].cadreRolesList.size() > 0}">
					<c:forEach var="role" items="${voterInfoVOList[0].cadreRolesList}" varStatus="indexValue">
					
			
				
			
					<div class="levelCls">
						<div>
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1"> Committee Level </h5>
						</c:if>
							<select class="levelCls" id="CadreCommitteeLevelsId" name="cadreRegistrationVO.previousRollesList[0].cadreCommitteeLevelId"  style="margin-left: 12px">			
							<c:forEach var="educationList" items="${cadreRolesVOList[1].selectOptionsList}" >		
									<c:if test="${educationList.id == role.id }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != role.id }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
							</c:forEach>															
									
							</select>
						</div>
		
					</div>
					
					<div class="levelCls">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1"> Cadre Committee </h5>
						</c:if>
							<select class="levelCls" id="CadreRolesId" name="cadreRegistrationVO.previousRollesList[0].cadreCommitteeId" style="margin-left: 12px">
							<c:forEach var="educationList" items="${cadreRolesVOList[0].selectOptionsList}" >		
										<c:if test="${educationList.id == role.count }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != role.count }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
							</c:forEach>
							</select>
									
						</div>
					</div>
					
					<div class="levelCls">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">Cadre Role </h5>
						</c:if>
								<select class="levelCls" id="CadreCommitteeId" name="cadreRegistrationVO.previousRollesList[0].cadreRoleId" style="margin-left: 12px">
								<c:forEach var="educationList" items="${cadreRolesVOList[2].selectOptionsList}" >		
										<c:if test="${educationList.id == role.rank }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != role.rank }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
							</c:forEach>
								</select>
						</div>
		
					</div>
		
					<div class="levelCls">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">From Date</h5>
						</c:if>
								<div class="input-prepend text-align2 ">				
									<input type="text" class="levelDtCls form-control span2 border-radius-0 border-right-0 datePickerCls" style="margin-top:5px;" name="cadreRegistrationVO.previousRollesList[0].fromDateStr" placeholder="From Date"  readOnly="true" value="${role.startTime}"></input></span>
								</div>
						</div>
					</div>
					<div class="levelCls">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">To Date</h5>
						</c:if>
								<div class="input-prepend  ">	
									<input type="text" class="levelDtCls form-control span2  border-radius-0 border-right-0 datePickerCls" style="margin-top:5px;" name="cadreRegistrationVO.previousRollesList[0].toDateStr" placeholder="To Date"  readOnly="true" value="${role.endTime}"></input></span>
								</div>
						</div>
					</div>
					
		
			</c:forEach>
			<div>
				<a class="icon-plus-sign" style="float:left;margin-top:30px;" onClick="createNewForm();" title="Add More Details"></a>
			</div>
				
			</s:if>
			<s:else>
					<div class="levelCls">
						<div>
							<h5 class="text-align1"> Committee Level </h5>
							<select class="levelCls" id="CadreCommitteeLevelsId" name="cadreRegistrationVO.previousRollesList[0].cadreCommitteeLevelId"  style="margin-left: 12px">			
							<c:forEach var="educationList" items="${cadreRolesVOList[1].selectOptionsList}" >																	
										<option value="${educationList.id}">${educationList.name}</option>
							</c:forEach>															
									
							</select>
						</div>
		
					</div>
					
					<div class="levelCls">
						<div class=" " >	
							<h5 class="text-align1"> Cadre Committee </h5>
							<select class="levelCls" id="CadreRolesId" name="cadreRegistrationVO.previousRollesList[0].cadreCommitteeId" style="margin-left: 12px">
				
							<c:forEach var="educationList" items="${cadreRolesVOList[0].selectOptionsList}" >
										<option value="${educationList.id}">${educationList.name}</option>
							</c:forEach>
							</select>
									
						</div>
					</div>
					
					<div class="levelCls">
						<div class=" " >
							<h5 class="text-align1">Cadre Role </h5>
								<select class="levelCls" id="CadreCommitteeId" name="cadreRegistrationVO.previousRollesList[0].cadreRoleId" style="margin-left: 12px">
						
								<c:forEach var="educationList" items="${cadreRolesVOList[2].selectOptionsList}" >	
									<option value="${educationList.id}">${educationList.name}</option>
								</c:forEach>
								</select>
						</div>
		
					</div>
		
					<div class="levelCls">
						<div class=" " >
							<h5 class="text-align1">From Date</h5>
								<div class="input-prepend text-align2 ">				
									<input type="text" class="levelDtCls form-control span2 border-radius-0 border-right-0 datePickerCls" style="margin-top:5px;" name="cadreRegistrationVO.previousRollesList[0].fromDateStr" placeholder="From Date"  readOnly="true" value=""></input></span>
								</div>
						</div>
					</div>
					<div class="levelCls">
						<div class=" " >
							<h5 class="text-align1">To Date</h5>
								<div class="input-prepend  ">	
									<input type="text" class="levelDtCls form-control span2  border-radius-0 border-right-0 datePickerCls" style="margin-top:5px;" name="cadreRegistrationVO.previousRollesList[0].toDateStr" placeholder="To Date"  readOnly="true" value=""></input></span>
								</div>
						</div>
					</div>					

			</s:else>
			</div>
	</div>
	</div>
	<div id="fadeInUp1">
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<h3 class="text-align ">PREVIOUSLY PARTICIPATED IN ELECTION</h3>
			</div>
		</div>
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;" id="electionsDiv">
			
			<s:if test="%{voterInfoVOList[0].previousParticipationInfoList != null && voterInfoVOList[0].previousParticipationInfoList.size() > 0}">
					<c:forEach var="role" items="${voterInfoVOList[0].previousParticipationInfoList}" varStatus="indexValue">
					
					<div class="row-fluid">
					<div class="span3">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">Election Type</h5>
						</c:if>

							<select  style="margin-left: 12px" onChange="getElectionYears(this.value,'electionYearId${indexValue.index}')" id="electionsTypeID${indexValue.index}">
								<option value="0"> Select Election Type</option>
								<c:forEach var="educationList" items="${eletionTypesList}" >															
									<c:if test="${educationList.id == role.id }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != role.id }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
								</c:forEach>
							</select>
							
						</div>
					</div>
					<div class="span3">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">Year</h5>
						</c:if>							  		
							 <select class="" name="cadreRegistrationVO.previousParicaptedElectionsList[${indexValue.index}].electionTypeId" id="electionYearId${indexValue.index}" onchange="getConstiteuncyListForElection(this.value,'constituencyList${indexValue.index}')">
								<option value="0"> Select Election </option>
								<c:forEach var="educationList" items="${role.genericVOList}" >															
									<c:if test="${educationList.id == role.count }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != role.count }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
								</c:forEach>
							</select>							
						</div>
					</div>
					<div class="span3">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">Constituency</h5>
						</c:if>

							<select   id="constituencyList${indexValue.index}"  style="margin-left: 12px" onchange="getCandidateDetailsForElection(this.value,'candidatesList${indexValue.index}','electionYearId${indexValue.index}',
							'electionsTypeID${indexValue.index}');">
								<option value="0"> Select Constituency</option>
								<c:forEach var="educationList" items="${voterInfoVOList[0].previousParticipationInfoList[indexValue.index].basicVO.hamletCasteInfo}" >															
									<c:if test="${educationList.id == role.rank }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != role.rank }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
								</c:forEach>
							</select>
							
						</div>
					</div>	

					<div class="span3">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">Candidate </h5>
						</c:if>

							<select   id="candidatesList${indexValue.index}"  style="margin-left: 12px" onchange="getCandidateDetailsById('constituencyList${indexValue.index}','electionYearId${indexValue.index}',this.value);">
								<option value="0"> Select Candidate</option>
								<c:forEach var="educationList" items="${voterInfoVOList[0].previousParticipationInfoList[indexValue.index].basicVO.hamletVoterInfo}" ><option value="${educationList.id}">${educationList.name}</option>
								</c:forEach>
							</select>
							
						</div>
					</div>		
					
				</div>				
					</c:forEach>
					<a class="icon-plus-sign" style="float:right;margin-right:206px;;margin-top:-30px;" onClick="createNewFormForElections();" title="Add More Details"></a>
					
					<div id="candidateDivTab" style="float:left;"></div>
					
			</s:if>
			<s:else>
			
				<div class="row-fluid">
					<div class="span3">
						<div class=" " >
							<h5 class="text-align1">Election Type</h5>
							<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="electionTypeId" list="eletionTypesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Election Type" style="width:220px;margin-left: 12px"   onChange="getElectionYears(this.value,'electionYearId0')" value="%{role.id}"/>
						</div>
					</div>
					<div class="span2">
						<div class=" " >
							<h5 class="text-align1">Year</h5>
							<select class="" style="width: 150px;" name="cadreRegistrationVO.previousParicaptedElectionsList[0].electionTypeId" id="electionYearId0" onchange="getConstiteuncyListForElection(this.value,'constituencyList0')">							
							  </select>
						</div>
					</div>
					<div class="span3">
						<div class=" " >
							<h5 class="text-align1">Constituency</h5>
							
							<select id="constituencyList0" name="cadreRegistrationVO.previousParicaptedElectionsList[0].constituencyId" style="margin-left: 12px" onchange="getCandidateDetailsForElection(this.value,'candidatesList0','electionYearId0',
							'electionsTypeID0');">		
							<option value="0"> Select Constituency</option>
							 </select>
							
						</div>
					</div>
					
					<div class="span3">
						<div class=" " >
							<h5 class="text-align1">Candidate </h5>
							<select   id="candidatesList0"  style="margin-left: 12px" onchange="getCandidateDetailsById('constituencyList0','electionYearId0',this.value); " name="cadreRegistrationVO.previousParicaptedElectionsList[0].candidateId">
								<option value="0"> Select Candidate</option>
								<c:forEach var="educationList" items="${voterInfoVOList[0].previousParticipationInfoList[indexValue.index].basicVO.hamletVoterInfo}" ><option value="${educationList.id}">${educationList.name}</option>
								</c:forEach>
							</select>
							
						</div>
					</div>	
					
					<div class="span2">
						<div class="" >
									<a class="icon-plus-sign" style="float:left;margin-top:30px;" onClick="createNewFormForElections();" title="Add More Details"></a>
						</div>
					</div>
					
					<div id="candidateDivTab" style="float:left;"></div>
				</div>				
			</s:else>
			</div>
		</div>
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<!-- <a class="btn btn-primary m_top20 border-radius-0 text-align2" href="search-constituency.html"><span class="icon-chevron-left icon-white"></span>&nbsp;&nbsp;Back </a> -->
				
				<a  class="btn btn-success text-align3 m_top20 pull-right border-radius-0"  onClick="submitCadreForm();"> &nbsp;&nbsp;Next<span class=" icon-chevron-right icon-white"></span></a>
			</div>
		</div>
	</div>
</form>
</div>
</div>

<div id="statusDiv">
</div>
	<!-- Footer Row -->
		<div class="row-fluid">
			<div class="span12 text-center m_top5 color-white">
					Cadre Registration Drive
				<p>Copyright &copy; 2014,  All Rights Reserved</p>
			</div>
		</div>
	<!-- Footer Row End-->
		
</body>
<script type="text/javascript">
var existingCadreArr = [];
var existingCadreInfoArr = [];
function getExistingCadreInfo()
{

	var value = $('#preEnrollNoValue').val();
	$('#preEnrollNo').val('');
	var constituencyId = '${constiteucnyId}';
	var panchayatId = '${houseNo}';  // panchayat Id 
	var boothId = '${boothId}';  // boothId Id 
	var isPresentCadre = '${panchayatId}';  // ispresentCader checked ot not 
	
	if(value.trim().length >2)
	{
		existingCadreArr = [];
		existingCadreInfoArr = [];
			var jsObj = 
			   {	
				name : value,
				constituencyId : constituencyId,
				panchayatId : panchayatId,
				boothId : boothId,
				isPresentCadre:isPresentCadre,
				task:"getExistingCadreInfo"             
			   }
			   
			   $.ajax({
					type : "POST",
					url : "getExistingCadreInfoAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					if(result != null && result.length > 0)
					{
						for(var i in result)
						{
							var cadreObj = {
							id : result[i].id,
							name : 	result[i].name,
							relativeName : result[i].desc,
							enrollNo : result[i].caste,
							check : result[i].name+" - "+result[i].desc+" - "+result[i].caste
							}
							
							existingCadreArr.push(cadreObj);
							
							existingCadreInfoArr.push(result[i].name+" - "+result[i].desc+" - "+result[i].caste);
						}
						
						$( "#preEnrollNoValue" ).autocomplete({ 
							source:existingCadreInfoArr,
							select: function (event, ui) {
								$('#preEnrollNoValue').val(ui.item.value);	
								
								for(var k in existingCadreArr)
								{
									
									if(existingCadreArr[k].check == ui.item.value)
									{
										console.log(existingCadreArr[k]);
										$('#preEnrollNo').val(existingCadreArr[k].enrollNo);
										$('#preEnrollNoValue').val(existingCadreArr[k].enrollNo);	
										break;
									}
								}
								
								return false;
							}
						});
		
					}
				});
	}
}

$("#cardNumber").keyup(function(){
	var cardNumberVal = $("#cardNumber").val();
	if(cardNumberVal.trim().length>2){
		var constituencyId = '${constiteucnyId}';
		var panchayatId = '${houseNo}'; 
		var boothId = '${boothId}';
		var isPresentCadre = '${panchayatId}';
		searchedVoters = [];
		searchedVotersInfoArr = [];
		
		var jsObj ={
					  constituencyId:constituencyId,
					  searchType :"voter", 
					  candidateName:cardNumberVal,
					  houseNo : "",
					  voterCardNo : "",
					  panchayatId : panchayatId,
					  boothId : boothId ,
					  locationId : 0,
					  isPresentCadre:isPresentCadre,
					  task:"searchCandidatesDtailsBySearchCriteria"             
				   }
		
		 $.ajax({
					type : "POST",
					url : "searchVoterAndCadreInfoBySearchCriteriaAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					//if(result==null){return;}
					for(var i in result){
							var vtrsObj = {
								id : result[i].id,
								name : 	result[i].name,
								relativeName : result[i].desc,
								voterCardNo :result[i].voterCardNo,
								check : result[i].name+" - "+result[i].voterCardNo+" - "+result[i].relativeName+" - "+result[i].relationType+" - "+result[i].age+" - "+result[i].gender
							}
							searchedVoters.push(vtrsObj);
							searchedVotersInfoArr.push(result[i].name+" - "+result[i].voterCardNo+" - "+result[i].relativeName+" - "+result[i].relationType+" - "+result[i].age+" - "+result[i].gender);
					}
					
					$( "#cardNumber" ).autocomplete({ 
							source:searchedVotersInfoArr,
							select: function (event, ui) {
								$('#cardNumber').val(ui.item.value);	
								for(var k in searchedVoters){
									console.log(searchedVoters);
									if(searchedVoters[k].check == ui.item.value){
										//$('#cardNo').val(searchedVoters[k].voterCardNo);
										$('#cardNumber').val(searchedVoters[k].voterCardNo);	
										break;
									}
								}
								
								return false;
							}
						});
						
						
				});
	}
	
});

function getCandidateDetailsById(constituencyId,electionId,nominationId )
{
var electionsId = $('#'+electionId+'').val();
	$('#candidateDivTab').html('');
	var jsObj = 
	   {
			electionId : electionsId,
			nominationId : nominationId,
			task:"getCandidateDetailsById"             
	   }				   
	   $.ajax({
			type : "POST",
			url : "getCandidateInfoByNominationAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			buildCandidateDetails(result);
		});
}
var prvEleCount = 1;
function buildCandidateDetails(result)
{
	var str ='';
	if(result != null)
	{
		str +='<table class="table table-bordered m_top20 table-hover table-striped"  id="seachDetalsTab1" style="width:850px;">';
		str+='<tbody>';
		
		for(var i in result)
		{
			str+='<tr class="removeId'+i+'" >';
				str += '<input type="hidden" value=""></input>';
				str += '<input type="hidden" value='+result[i].orderId+' name="cadreRegistrationVO.previousParicaptedElectionsList['+prvEleCount+'].electionTypeId"></input>';
				str += '<input type="hidden" value='+result[i].mainAccountId+' name="cadreRegistrationVO.previousParicaptedElectionsList['+prvEleCount+'].constituencyId"></input>';
				str +='<td>'+result[i].name+'</td>';
				str +='<td>'+result[i].location+'</td>';
				str +='<td>'+result[i].panchayatName+' ('+result[i].partno+')</td>';
				str +='<td> <a class="icon-minus-sign" href="javascript:{deleteDetails(\'removeId'+i+'\')}"></a></td>';
			str+='</tr>';
			prvEleCount++;
		}
		
		str+='</tbody>';
		str +='</table>';
		
		$('#candidateDivTab').html(str);
	}
}
function showHideFamRelatinoSts(){
  
   if($('#relativeTypeChecked').prop('checked')==true){
     $('#showHideFammemberType').show();
   }else{
     $('#showHideFammemberType').hide();
   }
}
function getAllRelationDetails(){
	 $.ajax({
		type : "POST",
		url : "getAllRelationDetails.action"
	}).done(function(result){
	  if(result != null && result.length > 0){
	   for(var i in result){
		 $('#relativeTypeId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	    }
	  }
	 
	  <s:if test="voterInfoVOList[0].relative != null">
	    $('#relativeTypeId').val('${voterInfoVOList[0].relationTypeId >0}');
		$('#relativeTypeChecked').attr("checked","checked");
		showHideFamRelatinoSts();
	  </s:if>
	});
}
getAllRelationDetails();
function deleteDetails(id)
	{
		if(confirm('Are you sure want to delete it? '))
		{
			$('.'+id).remove();
		}		
	}
	
</script>
</html>