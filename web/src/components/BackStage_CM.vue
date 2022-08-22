<template>
  <el-container style="height: 500px; border: 1px solid #eee">
    <SideBar></SideBar>
    <el-container>
      <el-main>
        <div>
          <!-- <div style="margin: 10px 0"> -->
          <!--            <el-input @input="search" style="width: 200px" clearable placeholder="plese input id or title" suffix-icon="el-icon-search" v-model="searchParams.title"></el-input>
            <el-button class="ml-5" type="primary" @click="search">search</el-button>-->
          <!-- <el-button class="ml-5" type="primary" @click="createDrawer">new</el-button> -->
          <!-- <el-button type="warning" @click="resetForm">rewrite</el-button> -->
          <!-- <el-button type="warning" :disabled="delArr.length>0?false:true" @click="delAll">delete all</el-button> -->
          <!-- </div> -->

          <el-table
            ref="singleTable"
            :data="tableData"
            @selection-change="selectionChange"
            tooltip-effect="dark"
            style="width: 100%"
          >
            <el-table-column prop="id" label="id" width="50"> </el-table-column>
            <el-table-column prop="title" label="title" show-overflow-tooltip>
            </el-table-column>
            <el-table-column label="operation">
              <template slot-scope="scope">
                <!-- <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">edit
                </el-button> -->
                <el-button
                  size="small"
                  type="primary"
                  @click="handleDelete(scope.row)"
                  >delete
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <!--    //@sizeChange[错误的] @size-change[正确的]-->
          <CustomPages
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :currentPage="pageInfo.pageNum"
            :total="pageInfo.total"
            :pageSize="pageInfo.pageSize"
          ></CustomPages>
          <CustomDrawer
            :modal="false"
            @close-drawer="closeDrawer"
            :isDrawerDialog="isDrawerDialog"
            @submit-drawer="submitDrawer"
            :form-data="formData"
            :rules-form="rulesForm"
            :size="size"
          >
            <div slot="content">
              <el-form-item label="title" prop="title">
                <el-input v-model="formData.title"></el-input>
              </el-form-item>
              <el-form-item label="class" prop="kind">
                <el-input v-model="formData.kind"></el-input>
              </el-form-item>
              <el-form-item label="description" prop="description">
                <el-input v-model="formData.description"></el-input>
              </el-form-item>
              <el-form-item label="picture1" prop="pic1">
                <el-input v-model="formData.pic1"></el-input>
              </el-form-item>
              <el-form-item label="picture2" prop="pic2">
                <el-input v-model="formData.pic2"></el-input>
              </el-form-item>
              <el-form-item label="picture3" prop="pic3">
                <el-input v-model="formData.pic3"></el-input>
              </el-form-item>
              <el-form-item label="audio" prop="audio">
                <el-input v-model="formData.audio"></el-input>
              </el-form-item>
              <el-form-item label="video" prop="video">
                <el-input v-model="formData.video"></el-input>
              </el-form-item>
            </div>
          </CustomDrawer>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<style>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>

<script>
import SideBar from "../components/SideBar.vue";
// 引入混合
import create from "@/mixins/create";
export default {
  components: {
    SideBar,
  },
  mixins: [create],
  data() {
    return {
      isDrawerDialog: true,
      size: "0%",
      api: {
        listUrl: "/page",
        saveUrl: "/admin/search/save",
        delUrl: "/admin/tutorial/delBatch",
      },
      formData: {
        id: null,
        title: "",
      },
      rulesForm: {
        title: [
          {
            required: true,
            message: "please input something",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {},
  mounted() {
    this.isDrawerDialog = false;

    setTimeout(() => {
      this.size = "100%";
    }, 1000);
  },
};
</script>
