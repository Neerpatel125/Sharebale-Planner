import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import './App.css';
import EventsList from "./EventsList";

function App() {
  /* Get the current date for the calendar */
  const [selectedDay, setSelectedDay] = useState(new Date());
  const splitSelectedDay = selectedDay.toString().split(" ").slice(0, 4);
  const [stringSelectedDay, setStringSelectedDay] = useState(splitSelectedDay.map( (word) => word = word + " "));
  /* Open the GMU events website function */
  const gmuEvents = () => {
    window.open("https://mason360.gmu.edu/events", "_blank");
  }
  /* Store input information for adding events */
  const [eventName, setEventName] = useState("");
  const [eventTime, setEventTime] = useState("");
  const [eventInvites, setEventInvites] = useState("");
  const [myEvents, setMyEvents] = useState([]); /* Temporary, since currently cannot add to database */

  function handleAddEvent(){
    /* Currently just adds the events to myEvents list, but should send them to the database */
    const event = {eventName, eventTime, eventInvites};
    setMyEvents((prevEvents) =>{
      return [...prevEvents, event];
    });
  }
  /* Update stringSelectedDay whenver selectedDay changes*/
  useEffect(() => {
    const splitSelectedDay = selectedDay.toString().split(" ").slice(0, 4);
    setStringSelectedDay(splitSelectedDay.map( (word) => word = word + " "));
  }, [selectedDay]);
  
  return(
    <>
    {/* HTML for the Title */}
    <h1 class = "Title">Shareable Planner</h1>

    {/* HTML for the Calendar */}
    <div class = "calendar">
      <Calendar value={selectedDay} minDetail="month" 
        next2Label={null} prev2Label={null} showNeighboringMonth={false} 
        onClickDay={(day)=> setSelectedDay(day)}> </Calendar>
    </div>

    {/* HTML for the Events List Box */}
    <div class="eventsBox">
      <div class="eventsBox__title">
        <h2>Events for {stringSelectedDay}</h2>
      </div>
      <EventsList events={myEvents}/>
    </div>

    {/* HTML for the Add Events Box */}
    <div class="addBox">
      <div class="addBox__title">
        <h2>Add Events for {stringSelectedDay}</h2>
      </div>
      <div class="addBox__content">
        <h3>Enter the name of the event: </h3>
        <input onChange={(e) => setEventName(e.target.value)} placeholder="Event Name" value={eventName} ></input>
        <h3>Enter the time of the event: </h3>
        <input onChange={(e) => setEventTime(e.target.value)} placeholder="12:00 PM to 1:00 PM" value={eventTime} ></input>
        <h3>Enter names of people to invite</h3>
        <input onChange={(e) => setEventInvites(e.target.value)} placeholder="Name1, Name2, ..." value={eventInvites}></input>
        <br></br> <br></br>
        <button onClick={handleAddEvent} class="addEventButton">Add Event</button>
      </div>
    </div>

    {/* HTML for GMU Events Button */}
    <button onClick={() => gmuEvents()} class="gmuEventsButton">GMU Events</button>
    </>
  );
}

export default App;

