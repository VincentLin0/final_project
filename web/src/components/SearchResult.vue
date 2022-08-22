<template>
  <div class="Box">
    <div class="BreadcrumbNavgation">
      <BreadcrumbNavgation></BreadcrumbNavgation>
    </div>
    <div class="result-num">
      the following are {{ this.$route.query.searchContent }} relevant results
    </div>
    {{ searchContent }}

    <ul class="results">
      <li
        class="result-content"
        v-for="searchResult in searchResults"
        :key="searchResult.id"
      >
        <router-link class="Rtitle" :to="'/knowledge/' + searchResult.id">{{
          searchResult.title
        }}</router-link>

        <!-- 如果返回的图片地址不为空，有图片盒子，文字盒子样式为des-text1 -->
        <div class="description" v-if="searchResult.pic1 !== null">
          <div class="image">
            <!-- 给img的src绑定数据要用v-bind -->
            <img :src="searchResult.pic1" />
          </div>
          <div class="des-text0">
            {{ searchResult.description }}
          </div>
        </div>
        <!-- 如果返回的图片地址为空，没有图片盒子，文字盒子样式为des-text2 -->
        <div class="description" v-else>
          <div class="des-text2">
            {{ searchResult.description }}
          </div>
        </div>
        <!-- <el-button @click="collect(searchResult.id)" :type="searchResult.isCollect?'primary':'none'"
                   icon="el-icon-star-off" circle> </el-button> -->
        <!-- {{ searchResult.isCollect ? "cancel collect" : "collect" }} -->
      </li>
    </ul>
    <!-- <div><button>上一页</button>
    <button>下一页</button>
    </div> -->

    <div class="center">
      <CustomPages
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :currentPage="pageInfo.pageNum"
        :total="pageInfo.total"
        :pageSize="pageInfo.pageSize"
      ></CustomPages>
    </div>
  </div>
</template>

<script>
import request from "../utils/request";
import BreadcrumbNavgation from "../components/breadcrumbNavigation.vue";
import {Loading} from "element-ui";

import create from "@/mixins/create";

export default {
  mixins: [create],
  name: "searchResult",
  data() {
    return {
      searchContent: "",
      searchResults: [],
      api: {
        listUrl: "/page",
        saveUrl: "/save",
        delUrl: "/delBatch",
      },
    };
  },
  components: {
    BreadcrumbNavgation,
    // AccountBox
  },
  methods: {
    search() {
      request
        .post("/result", {
          // 把 search 页面的 input 内容映射到 searchContent 变量中并发送给后端
          searchContent: this.$route.query.searchContent,
          pageNum: this.pageInfo.pageNum,
          pageSize: this.pageInfo.pageSize,
        })
        // .then意思是指定回调函数
        .then((successResponse) => {
          // successResponse.data[0].title：就是符合条件的搜索结果的标题。其他以此类推
          // console.log(successResponse.data[0].title)
          console.log("a", successResponse); // 数组，所有符合条件的结果数组
          // console.log(successResponse.data[0])  // 数组第一个，里面包含id title abstracts
          let list = successResponse.data.records;
          this.pageInfo.total = successResponse.data.total;
          this.searchResults = list;
          console.log("jieguo", this.searchResults);
          if (successResponse.length === 0) {
            this.open3();
          }
        });
    },

    open3() {
      this.$message({
        message: "Sorry, this keyword was not found.",
        type: "warning",
      });
    },
  },
  created() {
    let loadingInstance = Loading.service({fullscreen: true});
    this.search();
    loadingInstance.close();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.center {
  display: flex;
  justify-content: center;
}
.box {
  /* position: relative; */
  /* width: 1088px; */
  /* height: 1440px; */

  margin-left: 150px;
}

.BreadcrumbNavgation {
  margin-top: 20px;
}

.results {
  margin-top: 20px;
  width: 60%;
  margin-left: 15%;
  list-style: none;
  min-height: 500px;
}

.result-num {
  margin-top: 20px;
  margin-left: 15%;
  width: 538px;
  line-height: 30px;
  font-size: 13px;
  color: #999999;
}
/* .image{
  width: 100%;

} */
.Rtitle {
  display: block;
  width: 55%;
  height: 22px;
  margin-bottom: 4px;
  text-decoration: none;
  color: #2440b3;
  font-weight: normal;
}

.results h3 a:hover {
  color: #315efb;
}

.result-content {
  margin-bottom: 20px;
}

.description {
  /* position: relative;
  top: 6px;
  width: 560px;
  height: 85px; */
}

.description img {
  width: 60%;
  height: 90px;
  margin-top: 20px;
  margin-left: 0%;
  border: 1px solid #f2f2f2;
  border-radius: 15px;
}

.des-text0 {
  position: relative;
  margin-left: 70%;
  top: -130px;
  width: 416px;
  height: auto;
  font-size: 18px;
  color: #333;
  word-wrap: break-word;
  overflow:hidden;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 5;
  /* word-wrap: break-word; */
}

.des-text2 {
  /* position: absolute;
  left: 0;
  width: 560px;
  font-size: 13px;
  color: #333; */
  /* width: 80%; */
  margin-top: 20px;
  word-wrap: break-word;
  overflow:hidden;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 5;
}

@media screen and (min-width: 900px) and (max-width: 1440px) {
  .results {
    margin-top: 20px;
    width: 60%;
    margin-left: 7%;
    list-style: none;
  }

  .result-num {
    margin-top: 20px;
    margin-left: 7%;
    width: 538px;
    line-height: 30px;
    font-size: 13px;
    color: #999999;
  }

  .description {
    margin-left: 0%;
  }

  .description img {
    width: 60%;
    margin-top: 20px;
    margin-left: 0%;
    border: 1px solid #f2f2f2;
    border-radius: 15px;
  }

  .des-text0 {
    position: relative;
    margin-left: 70%;
    top: -130px;
    width: 416px;
    font-size: 18px;
    color: #333;
    word-wrap: break-word;
    /* background-color: #315efb; */
  }
}

@media screen and (min-width: 300px) and (max-width: 720px) {
  .box {
    position: relative;
    width: 1088px;
    height: 1440px;
    margin-left: 150px;
  }

  .BreadcrumbNavgation {
    margin-top: 20px;
  }

  .results {
    margin-top: 20px;
    width: 60%;
    margin-left: 5%;
    list-style: none;
  }

  .result-num {
    margin-top: 20px;
    margin-left: 5%;
    width: 538px;
    line-height: 30px;
    font-size: 13px;
    color: #999999;
  }

  .Rtitle {
    display: block;
    width: 100%;
    height: 22px;
    margin-bottom: 4px;
    text-decoration: none;
    color: #2440b3;
    font-weight: normal;
  }

  .results h3 a:hover {
    color: #315efb;
  }

  .result-content {
    margin-bottom: 20px;
  }

  .description {
    /* position: relative;
    top: 6px;
    width: 560px;
    height: 85px; */
    /* background-color: aqua; */
  }

  .description img {
    width: 60%;
    margin-top: 20px;
    margin-left: 0%;
    border: 1px solid #f2f2f2;
    border-radius: 15px;
    display: none;
  }

  .des-text0 {
    position: relative;
    margin-left: 0%;
    top: 10px;
    width: 416px;
    font-size: 18px;
    color: #333;
    width: 90%;
    word-wrap: break-word;
      overflow:hidden;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 3;
    /* margin-top: 0px; */
    /* background-color: #315efb; */
  }

  .des-text2 {
    /* position: absolute;
    left: 0;
    width: 560px;
    font-size: 13px;
    color: #333; */
    margin-top: 20px;
    overflow:hidden;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 3;
  }
}

@media screen and (min-width: 720px) and (max-width: 900px) {
  .box {
    position: relative;
    width: 1088px;
    height: 1440px;

    margin-left: 150px;
  }

  .BreadcrumbNavgation {
    margin-top: 20px;
  }

  .results {
    margin-top: 20px;
    width: 60%;
    margin-left: 15%;
    list-style: none;
  }

  .result-num {
    margin-top: 20px;
    margin-left: 15%;
    width: 538px;
    line-height: 30px;
    font-size: 13px;
    color: #999999;
  }

  .Rtitle {
    display: block;
    width: 55%;
    height: 22px;
    margin-bottom: 4px;
    text-decoration: none;
    color: #2440b3;
    font-weight: normal;
  }

  .results h3 a:hover {
    color: #315efb;
  }

  .result-content {
    margin-bottom: 20px;
  }

  .description {
    /* position: relative;
    top: 6px;
    width: 560px;
    height: 85px; */
    /* background-color: aqua; */
  }

  .description img {
    width: 60%;
    margin-top: 20px;
    margin-left: 0%;
    border: 1px solid #f2f2f2;
    border-radius: 15px;
    display: none;
  }

  .des-text0 {
    position: relative;
    margin-left: 0%;
    top: 10px;
    width: 416px;
    font-size: 18px;
    color: #333;
    word-wrap: break-word;
    /* background-color: #315efb; */
  }

  .des-text2 {
    /* position: absolute;
    left: 0;
    width: 560px;
    font-size: 13px;
    color: #333; */
    margin-top: 20px;
    word-wrap: break-word;
  }
}
</style>
