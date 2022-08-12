<template>
  <div>
    <!--    :visible.sync-->
    <el-drawer
        :size="size"
        :title="formData.id == null ?'new':'edit data'"
        :before-close="handleClose"
        :visible="isDrawerDialog"
        :modal="modal"
        direction="rtl"
        custom-class="demo-drawer"
        ref="drawer"
    >
      <div class="demo-drawer__content">
        <el-form :model="formData" :rules="rulesForm" ref="formData" label-width="100px" class="demo-ruleForm">
          <slot name="content">这里是中间</slot>
        </el-form>
        <div class="demo-drawer__footer">
          <el-button @click="cancelForm('formData')">cancle</el-button>
          <el-button type="primary" @click="submitForm('formData')" :loading="loading">upload</el-button>
        </div>
      </div>
    </el-drawer>
  </div>

</template>

<script>
export default {
  mounted() {
  },
  props: {
    modal:{
      type: Boolean,
      default: true
    },
    isDrawerDialog: {
      type: Boolean,
      default: false
    },
    formData: {
      type: Object,
      default: function () {
        return {}
      }
    },
    rulesForm: {
      type: Object,
      default: function () {
        return {}
      }
    },
    size: {
      type: String,
      default: '30%'
    }
//100%
  },
  data() {
    return {
      loading: false,
      timer: null,
    };
  },
  methods: {
    handleClose() {
      /*      if (this.loading) {
              return;
            }*/
      this.$emit("close-drawer", false)

    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$emit("submit-drawer", this.formData)
        } else {
          // console.log('error submit!!');
          this.$message.error('Please input the correct data');

          return false;
        }
      });

    },
    cancelForm(formName) {
      this.$refs[formName].resetFields();
      this.loading = false;
      this.$emit("close-drawer", false)
      clearTimeout(this.timer);
    },

  },

}
</script>