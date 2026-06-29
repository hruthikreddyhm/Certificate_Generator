import { Navbar, Nav, Container } from "react-bootstrap";
import { Link } from "react-router-dom";

function NavbarComponent() {

    return (

        <Navbar
            bg="dark"
            variant="dark"
            expand="lg"
            className="shadow"
            sticky="top"
        >

            <Container>

                <Navbar.Brand
                    as={Link}
                    to="/"
                    className="fw-bold"
                >

                    <i className="bi bi-award-fill me-2"></i>

                    CertifyPro

                </Navbar.Brand>

                <Navbar.Toggle aria-controls="basic-navbar-nav" />

                <Navbar.Collapse id="basic-navbar-nav">

                    <Nav className="ms-auto">

                        <Nav.Link
                            as={Link}
                            to="/"
                        >
                            Admin Dashboard
                        </Nav.Link>

                        <Nav.Link
                            as={Link}
                            to="/candidate"
                        >
                            Candidate Portal
                        </Nav.Link>

                    </Nav>

                </Navbar.Collapse>

            </Container>

        </Navbar>
    );
}

export default NavbarComponent;