import {handleChatSubmit, handleKeyDown} from "./formSubmit.js";


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