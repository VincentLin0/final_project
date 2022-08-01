<template>
	<div class="box">
		<div class="Content">
			<div id="text_content">
			

				<img v-if="dataInfo.title !=null"  id="star" src="../assets/img/star.png" alt="Star">
				<h2 v-if="dataInfo.title !=null" id="title"> {{dataInfo.title}}</h2>
				<p v-if="dataInfo.description!=null"  id="text"> {{dataInfo.description}}</p>
			</div>
			<hr class="line">
			<div id="Audio_content">
				<img id="star" src="../assets/img/audio.png" alt="Star">
				<h2 id="title"> Audio</h2>
				<video v-if="dataInfo.video !=null" id="audio" width="100%" height="80%" controls>
					<source :src="dataInfo.video" type="video/mp4">
				</video>
				<div class="carousel">
				<template >
						<el-carousel indicator-position="outside">
						<el-carousel-item >
							<img class="pic1" :src="dataInfo.pic1" alt="cat1" style="width:100%;height:100%;display:block;margin-left: 0px;">
						</el-carousel-item>
						<el-carousel-item >
							<img class="pic1" :src="dataInfo.pic2" alt="cat1" style="width:100%;height:100%;display:block;margin-left: 0px;">
						</el-carousel-item>
						<el-carousel-item v-if="dataInfo.pic3" !=null>
							<img class="pic1" :src="dataInfo.pic3" alt="cat1" style="width:100%;height:100%;display:block;margin-left: 0px;">
						</el-carousel-item>
						</el-carousel>
				</template>
				</div>
				<audio controls>
					<source :src="dataInfo.audio" type="audio/wav">
				</audio>
			</div>
			<!-- <hr class="line"> -->
			<!-- <div id="comments">
				<div id=scomments>
					<img id="star" src="../assets/img/talk.png" alt="Star">
					<h2 id="title"> Comments </h2>
					<img id="head" src="../assets/img/head.png" alt="Star">
					<h3 class="name">Alabama</h3>
					<p class="time">2022-06-25 14:00</p>
					<p class="comments_text">We are the leading multidisciplinary provider of veterinary s-
						ervices </p>
					<div class="totalpic">
						<img class="pic1" src="../assets/img/cat1.png" alt="cat1">
						<img class="pic1" src="../assets/img/cat2.png" alt="cat2">
						<img class="pic1" src="../assets/img/cat3.png" alt="cat3">
					</div>

				</div>

								<div id=scomments>
					<img id="head" src="../assets/img/head.png" alt="Star">
					<h3 class="name">Alabama</h3>
					<p class="time">2022-06-25 14:00</p>
					<p class="comments_text">We are the leading multidisciplinary provider of veterinary s-
						ervices </p>
					<div class="totalpic">
						<img class="pic1" src="../assets/img/cat1.png" alt="cat1">
						<img class="pic1" src="../assets/img/cat2.png" alt="cat2">
						<img class="pic1" src="../assets/img/cat3.png" alt="cat3">
					</div>

				</div>

			</div> -->

		</div>

	</div>
</template>

<script>

import request from '../utils/request'

export default {
	name: 'PageContent',
	data () {
    return {
		// searchContent:"",
      content: [],
		dataInfo:{
		},

    }
  },
	methods: {
      searchContent(){
        request.post('/content', {
            // 把 search 页面的 input 内容映射到 searchContent 变量中并发送给后端
            searchContent: this.$route.params.id// 通过 v-model 把 input 框输入的内容存进 searchContent 中
          })
          // .then意思是指定回调函数
          .then((successResponse) => {
            // successResponse.data[0].title：就是符合条件的搜索结果的标题。其他以此类推
            // console.log(successResponse.data[0].title)
            console.log(successResponse) // 数组，所有符合条件的结果数组
            // console.log(successResponse.data[0])  // 数组第一个，里面包含id title abstracts
            let list = successResponse
            this.content = list
				this.dataInfo = list[0]
            console.log(this.dataInfo.audio)
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
      this.searchContent() 
      }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.box{
	background-color: white;
}
.Content {
	margin-left: 6%;
}

#star {
	float: left;
	width: 1.3em;
}

#title {
	margin-left: 1.5em;
	position: relative;
	top: -0.25em;
}

#text {
	margin-right: 5%;
	margin-top: -1em;
	margin-right: 5%;
	width: 90%;
}

.line {
	margin-right: 5%;
}

#Audio_content {
	margin-right: 5%;
}

#head {
	float: left;
	width: 8%;
}

#comments {
	margin-right: 5%;
}

.name {
	margin-left:10%;
}

.time {
	margin-left: 10%;
	margin-top: -18px;
	color: rgba(150, 150, 150);
}

.pic1 {
	width: 29%;
	margin-left: 2%;
}



.comments_text {
	margin-right: 5%;
	/* position: relative;
	top: 100px; */
	margin-top: 4.6%;
}

.sidebar {
	float: right;

}

.totalpic{
	width:80%
}
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
  .carousel{
	width: 60%;
  }

</style>
