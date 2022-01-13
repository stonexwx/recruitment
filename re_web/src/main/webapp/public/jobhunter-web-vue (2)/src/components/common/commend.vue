<template>
  <div class="big-message">
    <p v-if="!commentFlag">啊哦，还没有评论哦~</p>
    <el-tabs type="border-card" v-if="commentFlag">
      <el-tab-pane label="好评">
        <div v-for="(item, index) in Message" :key="index" >
        <div class="commend-username" >
          <span>{{ item.userName }}:</span>
          <p>
            <el-rate
                v-model="item.evaluation.score"
                disabled
                score-template="{value}"
                show-score
                style="float:right; margin-right: 20px"
                text-color="#ff9900">
            </el-rate>
          </p>
        </div>
        <div class="commend-message">
          <p>{{ item.evaluation.content }}</p>
        </div>
          <el-divider></el-divider>
        </div>


      </el-tab-pane>
      <el-tab-pane label="差评" >
        <div v-for="(item, index) in badMessage" :key="index">
        <div class="commend-username">
          <span>{{ item.userName }}:</span>
          <p>
            <el-rate
                v-model="item.evaluation.score"
                disabled
                score-template="{value}"
                show-score
                style="float:right; margin-right: 20px"
                text-color="#ff9900">
            </el-rate>
          </p>
        </div>
        <div class="commend-message">
          <p>{{ item.evaluation.content }}</p>
        </div>
        </div>
        <el-divider></el-divider>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "Commend",
  data() {
    return {
      commentFlag:true,
      //好评数据
      Message: [
        ],
      //差评数据
      badMessage:[
      ]
    };
  },
  // created() {
  //   let token2 = null;
  //   token2 = sessionStorage.getItem("enter");
  //   if(sessionStorage.getItem("enter")==null){
  //     window.location.reload();
  //     window.location.reload();
  //   }
  //   //好评
  //   this.$http.get("/evaluation_select", {params:{
  //       eid: token2
  //     }
  //   }).then((res) => {
  //     if(res.data == null){
  //       this.commentFlag == false;
  //     }
  //     let j;
  //     let a=[];
  //     let b=[];
  //     for(j=0;j<res.data.length;j++){
  //       if(res.data[j].evaluation.score<=3){
  //         a.push(res.data[j])
  //       }else {
  //         b.push(res.data[j])
  //       }
  //     }
  //     this.badMessage =a;
  //     this.Message =b;
  //
  //   }).catch(() => {
  //     this.$message.error('评论信息获取异常');
  //   })
  // },

  methods: {

  }
};
</script>

<style lang="less" scoped>

.commend-username span {
  margin-left: 10px;
  font-weight: bold;
  margin-bottom: 10px;
  color: rgb(87, 80, 80);
}
.el-tabs{
  height:515px;
  overflow: auto;

}
.commend-message p {
  margin-left: 10px;
  margin-top: 10px;
  color: cadetblue
}
p{
  word-wrap: break-word;
  word-break: break-all;
}
</style>