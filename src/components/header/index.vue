<template>
  <div>
    {{ a }}
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
    >
      <el-menu-item index="home">主页</el-menu-item>
      <el-menu-item index="testing">检测入口</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  computed: {
    activeIndex({ $route }) {
      console.log("$router$router=", $route);
      return $route?.name;
    },
  },
  methods: {
    handleSelect(e) {
      console.log(e);
      this.$router.push({
        name: e,
      });
    },
  },
};
</script>

<style scoped></style>
