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
	
	var content = 		
		[
		{
			contentAncName:"privacyPolicy",
			contentHead:"PRIVACY POLICY",
			contentBody:[
						{
							ancName:"",
							head:"",
							data:"This privacy policy forms the part of the terms of service and disclaimer as complete agreement between the website and users of the website for registering, using, utilizing the features of the website. [www.partyanalyst.com] for reference the terms &#34We&#34, &#34Us&#34 are referred to the website and &#34You&#34 for the users. IT Grids (India) Pvt. Ltd., is committed to ensuring that your privacy is protected. All the information you provide to us on the website including personal details will be used only in accordance with this policy statement. In pursuance of providing with quality service, we continuously try to improve and update our privacy policy statement with frequent updates. We request you to keep checking the privacy policy page on the website for reviewing such updated privacy statement. This Policy sets out and spells the basis on the personal data that is used by the users to register on our website and how this data is processed. Please read the following information carefully to understand the practices that we implement on the website with regard to privacy of data of the users of this website."
						},
						{
							ancName:"",
							head:"At the time of registration on the website, the following information is collected from you:",
							data:"The name, date of birth, gender, location,Contact detail, educational information, work status,Social network profiles along with the photograph."
						},
						{
							ancName:"",
							head:"How this information is used:",
							data:"We may use this information for improving the website to suit your requirements.We may use this information to contact you to send promotional information on new Products and services offered on the website.We may use this information email marketing for market research purchase.And we may use this information to intimate you on new terms and policed updated to the website."
						},
						{
							ancName:"",
							head:"",
							data:"In case if you do not want us to send you any promotional material, you can always un subscribe to the same by sending an email to us at &#45;<a href=mailto:info@itgrids.com> info&#64;itgrids&#46;com</a>."
						},
						{
							ancName:"",
							head:"What we will NEVER do:",
							data:"We will never rent, sell or use your contact details including name, email id or any other personal information to anybody anywhere in the world."
						},
						{
							ancName:"",
							head:"Security",
							data:"We are committed to ensure that your personal information is safeguarded against any unauthorized access or disclosure. And as a part of this privacy policy, we are using the services of the best of the servers that are dedicated and exclusive for the website.Though a large amount of time, resources and infrastructure is put in place for the safety and security of the personal data of the users, unfortunately the information online is completely not safe. As such, no GUARANTEE can be given to any registered user that the personal data is entirely safe. The users will be entering their personal data on the website at their own risk."
						},
						{
							ancName:"",
							head:"User Interactions",
							data:"As a registered member of this website you may interacting and communicating with a host of other members registered on this website from different parts of the world. All communications with other members on the website including but not limited to exchanging of text messages, email id, personal phone number, that you will may do or will be doing at your cost and risk. This website or its owners DO NOT take any responsibility for any loss or harm that may cause to you physically, mentally or monetarily during your interactions with other members of the website."
						},
						{
							ancName:"",
							head:"Policy Updates",
							data:"From time to time we keep updating this privacy policy to serve you better. Though you will be kept informed about changes to the policies, please keep visiting this page regularly for updates.You may always contact us at <a href=mailto:info@itgrids.com>&#45; info&#64;itgrids&#46;com</a>. for any information or clarification that you may need. We would be more than happy to answer them."
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