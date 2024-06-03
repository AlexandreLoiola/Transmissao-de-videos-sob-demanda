import styled from "styled-components"

export const StyledContainer = styled.div`
    display: flex;
    flex-direction: ${(props) =>
      props.theme.deviceType === "mobile" ? "column" : "row"};
`;