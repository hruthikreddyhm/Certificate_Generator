import {
    Form,
    Button,
    Row,
    Col
} from "react-bootstrap";

function CandidateForm({

    formData,
    handleChange,
    createCandidate

}) {

    return (

        <div>

            <h3 className="mb-4 fw-bold">
                Add Candidate
            </h3>

            <Row>

                <Col md={6} className="mb-3">

                    <Form.Control
                        type="text"
                        name="name"
                        placeholder="Name"
                        value={formData.name}
                        onChange={handleChange}
                    />

                </Col>

                <Col md={6} className="mb-3">

                    <Form.Control
                        type="email"
                        name="email"
                        placeholder="Email"
                        value={formData.email}
                        onChange={handleChange}
                    />

                </Col>

                <Col md={6} className="mb-3">

                    <Form.Control
                        type="number"
                        name="score"
                        placeholder="Score"
                        value={formData.score}
                        onChange={handleChange}
                    />

                </Col>

                <Col md={6} className="mb-3">

                    <Form.Control
                        type="text"
                        name="courseName"
                        placeholder="Course Name"
                        value={formData.courseName}
                        onChange={handleChange}
                    />

                </Col>

                <Col md={6} className="mb-3">

                    <Form.Control
                        type="text"
                        name="organizationName"
                        placeholder="Organization Name"
                        value={formData.organizationName}
                        onChange={handleChange}
                    />

                </Col>

                <Col md={6} className="mb-3">

                    <Form.Control
                        type="text"
                        name="coordinatorName"
                        placeholder="Coordinator Name"
                        value={formData.coordinatorName}
                        onChange={handleChange}
                    />

                </Col>

            </Row>

            <div className="d-grid">

                <Button
                    variant="dark"
                    size="lg"
                    onClick={createCandidate}
                >
                    Add Candidate
                </Button>

            </div>

        </div>
    );
}

export default CandidateForm;