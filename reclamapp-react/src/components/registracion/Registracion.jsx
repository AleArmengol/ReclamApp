import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Button, FormControl, Form } from "react-bootstrap";
import "../registracion/Registracion.css";

export default class Registracion extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      usuario: "",
      documento: "",
      contra: "",
      contraRepetida: "",
      validated: false,
      error: false,
      errormsg: ""
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit = event => {
    console.log("hola");
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
      console.log("no validado");
    } else {
      console.log("validado");
      event.preventDefault();
      event.stopPropagation();
      if (this.state.contra !== this.state.contraRepetida) {
        console.log("No coinciden las contra");
        this.setState({
          error: true,
          errormsg: "Las contraseñas deben coincidir"
        });
      } else {
        console.log("validado y coinciden las contra");
        var url =
          "http://localhost:8080/reclamapp/verificarIdUsuario?idUsuario=" +
          this.state.usuario;
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
            return response.text();
          })
          .then(res => {
            if (res === "Success") {
              var url2 =
                "http://localhost:8080/reclamapp//verificarDocumentoUsuario?documento=" +
                this.state.documento;
              fetch(url2, {
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
                  return response.text();
                })
                .then(res => {
                  if (res === "Success") {
                    var urlPost =
                      "http://localhost:8080/reclamapp/registrarUsuario?idUsuario=" +
                      this.state.usuario +
                      "&documento=" +
                      this.state.documento +
                      "&password=" +
                      this.state.contra;
                    fetch(urlPost, {
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
                      .then(res => {
                        if (res === "Success") {
                          console.log("El Usuario se registro correctamente");
                          window.open("/", "_top");
                        }
                      });
                  }
                });
            }
          });
      }
    }
  };

  render() {
    return (
      <Form
        noValidate
        validated={this.state.validated}
        onSubmit={this.handleSubmit}
      >
        <Container className="mt-3">
          <label className="Label-Registro">Crea tu cuenta</label>
          <label className="Label-Registro2">
            y empezá a subir tus reclamos ...
          </label>
          <FormControl
            className="usuarioR"
            name="usuario"
            onChange={this.handleChange}
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            placeholder="Ingrese un nombre de usuario"
            required
            inputRef={ref => {
              this.usuario = ref;
            }}
          />
          <FormControl
            className="dni"
            name="documento"
            aria-label="Default"
            onChange={this.handleChange}
            aria-describedby="inputGroup-sizing-default"
            placeholder="Ingrese su dni"
            required
            inputRef={ref => {
              this.documento = ref;
            }}
          />
          <FormControl
            type="password"
            className="ContraseñaR"
            name="contra"
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            onChange={this.handleChange}
            placeholder="Ingrese una contraseña"
            required
            inputRef={ref => {
              this.contra = ref;
            }}
          />
          <FormControl
            type="password"
            className="repetirContraseña"
            name="contraRepetida"
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            placeholder="Repetir contraseña"
            onChange={this.handleChange}
            required
            inputRef={ref => {
              this.contraRepetida = ref;
            }}
          />
          <Button
            type="submit"
            variant="outline-light"
            className="btn-registro"
          >
            Registrar
          </Button>
        </Container>
      </Form>
    );
  }
}
