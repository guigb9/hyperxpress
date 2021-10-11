import styled from 'styled-components';

export const Container = styled.div`

  .report {
    width: 90%;
    margin: auto;
    margin-top: 1em;
  }
    @media (max-width: 700px) {
    .ImgReport img{
      display:none;
    }
    }
   .report{
      width: 90%;
      margin: auto;
      margin-top:1em;
   }
    .ImgReport img{
      width: 29em;
    }
   .container{
     width:100%;
   }
    .titulo{
    margin-bottom:1em;
    }
  
  form{
    width:90%;
  }
  
  select{
    width:100%;
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    font-variant: tabular-nums;
    list-style: none;
    font-feature-settings: 'tnum', "tnum";
    position: relative;
    display: inline-block;
    width: 100%;
    min-width: 0;
    padding: 4px 11px;
    color: rgba(0, 0, 0, 0.85);
    font-size: 14px;
    line-height: 1.5715;
    background-color: #fff;
    background-image: none;
    border: 1px solid #d9d9d9;
    border-radius: 2px;
    transition: all 0.3s;
  }
   .Select{
    margin-bottom: 1em;
  }
   form input{
    margin-bottom:1em;
  }
   form TextArea{
    margin-bottom:1em;
  }
 
`;

export const FormContent = styled.div`
  width: 100%;
  max-width: 45rem;

  margin-top: 2rem;

  form {
    width: 100%;
  }
`;
