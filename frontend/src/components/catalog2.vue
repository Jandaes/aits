<template>
	<html>
  <body >
      <noscript><h2>你的浏览器不支持，狗带了...</h2></noscript>
      <div>
        <div>
          ========================
            不点“连接”就狗带吧
          ========================
        </div>
        <div id="conversationDiv">
          <label>输入你的名字</label><input type="text" id="name" />
          <button id="connect" @click="connect">连接</button>
          <button id="sendName" @click="sendName">发送</button>
          <p id="response"></p>
        </div>
      </div>
  </body>
  </html>
</template>
<script>
  var stompClient=null;
  var message=null;
  export default {
    created(){
      this.disconnect();
    },
    methods: {
      setConnected: function (connected) {
        $('#response').html();
      },
      connect: function () {
        //连接sockjs的endpoint名称为/endPointJared
        var socket=new SockJS('http://localhost:8999/endPointJared');
        //使用stomp子协议的websocket客户端
        stompClient=Stomp.over(socket);
        //连接websocket服务端
        stompClient.connect({},function (frame) {
         // this.setConnected(true);
          console.log("connected:"+frame);
          //通过stompclient.send向/welcome目标（destination）发送消息，这个是在控制器的@messageMapping中定义的
          stompClient.subscribe("/topic/getResponse",function (response) {
            var message=JSON.parse(response.body).responseMessage;
            var response=  $("#response");
            response.html(message);
          });
        });
      },
      disconnect: function () {
        if(stompClient!=null){
          stompClient.disconnect();
        }
           this.setConnected(false);
        console.log("Disconnected");
      },
      sendName: function () {
        var name=$('#name').val();
        stompClient.send("/welcome",{},JSON.stringify({'name':name}));
      }

    }
  }
</script>
