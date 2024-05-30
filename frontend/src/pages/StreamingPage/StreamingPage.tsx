import axios from "axios";
import { IVideo } from "../../interfaces/IVideo";
import VideoPlayer from "../../Components/videoPlayer/VideoPlayer";
import Playlist from "../../Components/playlist/Playlist";
import { useEffect, useState } from "react";

const StreamingPage = () => {
  const [videos, setVideos] = useState([]);
  const [currentVideo, setCurrentVideo] = useState({
    videoUrl: "",
    title: "",
    description: "",
  });

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
      alert("Credenciais Inválidas!");
    }
  };

  useEffect(() => {
    handleVideos();
  }, []);

  const handleVideoSelect = (video: IVideo) => {
    setCurrentVideo(video);
  };

  return (
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
  );
};
export default StreamingPage;