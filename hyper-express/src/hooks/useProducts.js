import { createContext, useContext, useCallback } from 'react';

import usePersisted from './usePersisted';

const ProductContext = createContext({});

const ProductProvider = ({ children }) => {
  const [product, setProduct] = usePersisted('product', {
    replacement: '',
    gender: '',
    subCategory: '',
  });

  const reset = useCallback(() => {
    setProduct({
      replacement: '',
      gender: '',
      subCategory: '',
      codigoPedido: product.codigoPedido,
      valorTotal: product.valorTotal,
    });
  }, [product.codigoPedido, product.valorTotal, setProduct]);

  return (
    <ProductContext.Provider value={{ product, setProduct, reset }}>
      {children}
    </ProductContext.Provider>
  );
};

const useProduct = () => useContext(ProductContext);

export { ProductProvider, useProduct };
