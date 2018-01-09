<template>
  <div>
    <section class="content-header" style="text-align:left;">
      <h1>网站聊天室
        <small>开聊</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 网站聊天室</a></li>
        <li><a href="#">聊天</a></li>
        <li class="active">开聊</li>
      </ol>
    </section>
    <section class="content">
      <div class="row">
          <div class="col-md-4">
              <div class="box box-primary direct-chat direct-chat-primary">
                  <div class="box-header with-border">
                    <h3 class="box-title">在线聊天</h3>
                    <div class="box-tools pull-right">
                      <span data-toggle="tooltip" title="3 New Messages" class="badge bg-light-blue">0</span>
                      <button type="button" class="btn btn-box-tool" data-widget="collapse">
                        <i class="fa fa-minus"></i>
                      </button>
                      <button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="Contacts" data-widget="chat-pane-toggle">
                        <i class="fa fa-comments"></i>
                      </button>
                      <button type="button" class="btn btn-box-tool" data-widget="remove">
                        <i class="fa fa-times"></i>
                      </button>
                    </div>
                  </div>
                  <!-- /.box-header -->
                  <div class="box-body">
                    <!-- Conversations are loaded here -->
                    <div class="direct-chat-messages">
                      <!-- 左边聊天窗口 -->
                      <div class="direct-chat-msg">
                        <div class="direct-chat-info clearfix">
                          <span class="direct-chat-name pull-left">独孤求败</span>
                          <span class="direct-chat-timestamp pull-right">2017-10-27 15:29</span>
                        </div>
                        <!-- /.direct-chat-info -->
                        <img class="direct-chat-img" src="../../assets/img/user1-128x128.jpg" alt="独孤求败的头像">
                        <!-- /.direct-chat-img -->
                        <div class="direct-chat-text" style="text-align:left;">
                          {{ leftContxt }}
                        </div>
                        <!-- /.direct-chat-text -->
                      </div>
                      <!-- /.direct-chat-msg -->
                      <!-- 右边聊天窗口 -->
                      <div class="direct-chat-msg right">
                        <div class="direct-chat-info clearfix">
                          <span class="direct-chat-name pull-right">东方不败</span>
                          <span class="direct-chat-timestamp pull-left">2017-10-27 15:30</span>
                        </div>
                        <!-- /.direct-chat-info -->
                        <img class="direct-chat-img" src="../../assets/img/user3-128x128.jpg" alt="东方不败的头像">
                        <!-- /.direct-chat-img -->
                        <div class="direct-chat-text" style="text-align:right">
                          {{ response }}
                        </div>
                        <!-- /.direct-chat-text -->
                      </div>
                      <!-- /.direct-chat-msg -->
                    </div>
                    <!--/.direct-chat-messages-->
                    <!-- Contacts are loaded here -->
                    <div class="direct-chat-contacts">
                      <ul class="contacts-list">
                        <li>
                          <a href="#">
                            <img class="contacts-list-img" src="../../assets/img/user1-128x128.jpg" alt="User Image">
                            <div class="contacts-list-info">
                              <span class="contacts-list-name">
                                曾几何时
                                <small class="contacts-list-date pull-right">2017-10-27</small>
                              </span>
                              <span class="contacts-list-msg pull-left">这里是我的签名、想当年。。。</span>
                            </div>
                          <!-- /.contacts-list-info -->
                          </a>
                        </li>
                      <!-- End Contact Item -->
                      </ul>
                      <!-- /.contatcts-list -->
                    </div>
                    <!-- /.direct-chat-pane -->
                  </div>
                  <!-- /.box-body -->
                  <div class="box-footer">
                    <div class="input-group">
                      <span class="input-group-btn">
                        <button @click="connect" class="btn btn-success btn-flat">准备连接</button>
                      </span>
                      <input type="text" name="message" v-model="name" v-bind:readonly="isReadOnly" placeholder="请输入聊天内容" class="form-control">
                      <span class="input-group-btn">
                        <button type="submit" class="btn btn-primary btn-flat" @click="sendName">发送</button>
                      </span>
                    </div>
                  </div>
                  <!-- /.box-footer-->
              </div>
              <!--/.direct-chat -->
          </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <noscript><h2>你的浏览器不支持，狗带了...</h2></noscript>
          <div>
            <div>
              ========================
                不点“连接”就狗带吧
              ========================
            </div>
            <div id="conversationDiv">
              <label>输入你的名字</label><input type="text" v-model="name"/>
              <button id="connect" @click="connect">连接</button>
              <button id="sendName" @click="sendName">发送</button>
              <p>{{ response }}</p>
            </div>
          </div>
        </div>
      </div>
    </section>    
  </div>
</template>
<script>
  var stompClient=null;
  var message=null;
  export default {
    data() {
      return {
        name:'',
        response:'',
        isReadOnly:true,
        leftContxt:''
      }
    },
    created(){
      this.disconnect();
    },
    methods: {
      setConnected: function (connected) {
        this.response="";
      },
      connect: function () {
        //激活聊天窗口
        this.isReadOnly = false;
        //js的this和vue的this冲突
        var _this = this;
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
            //这里接收的显示在右边
            _this.response = JSON.parse(response.body).responseMessage;
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
        //自己发送的显示在左边
        this.leftContxt = this.name;
        stompClient.send("/welcome",{},JSON.stringify({'name':this.name}));
        this.name = '';
      }
    }
  }
</script>
