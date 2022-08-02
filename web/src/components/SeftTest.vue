<template>
  <div class="self_test">
    <div class="question">{{dataInfo.question}}</div>
<template>
  <el-radio-group class="options" v-model="radio" >
    <el-radio class="option1" :label="3">{{dataInfo.options[0]}}</el-radio>
    <el-radio class="option2" :label="6">{{dataInfo.options[1]}}</el-radio>
    <el-radio class="option3" :label="9">{{dataInfo.options[2]}}</el-radio>
  </el-radio-group>
</template>
      <el-button class="check" @click="check_answer()" type="primary" icon="el-icon-check">Check the answers</el-button>
      <!-- <el-button type="primary" icon="el-icon-check">Next question</el-button> -->
  </div>

</template>

<script>
import request from '../utils/request'

export default {
  name: 'self_test',
  data () {
    return {
      dataInfo:{
		},
      answerInfo:{
		},
      input: '',
      radio: '',
      id:9
    }
  },
  methods: {
      get_QandA(){
        request.post("users/quiz/question",{id:this.id})
          // .then意思是指定回调函数
          .then((successResponse) => {
            // successResponse.data[0].title：就是符合条件的搜索结果的标题。其他以此类推
            // console.log(successResponse.data[0].title)
            console.log(successResponse) // 数组，所有符合条件的结果数组
            // console.log(successResponse.data[0])  // 数组第一个，里面包含id title abstracts
            let list = successResponse
            this.dataInfo = list
            console.log(this.questions)
            if (successResponse.length === 0) {
              alert('error')
            }
          })
          // 指定发生错误时的回调函数
          .catch((failResponse) => {
            console.log(failResponse)
          })
      },
      check_answer(){
        if(this.radio == 3){
        request.post("users/quiz?id="+this.id,{"option": this.dataInfo.options[0]})
          .then((successResponse) => {
            console.log(successResponse) // 数组，所有符合条件的结果数组
            // console.log(successResponse.data[0])  // 数组第一个，里面包含id title abstracts
            let list = successResponse
            this.answerInfo = list
            console.log(this.answerInfo)
            if (successResponse.length === 0) {
              alert('error')
            }
          })
          // 指定发生错误时的回调函数
          .catch((failResponse) => {
            console.log(failResponse)
          })
        }
        if(this.radio == 6){
        request.post("users/quiz?id="+this.id,{"option": this.dataInfo.options[1]})
         .then((successResponse) => {
            console.log(successResponse) // 数组，所有符合条件的结果数组
            // console.log(successResponse.data[0])  // 数组第一个，里面包含id title abstracts
            let list = successResponse
            this.answerInfo = list
            console.log(this.answerInfo)
            if (successResponse.length === 0) {
              alert('error')
            }
          })
          // 指定发生错误时的回调函数
          .catch((failResponse) => {
            console.log(failResponse)
          })
        }
        if(this.radio == 9){
        request.post("users/quiz?id="+this.id,{"option": this.dataInfo.options[2]})
         .then((successResponse) => {
            console.log(successResponse) // 数组，所有符合条件的结果数组
            // console.log(successResponse.data[0])  // 数组第一个，里面包含id title abstracts
            let list = successResponse
            this.answerInfo = list
            console.log(this.answerInfo)
            if (successResponse.length === 0) {
              alert('error')
            }
          })
          // 指定发生错误时的回调函数
          .catch((failResponse) => {
            console.log(failResponse)
          })
        }
        open() 
      },
      next_question(){
        this.id= this.id+1
        this.$router.go(0);
        // request.post("users/quiz/add-questions",{question:"aaa",options:["4","5","6"],answer:"4"})
      },
      open() {
        if(this.answerInfo.right== true){
        this.$alert('the answer is right', 'Result', {
          confirmButtonText: 'OK',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        });
      }
      else{
        this.$alert('the answer is wrong, the correct answer is '+ this.answerInfo.answer, 'Result', {
          confirmButtonText: 'OK',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        });
      }
      }
      
  },
  created(){
      this.get_QandA() 
      // this.check_answer()
      // this.add_question()

      }
}

</script>
	
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.question{
  margin-top: 100px;
  margin-left: 15%;
  font-size: 150%
}
.options{
  margin-top: 80px;
  margin-left: 15%;
  font-size: 120%;

}
.option1{
  display: block;
}
.option2{
  display: block;
  margin-top: 10px;
}
.option3{
  display: block;
  margin-top: 10px;
}
.check{
  display: block;
  margin-top: 100px;
  margin-left: 15%;
}
					
        
</style>
