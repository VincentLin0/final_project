<template>
  <div id="app">
    <HeadBar></HeadBar>
    <BackStage_AC></BackStage_AC>
    <BottomBar class="bottom"></BottomBar>
  </div>
</template>

<script>
// import {HeadBar,BottomBar,SlideShow,NewBox,AccountBox} from '../components/index.js'
import HeadBar from '../components/HeadBar.vue'
import BottomBar from '../components/BottomBar.vue'
import BackStage_AC from '../components/BackStage_AC.vue'
// import AccountBox from '../components/AccountBox.vue'

export default {
  name: 'App',
  components: {
    HeadBar,

    BottomBar,
    BackStage_AC,
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
