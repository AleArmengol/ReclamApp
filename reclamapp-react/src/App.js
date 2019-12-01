import React from "react";
import AgregarReclamoPage from "./Pages/AgregarReclamoPage";
import PrincipalPage from "./Pages/PrincipalPage";
import { Route, BrowserRouter as Router } from "react-router-dom";
import {Spinner} from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

const routing = (
  <Router>
    <div>
      <Route exact path="/" component={PrincipalPage} />
      <Route path="/agregarReclamo" component={AgregarReclamoPage} />
      <Route path="/ReclamosAdmin" component={ReclamosAdminPage} />
      
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
      return  <Spinner animation="grow" variant="warning" />; //Ver por que no esta llamando a la imagen de carga
    } else {
      return routing;
    }
  }
}

function demoAsyncCall() {
  return new Promise(resolve => setTimeout(() => resolve(), 2500));
}

export default App;
