<template>
  <div class="header">
    <div class="header-content">
      <router-link to="/main/home">
        <img id="logo" alt="logo" src="../../assets/jh-logo.png"/>
      </router-link>
      <div class="nav">
        <router-link class="header-btn" to="/main/home">首页</router-link>
        <router-link class="header-btn" to="/main/job">职位</router-link>
        <router-link class="header-btn" to="/main/enterprise">企业</router-link>

      </div>
      <div class="user-nav">
        <router-link class="header-btn" to="/main/resume">信息管理</router-link>
        <el-dropdown @command="goOut"  >
          <span class="el-dropdown-link">
        欢迎！{{ headUname }}！
      </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-if="flag" command="0" style="width: 100px">注销</el-dropdown-item>
            <router-link to="/login"><el-dropdown-item v-if="!flag" command="1" style="width: 100px">去登录</el-dropdown-item></router-link>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  created() {
    const token = sessionStorage.getItem("token")
    let user_name = sessionStorage.getItem("user_name")
    if (token) {
      this.headUname = user_name;
      this.flag = !this.flag
    }
    // else{
    //     this.headUname = token.uname
    //   this.         flag = this.flagUserExised
    // }
  },
  data() {
    return {
      flag: false,
      avatarFileName: this.$store.state.avatar,
      headUname: "请登录"
    }
  },
  computed: {

    // uname() {
    //   const token = sessionStorage.getItem("token")
    //   if(token.uname = null){
    //
    //   }
    //   // return this.token.uname
    //   return  this.$store.state.uname;
    // },
    avatar() {
      return require("../../assets/avatar/user/" + this.avatarFileName);
      //return require("../../assets/avatar/user/default_photo.png");
    }
  },
  methods: {
    goOut(command) {
      if (command == "1"){
        this.$router.push("/login")
      }
      else if (command == "0"){
        sessionStorage.clear();//清空sessionStorage中所有信息
        this.$http.get("/zhuxiao").then((res)=>{
          if(res.data.flag){
            this.$router.push("/login")
          }
        }).catch(() => {
          this.$message.error("暂时无法注销");
        });

      }

    }
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

.el-dropdown-link {
  cursor: pointer;
  color: white;
}

a {
  text-decoration: none;
  margin-left: 25px;
  color: white;

  &:hover {
    color: #5dd4c7;
  }
}
.gogo{
  color: black;
}
.router-link-active {
  color: #5dd4c7;
}

.el-dropdown {
  margin-left: 30px;

}

</style>
