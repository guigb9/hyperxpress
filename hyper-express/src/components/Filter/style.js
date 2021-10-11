import styled from 'styled-components';

export const Container = styled.div`
  width: 22rem;

  h1 {
    margin: 0.5rem 0;
  }
  label {
    font-size: 1.6rem;
  }
  .flexColumn {
    align-items: start !important;

    label {
      font-size: 1.6rem;
      margin: 0.5rem 0;
    }
  }

  .ant-checkbox.ant-checkbox-checked {
    .ant-checkbox-inner {
      background: var(--purple) !important;
      border-color: var(--purple) !important;
    }
  }
`;
