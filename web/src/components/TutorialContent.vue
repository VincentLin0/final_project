<template>
  <div class="box">
    <div class="Content">
      <div id="text_content">
        <!-- <div class="center"><h2 v-if="dataInfo.title" class="title"> {{ dataInfo.title }}</h2></div> -->
        <div class="center"><p class="page" v-html="dataInfo.description"></p></div>
      </div>

      </div>
  </div>
</template>

<script>
import request from '../utils/request'

export default {
  name: 'PageContent',
  components: {
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

    searchContent() {
      request.get('/tutorial/queryById?id=' + this.$route.params.id)
          .then((successResponse) => {
            this.dataInfo = successResponse.data
            this.audioPlayerData = {
              title: 'default',
              artist: 'default',
              src: successResponse.data.audio,
              pic: ''
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
.center{
  display: flex;
  justify-content: center;
}
.page{
  width: 70%;
}
.box {
  /* background-color: white; */
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
.title {

  position: relative;
  top: -0.25em;
  display: inline-block;
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
