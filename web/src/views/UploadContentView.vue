<template lang="">
  <div class = "box">
    <HeadBar></HeadBar>
    <div>
    <!-- <HeadEle></HeadEle> -->
		<!-- <UpdateContent></UpdateContent> -->
    mp3
		<UpdateButtonMP3></UpdateButtonMP3>
    mp4
		<UpdateButtonMP4></UpdateButtonMP4>
    pic
		<UpdateButtonPic></UpdateButtonPic>
    </div>
    <BottomBar></BottomBar>
  </div>
</template>
<script>
import HeadBar from '../components/HeadBar.vue'
// import UpdateContent from '../components/UpdateContent.vue'
import BottomBar from '../components/BottomBar.vue'
import UpdateButtonPic from '../components/UpdateButtonPic.vue'
import UpdateButtonMP3 from '../components/UpdateButtonMP3.vue'
import UpdateButtonMP4 from '../components/UpdateButtonMP4.vue'
// import HeadEle from '../components/HeadEle.vue'
// import request from '../utils/request'
export default {
    name: 'App',
  components: {
   HeadBar,
   BottomBar,
	// UpdateContent,
	UpdateButtonPic,
	UpdateButtonMP3,
	UpdateButtonMP4,
  // HeadEle
  }
}
</script>
 
<style>


</style>

