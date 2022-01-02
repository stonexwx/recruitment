<template>
  <div id="login">
    <LoginBox />
  </div>
</template>

<script>
import LoginBox from "@/components/login/LoginBox.vue";

export default {
  name: "Login",
  components: {
    LoginBox,
  },
};
</script>

<style lang="less">
#login {
  background: url(../assets/loginbg.png) no-repeat;
  width: 100%; // 登陆区域宽度
  height: 793px; // 登陆区域高度
  background-size: 100% 100%;
  //background-color: #5dd5c8;
  background-color: #0a5e69;
}
</style>
