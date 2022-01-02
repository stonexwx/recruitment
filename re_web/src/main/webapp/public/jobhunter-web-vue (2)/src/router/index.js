import Vue from "vue";
import VueRouter from "vue-router";
import HomePage from "../views/HomePage.vue";
import JobPage from "../views/JobPage.vue";
import DiscoveryPage from "../views/DiscoveryPage.vue";
import PersonalPage from "../views/PersonalPage.vue";
import ResumePage from "../views/ResumePage.vue";
import BlankPage from "../views/BlankPage.vue";
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
      { path: "job", component: JobPage },
      { path: "discovery", component: DiscoveryPage },
      { path: "personal", component: PersonalPage },
      { path: "resume", component: ResumePage },
      { path: "blank", component: BlankPage },
      { path: "detail/:jobId", component: JobDetail, props: true },
      { path: "companyDetail", component: EnterpriseDetail},
      { path: "enterprise", component: EnterprisePage },
      { path: "enterpriseDetail/:rid", component: EnterpriseDetail,props: true },
    ],
  },
];

const router = new VueRouter({
  routes,
});

export default router;
