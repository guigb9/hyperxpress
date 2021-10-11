import React from 'react';
import { Container } from './style';
import camisa from './images/camisa.jpg';
import remove from './images/remove.svg';


export default function CaixaProduto(props) {
  return (
    <Container>
      <div className="caixa flex">
      <div className="camisa">
        <img width="100px" src={camisa}/>
      </div>

      <div>
        <span>{props.nomeProduto}</span>
      </div>

      <div>
        <span>
          R$ {props.valor}
        </span>

        <span>
          Pagamentos em até 12X sem juros
        </span>
      </div>

      <div className="remove">
        <img width="30px" src={remove}/>
      </div>
    </div>

    </Container>
  );
}
