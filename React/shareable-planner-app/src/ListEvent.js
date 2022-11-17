import React from "react";

export default function ListEvent( {event} ){
    return(
        <li class="eventsBox__EventsList__ListEvent">
            {event.eventName} {event.eventTime}
        </li>
    );
}