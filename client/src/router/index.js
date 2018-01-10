import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import phoneMenu from '@/components/phoneMenu'
import VueResource from 'vue-resource'

Vue.use(Router)
Vue.use(VueResource)



export default new Router({
  routes: [
    {
      path: '/',
      name: 'phoneMenu',
      component: phoneMenu
    }
  ]
});
