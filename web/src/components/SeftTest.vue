<template>
  <div class="self_test">
      {{questions[0]}}
<template>
  <el-radio-group v-model="radio">
    <el-radio :label="3">备选项</el-radio>
    <el-radio :label="6">备选项</el-radio>
    <el-radio :label="9">备选项</el-radio>
  </el-radio-group>
</template>
      <el-button type="primary" icon="el-icon-check">提交</el-button>

  </div>
</template>

<script>
import request from '../utils/request'

export default {
  name: 'self_test',
  data () {
    return {
      questions: [],
      input: '',
      radio: '3'
    }
  },
  methods: {
      get_question(){
        request.get("users/quiz/list-of-quiz")
          // .then意思是指定回调函数
          .then((successResponse) => {
            // successResponse.data[0].title：就是符合条件的搜索结果的标题。其他以此类推
            // console.log(successResponse.data[0].title)
            console.log(successResponse) // 数组，所有符合条件的结果数组
            // console.log(successResponse.data[0])  // 数组第一个，里面包含id title abstracts
            let list = successResponse
            this.questions = list
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
        request.post("users/quiz?id=2",{"option":"red"})
      },
      add_question(){
        request.post("users/quiz/add-questions",{question:"aaa",options:["4","5","6"],answer:"4"})
      }
  },
  created(){
      this.get_question() 
      this.check_answer()
      // this.add_question()

      }
}

</script>
	
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
					
        
</style>
