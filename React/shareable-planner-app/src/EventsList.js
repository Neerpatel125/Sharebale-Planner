import React from "react";
import ListEvent from "./ListEvent"

export default function EventsList( {events} ){
    return(
        <ul class="eventsBox__EventsList">
            {events.map((event, index) => {
                return <ListEvent key={index} event={event}/>            
            }) }
        </ul>
    );
}