import React from "react";
import ListEvent from "./ListEvent"

export default function EventsList( {events, setEvents} ){
    return(
        <ul class="eventsBox__EventsList">
            {events.map((event, index) => {
                return <ListEvent key={index} index={index} events={events} event={event} setMyEvents={setEvents} />            
            }) }
        </ul>
    );
}