import React from "react";
import { StyledContainer, StyledImage, StyledItem, StyledText, StyledTitle } from "./styles";
import { IVideo } from "../../interfaces/IVideo";

interface PlaylistProps {
  videos: IVideo[];
  onVideoSelect: (video: IVideo) => void;
  currentVideo: IVideo;
}

const Playlist: React.FC<PlaylistProps> = ({ videos, onVideoSelect, currentVideo }) => {
  return (
    <StyledContainer>
      <StyledText>Próximos Vídeos</StyledText>
      {videos.map((video, index) => (
        <StyledItem 
          key={index} 
          onClick={() => onVideoSelect(video)} 
        >
          <StyledImage src={video.thumbnailUrl} alt={`Thumbnail ${index + 1}`} style={{ border: video.videoUrl === currentVideo.videoUrl ? '4px solid red' : 'none' }} />
          <StyledTitle>{video.title}</StyledTitle>
        </StyledItem>
      ))}
    </StyledContainer>
  );
};
export default Playlist;
