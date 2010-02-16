
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

var candidateStaticPageDivs=new Array(	'candidatePageContent_body_ProfileMain','candidatePageContent_body_NewsMain','candidatePageContent_body_PhotoMain','candidatePageContent_body_VideoMain','candidatePageContent_body_DevelopmentsMain');

var ancArray = new Array('profileAnc','newsAnc','photoAnc','videoAnc','developmentsAnc');


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
	var candidatePageLayout = new YAHOO.widget.Layout('CandidatePageLayoutDiv', { 
			height:800,
			units: [
					{ 
						position: 'top',
						header:false,							
						height:50,						
						resize: false,
						gutter: '5px',
						collapse: false,
						scroll: true,
						body: 'CandidatePageTopNavLinksDiv',
						animate: true
					}, 	        
					{ 
						position: 'right',
						header:false,
						width: 250,							
						resize: false,
						gutter: '5px',
						collapse: false,
						scroll: true,
						body: 'CandidatePageRightImageDiv',
						animate: true
					},
					{ 
						position: 'bottom', 
						height: 200,
						header:false,
						body: 'CandidatePageBottomLinksDiv',
						resize: false,
						gutter: '5px',
						collapse: false,
						scroll: true,						
						animate: true 
					}, 					 
					{ 
						position: 'center',						
						body: 'CandidatePageCenterContentDiv',
						resize: false,
						gutter: '5px',
						collapse: true,
						scroll: true,						
						animate: true
					} 
	    ] 
		}); 
		candidatePageLayout.render(); 
}

function buildTopNavLinks()
{
	
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
	var electionPrfElmtHead = document.getElementById("candidatePageContent_electionPrf_head");
	var electionPrfElmtBody = document.getElementById("candidatePageContent_electionPrf_body");
	
	var ehStr='';
	ehStr+='<span>'+candidateInfoObject.name +'\'s Election Profile :</span>';
	
	
	var ebStr='';
	for(var i in candidateInfoObject.candidateInfoArray)
	{
		var data = candidateInfoObject.candidateInfoArray[i];
		ebStr+='<div id="candidateElectionInfo_Prf'+i+'" class="candidateElectionInfoClass" onclick="showElectionResultsInPopup('+i+')">';
		ebStr+='<span style="margin-right:10px;"> <img height="10" width="10" src="'+candidateInfoObject.contextPath+'/images/icons/arrow.png"/></span>';
		ebStr+='<span>'+data.status+' in '+data.electionYear+' '+data.electionType+' Election with '+data.votePercentage+'% of votes gain in '+data.constituencyName+' constituency</span>';
		ebStr+='</div>';
	}
	
	if(electionPrfElmtHead)
		electionPrfElmtHead.innerHTML=ehStr;
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

function showTopMenuContent(id)
{
	var elmt;
	var ancElmt = document.getElementById(id);
	var highlightedItems = new Array();

	for(var j in ancArray)
	{
		if(YAHOO.util.Dom.hasClass(ancArray[j], 'highlightmenu'))
		{
			highlightedItems = YAHOO.util.Dom.getElementsByClassName('highlightmenu');
			for(var k in highlightedItems)
				highlightedItems[k].style.color = '#000000';

			YAHOO.util.Dom.removeClass(ancArray[j], 'highlightmenu');

		}
	}

	YAHOO.util.Dom.addClass(id, 'highlightmenu');

	for(var i in candidateStaticPageDivs)
	{
		var divElmt = document.getElementById(candidateStaticPageDivs[i]);
		divElmt.style.display = 'none';
	}

	
	if(id=="profileAnc")
		elmt = document.getElementById('candidatePageContent_body_ProfileMain');
	if(id=="newsAnc")
		elmt = document.getElementById('candidatePageContent_body_NewsMain');
	if(id=="photoAnc")
		elmt = document.getElementById('candidatePageContent_body_PhotoMain');
	if(id=="videoAnc")
		elmt = document.getElementById('candidatePageContent_body_VideoMain');
	if(id=="developmentsAnc")
		elmt = document.getElementById('candidatePageContent_body_DevelopmentsMain');
	
	if(elmt)
		elmt.style.display = 'block';
		
	ancElmt.style.color = '#FFFFFF';
}

function buildCandidatePhotoGallery()
{
	var divElmt = document.getElementById("candidatePageContent_body_PhotoMain");
	var carousel = new YAHOO.widget.Carousel("candidatePageContent_body_PhotoMain");
	
	//carousel.set("animation", { speed: 0.5 });

	carousel.addItem('<img height="600" weight="600" src="images/photo_gallery/Nara_Chandrababu_Naidu/sample/Blue hills.jpg"/>'); 
	carousel.addItem('<img height="600" weight="600" src="images/photo_gallery/Nara_Chandrababu_Naidu/sample/Sunset.jpg"/>'); 
	carousel.addItem('<img height="600" weight="600" src="images/photo_gallery/Nara_Chandrababu_Naidu/sample/Water lilies.jpg"/>'); 
	carousel.render(); 


}
function initializeCandidatePage()
{
	buildCandidatePageLayout();
	buildTopNavLinks();		
	buildCandidateInitialProfile();	
	//buildCandidatePhotoGallery();

	YAHOO.util.Dom.addClass('profileAnc', 'highlightmenu'); 
}