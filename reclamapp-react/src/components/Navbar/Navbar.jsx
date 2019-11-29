import React from "react";
import { Navbar, Nav } from "react-bootstrap";
import "./Navbar.css";

class NavBar extends React.Component {
  render() {
    return (
      <Navbar bg="dark" variant="dark">
        <Navbar.Text>ReclamApp!</Navbar.Text>
        <Nav.Link href="/login">Mis edificios</Nav.Link>
        <Nav.Link href="/home">Mis Reclamos</Nav.Link>
        <Nav.Link href="/home">Agregar Reclamo</Nav.Link>
      </Navbar>
    );
  }
}

export default NavBar;
