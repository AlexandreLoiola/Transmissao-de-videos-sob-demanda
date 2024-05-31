import { createBrowserRouter } from "react-router-dom";
import App from "../App";
import PlaylistsOverviewPage from "../pages/PlaylistsOverviewPage/PlaylistsOverviewPage";
import StreamingPage from "../pages/StreamingPage/StreamingPage";
import ErrorPage from "../pages/ErrorPage/ErrorPage";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: "/",
        element: <PlaylistsOverviewPage />,
      },
      {
        path: "/streaming",
        element: <StreamingPage />,
      },
    ],
  },
]);
export default router;
