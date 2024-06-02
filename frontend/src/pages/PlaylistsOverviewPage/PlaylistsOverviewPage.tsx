
import Footer from "../../Components/Footer/Footer";
import Header from "../../Components/Header/Header";
import PlaylistCover from "../../Components/PlaylistCover/PlaylistCover";
import SearchForm from "../../Components/SearchForm/SearchForm";
import { PlaylistCoverContainer } from "./styles";

const PlaylistsOverviewPage = () => {
  return (
    <>
      <Header title={"Transmissão de Vídeo Sob Demanda"} />
      <SearchForm />
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
      <Footer />
    </>
  );
};

export default PlaylistsOverviewPage;
