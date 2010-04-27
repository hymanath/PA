
var candidateInfoObject = {
							name:'',
							candidateImgURL:'',
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


var candidateElectionResultPanel;


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
			height:600,
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

function buildCandidateElectionProfile()
{		
	var electionPrfElmtBody = document.getElementById("candidatePoliticalCareer_body");	
	
	var ebStr='';
	for(var i in candidateInfoObject.candidateInfoArray)
	{
		var data = candidateInfoObject.candidateInfoArray[i];
		ebStr+='<div id="candidateElectionInfo_Prf'+i+'" class="electionPrfDiv" onclick="showElectionResultsInPopup('+i+')">';
		ebStr+='<span style="margin-right:10px;"> <img height="10" width="10" src="'+candidateInfoObject.contextPath+'/images/icons/indexPage/listIcon.png"/></span>';
		ebStr+='<span>'+data.status+' in '+data.electionYear+' '+data.electionType+' Election with <b>'+data.votePercentage+'% </b>of votes gain in '+data.constituencyName+' constituency</span>';
		ebStr+='</div>';
	}
	
	if(electionPrfElmtBody)
		electionPrfElmtBody.innerHTML=ebStr;
	
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
		str+='	<span class="leftLinkSpanClass" onclick="showLeftMenuContent(this.innerHTML)">'+leftLinksArray[i].type+'</span>';
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

function initializeCandidatePage()
{
	buildCandidatePageLayout();	
	buildLeftNavLinks();
	buildCandidateElectionProfile();

	//buildCandidateInitialProfile();	
	//buildCandidatePhotoGallery();

	//YAHOO.util.Dom.addClass('profileAnc', 'highlightmenu'); 


}
