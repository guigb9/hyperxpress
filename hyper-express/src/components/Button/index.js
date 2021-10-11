import React from 'react';
import { LoadingOutlined } from '@ant-design/icons';
import { Container } from './style';

export default function Button({ loading, children, ...rest }) {
  return (
    <>
      {loading ? (
        <Container disabled="disabled" {...rest}>
          <LoadingOutlined />
        </Container>
      ) : (
        <Container whileTap={{ scale: 0.87 }} {...rest}>
          {children}
        </Container>
      )}
    </>
  );
}
