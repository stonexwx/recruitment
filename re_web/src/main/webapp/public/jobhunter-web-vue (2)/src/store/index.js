import Vue from "vue";
import Vuex from "vuex";
//import Cookies from 'js-cookie'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    //token:  localStorage.getItem("token")?localStorage.getItem("token"):"",
    //uname:  localStorage.getItem("uname")?sessionStorage.getItem("uname"):"",
    //avatar: localStorage.getItem("avatar")?localStorage.getItem("avatar"):"",
    //aid:    localStorage.getItem("aid")?sessionStorage.getItem("aid"):"",
    //uid:    localStorage.getItem("uid")?localStorage.getItem("uid"):"",token:  localStorage.getItem("token")?localStorage.getItem("token"):"",

    token:  sessionStorage.getItem("token")?sessionStorage.getItem("token"):"",
    uname:  sessionStorage.getItem("uname")?sessionStorage.getItem("uname"):"",
    avatar: sessionStorage.getItem("avatar")?sessionStorage.getItem("avatar"):"",
    aid:    sessionStorage.getItem("aid")?sessionStorage.getItem("aid"):"",
    uid:    sessionStorage.getItem("uid")?sessionStorage.getItem("uid"):"",

    joblist: [],
  },
  // 更改 Vuex 的 store 中的状态的唯一方法是提交 mutation
  mutations: {
    changeJobList(state, jobList) {
      state.joblist = jobList;
    },
    // 登录成功，记录登录账户信息
    login(state, loginInfo) {

      state.uname = loginInfo.uname;
      //localStorage.setItem("uname", loginInfo.uname);
      sessionStorage.setItem("uname", loginInfo.uname);


      state.avatar = loginInfo.avatar;
      //localStorage.setItem("avatar", loginInfo.avatar);
      sessionStorage.setItem("avatar", loginInfo.avatar);

      state.aid = loginInfo.aid;
      //localStorage.setItem("aid", loginInfo.aid);
      sessionStorage.setItem("aid", loginInfo.aid);

      state.uid = loginInfo.uid;
      //localStorage.setItem("uid", loginInfo.uid);
      sessionStorage.setItem("uid", loginInfo.uid);

      // Cookies.set("uname", loginInfo.uname);
      // Cookies.set("uid", loginInfo.uid);
      // Cookies.set("aid", loginInfo.aid);
    },
    // 退出登录，清除账户的状态记录
    logout (state){
      state.token = "";
      sessionStorage.clear();
    }

  },
  // 获取值得方式之一，方便加一些筛选项
  getters: {

  },

  actions: {},
  modules: {},
});
