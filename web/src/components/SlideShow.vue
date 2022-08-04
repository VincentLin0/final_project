<template>
	<div class="slideshow">
			<div>
            <img src="../assets/img/cat.svg" alt="cat" class="main_pic">
        </div>
            <div class="space"></div>
            <div class="description">
                <h3 class="introduction">Langford Vets is a group of customer focused veterinary businesses providing an
                    extensive range of Specialist capabilities and a superior quality of care.
                    We have the largest team of American and European Specialists in the South West of England and are
                    members of the Royal College of Veterinary Surgeons Practice Standards Scheme.
                     </h3>
            </div>
        <div class="selectpic">
            <div >
                <img src="../assets/img/search library.svg" alt="" class="searchlib">
                <img src="../assets/img/self-test.svg" alt="" class="selftest">
                <img src="../assets/img/tutorial2.svg" alt="" class="tutorial">
            </div>
            <div class="selections">
                <span @click="search" class="sl">Search</span>
                <span @click="self_test" class="st">Self-test</span>
                <span href="#" class="tt">Tutorial</span>
            </div>

        </div>
            <div class="vision">
                <p class="p1">Our Vision</p>
                <div class="double_quotation1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"</div>
                <div class="p2">
                    We are the leading multidisciplinart provider of veterinary services in the South West. As part of
                    the
                    University of Bristol we provide a world class teaching enviornment to both undergraduate and
                    post-graduate students
                </div>
                <div class='clear'></div>
                <div class="double_quotation2">"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
                <div class ="space1"></div>
            </div>
        </div>
</template>

<script>
export default {
	name: 'slide-show',
    methods:{
        search(){
          this.$router.push('/search');
        },
        self_test(){
        this.$router.push('/test');
        }
    }
}
    
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import '../css/mainPage.css';
</style>
