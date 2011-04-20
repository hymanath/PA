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
			contentHead:"About Us",
			contentBody:[
						{
							ancName:"whoWeAre",
							head:"Who we are",
							data:"Party Analyst is a product owned and operated by IT GRIDS (India) Pvt. Ltd., operating out of Hyderabad,Andhra Pradesh. It is been run by a core group of highly qualified young entrepreneurs with experience working in India and abroad in wide and multiple industry verticals such as IT, Realty with professional qualification in Engineering, Business Management."
						},
						{
							ancName:"whatWeDo",
							head:"What we do",
							data:"The core group envisions bringing about a change in the election domain by implementing a state of	art web platform that would go a long way in providing quality, reliable and deft information to all stake holders of Election Analysis including the political parties, political leaders, press/media and the voter of India."
						},
						{
							ancName:"coreCompetency",
							head:"Core Competency",
							data:"Our core competency lies in design, develop and provide and support a web platform using high end technology in the political domain. The team is led by a highly experienced Project Manager, with enormous experience in web technologies and backed by highly skilled software Engineers, designers, testers and maintenance engineers to ensure we provide continuous enhancements, updates and support to our customers."
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