<template>
  <div class="self_test">
    <div class="question">{{ dataInfo.question.questionTitle }}</div>
    <div class="image">
      <div>
        <el-image
          style="width: 445px; height: 250px"
          :src="dataInfo.question.image"
        ></el-image>
      </div>

      <Aplayer
        v-if="dataInfo.question.audio != null"
        class="audio-player"
        :music="audioPlayerData"
      ></Aplayer>
    </div>
    <template>
      <!-- <el-radio-group class="options" v-model="radio" >
    <el-radio class="option1" :label="3">{{dataInfo.options[0]}}</el-radio>
    <el-radio class="option2" :label="6">{{dataInfo.options[1]}}</el-radio>
    <el-radio class="option3" :label="9">{{dataInfo.options[2]}}</el-radio>
  </el-radio-group> -->
      <el-radio-group class="options" v-model="radio">
        <el-radio
          class="option1"
          v-for="item in dataInfo.options"
          :key="item"
          :label="item"
          >{{ item }}</el-radio
        >
      </el-radio-group>
    </template>

    <el-button
      v-show="isShow == false"
      class="check"
      @click="check_answer"
      type="primary"
      icon="el-icon-check"
      >Check the answers</el-button
    >
    <el-button
      v-show="isShow == true && this.num != this.last"
      class="check1"
      @click="next_question"
      type="primary"
      icon="el-icon-check"
      >Next question</el-button
    >
    <el-button
      v-show="resultIsShow == true "
      class="check1"
      @click="check_result"
      type="primary"
      icon="el-icon-check"
      >Check result</el-button
    >
    <!-- <el-button type="primary" icon="el-icon-check">Next question</el-button> -->
  </div>
</template>

<script>
import {Loading} from "element-ui";
import request from "../utils/request";
import Aplayer from "vue-aplayer";

export default {
  name: "self_test",
  components: {
    Aplayer,
  },
  data() {
    return {
      isShow:false,
      resultIsShow:false,
      dataInfo: {
        options: [],
        question: {
          questionTite: "",
        },
      },
      questionInfo: [],
      answerInfo: {},
      input: "",
      radio: "",
      id: 9,
      num: 0,
      last: 0,

      audioPlayerData: {},
    };
  },
  methods: {
    get_all_question() {
      let loadingInstance = Loading.service({fullscreen: true});
      request.post("users/quiz/clear-data");
      request
        .get("users/quiz/list-of-quiz")
        // .then???????????????????????????
        .then((successResponse) => {
          console.log(successResponse); // ??????????????????????????????????????????
          let list = successResponse;
          this.questionInfo = list.data;
          console.log("??????id???" + this.questionInfo);
          console.log("?????????id???" + this.questionInfo[this.num]);
          console.log("???????????????" + this.questionInfo.length);
          this.id = this.questionInfo[this.num];
          this.last = this.questionInfo.length - 1;
          console.log("??????????????????" + this.last);
          request
            .post("users/quiz/question", {id: this.id})
            // .then???????????????????????????
            .then((successResponse) => {
              loadingInstance.close();
              console.log(successResponse); // ??????????????????????????????????????????
              let list = successResponse;
              this.dataInfo = list.data;
              console.log("?????????0" + this.dataInfo.question);
              console.log("?????????0" + this.dataInfo.options[0]);
              console.log("audio???0", this.dataInfo.question.audio);
              this.audioPlayerData = {
                title: "audio",
                artist: "question",
                src: this.dataInfo.question.audio,
                pic: "http://176.58.99.74:8888/view?relativePath=2022/08/14/7ad3f443-21fd-4fe1-8563-97d90d2a593b.svg",
              };
            })
            // ????????????????????????????????????
            .catch((failResponse) => {
              console.log(failResponse);
            });
          // console.log("?????????"+this.dataInfo.options[0])
        })
        // ????????????????????????????????????
        .catch((failResponse) => {
          console.log(failResponse);
        });
    },
    get_QandA() {
      let loadingInstance = Loading.service({fullscreen: true});
      request
        .post("users/quiz/question", {id: this.id})
        // .then???????????????????????????
        .then((successResponse) => {
          loadingInstance.close();
          console.log("aaaa", successResponse); // ??????????????????????????????????????????
          let list = successResponse;
          this.dataInfo = list.data;
          console.log("?????????1" + this.dataInfo.question.questionTitle);
          console.log("?????????1" + this.dataInfo.options[0]);
          console.log("?????????1" + this.dataInfo.question.audio);
          this.audioPlayerData = {
            title: "audio",
            artist: "question",
            src: this.dataInfo.question.audio,
            pic: "http://176.58.99.74:8888/view?relativePath=2022/08/14/7ad3f443-21fd-4fe1-8563-97d90d2a593b.svg",
          };
        })
        // ????????????????????????????????????
        .catch((failResponse) => {
          console.log(failResponse);
        });

      // request.post("users/quiz/question",{id:this.id})
      //   // .then???????????????????????????
      //   .then((successResponse) => {
      //     loadingInstance.close();
      //     console.log(successResponse) // ??????????????????????????????????????????
      //     let list = successResponse
      //     this.dataInfo = list.data
      //     console.log(this.dataInfo)

      //   })
      //   // ????????????????????????????????????
      //   .catch((failResponse) => {
      //     console.log(failResponse)
      //   })
    },
    check_answer() {
      for (let i = 0; i < this.dataInfo.options.length; i++) {
        if (this.radio == this.dataInfo.options[i]) {
          request
            .post("users/quiz?id=" + this.id, {
              option: this.dataInfo.options[i],
            })
            .then((successResponse) => {
              console.log(successResponse); // ??????????????????????????????????????????
              // console.log(successResponse.data[0])  // ??????????????????????????????id title abstracts
              let list = successResponse;
              this.answerInfo = list.data;
              console.log(this.answerInfo);
              this.open();
              if (successResponse.length === 0) {
                alert("error");
              }

            })
            // ????????????????????????????????????
            .catch((failResponse) => {
              console.log(failResponse);
            });
        }
      }

      // if(this.radio === 3){
      // request.post("users/quiz?id="+this.id,{"option": this.dataInfo.options[0]})
      //   .then((successResponse) => {
      //     console.log(successResponse) // ??????????????????????????????????????????
      //     // console.log(successResponse.data[0])  // ??????????????????????????????id title abstracts
      //     let list = successResponse
      //     this.answerInfo = list.data
      //     console.log(this.answerInfo)
      //     this.open()
      //     if (successResponse.length === 0) {
      //       alert('error')
      //     }
      //   })
      //   // ????????????????????????????????????
      //   .catch((failResponse) => {
      //     console.log(failResponse)
      //   })
      // }
      // if(this.radio === 6){
      // request.post("users/quiz?id="+this.id,{"option": this.dataInfo.options[1]})
      //  .then((successResponse) => {
      //     console.log(successResponse) // ??????????????????????????????????????????
      //     // console.log(successResponse.data[0])  // ??????????????????????????????id title abstracts
      //     let list = successResponse
      //     this.answerInfo = list.data
      //     console.log(this.answerInfo)
      //     this.open()
      //     if (successResponse.length === 0) {
      //       alert('error')
      //     }
      //   })
      //   // ????????????????????????????????????
      //   .catch((failResponse) => {
      //     console.log(failResponse)
      //   })
      // }
      // if(this.radio === 9){
      // request.post("users/quiz?id="+this.id,{"option": this.dataInfo.options[2]})
      //  .then((successResponse) => {
      //     console.log(successResponse) // ??????????????????????????????????????????
      //     // console.log(successResponse.data[0])  // ??????????????????????????????id title abstracts
      //     let list = successResponse
      //     this.answerInfo = list.data
      //     console.log(this.answerInfo)
      //     this.open()
      //     if (successResponse.length === 0) {
      //       alert('error')
      //     }
      //   })
      //   // ????????????????????????????????????
      //   .catch((failResponse) => {
      //     console.log(failResponse)
      //   })
      // }
    },
    changeIsShowToTure(){
      this.isShow = true;
    },
    changeIsShowToFalse(){
      this.isShow = false;
    },
    changeResultIsShow(){
      this.resultIsShow = true;
    },
    next_question() {
      this.num = this.num + 1;
      this.id = this.questionInfo[this.num];

      console.log("????????????" + this.id);
      this.get_QandA();
      this.radio = "";
      this.$forceUpdate();
      this.changeIsShowToFalse()
      // this.$router.go(0);
      // request.post("users/quiz/add-questions",{question:"aaa",options:["4","5","6"],answer:"4"})
    },
    check_result() {
      request
        .get("users/quiz/result")
        .then((successResponse) => {
          console.log("?????????" + successResponse.data); // ??????????????????????????????????????????
          this.$alert(successResponse.data, "Result", {
            confirmButtonText: "Return to the home page",
            callback: (action) => {
              this.$router.push("/");
              // eslint-disable-next-line no-lone-blocks
              {
                // eslint-disable-next-line no-lone-blocks, no-unused-expressions
                {action}
              }
            },
          });
        })
        // ????????????????????????????????????
        .catch((failResponse) => {
          console.log(failResponse);
        });
    },

    open() {
      // if (this.num < this.last) {
        if (this.answerInfo.right === true) {
          this.$alert("the answer is right", "Result", {
            confirmButtonText: "Ok",
            callback: (action) => {
              this.changeIsShowToTure();
              if (this.num == this.last){
              this.changeResultIsShow();
              }
              // this.next_question();
              // this.$message({
              //   type: "success",
              //   message:
              //     "The answer has been uploaded, moving to the next question",
              // });
              // eslint-disable-next-line no-lone-blocks
              {
                // eslint-disable-next-line no-unused-expressions
                action;
              }
            },
          });
        } else {
          this.$alert(
            "the answer is wrong, the correct answer is " +
              this.answerInfo.answer,
            "Result",
            {
              confirmButtonText: "Ok",
              callback: (action) => {
                this.changeIsShowToTure();
                if (this.num == this.last){
                this.changeResultIsShow();
              }
                // this.next_question();
                // this.$message({
                //   type: "success",
                //   message:
                //     "The answer has been uploaded, moving to the next question",
                // });
                // eslint-disable-next-line no-lone-blocks
                {
                  // eslint-disable-next-line no-unused-expressions
                  action;
                }
              },
            }
          );
        }
      // } else {
      //   if (this.answerInfo.right === true) {
      //     this.$alert("the answer is right", "Result", {
      //       confirmButtonText: "Check the result",
      //       callback: (action) => {
      //         this.check_result();
      //         this.$message({
      //           type: "success",
      //           message: "The answer has been uploaded, Let's check the result",
      //         });
      //         // eslint-disable-next-line no-lone-blocks
      //         {
      //           // eslint-disable-next-line no-unused-expressions
      //           action;
      //         }
      //       },
      //     });
      //   } else {
      //     this.$alert(
      //       "the answer is wrong, the correct answer is " +
      //         this.answerInfo.answer,
      //       "Result",
      //       {
      //         confirmButtonText: "Check the result",
      //         callback: (action) => {
      //           this.check_result();
      //           this.$message({
      //             type: "success",
      //             message:
      //               "The answer has been uploaded, Let's check the result",
      //           });
      //           // eslint-disable-next-line no-lone-blocks
      //           {
      //             // eslint-disable-next-line no-unused-expressions
      //             action;
      //           }
      //         },
      //       }
      //     );
      //   }
      // }
    },
  },

  created() {
    this.get_all_question();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.audio-player {
  width: 60%;
}
.image {
  margin-left: 15%;
  margin-top: 20px;
}
.question {
  width:60%;
  margin-top: 50px;
  margin-left: 15%;
  font-size: 150%;
}
.options {
  margin-top: 30px;
  margin-left: 15%;
  font-size: 120%;
}
.option1 {
  display: block;
  margin-top: 10px;
}
.option2 {
  display: block;
  margin-top: 10px;
}
.option3 {
  display: block;
  margin-top: 10px;
}
.check {
  display: block;
  margin-top: 100px;
  margin-bottom: 100px;
  margin-left: 15%;
}
.check1{
  display: block;
  margin-top: 100px;
  margin-bottom: 100px;
  position: relative;
  left:15%;
}
</style>
