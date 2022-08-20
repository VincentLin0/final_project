<template>
  <div id="app">
    <HeadBar></HeadBar>
    <BackStage_CM></BackStage_CM>
    <BottomBar class="bottom"></BottomBar>
  </div>
</template>

<script>
// import {HeadBar,BottomBar,SlideShow,NewBox,AccountBox} from '../components/index.js'
import HeadBar from "../components/HeadBar.vue"
import BottomBar from "../components/BottomBar.vue"
import BackStage_CM from "../components/BackStage_CM.vue"
// import AccouƒntBox from '../components/AccountBox.vue'

export default {
  name: "App",
  components: {
    HeadBar,

    BottomBar,
    BackStage_CM,
    // AccountBox
  }
}
</script>

<style>
#app {
}
.bottom{
    position: absolute;
    left: 0;
    bottom: 0;
  }
</style>
