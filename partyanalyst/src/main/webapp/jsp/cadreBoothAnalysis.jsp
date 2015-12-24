<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Booth Analysis</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  
 <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css"> 
<script type="text/javascript" src="js/exportexcel.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<script type="text/javascript" src="js/sample.js"></script>
<style>
  #boothDataDiv .selected, #boothDataDiv .selectedchild{background:#EA4F5A !important;}
 #boothDataDiv .selected1, #boothDataDiv .selected1child{background: #0BBA7C !important;}

 
</style>
</head>
<body>
   <div class="container m_top10">
		<div id="usersWorkingStatusDiv">
			<div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase">  Cadre Booth Analysis</h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
			   <div id="errStatusDiv" align="center" ></div>
			   
			
			   
			   
			   <table  style="margin-left: 270px;" id="tabularReport">
					
				     <tr id="statedisplaydivid">
						<td><b>Select State :</b></td>
						<td>
						  <select id="statesDivId">
							
							<option value="1">AndhraPradesh</option>
							<option value="2">Telangana</option>
						  </select>
						</td>
				     </tr>
				  
				   <tr>
				      <td></td><td><input type="button" style="margin-left: 12px;margin-top: 13px;" class="btn btn-success" onclick="getConstituencyWiseBoothInfo();" value="Submit"/> 
						<div><img id="ajaxImgStyle" style="margin-left: 10px;width:80px;display:none;" src="images/Loading-data.gif"/></div>
					  </td>
					  
					  
				  </tr>
				  
			</table>
			  
					  <div id="boothDataDiv"></div>
					  
			  </div>
			
			</div>
			
		</div>
   </div>
   <script>

   function getConstituencyWiseBoothInfo()
   {
		$("#errStatusDiv").html("");
	
		var stateId = $("#statesDivId").val();
		$("#boothDataDiv").html('');
		$("#ajaxImgStyle").show();
		var jObj = {
		
		stateId:stateId,
		task:"constituencyInfo"
		}
		 $.ajax({
          type:'GET',
          url: 'getCadreBoothAnalysisInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
				$("#ajaxImgStyle").hide();
				buildData(result);
		});
   }
   function buildData(result)
   {
	   var str='';
	  str+='<table class="table table-bordered" id="tabledata1">';
		str+='<thead>';
		str+='<tr>';
		
		str+='<th>Constituency</th>';
		str+='<th>Total Booths</th>';
		str+='<th>Registration Started Booths</th>';
		str+='<th>Booths Below 10</th>';
		//str+='<th>Male(30%)</th>';
		//str+='<th>FemMale(30%)</th>';
		str+='<th>Difference(30%)</th>';
	
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
	   {
		str+='<tr class="removeCls clearCls'+result[i].id+'">';
		
	   str+='<td class="removeCls clearClsTD'+result[i].id+'">'+result[i].name+'</td>';
		str+='<td >'+result[i].totalBooths+'</td>';

		str+='<td>'+result[i].startedBooths+'</td>';
		str+='<td  class="removeCls clearClsTD'+result[i].id+'"><a  onclick="displayBoothDetails('+result[i].id+',\''+result[i].below10BoothIds+'\');" style="cursor:pointer;">'+result[i].belowCadres+'</a></td>';
		//str+='<td>'+result[i].mCount+'</td>';
		//str+='<td>'+result[i].fCount+'</td>';
		str+='<td class="removeCls clearClsTD'+result[i].id+'"><span class="pull-right removeicon"  id="iconDiv'+result[i].id+'" onclick="closeDiv('+result[i].id+');" style="display:none;"><i class="icon-remove"></i></span><a  onclick="displayBoothDetails('+result[i].id+',\''+result[i].boothIds+'\');" style="cursor:pointer;">'+result[i].count+'</a></td>';
		
		str+='</tr>';
	   }
	   str+='</tbody>';
		str+='</table>';
		$("#boothDataDiv").html(str);
		$("#tabledata1").dataTable();
   }
      function closeDiv(trID)
   {
	  
	$(".selectedchild").remove();
   }
   function displayBoothDetails(id,boothIds)
   {
	 $('.added').remove('');
	$(".removeicon").hide();
	$(".removeCls").removeClass("selected");
	$('.clearCls'+id).after('<tr class="selectedchild"><td id="subLevel'+id+'" colspan="8" class="added"><div align="center"><img id="ajaxImgStyle1" style="display:none;margin-left: 10px;width:80px;" src="images/Loading-data.gif"/></div></td></tr>');;
	$('.clearCls'+id).addClass("selected");
	$('.clearClsTD'+id).addClass("selected");
	$("#ajaxImgStyle1").show();
	$("#iconDiv"+id).show();
   var jObj = {
		
		id:id,
		ids :boothIds,
		task:"boothInfo"
		}
		 $.ajax({
          type:'GET',
          url: 'getCadreBoothAnalysisInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
				$("#ajaxImgStyle1").hide();
				buildData1(result,"subLevel"+id);
		});
   }
   function buildData1(result,divId)
   {
	   var str='';
	  str+='<table class="table table-bordered" id="tabledata1">';
		str+='<thead>';
		str+='<tr>';
		str+='<th>SNO</th>';
		str+='<th>Mandal/Muncipality</th>';
		str+='<th>Booth</th>';
		str+='<th>male count</th>';
		str+='<th>female count</th>';
		
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		var j =1;
		for(var i in result)
	   {
		str+='<tr>';
		str+='<td>'+j+'</td>';
	   str+='<td >'+result[i].districtName+'</td>';
	   str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].maleCnt+'</td>';

		str+='<td>'+result[i].feMaleCnt+'</td>';
		
		j++;
		str+='</tr>';
	   }
	   str+='</tbody>';
		str+='</table>';
		$("#"+divId).html(str);;
   }
 </script>
</body>
</html>