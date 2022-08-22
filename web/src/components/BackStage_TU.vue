<template>
  <el-container style="height: 500px; border: 1px solid #eee">
    <SideBar></SideBar>
    <el-container>
      <el-main>
        <div>
          <div style="margin: 10px 0">
            <!--            <el-input @input="search" style="width: 200px" clearable placeholder="please input title"
                                  suffix-icon="el-icon-search" v-model="searchParams.title"></el-input>
                        <el-button class="ml-5" type="primary" @click="search">search</el-button>-->
            <el-button class="ml-5" type="primary" @click="createDrawer"
              >new</el-button
            >
            <!-- <el-button type="warning" @click="resetForm">rewrite</el-button> -->
            <el-button
              type="warning"
              :disabled="delArr.length > 0 ? false : true"
              @click="delAll"
              >delete all</el-button
            >
          </div>

          <el-table
            ref="multipleTable"
            :data="tableData"
            @selection-change="selectionChange"
            tooltip-effect="dark"
            style="width: 100%"
          >
            <el-table-column type="selection" width="55"> </el-table-column>
            <el-table-column prop="id" label="id" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="title" label="title" show-overflow-tooltip>
            </el-table-column>
            <el-table-column
              prop="description"
              label="description"
              show-overflow-tooltip
            >
            </el-table-column>

            <el-table-column label="operation">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)"
                  >edit
                </el-button>
                <el-button
                  size="small"
                  type="primary"
                  @click="handleDelete(scope.row)"
                  >delete
                </el-button>
              </template>
            </el-table-column>
          </el-table>
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
              <el-form-item label="content" prop="title">
                <!--                <CustomEditor   :content="formData.description" @content-change="contentChange"></CustomEditor>-->
                <!--                <el-input type="textarea"
                                          :rows="4" v-model="formData.description"></el-input>-->

                <div id="home">
                  <div id="demo1"></div>
                </div>
              </el-form-item>

              <!--              <el-form-item label="视频路径" prop="title">
                                <CustomUpload :uploadPath="uploadPath" @on-img="video" :is-picture="true"
                                ></CustomUpload>
                            </el-form-item>-->
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
import wangEditor from "wangeditor";
import request from "@/utils/request";
import i18next from "i18next";
export default {
  components: {
    SideBar,
  },
  mixins: [create],
  data() {
    return {
      isDrawerDialog: true,
      size: "0%",
      uploadPath: process.env.VUE_APP_SERVER + "/uploads",
      editor: null,
      api: {
        listUrl: "/tutorial/page",
        saveUrl: "/admin/tutorial/save",
        delUrl: "/admin/tutorial/delBatch",
      },
      /*      formData: {
              id: null,
              title: "",
              description:"",
            },*/
      rulesForm: {
        title: [
          {
            required: true,
            message: "Please input something",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    createDrawer() {
      this.isDrawerDialog = true;
      this.formData = {};
      this.editor.txt.html("<p><br></p>");
    },
    handleEdit(i, item) {
      console.log(i);
      this.isDrawerDialog = true;
      this.formData = item;
      this.editor.txt.html(item.description);
    },
    video(e) {
      console.log(e);
      this.formData.video = e;
    },
    contentChange(e) {
      this.formData.description = e;
      console.log(e);
    },
  },
  mounted() {
    this.isDrawerDialog = false;

    setTimeout(() => {
      this.size = "100%";
    }, 1000);

    let _this = this;
    this.$nextTick(() => {
      const editor = new wangEditor("#demo1");

      editor.config.height = 500;
      editor.config.uploadFileName = "myFile"; // 设置文件上传的参数名称
      // editor.config.uploadImgServer = 'http://localhost:8002/uploadMinio'; //设置上传文件的服务器路径
      editor.config.uploadImgMaxSize = 10 * 1024 * 10240; // 将图片大小限制为 3M
      editor.config.uploadImgMaxLength = 20; // 一次性可以上传20张图片
      console.log("ddd", editor);
      editor.config.customUploadImg = function (files, insert) {
        for (var i = 0; i < files.length; i++) {
          // eslint-disable-next-line one-var
          var data = new FormData();
          data.append("file", files[i]);
          request.post(_this.uploadPath, data).then((res) => {
            console.log(res);
            res = res.data.absolutePath;
            if (res != null) {
              console.log(res);
              insert(res);
            } else {
              alert(res.msg);
              return;
            }
          });
        }
        console.log("customInsert", files);
      };
      editor.config.uploadVideoAccept = ["mov", "mp4"];
      editor.config.customUploadVideo = function (files, insert) {
        for (var i = 0; i < files.length; i++) {
          // eslint-disable-next-line one-var
          var data = new FormData();
          data.append("file", files[i]);
          request.post(_this.uploadPath, data).then((res) => {
            console.log(res);
            res = res.data.absolutePath;
            if (res != null) {
              console.log(res);
              insert(res);
            } else {
              alert(res.msg);
              return;
            }
          });
        }
        console.log("customInsert", files);
      };
      // 配置 onchange 回调函数，将数据同步到 vue 中
      editor.config.onchange = (newHtml) => {
        console.log(newHtml);
        this.formData.description = newHtml;
      };
      // 选择语言
      editor.config.lang = "en";
      // 引入 i18next 插件
      editor.i18next = i18next;

      // 创建编辑器
      editor.create();

      console.log("editor=");
      console.log(editor);
      this.editor = editor;
      console.log(this.formData);
    });
  },
  created() {
    this.$nextTick(() => {
      // dom元素更新后执行，因此这里能正确打印更改之后的值
      // console.log(that.$refs.tar.innerText) // 改变了的值
    });
  },
};
</script>

<style lang="less">
.home {
  width: 100%;
  margin: auto;

  .btn {
    position: absolute;
    right: 0;
    top: 0;
    padding: 5px 10px;
    cursor: pointer;
  }

  h3 {
    margin: 30px 0 15px;
  }
}
</style>
