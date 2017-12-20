<template>
  <html>
  <head>

  </head>
  <body class="hold-transition login-page">
  <div class="login-box">
    <div class="login-logo">
      <a href="../index"><b>Admin</b>LTE</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
      <p class="login-box-msg">Sign in to start your session</p>

      <div>
        <div class="form-group has-feedback">
          <input v-model="user.username" class="form-control" placeholder="please write your username">
          <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
          <input v-model="user.password" type="password" class="form-control" placeholder="">
          <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="row">
          <div class="col-xs-8">
            <div class="checkbox icheck">
              <label>
                <input type="checkbox"> Remember Me
              </label>
            </div>
          </div>
          <!-- /.col -->
          <div class="col-xs-4">
            <button type="button" @click="login" class="btn btn-primary btn-block btn-flat">Sign In</button>
          </div>
          <!-- /.col -->
        </div>
      </div>



      <a href="#">I forgot my password</a><br>
      <a href="register.html" class="text-center">Register a new membership</a>

    </div>
    <!-- /.login-box-body -->
  </div>
  <!-- /.login-box -->
  </body>
  </html>
</template>
<script>
  //let axios = require('axios');
  import {getCookie,setCookie} from '../utils/cookieUtil'
  export default{
    data() {
      return {
        isLoging: false,
        user:{
        username: '',
        password: ''}
      }
    },
    created (){

    },
    methods: {
      login: function () {
        if (this.user.username != '' && this.user.password != '') {
          this.toLogin();
        } else {
          alert("这块不和谐会改，用户名或密码为空");
        }
      },
      fetchData() {
        console.log('路由发送变化doing...');
      },
      toLogin: function () {
        var formData = JSON.stringify(this.user);
        var url = "http://localhost:8999/login";
        this.axios.post(url, formData, {
          headers: {
            "Content-Type": "application/json;charset=utf-8",
          }
        }).then(function(response) {
          if(response.data.code == 200){
            this.$router.push({path:'/index',query:{sessionId:response.data.sessionId}});
          }else{
            alert("用户名密码错误");
          }
          //这两个回调函数都有各自独立的作用域，如果直接在里面访问 this，无法访问到 Vue 实例,这时只要添加一个 .bind(this) 就能解决这个问题
        }.bind(this)).catch(function(response) {
          // 这里是处理错误的回调
          console.log(response)
        });
      }
    }
  }
</script>
