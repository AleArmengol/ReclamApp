import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import {
  Container,
  Col,
  CardDeck,
  Image,
  Carousel,
  Row,
  Card,
  ListGroup,
  ListGroupItem,
  Toast,
  Spinner,
  Button,
  FormControl,
  DropdownButton,
  DropdownItem,
  Dropdown,
  ButtonGroup
} from "react-bootstrap";
import "./ReclamosAdmin.css";
import humedad from "../../photos/humedad.png";
import canilla from "../../photos/canilla.png";
import cerradura from "../../photos/cerradura.png";
import ascensor from "../../photos/ascensor.png";
import techo from "../../photos/techo.png";
import piso from "../../photos/piso.png";

export default class LogIn extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      reclamosAdmin: [],
      estados: [
        "abierto",
        "anulado",
        "desestimado",
        "en proceso",
        "nuevo",
        "terminado"
      ],
      estadoSeleccionado: ""
    };
  }

  componentDidMount() {
    fetch("http://localhost:8080/reclamapp/getAllReclamos", {
      method: "GET", // *GET, POST, PUT, DELETE, etc.
      mode: "cors", // no-cors, *cors, same-origin
      cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
      credentials: "same-origin", // include, *same-origin, omit
      headers: {
        "Content-Type": "application/json"
        // 'Content-Type': 'application/x-www-form-urlencoded',
      }
    })
      .then(response => {
        return response.json();
      })
      .then(res => {
        this.setState({
          reclamosAdmin: res
        });
      });
  }

  handleSelect(eventKey, event) {
    var estSelec = eventKey.slice(2);
    this.setState({
      estadoSeleccionado: estSelec
    });
  }

  actualizarEstado(reclamo) {
    var numeroReclamo = reclamo.reclamoAdmin.numero;
    var url =
      "http://localhost:8080/reclamapp/cambiarEstado?numero=" +
      numeroReclamo +
      "&estado=" +
      this.state.estadoSeleccionado;
    fetch(url, {
      method: "PUT", // *GET, POST, PUT, DELETE, etc.
      mode: "cors", // no-cors, *cors, same-origin
      cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
      credentials: "same-origin", // include, *same-origin, omit
      headers: {
        "Content-Type": "application/json"
        // 'Content-Type': 'application/x-www-form-urlencoded',
      }
    })
      .then()
      .then(() => {
        window.open("/ReclamosAdmin", "_top");
      });
  }
  render() {
    console.log(this.state.reclamosAdmin);
    return (
      <div class="row">
        {this.state.reclamosAdmin.map((reclamoAdmin, i) => (
          <div>
            <Container>
              <label className="label">Reclamos</label>
              <Row style={{ marginTop: "150px" }}>
                <Col style={{ marginTop: "50px" }}>
                  <Card style={{ width: "291px", height: "325px" }}>
                    {/* <Card.Img variant="top" src={cerradura} /> */}
                    <Card.Body>
                      <Card.Title>{reclamoAdmin.edificio.nombre}</Card.Title>
                      <Card.Text>{reclamoAdmin.edificio.direccion} </Card.Text>
                      <Card.Text>
                        Numero de Reclamo: {reclamoAdmin.numero}{" "}
                      </Card.Text>

                      <Card.Text>
                        Ubicacion: {reclamoAdmin.ubicacion}{" "}
                      </Card.Text>
                      <Card.Text>
                        Estado actual: {reclamoAdmin.estado}{" "}
                      </Card.Text>

                      <Dropdown as={ButtonGroup}>
                        <Button
                          onClick={this.actualizarEstado.bind(this, {
                            reclamoAdmin
                          })}
                          variant="outline-dark"
                        >
                          Aplicar cambio
                        </Button>

                        <Dropdown.Toggle
                          split
                          variant="outline-dark"
                          id="dropdown-split-basic"
                        />

                        <Dropdown.Menu>
                          <Dropdown.Item
                            href="#/abierto"
                            onSelect={this.handleSelect.bind(this)}
                          >
                            Abierto
                          </Dropdown.Item>
                          <Dropdown.Item
                            href="#/anulado"
                            onSelect={this.handleSelect.bind(this)}
                          >
                            Anulado
                          </Dropdown.Item>
                          <Dropdown.Item
                            href="#/desestimado"
                            onSelect={this.handleSelect.bind(this)}
                          >
                            desestimado
                          </Dropdown.Item>
                          <Dropdown.Item
                            href="#/enProceso"
                            onSelect={this.handleSelect.bind(this)}
                          >
                            En proceso
                          </Dropdown.Item>
                          <Dropdown.Item
                            href="#/nuevo"
                            onSelect={this.handleSelect.bind(this)}
                          >
                            Nuevo
                          </Dropdown.Item>
                          <Dropdown.Item
                            href="#/terminado"
                            onSelect={this.handleSelect.bind(this)}
                          >
                            Terminado
                          </Dropdown.Item>
                        </Dropdown.Menu>
                      </Dropdown>
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
