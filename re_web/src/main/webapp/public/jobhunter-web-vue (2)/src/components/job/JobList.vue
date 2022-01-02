<template>
  <div class="job_list">
    <ul>
      <li v-for="(item, index) in jobItems" :key="index">
        <JobItem v-bind:item=item />
      </li>
    </ul>
  </div>
</template>

<script>
import JobItem from "@/components/job/JobItem.vue"
export default {
    name: "JobList",
    components: {
        JobItem,
    },
    props:[ "jobItems" ],
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