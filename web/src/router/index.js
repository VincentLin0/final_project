import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import SearchView from '../views/SearchView.vue'
import ContentView from '../views/ContentView.vue'
import SearchResult from '../views/SearchResult.vue'
import LogOnView from '../views/LogOnView'
import UploadContentView from '../views/UploadContentView.vue'
import SeftTestView from '../views/SeftTestView.vue'
import BackStageView_CM from '../views/BackStageView_CM.vue'
import BackStageView_AC from '../views/BackStageView_AC.vue'
import BackStageView_TM from '../views/BackStageView_TM.vue'
import BackStageView_AQ from '../views/BackStageView_AQ.vue'

Vue.use(VueRouter)

//https://www.jianshu.com/p/3643eed33246
const RouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (to) {
  return RouterPush.call(this, to).catch(err => err)
}
const RouterReplace = VueRouter.prototype.replace
VueRouter.prototype.replace = function replace (to) {
  return RouterReplace.call(this, to).catch(err => err)
}

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/search',
    name: 'search',
    component: SearchView
  },
  {
    path: '/content/:id',
    name: 'content',
    component: ContentView
  },
  {
    path: '/result/:searchContent',
    name: 'result',
    component: SearchResult
  },
  {
    path: '/signup',
    name: 'singnup',
    component: LogOnView
  },
  {
    path: '/uploadContent',
    name: 'UploadContent',
    component: UploadContentView
  },

  {
    path: '/test',
    name: 'SeftTest',
    component: SeftTestView
  },
  {
    path: '/backStage',
    name: 'BackStage',
    component: BackStageView_CM
  },
  {
    path: '/backStage/upload',
    name: 'BackStage',
    component: BackStageView_AC
  },
  {
    path: '/backStage/testManagement',
    name: 'testManagement',
    component: BackStageView_TM
  },
  {
    path: '/backStage/testManagement/uploadTest',
    name: 'testManagement',
    component: BackStageView_AQ
  },
  // {
  //   path: '/login',
  //   name: 'login',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


// router.beforeEach((to, from, next) => {
//   if (to.path === '/login' || to.path === '/signup' || to.path === '/') {
//     next();
//   } else {
//     let token = localStorage.getItem('token');

//     if (token === null || token === '') {
//       next('/login');
//     } else {
//       next();
//     }
//   }
// });



export default router;
