import styled from 'styled-components';

export const Container = styled.div`
  width: 100%;
`;

export const AddPicture = styled.div`
  width: 100%;
  height: 100%;
  cursor: pointer;

  border: 1px dashed var(--dark);

  &:hover {
    color: var(--white);
    background: var(--purple);

    svg {
      color: var(--white);
    }
  }

  svg {
    width: 2.7rem;
    height: 2.7rem;

    cursor: pointer;
    color: var(--purple);

    path {
      cursor: pointer;
    }
  }
`;
