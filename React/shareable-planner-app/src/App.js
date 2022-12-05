import React from "react";
import './App.css';
import HomePage from "./HomePage"
import {BrowserRouter as Router, Switch, Route, Link} from'react-router-dom';
import LoginPage1 from './Login';
import RegisterPage1 from './Register';


function App() {
  // const url = "http://localhost:8080"; 
  
  const response = fetch("/persons");  
  const body = response.json(); 
  console.log(body);
  
  return(
    <Router>
          <Switch>
            <Route path="/" exact>
              <LoginPage1/>
              {/* <Link to='/Register'>Register</Link> */}
            </Route>
            <Route path="/Register">
              <RegisterPage1/>
              {/* <Link to='/'>Login</Link> */}
            </Route>
            <Route path="/Home">
              <HomePage/>
              {/* <Link to="/Home">Home</Link>} */}
            </Route>
          </Switch>
        </Router>
  );
}

export default App;

