import React from "react";
import { Container, Col, Image, Row, Card, Button } from "react-bootstrap";
import "./MisEdificios.css";
import imagenCard from "../../photos/Edificio.jpg";

class MisEdificios extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      edificios: []
    };
  }
  componentDidMount() {
    fetch(
      "http://localhost:8080/reclamapp/edificiosDePersona?documento=" +
        sessionStorage.getItem("documento"),
      {
        method: "GET", // *GET, POST, PUT, DELETE, etc.
        mode: "cors", // no-cors, *cors, same-origin
        cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
        credentials: "same-origin", // include, *same-origin, omit
        headers: {
          "Content-Type": "application/json"
          // 'Content-Type': 'application/x-www-form-urlencoded',
        }
      }
    )
      .then(response => response.json())
      .then(edificios => this.setState({ edificios }));
  }

  verReclamo(nombreEdificio) {
    sessionStorage.setItem("nombreEdificio", nombreEdificio.nombreEdificio);
    window.open("/ReclamosEdificio", "_top");
  }

  render() {
    return (
      <div class="row">
        {this.state.edificios.map((nombreEdificio, i) => (
          <div>
            <Container>
              <Row style={{ marginTop: "2px" }}>
                <Col style={{ marginTop: "50px" }}>
                  <Card style={{ width: "291px", height: "325px" }}>
                    <Card.Img variant="top" src={imagenCard} />
                    <Card.Body>
                      <Card.Title>{nombreEdificio}</Card.Title>
                      <Button
                        key={i}
                        eventKey={i}
                        onClick={this.verReclamo.bind(this, { nombreEdificio })}
                        variant="outline-dark"
                      >
                        Ver Reclamos
                      </Button>
                    </Card.Body>
                  </Card>
                </Col>
              </Row>
            </Container>
          </div>
        ))}
      </div>
    );
  }
}

export default MisEdificios;
