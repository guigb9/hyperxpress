import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  align-items: flex-start;
  flex-direction: column;

  font-size: 1.8rem;
  font-weight: 500;

  color: var(--dark);
`;

export const Selects = styled.select`
  width: 100%;
  height: 4.3rem;

  margin-bottom: 2rem;
  padding: 1rem 0.5rem;

  font-size: 1.6rem;

  border: none;
  border-bottom: 2px solid var(--grayBold);

  transition: all ease 200ms;
  cursor: pointer;

  color: var(--darkMedium);
  background: transparent;

  :focus {
    border-bottom: 2px solid var(--purple);
  }

  option {
    color: var(--darkMedium);
    background: var(--white);
  }
`;

export const Error = styled.span`
  font-size: 1.4rem;
  font-weight: 500;

  opacity: 0.7;
  color: var(--redError);
`;
