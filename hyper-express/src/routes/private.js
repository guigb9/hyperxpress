import React from 'react';
import { Route, Navigate } from 'react-router-dom';
import { useAuth } from 'hooks/auth';

export const PrivateRoutes = ({ redirect, ...rest }) => {
  const { logged } = useAuth();

  if (!logged) {
    return <Navigate to={redirect} />;
  }

  return <Route {...rest} />;
};

export const PublicRoutes = ({ redirect, ...rest }) => {
  const { logged } = useAuth();

  if (logged) {
    return <Navigate to={redirect} />;
  }

  return <Route {...rest} />;
};
