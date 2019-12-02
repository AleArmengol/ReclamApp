import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../logIn/LogIn.css";
import { Container, Image, Button, FormControl, Form } from "react-bootstrap";
import { Navbar } from "react-bootstrap";
import logo from "../../photos/logo.png";

export default class LogIn extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      usuario: "",
      Contraseña: "",
      loginErrors: "",
      validated: false
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
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
      event.preventDefault();
      event.stopPropagation();
      var urlLogIn =
        "http://localhost:8080/reclamapp/logInUsuario?idUsuario=" +
        this.state.usuario +
        "&password=" +
        this.state.Contraseña;
      fetch(urlLogIn, {
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
          if (res === "Error") {
            console.log("El usuario y/o la contraseña no son las correctas");
          } else {
            var documento = res.documento;
            if (documento === "admin") {
              sessionStorage.setItem("documento", documento);
              window.open("/ReclamosAdmin", "_top");
            } else {
              sessionStorage.setItem("documento", documento);
              window.open("/Principal", "_top");
            }
          }
        });
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
          {/* <Row className="mx-auto"> */}
          <Navbar className="navbar-style"></Navbar>
          <Image className="Logo" src={logo} alt="El logo" />
          <label className="Label-App">ReclamApp</label>
          <label className="Label-Usuario">Usuario</label>
          <label className="Label-Contraseña">Contraseña</label>

          <FormControl
            className="usuario"
            name="usuario"
            onChange={this.handleChange}
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            required
            inputRef={ref => {
              this.usuario = ref;
            }}
          />
          <FormControl
            required
            type="password"
            className="Contraseña"
            name="Contraseña"
            onChange={this.handleChange}
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            inputRef={ref => {
              this.password = ref;
            }}
          />
          <Button
            type="submit"
            variant="outline-light"
            className="btn-ingresar"
          >
            Ingresar
          </Button>
        </Container>
      </Form>
    );
  }
}
