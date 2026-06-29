import {
    Card,
    Button,
    Row,
    Col,
    Badge
} from "react-bootstrap";

function CandidateList({

    candidates,
    deleteCandidate

}) {

    return (

        <div>

            <h3 className="fw-bold mb-4">
                Candidate List
            </h3>

            <Row>

                {

                    candidates.map((candidate) => (

                        <Col
                            md={6}
                            lg={4}
                            key={candidate.id}
                            className="mb-4"
                        >

                            <Card className="shadow border-0 rounded-4 h-100">

                                <Card.Body>

                                    <h4 className="fw-bold">
                                        {candidate.name}
                                    </h4>

                                    <p className="mb-2">
                                        <strong>Email:</strong>
                                        {" "}
                                        {candidate.email}
                                    </p>

                                    <p className="mb-2">
                                        <strong>Course:</strong>
                                        {" "}
                                        {candidate.courseName}
                                    </p>

                                    <Badge
                                        bg={
                                            candidate.score >= 80
                                                ? "success"
                                                : "warning"
                                        }
                                        className="mb-3"
                                    >
                                        Score:
                                        {" "}
                                        {candidate.score}
                                    </Badge>

                                    <div className="d-grid">

                                        <Button
                                            variant="danger"
                                            onClick={() =>
                                                deleteCandidate(candidate.id)
                                            }
                                        >
                                            Delete
                                        </Button>

                                    </div>

                                </Card.Body>

                            </Card>

                        </Col>
                    ))
                }

            </Row>

        </div>
    );
}

export default CandidateList;