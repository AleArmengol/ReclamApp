import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import {
  Container,
  Alert,
  Card,
  ListGroup,
  ListGroupItem
} from "react-bootstrap";
import "../ReclamoBuscado/ReclamoBuscado.css";
import cerradura from "../../photos/cerradura.png";

export default class ReclamoBuscado extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      reclamo: {},
      nombre: "",
      edificio: "",
      ubicacion: "",
      descripcion: "",
      estado: "",
      unidad: ""
    };
  }

  componentDidMount() {
    var url =
      "http://localhost:8080/reclamapp/reclamosPorNumero?numero=" +
      sessionStorage.getItem("numeroReclamo");
    fetch(url, {
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
          nombre: res.usuario.nombre,
          edificio: res.edificio.nombre + ", " + res.edificio.direccion,
          ubicacion: "Ubicacion: " + res.ubicacion,
          descripcion: "Descripcion: " + res.descripcion,
          estado: "Estado: " + res.estado,
          piso: "Piso: " + res.piso,
          numero: "numero: " + res.numero
        });
      });
  }
  render() {
    console.log(this.state);
    return (
      <Container>
        <label className="resultado">Resultado de su busqueda</label>
        <Card style={{ width: "500px", top: "120px", left: "300px" }}>
          <Card.Img variant="top" src={cerradura} />
          <Card.Body>
            <Card.Title>{this.state.nombre}</Card.Title>
            <Card.Subtitle className="mb-2 text-muted">
              {this.state.edificio}
            </Card.Subtitle>
            <Card.Text>{this.state.descripcion}</Card.Text>
          </Card.Body>
          <ListGroup className="list-group-flush">
            <ListGroupItem>{this.state.estado}</ListGroupItem>
            <ListGroupItem>{this.state.unidad}</ListGroupItem>
            <ListGroupItem>{this.state.ubicacion}</ListGroupItem>
          </ListGroup>
        </Card>
      </Container>
    );
  }
}
