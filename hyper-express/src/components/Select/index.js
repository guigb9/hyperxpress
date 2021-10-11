import React from 'react';
import { Container, Error, Selects } from './style';

export default function Select({
  title,
  id,
  register,
  errors,
  children,
  required,
  ...rest
}) {
  return (
    <Container>
      <div className="flex">
        <label htmlFor={id}> {title} </label>
        <Error> {errors?.message} </Error>
      </div>

      <Selects
        id={id}
        title={title}
        ref={register({ required })}
        errors={errors}
        {...rest}
      >
        {children}
      </Selects>
    </Container>
  );
}
