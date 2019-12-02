import React from "react";
import { Table, Button, handleClose } from "react-bootstrap";
import "./MisReclamos.css";

class MisReclamos extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      reclamos: []
    };
  }

  componentDidMount() {
    var url =
      "http://localhost:8080/reclamapp/reclamosPorPersona?documento=" +
      sessionStorage.getItem("documento");
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
  render() {
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
                  <td width="10%">{sessionStorage.getItem("documento")}</td>
                  <td width="1%">{reclamo.idReclamo}</td>
                  <td width="55%">{reclamo.descripcion}</td>
                  <td width="10%">{reclamo.estado}</td>
                  <td width="13%">
                    <Button variant="primary">Ver Imagenes</Button>
                  </td>
                </tr>
              </tbody>
            </Table>
          </div>
        ))}
      </div>
    );
  }
}
export default MisReclamos;
