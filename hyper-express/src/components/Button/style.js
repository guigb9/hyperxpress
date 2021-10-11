import styled from 'styled-components';
import { motion } from 'framer-motion';

export const Container = styled(motion.button)`
  width: 100%;
  max-width: 40rem;
  height: 4.5rem;

  margin: 0.5rem auto;

  font-size: 1.7rem;
  font-weight: 700;

  border-radius: 3px;
  border: none;
  box-shadow: 0 0 8px rgb(0 0 0 /35%);

  cursor: pointer;
  transition: all ease-in-out 100ms;

  &:disabled {
    cursor: no-drop;
    box-shadow: unset;
    svg {
      width: 2.4rem;
      height: 2.4rem;
      cursor: no-drop;
    }
  }

  /* Colors Button Secundario */
  ${(props) => {
    if (props.secundary) {
      return ` 
        border: 2px solid var(--purple);
        color: var(--purple); 
        background: var(--white);

        svg {
          color:var(--purple);
        }

        &:hover {
          border: 2px solid var(--purpleDark);
          color: var(--purpleDark);
        }
      `;
    } else {
      return ` 
        color: var(--white); 
        background: var(--purple);

        svg {
          color:var(--white);
        }

        &:hover {
          background: var(--purpleDark);
        }
      `;
    }
  }}
`;
