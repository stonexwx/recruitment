<template>
  <div class="job_item" v-on:click="toDetail">
    <div class="info_primary">
      <div class="message_line1">
        <span class="job_name">{{ item.reInfo.job_name }}</span>
        <span class="job_area">[{{ item.reInfo.full_path }}]</span>
        <span class="job_pub_time">发布于{{ item.reInfo.addtime }}</span>
      </div>
      <div class="message_line2">
        <span class="job_salary">
          {{ item.reInfo.scale }}/月
        </span>
        <span class="small-font-size">
          <span>{{ item.reInfo.job_time }} | {{ item.reInfo.re_education }}</span>
        </span>
        <span class="small-font-size right-location">
           <span>{{ item.ename }}</span><br>
          <span>类型:{{ item.job2Name }}</span>
        </span>
      </div>
      <!-- <img class="company-logo" :src=companyAvatar alt /> -->
    </div>
    <div class="info_append">
      <div class="welfare">{{ item.reInfo.job_welfare }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "JobItem",
  props: [ "item" ],
  computed: {
    companyAvatar(){
      return require("../../assets/avatar/company/" + this.item.company.avatar)
    },
  },
  data() {
    return {
    };
  },
  methods: {
    toDetail() {

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

// 设置工作项的偏右侧的信息位置
.right-location {
  position: absolute;
  left: 177px;
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
