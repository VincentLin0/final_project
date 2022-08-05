<template>
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu :default-openeds="['1', '2']">
      <!-- <el-submenu index="1">
        <template slot="title"><i class="el-icon-s-order"></i>Content management</template>
        <el-menu-item-group>
          <el-menu-item @click="toCM" index="1-1">Modify content</el-menu-item>
          <el-menu-item @click="toAC" index="1-2">Add content</el-menu-item>
        </el-menu-item-group>
      </el-submenu> -->
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-edit"></i>Test management</template>
        <el-menu-item-group>
          <el-menu-item @click="toQM" index="2-1">Modify question</el-menu-item>
          <el-menu-item @click="toAQ" index="2-2">Add question</el-menu-item>
        </el-menu-item-group>
       

      </el-submenu>
    </el-menu>
  </el-aside>

</template>

<style>
  .el-aside {
    color: #333;
  }
</style>

<script>
	export default{
	methods: {
      toCM(){
			this.$router.push('/backStage');
			this.$router.push(0);
      },
      toAC(){
			this.$router.push('/backStage/upload');
        
        // else{
        //   tanchuang()
        // }
      },
      toQM(){
			this.$router.push('/backStage/testManagement');
      },
      toAQ(){
			this.$router.push('/backStage/testManagement/uploadTest');
        // else{
        //   tanchuang()
        // }
      },
      
    }
	}
</script>
