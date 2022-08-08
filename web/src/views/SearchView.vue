<template lang="">
  <div class = "box">

<HeadBar></HeadBar>

    <div class="search">		
				<select class="d6">		
					<option value="">Listes</option>
					<option value="2">Listes1</option>
					<option value="3">Listes2</option>
				</select>
				<input type="search" v-model="searchContent" placeholder="Type in something.">	
				<div @click="search" class="sea"></div>
				<!-- <router-link class="search_box" :to="'/result/' + searchContent" ><div  class="sea"></div></router-link> -->
			</div>
			<div class="list">			
				<p class="title1">Lists of Animals</p>
				<hr>	
				<br>	
				<div class="listleft">		
					<li class="listtitle">Class1</li>
					<li @click= "content" class="listfont">animal1</li>
					<li class="listfont">animal2</li>
					<li class="listfont">animal3</li>
					<br>	
					<li class="listtitle">Class3</li>
					<li class="listfont">animal1</li>
					<li class="listfont">animal2</li>
					<li class="listfont">animal3</li>
				</div>
				<div class="listright">		
					<li class="listtitle">Class2</li>
					<li class="listfont">animal1</li>
					<li class="listfont">animal2</li>
					<li class="listfont">animal3</li>
					<br>
					<li class="listtitle">Class4</li>
					<li class="listfont">animal1</li>
					<li class="listfont">animal2</li>
					<li class="listfont">animal3</li>
				</div>
				<hr>
			</div>
			<div class="latest">		
				<p class="title1">Latest Updates</p>
				<hr><br>
				<h3 class="lattitle">Title</h3>
				<p>The content.The content.The content.</p>
				<p>The content.</p>
				<p class="lattime">2022/04/11 | 50 Comments</p>
				<br><br>
				<h3 class="lattitle">Title</h3>
				<p>The content.The content.The content.</p>
				<p>The content.</p>
				<p class="lattime">2022/04/11 | 50 Comments</p>
				<br><hr>
			</div>

<BottomBar></BottomBar>

  </div>
</template>
<script>
import HeadBar from '../components/HeadBar.vue'
import BottomBar from '../components/BottomBar.vue'



export default {
    name: 'App',
  components: {
   HeadBar,
   BottomBar,
	
  },
  data () {
    return {
        searchContent: ''
    }
  },

  methods:{
	content(){
      this.$router.push('/content');
    },
	search(){
		this.$router.push({
                path:'/result/' + this.searchContent,
                query:{searchContent:this.searchContent}
            })
	}
}
}
</script>
 
<style>
.search_box{
	height: 0;
}
.search{	
				margin: 0 auto;
				width: 63.2%;
				height: 6em;
				position: relative;
				top:5em;
				/* background-color: aqua; */
			}
			input{
				width: 77.7%;
				height: 50px;
				border: 0.1em solid #000000;  
				padding:0 0 0 40px;
			}
			.d6{
				float: left;
				width: 16.6%;
				height:3.75em;
				border: 0.1em solid #000000;  
			}
			.sea{
				width:2.96em;	
				height:2.96em;
				border: 0.1em solid #7a7a7a;
				/* float: right;  */
				
				position: relative;
				left :94.3%;
				top: -52%;
				/* margin-top: -5.5%; */
				background-image: url(../assets/img/sea.png);	
				background-repeat: no-repeat;
				cursor: pointer;	
				
			}
			.list{
				height: 400px;
				width: 63.2%;
				margin: 0 auto;
				position: relative;
				top:5em;
			}
			.list li{
				list-style-type: none;
				/* background-color: aqua; */
			}
			.listtitle{
				font-size: 14px;
				color: black;
				font-weight: bolder;
			}
			.listfont{
				cursor:pointer;
			}
			.listleft{
				float: left;
				width: 50px;
				margin-right: 10px;
				/* background-color: aqua; */
			}
			.listright{
				height: 253px;
				padding-top: 1px;
				margin-left: 50%;
				/* position: relative; */
				/* left:40%; */
			}
			.title1{
				color: rgb(0, 0, 0);
				font-size: 16px;
			}
			.lattitle{
				color: black;
				
			}
			.latest {
				margin: 0 auto;
				width: 63.2%;
				position: relative;
				top:5em;
				
			}
			.latest p{
				line-height: 1em;

			}
			.lattime{
				color: gainsboro;
			}
			
</style>

