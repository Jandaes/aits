import Vue from 'vue'
import Router from 'vue-router'
import VueResource from 'vue-resource'
// import HelloWorld from '@/components/HelloWorld'
import index from '@/components/index'
import deviceCatalog from '@/components/catalog'
import deviceCatalog2 from '@/components/catalog2'
import general from '@/components/forms/general'
import ticketInfo from '@/components/forms/ticketInfo'
import person from '@/components/forms/person'

// 导入定义路由
Vue.use(Router)
Vue.use(VueResource)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
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
        path: '/person',
        component: person
      }]
    }
  ]
})
