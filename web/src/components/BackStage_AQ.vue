<template>
  <el-container style="height: 500px; border: 1px solid #eee">
    <SideBar></SideBar>
    <el-container>
      <el-main>
        <div class="hang">
          <div class="question:">question</div>
          <div class="question1">
            <el-input v-model="question" clearable></el-input>
          </div>
        </div>
        <div class="option">option A</div>
        <div class="option1">
          <el-input v-model="option1" clearable></el-input>
        </div>
        <div class="option">option B</div>
        <div class="option1">
          <el-input v-model="option2" clearable></el-input>
        </div>
        <div class="option">option C</div>
        <div class="option1">
          <el-input v-model="option3" clearable></el-input>
        </div>
        <div class="option">option D</div>
        <div class="option1">
          <el-input v-model="option4" clearable></el-input>
        </div>
        <div class="option">answer</div>
        <div class="option1">
          <el-input v-model="answer" clearable></el-input>
        </div>
        <div class="upload">
          <el-button type="primary" @click="upload">upload</el-button>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideBar from "../components/SideBar.vue";
import request from "@/utils/request";

export default {
  components: {
    SideBar,
    // AccountBox
  },
  data() {
    return {
      question: "",
      option1: "",
      option2: "",
      option3: "",
      option4: "",
      answer: "",
    };
  },
  methods: {
    upload() {
      console.log(this.question);
      let question0 = this.question;
      request
        .post("/admin/quiz/add-question", {
          question: question0,
          options: [this.option1, this.option2, this.option3, this.option4],
          answer: this.answer,
        })
        .then((successResponse) => {
          console.log("结果是" + successResponse.data); // 数组，所有符合条件的结果数组
          this.$alert(successResponse.data, "Result", {
            confirmButtonText: "OK",
            callback: (action) => {
              console.log("111");
                this.question = "";
                this.option1 = "";
                this.option2 = "";
                this.option3 = "";
                this.option4 = "";
                this.answer = "";
                // eslint-disable-next-line no-lone-blocks, no-unused-expressions
                {action}
            },
          });
        })
        // 指定发生错误时的回调函数
        .catch((failResponse) => {
          console.log(failResponse);
        });
    },
  },
};
</script>

<style>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
.question1 {
  margin-top: 10px;
  width: 70%;
}
.option {
  margin-top: 10px;
}
.option1 {
  margin-top: 10px;
  width: 50%;
  display: inline-block;
}
.upload {
  margin-top: 20px;
  margin-left: 30px;
  display: inline-block;
}
</style>
