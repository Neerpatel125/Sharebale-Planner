import React from "react";
import EventsTableRow from "./EventsTableRow";

export default function EventsTable( {events, setEvents, removeFromSchedule, eventsInvites, setEventsInvites} ){
    return(
        <table class="eventsBox__Table">
            <thead class="eventsBox__Table__Head">
                <th>Name</th>
                <th>Time</th>
                <th>Attending</th>
            </thead>
            <tbody class="eventsBox__Table__Body">
                {events.map((event, index) => {
                    return <EventsTableRow key={index} index={index} events={events} event={event} 
                        setMyEvents={setEvents} removeFromSchedule={removeFromSchedule} 
                        eventsInvites={eventsInvites} setEventsInvites={setEventsInvites}/>            
                })}
            </tbody>
        </table>
    );
}