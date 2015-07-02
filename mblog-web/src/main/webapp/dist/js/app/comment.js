
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
        		var pid = $('#chat_pid').val();
        		that.post(that.options.toId, pid, text);
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
        		
        		$('#chat_count').html(ret.totalCount);
        		
          		jQuery.each(ret.results, function(i, n) {
          			var reply = n.id;
    				var item = opts.onLoad.call(this, i, n, reply, 'pat');

    				html += item;
    				
    				if (n.children.length > 0) {
    					jQuery.each(n.children, function(j, child) {
    	    				var sub = opts.onLoad.call(this, j, child, reply, 'child');
    	    				html += sub;
    					});
    				}
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
        
        post: function (toId, pid, text) {
        	var opts = this.options;
        	var that = this;
        	
        	if (text.length == 0) {
        		layer.msg('请输入内容再提交!', {icon: 2});
        		return false;
        	}
        	if (text.length > 255) {
        		layer.msg('内容过长，请输入140以内个字符', {icon: 2});
        		return false;
        	}
        	
        	jQuery.ajax({
        		url: opts.post_url, 
        		data: {'toId': toId,'pid': pid, 'text': text},
        		dataType: "json",
        		type :  "POST",
        		cache : false,
        		async: false,
        		error : function(i, g, h) {
        			layer.msg('发送错误', {icon: 2});
        		},
        		success: function(ret){
        			if(ret){
        				if (ret.code >= 0) {
        					layer.msg(ret.message, {icon: 1});
        					$('#chat_text').val('');
        					$('#chat_reply').hide();
        					$('#chat_pid').val('0');
        					//window.location.reload();
        					that.pageCallback(1);
        				} else {
        					layer.msg(ret.message, {icon: 5});
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