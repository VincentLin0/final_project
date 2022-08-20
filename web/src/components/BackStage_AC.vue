<template>
  <el-container style="height: 500px; border: 1px solid #eee">
    <SideBar></SideBar>
    <el-container>
      <el-main>
        <div>
          <div class="input1">
            Title<el-input v-model="formData.title"></el-input>
          </div>

          <div class="input1">
            Class<el-input v-model="formData.kind"></el-input>
          </div>
          <div class="input2">
            Description
            <el-input
              type="textarea"
              :rows="2"
              v-model="formData.description"
            ></el-input>
          </div>
          <div class="pic1">
            picture1
            <CustomUpload
              :uploadPath="uploadPath"
              :isClearPath="isClearPath"
              :is-picture="true"
              @on-img="pic1"
            ></CustomUpload>
          </div>

          <div class="pic1">
            picture2<CustomUpload
              :uploadPath="uploadPath"
              :isClearPath="isClearPath"
              :is-picture="true"
              @on-img="pic2"
            ></CustomUpload>
          </div>
          <div class="pic1">
            picture3<CustomUpload
              :uploadPath="uploadPath"
              :isClearPath="isClearPath"
              :is-picture="true"
              @on-img="pic3"
            ></CustomUpload>
          </div>
          <div class="audioAndVideo">
            <div class="audio">
              audio
              <CustomUpload
                :uploadPath="uploadPath"
                :isClearPath="isClearPath"
                @on-img="pic4"
              ></CustomUpload>
            </div>
            <div class="video">
              video<CustomUpload
                :uploadPath="uploadPath"
                :isClearPath="isClearPath"
                @on-img="pic5"
              ></CustomUpload>
            </div>
          </div>
        </div>
        <!--        <CustomEditor @reset-editor="resetEditor" :is-clear-content="isClearContent" :content="content" @content-change="contentChange"></CustomEditor>-->
        <div class="submit">
          <el-button type="primary" @click="save">submit</el-button>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<style>
.submit {
  margin-top: 20px;
}
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}

.input1 {
  width: 50%;
  margin-top: 10px;
}
.input2 {
  width: 70%;
  margin-top: 10px;
}
.pic1 {
  margin-top: 10px;
  margin-right: 20px;
  display: inline-block;
}
.audio {
  margin-top: 10px;
  margin-right: 20px;
  /* display: inline-block; */
}
.video {
  margin-top: 10px;
  margin-right: 20px;
  display: inline-block;
}
.audioAndVideo {
  display: inline-block;
}
</style>

<script>
import SideBar from "../components/SideBar.vue";
// import CustomEditor from "@/components/temp/CustomEditor";
import request from "@/utils/request";

export default {
  components: {
    SideBar,
    // CustomEditor
    // AccountBox
  },
  data() {
    return {
      isClearPath: false,
      uploadPath: process.env.VUE_APP_SERVER + "/uploads",
      input: "",
      isClearContent: false,
      /*      audio: （…）
      description: （…）
      id: （…）
      kind: （…）
      pic1: （…）
      pic2: （…）
      pic3: （…）
      title: （…）
      video: （…）*/
      content: "",
      formData: {
        description: "",
        title: "",
        kind: "",
        content: "123",
      },
    };
  },
  methods: {
    pic1(e) {
      this.formData.pic1 = e;
    },
    pic2(e) {
      this.formData.pic2 = e;
    },
    pic3(e) {
      this.formData.pic3 = e;
    },
    pic4(e) {
      this.formData.audio = e;
    },
    pic5(e) {
      this.formData.video = e;
    },
    save() {
      // this.formData.description = this.content
      request.post("save", this.formData).then((successResponse) => {
        this.tableData = successResponse.data;
        if (successResponse.code === "200") {
          this.$message.success("successful");
        }
        // location. reload()
        this.$router.go(0);
      });
    },
    contentChange(e) {
      this.formData.description = e;
      console.log(e);
    },
    resetEditor(e) {
      this.isClearContent = e;
    },
    open() {
      this.$confirm(
        "This operation will permanently delete the data. Do you want to continue?",
        "Notification",
        {
          confirmButtonText: "Yes",
          cancelButtonText: "No",
          type: "warning",
        }
      )
        .then(() => {
          this.$message({
            type: "success",
            message: "Deleted successfully!",
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "The deletion has been cancelled.",
          });
        });
    },
  },
};
</script>
