<template>
	
	<el-container style="height: 500px; border: 1px solid #eee">
  <SideBar></SideBar>
  <el-container>    

    <el-main>
      <el-table :data="tableData">
        <el-table-column prop="date" label="id" width="80">
        </el-table-column>
        <el-table-column prop="name" label="title" width="200">
        </el-table-column>
        <el-table-column prop="description" label="description" width="600">
        </el-table-column>
        <el-table-column >
          <el-button type="primary" size="medium" round>edit</el-button>
          <el-button type="primary" size="medium" round  @click="open">delete</el-button>
          </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</el-container>


</template>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }
</style>

<script>
import SideBar from '../components/SideBar.vue'




  export default {
      components: {
    SideBar,
    // AccountBox
  },
    data() {
      const item = {
        date: '1',
        name: 'Musical murmur',
        description: 'A musical murmur results from a uniform periodic vibration of a cardiac structure. A non-musical murmur results from turbulent blood flow which initiates random vibrations of adjacent structures.'
      };
      return {
        tableData: Array(20).fill(item)
      }
    },
    methods: {
      open() {
        this.$confirm('This operation will permanently delete the data. Do you want to continue?', 'Notification', {
          confirmButtonText: 'Yes',
          cancelButtonText: 'No',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: 'Deleted successfully!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'The deletion has been cancelled.'
          });          
        });
      },
      
      
    }
  };
</script>