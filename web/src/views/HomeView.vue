<template>
  <div id="app">
    <HeadBar></HeadBar>
    <div class="mainContent">
    <SlideShow></SlideShow>
    <NewBox></NewBox>
    </div>
    <div class="blank"></div>
    <BottomBar></BottomBar>
    
   
  </div>
</template>

<script>
// import {HeadBar,BottomBar,SlideShow,NewBox,AccountBox} from '../components/index.js'
import HeadBar from '../components/HeadBar.vue'
import BottomBar from '../components/BottomBar.vue'
import SlideShow from '../components/SlideShow.vue'
import NewBox from '../components/NewBox.vue'
// import AccountBox from '../components/AccountBox.vue'

export default {
  name: 'App',
  components: {
    HeadBar,
    BottomBar,
    SlideShow,
    NewBox,
    // AccountBox
  }
}
</script>

<style>
#app {

}
.mainContent{
  min-height: 880px;
}
.blank{
  height: 250px;
}
</style>
