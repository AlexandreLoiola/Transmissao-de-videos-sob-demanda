import {
  CoverContainer,
  CoverImage,
  Overlay,
  PlayIcon,
  StyledTitle,
} from "./styles";

interface PlaylistCoverProps {
  coverUrl: string;
  title: string;
  onClick?: () => void;
}

const PlaylistCover: React.FC<PlaylistCoverProps> = ({ coverUrl, title, onClick }) => {
  return (
    <CoverContainer onClick={onClick}>
      <CoverImage src={coverUrl} alt="Playlist cover" />
      <Overlay>
        <PlayIcon />
      </Overlay>
      <StyledTitle>{title}</StyledTitle>
    </CoverContainer>
  );
};

export default PlaylistCover;
