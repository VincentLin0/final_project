<template>
  <div>
    <el-popover placement="top-start" title="" trigger="hover" v-if="absolutePath != null&&isPicture">
      <img :src="absolutePath" alt="" style="width: 100px;height: 100px">
      <img slot="reference" :src="absolutePath" style="width: 100px;height: 100px">
    </el-popover>
    
    <el-upload :action="uploadPath" :show-file-list="false"
               :headers="headers"
               :on-success="handleFileUploadSuccess" >
      <el-button type="primary" class="ml-5">upload <i class="el-icon-top"></i></el-button>
    </el-upload>

  </div>
</template>
<script>

export default {
  watch: {
    isClearPath: {
      handler(newValue, oldValue) {
        console.log("新的", JSON.stringify(newValue));
        console.log("旧的", JSON.stringify(oldValue));
        if (newValue) {
          this.absolutePath = ""
        }
      },

    }
  },
  props: {
    uploadPath: {
      type: String,
      default: ""
    },
    isPicture: {
      type: Boolean,
      default: false
    },
    isClearPath: {
      type: Boolean,
      default: false
    },

  },
  data() {
    return {
      absolutePath: "",
      relativePath: "",
      headers: {
        token: localStorage.getItem("token")
      }
    }
  },
  computed: {},
  methods: {
    handleFileUploadSuccess(res) {
      this.absolutePath = res.data.absolutePath
      this.$emit("on-img", res.data.relativePath)
    },
    handleSizeChange(val) {
      this.$message.success("upload success")
      this.$emit('size-change', val)
    },

  }
}
</script>