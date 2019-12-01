import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container,Button, FormControl} from "react-bootstrap";
import '../registracion/Registracion.css';


export default class Registracion extends React.Component {
    render() {
        return (

            <Container className="mt-3">
            <label className = "Label-Registro">Crea tu cuenta</label>
            <label className = "Label-Registro2">y empezá a subir tus reclamos ...</label>
            <FormControl
            className = "usuarioR"
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            placeholder="Ingrese un nombre de usuario"
        />
        <FormControl
            className = "dni"
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            placeholder="Ingrese su dni"
        />
            <FormControl
            type="password"
            className = "ContraseñaR"
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            placeholder="Ingrese una contraseña"
            />
             <FormControl
                 type="password" 
                 className="repetirContraseña" 
                 aria-label="Default"
                 aria-describedby="inputGroup-sizing-default"
                 placeholder="Repetir contraseña"
             />
        <Button type="submit" variant="outline-light" className="btn-registro">Registrar</Button>
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
