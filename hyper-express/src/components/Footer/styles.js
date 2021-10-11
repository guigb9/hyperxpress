import styled from 'styled-components';

export const Container = styled.div`
  padding: 4rem 3rem;
  background-color: var(--purple);

  margin-top: 5rem;

  @media (max-width: 1000px) {
    padding: 7rem 3rem;
  }

  .Wrapper {
    display: flex;
    flex-direction: column;
    justify-content: center;
    max-width: 90%;
    margin: 0 auto;
  }

  .Column {
    display: flex;
    flex-direction: column;
    text-align: left;
    margin-left: 60px;
  }

  .Row {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(270px, 1fr));
    grid-gap: 2.5rem;
    @media (min-width: 1024px) {
      justify-items: left;
    }

    @media (max-width: 1000px) {
      grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    }
  }

  .Link {
    color: #fff;
    margin-bottom: 10px;
    text-decoration: none;
    text-align: left;
    list-style-type: none;

    &:hover {
      color: var(--darkBold);
      transition: 200ms ease-in;
    }
  }

  .Title {
    font-size: 24px;
    color: #fff;
    margin-bottom: 40px;
    font-weight: bold;
    text-align: left;
  }

  .Logo {
    width: 150px;
  }

  .FooterTxt {
    color: var(--white);
    margin-bottom: 10px;
    text-decoration: none;
    text-align: left;
  }

  .Social {
    width: 30px;
    margin-right: 10px;

    &:hover {
      color: var(--darkBold);
      transition: 200ms ease-in;
    }
  }
`;