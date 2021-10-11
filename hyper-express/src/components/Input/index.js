import React from 'react';
import { Container, Inputs, Error } from './style';

export default function Input({
  title,
  id,
  register,
  errors,
  required,
  ...rest
}) {
  return (
    <Container>
      <div className="flex">
        <label htmlFor={id}> {title} </label>
        <Error> {errors?.message} </Error>
      </div>

      <Inputs
        id={id}
        title={title}
        ref={register({ required })}
        errors={errors}
        {...rest}
      />
    </Container>
  );
}
