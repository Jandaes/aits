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
// AdminLTE for demo purposes
import '../assets/js/demo.js'

// socketjs.min.js
import '../assets/js/sockjs.min.js'
// stomp.min.ks
import '../assets/js/stomp.min.js'
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
