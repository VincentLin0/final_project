<template>
  <div id="app">
    <HeadBar></HeadBar>
    <div class="content"><ContentBox></ContentBox></div>

    <BottomBar class="bottom"></BottomBar>
  </div>
</template>

<script>
// import {HeadBar,BottomBar,SlideShow,NewBox,AccountBox} from '../components/index.js'
import HeadBar from "../components/HeadBar.vue";
import BottomBar from "../components/BottomBar.vue";
import ContentBox from "../components/ContentBox.vue";
// import AccountBox from '../components/AccountBox.vue'

export default {
  name: "App",
  components: {
    HeadBar,

    BottomBar,
    ContentBox,
    // AccountBox
  },
};
</script>

<style>
#app {
}
.content {
  min-height: 500px;
}
.bottom {
  position: absolute;
  left: 0;
  bottom: 0;
}
</style>
