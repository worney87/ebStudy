import React, {Component} from 'react';
import {Container, Row, Col} from 'react-bootstrap';


class ControlSection extends Component {
    render() {
        return (
            <div>
                <Container>
                    <Row>
                        <Col>1 of 2</Col>
                        <Col>2 of 2</Col>
                    </Row>
                </Container>
            </div>
        );
    }
}

export default ControlSection;