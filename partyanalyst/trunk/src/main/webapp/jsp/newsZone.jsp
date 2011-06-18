<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>News Zone</title>

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
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	

	<!-- YUI Dependency files (End) -->
	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script src="http://www.google.com/uds/api?file=uds.js&v=1.0"
    type="text/javascript"></script>
<link href="http://www.google.com/uds/css/gsearch.css"
    rel="stylesheet" type="text/css"/>
<script src="http://www.google.com/uds/solutions/videobar/gsvideobar.js"
    type="text/javascript"></script>
		
<link href="http://www.google.com/uds/solutions/videobar/gsvideobar.css"
    rel="stylesheet" type="text/css"/>
<script type="text/javascript">
		google.load("elements", "1", {packages : ["newsshow"]});

</script>
	<style type="text/css">
				
		.yui-skin-sam .yui-navset .yui-nav, .yui-skin-sam .yui-navset .yui-navset-top .yui-nav 
		{
			text-align:left;
			font-weight:bold;
		}
		.yui-skin-sam .yui-dt th
		{
			background-image:url(images/YUI-images/sprite.png);
			width:100%;
		}
		
		#yui-dt0-th-Categorize
		{
			background-color:blue;
		}

		
		.selectWidth
		{
			width:160px;
		}
		
		
		
		#distEPapersTabContent_body yui-skin-sam yui-dt-sortable
		{
			width:100px;
		}
		
		#distEPapersTabContent_body
		{
			width:50%;
		}		
		#pendingTabContentDiv
		{
			width: 900px;
			overflow-x:auto;
		}
		
		.yui-skin-sam .yui-layout .yui-layout-unit div.yui-layout-bd 
		{			
			border:none;
		}
		
		a:hover {
			text-decoration:underline;
		}
		a {
			color:#0D5CAB;
			text-decoration:none;
		} 	
		
		#leftLayoutMain a
		{
			margin-left:21px;			
		}
					
		.ygtvlabel, .ygtvlabel:link, .ygtvlabel:visited, .ygtvlabel:hover {
			color:#808080;
			cursor:pointer;
			font-weight:bold;
			margin-left:1px;
			text-decoration:none;
		}
		.ygtvlabel, .ygtvlabel:link, .ygtvlabel:visited, .ygtvlabel:hover {
			cursor:pointer;
			margin-left:2px;
			text-decoration:none;
			background: #ECF1F5;
		}
		.section_title {
			border-bottom:1px dotted #B9B9B9;
			color:#0D5CAB;
			font-size:14px;
			font-weight:bold;
			margin-bottom:5px;
			padding:5px 0;
			text-transform:uppercase;
		}
		
		
		.yui-skin-sam .yui-panel {
				background:#EFF3F7 none repeat scroll 0 0;
				border:1px solid #96B4D3;
				position:relative;
				z-index:1;
		}
		.yui-skin-sam .yui-layout .yui-layout-unit div.yui-layout-bd {
				background:none;
				border-color:none;
				border-style:none;
				text-align:center;
				padding:4px;
		}
		.yui-skin-sam .yui-panel .hd {
				background:transparent url(images/icons/aqua-hd-bg.gif) repeat-x scroll 0 0;
				color:#628C2A;
				font-size:93%;
				font-weight:bold;
				line-height:2;
				padding:0 10px;
				text-align:center;
		}
		fieldset {
			border:4px solid #CFD6DF;
			margin-bottom:10px;
			padding:10px;	
		}
		legend {
			background-color:#567AAF;
			color:#FFFFFF;
			font-size:12px;
			padding:5px;
			margin: 10px;
		}
		.requiredFont
		{
			color:red;
			margin-left:5px;
		}
		.button {
			background:none repeat scroll 0 0 #335291;
			color:#FFFFFF;
			font-weight:bold;
			padding:5px;
		}
		.confirmationMessage  {
			color:green;
			font-weight:bold;
			padding:5px;
			text-align:left;
		}
		.linkButton {
			background-image:url("images/icons/electionResultsReport/linkBtn.png");
			border-left:1px solid #9999CC;
			border-right:1px solid #9999CC;
			color:#FFFFFF;
			font-size:12px;
			font-weight:bold;
			margin-left:10px;
			padding:5px;
			text-decoration:none;
		}
		
		
	</style>

<script type="text/javascript">



var Localization = { <%
	ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
	String mainEdition = rb.getString("mainEdition");
			String subEdition = rb.getString("subEdition");
			String language = rb.getString("language");
%>}

var problemsMainObj={	
							probTypesArr:[],
							problemSourcesArr:[],
							problemRegionScopeArr:[],	
							newProblemsArr:[],
							classifiedProblemsArr:[],
							assignedProblemsArr:[],
							progressedProblemsArr:[],
							pendingProblemArr:[],
							fixedProblemsArr:[],
							probConcDeptsArr:[]
						};

	var locationDetails={
							stateArr:[],
							districtArr:[],
							constituencyArr:[],
							mandalArr:[],
							villageArr:[],
							hamletArr:[],
							allDistrictsByStateArr:[],
							parliamentConstituency:[]
						};
	var constMgmtMainObj={
							votersArray:[],
							castStatsArray:[],
							totalvotersStatsArray:[],
							votersByHouseNoArray:[],
							localLeadersArray:[],
							localPoliticalChangesArray:[],
							localProblemsArr:[],
							totalLeadersArr:[],
							electedMandalLeadersArr:[]
						};
	var distPapersObj={
							ePapersArray:[]
						};	
	

	function callAjax(param,jsObj,url){

		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);						
									
									if(jsObj.task == "distPapersUrl")
									{
										
										showNewsPapersLinks(myResults);
										buildDistrictsByStateSelectOption(myResults);
									} 
									else if(jsObj.task=="getSelectedDistPaper")
									{
										updateSelectedDistUrls(myResults);
									} 
									
							}catch (e) {   
								alert("result not found");
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
 	}
	
	function handleDistPapersTabClick()
	 	 {
	   
		var jsObj=
		  {
			task:"distPapersUrl"
		 };
	
	var param ="task="+YAHOO.lang.JSON.stringify(jsObj);						
	var url = "<%=request.getContextPath()%>/distPapersAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
	}
	


function showNewsPapersLinks(results)
	{	
		
		assignToEPapersURL = new Array();
		var newsPaperURL = results.EPapersURLsVO;
		for(var i in newsPaperURL)
		{
			var distEditionUrl;
			var mainEditionUrl = ""+newsPaperURL[i].mainUrl;
			var paperName = ""+newsPaperURL[i].paperName;
			var distName; 
			var image = newsPaperURL[i].image;
			if(newsPaperURL[i].districtName == null)
			{
				distName = "";
			} else distName = ""+newsPaperURL[i].districtName;
			if(newsPaperURL[i].epaperUrl == null)
			{
				distEditionUrl = "";
			} else distEditionUrl = ""+newsPaperURL[i].epaperUrl;
			
			var epapersObj={
					mainEdition: '<A href="'+mainEditionUrl+'" target="_blank" title="Click To Visit"><Img src="<%=request.getContextPath()%>/images/icons/'+image+'" border="none"/></A>',
					distEdition: '<A href="'+distEditionUrl+'" target="_blank">'+distName+'</A>',
					language: newsPaperURL[i].language 								 
			};
			
		assignToEPapersURL.push(epapersObj);	
		distPapersObj.ePapersArray = assignToEPapersURL;
		}
		
		buildEPapersURLDataTable();		
	}

	function buildDistrictsByStateSelectOption(results)
	{	
		
		var distByState = results.districtsList;
		var selectedElmt = document.getElementById("allDistrictsField");
		
		for(var val in distByState)
		{			
			var opElmt=document.createElement('option');
			opElmt.value=distByState[val].id;
			opElmt.text=distByState[val].name;
			
			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}			
		}
		selectedElmt.style.display='block';
	}
	
function updateSelectedDistUrls(results)
	{
		
		assignToEPapersURL = new Array();
		var newsPaperURL = results.EPapersURLsVO;
		for(var i in newsPaperURL)
		{
			var distEditionUrl = ""+newsPaperURL[i].epaperUrl;
			var mainEditionUrl = ""+newsPaperURL[i].mainUrl;
			var paperName = ""+newsPaperURL[i].paperName;
			var distName = ""+newsPaperURL[i].districtName;
			var image = newsPaperURL[i].image;
			var epapersObj={
				mainEdition: '<A href="'+mainEditionUrl+'" target="_blank"><Img src="<%=request.getContextPath()%>/images/icons/'+image+'" border="none"/></A>',
				distEdition: '<A href="'+distEditionUrl+'" target="_blank">'+distName+'</A>',
				language: newsPaperURL[i].language 								 
			};
			
		assignToEPapersURL.push(epapersObj);
		distPapersObj.ePapersArray = assignToEPapersURL;
		}
		
		
		if(ePapersDataTable)
			ePapersDataTable.destroy();
		buildEPapersURLDataTable();
	}



function buildOuterTabView()
	{
		var distEPapersTabContent='<div id="distEPapersTabContent">'; 
		distEPapersTabContent+='<br>';
		distEPapersTabContent+='<div id="distEPapersTabContent_header" align="center">';
		distEPapersTabContent+='Select a District To Access Other  District Editions: <select id="allDistrictsField" class="selectWidth" name="allDistrictsField" onchange="getNewPapersForSelectedDist(this.name,this.options[this.selectedIndex].value)">';
		for(var i in locationDetails.allDistrictsByStateArr)
		{
			distEPapersTabContent+='<option value='+locationDetails.allDistrictsByStateArr[i].id+'>'+locationDetails.allDistrictsByStateArr[i].value+'</option>';
		}
		distEPapersTabContent+='</select>';
		distEPapersTabContent+='</div>';
		distEPapersTabContent+='<br>';
		distEPapersTabContent+='<div id="distEPapersTabContent_body class="yui-skin-sam"></div>';
		distEPapersTabContent+='<div id="distEPapersTabContent_footer"></div>';
		distEPapersTabContent+='</div>';
			
		}


	function buildEPapersURLDataTable()
	{	
		
		var ePapersUrlColumnDefs = [
								{key: "mainEdition", label: "<%=mainEdition%>", sortable:true},		
		              	 	    {key: "distEdition", label: "<%=subEdition%>", sortable:true},
		              	 	    {key: "language", label: "<%=language%>", sortable:true}
		              	 	    ];                	 	    

		var ePapersDataSource = new YAHOO.util.DataSource(distPapersObj.ePapersArray); 
		ePapersDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		ePapersDataSource.responseSchema = {
                fields: ["mainEdition","distEdition", "language"] 
        		};
		ePapersDataTable = new YAHOO.widget.DataTable("distEPapersTabContent_body", ePapersUrlColumnDefs, ePapersDataSource);
		   	
	}

function getNewPapersForSelectedDist(name, value)
	{
		var jsObj=
		{
				selectName:name,
				selected:value,
				task:"getSelectedDistPaper"
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
	var url = "<%=request.getContextPath()%>/distPapersAjaxAction.action?"+rparam;
	callAjax(rparam,jsObj,url);
	}

function buildCandidateNews()
{
	var options = {
		"format" : "300x250",
		"queryList" : [
			  {
				"title" : 'Elections',
				"q" : "elections news, India"
			  }
		 ],
		"linkTarget" : "_blank"
	  }
	
	var content = document.getElementById('newsDiv');
	content.innerHTML = '';
	
	var newsShow = new google.elements.NewsShow(content, options);
}

function buildCandidateNews1()
{
	var options = {
		"format" : "300x250",
		"queryList" : [
			  {
				"title" : 'Parties',
				"q" : "Political parties, India"
			  }
		 ],
		"linkTarget" : "_blank"
	  }
	
	var content = document.getElementById('newsDiv1');
	content.innerHTML = '';
	
	var newsShow = new google.elements.NewsShow(content, options);
}


function buildCandidateNews2()
{
	var options = {
		"format" : "300x250",
		"queryList" : [
			  {
				"title" : 'Leaders',
				"q" : "Political leaders, India"
			  }
		 ],
		"linkTarget" : "_blank"
	  }
	
	var content = document.getElementById('newsDiv2');
	content.innerHTML = '';
	
	var newsShow = new google.elements.NewsShow(content, options);
}

function buildCandidateVideoGallery()
{
	

	var value="http://www.youtube.com/v/8jhiIWiCch8?enablejsapi=1&playerapiid=ytplayer&rel=1&color1=0x2b405b&color2=0x6b8ab6&border=1&fs=1";
	var params = { allowScriptAccess: "always" };	 
	var atts = { id: "myytplayer" };
	swfobject.embedSWF(value, "videoBarOne", "500", "300", "15", null, null, params,atts);
	var elmtId = document.getElementById( "videoBarOne2");
  
}

function LoadVideoBar()
{
	var vbl;
	var vbr;

	var searchString1 = 'anna hazare, baba ramdev, 2g scam';
		

	var options = {
	  largeResultSet : false,
		  horizontal:true
		  
			}
	vbl = new GSvideoBar(
				document.getElementById("videoBarOne"),
				document.getElementById("ytVideoPlayer"),
				options
				);
	
	vbl.execute(searchString1);
	
}

function LoadVideoBar1()
{
	var vbl;
	var vbr;

	var searchString1 = 'ASSEMBLY ELECTIONS IN ANDHRPA PRADESH, TAMILNADU, KERALA, ASSAM, WEST BENGAL, KARNATAKA';
		

	var options = {
	  largeResultSet : false,
		  horizontal:true
		  
			}
	vbl = new GSvideoBar(
				document.getElementById("videoBarOne1"),
				document.getElementById("ytVideoPlayer1"),
				options
				);
	

	
	vbl.execute(searchString1);
	
}


function LoadVideoBar2()
{
	var vbl;
	var vbr;

	var searchString1 = 'tv9 telugu news, ndtv news';
		

	var options = {
	  largeResultSet :false,
		  horizontal:true
		  
			}
	vbl = new GSvideoBar(
				document.getElementById("videoBarOne2"),
				document.getElementById("ytVideoPlayer2"),
				options
				);
	

	
	vbl.execute(searchString1);
	
}

function onYouTubePlayerReady(playerId)
{
	var ytplayer = document.getElementById("myytplayer");
	//ytplayer.cueVideoById(videoId:, startSeconds:Number, suggestedQuality:String):Void
}


</script>

</head>
<body>
<div>
	<table>
	<tr>
		<td><div style="padding:3px;width:33%;" id="ytVideoPlayer"></div></td>
		<td><div style="padding:3px;width:33%;" id="ytVideoPlayer1"></div></td>
		<td><div style="padding:3px;width:33%;" id="ytVideoPlayer2"></div></td>
	</tr>
	</table>
</div>
<div>
<table>
	<tr>
	<td><div id="newsDiv" style="width:300px;height:206px;"></div></td>
		<td><div id="newsDiv1" style="top:-40px;width:300px;height:206px;"></div></td>
		<td><div id="newsDiv2"  style="top:-40px;width:300px;height:206px;"></div></td>
	</tr><tr></tr>
</table>
</div><BR><BR>

<div>
<table border="2" valign="top" width="95%">
	<tr>
		<td valign="top" width="200">
			<table width="190"><tr><td>
			<center><h2>E-Papers</h2><br>
			<div><select id="allDistrictsField" class="selectWidth" name="allDistrictsField" onchange="getNewPapersForSelectedDist(name,options[selectedIndex].value)"></select></center>
			</div><br>
			<div id="distEPapersTabContent_head" class="yui-skin-sam">
			<div id="distEPapersTabContent_body"></div>
			</div>
			</td></tr></table>
		</td>
		<td colspan="3" align="center">
			<table border="2">
			<tr>
				<td style="font-family:sans-serif;font-size:large">Anti Corruption, Scams</td>
			</tr>
			
			<tr>
				<td><div style="padding:12px;" id="videoBarOne"></div></td>
			</tr>

			<tr>
				<td style="font-family:sans-serif;font-size:large">Politics</td>
			</tr>

			<tr>
				<td><div style="padding:12px;" id="videoBarOne2"></div></td>
			</tr>
			
			<tr>
				<td style="font-family:sans-serif;font-size:large">Elections</td>
			</tr>
			
			<tr>
				<td><div style="padding:12px;" id="videoBarOne1"></div></td>
			</tr>

			</table>
		</td>
	</tr>
</table>

</div>	


<script type="text/javascript">
	buildCandidateNews();
	handleDistPapersTabClick();
	buildCandidateNews1();
	buildCandidateNews2();
	LoadVideoBar();
	LoadVideoBar1();
	LoadVideoBar2();
</script>

</body>

</html>