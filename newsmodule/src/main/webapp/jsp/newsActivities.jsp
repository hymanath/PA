<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>News Activities</title>
</head>
<!-- Bootstrap Scripts files start -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- Bootstrap Scripts files End -->

<!-- JQuery Scripts files start -->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="js/jquery.carousel.js"></script>
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>

<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<!-- JQuery Scripts files End -->

<!-- JQuery Style files start -->
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 
<!-- JQuery Style files End -->

<style>
@font-face
{
    font-family:eFont;src: url('img/eenadu.ttf');
}
@font-face{ font-family: 'eFont'; src: url('fonts/eenadu.eot');}
@font-face {
    font-family: "eFont";
    font-style: normal;
    font-weight: normal;
    src: local("?"), url("fonts/eenadu_fonts/eenadu.woff") format("woff"), url("fonts/eenadu_fonts/eenadu.ttf") format("truetype"), url("fonts/eenadu_fonts/eenadu.svg") format("svg");
}
 
.enadu
{
font-family: eFont;
font-size:20px;
}
.newssources{
 background-color:#97DFEB;
 padding:8px 8px 8px 8px;
 margin-left:5px;
 border-radius: 5px 5px 5px 5px;

}
</style>
<script type="text/javascript">
/*
	this is is the staring method when ever page is loaded it will execute first
*/
$(document).ready(function(){
	$('#todateId').datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
		changeYear: true,
		maxDate: new Date()
	});
	$('#fromDateId').datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
		changeYear: true,
		maxDate: new Date()
	});

	$('#districtSelReportId').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
	}).multiselectfilter({    
	});
			
	
	 $('#assembSelReportId').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
	   }).multiselectfilter({   
	}); 
	
	

});
var districtIds = ""
/*
	this method is called for filling the seleted districts with comma seperator
*/
function getConstituencyes()
{

	var selected_values = $("#districtSelReportId").multiselect("getChecked").map(function(){
       return this.value;
     }).get();
	 districtIds = "";
	 for(var i in selected_values){
         districtIds = districtIds+""+selected_values[i]+",";
      }
	  
	  getConstituencyesList();
}
/*
this method is used for making a ajax call for getting all constituencyes
*/
function getConstituencyesList()
{
	var jsObj = {
		districtIds : districtIds,
		task        : "getDistricts"
	}
	 $.ajaxSetup({
		   jsonp: null,
		   jsonpCallback: null
		});
	 $.ajax({
		type: "POST",
		url: "getConstituencyusAction.action",
		data: {task : JSON.stringify(jsObj)}
		})
		.done(function( result ) {
		
		$('#assembSelReportId').find('option').remove();
		$.each(result,function(index,value){
			$('#assembSelReportId').append('<option value="'+value.id+'">'+value.name+'</option>');
			$('#assembSelReportId').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	
	        }).multiselectfilter({   
	       });
		});
     });
}
/*
this method is used for getting the all categories and filling the categories list in select box
*/
function getPartyGallariesForUplaod()
{
	var partyId=872;
	var jsObj =
		{ 
			partyId : partyId,
			contentType : "News Gallary",
			type:"whomegallaryId",
			task : "partyGallariesForUplaod"
		};

	 $.ajaxSetup({
		   jsonp: null,
		   jsonpCallback: null
		});
	 $.ajax({
		type: "POST",
		url: "getPartyGallariesForUplaodPhotoAction.action",
		data: {task : JSON.stringify(jsObj)}
		})
		.done(function( result ) {
		
		$('#catSelReportId').find('option').remove();
		$.each(result,function(index,value){
			$('#catSelReportId').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
		
			$("#catSelReportId").multiselect({	
					multiple: true,
					selectedList: 1,
					hide: "explode"	
			}).multiselectfilter({
				header:"Select Keyword"    
			});
     });
}
/*
this method is used for clearing the date field 
*/
function clearDate(id){
   $("#"+id).val('');
}
getPartyGallariesForUplaod();

function getCategoeryWiseNews(pageNo)
{

	var fromDate = $('#fromDateId').val();
	
	var toDate   = $('#todateId').val();
	
	var categorieyIds = "";
	var selected_values = $("#catSelReportId").multiselect("getChecked").map(function(){
        return this.value;
    }).get();
	for(var i in selected_values)
	{
        categorieyIds = categorieyIds+""+selected_values[i]+",";
    }
	
	var constituencyIds = "";
	var selected_values1 = $("#assembSelReportId").multiselect("getChecked").map(function(){
        return this.value;
    }).get();
	for(var j in selected_values1)
	{
        constituencyIds = constituencyIds+""+selected_values1[j]+",";
    }
	
	var jsObj =
	{ 
		fromDate        : fromDate,
		toDate          : toDate,
		categorieyIds   : categorieyIds,
		constituencyIds : constituencyIds,
		startIndex      : pageNo,
		maxIndex        : 10,
		task            : "categoeryWiseNews"
	};
	
	$.ajax({
	type: "POST",
	url: "getCategoeryWiseNewsAction.action",
	data: {task : JSON.stringify(jsObj)}
	})
	.done(function( result ) {
		buildCategoeryDetails(result,jsObj);
     });
	
}

function buildCategoeryDetails(result,jsObj)
{
	if(result != null && result.length > 0)
	{
		var str = '';
		str += '<table id="responseTable" class="table table-bordered table-hover"><thead>';
		str += '<tr>';
		str += '<th>Date</th>';
		str += '<th>Constituency</th>';
		str += '<th>Categories</th>';
		str += '<th>News</th>';
		str += '</tr></thead><tbody>';
		for(var i in result)
		{
			str += '<tr>';	
			str += '<td>'+result[i].value+'</td>';
			str += '<td>'+result[i].location+'</td>';
			str += '<td>'+result[i].type+'</td>';
			if(result[i].id == 0)
			{
				str += '<td><p>'+result[i].name+'</p></td>';
			}
			else
			{
				str += '<td><p class="enadu">'+result[i].name+'</p></td>';
			}
			str += '</tr>';
		}
		str += '</tbody></table>';
		var itemsCount=result[0].orderId;
	    var maxResults=jsObj.maxIndex;
	    $("#categoeryDetails").html(str);
	
	    if(jsObj.startIndex==0)
		{
			$("#paginationId").pagination({
				items: itemsCount,
				itemsOnPage: maxResults,
				searchType:'catgNews',
				cssStyle: 'light-theme'
			});
		
			/* $('#responseTable').dataTable({
			   "iDisplayLength": 10,
			   "aLengthMenu": [[10, 20, 30, -1], [10, 20, 30, "All"]],
			  "aoColumns": [null,null,null,null] 
			}); */
	    }
	}
	
	getCategoeryWiseNewsByCount(jsObj,"constituency");
	getCategoeryWiseNewsByCount(jsObj,"district");
}
function getCategoeryWiseNewsByCount(jsObj,type)
{
	var selected_values = $("#districtSelReportId").multiselect("getChecked").map(function(){
        return this.value;
    }).get();
	var districtIds = "";
	for(var i in selected_values)
	{
        districtIds = districtIds+""+selected_values[i]+",";
    }
	var jsObj =
	{ 
		fromDate        : jsObj.fromDate,
		toDate          : jsObj.toDate,
		categorieyIds   : jsObj.categorieyIds,
		constituencyIds : jsObj.constituencyIds,
		districtIds     : districtIds,
		type            : type,
		typeFor         : "",
		task            : "categoeryWiseNewsCount"
	};
	
	$.ajax({
	type: "POST",
	url: "getCategoeryWiseNewsAction.action",
	data: {task : JSON.stringify(jsObj)}
	})
	.done(function( result ) {
		buildCategoeryDetailsCountWise(result,jsObj);
     });
}
function buildCategoeryDetailsCountWise(result,jsObj)
{
	if(result != null && result.length > 0)
	{
		$('#pdfExcelDiv').show();
		var str = '';
		str += '<table class="table table-bordered table-hover">';
		str += '<tr>';
		str += '<th>Constituency</th>';
		for(var i in result[0].selectOptionsList)
		{
			str += '<th>'+result[0].selectOptionsList[i].name+'</th>';
		}
		str += '</tr>';
		for(var j in result)
		{
			str += '<tr>';
			str += '<td>'+result[j].location+'</td>';
			for(var m in result[j].selectOptionsList)
			{
				str += '<td>'+result[j].selectOptionsList[m].id+'</td>';
			}
			str += '</tr>';
		}
		str += '</table>';
		if(jsObj.type == "constituency")
		{
			$('#categoeryDetailsConstituencyCountWise').html(str);
		}
		else
		{
			$('#categoeryDetailsDistrictCountWise').html(str);
		}
		
	}
}

function genereatePDFOrExcel(type)
{
	var fromDate = $('#fromDateId').val();
	
	var toDate   = $('#todateId').val();
	
	var categorieyIds = "";
	var selected_values = $("#catSelReportId").multiselect("getChecked").map(function(){
        return this.value;
    }).get();
	for(var i in selected_values)
	{
        categorieyIds = categorieyIds+""+selected_values[i]+",";
    }
	
	var constituencyIds = "";
	var selected_values1 = $("#assembSelReportId").multiselect("getChecked").map(function(){
        return this.value;
    }).get();
	for(var j in selected_values1)
	{
        constituencyIds = constituencyIds+""+selected_values1[j]+",";
    }
	var selected_values2 = $("#districtSelReportId").multiselect("getChecked").map(function(){
        return this.value;
    }).get();
	var districtIds = "";
	for(var i in selected_values2)
	{
        districtIds = districtIds+""+selected_values2[i]+",";
    }
	var jsObj =
	{ 
		fromDate        : fromDate,
		toDate          : toDate,
		categorieyIds   : categorieyIds,
		constituencyIds : constituencyIds,
		districtIds     : districtIds,
		typeFor         : type,
		task            : "genereatePDFOrExcel"
	};
	
	$.ajax({
	type: "POST",
	url: "getCategoeryWiseNewsAction.action",
	data: {task : JSON.stringify(jsObj)}
	})
	.done(function( result ) {
		//buildCategoeryDetails(result,jsObj);
     });
}
</script>

<body>
<!----Container---->
  <div class="container">
  <!--------- Row-1 -------->
    <div class="row-fluid">
    <div class="span12 content_widget">
        <legend class="boxHeading text-center">News Activities</legend>

		<div class="row-fluid">
			<div class="span6" style="margin-left: -4px;">
			<label class="help-inline"><strong>From Date :</strong></label>
			<input type="text"  id="fromDateId" readonly="readonly">
			<span title="Clear From Date" style="cursor: pointer;" onclick="clearDate('fromDateId');" class="icon-remove-sign"></span>							
			</div>
			<div class="span6" style="margin-left: -104px;">
			<label class="help-inline"><strong>To Date :</strong></label>
			<input type="text" id="todateId" readonly="readonly">	
			<span title="Clear To Date" style="cursor: pointer;" onclick="clearDate('todateId');" class="icon-remove-sign"></span>				</div>
		</div>
		
		<div class="row-fluid m_top10">
			<div  class="span4">
			<label><strong>Select Categoery :</strong></label>
				<select  name="catSelReport" id="catSelReportId"></select>
			</div>
			
			<div  class="span4">
			<label><strong>Select District :</strong></label>
			<s:select name="districtSelReport" id="districtSelReportId" class="districtSelReportId" list="districtsList" theme="simple" listKey="id" listValue="name" headervalue="Select District" onChange="getConstituencyes();"/>
			</div>
			
			<div  class="span4">
			<label><strong>Select Constituency :</strong></label>
			<select name="assembSelReport" id="assembSelReportId"></select>
			</div>
			
		</div>
	    <!-------Submit Button------>
		<div class="form-actions text-center">
		<button class="btn btn-success" type="submit" onClick="getCategoeryWiseNews(0);">Submit</button><img id="submitDataImg" style="display: none;margin-left:10px;" src="images/search.jpg">
		
		</div><!-------Submit Button END------>
		
		<div>
		    <div id="categoeryDetailsDistrictCountWise"></div>
			<div id="categoeryDetailsConstituencyCountWise"></div>
			<div id="pdfExcelDiv" style="display:none;float:right;">
			<a class="btn btn-info" onClick="genereatePDFOrExcel('pdf');">Pdf</a>
			<a class="btn btn-info" onClick="genereatePDFOrExcel('excel');">Excel</a>
			</div>
		</div>
		
		<div style="margin-top: 66px;">
			<div id="categoeryDetails"></div>
			<div id="paginationId"></div>
		</div>
		
		</div>	
		</div><!--------- Row-1 End -------->
  </div><!----Container END---->
</body>
</html>