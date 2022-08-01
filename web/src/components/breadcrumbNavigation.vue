<template>
  <div class="BreadcrumbNavgation">
  <el-breadcrumb separator-class="el-icon-arrow-right">
  <el-breadcrumb-item :to="{ path: '/' }">Home</el-breadcrumb-item>
  <el-breadcrumb-item :to="{path:'/search'}">Search</el-breadcrumb-item>
  <el-breadcrumb-item>current</el-breadcrumb-item>
  </el-breadcrumb>
  </div>
</template>

<script>
export default {
  name: 'BreadcrumbNavgation',
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
