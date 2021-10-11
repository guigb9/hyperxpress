import React from 'react';
import { priceConvert } from 'utils/masks';

import { Tooltip } from 'antd';
import imgReplacement from 'assets/svgs/repeat.svg';

import {
  Container,
  Skeleto,
  Content,
  ContainerBig,
  ContentBig,
  SkeletoBig,
} from './style';

export default function CardProduct({
  title,
  replacement,
  price,
  loading,
  buy,
  src,
  children,
}) {
  return (
    <>
      {!buy ? (
        <Skeleto loading={loading} avatar active>
          <Container
            hoverable
            cover={<img alt="foto produto" src={src} />}
            style={
              replacement
                ? { border: '1px solid var(--purple)' }
                : { border: 'none' }
            }
          >
            <Tooltip title="Trocavel">
              <img
                className="replacement"
                src={imgReplacement}
                alt="trocavel"
                style={replacement ? { display: 'block' } : { display: 'none' }}
              />
            </Tooltip>

            <Content>
              <h4>
                {title.length > 13 ? title.substring(0, 13) + '...' : title}
              </h4>
              <h3>{priceConvert(price)}</h3>
            </Content>
          </Container>
        </Skeleto>
      ) : (
        <SkeletoBig loading={loading} avatar active>
          <ContainerBig
            hoverable
            cover={<img alt="foto produto" src={src} />}
            style={
              replacement
                ? { border: '2px solid var(--purple)' }
                : { border: 'none' }
            }
          >
            <Tooltip title="Trocavel">
              <img
                className="replacement"
                src={imgReplacement}
                alt="trocavel"
                style={replacement ? { display: 'block' } : { display: 'none' }}
              />
            </Tooltip>
            <ContentBig className="flex">{children}</ContentBig>
          </ContainerBig>
        </SkeletoBig>
      )}
    </>
  );
}
