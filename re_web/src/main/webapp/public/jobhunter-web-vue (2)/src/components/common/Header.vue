<template>
  <div class="header">
    <div class="header-content">
      <router-link to="/main/home">
        <img id="logo" src="../../assets/jh-logo.png" alt="logo" />
      </router-link>
      <div class="nav">
        <router-link to="/main/home" class="header-btn">首页</router-link>
        <router-link to="/main/job" class="header-btn">职位</router-link>
        <router-link to="/main/enterprise" class="header-btn">企业</router-link>
        
      </div>
      <div class="user-nav">
        <router-link to="/main/resume" class="header-btn">信息管理</router-link>
        <router-link v-if="uname!==''" to="/main/personal" class="header-btn">
          <label id="uname">{{ uname }}</label>
          <img class="uphoto" :src=avatar alt="" />
        </router-link>
        <router-link v-else to="/login" class="header-btn">
          <label>请登录</label>
          <img class="uphoto" src="../../assets/avatar/user/default_photo.png" alt="" />
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data(){
    return {
      avatarFileName: this.$store.state.avatar,
    }
  },
  computed: {
    uname() {
      return  this.$store.state.uname;
    },
    avatar() {
      return require("../../assets/avatar/user/" + this.avatarFileName);
      //return require("../../assets/avatar/user/default_photo.png");
    }
  },
  methods: {

  }
};
</script>

<style lang="less">
.header {
  // 盒模型
  margin: 0;
  padding: 0;
  width: 100%;
  height: 50px;

  // 排版
  text-align: left;
  // 边框背景
  background-color: black;
  .header-content {
    position: relative;
    height: 100%;
    margin-left: 150px;
    #logo {
      position: relative;
      top: 7px;
      //left: 150px;
    }
    .nav {
      display: inline-block;
    }
    .user-nav {
      display: inline-block;
      position: relative;
      right: -700px;
      .uphoto {
        position: relative;
        top: 9px;
        left: 8px;
        width: 30px;
        height: 30px;
        border-radius: 50%;
      }
    }
  }
}
a {
  text-decoration: none;
  margin-left: 25px;
  color: white;
  &:hover {
    color: #5dd4c7;
  }
}
.router-link-active {
  color: #5dd4c7;
}
</style>
