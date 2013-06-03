<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Voters Analysis</title>
<style type="text/css">
.noteDiv {
    margin-bottom: 18px;
    margin-top: 29px;
}
#impFamiliesTitle{
margin-top: 17px;
}
.table thead.info th,.impFamilesMainDiv th,#censusTab th{background:#d9edf7; color:#454545;}

.whitegloss h5.whitegloss{margin: 0px -20px; padding: 10px 10px 10px 20px;clear:both;}

.FamiliyList li{margin:5px;font-weight:bold;font-size:14px;padding:6px;width:100%;}
#ImportantFamiliesDiv{
	  margin-bottom: 20px;
	}
</style>
<script>
var customVoterGroupId = "${customVoterGroupId}";
var publicationId = "${publicationDateId}";
var typeName = "${typeName}";
var publicationYear="${publicationYear}";

function getFamilies(){
	$("#basicInfoAjaxDiv").show();
				var jsObj1=
					{
						typeName:typeName,
						customVoterGroupId:customVoterGroupId,
						publicationDateId:publicationId,
						task:"CustomVoterImpFamilies",
			
					}
			var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj1);
					var url1 = "getCustomVoterFamilyAction.action?"+rparam1;						
				callAjax(jsObj1,url1);
}
function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
									if(jsObj.task == "CustomVoterImpFamilies")
								{
										CustomVoterImpFamiliesTable(myResults,jsObj);
										
								}
								}catch (e) {
							     
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}

function CustomVoterImpFamiliesTable(myresults,jsObj){
	$("#basicInfoAjaxDiv").hide();
	$("#impFamiliesTitle").show();
	$("#ImportantFamiliesDiv").show();

	var value=0;
	var str='';
	var type = jsObj.type;	
	str+='<div class="impFamilesMainDiv row">';
	
	$("#impFamiliesTitle").html(" "+jsObj.typeName+" Family Wise Statistics in "+publicationYear+"");
		str += '<div class="span3" style="margin-left: 30px;"><ul class="FamiliyList"> <li> <div style="width:68%;float:left;">Total Voters </div> <span style="clear:left;">: '+myresults.totalVoters+'</span></li>';

	if(myresults.totalFamalies==null){
		str+='<li><div style="width:68%;float:left;"> Total Families </div> : <span style="clear:left;">'+value+'</span> </li> ';
	}
	else{
	str+='<li><div style="width:68%;float:left;"> Total Families </div> : <span style="clear:left;">'+myresults.totalFamalies+'</span></li> ';
	}

	if(myresults.totalMaleVoters==null){
		str +='<li><div style="width:68%;float:left;"> Total Male Voters </div> : <span style="clear:left;"> '+value+'</span></li> ';
	}
	else{
	str +='<li><div style="width:68%;float:left;"> Total Male Voters </div> : <span style="clear:left;">'+myresults.totalMaleVoters+'</span> </li> ';
	}

	if(myresults.totalFemaleVoters==null){
		str +='<li><div style="width:68%;float:left;">Total Female Voters </div>: <span style="clear:left;">'+value+'</span> </li> ';
	}
	else{
	str +='<li> <div style="width:68%;float:left;">Total Female Voters </div>: <span style="clear:left;">'+myresults.totalFemaleVoters+'</span> </li> ';
	}
	
	str +='</ul></div>';
	str+='<div class="span9" style="margin-top:6px;">';
	str+='<table id="FamilyTable" class="table table-bordered table-hover"  style="margin-top: 15px; font-size: 13px;">';
	str+='<thead class="info"><tr>';
	str+='<th>Report</th><th>Voters Below 3</th><th>Voters Between 4-6</th><th>Voters Between 7-10</th><th>Above 10 Voters</th>';
	str+='</tr></thead><tbody>';
	str+='<tr>';
	str+='<th>No of Familes</th>';

	if(myresults.below3 != null)
		str+='<td>'+myresults.below3+'</td>';
	else
		str+='<td>'+0+'</td>';

	if(myresults.betwn4to6 != null)
		str+='<td>'+myresults.betwn4to6+'</td>';
	else
		str+='<td>'+0+'</td>';

	if(myresults.betwn7to10 != null)
		str+='<td>'+myresults.betwn7to10+'</td>';
	else
		str+='<td>'+0+'</td>';

	if(myresults.above10 != null)
		str+='<td>'+myresults.above10+'</td>';
	else
		str+='<td>'+0+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Familes %</th>';

	if(myresults.below3perc != null)
		str+='<td>'+myresults.below3perc+'%</td>';
	if(myresults.betwn4to6perc != null)
		str+='<td>'+myresults.betwn4to6perc+'%</td>';
	if(myresults.betwn7to10perc != null)
		str+='<td>'+myresults.betwn7to10perc+'%</td>';
	if(myresults.above10perc != null)
		str+='<td>'+myresults.above10perc+'%</td>';

	str+='<tr>';
	str+='</tbody></table>';
	str+='</div>';
	$("#impFamilesBasicDetails").html(str);
	
}

</script>
</head>
<body>
<div id="basicInfoAjaxDiv" align="center" style="margin-top: 100px; margin-bottom: 100px;display: none;">
<br>
<img alt="Processing Image" src="./images/icons/goldAjaxLoad.gif">
</div>
<div id='votersMainOuterDiv1' class="widget blue whitegloss" style="margin-right: auto; margin-left: auto;width: 96%;color:#000;position:relative;">
<h4 class="breadcrumb"  id="impFamiliesTitle" style="text-align: center;display: none;" ></h4>
	<div id="ImportantFamiliesDiv" style="border: 2px solid rgb(221, 221, 221); margin-top: -19px;display: none;">
	<div id ="impFamilesBasicDetails" style="margin-left: auto; margin-right: auto; width: 950px;"></div>
	</br>
	</div>
</div>
<script type="text/javascript">
getFamilies();
</script>
</body>
</html>