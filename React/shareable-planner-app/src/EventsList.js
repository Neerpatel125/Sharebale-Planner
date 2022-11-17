import React from "react";
import ListEvent from "./ListEvent"

export default function EventsList( {events} ){
    /* Should get the event info for the selected day from the database.
        After hooked up to data base, change "events" param to "selectedDay"
        and use that to get the event information
    */ 
    return(
        <ul class="eventsBox__EventsList">
            {events.map((event, index) => {
                return <ListEvent key={index} event={event}/>            
            }) }
        </ul>
    );
}