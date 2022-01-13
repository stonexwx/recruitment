// 导入组件
import Vue from 'vue';
import Router from 'vue-router';
// 登录
import login from '@/views/login';
// 首页
import index from '@/views/index';
/**
 * 基础菜单
 */
// 预约管理
import Interview from '@/views/interview/interview';
/**
 * 系统管理
 */
// 用户管理
import user from '@/views/system/user';
// 公司管理
import Dept from '@/views/system/Dept';
/**
 * 评论管理
 */
import Comment from '@/views/comment/comment';

// 启用路由
Vue.use(Router);

// 导出路由 
export default new Router({
    routes: [{
        path: '/',
        name: '',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false
        }
    }, {
        path: '/login',
        name: '登录',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false
        }
    }, {
        path: '/index',
        name: '首页',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [
           {
            path: '/interview/interview',
            name: '预约请求',
            component: Interview,
            meta: {
                requireAuth: true
            }
        }, 
        {
            path: '/system/user',
            name: '用户管理',
            component: user,
            meta: {
                requireAuth: true
            }
        },  {
            path: '/system/Dept',
            name: '公司管理',
            component: Dept,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/comment/comment',
            name: '评论管理',
            component: Comment,
            meta: {
                requireAuth: true
            }
        }
    ]
    }]
})