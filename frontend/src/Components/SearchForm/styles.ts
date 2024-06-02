import styled from "styled-components";
import { MdSearch, MdFilterListAlt } from "react-icons/md";
import { Button, FormControl } from "react-bootstrap";

export const SearchFormContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 40px;
`;

export const StyledForm = styled.form`
  display: flex;
  width: 100%;
  padding: 0 30vw;
  margin-bottom: 0rem !important;
  input {
    margin-left: 5px;
    margin-right: 5px;
  }
  .mb-2 {
    margin-bottom: 0 !important;
  }
  .dropdown-toggle::after {
    display: none;
  }
`;

export const SearchButton = styled(Button)`
  border-radius: 6px 0 0 6px;
  margin-right: -6px;
`;

export const StyledInput = styled(FormControl)`
    border-radius: 0 6px 6px 0;
`;

export const SearchIcon = styled(MdSearch)`
  width: 22px;
  height: 22px;
`;

export const FilterhIcon = styled(MdFilterListAlt)``;
