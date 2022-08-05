<template>
  <div id="app">
    <HeadBar></HeadBar>
    <BackStage_AQ></BackStage_AQ>
    <BottomBar class="bottom"></BottomBar>
  </div>
</template>

<script>
// import {HeadBar,BottomBar,SlideShow,NewBox,AccountBox} from '../components/index.js'
import HeadBar from '../components/HeadBar.vue'
import BottomBar from '../components/BottomBar.vue'
import BackStage_AQ from '../components/BackStage_AQ.vue'
// import AccountBox from '../components/AccountBox.vue'

export default {
  name: 'App',
  components: {
    HeadBar,

    BottomBar,
    BackStage_AQ,
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
