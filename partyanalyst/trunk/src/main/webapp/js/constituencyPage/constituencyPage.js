
var constituencyPageMainObj={
								constituencyAddress:'',
								contextPath:'',
								constituencyInfo:{
													constituencyName:'',
													districtName:'',
													stateName:'',
													startDate:'',
													deformDate:'',
													constituencyType:''
												 },
								constituencyElectionInfo:[],
								constituencyVotersInfo:[]
							};

/*var address="${constituencyDetails.constituencyName},${constituencyDetails.districtName},${constituencyDetails.stateName}";		
		var map = new GMap2(document.getElementById("map_canvas"));
		var geocoder = new GClientGeocoder();
		geocoder.getLatLng(address,
		function(point)
		{			
	      if (!point) 
		  {
			alert(address + " not found");
		  }
		  else 
		  {
			map.setCenter(point, 8);
			var marker = new GMarker(point);
			map.addOverlay(marker);
			map.setUIToDefault();
		  }
		})


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
function getString() 
{
  alert("In function");
  if (GBrowserIsCompatible())
  {        
	//var str=document.getElementById("add").value;
	//console.log("In second if");
//	showAddress(str);      
  }
}

function showAddress(address)
{
	var map = new GMap2(document.getElementById("map_canvas"));
	var geocoder = new GClientGeocoder();
	geocoder.getLatLng(address,
	function(point)
	{			
	  if (!point) 
	  {
		//alert(address + " not found");
	  }
	  else 
	  {
		map.setCenter(point, 14);
		var marker = new GMarker(point);
		map.addOverlay(marker);
		//marker.openInfoWindowHtml(address);
		map.setUIToDefault();
	  }
	})
}
*/

function buildRightlayoutMap()
{
	var address=constituencyPageMainObj.constituencyAddress;		
		var map = new GMap2(document.getElementById("map_canvas"));
		var geocoder = new GClientGeocoder();
		geocoder.getLatLng(address,
		function(point)
		{			
	      if (!point) 
		  {
			alert(address + " not found");
		  }
		  else 
		  {
			map.setCenter(point, 8);
			var marker = new GMarker(point);
			map.addOverlay(marker);
			map.setUIToDefault();
		  }
		})

	var divElmtHead = document.getElementById("constituencyInfoDiv_Head");
	var divElmtBody = document.getElementById("constituencyInfoDiv_Body");

	var heading='<h3>Constituency Info </h3>';
	if(divElmtHead)
		divElmtHead.innerHTML=heading;
	
	var str = '';
	str+='<table id="constituencyInfoTable">';
	str+='<tr>';
	str+='<th> Constituency Name </th>';
	str+='<th> : </th>';
	str+='<td> '+constituencyPageMainObj.constituencyInfo.constituencyName+' </td>';
	str+='</tr>';
	if(constituencyPageMainObj.constituencyInfo.constituencyType.length > 0)
	{
		str+='<tr>';
		str+='<th> Constituency Type </th>';
		str+='<th> : </th>';
		str+='<td> '+constituencyPageMainObj.constituencyInfo.constituencyType+' </td>';
		str+='</tr>';
	}
	str+='<tr>';
	str+='<th> District Name </th>';
	str+='<th> : </th>';
	str+='<td> '+constituencyPageMainObj.constituencyInfo.districtName+' </td>';
	str+='</tr>';
	str+='<tr>';
	str+='<th> State Name </th>';
	str+='<th> : </th>';
	str+='<td> '+constituencyPageMainObj.constituencyInfo.stateName+' </td>';
	str+='</tr>';
	if(constituencyPageMainObj.constituencyInfo.startDate.length > 0)
	{
		str+='<tr>';
		str+='<th> Start Date </th>';
		str+='<th> : </th>';
		str+='<td> '+constituencyPageMainObj.constituencyInfo.startDate+' </td>';
		str+='</tr>';
	}
	if(constituencyPageMainObj.constituencyInfo.deformDate.length > 0)
	{
		str+='<tr>';
		str+='<th> Deformation Date </th>';
		str+='<th> : </th>';
		str+='<td> '+constituencyPageMainObj.constituencyInfo.deformDate+' </td>';
		str+='</tr>';
	}
	
	if(divElmtBody)
		divElmtBody.innerHTML=str;

}

function buildElectionResults()
{
	var HeadElmt = document.getElementById('constituencyPageElectionInfoDiv_Head');
	var BodyElmt = document.getElementById('constituencyPageElectionInfoDiv_Body');
	
	if(HeadElmt)
		HeadElmt.innerHTML = ' Election Information in '+constituencyPageMainObj.constituencyInfo.constituencyName;

	var elecStr = '';
	for(var i in constituencyPageMainObj.constituencyElectionInfo)
	{
		var data = constituencyPageMainObj.constituencyElectionInfo[i];
		var info = constituencyPageMainObj.constituencyInfo;
		elecStr+='<div id="constituencyElectionInfo_'+i+'" class="electionInformationClass" onclick="showDetailedElectionResult(this.id)">';
		elecStr+='<span id="pointerImg"> <img height="10" width="10" src="'+constituencyPageMainObj.contextPath+'/images/icons/arrow.png"/></span>';
		elecStr+='<span id=""> '+info.constituencyType+' Election Results in '+data.year+' - '+data.candidateName+' Won with '+data.votesPercentage+' votes %</span>';		
		elecStr+='</div>';
	}
	
	if(BodyElmt)
		BodyElmt.innerHTML=elecStr;
}

function showDetailedElectionResult(id)
{
	
	var index = id.substring((id.indexOf('_')+1),id.length);
	
	var data = constituencyPageMainObj.constituencyElectionInfo[index];
	var info = constituencyPageMainObj.constituencyInfo;
		
	var str='';
	str+='<fieldset id="constituencyInfoFieldSet">';
	str+='<legend> Constituency Info </legend>';
	str+='<div id="coinstituencyInfoDiv">';
	str+='<table id="constituencyInfoTableClass" class="legendTable" width="100%">';
	str+='<tr>';
	str+='<th>Constituency Name</th>';
	str+='<td>'+info.constituencyName+'</td>';
	str+='<th>Constituency Type</th>';
	str+='<td>'+info.constituencyType+'</td>';	
	str+='</tr>';

	str+='<tr>';	
	str+='<th>District</th>';
	str+='<td>'+info.districtName+'</td>';
	str+='<th>District</th>';
	str+='<td>'+info.stateName+'</td>';	
	str+='</tr>';	
	str+='</table>';
	str+='</div>';
	str+='</fieldset>';
	//--------------
	str+='<fieldset id="WinningCandidateFieldSet">';
	str+='<legend> Winning Candidate Info </legend>';
	str+='<div id="WinningCandidateDiv">';
	str+='<table id="WinningCandidateTableClass" class="legendTable" width="100%">';
	str+='<tr>';
	str+='<th>Name</th>';
	str+='<td colspan="3">'+data.candidateName+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Party</th>';
	str+='<td>'+data.partyName+'</td>';	
	str+='<th>Year</th>';
	str+='<td>'+data.year+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Votes Earned</th>';
	str+='<td>'+data.votesEarned+'</td>';	
	str+='<th>Votes Percentage</th>';
	str+='<td>'+data.votesPercentage+'</td>'
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

	ElectionResultPanel = new YAHOO.widget.Panel("electionResults_Panel", 
				{
					width:"800px", 
					fixedcenter : false, 
					visible : true,  
					constraintoviewport : true,
					x:200,
					y:400,
					iframe :true,
					modal :true,
					visible:true,						
					draggable:true, 
					close:true
				} ); 
	

	ElectionResultPanel.render();
	ElectionResultPanel.setHeader(' Election Results');
	ElectionResultPanel.setBody(str);
	
	 var myDataSource = new YAHOO.util.DataSource(data.oppositionCandInfo); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	 myDataSource.responseSchema = { 
	            fields: ["candidateName","partyName","year","votesEarned","votesPercentage"] 
	        }; 
	
	 var myColumnDefs = [ 
	            {key:"candidateName",label:'Candidate Name', sortable:true, resizeable:true}, 
	            {key:"partyName", label:'Party Name', sortable:true, resizeable:true}, 
	            {key:"year", label:'Year',sortable:true, resizeable:true}, 
	            {key:"votesEarned",label:'Votes Earned',formatter:YAHOO.widget.DataTable.formatNumber, sortable:true, resizeable:true}, 
	            {key:"votesPercentage",label:'Votes %', sortable:true, resizeable:true} 
	        ]; 
	 
	var myDataTable = new YAHOO.widget.DataTable("oppCandResultsDiv",myColumnDefs, myDataSource); 
}
function buildConstituencyLayout()
{
	var constituencyPageLayout = new YAHOO.widget.Layout('constituencyPageLayoutDiv', { 
			height:900,
			units: [					 
					{ 
						position: 'right',
						header:false,
						width: 280,							
						resize: false,
						gutter: '5px',
						collapse: false,
						scroll: true,
						body: 'constituencyPageRightMapDiv',
						animate: true
					},	 
					{ 
						position: 'center',						
						body: 'constituencyPageCenterInfoDiv',
						resize: false,
						gutter: '5px',
						collapse: true,
						scroll: true,						
						animate: true
					} 
	    ] 
		}); 
		constituencyPageLayout.render(); 
}

function buildCenterVotersCandidateInfoContent()
{
	var elmt = document.getElementById("constituencyVotersInfoDiv_Body");
	
	if(constituencyPageMainObj.constituencyVotersInfo.length == 0)
	{
		elmt.innerHTML='Voter Info Unavailable';
			return;
	}
	

	for(var i in constituencyPageMainObj.constituencyVotersInfo)
	{
		var data = constituencyPageMainObj.constituencyVotersInfo[i];

		var divChild = document.createElement('div');
		divChild.setAttribute("id","divChild"+i);

		var str = '';
		str+='<div id="divChild_Head_'+i+'" class="voterInfoHead">';
		if(data.year == "2009")
			str+='Mandals After Delimitation';
		else
			str+='Mandals Before Delimitation';
		str+='</div>';
		str+='<div id="divChild_Body_'+i+'" class="voterInfoBody"></div>';
		divChild.innerHTML=str;

		if(elmt)
			elmt.appendChild(divChild);
		
		 var myDataSource = new YAHOO.util.DataSource(data.info); 
		 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		 myDataSource.responseSchema = { 
					fields: [
								{	key : "mandalName"},
								{	key : "mandalMaleVoters",parser:"number"},
								{	key : "mandalFemaleVoters",parser:"number"},
								{	key : "mandalTotalVoters",parser:"number"}
							]
				}; 
		
		 var myColumnDefs = [ 
					{key:"mandalName",label:'Mandal Name', sortable:true, resizeable:true}, 
					{key:"mandalMaleVoters", label:'Male Voters', sortable:true, resizeable:true}, 
					{key:"mandalFemaleVoters", label:'Female Voters',sortable:true, resizeable:true}, 
					{key:"mandalTotalVoters",label:'Total Voters', sortable:true, resizeable:true}
				]; 
		 
		var myDataTable = new YAHOO.widget.DataTable("divChild_Body_"+i+"",myColumnDefs, myDataSource); 

	}
}
function initializeConstituencyPage()
{
	buildConstituencyLayout();
	buildRightlayoutMap();
	buildElectionResults();
	buildCenterVotersCandidateInfoContent();
}