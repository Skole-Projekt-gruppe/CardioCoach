# CardioCoach - Powered By Strava

CardioCoach is an application that combines your Strava data with OpenAI's intelligence. It allows you to fetch your workout data via the Strava API and interact with ChatGPT directly to gain insights, analyses, and personalized advice based on your activity.

## 🚀 Features

- Strava API Integration – Fetch your running, cycling, and other activity data.
- Chat with CardioCoach – Ask questions about your workouts and get instant answers.
- Personalized Insights – Analyze pace, heart rate, distance, and trends over time.
- Data-Driven Coaching – Receive suggestions for improvement and training goals.

---

## Get Started

### ChatGPT

Set up your OpenAI API key and endpoint in a `.env` file:

chatgpt.api.key=YOUR_OPENAI_API_KEY
chatgpt.api.baseUrl=https://api.openai.com/v1

### Strava

1. Create an API application on [Strava API settings](https://www.strava.com/settings/api).
2. Authorize your app by opening this URL (replace YOUR_CLIENT_ID with your client ID):

http://www.strava.com/oauth/authorize?client_id=YOUR_CLIENT_ID&response_type=code&redirect_uri=http://localhost/exchange_token&approval_prompt=force&scope=read_all,activity:read_all,profile:read_all

3. Click Authorize. You will be redirected to a URL like:

http://localhost/exchange_token?state=&code=YOUR_CODE&scope=read,activity:read_all,profile:read_all,read_all

4. Copy the code from the URL.
5. Exchange the code for an access token and refresh token using the Strava API.
6. Store your Strava credentials in your `.env` file:

# Strava API
strava.api.baseUrl=https://www.strava.com/api/v3
strava.api.authUrl=https://www.strava.com

# Strava credentials
strava.access-token=YOUR_ACCESS_TOKEN
strava.refresh-token=YOUR_REFRESH_TOKEN
strava.client-id=YOUR_CLIENT_ID
strava.client-secret=YOUR_CLIENT_SECRET

---

Now your CardioCoach app is ready to fetch Strava data and interact with ChatGPT!
