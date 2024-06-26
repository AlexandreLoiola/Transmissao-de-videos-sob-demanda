import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Footer from "../../Components/Footer/Footer";
import Header from "../../Components/Header/Header";
import PlaylistCover from "../../Components/PlaylistCover/PlaylistCover";
import SearchForm from "../../Components/SearchForm/SearchForm";
import { PlaylistCoverContainer } from "./styles";

const PlaylistsOverviewPage = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const navigate = useNavigate();
  const playlists = [
    { coverUrl: "src/assets/capa_playlist_paisagem.png", title: "Paisagens" },
    { coverUrl: "src/assets/capa_playlist_musica.jpeg", title: "Músicas" },
    { coverUrl: "src/assets/capa_playlist_esporte.png", title: "Esportes" },
  ];

  const filteredPlaylists = playlists.filter(playlist =>
    playlist.title.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const handleSearch = (query: string) => {
    setSearchQuery(query);
  };

  const handlePlaylistClick = (title: string) => {
    navigate("/streaming", { state: { title } });
  };

  return (
    <>
      <Header title={"Transmissão de Vídeo Sob Demanda"} />
      <SearchForm onSearch={handleSearch} />
      <PlaylistCoverContainer>
        {filteredPlaylists.length > 0 ? (
          filteredPlaylists.map((playlist, index) => (
            <PlaylistCover
              key={index}
              coverUrl={playlist.coverUrl}
              title={playlist.title}
              onClick={() => handlePlaylistClick(playlist.title)}
            />
          ))
        ) : (
          <p>Nenhuma playlist encontrada.</p>
        )}
      </PlaylistCoverContainer>
      <Footer />
    </>
  );
};

export default PlaylistsOverviewPage;