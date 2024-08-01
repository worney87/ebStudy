import React from 'react';
import { Table, Button } from "react-bootstrap";

const BingoBoard = ({ board, onRemove }) => (
    <>
        <Button
            variant={"outline-danger"}
            onClick={onRemove}
        >
            x
        </Button>
        <Table className='mt-3' style={{ backgroundColor: 'transparent' }}>
            <tbody>
            {board.map((row, rowIndex) => (
                <tr key={rowIndex}>
                    {row.map((cell, colIndex) => (
                        <td key={colIndex}>{cell}</td>
                    ))}
                </tr>
            ))}
            </tbody>
        </Table>
    </>
);

export default BingoBoard;
