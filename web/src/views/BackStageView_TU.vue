<template>
  <div id="app">
    <HeadBar></HeadBar>
    <BackStage_TU></BackStage_TU>
    <BottomBar class="bottom"></BottomBar>
  </div>
</template>

<script>
import HeadBar from '../components/HeadBar.vue'
import BottomBar from '../components/BottomBar.vue'
import BackStage_TU from '../components/BackStage_TU.vue'

export default {
  name: 'App',
  components: {
    HeadBar,

    BottomBar,
    BackStage_TU,
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
