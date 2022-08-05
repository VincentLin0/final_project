<template>

  <el-container style="height: 500px; border: 1px solid #eee">
    <SideBar></SideBar>
    <el-container>

      <el-main>
        <el-table :data="tableData">
          <el-table-column prop="questionId" label="id" width="80">
          </el-table-column>
          <el-table-column prop="question" label="question" width="450">
          </el-table-column>
          <el-table-column prop="answer" label="answer" width="180">
          </el-table-column>
          <el-table-column >
            <template slot-scope="scope">
            <!-- <el-button type="primary" size="medium" round>edit</el-button> -->
            <el-button type="primary" size="medium" round @click="delete_question(scope.row.question)">delete</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>


</template>


<script>
import SideBar from '../components/SideBar.vue'
import { Loading } from 'element-ui';
import request from '@/utils/request';

export default {
  components: {
    SideBar,
    // AccountBox
  },
  data() {

    return {
      tableData: [],
      questionInfo: {
        data: []
      },
    }
  },
  methods: {
      delete_question(index) {
      this.$confirm('This operation will permanently delete the data. Do you want to continue?', 'Notification', {
        confirmButtonText: 'Yes',
        cancelButtonText: 'No',
        type: 'warning'
      }).then(() => {
        let loadingInstance = Loading.service({ fullscreen: true });
        request.post("users/admin/quiz/delete-question", {
            "question": index
          }) .then((successResponse) => {
          console.log(successResponse)
        request.get("users/admin/quiz/all-quiz")
        // .then意思是指定回调函数
        .then((successResponse) => {
          this.tableData = successResponse.data
          console.log("new data", this.tableData)


        }).catch((failResponse) => {
          console.log(failResponse)
        })
        }).catch((failResponse) => {
          console.log(failResponse)
        })
        this.$forceUpdate()
        loadingInstance.close()
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
    get_all_question() {
      let loadingInstance = Loading.service({ fullscreen: true });
      request.get("users/admin/quiz/all-quiz")
        // .then意思是指定回调函数
        .then((successResponse) => {


          this.tableData = successResponse.data
          console.log("data", this.tableData)
          loadingInstance.close()

        }).catch((failResponse) => {
          console.log(failResponse)
          loadingInstance.close();
        })
    },


  },
  created() {
    this.get_all_question()
  }
};
</script>



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
