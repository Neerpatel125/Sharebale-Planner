import React from "react";

export default function EventsTableRow( {event, index, events, setMyEvents, removeFromSchedule, fetchFromInvitesByScheduileId} ){
    
    async function handleOnClick(){
        // Remove the event from the Schedule Table
        await removeFromSchedule(event); 
        // Remove the event from myEvents
        events = events.slice(0, index).concat(events.slice(index+1, events.length));
        setMyEvents(events); 
    }
    async function getAllInvited(){
        const allInvites = await fetchFromInvitesByScheduileId(event.id);
        const allInvited = allInvites.map((e) => e.invitee.userName + " "); 
        const invitedString = allInvited.join().replaceAll(",", "");
        return invitedString; 
    }

    return(
        <tr class="eventsBox__Table__Row">
            <td> 
                <button onClick={handleOnClick} class="eventsBox__Table__Row__Button">Remove</button>
                {event.name}
            </td>
            <td>{event.time}</td>
            <td>
                {getAllInvited()}
            </td>
        </tr>
    );
}