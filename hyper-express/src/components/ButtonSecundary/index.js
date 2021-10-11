import React from 'react';
import { LoadingOutlined } from '@ant-design/icons';
import { Container } from '../Button/style';

export default function ButtonSecundary({ loading, children, ...rest }) {
  return (
    <>
      {loading ? (
        <Container secundary disabled="disabled" {...rest}>
          <LoadingOutlined />
        </Container>
      ) : (
        <Container secundary whileTap={{ scale: 0.87 }} {...rest}>
          {children}
        </Container>
      )}
    </>
  );
}
