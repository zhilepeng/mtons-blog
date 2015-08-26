/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/

define(function(require, exports, module) {
	J = jQuery;
	require('plugins');
	
	var Sidebox = {
        init : function (options) {
        	this.options = $.extend({}, this.defaults, options);
        },
        defaults: {
			latestUrl : '',
        	hotUrl : '',
			hotTagUrl : '',
        	maxResults :6,
            // callback
            onLoadLatest : function (i, data) {},
            onLoadHot : function (i, data) {}
        },
        
        onLoad : function () {
        	var opts = this.options;
        	// load hots
			if (opts.hotUrl) {
				J.getJSON(opts.hotUrl, {maxResults : opts.maxResults}, function (ret) {
					if(ret && ret.length > 0){
						$('#hots').empty();
						jQuery.each(ret, function(i, n) {
							var item = opts.onLoadHot.call(this, i, n);
							$('#hots').append(item);
						});
					} else {
						$('#hots').append('<li class="cat-item cat-item-6"><span>沒有相关记录</span></li>');
					}
				});
			}

			if (opts.latestUrl) {
				J.getJSON(opts.latestUrl, {maxResults: opts.maxResults}, function (ret) {
					if (ret && ret.length > 0) {
						$('#latests').empty();
						jQuery.each(ret, function (i, n) {
							var item = opts.onLoadLatest.call(this, i, n);
							$('#latests').append(item);
						});
					} else {
						$('#latests').append('<li class="cat-item cat-item-6"><span>沒有相关记录</span></li>');
					}
				});
			}

			if (opts.hotTagUrl) {
				J.getJSON(opts.hotTagUrl, {maxResults: opts.maxResults}, function (ret) {
					if (ret && ret.length > 0) {
						$('#hottags').empty();
						jQuery.each(ret, function (i, n) {
							var item = opts.onLoadHotTag.call(this, i, n);
							$('#hottags').append(item);
						});
					} else {
						$('#hottags').append('<li class="cat-item cat-item-6"><span>沒有相关记录</span></li>');
					}
				});
			}
        }
    };
	
	exports.init = function (opts) {
		Sidebox.init(opts);
		Sidebox.onLoad();
	}
	
});