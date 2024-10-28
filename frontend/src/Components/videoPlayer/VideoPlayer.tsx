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
  autoplay?: boolean;
}

const VideoPlayer: React.FC<VideoPlayerProps> = ({
  videoUrl,
  title,
  description,
  autoplay = true
}) => {
  return (
    <VideoContainer>
      <StyledVideo url={videoUrl} playing={autoplay} controls={true} />
      <TitleDescriptionContainer>
        <StyledTitle> {title}</StyledTitle>
        <StyledDescription>{description}</StyledDescription>
      </TitleDescriptionContainer>
    </VideoContainer>
  );
};

export default VideoPlayer;