<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>


<title>Voters analysis</title>
<style>
.pull-right{
	 margin-top: -15px;
}

.form-horizontal label{  display:block; font:12px Arial;
    margin-bottom:5px;padding-top:5px;cursor:hand;cursor:pointer;}
	.form-horizontal .radio
	{
	font-size: 13px;
    font-weight: normal;
    margin-top:-1px;
	margin-right:8px;
	}
.buttonStyle {
	-moz-border-radius:5px 5px 5px 5px;
    background: none repeat scroll 0 0 #0063DC;
    border: medium none;
    border-radius: 4px 4px 4px 4px;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
	height:24px;
}
 #votersouterDiv{
	 margin-left: auto;
    margin-right: auto;
    width: 980px;
 }

 fieldset {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border: 3px solid #CFD6DF;
   
    margin-bottom: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
}
p {
    color: #333333;
    font-size: 13px;
    line-height: 18px;
}
.divInfo
{
 background-color:#FFFFFF;
 border-bottom: 1px solid #B3C1CE;
 border-left: 1px solid #B3C1CE;
 border-right: 1px solid #B3C1CE;
 padding:5px;
}
</style>

<script type="text/javascript">
var Localization = { <%
			
			ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
			
			String ACONSTITUENCY = rb.getString("ACONSTITUENCY");
			String locationAlert = rb.getString("validLocation");
		  %> }
var locationDetails={
				constituencyArr:[],
					};

<c:forEach var="constituency" items="${constituencyList}">
	var ob={
			id:'${constituency.id}',
			value:'${constituency.name}'
			};
locationDetails.constituencyArr.push(ob);
</c:forEach>
/*function buildOuterView(){
var divEle = document.getElementById("votersouterDiv");	
	var str='';
	str+='<fieldset>';
	str+='<div style="color:#707070;font-weight:bold;font-size:12px;">Please select from the following list boxes to view detailed statistics by Assmbly/mandal/Panchayat/Polling station level</div>';

	str+='<P >Fields marked with <font class="requiredFont"> * </font> are mandatory</P>';
	str+='<div id="locationAlertMsg" align="left"></div>';
	str+='<div id="ConstituencyDiv" style=" margin-left: 200px;margin-top: 5px;width: 290px;">';
	str +='<label><input id="Assembly" type="radio" checked="true" value="assembly"  name="radioEle"/>&nbsp;Assembly&nbsp;';
	str+='<select id="constituencyList" class="selectWidth" name="constituencyList" onchange="getMandalList();">';
		for(var i in locationDetails.constituencyArr)
		{
			str+='<option value='+locationDetails.constituencyArr[i].id+'>'+locationDetails.constituencyArr[i].value+'</option>';
		} 
		str+='</select>';
	
	str+='</div>';
	str+='<div id="mandalDiv" style="display:block; width: 507px;float:right;margin-top: -31px;">';
	str+='<input id="mandal" type="radio" value="mandal"  name="radioEle" onclick="showMandalDiv();getMandalList();"/>&nbsp;Mandal&nbsp;';
	
	str+='<select id="mandalField" class="selectWidth" name="state" style="display:none;" onchange="getVotersForConstituency();getPanchayatList();getVotersBasicInfo(this.options[this.selectedIndex].value);"></select>';
	str+='</div>';
	str+='</fieldset>';
	divEle.innerHTML= str;

}
*/
function buildOuterView()
	{

		var divEle = document.getElementById("votersouterDiv");	
		var str='';
		
		str+='<fieldset>';
		str+='<div style="color:#707070;font-weight:bold;font-size:12px;">Please select from the following list boxes to view detailed statistics by Assmbly/mandal/Panchayat/Polling station level</div>';
		str+='<P >Fields marked with <font class="requiredFont"> * </font> are mandatory</P>';
		str+='<div id="locationAlertMsg" align="left"></div>';
		str +='<div style="margin-left: 200px;" class="control-group form-horizontal" >';
		str+='<table>';
		str+='<tr>';
		str+='<td>';

		str +='<label><input id="Assembly" type="radio" checked="true"  class="radio" value="assembly"  name="radioEle"/>Assembly</label>';
		str+='</td>';
		str+='<td>';
		str+='<div id="ConstituencyDiv" style="width:166px;margin-top:5px;">';
		str+='<table>';
		str+='<tr>';
		
		str+='<td><select id="constituencyList" class="selectWidth" name="constituencyList" onchange="getMandalList();">';
		for(var i in locationDetails.constituencyArr)
		{
			str+='<option value='+locationDetails.constituencyArr[i].id+'>'+locationDetails.constituencyArr[i].value+'</option>';
		} 
		str+='</select></td>';
		str+='</td></tr></table></div>';
		str+='</td>';
		
		str+='<td>';
		
		str+='<label><input id="mandal" type="radio" value="mandal"  name="radioEle"  class="radio" onclick="showMandalDiv();getMandalList();"/>Mandal</label>';
		str+='</td></tr></table>';
		
		str+='<div id="mandalDiv" style="display:none; width: 444px;float:right;margin-top: -36px;">';
		str+='<table><tr>';
		
		str+='<td><select id="mandalField" class="selectWidth" name="state" onchange="getVotersForConstituency();getPanchayatList();getVotersBasicInfo(this.options[this.selectedIndex].value);"></select>';
		str+='</td></tr>';
		str+='</table>';
		str+='</div>';
		str +='</div>';
		divEle.innerHTML= str;
		
	}

	function showMandalDiv()
	{
		/*var mandalField = document.getElementById("mandalField");
		removeSelectElements(mandalField);*/
		
		document.getElementById("mandalDiv").style.display = "block";
	}

	function showImportantFamiliesDiv()
	{
		document.getElementById('votersMainOuterDiv1').style.display='block';
		document.getElementById('votersMainOuterDiv2').style.display='none';
		document.getElementById('votersMainOuterDiv3').style.display='none';
		$("#importantFamiliesId").css({"background":"none repeat scroll 0 0 #F61D50"});
		$("#localCaststatId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#votersId").css({"background":"none repeat scroll 0 0 #0063DC"});
	}
	
	function showLocalCastDiv()
	{
		showLocalCastDiv1();
		document.getElementById('votersMainOuterDiv1').style.display='none';
		document.getElementById('votersMainOuterDiv2').style.display='block';
		document.getElementById('votersMainOuterDiv3').style.display='none';
		$("#importantFamiliesId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#localCaststatId").css({"background":"none repeat scroll 0 0 #F61D50"});
		$("#votersId").css({"background":"none repeat scroll 0 0 #0063DC"});
		showPanchayatDiv();
		getPanchayatList();
	}

	function showVotersDiv()
	{
		document.getElementById('votersMainOuterDiv1').style.display='none';
		document.getElementById('votersMainOuterDiv2').style.display='none';
		document.getElementById('votersMainOuterDiv3').style.display='block';
		$("#importantFamiliesId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#localCaststatId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#votersId").css({"background":"none repeat scroll 0 0 #F61D50"});
	}


	function showPollingStationDiv()
	{
		var pollingField = document.getElementById("pollingStationField");
		//removeSelectElements(pollingField);
		
		document.getElementById("polingStationDiv").style.display = "block";
		document.getElementById("panchayatDiv").style.display = "none";

	}
	function showPanchayatDiv()
	{
		
		var panchayatField = document.getElementById("panchayatField");
		//removeSelectElements(panchayatField);
		
		document.getElementById("polingStationDiv").style.display = "none";
		document.getElementById("panchayatDiv").style.display = "block";

	}
	function showLocalCastDiv1()
	{
		var str='';
		var divEle =document.getElementById('LocalCastDiv');
		str +='<div style="margin-left: 200px;" class="control-group form-horizontal" >';
		str+='<table>';
		str+='<tr>';
		str+='<td>';

		str+='<label><input id="panchayat" type="radio" value="panchayat"  name="radioEle1" checked="true" onclick="showPanchayatDiv();getPanchayatList();"/>Panchayat</label>';
		str+='</td>';
		str+='<td>';
		str+='<div id="panchayatDiv" style="display:block; width: 167px;margin-top:0px;">';
		str+='<table><tr>';
		
		str+='<td><select id="panchayatField" class="selectWidth" name="state" onchange="getVotersForHamlet(this.name,this.options[this.selectedIndex].value,this.options[this.selectedIndex].text);getVotersBasicInfoForPanchayat(this.options[this.selectedIndex].value);"></select>';
		str+='</td></tr>';
		str+='</table>';
		str+='</div>';
		str+='</td>';
		str+='<td>';
		
		str+='<label><input id="pollingstation" type="radio" value="village"  name="radioEle1"  onclick="showPollingStationDiv();getPanchayatList();"/>Polling Station</label>';
		str+='</td>';
		
		str+='</tr>';
		str+='</table>';
		str+='</div>';
		
		
		str+='<div id="polingStationDiv" style="display:block; width: 601px;float:right;margin-top:-41px;">';
		str+='<table><tr>';
		
		str+='<td><select id="pollingStationField" class="selectWidth" name="state" onchange="getVotersForHamlet(this.name,this.options[this.selectedIndex].value,this.options[this.selectedIndex].text);getVotersBasicInfoForPanchayat(this.options[this.selectedIndex].value);"></select></td></tr>';
		str+='</table>';
		str+='</div>';
		str+='</div>';
		divEle.innerHTML = str;


	}
	function getMandalList()
	{
		
		var assemblyradioEle =document.getElementById('Assembly'); 
		var constituencyID = document.getElementById("constituencyList");
		var name=constituencyID.options[constituencyID.selectedIndex].name;
		var value=constituencyID.options[constituencyID.selectedIndex].value;
		var choice=false;
		var locationAlertEl =  document.getElementById("locationAlertMsg");
		
		if(value=='0')
		{
		
			assemblyradioEle.checked = true;
			return;
		}
		
	
		var jsObj=
			{
					
					selected:value,
					task:"getMandalList"
			};
		


		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		
		callAjax(jsObj,url);
	}


	function getPanchayatList()
	{	

		var panchayatDiv = document.getElementById("localCastStatsTabContent_body1");
		var votersPanchayatDiv=document.getElementById("votersByLocationTabContent1");
		var impPanchayatDiv=document.getElementById("importantVotersTabContent1");
		var mandalId=document.getElementById("mandalField");
		var name=mandalId.options[mandalId.selectedIndex].name;
		var value1=mandalId.options[mandalId.selectedIndex].value;
		var value = value1.substring(1);
		var choice=false;
		
		var pollingstationradioEle = document.getElementById('pollingstation');
		var panchayatradioEle =document.getElementById('panchayat'); 
		var imppollingstationradioEle = document.getElementById('imppollingstation');
		var imppanchayatradioEle =document.getElementById('imppanchayat'); 
		var voterspollingstationradioEle = document.getElementById('voterspollingstation');
		var voterspanchayatradioEle =document.getElementById('voterspanchayat'); 
		var constituencyFieldEl = document.getElementById("constituencyList");
		var constituencyFieldElOptions = constituencyFieldEl.options;
		var alertEl = document.getElementById("locationAlertMsg");
		alertEl.innerHTML = '';
		
		selectname = mandalField.options[mandalField.selectedIndex].text;
		flag= selectname.search("MUNCIPALITY");
		if(flag != -1)
		{
			panchayatDiv.style.display='none';
			votersPanchayatDiv.style.display='none';
			impPanchayatDiv.style.display='none';
		}
		
		else if(value == 0)
		{
			alertEl.innerHTML ='<P><%=locationAlert%></P>';
			return;
		}
		
		
			if(pollingstationradioEle.checked)
		{
			checkedele = "pollingstation";
		}
		else if(panchayatradioEle.checked)
		{
			checkedele = "panchayat";
		}
		else if(imppanchayatradioEle.checked)
		{
			checkedele = "panchayat";
		}

		else if(imppollingstationradioEle.checked)
		{
			checkedele = "pollingstation";
		}
		else if(voterspanchayatradioEle.checked)
		{
			checkedele = "panchayat";
		}

		else if(voterspollingstationradioEle.checked)
		{
			checkedele = "pollingstation";
		}
		
		
		var jsObj=
			{
					
				selected:value,
				checkedele:checkedele,
				task:"getPanchayat"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatByConstituencyAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	

		function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
									if(jsObj.task == "getMandalList")
								{
										buildMandalList(myResults);
								}

								else if(jsObj.task == "getPanchayat")
									{
										buildPanchayatData(myResults);
									}

							}catch (e) {   
								
							   	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
 	}

	function buildMandalList(results)
	{
		var selectedElmt=document.getElementById("mandalField");
		removeSelectElements(selectedElmt);
		for(var val in results)
		{
			var opElmt = document.createElement('option');
			opElmt.value=results[val].id;
			opElmt.text=results[val].name;

			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}	
		}
	}


function buildPanchayatData(results)
	{
		
		var pollingstationradioEle = document.getElementById('pollingstation');
		var panchayatradioEle =document.getElementById('panchayat'); 
		var imppollingstationradioEle = document.getElementById('imppollingstation');
		var imppanchayatradioEle =document.getElementById('imppanchayat'); 

		var voterspollingstationradioEle = document.getElementById('voterspollingstation');
		var voterspanchayatradioEle =document.getElementById('voterspanchayat'); 
			if(pollingstationradioEle.checked)
		{
		var phamletFieldEl = document.getElementById("pollingStationField");
		}
			else if(panchayatradioEle.checked)
		{
		var phamletFieldEl = document.getElementById("panchayatField");
		}

		else if(imppollingstationradioEle.checked)
		{
		var phamletFieldEl = document.getElementById("imppollingStationField");
		}
		else if(imppanchayatradioEle.checked)
		{
		var phamletFieldEl = document.getElementById("imppanchayatField");
		}
		else if(voterspollingstationradioEle.checked)
		{
		var phamletFieldEl = document.getElementById("voterspollingStationField");
		}
		else if(voterspanchayatradioEle.checked)
		{
		var phamletFieldEl = document.getElementById("voterspanchayatField");
		}
		removeSelectElements(phamletFieldEl);
		for(var i in results)
		{
			if(results[i] == null)
				continue;
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
		
			try
				{
				phamletFieldEl.add(opElmt,null); // standards compliant
				}
			catch(ex)
				{
				phamletFieldEl.add(opElmt); // IE only
				}
		}

	}
	function removeSelectElements(selectedElmt)
	{
		var len = selectedElmt.length;
		for(var i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}
	}
</script>
</head>
<body>
<br><br>

<div id="votersouterDiv" ></div>
<br><br>
<div id='votersMainOuterDiv'>
<!-- heading -->
<div id='votersHeaderDiv'>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tr>
		  <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg">
		    </td>
		    <td width="98%">
		       <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
		         <span style="text-decoration: none;" class="headerLabelSpan2"><center>Constituency Management Admin</center></span>
		       </div>
		    </td>
		   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
		   </td>
		</tr>
	</table>
</div>

<div id='votersMainInnerDiv' class="divInfo" style="padding:10px;">
	<div id="profileManagementDiv">
		<table class="statusData_table" width="100%">	
		
          <tr>
			<td class="statusData_table_data" width="100%" style="padding-top:23px">
				<table>
				  <tr>
			    	<td style="padding-left:109px"><b><input type="button" class="buttonStyle" value="Important Families" id="importantFamiliesId" style="height:24px;" onclick="showImportantFamiliesDiv()"></b></td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Local Cast Statistics" 
					 id="localCaststatId" style="height:24px;" onclick="showLocalCastDiv()"></b> </td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Voters" id="votersId" style="height:24px;" onclick="showVotersDiv()"></b> </td>
					
				  </tr>
				</table>
			</td>
		 </tr>
		</table>
	
</div>
<!-- header End -->
</div>



<!-- for  body 1 start    result  -->
<HR>
<div id='votersMainOuterDiv1' style="display:none">
	<div id='votersHeaderDiv1'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Important Families</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>
	</div>

	<div id='ImportantFamiliesDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 1 end    result  -->




<!-- for  body 2 start    result  -->

<div id='votersMainOuterDiv2' style="display:none">
	<div id='votersHeaderDiv2'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Local Cast statistics</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>
	</div>

	<div id='LocalCastDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 2 end    result  -->



<!-- for  body3 start    result  -->

<div id='votersMainOuterDiv3' style="display:none">
	<div id='votersHeaderDiv3'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Voters Info</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>
	</div>

	<div id='votersDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 3 end    result  -->



</div>

<script type="text/javascript">
 buildOuterView();
 
 showImportantFamiliesDiv();

</script>
</body>
</html>