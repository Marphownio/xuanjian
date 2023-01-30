<template>
  <div class="report">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="信息总览" name="first">
        <div>
          <h4>应用信息</h4>
          <div class="appContent">
            <div class="icon">
              <img
                  :src="appInfo.appIcon"
                  width="100px"
                  height="100px"
                  class="icon-img"
              />
            </div>
            <div class="appInfo">
              <div class="flex row">
                <h4>黑产：</h4>
                <div>{{ appInfo.apkName }}</div>
              </div>
              <div class="flex row">
                <h4>APK MD5：</h4>
                <div>{{ appInfo.apkMd5 }}</div>
              </div>
              <div class="flex row">
                <h4>上传时间：</h4>
                <div>{{ appInfo.updateTime }}</div>
              </div>
              <div class="flex row">
                <h4>分析时间：</h4>
                <div>{{ appInfo.testingTime }} min</div>
              </div>
              <div class="flex row">
                <h4>签名发布者：</h4>
                <div>{{ appInfo.author }}</div>
              </div>
              <div class="flex row">
                <h4>签名时间：</h4>
                <div>{{ appInfo.authorTime }}</div>
              </div>
              <div class="flex row">
                <h4>版本号：</h4>
                <div>{{ appInfo.version }}</div>
              </div>
            </div>
          </div>
        </div>
        <div>
          <h4>信息概览</h4>
          <div class="appContent">
            <div id="echarts1" ref="echarts1"></div>
            <div id="echarts2" ref="echarts2"></div>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import * as echarts from "echarts";
export default {
  data() {
    return {
      appInfo: {},
      activeName:'first'
    };
  },
  mounted() {
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
      this.innitEcharts1();
      this.innitEcharts2();
    },
    innitEcharts1() {
      const chartDom = this.$refs.echarts1;
      const myChart = echarts.init(chartDom);
      let option;

      option = {
        tooltip: {
          trigger: "item",
        },
        series: [
          {
            name: "Access From",
            type: "pie",
            radius: "50%",
            data: [
              { value: 1048, name: "正常域名" },
              { value: 735, name: "黑产域名" },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };

      option && myChart.setOption(option);
    },
    innitEcharts2() {

      const chartDom = this.$refs.echarts2;
      const myChart = echarts.init(chartDom);
      let option;

      option = {
        tooltip: {
          trigger: "item",
        },
        series: [
          {
            name: "Access From",
            type: "pie",
            radius: "50%",
            data: [
              { value: 1048, name: "分发域名" },
              { value: 735, name: "资源域名" },
              { value: 580, name: "直播域名" },
              { value: 484, name: "备选域名" },
              { value: 300, name: "图片分隔域名" },
              { value: 300, name: "页面域名" },
              { value: 300, name: "更新域名" },
              { value: 300, name: "用户域名" },
              { value: 300, name: "业务域名" },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };

      option && myChart.setOption(option);
    },
  },
};
</script>

<style scoped>
.flex {
  display: flex;
}
.report {
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
.appInfo {
  flex: 1;
  background-color: #88888850;
  padding: 10px;
  border-radius: 10px;
}
.row {
  align-items: center;
  width: 100%;
  height: 2rem;
}
#echarts1,#echarts2{
  width: 400px;
  height: 400px;
}
</style>
