<template>
  <html>
  <head></head>
  <body>
  <div class="page-header">
    <h1>{{shipName}}
      <small>新店优惠</small>
    </h1>
    <h2>
      <small>{{address}}</small>
    </h2>
  </div>
  <div class="row">
    <div class="col-xs-3 col-md-3">菜品类别</div>
    <div class="col-xs-9 col-md-9">菜品详情</div>
  </div>
  <div class="row">
    <div class="col-xs-3 col-md-3">

      <div class="list-group">
        <span v-for="menu in menuList">
        <button type="button" class="list-group-item">{{menu.bmenuName}}</button>
        </span>
      </div>


    </div>
    <div class="col-xs-9 col-md-9">

      <div class="list-group">
        <span v-for="menu in menuList">
        <button type="button" class="list-group-item">原价：{{menu.bprice}}元&nbsp;&nbsp;&nbsp;{{menu.bdiscount!=null ? ',折扣为：'+menu.bdiscount + '折' : ''}}&nbsp;&nbsp;&nbsp;{{menu.bdiscountPrice!=null? '，折后价：'+menu.bdiscountPrice : ''}}</button>
        </span>
      </div>


    </div>
  </div>


  <nav class="navbar navbar-default navbar-fixed-bottom">
    <button class="btn btn-default btn-block" type="submit">查看订单</button>
  </nav>

  </body>
  </html>
</template>
<script>
  //let axios = require('axios');
  import {getCookie, setCookie} from '../utils/cookieUtil'

  export default {
    data() {
      return {
        isLoging: false,
        shipName: '',
        address:'',
        menuList:'',
        user: {
          username: '',
          password: ''
        }
      }
    },
    created() {
      var formData = JSON.stringify(this.user);
      var url = "http://localhost:8001/getShipInfo?shipNo=S001";
      this.axios.post(url, formData, {
        headers: {
          "Content-Type": "application/json;charset=utf-8",
        }
      }).then(function (response) {
        this.shipName=response.data.shipName;
        this.address=response.data.address;
        this.menuList=response.data.menuList;
        alert(shipName);
        alert(response.data);
        alert(aaaa)
        if (response.data.code == 200) {
          alert(data);
        } else {
          alert("用户名密码错误");
        }
        //这两个回调函数都有各自独立的作用域，如果直接在里面访问 this，无法访问到 Vue 实例,这时只要添加一个 .bind(this) 就能解决这个问题
      }.bind(this)).catch(function (response) {
        // 这里是处理错误的回调
        console.log(response)
      });
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
        var url = "http://localhost:8001/getShipInfo?shipNo=S001";
        this.axios.post(url, formData, {
          headers: {
            "Content-Type": "application/json;charset=utf-8",
          }
        }).then(function (response) {
          if (response.data.code == 200) {
            this.$router.push({path: '/index', query: {sessionId: response.data.sessionId}});
          } else {
            alert("用户名密码错误");
          }
          //这两个回调函数都有各自独立的作用域，如果直接在里面访问 this，无法访问到 Vue 实例,这时只要添加一个 .bind(this) 就能解决这个问题
        }.bind(this)).catch(function (response) {
          // 这里是处理错误的回调
          console.log(response)
        });
      }
    }
  }
</script>
