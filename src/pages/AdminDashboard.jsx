import { useEffect, useState } from "react";

import axios from "axios";

import {
    Container,
    Row,
    Col,
    Card
} from "react-bootstrap";

import { toast, ToastContainer } from "react-toastify";

import CandidateForm from "../components/CandidateForm";
import CandidateList from "../components/CandidateList";

function AdminDashboard() {

    const [formData, setFormData] = useState({
        name: "",
        email: "",
        score: "",
        courseName: "",
        organizationName: "",
        coordinatorName: ""
    });

    const [candidates, setCandidates] = useState([]);

    const handleChange = (e) => {

        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const fetchCandidates = async () => {

        const response = await axios.get(
            "http://localhost:8084/admin/candidates"
        );

        setCandidates(response.data);
    };

    useEffect(() => {

        fetchCandidates();

    }, []);

    const createCandidate = async () => {

        try {

            await axios.post(
                "http://localhost:8084/admin/candidates",
                formData
            );

            fetchCandidates();

            toast.success("Candidate Added Successfully");

            setFormData({
                name: "",
                email: "",
                score: "",
                courseName: "",
                organizationName: "",
                coordinatorName: ""
            });

        } catch (error) {

            toast.error("Failed to add candidate");
        }
    };

    const deleteCandidate = async (id) => {

        try {

            await axios.delete(
                `http://localhost:8084/admin/candidates/${id}`
            );

            fetchCandidates();

            toast.warning("Candidate Deleted");

        } catch (error) {

            toast.error("Delete Failed");
        }
    };

    return (

        <div
            style={{
                minHeight: "100vh",
                background:
                    "linear-gradient(to right, #141e30, #243b55)",
                padding: "40px"
            }}
        >

            <Container>

                <Row className="justify-content-center">

                    <Col lg={10}>

                        <Card className="shadow-lg border-0 rounded-4">

                            <Card.Body className="p-4">

                                <h1 className="text-center fw-bold mb-4">

                                    <i className="bi bi-award-fill me-2"></i>

                                    Admin Dashboard

                                </h1>

                                <CandidateForm
                                    formData={formData}
                                    handleChange={handleChange}
                                    createCandidate={createCandidate}
                                />

                                <hr className="my-5" />

                                <CandidateList
                                    candidates={candidates}
                                    deleteCandidate={deleteCandidate}
                                />

                            </Card.Body>

                        </Card>

                    </Col>

                </Row>

            </Container>

            <ToastContainer position="top-right" autoClose={3000} />

        </div>
    );
}

export default AdminDashboard;