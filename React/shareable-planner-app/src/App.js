import React, { useState } from "react";
import './App.css';
import HomePage from "./HomePage"
import {BrowserRouter as Router, Switch, Route} from'react-router-dom';
import LoginPage1 from './Login';
import RegisterPage1 from './Register';


function App() { 
  const [myPersonID, setPersonID] = useState();
  return(
    <Router>
          <Switch>
            <Route path="/" exact>
              <LoginPage1 setPersonID={setPersonID}/>
              {/* <Link to='/Register'>Register</Link> */}
            </Route>
            <Route path="/Register">
              <RegisterPage1/>
              {/* <Link to='/'>Login</Link> */}
            </Route>
            <Route path="/Home">
              <HomePage myPersonID={myPersonID} setPersonID={setPersonID}/>
              {/* <Link to="/Home">Home</Link>} */}
            </Route>
          </Switch>
        </Router>
  );
}

export default App;

