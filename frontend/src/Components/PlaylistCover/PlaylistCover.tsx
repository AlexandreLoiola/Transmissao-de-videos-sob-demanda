import { CoverContainer, CoverImage, Overlay, PlayIcon, StyledTitle } from "./styles";

interface PlaylistCoverProps {
    coverUrl: string;
    title: string;
  }

const PlaylistCover: React.FC<PlaylistCoverProps> = ({ coverUrl, title }) => {
    return (
      <CoverContainer>
        <CoverImage src={coverUrl} alt="Playlist cover" />
        <Overlay>
          <PlayIcon />
        </Overlay>
        <StyledTitle>{title}</StyledTitle>
      </CoverContainer>
    );
  };
  
  export default PlaylistCover;