<template>
  <div class="box">
    <div class="Content">
      <div class="text_content">

        <img v-if="dataInfo.title !=null" id="star" src="../assets/img/star.png" alt="Star">
        <h2 v-if="dataInfo.title !=null" id="title"> {{ dataInfo.title }}</h2>
        <div class="collection" v-if="dataInfo.isCollect != null">
          <el-button @click="collect(dataInfo.id)" :type="dataInfo.isCollect?'primary':'none'" icon="el-icon-star-off" circle> </el-button>
       </div>
        <p v-if="dataInfo.description!=null" id="text"> {{ dataInfo.description }}</p>
      </div>
      <hr class="line">
      <div id="Audio_content">
        <img id="star" src="../assets/img/audio.png" alt="Star">
        <h2 id="title"> Audio</h2>
        <video v-if="dataInfo.video !=null" id="audio" width="100%" height="80%" controls>
          <source :src="dataInfo.video" type="video/mp4">
        </video>
        <div class="carousel">
          <div v-if="dataInfo.pic1!=null">
          <template>
            <el-carousel indicator-position="outside">
              <el-carousel-item v-if="dataInfo.pic1!=null">
                <img class="pic1" :src="dataInfo.pic1" alt="cat1"
                     style="width:100%;height:100%;display:block;margin-left: 0px;">
              </el-carousel-item>
              <el-carousel-item v-if="dataInfo.pic2!=null">
                <img class="pic1" :src="dataInfo.pic2" alt="cat1"
                     style="width:100%;height:100%;display:block;margin-left: 0px;">
              </el-carousel-item>
              <el-carousel-item v-if="dataInfo.pic3!=null">
                <img class="pic1" :src="dataInfo.pic3" alt="cat1"
                     style="width:100%;height:100%;display:block;margin-left: 0px;">
              </el-carousel-item>

            </el-carousel>
          </template>
          </div>
        </div>
        <aplayer v-if="isAuduiShow" class="audio-player" :music="audioPlayerData"></aplayer>
      </div>

    </div>

  </div>
</template>

<script>
import Aplayer from 'vue-aplayer'
import request from '../utils/request'

export default {
  name: 'PageContent',
  components: {
    Aplayer
  },
  data() {
    return {
      // searchContent:"",
      content: [],
      dataInfo: {
        audio: "",
      },
      isAuduiShow: false,
      audioPlayerData: {}

    }
  },
  methods: {
    collect(e) {
      request.post("/collect", {
        knowledgeId: e
      })
          .then((successResponse) => {
            if (successResponse.code === "200") {
              this.$message.success("Add collection successfully")
              this.searchContent()
            }
          })
      //collect or not collect
    },
    searchContent() {
      request.get('/queryById?id=' + this.$route.params.id)
          .then((successResponse) => {
            this.dataInfo = successResponse.data
            this.audioPlayerData = {
              title: 'default',
              artist: 'default',
              src: successResponse.data.audio,
              pic: 'http://176.58.99.74:8888/view?relativePath=2022/08/14/7ad3f443-21fd-4fe1-8563-97d90d2a593b.svg'
            }
            setTimeout(()=>{
              this.isAuduiShow = true
            },0)
          })

    }
  },
  created() {
    this.searchContent()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.text_content{
  margin-top: 20px;
}
.box {
  background-color: white;
}

.Content {
  margin-left: 6%;

}
.collection{
  display: inline-block;
position: relative;
left: 30px;
bottom: 10px;
}
#star {
  width: 1.3em;
  margin-bottom: 5px;
  display: inline-block;
}

#title {
  margin-left: 1.5em;
  position: relative;
  top: -0.25em;
  display: inline-block;
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
  margin-left: 10%;
}

.time {
  margin-left: 10%;
  margin-top: -18px;
  color: rgba(150, 150, 150);
}

.pic1 {
  width: 29%;
  height: 100%;
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

.totalpic {
  width: 80%
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

.carousel {
  width: 60%;
}
.audio-player{
  width: 60%;
}

</style>
