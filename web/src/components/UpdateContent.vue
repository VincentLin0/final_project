<template>
  <el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="title">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
    <el-form-item label="class">
      <el-input v-model="form.class"></el-input>
    </el-form-item>

    <el-form-item label="time">
      <el-col :span="11">
        <el-date-picker
          type="date"
          placeholder="Select a date"
          v-model="form.date1"
          style="width: 100%"
        >
        </el-date-picker>
      </el-col>
      <el-col class="line" :span="2">-</el-col>
      <el-col :span="11">
        <el-time-picker
          placeholder="Select time"
          v-model="form.date2"
          style="width: 100%"
        ></el-time-picker>
      </el-col>
    </el-form-item>

    <el-form-item label="description">
      <el-input type="textarea" v-model="form.desc"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">publish</el-button>
      <el-button>cancel</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data() {
    return {
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
      },
    };
  },
  methods: {
    onSubmit() {
      console.log("submit!");
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
