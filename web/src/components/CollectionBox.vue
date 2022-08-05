<template>
<div class="Box">
    <div class="blank"></div>
    <div class="BreadcrumbNavgation"><BreadcrumbNavgation></BreadcrumbNavgation> </div>
	<div class="result-num">the following are my collection</div>
{{searchContent}}
	<ul class="results">
        <li
          class="result-content"
          v-for="collectionResult in collectionResults"
          :key="collectionResult.id"
        >
        
          <router-link class="Rtitle" :to="'/content/' + collectionResult.id" >{{ searchResult.title }}</router-link>
        
          <!-- 如果返回的图片地址不为空，有图片盒子，文字盒子样式为des-text1 -->
          <div class="description" v-if="collectionResult.pic1 !== null">
            <div class="image">
                <!-- 给img的src绑定数据要用v-bind -->
                <img :src='collectionResult.pic1' />
            </div>
            <div class="des-text1">
              {{ collectionResult.description }}
            </div>
          </div>
          <!-- 如果返回的图片地址为空，没有图片盒子，文字盒子样式为des-text2 -->
          <div class="description" v-else>
            <div class="des-text2">
              {{ collectionResult.description }}
            </div>
          </div>
        </li>
      </ul>
	<!-- <div><button>上一页</button>
	<button>下一页</button>
	</div> -->
  </div>
</template>

<script>
import request from '../utils/request'
import BreadcrumbNavgation  from '../components/breadcrumbNavigation.vue'
export default {
  name: 'searchResult',
  data () {
    return {

        
      searchContent: '',
      
      collectionResults: []
    }
  },
  components: {
    BreadcrumbNavgation
    // AccountBox
  },
  methods: {
      search(){
        request.post("/result", {
            // 把 search 页面的 input 内容映射到 searchContent 变量中并发送给后端
            searchContent: this.$route.params.searchContent// 通过 v-model 把 input 框输入的内容存进 searchContent 中
          })
          // .then意思是指定回调函数
          .then((successResponse) => {
            // successResponse.data[0].title：就是符合条件的搜索结果的标题。其他以此类推
            // console.log(successResponse.data[0].title)
            console.log(successResponse) // 数组，所有符合条件的结果数组
            // console.log(successResponse.data[0])  // 数组第一个，里面包含id title abstracts
            let list = successResponse
            this.collectionResults = list
            console.log(this.collectionResults)
            if (successResponse.length === 0) {
              alert('error')
            }
          })
          // 指定发生错误时的回调函数
          .catch((failResponse) => {
            console.log(failResponse)
          })
      }
  },
  created(){
      this.search() 
      }
}

</script>
  
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.blank{
  position: absolute;
  height: 900px;
}
.box {
  /* position: relative; */
  width: 1088px;
  height: 1440px;
  /* background-color: rgb(153, 238, 238); */
  margin-left: 150px;
}
.BreadcrumbNavgation{
  margin-top: 20px;
}
.results {
  margin-top: 20px;
  width: 60%;
  margin-left: 15%;
  list-style: none;
  /* background-color: #bfc; */
}

.result-num {
  margin-top: 20px;
  margin-left: 15%;
  width: 538px;
  line-height: 30px;
  font-size: 13px;
  color: #999999;
}

.Rtitle{
  display: block;
  width: 560px;
  height: 22px;
  margin-bottom: 4px;
  text-decoration: none;
  color: #2440b3;
  font-weight: normal;
}

.results h3 a:hover {
  color: #315efb;
}

.result-content {
  margin-bottom: 20px;
}

.description {
  /* position: relative;
  top: 6px;
  width: 560px;
  height: 85px; */
  /* background-color: aqua; */
} 

.description img {
  width: 25%;
  margin-top: 20px;
  margin-left: 0%;
  border: 1px solid #f2f2f2;
  border-radius: 15px;
}  


.des-text1 {
  position: relative;
  margin-left: 40%;
  top: -130px;
  width: 416px;
  font-size: 18px;
  color: #333;
  /* background-color: #315efb; */
}

.des-text2 {
  /* position: absolute;
  left: 0;
  width: 560px;
  font-size: 13px;
  color: #333; */
  margin-top: 20px;
}
  @media screen and (min-width: 900px) and (max-width: 1440px){
    .results {
      margin-top: 20px;
      width: 60%;
    margin-left: 7%;
    list-style: none;
  /* background-color: #bfc; */
}

    .result-num {
      margin-top: 20px;
    margin-left: 7%;
    width: 538px;
    line-height: 30px;
    font-size: 13px;
    color: #999999;
}
.description {
   margin-left: 0%;
} 

.description img {
  width: 25%;
  margin-top: 20px;
  margin-left: 0%;
  border: 1px solid #f2f2f2;
  border-radius: 15px;
}  
.des-text1 {
  position: relative;
  margin-left: 50%;
  top: -130px;
  width: 416px;
  font-size: 18px;
  color: #333;
  /* background-color: #315efb; */
}
    }

@media screen and (min-width: 300px) and (max-width: 720px){
    .box {
  position: relative;
  width: 1088px;
  height: 1440px;
  /* background-color: rgb(153, 238, 238); */
  margin-left: 150px;
}
.BreadcrumbNavgation{
  margin-top: 20px;
}
.results {
  margin-top: 20px;
  width: 60%;
  margin-left: 5%;
  list-style: none;
  /* background-color: #bfc; */
}

.result-num {
  margin-top: 20px;
  margin-left: 5%;
  width: 538px;
  line-height: 30px;
  font-size: 13px;
  color: #999999;
}

.Rtitle{
  display: block;
  width: 560px;
  height: 22px;
  margin-bottom: 4px;
  text-decoration: none;
  color: #2440b3;
  font-weight: normal;
}

.results h3 a:hover {
  color: #315efb;
}

.result-content {
  margin-bottom: 20px;
}

.description {
  /* position: relative;
  top: 6px;
  width: 560px;
  height: 85px; */
  /* background-color: aqua; */
} 

.description img {
  width: 25%;
  margin-top: 20px;
  margin-left: 0%;
  border: 1px solid #f2f2f2;
  border-radius: 15px;
  display: none;
}  


.des-text1 {
  position: relative;
  margin-left: 0%;
  top: 10px;
  width: 416px;
  font-size: 18px;
  color: #333;
  width: 90%;
  /* background-color: #315efb; */
}

.des-text2 {
  /* position: absolute;
  left: 0;
  width: 560px;
  font-size: 13px;
  color: #333; */
  margin-top: 20px;
}
}

@media screen and (min-width: 720px) and (max-width: 900px){
    .box {
  position: relative;
  width: 1088px;
  height: 1440px;
  /* background-color: rgb(153, 238, 238); */
  margin-left: 150px;
}
.BreadcrumbNavgation{
  margin-top: 20px;
}
.results {
  margin-top: 20px;
  width: 60%;
  margin-left: 15%;
  list-style: none;
  /* background-color: #bfc; */
}

.result-num {
  margin-top: 20px;
  margin-left: 15%;
  width: 538px;
  line-height: 30px;
  font-size: 13px;
  color: #999999;
}

.Rtitle{
  display: block;
  width: 560px;
  height: 22px;
  margin-bottom: 4px;
  text-decoration: none;
  color: #2440b3;
  font-weight: normal;
}

.results h3 a:hover {
  color: #315efb;
}

.result-content {
  margin-bottom: 20px;
}

.description {
  /* position: relative;
  top: 6px;
  width: 560px;
  height: 85px; */
  /* background-color: aqua; */
} 

.description img {
  width: 25%;
  margin-top: 20px;
  margin-left: 0%;
  border: 1px solid #f2f2f2;
  border-radius: 15px;
  display: none;
}  


.des-text1 {
  position: relative;
  margin-left: 0%;
  top: 10px;
  width: 416px;
  font-size: 18px;
  color: #333;
  /* background-color: #315efb; */
}

.des-text2 {
  /* position: absolute;
  left: 0;
  width: 560px;
  font-size: 13px;
  color: #333; */
  margin-top: 20px;
}
}

</style>
