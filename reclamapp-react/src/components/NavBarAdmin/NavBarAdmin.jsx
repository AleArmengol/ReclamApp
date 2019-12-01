import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import '../NavBarAdmin/NavBarAdmin.css';
import {Container,Navbar,Nav, Button} from "react-bootstrap";
import ExitToAppIcon from '@material-ui/icons/ExitToApp';

export default class LogIn extends React.Component {
    render() {
        return (
            
            <Container className="mt-3">
                <Navbar className = "nav">
                <ExitToAppIcon href= "/Principal" type = "button" className="logout"></ExitToAppIcon>
                </Navbar>
                
            </Container>
        );
    }
}