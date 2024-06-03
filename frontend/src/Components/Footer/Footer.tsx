import { FooterContainer, StyledLink, Title } from "./styles";

const Footer: React.FC = () => {
  return (
    <FooterContainer>
      <Title>Desenvolvido por <StyledLink className="link-secondary" href="https://github.com/AlexandreLoiola" target="_blank" rel="noopener noreferrer">Alexandre Loiola</StyledLink></Title>
    </FooterContainer>
  );
};

export default Footer;