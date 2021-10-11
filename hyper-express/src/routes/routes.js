import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { PrivateRoutes } from './private';

import Home from 'pages/home';
import Login from 'pages/login';
import Register from 'pages/register';
import Error404 from 'pages/404';
import PostProduct from 'pages/postProduct';
import Report from 'pages/report';
import Cart from 'pages/cart';
import Follow from 'pages/follow-up';
import Payment from 'pages/payment';
import Seller from 'pages/seller';
import BuyProduct from 'pages/buyProduct';
import Products from 'pages/products';

export default function MainRoutes() {
  return (
    <Routes>
      <Route path="*" element={<Error404 />} />
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />

      <PrivateRoutes
        path="/products/product/:id"
        redirect="/login"
        element={<BuyProduct />}
      />
      <PrivateRoutes path="/cart" redirect="/login" element={<Cart />} />
      <PrivateRoutes path="/follow-up" redirect="/login" element={<Follow />} />
      <Route path="/payment"  element={<Payment />} />
      <PrivateRoutes path="/report" redirect="/login" element={<Report />} />
      <PrivateRoutes
        path="/seller/:id"
        redirect="/login"
        element={<Seller />}
      />
      <PrivateRoutes path="/post" redirect="/login" element={<PostProduct />} />

      <Route
        path="/products/:id"
      
        element={<Products />}
      />
    </Routes>
  );
}
