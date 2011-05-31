
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">

<title>Guest Articels Submission</title>
<style type="text/css">

#footerLinksdata_main
{
	padding:10px;
}
#footerLinksdata_body
{
	text-align:left;
}

.sectionContent_main
{
	margin: 10px 0;
}

.sectionContent_head
{
	width:150px;
	background: url("http://yui.yahooapis.com/2.8.2r1/build/assets/skins/sam/sprite.png") repeat-x scroll 0 0 transparent;
	border-left: 1px solid #ADADAD;
	border-right: 1px solid #ADADAD;
	border-top: 1px solid #ADADAD;
	-moz-border-radius-topleft: 4px;
	-moz-border-radius-topright: 4px;	
	padding: 6px;
	width: 17%;
	color: #109AEB;
    font-weight: bold;
	font-size: 12px;
}
.sectionContent_body
{
	padding:5px;
	border-top: 1px solid #ADADAD;
	background-color: #ECF5FA;
}

.sectionContent_body_linkHead,.sectionContent_body_linkBody
{
	margin:5px 0px 5px 0px;
}

.sectionContent_body_linkBody
{
	color: #2C353C;
    font-size: 12px;
    line-height: 18px;
    padding-left: 18px;
    text-align: justify;
}

.sectionContent_body_linkMain
{
	margin: 15px 0;
}

.spacerLine
{
	border: 1px solid #D8E3E9;
    margin: 0 10px;
}

.sectionContent_body_linkHead table th a,.sectionContent_body_linkHead table th a:hover
{
	color:#2B2013;
	text-decoration:none;	
	font-size: 13px;
}

.sectionContent_head a,.sectionContent_head a:hover
{
	text-decoration:none;	
	color: #109AEB;
}
.sectionContent_body
{
	padding:5px;
	border-top: 1px solid #ADADAD;
	background-color: #ECF5FA;
	color: #2C353C;
    font-size: 12px;
    line-height: 18px;
    padding-left: 18px;
    text-align: justify;
}

</style>

<meta http-equiv="Content-Language" content="en">

<meta name="description" content="Party Analyst is a complete Indian Election Analysis Platform that also offers, Constituency, Cadre Management to Indian Political Parties and Politicians.">

<meta name="keywords" content="Indian Elections, Election Analysis, Indian Democracy, Andhra Pradesh Politics, Indian Political Parties, Indian Politicians, Indian Leaders, Congress, BJP, TDP, TRS, Indian Election Commission, Know Analyze, Act, MLA Elections, MP Elections, Cross Voting, District Election Results, MPTC Elections, ZPTC Elections, Constituency Management, Cadre Management, Party Performance, Election Comparison, Municipal Elections, Corporation Elections">

<meta name="copyright" content="IT Grids (India) Pvt. Ltd.">

<meta name="author" content="Ashok Dakavaram">

<meta name="email" content="info@itgrids.com">

<meta name="Charset" content="ISO-8859-1">

<meta name="Distribution" content="Global">

<meta name="Rating" content="General">

<meta name="ROBOTS" content="INDEX,FOLLOW">

<meta name="Revisit-after" content="1 Day">

<meta name="expires" content="Never">


<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">


<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/assets/skins/sam/skin.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script> 


<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js" ></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/landingPage/landingPage.js" ></script>
<script type="text/javascript" src="js/homePage/homePage.js"> </script>
<script type="text/javascript" src="js/statePage/statePage.js"> </script>
<script type="text/javascript" src="js/cncSearch.js"> </script>

 <script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>   
<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<script type="text/javascript" src="js/jQuery/floating-1.5.js"></script>
<script type="text/javascript" src="js/homePage/jquery.sudoSlider.min.js"></script>

<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>

<link  rel="stylesheet" type="text/css" href="styles/homePage/jquerySlider.css"/>

<!-- JQuery files (End) -->

<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<script type="text/javascript">
	
	var content = [
		{
			contentAncName:"",
			contentHead:"Guest Articles Submission",
			contentBody:[
						{
							ancName:"Guest Articles Submission",
							head:"Requesting To Post Articles On PartyAnalyst ",
							data:"1&#46We guarantee you a huge exposure by writing to one of its kind website&#46<br>2&#46You will be reaching a huge number of new targeted audience<br>3&#46You can connect with a whole new group of audience<br>4&#46Guaranteed back links to your blog<br><br>All this means<br><strong>&#61&#62 A massive growth in the quality traffic to your own blog</strong><br><br><b>Topics you can write about&#58</b><br>You can write articles in any of the following topics&#58<br>1&#46Indian Politics &#45 General<br>2&#46Current Indian Political Situation<br>3&#46What made politicians&#47political parties loose&#47win the previous elections<br>4&#46Views&#47Analysis on Political programs&#47schemes<br>5&#46Views&#47Analysis on CurrentAffairs &#45 Politics<br><br><b>Guidelines&#63</b><br><p>To provide the readers with the best&#44 valuable content and also not to salvage the writer&#39s reputation&#44 we have put some <a href=footerLinksAction.action?linkFrom=guidelines target=_blank><font color=#109AEB>guidelines</font></a> in place&#46 Please make sure you follow them so as to maintain a healthy environment&#46</p><b>Interested&#63</b><br><p>Please reply back to us with subject line &#34Guest Articles Submission Interest&#34&#46 Please fill & submit the below form &#34Guest Articles Submission Interest Form&#34 with your name&#44contact no&#44 email and article and we will get back to you as soon as possible&#46</p><p>Thank You and we look forward to seeing you on <a href=http&#58&#47&#47www&#46partyanalyst&#46com target=_blank><font color=#109AEB>partyanalyst&#46com</font></a></p><p>We completely understand the work that it takes to write an article&#46 However&#44 it doesn&#39t mean that we publish everything that is submitted to us&#46 Given the sensitive nature of the article subject&#44 we have some internal scrutiny guidelines in place&#46 A final decision will be made as to whether publish an article or not&#44 after it is scrutinized by our internal team &#40although&#44 all the articles that are written as per our guidelines are highly unlikely to be rejected&#41&#46</p>"
						}

					]
		}
				
				];
					
	
	function buildfooterLinksContent()
	{
		var elmt = document.getElementById("footerLinksdata_body");
		if(!elmt)
			return;

		var str = '';
		str += '<div style="font-family:sans-serif">';
		
		for(var i=0;i<content.length;i++)
		{
			str += '<div class="sectionContent_main">';
			str += '<div class="sectionContent_head"><a href="javascript:{}" name="'+content[i].contentAncName+'">'+content[i].contentHead+'</a></div>';
			str += '<div class="sectionContent_body">';
			for(var j=0;j<content[i].contentBody.length;j++)
			{
				str += '<div class="sectionContent_body_linkMain">';
				str += '<div class="sectionContent_body_linkHead">';
				str += '<table>';
				str += '<tr>';
				str += '<td>';
				if(content[i].contentBody[j].head != "")	
				{
					str += '<img src="images/icons/homePage_new/widgetHeaderIcon.jpeg"/>';
				}
				str += '</td>';
				str += '<th><a href="javascript:{}" name="'+content[i].contentBody[j].ancName+'">'+content[i].contentBody[j].head+'</a></th>';	
				str += '</tr>';
				str += '</table>';
				str += '</div>';
				str += '<div class="sectionContent_body_linkBody">'+content[i].contentBody[j].data+'</div>';
				str += '</div>';	
				if((j+1) != content[i].contentBody.length)
					str += '<div class="spacerLine"></div>';
			}
			str += '</div>';
			str += '</div>';
		}
		str += '</div>';

		elmt.innerHTML = str;
	}
</script>
</head>
<body>

<div id="footerLinksdata_main">
		<div id="footerLinksdata_head">
			
		</div>
		<div id="footerLinksdata_body">
			
		</div>
	</div>
	<div align="left"><strong><font color="#109AEB">&nbsp;&nbsp;&nbsp;&nbsp;Guest Articles Submission Interest Form:</font></strong></div>
	<div class="homePageContentWidget_body" style="border: 0px solid">
		<table id="quickRequestTable">
			<tr>
				<td colspan="2" align="right"><font color="red">*</font><span>Fields are Mandatory</span></td>
			</tr>
		<table/>
		</div>
		<div id="errorMsgDiv">
		
		
		<table>
			<tr>
			  <td style="font-weight:bold">
				<font color="red"> * </font>Name:
			  </td>
			  <td>
				<input class="quickRequestTextbox" type="text" name="name" id="articleNameId">
			  </td>
			</tr>
			<tr>
			  <td style="font-weight:bold">
				<font color="red"> * </font>Email:
			  </td>
			  <td>
				<input type="text" class="quickRequestTextbox" name="email" id="articleEmailId">
			  </td>
			</tr>
			<tr>
			  <td style="font-weight:bold">
				<font color="red"> * </font>Mobile:
			  </td>
			  <td>
				<input type="text" class="quickRequestTextbox" name="mobileNO" id="articleMobileId">
			  </td>
			</tr>
			<tr>
			  <td style="font-weight:bold" colspan="2">
				 Comment:
			  </td>
			</tr>
			<tr>
			  <td colspan="2">
				<textarea name="requirement" rows="3" cols="21" id="articleCommentId" ></textarea>
			   </td>
			</tr>
			<tr>
			  <td colspan="2" align="right"><div id="feedback"><input type="submit" value="Submit" class="quickRequestTextbox" style="margin-top=25px;width:54px;" onclick="submitArticleBox()"></div></td>
			</tr>
				 </table>
	</div>
	<script type="text/javascript">
		buildfooterLinksContent();
	</script>
</body>
</html>
