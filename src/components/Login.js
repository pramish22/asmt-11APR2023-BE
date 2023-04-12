import React from 'react';

const Login = () => {
  return (
    <form>
      <div className="container col-6 border mt-3">
        <div className="row">
          <div className="">
            <label className="form-label col-6" for="userId">
              User ID:
            </label>
            <input id="userId" className="form-control col-6" required />
          </div>
        </div>

        <div className="row">
          <div className="">
            <label className="form-label" for="password">
              Password:
            </label>
            <input
              type="password"
              id="password"
              className="form-control"
              required
            />
          </div>
        </div>

        <div className="d-flex align-items-center justify-content-center my-3">
          <button type="button" className="btn btn-primary btn-block mb-4">
            Log in
          </button>
        </div>
      </div>
    </form>
  );
};

export default Login;
