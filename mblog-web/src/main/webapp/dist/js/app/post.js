
(function($){
	var mtons = window.mtons,
	Widget = mtons.Widget,
	J = jQuery;
	
	var PostView = Widget.extend({
        name : 'PostView',
        init : function (element, options) {
            Widget.fn.init.call(this, element, options);
        },
        defaults: {
        	type : 'text',
        	defaultEditor: 'ueditor',
        	maxFiles : 6,
        },
        bindEvents : function () {
        	var that = this;
        	
        	that.bindTagit();
        	that.bindValidate();
        },
        
        bindTagit : function () {
        	$('#tags').tagit({
                singleField: true,
                singleFieldNode: $('#fieldTags')
            });
        },
        
        bindValidate: function () {
        	$('form').validate({
                onKeyup: true,
                onChange: true,
                eachValidField: function () {
                    $(this).closest('div').removeClass('has-error').addClass('has-success');
                },
                eachInvalidField: function () {
                    $(this).closest('div').removeClass('has-success').addClass('has-error');
                },
                conditional: {
                    content: function () {
                        return $(this).val().trim().length > 0;
                    }
                },
                description: {
                    content: {
                        required: '<div class="alert alert-danger"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>写点内容吧</div>'
                    }
                }
            });
        },
        destroy : function () {
        }
    });
	
	this.PostView = PostView;
	
})(window.jQuery);