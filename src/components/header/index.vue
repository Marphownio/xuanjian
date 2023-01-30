<template>
<div>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
    <el-menu-item index="1">主页</el-menu-item>
    <el-menu-item index="2" disabled>检测入口</el-menu-item>
  </el-menu>
</div>
</template>

<script>
export default {
  name: "index",
  data(){
    return{
      activeIndex:1
    }
  },
  methods:{
    handleSelect(e){
      console.log(e)
    }
  }
}
</script>

<style scoped>

</style>
