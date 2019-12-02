import React from "react";
import AgregarReclamoPage from "./Pages/AgregarReclamoPage";
import MisEdificiosPage from "./Pages/MisEdificiosPage";
import MisReclamosPage from "./Pages/MisReclamosPage";
import ReclamosEdificioPage from "./Pages/ReclamosEdificioPage";
import ReclamosAdminPage from "./Pages/ReclamosAdminPage";
import PrincipalPage from "./Pages/PrincipalPage";
import { Route, BrowserRouter as Router } from "react-router-dom";
import { Spinner } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import LogInPage from "./Pages/LogInPage";
import ReclamoBuscadoPage from "./Pages/ReclamoBuscadoPage";

const routing = (
  <Router>
    <div>
      <Route exact path="/" component={LogInPage} />
      <Route path="/agregarReclamo" component={AgregarReclamoPage} />
      <Route path="/MisEdificios" component={MisEdificiosPage} />
      <Route path="/MisReclamos" component={MisReclamosPage} />
      <Route path="/ReclamosEdificio" component={ReclamosEdificioPage} />
      <Route path="/ReclamosAdmin" component={ReclamosAdminPage} />
      <Route path="/Principal" component={PrincipalPage} />
      <Route path="/ReclamoBuscado" component={ReclamoBuscadoPage} />
    </div>
  </Router>
);

class App extends React.Component {
  state = {
    loading: true
  };

  componentDidMount() {
    demoAsyncCall().then(() => this.setState({ loading: false }));
  }

  render() {
    const { loading } = this.state;

    if (loading) {
      return <Spinner animation="grow" variant="warning" />; //Ver por que no esta llamando a la imagen de carga
    } else {
      return routing;
    }
  }
}

function demoAsyncCall() {
  return new Promise(resolve => setTimeout(() => resolve(), 2500));
}

export default App;
