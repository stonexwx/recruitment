<template>
  <div class="job_page">
    <FiltrateEnterprise @change-job-list="changeJobList($event)"/>
    <JobList v-bind:job-items="joblist" v-if="!searchConditions.se_type"/>
    <EnterpriseList v-bind:job-items="enterpriselist" v-if="searchConditions.se_type"/>

  </div>
</template>

<script>
import JobList from "@/components/job/JobList.vue";
import EnterpriseList from "@/components/job/EnterpriseList.vue";
import FiltrateEnterprise from "@/components/common/FiltrateEnterprise.vue";
export default {
  name: "JobPage",
  data() {
    return {
      prop:["searchConditions.se_type"],
      joblist: this.$store.state.joblist,
       searchConditions: {
        jobName: "",
        se_type: "1",
        city: "*",
      },
       enterpriselist:[
        {
          address:"",
          ename:"",
          e_email:""
        }
      ],
    }
  },
  // created() {
  //   this.$http({
  //     method: "POST",
  //     url: "/jobhunter/job/getJobs",
  //     data: {
  //       uid: sessionStorage.getItem("uid"),
  //     }
  //   }).then((res)=>{
  //     this.joblist = res.data;
  //     //this.$store.state.joblist = res.data;
  //   }).catch(()=>{
  //     this.$message.error('工作信息获取异常');
  //   })
  // },
  methods: {
    changeJobList(data) {
      this.joblist = data;
    }
  },
  components: {
    JobList,
    EnterpriseList,
    FiltrateEnterprise,
  },
};
</script>

<style lang="less">
ul {
  list-style: none;
  display: inline-block;
  margin-left: 70px;
  li {
    margin-bottom: 40px;
    width: 884px;
  }
}
.job_page {
  position: relative;
  height: 100%;
  color: red;
  //background-color: black;
  .job_visited_list {
    //display: inline-block;
    position: absolute;
    //width: 50px;
    top: 136px;
    left: 1050px;
    //background-color: red;
    .advertisement {
      width: 300px;
    }
  }
}
</style>
