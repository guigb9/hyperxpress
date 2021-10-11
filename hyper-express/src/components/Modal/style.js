import styled from 'styled-components';
import { Modal } from 'antd';

export const Container = styled(Modal)`
  padding: 1rem;

  border-radius: 0.5rem;
  box-shadow: 0 0 5rem #0005;

  background: var(--white);

  div {
    text-align: center;
    background: unset;
    box-shadow: unset !important;
  }
  .ant-modal-close-x {
    svg {
      width: 2.5rem;
      height: 2.5rem;

      cursor: pointer;
      color: var(--darkBold);

      &:hover {
        color: var(--purple) !important;
      }

      path {
        cursor: pointer;
      }
    }
  }

  .ant-modal-header {
    h1 {
      font-size: 3.5rem;
      color: var(--dark);
    }
  }

  .ant-modal-body {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;

    min-height: 30rem;
  }
`;
