import Vue from "vue";
import Router from "vue-router";
import routerList from "./modules/index";

// 读取 module 文件夹的路由js

Vue.use(Router);
/**
 * 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题(主要解决在同一页面点击面包屑进行重定向返回)
 */
const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err);
};

/**
 * 基础路由页面
 * 不需要验证权限
 * 所有角色都可以访问
 */
export const constantRoutes = [
    {
        path: "/404",
        component: () => import("@/views/ErrorPage/404"),
        hidden: true,
    },
    {
        path: "/401",
        component: () => import("@/views/ErrorPage/401"),
        hidden: true,
    },
];

/**
 * 根据后台返回的权限列表
 * 异步加载的的路由
 */
export const asyncRoutes = routerList;

// 生成路由实例
const createRouter = () => {
    const r = new Router({
        mode: "hash",
        scrollBehavior: () => ({ y: 0 }),
        routes: constantRoutes,
    });
    r.afterEach(() => {
        let loadings = document.querySelectorAll(".el-loading-mask");
        if (loadings) {
            for (let i = loadings.length - 1; i >= 0; i--) {
                loadings[i].parentNode.removeChild(loadings[i]);
            }
        }
    });
    return r;
};
const router = createRouter();

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
    const newRouter = createRouter();
    router.matcher = newRouter.matcher; // reset router
}

export default router;
