<template>
  <div class="footer">
    <div class="content">Copyright ©2021 Jobhunter - 【Author:LiuHongYou】</div>
  </div>
</template>

<script>
export default {
  name: "Footer",
};
</script>

<style lang="less">
.footer {
  position: fixed;
  bottom: 0;
  height: 20px;
  width: 100%;
  padding-top: 5px;
  padding-bottom: 5px;
  margin-top: 5px;
  background-color: darkslategrey;
  text-align: center;
  .content {
    font-size: 10px;
    color: white;
  }
}
</style>
