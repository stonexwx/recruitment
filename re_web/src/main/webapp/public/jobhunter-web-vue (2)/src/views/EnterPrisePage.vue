<template>
  <div class="job-recommend-box">
    <h1>
      <span class="line">——&nbsp;</span>企业排行<span class="line"
        >&nbsp;——</span
      >
    </h1>
    <el-table :data="enterpriselist.data" @row-click="enterpriseDetail" stripe style="width: 80%">
      <el-table-column prop="number" label="排名" width="180" />
      <el-table-column prop="enterprise.ename" label="公司名称" width="400" />
      <el-table-column prop="enterprise.address" label="地址" width="300"/>
      <el-table-column prop="enterprise.eva_scale" label="好评率" />
    </el-table>
  </div>
</template>

<script>
export default {
  name: "EnterprisePage",
  data() {
    return {
      enterpriselist:[
        //此数据仅为测试使用
        // {
        //   eid:"1",
        //   number: "1",
        //   ename: "奇思妙想",
        //   address: "重庆",
        //   eva_scale:"100%"
        // },
        // {
        //   eid:"1",
        //   number: "2",
        //   ename: "腾讯",
        //   address: "深圳",
        //   eva_scale:"80%"
        // },
        // {
        //   eid:"1",
        //   number: "3",
        //   ename: "阿里",
        //   address: "深圳",
        //   eva_scale:"80%"
        // },
        // {
        //   eid:"1",
        //   number: "2",
        //   ename: "腾讯",
        //   address: "深圳",
        //   eva_scale:"80%"
        // },
      ],

      //正式数据


    };
  },
  created() {
    this.$http({
      method: "POST",
      url: "/enterprise_paiming_select",
      data: {
      }
    }).then((res)=>{
      this.enterpriselist = res.data;
    }).catch(()=>{
      this.$message.error('企业排名信息获取异常');
    })
  },
  methods:{
    enterpriseDetail() {
      this.$router.push("/main/enterpriseDetail/"+this.enterpriselist.eid);
    },
  }
};
</script>

<style lang="less">
.line {
  color: #d1d4db;
  font-weight: lighter;
}
ul {
  list-style: none;
  display: inline-block;
  margin-left: 70px;
  li {
    margin-bottom: 40px;
    width: 884px;
  }
}
  h1 {
    text-align: center;
    font-weight: lighter;
  }
  .el-table{
    margin: 10px auto;
  }
</style>
