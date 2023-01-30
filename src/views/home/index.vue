<template>
  <div>
    <mainContainer>
      <vue-particles
        color="#000000"
      >
      </vue-particles>
      <div class="content">
        <div class="title">
          黑产应用的后台黑产域名检测
        </div>
        <div class="text">
          利用面向黑产应用后台域名的自动化流量采集技术，财力黑产应用的后台域名，并予以分类
        </div>
        <div class="ope">
          <el-button @click="$router.push({name:'testing'})">开始检测</el-button>
        </div>
        <div class="text">统计数据网站过往分析APP个数，检测出黑产域名总个数</div>
      </div>
    </mainContainer>
  </div>
</template>

<script>
export default {
  methods: {
    push() {},
  },
};
</script>

<style scoped>
.content{
  position: absolute;
  top: 40%;
}
.title{
  font-weight: bold;
  font-size: 2rem;
}
.text{
  padding: 10px 0;
}
</style>
