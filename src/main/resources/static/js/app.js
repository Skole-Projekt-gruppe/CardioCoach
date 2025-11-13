import {handleChatSubmit, handleKeyDown} from "./formSubmit.js";
import {getActivities} from "./apiService.js";

window.addEventListener("DOMContentLoaded", initApp);

function initApp() {
    console.log("App initialized");
    setupEventlisteners();
}

function setupEventlisteners() {
    const chatForm = document.querySelector("#chat-form");
    chatForm.addEventListener("submit", handleChatSubmit);
    const form = document.getElementById('chat-form');
    const textarea = form.querySelector('textarea');
    textarea.addEventListener("keydown", handleKeyDown);
}

// === Strava Activities Sidebar ===
const ACT_URL = "/api/strava/activities?per_page=30"; // backend-proxy
const $list = document.getElementById("activities");
const $status = document.getElementById("activities-status");
const $refresh = document.getElementById("refresh-activities");

const metersToKm = m => (m ?? 0) / 1000;
const secondsToHMM = s => {
    if (!s && s !== 0) return "-";
    const h = Math.floor(s / 3600);
    const m = Math.floor((s % 3600) / 60);
    return h ? `${h}h ${m}m` : `${m}m`;
};
const escapeHtml = str => String(str).replace(/[&<>"']/g, s => ({ "&":"&amp;","<":"&lt;",">":"&gt;","\"":"&quot;","'":"&#39;" }[s]));

async function fetchActivities() {
    if (!$list) return;
    $status.textContent = "Loading…";
    try {
        const res = await fetch(ACT_URL, { headers: { "Accept": "application/json" } });
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        const items = await res.json();
        renderActivities(Array.isArray(items) ? items : []);
        $status.textContent = `Updated ${new Date().toLocaleTimeString()}`;
    } catch (e) {
        console.error(e);
        $status.textContent = "Could not load activities.";
    }
}

function renderActivities(items) {
    if (!items.length) {
        $list.innerHTML = `<div class="activity"><div class="dot"></div><div>
      <p class="title">No recent activities</p>
      <p class="meta">Try refreshing in a bit.</p></div></div>`;
        return;
    }
    $list.innerHTML = items.slice(0,30).map(a => {
        const name = a.name ?? (a.sport_type || "Activity");
        const dist = metersToKm(a.distance).toFixed(1) + " km";
        const dur = secondsToHMM(a.moving_time);
        const when = a.start_date ? new Date(a.start_date).toLocaleDateString() : "";
        const sport = a.sport_type ?? "";
        return `<div class="activity" data-id="${a.id}">
      <div class="dot"></div>
      <div>
        <p class="title">${escapeHtml(name)}</p>
        <p class="meta">${sport} • ${dist} • ${dur} • ${when}</p>
      </div>
    </div>`;
    }).join("");
}

document.addEventListener("DOMContentLoaded", fetchActivities);
$refresh?.addEventListener("click", fetchActivities);
setInterval(fetchActivities, 5 * 60 * 1000);