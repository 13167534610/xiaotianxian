<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<header>
		<script src="../js/jquery.min.js"></script>
		<link rel="stylesheet" href="../css/shortui.css">
	</header>
	<style>
		#main-msg{
			margin-right: 10px;
			width: 300px;
			height: 700px;
			right: 10px;
		}
		.main-header{
			border: 1px solid seashell;
			background-image: url("../img/bg_1.png");
			background-repeat: no-repeat;
			background-size: 100% 100%;
			opacity: 0.5;
			width: 298px;
			height: 100px;
		}
		.my-star{
			float: left;
			border: 1px solid gray;
			background-image: url("../img/Taget_48.png");
			background-repeat: no-repeat;
			background-size: 100% 100%;
			width: 60px;
			height: 60px;
			border-radius: 50%;
			margin-top: 30px;
		}
		.my-mssage{
			float: left;
			border: 0px solid gray;
			text-decoration: underline;
			width: 224px;
			height: 60px;
			margin-top: 30px;
			margin-left: 10px;
		}
		.main-bar{
			border-top: 1px solid grey;
			border-bottom: 1px solid grey;
			width: 298px;
			height: 32px;
		}
		.shortui-list-bar li{
			width: 87px;
			text-align: center;
			font-size: 1em;
			margin-left: 8px;
			border-right: 1px dotted grey;
		}
		.shortui-list-bar li a{
			text-decoration: none;
			color: gray;
		}
		.shortui-list-bar li a:hover{
			color: black;
		}
		#talk-view{
			border: 1px solid gainsboro;
			display: none;
			width: 800px;
			height: 600px;
		}
		.talk-view-show{
			width: 798px;
			height:400px;
			border-top: 1px solid gainsboro;
			border-bottom: 1px solid gainsboro;
			overflow-y: scroll;
		}
		.list-relation{
			list-style: none;
		}
	</style>
	<body style="background: gainsboro">
		<div id="main-msg" class="shortui-drag" drag-area-width="300" drag-area-height="100">
			<div class="main-header">
				<div class="my-star">
				</div>
				<div class="my-mssage">
					<span class="my-nickname" id="my-nickname">${user.nickname}</span>
					<input type="hidden" id="my-uid" value="${user.uid}">
				</div>
			</div>
			<div class="main-bar">
				<ul class="shortui-list-bar">
					<li><a id="my-friends" href="#" class="shortui-linka-1"><strong>好友列表</strong></a></li>
					<li><a id="my-groups" href="#" class="shortui-linka-1"><strong>群列表</strong></a></li>
					<li><a id="my-add" href="#" class="shortui-linka-1"><strong>添加</strong></a></li>
				</ul>
			</div>
			<div class="main-content">
				<div class="main-friends" id="main-friends">
					<ul class="friends">
						<c:forEach items="${friends }" var="friend">
							<li onclick="showView('1', '${friend.uid}')" class="list-relation">
								<span>></span>
								<span  id="${friend.uid}">${friend.nickname}</span>
								<span id="${friend.uid}-status" style="font-size: 5px; margin-left: 5px">
									<c:if test="${friend.online == '1'}">(在线)</c:if><c:if test="${friend.online == '0'}">(离线)</c:if>
								</span>
							</li>
						</c:forEach>
					</ul>
				</div>

				<%--<div class="main-groups" id="main-groups" style="display: none">
					<ul class="groups">
						<li onclick="showView('4', 'group1')" class="list-relation" id="group1">group1</li>
						<li onclick="showView('4', 'group2')" class="list-relation" id="group2">group2</li>
						<li onclick="showView('4', 'group3')" class="list-relation" id="group3">group3</li>
					</ul>
				</div>--%>
			</div>
		</div>
		<div id="talk-view" class="shortui-drag" drag-area-width="800" drag-area-height="50">
			<div class="talk-view-header" style="width: 798px; height: 50px;background-image: url('../img/bg_1.png')">
				<h3 id="targetName" style="text-align: center">targetName</h3>
				<input id="targetId" type="hidden" value="">
				<input id="messageType" type="hidden" value="">
			</div>
			<div id="talk-view-show" class="talk-view-show" style="">

			</div>
			<div id="talk-view-edit" class="talk-view-edit" contenteditable="true" style=" height:90px; outline: none">
			</div>
			<div class="talk-view-edit-bar" style="border-top: 1px solid gainsboro">
				<button id="message-send">发送</button>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="../js/shortui.js"></script>
	<script type="text/javascript">
		$(function () {
			$("#my-friends").css("color","black");
			$("#main-friends").css("display","block");
			$("#my-groups").css("color","gray");
			$("#main-groups").css("display","none");
		})
		$("#my-friends").click(function () {
			$("#my-friends").css("color","black");
			$("#main-friends").css("display","block");
			$("#my-groups").css("color","gray");
			$("#main-groups").css("display","none");
		})

		$("#my-groups").click(function () {
			$(this).css("color","black");
			$("#main-groups").css("display","block");
			$("#my-friends").css("color","gray");
			$("#main-friends").css("display","none");
		})

		function showView(type, target) {
			$("#talk-view").css("display", "block");
			$("#targetId").val(target);
			$("#messageType").val(type);
			$("#targetName").text($("#" + target).text());
			console.info("target:" + target + " nickName" + $("#" + target).text());
		}

		$("#message-send").click(function () {
			var targetId = $("#targetId").val();
			var messageType = $("#messageType").val();
			var message = $("#talk-view-edit").text();
			$("#talk-view-edit").text("");
			var myNickName = $("#my-nickname").text();
			setShowView("send", myNickName, message);
			sendMsg(targetId, messageType, message);
			//调用发送方法
		})

		function setShowView(type, nickName, msg) {
			var showView = $("#talk-view-show");
			if (type === "send"){
				showView.append("<div style='clear: both;float: left;margin-left: 5px;'><b style='font-size: 10px'>"+nickName+": </b><br/><span style='margin-left: 10px'>"+msg+"</span></div>");
			}else if (type === 'receive'){
				showView.append("<div style='clear: both;float: right;margin-right: 5px;'><b style='font-size: 10px'>"+nickName+": </b><br/><span style='margin-left: 10px'>"+msg+"</span></div>");
			}
		}

		function setFriendStatus(status, fid) {
			$("#"+fid + "-status").text("("+status+")");
		}


		var userId = $("#my-uid").val();
		var websocket = null;

		//判断当前浏览器是否支持WebSocket
		if ('WebSocket' in window) {
			websocket = new WebSocket("ws://" + document.location.host + "/xiaotianxian/" + userId+"/chat.do");
		} else {
			alert('当前浏览器 Not support websocket')
		}
		//连接发生错误的回调方法
		websocket.onerror = function() {
			console.log("WebSocket连接发生错误");
		};
		//连接成功建立的回调方法
		websocket.onopen = function() {
			console.log("WebSocket连接成功");
		}
		//连接关闭的回调方法
		websocket.onclose = function() {
			console.log("WebSocket连接关闭");
		}
		//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
		window.onbeforeunload = function() {
			closeWebSocket();
		}
		//关闭WebSocket连接
		function closeWebSocket() {
			websocket.close();
		}
		//接收到消息的回调方法
		websocket.onmessage = function(event) {
			var info=JSON.parse(event.data);
			var msg = info.msg;
			console.log(info);
			if (info.code === "00"){
				if (info.type == "1"){//点对聊天
					showView("1", info.msgFrom);
					var nickName = $("#"+info.msgFrom).text();
					console.log("nick:" + nickName);
					setShowView("receive", nickName, msg)
				}
				if (info.type == "2"){//群聊天

				}
				if (info.type == "3"){//上线通知
					setFriendStatus("在线", info.msg);
				}
				if (info.type == "4"){//下线通知
					setFriendStatus("离线", info.msg);
				}
			}else {}
		}

		function sendMsg(target, type, msg){
			console.log(target);
			console.log(msg);
			var employees ={"target":target,"msg":msg, "type":type};
			console.log(employees)
			websocket.send(JSON.stringify(employees));
		}
	</script>
</html>