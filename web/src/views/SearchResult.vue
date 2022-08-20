<!-- eslint-disable import/no-duplicates -->
<template lang="">
  <div class="box">
    <HeadBar></HeadBar>
    <div class="mainContent"><searchResult></searchResult></div>
    <BottomBar></BottomBar>
  </div>
</template>
<script>
import HeadBar from "../components/HeadBar.vue";
import BottomBar from "../components/BottomBar.vue";
import SearchResult from "../components/SearchResult.vue";
export default {
  name: "App",
  components: {
    HeadBar,
    BottomBar,
    SearchResult,
  },

  methods: {
    content() {
      this.$router.push("/content");
    },
  },
};
</script>

<style>
.mainContent {
  min-height: 880px;
}
.bottom {
  position: absolute;
  left: 0;
  bottom: 0;
}
</style>
