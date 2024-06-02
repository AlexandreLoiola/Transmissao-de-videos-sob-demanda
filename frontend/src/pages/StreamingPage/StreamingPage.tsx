import axios from "axios";
import { IVideo } from "../../interfaces/IVideo";
import VideoPlayer from "../../Components/videoPlayer/VideoPlayer";
import Playlist from "../../Components/playlist/Playlist";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Header from "../../Components/Header/Header";

const StreamingPage = () => {
  const [videos, setVideos] = useState([]);
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
        "http://localhost:8080/v1/api/streaming-service/video"
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
      <Header title={"Transmissão de Vídeo Sob Demanda"} showBackIcon onBackClick={handlePreviousPage} />
      <div style={{ display: "flex", flexDirection: "row" }}>
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
      </div>
    </>
  );
};
export default StreamingPage;
