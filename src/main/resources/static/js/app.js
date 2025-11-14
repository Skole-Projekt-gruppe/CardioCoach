import {handleChatSubmit, handleKeyDown} from "./formSubmit.js";
import {getActivities} from "./apiService.js";

window.addEventListener("DOMContentLoaded", initApp);

async function initApp() {
    console.log("App initialized");
    setupEventlisteners();
    await showActivites();
}

function setupEventlisteners() {
    const chatForm = document.querySelector("#chat-form");
    chatForm.addEventListener("submit", handleChatSubmit);
    const form = document.getElementById('chat-form');
    const textarea = form.querySelector('textarea');
    textarea.addEventListener("keydown", handleKeyDown);
    const refreshBtn = document.getElementById('refresh-activities');
    refreshBtn.addEventListener("click",showActivites);
    refreshBtn.addEventListener("click", () => console.log("clicked"));
}

async function showActivites() {
    const aktiviteter = document.getElementById('aktiviteter');
    const activities = await getActivities();

    aktiviteter.innerHTML = " ";

    activities.forEach(a => {
        const li = document.createElement('li');

        li.innerHTML =
            "<strong>name:</strong> " + a.name + "<br>" +
            "<strong>id:</strong> " + a.id + "<br>" +
            "<strong>start_date_local:</strong> " + a.start_date_local + "<br>" +
            "<strong>distance:</strong> " + a.distance + " m<br>" +
            "<strong>moving_time:</strong> " + a.moving_time + " s<br>" +
            "<strong>elapsed_time:</strong> " + a.elapsed_time + " s<br>" +
            "<strong>total_elevation_gain:</strong> " + a.total_elevation_gain + " m<br>" +
            "<strong>type:</strong> " + a.type + "<br>" +
            "<strong>average_speed:</strong> " + a.average_speed + " m/s<br>" +
            "<strong>max_speed:</strong> " + a.max_speed + " m/s<br>" +
            "<strong>average_cadence:</strong> " + a.average_cadence + " rpm<br>" +
            "<strong>average_watts:</strong> " + a.average_watts + " W<br>" +
            "<strong>max_watts:</strong> " + a.max_watts + " W<br>" +
            "<strong>weighted_average_watts:</strong> " + a.weighted_average_watts + " W<br>" +
            "<strong>kilojoules:</strong> " + a.kilojoules + " kJ<br>" +
            "<strong>has_heartrate:</strong> " + a.has_heartrate + "<br>" +
            "<strong>average_heartrate:</strong> " + a.average_heartrate + " bpm<br>" +
            "<strong>max_heartrate:</strong> " + a.max_heartrate + " bpm<br>" +
            "<strong>elev_high:</strong> " + a.elev_high + " m<br>" +
            "<strong>elev_low:</strong> " + a.elev_low + " m";

        aktiviteter.appendChild(li);
    });
}

