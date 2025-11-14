// formSubmit.js
import {askAI} from "./apiService.js";

export async function handleChatSubmit(event) {
    event.preventDefault();
    const form = new FormData(event.target);
    const prompt = form.get("prompt");

    // vis brugerens besked i chatten
    addToChat(prompt, "user");

    event.target.reset();

    const data = await askAI(prompt);

    // svar fra AI til chat
    addToChat(data.response, "assistant");
}

// ⬇️ VIGTIGT: export her, så app.js kan importere den
export function addToChat(message, sender) {
    const messages = document.querySelector("#chat");
    const messageElement = document.createElement("div");
    messageElement.classList.add("message", sender);

    const bubble = document.createElement("div");
    bubble.classList.add("bubble");
    bubble.textContent = message;

    messageElement.appendChild(bubble);
    messages.appendChild(messageElement);

    messages.scrollTop = messages.scrollHeight;
}

export function handleKeyDown(e) {
    const form = document.getElementById('chat-form');
    if (e.key === 'Enter' && !e.ctrlKey && !e.shiftKey) {
        e.preventDefault();   // stop newline
        form.requestSubmit(); // submit form
    }
}