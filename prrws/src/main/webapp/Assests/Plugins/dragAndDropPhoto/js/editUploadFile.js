/*swadhin*/
function initializeEditFileUploadMainBlock(typeVal,candidateId,divId) {
	//alert(candidateId)
	var itemStr = '';
	var itemAppendStr = '';
	itemStr+='<li class="jFiler-item">';
                    itemStr+='<div class="jFiler-item-container" style="margin:10px 20px 30px 0">';
                        itemStr+='<div class="jFiler-item-inner">';
                           itemStr+='<div class="jFiler-item-thumb">';
                                itemStr+='<div class="jFiler-item-status"></div>';
                                itemStr+='<div class="jFiler-item-info">';
                                    itemStr+='<span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>';
                                    itemStr+='<span class="jFiler-item-others">{{fi-size2}}</span>';
                                itemStr+='</div>';
                                itemStr+='{{fi-image}}';
                            itemStr+='</div>';
                            itemStr+='<div class="jFiler-item-assets jFiler-row">';
                                itemStr+='<ul class="list-inline pull-left">';
                                    itemStr+='<li>{{fi-progressBar}}</li>';
                                itemStr+='</ul>';
                                itemStr+='<ul class="list-inline pull-right">';
                                    itemStr+='<li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>';
                                itemStr+='</ul>';
                            itemStr+='</div>';
                        itemStr+='</div>';
                    itemStr+='</div>';
                itemStr+='</li>';
				
				
				itemAppendStr+='<li class="jFiler-item">';
				itemAppendStr+='<div class="jFiler-item-container">';
				itemAppendStr+='<div class="jFiler-item-inner">';
					itemAppendStr+='<div class="jFiler-item-thumb">';
						itemAppendStr+='<div class="jFiler-item-status"></div>';
						itemAppendStr+='<div class="jFiler-item-info">';
							itemAppendStr+='<span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>';
							itemAppendStr+='<span class="jFiler-item-others">{{fi-size2}}</span>';
						itemAppendStr+='</div>';
						itemAppendStr+='{{fi-image}}';
					itemAppendStr+='</div>';
					itemAppendStr+='<div class="jFiler-item-assets jFiler-row">';
						itemAppendStr+='<ul class="list-inline pull-left">';
							itemAppendStr+='<li><span class="jFiler-item-others">{{fi-icon}}</span></li>';
						itemAppendStr+='</ul>';
						itemAppendStr+='<ul class="list-inline pull-right">';
							itemAppendStr+='<li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>';
						itemAppendStr+='</ul>';
					itemAppendStr+='</div>';
				itemAppendStr+='</div>';
			itemAppendStr+='</div>';
		itemAppendStr+='</li>';  
				
	$('#'+divId+candidateId+typeVal).filer({      
    changeInput: '<div class="jFiler-input-dragDrop" style="margin:0px;padding:10px;"><a class="jFiler-input-choose-btn blue">Browse <b style="color:green;"> PDF / IMAGES </b> Files (<i style="color:red">Max size 10 MB</i>) </a></div></div>',
    showThumbs: true,
    theme: "dragdropbox",
    extensions :["pdf","bmp","png","jpg","jpeg","tiff"],
    templates: {  
        box: '<ul class="jFiler-items-list jFiler-items-grid"></ul>',
        item: itemStr,
		itemAppend: itemAppendStr,   
		progressBar: '<div class="bar"></div>',
		itemAppendToEnd: true,
            removeConfirmation: true,
            _selectors: {
                list: '.jFiler-items-list',
                item: '.jFiler-item',
                progressBar: '.bar',
                remove: '.jFiler-item-trash-action'
            }
        },
        dragDrop: {
            dragEnter: null,
            dragLeave: null,
            drop: null,
        },
      /*  uploadFile: {
		  
            url: "nominatedPostUploadFormAction.action",
			//autoProcessQueue: false,
            data: {"nominatedCandId":globalNominatedCandId},
            type: 'POST',
			
           enctype: 'multipart/form-data',
            beforeSend: function(){},
            success: function(data, el){
				if(data.resultCode==0){
					$("#savingStatusDivId").html("");
					var parent = el.find(".jFiler-jProgressBar").parent();
					el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
						$("<div class=\"jFiler-item-others text-success\"><i class=\"icon-jfi-check-circle\"></i> Success</div>").hide().appendTo(parent).fadeIn("slow");    
					});
				}else{
					var parent = el.find(".jFiler-jProgressBar").parent();
					el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
						$("<div class=\"jFiler-item-others text-error\"><i class=\"icon-jfi-minus-circle\"></i> Error</div>").hide().appendTo(parent).fadeIn("slow");    
					});
				}
                
				//window.location.href="eventFieUploadAction.action";
            },
            error: function(el){
                var parent = el.find(".jFiler-jProgressBar").parent();
                el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
                    $("<div class=\"jFiler-item-others text-error\"><i class=\"icon-jfi-minus-circle\"></i> Error</div>").hide().appendTo(parent).fadeIn("slow");    
                });
            },
            statusCode: null,
            onProgress: null,
            onComplete: null
        },
		  */
		
		files: null,
        addMore: true,
        clipBoardPaste: true,
        excludeName: null,
        beforeRender: null,
        afterRender: null,
        beforeShow: null,
        beforeSelect: null,
        onSelect: null,
        afterShow: null,
       
        onEmpty: null,
        options: null,
        captions: {
            button: "Choose Files",
            feedback: "Choose files To Upload",
            feedback2: "files were chosen",
            drop: "Drop file here to Upload",
            removeConfirmation: "Are you sure you want to remove this file?",
            errors: {
                filesLimit: "Only {{fi-limit}} files are allowed to be uploaded.",
                filesType: " Only PDF / IMAGES are allowed ",
                filesSize: "{{fi-name}} is too large! Please upload file up to {{fi-maxSize}} MB.",
                filesSizeAll: "Files you've choosed are too large! Please upload files up to {{fi-maxSize}} MB."
            }
        }
		
	});
	/*var filerKit = $("#filer_input3").prop("jFiler");
	filerKit.reset();*/
	}
