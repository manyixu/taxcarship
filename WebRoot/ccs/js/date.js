	function disable() {
		$('#dd').datebox('disable');
	}
	function enable() {
		$('#dt').datebox('enable');
	}
	/*  
	 将Date/String类型,解析为String类型.  
	 传入String类型,则先解析为Date类型  
	 不正确的Date,返回 ''  
	 如果时间部分为0,则忽略,只返回日期部分.  
	 */
	function formatDate(v) {
		if (v instanceof Date) {
			var y = v.getFullYear();
			var m = v.getMonth() + 1;
			var d = v.getDate();
			var h = v.getHours();
			var i = v.getMinutes();
			var s = v.getSeconds();
			var ms = v.getMilliseconds();
			if (ms > 0)
				return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s
						+ '.' + ms;
			if (h > 0 || i > 0 || s > 0)
				return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s;
			return y + '-' + m + '-' + d;
		}
		return '';
	}
	$( function() {
		$('#dd').datebox( {
			currentText : '今天',
			closeText : '关闭',
			disabled : false,
			required : true,
			required:false,
			formatter : formatDate
		});
	});
	$( function() {
		$('#dt').datebox( {
			currentText : '今天',
			closeText : '关闭',
			disabled : false,
			required : true,
			required:false,
			formatter : formatDate
		});
	});



//$(function() {
//	$("#begindate").datepicker({ 
//		dateFormat:'yy/mm/dd',
//		onSelect: function(dateText, inst) {}
//	});
//	$("#enddate").datepicker({ 
//		dateFormat:'yy/mm/dd',
//		onSelect: function(dateText, inst) {}
//	});
//});