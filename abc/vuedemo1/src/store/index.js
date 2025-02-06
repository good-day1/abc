import vue from 'vue'
import Vuex from 'vuex'
vue.use(Vuex)
import router,{resetRouter} from "../router";
function addNewRouter(menuList){
    console.log(menuList)
    let routes=router.options.routes
    routes.forEach(routerItem=>{
        if(routerItem.path=="/myTmp"){
            menuList.forEach(menu=>{
                let childRoute={
                    path:'/'+menu.menuclick,
                    name:menu.menuname,
                    meta:{
                        title:menu.menuname
                    },
                    component:()=>import("../components/"+menu.menucomponent)
                }
                routerItem.children.push(childRoute)
            })
        }
    })
    resetRouter()
    router.addRoutes(routes)
}
export default new Vuex.Store({
    state:{
       menu:[]
    },
    mutations:{
        setMenu(state,menuList) {
            state.menu=menuList
            addNewRouter(menuList)
        },
    },
    getters:{
        getMenu(state){
            return state.menu
        }
    }
})

