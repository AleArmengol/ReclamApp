import React from "react";
import { Table, Button, Modal } from "react-bootstrap";
import "./ReclamosEdificio.css";

class ReclamosEdificio extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      seClickeo: false,
      reclamos: []
    };
  }

  componentDidMount() {
    var url =
      "http://localhost:8080/reclamapp/reclamosPorEdificio?nombre=" +
      sessionStorage.getItem("nombreEdificio");
    console.log(url);
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
          reclamos: res
        });
      });
  }

  handleOpenPopUp = (eventKey, event) => {
    this.setState({
      seClickeo: true
    });
  };

  handleClose = (eventKey, event) => {
    this.setState({
      seClickeo: false
    });
  };

  render() {
    console.log(this.state);
    var click = this.state.seClickeo;
    var popup;

    if (this.state.seClickeo) {
      popup = (
        <Modal show={true} onHide={this.handleClosePopUp}>
          <Modal.Body className="modal-body"></Modal.Body>
        </Modal>
      );
    } else {
      popup = "";
    }

    return (
      <div>
        {this.state.reclamos.map((reclamo, i) => (
          <div>
            <Table className="Tabla" striped bordered hover variant="dark">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Documento</th>
                  <th scope="col">Id</th>
                  <th scope="col">Descripcion</th>
                  <th scope="col">Estado</th>
                  <th scope="col">Imagenes Reclamo</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td width="5%">{i}</td>
                  <td width="10%">{reclamo.usuario.documento}</td>
                  <td width="1%">{reclamo.numero}</td>
                  <td width="55%">{reclamo.descripcion}</td>
                  <td width="10%">{reclamo.estado}</td>
                  <td width="13%">
                    <Button variant="primary" onClick={this.handleOpenPopUp}>
                      Ver Imagenes
                    </Button>
                  </td>
                </tr>
              </tbody>
            </Table>
          </div>
        ))}
        <Button
          variant="dark"
          style={{ marginTop: "5px", marginLeft: "5px" }}
          href="/MisEdificios"
          onClick={this.handleClosePopUp}
        >
          Volver a Mis Edificios
        </Button>
        <div>{popup}</div>
      </div>
    );
  }
}

export default ReclamosEdificio;
