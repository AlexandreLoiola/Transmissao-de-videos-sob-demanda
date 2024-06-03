import styled from "styled-components";

export const PlaylistCoverContainer = styled.div`
  display: flex;
  flex-direction: row;
  height: 60vh;
  justify-content: center;
  align-items: center;
  padding: ${(props) =>
      props.theme.deviceType === "mobile" ? "0px" : "40px 100px"};
  flex-wrap: wrap;
  gap: ${(props) =>
      props.theme.deviceType === "mobile" ? "50px" : "80px 50px"};
`;