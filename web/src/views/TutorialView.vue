<template lang="">
  <div class = "box">

      <HeadBar></HeadBar>
        <div class="mainContent">
          <TutorialBox></TutorialBox>
        </div>
      <BottomBar></BottomBar>

  </div>
</template>
<script>
import HeadBar from '../components/HeadBar.vue'
import BottomBar from '../components/BottomBar.vue'
import TutorialBox from '../components/TutorialBox.vue'
export default {
    name: 'App',
  components: {
   HeadBar,
   BottomBar,
	TutorialBox
  },

  methods:{

}
}
</script>
 
<style>
.mainContent{
  min-height: 880px;
}
.bottom{
    position: absolute;
    left: 0;
    bottom: 0;
  }
</style>

