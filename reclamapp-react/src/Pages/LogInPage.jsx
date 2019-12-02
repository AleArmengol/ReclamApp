import React from "react";
import NavBar from "../components/Navbar/Navbar";
import LogIn from "../components/logIn/LogIn";
import Registracion from "../components/registracion/Registracion";

function LogInPage() {
  return (
    <div>
      <LogIn></LogIn>

      <Registracion></Registracion>
    </div>
  );
}

export default LogInPage;
