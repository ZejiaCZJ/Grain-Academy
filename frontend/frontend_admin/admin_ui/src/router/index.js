import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },

  {
    path: '/teacher',
    component: Layout,
    redirect: '/teacher/list',
    name: 'Teacher',
    meta: { title: 'Teacher', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'list',
        name: 'Teacher List',
        component: () => import('@/views/edu/admin/teacher/list.vue'),
        meta: { title: 'Teacher List', icon: 'table' }
      },
      {
        path: 'add',
        name: 'Add New Teacher',
        component: () => import('@/views/edu/admin/teacher/save.vue'),
        meta: { title: 'Add New Teacher', icon: 'tree' }
      },
      {
        path: 'edit/:id',
        name: 'Edit Information',
        component: () => import('@/views/edu/admin/teacher/save.vue'),
        meta: {title: 'Edit Information', noCache: true},
        hidden: true
      } 
    ]
  },

  {
    path: '/subjects',
    component: Layout,
    redirect: '/subjects/list',
    name: 'Subjects',
    meta: { title: 'Subjects', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'list',
        name: 'Subjects List',
        component: () => import('@/views/edu/admin/subjects/list.vue'),
        meta: { title: 'Subjects List', icon: 'table' }
      },
      {
        path: 'add',
        name: 'Add New Subject',
        component: () => import('@/views/edu/admin/subjects/save.vue'),
        meta: { title: 'Add New Subject', icon: 'tree' }
      },
    ]
  },

  {
    path: '/courses',
    component: Layout,
    redirect: '/courses/list',
    name: 'Courses',
    meta: { title: 'Courses', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'list',
        name: 'Courses List',
        component: () => import('@/views/edu/admin/courses/list.vue'),
        meta: { title: 'Course List', icon: 'table' }
      },
      {
        path: 'info',
        name: 'Add a New Course',
        component: () => import('@/views/edu/admin/courses/info.vue'),
        meta: { title: 'Add New Course', icon: 'tree' }
      },
      {
        path: 'info/:id',
        name: 'Edit Course Information',
        component: () => import('@/views/edu/admin/courses/info.vue'),
        meta: {title: 'Edit Course Information', noCache: true},
        hidden: true
      } ,
      {
        path: 'chapter/:id',
        name: 'Edit Course Chapters',
        component: () => import('@/views/edu/admin/courses/chapter.vue'),
        meta: {title: 'Edit Course Chapters', noCache: true},
        hidden: true
      } ,
      {
        path: 'publish/:id',
        name: 'Edit Course Details',
        component: () => import('@/views/edu/admin/courses/publish.vue'),
        meta: {title: 'Edit Course Details', noCache: true},
        hidden: true
      } ,
    ]
  },

  {
    path: '/example',
    component: Layout,
    redirect: '/example/table',
    name: 'Example',
    meta: { title: 'Example', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'table',
        name: 'Table',
        component: () => import('@/views/table/index'),
        meta: { title: 'Table', icon: 'table' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: { title: 'Tree', icon: 'tree' }
      }
    ]
  },

  {
    path: '/form',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: { title: 'Form', icon: 'form' }
      }
    ]
  },

  {
    path: '/nested',
    component: Layout,
    redirect: '/nested/menu1',
    name: 'Nested',
    meta: {
      title: 'Nested',
      icon: 'nested'
    },
    children: [
      {
        path: 'menu1',
        component: () => import('@/views/nested/menu1/index'), // Parent router-view
        name: 'Menu1',
        meta: { title: 'Menu1' },
        children: [
          {
            path: 'menu1-1',
            component: () => import('@/views/nested/menu1/menu1-1'),
            name: 'Menu1-1',
            meta: { title: 'Menu1-1' }
          },
          {
            path: 'menu1-2',
            component: () => import('@/views/nested/menu1/menu1-2'),
            name: 'Menu1-2',
            meta: { title: 'Menu1-2' },
            children: [
              {
                path: 'menu1-2-1',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
                name: 'Menu1-2-1',
                meta: { title: 'Menu1-2-1' }
              },
              {
                path: 'menu1-2-2',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
                name: 'Menu1-2-2',
                meta: { title: 'Menu1-2-2' }
              }
            ]
          },
          {
            path: 'menu1-3',
            component: () => import('@/views/nested/menu1/menu1-3'),
            name: 'Menu1-3',
            meta: { title: 'Menu1-3' }
          }
        ]
      },
      {
        path: 'menu2',
        component: () => import('@/views/nested/menu2/index'),
        name: 'Menu2',
        meta: { title: 'menu2' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
