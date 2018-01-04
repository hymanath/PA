/*swadhin*/
function initializeSingleUploadDocument(id) {
	//alert(candidateId)
	var itemStr = '';
	itemStr+='<li class="jFiler-item"><span class="jFiler-item-others">{{fi-name}}</span> <span class="tooltipCls" data-toggle="tooltip" data-placement="top" title="Remove Work Document"><a class="icon-jfi-trash jFiler-item-trash-action"></a></span></li>';
	
	$('#'+id).filer({      
    changeInput: '<div class="jFiler-input-dragDrop" style="margin:0px;padding:5px;border:none;"><a class="jFiler-input-choose-btn blue selectFileCss">Select File</a></div></div>',
    showThumbs: true,
    theme: "dragdropbox",
    templates: {  
        box: '<ul class="jFiler-items-list jFiler-items-grid"></ul>',
        item: itemStr,
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
     	files: null,
        addMore: false,
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
	/*var filerKit = $("#filer_input3").prop("jFiler");
	filerKit.reset();*/
	}
