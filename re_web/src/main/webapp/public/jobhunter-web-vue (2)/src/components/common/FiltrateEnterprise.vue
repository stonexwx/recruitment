<template>
  <div class="filtrate-area">
    <div class="content">
      <!-- 输入框区域 -->
      <div class="input-box">
        <el-input
          placeholder="搜索职位或企业"
          v-model="searchConditions.jobName"
          class="input-with-select"
        >
          <el-select
            v-model="searchConditions.se_type"
            slot="prepend"
            placeholder="选择搜索类别"
          >
            <el-option label="企业" value="1"></el-option>
            <el-option label="职位" value="0"></el-option>
          </el-select>
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="search()"
          ></el-button>
        </el-input>
      </div>
      <!-- 城市信息选择区域 -->
      <div class="city-select-box">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item>{{ selectedCity }}</el-breadcrumb-item>
          <el-breadcrumb-item>
            热门城市:
            <ul>
              <li
                v-for="(item, index) in hotCity"
                :key="index"
                v-on:click="changeSelectedHotCity(item)"
              >
                {{ item }}
              </li>
            </ul>
          </el-breadcrumb-item>
        </el-breadcrumb>
      </div>

    </div>
    <el-table
        :data="tableData"
        stripe
        style="width: 100%">
      <el-table-column
          :prop="name"
          :label="label_two"
          width="180">
      </el-table-column>
      <el-table-column
          :prop="address"
          label="地址">
      </el-table-column>
      <el-table-column
          :prop="other"
          :label="label_three">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "FiltrateEnterprise",
  data() {
    return {
      prop:["searchConditions.se_type"],
      tableData:[

      ],
      inputContext: "",
      select: "",
      label_two:"职位/企业",
      label_three:"邮箱/薪水",
      selectedCity: "全国",
      hotCity: [
        "全国",
        "北京",
        "上海",
        "广州",
        "深圳",
        "杭州",
        "天津",
        "西安",
        "苏州",
        "武汉",
        "厦门",
        "长沙",
        "成都",
        "郑州",
        "佛山",
      ],
      searchConditions: {
        jobName: "",
        se_type: "1",
        city: "",
      },
    };
  },
  methods: {
    changeSelectedHotCity: function (selectedCity) {
      if (selectedCity !== "全国") {
        this.selectedCity = selectedCity;
        this.searchConditions.city = selectedCity;
      } else {
        this.searchConditions.city = "*";
      }
      this.updateJobList();
    },

    search() {
      //企业查询
      if (this.searchConditions.se_type === "1") {
        this.$http
          .get("/query", {params:{
            se_type: "1",
            city: this.searchConditions.city,
            message: this.searchConditions.jobName}
          })
          .then((res) => {
            this.searchConditions.jobName = "";
            this.label_two = "企业";
            this.label_three = "企业邮箱";
            this.tableData = res.data;
          })
          .catch(() => {
            this.$message.error("关键字查询失败");
          });
      }
      //查询类别为职业
      else if (this.searchConditions.se_type === "0") {
        this.$http
          .get("/query", {params:{
            se_type: "0",
            city: this.searchConditions.city,
            message: this.searchConditions.jobName}
          })
          .then((res) => {
            this.searchConditions.jobName = "";
            this.label_two = "职位";
            this.label_three = "薪水";
            this.tableData = res.data.reInfo;
          })
          .catch(() => {
            this.$message.error("关键字查询失败");
          });
      }
    },
    // 请求更新工作列表
    updateJobList() {
      this.$http
        .post("", {
          jobName: this.searchConditions.jobName,
          se_type: this.searchConditions.se_type,
          city: this.searchConditions.city,
          jobExperence: this.searchConditions.jobExperence,
          jobEB: this.searchConditions.jobEB,
          jobSalary: this.searchConditions.jobSalary,
          companyScale: this.searchConditions.companyScale,
        })
        .then((res) => {
          console.log("条件检索获得");
          console.log(res.data);
          this.$emit("change-job-list", res.data);
        })
        .catch(() => {
          this.$message.error("工作列表更新失败");
        });
    },
  },
};
</script>

<style lang="less">
.filtrate-area {
  height: 100px;
  background-color: white;
  box-shadow: 0 2px rgba(0, 0, 0, 0.12);
  .content {
    position: relative;
    top: 5px;
    left: 110px;
    height: 100%;
    width: 1200px;
    //background-color: gray;
    .el-divider {
      width: 780px;
      //border-color: #eef0f5;
      border: red;
    }
    .input-box {
      width: 800px;
      margin: 0;
      padding: 0;
      border: 1px solid #55cbc4;
      .el-select .el-input {
        margin: 0;
        padding: 0;
        width: 130px;
        border-radius: 0;
      }
      .input-with-select .el-input-group__prepend {
        background-color: #fff;
      }
      .el-input {
        .el-option {
          width: 100px;
          margin: 0;
        }
      }
      .el-button {
        position: relative;
        border-radius: 0%;
        right: 0px;
        bottom: 0px;
        width: 100px;
        background-color: #55cbc4;
      }
    }
  }
}
.job-fileter-box {
  position: relative;
  top: 20px;
  left: -5px;
  padding: 0;
}
.city-select-box {
  position: relative;
  padding-bottom: 5px;
  width: 1000px;
  top: 20px;
  ul {
    margin: 0;
    list-style: none;
    li {
      display: inline; // 使 li 横向分布
      padding-right: 10px;
      &:hover {
        color: red;
        cursor: pointer;
      }
    }
  }
}
.cell{
  text-align: center;
  width: 30%;
}
</style>