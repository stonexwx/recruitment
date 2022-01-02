<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  /* 这两个属性可以使页面上的字体抗锯齿,使用后字体看起来会更清晰 */
  -webkit-font-smoothing: antialiased; /*chrome、safari*/
  -moz-osx-font-smoothing: grayscale; /*firefox*/
  /* 其它 css 配置 */
  //text-align: center;
  height: 100%;
  // color: #5dd5c8;
  // background-color: red;
}
</style>
