
(function($){
	var mtons = window.mtons,
	Widget = mtons.Widget,
	J = jQuery;
	
	var Comment = Widget.extend({
        name : 'Comment',
        init : function (element, options) {
            Widget.fn.init.call(this, element, options);
        },
        defaults: {
        	load_url : null,
        	maxResults :6,
            // callback
        	onLoad : function (i, data) {}
        },
        bindEvents : function () {
        },
        
        onLoad : function () {
        	this.pageCallback(1);
        },
        
        pageCallback: function (pn) {
        	var opts = this.options;
        	
        	var html = '';
        	var $list = $('#comments');
        	
        	J.getJSON(opts.load_url, {maxResults : opts.maxResults}, function (ret) {
          		jQuery.each(ret.results, function(i, n) {
    				var item = opts.onLoad.call(this, i, n);
    				html += item;
          		});
        	});
        	
        	if (ret.size < 1) {
        		html = '<li><p>空空如也, 快来占沙发吧!</p></li>';
        	} else {
        		$list.empty().append(html);
        	}
        	
        	if (paging.pageCount > 1) {
    			$("#pager").page(paging, callback);
    		}
        },
        destroy : function () {
        }
    });
	
	this.Comment = Comment;
	
})(window.jQuery);