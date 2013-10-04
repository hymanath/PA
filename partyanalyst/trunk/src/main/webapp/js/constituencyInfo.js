function buildConstituencyBasicInfo(results)
	{
 	
        $("#basicInfoAjaxImage").css("display","none");
	    var mandalList = results[0].mandalList;
		var localbodyList = results[0].localbodiesList;
		var divEle =document.getElementById("constituencyBasicInfo");
		var str='';
		if(mandalList != null && mandalList.length > 0)
		{
		str+='<table class="table table-bordered table-hover">';
		str+='<tr>';
		str+='<th>Mandal</th>';
		str+='<th>Panchayat</th>';
		str+='<th>Booth</th>';
		str+='<th>Hamlet</th>';
		str+='</tr>';
		for(var i in mandalList)
		{
		for(var j=0;j<mandalList[i].selectOptionsList.length;j++)
			{
		 str+='<tr>';
		 str+='<td>'+mandalList[i].name+'</td>';
		 str+='<td>'+mandalList[i].selectOptionsList[j].name+'</td>';
		  str+='<td>';
		 for(var k=0;k<mandalList[i].selectOptionsList[j].selectOptionsList.length;k++)
				{
			 var temp =mandalList[i].selectOptionsList[j].selectOptionsList.length - 1; 
		str+='<span>'+mandalList[i].selectOptionsList[j].selectOptionsList[k].name+'</span> ';
		if(k!=temp)
		str +=',  ';
				}
		str+='</td>';
		 str+='<td>';
		 for(var k1=0;k1<mandalList[i].selectOptionsList[j].selectOptionsList1.length;k1++)
				{
		 var temp1 =mandalList[i].selectOptionsList[j].selectOptionsList1.length - 1; 
		str+='<span>'+mandalList[i].selectOptionsList[j].selectOptionsList1[k1].name+'</span> ';
		if(k1!=temp1)
		str +=',  ';
				}
		str+='</td>';
	   	str+='</tr>';
			}
	
	}
		str+='</table>';
		str+='<br/><br/>';
		}
		if(localbodyList != null && localbodyList.length > 0)
		{
		str+='<table class="table table-bordered table-hover">';
		str+='<tr>';
		str+='<th>Muncipality</th>';
		str+='<th>Ward</th>';
		
		str+='<th>Booth</th>';
		str+='</tr>';
		str+='<tr>';
  for(l=0;l<localbodyList.length;l++)
		 	 {
	str+='<td>'+localbodyList[l].name+'</td>';
	str+='<td>';
	 for(m=0;m<localbodyList[l].selectOptionsList.length;m++)
				 {
	 var temp1 =  localbodyList[l].selectOptionsList.length - 1;
	 str+=''+localbodyList[l].selectOptionsList[m].name+'';
	       if(m !=temp1)
		 	 str+=', ';
				 }
				 str+='</td>'
				 
				  str+='<td>'
				  
	 for(n=0;n<localbodyList[l].selectOptionsList1.length;n++)
				 {
		 var temp2 = localbodyList[l].selectOptionsList1.length - 1;
         str+=''+localbodyList[l].selectOptionsList1[n].name+'';
		 if(n !=temp2)
			 str+=', ';
				 }
			 str+='</td>'
		 }
 	 str+='</tr>';
	 str+='</table>';
	 }
	divEle.innerHTML = str;
	}