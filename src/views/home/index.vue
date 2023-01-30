<template>
  <div>
    <mainContainer>
      <vue-particles color="#000000"> </vue-particles>
      <div class="content">
        <div class="title">黑产应用的后台黑产域名检测</div>
        <div class="text">
          利用面向黑产应用后台域名的自动化流量采集技术，财力黑产应用的后台域名，并予以分类
        </div>
        <div class="ope">
          <el-button @click="$router.push({ name: 'testing' })"
            >开始检测</el-button
          >
        </div>
        <div class="text">
          统计数据网站过往分析APP个数，检测出黑产域名总个数
        </div>
      </div>
      <div class="echarts">
        <div id="echarts1" ref="echarts1"></div>
      </div>
    </mainContainer>
  </div>
</template>

<script>
import * as echarts from "echarts";
export default {
  mounted() {
    this.innit();
  },
  methods: {
    innit() {
      var chartDom = this.$refs.echarts1;
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        legend: {
          top: "bottom",
        },
        toolbox: {
          show: false,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true },
          },
        },
        series: [
          {
            name: "已经统计过的APP",
            type: "pie",
            radius: [50, 250],
            center: ["50%", "50%"],
            roseType: "area",
            itemStyle: {
              borderRadius: 8,
            },
            data: [
              { value: 40, name: " 1月份" },
              { value: 38, name: " 2月份" },
              { value: 32, name: " 3月份" },
              { value: 30, name: " 4月份" },
              { value: 28, name: " 5月份" },
              { value: 26, name: " 6月份" },
              { value: 22, name: " 7月份" },
              { value: 18, name: " 8月份" },
              { value: 18, name: " 9月份" },
              { value: 18, name: " 10月份" },
              { value: 18, name: " 11月份" },
              { value: 18, name: " 12月份" },
            ],
          },
        ],
      };

      option && myChart.setOption(option);
    },
  },
};
</script>

<style scoped>
.content {
  position: absolute;
  top: 40%;
}
.title {
  font-weight: bold;
  font-size: 2rem;
}
.text {
  padding: 10px 0;
}
.echarts {
  position: absolute;
  top: 30%;
  right: 200px;
}
#echarts1 {
  width: 500px;
  height: 500px;
}
</style>
