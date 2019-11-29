import React from "react";
import AgregarReclamoPage from "./Pages/AgregarReclamoPage";
import { Route, BrowserRouter as Router } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

const routing = (
  <Router>
    <div>
      <Route path="/agregarReclamo" component={AgregarReclamoPage} />
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
      return <img alt="..."></img>; //Ver por que no esta llamando a la imagen de carga
    } else {
      return routing;
    }
  }
}

function demoAsyncCall() {
  return new Promise(resolve => setTimeout(() => resolve(), 2500));
}

export default App;
