<template>
  <div class="job_item" v-on:click="EnterpriseDetail">
    <div class="info_primary">
      <div class="message_line1">
        <span class="job_name">{{ item.ename }}</span>
        <span class="job_area">[{{ item.reInfo.full_path }}]</span>
      </div>
    </div>
    <div class="info_append">
      <div class="welfare">公司类型:{{ item.job2Name }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "EnterpriseItem",
  props: [ "item" ],
  data() {
    return {
    };
  },
  methods: {
    EnterpriseDetail() {
      this.$router.push("/main/detail/"+this.item.reInfo.rid);
    },
  },
};
</script>

<style lang="less">
// 定义小号字体
.small-font-size {
  font-size: 12px;
}
.job_item {
  width: 400px;
  height: 110px;
  color: #868b9a;
  background-color: gray;
  // 设置 box-sizing 防止 padding 撑开 div
  box-sizing: border-box;
  &:hover {
    cursor: pointer;
  }
  .info_primary {
    position: relative;
    height: 90px;
    padding-left: 25px;
    padding-top: 25px;
    background-color: white;
    box-sizing: border-box;
    .message_line1 {
      color: #00c2b3;
      .job_area {
        position: relative;
        left: 20px;
        margin-right: 30px;
      }
    }
    .message_line2 {
      padding-top: 5px;
      color: #a6919c;
      .job_salary {
        color: #fc807e;
      }
    }
    .company-logo {
      position: absolute;
      top: 20px;
      left: 750px;
      width: 50px;
      height: 50px;
      border-radius: 20%;
    }
  }
  .info_append {
    height: 40px;
    padding-top: 10px;
    padding-left: 20px;
    background-color: #f8f9fc;
    .small-font-size();
    box-sizing: border-box;
  }
}
</style>
