import styled from "styled-components";
import { TbPlayerPlay } from "react-icons/tb";

export const CoverContainer = styled.div`
  position: relative;
  width: 340px;
  height: 200px;
  cursor: pointer;
  transition: transform 0.3s ease-in-out;

  &:hover {
    transform: scale(1.1);
  }
`;

export const CoverImage = styled.img`
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 12px; 
`;

export const Overlay = styled.div`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.35);
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 12px;
  transition: transform 0.3s ease-in-out;

  &:hover {
    background-color: rgba(0, 0, 0, 0.1);
  }
`;

export const PlayIcon = styled(TbPlayerPlay)`
  width: 50px;
  height: 50px;
  color: white;
`;

export const StyledTitle = styled.h1`
  margin-top: 2px;
  display: flex;
  justify-content: center;
  font-family: Roboto;
  font-size: 24px;
  font-weight: 600;
  text-align: left;
  color: black;
`;