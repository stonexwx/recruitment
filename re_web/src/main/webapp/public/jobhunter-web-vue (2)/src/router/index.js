import Vue from "vue";
import VueRouter from "vue-router";
import HomePage from "../views/HomePage.vue";
import JobPage from "../views/JobPage.vue";
import ResumePage from "../views/ResumePage.vue";
import Login from "../views/Login.vue";
import Register from "../views/RegisterPage"
import JobDetail from "../views/JobDetail";
import MainPage from "../views/MainPage.vue";
import EnterprisePage from "../views/EnterPrisePage.vue";
import EnterpriseDetail from "../views/EnterpriseDetail.vue";
Vue.use(VueRouter);

const routes = [
  { path: "/", redirect: "/main/home" },
  { path: "/login", component: Login },
  { path: "/register", component: Register },
  {
    path: "/main",
    component: MainPage,
    children: [
      { path: "home", component: HomePage },
      { path: "job", component: JobPage, beforeEnter:((to,from,next)=>{
          let token = sessionStorage.getItem("token");
          if(token||to.path==="/login"){
            next();
          }else{
            next("/login")
          }
        }) },
      { path: "resume", component: ResumePage, beforeEnter:((to,from,next)=>{
          let token = sessionStorage.getItem("token");
          if(token||to.path==="/login"){
            next();
          }else{
            next("/login")
          }
        }) },
      { path: "detail/:rid", component: JobDetail,props: true, beforeEnter:((to,from,next)=>{
          let token = sessionStorage.getItem("token");
          if(token||to.path==="/login"){
            next();
          }else{
            next("/login")
          }
        }) },
      { path: "enterprise", component: EnterprisePage, beforeEnter:((to,from,next)=>{
          let token = sessionStorage.getItem("token");
          if(token||to.path==="/login"){
            next();
          }else{
            next("/login")
          }
        })},
      { path: "enterpriseDetail/:rid", component: EnterpriseDetail,props: true, beforeEnter:((to,from,next)=>{
          let token = sessionStorage.getItem("token");
          if(token||to.path==="/login"){
            next();
          }else{
            next("/login")
          }
        }) },
    ],
  },
];

const router = new VueRouter({
  routes,
});
export default router;
