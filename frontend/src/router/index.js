import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import index from '@/components/index'
import login from '@/components/login'
import deviceCatalog from '@/components/catalog'
import deviceCatalog2 from '@/components/catalog2'
import general from '@/components/forms/general'
import person from '@/components/forms/person'
import ticketInfo from '@/components/forms/ticketInfo'
import amap from '@/components/map/amap'
import VueResource from 'vue-resource'

Vue.use(Router)
Vue.use(VueResource)



export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    }, {
      path: '/index',
      name: 'index',
      component: index,
      children: [{
        path: '/deviceCatalog',
        component: deviceCatalog
      }, {
        path: '/deviceCatalog2',
        component: deviceCatalog2
      }, {
        path: '/general',
        component: general
      }, {
        path: '/ticketInfo',
        component: ticketInfo
      }, {
        path: '/person',
        component: person
      }, {
        path: '/map',
        component: amap
      }]
    }
  ]
});
