<template>
  <div class="login-box">
    <div id="title">JOBHUNTER</div>
    <div class="input_area">
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules">
        <el-form-item prop="phonenum">
          <!-- 账号输入框 -->
          <el-input
            v-model="loginForm.phonenum"
            placeholder="请输入手机号"
            prefix-icon="el-icon-mobile"
            oninput="value=value.replace(/[^\d]/g,'')"
          ></el-input>
        </el-form-item>

        <el-form-item prop="password">
          <!-- 密码输入框 -->
          <el-input
            v-model="loginForm.password"
            placeholder="请输入密码"
            prefix-icon="el-icon-lock"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item>
          <!-- 登陆按钮 -->
          <el-button
            class="login_btn"
            style="width: 100%"
            @click="login('loginForm')"
          >
            <label>登陆</label>
          </el-button>
        </el-form-item>
      </el-form>
      <div id="last-btn-group">
        <router-link class="line-btn" to="/register">注册账号</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginBox",
  data() {
    // 手机号验证
    var checkPhone = (rule, value, callback) => {
      const phoneReg = /^1[3|4|5|6|7|8][0-9]{9}$/; // 手机号验证正则
      if (!value) {
        return callback(new Error("电话号码不能为空"));
      }
      setTimeout(() => {
        if (!Number.isInteger(+value)) {
          callback(new Error("请输入数字值"));
        } else {
          if (phoneReg.test(value)) {
            callback();
          } else {
            callback(new Error("电话号码格式不正确"));
          }
        }
      }, 100);
    };
    // 密码检验
    // var checkPass = (rule, value, callback) => {
      /*
        密码验证正则
        1.密码必须由字母、数字组成，区分大小写
        2.密码长度为8-18位
      */
      // const passReg = /^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{8,18}$/;
      // if (!value) {
      //   return callback(new Error("密码不能为空"));
      // }
      // setTimeout(() => {
      //   if (passReg.test(value)) {
      //     callback();
      //   } else {
      //     callback(new Error("密码格式不正确"));
      //   }
      // }, 100);
    // };
    return {
      loginForm: {

      },
      token: "",
      loginInfo: {
        uname: "",
        uid: "",
        avatar: ""
      },
      loginRules: {
        phonenum: [{ required: true, validator: checkPhone, trigger: "blur" }],
        // password: [{ required: true, validator: checkPass, trigger: "blur" }],
      },
    };
  },
  created(){
    this.getCaptcha();
  },
  methods: {
    login(forname) {
        // 判断是否通过了输入项规则校验
        this.$refs[forname].validate((valid) => {
          if (valid) {
            // 到了这里说明通过了前端检验,发送账号密码到后端检验
            this.$http.get(`/login`, {params: {phone: this.loginForm.phonenum,password:this.loginForm.password}}).then((res) => {
                // 这里会获得后端传来的用于检验
                let isVerify = true
                if (res.data === ""){
                  isVerify = false;
                } else {
                  if(res.data.flag){
                    isVerify=true;
                    // 将 token 存入 sessionStorage
                    this.token = res.data.user;
                    sessionStorage.setItem("token",res.data.user);
                  }

                }
                //TODO 如果通过校验，提示成功并进行页面跳转
                if (isVerify) {
                  // // 获取并记录用户信息

                    // 存入登录信息
                    //this.loginInfo.token = token;
                    // this.loginInfo.uname = res.data.uname;
                    // this.loginInfo.uid = res.data.uid;
                    // this.loginInfo.avatar = res.data.avatar;
                    // // 存储token
                    // this.$store.commit("login", this.loginInfo);
                  //   // 跳转到主页面
                    this.$router.push("/main/home"); //跳到主页面
                    // 提示登录成功
                    this.$notify({
                      title: "success",
                      message: "登陆成功",
                      type: "success",
                    });
                    ///////// 注意问题，在不同 axios 中，data数据是不共享的
                  // }).catch(()=>{

                  // })
                } else {
                  this.$alert("账号或密码有误，请重新输入", {
                    type: "error",
                    showClose: false,
                    confirmButtonText: "确定",
                    callback: () => {
                      this.$message({
                        type: "warning",
                        message: "请输入正确的账号或密码",
                      });
                    },
                  });
                }
              })
              .catch((err) => {
                console.log(err);
              });
          } else {
            this.$alert("账号或密码有误，请重新输入", {
              type: "error",
              showClose: false,
              confirmButtonText: "确定",
              callback: () => {
                this.$message({
                  type: "warning",
                  message: "请输入正确的账号和密码",
                });
              },
            });
          }
        });
    },
  },
};
</script>

<style lang="less">
.login-box {
  position: relative;
  width: 600px;
  height: 450px;
  top: 200px;
  left: 450px;
  border-radius: 10px;
  background-color: #f8fcff;
  opacity: 0.85;
  box-shadow: 1px 1px green;
  #title{
    position: absolute;
    top: 30px;
    left: 100px;
    color: #134857;
    font-size: 30px;
    font-style: italic;
    font-weight: bold;
  }
  .input_area {
    position: relative;
    width: 400px; // 输入框的宽度
    left: 100px;
    top: 80px;
    #captcha-input {
      width: 250px;
    }
    #captcha {
      position: absolute;
      left: 300px;
      width: 80px;
      height: 40px;
      cursor: pointer;
    }
    // 修改 element-ui 按钮默认颜色的方式
    .login_btn {
      background-color: #5dd5c8;
      border-color: #5dd5c8;
      color: #fff;
      &:hover {
        background-color: rgba(93, 213, 200, 0.8);
        border-color: rgba(93, 213, 200, 0.8);
        color: #fff;
      }
      width: 100%;
    }
    #last-btn-group {
      position: relative;
      top: -15px;
      left: 200px;
      .line-btn {
        color: gray;
        &:hover {
          font-weight: bold;
        }
      }
    }
  }
}
</style>
