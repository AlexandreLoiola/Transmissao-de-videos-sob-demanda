import { useNavigate } from "react-router-dom";
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
}

const PlaylistCover: React.FC<PlaylistCoverProps> = ({ coverUrl, title }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate("/streaming", { state: { title } });
  };

  return (
    <CoverContainer onClick={handleClick}>
      <CoverImage src={coverUrl} alt="Playlist cover" />
      <Overlay>
        <PlayIcon />
      </Overlay>
      <StyledTitle>{title}</StyledTitle>
    </CoverContainer>
  );
};

export default PlaylistCover;
