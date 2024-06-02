import { useEffect, useState } from "react";

export const UseDeviceType = () => {
  const [deviceType, setDeviceType] = useState('');

  useEffect(() => {
    const deviceTypes = [
      { type: 'mobile', query: '(max-width: 768px)' },
      { type: 'tablet', query: '(min-width: 769px) and (max-width: 1024px)' },
      { type: 'desktop', query: '(min-width: 1025px)' },
    ];

    const checkDeviceType = () => {
      for (const { type, query } of deviceTypes) {
        if (window.matchMedia(query).matches) {
          if (deviceType !== type) {
            setDeviceType(type);
          }
          break;
        }
      }
    };

    checkDeviceType();
    window.addEventListener('resize', checkDeviceType);

    return () => {
      window.removeEventListener('resize', checkDeviceType);
    };
  }, []);

  return deviceType;
};