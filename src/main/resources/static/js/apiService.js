const BASE_URL = "http://localhost:8080"

// Add credentials: 'include' if your server requires cookies or authentication
export default async function askAI(prompt) {
    const response = await fetch(BASE_URL+"/api/prompt", {
            method: "POST",
            credentials: 'include',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({prompt: prompt}),
        }
    );
    return await response.json();
}