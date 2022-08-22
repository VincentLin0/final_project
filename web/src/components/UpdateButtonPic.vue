<template>
  <!--  action="http://176.58.99.74:8888/uploads"-->

  <el-upload
    class="upload-demo"
    action="uploadPath"
    :on-preview="handlePreview"
    :on-remove="handleRemove"
    :file-list="fileList"
    list-type="picture"
  >
    <el-button size="small" type="primary">upload</el-button>
    <div slot="tip" class="el-upload__tip">only upload jpg/png</div>
  </el-upload>
</template>

<script>
export default {
  data() {
    return {
      uploadPath: process.env.VUE_APP_SERVER + "/uploads",
      fileList: [
        {
          name: "food.jpeg",
          url: "http://localhost:8080/view?relativePath=79200ad8-3394-4644-acd7-8959282a9848.png",
        },
        {
          name: "111",
          url: "/Users/linduoying/Downloads/saveAllFile/79200ad8-3394-4644-acd7-8959282a9848.png",
        },
      ],
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
  },
};
</script>
