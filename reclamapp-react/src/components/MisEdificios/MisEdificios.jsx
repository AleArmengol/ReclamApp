import React from "react";
import { Card, CardBody, Image, map } from 'react-bootstrap';
import './MisEdificios.css';

class MisEdificios extends React.Component {
    render() {
        var ed = ["Torre1", "Torre2", "Torre3", "Torre4"];
        return (
            <div>
                {ed.map((nombreEdificio, i) => (
                    <div>
                        <Card style={{ width: '18rem' }}>
                            <Card.Body>
                                <Card.Title>{nombreEdificio}</Card.Title>
                                <Card.Link href="/ReclamosEdificio">Ver Reclamos</Card.Link>
                            </Card.Body>
                        </Card>
                    </div>
                ))}
            </div>
        );
    }
}
export default MisEdificios;
