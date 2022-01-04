<template>
  <div class="home_page">
    <el-divider></el-divider>
    <!-- 首页轮播图 -->
    <div class="scroll-news">
      <el-carousel
        :interval="4000"
        type="card"
        height="200px indicator-position=none"
      >
        <el-carousel-item v-for="(item, index) in carouselArr" :key="index">
          <img :src="item.url" alt="" />
        </el-carousel-item>
      </el-carousel>
    </div>
    <el-divider></el-divider>
    <!-- 职位推荐区 -->
    <div class="job-recommend-box">
      <h1>
        <span class="line">——&nbsp;</span>职位推荐<span class="line"
          >&nbsp;——</span
        >
      </h1>
      <joblist class="job-list" v-bind:job-items="joblist" />
    </div>
  </div>
</template>

<script>
import JobList from "@/components/job/JobList.vue";
export default {
  name: "HomePage",
  data() {
    return {
      carouselArr: [
        { url: require("../assets/jobnews1.jpg") },
        { url: require("../assets/jobnews2.jpg") },
        { url: require("../assets/jobnews3.jpg") },
        { url: require("../assets/jobnews4.jpg") },
      ],
      joblist: [
        //此数据仅为测试使用
        // {
        //   // ename: "奇思妙想",
        //   // job2Name: "IT",
        //   // reInfo: {
        //   //   eid:"1",
        //   //   rid: "1",
        //   //   addtime: "2021-10-09",
        //   //   full_path: "深圳",
        //   //   job_name: "java工程师",
        //   //   scale: "10000", //工资
        //   //   job_time: "1-3",
        //   //   job_welfare: "入职每人奖励一个薛文潇",
        //   //   re_education: "本科",
        //   //   requirement:""
        //   },
        // },
      ],
    };
  },
  components: {
    joblist: JobList,
  },
  created() {
    this.$http({
      method: "POST",
      url: "/reinfoAll",
      data: {
      }
    }).then((res)=>{

      this.joblist = res.data;
    }).catch(()=>{
      this.$message.error('工作信息获取异常');
    })
  },

};
</script>

<style lang="less">
// 大标题修饰线
.line {
  color: #d1d4db;
  font-weight: lighter;
}
.home_page {
  //color: #5dd5c8;
  background-color: #f6f6f8;
  h1 {
    text-align: center;
    font-weight: lighter;
  }
  .scroll-news {
    position: relative;
    width: 1300px;
    height: 300px;
    left: 100px;
    top: 10px;
    // background-color: #a4b0be;
    ul {
      position: relative;
      list-style: none;
      display: inline-block;
      left: 100px;
      //top: 2px;
      li {
        width: 20px;
        padding-right: 10px;
      }
    }
    .el-carousel__item {
      img {
        width: 100%;
        height: 100%;
      }
      box-shadow: 0px 1px 4px rgba(0, 0, 0, 0.3),
        0 0 40px rgba(0, 0, 0, 0.1) inset;
    }
    .el-carousel__item:nth-child(2n) {
      background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n + 1) {
      background-color: #d3dce6;
    }
  }
}
</style>
