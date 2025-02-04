import React from "react";
import { Navigate, useLocation } from "react-router-dom";
import { useAuth } from "../../contexts/AuthContext";

const PrivateRoute = ({ children, userType }) => {
  const { user, loading } = useAuth();
  const location = useLocation();

  if (loading) {
    return <div>Loading...</div>;
  }

  if (!user) {
    // Redirect to login with return URL
    return (
      <Navigate
        to={userType === "admin" ? "/admin/login" : "/donor/login"}
        state={{ from: location.pathname }}
        replace
      />
    );
  }

  if (userType && user.userType !== userType) {
    return <Navigate to="/" replace />;
  }

  return children;
};

export default PrivateRoute;
