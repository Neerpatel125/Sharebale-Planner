import React from "react";

export default function ListEvent( {event, index, events, setMyEvents} ){
    
    function handleOnClick(){
        events = events.slice(0, index).concat(events.slice(index+1, events.length));
        setMyEvents(events); 
    }

    return(
        <li class="eventsBox__EventsList__ListEvent">
            <button onClick={handleOnClick} class="eventsBox__EventsList__ListEvent__Button">Remove</button>
            {event.eventName}
            {event.eventTime}
        </li>
    );
}