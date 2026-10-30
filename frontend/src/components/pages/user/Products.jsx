import React, { useContext, useEffect, useState } from 'react'
import { LoginContext } from '../../../context/LoginContext'
import api from '../../../api'

function Products() {


  const [products,setProducts]=useState(null)
  const [totalPages,setTotalPages]=useState(0)
  const [currentPage,setCurrentPage]= useState(0)

  const fetchProducts=async()=>{

       try {

           const response=await api.get(`/products?size=3&page=${currentPage}`)
           console.log(response.data)
           setProducts(response.data.content)

           setTotalPages(response.data.totalPages)
        
       } catch (error) {
           alert("Something went wrong") 
       }

  }
  


  useEffect(()=>{
       fetchProducts();
  },[currentPage])


  const addToCart=async(pid)=>{
     try {

          const response=await api.post("/cart/add-to-cart",
            {
              productId:pid,
              quantity:1
            }
          )

          alert("Product added to cart")


          console.log(response.data)
      
     } catch (error) {
         console.log(error)
         alert("Something went wrong")
     }
  }



  return (
    <> 
    
      <button className='btn btn-primary m-3'  
      onClick={()=>setCurrentPage(currentPage-1)}
       disabled={currentPage==0}>Previous</button>

      <button className='btn btn-primary m-3' 
      onClick={()=>setCurrentPage(currentPage+1)}
       disabled={currentPage==totalPages-1}>Next</button>

    <div class="row row-cols-1 row-cols-md-3 g-4">


      {
          products && products.map(p=><>
               <div class="col">
    <div class="card">
      <img src={p.imageUrl} class="card-img-top" alt="..."/>
      <div class="card-body">
        <h5 class="card-title">{p.name}    {p.id}</h5>
        <p class="card-text">{p.description}</p>
         <p class="card-text">Price :{p.price}</p>
         <button className='btn btn-primary m-3' onClick={()=>addToCart(p.id)} >Add to Cart</button>
      </div>
    </div>
  </div>
          </>)
      }
    </div>
    </>
  )
}

export default Products