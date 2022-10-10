import React, { useState } from "react";
import Calendar from "react-calendar";
import './App.css';

function App() {
  const value = useState(new Date());
  
  return (
    <>
    {/* HTML for the Title */}
    <h1 class = "Title">
      Shareable Planner
    </h1>

    {/* HTML for the Calendar */}
    <div class = "calendar">
      <Calendar value={value} minDetail="month" 
        next2Label={null} prev2Label={null} showNeighboringMonth={false} > </Calendar>
    </div>

    {/* HTML for the Events List Box */}
    <div class="eventsBox">
      <div class="eventsBox__title">
        <h2>Events for Selected Day</h2>
      </div>
      <ul class="eventsList"></ul>
    </div>

    {/* HTML for the Add Events Box */}
    <div class="addBox">
      <div class="addBox__title">
        <h2>Add Events for Selected Day</h2>
      </div>
      <div class="addBox__content">
        <h3>Enter the name of the event: </h3>
        <input placeholder="Event Name"></input>
        <h3>Enter names of people to invite</h3>
        <input placeholder="Name1, Name2, ..."></input>
        <br></br> <br></br>
        <button class="addEventButton">Add Event</button>
      </div>
    </div>

    </>
  );
}

export default App;

