<template>
	<div class="slideshow">
			<div>
            <img src="../assets/img/dog1.png" alt="cat" class="main_pic">
        </div>
            <div class="space"></div>
            <div class="description">
                <h3 class="introduction">Vet Auscultation Library (VAL) is an open-access, online tool where veterinary professionals and students can refine their auscultation techniques. Built by a team of veterinary specialists in cardiology, VAL aims to provide a wide variety of normal and abnormal heart sounds for reference, in addition to a self-test quiz of heart sounds with multiple-choice questions and feedback.  
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
                <span @click="tutorial" class="tt">Tutorial</span>
            </div>

        </div>
            <div class="vision">
                <p class="p1">Our Vision</p>
                <div class="double_quotation1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"</div>
                <div class="p2">
                    Auscultation skills are a vital part of the routine physical examination of companion animals.
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
      tutorial(){
        this.$router.push('/tutorial');
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
