import PlaylistCover from "../../Components/PlaylistCover/PlaylistCover";
import { PlaylistCoverContainer } from "./styles";

const PlaylistsOverviewPage = () => {
  return (
    <>
      <PlaylistCoverContainer>
        <PlaylistCover
          coverUrl={"src/assets/WhatsApp Image 2024-05-28 at 11.27.37.jpeg"}
          title={"Musicas"}
        ></PlaylistCover>
        <PlaylistCover
          coverUrl={"src/assets/WhatsApp Image 2024-05-28 at 11.26.56.jpeg"}
          title={"Musicas"}
        ></PlaylistCover>
        <PlaylistCover
          coverUrl={"src/assets/Captura de tela 2024-05-25 092345.png"}
          title={"Musicas"}
        ></PlaylistCover>
      </PlaylistCoverContainer>
    </>
  );
};

export default PlaylistsOverviewPage;
