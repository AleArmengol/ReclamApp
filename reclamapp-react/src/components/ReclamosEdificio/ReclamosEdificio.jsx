import React from "react";
import { Table } from 'react-bootstrap';
import './ReclamosEdificio.css';

class ReclamosEdificio extends React.Component {
    render() {
        var Reclamos = [
            { id: 1000, documento: 'DNI41065529', codigoE: '1000', descripcion: 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', estado: "nuevo" },
            { id: 1000, documento: 'DNI10203040', codigoE: '2000', descripcion: 'aaaaaaaaaa', estado: "nuevo" },
            { id: 1000, documento: 'DNI41065529', codigoE: '1000', descripcion: 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', estado: "nuevo" },
        ]
        return (
            <div>
                {Reclamos.map((reclamo, i) => (
                    <div>
                        <Table className="Tabla" striped bordered hover variant="dark" >
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Documento</th>
                                    <th>Id</th>
                                    <th>Descripcion</th>
                                    <th>Estado</th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>{i}</td>
                                    <td>{reclamo.documento}</td>
                                    <td>{reclamo.id}</td>
                                    <td>{reclamo.descripcion}</td>
                                    <td>{reclamo.estado}</td>
                                </tr>
                            </tbody>
                        </Table>
                    </div>
                ))
                }
            </div>

        );
    }
}
export default ReclamosEdificio;
