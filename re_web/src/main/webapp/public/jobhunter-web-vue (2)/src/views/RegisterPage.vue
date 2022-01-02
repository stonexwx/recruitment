<template>
  <div id="register-page">
    <div class="content-area">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="手机号">
            <el-input v-model="form.phone"></el-input>
          </el-form-item>
          <el-form-item label="登录密码">
            <el-input v-model="form.password" show-password></el-input>
          </el-form-item>
          <el-form-item label="确认密码">
            <el-input v-model="form.verifyPassword" show-password></el-input>
          </el-form-item>
        </el-form>
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="用户名">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="form.sex" size="medium">
              <el-radio border label="男 ♂" value="男"></el-radio>
              <el-radio border label="女 ♀" value="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="邮箱号">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
        </el-form>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即创建</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "RegisterPage",
 
  data() {
    return {
      imageUrl: "",
      form: {
        phone: "",
        password: "",
        verifyPassword: "",
        name: "",
        sex: "",
        email: "",
      },
    };
  },
  methods: {
    // 头像上传回显项目
    handleChange(file) {
      console.log("handleChange");
      let fileName = file.name;
      let regex = /(.jpg|.jpeg|.gif|.png|.bmp)$/;
      if (regex.test(fileName.toLowerCase())) {
        this.imageUrl = URL.createObjectURL(file.raw);
      } else {
        this.$message.error("请选择图片文件");
      }
    },
    // 账号是否已注册检查
    accountCheck() {
      // 非空检验
      if (
        this.form.phone === "" ||
        this.form.password === "" ||
        this.form.verifyPassword === ""
      ) {
        this.$alert("账号密码不可为空", "注册信息检验", { type: "warning" });
        return;
      }
      // 密码输入确认检验
      if (this.form.password !== this.form.verifyPassword) {
        this.$alert("两次输入的密码不一致，请重新输入", "注册信息检验", {
          type: "warning",
        });
        return;
      }
      // 账号重复性检验
      this.$http
        .get("/jobhunter/account/checkAccount/" + this.form.phone)
        .then((res) => {
          if (res.data) {
            this.nextStep();
          } else {
            this.$confirm("该账号已经注册, 是否前往登录?", "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning",
            })
              .then(() => {
                this.$router.push("/login");
              })
              .catch(() => {
                this.$message({
                  type: "warning",
                  message: "请输入其他手机号作为账号",
                });
              });
          }
        })
        .catch(() => {
          this.$message({
            type: "warning",
            message: "账号重复性检验请求失败~",
          });
        });
    },
    // 表单提交
    onSubmit() {
      this.$http
        .post("/user_insert", this.form)
        .then(() => {
          this.active = 3;
          this.$alert("账号注册成功，请登录", "注册结果", { type: "success" });
          this.$router.push("/login");
        })
        .catch(() => {
          this.$message({ type: "warning", message: "账号注册请求失败~" });
        });
    },
  },
};
</script>

<style lang="less">
#register-page {
  background: url(../assets/loginbg.png) no-repeat;
  width: 100%; // 登陆区域宽度
  height: 740px; // 登陆区域高度
  background-size: 100% 100%;
  //background-color: #5dd5c8;
  background-color: #0a5e69;
  .content-area {
    padding: 40px;
    padding-top: 60px;
    position: relative;
    top: 50px;
    left: 250px;
    width: 1000px;
    height: 550px;
    background-color: white;
    border-radius: 10px;
    opacity: 0.9;
    box-shadow: darkgrey 10px 10px 30px 5px;
    .el-steps {
      position: relative;
      top: 20px;
      left: 250px;
    }
    .window {
      width: 950px;
      height: 600px;
      overflow-y: auto;
      margin-top: 20px;
      margin-left: 20px;
      padding-bottom: 10px;
      //background-color: red;
      border: 1px solid;
      border-radius: 10px;
      .input-area {
        position: relative;
        top: 20px;
        left: 150px;
        width: 600px;
        //background-color: red;
        #account-form-area {
          position: relative;
          top: 150px;
        }
        #user-info-area {
          .avatar-uploader .el-upload {
            border: 1px dashed #d9d9d9;
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
          }
          .avatar-uploader .el-upload:hover {
            border-color: #409eff;
          }
          .avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 178px;
            height: 178px;
            line-height: 178px;
            text-align: center;
          }
          .avatar {
            width: 178px;
            height: 178px;
            display: block;
          }
        }
        #job-area {
          position: relative;
          top: 40px;
        }
      }
    }
  }
}
</style>