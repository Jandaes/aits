// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// bootstrap
import '../bootstrap/css/bootstrap.css'
import '../bootstrap/js/bootstrap.min'
// adminlte
import '../assets/css/AdminLTE.min.css'
import '../assets/css/skins/_all-skins.min.css'
import '../assets/js/app.min'
// font-awesome
import 'font-awesome/css/font-awesome.min.css'

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
