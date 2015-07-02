(function( $, window, undefined ) {
  $.album = $.extend( {}, {
    
    addFile: function(id, i, file){
		var template = '<div id="album-file' + i + '">' +
						   '<button type="button" class="close uploader-close" data-action="remove-album"><span>×</span></button>' +
		                   '<img src="http://placehold.it/48.png" class="uploadr-image-preview" />' +
		                   '<span class="uploadr-file-id">第' + (i + 1) + '张</span> - ' + file.name + ' <span class="uploadr-file-size">(' + $.album.humanizeSize(file.size) + ')</span><br />状态: <span class="uploadr-file-status">等待上传</span>'+
		                   '<div class="progress active">'+
		                       '<div class="progress-bar progress-bar-success" role="progressbar" style="width: 0%;">'+
		                           '<span class="sr-only">0% Complete</span>'+
		                       '</div>'+
		                   '</div>'+
		                   '<input type="hidden" name="albums[' + i + '].original" value=""/>' +
		               '</div>';
		               
		var i = $(id).attr('file-counter');
		if (!i){
			$(id).empty();
			i = 0;
		}
		
		i++;
		
		$(id).attr('file-counter', i);
		
		$(id).prepend(template);
	},
	
	updateFileStatus: function(i, status, message){
		$('#album-file' + i).find('span.uploadr-file-status').html(message).addClass('uploadr-file-status-' + status);
	},
	
	updateFileData: function(i, data){
		$('#album-file' + i).find('input').val(data.data);
	},
	
	updateFileProgress: function(i, percent){
		$('#album-file' + i).find('div.progress-bar').width(percent);
		
		$('#album-file' + i).find('span.sr-only').html('已上传 ' + percent);
	},
	
	humanizeSize: function(size) {
      var i = Math.floor( Math.log(size) / Math.log(1024) );
      return ( size / Math.pow(1024, i) ).toFixed(2) * 1 + ' ' + ['B', 'kB', 'MB', 'GB', 'TB'][i];
    }

  }, $.album);
  
})(jQuery, this);


$(function () {
	$('#drag-and-drop-zone').dmUploader({
		url : _base_path + '/post/upload',
		dataType : 'json',
		allowedTypes : 'image/*',
		maxFiles : 6,
		onBeforeUpload : function(id) {
			$.album.updateFileStatus(id, 'default', '上传中...');
		},
		onNewFile : function(id, file) {
			$.album.addFile('#upload-albums', id, file);

			/*** 预览图片加载 ***/
			if (typeof FileReader !== "undefined") {

				var reader = new FileReader();

				// 获取最后添加的图片
				var img = $('#upload-albums').find('.uploadr-image-preview').eq(0);

				reader.onload = function(e) {
					img.attr('src', e.target.result);
				};

				reader.readAsDataURL(file);

			} else {
				// 如果比支持 FileReader 清空所有预览
				$('#upload-albums').find('.uploadr-image-preview').remove();
			}
		},
		onUploadProgress : function(id, percent) {
			var percentStr = percent + '%';
			$.album.updateFileProgress(id, percentStr);
		},
		onUploadSuccess : function(id, data) {
			$.album.updateFileStatus(id, 'success', '上传完成');
			$.album.updateFileProgress(id, '100%');
			$.album.updateFileData(id, data);
		},
		onUploadError : function(id, message) {
			$.album.updateFileStatus(id, 'error', '上传文件出错');
		},
		onFilesMaxError : function (file) {
			alert('图片个数已达限制');
		}
	});
	
	$(document).on('click', 'button[data-action="remove-album"]', function () {
		$(this).closest('div').remove();
		
		var i = $('#upload-albums').attr('file-counter');
		if (parseInt(i) > 0) {
			$('#upload-albums').attr('file-counter', i - 1);
		}
	});
});
