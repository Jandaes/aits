<template>
  <html>
  <head>
    <meta charset="utf-8">
    <title>dian can</title>
    <!--忽略电话号码和email识别-->
    <meta name="format-detection" content="telephone=no" />
    <meta name="format-detection" content="email=no" />
    <!--当网站添加到主屏幕快速启动方式，将网站添加到主屏幕快速启动方式-->
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <!--隐藏ios上的浏览器地址栏-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <!-- UC默认竖屏 ，UC强制全屏 -->
    <meta name="full-screen" content="yes">
    <meta name="browsermode" content="application">
    <!-- QQ强制竖屏 QQ强制全屏 -->
    <meta name="x5-orientation" content="portrait">
    <meta name="x5-fullscreen" content="true">
    <meta name="x5-page-mode" content="app">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no,minimal-ui">
  </head>
  <body>
  <div class="page-header ">
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
        <span v-for="mType in fmenuTypes">
        <button  class="list-group-item" @click="getMenus(shipNo,mType.menuTypeId)">{{mType.menuType}}</button>
        </span>
      </div>


    </div>
    <div class="col-xs-9 col-md-9">

      <div class="list-group">
        <span v-for="menu in menuList">
        <button type="button" class="list-group-item"> {{menu.bmenuName}} &nbsp;&nbsp;&nbsp; 原价：{{menu.bprice}}元&nbsp;&nbsp;&nbsp;{{menu.bdiscount!=null ? ',折扣为：'+menu.bdiscount + '折' : ''}}&nbsp;&nbsp;&nbsp;{{menu.bdiscountPrice!=null? '，折后价：'+menu.bdiscountPrice : ''}}&nbsp;&nbsp;&nbsp;+ num -</button>
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
        shipNo:'',
        address:'',
        menuType:'',
        fmenuTypes:'',
        menuList:'',
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
        this.shipNo=response.data.shipNo;
        this.address=response.data.address;
        this.fmenuTypes=response.data.fmenuTypes;
        this.menuList=response.data.fmenuTypes[0].menuList;

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
      getMenus : function (shipNo,menuTypeId) {

        ///getMenuType?shipNo=S001&menuTypeId=1
        var url = "http://localhost:8001/getMenuType";
        this.axios.post(url,  $.param({menuTypeId:menuTypeId,shipNo:shipNo}), {}).then(function(response) {
          this.menuList=response.data.menuList;
          //这两个回调函数都有各自独立的作用域，如果直接在里面访问 this，无法访问到 Vue 实例,这时只要添加一个 .bind(this) 就能解决这个问题
        }.bind(this)).catch(function(response) {
          // 这里是处理错误的回调
          console.log(response)
        });
      }
    }
  }
</script>
