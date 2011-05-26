<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Analyst - Know, Analyse, Act</title>

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
	width: 15%;
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

</style>
<script type="text/javascript">
	
	var content = [
		{
			contentAncName:"",
			contentHead:"GUIDELINES:",
			contentBody:[
						{
							ancName:"whoWeAre",
							head:"",
							data:"<b>Please make sure you adhere to these guidelines&#58</b><br><br><br><b>No offensive Language&#58</b> Articles must not be intended to abuse any particular person&#47leader nor an offensive and&#47or non&#45parliamentary language should be used&#46<br><br><br><b>Original content only&#58</b> Content must be original to be considered&#46 It should not appear in any other blog or website&#44 including your&#39s&#46<br><br><br><b>Backlink&#58</b> We want to promote you as the author of the article and so&#44 we provide a signature&#47bio section&#46 This space&#44 at any cost&#44 must not be used as adspace but should be used to let readers know about you and provide a link to your website&#46 There is no limit to external sites as long as they are in bio section&#46 These links&#44 however&#44 must not be an affiliate link or links to a sales pitch of your product&#47services that are treated as competitors for us<br><br><b>Appropriate length&#58</b> Article can be of any length as long as you can keep it interesting and informative&#44 but the minimum length must be at least 300 words&#46<br><br><b>Images&#58</b> You are free to use any images as long as you have permission to do so or there are no copy right issues&#46 These images&#44 however&#44 must not be offensive&#46"
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
		str += '<div>';
		
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

	<script type="text/javascript">
		buildfooterLinksContent();
		window.location.hash;
	</script>
</body>
</html>