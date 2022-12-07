import React from "react";

export default function EventsTableRow( {event, index, events, setMyEvents, 
    removeFromSchedule, eventsInvites, setEventsInvites, myPersonID, 
    fetchFromInvitesByInviteeIdAndScheduleId, removeFromInvitesByID} ){

    async function handleOnClick(){
        // Remove the event from the Schedule Table
        if (myPersonID === event.personId.id){
            await removeFromSchedule(event.id);
        }
        else{
            const invite = await fetchFromInvitesByInviteeIdAndScheduleId(myPersonID, event.id);
            await removeFromInvitesByID(invite.id); 
        }
        // Remove the event from myEvents
        events = events.slice(0, index).concat(events.slice(index+1, events.length));
        setMyEvents(events);
        eventsInvites = eventsInvites.slice(0, index).concat(eventsInvites.slice(index+1, eventsInvites.length));
        setEventsInvites(eventsInvites);
    }

    return(
        <tr class="eventsBox__Table__Row">
            <td> 
                <button onClick={handleOnClick} class="eventsBox__Table__Row__Button">Remove</button>
                {event.name}
            </td>
            <td>{event.time}</td>
            <td>
                {eventsInvites[index]}
            </td>
        </tr>
    );
}