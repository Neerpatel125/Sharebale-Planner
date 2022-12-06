import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import { Link } from "react-router-dom";
import './App.css';
import EventsTable from "./EventsTable";

export default function HomePage(myPersonID){
  
  //  How to get things from the backend. To put things, change "Get" to "Put"
  /*
  const [data, setData] = useState(); 
  async function getPersons(userName){
    const response = await fetch("/persons" + "/" + userName, {
      method: "Get",
      headers: {
        "Accept": "application/json", 
        "Content-Type": "application/json"
      },
    });
    const body = await response.json();
    setData(body);
    console.log(body._embedded.personList[0].userName)
  }
  */

  async function fetchFromSchedule(dayString, myPersonID){
    const response = await fetch("/schedules/" + dayString + "/" + myPersonID, {
      method: "Get",
      headers: {
        "Accept": "application/json", 
        "Content-Type": "application/json"
      },
    });
    const body = await response.json();
    console.log(body);
    return body._embedded; 
  }

  async function fetchFromScheduleID(event){
    const url = "/schedules/" + event.personID + "/" + event.date + "/" + event.time + "/" + event.name
    const response = await fetch(url, {
      method: "Get",
      headers: {
        "Accept": "application/json", 
        "Content-Type": "application/json"
      },
    });
    const body = await response.json();
    console.log(body);
    // return body._embdedded.eventsList.ID? 
  }

  async function fetchFromInvites(inviteeID){
    const response = await fetch("/invites/invitee/" + inviteeID, {
      method: "Get",
      headers: {
        "Accept": "application/json", 
        "Content-Type": "application/json"
      },
    });
    const body = await response.json();
    console.log(body);
    // return body._embedded; 
  }

  async function fetchFromPersons(personID){
    const response = await fetch("/persons/id/" + personID, {
      method: "Get",
      headers: {
        "Accept": "application/json", 
        "Content-Type": "application/json"
      },
    });
    const body = await response.json()
    return body._embedded; 
  }

  async function sendToSchedule(event){
    const response = await fetch("/schedules", {
      method: "Put",
      headers: {
        "Accept": "application/json", 
        "Content-Type": "application/json"
      },
      body: JSON.stringify(event), 
    });
    console.log("Sent to backend")
  }

  async function sendToInvites(invite){
    const response = await fetch("/invites", {
      method: "Put",
      headers: {
        "Accept": "application/json", 
        "Content-Type": "application/json"
      },
      body: JSON.stringify(invite),
    });
    console.log("Sent to backend")
  }

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
  /* Updates stringSelectedDay whenver selectedDay changes.  
    Note: When selectedDay changes, you should load the events for the new day from the databse into myEvents,
    then <EventsList events={myEvents}/> will update the list to display events for the new day upon re-rendering. 
  */
  useEffect(() => {
    const splitSelectedDay = selectedDay.toString().split(" ").slice(0, 4);
    setStringSelectedDay(splitSelectedDay.map( (word) => word = word + " "));
  }, [selectedDay]);
  
  return(
    <> 
    <button onClick={sendToSchedule({personId: 1, date: stringSelectedDay, time: 12-2, name: testingAdding})}>Testing Adding</button> 
    <button onClick={sendToInvites({inviter: 1, invitee: 3, scheulde: 2})}>Testing Inviting</button>
    <button onClick={fetchFromSchedule("10 4 2022", 2) }>Testing Getting an Event</button>
    <button onCLick={fetchFromScheduleID({personID: 1, date: "12 15 2022", time: "12pm-5pm", name: "bday"})}>Testing Getting ID of Event</button>
    <button onCLick={fetchFromInvites(2)}>Testing Getting Invitee</button>
    <button onClick={fetchFromPersons(1)}>Testing Getting PersonID</button>

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
      <EventsTable events={myEvents} setEvents={setMyEvents}/>
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
    <button class="LogoutButton"><Link to="/">Logout</Link> </button>
    </>
    );
}