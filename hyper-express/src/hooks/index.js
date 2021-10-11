import React from 'react';
import { AuthProvider } from './auth';
import { ProductProvider } from './useProducts';

export default function AppProvider({ children }) {
  return (
    <AuthProvider>
      <ProductProvider>{children}</ProductProvider>
    </AuthProvider>
  );
}
