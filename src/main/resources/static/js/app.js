// app.js
import {handleChatSubmit, handleKeyDown, addToChat} from "./formSubmit.js";
import {getActivities, askAI} from "./apiService.js";

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
    refreshBtn.addEventListener("click", showActivites);
    refreshBtn.addEventListener("click", () => console.log("clicked"));

    // 👇 NY: klik-listener på aktivitets-listen (bruger event delegation)
    const activitiesList = document.getElementById('aktiviteter');
    activitiesList.addEventListener("click", handleActivityClick);
}

async function showActivites() {
    const aktiviteter = document.getElementById('aktiviteter');
    const activities = await getActivities();

    aktiviteter.innerHTML = "";

    activities.forEach(a => {
        const li = document.createElement('li');
        li.classList.add("aktivitet");

        // --- data-* attributes til AI-coach ---
        li.dataset.name               = a.name || "Ukendt tur";
        li.dataset.distanceKm         = (a.distance / 1000).toFixed(1);
        li.dataset.movingTimeMin      = (a.moving_time / 60).toFixed(0);
        li.dataset.elapsedTimeSec     = a.elapsed_time;
        li.dataset.avgSpeedMs         = (a.average_speed ?? 0).toFixed(3);
        li.dataset.maxSpeedMs         = (a.max_speed ?? 0).toFixed(3);
        li.dataset.avgCadence         = (a.average_cadence ?? 0).toFixed(1);
        li.dataset.avgWatts           = (a.average_watts ?? 0).toFixed(0);
        li.dataset.maxWatts           = (a.max_watts ?? 0).toFixed(0);
        li.dataset.weightedAvgWatts   = (a.weighted_average_watts ?? 0).toFixed(0);
        li.dataset.kilojoules         = (a.kilojoules ?? 0).toFixed(1);
        li.dataset.hasHeartrate       = a.has_heartrate;
        li.dataset.avgHeartrate       = (a.average_heartrate ?? 0).toFixed(1);
        li.dataset.maxHeartrate       = (a.max_heartrate ?? 0).toFixed(1);
        li.dataset.totalElevationGain = (a.total_elevation_gain ?? 0).toFixed(0);
        li.dataset.startDateLocal     = a.start_date_local;

        // --- HTML med din nuværende visning + en ekstra knap øverst ---
        li.innerHTML =
            `<button type="button" class="ask-ai-btn">
                💬 Hvad kan jeg gøre bedre på denne tur?
            </button><br><br>` +

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
            "<strong>elev_low:</strong> " + a.elev_low + " m<br><br>";

        aktiviteter.appendChild(li);
    });
}

// --------- NY: håndter klik på "Hvad kan jeg gøre bedre..." knap ---------

async function handleActivityClick(event) {
    const btn = event.target.closest(".ask-ai-btn");
    if (!btn) return;

    const li = btn.closest("li");
    if (!li) return;

    const d = li.dataset;

    // Vis i chat hvad der sker
    addToChat(
        `Generér en kort rapport for denne tur:\n${d.name} (${d.startDateLocal})`,
        "user"
    );

    const prompt = `
Lav en kort rapport opdelt i tre tydelige afsnit.
Hver sektion skal starte med en overskrift, og teksten må kun være 1–3 linjer pr. sektion.

Sektionerne skal være:
1) Hvad gik godt?
2) Hvad gik mindre godt?
3) Hvad kan jeg forbedre?

Brug turens data til at understøtte vurderingerne, men skriv kort, klart og let at læse.
Sørg for, at hver sektion står i et separat afsnit med line breaks mellem.

Data fra turen:
Navn: ${d.name}
Dato: ${d.startDateLocal}
Distance: ${d.distanceKm} km
Varighed: ${d.movingTimeMin} min
Gns. hastighed: ${d.avgSpeedMs} m/s
Gns. watt: ${d.avgWatts} W (max: ${d.maxWatts} W, NP: ${d.weightedAvgWatts} W)
Kadence: ${d.avgCadence} rpm
Puls: gns ${d.avgHeartrate} bpm, max ${d.maxHeartrate} bpm
Elevation gain: ${d.totalElevationGain} m
Energi: ${d.kilojoules} kJ
`;

    try {
        const data = await askAI(prompt);
        addToChat(data.response, "assistant");
    } catch (err) {
        console.error(err);
        addToChat("Der skete en fejl, da jeg bad om en kort rapport for turen.", "assistant");
    }
}