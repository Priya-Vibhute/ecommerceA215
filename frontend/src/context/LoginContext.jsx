import { createContext, useState } from "react";


export const LoginContext=createContext()

export const LoginProvider=({children})=>{

    const [token,setToken]=useState(localStorage.getItem('token'))
    const [username,setUserName]=useState(localStorage.getItem("username"))


    const login=(tok,name)=>{
        setToken(tok)
        setUserName(name)
        localStorage.setItem("token",tok)
        localStorage.setItem("username",name)
    }

    const logout=()=>{
        localStorage.clear()
        setToken(null)
    }

    const isAuthenticated= token ? true: false 

    return <LoginContext.Provider value={{login,logout,isAuthenticated}}> 
               {children}
    </LoginContext.Provider>


}




