import styled from "styled-components";

export const StyledContainer = styled.div`
  display: flex;
  height: 100vh;
  padding-top: 10px;
  width: 25vw;
  position: fixed;
  right: 0;
  background-color: #fff0f5;
  flex-direction: column;
  gap: 10px;
  align-items: center;
  overflow-y: auto;
  box-shadow: 0px 0px 20px 0px #0000001A; 
  z-index: 1;
`;

export const StyledItem = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  transition: transform 0.3s ease-in-out;
  
  &:hover {
    transform: scale(1.1);
  }
`;

export const StyledImage = styled.img`
  height: 180px;
  width: 280px;
  cursor: pointer;
  border-radius: 12px;
`;

export const StyledTitle = styled.span`
  margin-top: 4px;
  margin-bottom: 20px;
  font-family: Roboto;
  font-size: 18px;
  font-weight: 600;
  line-height: 24px;
  text-align: center;
  color: #000000CC;
`;

export const StyledText = styled.h1`
  margin-top: 32px;
  margin-bottom: 30px;
  font-family: Roboto;
  font-size: 32px;
  font-weight: 600;
  line-height: 24px;
  text-align: center;
  color: #000000CC;
`;
