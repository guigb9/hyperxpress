import * as yup from 'yup';

const msg = {
  Required: 'Informe esse campo',
  Number: 'Somente números',
  Email: 'Informe um email valido',
  Positive: 'Somente números positivos',
  Integer: 'Somente números inteiros',
};

export const SchemaLogin = yup.object().shape({
  email: yup.string().email(msg.Email).required(msg.Required),
  senha: yup.string().min(8, 'min 8 digitos').required(msg.Required),
});

export const SchemaBasic = yup.object().shape({
  nome: yup.string().required(msg.Required),
  avatar: yup.string().min(5, 'min 5 digitos').required(msg.Required),
  cpf: yup.string().min(14, 'Min 11 digitos').required(msg.Required),
  dataNasc: yup.string().required(msg.Required),

  email: yup.string().email(msg.Email).required(msg.Required),
  senha: yup.string().min(8, 'min 8 caracters').required(msg.Required),
  confirmSenha: yup
    .string()
    .oneOf([yup.ref('senha'), null], 'Senhas diferentes')
    .required(msg.Required),
});

export const SchemaLocal = yup.object().shape({
  cep: yup.string().min(9, 'min 8 digitos').required(msg.Required),
  estadoUf: yup.string().required(msg.Required),
  cidade: yup.string().required(msg.Required),
  logradouro: yup.string().required(msg.Required),
  bairro: yup.string().required(msg.Required),
  numero: yup.string().required(msg.Required),
  complemento: yup.string(),
});

export const SchemaCodigo = yup.object().shape({
  codigo: yup.string().min(6, 'min 6 digitos').required(msg.Required),
});

export const SchemaProduct = yup.object().shape({
  nomeProduto: yup.string().max(20, 'max 20 digitos').required(msg.Required),
  marca: yup.string().required(msg.Required),
  preco: yup.string().required(msg.Required),
  tecido: yup.string().required(msg.Required),
  genero: yup.string().required(msg.Required),
  troca: yup.string().required(msg.Required),
  tamanhoProduto: yup.string().max(2, 'max 2 digitos').required(msg.Required),
  telefone: yup.string().min(16, 'min 11 digitos').required(msg.Required),
  descricaoProduto: yup
    .string()
    .min(50, 'min 50 digitos')
    .required(msg.Required),
});

export const SchemaReport = yup.object().shape({
  nome: yup.string().required(msg.Required),
  email: yup.string().email(msg.Email).required(msg.Required),
  assunto: yup.string().required(msg.Required),
  mensagem: yup.string().min(50, 'min 50 digitos').required(msg.Required),
});
