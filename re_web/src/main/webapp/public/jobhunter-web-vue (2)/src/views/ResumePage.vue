<template>
  <div class="content">
    <el-tabs type="border-card">
      <el-tab-pane label="个人信息">
        <div class="headimg">
          <el-upload
            class="avatar-uploader"
            :action="action"
            :show-file-list="true"
            :on-change="handleChange"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
        <el-form
          ref="ruleForm"
          :model="ruleForm"
          :rules="rules"
          label-width="120px"
          class="demo-ruleForm"
        >
          <el-form-item label="用户昵称" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="用户姓名" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="用户电话" prop="edu_phone">
            <el-input v-model="ruleForm.edu_phone"></el-input>
          </el-form-item>
          <el-form-item label="用户邮箱" prop="email">
            <el-input v-model="ruleForm.email"></el-input>
          </el-form-item>
          <el-form-item label="学历水平" prop="education">
            <el-select v-model="ruleForm.education" placeholder="还未填写">
              <el-option
                :label="item"
                :value="item"
                v-for="(item, index) in education"
                :key="index"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="ruleForm.sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="简历信息" prop="resume">
            <el-row>
              <el-upload
                class="avatar-uploader"
                :action="action_resume"
                :show-file-list="true"
                :on-change="handleChange_resume"
              >
                <img
                  v-if="imageUrl_resume"
                  :src="imageUrl_resume"
                  class="avatar"
                />
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-row>
          </el-form-item>
          <el-form-item label="学历证书" prop="photo">
            <el-row>
              <el-col :span="24">
                <el-form-item>
                  <el-upload
                    class="avatar-uploader"
                    :action="action_photo"
                    :show-file-list="true"
                    :on-change="handleChange_photo"
                  >
                    <img
                      v-if="imageUrl_photo"
                      :src="imageUrl_photo"
                      class="avatar"
                    />
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')"
              >保存</el-button
            >
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form></el-tab-pane
      >
      <el-tab-pane label="修改密码"
        ><el-form
          ref="ruleForm2"
          :model="ruleForm2"
          label-width="120px"
          class="demo-ruleForm"
        >
          <el-form-item label="请输入当前密码" prop="password">
            <el-input v-model="ruleForm2.password"></el-input>
          </el-form-item>
          <el-form-item label="输入新密码" prop="newpassword">
            <el-input v-model="ruleForm2.newpassword"></el-input>
          </el-form-item>
          <el-form-item label="再次输入新密码" prop="newpassword2">
            <el-input v-model="ruleForm2.newpassword2"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="changePassword('ruleForm2')"
              >保存</el-button
            >
            <el-button @click="resetForm('ruleForm2')">重置</el-button>
          </el-form-item>
        </el-form></el-tab-pane
      >
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "ResumePage",
  data() {
    return {
      imageUrl: "",
      imageUrl_resume: "",
      imageUrl_photo: "",
      action: "/seeker_head_up",
      action_resume: "/seeker_resume_up",
      action_photo: "/seeker_books_up",
      // 如果在html中携带accept则默认选择规定类型的文件，否则可以在钩子函数中判断上传的文件类型
      accept: ".jpg,.png",
      // .doc,.docx,.ppt,.xls,.xlsx,.wps,.dps,.pdf,.txt,
      fileList: [],
      education: [
        "未受教育",
        "小学",
        "初中",
        "高中",
        "专科",
        "本科",
        "研究生",
        "硕士",
        "博士",
        "博士后",
      ],
      ruleForm: {
        username: "哈哈哈哈哈",
        name: "薛文潇",
        education: "本科",
        email: "4990590",
        edu_phone: "1111111111",
        sex: "男",
        photo: "",
        resume: "",
      },
      ruleForm2: {
        password: "",
        newpassword: "",
        newpassword2: "",
      },
      rules: {
        name: [
          {
            required: true,
            message: "请填写您的昵称",
            trigger: "blur",
          },
        ],
        education: [
          {
            required: true,
            message: "请选择您的学历水平",
            trigger: "change",
          },
        ],
        sex: [
          {
            required: true,
            message: "请选择您的性别信息",
            trigger: "change",
          },
        ],
        edu_phone: [
          {
            required: true,
            message: "请输入您的电话",
            trigger: "change",
          },
        ],
        email: [
          {
            required: false,
            message: "请选择您的邮箱",
            trigger: "change",
          },
        ],
        password: [
          {
            required: true,
            message: "请输入您的密码",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    //回显
    handleChange(file) {
      let fileName = file.name;
      let regex = /(.jpg|.jpeg|.gif|.png|.bmp)$/;
      if (regex.test(fileName.toLowerCase())) {
        this.imageUrl = URL.createObjectURL(file.raw);
      } else {
        this.$message.error("请选择图片文件");
      }
    },
    handleChange_resume(file) {
      let fileName = file.name;
      let regex = /(.jpg|.jpeg|.gif|.png|.bmp)$/;
      if (regex.test(fileName.toLowerCase())) {
        this.imageUrl_resume = URL.createObjectURL(file.raw);
      } else {
        this.$message.error("请选择图片文件");
      }
    },
    handleChange_photo(file) {
      let fileName = file.name;
      let regex = /(.jpg|.jpeg|.gif|.png|.bmp)$/;
      if (regex.test(fileName.toLowerCase())) {
        this.imageUrl_photo = URL.createObjectURL(file.raw);
      } else {
        this.$message.error("请选择图片文件");
      }
    },
    //正式数据 表单1
    // created() {
    //   const token = sessionStorage.getItem("token");
    //   this.$http({
    //     method: "POST",
    //     url: "/seeker_select",
    //     data: {},
    //   })
    //     .then((res) => {
    //       this.ruleForm.username = token.user_name;
    //       this.ruleForm = res.data;
    //     })
    //     .catch(() => {
    //       this.$message.error("个人信息获取异常");
    //     });
    // },

    //正式数据 表单2

    // changePassword(passwordForm) {
    //   if (passwordForm.newpassword == passwordForm.newpassword2) {
    //     this.$http({
    //       method: "POST",
    //       url: "/password_update",
    //       data: {
    //         password: this.passwordForm.password,
    //         newpassword: this.passwordForm.newpassword,
    //       },
    //     })
    //       .then((res) => {
    //         if (res.flag) {
    //           console.log("修改成功");
    //         } else {
    //           this.$message.error("当前密码输入错误，请重新输入！");
    //         }
    //       })
    //       .catch(() => {
    //         this.$message.error("发生错误啦");
    //       });
    //   } else {
    //     this.$message.error("两次输入的新密码不一致哦~请重新输入！");
    //   }
    // },
    uploadSuccess(res) {
      // 图片上传成功后即调用的函数
      if (res.data.code === 0) {
        this.$message({
          message: "上传成功",
          type: "success",
          duration: 1000,
        });
        // 上传成功后为当前页面中的img赋值src，即照片回显（回显的地址是后台传递给前端的）
        this.imgShow = true;
        this.imgSrc = res.data.uploadUrl;
      } else {
        this.$message({
          message: res.msg,
          type: "error",
          duration: 1000,
        });
      }
      this.confirmLoading = false;
      this.$refs.upload.clearFiles();
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            method: "POST",
            url: "/seeker_update",
            data: {
              name: this.formName.name,
              username: this.formName.username,
              education: this.formName.education,
              sex: this.formName.sex,
              edu_phone: this.formName.edu_phone,
              email: this.formName.email,
            },
          })
            .then((res) => {
              this.ruleForm = res.data;
            })
            .catch(() => {
              this.$message.error("暂时无法保存哦");
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style lang="less" scoped>
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
  width: 120px;
  height: 120px;
  text-align: center;
}
.avatar-uploader-icon svg {
  margin-top: 74px; /* (178px - 28px) / 2 - 1px */
}
.avatar {
  width: 120px;
  height: 120px;
  display: block;
}
.content {
  width: 80%;
  height: 500px;
  margin: 40px auto;
}
.headimg {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

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

.el-upload {
  img {
    width: 140px;
    height: 140px;
  }
}
</style>