import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../NavBarAdmin/NavBarAdmin.css";
import {
  Container,
  Navbar,
  Image,
  Button,
  Form,
  FormControl
} from "react-bootstrap";
import ExitToAppIcon from "@material-ui/icons/ExitToApp";
import logo from "../../photos/logo.png";

export default class LogIn extends Component {
  constructor(props) {
    super(props);
    this.state = {
      numeroReclamo: 0,
      seClickeo: true
    };
  }
  logOut = () => {
    window.open("/", "_top");
  };
  handleChange(event) {
    this.setState({
      numeroReclamo: parseInt(event.target.value)
    });
  }

  verReclamoBuscado() {
    sessionStorage.setItem("numeroReclamo", this.state.numeroReclamo);
    window.open("/ReclamoBuscado", "_top");
  }
  render() {
    return (
      <Container className="mt-3">
        <Navbar className="nav">
          <label className="bienvenida">Bienvenido Administrador !</label>

          <ExitToAppIcon
            onClick={this.logOut}
            type="button"
            className="logout"
          ></ExitToAppIcon>
          <div className="buscar-web">
            <Form style={{ marginLeft: "100px" }} inline>
              <FormControl
                inputRef={ref => {
                  this.numeroReclamo = ref;
                }}
                onChange={this.handleChange.bind(this)}
                type="search"
                placeholder="Buscar"
                className="mr-sm-2"
              />
              <Button
                onClick={this.verReclamoBuscado.bind(this)}
                variant="outline-light"
                href="/ReclamoBuscado"
              >
                Ir!
              </Button>
            </Form>
          </div>
        </Navbar>
      </Container>
    );
  }
}
