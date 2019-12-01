import React from "react";
import { Table } from 'react-bootstrap';
import './MisReclamos.css';

class MisReclamos extends React.Component {
    render() {
        var ReclamosComun = [
            { idReclamo: 1000, documento: 'DNI41065529', codigoE: '1000', descripcion: 'aaa', estado: "nuevo" },
            { idReclamo: 1000, documento: 'DNI10203040', codigoE: '2000', descripcion: 'bbb', estado: "nuevo" },
            { idReclamo: 1000, documento: 'DNI41065529', codigoE: '1000', descripcion: 'ccc', estado: "nuevo" },
        ]
        var ReclamosUnidad = [
            { idReclamo: 1000, idUnidad: 10, documento: 'DNI41065529', codigoE: '1000', descripcion: '', estado: "nuevo" },
            { idReclamo: 1000, idUnidad: 10, documento: 'DNI10203040', codigoE: '2000', descripcion: '', estado: "nuevo" },
            { idReclamo: 1000, idUnidad: 10, documento: 'DNI41065529', codigoE: '1000', descripcion: '', estado: "nuevo" },
        ]
        return (
            <div>
                {ReclamosComun.map((reclamo, i) => (
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
                                    <td>{reclamo.idReclamo}</td>
                                    <td>{reclamo.descripcion}</td>
                                    <td>{reclamo.estado}</td>
                                </tr>
                            </tbody>
                        </Table>
                    </div>
                ))
                }
                {ReclamosUnidad.map((reclamo, i) => (
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
                                    <td>{reclamo.idReclamo}</td>
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
export default MisReclamos;
