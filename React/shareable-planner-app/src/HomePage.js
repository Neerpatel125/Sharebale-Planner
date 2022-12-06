import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import { Link } from "react-router-dom";
import './App.css';
import EventsTable from "./EventsTable";

export default function HomePage( {myPersonID} ){

  async function fetchFromSchedule(dayString, personID){
    const response = await fetch("/schedules/" + dayString + "/" + personID, {
      method: "Get",
      headers: {
        "Accept": "application/json", 
        "Content-Type": "application/json"
      },
    });
    const body = await response.json();
    return body; 
  }

  async function fetchFromScheduleToGetId(e){
    const url = "/schedules/" + e.personId["id"] + "/" + e.date + "/" + e.time + "/" + e.name; 
    const response = await fetch(url, {
      method: "Get",
      headers: {
        "Accept": "application/json", 
        "Content-Type": "application/json"
      },
    });
    const body = await response.json();
    return body["id"];
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
    return body; 
  }

  async function fetchFromPersons(userName){
    const response = await fetch("/persons/userName/" + userName, {
      method: "Get",
      headers: {
        "Accept": "application/json", 
        "Content-Type": "application/json"
      },
    });
    const body = await response.json();
    return body; 
  }

  async function fetchFromInvitesByScheduileId(scheduleId){
    const response = await fetch("/invites/schedule/" + scheduleId, {
      method: "Get",
      headers: {
        "Accept": "application/json", 
        "Content-Type": "application/json"
      },
    });
    const body = await response.json();
    return body;
  }

  async function sendToSchedule(event){
    await fetch("/schedules", {
      method: "Post",
      headers: {
        "Accept": "application/json", 
        "Content-Type": "application/json"
      },
      body: JSON.stringify(event), 
    });
  }

  async function sendToInvites(invite){
    await fetch("/invites", {
      method: "Post",
      headers: {
        "Accept": "application/json", 
        "Content-Type": "application/json"
      },
      body: JSON.stringify(invite),
    });
  }

  async function removeFromSchedule(scheduleID){ 
    await removeFromInvites(scheduleID);
    await fetch("/schedules/" + scheduleID, {
      method: "Delete",
      headers: {
        "Accept": "application/json", 
        "Content-Type": "application/json"
      },
    });
  }

  async function removeFromInvites(scheduleID){
    const allInvites = await fetchFromInvitesByScheduileId(scheduleID);
    for (const elem in allInvites){
      await fetch("/invites/" + allInvites[elem].id, {
        method: "Delete",
        headers: {
          "Accept": "application/json", 
          "Content-Type": "application/json"
        },
      });
    }
  }

  /* Get the current date for the calendar */
  const [selectedDay, setSelectedDay] = useState(new Date());
  const splitSelectedDay = selectedDay.toString().split(" ").slice(0, 4);
  const [stringSelectedDay, setStringSelectedDay] = useState(splitSelectedDay.map( (word) => word = word + " ").join().replaceAll(",", ""));
  
  /* Open the GMU events website function */
  const gmuEvents = () => {
    window.open("https://mason360.gmu.edu/events", "_blank");
  }
  
  /* Store input information for adding events */
  
  async function getTodaysEvents(){
    // Get all events for this user for today from Schedule
    const thisDayEvents= await fetchFromSchedule(stringSelectedDay, myPersonID);
    // Get all events this user is invited to for today
    const allInvited = await fetchFromInvites(myPersonID);
    const allInvitedEvents = allInvited.map((e) => e.schedule);
    // Combine the two lists of events
    const allThisDayEvents = thisDayEvents.concat(allInvitedEvents);
    // Set myEvents to today's events.
    if (allThisDayEvents.length < 1){
      setMyEvents([]);
    }
    else{
      setMyEvents(allThisDayEvents);
    } 
  }

  const [eventName, setEventName] = useState("");
  const [eventTime, setEventTime] = useState("");
  const [eventInvites, setEventInvites] = useState("");
  const [myEvents, setMyEvents] = useState([]);
  getTodaysEvents();

  async function handleAddEvent(){
    const event = {
      personId: {
        "id": myPersonID, 
      },
      date: stringSelectedDay, 
      time: eventTime, 
      name: eventName
    };
    // Add the event to Schedules Table
    await sendToSchedule(event);
    // Now the invites
    const scheduleId = await fetchFromScheduleToGetId(event);
    const peopleToInvite = eventInvites.split(","); 
    for(let i = 0; i < peopleToInvite.length; i++){
      const inviteeId = fetchFromPersons(peopleToInvite[i]).id; 
      await sendToInvites({inviter: {"id": myPersonID}, invitee: {"id": inviteeId}, scheduleId: {"id": scheduleId}}); 
    }
    // Add the event to myEvents
    setMyEvents((prevEvents) =>{
      return [...prevEvents, event];
    });
  }
  // Updates stringSelectedDay whenver selectedDay changes.  
  useEffect(() => {
    const splitSelectedDay = selectedDay.toString().split(" ").slice(0, 4);
    setStringSelectedDay(splitSelectedDay.map( (word) => word = word + " ").join().replaceAll(",", ""));
  }, [selectedDay]);

  // When the stringSelected day changes, load the events for this new day into myEvents.
  // This will cause the EventsBox to update and display the correct events.  
  useEffect( () => {
    getTodaysEvents(); 
  }, // eslint-disable-next-line
  [stringSelectedDay]);

  return(
    <> 
    {/* These are some test cases to ensure that the fetch methods work. 
    <button onClick={() => sendToSchedule({personId: {"id": 1}, date: stringSelectedDay, time: "12-2", name: "testingAdding"})}>Adding</button> 
    <button onClick={() => sendToInvites({inviter: {"id": 1}, invitee: {"id": 3}, schedule: {"id": 1}})}>Inviting</button>
    <button onClick={() => fetchFromSchedule("10 4 2022", 2) }>Getting an Event</button>
    <button onClick={() => fetchFromInvites(2) }>Get Invites</button>
    <button onClick={() => fetchFromPersons("neer")}>Get Person Username</button>
    <button onClick={() => removeFromSchedule(1)}>Remove Event</button>
    <button onClick={() => fetchFromScheduleToGetId({personId: {"id": 1}, date: stringSelectedDay, time: "12-2", name: "testingAdding"})}>Get Event's Id</button>
    <button onClick={() => removeFromInvites(1)}>Remove Invite</button>
    */}

    {/* HTML for the Title */}
    <div class className="HomePage">
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
      <EventsTable events={myEvents} setEvents={setMyEvents} removeFromSchedule={removeFromSchedule}
        fetchFromInvitesByScheduileId={fetchFromInvitesByScheduileId}/>
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
    </div>
    </>
    );
}
