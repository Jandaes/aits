// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// Bootstrap 3.3.6
import '../bootstrap/css/bootstrap.css'
import '../bootstrap/js/bootstrap.min.js'
// adminlte
import '../assets/css/AdminLTE.css'
import '../assets/css/skins/_all-skins.css'
// font-awesome
// import 'font-awesome/css/font-awesome.min.css'

// jquery
import '../plugins/jQuery/jquery-2.2.3.min.js'
// SlimScroll
import '../plugins/slimScroll/jquery.slimscroll.min.js'
// FastClick
import '../plugins/fastclick/fastclick.js'
// AdminLTE App
import '../assets/js/app.min.js'
// AdminLTE for oriDemo purposes
import '../assets/js/demo.js'

// socketjs.min.js
import '../assets/js/sockjs.min.js'
// stomp.min.ks
import '../assets/js/stomp.min.js'



import VueAMap from 'vue-amap';
import { lazyAMapApiLoaderInstance } from 'vue-amap';
import axios from 'axios'


Vue.use(VueAMap);
VueAMap.initAMapApiLoader({
  key: '1b08fe5304b0b71ba06e5e2f38095d78',
  plugin: ['AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType']
});

lazyAMapApiLoaderInstance.load().then(() => {
  // your code ...
  this.map = new AMap.Map('amapContainer', {
    center: new AMap.LngLat(121.59996, 31.197646)
  });
});
Vue.prototype.axios = axios;
axios.defaults.withCredentials=true;


Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
