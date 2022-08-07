<template>
  <div id="app">

  <HeadBar></HeadBar>
  <SelfTest class="mainTest"></SelfTest>
  <BottomBar></BottomBar>


  </div>
</template>

<script>
// import {HeadBar,BottomBar,SlideShow,NewBox,AccountBox} from '../components/index.js'
import HeadBar from '../components/HeadBar.vue'
import BottomBar from '../components/BottomBar.vue'
import SelfTest from '../components/SeftTest.vue'
// import AccountBox from '../components/AccountBox.vue'

export default {
  name: 'App',
  components: {
    HeadBar,
    BottomBar,
    SelfTest,
    // AccountBox
  }
}
</script>

<style>
#app {

}
.mainTest{
  height: 420px;
}
.bottom{
  width: 100%;
    position: absolute;
    left: 0px;
    bottom: 0px;
  }
</style>
