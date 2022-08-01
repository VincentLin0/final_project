<template lang="">
  <div class = "box">
    <HeadBar></HeadBar>
	<searchResult></searchResult>
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

</style>

