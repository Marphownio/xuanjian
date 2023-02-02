<template>
  <div>
    <mainContainer>
      <h1>黑产域名检测入口</h1>
      <el-steps :active="stepsActive" finish-status="success">
        <el-step title="上传应用文件"></el-step>
        <el-step title="">
          <div slot="title">
            <div @click="handleShowReport">查看检测报告</div>
          </div>
        </el-step>
      </el-steps>
      <div class="upload">
        <h4>
          我们会根据上传的apk文件，检查后台黑产域名。
        </h4>
        <el-upload
          style="width: 100%"
          class="upload-demo"
          drag
          action="https://jsonplaceholder.typicode.com/posts/"
          :limit="1"
          :show-file-list="true"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将apk文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">请上传需要检测的apk文件。</div>
        </el-upload>
      </div>
      <div class="footer">
        <el-button @click="handleImTest">立即检测</el-button>
      </div>
    </mainContainer>
    <el-drawer
      size="50%"
      title="黑产检测报告"
      :visible.sync="drawer"
      direction="rtl"
    >
      <report />
    </el-drawer>
  </div>
</template>

<script>
import report from "./componetns/report";
export default {
  components: { report },
  data() {
    return {
      stepsActive: 1,
      drawer: false,
      isSuccessUpload: true,
    };
  },
  methods: {
    handleAvatarSuccess(e) {
      this.isSuccessUpload = true;
      console.log("上传成功", e);
    },
    handleImTest() {
      if (this.isSuccessUpload) {
        this.stepsActive = 2;
      }
    },
    beforeAvatarUpload(file) {
      console.log("beforeAvatarUpload", file);
      const isApk = file.type === "application/vnd.android.package-archive";
      if (!isApk) {
        this.$message.error("上传APK文件只能是 APK 格式!");
      }
      return isApk;
    },
    handleShowReport() {
      if (this.stepsActive === 2) {
        this.drawer = true;
      }
    },
  },
};
</script>

<style scoped>
.upload {
  width: 80%;
  border: 1px solid black;
  margin: 10px auto;
  padding: 10px;
}
.upload-demo {
  width: 100% !important;
}
:deep().el-upload-dragger {
  width: 600px !important;
  margin: 0 auto;
}
:deep().el-upload {
  margin: 0 auto;
  display: block;
}
</style>
