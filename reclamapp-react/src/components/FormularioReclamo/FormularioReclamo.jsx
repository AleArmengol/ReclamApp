import React, { Component } from "react";
import { Form, DropdownButton, FormGroup, Button } from "react-bootstrap";
import DropdownItem from "react-bootstrap/DropdownItem";
import Modal from "react-bootstrap/Modal";

class FormularioReclamo extends Component {
  constructor(props) {
    super(props);
    this.state = {
      seleccionoLugar: false,
      lugares: ["Espacio Comun del edificio", "Dentro de la Unidad"],
      dondeOcurrio: "¿Donde ocurrio su Reclamo?",
      dentroUnidad: false,
      nombreEdificios: [],
      edificioSeleccionado: "Seleccione un Edificio",
      seleccionoEdificio: false,
      unidadSeleccionada: "Seleccione una Unidad",
      unidades: [],
      piso: "",
      numero: "",
      descripcion: "",
      popup: "",
      seAgregoReclamo: false
    };
  }

  componentDidMount() {
    sessionStorage.setItem("documento", "DNI29988738");
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
      .then(nombreEdificios => this.setState({ nombreEdificios }));
  }

  handleSelectEdificio = (eventKey, event) => {
    this.setState(
      {
        edificioSeleccionado: this.state.nombreEdificios[eventKey],
        seleccionoEdificio: true
      },
      () => {
        var url =
          "http://localhost:8080/reclamapp/getUnidadesPorNombreEdificio?nombre=" +
          this.state.edificioSeleccionado;
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
          .then(response => response.json())
          .then(unidades => this.setState({ unidades }));
      }
    );
  };

  //http://localhost:8080/reclamapp/agregarReclamoDentroUnidad?nombre=SLS Puerto Madero&piso=10&numero=6&documento=DNI41893184&descripcion=Rotura canilla
  agregarReclamo() {
    this.setState({
      descripcion: document.getElementById("exampleForm.ControlTextarea1").value
    });
    if (this.state.seleccionoEdificio && this.state.seleccionoLugar) {
      if (this.state.dentroUnidad) {
        var url =
          "http://localhost:8080/reclamapp/agregarReclamoDentroUnidad?nombre=" +
          this.state.edificioSeleccionado +
          "&piso=" +
          this.state.piso +
          "&numero=" +
          this.state.numero +
          "&documento=" +
          sessionStorage.getItem("documento") +
          "&descripcion=" +
          document.getElementById("exampleForm.ControlTextarea1").value;
        fetch(url, {
          method: "POST", // *GET, POST, PUT, DELETE, etc.
          mode: "cors", // no-cors, *cors, same-origin
          cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
          credentials: "same-origin", // include, *same-origin, omit
          headers: {
            "Content-Type": "application/json"
            // 'Content-Type': 'application/x-www-form-urlencoded',
          }
        })
          .then(response => {
            return response.text();
          })
          .then(data => {
            this.setState({
              popup: data,
              seAgregoReclamo: true
            });
          });
      } else {
        //http://localhost:8080/reclamapp/agregarReclamoEspacioComun?nombre=Alvear Tower&documento=DNI41893184&descripcion=No funciona el elevador al piso 3
        var url2 =
          "http://localhost:8080/reclamapp/agregarReclamoEspacioComun?nombre=" +
          this.state.edificioSeleccionado +
          "&documento=" +
          sessionStorage.getItem("documento") +
          "&descripcion=" +
          document.getElementById("exampleForm.ControlTextarea1").value;
        fetch(url2, {
          method: "POST", // *GET, POST, PUT, DELETE, etc.
          mode: "cors", // no-cors, *cors, same-origin
          cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
          credentials: "same-origin", // include, *same-origin, omit
          headers: {
            "Content-Type": "application/json"
            // 'Content-Type': 'application/x-www-form-urlencoded',
          }
        })
          .then(response => {
            return response.text();
          })
          .then(data => {
            this.setState({
              popup: data,
              seAgregoReclamo: true
            });
          });
      }
    }
  }

  handleSelectUnidad = (eventKey, event) => {
    this.setState({
      piso: this.state.unidades[eventKey].piso,
      numero: this.state.unidades[eventKey].numero,
      unidadSeleccionada:
        "Piso: " +
        this.state.unidades[eventKey].piso +
        " Numero: " +
        this.state.unidades[eventKey].numero
    });
  };

  handleSelectDonde = (eventKey, event) => {
    eventKey = parseInt(eventKey); //convierte eventKey(Que es un string) a un int
    this.setState({
      dondeOcurrio: this.state.lugares[eventKey],
      seleccionoLugar: true,
      dentroUnidad: eventKey
    });
  };

  handleClosePopUp = (eventKey, event) => {
    this.setState({
      popup: "",
      seAgregoReclamo: false
    });
  };

  render() {
    var dentroUnidad = this.state.dentroUnidad;
    //console.log(dentroUnidad);
    console.log("state popup: " + this.state.popup);
    console.log("Se agrego reclamo: " + this.state.seAgregoReclamo);
    var mostrar;
    var popup;
    if (this.state.seAgregoReclamo) {
      popup = (
        <Modal show={true} onHide={this.handleClosePopUp}>
          <Modal.Header closeButton>
            <Modal.Title>Reclamo Enviado</Modal.Title>
          </Modal.Header>
          <Modal.Body>{this.state.popup}</Modal.Body>
          <Modal.Footer>
            <Button variant="primary" onClick={this.handleClosePopUp}>
              Entendido!
            </Button>
          </Modal.Footer>
        </Modal>
      );
    }
    if (dentroUnidad) {
      mostrar = (
        <div>
          <h1></h1>
          <DropdownButton
            title={this.state.unidadSeleccionada}
            onSelect={this.handleSelectUnidad.bind(this)}
          >
            {this.state.unidades.reverse().map((unidad, i) => (
              <DropdownItem key={i} eventKey={i}>
                Piso: {unidad.piso} Numero: {unidad.numero}
              </DropdownItem>
            ))}
          </DropdownButton>
          <Form.Group controlId="exampleForm.ControlTextarea1">
            <Form.Label>Descripcion</Form.Label>
            <Form.Control
              as="textarea"
              rows="3"
              placeholder="Describa su reclamo..."
            />
          </Form.Group>
          <FormGroup>
            Seleccione una imagen para adjuntar a su Reclamo
            <input type="file" name="imagen"></input>
          </FormGroup>
        </div>
      );
    } else {
      mostrar = (
        <div>
          <Form.Group controlId="exampleForm.ControlTextarea1">
            <Form.Label>Descripcion</Form.Label>
            <Form.Control
              as="textarea"
              rows="3"
              inputRef={ref => {
                this.descripcion = ref;
              }}
            />
          </Form.Group>
          <FormGroup>
            Seleccione una imagen para adjuntar a su Reclamo
            <input type="file" name="imagen"></input>
          </FormGroup>
        </div>
      );
    }

    return (
      <div>
        <Form>
          <h1></h1>
          <DropdownButton
            title={this.state.dondeOcurrio}
            onSelect={this.handleSelectDonde}
          >
            <DropdownItem key={0} eventKey={0}>
              Espacio Comun del Edificio
            </DropdownItem>
            <DropdownItem key={1} eventKey={1}>
              Dentro de la Unidad
            </DropdownItem>
          </DropdownButton>
          <h1></h1>

          {this.state.seleccionoLugar ? (
            <div>
              <DropdownButton
                title={this.state.edificioSeleccionado}
                onSelect={this.handleSelectEdificio.bind(this)}
              >
                {this.state.nombreEdificios.map((nombreEdificio, i) => (
                  <DropdownItem key={i} eventKey={i}>
                    {nombreEdificio}
                  </DropdownItem>
                ))}
              </DropdownButton>
              {mostrar}
              <div>
                <Button onClick={this.agregarReclamo.bind(this)}>
                  Subir Reclamo
                </Button>
              </div>
            </div>
          ) : (
            console.log("nada2")
          )}
        </Form>
        {popup}
      </div>
    );
  }
}

export default FormularioReclamo;

/*  
    var url =
      "http://localhost:8080/reclamapp/getUnidadesPorNombreEdificio?nombre=" +
      this.state.edificioSeleccionado;  
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
      .then(response => response.json())
      .then(unidades => this.setState({ unidades })); 
      */

/*
              <Form.Group>
          <Form.Label as="legend" column sm={2}>
            Su Reclamo es:
          </Form.Label>
          <Form.Check
            type="radio"
            label="Espacio Comun del Edificio"
            name="formHorizontalRadios"
            id="formHorizontalRadios1"
          />
          <Form.Check
            type="radio"
            label="Dentro de la Unidad"
            name="formHorizontalRadios"
            id="formHorizontalRadios2"
          />
        </Form.Group>
         */
