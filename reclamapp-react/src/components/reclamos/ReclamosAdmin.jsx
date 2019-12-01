import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Container,Col,CardDeck,Image,Carousel,Row,Card,ListGroup,ListGroupItem,Toast,Spinner,Button, FormControl} from "react-bootstrap";
import './ReclamosAdmin.css';
import humedad from '../../photos/humedad.png';
import canilla from '../../photos/canilla.png';
import cerradura from '../../photos/cerradura.png';
import ascensor from '../../photos/ascensor.png';
import techo from '../../photos/techo.png';
import piso from '../../photos/piso.png';


export default class LogIn extends React.Component {
    render() {
        return (

            <Container>
                
               <label className = "label">Reclamos</label>
                    <Row className = "ml-auto" style ={{marginTop: '150px'}}>
                        <Col style={{marginTop:'50px'}}>
                         <Card style={{ width: '291px', height: '420px'}}>
                                <Card.Img variant="top" src={cerradura} />
                            <Card.Body>
                                <Card.Title>Nuevo Reclamo !!</Card.Title>
                                     <Card.Text>
                                      Se rompio la puta cerradura por decima vez. ARREGLENLO DE UNA VEZ HIJOS DE MIL PUTA
                                     </Card.Text>
                              <Button variant="outline-dark">Ver Reclamo</Button>
                            </Card.Body>
                        </Card>
                        </Col>
                        <Col style={{marginTop:'50px'}}>
                         <Card style={{ width: '291px', height: '420px'}}>
                                <Card.Img variant="top" src={canilla} />
                            <Card.Body>
                                <Card.Title>Nuevo Reclamo !!</Card.Title>
                                     <Card.Text>
                                      Me estaba bañando y se me rompio toda la canilla. Cansado de tanta pobreza :(
                                     </Card.Text>
                              <Button variant="outline-dark">Ver Reclamo</Button>
                            </Card.Body>
                        </Card>
                        </Col>
                        <Col style={{marginTop:'50px'}}>
                         <Card style={{ width: '291px', height: '420px'}}>
                                <Card.Img variant="top" src={humedad} />
                            <Card.Body>
                                <Card.Title>Nuevo Reclamo !!</Card.Title>
                                     <Card.Text>
                                      JAJAJAJA SE ME CAE A PEDAZOS LA CASA ME VUELVO LOCO
                                     </Card.Text>
                              <Button variant="outline-dark">Ver Reclamo</Button>
                            </Card.Body>
                        </Card>
                        </Col>
                        <Col style={{marginTop:'50px'}}>
                         <Card style={{ width: '291px', height: '420px'}}>
                                <Card.Img variant="top" src={ascensor} />
                            <Card.Body>
                                <Card.Title>Nuevo Reclamo !!</Card.Title>
                                     <Card.Text>
                                      El ascensor esta mas sucio que el culo de tu abuela
                                     </Card.Text>
                              <Button variant="outline-dark">Ver Reclamo</Button>
                            </Card.Body>
                        </Card>
                        </Col>
                        <Col style={{marginTop:'50px'}}>
                         <Card style={{ width: '291px', height: '420px'}}>
                                <Card.Img variant="top" src={techo} />
                            <Card.Body>
                                <Card.Title>Nuevo Reclamo !!</Card.Title>
                                     <Card.Text>
                                      Che se rompio el techito
                                     </Card.Text>
                              <Button variant="outline-dark">Ver Reclamo</Button>
                            </Card.Body>
                        </Card>
                        </Col>
                        <Col style={{marginTop:'50px'}}>
                         <Card style={{ width: '291px', height: '420px'}}>
                                <Card.Img variant="top" src={piso} />
                            <Card.Body>
                                <Card.Title>Nuevo Reclamo !!</Card.Title>
                                     <Card.Text>
                                      El piso esta mas roto que yo el finde pasado
                                     </Card.Text>
                              <Button variant="outline-dark">Ver Reclamo</Button>
                            </Card.Body>
                        </Card>
                        </Col>
                    </Row>
                    
            </Container>
        
        );
    }
}
