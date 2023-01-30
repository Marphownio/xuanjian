<template>
  <div>
    <el-container>
      <el-header>
        <layoutHeader />
      </el-header>
      <el-main>
        <slot />
      </el-main>
    </el-container>
  </div>
</template>

<script>
import layoutHeader from "../header";
export default {
  components: {
    layoutHeader,
  },
};
</script>

<style scoped></style>
