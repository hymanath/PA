

function buildCadreLevelTable(jsObj,cadreData)
{
	var cadresArray = new Array();
	for(var i in cadreData)
	{
		var cObj={
					name:cadreData[i].firstName+' '+cadreData[i].middleName+' '+cadreData[i].lastName,
					mobile:cadreData[i].mobile,
					landLine:cadreData[i].landLineNo,
					cadreLevel:cadreData[i].strCadreLevel+'-'+cadreData[i].strCadreLevelValue,
					email:cadreData[i].email
				 };

		cadresArray.push(cObj);
	}

	var myColumnDefs = [ 	           
							{key:"name",label : "Name",sortable:true,resizeable:true}, 
							{key:"mobile",label : "Mobile", sortable:true, resizeable:true},
							{key:"cadreLevel",label : "Cadre Level", sortable:true, resizeable:true}, 
							{key:"email",label : "Email",sortable:true, resizeable:true}
						]; 
	var myDataSource = new YAHOO.util.LocalDataSource(cadresArray); 		
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	myDataSource.responseSchema = { 
	fields : [
				{key : "name"}, {key : "mobile",parser:"number"},
				{key :"cadreLevel"},{key : "email"}
			 ]
	};
	
	var configs = {
		paginator : new YAHOO.widget.Paginator({
			rowsPerPage: 10
		})
	};
		
	getCadrePopup(myColumnDefs,myDataSource,configs);
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

function getCadrePopup(myColumnDefs,myDataSource,configs)
{
	var contentStr = '';
	contentStr +='<div id="cadreDiv"></div>';
	 var myPanel = new YAHOO.widget.Dialog("cadreDetailsPopup", {             
   
		 width : "500px", 
		 fixedcenter : true, 
		 visible : true,  
		 constraintoviewport : true, 
		  iframe :true,
		  modal :true,
		  hideaftersubmit:true,
		  close:true
	   });
	   myPanel.setHeader("Cadre Details");
	   myPanel.setBody(contentStr);
	   myPanel.render();

	   var myDataTable = new YAHOO.widget.DataTable("cadreDiv",
				myColumnDefs, myDataSource,configs);
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
		buildZeroCadreTableDistrictt(myResults, cadreData);
	if(myResults.region=='STATE')
		buildZeroCadreTableState(myResults, cadreData);
}

function buildZeroCadreTableHamlet(myResults, cadreData)
{
	var localArr = new Array();
	
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

	getCadrePopup(myColumnDefs,myDataSource,configs);
	
}
function buildZeroCadreTableVillage(myResults, cadreData)
{
	var localArr = new Array();
			
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
		{key:"state",label:"State", sortable:true, resizeable:true},
		{key:"district",label:"District", resizeable:true},
		{key:"mandal",label:"Mandal",sortable:true, resizeable:true},
		{key:"revenueVillage",label:"Revenue Village",sortable:true, resizeable:true}
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

	getCadrePopup(myColumnDefs,myDataSource,configs);
}

function buildZeroCadreTableTehsil(myResults, cadreData)
{
	var localArr = new Array();
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
			{key:"state",label:"State", sortable:true, resizeable:true},
			{key:"district",label:"District", resizeable:true},
			{key:"mandal",label:"Mandal",sortable:true, resizeable:true}
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



	getCadrePopup(myColumnDefs,myDataSource,configs);
}
function buildZeroCadreTableDistrict(myResults, cadreData)
{
	var localArr = new Array();
		
	for(var i in cadreData)
	{				
		var obj = {
			state : cadreData[i].state.name,
			district : cadreData[i].district.name		
		}
		localArr.push(obj);						
	}
	
	 var myColumnDefs = [
			{key:"state",label:"State", sortable:true, resizeable:true},
			{key:"district",label:"District", resizeable:true}					
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

	getCadrePopup(myColumnDefs,myDataSource,configs);			

}
function buildZeroCadreTableState(myResults, cadreData)
{
	var localArr = new Array();	

	for(var i in cadreData)
	{
		var obj = {					
					state : cadreData[i].state.name,								
					district : cadreData[i].district.name
		};
		localArr.push(obj);
	}
	
	 var myColumnDefs = [
			{key:"state",label:"State", sortable:true, resizeable:true},
			{key:"district",label:"District", resizeable:true}					
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

	getCadrePopup(myColumnDefs,myDataSource,configs);				
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