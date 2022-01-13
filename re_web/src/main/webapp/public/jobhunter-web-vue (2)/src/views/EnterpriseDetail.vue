<template>
  <div class="enterprise-detail">
    <!-- 企业详情页横幅 -->
    <div class="enterprise-banner">
      <div v-if="imgFlag" class="enterprise-photo">
        <!-- <img :src="EnterPrise.photo" /> -->
      </div>
      <div class="banner-info">
        <div class="title">
          <span class="enterprise-name">{{ EnterPrise.ename }}&nbsp;</span>
          <span class="enterprise-fullPath">{{ EnterPrise.address }}</span>
        </div>
        <div class="enterprise-description">
          <span>公司规模:{{ EnterPrise.scale }}</span>
          &nbsp;▪&nbsp;
          <span>公司邮箱:{{ EnterPrise.e_email }}</span>
          &nbsp;▪&nbsp;
          <span>公司电话:{{ EnterPrise.ephone }}</span>
        </div>
      </div>
    </div>
    <div class="enterprise-content">
      <!-- enterprise具体介绍 -->
      <div class="enterprise-info">
        <!--招聘者简要信息-->
        <div class="enterprise-info-detail">
          <!--公司简介-->
          <br/>
          <my-h1 :content="title2"/>
          <div class="the-content">
            {{ EnterPrise.e_introduce }}
          </div>
        </div>
        <video
            v-if="videoFlag"
            :src="EnterPrise.media"
            class="enterprise-video"
            controls="controls"
        >
          您的浏览器不支持 video 标签。
        </video>
        <span v-if="flag" class="video-tip">
          啊哦~该公司还没有上传介绍视频哦~</span
        >
      </div>

      <img alt="" class="advertisement" src="../assets/ad-demo.png"/>
    </div>
    <div class="job-recommend-box">
      <h1>
        <span class="line">——&nbsp;</span>评价该公司<span class="line"
      >&nbsp;——</span
      >
      </h1>
      <div class="score-left">
        <div class="score">
          <span>请选择您的评分</span>
          <el-rate v-model="score"></el-rate>
        </div>
        <div class="commend-input">
          <el-input
              v-model="textarea"
              :rows="2"
              placeholder="请输入您想评论的信息~"
              type="textarea"
          />
          <el-button type="primary" @click="sendComment()">发表评论</el-button>
        </div>
      </div>
      <div class="score-right">
        <div class="big-message">
          <p v-if="!commentFlag">啊哦，还没有评论哦~</p>
          <el-tabs v-if="commentFlag" type="border-card">
            <el-tab-pane label="好评">
              <div v-for="(item, index) in Message" :key="index">
                <div class="commend-username">
                  <span>{{ item.userName }}:</span>
                  <p>
                    <el-rate
                        v-model="item.evaluation.score"
                        disabled
                        score-template="{value}"
                        show-score
                        style="float:right; margin-right: 20px"
                        text-color="#ff9900">
                    </el-rate>
                  </p>
                </div>
                <div class="commend-message">
                  <p>{{ item.evaluation.content }}</p>
                </div>
                <el-divider></el-divider>
              </div>


            </el-tab-pane>
            <el-tab-pane label="差评">
              <div v-for="(item, index) in badMessage" :key="index">
                <div class="commend-username">
                  <span>{{ item.userName }}:</span>
                  <p>
                    <el-rate
                        v-model="item.evaluation.score"
                        disabled
                        score-template="{value}"
                        show-score
                        style="float:right; margin-right: 20px"
                        text-color="#ff9900">
                    </el-rate>
                  </p>
                </div>
                <div class="commend-message">
                  <p>{{ item.evaluation.content }}</p>
                </div>
              </div>
              <el-divider></el-divider>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import MyH1 from "@/components/common/MyH1";

export default {
  name: "EnterPriseDetail",
  created() {
    let rid = this.$route.params.rid;
    return this.$http.get("/enterprise_select", {
      params:
          {
            rid: rid,
          }
    }).then((res) => {
      this.EnterPrise = res.data;
      this.$route.params.rid=res.data.eid;
      if (this.EnterPrise.photo == "") {
        this.imgFlag == false;
      } else {
        this.imgFlag == true;
      }
      if (this.EnterPrise.media == "") {
        this.flag = true;
        this.videoFlag = false;
      } else {
        this.flag = !this.flag;
        this.videoFlag = true;
      }
    }).catch(() => {
      this.$message.error('企业信息获取异常');
    })
  },
  mounted() {
    let rid = this.$route.params.rid;
    return this.$http.get("/evaluation_select", {
      params: {
        eid: rid
      }
    }).then((res) => {
      if (res.data == null) {
        this.commentFlag == false;
      }
      let j;
      let a = [];
      let b = [];
      for (j = 0; j < res.data.length; j++) {
        if (res.data[j].evaluation.score <= 3) {
          a.push(res.data[j])
        } else {
          b.push(res.data[j])
        }
      }
      this.badMessage = a;
      this.Message = b;

    }).catch(() => {
      this.$message.error('评论信息获取异常');
    })

  },
  methods: {
    //点击发表评论后执行的操作
    sendComment() {
      if (this.score == "") {
        alert("您还没有打分哦~")
        return;
      }
      ;
      if (this.textarea == "") {
        this.textarea == "该用户没有发表评论~"
      }
      this.$http.get("/evaluation_insert", {
        params: {
          ename: this.EnterPrise.ename,
          textarea: this.textarea,
          score: this.score
        }
      }).then((res) => {
        if (res.data.flag) {
          this.$message({
            message: '您的评论正发送至后台审核哦~',
            type: 'success'
          });
        }
      }).catch(() => {
        this.$message.error('发生错误啦');
      })
    }
  },
  props: ["rid"],
  data() {

    return {
      textarea: "",
      score: null,
      videoFlag: false,
      imgFlag: false,
      flag: true,
      isSend: 0,
      title2: "公司简介",
      EnterPrise: {},
      commentFlag: true,
      //好评数据
      Message: [],
      //差评数据
      badMessage: []
    };
  },
  components: {
    "my-h1": MyH1,

  },
};
</script>

<style lang="less" scoped>
video {
  width: 400px;
  position: absolute;
  top: 40px;
  left: 811px;
}

.enterprise-detail {
  .enterprise-banner {
    width: 100%;
    height: 180px;
    background-color: #134857;
    // background-image: url();
    .enterprise-photo {
      position: absolute;
      top: 50px;
      left: 975px;
      width: 560px;
      height: 180px;
      background-color: white;
    }

    .enterprise-photo img {
      width: 560px;
      height: 180px;
    }

    .banner-info {
      width: 800px;
      position: relative;
      top: 30px;
      left: 175px;

      .title {
        font-size: 30px;
        font-weight: bolder;

        .enterprise-name {
          color: white;
        }

        .enterprise-fullPath {
          color: white;
          font-size: 20px;
        }
      }

      .enterprise-description {
        margin: 15px 0;
        color: #c7c9be;
      }

      .welfare-item {
        padding: 3px;
        margin-right: 10px;
        color: #5dd5c8;
        border: 1px solid #5dd5c8;
        border-radius: 5px;
      }

      .send-btn {
        position: absolute;
        top: 10px;
        left: 810px;
        width: 40px;
        padding: 20px;
        color: white;
        border: 2px solid white;
        font-weight: bold;
        border-radius: 5px;

        &:hover {
          cursor: pointer;
          color: #134857;
          border-color: #134857;
          background-color: white;
        }
      }
    }
  }

  .enterprise-content {
    position: relative;
    top: 5px;

    .enterprise-info {
      width: 800px;
      position: relative;
      left: 175px;
      //background-color: #223e36;
      .enterprise-info-detail {
        overflow: auto;
        height: 393.5px;
        margin-top: 5px;
        margin-bottom: 5px;
        background-color: white;

        .the-content {
          line-height: 30px;
          width: 750px;
          margin: 10px;
          color: #134857;
        }
      }
    }

    .company-info {
      width: 160px;
      height: 220px;
      position: absolute;
      top: 1px;
      left: 985px;
      background-color: white;
      padding: 20px;

      #title1 {
        margin-bottom: 30px;
      }

      #company-avatar {
        width: 60px;
        border-radius: 10px;
      }

      #company-name {
        position: relative;

        top: -20px;
        font-weight: bold;
      }

      ul {
        width: 150px;
        padding: 0;
        margin: 0;
        list-style: none;

        li {
          margin: 0 0 20px;
          width: 150px;

          .company-items {
            margin-left: 20px;
            font-size: 15px;
          }
        }
      }
    }

    .advertisement {
      width: 200px;
      position: absolute;
      top: 280px;
      left: 985px;
      background-color: red;
    }
  }
}

.video-tip {
  width: 300px;
  position: absolute;
  top: 120px;
  left: 812px;
  color: grey;
}

.score {
  width: 200px;
  margin: 10px auto;

  .el-rate {
    margin: 40px 35px 20px;
  }

  span {
    color: #134857;
    font-weight: bold;
    text-align: center;
    margin-left: 35px;
  }
}

.score-left {
  float: left;
  width: 400px;
  height: 500px;
  background-color: white;
  margin: 10px 30px 10px 175px;
  padding: 10px;

  .el-button {
    margin-top: 30px;
    margin-left: 140px;
  }
}

.score-right {
  float: left;
  height: 520px;
  width: 550px;
  margin: 10px;
  background-color: white;
  overflow: auto;
}

::-webkit-scrollbar {
  display: none;
}
</style>
<style lang="less" scoped>

.commend-username span {
  margin-left: 10px;
  font-weight: bold;
  margin-bottom: 10px;
  color: rgb(87, 80, 80);
}

.el-tabs {
  height: 515px;
  overflow: auto;

}

.commend-message p {
  margin-left: 10px;
  margin-top: 10px;
  color: cadetblue
}

p {
  word-wrap: break-word;
  word-break: break-all;
}
</style>