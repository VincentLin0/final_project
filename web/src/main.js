import Vue from 'vue'
import App from './App.vue'
import router from './router'
// import '@/css/global.css'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import locale from 'element-ui/lib/locale/lang/en'
import store from "./store/index"




Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(ElementUI, { locale })


// var axios = require('axios')
// axios.defaults.baseURL = 'http://localhost:9090/'
// Vue.prototype.$axios = axios
// Vue.config.productionTip = false

new Vue({
  router,
  store: store,
  render: h => h(App)
}).$mount('#app')

