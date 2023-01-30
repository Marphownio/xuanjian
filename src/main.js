import Vue from "vue";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import App from "./App";
import router from "./router";
import mainContainer from "@/components/container";
import VueParticles from 'vue-particles'
// 如果想要中文版 element-ui，按如下方式声明
Vue.use(ElementUI);
Vue.use(VueParticles)
Vue.component("mainContainer", mainContainer);

Vue.config.productionTip = false;

new Vue({
  el: "#app",
  router,
  render: (h) => h(App),
});
