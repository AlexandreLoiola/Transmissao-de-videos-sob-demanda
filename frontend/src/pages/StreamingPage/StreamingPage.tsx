import axios from "axios";
import { IVideo } from "../../interfaces/IVideo";
import VideoPlayer from "../../Components/videoPlayer/VideoPlayer";
import Playlist from "../../Components/playlist/Playlist";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Header from "../../Components/Header/Header";
import { StyledContainer } from "./styles";
const apiUrl = import.meta.env.VITE_API_URL;

const StreamingPage = () => {
  const [videos, setVideos] = useState<IVideo[]>([]);
  const [currentVideo, setCurrentVideo] = useState({
    videoUrl: "",
    title: "",
    description: "",
  });

  const location = useLocation();
  const { title } = location.state;

  const handleVideos = async (): Promise<void> => {
    try {
      const response = await axios.get(
        `${apiUrl}/video`
      );
      console.log(response.data);
      setVideos(response.data);
      if (response.data.length > 0) {
        setCurrentVideo(response.data[0]);
      }
    } catch (error) {
      console.error(error);
      alert("Um erro ocorreu");
    }
  };

  useEffect(() => {
    handleVideos();
  }, []);

  const handleVideoSelect = (video: IVideo) => {
    setCurrentVideo(video);
  };
  
  const navigate = useNavigate();

  const handlePreviousPage = () => {
    navigate("/", { state: { title } });
  };

  return (
    <>
      <Header title={"Playlist: " + title} showBackIcon onBackClick={handlePreviousPage} />
      <StyledContainer>
        <VideoPlayer
          videoUrl={currentVideo.videoUrl}
          title={currentVideo.title}
          description={currentVideo.description}
        />
        <Playlist
          videos={videos}
          onVideoSelect={handleVideoSelect}
          currentVideo={currentVideo}
        />
      </StyledContainer>
    </>
  );
};

export default StreamingPage;