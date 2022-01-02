<template>
  <div class="job_list">
    <ul>
      <li v-for="(item, index) in EnterpriseItem" :key="index">
        <EnterpriseItem v-bind:item=item />
      </li>
    </ul>
  </div>
</template>

<script>
import EnterpriseItem from "@/components/job/EnterpriseItem.vue"
export default {
    name: "EnterpriseList",
    components: {
        EnterpriseItem,
    },
    props:[ "EnterpriseItem" ],
    data() {
        return {
            
        }
    }
}
</script>

<style lang="less" scoped>
ul {
  list-style: none;
  display: inline-block;
  margin-left: 70px;
  li {
    float: left;
    margin-bottom: 40px;
    margin-left: 25px;
    width: 400px !important;
  }
}
</style>