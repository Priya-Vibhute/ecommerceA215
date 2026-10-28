import React, { useContext } from 'react'
import { LoginContext } from '../context/LoginContext'
import { Navigate, Outlet } from 'react-router-dom'

function ProtectedRoute() {

 const {isAuthenticated}=useContext(LoginContext)

    if (!isAuthenticated)
        return <Navigate to={"/login"}/>

    return <Outlet/>
  
}

export default ProtectedRoute