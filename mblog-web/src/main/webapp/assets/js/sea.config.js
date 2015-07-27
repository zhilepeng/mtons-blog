seajs.config({
	alias: {
        'jquery': 'jquery.min',
        '$': 'jquery.min',
        'jquery.migrate': 'jquery-migrate-1.2.1.min',
        'plugins': 'plugins',

        /* modules */
        'main': 'modules/main',
        'sidebox': 'modules/sidebox',
        'post': 'modules/post',
        'upload': 'modules/upload',
        'video': 'modules/video',
        'comment': 'modules/comment',
        'phiz': 'modules/phiz',
        'avatar': 'modules/avatar',
        
        /* vendors */
        'bootstrap': 'vendors/bootstrap/js/bootstrap.min',
        'baguetteBox': 'vendors/baguette/baguetteBox.min',
        'layer': 'vendors/layer/layer',
        'pace': 'vendors/pace/pace.min',
        'dmuploader': 'vendors/uploader/dmuploader',
        'jcrop': 'vendors/jcrop/jquery.jcrop.min',
        "validate": 'vendors/validate/jquery-validate',
        'lazyload': 'vendors/lazyload/jquery.lazyload'
    },

	// 预加载项
	preload: [this.JSON ? '' : 'json', 'jquery'],

	// 路径配置
	paths: {
		'vendors': '../../vendors',
	},

	// 变量配置
	vars: {
		'locale': 'zh-cn'
	},

	charset: 'utf-8',

	debug: false
});

var __SEAJS_FILE_VERSION = '?v=1.3';

//seajs.on('fetch', function(data) {
//	if (!data.uri) {
//		return ;
//	}
//
//	if (data.uri.indexOf(app.mainScript) > 0) {
//		return ;
//	}
//
//    if (/\:\/\/.*?\/assets\/libs\/[^(common)]/.test(data.uri)) {
//        return ;
//    }
//
//    data.requestUri = data.uri + __SEAJS_FILE_VERSION;
//
//});
//
//seajs.on('define', function(data) {
//	if (data.uri.lastIndexOf(__SEAJS_FILE_VERSION) > 0) {
//	    data.uri = data.uri.replace(__SEAJS_FILE_VERSION, '');
//	}
//});