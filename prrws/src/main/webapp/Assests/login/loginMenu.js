
 function buildMenu(result){
	  var iconsArr=["fa-pencil-square-o","fa-eye","fa-qrcode"];
	  
			var str='';	
					
							for(var i in result){
								
							
								str+='<div class="panel-group" style="padding:5px;">';
										str+='<div class="panel panel-default">';
											str+='<div class="panel-heading">';
												str+='<h4 class="panel-title">';
													str+='<a data-toggle="collapse" href="#collapse'+i+'">'+result[i].name+'&nbsp;&nbsp;<i class="fa fa-bars fa-lg" aria-hidden="true"></i></a>';
												str+='</h4>';
											str+='</div>';
											str+='<div id="collapse'+i+'" class="panel-collapse collapse">';
												str+='<ul class="list-group">';
											
													for(var j in result[i].subList){
														
													str+='<li class="list-group-item"><a href="'+result[i].subList[j].url+'">'+result[0].subList[j].name+'</a><span class="fa fa-lg '+iconsArr[j]+' pull-right"></span></li>';
													}
												
												str+='</ul>	';
											str+='</div>';
										str+='</div>';
									str+='</div>';		
							
							}
						
				$("#menuId").html(str);
			
				
 } 