<template>
  <div class="Bottom">
    <img  src="../assets/img/logo1.png"  alt="cannot  show"  class="logo2">
  </div>
</template>

<script>
export default {
  name: 'bottom-bar',
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
					.Bottom{
            /* width: 1344px;  */
				position: relative;
				top: 4em;
        height: 8em;
        /* width: content; */
				background-color:rgb(80,130,241)
        }


        .logo2  {
            width: 4em;
            height: 4em;
            line-height: 109px;
            position: relative;
            left: 4%;
				top: 2em;
				background-color:rgb(90,130,241)

        }
</style>
