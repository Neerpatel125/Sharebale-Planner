import React, { useState } from "react"
import Calendar from "react-calendar"
import './App.css';

function App() {
  const [value, onChange] = useState(new Date());
  
  return (
    <>
    <h1 class = "Title">
      Shareable Planner
    </h1>
    <div>
      <Calendar onChange = {onChange} value = {value}></Calendar>
    </div>
    </>
  );
}

export default App;
