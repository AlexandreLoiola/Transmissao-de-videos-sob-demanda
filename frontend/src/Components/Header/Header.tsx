import { FaArrowLeft } from "react-icons/fa";
import { HeaderContainer, IconContainer, Title } from "./styles";

interface HeaderProps {
  title: string;
  showBackIcon?: boolean;
  onBackClick?: () => void;
}

const Header: React.FC<HeaderProps> = ({
  title,
  showBackIcon = false,
  onBackClick,
}) => {
  return (
    <HeaderContainer>
      {showBackIcon && (
        <IconContainer onClick={onBackClick}>
          <FaArrowLeft size={24} />
        </IconContainer>
      )}
      <Title>{title}</Title>
    </HeaderContainer>
  );
};

export default Header;
