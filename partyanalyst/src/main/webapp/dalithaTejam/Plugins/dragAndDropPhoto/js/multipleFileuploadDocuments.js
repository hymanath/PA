/*swadhin*/
function initializeMultipleUploadDocument(id) {
	//alert(candidateId)
	var itemStr = '';

	itemStr+='<li class="jFiler-item">';
			itemStr+='<div class="jFiler-item-container" style="margin:0px;padding:0px;border:0px;">';
				itemStr+='<span style="margin-top: -12px;position: relative;top: 13px;z-index: 9999;background-color:#e5e5e5;left: 3px;border-radius: 50%;padding: 2px;display: block;border: 1px solid #ddd;height: 16px;" class="pull-right"><a style="color:#000;position: relative;top: -4px;"class="fa fa-times jFiler-item-trash-action"></a></span>';
				itemStr+='<div class="jFiler-item-inner">';
				   itemStr+='<div class="jFiler-item-thumb" style="width:50px;height:50px;min-height:50px;">';
						itemStr+='<div class="jFiler-item-status"></div>';
						itemStr+='<div class="jFiler-item-info">';
							//itemStr+='<span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>';
							//itemStr+='<span class="jFiler-item-others">{{fi-size2}}</span>';
						itemStr+='</div>';
						itemStr+='{{fi-image}}';
					itemStr+='</div>';
					
				itemStr+='</div>';
			itemStr+='</div>';
		itemStr+='</li>'; 
	
	$('#'+id).filer({      
    changeInput: '<div class="jFiler-input-dragDrop" style="margin:0px;padding:5px;border:none;"><a class="jFiler-input-choose-btn blue selectFileCss">Upload Floor Images</a></div></div>',
    showThumbs: true,
    theme: "dragdropbox",
    templates: {  
        box: '<ul class="jFiler-items-list jFiler-items-grid"></ul>',
        item: itemStr,
		
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
	$('.tooltipCls').tooltip();
	$(".jFiler-item-thumb-image img").css("width","30px !important")
	$(".jFiler-item-thumb-image img").css("height","30px !important")
	/*var filerKit = $("#filer_input3").prop("jFiler");
	filerKit.reset();*/
	}
