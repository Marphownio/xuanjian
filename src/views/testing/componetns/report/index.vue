<template>
  <div class="report">
    <div>
      <h4>应用信息</h4>
      <div class="appContent">
        <div class="icon">
          <img :src="appInfo.appIcon" width="100px" height="100px" class="icon-img" />
        </div>
        <div class="appInfo">
          <div class="flex row">
            <h4>黑产：</h4>
            <div> {{ appInfo.apkName }}</div>
          </div>
          <div class="flex row">
            <h4>APK MD5：</h4>
            <div> {{ appInfo.apkMd5 }}</div>
          </div>
          <div class="flex row">
            <h4>上传时间：</h4>
            <div> {{ appInfo.updateTime }}</div>
          </div>
          <div class="flex row">
            <h4>分析时间：</h4>
            <div> {{ appInfo.testingTime }} min</div>
          </div>
          <div class="flex row">
            <h4>签名发布者：</h4>
            <div> {{ appInfo.author }}</div>
          </div>
          <div class="flex row">
            <h4>签名时间：</h4>
            <div> {{ appInfo.authorTime }}</div>
          </div>
          <div class="flex row">
            <h4>版本号：</h4>
            <div> {{ appInfo.version }}</div>
          </div>
        </div>
      </div>
    </div>
    <div>
      <h4>信息概览</h4>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      appInfo: {},
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      this.appInfo = {
        appIcon:
          "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
        apkName: "凯发娱乐",
        apkMd5: "a60wad89wndwa0dawdwah0d8wahd8aw",
        updateTime: "2023-01-27 1:47",
        testingTime: "10",
        author: "5ad6d6d6d6d",
        authorTime: "1981-01-02 01:01:02",
        version: "v0.1.1",
      };
    },
  },
};
</script>

<style scoped>
.flex{
  display: flex;
}
.report{
  padding: 10px;
}
.appContent {
  display: flex;
  align-items: center;
}
.icon {
  margin: 0 10px;
}
.icon-img {
  border-radius: 20px;
}
.appInfo{
  flex: 1;
  background-color: #88888850;
  padding: 10px;
  border-radius: 10px;
}
.row{
  align-items: center;
  width: 100%;
  height: 2rem;
}
</style>
