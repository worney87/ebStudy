import React, { Component } from 'react';
import { Container, Row, Col, Button } from "react-bootstrap";
import BingoBoard from './BingoBoard';

class BoardSection extends Component {

    constructor(props) {
        super(props);
        this.state = {
            boards: Array(5).fill(null)
        };
    }

    generateBingoBoard = (index) => {
        const numbers = Array.from({ length: 15 }, (_, i) => i + 1);
        for (let i = numbers.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [numbers[i], numbers[j]] = [numbers[j], numbers[i]];
        }
        const newBoard = Array(3).fill(null).map((_, rowIndex) =>
            numbers.slice(rowIndex * 3, rowIndex * 3 + 3)
        );
        const newBoards = [...this.state.boards];
        newBoards[index] = newBoard;
        this.setState({ boards: newBoards });
    }

    removeBingoBoard = (index) => {
        const newBoards = [...this.state.boards];
        newBoards[index] = null;
        this.setState({ boards: newBoards });
    }

    render() {
        return (
            <Container>
                <Row>
                    {this.state.boards.map((board, index) => (
                        <Col key={index}>
                            {!board ? (
                                <Button
                                    variant={"primary"}
                                    onClick={() => this.generateBingoBoard(index)}
                                >
                                    +
                                </Button>
                            ) : (
                                <>
                                    <BingoBoard
                                        board={board}
                                        onRemove={() => this.removeBingoBoard(index)}
                                    />
                                    <span key={{index}}>player{index + 1}</span>
                                </>
                            )}
                        </Col>
                    ))}
                </Row>
            </Container>
        );
    }
}

export default BoardSection;
