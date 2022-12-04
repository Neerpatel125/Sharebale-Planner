import React from "react";

export default function EventsTableRow( {event, index, events, setMyEvents} ){
    
    function handleOnClick(){
        events = events.slice(0, index).concat(events.slice(index+1, events.length));
        setMyEvents(events); 
    }

    return(
        <tr class="eventsBox__Table__Row">
            <td> 
                <button onClick={handleOnClick} class="eventsBox__Table__Row__Button">Remove</button>
                {event.eventName}
            </td>
            <td>{event.eventTime}</td>
        </tr>
    );
}