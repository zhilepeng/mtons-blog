
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
        	post_url : null,
        	toId : 0,
        	maxResults :6,
            // callback
            onLoad : function (i, data) {}
        },
        bindEvents : function () {
        	var that = this;
        	that.pageCallback(1);
        	
        	$('#btn-chat').click(function () {
        		var text = $('#chat_text').val();
        		that.post(that.options.toId, text);
        	});
        },
        
        onLoad : function () {
        	this.pageCallback(1);
        },
        
        pageCallback: function (pn) {
        	var opts = this.options;
        	var that = this;
        	
        	var $list = this.element;
        	var html = '';

        	J.getJSON(opts.load_url, {maxResults : opts.maxResults, pn: pn}, function (ret) {
          		jQuery.each(ret.results, function(i, n) {
    				var item = opts.onLoad.call(this, i, n);
    				html += item;
          		});
        	
	        	$list.empty().append(html);
	        	
	    		if (ret.size < 1) {
	    			$list.append('<li><p>还没有评论, 快来占沙发吧!</p></li>');
	    		}
	    		if (ret.pageCount > 1) {
	    			$("#pager").page(ret, J.proxy(that, 'pageCallback'));
	    		}
        	});
        },
        
        post: function (toId, text) {
        	var opts = this.options;
        	var that = this;
        	
        	if (text.length == 0) {
        		alert('请输入内容再提交!');
        		return false;
        	}
        	if (text.length > 255) {
        		alert('内容过长，请输入140以内个字符');
        		return false;
        	}
        	
        	jQuery.ajax({
        		url: opts.post_url, 
        		data: {'toId': toId, 'text': text},
        		dataType: "json",
        		type :  "POST",
        		cache : false,
        		error : function(i, g, h) {
        			alert("发生错误");
        		},
        		success: function(ret){
        			if(ret){
        				if (ret.code >= 0) {
        					$('#chat_text').val('');
        					
        					that.pageCallback(1);
        				} else {
        					alert(ret.message);
        				}
        			}
              	}
        	});
        },
        
        destroy : function () {
        }
    });
	
	this.Comment = Comment;
	
})(window.jQuery);