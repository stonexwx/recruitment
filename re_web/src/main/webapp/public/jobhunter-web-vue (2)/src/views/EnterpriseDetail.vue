<template>
  <div class="enterprise-detail">
    <!-- 企业详情页横幅 -->
    <div class="enterprise-banner">
      <div class="enterprise-photo" v-if="imgFlag">
        <!-- <img :src="EnterPrise.photo" /> -->
      </div>
      <div class="banner-info">
        <div class="title">
          <span class="enterprise-name">{{ EnterPrise.name }}&nbsp;</span>
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
          <br />
          <my-h1 :content="title2" />
          <div class="the-content">
            {{ EnterPrise.introduce }}
          </div>
        </div>
        <video
          :src="EnterPrise.media"
          controls="controls"
          class="enterprise-video"
          v-if="videoFlag"
        >
          您的浏览器不支持 video 标签。
        </video>
        <span class="video-tip" v-if="flag">
          啊哦~该公司还没有上传介绍视频哦~</span
        >
      </div>

      <img class="advertisement" src="../assets/ad-demo.png" alt="" />
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
            type="textarea"
            placeholder="请输入您想评论的信息~"
          />
          <el-button type="primary" @click="sendComment()">发表评论</el-button>
        </div>
      </div>
      <div class="score-right">
        <Commend />
      </div>
    </div>
  </div>
</template>

<script>
import Commend from "@/components/common/commend.vue";
import MyH1 from "@/components/common/MyH1";
export default {
  name: "EnterPriseDetail",
  created() {
    const token2 = sessionStorage.getItem("token2");
    this.$http.get("/enterprise_select",{params:
          {
            rid:token2.reInfo.rid
          }
    }).then((res)=>{
      this.EnterPrise = res.data;
      if (this.EnterPrise.photo == "") {
        this.imgFlag == false;
      } else {
        this.imgFlag == true;
      }
      if (this.EnterPrise.media == "") {
        this.flag = true;
        this.videoFlag =false;
      } else {
        this.flag = !this.flag;
        this.videoFlag =true;
      }
    }).catch(()=>{
      this.$message.error('企业信息获取异常');
    })
  },
  methods: {
    //正式数据传输


    //点击发表评论后执行的操作
    sendComment(){
      const token2 = sessionStorage.getItem("token2");
      if(this.score == ""){
          console.log("您还没有打分哦~")
      };
      if(this.textarea == ""){
        this.textarea == "该用户没有发表评论~"
      }
      this.$http.get("/evaluation _insert",{params:{

          eid:token2.reInfo.eid,
          textarea:this.textarea,
          score:this.score
        }
      }).then((res)=>{
        if(res.flag){
          this.$message({
          message: '您的评论正发送至后台审核哦~',
          type: 'success'
        });
        }
      }).catch(()=>{
        this.$message.error('');
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
      EnterPrise: {
        // name: "奇思妙想",
        // scale: "1000人",
        // media: "",
        // introduce:
        //   "我们公司福利贼好,在这里，你可以学到如何反抗资本主义薛文潇，成为一个合格的反卷达人！" +
        //   "我们公司福利贼好,在这里，你可以学到如何反抗资本主义薛文潇，成为一个合格的反卷达人！" +
        //   "我们公司福利贼好,在这里，你可以学到如何反抗资本主义薛文潇，成为一个合格的反卷达人！" +
        //   "我们公司福利贼好,在这里，你可以学到如何反抗资本主义薛文潇，成为一个合格的反卷达人！" +
        //   "我们公司福利贼好,在这里，你可以学到如何反抗资本主义薛文潇，成为一个合格的反卷达人！" +
        //   "我们公司福利贼好,在这里，你可以学到如何反抗资本主义薛文潇，成为一个合格的反卷达人！" +
        //   "我们公司福利贼好,在这里，你可以学到如何反抗资本主义薛文潇，成为一个合格的反卷达人！" +
        //   "我们公司福利贼好,在这里，你可以学到如何反抗资本主义薛文潇，成为一个合格的反卷达人！",
        // address: "重庆",
        // photo: "",
        // e_email: "1234567890@qq.com",
        // ephone: "1234567890123",
      },
    };
  },
  components: {
    "my-h1": MyH1,
    Commend,
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
