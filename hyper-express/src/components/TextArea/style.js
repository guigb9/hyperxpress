import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  align-items: flex-start;
  flex-direction: column;

  font-size: 1.8rem;
  font-weight: 500;

  color: var(--dark);
`;

export const TextAreas = styled.textarea`
  width: 100%;
  height: 15rem;

  margin-bottom: 2rem;
  padding: 1rem;

  font-size: 1.6rem;

  border: 2px solid var(--grayBold);

  transition: all ease 200ms;
  cursor: text;

  color: var(--darkMedium);
  background: transparent;

  :focus {
    border: 2px solid var(--purple);
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
