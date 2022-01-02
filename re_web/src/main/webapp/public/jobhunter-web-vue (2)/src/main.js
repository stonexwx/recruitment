import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from "axios";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

Vue.config.productionTip = false;
Vue.use(ElementUI);

// Axios
Vue.prototype.$http = axios.create({
  baseURL: "http://localhost:8080", //基础路径
  withCredentials: true, // 表示请求可以携带cookie
  headers: { "token":  sessionStorage.getItem("token")!==null?sessionStorage.getItem("token"):""} //每次发送请求携带token
  //headers: { "token":  sessionStorage.getItem("token")?sessionStorage.getItem("token"):""} //每次发送请求携带token
});

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
