<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title></title>
	<meta name="" content="">
	<!-- Bootstrap -->
	
	<!-------PT-sans font---->
	<!-- <link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'> -->
	<link  rel="stylesheet" type="text/css" href="js/jquery.google.api/googleAPIStyles.css"/>
	
	<style>
	#fromDateId,#todateId{
	cursor: pointer;
	}
	#headings{
	color:#5B5B5B;
	font-variant: small-caps; 
	text-transform: none; 
	font-weight: 500; 
	height: 20px; 
	padding: 5px; 
	width: 918px;
	margin-bottom:0px;

	}
	.content_widget {background-color: #FFFFFF; border: 1px solid #E5E5E5; height: 100%; padding: 10px;}
	.m_topN21{margin-top:-21px;}
	.m_left0{margin-left: 0px !important;}
	
	.ui-multiselect {
    padding: 2px 0 2px 4px;
    text-align: left;
    width: 250px !important;
	height:33px !important;
	}
	
	.ui-multiselect-menu {
    display: none;
    padding: 3px;
    position: absolute;
    text-align: left;
    z-index: 10000;
	}
	
	.badge-50 , .label-50{
		background-color: rgba(158, 158, 158, 0.5);
	}
	
	.label-success-50, .badge-success-50 {
    background-color: #d3d3d3 !important;
    color: #333333 !important;}
	.tabel-bordered{border-color:3px solid #ffcc00;}
	.analysisResult thead tr th{background:#ffcc00;}
	.tabel tr, .tabel td {border-top: 1px solid #FFCC00;}
	.requiredFont {
    color: #FF0000;
    font-size: 18px;
	margin-left:3px;
}
	</style>
</head>
<body>

		<!----Container---->
		<div class="container">
			<!--------- Row-1 -------->
			<div class="row-fluid">
				<div class="span12 content_widget">
                  <div id="myResult1"><div id="ajaximg"><img id="" width="18" height="11" style="width: 150px; height: 15px;margin-top:100px;margin-left:400px;" src="images/icons/goldAjaxLoad.gif"></div></div>
				</div>	
			</div>
			<!--------- Row-1 End -------->
			</div><!----Container END---->
		    
			<!-----Footer---->
			<!--<div class="container- fluid">
				<footer>
					<p class="text-center">&copy; Telugudesham Party 2013</p>
				</footer>
			</div>-->
		
		<!------JS------>
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery.google.api/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	 <!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" /> -->
<link  rel="stylesheet" type="text/css" href="js/jquery.google.api/jquery-ui.css"/>
    <!-- <script src="http://code.jquery.com/jquery-1.8.2.js"></script> -->
<script type="text/javascript" src="js/jquery.google.api/jquery-1.8.2.js"></script>
    <!-- <script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script> -->
<script type="text/javascript" src="js/jquery.google.api/jquery-ui1.9.0.js"></script>
	
	<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	

	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
    
   

	<!-- YUI Dependency files (End) -->
	
	<script type="text/javascript">
	 var sourceCandId = '${sourceCandId}';
		var sourcePartyId = '${sourcePartyId}';
		var locationLvl = '${locationLvl}';
		var locationIds = '${locationIds}';
		var startDate = '${startDate}';
		var endDate = '${endDate}';
		var sourceType = '${sourceType}';
		var benifitsFor = '${benifitsFor}';
		var destiPartyId = '${destiPartyId}';
		var categoryId = '';
		var sourceId = '';
	function callAjax(jsObj,url)
	{

		var callback =
		{			
			success : function( o )
			{
				try
				{ 
					myResults = YAHOO.lang.JSON.parse(o.responseText); 
						buildAnalysisDetails(myResults,"whomPartysList","partyList","Party");	
					
				}
				catch(e)
				{   
				 $("#submitDataImg").hide();
				}  
			},
			scope : this,
			failure : function( o )
			{
				
			}
		};

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
	function buildAnalysisDetails(result,jsObj){
	    var str="";
		if(result != null && result.length > 0){
		 for(var i in result){
		   if(result[i].subList != null && result[i].subList.length > 0){
		     str+="<legend class='boxHeading m_top10'>"+result[i].name+" Wise Analysis </legend>";
			 str+="<table class='analysisResult table table-bordered table-striped table-hover'>";
					   str+="  <thead><tr>";
					   str+="    <th>"+result[i].name+"</th>";
					   str+="    <th>News Count</th>";
					   str+="  </tr></thead>";
					   str+="  <tbody>";
					for(var j in result[i].subList){
                          str+="<tr>";
						  str+="  <td>"+result[i].subList[j].name+"</td>";
						  if(result[i].subList[j].total > 0){
						    str+="  <td><a title='Click here to view news' href='javascript:{};' onclick='getNews("+result[i].subList[j].id+",\""+result[i].name+"\")' >"+result[i].subList[j].total+"</td>";
						  }else{
						    str+="  <td>"+result[i].subList[j].total+"</td>";
						  }
						  str+="</tr>";
                     }					
					   str+="  </tbody>";
					   str+=" </table>";
			}
		 }
		}
		  else{ 
		   
			  str+='<span style="margin-left:340px;font-weight:bold;">No News Exists To Analyse</span>';
			}
				$("#myResult1").html(str);
			}
	
	var jsObj = {
			task : "getAnalysedDataNew"	
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	    var url = "getAttributeWiseCountAction.action?sourceCandId="+sourceCandId+"&sourcePartyId="+sourcePartyId+"&locationLvl="+locationLvl+"&locationIds="+locationIds+"&startDate="+startDate+"&endDate="+endDate+"&sourceType="+sourceType+"&benifitsFor="+benifitsFor+"&destiPartyId="+destiPartyId;						
	    callAjax(jsObj,url);
	function getNews(id,attrType){
	   categoryId ='';
	   sourceId ='';
	   keywordId ='';
	   if(attrType == "Category"){
	     categoryId = id;
	   }else if(attrType == "Source"){
	     sourceId = id;
	   }else if(attrType == "Keyword"){
		 keywordId = id;
	   }
      var browser1 = window.open("getAllNewsAction.action?sourceCandId="+sourceCandId+"&sourcePartyId="+sourcePartyId+"&locationLvl="+locationLvl+"&locationIds="+locationIds+"&startDate="+startDate+"&endDate="+endDate+"&sourceType="+sourceType+"&benifitsFor="+benifitsFor+"&destiPartyId="+destiPartyId+"&categoryId="+categoryId+"&sourceId="+sourceId+"&keywordId="+keywordId,"viewNewsWindow","scrollbars=yes,height=600,width=1050,left=200,top=200");	
       browser1.focus();

}	
		
	</script>
</body>
</html>