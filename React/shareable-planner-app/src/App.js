import React, { useState } from "react";
import Calendar from "react-calendar";
import './App.css';

function App() {
  /* Get the current date for the calendar */
  const value = useState(new Date());
  /* Open the GMU events website function */
  const gmuEvents = () => {
    window.open("https://mason360.gmu.edu/events", "_blank");
  }
  /* Store input information for adding events */
  
  
  return (
    <>
    {/* HTML for the Title */}
    <h1 class = "Title">Shareable Planner</h1>

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
        <h3>Enter the time of the event: </h3>
        <input placeholder="12:00 PM to 1:00 PM"></input>
        <h3>Enter names of people to invite</h3>
        <input placeholder="Name1, Name2, ..."></input>
        <br></br> <br></br>
        <button class="addEventButton">Add Event</button>
      </div>
    </div>

    {/* HTML for GMU Events Button */}
    <button onClick={() => gmuEvents()} class="gmuEventsButton">GMU Events</button>
    </>
  );
}

export default App;

