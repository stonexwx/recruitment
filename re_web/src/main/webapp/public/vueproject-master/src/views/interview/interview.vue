/**
 * 系统管理 评论管理
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>预约请求</el-breadcrumb-item>
    </el-breadcrumb>
    <!--列表-->
    <el-table
      size="small"
      @selection-change="selectChange"
      :data="userData"
      highlight-current-row
      v-loading="loading"
      border
      element-loading-text="拼命加载中"
      style="width: 100%"
    >
      <el-table-column align="center" type="selection" width="50">
      </el-table-column>
      <el-table-column
        align="center"
        sortable
        prop="userName"
        label="用户名"
        width="120"
      >
      </el-table-column>
      <el-table-column
        align="center"
        sortable
        prop="userMobile"
        label="手机号"
        width="120"
      >
      </el-table-column>

      <el-table-column
        align="center"
        sortable
        prop="forWardTime"
        label="预约时间"
        min-width="200"
        :formatter="dateFormat"
      >
      </el-table-column>
      <el-table-column
        align="center"
        sortable
        prop="forWardDept"
        label="被预约公司"
        min-width="120"
      >
      </el-table-column>
      <el-table-column align="center" label="操作" min-width="300">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleNext(scope.$index, scope.row)"
            >查看</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="deleteUser(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <Pagination
      v-bind:child-msg="pageparm"
      @callFather="callFather"
    ></Pagination>
    <!-- 编辑界面 -->
    <el-dialog
      :title="title"
      :visible.sync="editFormVisible"
      width="30%"
      @click="closeDialog('edit')"
    >
      <el-form
        label-width="80px"
        ref="interview"
        :model="interview"
        
      >
       <el-form-item label="用户名" prop="userName">
          <el-input size="small" v-model="interview.userName" auto-complete="off" :disabled="true" ></el-input>
        </el-form-item>
        <el-form-item label="用户电话" prop="userMobile">
          <el-input size="small" v-model="interview.userMobile" auto-complete="off" :disabled="true" ></el-input>
        </el-form-item>
        <el-form-item label="预约时间" prop="forWardTime">

          <el-date-picker
              v-model="interview.forWardTime"
              type="date"
              readonly="true"
              format="yyyy年MM月dd日"
              disabled="true"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约企业" prop="forWardDept">
          <el-input size="small" v-model="interview.forWardDept" auto-complete="off" :disabled="true" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog('edit')">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// 导入请求方法

import Pagination from "../../components/Pagination";
import {interviewDelete, interviewList} from "../../api/userMG";
export default {
  name: "Comment",
  data() {
    return {
      editFormVisible: false, //控制编辑页面显示与隐藏
      nshow: true, //switch开启
      fshow: false, //switch关闭
      loading: false, //是显示加载
      title: "查看预约",
      // 编辑与添加
      interview: {
        userName: "",
        userMobile: "",
        forWardTime: "",
        forWardDept: "",
      },
      // 选择数据
      selectdata: [],
      // 删除用户
      seletedata: {
        ids: "",
        token: localStorage.getItem("logintoken"),
      },
      // 重置密码
      resetpsd: {
        userId: "",
        token: localStorage.getItem("logintoken"),
      },
      // 请求数据参数
      formInline: {
        page: 1,
        limit: 10,

      },
      //用户数据
      userData: [],
      // 选中
      checkmenu: [],
      //参数role
      saveroleId: "",
      // 分页参数
      pageparm: {
        currentPage: 1,
        pageSize: 10,
        total: 10,
      },
    };
  },
  // 注册组件
  components: {
    Pagination,
  },

  /**
   * 数据发生改变
   */
  watch: {},

  /**
   * 创建完毕
   */
  created() {
    this.getdata(this.formInline);
  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    dateFormat:function(date){
      return this.$moment(date).format("YYYY-MM-DD")
    },
    //弹框取消
    closeDialog(dialog) {
      if (dialog == "edit") {
        this.editFormVisible = false;
      } else if (dialog == "perm") {
        this.dataAccessshow = false;
      } else if (dialog == "unit") {
        this.unitAccessshow = false;
      }
    },
    // 获取数据方法
    getdata(parameter) {
      this.loading = true;
      /***
       * 调用接口，注释上面模拟数据 取消下面注释
       */
      // 获取用户列表
        interviewList(parameter).then(res => {
        this.loading = false
        if (res.flag == false) {
          this.$message({
            type: 'info',
            message: "查询错误，请联系代码编写人员"
          })
        } else {
          this.userData = res.interview
          // 分页赋值
          this.pageparm.currentPage = this.formInline.page
          this.pageparm.pageSize = this.formInline.limit
          this.pageparm.total = res.count
        }
      })
    },
    // 分页插件事件
    callFather(parm) {
      this.formInline.page = parm.currentPage;
      this.formInline.limit = parm.pageSize;
      this.getdata(this.formInline);
    },
    //查看详情
    handleNext: function (index, row) {
      this.editFormVisible = true;
      if (row != undefined && row != "undefined") {
        this.interview.userName = row.userName
        this.interview.userMobile = row.userMobile
        this.interview.forWardTime = row.forWardTime
        this.interview.forWardDept = row.forWardDept
        
      }
    },
    // 选择复选框事件
    selectChange(val) {
      this.selectdata = val;
    },
    // 删除用户
    deleteUser(index, row) {
      this.$confirm("确定要删除吗?", "信息", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 删除
          let params = {"iid":this.userData[index].iid};
          //填写接口 数据
          interviewDelete(params)
            .then((res) => {
              if (res.flag) {
                this.$message({
                  type: "success",
                  message: "数据已删除!",
                });
                this.getdata(this.formInline);
              } else {
                this.$message({
                  type: "info",
                  message: res.msg,
                });
              }
            })
            .catch((err) => {
              this.loading = false;
              this.$message.error("数据删除失败，请稍后再试!");
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除!",
          });
        });
    },

    // 选中菜单
    changemenu(arr) {
      let change = [];
      for (let i = 0; i < arr.length; i++) {
        if (arr[i].checked) {
          change.push(arr[i].id);
        }
      }
      return change;
    },
  },
};
</script>

<style scoped>
.user-search {
  margin-top: 20px;
}
.userRole {
  width: 100%;
}
.el-breadcrumb {
  margin-bottom: 20px;
}
</style>

 