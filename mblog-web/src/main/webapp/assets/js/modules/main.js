define(function(require, exports, module) {
    var plugins = require('plugins');
    require('bootstrap');
    
    var wpexLocalize = {
    		"mobileMenuOpen" : "Click here to navigate",
    		"mobileMenuClosed" : "Close navigation",
    		"isOriginLeft" : "1"
    	};
    
    // 图片懒加载
    var imagesLazyload = function () {
    	require.async('lazyload', function () {
    		$("img").lazyload({
	   	   		 placeholder: app.base + '/assets/images/spinner.gif',
	   	   		 effect: "fadeIn"
	   	   	});
        });
    }
    
    // 加载图片灯箱
    var initLightbox = function () {
    	require.async('baguetteBox', function () {
    		baguetteBox.run('.thumbs', {
        		animation: 'slideIn'
        	});
        });
    };
    
    // 返回顶部
    var backToTop = function () {
    	var $window = $(window);
    	$scrollTopLink = $( 'a.site-scroll-top' );
		$window.scroll(function () {
			if ($(this).scrollTop() > 100) {
				$scrollTopLink.fadeIn();
			} else {
				$scrollTopLink.fadeOut();
			}
		});		
		$scrollTopLink.on('click', function() {
			$( 'html, body' ).animate({scrollTop:0}, 400);
			return false;
		} );
    }
    
	// 瀑布流
    var masonry = function () {
    	// Masonry
		var $container = $('.masonry-grid');
		$container.imagesLoaded(function(){
			// FlexSlider run after images are loaded
			$container.masonry({
				itemSelector: '.masonry-entry',
				transitionDuration: '0.3s',
				isOriginLeft: wpexLocalize.isOriginLeft
			});
		});

		// Infinite scroll
		var $container = $('.masonry-grid');
		$container.infinitescroll( {
			loading: {
				msg: null,
				finishedMsg : null,
				msgText : null,
				msgText: '<div class="infinite-scroll-loader"><i class="fa fa-spinner fa-spin"></i></div>',
			},
				navSelector  : 'div.page-jump',
				nextSelector : 'div.page-jump div.older-posts a',
				itemSelector : '.masonry-entry',
			},
			// trigger Masonry as a callback
			function( newElements ) {
				// hide new items while they are loading
				var $newElems = $( newElements ).css({ opacity: 0 });
				// ensure that images load before adding to masonry layout
				$newElems.imagesLoaded(function(){
					// show elems now they're ready
					$newElems.animate({ opacity: 1 });
					$container.masonry( 'appended', $newElems, true );
					// Self hosted audio and video
			});
		});
    }
    
    exports.init = function () {
    	imagesLazyload();

    	initLightbox();
    	
    	backToTop();
    	
    	masonry();
    }
    
});