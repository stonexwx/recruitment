<template>
  <div class="personal_page">
    <div id="side-nav">
      <el-menu
          default-active="3"
          class="el-menu-vertical-demo">
        <el-menu-item index="1" @click="changeContentView(1)">
          <i class="el-icon-suitcase-1"></i>
          <span slot="title">投递历史</span>
        </el-menu-item>
        <el-menu-item index="2"  @click="changeContentView(2)">
          <i class="el-icon-user"></i>
          <span slot="title">信息管理</span>
        </el-menu-item>
        <el-menu-item index="3" @click="changeContentView(3)">
          <i class="el-icon-setting"></i>
          <span slot="title">账号设置</span>
        </el-menu-item>
      </el-menu>
    </div>
    <div id="content">
      <div>
        <!-- 已投简历展示区 -->
        <div v-if="contentView === 1" class="jobList">
          <div class="title">已投递简历列表</div>
          <div>
            <el-table :data="recordList" stripe border style="width: 100%; padding-left: 50px">
              <el-table-column prop="job.jobId" label="工作ID" hidden width="100px"/>
              <!--<el-table-column prop="index" label="序号" width="100px"/>-->
              <el-table-column prop="sendDate" label="简历投递日期" width="180px"/>
              <el-table-column prop="company.name" label="公司名称" width="100px"/>
              <el-table-column prop="job.jobName" label="工作名称" width="100px"/>
              <el-table-column prop="fullPath" label="工作地址" width="100px"/>
              <el-table-column label="操作" width="150px">
                <template slot-scope="scope">
                  <el-button
                      size="mini"
                      @click="toJobDetail(scope.row.job.jobId)">
                    查看工作详情
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </div>
        <!-- 个人信息管理区 -->
        <div v-if="contentView === 2" class="message-update">
          <div class="title">个人信息管理区</div>
          <div id="userInfoManageBox">
            <el-form ref="form" :model="sizeForm" label-width="80px" size="mini">
              <div class="sub-title"><span class="el-icon-user"></span> 基本信息</div>
              <el-form-item label="用户头像">
                <br>
                <el-upload
                    class="avatar-uploader"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                  <img v-if="imageUrl" :src="user_avatar" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
              </el-form-item>
              <el-form-item label="用户名">
                <el-input v-model="sizeForm.uname"></el-input>
              </el-form-item>
              <el-form-item label="性别">
                <el-radio-group v-model="sizeForm.usex" size="medium">
                  <el-radio border label="先生"></el-radio>
                  <el-radio border label="女士"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="当前身份">
                <el-input v-model="sizeForm.urole" placeholder="如应届毕业生、自由工作者.."></el-input>
              </el-form-item>
              <el-form-item label="个人简介">
                <el-input
                    type="textarea"
                    :autosize="{ minRows: 2, maxRows: 4}"
                    placeholder="请输入内容"
                    v-model="sizeForm.introduce">
                </el-input>
              </el-form-item>
              <el-form-item label="资格证书">
                <el-input v-model="sizeForm.certificate" placeholder="多个请用、分隔"></el-input>
              </el-form-item>

              <div class="sub-title"><span class="el-icon-user"></span> 联系方式</div>
              <el-form-item label="手机号">
                <el-input v-model="sizeForm.phone" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="邮箱号">
                <el-input v-model="sizeForm.email"></el-input>
              </el-form-item>
              <el-form-item label="微信号">
                <el-input v-model="sizeForm.wechat"></el-input>
              </el-form-item>

              <div class="sub-title"><span class="el-icon-user"></span> 求职意向</div>
              <el-form-item label="职业类型">
                <el-input v-model="sizeForm.jobType"></el-input>
              </el-form-item>
              <el-form-item label="意向城市">
                <el-input v-model="sizeForm.jobCity" placeholder="填写市级地址，如佛山市"></el-input>
              </el-form-item>
              <el-form-item label="意向薪资">
                <el-input v-model="sizeForm.jobSalary" placeholder="填写市级地址，如佛山市"></el-input>
              </el-form-item>

              <div class="sub-title"><span class="el-icon-user"></span> 职业经验</div>
              <el-form-item label="经历名称">
                <el-input v-model="sizeForm.ename" placeholder="项目名或经历命名"></el-input>
              </el-form-item>
              <el-form-item label="扮演角色">
                <el-input v-model="sizeForm.erole" placeholder="在这次工作经历中扮演的角色"></el-input>
              </el-form-item>
              <el-form-item label="经历描述">
                <el-input
                    type="textarea"
                    :autosize="{ minRows: 2, maxRows: 4}"
                    placeholder="简要描述该经历"
                    v-model="sizeForm.eintroduce">
                </el-input>
              </el-form-item>

              <el-form-item size="large">
                <el-button type="primary" @click="onSubmit">更新个人资料</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
        <!-- 设置区 -->
        <div v-if="contentView === 3" class="setting">
          <div id="changePasswordBox">
            <div class="title">修改密码</div>
            <div class="pwd-input-box">
              <el-input placeholder="请输入旧密码" v-model="oldPwd" show-password/>
              <el-input placeholder="请输入新密码" v-model="newPwd" show-password/>
              <el-input placeholder="请再次输入新密码" v-model="confirmNewPwd" show-password/>
            </div>
            <el-button id="change-pwd-btn" type="primary" round @click="changePwd">修改密码</el-button>
          </div>
          <div id="logoutBox">
            <div class="title">账号退登</div>
            <el-button id="logout-btn" type="warning" round @click="logout">退出登录</el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PersonalPage",
  created() {
    // 获取用户信息
    this.$http({
      method: "POST",
      url: "/jobhunter/user-info/getUserByToken",
      headers: {
        "token": sessionStorage.getItem("token")!==null?sessionStorage.getItem("token"):""
      },
    }).then((res)=>{
      console.log(res.data);
      this.user = res.data;
      let u = res.data;
      this.sizeForm.uid = u.userInfo.uid;
      this.sizeForm.uname = u.userInfo.uname;
      let sex = u.userInfo.sex;
      if (sex===1){
        this.sizeForm.usex = "先生";
      } else {
        this.sizeForm.usex = "女士"
      }
      this.sizeForm.urole = u.userInfo.role;
      this.sizeForm.introduce = u.userInfo.introduce;
      this.sizeForm.certificate = u.userInfo.certificate;
      this.sizeForm.phone = u.userInfo.phone;
      this.sizeForm.email = u.userInfo.email;
      this.sizeForm.wechat = u.userInfo.wechat;
      this.sizeForm.jobType = u.targetJobType;
      this.sizeForm.jobCity = u.targetCity;
      this.sizeForm.jobSalary = u.userInfo.targetSalary;
      this.sizeForm.ename = u.experience.ename;
      this.sizeForm.erole = u.experience.erole;
      this.sizeForm.eintroduce = u.experience.edescribe;
    }).catch(()=>{
      this.$message.error('用户信息请求异常');
    })

    // 获取简历投递历史记录
    this.$http.post("/jobhunter/resume-mailing-list/getResumeSendList",{
      uid: sessionStorage.getItem("uid"),
    }).then((res)=>{
      this.recordList = res.data;
    }).catch(()=>{
      this.$message.error('简历投递记录请求异常');
    })
  },
  methods: {
    // 切换导航子页面
    changeContentView(targetViewNum) {
      this.contentView = targetViewNum;
    },
    toJobDetail(jobID){
      console.log(jobID);
      //TODO 跳转到工作详情页
      this.$router.push("/main/detail/"+jobID);
    },
    // 修改密码方法
    changePwd(){
      // 保证新旧密码和确认密码都输入
      if (this.oldPwd!==""&&this.newPwd!==""&&this.confirmNewPwd!==""){
        if (this.newPwd === this.confirmNewPwd){
          // 先判断旧密码是否正确
          this.$http.post("/jobhunter/account/checkPassword",{
            accountId: sessionStorage.getItem("aid"),
            password: this.oldPwd,
          }).then((res)=>{
            if (res.data){
              // 旧密码正确发送修改请求
              //TODO 修改密码后端请求
              this.$http.post("/jobhunter/account/changPass",{
                accountId: sessionStorage.getItem("aid"),
                password: this.confirmNewPwd,
              }).then((res)=>{
                if (res.data){
                  this.$notify({
                    title: '成功',
                    message: '密码修改成功, 请重新等登录',
                    type: 'success'
                  });
                  // 要求重新登录
                  this.logout();
                } else {
                  this.$message.error('密码修改失败，请联系开发人员');
                }
              }).catch(()=>{
                this.$message.error('密码修改请求异常，请联系开发人员');
              })
            } else {
              // 旧密码验证错误，提示错误
              this.$alert('旧密码错误，请重新输入', '提示', {
                confirmButtonText: '确定',
                type: 'warning'
              })
            }
          }).catch(()=>{
            this.$message.error('原密码检验请求异常，请联系开发人员');
          })
        } else {
          this.$alert('两次输入的密码不一致，请重新输入', '提示', {
            confirmButtonText: '确定',
            type: 'warning'
          })
        }
      }else {
        // 提示三项密码都必须填
        this.$alert('密码输入项不可为空，请重新输入', '提示', {
          confirmButtonText: '确定',
          type: 'warning'
        })
      }
    },
    // 退出登录方法
    logout(){
      this.$confirm('确定要退出该账号吗？', '退登确认', {
        confirmButtonText: '确定',
        type: 'warning'
      }).then(()=>{
        // 清除账户状态记录
        this.$store.commit("logout");
        // 跳转到登录页面
        this.$router.push("/login");
        this.$notify({
          title: '成功',
          message: '账号已退出',
          type: 'success'
        });
      })
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    // 用户信息表单修改
    onSubmit(){
      this.$http.post("/jobhunter/account/update",this.sizeForm)
      .then(()=>{
        this.$message.success("用户信息更新成功")
      }).catch(()=>{
        this.$message.error('个人信息修改请求失败');
      })
    }
  },
  data() {
    return {
      contentView: 2,
      oldPwd: "",
      newPwd: "",
      confirmNewPwd: "",
      imageUrl: "",
      recordList: [],
      user: {},
      sizeForm: {
        uid: "",
        uname: "",
        usex: "",
        urole: '',
        introduce: "",
        certificate: "",
        phone: "",
        email: "",
        wechat: "",
        jobType: "",
        jobCity: "",
        jobSalary: "",
        ename: "",
        erole: "",
        eintroduce: ""
      },
    }
  },
  computed: {
    user_avatar() {
      return require("../assets/avatar/user/" + sessionStorage.getItem("avatar"));
    }
  }
};
</script>

<style lang="less">
.personal_page {
  position: relative;
  #side-nav {
    width: 200px;
    height: 719px;
    background-color: #f8f4ed;
    .el-menu {
      width: 200px;
      margin: 0;
      padding: 0;
      .el-submenu__title {
        margin: 0;
        padding: 0;
        width: 200px;
      }
    }
    .el-menu-item {
      margin: 0;
      padding: 0;
      width: 200px;
      &:active {
        color: #134857;
      }
    }
  }

  #content {
    position: absolute;
    top: 0;
    left: 300px;
    width: 800px;
    height: 719px;
    background-color: #f8f4ed;
    .jobList {
      .title {
        color: #134857;
        font-weight: bold;
        margin-top: 10px;
        margin-left: 10px;
        margin-bottom: 20px;
      }
    }
    .message-update {
      height: 719px;
      overflow: auto;
      margin-bottom: 20px;
      .title {
        color: #134857;
        font-weight: bold;
        margin-top: 10px;
        margin-left: 10px;
        margin-bottom: 20px;
      }
      #userInfoManageBox {
        width: 400px;
        overflow: auto;
        position: relative;
        left: 50px;
        .sub-title {
          margin: 10px 0;
          color: deeppink;
          font-weight: bold;
        }
        // 下面都是element-ui头像上传按钮的样式
        .avatar-uploader .el-upload {
          border: 1px dashed #d9d9d9;
          border-radius: 6px;
          cursor: pointer;
          position: relative;
          overflow: hidden;
        }
        .avatar-uploader .el-upload:hover {
          border-color: #409EFF;
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
    }
    .setting {
      margin-top: 20px;
      margin-left: 80px;
      .title {
        color: #134857;
        font-weight: bold;
        margin-bottom: 30px;
      }
      #changePasswordBox {
        width: 600px;
        margin-top: 10px;
        padding: 20px;
        border: 1px solid gray;
        border-radius: 10px;
        .pwd-input-box {
          position: relative;
          width: 400px;
          input {
            margin-left: 80px;
            margin-bottom: 10px;
          }
        }
        #change-pwd-btn {
          position: relative;
          left: 120px;
          width: 300px;
        }
      }
      #logoutBox {
        width: 600px;
        margin-top: 10px;
        padding: 20px;
        border: 1px solid gray;
        border-radius: 10px;
        #logout-btn {
          position: relative;
          left: 120px;
          width: 300px;
        }
      }
    }
  }
}
</style>
