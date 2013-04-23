<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <title> Update voters details </title>
  <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script> 
  <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <script type="text/javascript" src="js/blockui.js"></script>
  <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
  <link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<style>
tr.labelClass1 td{background-color:#c3c3c3;}
.labelClass{float:left;margin:3px 7px 0px 0px;}
#voterEditDetailsShowDIV table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color:#676A67;}
#voterEditDetailsShowDIV table td{padding-right:3px !important;}
#voterEditDetailsShowDIV table th{padding:5px !important;}
#voterEditDetailsShowDIV table tr:nth-child(even){background:#EdF5FF;}
#voterDetailsDiv table th a{color:#333333;}
#voterEditDetailsShowDIV table th{background-color: #CDE6FC;font-size: 13px; font-weight: bold;padding-bottom: 10px;padding-left: 10px;padding-right: 10px;    padding-top: 10px;text-align: left;color:#333333;}
#voterEditDetailsShowDIV table{border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;
width:100%;background:#EdF5FF;padding:8px;padding-left:10px;font-weight:normal;
font:small-caption;color: #676A67;}
tr.diffColor td { background-color: yellow;}
#directionsDiv{display:none;position:fixed;z-index:1;background-color:yellow;border-radius:4px;margin:70px;}
#updateBtn{position:fixed;top:175px;right:0px; z-index: 999;}
.noMargin{margin:0px}
</style>
</head>
<body>

<div id="directionsDiv" >
 <div  style="float:left;margin-top:10px;" title="Click on the image to move left">
 <a class="left" href="javascript:{}"><img src="images/icons/arrows/leftarrow.png"/></a> 
 </div>
 <div style="width:23px;float:left;" >
 <a title="Click on the image to move up" class="top" href="javascript:{}"><img src="images/icons/arrows/uparrow.png"/></a>
 <a title="Click on the image to move down" class="bottom" href="javascript:{}"><img src="images/icons/arrows/downarrow.png"/></a>
 </div>
 <div style="float:left;margin-top:10px;" title="Click on the image to move right">
 <a class="right" href="javascript:{}"><img src="images/icons/arrows/rightarrow.png"/></a>
 </div>
</div>



  <form id="updateSelectedVoters" method="post" action="updateAllSelectedVotersInformation.action" name="updateSelectedVoters">
	   <input type="hidden" name="task" id="updateAllVotersFormValues" />
  </form>

   <form id="getAllVoterFamiliesForEditForm1" method="post" action="getVotersFamiliesForEditFormAction.action" name="getAllVoterFamiliesForEditForm1">
	   <input type="hidden" name="task" id="getAllVoterFamiliesForEditFormValues1" />
   </form>

 <div id="voterEditDetailsShowDIV" style="width:auto;overflow:auto;"></div>				
			
 <a id="updateBtn" class="btn btn-info" title="click here to update voter details" href="javascript:{updatedetailsBasedOnSelection();}">Click here to update<a>		
	
 </div>

<script>
var selectedVotersNewArr = new Array();
 $(document).ready(function(){

	  $(".topDivBtn").live("click",function() {
        $("html,body").animate({scrollTop: $("#bottomDiv").offset().top});
      });

	  $(".bottomDivBtn").live("click",function() {
        $("html,body").animate({scrollTop: $("#topDiv").offset().top});
      });

	

	  $('.right').click(function() {
       $("#mainDiv").animate({ scrollLeft: "+=200" }, "slow");
           return false;
      });

	 $('.left').click(function() {
		$("#mainDiv").animate({ scrollLeft: "-=200" }, "slow");
		return false;
	 });

	 $('.bottom').click(function() {
       $("html , body").animate({ scrollTop: "+=200" }, "slow");
           return false;
     });

	 $('.top').click(function() {
		$("html , body").animate({ scrollTop: "-=200" }, "slow");
		return false;
	 });

	 $('.selectionType').live("change",function(){
		 if($('input:radio[name=type]:checked').val() == "All")
			 $('#selectedOptionsDiv').hide('slow');
		 else
			 $('#selectedOptionsDiv').show('slow');
	 });


	$("#votersDetailsTable td").live("click", function (e) {

      if (e.target === this){
		//if($(this).parents("tr:first").is('.diffColor'))
		if(!$(this).parents("tr:first").hasClass('diffColor'))
		 selectedVotersToUpdate.push($(this).closest('tr').find('#voterId').val());
		else		
			selectedVotersToUpdate.splice($.inArray($(this).closest('tr').find('#voterId').val(), selectedVotersToUpdate), 1);

		$(this).parents("tr:first").toggleClass("diffColor");
	  }
    });


	for(var i in window.opener.selectedVotersArr)
	{
		var obj={
				 voterId:window.opener.selectedVotersArr[i].voterId,
			     boothId:window.opener.selectedVotersArr[i].boothId
			   }
			   selectedVotersNewArr.push(obj);
	}

	getAllVoterFamiliesForEditWithSelection11();

	$('.allFieldsCheck').live("change",function(){
		if ($(this).is(':checked')) {
			
			 $('.allFieldsUnCheck').attr('checked',false);

			  $('.fields').each(function(index,value){
				 $(this).attr('disabled',true);
				 $(this).attr('checked',true);
				 $(this).trigger('change')
			  });
		}else{
			
              $('.allFieldsUnCheck').attr('checked',true); 
			  
			  $('.fields').each(function(index,value){
			    $(this).attr('disabled',false);
                $(this).attr('checked',false);
			    $(this).trigger('change')
		      });

		}

	});

	$('.allFieldsUnCheck').live("change",function(){

		if ($(this).is(':checked')) {
		  $('.allFieldsCheck').attr('checked',false);
		    $('.fields').each(function(index,value){
				$(this).attr('disabled',false);             
             $(this).attr('checked',false);
			 $(this).trigger('change')
		   });
		}else{

		 $('.allFieldsCheck').attr('checked',true);
		    $('.fields').each(function(index,value){
             $(this).attr('disabled',true);
             $(this).attr('checked',true);
			 $(this).trigger('change')
		   });

		}

	});

	 $('.fields').live("change",function(){
		
		 var value = $(this).val();

		 var indextoShow = $('#votersDetailsTable tr th').filter(
           function(){
           return $(this).text() == value;
          }).index();

		 if($(this).is(":checked")){	 
            $('#votersDetailsTable  th').eq(indextoShow).show();
			$('#votersDetailsTable tbody tr').each(function() {
                $(this).find("td").eq(indextoShow).show();            
            });
			
		 }else{
			  $('#votersDetailsTable  th').eq(indextoShow).hide();
			 $('#votersDetailsTable tbody tr').each(function() {
                $(this).find("td").eq(indextoShow).hide();            
            });
		 }
			  
	 });
});

function getAllVoterFamiliesForEditWithSelection11(){

	 $.blockUI({ message: '<h6><img src="images/icons/ajaxImg.gif"/>Please wait.....</h6>' });

	var jsObj=
			{		           
				votersIds:selectedVotersNewArr,
				selectedType:window.opener.selectedType,
				selectedTypeId:window.opener.selectedTypeId,
				publicationId:window.opener.RpublicationDateId
				
			}
			$("#getAllVoterFamiliesForEditFormValues1").val(YAHOO.lang.JSON.stringify(jsObj));
			var uploadHandler = {
			   success : function( o ) {
						  var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
						 buildSelectedVotersData(uploadResult);
					}									
		   };

	
	YAHOO.util.Connect.setForm('getAllVoterFamiliesForEditForm1',false);
	YAHOO.util.Connect.asyncRequest('POST','getVotersFamiliesForEditFormAction.action?save=',uploadHandler);
 }

function unSelectAllSelectedRows()
{
	selectedVotersToUpdate = new Array();
	$("#votersDetailsTable tr").each(function(){
     $(this).removeClass('diffColor');
	});

}

function invertSelection()
{
   $("#votersDetailsTable tr").each(function(index,value){
	if(index > 0){
		$(this).toggleClass('diffColor');
		
		if($(this).hasClass('diffColor'))
			selectedVotersToUpdate.push($(this).closest('tr').find('#voterId').val());
		else
			selectedVotersToUpdate.splice($.inArray($(this).closest('tr').find('#voterId').val(), selectedVotersToUpdate), 1);
		  }

	   });
}

var totalCategoriesCount = 0;
var votersToUpdate = new Array();
var selectedVotersToUpdate = new Array();
var totalNoOfVoters = 0;
function buildSelectedVotersData(results)
{
	
	votersToUpdate = new Array();
            

	 var str='';	
	   

		
	

        str+='<label class="labelClass"><input type="checkbox" value="Voter Name" class="allFieldsCheck noMargin" checked="true"/>&nbsp;Select All</label>';

		str+='<label class="labelClass"><input type="checkbox" value="Voter Name" class="allFieldsUnCheck noMargin"/>&nbsp;Unselect All</label>';

		str+='<label class="labelClass"><input type="checkbox" value="Voter Name" class="fields noMargin" checked="true"/>&nbsp;VoterName</label>';

		str+='<label class="labelClass"><input type="checkbox" value="House No" class="fields noMargin" checked="true"/>&nbsp;House No</label>';

		str+='<label class="labelClass"><input type="checkbox" value="Gaurdian Name" class="fields noMargin" checked="true"/>&nbsp;Gaurdian Name</label>';
		

		str+='<label class="labelClass"><input type="checkbox" value="Relationship" class="fields noMargin" checked="true" style="margin:0px;"/>&nbsp;Relationship</label>';

		str+='<label class="labelClass"><input type="checkbox" value="Age" class="fields" checked="true" style="margin:0px;"/>&nbsp;Age</label>';

		str+='<label class="labelClass"><input type="checkbox" value="Gender" class="fields" checked="true" style="margin:0px;"/>&nbsp;Gender</label>';

		str+='<label class="labelClass"><input type="checkbox" value="Caste" class="fields" checked="true" style="margin:0px;"/>&nbsp;Caste</label>';

		str+='<label class="labelClass"><input type="checkbox" value="Party" class="fields" checked="true" style="margin:0px;"/>&nbsp;Party</label>';

		str+='<label class="labelClass"><input type="checkbox" value="Locality Name" class="fields" checked="true" style="margin:0px;"/>&nbsp;LocalityName</label>';

		str+='<label class="labelClass"><input type="checkbox" value="Sub Locality Name" class="fields" checked="true" style="margin:0px;"/>&nbsp;SubLocalityName</label>';

     

       for(var id in results.userCategoriesList)		   
		str+='<label class="labelClass"><input type="checkbox" value="'+results.userCategoriesList[id].name+'" class="fields" checked="true" style="margin:0px;"/>&nbsp;'+results.userCategoriesList[id].name+'</label>';
	   str+='<br>';

	       
		str+='<div style="clear: both; margin-top: 28px; margin-left: 295px;"><label style="float:left;margin:0px 10px 0px 0px;"><h5><input style="margin:0px;" type="radio" value="All" checked name="type" class="selectionType"/>&nbsp;Update all</h5></label>';
		str+='<label style="float:left;"><h5><input style="margin:0px;" type="radio" value="selected" name="type" class="selectionType"/>&nbsp;Update only selected records</label>(Click on the record to select)</h5></div>';

		str+='<div id="selectedOptionsDiv" style="clear:both;display:none;margin: 10px 0px 0px 386px;"><a class="btn btn-info" style="margin:3px;" href="javascript:{unSelectAllSelectedRows();}">Unselect all</a><a class="btn btn-inverse" href="javascript:{invertSelection();}">Invert selection</a></div>';

		str+='<div id="mainDiv" style="overflow: auto; width: 980px;">';
		str+='<div id="topDiv"></div>';
		str+='<b><a class="topDivBtn pull-right" href="javascript:{}" class="btn">Move To Bottom</a></b>';
		
		
          str+='<table id="votersDetailsTable">';
		  str+='<thead>';
		   str+='<tr>';
		    str+='<th style="text-align:justify;">Booth No</th>';
		    str+='<th style="text-align:justify;">SNo</th>';
		    str+='<th style="text-align:justify;">Voter Name</th>';
			str+='<th style="text-align:justify;">House No</th>';
			str+='<th style="text-align:justify;">Gaurdian Name</th>';
			str+='<th style="text-align:justify;">Relationship</th>';
			
			str+='<th style="text-align:justify;">Age</th>';
			str+='<th style="text-align:justify;">Gender</th>';			
			str+='<th style="text-align:justify;">Caste</th>';
			str+='<th style="text-align:justify;">Party</th>';
            str+='<th style="text-align:justify;">Locality Name</th>';
			str+='<th style="text-align:justify;">Sub Locality Name</th>';
             totalCategoriesCount = results.userCategoriesList.length;
			for(var id in results.userCategoriesList)		   
			str+='<th style="text-align:justify;">'+results.userCategoriesList[id].name+'</th>';
		
		   str+='</tr>';
		   str+='</thead>';

	  for(var i in results.boothsList){

		  var family = results.boothsList[i].familiesList;  
		  
		   	
		  for(var j in family){
			   var voters = family[j].votersList;

			   for(var k in voters){

			totalNoOfVoters = totalNoOfVoters +1;

			  str+='<tr>';
			     str+='<td>'+results.boothsList[i].partNo+'</td>';
			    str+='<td><input type="hidden" id="voterId" value="'+voters[k].voterId+'"/>'+voters[k].fromSno+'</td>';
		        str+='<td>'+voters[k].name+'</td>';
				str+='<td>'+voters[k].houseNo+'</td>';
				str+='<td>'+voters[k].gaurdian+'</td>';
			    str+='<td>'+voters[k].relationship+'</td>';				
			    str+='<td>'+voters[k].age+'</td>';
				str+='<td>'+voters[k].gender+'</td>';


                 votersToUpdate.push(voters[k].voterId);

			    str+='<td>';
				 str+='<div class="row" style="width:149px;"><select class="casteClass caste'+i+j+'" id="caste'+voters[k].voterId+'" title="Select caste to the voter:'+voters[k].name+'" style="width:100px;">';
				    for(var l in results.casteGroupNameList){
						   if(voters[k].casteStateId != results.casteGroupNameList[l].id)
							str+="<option value="+results.casteGroupNameList[l].id+">"+results.casteGroupNameList[l].name+"</option>";
						   else
							str+="<option value="+results.casteGroupNameList[l].id+" selected='selected'>"+results.casteGroupNameList[l].name+"</option>";			
					 }
				 str+='</select>';
				 str+='<a  title="Apply  this to current family" href="javascript:{applyValue(\'caste'+voters[k].voterId+'\',\'caste'+i+j+'\');}"><i class="icon-ok"></i></a>';
				 str+='<a  title= "Apply this to  all families" href="javascript:{applyValue(\'caste'+voters[k].voterId+'\',\'casteClass\');}"><i class="icon-ok-sign"></i></a>';
				str+='</div></td>';

			    str+='<td>';
				 str+='<div class="row" style="width:149px;"><select title="Select party to the voter:'+voters[k].name+'" class="partyClass party'+i+j+'" id="party'+voters[k].voterId+'" style="width:100px;">';
				    for(var l in results.parties){
						   if(voters[k].partyId != results.parties[l].id)
							str+="<option value="+results.parties[l].id+">"+results.parties[l].name+"</option>";
						   else
							str+="<option value="+results.parties[l].id+" selected='selected'>"+results.parties[l].name+"</option>";			
						 }
				 str+='</select>';
				 str+='<a  title="Apply  this to current family" href="javascript:{applyValue(\'party'+voters[k].voterId+'\',\'party'+i+j+'\');}"><i class="icon-ok"></i></a>';
				 str+='<a title="Apply  this to all families"  href="javascript:{applyValue(\'party'+voters[k].voterId+'\',\'partyClass\');}"><i class="icon-ok-sign"></i></a>';
				str+='</div></td>';

				str+='<td>';
				 str+='<div class="row" style="width:149px;"><select id="locality'+voters[k].voterId+'" class="localityClass locality'+i+j+'" style="width:100px;"  onChange="getLocalitiesListForHamlet(this.value , '+voters[k].voterId+',\'single\');" title="Select locality to the voter:'+voters[k].name+'">';


				  for(var l in results.localitiesList){
					if(voters[k].hamletId != results.localitiesList[l].id) 
						str+="<option value="+results.localitiesList[l].id+">"+results.localitiesList[l].value+"</option>";
					 else{
							  selectedValue = results.localitiesList[l].id;
						  str+="<option selected='selected' value="+results.localitiesList[l].id+">"+results.localitiesList[l].value+"</option>";
					 }
				  }
                      
 				str+='</select>';
				str+='<a title="Apply  this to current family" href="javascript:{applyValueForLocality(\'locality'+voters[k].voterId+'\',\'locality'+i+j+'\');}"><i class="icon-ok"></i></a>';
				str+='<a  title="Apply  this to all families" href="javascript:{applyValueForLocality(\'locality'+voters[k].voterId+'\',\'localityClass\');}"><i class="icon-ok-sign"></i></a>';
				str+='</div></td>';
				str+='<div class="row" style="width:149px;"><td>';
				
				if(voters[k].hamletId != null)
			    {
                  str+='<div class="row" style="width:149px;"><select id="sublocality'+voters[k].voterId+'" title="Select sub locality to the voter:'+voters[k].name+'" style="width:100px;">';
				  for(var l in voters[k].subLocalities){					 
					 
					if(voters[k].subLocalityId !=  voters[k].subLocalities[l].id)								   
						 str+="<option value="+voters[k].subLocalities[l].id+">"+voters[k].subLocalities[l].value+"</option>";

					     else

						 str+="<option selected='selected' value="+voters[k].subLocalities[l].id+">"+voters[k].subLocalities[l].value+"</option>";						
						 
				  }                      
 				 str+='</select>';
				}
				else				
					str+='<div class="row" style="width:149px;"><select id="sublocality'+voters[k].voterId+'" style="width:100px;">';
					str+='</select>';				

				str+='</div></td>';				


				for(var m in voters[k].categoriesList){

						str+='<td><div class="row" style="width:149px;"><input type="hidden" id="'+voters[k].voterId+'categ'+m+'" class="categ'+m+'" value="'+voters[k].categoriesList[m].userCategoryValueId+'"/>';
                          str+='<select id="'+voters[k].voterId+'categVal'+m+'" style="width:100px;" class="categClass'+m+' categ'+i+j+m+'" title="Select '+results.userCategoriesList[m].name
+' to the voter:'+voters[k].name+'">';
						    for(var l in voters[k].categoriesList[m].category)
								{
							   if(voters[k].categoriesList[m].categoryValuesId != voters[k].categoriesList[m].category[l].id)
								str+="<option value="+voters[k].categoriesList[m].category[l].id+">"+voters[k].categoriesList[m].category[l].name+"</option>";
							   else
								str+="<option value="+voters[k].categoriesList[m].category[l].id+" selected='selected'>"+voters[k].categoriesList[m].category[l].name+"</option>";			
							 }
						  str+='</select>';
                           str+='<a  title="Apply  this to current family" href="javascript:{applyValue(\''+voters[k].voterId+'categVal'+m+'\',\'categ'+i+j+m+'\');}"><i class="icon-ok"></i></a>';
						   str+='<a  title="Apply  this to all families" href="javascript:{applyValue(\''+voters[k].voterId+'categVal'+m+'\',\'categClass'+m+'\');}"><i class="icon-ok-sign"></i></a>';
						str+='</div></td>';

				}

  			  str+='</tr>';
			   }
		  }
	  }
	  str+='</table>';
	  str+='<div id="bottomDiv"></div>';
		str+='<b><a class="bottomDivBtn pull-right" href="javascript:{}" >Move To Top</a></b>';
	  str+='<div id="successMessageDiv"></div>';
    
	  $('#voterEditDetailsShowDIV').html(str);
	  $('#directionsDiv').show();

	  $('#votersDetailsTable').dataTable({
		  "iDisplayLength" : totalNoOfVoters
	  });

		   $('#votersDetailsTable_length').hide();
            $.unblockUI();
}

function applyValueForLocality(id,className)
{
	var selectedValue = $('#'+id).val();
	getLocalitiesListForHamlet(selectedValue,"","multiple");
	//$('.'+className).val(selectedValue);
	$('.'+className).each(function(index,value){
       $(this).val(selectedValue);
	});

}

function applyValue(id,className)
{
	var selectedValue = $('#'+id).val();
	//$('.'+className).val(selectedValue);
	$('.'+className).each(function(index,value){
        $(this).val(selectedValue);
         
		 $(this).closest("tr").addClass("labelClass1")
                       .delay(700)
                       .queue(function() {
						   $(this).removeClass('labelClass1');
                           $(this).dequeue();
                       });
// $(this).closest("tr").addClass("labelClass1").fadeIn(100).fadeOut(100).fadeIn(100).fadeOut(100).fadeIn(100).removeClass("labelClass1");
					  
	});

     
}

function updatedetailsBasedOnSelection()
{
	if($('input:radio[name=type]:checked').val() == "All")
		updateAllSelectedVotersDetails();
	else
		updateAllSelectedVotersDetails1();
}

function updateAllSelectedVotersDetails1()
{

	if(selectedVotersToUpdate.length == 0){
		alert("Select atleast one recore to update");
		return false;
	}

	allVotersToUpdate = new Array();

	 for(var i in selectedVotersToUpdate)
	 {
		 var obj={
		 };
		 obj["partyId"] = $('#party'+selectedVotersToUpdate[i]).val();
		 obj["casteId"] = $('#caste'+selectedVotersToUpdate[i]).val();
		 obj["voterId"] = selectedVotersToUpdate[i];

		 if ($('#locality'+selectedVotersToUpdate[i]).length )
			obj["hamletId"] = $('#locality'+selectedVotersToUpdate[i]).val();
		 else
			obj["hamletId"] = 0;

		 if ($('#sublocality'+selectedVotersToUpdate[i]).length ){

			 var localityHamletId = $('#sublocality'+selectedVotersToUpdate[i]).val();
			
			 if(localityHamletId == null || localityHamletId == 0)
			  obj["localityHamletId"] = 0;
			 else
				 obj["localityHamletId"] = localityHamletId;

		 }
		 else
		  obj["localityHamletId"] = 0;
		 

		 for(var j = 0 ; j< totalCategoriesCount ;j++){

			 obj["categ"+j] = $('#'+selectedVotersToUpdate[i]+'categ'+j).val()+"-"+$('#'+selectedVotersToUpdate[i]+'categVal'+j).val();
		 }
		 allVotersToUpdate.push(obj);

	 }

	 var jsObj=
			{
				total:totalCategoriesCount,
				selectedVoters:allVotersToUpdate,
				task:"updateIndividuls"
		  };
		  $("#updateAllVotersFormValues").val(YAHOO.lang.JSON.stringify(jsObj));
		  
		  var uploadHandler = {
			success: function(o) {
				var uploadResult = YAHOO.lang.JSON.parse(o.responseText);

				var cssObj = {    
				   'font-weight' : 'bold',
				   'color' : 'green',
				   'margin-left':'400px'
				}
		

		$('#successMessageDiv').text("Voters details updated successfully...").css(cssObj).show().delay(2000).fadeOut(400);
			}
		};

	
	YAHOO.util.Connect.setForm('updateSelectedVoters',false);
	YAHOO.util.Connect.asyncRequest('POST','updateAllSelectedVotersInformation.action?save=',uploadHandler);

}

 function updateAllSelectedVotersDetails()
 {
	 if(votersToUpdate.length == 0){
		alert("Select atleast one recore to update");
		return false;
	}
	allVotersToUpdate = new Array();

	 for(var i in votersToUpdate)
	 {
		 var obj={
		 };
		 obj["partyId"] = $('#party'+votersToUpdate[i]).val();
		 obj["casteId"] = $('#caste'+votersToUpdate[i]).val();
		 obj["voterId"] = votersToUpdate[i];

		 if ($('#locality'+votersToUpdate[i]).length )
			obj["hamletId"] = $('#locality'+votersToUpdate[i]).val();
		 else
			obj["hamletId"] = 0;

		 if ($('#sublocality'+votersToUpdate[i]).length ){

			 var localityHamletId = $('#sublocality'+votersToUpdate[i]).val();
			
			 if(localityHamletId == null || localityHamletId == 0)
			  obj["localityHamletId"] = 0;
			 else
				 obj["localityHamletId"] = localityHamletId;

		 }
		 else
		  obj["localityHamletId"] = 0;
		 

		 for(var j = 0 ; j<3 ;j++){

			 obj["categ"+j] = $('#'+votersToUpdate[i]+'categ'+j).val()+"-"+$('#'+votersToUpdate[i]+'categVal'+j).val();
		 }
		 allVotersToUpdate.push(obj);

	 }

	 var jsObj=
			{
				total:totalCategoriesCount,
				selectedVoters:allVotersToUpdate,
				task:"updateIndividuls"
		  };
		  $("#updateAllVotersFormValues").val(YAHOO.lang.JSON.stringify(jsObj));
		  
		  var uploadHandler = {
			success: function(o) {
				var uploadResult = YAHOO.lang.JSON.parse(o.responseText);

				var cssObj = {    
				   'font-weight' : 'bold',
				   'color' : 'green',
				   'margin-left':'400px'
				}
		

		$('#successMessageDiv').text("Voters details updated successfully...").css(cssObj).show().delay(2000).fadeOut(400);
			}
		};

	
	YAHOO.util.Connect.setForm('updateSelectedVoters',false);
	YAHOO.util.Connect.asyncRequest('POST','updateAllSelectedVotersInformation.action?save=',uploadHandler);
 }

 function getLocalitiesListForHamlet(value , voterId , type)
	{
		var jsObj=
			{
					
				selected:value,
				voterId:voterId,
				type:type,	
				task:"getLocalities"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
		
	}

function buildSubLocalitiesForAllVoters(results,jobj)
{
  for(var i in votersToUpdate)
  {
	  var id = '#sublocality'+votersToUpdate[i];
	  $(id).find('option').remove();
	  for(var i in results)
	$(id).append('<option value='+results[i].id+'>'+results[i].name+'</option>');


  }
}

function buildSubLocalities(results,jobj)
{
	var id = '#sublocality'+jobj.voterId;		

	$(id).find('option').remove();

	for(var i in results)
	$(id).append('<option value='+results[i].id+'>'+results[i].name+'</option>');

}

function callAjax(jsObj,url)
{
	var myResults;
	 var callback = {			
			   success : function( o ) {
					try {												
							myResults = YAHOO.lang.JSON.parse(o.responseText);					
						if(jsObj.task == "getLocalities"){	
							debugger;
							if(jsObj.type == "single")
							 buildSubLocalities(myResults,jsObj);
							else
							 buildSubLocalitiesForAllVoters(myResults,jsObj);
						}
					
														
					}catch (e) { 
						 $("#votersEditSaveAjaxImg").hide();
						 $("#votersEditSaveButtnImg").removeAttr("disabled");
						}  
			   },
			   scope : this,
			   failure : function( o ) {
							//alert( "Failed to load result" + o.status + " " + o.statusText);
						 }
			   };

YAHOO.util.Connect.asyncRequest('POST', url, callback);
}
</script>  
 </body>
</html>
