import styled from 'styled-components';
import { Card, Skeleton } from 'antd';

export const Container = styled(Card)`
  position: relative;
  width: 19rem;
  height: 27rem;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  margin: 0 0.5rem;
  padding-top: 0.5rem;

  transform: scale(0.9);
  cursor: pointer !important;

  transition: all 150ms ease-in-out !important;
  box-shadow: 1px 0px 10px #0004 !important;

  &:hover {
    transform: scale(1);
    box-shadow: 1px 0px 10px #0007 !important;
  }

  .ant-card-cover {
    width: 15rem;
    margin: 1rem 0;
  }

  .replacement {
    position: absolute;
    width: 2.5rem !important;

    top: 0.5rem;
    right: 0.5rem;
  }

  img {
    width: 100% !important;
    cursor: pointer;
  }
`;

export const Content = styled.div`
  display: flex;

  flex-direction: column;
  justify-content: center;
  align-items: center;

  font-size: 1.6rem;
  font-family: 'Poppins';
  text-align: center;

  h3,
  h4 {
    color: var(--darkBold);
    cursor: pointer;
  }

  h4 {
    color: var(--darkMedium);
    border-bottom: 1px solid #0004;
    margin-bottom: 0.5rem;
  }
`;

export const Skeleto = styled(Skeleton)`
  width: 19rem;
  height: 27rem;

  transform: scale(0.85);

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  border-radius: 0.5rem;
  box-shadow: 1px 0px 10px #0004 !important;

  h3 {
    display: none;
  }

  ul {
    padding: 0 3.5rem !important;
  }

  .ant-skeleton-header {
    padding: unset !important;
  }

  .ant-skeleton-avatar.ant-skeleton-avatar-lg.ant-skeleton-avatar-circle {
    width: 17rem;
    height: 17rem;

    margin: unset !important;
    border-radius: unset;
  }
`;

// CARD DE PRODUTO PARA COMPRA

export const ContainerBig = styled(Card)`
  position: relative;
  width: 35rem;
  height: 55rem;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  padding: 2rem;

  cursor: pointer !important;

  transition: all 150ms ease-in-out !important;
  box-shadow: 1px 0px 10px #0005 !important;

  .replacement {
    position: absolute;
    width: 2.5rem !important;

    top: 0.5rem;
    right: 0.5rem;
  }

  .ant-card-cover {
    width: 35rem;
    height: 55rem;
    margin: 0 0 3rem;
    border-bottom: 2px solid #0004;

    img {
      width: 95% !important;
      height: 95% !important;
      margin: auto;
    }
  }
`;

export const ContentBig = styled.div`
  img {
    width: 8rem !important;
    margin-right: 3rem;
    border: 1px solid #0003;

    transition: all 150ms ease-in-out !important;
    box-shadow: 1px 0px 10px #0002 !important;

    &:nth-last-child(1) {
      margin: unset;
    }

    &:hover {
      transform: scale(1.4);
    }
  }
`;

export const SkeletoBig = styled(Skeleton)`
  width: 35rem;
  height: 50rem;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  border-radius: 0.5rem;
  box-shadow: 1px 0px 10px #0004 !important;
  h3 {
    display: none;
  }

  ul {
    padding: 0 2.5rem !important;
  }

  li {
    width: 30rem !important;
    height: 5rem !important;

    &:nth-last-child(1) {
      display: none;
    }
  }

  .ant-skeleton-header {
    padding: unset !important;
  }

  .ant-skeleton-avatar.ant-skeleton-avatar-lg.ant-skeleton-avatar-circle {
    width: 30rem;
    height: 36rem;

    margin: unset !important;
    border-radius: unset;
  }
`;
