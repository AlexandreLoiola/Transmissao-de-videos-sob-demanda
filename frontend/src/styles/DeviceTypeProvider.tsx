import { ReactNode } from "react";
import { ThemeProvider } from "styled-components";
import { UseDeviceType } from "./UseDeviceType";

interface DeviceTypeProviderProps {
  children: ReactNode;
}

export const DeviceTypeProvider = ({ children }: DeviceTypeProviderProps) => {
  const deviceType = UseDeviceType();

  const extendedTheme = {
    deviceType,
  };

  return (
    <ThemeProvider theme={extendedTheme}>
    {children}
  </ThemeProvider>
  );
};