<template>
  <div class="my-h1">
    <span class="color-block">-</span>
    <span class="h1-text">{{ content }}</span>
  </div>
</template>

<script>
export default {
  name: "MyH1",
  props: [
      "content"
  ]
}
</script>

<style scoped lang="less">
.my-h1 {
  margin-left: 5px;
  font-size: 25px;
  .color-block {
    position: relative;
    left: 1px;
    width: 5px;
    height: 100%;
    background-color: #134857;
    margin-right: 10px;
  }
}
</style>