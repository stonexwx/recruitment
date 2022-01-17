import axios from 'axios';
import { loginreq, req } from './axiosFun';

// 登录接口 
export const login = (params) => { return loginreq("post", "/login", params) };
// 获取用户菜单
export const menu = (params) => { return axios.get("/api/menu?&token=" + localStorage.getItem('logintoken')).then(res => res.data) };
// 退出接口
export const loginout = () => { return axios.delete("/api/login?&token=" + localStorage.getItem('logintoken')).then(res => res.data) };

/**
 * 用户管理 
 **/
// 用户管理-获取用户列表
export const userList = (params) => { return req("post", "/admin_user_select", params) };
// 用户管理-保存（添加编辑）
export const userSave = (params) => { return req("post", "/admin_user_update", params) };
// 用户管理-删除用户
export const userDelete = (params) => { return req("post","/admin_user_delete",params)};
// 用户管理-重置密码
export const userPwd = (params) => { return req("post", "/password_info", params) };

/**
 * 公司管理 
 **/
// 公司管理-获取公司列表
export const deptList = (params) => { return req("post", "/api/Dept/list", params) };
// 公司管理-保存（添加编辑）
export const deptSave = (params) => { return req("post", "/api/Dept/save", params) };
// 公司管理-删除公司
export const deptDelete = (params) => { return axios.get("/api/Dept/delete?ids=" + params + "&token=" + localStorage.getItem('logintoken')).then(res => res.data) };
/**
 * 面试信息管理
 */
//面试信息管理-获取面试列表
export const interviewList = (params) => {return req("post","/interview_select",params)};
//面试信息管理-删除某个面试信息
export const interviewDelete = (params) => {return req("post","/interview_delete",params)};
/**
 * 评论管理
 */
//评论管理-获取评论信息
export const evaluationList = (params) => {
    return req("post", "/admin_evaluation_select", params);}
//评论管理-通过评论信息
export const evaluationUpdate = (params) =>{
    return req("post", "/admin_evaluation_update", params);}
//论管理-删除评论信息
export const evaluationDelete = (params) =>{
    return req("post", "/admin_evaluation_delete", params);}

