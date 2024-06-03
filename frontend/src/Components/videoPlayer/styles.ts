import styled from "styled-components";
import ReactPlayer from "react-player";

export const VideoContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-left: ${(props) =>
      props.theme.deviceType === "mobile" ? "0" : "12vw"};
`;

export const StyledVideo = styled(ReactPlayer)`
  width: ${(props) =>
      props.theme.deviceType === "mobile" ? "82vw !important" : "49vw !important"};
  height: ${(props) =>
      props.theme.deviceType === "mobile" ? "46vw !important" : "28vw !important"};
  border-radius: 20px !important;
  overflow: hidden !important;
  margin-top: 20px;
`;

export const TitleDescriptionContainer = styled.div`
  background-color: #fff0f5;
  box-shadow: 0px 0px 20px 0px #0000001A; 
  border-radius: 12px;
  padding: 20px;
  margin: 24px 0;
  width: ${(props) =>
      props.theme.deviceType === "mobile" ? "82vw" : "49vw"};
`;


export const StyledTitle = styled.h1`
  font-family: Roboto;
  font-size: ${(props) =>
      props.theme.deviceType === "mobile" ? "24px" : "32px"};
  font-weight: 600;
  line-height: 46.88px;
  text-align: left;
  color: black;
`;

export const StyledDescription = styled.span`
  font-family: Roboto;
  font-size: ${(props) =>
      props.theme.deviceType === "mobile" ? "16px" : "18px"};
  font-weight: 500;
  line-height: 24px;
  text-align: left;
  color: #000000CC;
`;