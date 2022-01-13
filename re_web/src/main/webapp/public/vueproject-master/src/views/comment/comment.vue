/**
 * 系统管理 评论管理
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>评论审核</el-breadcrumb-item>
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
        prop="commentMessage"
        label="评论信息"
        min-width="200"
      >
      </el-table-column>
      <el-table-column
        align="center"
        sortable
        prop="commentDept"
        label="被评论公司"
        min-width="120"
      >
      </el-table-column>
      <el-table-column align="center" label="操作" min-width="300">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleNext(scope.$index, scope.row)"
            >通过</el-button
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
  </div>
</template>

<script>
// 导入请求方法
import { userSave, userDelete, userPwd } from "../../api/userMG";
import Pagination from "../../components/Pagination";
export default {
  name: "Comment",
  data() {
    return {
      nshow: true, //switch开启
      fshow: false, //switch关闭
      loading: false, //是显示加载
      title: "添加用户",
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
        deptId: "",
        userName: "",
        userMobile: "",
        isLock: "",
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
    // 获取数据方法
    getdata(parameter) {
      this.loading = true;
      // 模拟数据开始
      let res = {
        code: 0,
        msg: null,
        count: 3,
        data: [
          {
            userId: 1,
            commentMessage:
              "这个公司是薛文潇在管 大家千万不要去!这简直就是资本主义的世界!太恐怖了!",
            commentDept: "奇思妙想重庆有限公司",
            userName: "ahaua",
            userMobile: "138123456789",
          },
          {
            userId: 2,
            commentMessage: "我同意楼上",
            userName: "谢传昕",
            commentDept: "奇思妙想重庆有限公司",
            userMobile: "138123456789",
          },
          {
            userId: 1,
            commentMessage: "我同意楼上",
            commentDept: "奇思妙想重庆有限公司",
            userName: "易世嫘",
            userMobile: "138123456789",
          },
        ],
      };
      this.loading = false;
      this.userData = res.data;
      // 分页赋值
      this.pageparm.currentPage = this.formInline.page;
      this.pageparm.pageSize = this.formInline.limit;
      this.pageparm.total = res.count;
      // 模拟数据结束

      /***
       * 调用接口，注释上面模拟数据 取消下面注释
       */
      // 获取用户列表
      // userList(parameter).then(res => {
      //   this.loading = false
      //   if (res.success == false) {
      //     this.$message({
      //       type: 'info',
      //       message: res.msg
      //     })
      //   } else {
      //     this.userData = res.data
      //     // 分页赋值
      //     this.pageparm.currentPage = this.formInline.page
      //     this.pageparm.pageSize = this.formInline.limit
      //     this.pageparm.total = res.count
      //   }
      // })
    },
    // 分页插件事件
    callFather(parm) {
      this.formInline.page = parm.currentPage;
      this.formInline.limit = parm.pageSize;
      this.getdata(this.formInline);
    },
    //通过评论
    handleNext: function (index, row) {
      if (row != undefined && row != "undefined") {
        //写请求
        this.$message.error(
          "这条评论我觉得不能通过!虽然薛文潇是资本主义!但是周总很不错啊!"
        );
      } else {
        this.$message.error(
          "这条评论我觉得不能通过!虽然薛文潇是资本主义!但是周总很不错啊!"
        );
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
          //填写接口 数据
          userDelete(row.id)
            .then((res) => {
              if (res.success) {
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
.el-breadcrumb{
  margin-bottom: 20px;
}
</style>

 