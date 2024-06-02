import styled from 'styled-components';

export const HeaderContainer = styled.div`
  display: flex;
  align-items: center;
  padding: 16px;
  background-color: #fff0f5;
  position: relative;
  z-index: 9999;
  height: 10vh;
`;

export const Title = styled.h1`
  margin: 0;
  font-family: Roboto;
  width: 100%;
  position: fixed;
  font-size: 36px;
  font-weight: 600;
  flex-grow: 1;
  text-align: center;
`;

export const IconContainer = styled.div`
  cursor: pointer;
  margin-left: 4%;
  position: relative;
  z-index: 9999;
`;