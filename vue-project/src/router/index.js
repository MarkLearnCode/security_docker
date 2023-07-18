import { createRouter, createWebHistory } from "vue-router";
import CountBtn from "../components/countBtn.vue";
import Home from "../views/home.vue";

import PageA from "../views/pageA.vue";
import PageB from "../views/pageB.vue";
import PageC from "../views/pageC.vue";
import PageD from "../views/pageD.vue";
import Login from "../views/login.vue";
import Sign from "../views/sign.vue";
import Oauth from "../views/oauth.vue";
import Profile from "../views/profile.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/oauth",
      name: "oauth",
      component: Oauth,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/sign",
      name: "sign",
      component: Sign,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/profile",
      name: "profile",
      component: Profile,
      meta: {
        requiresAuth: true,
        requiredRoles: ["GUEST", "VIP", "MANERGE", "ADMIN"],
      },
    },
    {
      path: "/pageA",
      name: "pageA",
      component: PageA,
      meta: {
        requiresAuth: true,
        requiredRoles: ["GUEST", "VIP", "MANERGE", "ADMIN"],
      },
    },
    {
      path: "/pageB",
      name: "pageB",
      component: PageB,
      meta: {
        requiresAuth: true,
        requiredRoles: ["VIP", "MANERGE", "ADMIN"],
      },
    },
    {
      path: "/pageC",
      name: "pageC",
      component: PageC,
      meta: {
        requiresAuth: true,
        requiredRoles: ["MANERGE", "ADMIN"],
      },
    },
    {
      path: "/pageD",
      name: "pageD",
      component: PageD,
      meta: {
        requiresAuth: true,
        requiredRoles: ["ADMIN"],
      },
    },
  ],
});

router.beforeEach((to, from, next) => {
  // const isAuthenticated = checkUserAuth();
  const userRole = localStorage.getItem("role");
  console.log(userRole);

  if (to.meta.requiresAuth && !userRole) {
    // 需要权限但用户未登录，则重定向到登录页或其他提示页
    next("/login");
  } else if (
    to.meta.requiredRoles &&
    !to.meta.requiredRoles.includes(userRole)
  ) {
    // 用户权限不满足要求，则重定向到无权限页面或其他提示页
    next("/");
  } else {
    // 其他情况允许继续访问
    next();
  }
});

export default router;
