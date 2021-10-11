import styled from 'styled-components';

export const Container = styled.div`
  width: 100%;
  height: 5rem;
  position: relative;

  padding: 0.5rem 1.6rem;

  box-shadow: 0px 0 5px #0006;
  border-radius: 5px;

  background: var(--white);

  img {
    width: 2.3rem;
    cursor: pointer;
  }
`;

export const Input = styled.input`
  width: 95%;
  height: 100%;

  font-size: 1.8rem;
  font-family: 'Poppins';

  border: unset;

  transition: all ease 300ms;
  cursor: text;

  color: var(--darkMedium);
  background: transparent;

  :focus {
    ::placeholder {
      transition: all ease-in-out 100ms;
      opacity: 0;
    }
  }

  ::placeholder {
    text-align: center;
    font-weight: 300;
    opacity: 0.7;
    color: var(--darkMedium);
  }
`;

export const Pesquisa = styled.div`
  position: absolute !important;
  width: 100%;

  align-items: start !important;

  top: 6rem;
  right: 0;

  z-index: 10;
  cursor: pointer;
  box-shadow: 0 0 10px #0002;

  background: white;

  a {
    width: 100%;
    padding: 1rem;
    margin: 1rem 0 0 0 !important;

    color: var(--dark) !important;
    border-bottom: 1px solid #0002;
    &:hover {
      color: var(--purple) !important;
      cursor: pointer;
    }
  }
`;
