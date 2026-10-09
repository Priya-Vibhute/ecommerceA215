import React from "react";
import { useForm } from "react-hook-form";
import { Link } from "react-router-dom";
import api from "../../../api";

function AddProduct() {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm();

  const onSubmit = async (data) => {
    console.log("Product Data:", data);

    try {

      const response=await api.post("/products",data)
      console.log(response)
      alert("Product added successfully")
      
    } catch (error) {
      alert("Something went wrong")
    }


    reset();
  };

  return (
    <div className="admin-form-page">
      <div className="dashboard-header">
        <div>
          <p className="dashboard-eyebrow">Product management</p>
          <h1 className="dashboard-title">Add Product</h1>
          <p className="dashboard-subtitle">
            Create a new product and return to the list when you are done.
          </p>
        </div>

        <Link to="/admin/products" className="btn btn-outline-secondary dashboard-action-btn">
          Back to Products
        </Link>
      </div>

      <div className="admin-form-card">
        <form onSubmit={handleSubmit(onSubmit)}>
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
                Add Product
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}

export default AddProduct;
