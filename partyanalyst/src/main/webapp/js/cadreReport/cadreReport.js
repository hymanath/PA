

function buildCadreLevelTable(jsObj,cadreData)
{
	var cadresArray = new Array();
	var cadreDetailsPanel;
	var title = cadreData.length+" Cadres available in "+jsObj.nodeLabel+" Level";
	var update = "No Access";
	var remove = "No Access";

	for(var i in cadreData)
	{
		if(cadreUpdate)			
			update = '<A href="javascript:{}" onclick="openRegistrationForm('+cadreData[i].cadreID+')"><img width="20" height="20" src="images/icons/edit.png" style="text-decoration:none;border:0px;"></A>';
		if(cadreDelete)
			remove = '<A href="javascript:{}" onclick="deleteCadre('+cadreData[i].cadreID+')"><img width="18" height="18" src="images/icons/delete.png" style="text-decoration:none;border:0px;"></A>';

		var cObj={
				name:cadreData[i].firstName+' '+cadreData[i].middleName+' '+cadreData[i].lastName,
				mobile:cadreData[i].mobile,
				cadreLevel: cadreData[i].strCadreLevel+'-'+cadreData[i].strCadreLevelValue,
				address: cadreData[i].villageName+', '+cadreData[i].mandalName+', '+cadreData[i].districtName,
				memberType: cadreData[i].memberType,
				casteCategory: cadreData[i].casteCategoryStr,
				moreDetails:'<a href="javascript:{}" onclick="getCadreInfo(\''+cadreData[i].cadreID+'\')">More Details</a>',
				update:update,
				remove:remove
				 };

		cadresArray.push(cObj);
	}

	var myColumnDefs = [ 	           
		{key:"name",label : "Name",sortable:true}, 
		{key:"mobile",label : "Mobile"}, 
		{key:"cadreLevel",label : "Cadre Level", sortable:true}, 
		{key:"address",label : "Address", sortable:true},
		{key:"memberType",label : "Cadre Type", sortable:true},
		{key:"casteCategory",label : "Caste Category", sortable:true},
		{key:"moreDetails",label : "More Details"},
		{key:"update",label : "Edit"},
		{key:"remove",label : "Remove"}
						]; 
	var myDataSource = new YAHOO.util.LocalDataSource(cadresArray); 		
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	myDataSource.responseSchema = { 
	fields : [
				{key : "name"}, {key : "mobile",parser:"number"}, {key : "address"},{key: "casteCategory"}, 
				{key :"cadreLevel"},{key : "memberType"},{key : "update"},{key:"remove"},{key:"moreDetails"}
			 ]
	};
	
	var configs = {
		paginator : new YAHOO.widget.Paginator({
			rowsPerPage: 10
		})
	};
		
	getCadrePopup(myColumnDefs,myDataSource,configs,title);
}

function buildZeroCadreTree(id,val)
{								
	var divElmt = document.getElementById("zeroCadreInfoDivBody");
	
	if(!divElmt)
		alert("No div element present to create elements");

	var str = '';
	str += '<table width="85%" style="width:85%">';
	str += '<tr>';
	str += '<td width="5%"> <img src="images/icons/indexPage/listIcon.png"/> </td>';
	str += '<td width="40%">'+val+' - '+id+'</td>';
	str += '<td width="25%"><a href="javascript:{}" class="viewCadreDetailsAnc" onclick="showCadreDetails(\''+id+'\',\''+val+'\',\'ZeroLevelCadre\')"> View Details </a></td>';
	str += '</tr>';
	str += '</table>';	

	var childDivElmt = document.createElement('div');
	childDivElmt.setAttribute('id',val+''+id+'ZeroCadreDiv');
	childDivElmt.setAttribute('className','regionCadreDivClass');
	childDivElmt.setAttribute('class','regionCadreDivClass');

	childDivElmt.innerHTML = str;
	divElmt.appendChild(childDivElmt);
}

function buildRegionCadreTree(id,val)
{			
	var divElmt = document.getElementById("cadreLevelInfoDivBody");
	
	if(!divElmt)
		alert("No div element present to create tree");

	var str = '';
	str += '<table width="85%" style="width:85%">';
	str += '<tr>';
	str += '<td width="5%"> <img src="images/icons/indexPage/listIcon.png"/> </td>';
	str += '<td width="40%">'+val+' - '+id+'</td>';
	str += '<td width="25%"><a href="javascript:{}" class="viewCadreDetailsAnc" onclick="showCadreDetails(\''+id+'\',\''+val+'\',\'RegionLevelCadre\')"> View Details </a></td>';
	str += '</tr>';
	str += '</table>';	

	var childDivElmt = document.createElement('div');
	childDivElmt.setAttribute('id',val+''+id+'RegioncadreDiv');
	childDivElmt.setAttribute('class','regionCadreDivClass');
	childDivElmt.setAttribute('className','regionCadreDivClass');

	childDivElmt.innerHTML = str;
	divElmt.appendChild(childDivElmt);			
}		

function getCadrePopup(myColumnDefs,myDataSource,configs, title)
{
	var contentStr = '';
	contentStr +='<div style="text-align:left;color:#707070;font-size:12px;font-family:verdana;font-weight:bold;">'+title+'</div>';
	contentStr +='<div id="cadreDiv_outer" class="yui-skin-sam"><div id="cadreDiv"></div></div>';

	var elmt = document.getElementById("cadreDetailsPopup");
	if(elmt)
		elmt.innerHTML = contentStr;

	$( "#cadreDetailsPopup" ).dialog({
			title:"Cadres Details",
			autoOpen: true,
			show: "blind",
			width: 1000,
			minHeight:400,
			modal: true,
			hide: "explode",
		    close: function(ev, ui) { dialogClose(); }

		});
	
	

	/*cadreDetailsPanel = new YAHOO.widget.Dialog("cadreDetailsPopup", {             
		
	 fixedcenter : false, 
	 visible : true,  
	 constraintoviewport : true, 
	 iframe :true,
	 modal :true,
	 hideaftersubmit:true,
	 close:true		 
	 });	 
	 
	 cadreDetailsPanel.setHeader("Cadres Details");
	 cadreDetailsPanel.setBody(contentStr);
	 cadreDetailsPanel.render();*/

	   var myDataTable = new YAHOO.widget.DataTable("cadreDiv",
				myColumnDefs, myDataSource,configs);
}
function dialogClose()
{
  //alert('close dialogBox');
  //window.location.reload(true);
}

function buildZeroCadreTable(myResults, cadreData)
{
	if(myResults.region=='HAMLET')
		buildZeroCadreTableHamlet(myResults, cadreData);
	if(myResults.region=='VILLAGE')
		buildZeroCadreTableVillage(myResults, cadreData);
	if(myResults.region=='MANDAL')
		buildZeroCadreTableTehsil(myResults, cadreData);
	if(myResults.region=='DISTRICT')
		buildZeroCadreTableDistrict(myResults, cadreData);
	if(myResults.region=='CONSTITUENCY')
		buildZeroCadreTableConstituency(myResults, cadreData);
    if(myResults.region=='PARLIAMENT CONSTITUENCY')
		buildZeroCadreTableForPArlConstituency(myResults, cadreData);			
	if(myResults.region=='STATE')
		buildZeroCadreTableState(myResults, cadreData);
	if(myResults.region=='MUNICIPAL/CORP/GMC')
		buildZeroCadreTableLocalElectionBodies(myResults, cadreData);	
	if(myResults.region=='WARDS')
		buildZeroCadreTableWards(myResults, cadreData);	
	if(myResults.region=='BOOTHS IN MANDALS')
		buildZeroCadreTableBoothsInMandals(myResults, cadreData);	
	if(myResults.region=='BOOTHS In Municipal/Corp/GMC')
		buildZeroCadreTableBoothsInLEB(myResults, cadreData);	
	
}

function buildZeroCadreTableBoothsInMandals(myResults, cadreData)
{
	var localArr = new Array();
	var title='';
	for(var i in cadreData)
	{
		var obj = {				
					mandal : cadreData[i].mandal.name,
					partNo: cadreData[i].booth.id,
					booth : cadreData[i].booth.name						
				  }
		localArr.push(obj);
	}

	var myColumnDefs = [
	
	{key:"mandal",label:"Mandal",sortable:true, resizeable:true},
	{key:"partNo",label:"Booth Part No",sortable:true, resizeable:true},	
	{key:"booth",label:"Booth Location", resizeable:true}
	];
	
	var configs = {
						paginator: new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10			        
						})
				   };
	var myDataSource = new YAHOO.util.DataSource(localArr);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource.responseSchema = {
		fields: ["mandal","partNo","booth"]
	};					

	getCadrePopup(myColumnDefs,myDataSource,configs,title);

	
}

function buildZeroCadreTableBoothsInLEB(myResults, cadreData)
{
var localArr = new Array();
	var title='';
	for(var i in cadreData)
	{
		var obj = {	
					localBody: cadreData[i].localElectionBody.name,
					partNo: cadreData[i].booth.id,
					booth : cadreData[i].booth.name						
				  }
		localArr.push(obj);
	}

	var myColumnDefs = [
	
	{key:"localBody",label:"Municipal/Corp/GMC",sortable:true, resizeable:true},
	{key:"partNo",label:"Booth Part No",sortable:true, resizeable:true},	
	{key:"booth",label:"Booth Location", resizeable:true}
	
	];
	
	var configs = {
						paginator: new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10			        
						})
				   };
	var myDataSource = new YAHOO.util.DataSource(localArr);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource.responseSchema = {
		fields: ["localBody","partNo","booth"]
	};					

	getCadrePopup(myColumnDefs,myDataSource,configs,title);

	
}
function buildZeroCadreTableHamlet(myResults, cadreData)
{
	var localArr = new Array();
	var title='';
	for(var i in cadreData)
	{
		var obj = {				
					state : cadreData[i].state.name,
					district : cadreData[i].district.name,
					mandal : cadreData[i].mandal.name,
					revenueVillage : cadreData[i].revenueVillage.name,
					hamlet : cadreData[i].hamlet.name	
				  }
		localArr.push(obj);
	}

	var myColumnDefs = [
	{key:"state",label:"State", sortable:true, resizeable:true},
	{key:"district",label:"District", resizeable:true},
	{key:"mandal",label:"Mandal",sortable:true, resizeable:true},
	{key:"revenueVillage",label:"Revenue Village",sortable:true, resizeable:true},
	{key:"hamlet",label:"Hamlet",sortable:true, resizeable:true}
	];
	
	var configs = {
						paginator: new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10			        
						})
				   };
	var myDataSource = new YAHOO.util.DataSource(localArr);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource.responseSchema = {
		fields: ["state","district","mandal","revenueVillage","hamlet"]
	};					

	getCadrePopup(myColumnDefs,myDataSource,configs,title);
	
}
function buildZeroCadreTableLocalElectionBodies(myResults, cadreData)
{
	var title='';
	var localArr = new Array();
	for(var i in cadreData)
	{
		var obj = {
					state:cadreData[i].state.name,
					district:cadreData[i].district.name,
					localElectionBody:cadreData[i].localElectionBody.name
				  };
		localArr.push(obj);
	}

	
	  var myColumnDefs = [
			{key:"state",label:"State", sortable:true, resizeable:true},
			{key:"district",label:"District"},
			{key:"localElectionBody",label:"Municipal-Corp-GMC",sortable:true}
		];
		
		var configs = {
							paginator: new YAHOO.widget.Paginator({ 
							rowsPerPage    : 10			        
							})
					   };
		var myDataSource = new YAHOO.util.DataSource(localArr);
		myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		myDataSource.responseSchema = {
			fields: ["state","district","localElectionBody"]
		};					



	getCadrePopup(myColumnDefs,myDataSource,configs,title);
}

function buildZeroCadreTableWards(myResults, cadreData)
{
	var localArr = new Array();
	var title='';
	for(var i in cadreData)
	{
		var obj = {
					state:cadreData[i].state.name,
					district:cadreData[i].district.name,
					localElectionBody:cadreData[i].localElectionBody.name,
					ward:cadreData[i].ward.name
				  };
		localArr.push(obj);
	}

	
	  var myColumnDefs = [
			{key:"state",label:"State", sortable:true},
			{key:"district",label:"District"},
			{key:"localElectionBody",label:"Municipal-Corp-GMC",sortable:true},
			{key:"ward",label:"Ward",sortable:true}
		];
		
		var configs = {
							paginator: new YAHOO.widget.Paginator({ 
							rowsPerPage    : 10			        
							})
					   };
		var myDataSource = new YAHOO.util.DataSource(localArr);
		myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		myDataSource.responseSchema = {
			fields: ["state","district","localElectionBody","ward"]
		};					



	getCadrePopup(myColumnDefs,myDataSource,configs,title);
}
function buildZeroCadreTableConstituency(myResults, cadreData)
{
	var localArr = new Array();
	var title='';
	for(var i in cadreData)
	{
		var obj = {
					state:cadreData[i].state.name,
					district:cadreData[i].district.name,
					constituency:cadreData[i].constituency.name
				  };
		localArr.push(obj);
	}

	
	  var myColumnDefs = [
			{key:"state",label:"State", sortable:true},
			{key:"district",label:"District"},
			{key:"constituency",label:"Constituency",sortable:true}
		];
		
		var configs = {
							paginator: new YAHOO.widget.Paginator({ 
							rowsPerPage    : 10			        
							})
					   };
		var myDataSource = new YAHOO.util.DataSource(localArr);
		myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		myDataSource.responseSchema = {
			fields: ["state","district","constituency"]
		};					



	getCadrePopup(myColumnDefs,myDataSource,configs,title);
}
function buildZeroCadreTableForPArlConstituency(myResults, cadreData)
{
   
	var localArr = new Array();
	var title='';
	for(var i in myResults.zeroCadresRegion)
	{
		var obj = {
					state:cadreData[i].state.name,
					constituency:cadreData[i].constituency.name
				  };
		localArr.push(obj);
	}

	
	  var myColumnDefs = [
			{key:"state",label:"State", sortable:true},
			{key:"constituency",label:"Constituency",sortable:true}
		];
		
		var configs = {
							paginator: new YAHOO.widget.Paginator({ 
							rowsPerPage    : 10			        
							})
					   };
		var myDataSource = new YAHOO.util.DataSource(localArr);
		myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		myDataSource.responseSchema = {
			fields: ["state","constituency"]
		};					



	getCadrePopup(myColumnDefs,myDataSource,configs,title);
 
}
function buildZeroCadreTableVillage(myResults, cadreData)
{
	var localArr = new Array();
	var title='';		
	for(var i in cadreData)
	{		
		var obj = {
					state:cadreData[i].state.name,
					district:cadreData[i].district.name,
					mandal:cadreData[i].mandal.name,
					revenueVillage : cadreData[i].revenueVillage.name											
				  }
		localArr.push(obj);
	}				
	
	
	var myColumnDefs = [
		{key:"state",label:"State", sortable:true},
		{key:"district",label:"District"},
		{key:"mandal",label:"Mandal",sortable:true},
		{key:"revenueVillage",label:"Village",sortable:true}
	];
	
	var configs = {
						paginator: new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10			        
						})
				   };
	var myDataSource = new YAHOO.util.DataSource(localArr);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource.responseSchema = {
		fields: ["state","district","mandal","revenueVillage"]
	};					

	getCadrePopup(myColumnDefs,myDataSource,configs,title);
}

function buildZeroCadreTableTehsil(myResults, cadreData)
{
	var localArr = new Array();
	var title='';
	for(var i in cadreData)
	{
		var obj = {
					state:cadreData[i].state.name,
					district:cadreData[i].district.name,
					mandal:cadreData[i].mandal.name
				  };
		localArr.push(obj);
	}

	
	  var myColumnDefs = [
			{key:"state",label:"State", sortable:true},
			{key:"district",label:"District"},
			{key:"mandal",label:"Mandal",sortable:true}
		];
		
		var configs = {
							paginator: new YAHOO.widget.Paginator({ 
							rowsPerPage    : 10			        
							})
					   };
		var myDataSource = new YAHOO.util.DataSource(localArr);
		myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		myDataSource.responseSchema = {
			fields: ["state","district","mandal"]
		};					



	getCadrePopup(myColumnDefs,myDataSource,configs,title);
}
function buildZeroCadreTableDistrict(myResults, cadreData)
{
	var localArr = new Array();
	var title='';	
	for(var i in cadreData)
	{				
		var obj = {
			state : cadreData[i].state.name,
			district : cadreData[i].district.name		
		}
		localArr.push(obj);						
	}
	
	 var myColumnDefs = [
			{key:"state",label:"State", sortable:true},
			{key:"district",label:"District"}					
		];
		
	var configs = {
						paginator: new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10			        
						})
				   };
	var myDataSource = new YAHOO.util.DataSource(localArr);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource.responseSchema = {
		fields: ["state","district"]
	};					

	getCadrePopup(myColumnDefs,myDataSource,configs,title);			

}
function buildZeroCadreTableState(myResults, cadreData)
{
	var localArr = new Array();	
	var title='';
	for(var i in cadreData)
	{
		var obj = {					
					state : cadreData[i].state.name,								
					district : cadreData[i].district.name
		};
		localArr.push(obj);
	}
	
	 var myColumnDefs = [
			{key:"state",label:"State", sortable:true},
			{key:"district",label:"District"}					
		];
		
	var configs = {
						paginator: new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10			        
						})
				   };
	var myDataSource = new YAHOO.util.DataSource(localArr);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource.responseSchema = {
		fields: ["state","district"]
	};					

	getCadrePopup(myColumnDefs,myDataSource,configs,title);				
}



function buildcadreReportLayout()
{
	var cadreReportPageLayout = new YAHOO.widget.Layout('cadreReportLayout_main', { 
	height:600,
	units: [
			{ 
				position: 'top', 
				height: 50,
				header:false,
				body: 'cadreReportLayout_top',
				resize: false,
				gutter: '2px',
				collapse: false,
				scroll: true,						
				animate: true 
			},
			{ 
				position: 'right', 
				width: 280,
				header:false,
				body: 'cadreReportLayout_right',
				resize: false,
				gutter: '2px',
				collapse: false,
				scroll: true,						
				animate: true 
			}, 					 
			{ 
				position: 'center',						
				body: 'cadreReportLayout_center',
				resize: false,
				gutter: '2px',
				collapse: true,
				scroll: true,						
				animate: true
			} 
		 ] 
		
	});
	cadreReportPageLayout.render(); 
}


function initializeCadreReport()
{
	buildcadreReportLayout();
}