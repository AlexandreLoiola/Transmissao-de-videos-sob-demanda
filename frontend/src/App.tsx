import { Outlet } from "react-router-dom";
import { DeviceTypeProvider } from "./styles/DeviceTypeProvider";

const App: React.FC = () => {
  return (
    <div className="App">
      <DeviceTypeProvider>
        <Outlet />
      </DeviceTypeProvider>
    </div>
  );
};

export default App;