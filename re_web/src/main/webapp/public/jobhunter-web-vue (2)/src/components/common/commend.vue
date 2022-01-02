<template>
  <div class="big-message">
    <ul>
      <li v-for="(item, index) in Message" :key="index">
        <div class="commend-username">
          <span>{{ item.username }}:</span>
        </div>
        <div class="commend-message">
          <p>{{ item.evaluation.content }}</p>
        </div>
        <el-divider></el-divider>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Commend",
  data() {
    return {
      Message: [
        {
          username: "学问熊",
          evaluation:{
            content:"hhhhhhhhhhhh"
          }
        },
        {
          username: "学问熊",
          evaluation:{
            content:"hhhhhhhhhhhh"
          }
        },
      ],
    };
  },
  methods:{
    created() {
      const token2 = sessionStorage.getItem("token");
      this.$http({
        method: "POST",
        url: "/evaluation_select",
        data: {
          eid:token2.reInfo.eid
        }
      }).then((res)=>{
        this.Message = res.data;
      }).catch(()=>{
        this.$message.error('评论信息获取异常');
      })
    },
  }
};
</script>

<style lang="less" scoped>
li {
  width: 550px;
  margin-top: 10px;
  margin-bottom: 10px;
}
ul {
  margin: 0px;
  padding: 0px;
}
.commend-username span {
  margin-left: 10px;
  font-weight: bold;
  margin-bottom: 10px;
  color: rgb(87, 80, 80);
}
.commend-message p {
  margin-left: 10px;
  margin-top: 10px;
  color: cadetblue
}
</style>