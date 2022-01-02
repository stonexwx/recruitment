<template>
  <div class="job-detail">
    <!-- 工作详情页横幅 -->
    <div class="job-banner">
      <div class="banner-info">
        <div class="title">
          <span class="job-name">{{ pageContent.reInfo.job_name }}&nbsp;</span>
          <span class="job-salary">{{ pageContent.reInfo.scale }}</span>
        </div>
        <div class="job-description">
          <span>{{ pageContent.reInfo.full_path }}</span
          >&nbsp;▪&nbsp; <span>{{ pageContent.reInfo.job_time }}</span
          >&nbsp;▪&nbsp;
          <span>{{ pageContent.reInfo.re_education }}</span>
        </div>
        <el-button @click="drawer = true" v-if="showButton">预约面试</el-button>
        <el-drawer
          title="预约面试"
          :visible.sync="drawer"
          :direction="direction"
        >
          <span>请选择您的面试时间如:2021-02-02</span>
          <el-date-picker v-model="value1" type="date" placeholder="选择日期">
          </el-date-picker>
          <div class="interview-button">
            <el-button type="primary" @click="interview()">预约面试</el-button>
          </div></el-drawer
        >
      </div>
    </div>
    <div class="job-content">
      <!-- 工作具体介绍 -->
      <div class="job-info">
        <!--招聘者简要信息-->
        <div class="job-info-detail">
          <!--职位介绍-->
          <my-h1 :content="title1" />
          <div class="the-content" v-html="pageContent.reInfo.requirement"></div>
        </div>
      </div>
      <!-- 公司简介 -->
      <div class="company-info">
        <div id="title1" style="color: gray">公司基本信息</div>
        <div id="company-name" @click="enterpriseDetail()">
          {{ pageContent.ename }}
        </div>
        <ul>
          <li class="el-icon-menu" @click="enterpriseDetail()">
            <span class="company-items">{{
              pageContent.reInfo.job_welfare
            }}</span>
          </li>
          <li class="el-icon-menu" @click="enterpriseDetail()">
            <span class="company-items">{{ pageContent.job2Name }}</span>
          </li>
        </ul>
      </div>
      <img class="advertisement" src="../assets/ad-demo.png" alt="" />
    </div>
  </div>
</template>

<script>
import MyH1 from "@/components/common/MyH1";
export default {
  name: "JobDetail",
  methods: {
    created() {
      const token2 = sessionStorage.getItem("token2");
      this.pageContent = token2;
    },
    interview() {
      const token = sessionStorage.getItem("token");
      this.$http({
        method: "POST",
        url: "/intereview_insert",
        data: {
          rid:this.pageContent.reInfo.rid,
          uid: token.uid,
          value1: this.value1,
        },
      })
        .then((res) => {
          if (res.flag) {
            console.log("预约成功 您的预约时间是" + this.value1);
          } else {
            this.$message.error("预约数据错误");
          }
        })
        .catch(() => {
          this.$message.error("预约失败");
        });
    },
    enterpriseDetail() {
      this.$router.push("/main/companyDetail/"+this.pageContent.reInfo.rid);
    },
  },
  props: ["rid"],
  data() {
    return {
      value1: "",
      drawer: false,
      direction: "rtl",
      title1: "职位介绍",
      pageContent: {
        ename: "奇思妙想",
        job2Name: "IT",
        reInfo: {
          eid:"",
          rid:"",
          addtime: "2021-10-09",
          full_path: "深圳",
          job_name: "java工程师",
          scale: "10K", //工资
          job_time: "1-3",
          job_welfare: "下午茶",
          re_education: "本科",
          requirement:"这个工作要会的很多，首先就是反抗资本主义薛文潇，不能让资本主义入侵我们的生活，要学会和资本主义家薛文潇斗争到底！",
        },
      },
    };
  },
  components: {
    "my-h1": MyH1,
  },
};
</script>

<style lang="less" scoped>
.el-drawer {
  span {
    margin-left: 20px;
  }
  .el-date-editor {
    margin: 30px 0px 10px 20px;
  }
  .el-button {
    margin: 10px 20px;
  }
}
.job-detail {
  .job-banner {
    width: 100%;
    height: 180px;
    background-color: #134857;
    .banner-info {
      width: 800px;
      position: relative;
      top: 30px;
      left: 175px;
      .title {
        font-size: 30px;
        font-weight: bolder;
        .job-name {
          color: white;
        }
        .job-salary {
          color: red;
        }
      }
      .job-description {
        margin: 15px 0;
        color: #c7c9be;
      }
      .welfare-item {
        padding: 3px;
        margin-right: 10px;
        color: #5dd5c8;
        border: 1px solid #5dd5c8;
        border-radius: 5px;
      }
    }
  }
  .job-content {
    position: relative;
    top: 5px;
    .job-info {
      width: 800px;
      position: relative;
      left: 175px;
      //background-color: #223e36;
      .job-info-detail {
        overflow: auto;
        height: 393.5px;
        margin-top: 5px;
        margin-bottom: 5px;
        background-color: white;
        .the-content {
          line-height: 30px;
          width: 750px;
          margin: 10px;
          color: #134857;
        }
      }
    }
    .company-info {
      width: 160px;
      height: 220px;
      position: absolute;
      top: 1px;
      left: 985px;
      background-color: white;
      padding: 20px;
      #title1 {
        margin-bottom: 30px;
      }
      #company-avatar {
        width: 60px;
        border-radius: 10px;
      }
      #company-name {
        position: relative;

        top: -20px;
        font-weight: bold;
      }
      ul {
        width: 150px;
        padding: 0;
        margin: 0;
        list-style: none;
        li {
          margin: 0 0 20px;
          width: 150px;
          .company-items {
            margin-left: 20px;
            font-size: 15px;
          }
        }
      }
    }
    .advertisement {
      width: 200px;
      position: absolute;
      top: 280px;
      left: 985px;
      background-color: red;
    }
  }
}
video {
  width: 150px;
}
</style>
