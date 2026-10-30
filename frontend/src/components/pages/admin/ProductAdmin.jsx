import React, { useEffect, useState } from 'react'
import api from '../../../api'
import { useForm } from 'react-hook-form';

function ProductAdmin() {


 const [selectedProduct,setSelectedProduct]= useState(null)
 

   const {
      register,
      handleSubmit,
      reset,
      formState: { errors },
    } = useForm();

    const handleToUpdate=async(data)=>{
       
      console.log(data)
      try {
       const response=await api.put(`/products/${selectedProduct}`,data);
       fetchProducts()
       document.getElementById('close-btn').click()
      } catch (error) {
        alert("Something went wrong")
      }

    }


    const changeSelectedProduct=(product)=>{
        console.log(product.name +" "+product.id)
        setSelectedProduct(product.id)
        reset({
          name:product.name,
          price:product.price,
          description:product.description,
          imageUrl:product.imageUrl
        })
    }
  

  const [products, setProducts] = useState(null)

  const fetchProducts = async () => {
    try {
      const response = await api.get("/products")
      setProducts(response.data.content)
    } catch (error) {
      alert("Something went wrong")
      console.log(error)
    }

  }

  useEffect(() => {
    fetchProducts()
  }, [])


  const handleToDelete=async (productId)=>{

        try {

              const response=await api.delete(`/products/${productId}`)  // backend
              console.log(response)
              fetchProducts()
             
          
        } catch (error) {
          alert("something went wrong")
        }
      
  }


  return (
    <div className="container mt-4">
  <h3 className="mb-3">Product List</h3>

  {products ? (
    <div className="table-responsive">
      <table className="table table-bordered table-striped table-hover">
        <thead className="table-dark">
          <tr>
            <th>#</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>imageUrl</th>
            <th>Category</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {products.map((p, index) => (
            <tr key={p.id }>
              <td>{p.id}</td>
              <td>{p.name}</td>
              <td>₹{p.price}</td>
              <td>{p.description}</td>
              <td>{p.imageUrl}</td>
              <td>{p.category?.name || "not assigned"}</td>
              <td>
                

                {/* modal  */}

                {/* <!-- Button trigger modal --> */}
<button type="button" class="btn btn-primary mx-3" data-bs-toggle="modal" data-bs-target="#exampleModal"
onClick={()=>changeSelectedProduct(p)}>
  Update 
</button>




                {/* modal */}







                <button
                  className="btn btn-danger btn-sm"
                  onClick={()=>handleToDelete(p.id)}
                
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  ) : (
    <div className="alert alert-info">Loading....</div>
  )}

  {/* <!-- Modal --> */}
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">


          <div className="admin-form-card">
        <form onSubmit={handleSubmit(handleToUpdate)}>
          <div className="row g-3">
            <div className="col-12">
              <label className="form-label">Name</label>
              <input
                type="text"
                className={`form-control ${errors.name ? "input-error" : ""}`}
                placeholder="Product name"
                {...register("name", {
                  required: "Name is required",
                  minLength: {
                    value: 3,
                    message: "Name must be at least 3 characters",
                  },
                })}
              />
              {errors.name && (
                <small className="error-message">{errors.name.message}</small>
              )}
            </div>

            <div className="col-12 col-md-6">
              <label className="form-label">Price</label>
              <input
                type="number"
                step="0.01"
                className={`form-control ${errors.price ? "input-error" : ""}`}
                placeholder="0.00"
                {...register("price", {
                  required: "Price is required",
                  valueAsNumber: true,
                  min: {
                    value: 0,
                    message: "Price must be greater than or equal to 0",
                  },
                })}
              />
              {errors.price && (
                <small className="error-message">{errors.price.message}</small>
              )}
            </div>

            <div className="col-12 col-md-6">
              <label className="form-label">Image URL</label>
              <input
                type="text"
                className={`form-control ${errors.imageUrl ? "input-error" : ""}`}
                placeholder="https://example.com/image.jpg"
                {...register("imageUrl", {
                  required: "Image URL is required",
                  
                })}
              />
              {errors.imageUrl && (
                <small className="error-message">
                  {errors.imageUrl.message}
                </small>
              )}
            </div>

            <div className="col-12">
              <label className="form-label">Description</label>
              <textarea
                rows="5"
                className={`form-control ${errors.description ? "input-error" : ""}`}
                placeholder="Write a short product description"
                {...register("description", {
                  required: "Description is required",
                  minLength: {
                    value: 10,
                    message: "Description must be at least 10 characters",
                  },
                })}
              />
              {errors.description && (
                <small className="error-message">
                  {errors.description.message}
                </small>
              )}
            </div>

            <div className="col-12">
              <button type="submit" className="admin-submit-btn">
                Update product
              </button>
            </div>
          </div>
        </form>
      </div>

            
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id='close-btn'>Close</button>
      </div>
    </div>
  </div>
</div>


</div>


  )
}

export default ProductAdmin