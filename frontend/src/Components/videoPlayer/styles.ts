import styled from "styled-components";
import ReactPlayer from "react-player";

export const VideoContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-left: 9vw;
`;

export const StyledVideo = styled(ReactPlayer)`
  width: 56vw !important;
  height: 34vw !important;
  border-radius: 20px !important;
  overflow: hidden !important;
`;

export const TitleDescriptionContainer = styled.div`
  background-color: #F9F9F9;
  box-shadow: 0px 0px 20px 0px #0000001A;
  border-radius: 12px;
  padding: 20px;
  margin-top: 20px;
  width: 56vw;
`;


export const StyledTitle = styled.h1`
  font-family: Roboto;
  font-size: 32px;
  font-weight: 600;
  line-height: 46.88px;
  text-align: left;
  color: black;
`;

export const StyledDescription = styled.span`
  font-family: Roboto;
  font-size: 18px;
  font-weight: 500;
  line-height: 24px;
  text-align: left;
  color: #000000CC;
`;
