function init() {
	var speed = 30;
	var MyMarh = setInterval(Marqueeh, speed)
	demo2.innerHTML = demo1.innerHTML ;
	demo.scrollTop = demo.scrollHeight ;
	//原理是不断的向demoh2中填入demoh1中的内容，然后将已经看不见的清除
	//用一个两行一列的表格，上一列再放一个装填有内容的表格，定义为demoh1，下一列是空的TD，定义为demoh2
	//SPEED是用来控制速度的。
	demo.onmouseover = function() {
		clearInterval(MyMarh);
	}
	demo.onmouseout = function() {
		MyMarh = setInterval(Marqueeh, speed);
	}
	function Marqueeh() {
//			if (demo1.offsetTop - demo.scrollTop >= 0)
//				demo.scrollTop += demo2.offsetHeight;
//			else {
			demo.scrollTop--;
//			}
	}
	setTimeout("send()", 1000);
	//send();
}
function send(){
	var speed = 30;
	var MyMarh = setInterval(Marqueeh, speed)
	demo4.innerHTML = demo3.innerHTML ;
	demos.scrollTop = demos.scrollHeight ;
	function Marqueeh() {
		demos.scrollTop-- ;
	}
}