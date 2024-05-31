import React from "react";
import {
  StyledDescription,
  StyledTitle,
  StyledVideo,
  TitleDescriptionContainer,
  VideoContainer,
} from "./styles";

interface VideoPlayerProps {
  videoUrl: string;
  title: string;
  description: string;
}

const VideoPlayer: React.FC<VideoPlayerProps> = ({
  videoUrl,
  title,
  description,
}) => {
  return (
    <VideoContainer>
      <StyledVideo url={videoUrl} controls={true} />
      <TitleDescriptionContainer>
        <StyledTitle> {title}</StyledTitle>
        <StyledDescription>{description}</StyledDescription>
      </TitleDescriptionContainer>
    </VideoContainer>
  );
};

export default VideoPlayer;