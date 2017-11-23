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
        //  let user = this.user;
        var formData = JSON.stringify(this.user);
        var url = "http://www.jared73.com:8999/check";
        this.$http.post(url, formData).then(response => {
        // alert(data);
         // alert(response.data);
          if(response.data == null){
            this.$router.push('/');
            this.$router.push('/index');
          }else{
            alert("用户名密码错误");
          }
          //    this.$router.replace({ path: '/index'})
//          if(response.data.code == 1){
//            //如果登录成功则保存登录状态并设置有效期
//            let expireDays = 1000 * 60 * 60 * 24 * 15;
//            this.setCookie('session', response.data.session, expireDays);
//            //跳转
//            this.$router.push('/user_info');
//          }
        }, response => {
          // error callback
        });
      }
    }
  }
</script>
