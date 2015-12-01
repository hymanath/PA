					
					$(document).ready(function() {
					$("#add-head,#add-content,#memberpanel,#add-search").hide();
					$("#btn-add").click(function(){
						$("#add-head,#add-content,#memberpanel,#add-search").show();
						$("#view-head,#view-content,#main-table").hide();
					});
					$("#btn-view").click(function(){
						$("#add-head,#add-content,#memberpanel,#add-search").hide();
						$("#view-head,#view-content,#main-table").show();
					});
					}); //document.ready close