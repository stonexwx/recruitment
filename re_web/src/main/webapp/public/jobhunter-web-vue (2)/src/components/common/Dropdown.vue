<template>
  <div class="dropdown">
    <button class="dropbtn el-icon-arrow-down"> {{ data.name }}</button>
    <div class="dropdown-content">
      <a v-for="item in data.items" :key="item" href="#" v-on:click="click(item)">{{ item }}</a>
    </div>
  </div>
</template>

<script>
export default {
  name: "Dropdown",
  props: [
      "data",
  ],
  methods: {
    click(item){
      this.data.name = item;
      this.$emit("selected-item", item);
    }
  }
}
</script>

<style lang="less">
/* 下拉按钮样式 */
.dropbtn {
    background-color: white;
    color: black;
    padding: 10px;
    margin-right: 15px;
    font-size: 13px;
    border: none;
    cursor: pointer;
}

/* 容器 <div> - 需要定位下拉内容 */
.dropdown {
    position: relative;
    display: inline-block;
}

/* 下拉内容 (默认隐藏) */
.dropdown-content {
    display: none;
    position: absolute;
    font-size: 13px;
    z-index: 999;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

/* 下拉菜单的链接 */
.dropdown-content a {
    color: black;
    padding: 8px;
    margin: 0;
    text-decoration: none;
    display: block;
}

/* 鼠标移上去后修改下拉菜单链接颜色 */
.dropdown-content a:hover {background-color: #f1f1f1}

/* 在鼠标移上去后显示下拉菜单 */
.dropdown:hover .dropdown-content {
    display: block;
}
</style>