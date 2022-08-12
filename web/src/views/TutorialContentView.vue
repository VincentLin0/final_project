<template lang="">
  <div class = "box">

      <HeadBar></HeadBar>
        <div class="mainContent">
          <TutorialContent></TutorialContent>
        </div>
      <BottomBar></BottomBar>

  </div>
</template>
<script>
import HeadBar from '../components/HeadBar.vue'
import BottomBar from '../components/BottomBar.vue'
import TutorialContent from '../components/TutorialContent.vue'
export default {
    name: 'App',
  components: {
   HeadBar,
   BottomBar,
	TutorialContent
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

