import React, { useEffect, useState } from 'react'
import api from '../../../api'

function ProductAdmin() {

  const [products, setProducts] = useState(null)

  const fetchProducts = async () => {

    try {

      const response = await api.get("/products")
      setProducts(response.data)

    } catch (error) {
      alert("Something went wrong")
      console.log(error)
    }

  }

  useEffect(() => {
    fetchProducts()
  }, [])


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
              <td>-</td>
              <td>
                <button
                  className="btn btn-warning btn-sm me-2"
                  
                >
                  Update
                </button>

                <button
                  className="btn btn-danger btn-sm"
                
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
</div>
  )
}

export default ProductAdmin