import {
    Container,
    Row,
    Col,
    Card,
    Button
} from "react-bootstrap";

import { Link } from "react-router-dom";

function Home() {

    return (

        <div
            style={{
                minHeight: "100vh",
                background:
                    "linear-gradient(to right, #141e30, #243b55)",
                display: "flex",
                alignItems: "center"
            }}
        >

            <Container>

                <Row className="justify-content-center">

                    <Col lg={10}>

                        <div className="text-center text-white mb-5">

                            <h1
                                className="fw-bold display-3 mb-3"
                            >

                                Certificate Management System

                            </h1>

                            <p className="lead">

                                Generate, manage, and download
                                certificates easily using a modern
                                dashboard.

                            </p>

                        </div>

                        <Row>

                            <Col md={6} className="mb-4">

                                <Card
                                    className="shadow-lg border-0 rounded-4 h-100"
                                >

                                    <Card.Body className="p-5 text-center">

                                        <i
                                            className="bi bi-person-workspace"
                                            style={{
                                                fontSize: "60px"
                                            }}
                                        ></i>

                                        <h2 className="fw-bold mt-4">

                                            Admin Dashboard

                                        </h2>

                                        <p className="text-muted mt-3">

                                            Add candidates, manage records,
                                            and control certificate generation.

                                        </p>

                                        <div className="d-grid mt-4">

                                            <Button
                                                as={Link}
                                                to="/admin"
                                                variant="dark"
                                                size="lg"
                                            >
                                                Go To Dashboard
                                            </Button>

                                        </div>

                                    </Card.Body>

                                </Card>

                            </Col>

                            <Col md={6} className="mb-4">

                                <Card
                                    className="shadow-lg border-0 rounded-4 h-100"
                                >

                                    <Card.Body className="p-5 text-center">

                                        <i
                                            className="bi bi-file-earmark-arrow-down-fill"
                                            style={{
                                                fontSize: "60px"
                                            }}
                                        ></i>

                                        <h2 className="fw-bold mt-4">

                                            Candidate Portal

                                        </h2>

                                        <p className="text-muted mt-3">

                                            Download certificates instantly
                                            using your candidate ID.

                                        </p>

                                        <div className="d-grid mt-4">

                                            <Button
                                                as={Link}
                                                to="/candidate"
                                                variant="primary"
                                                size="lg"
                                            >
                                                Open Portal
                                            </Button>

                                        </div>

                                    </Card.Body>

                                </Card>

                            </Col>

                        </Row>

                    </Col>

                </Row>

            </Container>

        </div>
    );
}

export default Home;