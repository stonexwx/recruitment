<template>
  <div class="main_page">
    <Header />
    <div class="main">
      <router-view />
    </div>
    <Footer />
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/common/Header.vue";
import Footer from "@/components/common/Footer.vue";
export default {
  name: "MainPage",
  components: {
    Header,
    Footer,
  },
};
</script>

<style lang="less">
.main_page {
  background-color: #f6f6f8;
}
.main {
  margin: 0;
  width: 100%;
  height: 600px;
  overflow:auto;
  padding: 0 0 25px;
  background-color: #f6f6f8;
}
</style>
