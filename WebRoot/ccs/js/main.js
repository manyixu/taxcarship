
function getObject(elementId) { 
    if (document.getElementById) {
         return document.getElementById(elementId);
    } else if (document.all) {
        return document.all[elementId];
    } else if (document.layers) {
       return document.layers[elementId];
    }
}

function changeStyle(id){
    var stylesheet=getObject("skinStyle").href="../themes/"+id+"/easyui.css";
    document.cookie="stylesheet="+escape(stylesheet);
}

function initStyle(){    
      if(/stylesheet=([^;]+)/.test(document.cookie))
     getObject("skinStyle").href=unescape(RegExp.$1);
 }

/**
 * 双击菜单项
 * @param node
 * @return
 */
function doMenuClick(node) {
	if ($("#tree").tree("isLeaf", node.target) == false)
		return;

	var id = node.id;
	var text = node.text;
	if (!id) return;
			
	var elTab = parent.$('#mainTabs'); 
	if (elTab.tabs('exists', text)) {
		elTab.tabs('select', text);
	} else {
		// FIXME: iframe 必须包在 div 里, 否则会出现多余的滚动条
		// 如果新tab页能够设置style, 就不必这个多余的div了
		var url;// = '../jsp' + '/' + id + '.jsp';
		if(id=='druid'){
			url = '../../druid';
		}else{
			url = '../jsp' + '/' + id + '.jsp';
		}
		var content = '<div style="width:100%;height:100%;overflow:hidden">'+//;"之间overflow:hidden;
				'<iframe src="' + url + '" scrolling="auto" style="width:100%;height:100%;border:0;" ></iframe></div>';

		elTab.tabs('add', {
			title : text,
			content : content,
			// href: url,
			closable : true
		});
	}
}

function openTab(id,text){
	var elTab = parent.$('#mainTabs'); 
	if (elTab.tabs('exists', text)) {
		elTab.tabs('select', text);
	} else {
		// FIXME: iframe 必须包在 div 里, 否则会出现多余的滚动条
		// 如果新tab页能够设置style, 就不必这个多余的div了
		var url = '../jsp' + '/' + id + '.jsp';
		var content = '<div style="width:100%;height:100%;overflow:hidden">'+//;"之间overflow:hidden;
				'<iframe src="' + url + '" scrolling="auto" style="width:100%;height:100%;border:0;" ></iframe></div>';

		elTab.tabs('add', {
			title : text,
			content : content,
			// href: url,
			closable : true
		});
	}
}

/**
 * 
 */
function create(){
	$.messager.alert('返回信息','操作1','info');
}
/**
 * 
 */
function save(){
	$.messager.alert('返回信息','操作3','info');
}
/**
 * 
 */
function saveAs(){
	$.messager.alert('返回信息','操作4','info');
}
/**
 * 
 */
function load(){
	$.messager.alert('返回信息','操作2','info');
}
/**
 * 删除流程
 */
function del(){
	$.messager.alert('返回信息','操作5','info');
}


function showHelp(){
	$.messager.alert('DerunTech','I can help you','');
}

function showAbout(){
	$.messager.alert('DerunTech','德润出品，必属精品','');
}

function goBack(){
	top.location='../../index.jsp';
}
function showAbout(){
	$.messager.alert("DerunTech","正在开发中···");
}