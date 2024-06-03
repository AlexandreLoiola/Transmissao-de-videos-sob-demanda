import styled from 'styled-components';

export const HeaderContainer = styled.div`
  display: flex;
  align-items: center;
  padding: 16px;
  background-color: #fff0f5;
  position: relative;
  z-index: 9999;
  height: ${(props) =>
      props.theme.deviceType === "mobile" ? "12vh" : "10vh"};
`;

export const Title = styled.h1`
  margin: 0;
  font-family: 'Libre Baskerville', serif;
  width: 100%;
  position: ${(props) =>
      props.theme.deviceType === "mobile" ? "block" : "fixed"};
  font-size: ${(props) =>
      props.theme.deviceType === "mobile" ? "20px" : "34px "};
  padding:  ${(props) =>
      props.theme.deviceType === "mobile" ? "20px 4 0px" : "0"};
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