import styled from 'styled-components';
import { motion } from 'framer-motion';

export const animation = {
  hidden: { opacity: 1 },
  visible: {
    opacity: 1,
    transition: {
      delayChildren: 0.3,
      staggerChildren: 0.2,
    },
  },
};

export const itemsAnimation = {
  hidden: { y: 50, x: 33, opacity: 0 },
  visible: { y: 0, x: 0, opacity: 1 },
};

// Styled
export const MotionDiv = styled(motion.div)`
  /* background: #000; */
`;

export const MotionContent = styled(motion.div)`
  /* background: red; */
`;
