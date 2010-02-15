<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

		var mandalId;

		function getDistrictsList(id)
		{
			var jsObj=
				{
						location:"STATE",
						locationId:id,
						task:"getDistricts"						
				}
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/hamletBoothMapperAjaxAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
		}

		function getMandalsList(id)
		{
			mandalId = id;
			var jsObj=
				{
						location:"DISTRICT",
						locationId:id,
						task:"getMandals"						
				}
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/hamletBoothMapperMandalAjaxAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
		}

		function getRevenueVillagesList(id)
		{
			var jsObj=
				{
						location:"REVENUE VILLAGES",
						locationId:id,
						task:"getRevenueVillages"						
				}
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/hamletBoothMapperAjaxAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
		}

		function getBoothAndHamlets(id, mandalId){

			alert("In Booths And Hamlets ?start?");
			
			var jsObj=
			{
					task:"getBoothsAndHamlets",
					locationId:id,
					mandalId:mandalId
			}

			alert("In Booths And Hamlets ?End?");
			
			var rparam = "task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/hamletBoothMapperFinalAjaxAction.action?"+rparam;
			callAjax(rparam, jsObj, url);
		}
		
		function callAjax(rparam,jsObj,url){
			var resultVO;			
			var callback = {			
		               success : function( o ) {
							try {												
									resultVO = YAHOO.lang.JSON.parse(o.responseText);										
									
									if(jsObj.task == "getDistricts")
									{								
										showDistricts(resultVO);				
									}
									if(jsObj.task == "getMandals")
									{										
										showMandals(resultVO);				
									}
									if(jsObj.task == "getRevenueVillages")
									{	
										showRevenueVillages(resultVO);				
									}
									if(jsOj.task == "getBoothsAndHamlets")
									{
										showBoothsAndHamlets(resultVO);
									}
									
							}catch (e) {   
								var ajaxImgSpanElmt = document.getElementById("ajaxImgSpan");
								ajaxImgSpanElmt.style.display = 'none';
							}  
		               },
		               scope : this,
		               failure : function( o ) {
		                			alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);			
		}

	  function showDistricts(resultVO){
		var selectContentEl = document.getElementById("districtSelect");
		var selectLableEl = document.getElementById("districtLable");
		var strContent = '';
		strContent+='<select onchange="getMandalsList(this.options[this.selectedIndex].value)">';
		for(var i in resultVO)
		{			
			strContent+='<option value='+resultVO[i].id+'>'+resultVO[i].name+'</option>';
		}
		strContent+='</select>';

		var strLable = 'District:';

		selectLableEl.innerHTML = strLable;
		selectContentEl.innerHTML = strContent;
	  }

	  function showMandals(resultVO){
		var selectContentEl = document.getElementById("mandalSelect");
		var selectLableEl = document.getElementById("mandalLable");
		var strContent = '';
		strContent+='<select onchange="getRevenueVillagesList(this.options[this.selectedIndex].value)">';
		for(var i in resultVO)
		{
			console.log(resultVO[i].id);
			strContent+='<option value='+resultVO[i].id+'>'+resultVO[i].name+'</option>';
		}
		strContent+='</select>';

		var strLable = 'Mandal:';

		selectLableEl.innerHTML = strLable;
		selectContentEl.innerHTML = strContent;
		
	  }

	  function showRevenueVillages(resultVO){
		var selectContentEl = document.getElementById("revenueVillageSelect");
		var selectLableEl = document.getElementById("revenueVillageLable");
		var strContent = '';
		strContent+='<select onchange="getBoothAndHamlets(this.options[this.selectedIndex].value, mandalId)">';
		for(var i in resultVO)
		{
			console.log(resultVO[i].id);
			strContent+='<option value='+resultVO[i].id+'>'+resultVO[i].name+'</option>';
		}
		strContent+='</select>';

		var strLable = 'Revenue Village:';

		selectLableEl.innerHTML = strLable;
		selectContentEl.innerHTML = strContent;
	  }

	  function showBoothsAndHamlets(resultVO){
			alert("In Booths And Hamlets ?show?");
	  }
	  
	</script>
</head>
<body>	
	<table>
		<tr>
			<td align = "right">State:</td>
			<td><s:select id="stateId" theme="simple" list="result" listKey="id" listValue="name" onchange="getDistrictsList(this.value)"/></td>
		</tr>
		<tr>
			<td align = "right"><div id="districtLable" /></td>
			<td><div id="districtSelect" /></td>
		</tr>
		<tr>
			<td align = "right"><div id="mandalLable" /></td>
			<td><div id="mandalSelect" /></td>
		</tr>
		<tr>
			<td align = "right"><div id="revenueVillageLable" /></td>
			<td><div id="revenueVillageSelect" /></td>
		</tr>
		
	</table>
	
</body>
</html>