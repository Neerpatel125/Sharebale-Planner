import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Switch, Route, Link} from'react-router-dom';
import LoginPage1 from './components/Login/Login';
import RegisterPage1 from './components/Register/Register';


function App() {
  return (
    <div className="App">
      <header className="App-header">
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
          </Switch>
        </Router>
      </header>
    </div>
  );
}

export default App;
