import VueRouter from 'vue-router'
import aLogin from "@/components/aLogin";
import myTmp from "@/components/myTmp";
import myHome from "@/components/myHome";
const routes=[{
    path:"/",
    name:aLogin,
    component:()=>import("../components/aLogin")
},{
    path:"/myTmp",
    name:myTmp,
    component:()=>import("../components/myTmp"),
    children:[{
        path:"/myHome",
        name:myHome,
        meta:{
          title:"首页"
        },
        component:()=>import("../components/myHome")
    }]
}]

const router=new VueRouter({
    mode:'history',
    routes
})
const VueRouterPush=VueRouter.prototype.push
VueRouter.prototype.push=function push (to){
    return VueRouterPush.call(this,to).catch(err=>err)
}
export function resetRouter(){
    router.matcher=new VueRouter({
        mode:"history",
        routes:[]
    }).matcher
}
export default router;