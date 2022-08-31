/* eslint-disable one-var */
import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import SearchView from "../views/SearchView.vue";
import ContentView from "../views/ContentView.vue";
import SearchResult from "../views/SearchResult.vue";
import LogOnView from "../views/LogOnView";
import UploadContentView from "../views/UploadContentView.vue";
import SeftTestView from "../views/SeftTestView.vue";
import BackStageView_CM from "../views/BackStageView_CM.vue";
import BackStageView_AC from "../views/BackStageView_AC.vue";
import BackStageView_TM from "../views/BackStageView_TM.vue";
import BackStageView_AQ from "../views/BackStageView_AQ.vue";
import BackStageView_TU from "../views/BackStageView_TU.vue";
import MyCollectionView from "../views/CollectionView.vue";
import TutorialView from "../views/TutorialView.vue";
import TutorialContent from "../views/TutorialContentView.vue";

Vue.use(VueRouter);

const RouterPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(to) {
  return RouterPush.call(this, to).catch((err) => err);
};
const RouterReplace = VueRouter.prototype.replace;
VueRouter.prototype.replace = function replace(to) {
  return RouterReplace.call(this, to).catch((err) => err);
};

// eslint-disable-next-line one-var
const routes = [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
    },
    {
      path: "/search",
      name: "search",
      component: SearchView,
    },
    {
      path: "/knowledge/:id",
      name: "knowledge",
      component: ContentView,
    },
    {
      path: "/tutorial/:id",
      name: "tutorialContent",
      component: TutorialContent,
    },
    {
      path: "/result/",
      name: "result",
      component: SearchResult,
    },
    {
      path: "/collection/",
      name: "collection",
      component: MyCollectionView,
    },
    {
      path: "/tutorial/",
      name: "tutorial",
      component: TutorialView,
    },
    {
      path: "/signup",
      name: "singnup",
      component: LogOnView,
    },
    {
      path: "/uploadContent",
      name: "UploadContent",
      component: UploadContentView,
    },

    {
      path: "/test",
      name: "SeftTest",
      component: SeftTestView,
    },
    {
      path: "/backStage",
      name: "BackStage",
      component: BackStageView_CM,
    },
    {
      path: "/backStage/upload",
      name: "BackStage",
      component: BackStageView_AC,
    },
    {
      path: "/backStage/testManagement",
      name: "testManagement",
      component: BackStageView_TM,
    },
    {
      path: "/backStage/testManagement/uploadTest",
      name: "testManagement",
      component: BackStageView_AQ,
    },
    {
      path: "/backStage/TutorialManagement",
      name: "BackStageTutorial",
      component: BackStageView_TU,
    },
    // {
    //   path: '/login',
    //   name: 'login',
    //   // route level code-splitting
    //   // this generates a separate chunk (about.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    // }
  ],
  router = new VueRouter({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes,
  });

router.beforeEach((to, from, next) => {
  if (
    to.path === "/login" ||
    to.path === "/signup" ||
    to.path === "/" ||
    to.path === "/search" ||
    to.path === "/result/" ||
    to.name == "knowledge"
  ) {
    next();
  } else {
    let token = localStorage.getItem("token");
    if (token === null || token === "") {
      next("/login");
    } else {
      next();
    }
  }
});

export default router;
