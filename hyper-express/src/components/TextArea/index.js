import React from 'react';

import { Container, Error, TextAreas } from './style';

export default function TextArea({
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

      <TextAreas
        id={id}
        title={title}
        ref={register({ required })}
        errors={errors}
        {...rest}
      />
    </Container>
  );
}
