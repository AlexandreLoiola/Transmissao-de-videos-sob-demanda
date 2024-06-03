import styled from "styled-components";
export const FooterContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  bottom: 0;
  width: 100%;
  padding: 16px;
  background-color: #fff0f5;
  box-shadow: 0px 0px 20px 0px #0000001a;
`;

export const Title = styled.h1`
  margin: 0;
  font-family: 'Libre Baskerville', serif;
  font-size: ${(props) =>
      props.theme.deviceType === "mobile" ? "16px" : "22px "};
  font-weight: 600;
  flex-grow: 1;
  text-align: center;
`;

export const StyledLink = styled.a`
`;
