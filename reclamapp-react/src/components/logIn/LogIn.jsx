import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import '../logIn/LogIn.css';
import {Container,Image,Button, FormControl} from "react-bootstrap";
import {Navbar} from "react-bootstrap";
import logo from '../../photos/logo.png'




export default class LogIn extends React.Component {
  constructor(props){
    super(props);
    
    this.state = {
      usuario: "",
      Contraseña: "",
      loginErrors:""
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event){
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit(event) {
    const {usuario, Contraseña} = this.state;
  }


    render() {
        return (
            
            <Container className="mt-3">
            {/* <Row className="mx-auto"> */}
            <Navbar className="navbar-style">
            </Navbar>
            <Image className="Logo" src={logo} alt="El logo" />
            <label className = "Label-App">ReclamApp</label>
            <label className = "Label-Usuario">Usuario</label>
            <label className = "Label-Contraseña">Contraseña</label>

            <FormControl
            className = "usuario"
            name = "usuario"
            value={this.state.usuario}
            onChange={this.handleChange}
            required
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            />
            <FormControl
            type="password"
            className = "Contraseña"
            name = "Contraseña"
            value={this.state.Contraseña}
            onChange={this.handleChange}
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            />
            <Button href= "/Home" type="submit" variant="outline-light" className="btn-ingresar">Ingresar</Button>
            </Container>
        );
    }
    myFunction() {
        var x = document.getElementById("myInput");
        if (x.type === "password") {
          x.type = "text";
        } else {
          x.type = "password";
        }
      }
}

