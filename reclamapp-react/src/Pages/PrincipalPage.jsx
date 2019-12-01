import React from 'react';
import LogIn from '../components/logIn/LogIn';
import Registracion from '../components/registracion/Registracion';

function PrincipalPage() {
  return (
    <div>
      <LogIn/>
      <Registracion/>
    </div>   
  );
}

export default PrincipalPage;