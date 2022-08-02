<template lang="">
  <div class = "box">
    <HeadBar></HeadBar>
	<searchResult class="body"></searchResult>
    <BottomBar></BottomBar>
  </div>
</template>
<script>
import HeadBar from '../components/HeadBar.vue'
import BottomBar from '../components/BottomBar.vue'
import SearchResult from '../components/SearchResult.vue'
export default {
    name: 'App',
  components: {
   HeadBar,
   BottomBar,
	SearchResult
  },

  methods:{
	content(){
      this.$router.push('/content');
    },
}
}
</script>
 
<style>
.body{
  height: 720px;
}
</style>

