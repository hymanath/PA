
var candidateInfoObject = {
							name:'',
							candidateImgURL:'',
							candidatePartyFlag:'',
							contextPath:'',
							candidateInfoArray:[]					
						  };

var candidatePageURLInfo={
							'profilePageURL':'Nara_Chandrababu_Naidu',
							'newsPageURL':'',
							'photoPageURL':'',
							'videoPageURL':'',
							'developmentsPageURL':''
					  };

var candidatePhotoGalleryURL={
								photoURL:''		
							};

var candidateStaticPageDivs=new Array(	'candidatePageContent_body_profileMain','candidatePageContent_body_constituencyMain','candidatePageContent_body_NewsMain','candidatePageContent_body_DevelopmentsMain','candidatePageContent_body_SpeechesMain','candidatePageContent_body_photoMain','candidatePageContent_body_videosMain','candidatePageContent_body_contactMain');

var ancArray = new Array('profileAnc','newsAnc','photoAnc','videoAnc','developmentsAnc');

var leftLinksArray = [
						{
							"type":"Profile",
							"innerLinks":["My Profile","My Familiy","My Career","My Achievements"]
						},
						{
							"type":"My Constituency",
							"innerLinks":[]
						},
						{
							"type":"News/Events",
							"innerLinks":[]
						},
						{
							"type":"Developments",
							"innerLinks":["Awards","Initiatives","Schemes"]
						},
						{
							"type":"Speeches",
							"innerLinks":[]
						},
					 ];

new Array('Profile','Political Career','My Constituency','News/Events','Developments','Speeches');


var candidateElectionResultPanel,ImgPanel;


function showDetails(id)
{	
	tr=document.getElementsByTagName('tr')

	for (i=0;i<tr.length;i++)
	{			
		if (tr[i].id && tr[i].id==id)
		{				
			if ( tr[i].style.display=='none' )
			{
				tr[i].style.display = '';
			}
			else
			{
				tr[i].style.display = 'none';
			}
		}
	}		
}

function buildCandidatePageLayout()
{
	var candidatePageLayout = new YAHOO.widget.Layout('candidatePageLayoutDiv', { 
			height:650,
			units: [					 	        
					{ 
						position: 'left',
						header:false,
						width: 250,							
						resize: false,
						gutter: '2px',
						collapse: false,
						scroll: true,
						body: 'candidatePageLeftContentDiv',
						animate: true
					}, 					 
					{ 
						position: 'center',						
						body: 'candidatePageCenterContentDiv',
						resize: false,
						gutter: '2px',
						collapse: true,
						scroll: true,						
						animate: true
					} 
	    ] 
		}); 
		candidatePageLayout.render(); 
}



function buildCandidateInitialProfile()
{
	/*var basicPrfElmt = document.getElementById("candidatePageContent_body_basicPrf");
	var str ='';
	str+='<table id="candidateDetailsTable" style="">';
	str+='<tr>';
	str+='<th>Name</th>';
	str+='<td>'+candidateInfoObject.name+'</td>';
	str+='</tr>';
	str+='<tr>';
	str+='<td colspan="2" align="right"><a id="moreDetailsAnc" href="javascript:{}">More Details... </a></td>';
	str+='</tr>';
	str+='</table>';	

	if(basicPrfElmt)
		basicPrfElmt.innerHTML=str;*/

	
	buildCandidateElectionProfile();

}

function setTextColor(elmt)
{
	elmt.style.color = "black";
}

function unSetTextColor(elmt)
{
	elmt.style.color = "#6C7076";
}
var candidateObject;
function buildCandidateElectionProfile()
{		
	var electionPrfElmtBody = document.getElementById("candidatePoliticalInfo");	
	var candidateFlag = document.getElementById("candidatePartyFlag");	
	
	

	var ebStr='';
	candidateObject = candidateInfoObject; 

	for(var i in candidateInfoObject.candidateInfoArray)
	{
		var data = candidateInfoObject.candidateInfoArray[i];
	
		ebStr+='<div id="candidateElectionInfo_Prf'+i+'" class="electionPrfDiv"  onclick="showElectionResultsInNewWindow('+i+')" onmouseover="setTextColor(this)" onmouseout="unSetTextColor(this)">';
		ebStr+='<span style="margin-right:10px;"> <img height="10" width="10" src="'+candidateInfoObject.contextPath+'/images/icons/indexPage/listIcon.png"/></span>';
		if(data.status == "Won")
			ebStr+='<span><font color="green">'+data.status+'</font>';
		else
			ebStr+='<span><font color="red">'+data.status+'</font>';
		ebStr+=' in '+data.electionYear+' '+data.electionType+' Election with <b>'+data.votePercentage+'% </b>of votes gain for '+data.partyName+' party in '+data.constituencyName+' constituency</span>';
		ebStr+='</div>';
	}
	
	if(electionPrfElmtBody)
		electionPrfElmtBody.innerHTML=ebStr;
	
	var flag = '';
	flag += '<img width="150" height="100" src="'+candidateInfoObject.candidatePartyFlag+'"/>';
	if(candidateFlag)
		candidateFlag.innerHTML = flag;
}


function showElectionResultsInNewWindow(index){
	var data = candidateInfoObject.candidateInfoArray[index];
	
	var politicalChangesWindow = window.open("constituencyElectionResultsAction.action?constituencyId="+data.constituencyId+"&electionType="+data.electionType+"&electionYear="+data.electionYear,"politicalChangesWindow","scrollbars=yes,height=600,width=850,left=200,top=200");
    politicalChangesWindow.focus();
}

function showElectionResultsInPopup(index)
{
	var data = candidateInfoObject.candidateInfoArray[index];
	//var mainDivElmt = document.getElementById("CandidatePageMainDiv");
		
	//var divElmt = document.createElement('div');
	//divElmt.setAttribute('id','newImpDateDiv');
		
	var str='';
	//str+='<div id="cand_elect_div" class="yui-skin-sam">'
	//str+='<div class="hd">'+data.electionYear+' '+data.electionType+' Election Details</div> ';
	//str+='<div class="bd">';
	
	str+='<fieldset id="electionProfileField">';
	str+='<legend> Election Profile Info </legend>';
	str+='<div id="electionProfileDiv">';
	str+='<table class="elecInfoTableClass" width="100%">';
	str+='<tr>';
	str+='<th>Name</th>';
	str+='<td colspan="3">'+data.candidateName+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Party</th>';
	str+='<td>'+data.partyName+'</td>';	
	str+='<th>Constituency</th>';
	str+='<td>'+data.constituencyName+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>District</th>';
	str+='<td>'+data.districtName+'</td>';	
	str+='<th>State</th>';
	str+='<td>'+data.stateName+'</td>'
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	str+='</fieldset>';
	//--------------
	str+='<fieldset id="electionResultsField">';
	str+='<legend> Election Results Info </legend>';
	str+='<div id="electionResultsDiv">';
	str+='<table class="elecInfoTableClass" width="100%">';
	str+='<tr>';
	str+='<th>Election Type</th>';
	str+='<td colspan="3">'+data.electionType+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Year</th>';
	str+='<td>'+data.electionYear+'</td>';	
	str+='<th>Status</th>';
	str+='<td>'+data.status+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Votes Earned</th>';
	str+='<td>'+data.votesEarned+'</td>';	
	str+='<th>Votes Percentage</th>';
	str+='<td>'+data.votePercentage+'</td>'
	str+='</tr>';
	str+='</table>';
	str+='</div>';	
	str+='</fieldset>';
	//--------
	str+='<fieldset id="oppositionResultsField">';
	str+='<legend> Opposition\'s Results Info </legend>';
	str+='<div id="oppCandResultsDiv">';	
	str+='</div>';
	str+='</fieldset>';
	
	//str+='</div>';
	//str+='</div>';

	//divElmt.innerHTML=str;
	//mainDivElmt.appendChild(divElmt);

	//YAHOO.util.Dom.addClass('cand_elect_div', 'yui-skin-sam'); 
	
	
	candidateElectionResultPanel = new YAHOO.widget.Panel("cand_elec_div_panel", 
				{
					width:"800px", 
					fixedcenter : false, 
					visible : true,  
					constraintoviewport : true,
					x:200,
					y:300,
					iframe :true,
					modal :true,
					visible:true,						
					draggable:true, 
					close:true
				} ); 
	

	candidateElectionResultPanel.render();
	candidateElectionResultPanel.setHeader(data.electionYear+' '+data.electionType+' Election Details');
	candidateElectionResultPanel.setBody(str);

	 var myDataSource = new YAHOO.util.DataSource(data.oppositionCandidates); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	 myDataSource.responseSchema = { 
	            fields: ["candidateName","partyName","status","votesEarned","votesPercentage"] 
	        }; 
	
	 var myColumnDefs = [ 
	            {key:"candidateName",label:'Candidate Name', sortable:true, resizeable:true}, 
	            {key:"partyName", label:'Party Name', sortable:true, resizeable:true}, 
	            {key:"status", label:'Status',sortable:true, resizeable:true}, 
	            {key:"votesEarned",label:'Votes Earned',formatter:YAHOO.widget.DataTable.formatNumber, sortable:true, resizeable:true}, 
	            {key:"votesPercentage",label:'Votes %', sortable:true, resizeable:true} 
	        ]; 
	 
	var myDataTable = new YAHOO.widget.DataTable("oppCandResultsDiv",myColumnDefs, myDataSource); 
}

function showLeftMenuContent(content)
{	
	var linksHeadDivElmt = document.getElementById("candidateStaticInfo_head");

	var str = '';
	var elmt;
	
	for(var i in candidateStaticPageDivs)
	{
		var divElmt = document.getElementById(candidateStaticPageDivs[i]);
		divElmt.style.display = 'none';
	}

	
	if(content=="Profile")
	{
		str+=candidateInfoObject.name+"'s Profile Info";
		elmt = document.getElementById('candidatePageContent_body_profileMain');
	}
	if(content=="My Constituency")
	{
		str+=candidateInfoObject.name+"'s Constituency Info";
		elmt = document.getElementById('candidatePageContent_body_constituencyMain');
	}
	if(content=="News/Events")
	{
		str+=candidateInfoObject.name+"'s News/Events Info";
		elmt = document.getElementById('candidatePageContent_body_NewsMain');
	}
	if(content=="Developments")
	{
		str+=candidateInfoObject.name+"'s Developments Info";
		elmt = document.getElementById('candidatePageContent_body_DevelopmentsMain');
	}
	if(content=="Speeches")
	{
		str+=candidateInfoObject.name+"'s Speeches Info";
		elmt = document.getElementById('candidatePageContent_body_SpeechesMain');
	}
	if(content=="photo")
	{
		str+=candidateInfoObject.name+"'s Photo Gallery";
		elmt = document.getElementById('candidatePageContent_body_photoMain');
	}
	if(content=="video")
	{
		str+=candidateInfoObject.name+"'s Video Gallery";
		elmt = document.getElementById('candidatePageContent_body_videosMain');		
	}
	if(content=="contact")
	{
		str+=candidateInfoObject.name+"'s Contact Info";
		elmt = document.getElementById('candidatePageContent_body_contactMain');
	}
	
	if(linksHeadDivElmt)
		linksHeadDivElmt.innerHTML = str;

	if(elmt)
		elmt.style.display = 'block';	
}

function expandSubDiv(id,count)
{

	/*if(leftLinksArray[count].innerLinks.length == 0)
		return;
	
	var bodyStr = id+"_body";
	listAnim = new YAHOO.util.Anim(bodyStr, {
		height: {
			to: 80 
		} 
	}, 1, YAHOO.util.Easing.easeOut);

	listAnim.animate();*/
}

function buildLeftNavLinks()
{
	//var tree = new YAHOO.widget.TreeView("candidatePageLeftContentDiv_body"); 
	var elmt = document.getElementById("candidatePageLeftContentDiv_leftNavLinks");
	
	var str = '';
	for(var i in leftLinksArray)
	{
		str+='<div id="'+leftLinksArray[i].type+'" class="leftLinksClass" onclick="expandSubDiv(this.id,'+i+')">';
		str+='<div id="'+leftLinksArray[i].type+'_head">';
		str+='	<img src="images/icons/districtPage/listIcon.png"/>';
		str+='	<span class="leftLinkSpanClass" onclick="showCandidateNewsGallary();showPhotoGallary();showLeftMenuContent(this.innerHTML);">'+leftLinksArray[i].type+'</span>';
		str+='</div>';
		str+='<div id="'+leftLinksArray[i].type+'_body" class="leftLinksClass_body">';
		if(leftLinksArray[i].innerLinks.length > 0)
		{
			for(var j in leftLinksArray[i].innerLinks)
			{
				var localObj = leftLinksArray[i].innerLinks[j];
				str+='<div class="innerlistDiv">';
				str+='	<img src="images/icons/districtPage/listIcon.png"/>';
				str+='	<span>'+localObj+'</span>';
				str+='</div>';
			}
		}
		str+='</div>';
		str+='</div>';
	}			

	if(elmt)
		elmt.innerHTML = str;
}

function showLargeImage(elmt)
{
	var str = '';
	str+='<div>';
	str+='<div class="imgPanelCloseDiv"><span style="cursor:pointer;" onclick="javascript:{ImgPanel.hide();}">Close</span></div>';
	str+='<div>';
	str+='<img height="200" width="100%" src="'+elmt.src+'"/>';
	str+='</div>';
	str+='</div>';


	ImgPanel = new YAHOO.widget.Dialog("cand_image_div_panel", {
                 
                 width : "220px", 
                 fixedcenter : true, 
                 visible : true,  
                 constraintoviewport : true, 
        		 iframe :true,
        		 modal :true,
        		 hideaftersubmit:true,
        		 close:false,
				 draggable:false
       });	 
    ImgPanel.setBody(str);
    ImgPanel.render();

	var maskElmt = document.getElementById("cand_image_div_panel_mask");

}

function closeImgPanel()
{
	ImgPanel.hide();
}

function buildImagesType(divId,imagesList)
{
	var groupsElmt = document.getElementById(divId);

	if(!groupsElmt)
		return;
	
	groupsElmt.innerHTML = '';

	var str = '';
	for(var i in imagesList)
	{
		str += '<div id="'+imagesList[i].groupName+'_main" class="yui-skin-sam imagesCarouselDiv">';	
		str += '<ul class="imagesULElmt">';
		for(var j in imagesList[i].imagesUrl)
		{
			str += '<li>';
				str += '<span class="imgSpan" onclick="showLargeImage(this.firstChild)">'+imagesList[i].imagesUrl[j]+'</span>';
			str += '</li>';
		}
		str += '</ul>';
		str += '</div>';
		groupsElmt.innerHTML += str;	
		buildImagesCarousel(imagesList[i].groupName);
	}
}

function buildImagesCarousel(divId)
{
	reportsCarousel = new YAHOO.widget.Carousel(divId+'_main',
		{
			carouselEl: "UL",
			isCircular: true,
			isVertical: false,
			numVisible: 6,
			animation: { speed: 1.0 },
			autoPlayInterval: 2000
		});		
	
	reportsCarousel.registerPagination('<span class="carouselTitleSpan">'+divId+'</span>'); 
	reportsCarousel.render(); 
	reportsCarousel.show();

}


function animateNext(id)
{	
	var elmtId = id.substring(0,id.indexOf('_'));

	var element = document.getElementById(elmtId+"_list");
	
	var attributes = {
      scroll: { to: [700,element.scrollTop] }
	};

   var anim = new YAHOO.util.Scroll(elmtId, attributes, 1, YAHOO.util.Easing.easeOut);
   anim.animate();

	/*var myAnim = new YAHOO.util.Scroll(element, {
		scroll: {    
			to: [ 500, elmtId.scrollTop ]
		} 
	});
	myAnim.animate();
	*/
}

function buildCandidateNews()
{   
    
	var options = {
		"format" : "728x90",
		"queryList" : [
			  {
				"title" : candidateInfoObject.name,
				"q" : candidateInfoObject.name+", India"
			  }
		 ]
	  }
	
	var content = document.getElementById('one');
	var newsShow = new google.elements.NewsShow(content, options);

	var options = {
		"format" : "728x90",
		"queryList" : [
			  {
				"title" : candidateInfoObject.candidateInfoArray[0].constituencyName,
				"q" : candidateInfoObject.candidateInfoArray[0].constituencyName+", India"
			  }
		 ]
	  }
	
	var content = document.getElementById('two');
	var newsShow = new google.elements.NewsShow(content, options);

	 
}

function buildCandidateVideoGallery()
{
	

	var value="http://www.youtube.com/v/8jhiIWiCch8?enablejsapi=1&playerapiid=ytplayer&rel=1&color1=0x2b405b&color2=0x6b8ab6&border=1&fs=1";
	var params = { allowScriptAccess: "always" };	 
	var atts = { id: "myytplayer" };
	swfobject.embedSWF(value, "videoBarOne", "300", "200", "8", null, null, params,atts);
	var elmtId = document.getElementById( "videoBarOne");
  
}

function LoadVideoBar()
{
	var vbl;
	var vbr;

	var searchString1 = candidateInfoObject.name+' interview';
	var searchString2 = candidateInfoObject.name+' '+candidateInfoObject.candidateInfoArray[0].constituencyName;
	

	var options = {
	  largeResultSet : false,
		  horizontal:true
		  
			}
	vbl = new GSvideoBar(
				document.getElementById("videoBarOne"),
				document.getElementById("ytVideoPlayer"),
				options
				);
	

	options = {
      master : vbl,
      largeResultSet :false ,
		 horizontal:true
		}
    vbr = new GSvideoBar(
                document.getElementById("videoBartwo"),
                null,
		
                options
                );


	vbl.execute(searchString1);
	vbr.execute(searchString2);
}


function onYouTubePlayerReady(playerId)
{
	var ytplayer = document.getElementById("myytplayer");
	//ytplayer.cueVideoById(videoId:, startSeconds:Number, suggestedQuality:String):Void
}


function initializeCandidatePage()
{
	buildCandidatePageLayout();	
	buildLeftNavLinks();
	buildCandidateElectionProfile();
	buildCandidateNews();
	LoadVideoBar();
	//buildCandidateVideoGallery();

	//buildCandidateInitialProfile();	
	//buildCandidatePhotoGallery();

	//YAHOO.util.Dom.addClass('profileAnc', 'highlightmenu'); 


}
function candidateProfileInfo()
{
	var elmt = document.getElementById("candidateProfile");
	var str =' ';
		str +='	<table>';
		str +='<tr>';
		str +='	 <td>Constituency :<b>&nbsp;&nbsp;<a href="constituencyPageAction.action?constituencyId='+candidateObject.candidateInfoArray[0].constituencyId+'">'+candidateObject.candidateInfoArray[0].constituencyName+'</b></a></td>';
		str +='</tr>';
		str +='<tr>';
		str +='<td title="'+candidateObject.candidateInfoArray[0].partyName+'">Political Party:<b>&nbsp;&nbsp;'+candidateObject.candidateInfoArray[0].partyShortName+'</b> </td>';
		str +='</tr>';
		str +='<tr>';
		if(candidateObject.candidateInfoArray[0].education != ''){
			str +='<td>Education:<b>&nbsp;&nbsp;'+candidateObject.candidateInfoArray[0].education+'</b> </td>';
		}
		str +='</tr>';
		str +='</table>';

		elmt.innerHTML = str;

		var candidatePageHeaderElmt = document.getElementById("candidatePageHeader");

		var str1 ='';
		if(candidateObject.candidateInfoArray[0].electionType == 'Parliament' && candidateObject.candidateInfoArray[0].status == 'Won'){
			str1 +='&nbsp;&nbsp;MP';
		}
        if(candidateObject.candidateInfoArray[0].electionType == 'Assembly' && candidateObject.candidateInfoArray[0].status == 'Won'){
		  str1 +='&nbsp;&nbsp;MLA';
		}
		str1 +='<span>&nbsp;&nbsp;From&nbsp;&nbsp;'+candidateObject.candidateInfoArray[0].constituencyName+'</span>&nbsp;&nbsp; Constituency';

		if(candidateObject.candidateInfoArray[0].partyFlag != ""){
		 str1 +='<img style="padding-left:190px;" src="'+candidateInfoObject.contextPath+'/images/party_flags/'+candidateObject.candidateInfoArray[0].partyFlag+'" height="20" width="40"/>';
		}
		else{ 
		 str1 +='<img style="padding-left:190px;" src="'+candidateInfoObject.contextPath+'/images/party_flags/no_Image.png" height="20" width="45"/>';
		}
		candidatePageHeaderElmt.innerHTML = str1;
}

