import { useState } from "react";

import axios from "axios";

import {
    Container,
    Card,
    Button,
    Form,
    InputGroup
} from "react-bootstrap";

import {
    toast,
    ToastContainer
} from "react-toastify";

function CandidatePortal() {

    const [searchValue, setSearchValue] = useState("");

    const [loading, setLoading] = useState(false);

    const downloadCertificate = async () => {

        if (!searchValue.trim()) {

            toast.warning(
                "Please enter Candidate ID or Email"
            );

            return;
        }

        try {

            setLoading(true);

            const response = await axios.get(

                `http://localhost:8084/candidate/certificates/${searchValue}`,

                {
                    responseType: "blob"
                }
            );

            const url = window.URL.createObjectURL(

                new Blob([response.data])
            );

            const link =
                    document.createElement("a");

            link.href = url;

            link.setAttribute(
                "download",
                "certificate.pdf"
            );

            document.body.appendChild(link);

            link.click();

            link.remove();

            toast.success(
                "Certificate Downloaded Successfully"
            );

            setSearchValue("");

        } catch (error) {

            if (error.response?.status === 404) {

                toast.error(
                    "Candidate Not Found"
                );

            } else if (
                error.response?.status === 400
            ) {

                toast.warning(
                    "Candidate Not Eligible"
                );

            } else {

                toast.error(
                    "Something Went Wrong"
                );
            }

        } finally {

            setLoading(false);
        }
    };

    return (

        <div
            style={{
                minHeight: "100vh",
                background:
                    "linear-gradient(to right, #000428, #004e92)",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                padding: "20px"
            }}
        >

            <Container>

                <Card
                    className="shadow-lg border-0 rounded-4"
                >

                    <Card.Body className="p-5">

                        <div className="text-center mb-4">

                            <i
                                className="bi bi-file-earmark-pdf-fill"
                                style={{
                                    fontSize: "60px",
                                    color: "#0d6efd"
                                }}
                            ></i>

                            <h1 className="fw-bold mt-3">

                                Candidate Portal

                            </h1>

                            <p className="text-muted">

                                Enter your Candidate ID
                                or Registered Email

                            </p>

                        </div>

                        <InputGroup className="mb-4">

                            <Form.Control
                                type="text"
                                placeholder="Enter ID or Email"
                                value={searchValue}
                                onChange={(e) =>
                                    setSearchValue(
                                        e.target.value
                                    )
                                }
                            />

                        </InputGroup>

                        <div className="d-grid">

                            <Button
                                variant="primary"
                                size="lg"
                                onClick={downloadCertificate}
                                disabled={loading}
                            >

                                {
                                    loading
                                        ? "Downloading..."
                                        : "Download Certificate"
                                }

                            </Button>

                        </div>

                    </Card.Body>

                </Card>

            </Container>

            <ToastContainer
                position="top-right"
                autoClose={3000}
            />

        </div>
    );
}

export default CandidatePortal;