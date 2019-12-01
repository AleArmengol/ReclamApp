import React from "react";
import { Navbar, Nav } from "react-bootstrap";
import "./Navbar.css";

class NavBar extends React.Component {
  render() {
    return (
      <Navbar bg="dark" variant="dark">
        <Navbar.Text>ReclamApp!</Navbar.Text>
        <Nav.Link href="/MisEdificios">Mis edificios</Nav.Link>
        <Nav.Link href="/MisReclamos">Mis Reclamos</Nav.Link>
        <Nav.Link href="/AgregarReclamo">Agregar Reclamo</Nav.Link>
      </Navbar>
    );
  }
}

export default NavBar;
