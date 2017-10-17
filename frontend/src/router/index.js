import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import index from '@/components/index'
import deviceCatalog from '@/components/catalog'
import deviceCatalog2 from '@/components/catalog2'
import general from '@/components/forms/general'

Vue.use(Router)

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
      }]
    }
  ]
})
